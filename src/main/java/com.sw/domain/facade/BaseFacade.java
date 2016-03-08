package com.sw.domain.facade;


import javax.inject.Inject;
import javax.persistence.EntityManager;


public class BaseFacade {
    @Inject
    protected EntityManager entityManager;

}
