package com.sw.base;

import java.lang.annotation.Annotation;

public interface StartupTasks<AnnotationType extends Annotation, ConfigurationType> extends FacetEnabler {
    void perform(AnnotationType annotation, ConfigurationType configuration);
}
