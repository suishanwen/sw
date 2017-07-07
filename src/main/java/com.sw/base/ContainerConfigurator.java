package com.sw.base;

import com.sw.base.config.Configuration;

import java.lang.annotation.Annotation;

public interface ContainerConfigurator<AnnotationType extends Annotation, ConfigurationType extends Configuration, ContainerType extends ServletContainer> extends FacetEnabler {
    void configure(ContainerType container, AnnotationType annotation, ApplicationModule<ConfigurationType> module) throws Exception;
}
