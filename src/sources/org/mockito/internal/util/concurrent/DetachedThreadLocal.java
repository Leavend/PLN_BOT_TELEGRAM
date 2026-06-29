package org.mockito.internal.util.concurrent;

import org.mockito.internal.util.concurrent.WeakConcurrentMap;

/* loaded from: classes3.dex */
public class DetachedThreadLocal<T> implements Runnable {
    final WeakConcurrentMap<Thread, T> map;

    public enum Cleaner {
        THREAD,
        INLINE,
        MANUAL
    }

    protected T inheritValue(T t) {
        return t;
    }

    protected T initialValue(Thread thread) {
        return null;
    }

    /* renamed from: org.mockito.internal.util.concurrent.DetachedThreadLocal$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner;

        static {
            int[] iArr = new int[Cleaner.values().length];
            $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner = iArr;
            try {
                iArr[Cleaner.THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner[Cleaner.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner[Cleaner.INLINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public DetachedThreadLocal(Cleaner cleaner) {
        int i = AnonymousClass3.$SwitchMap$org$mockito$internal$util$concurrent$DetachedThreadLocal$Cleaner[cleaner.ordinal()];
        if (i == 1 || i == 2) {
            this.map = new WeakConcurrentMap<Thread, T>(cleaner == Cleaner.THREAD) { // from class: org.mockito.internal.util.concurrent.DetachedThreadLocal.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
                public T defaultValue(Thread thread) {
                    return (T) DetachedThreadLocal.this.initialValue(thread);
                }
            };
        } else {
            if (i == 3) {
                this.map = new WeakConcurrentMap.WithInlinedExpunction<Thread, T>() { // from class: org.mockito.internal.util.concurrent.DetachedThreadLocal.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // org.mockito.internal.util.concurrent.WeakConcurrentMap
                    public T defaultValue(Thread thread) {
                        return (T) DetachedThreadLocal.this.initialValue(thread);
                    }
                };
                return;
            }
            throw new AssertionError();
        }
    }

    public T get() {
        return this.map.get(Thread.currentThread());
    }

    public void set(T t) {
        this.map.put(Thread.currentThread(), t);
    }

    public void clear() {
        this.map.remove((WeakConcurrentMap<Thread, T>) Thread.currentThread());
    }

    public void clearAll() {
        this.map.clear();
    }

    public T pushTo(Thread thread) {
        T t = get();
        if (t != null) {
            this.map.put(thread, inheritValue(t));
        }
        return t;
    }

    public T fetchFrom(Thread thread) {
        T t = this.map.get(thread);
        if (t != null) {
            set(inheritValue(t));
        }
        return t;
    }

    public T get(Thread thread) {
        return this.map.get(thread);
    }

    public void define(Thread thread, T t) {
        this.map.put(thread, t);
    }

    public WeakConcurrentMap<Thread, T> getBackingMap() {
        return this.map;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.map.run();
    }
}
