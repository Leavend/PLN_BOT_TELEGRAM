package org.mockito.configuration;

import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public interface IMockitoConfiguration {
    boolean cleansStackTrace();

    boolean enableClassCache();

    @Deprecated
    AnnotationEngine getAnnotationEngine();

    Answer<Object> getDefaultAnswer();
}
