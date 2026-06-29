package androidx.test.espresso;

import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.espresso.internal.data.TestFlowVisualizer;
import androidx.test.platform.io.PlatformTestStorage;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class ViewInteractionModule_ProvideTestFlowVisualizerFactory implements Provider<TestFlowVisualizer> {
    private final ViewInteractionModule module;
    private final Provider<PlatformTestStorage> platformTestStorageProvider;

    public ViewInteractionModule_ProvideTestFlowVisualizerFactory(ViewInteractionModule viewInteractionModule, Provider<PlatformTestStorage> provider) {
        this.module = viewInteractionModule;
        this.platformTestStorageProvider = provider;
    }

    public static ViewInteractionModule_ProvideTestFlowVisualizerFactory create(ViewInteractionModule viewInteractionModule, Provider<PlatformTestStorage> provider) {
        return new ViewInteractionModule_ProvideTestFlowVisualizerFactory(viewInteractionModule, provider);
    }

    public static TestFlowVisualizer provideTestFlowVisualizer(ViewInteractionModule viewInteractionModule, PlatformTestStorage platformTestStorage) {
        return (TestFlowVisualizer) Preconditions.checkNotNullFromProvides(viewInteractionModule.provideTestFlowVisualizer(platformTestStorage));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public TestFlowVisualizer get2() {
        return provideTestFlowVisualizer(this.module, this.platformTestStorageProvider.get2());
    }
}
