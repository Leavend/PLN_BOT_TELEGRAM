package org.mockito.internal.verification;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class DefaultRegisteredInvocations implements RegisteredInvocations, Serializable {
    private static final long serialVersionUID = -2674402327380736290L;
    private final LinkedList<Invocation> invocations = new LinkedList<>();

    @Override // org.mockito.internal.verification.RegisteredInvocations
    public void add(Invocation invocation) {
        synchronized (this.invocations) {
            this.invocations.add(invocation);
        }
    }

    @Override // org.mockito.internal.verification.RegisteredInvocations
    public void removeLast() {
        synchronized (this.invocations) {
            if (!this.invocations.isEmpty()) {
                this.invocations.removeLast();
            }
        }
    }

    @Override // org.mockito.internal.verification.RegisteredInvocations
    public List<Invocation> getAll() {
        LinkedList linkedList;
        synchronized (this.invocations) {
            linkedList = new LinkedList(this.invocations);
        }
        return ListUtil.filter(linkedList, new RemoveToString());
    }

    @Override // org.mockito.internal.verification.RegisteredInvocations
    public void clear() {
        synchronized (this.invocations) {
            this.invocations.clear();
        }
    }

    @Override // org.mockito.internal.verification.RegisteredInvocations
    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.invocations) {
            zIsEmpty = this.invocations.isEmpty();
        }
        return zIsEmpty;
    }

    private static class RemoveToString implements ListUtil.Filter<Invocation> {
        private RemoveToString() {
        }

        @Override // org.mockito.internal.util.collections.ListUtil.Filter
        public boolean isOut(Invocation invocation) {
            return ObjectMethodsGuru.isToStringMethod(invocation.getMethod());
        }
    }
}
