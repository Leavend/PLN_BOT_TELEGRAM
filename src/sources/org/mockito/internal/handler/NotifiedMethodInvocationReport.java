package org.mockito.internal.handler;

import org.mockito.internal.matchers.Equality;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.listeners.MethodInvocationReport;

/* loaded from: classes3.dex */
public class NotifiedMethodInvocationReport implements MethodInvocationReport {
    private final Invocation invocation;
    private final Object returnedValue;
    private final Throwable throwable;

    public NotifiedMethodInvocationReport(Invocation invocation, Object obj) {
        this.invocation = invocation;
        this.returnedValue = obj;
        this.throwable = null;
    }

    public NotifiedMethodInvocationReport(Invocation invocation, Throwable th) {
        this.invocation = invocation;
        this.returnedValue = null;
        this.throwable = th;
    }

    @Override // org.mockito.listeners.MethodInvocationReport
    public DescribedInvocation getInvocation() {
        return this.invocation;
    }

    @Override // org.mockito.listeners.MethodInvocationReport
    public Object getReturnedValue() {
        return this.returnedValue;
    }

    @Override // org.mockito.listeners.MethodInvocationReport
    public Throwable getThrowable() {
        return this.throwable;
    }

    @Override // org.mockito.listeners.MethodInvocationReport
    public boolean threwException() {
        return this.throwable != null;
    }

    @Override // org.mockito.listeners.MethodInvocationReport
    public String getLocationOfStubbing() {
        if (this.invocation.stubInfo() == null) {
            return null;
        }
        return this.invocation.stubInfo().stubbedAt().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NotifiedMethodInvocationReport notifiedMethodInvocationReport = (NotifiedMethodInvocationReport) obj;
        return Equality.areEqual(this.invocation, notifiedMethodInvocationReport.invocation) && Equality.areEqual(this.returnedValue, notifiedMethodInvocationReport.returnedValue) && Equality.areEqual(this.throwable, notifiedMethodInvocationReport.throwable);
    }

    public int hashCode() {
        Invocation invocation = this.invocation;
        int iHashCode = (invocation != null ? invocation.hashCode() : 0) * 31;
        Object obj = this.returnedValue;
        int iHashCode2 = (iHashCode + (obj != null ? obj.hashCode() : 0)) * 31;
        Throwable th = this.throwable;
        return iHashCode2 + (th != null ? th.hashCode() : 0);
    }
}
