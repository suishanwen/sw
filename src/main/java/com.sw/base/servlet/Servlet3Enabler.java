package com.sw.base.servlet;

import com.google.inject.Binder;
import com.sw.base.Servlet3;
import com.sw.base.ApplicationModule;
import com.sw.base.BindingProvider;
import com.sw.base.config.Configuration;

public class Servlet3Enabler implements BindingProvider<Servlet3, Configuration> {
    @Override
    public void configure(Binder binder, Servlet3 annotation, ApplicationModule<?> module, Configuration configuration) {
        final String[] autoScanPackages = new String[]{module.getClass().getPackage().getName()};
        binder.install(new AutoScanningServletModule(annotation.packages().length == 0 ? autoScanPackages : annotation.packages()));
    }
}
