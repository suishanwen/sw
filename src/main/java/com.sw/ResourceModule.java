package com.sw;

import com.sw.base.Application;
import com.sw.base.ApplicationModule;
import com.sw.base.GuiceModule;
import com.sw.base.Servlet3;
import com.sw.base.config.Configuration;
import com.sw.base.config.DatabaseConfiguration;
import com.sw.base.grizzly.EmbeddedGrizzly;
import com.sw.base.jersey.RestApi;
import com.sw.base.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static com.sw.base.config.Configuration.config;

@Application("sw")
@GuiceModule
@Servlet3
@RestApi
@JpaPersist(unit = "domain")
@EmbeddedGrizzly(assets =
        {@EmbeddedGrizzly.Asset(uri = "/note", resource = "note"),
                @EmbeddedGrizzly.Asset(uri = "/mine", resource = "mine"),
                @EmbeddedGrizzly.Asset(uri = "/note2", resource = "note2")
        }
)
public class ResourceModule extends ApplicationModule<JpaConfiguration> {
    private static final String DEFAULT_CONFIG_FOLDER = "./";
    private static final String DEFAULT_YML_FILENAME = "config.yml";
    private static final String DEFAULT_YML_PATHNAME = "/home/config.yml";
    private static Logger logger = LoggerFactory.getLogger(ResourceModule.class);
    private static JpaConfiguration jpaConfiguration;

    public ResourceModule() {
        super();
    }

    @Override
    protected JpaConfiguration createDefaultConfiguration(Configuration.ConfigurationBuilder config) {
        try {
            if (!getDefaultFile().equals("")) {
                jpaConfiguration = readConfigurationFromFile(DEFAULT_YML_PATHNAME);
            } else {
                URL configFile = this.getClass().getClassLoader().getResource(DEFAULT_CONFIG_FOLDER + DEFAULT_YML_FILENAME);
                if (configFile != null) {
                    logger.info("Reading configuration from project config");
                    jpaConfiguration = readConfigurationFromFile(configFile.getFile());
                } else {
                    logger.warn("config file in project url is null");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (jpaConfiguration == null) {
            jpaConfiguration = defaultConfiguration();
        }
        return jpaConfiguration;
    }

    private static String getDefaultFile() {
        File file = new File(DEFAULT_YML_PATHNAME);
        if(file.exists()){
            return DEFAULT_YML_PATHNAME;
        }
        return "";
    }

    private JpaConfiguration readConfigurationFromFile(String path) {
        JpaConfiguration jpaConfiguration = null;
        try {
            File file = new File(path);
            logger.info("Reading configuration from file {}", file.getCanonicalPath());
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
        logger.info("No configuration found, will use default configuration.");

        return new JpaConfiguration(config().http().port(8051).end().build(),
                DatabaseConfiguration.database().user("root").password("root").driver("com.mysql.jdbc.Driver")
                        .url("jdbc:mysql://127.0.0.1:3306/sw").build());
    }
}
