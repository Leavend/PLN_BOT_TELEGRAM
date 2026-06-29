package org.mockito.internal.invocation.finder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.mockito.internal.invocation.InvocationComparator;
import org.mockito.internal.stubbing.StubbingComparator;
import org.mockito.internal.util.DefaultMockingDetails;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class AllInvocationsFinder {
    private AllInvocationsFinder() {
    }

    public static List<Invocation> find(Iterable<?> iterable) {
        TreeSet treeSet = new TreeSet(new InvocationComparator());
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            treeSet.addAll(new DefaultMockingDetails(it.next()).getInvocations());
        }
        return new LinkedList(treeSet);
    }

    public static Set<Stubbing> findStubbings(Iterable<?> iterable) {
        TreeSet treeSet = new TreeSet(new StubbingComparator());
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            treeSet.addAll(new DefaultMockingDetails(it.next()).getStubbings());
        }
        return treeSet;
    }
}
