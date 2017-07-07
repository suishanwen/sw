package com.sw.base.jersey;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/")
public class AppConfig extends ResourceConfig {

    public AppConfig(String pack) {
        packages(pack);
        register(MultiPartFeature.class);
    }
}