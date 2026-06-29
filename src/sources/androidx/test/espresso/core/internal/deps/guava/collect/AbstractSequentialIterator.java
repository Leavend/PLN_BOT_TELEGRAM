package androidx.test.espresso.core.internal.deps.guava.collect;

import java.util.NoSuchElementException;

/* loaded from: classes5.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    private Object nextOrNull;

    protected AbstractSequentialIterator(Object obj) {
        this.nextOrNull = obj;
    }

    protected abstract Object computeNext(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        T t = (T) this.nextOrNull;
        if (t == null) {
            throw new NoSuchElementException();
        }
        this.nextOrNull = computeNext(t);
        return t;
    }
}
