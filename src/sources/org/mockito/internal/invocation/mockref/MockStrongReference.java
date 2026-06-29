package org.mockito.internal.invocation.mockref;

import java.io.ObjectStreamException;

/* loaded from: classes3.dex */
public class MockStrongReference<T> implements MockReference<T> {
    private final boolean deserializeAsWeakRef;
    private final T ref;

    public MockStrongReference(T t, boolean z) {
        this.ref = t;
        this.deserializeAsWeakRef = z;
    }

    @Override // org.mockito.internal.invocation.mockref.MockReference
    public T get() {
        return this.ref;
    }

    private Object readResolve() throws ObjectStreamException {
        return this.deserializeAsWeakRef ? new MockWeakReference(this.ref) : this;
    }
}
