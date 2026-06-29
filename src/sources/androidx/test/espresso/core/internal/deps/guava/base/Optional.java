package androidx.test.espresso.core.internal.deps.guava.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    Optional() {
    }

    public static Optional absent() {
        return Absent.withType();
    }

    public static Optional fromNullable(Object obj) {
        return obj == null ? absent() : new Present(obj);
    }

    public static Optional of(Object obj) {
        return new Present(Preconditions.checkNotNull(obj));
    }

    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterable() { // from class: androidx.test.espresso.core.internal.deps.guava.base.Optional.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new AbstractIterator() { // from class: androidx.test.espresso.core.internal.deps.guava.base.Optional.1.1
                    private final Iterator iterator;

                    {
                        this.iterator = (Iterator) Preconditions.checkNotNull(iterable.iterator());
                    }

                    @Override // androidx.test.espresso.core.internal.deps.guava.base.AbstractIterator
                    protected Object computeNext() {
                        while (this.iterator.hasNext()) {
                            Optional optional = (Optional) this.iterator.next();
                            if (optional.isPresent()) {
                                return optional.get();
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    public abstract Set asSet();

    public abstract boolean equals(Object obj);

    public abstract Object get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional or(Optional optional);

    public abstract Object or(Supplier supplier);

    public abstract Object or(Object obj);

    public abstract Object orNull();

    public abstract String toString();

    public abstract Optional transform(Function function);
}
