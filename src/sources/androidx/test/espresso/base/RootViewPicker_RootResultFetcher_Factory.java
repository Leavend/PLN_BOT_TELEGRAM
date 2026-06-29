package androidx.test.espresso.base;

import androidx.test.espresso.Root;
import androidx.test.espresso.base.RootViewPicker;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class RootViewPicker_RootResultFetcher_Factory implements Provider<RootViewPicker.RootResultFetcher> {
    private final Provider<ActiveRootLister> activeRootListerProvider;
    private final Provider<AtomicReference<Matcher<Root>>> rootMatcherRefProvider;

    public RootViewPicker_RootResultFetcher_Factory(Provider<ActiveRootLister> provider, Provider<AtomicReference<Matcher<Root>>> provider2) {
        this.activeRootListerProvider = provider;
        this.rootMatcherRefProvider = provider2;
    }

    public static RootViewPicker_RootResultFetcher_Factory create(Provider<ActiveRootLister> provider, Provider<AtomicReference<Matcher<Root>>> provider2) {
        return new RootViewPicker_RootResultFetcher_Factory(provider, provider2);
    }

    public static RootViewPicker.RootResultFetcher newInstance(ActiveRootLister activeRootLister, AtomicReference<Matcher<Root>> atomicReference) {
        return new RootViewPicker.RootResultFetcher(activeRootLister, atomicReference);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public RootViewPicker.RootResultFetcher get2() {
        return newInstance(this.activeRootListerProvider.get2(), this.rootMatcherRefProvider.get2());
    }
}
