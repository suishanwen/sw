package com.sw.base.jpa;

import com.google.inject.Binder;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.ReJpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.sw.base.ApplicationModule;
import com.sw.base.BindingProvider;

import static com.google.common.base.Preconditions.checkArgument;

public class JpaPersistUnitEnabler implements BindingProvider<JpaPersist, WithDatabase> {

    @Override
    public void configure(Binder binder, JpaPersist annotation, ApplicationModule<?> module, WithDatabase configuration) {
        checkArgument(configuration.getDatabase() != null, "No database configuration found");
        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};
        // FIXME: 2016/10/22 采用自己的PersistModule,实现Entity自动扫描
        binder.install(new ReJpaPersistModule(annotation.unit()).properties(configuration.getDatabase().toProperties()).packages(autoScanPackages));
//        binder.install(new JpaPersistModule(annotation.unit()).properties(configuration.getDatabase().toProperties()));
//        binder.bind(JpaInitializer.class).asEagerSingleton();
        binder.install(new ServletModule() {
            @Override
            protected void configureServlets() {
                filter("/*").through(PersistFilter.class);
            }
        });

    }
}
