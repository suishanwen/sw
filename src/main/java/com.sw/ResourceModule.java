package com.sw;

import com.sw.service.Application;
import com.sw.service.jersey.RestApi;
import com.sw.service.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static com.sw.service.jpa.Configuration.config;

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
        if (jpaConfiguration == null) {
            jpaConfiguration = defaultConfiguration();
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

    private static JpaConfiguration defaultConfiguration() {
        if (logger.isInfoEnabled())
            logger.info("No configuration found, will use default configuration.");

        return new JpaConfiguration(config().http().port(8051).end().build(),
                DatabaseConfiguration.database().user("root").password("root").driver("com.mysql.jdbc.Driver")
                        .url("jdbc:mysql://127.0.0.1:3306/sw").build());
    }
}
