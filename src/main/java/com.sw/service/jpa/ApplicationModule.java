package com.sw.service.jpa;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.inject.AbstractModule;
import com.sw.service.Application;
import com.sw.service.Facet;
import com.sw.service.FacetEnabler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Optional.of;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.ImmutableSet.copyOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.find;
import static com.sw.service.TypePredicates.*;
import static com.sw.service.jpa.Configuration.config;

public abstract class ApplicationModule<T extends Configuration> extends AbstractModule {
    public static final Comparator<Annotation> FACET_COMPARATOR = new Comparator<Annotation>() {
        @Override
        public int compare(Annotation o1, Annotation o2) {
            int result = o1.annotationType().getAnnotation(Facet.class).order() - o2.annotationType().getAnnotation(Facet.class).order();
            return result == 0 ? -1 : result; // never return 0 as it will think o1 and o2 are duplicate and remove one of them;
        }
    };
    protected final Application application;

    private Optional<T> configuration = Optional.absent();

    public static Map<String, String> properties;

    private final Map<Annotation, FacetEnabler> enablers;

    public ApplicationModule() {
        checkState(getClass().isAnnotationPresent(Application.class), "missing @Application annotation for application module '" + getClass().getName() + "'");
        this.application = getClass().getAnnotation(Application.class);
        this.enablers = findEnablers();
    }

    private ImmutableMap<Annotation, FacetEnabler> findEnablers() {
        ImmutableMap.Builder<Annotation, FacetEnabler> builder = ImmutableMap.builder();
        for (Annotation annotation : filter(copyOf(getClass().getAnnotations()), isStack))
            findEnablers(annotation.annotationType(), builder);
        findEnablers(getClass(), builder);
        return builder.build();
    }

    private void findEnablers(Class<?> target, ImmutableMap.Builder<Annotation, FacetEnabler> builder) {
        Iterable<Annotation> filter = filter(copyOf(target.getAnnotations()), isFacet);
        for (Annotation annotation : ImmutableSortedMultiset.orderedBy(FACET_COMPARATOR).addAll(filter).build()) {
            Facet facet = annotation.annotationType().getAnnotation(Facet.class);
            Class<? extends FacetEnabler> enablerClass = facet.value();
            try {
                builder.put(annotation, enablerClass.getConstructor().newInstance());
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new IllegalArgumentException("Can not create enabler for facet: " + facet);
            }
        }
    }

    final Class<T> configurationType() {
        return (Class<T>) find(typeParametersOf(genericSuperClass()), typeSubClassOf(Configuration.class));
    }

    private Type genericSuperClass() {
        Type t = getClass();
        while (t instanceof Class<?>) t = ((Class<?>) t).getGenericSuperclass();
        return t;
    }

    private ImmutableSet<Type> typeParametersOf(Type t) {
        return t instanceof ParameterizedType ? copyOf(((ParameterizedType) t).getActualTypeArguments()) :
                ImmutableSet.<Type>of();
    }

    @Override
    protected final void configure() {
        for (Map.Entry<Annotation, FacetEnabler> enabler : enablers.entrySet())
            if (enabler.getValue() instanceof BindingProvider)
                ((BindingProvider) enabler.getValue()).configure(binder(), enabler.getKey(), this, configuration());
    }

    void setConfiguration(Configuration configuration) {
        this.configuration = of((T) configuration);
    }

    public final T configuration() {
        if (!configuration.isPresent()) configuration = fromNullable(createDefaultConfiguration(config()));
        Preconditions.checkArgument(configuration.isPresent(), "No configuration for module: " + getClass());
        T configuration = this.configuration.get();
        properties = configuration.getProperties();
        return configuration;
    }

    protected T createDefaultConfiguration(Configuration.ConfigurationBuilder config) {
        return null;
    }

    public String path() {
        return application.value().startsWith("/") ? application.value() : "/" + application.value();
    }

    public Map<Annotation, FacetEnabler> enablers() {
        return enablers;
    }

    public ApplicationModule[] getSubModules() {
        return new ApplicationModule[0];
    }

    public static String getProperty(String key) {
        return properties.get(key);
    }
}
