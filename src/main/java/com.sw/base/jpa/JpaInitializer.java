package com.sw.base.jpa;

import com.google.inject.persist.PersistService;

import javax.inject.Inject;

public class JpaInitializer {

    @Inject
    JpaInitializer(PersistService service) {
        service.start();
    }
}
