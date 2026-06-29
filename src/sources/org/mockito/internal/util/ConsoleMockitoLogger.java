package org.mockito.internal.util;

/* loaded from: classes3.dex */
public class ConsoleMockitoLogger implements MockitoLogger {
    @Override // org.mockito.internal.util.MockitoLogger
    public void log(Object obj) {
        System.out.println(obj);
    }
}
