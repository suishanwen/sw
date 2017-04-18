package com.sw.service.jpa;

import com.sw.service.Facet;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Facet(value = JpaPersistUnitEnabler.class, order = -1)
public @interface JpaPersist {
    String unit();
}
