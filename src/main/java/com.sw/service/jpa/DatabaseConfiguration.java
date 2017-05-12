package com.sw.service.jpa;

import com.google.common.collect.ImmutableMap;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;
import java.util.Properties;


@XmlType
public class DatabaseConfiguration {
    public static DatabaseConfigurationBuilder database() {
        return new DatabaseConfigurationBuilder();
    }

    @NotNull
    private String driver;
    @NotNull
    private String url;
    @NotNull
    private String password;
    @NotNull
    private String user;
    @NotNull
    private Map<String, String> properties = ImmutableMap.of();

    public DatabaseConfiguration() {
    }

    DatabaseConfiguration(String driver, String url, String password, String user, Map<String, String> properties) {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.user = user;
        this.properties = properties;
    }

    @XmlElement
    public String getDriver() {
        return driver;
    }

    @XmlElement
    public String getUrl() {
        return url;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    @XmlElement
    public String getUser() {
        return user;
    }

    @XmlElement
    public Map<String, String> getProperties() {
        return properties;
    }


    public Properties toProperties() {
        Properties properties = new Properties();

        if (driver != null) properties.put("javax.persistence.jdbc.driver", driver);
        if (url != null) properties.put("javax.persistence.jdbc.url", url);
        if (password != null && user != null) {
            properties.put("javax.persistence.jdbc.user", user);
            properties.put("javax.persistence.jdbc.password", password);
        }
        if (this.properties != null)
            for (String key : this.properties.keySet())
                properties.put(key, this.properties.get(key));
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatabaseConfiguration that = (DatabaseConfiguration) o;

        if (!driver.equals(that.driver)) return false;
        if (!password.equals(that.password)) return false;
        if (!properties.equals(that.properties)) return false;
        if (!url.equals(that.url)) return false;
        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = driver.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + properties.hashCode();
        return result;
    }



    public static class DatabaseConfigurationBuilder implements Builder<DatabaseConfiguration> {
        private String driver;
        private String url;
        private StringBuffer urlAppendix = new StringBuffer();
        private String password;
        private String user;
        private ImmutableMap.Builder<String, String> properties = ImmutableMap.builder();


        DatabaseConfigurationBuilder() {
        }

        public DatabaseConfigurationBuilder driver(String driver) {
            this.driver = driver;
            return this;
        }

        public DatabaseConfigurationBuilder url(String url) {
            this.url = url;
            return this;
        }

        public DatabaseConfigurationBuilder password(String password) {
            this.password = password;
            return this;
        }

        public DatabaseConfigurationBuilder user(String user) {
            this.user = user;
            return this;
        }

        public DatabaseConfigurationBuilder appendToUrl(String appendix) {
            this.urlAppendix.append(appendix);
            return this;
        }

        public DatabaseConfigurationBuilder property(String key, String value) {
            properties.put(key, value);
            return this;
        }

        public DatabaseConfigurationBuilder with(Setting... settings) {
            for (Setting setting : settings) {
                setting.set(this);
            }
            return this;
        }


        public static abstract class Setting {
            public static Setting and(final Setting... settings) {
                return new Setting() {
                    @Override
                    public void set(DatabaseConfigurationBuilder config) {
                        for (Setting setting : settings) setting.set(config);
                    }
                };
            }

            public abstract void set(DatabaseConfigurationBuilder config);
        }

        public DatabaseConfiguration build() {
            return new DatabaseConfiguration(driver, url, password, user, properties.build());
        }

    }
}
