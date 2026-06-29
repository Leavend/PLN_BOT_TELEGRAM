package org.mockito.internal.configuration.plugins;

import org.mockito.internal.creation.instance.InstantiatorProviderAdapter;
import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.PluginSwitch;
import org.mockito.plugins.StackTraceCleanerProvider;

/* loaded from: classes3.dex */
class PluginRegistry {
    private AnnotationEngine annotationEngine;
    private final InstantiatorProvider2 instantiatorProvider;
    private final MockMaker mockMaker;
    private final PluginSwitch pluginSwitch;
    private final StackTraceCleanerProvider stackTraceCleanerProvider;

    PluginRegistry() {
        PluginSwitch pluginSwitch = (PluginSwitch) new PluginLoader(new DefaultPluginSwitch()).loadPlugin(PluginSwitch.class);
        this.pluginSwitch = pluginSwitch;
        this.mockMaker = (MockMaker) new PluginLoader(pluginSwitch, "mock-maker-inline").loadPlugin(MockMaker.class);
        this.stackTraceCleanerProvider = (StackTraceCleanerProvider) new PluginLoader(pluginSwitch).loadPlugin(StackTraceCleanerProvider.class);
        this.annotationEngine = (AnnotationEngine) new PluginLoader(pluginSwitch).loadPlugin(AnnotationEngine.class);
        Object objLoadPlugin = new PluginLoader(pluginSwitch).loadPlugin(InstantiatorProvider2.class, InstantiatorProvider.class);
        if (objLoadPlugin instanceof InstantiatorProvider) {
            this.instantiatorProvider = new InstantiatorProviderAdapter((InstantiatorProvider) objLoadPlugin);
        } else {
            this.instantiatorProvider = (InstantiatorProvider2) objLoadPlugin;
        }
    }

    StackTraceCleanerProvider getStackTraceCleanerProvider() {
        return this.stackTraceCleanerProvider;
    }

    MockMaker getMockMaker() {
        return this.mockMaker;
    }

    InstantiatorProvider2 getInstantiatorProvider() {
        return this.instantiatorProvider;
    }

    AnnotationEngine getAnnotationEngine() {
        return this.annotationEngine;
    }
}
