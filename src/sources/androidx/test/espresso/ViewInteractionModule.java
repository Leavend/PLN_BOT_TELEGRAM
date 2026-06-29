package androidx.test.espresso;

import android.view.View;
import androidx.test.espresso.base.RootViewPicker;
import androidx.test.espresso.base.ViewFinderImpl;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.internal.data.TestFlowVisualizer;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.remote.RemoteInteraction;
import androidx.test.espresso.remote.RemoteInteractionRegistry;
import androidx.test.platform.io.PlatformTestStorage;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class ViewInteractionModule {
    private final Matcher<View> viewMatcher;
    private final AtomicReference<Matcher<Root>> rootMatcher = new AtomicReference<>(RootMatchers.DEFAULT);
    private final AtomicReference<Boolean> needsActivity = new AtomicReference<>(true);

    ViewInteractionModule(Matcher<View> matcher) {
        this.viewMatcher = (Matcher) Preconditions.checkNotNull(matcher);
    }

    AtomicReference<Boolean> provideNeedsActivity() {
        return this.needsActivity;
    }

    RemoteInteraction provideRemoteInteraction() {
        return RemoteInteractionRegistry.getInstance();
    }

    AtomicReference<Matcher<Root>> provideRootMatcher() {
        return this.rootMatcher;
    }

    public View provideRootView(RootViewPicker rootViewPicker) {
        return rootViewPicker.get2();
    }

    TestFlowVisualizer provideTestFlowVisualizer(PlatformTestStorage platformTestStorage) {
        return TestFlowVisualizer.getInstance(platformTestStorage);
    }

    ViewFinder provideViewFinder(ViewFinderImpl viewFinderImpl) {
        return viewFinderImpl;
    }

    Matcher<View> provideViewMatcher() {
        return this.viewMatcher;
    }
}
