package androidx.test.espresso.base;

import android.os.Looper;
import androidx.test.platform.tracing.Tracing;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class IdlingResourceRegistry_Factory implements Provider<IdlingResourceRegistry> {
    private final Provider<Looper> looperProvider;
    private final Provider<Tracing> tracerProvider;

    public IdlingResourceRegistry_Factory(Provider<Looper> provider, Provider<Tracing> provider2) {
        this.looperProvider = provider;
        this.tracerProvider = provider2;
    }

    public static IdlingResourceRegistry_Factory create(Provider<Looper> provider, Provider<Tracing> provider2) {
        return new IdlingResourceRegistry_Factory(provider, provider2);
    }

    public static IdlingResourceRegistry newInstance(Looper looper, Tracing tracing) {
        return new IdlingResourceRegistry(looper, tracing);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdlingResourceRegistry get2() {
        return newInstance(this.looperProvider.get2(), this.tracerProvider.get2());
    }
}
