package androidx.test.espresso.core.internal.deps.dagger.internal;

import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class DoubleCheck<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = true;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private DoubleCheck(Provider provider) {
        if (!$assertionsDisabled && provider == null) {
            throw new AssertionError();
        }
        this.provider = provider;
    }

    public static Provider provider(Provider provider) {
        Preconditions.checkNotNull(provider);
        return provider instanceof DoubleCheck ? provider : new DoubleCheck(provider);
    }

    private static Object reentrantCheck(Object obj, Object obj2) {
        if (!(obj != UNINITIALIZED) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // javax.inject.Provider
    public T get() {
        Object obj = (T) this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.instance;
                if (obj == obj2) {
                    obj = (T) this.provider.get();
                    this.instance = reentrantCheck(this.instance, obj);
                    this.provider = null;
                }
            }
        }
        return (T) obj;
    }
}
