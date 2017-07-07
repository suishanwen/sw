package com.sw.base;

import com.sw.base.guice.GuiceModuleEnabler;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Target({ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
@Facet(GuiceModuleEnabler.class)
public @interface GuiceModule {
    String[] packages() default {};
}
