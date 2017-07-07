package com.sw.base;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.squarespace.jersey2.guice.JerseyGuiceModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.and;
import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.ImmutableSet.copyOf;
import static com.google.common.collect.Iterables.*;

import static com.sw.base.util.TypePredicates.isApplicationModule;
import static com.sw.base.util.TypePredicates.moduleName;
import com.sw.base.config.Configuration;
import com.sw.base.util.ClassScanner;


public class Launcher {
    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private static String configFolder = "./";

    private static final Function<String, File> TO_CONFIG_FILE = new Function<String, File>() {
        @Nullable
        @Override
        public File apply(@Nullable String input) {
            return new File(configFolder + input + ".yml");
        }
    };
    public static final Predicate<File> EXISTS = new Predicate<File>() {
        @Override
        public boolean apply(@Nullable File input) {
            return input.exists();
        }
    };

    public static final Predicate<Map.Entry<Annotation, FacetEnabler>> CONTAINER_CREATOR = new Predicate<Map.Entry<Annotation, FacetEnabler>>() {
        @Override
        public boolean apply(@Nullable Map.Entry<Annotation, FacetEnabler> input) {
            return input.getValue() instanceof ContainerCreator;
        }
    };

    public static <T extends Configuration> ServletContainer launch(ApplicationModule<T> module, T configuration, boolean standalone) throws Exception {
        module.setConfiguration(configuration);
        return launch(module, standalone);
    }

    public static ServletContainer launch(ApplicationModule<? extends Configuration> module, boolean standalone) throws Exception {
        Configuration configuration = module.configuration();
        for (Map.Entry<Annotation, FacetEnabler> enabler : module.enablers().entrySet())
            if (enabler.getValue() instanceof StartupTasks)
                ((StartupTasks) enabler.getValue()).perform(enabler.getKey(), configuration);

        ServletContainer container = createServletContainer(module, configuration);
        container.addServletContext(module.path(), true, module,
                new JerseyGuiceModule("__HK2_Generated_0"));

        for (Map.Entry<Annotation, FacetEnabler> enabler : module.enablers().entrySet())
            if (enabler.getValue() instanceof ContainerConfigurator)
                ((ContainerConfigurator) enabler.getValue()).configure(container, enabler.getKey(), module);

        container.start(standalone);

        return container;
    }

    private static ServletContainer createServletContainer(ApplicationModule<? extends Configuration> module, Configuration configuration) {

        Iterable<Map.Entry<Annotation, FacetEnabler>> creators = filter(module.enablers().entrySet(), CONTAINER_CREATOR);

        checkArgument(size(creators) != 0, "No servlet container creator found!");
        checkArgument(size(creators) == 1, "More than 1 servlet container found!");

        Map.Entry<Annotation, FacetEnabler> creator = getLast(creators);
        return ((ContainerCreator) creator.getValue()).create(creator.getKey(), configuration);
    }

    private static ApplicationModule<? extends Configuration> findApplicationModule(String name) throws Exception {
        ClassScanner scanner = new ClassScanner(Launcher.class.getProtectionDomain().getCodeSource());
        Set<Class<?>> modules = copyOf(scanner.findBy(and(isApplicationModule, moduleName(name))));
        checkArgument(modules.size() != 0, "Can not found module named: " + name);
        checkArgument(modules.size() == 1, "More than 1 module named: " + name);
        return createApplicationModule(getFirst(modules, null));
    }

    private static ApplicationModule<? extends Configuration> findDefaultApplicationModule() throws Exception {
        ClassScanner scanner = new ClassScanner(Launcher.class.getProtectionDomain().getCodeSource());
        Set<Class<?>> modules = copyOf(scanner.findBy(isApplicationModule));
        checkArgument(modules.size() == 1, "More than 1 module found.");
        return createApplicationModule(getFirst(modules, null));
    }

    private static ApplicationModule createApplicationModule(Class<?> input) {
        try {
            return (ApplicationModule) input.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Throwables.propagate(e);
        }
        return null;
    }

    private static ApplicationModule<? extends Configuration> readConfiguration(ApplicationModule<? extends Configuration> module) throws IOException {
        Optional<File> config = tryFind(transform(of(module.path(), "config"), TO_CONFIG_FILE), EXISTS);

        if (config.isPresent()) {
            logger.info("Reading configuration from file: " + config.get().getAbsolutePath());
            module.setConfiguration(Configuration.read(new FileInputStream(config.get()), module.configurationType()));
        } else {
            logger.info("No configuration found, will use default configuration.");
        }
        return module;
    }

    public static void main(String... arguments) throws Exception {
        checkArgument(arguments.length <= 2, "Too many arguments");
        if (arguments.length == 2) {
            configFolder = arguments[1];
        }
        launch(readConfiguration(arguments.length == 0 ? findDefaultApplicationModule() : findApplicationModule(arguments[0])), true);
    }
}
