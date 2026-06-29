package androidx.test.espresso.core.internal.deps.guava.cache;

/* loaded from: classes5.dex */
public interface Cache<K, V> {
    Object getIfPresent(Object obj);

    void invalidateAll();

    void put(Object obj, Object obj2);
}
