package org.mockito.internal.junit;

/* loaded from: classes3.dex */
public class DefaultTestFinishedEvent implements TestFinishedEvent {
    private final Object testClassInstance;
    private final Throwable testFailure;
    private final String testMethodName;

    public DefaultTestFinishedEvent(Object obj, String str, Throwable th) {
        this.testClassInstance = obj;
        this.testMethodName = str;
        this.testFailure = th;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public Throwable getFailure() {
        return this.testFailure;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public String getTestName() {
        return this.testClassInstance.getClass().getSimpleName() + "." + this.testMethodName;
    }
}
