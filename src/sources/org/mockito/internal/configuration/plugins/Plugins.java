package org.mockito.internal.configuration.plugins;

import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider2;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.MockitoPlugins;
import org.mockito.plugins.StackTraceCleanerProvider;

/* loaded from: classes3.dex */
public class Plugins {
    private static final PluginRegistry registry = new PluginRegistry();

    public static StackTraceCleanerProvider getStackTraceCleanerProvider() {
        return registry.getStackTraceCleanerProvider();
    }

    public static MockMaker getMockMaker() {
        return registry.getMockMaker();
    }

    public static InstantiatorProvider2 getInstantiatorProvider() {
        return registry.getInstantiatorProvider();
    }

    public static AnnotationEngine getAnnotationEngine() {
        return registry.getAnnotationEngine();
    }

    public static MockitoPlugins getPlugins() {
        return new DefaultMockitoPlugins();
    }
}
