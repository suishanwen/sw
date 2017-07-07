package com.sw.base;


import java.lang.annotation.Annotation;

public interface ContainerCreator<AnnotationType extends Annotation, ConfigurationType> extends FacetEnabler {
    ServletContainer create(AnnotationType annotation, ConfigurationType configuration);
}
