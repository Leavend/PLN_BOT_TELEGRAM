package org.mockito.internal.invocation;

import java.util.Comparator;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class InvocationComparator implements Comparator<Invocation> {
    @Override // java.util.Comparator
    public int compare(Invocation invocation, Invocation invocation2) {
        return Integer.valueOf(invocation.getSequenceNumber()).compareTo(Integer.valueOf(invocation2.getSequenceNumber()));
    }
}
