package com.sw.base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sw.ResourceModule;
import com.sw.base.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class SwGuiceServletContextListener extends GuiceServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SwGuiceServletContextListener.class);

    private ApplicationModule module;

    public SwGuiceServletContextListener(){
        module = new ResourceModule();
    }

    @Override
    protected Injector getInjector() {
        Configuration configuration = module.configuration();

        for (Object annotation : module.enablers().keySet()){
            FacetEnabler value = (FacetEnabler)module.enablers().get(annotation);
            if (value instanceof StartupTasks)
                ((StartupTasks) value).perform((Annotation) annotation, configuration);
        }

        return Guice.createInjector(module);
    }
}
