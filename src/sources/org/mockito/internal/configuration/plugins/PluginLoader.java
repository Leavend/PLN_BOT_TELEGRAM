package org.mockito.internal.configuration.plugins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.mockito.plugins.PluginSwitch;

/* loaded from: classes3.dex */
class PluginLoader {
    private final PluginInitializer initializer;
    private final DefaultMockitoPlugins plugins;

    PluginLoader(DefaultMockitoPlugins defaultMockitoPlugins, PluginInitializer pluginInitializer) {
        this.plugins = defaultMockitoPlugins;
        this.initializer = pluginInitializer;
    }

    PluginLoader(PluginSwitch pluginSwitch) {
        this(new DefaultMockitoPlugins(), new PluginInitializer(pluginSwitch, null, new DefaultMockitoPlugins()));
    }

    @Deprecated
    PluginLoader(PluginSwitch pluginSwitch, String str) {
        this(new DefaultMockitoPlugins(), new PluginInitializer(pluginSwitch, str, new DefaultMockitoPlugins()));
    }

    <T> T loadPlugin(Class<T> cls) {
        return (T) loadPlugin(cls, null);
    }

    <PreferredType, AlternateType> Object loadPlugin(final Class<PreferredType> cls, final Class<AlternateType> cls2) {
        Object objLoadImpl;
        try {
            Object objLoadImpl2 = this.initializer.loadImpl(cls);
            return objLoadImpl2 != null ? objLoadImpl2 : (cls2 == null || (objLoadImpl = this.initializer.loadImpl(cls2)) == null) ? this.plugins.getDefaultPlugin(cls) : objLoadImpl;
        } catch (Throwable th) {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: org.mockito.internal.configuration.plugins.PluginLoader.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    throw new IllegalStateException("Could not initialize plugin: " + cls + " (alternate: " + cls2 + ")", th);
                }
            });
        }
    }
}
