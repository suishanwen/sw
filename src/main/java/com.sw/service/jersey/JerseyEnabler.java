package com.sw.service.jersey;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sw.CorsFilter;
import com.sw.service.Application;
import com.sw.service.jpa.ApplicationModule;
import com.sw.service.jpa.BindingProvider;
import com.sw.service.jpa.Configuration;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.reflections.Reflections;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Joiner.on;
import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

public class JerseyEnabler implements BindingProvider<RestApi, Configuration> {

    @Override
    public void configure(Binder binder, final RestApi annotation, ApplicationModule<?> module, Configuration configuration) {
        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};
        binder.install(new AutoScanningServletModule() {

            @Override
            protected void configureServlets() {
//                bind(GuiceContainer.class);
//                binder().requireExplicitBindings();
//                bind(GuiceFilter.class);
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

//                bind(ResourceConfig.class).toInstance(new MyApplication(on(",").skipNulls().join(packageSet), org.glassfish.jersey.jackson.JacksonFeature.class));
//                I0ResourceConfig i0ResourceConfig = new I0ResourceConfig(on(",").skipNulls().join(packageSet));
//                bind(ResourceConfig.class).toInstance(i0ResourceConfig);
//                ServletContainer servletContainer = new ServletContainer(i0ResourceConfig);
//                serve(annotation.prefix()).with(servletContainer, new ImmutableMap.Builder<String, String>()
//                        .put(ServerProperties.PROVIDER_PACKAGES, on(",").skipNulls().join(packageSet)).build());
                //                serve(annotation.prefix()).with(Application.class);
                bind(CorsFilter.class).in(Singleton.class);
                Map<String, String> params = new HashMap<>();
                params.put(PROPERTY_PACKAGES, "com.sw.api.*");
                filter("/api/*").through(CorsFilter.class);
                serve("/api/*").with(GuiceContainer.class, params);
            }
        });
    }
}
