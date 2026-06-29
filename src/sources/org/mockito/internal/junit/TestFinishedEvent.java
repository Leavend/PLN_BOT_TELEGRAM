package org.mockito.internal.junit;

/* loaded from: classes3.dex */
public interface TestFinishedEvent {
    Throwable getFailure();

    String getTestName();
}
