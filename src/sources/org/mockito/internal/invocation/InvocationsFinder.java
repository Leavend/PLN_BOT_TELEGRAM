package org.mockito.internal.invocation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class InvocationsFinder {
    private InvocationsFinder() {
    }

    public static List<Invocation> findInvocations(List<Invocation> list, MatchableInvocation matchableInvocation) {
        return ListUtil.filter(list, new RemoveNotMatching(matchableInvocation));
    }

    public static List<Invocation> findAllMatchingUnverifiedChunks(List<Invocation> list, MatchableInvocation matchableInvocation, InOrderContext inOrderContext) {
        return ListUtil.filter(removeVerifiedInOrder(list, inOrderContext), new RemoveNotMatching(matchableInvocation));
    }

    public static List<Invocation> findMatchingChunk(List<Invocation> list, MatchableInvocation matchableInvocation, int i, InOrderContext inOrderContext) {
        List<Invocation> firstMatchingChunk = getFirstMatchingChunk(matchableInvocation, removeVerifiedInOrder(list, inOrderContext));
        return i != firstMatchingChunk.size() ? findAllMatchingUnverifiedChunks(list, matchableInvocation, inOrderContext) : firstMatchingChunk;
    }

    private static List<Invocation> getFirstMatchingChunk(MatchableInvocation matchableInvocation, List<Invocation> list) {
        LinkedList linkedList = new LinkedList();
        for (Invocation invocation : list) {
            if (matchableInvocation.matches(invocation)) {
                linkedList.add(invocation);
            } else if (!linkedList.isEmpty()) {
                break;
            }
        }
        return linkedList;
    }

    public static Invocation findFirstMatchingUnverifiedInvocation(List<Invocation> list, MatchableInvocation matchableInvocation, InOrderContext inOrderContext) {
        for (Invocation invocation : removeVerifiedInOrder(list, inOrderContext)) {
            if (matchableInvocation.matches(invocation)) {
                return invocation;
            }
        }
        return null;
    }

    public static Invocation findSimilarInvocation(List<Invocation> list, MatchableInvocation matchableInvocation) {
        Invocation invocation = null;
        for (Invocation invocation2 : list) {
            if (matchableInvocation.hasSimilarMethod(invocation2)) {
                if (invocation == null) {
                    invocation = invocation2;
                }
                if (matchableInvocation.hasSameMethod(invocation2)) {
                    return invocation2;
                }
            }
        }
        return invocation;
    }

    public static Invocation findFirstUnverified(List<Invocation> list) {
        return findFirstUnverified(list, null);
    }

    static Invocation findFirstUnverified(List<Invocation> list, Object obj) {
        for (Invocation invocation : list) {
            boolean z = obj == null || obj == invocation.getMock();
            if (!invocation.isVerified() && z) {
                return invocation;
            }
        }
        return null;
    }

    public static Location getLastLocation(List<Invocation> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1).getLocation();
    }

    public static Invocation findPreviousVerifiedInOrder(List<Invocation> list, InOrderContext inOrderContext) {
        LinkedList linkedListFilter = ListUtil.filter(list, new RemoveUnverifiedInOrder(inOrderContext));
        if (linkedListFilter.isEmpty()) {
            return null;
        }
        return (Invocation) linkedListFilter.getLast();
    }

    private static List<Invocation> removeVerifiedInOrder(List<Invocation> list, InOrderContext inOrderContext) {
        LinkedList linkedList = new LinkedList();
        for (Invocation invocation : list) {
            if (inOrderContext.isVerified(invocation)) {
                linkedList.clear();
            } else {
                linkedList.add(invocation);
            }
        }
        return linkedList;
    }

    public static List<Location> getAllLocations(List<Invocation> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<Invocation> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next().getLocation());
        }
        return linkedList;
    }

    private static class RemoveNotMatching implements ListUtil.Filter<Invocation> {
        private final MatchableInvocation wanted;

        private RemoveNotMatching(MatchableInvocation matchableInvocation) {
            this.wanted = matchableInvocation;
        }

        @Override // org.mockito.internal.util.collections.ListUtil.Filter
        public boolean isOut(Invocation invocation) {
            return !this.wanted.matches(invocation);
        }
    }

    private static class RemoveUnverifiedInOrder implements ListUtil.Filter<Invocation> {
        private final InOrderContext orderingContext;

        public RemoveUnverifiedInOrder(InOrderContext inOrderContext) {
            this.orderingContext = inOrderContext;
        }

        @Override // org.mockito.internal.util.collections.ListUtil.Filter
        public boolean isOut(Invocation invocation) {
            return !this.orderingContext.isVerified(invocation);
        }
    }

    public static Invocation findFirstUnverifiedInOrder(InOrderContext inOrderContext, List<Invocation> list) {
        while (true) {
            Invocation invocation = null;
            for (Invocation invocation2 : list) {
                if (!inOrderContext.isVerified(invocation2)) {
                    if (invocation == null) {
                        invocation = invocation2;
                    }
                }
            }
            return invocation;
        }
    }
}
