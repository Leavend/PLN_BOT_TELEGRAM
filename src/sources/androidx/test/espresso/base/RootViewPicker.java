package androidx.test.espresso.base;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.test.espresso.EspressoException;
import androidx.test.espresso.NoActivityResumedException;
import androidx.test.espresso.NoMatchingRootException;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.ImmutableList;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.core.internal.deps.guava.collect.UnmodifiableIterator;
import androidx.test.espresso.internal.inject.TargetContext;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.internal.util.LogUtil;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import androidx.test.runner.lifecycle.Stage;
import com.google.logging.type.LogSeverity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class RootViewPicker implements Provider<View> {
    private static final ImmutableList<Integer> CREATED_WAIT_TIMES = ImmutableList.of((Object) 10, (Object) 50, (Object) 150, (Object) Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
    private static final ImmutableList<Integer> RESUMED_WAIT_TIMES = ImmutableList.of((Object) 10, (Object) 50, (Object) 100, (Object) 500, (Object) 2000, (Object) 30000);
    private static final String TAG = "RootViewPicker";
    private final ActivityLifecycleMonitor activityLifecycleMonitor;
    private final Context appContext;
    private final ControlledLooper controlledLooper;
    private final AtomicReference<Boolean> needsActivity;
    private final RootResultFetcher rootResultFetcher;
    private final UiController uiController;

    /* renamed from: androidx.test.espresso.base.RootViewPicker$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$test$espresso$base$RootViewPicker$RootResults$State;

        static {
            int[] iArr = new int[RootResults.State.values().length];
            $SwitchMap$androidx$test$espresso$base$RootViewPicker$RootResults$State = iArr;
            try {
                iArr[RootResults.State.ROOTS_PICKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$test$espresso$base$RootViewPicker$RootResults$State[RootResults.State.NO_ROOTS_PRESENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$test$espresso$base$RootViewPicker$RootResults$State[RootResults.State.NO_ROOTS_PICKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static abstract class BackOff {
        private final List<Integer> backoffTimes;
        private int numberOfAttempts = 0;
        private final TimeUnit timeUnit;

        public BackOff(List<Integer> list, TimeUnit timeUnit) {
            this.backoffTimes = list;
            this.timeUnit = timeUnit;
        }

        protected final long getBackoffForAttempt() {
            if (this.numberOfAttempts >= this.backoffTimes.size()) {
                List<Integer> list = this.backoffTimes;
                return list.get(list.size() - 1).intValue();
            }
            int iIntValue = this.backoffTimes.get(this.numberOfAttempts).intValue();
            this.numberOfAttempts++;
            return this.timeUnit.toMillis(iIntValue);
        }

        protected abstract long getNextBackoffInMillis();
    }

    private static final class NoActiveRootsBackoff extends BackOff {
        private static final ImmutableList<Integer> NO_ACTIVE_ROOTS_BACKOFF = ImmutableList.of((Object) 10, (Object) 10, (Object) 20, (Object) 30, (Object) 50, (Object) 80, (Object) 130, (Object) 210, (Object) 340);

        public NoActiveRootsBackoff() {
            super(NO_ACTIVE_ROOTS_BACKOFF, TimeUnit.MILLISECONDS);
        }

        @Override // androidx.test.espresso.base.RootViewPicker.BackOff
        public long getNextBackoffInMillis() {
            long backoffForAttempt = getBackoffForAttempt();
            LogUtil.logDebugWithProcess(RootViewPicker.TAG, "No active roots available - waiting: %sms for one to appear.", Long.valueOf(backoffForAttempt));
            return backoffForAttempt;
        }
    }

    private static final class NoMatchingRootBackoff extends BackOff {
        private static final ImmutableList<Integer> NO_MATCHING_ROOT_BACKOFF = ImmutableList.of((Object) 10, (Object) 20, (Object) 200, (Object) 400, (Object) 1000, (Object) 2000);

        public NoMatchingRootBackoff() {
            super(NO_MATCHING_ROOT_BACKOFF, TimeUnit.MILLISECONDS);
        }

        @Override // androidx.test.espresso.base.RootViewPicker.BackOff
        public long getNextBackoffInMillis() {
            long backoffForAttempt = getBackoffForAttempt();
            Log.d(RootViewPicker.TAG, String.format(Locale.ROOT, "No matching root available - waiting: %sms for one to appear.", Long.valueOf(backoffForAttempt)));
            return backoffForAttempt;
        }
    }

    private static final class RootReadyBackoff extends BackOff {
        private static final ImmutableList<Integer> ROOT_READY_BACKOFF = ImmutableList.of((Object) 10, (Object) 25, (Object) 50, (Object) 100, (Object) 200, (Object) 400, (Object) Integer.valueOf(LogSeverity.EMERGENCY_VALUE), (Object) 1000);

        public RootReadyBackoff() {
            super(ROOT_READY_BACKOFF, TimeUnit.MILLISECONDS);
        }

        @Override // androidx.test.espresso.base.RootViewPicker.BackOff
        public long getNextBackoffInMillis() {
            long backoffForAttempt = getBackoffForAttempt();
            Log.d(RootViewPicker.TAG, String.format(Locale.ROOT, "Root not ready - waiting: %sms for one to appear.", Long.valueOf(backoffForAttempt)));
            return backoffForAttempt;
        }
    }

    static class RootResultFetcher {
        private final ActiveRootLister activeRootLister;
        private final Matcher<Root> selector;

        public RootResultFetcher(ActiveRootLister activeRootLister, AtomicReference<Matcher<Root>> atomicReference) {
            this.activeRootLister = activeRootLister;
            this.selector = atomicReference.get();
        }

        public RootResults fetch() {
            List<Root> listListActiveRoots = this.activeRootLister.listActiveRoots();
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            for (Root root : listListActiveRoots) {
                if (this.selector.matches(root)) {
                    arrayListNewArrayList.add(root);
                }
            }
            return new RootResults(listListActiveRoots, arrayListNewArrayList, this.selector);
        }
    }

    private static class RootResults {
        private final List<Root> allRoots;
        private final List<Root> pickedRoots;
        private final Matcher<Root> rootSelector;

        enum State {
            NO_ROOTS_PRESENT,
            NO_ROOTS_PICKED,
            ROOTS_PICKED
        }

        private RootResults(List<Root> list, List<Root> list2, Matcher<Root> matcher) {
            this.allRoots = list;
            this.pickedRoots = list2;
            this.rootSelector = matcher;
        }

        private Root getRootFromMultipleRoots() {
            Root root = this.pickedRoots.get(0);
            if (this.pickedRoots.size() > 0) {
                for (Root root2 : this.pickedRoots) {
                    if (RootMatchers.isDialog().matches(root2)) {
                        return root2;
                    }
                    if (isTopmostRoot(root, root2)) {
                        root = root2;
                    }
                }
            }
            return root;
        }

        private static boolean isTopmostRoot(Root root, Root root2) {
            return root2.getWindowLayoutParams().get().type > root.getWindowLayoutParams().get().type;
        }

        public Root getPickedRoot() {
            if (this.pickedRoots.size() <= 1) {
                return this.pickedRoots.get(0);
            }
            LogUtil.logDebugWithProcess(RootViewPicker.TAG, "Multiple root windows detected: %s", this.pickedRoots);
            return getRootFromMultipleRoots();
        }

        public State getState() {
            return this.allRoots.isEmpty() ? State.NO_ROOTS_PRESENT : this.pickedRoots.isEmpty() ? State.NO_ROOTS_PICKED : this.pickedRoots.size() > 0 ? State.ROOTS_PICKED : State.NO_ROOTS_PICKED;
        }
    }

    private static final class RootViewWithoutFocusException extends RuntimeException implements EspressoException {
        private RootViewWithoutFocusException(String str) {
            super(str);
        }
    }

    RootViewPicker(UiController uiController, RootResultFetcher rootResultFetcher, ActivityLifecycleMonitor activityLifecycleMonitor, AtomicReference<Boolean> atomicReference, ControlledLooper controlledLooper, @TargetContext Context context) {
        this.uiController = uiController;
        this.rootResultFetcher = rootResultFetcher;
        this.activityLifecycleMonitor = activityLifecycleMonitor;
        this.needsActivity = atomicReference;
        this.controlledLooper = controlledLooper;
        this.appContext = context;
    }

    private List<Activity> getAllActiveActivities() {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        Iterator it = EnumSet.range(Stage.PRE_ON_CREATE, Stage.RESTARTED).iterator();
        while (it.hasNext()) {
            arrayListNewArrayList.addAll(this.activityLifecycleMonitor.getActivitiesInStage((Stage) it.next()));
        }
        return arrayListNewArrayList;
    }

    private Root pickARoot() {
        long jCurrentTimeMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L);
        RootResults rootResultsFetch = this.rootResultFetcher.fetch();
        NoActiveRootsBackoff noActiveRootsBackoff = new NoActiveRootsBackoff();
        NoMatchingRootBackoff noMatchingRootBackoff = new NoMatchingRootBackoff();
        while (System.currentTimeMillis() <= jCurrentTimeMillis) {
            int i = AnonymousClass1.$SwitchMap$androidx$test$espresso$base$RootViewPicker$RootResults$State[rootResultsFetch.getState().ordinal()];
            if (i == 1) {
                return rootResultsFetch.getPickedRoot();
            }
            if (i == 2) {
                this.uiController.loopMainThreadForAtLeast(noActiveRootsBackoff.getNextBackoffInMillis());
            } else if (i == 3) {
                this.uiController.loopMainThreadForAtLeast(noMatchingRootBackoff.getNextBackoffInMillis());
            }
            rootResultsFetch = this.rootResultFetcher.fetch();
        }
        if (RootResults.State.ROOTS_PICKED == rootResultsFetch.getState()) {
            return rootResultsFetch.getPickedRoot();
        }
        throw NoMatchingRootException.create(rootResultsFetch.rootSelector, rootResultsFetch.allRoots);
    }

    private View pickRootView() {
        return waitForRootToBeReady(pickARoot()).getDecorView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void waitForAtLeastOneActivityToBeResumed() {
        Collection<Activity> activitiesInStage = this.activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED);
        if (activitiesInStage.isEmpty()) {
            this.uiController.loopMainThreadUntilIdle();
            activitiesInStage = this.activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED);
        }
        if (!activitiesInStage.isEmpty()) {
            ConfigurationSynchronizationUtils.waitForConfigurationChangesOnActivity((Activity) activitiesInStage.toArray()[0], this.uiController, this.appContext);
            return;
        }
        List<Activity> allActiveActivities = getAllActiveActivities();
        if (allActiveActivities.isEmpty()) {
            UnmodifiableIterator it = CREATED_WAIT_TIMES.iterator();
            while (it.hasNext()) {
                long jIntValue = ((Integer) it.next()).intValue();
                Log.w(TAG, "No activities found - waiting: " + jIntValue + "ms for one to appear.");
                this.uiController.loopMainThreadForAtLeast(jIntValue);
                allActiveActivities = getAllActiveActivities();
                if (!allActiveActivities.isEmpty()) {
                    break;
                }
            }
        }
        if (allActiveActivities.isEmpty()) {
            throw new NoActivityResumedException("No activities found. Did you forget to launch the activity by calling getActivity() or startActivitySync or similar?");
        }
        UnmodifiableIterator it2 = RESUMED_WAIT_TIMES.iterator();
        while (it2.hasNext()) {
            long jIntValue2 = ((Integer) it2.next()).intValue();
            Log.w(TAG, "No activity currently resumed - waiting: " + jIntValue2 + "ms for one to appear.");
            this.uiController.loopMainThreadForAtLeast(jIntValue2);
            if (!this.activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED).isEmpty()) {
                return;
            }
        }
        throw new NoActivityResumedException("No activities in stage RESUMED. Did you forget to launch the activity. (test.getActivity() or similar)?");
    }

    private Root waitForRootToBeReady(Root root) {
        long jCurrentTimeMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L);
        RootReadyBackoff rootReadyBackoff = new RootReadyBackoff();
        while (System.currentTimeMillis() <= jCurrentTimeMillis) {
            if (root.isReady()) {
                return root;
            }
            this.controlledLooper.simulateWindowFocus(root.getDecorView());
            this.uiController.loopMainThreadForAtLeast(rootReadyBackoff.getNextBackoffInMillis());
        }
        throw new RootViewWithoutFocusException(String.format(Locale.ROOT, "Waited for the root of the view hierarchy to have window focus and not request layout for 10 seconds. If you specified a non default root matcher, it may be picking a root that never takes focus. Root:\n%s", root));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public View get2() {
        Preconditions.checkState(Looper.getMainLooper().equals(Looper.myLooper()), "must be called on main thread.");
        if (this.needsActivity.get().booleanValue()) {
            waitForAtLeastOneActivityToBeResumed();
        }
        return pickRootView();
    }
}
