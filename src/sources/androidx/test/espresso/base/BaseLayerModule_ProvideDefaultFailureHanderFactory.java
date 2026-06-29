package androidx.test.espresso.base;

import android.content.Context;
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.platform.io.PlatformTestStorage;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class BaseLayerModule_ProvideDefaultFailureHanderFactory implements Provider<DefaultFailureHandler> {
    private final Provider<Context> contextProvider;
    private final BaseLayerModule module;
    private final Provider<PlatformTestStorage> testStorageProvider;

    public BaseLayerModule_ProvideDefaultFailureHanderFactory(BaseLayerModule baseLayerModule, Provider<Context> provider, Provider<PlatformTestStorage> provider2) {
        this.module = baseLayerModule;
        this.contextProvider = provider;
        this.testStorageProvider = provider2;
    }

    public static BaseLayerModule_ProvideDefaultFailureHanderFactory create(BaseLayerModule baseLayerModule, Provider<Context> provider, Provider<PlatformTestStorage> provider2) {
        return new BaseLayerModule_ProvideDefaultFailureHanderFactory(baseLayerModule, provider, provider2);
    }

    public static DefaultFailureHandler provideDefaultFailureHander(BaseLayerModule baseLayerModule, Context context, PlatformTestStorage platformTestStorage) {
        return (DefaultFailureHandler) Preconditions.checkNotNullFromProvides(baseLayerModule.provideDefaultFailureHander(context, platformTestStorage));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultFailureHandler get2() {
        return provideDefaultFailureHander(this.module, this.contextProvider.get2(), this.testStorageProvider.get2());
    }
}
