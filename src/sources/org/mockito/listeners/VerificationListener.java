package org.mockito.listeners;

import org.mockito.Incubating;
import org.mockito.verification.VerificationEvent;

@Incubating
/* loaded from: classes3.dex */
public interface VerificationListener extends MockitoListener {
    void onVerification(VerificationEvent verificationEvent);
}
