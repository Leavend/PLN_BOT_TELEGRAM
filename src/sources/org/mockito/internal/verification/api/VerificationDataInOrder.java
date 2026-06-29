package org.mockito.internal.verification.api;

import java.util.List;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public interface VerificationDataInOrder {
    List<Invocation> getAllInvocations();

    InOrderContext getOrderingContext();

    MatchableInvocation getWanted();
}
