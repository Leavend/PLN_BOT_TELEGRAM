package androidx.test.espresso.base;

import android.content.Context;
import androidx.test.platform.io.PlatformTestStorage;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class DefaultFailureHandler_Factory implements Provider<DefaultFailureHandler> {
    private final Provider<Context> appContextProvider;
    private final Provider<PlatformTestStorage> testStorageProvider;

    public DefaultFailureHandler_Factory(Provider<Context> provider, Provider<PlatformTestStorage> provider2) {
        this.appContextProvider = provider;
        this.testStorageProvider = provider2;
    }

    public static DefaultFailureHandler_Factory create(Provider<Context> provider, Provider<PlatformTestStorage> provider2) {
        return new DefaultFailureHandler_Factory(provider, provider2);
    }

    public static DefaultFailureHandler newInstance(Context context, PlatformTestStorage platformTestStorage) {
        return new DefaultFailureHandler(context, platformTestStorage);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultFailureHandler get2() {
        return newInstance(this.appContextProvider.get2(), this.testStorageProvider.get2());
    }
}
