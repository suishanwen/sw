package com.sw.base.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.sw.base.util.ClassScanner;
import com.sw.base.GuiceModule;
import com.sw.base.ApplicationModule;
import com.sw.base.BindingProvider;
import com.sw.base.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static com.google.common.base.Joiner.on;
import static com.sw.base.util.TypePredicates.isModule;

public class GuiceModuleEnabler implements BindingProvider<GuiceModule, Configuration> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationModule.class);

    @Override
    public void configure(Binder binder, GuiceModule annotation, ApplicationModule<?> module, Configuration configuration) {

        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};

        String[] packages = annotation.packages().length == 0 ? autoScanPackages : annotation.packages();

        logger.info("Scanning for Guice module classes in packages:\n  {}", on("\n  ").join(packages));

        ClassScanner scanner = new ClassScanner(packages);
        Set<Class<?>> moduleClasses = scanner.findBy(isModule);
        for (Class<?> moduleClass : moduleClasses)
            try {
                binder.install((Module) moduleClass.getConstructor().newInstance());
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.warn("Can not instantiate module class '" + moduleClass.getName() + "'", e);
            }

        logger.info(moduleClasses.isEmpty() ? ("No module classes found") : ("Module classes found:\n  {}"),
                on("\n  ").join(moduleClasses));
    }
}
