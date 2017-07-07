package com.sw.base.jersey;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Binder;
import com.sw.base.ApplicationModule;
import com.sw.base.BindingProvider;
import com.sw.base.config.Configuration;
import com.sw.base.servlet.AutoScanningServletModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.reflections.Reflections;


import javax.ws.rs.Path;

import java.util.Set;

import static com.google.common.base.Joiner.on;

public class JerseyEnabler implements BindingProvider<RestApi, Configuration> {

    @Override
    public void configure(Binder binder, final RestApi annotation, ApplicationModule<?> module, Configuration configuration) {
        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};
        binder.install(new AutoScanningServletModule() {
            @Override
            protected void configureServlets() {
                ImmutableSet<String> packageSet = ImmutableSet.<String>builder()
                        .add(annotation.packages().length == 0 ? autoScanPackages : annotation.packages()).build();
                if(packageSet != null && packageSet.size() > 0){
                    for(String packageName : packageSet){
                        Reflections reflections = new Reflections(packageName);
                        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Path.class);
                        if(classSet != null && classSet.size() > 0){
                            for(Class<?> resouce : classSet){
                                bind(resouce);
                            }
                        }
                    }
                }
                RestResourceConfig restResourceConfig = new RestResourceConfig(on(",").skipNulls().join(packageSet));
                bind(ResourceConfig.class).toInstance(restResourceConfig);
                serve(annotation.prefix()).with(new ServletContainer(restResourceConfig), new ImmutableMap.Builder<String, String>()
                        .put(ServerProperties.PROVIDER_PACKAGES, on(";").skipNulls().join(packageSet)).build());
            }
        });
    }
}
