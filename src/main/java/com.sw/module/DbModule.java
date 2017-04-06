package com.sw.module;


import com.google.inject.AbstractModule;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.ReJpaPersistModule;
import com.google.inject.servlet.ServletModule;

import javax.persistence.EntityManager;
import java.util.Properties;

public class DbModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
            = new ThreadLocal<EntityManager>();

    public void configure() {
        bind(PersistFilter.class);
        final String[] autoScanPackages = new String[]{"com.sw"};
        install(new ReJpaPersistModule("domain").properties(toProperties()).packages(autoScanPackages));
        install(new ServletModule() {
            @Override
            protected void configureServlets() {
                filter("/api/*").through(PersistFilter.class);
            }
        });

    }

    private Properties toProperties() {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/sw");
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "");
        return properties;
    }

}
