package org.mockito.internal.verification.checkers;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class AtLeastXNumberOfInvocationsChecker {
    public static void checkAtLeastNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i) {
        List<Invocation> listFindInvocations = InvocationsFinder.findInvocations(list, matchableInvocation);
        int size = listFindInvocations.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocations(new AtLeastDiscrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(listFindInvocations));
        }
        InvocationMarker.markVerified(listFindInvocations, matchableInvocation);
    }

    public static void checkAtLeastNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        List<Invocation> listFindAllMatchingUnverifiedChunks = InvocationsFinder.findAllMatchingUnverifiedChunks(list, matchableInvocation, inOrderContext);
        int size = listFindAllMatchingUnverifiedChunks.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocationsInOrder(new AtLeastDiscrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(listFindAllMatchingUnverifiedChunks));
        }
        InvocationMarker.markVerifiedInOrder(listFindAllMatchingUnverifiedChunks, matchableInvocation, inOrderContext);
    }
}
