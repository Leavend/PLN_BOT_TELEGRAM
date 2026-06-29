package androidx.test.espresso.base;

import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.io.PlatformTestStorageRegistry;

/* loaded from: classes5.dex */
public class PlatformTestStorageModule {
    PlatformTestStorage provideTestStorage() {
        return PlatformTestStorageRegistry.getInstance();
    }
}
