package androidx.test.espresso.core.internal.deps.guava.collect;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Predicate;
import java.io.Serializable;
import java.lang.Comparable;

/* loaded from: classes5.dex */
public final class Range<C extends Comparable> extends RangeGwtSerializationDependencies implements Predicate<C>, Serializable {
    private static final Range ALL = new Range(Cut.belowAll(), Cut.aboveAll());
    private static final long serialVersionUID = 0;
    final Cut lowerBound;
    final Cut upperBound;

    private Range(Cut cut, Cut cut2) {
        this.lowerBound = (Cut) Preconditions.checkNotNull(cut);
        this.upperBound = (Cut) Preconditions.checkNotNull(cut2);
        if (cut.compareTo(cut2) > 0 || cut == Cut.aboveAll() || cut2 == Cut.belowAll()) {
            throw new IllegalArgumentException("Invalid range: " + toString(cut, cut2));
        }
    }

    public static Range all() {
        return ALL;
    }

    public static Range closed(Comparable comparable, Comparable comparable2) {
        return create(Cut.belowValue(comparable), Cut.aboveValue(comparable2));
    }

    static int compareOrThrow(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    static Range create(Cut cut, Cut cut2) {
        return new Range(cut, cut2);
    }

    @Override // androidx.test.espresso.core.internal.deps.guava.base.Predicate
    @Deprecated
    public boolean apply(Comparable comparable) {
        return contains(comparable);
    }

    public boolean contains(Comparable comparable) {
        Preconditions.checkNotNull(comparable);
        return this.lowerBound.isLessThan(comparable) && !this.upperBound.isLessThan(comparable);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        return this.lowerBound.equals(range.lowerBound) && this.upperBound.equals(range.upperBound);
    }

    public int hashCode() {
        return (this.lowerBound.hashCode() * 31) + this.upperBound.hashCode();
    }

    Object readResolve() {
        return equals(ALL) ? all() : this;
    }

    public String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    private static String toString(Cut cut, Cut cut2) {
        StringBuilder sb = new StringBuilder(16);
        cut.describeAsLowerBound(sb);
        sb.append("..");
        cut2.describeAsUpperBound(sb);
        return sb.toString();
    }
}
