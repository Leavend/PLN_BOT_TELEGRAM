package org.mockito.internal.junit;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class UnusedStubbingsFinder {
    public UnusedStubbings getUnusedStubbings(Iterable<Object> iterable) {
        return new UnusedStubbings(ListUtil.filter(AllInvocationsFinder.findStubbings(iterable), new ListUtil.Filter<Stubbing>() { // from class: org.mockito.internal.junit.UnusedStubbingsFinder.1
            @Override // org.mockito.internal.util.collections.ListUtil.Filter
            public boolean isOut(Stubbing stubbing) {
                return stubbing.wasUsed();
            }
        }));
    }

    public Collection<Invocation> getUnusedStubbingsByLocation(Iterable<Object> iterable) {
        Set<Stubbing> setFindStubbings = AllInvocationsFinder.findStubbings(iterable);
        HashSet hashSet = new HashSet();
        for (Stubbing stubbing : setFindStubbings) {
            if (stubbing.wasUsed()) {
                hashSet.add(stubbing.getInvocation().getLocation().toString());
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Stubbing stubbing2 : setFindStubbings) {
            String string = stubbing2.getInvocation().getLocation().toString();
            if (!hashSet.contains(string)) {
                linkedHashMap.put(string, stubbing2.getInvocation());
            }
        }
        return linkedHashMap.values();
    }
}
