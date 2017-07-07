package com.sw.base.jpa;


import com.sw.base.config.Configuration;
import com.sw.base.config.DatabaseConfiguration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class JpaConfiguration extends Configuration implements WithDatabase {
    private DatabaseConfiguration database;

    public JpaConfiguration() {
    }

    public JpaConfiguration(Configuration configuration, DatabaseConfiguration database) {
        super(configuration);
        this.database = database;
    }

    @XmlElement
    @Override
    public DatabaseConfiguration getDatabase() {
        return database;
    }
}
