package androidx.test.espresso.base;

import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.platform.tracing.Tracing;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class BaseLayerModule_ProvidesTracingFactory implements Provider<Tracing> {
    private final BaseLayerModule module;

    public BaseLayerModule_ProvidesTracingFactory(BaseLayerModule baseLayerModule) {
        this.module = baseLayerModule;
    }

    public static BaseLayerModule_ProvidesTracingFactory create(BaseLayerModule baseLayerModule) {
        return new BaseLayerModule_ProvidesTracingFactory(baseLayerModule);
    }

    public static Tracing providesTracing(BaseLayerModule baseLayerModule) {
        return (Tracing) Preconditions.checkNotNullFromProvides(baseLayerModule.providesTracing());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public Tracing get2() {
        return providesTracing(this.module);
    }
}
