package org.mockito.internal.stubbing;

import java.util.Comparator;
import org.mockito.internal.invocation.InvocationComparator;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class StubbingComparator implements Comparator<Stubbing> {
    private final InvocationComparator invocationComparator = new InvocationComparator();

    @Override // java.util.Comparator
    public int compare(Stubbing stubbing, Stubbing stubbing2) {
        return this.invocationComparator.compare(stubbing.getInvocation(), stubbing2.getInvocation());
    }
}
