package com.sw.service.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.common.collect.ImmutableMap;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@XmlType
public class Configuration {

    public static <T extends Configuration> T read(InputStream configStream, Class<T> configurationType) throws IOException {
        return getMapper().readValue(configStream, configurationType);
    }

    public static String dump(Configuration configuration) throws JsonProcessingException {
        return getMapper().writeValueAsString(configuration);
    }

    public static ConfigurationBuilder config() {
        return new ConfigurationBuilder();
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        mapper.registerModule(new GuavaModule());
        mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()), new JacksonAnnotationIntrospector()));
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
        return mapper;
    }

    @NotNull
    private HttpConfiguration http;



    private Map<String, String> properties = ImmutableMap.of();

    public Configuration() {
        http = config().http().build();
    }

    private Configuration(HttpConfiguration http,  Map<String, String> properties) {
        this.http = http;
        this.properties = properties;
    }

    protected Configuration(Configuration configuration) {
        this.http = configuration.http;
    }

    @XmlElement
    public HttpConfiguration getHttp() {
        return http;
    }


    @XmlElement
    public Map<String, String> getProperties() {
        return properties;
    }

    public static class ConfigurationBuilder implements Builder<Configuration> {

        private HttpConfiguration.HttpConfigurationBuilder http = new HttpConfiguration.HttpConfigurationBuilder(this);


        private Map<String, String> properties = ImmutableMap.of();
        ConfigurationBuilder() {
        }

        public HttpConfiguration.HttpConfigurationBuilder http() {
            return http;
        }


        public ConfigurationBuilder addProperties(String key, String value) {
            this.properties.put(key, value);
            return this;
        }

        @Override
        public Configuration build() {
            return new Configuration(http.build(), properties);
        }
    }
}
