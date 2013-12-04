package com.glo4003.project.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Resolver {

    private static volatile Injector injector;

    public static <T> T get(Class<T> clazz) {
        initialize();

        return injector.getInstance(clazz);
    }

    public static <T> T getInjectedInstance(Class<T> clazz) {
        initialize();

        return injector.getInstance(clazz);
    }

    private static void initialize() {
        if (injector == null) { injector = Guice.createInjector(new Module()); }
    }
}
