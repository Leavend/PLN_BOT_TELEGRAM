package androidx.test.espresso.core.internal.deps.guava.collect;

import androidx.test.espresso.core.internal.deps.guava.base.Function;
import java.util.Comparator;

/* loaded from: classes5.dex */
public abstract class Ordering<T> implements Comparator<T> {
    protected Ordering() {
    }

    public static Ordering from(Comparator comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @Override // java.util.Comparator
    public abstract int compare(T t, T t2);

    public Ordering onResultOf(Function function) {
        return new ByFunctionOrdering(function, this);
    }
}
