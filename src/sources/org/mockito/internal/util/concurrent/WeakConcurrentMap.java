package org.mockito.internal.util.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes3.dex */
public class WeakConcurrentMap<K, V> extends ReferenceQueue<K> implements Runnable, Iterable<Map.Entry<K, V>> {
    private static final AtomicLong ID = new AtomicLong();
    public final ConcurrentMap<WeakKey<K>, V> target = new ConcurrentHashMap();
    private final Thread thread;

    protected V defaultValue(K k) {
        return null;
    }

    public WeakConcurrentMap(boolean z) {
        if (z) {
            Thread thread = new Thread(this);
            this.thread = thread;
            thread.setName("weak-ref-cleaner-" + ID.getAndIncrement());
            thread.setPriority(1);
            thread.setDaemon(true);
            thread.start();
            return;
        }
        this.thread = null;
    }

    public V get(K k) {
        V vPutIfAbsent;
        k.getClass();
        V v = this.target.get(new LatentKey(k));
        if (v != null) {
            return v;
        }
        V vDefaultValue = defaultValue(k);
        return (vDefaultValue == null || (vPutIfAbsent = this.target.putIfAbsent(new WeakKey<>(k, this), vDefaultValue)) == null) ? vDefaultValue : vPutIfAbsent;
    }

    public boolean containsKey(K k) {
        k.getClass();
        return this.target.containsKey(new LatentKey(k));
    }

    public V put(K k, V v) {
        if (k == null || v == null) {
            throw null;
        }
        return this.target.put(new WeakKey<>(k, this), v);
    }

    public V remove(K k) {
        k.getClass();
        return this.target.remove(new LatentKey(k));
    }

    public void clear() {
        this.target.clear();
    }

    public Thread getCleanerThread() {
        return this.thread;
    }

    public void expungeStaleEntries() {
        while (true) {
            Reference<? extends K> referencePoll = poll();
            if (referencePoll == null) {
                return;
            } else {
                this.target.remove(referencePoll);
            }
        }
    }

    public int approximateSize() {
        return this.target.size();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                this.target.remove(remove());
            } catch (InterruptedException unused) {
                clear();
                return;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return new EntryIterator(this.target.entrySet().iterator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class WeakKey<T> extends WeakReference<T> {
        private final int hashCode;

        WeakKey(T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hashCode = System.identityHashCode(t);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            return obj instanceof LatentKey ? ((LatentKey) obj).key == get() : ((WeakKey) obj).get() == get();
        }
    }

    private static class LatentKey<T> {
        private final int hashCode;
        final T key;

        LatentKey(T t) {
            this.key = t;
            this.hashCode = System.identityHashCode(t);
        }

        public boolean equals(Object obj) {
            return obj instanceof LatentKey ? ((LatentKey) obj).key == this.key : ((WeakKey) obj).get() == this.key;
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    public static class WithInlinedExpunction<K, V> extends WeakConcurrentMap<K, V> {
        public WithInlinedExpunction() {
            super(false);
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
        public V get(K k) {
            expungeStaleEntries();
            return (V) super.get(k);
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
        public boolean containsKey(K k) {
            expungeStaleEntries();
            return super.containsKey(k);
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
        public V put(K k, V v) {
            expungeStaleEntries();
            return (V) super.put(k, v);
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
        public V remove(K k) {
            expungeStaleEntries();
            return (V) super.remove((WithInlinedExpunction<K, V>) k);
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            expungeStaleEntries();
            return super.iterator();
        }

        @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
        public int approximateSize() {
            expungeStaleEntries();
            return super.approximateSize();
        }
    }

    private class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private final Iterator<Map.Entry<WeakKey<K>, V>> iterator;
        private Map.Entry<WeakKey<K>, V> nextEntry;
        private K nextKey;

        private EntryIterator(Iterator<Map.Entry<WeakKey<K>, V>> it) {
            this.iterator = it;
            findNext();
        }

        private void findNext() {
            while (this.iterator.hasNext()) {
                Map.Entry<WeakKey<K>, V> next = this.iterator.next();
                this.nextEntry = next;
                K k = (K) next.getKey().get();
                this.nextKey = k;
                if (k != null) {
                    return;
                }
            }
            this.nextEntry = null;
            this.nextKey = null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextKey != null;
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (this.nextKey == null) {
                throw new NoSuchElementException();
            }
            try {
                return new SimpleEntry(this.nextKey, this.nextEntry);
            } finally {
                findNext();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class SimpleEntry implements Map.Entry<K, V> {
        final Map.Entry<WeakKey<K>, V> entry;
        private final K key;

        private SimpleEntry(K k, Map.Entry<WeakKey<K>, V> entry) {
            this.key = k;
            this.entry = entry;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.entry.getValue();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            v.getClass();
            return this.entry.setValue(v);
        }
    }
}
