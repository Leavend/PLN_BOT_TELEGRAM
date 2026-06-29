package androidx.test.espresso.base;

import android.content.Context;
import androidx.test.espresso.UiController;
import androidx.test.espresso.base.RootViewPicker;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

/* loaded from: classes5.dex */
public final class RootViewPicker_Factory implements Provider<RootViewPicker> {
    private final Provider<ActivityLifecycleMonitor> activityLifecycleMonitorProvider;
    private final Provider<Context> appContextProvider;
    private final Provider<ControlledLooper> controlledLooperProvider;
    private final Provider<AtomicReference<Boolean>> needsActivityProvider;
    private final Provider<RootViewPicker.RootResultFetcher> rootResultFetcherProvider;
    private final Provider<UiController> uiControllerProvider;

    public RootViewPicker_Factory(Provider<UiController> provider, Provider<RootViewPicker.RootResultFetcher> provider2, Provider<ActivityLifecycleMonitor> provider3, Provider<AtomicReference<Boolean>> provider4, Provider<ControlledLooper> provider5, Provider<Context> provider6) {
        this.uiControllerProvider = provider;
        this.rootResultFetcherProvider = provider2;
        this.activityLifecycleMonitorProvider = provider3;
        this.needsActivityProvider = provider4;
        this.controlledLooperProvider = provider5;
        this.appContextProvider = provider6;
    }

    public static RootViewPicker_Factory create(Provider<UiController> provider, Provider<RootViewPicker.RootResultFetcher> provider2, Provider<ActivityLifecycleMonitor> provider3, Provider<AtomicReference<Boolean>> provider4, Provider<ControlledLooper> provider5, Provider<Context> provider6) {
        return new RootViewPicker_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static RootViewPicker newInstance(UiController uiController, Object obj, ActivityLifecycleMonitor activityLifecycleMonitor, AtomicReference<Boolean> atomicReference, ControlledLooper controlledLooper, Context context) {
        return new RootViewPicker(uiController, (RootViewPicker.RootResultFetcher) obj, activityLifecycleMonitor, atomicReference, controlledLooper, context);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public RootViewPicker get2() {
        return newInstance(this.uiControllerProvider.get2(), this.rootResultFetcherProvider.get2(), this.activityLifecycleMonitorProvider.get2(), this.needsActivityProvider.get2(), this.controlledLooperProvider.get2(), this.appContextProvider.get2());
    }
}
