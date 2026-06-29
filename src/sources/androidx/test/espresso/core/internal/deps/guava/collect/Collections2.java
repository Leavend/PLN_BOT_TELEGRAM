package androidx.test.espresso.core.internal.deps.guava.collect;

/* loaded from: classes5.dex */
public final class Collections2 {
    static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, "size");
        return new StringBuilder((int) Math.min(i * 8, 1073741824L));
    }
}
