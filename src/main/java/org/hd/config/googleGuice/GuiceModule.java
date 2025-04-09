package org.hd.config.googleGuice;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Modifier;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        // Scan the "Pages" package for all classes
        String packageName = "org.hd.Pages";
        Reflections reflections = new Reflections(packageName, Scanners.SubTypes);
        reflections.getSubTypesOf(Object.class).forEach(pageClass -> {
            // exclude Abstract classes/interfaces/Enums/Annotations from singleton creation
            if (!Modifier.isAbstract(pageClass.getModifiers()) && !pageClass.isInterface() && !pageClass.isEnum() && !pageClass.isAnnotation()) {
                bind(pageClass).asEagerSingleton();
            }
        });
    }

}