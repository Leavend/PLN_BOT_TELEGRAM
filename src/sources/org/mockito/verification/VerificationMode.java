package org.mockito.verification;

import org.mockito.internal.verification.api.VerificationData;

/* loaded from: classes3.dex */
public interface VerificationMode {
    VerificationMode description(String str);

    void verify(VerificationData verificationData);
}
