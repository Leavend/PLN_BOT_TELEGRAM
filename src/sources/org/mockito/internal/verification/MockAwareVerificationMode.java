package org.mockito.internal.verification;

import java.util.Iterator;
import java.util.Set;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.listeners.VerificationListener;
import org.mockito.verification.VerificationEvent;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class MockAwareVerificationMode implements VerificationMode {
    private final Set<VerificationListener> listeners;
    private final Object mock;
    private final VerificationMode mode;

    public MockAwareVerificationMode(Object obj, VerificationMode verificationMode, Set<VerificationListener> set) {
        this.mock = obj;
        this.mode = verificationMode;
        this.listeners = set;
    }

    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) {
        try {
            this.mode.verify(verificationData);
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, null));
        } catch (Error e) {
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, e));
            throw e;
        } catch (RuntimeException e2) {
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, e2));
            throw e2;
        }
    }

    private void notifyListeners(VerificationEvent verificationEvent) {
        Iterator<VerificationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onVerification(verificationEvent);
        }
    }

    public Object getMock() {
        return this.mock;
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
