package com.sw.module;


import com.sw.CorsFilter;
import com.sw.api.controller.AdminResource;
import com.sw.api.controller.ProjectResource;
import com.sw.api.controller.VmResource;
import com.sw.api.game.GameRecordResource;
import com.sw.api.note.NoteResource;
import com.sw.domain.facade.controller.AdminFacade;
import com.sw.domain.facade.controller.ProjectFacade;
import com.sw.domain.facade.controller.VmFacade;
import com.sw.domain.facade.game.GameRecordFacade;
import com.sw.domain.facade.note.NoteFacade;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

public class ResourceModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {

        bind(GuiceContainer.class);
        binder().requireExplicitBindings();
        bind(GuiceFilter.class);
        bind(NoteFacade.class);
        bind(GameRecordFacade.class);
        bind(AdminFacade.class);
        bind(VmFacade.class);
        bind(ProjectFacade.class);
        install(new DbModule());
        bind(NoteResource.class);
        bind(GameRecordResource.class);
        bind(AdminResource.class);
        bind(VmResource.class);
        bind(ProjectResource.class);
        bind(CorsFilter.class).in(Singleton.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put(PROPERTY_PACKAGES, "com.sw.api.*");
        filter("/api/*").through(CorsFilter.class);
        serve("/api/*").with(GuiceContainer.class, params);
    }
}
