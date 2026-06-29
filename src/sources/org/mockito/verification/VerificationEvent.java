package org.mockito.verification;

import org.mockito.Incubating;
import org.mockito.internal.verification.api.VerificationData;

@Incubating
/* loaded from: classes3.dex */
public interface VerificationEvent {
    VerificationData getData();

    Object getMock();

    VerificationMode getMode();

    Throwable getVerificationError();
}
