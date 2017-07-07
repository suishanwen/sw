package com.sw.base.grizzly;

import com.google.inject.Binder;
import com.google.inject.servlet.ServletModule;
import com.sw.base.ApplicationModule;
import com.sw.base.BindingProvider;
import com.sw.base.ContainerCreator;
import com.sw.base.ServletContainer;
import com.sw.base.config.Configuration;
import com.sw.base.servlet.AssetServlet;


import java.util.HashMap;
import java.util.Map;


public class EmbeddedFactory implements ContainerCreator<EmbeddedGrizzly, Configuration>, BindingProvider<EmbeddedGrizzly, Configuration> {

    @Override
    public ServletContainer create(EmbeddedGrizzly annotation, Configuration configuration) {
        return new Embedded(configuration.getHttp());
    }

    @Override
    public void configure(Binder binder, final EmbeddedGrizzly annotation, ApplicationModule<?> module, Configuration configuration) {
        if (annotation.assets().length == 0) return;
        binder.install(new ServletModule() {
            @Override
            protected void configureServlets() {
                for (EmbeddedGrizzly.Asset asset : annotation.assets()) {
                    serve(asset.uri() + "/*").with(new AssetServlet(asset.resource()).setMimeExtensions(toMap(annotation.mimeExtensions())));
                }
            }
        });
    }

    private Map<String, String> toMap(EmbeddedGrizzly.MimeExtension[] mimeExtensions) {
        Map<String, String> map = new HashMap<>();
        for (EmbeddedGrizzly.MimeExtension mimeExtension : mimeExtensions) {
            map.put(mimeExtension.extension(), mimeExtension.mime());
        }
        return map;
    }
}
