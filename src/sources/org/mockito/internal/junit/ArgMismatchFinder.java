package org.mockito.internal.junit;

import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
class ArgMismatchFinder {
    ArgMismatchFinder() {
    }

    StubbingArgMismatches getStubbingArgMismatches(Iterable<?> iterable) {
        StubbingArgMismatches stubbingArgMismatches = new StubbingArgMismatches();
        for (Invocation invocation : AllInvocationsFinder.find(iterable)) {
            if (invocation.stubInfo() == null) {
                for (Stubbing stubbing : AllInvocationsFinder.findStubbings(iterable)) {
                    if (!stubbing.wasUsed() && stubbing.getInvocation().getMock() == invocation.getMock() && stubbing.getInvocation().getMethod().getName().equals(invocation.getMethod().getName())) {
                        stubbingArgMismatches.add(invocation, stubbing.getInvocation());
                    }
                }
            }
        }
        return stubbingArgMismatches;
    }
}
