package org.mockito.internal.configuration.plugins;

import java.util.HashMap;
import java.util.Map;
import org.mockito.internal.creation.instance.InstantiatorProvider2Adapter;
import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.MockitoPlugins;
import org.mockito.plugins.PluginSwitch;
import org.mockito.plugins.StackTraceCleanerProvider;

/* loaded from: classes3.dex */
class DefaultMockitoPlugins implements MockitoPlugins {
    private static final Map<String, String> DEFAULT_PLUGINS;
    static final String INLINE_ALIAS = "mock-maker-inline";

    DefaultMockitoPlugins() {
    }

    static {
        HashMap map = new HashMap();
        DEFAULT_PLUGINS = map;
        map.put(PluginSwitch.class.getName(), DefaultPluginSwitch.class.getName());
        map.put(MockMaker.class.getName(), "org.mockito.internal.creation.bytebuddy.ByteBuddyMockMaker");
        map.put(StackTraceCleanerProvider.class.getName(), "org.mockito.internal.exceptions.stacktrace.DefaultStackTraceCleanerProvider");
        map.put(InstantiatorProvider2.class.getName(), "org.mockito.internal.creation.instance.DefaultInstantiatorProvider");
        map.put(AnnotationEngine.class.getName(), "org.mockito.internal.configuration.InjectingAnnotationEngine");
        map.put(INLINE_ALIAS, "org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker");
    }

    @Override // org.mockito.plugins.MockitoPlugins
    public <T> T getDefaultPlugin(Class<T> cls) {
        if (cls == InstantiatorProvider.class) {
            return cls.cast(new InstantiatorProvider2Adapter((InstantiatorProvider2) create(InstantiatorProvider2.class, DEFAULT_PLUGINS.get(InstantiatorProvider2.class.getName()))));
        }
        return (T) create(cls, DEFAULT_PLUGINS.get(cls.getName()));
    }

    String getDefaultPluginClass(String str) {
        return DEFAULT_PLUGINS.get(str);
    }

    private <T> T create(Class<T> cls, String str) {
        if (str == null) {
            throw new IllegalStateException("No default implementation for requested Mockito plugin type: " + cls.getName() + "\nIs this a valid Mockito plugin type? If yes, please report this problem to Mockito team.\nOtherwise, please check if you are passing valid plugin type.\nExamples of valid plugin types: MockMaker, StackTraceCleanerProvider.");
        }
        try {
            return cls.cast(Class.forName(str).newInstance());
        } catch (Exception e) {
            throw new IllegalStateException("Internal problem occurred, please report it. Mockito is unable to load the default implementation of class that is a part of Mockito distribution. Failed to load " + cls, e);
        }
    }

    @Override // org.mockito.plugins.MockitoPlugins
    public MockMaker getInlineMockMaker() {
        return (MockMaker) create(MockMaker.class, DEFAULT_PLUGINS.get(INLINE_ALIAS));
    }
}
