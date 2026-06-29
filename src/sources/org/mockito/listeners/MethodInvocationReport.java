package org.mockito.listeners;

import org.mockito.invocation.DescribedInvocation;

/* loaded from: classes3.dex */
public interface MethodInvocationReport {
    DescribedInvocation getInvocation();

    String getLocationOfStubbing();

    Object getReturnedValue();

    Throwable getThrowable();

    boolean threwException();
}
