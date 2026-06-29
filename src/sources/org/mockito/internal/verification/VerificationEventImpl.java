package org.mockito.internal.verification;

import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationEvent;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class VerificationEventImpl implements VerificationEvent {
    private final Throwable cause;
    private final VerificationData data;
    private final Object mock;
    private final VerificationMode mode;

    public VerificationEventImpl(Object obj, VerificationMode verificationMode, VerificationData verificationData, Throwable th) {
        this.mock = obj;
        this.mode = verificationMode;
        this.data = verificationData;
        this.cause = th;
    }

    @Override // org.mockito.verification.VerificationEvent
    public Object getMock() {
        return this.mock;
    }

    @Override // org.mockito.verification.VerificationEvent
    public VerificationMode getMode() {
        return this.mode;
    }

    @Override // org.mockito.verification.VerificationEvent
    public VerificationData getData() {
        return this.data;
    }

    @Override // org.mockito.verification.VerificationEvent
    public Throwable getVerificationError() {
        return this.cause;
    }
}
