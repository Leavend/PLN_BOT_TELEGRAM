package org.mockito.internal.handler;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.listeners.InvocationListener;
import org.mockito.mock.MockCreationSettings;

/* loaded from: classes3.dex */
class InvocationNotifierHandler<T> implements MockHandler<T> {
    private final List<InvocationListener> invocationListeners;
    private final MockHandler<T> mockHandler;

    public InvocationNotifierHandler(MockHandler<T> mockHandler, MockCreationSettings<T> mockCreationSettings) {
        this.mockHandler = mockHandler;
        this.invocationListeners = mockCreationSettings.getInvocationListeners();
    }

    @Override // org.mockito.invocation.MockHandler
    public Object handle(Invocation invocation) throws Throwable {
        try {
            Object objHandle = this.mockHandler.handle(invocation);
            notifyMethodCall(invocation, objHandle);
            return objHandle;
        } catch (Throwable th) {
            notifyMethodCallException(invocation, th);
            throw th;
        }
    }

    private void notifyMethodCall(Invocation invocation, Object obj) {
        for (InvocationListener invocationListener : this.invocationListeners) {
            try {
                invocationListener.reportInvocation(new NotifiedMethodInvocationReport(invocation, obj));
            } catch (Throwable th) {
                throw Reporter.invocationListenerThrewException(invocationListener, th);
            }
        }
    }

    private void notifyMethodCallException(Invocation invocation, Throwable th) {
        for (InvocationListener invocationListener : this.invocationListeners) {
            try {
                invocationListener.reportInvocation(new NotifiedMethodInvocationReport(invocation, th));
            } catch (Throwable th2) {
                throw Reporter.invocationListenerThrewException(invocationListener, th2);
            }
        }
    }

    @Override // org.mockito.invocation.MockHandler
    public MockCreationSettings<T> getMockSettings() {
        return this.mockHandler.getMockSettings();
    }

    @Override // org.mockito.invocation.MockHandler
    public InvocationContainer getInvocationContainer() {
        return this.mockHandler.getInvocationContainer();
    }
}
