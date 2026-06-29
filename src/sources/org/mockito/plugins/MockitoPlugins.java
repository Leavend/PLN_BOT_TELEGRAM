package org.mockito.plugins;

/* loaded from: classes3.dex */
public interface MockitoPlugins {
    <T> T getDefaultPlugin(Class<T> cls);

    MockMaker getInlineMockMaker();
}
