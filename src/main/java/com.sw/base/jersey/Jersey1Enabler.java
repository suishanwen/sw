//package com.sw.base.jersey;
//
//import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
//import com.google.common.collect.ImmutableMap;
//import com.google.common.collect.ImmutableSet;
//import com.google.inject.Binder;
//import com.sun.jersey.guice.JerseyServletModule;
//import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
//import com.sw.base.ApplicationModule;
//import com.sw.base.BindingProvider;
//import com.sw.base.config.Configuration;
//
//import javax.inject.Singleton;
//
//import static com.google.common.base.Joiner.on;
//import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;
//
//public class Jersey1Enabler implements BindingProvider<RestApi, Configuration> {
//
//    @Override
//    public void configure(Binder binder, final RestApi annotation, ApplicationModule<?> module, Configuration configuration) {
//        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};
//        binder.install(new JerseyServletModule() {
//            @Override
//            protected void configureServlets() {
//                bind(JacksonJaxbJsonProvider.class).in(Singleton.class);
//                ImmutableSet<String> packageSet = ImmutableSet.<String>builder()
//                        .add(annotation.packages().length == 0 ? autoScanPackages : annotation.packages()).build();
//                serve(annotation.prefix()).with(GuiceContainer.class, new ImmutableMap.Builder<String, String>()
//                        .put(PROPERTY_PACKAGES, on(";").skipNulls().join(packageSet)).build());
//            }
//        });
//    }
//}
