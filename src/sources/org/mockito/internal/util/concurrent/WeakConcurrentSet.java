package org.mockito.internal.util.concurrent;

import java.util.Iterator;
import java.util.Map;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;

/* loaded from: classes3.dex */
public class WeakConcurrentSet<V> implements Runnable, Iterable<V> {
    final WeakConcurrentMap<V, Boolean> target;

    public enum Cleaner {
        THREAD,
        INLINE,
        MANUAL
    }

    /* renamed from: org.mockito.internal.util.concurrent.WeakConcurrentSet$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner;

        static {
            int[] iArr = new int[Cleaner.values().length];
            $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner = iArr;
            try {
                iArr[Cleaner.INLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner[Cleaner.THREAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner[Cleaner.MANUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public WeakConcurrentSet(Cleaner cleaner) {
        int i = AnonymousClass1.$SwitchMap$org$mockito$internal$util$concurrent$WeakConcurrentSet$Cleaner[cleaner.ordinal()];
        if (i == 1) {
            this.target = new WeakConcurrentMap.WithInlinedExpunction();
        } else {
            if (i == 2 || i == 3) {
                this.target = new WeakConcurrentMap<>(cleaner == Cleaner.THREAD);
                return;
            }
            throw new AssertionError();
        }
    }

    public boolean add(V v) {
        return this.target.put(v, Boolean.TRUE) == null;
    }

    public boolean contains(V v) {
        return this.target.containsKey(v);
    }

    public boolean remove(V v) {
        return this.target.remove((WeakConcurrentMap<V, Boolean>) v).booleanValue();
    }

    public void clear() {
        this.target.clear();
    }

    public int approximateSize() {
        return this.target.approximateSize();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.target.run();
    }

    public void expungeStaleEntries() {
        this.target.expungeStaleEntries();
    }

    public Thread getCleanerThread() {
        return this.target.getCleanerThread();
    }

    @Override // java.lang.Iterable
    public Iterator<V> iterator() {
        return new ReducingIterator(this.target.iterator(), null);
    }

    private static class ReducingIterator<V> implements Iterator<V> {
        private final Iterator<Map.Entry<V, Boolean>> iterator;

        /* synthetic */ ReducingIterator(Iterator it, AnonymousClass1 anonymousClass1) {
            this(it);
        }

        private ReducingIterator(Iterator<Map.Entry<V, Boolean>> it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.iterator.next().getKey();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }
    }
}
