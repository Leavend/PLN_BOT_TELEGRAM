package androidx.test.espresso.base;

import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.platform.io.PlatformTestStorage;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class PlatformTestStorageModule_ProvideTestStorageFactory implements Provider<PlatformTestStorage> {
    private final PlatformTestStorageModule module;

    public PlatformTestStorageModule_ProvideTestStorageFactory(PlatformTestStorageModule platformTestStorageModule) {
        this.module = platformTestStorageModule;
    }

    public static PlatformTestStorageModule_ProvideTestStorageFactory create(PlatformTestStorageModule platformTestStorageModule) {
        return new PlatformTestStorageModule_ProvideTestStorageFactory(platformTestStorageModule);
    }

    public static PlatformTestStorage provideTestStorage(PlatformTestStorageModule platformTestStorageModule) {
        return (PlatformTestStorage) Preconditions.checkNotNullFromProvides(platformTestStorageModule.provideTestStorage());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    public PlatformTestStorage get() {
        return provideTestStorage(this.module);
    }
}
