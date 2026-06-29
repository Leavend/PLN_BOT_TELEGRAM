package androidx.test.espresso.core.internal.deps.guava.collect;

import androidx.test.espresso.core.internal.deps.guava.base.Function;
import androidx.test.espresso.core.internal.deps.guava.base.Objects;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
public final class Iterators {
    public static boolean addAll(Collection collection, Iterator it) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(it);
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }

    public static boolean elementsEqual(Iterator it, Iterator it2) {
        while (it.hasNext()) {
            if (!it2.hasNext() || !Objects.equal(it.next(), it2.next())) {
                return false;
            }
        }
        return !it2.hasNext();
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> it, final Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        return new AbstractIterator() { // from class: androidx.test.espresso.core.internal.deps.guava.collect.Iterators.5
            @Override // androidx.test.espresso.core.internal.deps.guava.collect.AbstractIterator
            protected Object computeNext() {
                while (it.hasNext()) {
                    Object next = it.next();
                    if (predicate.apply(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        };
    }

    public static Object getNext(Iterator it, Object obj) {
        return it.hasNext() ? it.next() : obj;
    }

    public static Object getOnlyElement(Iterator it) {
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sbAppend = new StringBuilder("expected one element but was: <").append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            sbAppend.append(", ").append(it.next());
        }
        if (it.hasNext()) {
            sbAppend.append(", ...");
        }
        sbAppend.append(Typography.greater);
        throw new IllegalArgumentException(sbAppend.toString());
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(final T t) {
        return new UnmodifiableIterator() { // from class: androidx.test.espresso.core.internal.deps.guava.collect.Iterators.9
            boolean done;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.done;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.done) {
                    throw new NoSuchElementException();
                }
                this.done = true;
                return t;
            }
        };
    }

    public static Object[] toArray(Iterator it, Class cls) {
        return Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static String toString(Iterator it) {
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(it.next());
            z = false;
        }
        return sb.append(']').toString();
    }

    public static Iterator transform(Iterator it, final Function function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator(it) { // from class: androidx.test.espresso.core.internal.deps.guava.collect.Iterators.6
            @Override // androidx.test.espresso.core.internal.deps.guava.collect.TransformedIterator
            Object transform(Object obj) {
                return function.apply(obj);
            }
        };
    }
}
