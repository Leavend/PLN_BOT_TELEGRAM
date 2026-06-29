package org.mockito.exceptions.base;

/* loaded from: classes3.dex */
public class MockitoInitializationException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public MockitoInitializationException(String str) {
        super(str);
    }

    public MockitoInitializationException(String str, Throwable th) {
        super(str, th);
    }
}
