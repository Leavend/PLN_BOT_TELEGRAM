package org.mockito.internal.invocation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

@Deprecated
/* loaded from: classes3.dex */
public class UnusedStubsFinder {
    public List<Invocation> find(List<?> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            for (Stubbing stubbing : MockUtil.getInvocationContainer(it.next()).getStubbedInvocations()) {
                if (!stubbing.wasUsed()) {
                    linkedList.add(stubbing.getInvocation());
                }
            }
        }
        return linkedList;
    }
}
