package org.mockito.internal.verification.checkers;

import java.util.Arrays;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.reporting.Discrepancy;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class NumberOfInvocationsChecker {
    private NumberOfInvocationsChecker() {
    }

    public static void checkNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i) {
        List<Invocation> listFindInvocations = InvocationsFinder.findInvocations(list, matchableInvocation);
        int size = listFindInvocations.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocations(new Discrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(listFindInvocations));
        }
        if (i == 0 && size > 0) {
            throw Reporter.neverWantedButInvoked(matchableInvocation, InvocationsFinder.getAllLocations(listFindInvocations));
        }
        if (i < size) {
            throw Reporter.tooManyActualInvocations(i, size, matchableInvocation, InvocationsFinder.getAllLocations(listFindInvocations));
        }
        InvocationMarker.markVerified(listFindInvocations, matchableInvocation);
    }

    public static void checkNumberOfInvocations(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        List<Invocation> listFindMatchingChunk = InvocationsFinder.findMatchingChunk(list, matchableInvocation, i, inOrderContext);
        int size = listFindMatchingChunk.size();
        if (i > size) {
            throw Reporter.tooLittleActualInvocationsInOrder(new Discrepancy(i, size), matchableInvocation, InvocationsFinder.getAllLocations(listFindMatchingChunk));
        }
        if (i < size) {
            throw Reporter.tooManyActualInvocationsInOrder(i, size, matchableInvocation, InvocationsFinder.getAllLocations(listFindMatchingChunk));
        }
        InvocationMarker.markVerifiedInOrder(listFindMatchingChunk, matchableInvocation, inOrderContext);
    }

    public static void checkNumberOfInvocationsNonGreedy(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        Location location = null;
        for (int i2 = 0; i2 < i; i2++) {
            Invocation invocationFindFirstMatchingUnverifiedInvocation = InvocationsFinder.findFirstMatchingUnverifiedInvocation(list, matchableInvocation, inOrderContext);
            if (invocationFindFirstMatchingUnverifiedInvocation == null) {
                throw Reporter.tooLittleActualInvocationsInOrder(new Discrepancy(i, i2), matchableInvocation, Arrays.asList(location));
            }
            InvocationMarker.markVerified(invocationFindFirstMatchingUnverifiedInvocation, matchableInvocation);
            inOrderContext.markVerified(invocationFindFirstMatchingUnverifiedInvocation);
            location = invocationFindFirstMatchingUnverifiedInvocation.getLocation();
        }
    }
}
