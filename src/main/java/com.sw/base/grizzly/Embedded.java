package com.sw.base.grizzly;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sw.base.ServletContainer;
import com.sw.base.config.HttpConfiguration;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.DefaultServlet;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.grizzly.utils.ArraySet;


import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Embedded implements ServletContainer {
    private final HttpServer server;
    private Injector injector;

    public Embedded(HttpConfiguration configuration) {
        server = HttpServer.createSimpleServer(null, configuration.getPort());
        server.getServerConfiguration().setJmxEnabled(true);
    }

    @Override
    public void addServletContext(String name, boolean shareNothing, Module... modules) {
        injector = Guice.createInjector(modules);
        WebappContext context = new WebappContext(name, name);
        context.addFilter("guice", GuiceFilter.class).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), "/*");
        context.addServlet("default", new DefaultServlet(new ArraySet(Embedded.class)) {
        }).addMapping("/*");
        context.addListener(new GuiceServletContextListener() {
            @Override
            protected Injector getInjector() {
                return injector;
            }
        });
        context.deploy(server);
    }

    @Override
    public Injector injector() {
        return injector;
    }

    @Override
    public void start(boolean standalone) throws Exception {
        server.start();
        if (standalone) {
            Thread.sleep(24 * 60 * 60 * 1000);
        }

    }


    @Override
    public void stop() throws Exception {
        server.stop();
    }

}
