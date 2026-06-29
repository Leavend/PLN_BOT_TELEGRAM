package org.mockito.internal;

import java.util.LinkedList;
import java.util.List;
import org.mockito.InOrder;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.verification.InOrderContextImpl;
import org.mockito.internal.verification.InOrderWrapper;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationWrapper;
import org.mockito.internal.verification.VerificationWrapperInOrderWrapper;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.invocation.Invocation;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class InOrderImpl implements InOrder, InOrderContext {
    private final InOrderContext inOrderContext;
    private final MockitoCore mockitoCore = new MockitoCore();
    private final List<Object> mocksToBeVerifiedInOrder;

    public List<Object> getMocksToBeVerifiedInOrder() {
        return this.mocksToBeVerifiedInOrder;
    }

    public InOrderImpl(List<?> list) {
        LinkedList linkedList = new LinkedList();
        this.mocksToBeVerifiedInOrder = linkedList;
        this.inOrderContext = new InOrderContextImpl();
        linkedList.addAll(list);
    }

    @Override // org.mockito.InOrder
    public <T> T verify(T t) {
        return (T) verify(t, VerificationModeFactory.times(1));
    }

    @Override // org.mockito.InOrder
    public <T> T verify(T t, VerificationMode verificationMode) {
        if (!this.mocksToBeVerifiedInOrder.contains(t)) {
            throw Reporter.inOrderRequiresFamiliarMock();
        }
        if (verificationMode instanceof VerificationWrapper) {
            return (T) this.mockitoCore.verify(t, new VerificationWrapperInOrderWrapper((VerificationWrapper) verificationMode, this));
        }
        if (!(verificationMode instanceof VerificationInOrderMode)) {
            throw new MockitoException(verificationMode.getClass().getSimpleName() + " is not implemented to work with InOrder");
        }
        return (T) this.mockitoCore.verify(t, new InOrderWrapper((VerificationInOrderMode) verificationMode, this));
    }

    @Override // org.mockito.internal.verification.api.InOrderContext
    public boolean isVerified(Invocation invocation) {
        return this.inOrderContext.isVerified(invocation);
    }

    @Override // org.mockito.internal.verification.api.InOrderContext
    public void markVerified(Invocation invocation) {
        this.inOrderContext.markVerified(invocation);
    }

    @Override // org.mockito.InOrder
    public void verifyNoMoreInteractions() {
        this.mockitoCore.verifyNoMoreInteractionsInOrder(this.mocksToBeVerifiedInOrder, this);
    }
}
