package com.sw;


import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sw.service.Application;
import com.sw.service.jersey.RestApi;
import com.sw.service.jpa.ApplicationModule;
import com.sw.service.jpa.Configuration;
import com.sw.service.jpa.JpaConfiguration;
import com.sw.service.jpa.JpaPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;
import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

@Application(value = "sw")
@RestApi
@JpaPersist(unit = "domain")
public  class ResourceModule extends ApplicationModule<JpaConfiguration>  {
    private static final String DEFAULT_CONFIG_FOLDER = "./";
    private static final String DEFAULT_YML_FILENAME = "config.yml";
    private static Logger logger = LoggerFactory.getLogger(ResourceModule.class);
    private static JpaConfiguration jpaConfiguration;
    public ResourceModule() {
        super();
    }

    @Override
    protected JpaConfiguration createDefaultConfiguration(Configuration.ConfigurationBuilder config) {
        try {
            URL configFile = this.getClass().getClassLoader().getResource(DEFAULT_CONFIG_FOLDER + DEFAULT_YML_FILENAME);
            if (configFile != null) {
                logger.info("Reading configuration from project config");
                jpaConfiguration = readConfigurationFromFile(configFile.getFile());
            } else {
                logger.warn("config file in project url is null");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jpaConfiguration;
    }

    private JpaConfiguration readConfigurationFromFile(String path) {
        JpaConfiguration jpaConfiguration = null;
        try {
            File file = new File(path);
            if (logger.isInfoEnabled()) {
                logger.info("Reading configuration from file {}", file.getCanonicalPath());
            }
            if (file.exists()) {
                jpaConfiguration = Configuration.read(new FileInputStream(file), JpaConfiguration.class);
            } else {
                logger.warn("config file {} not exist", file.getCanonicalPath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return jpaConfiguration;
    }


//    @Override
//    protected void configureServlets() {
//
//        bind(GuiceContainer.class);
//        binder().requireExplicitBindings();
//        bind(GuiceFilter.class);
//
////        bind Facade
//        bind(SequenceFacade.class);
//        bind(NoteFacade.class);
//        bind(GameRecordFacade.class);
//        bind(AdminFacade.class);
//        bind(VmFacade.class);
//        bind(ProjectFacade.class);
//        bind(ControllerFacade.class);
//        bind(AutoVoteFacade.class);
//
//        install(new DbModule());
//
////        bind Resource
//        bind(NoteResource.class);
//        bind(GameRecordResource.class);
//        bind(AdminResource.class);
//        bind(VmResource.class);
//        bind(ProjectResource.class);
//        bind(ControllerResource.class);
//        bind(AutoVoteResource.class);
//
//        bind(CorsFilter.class).in(Singleton.class);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put(PROPERTY_PACKAGES, "com.sw.api.*");
//        filter("/api/*").through(CorsFilter.class);
//        serve("/api/*").with(GuiceContainer.class, params);
//    }
}
