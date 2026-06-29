package androidx.test.core.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import androidx.tracing.Trace;
import java.io.Closeable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public final class ActivityScenario<A extends Activity> implements AutoCloseable, Closeable {
    private static final Map<Stage, Lifecycle.State> STEADY_STATES;
    private static final String TAG = "ActivityScenario";
    private static final long TIMEOUT_MILLISECONDS = 45000;
    private final ActivityInvoker activityInvoker;
    private final ActivityLifecycleCallback activityLifecycleObserver;
    private final ControlledLooper controlledLooper;
    private A currentActivity;
    private Stage currentActivityStage;
    private final ReentrantLock lock;
    private final Intent startActivityIntent;
    private final Condition stateChangedCondition;

    public interface ActivityAction<A extends Activity> {
        void perform(A activity);
    }

    static {
        EnumMap enumMap = new EnumMap(Stage.class);
        STEADY_STATES = enumMap;
        enumMap.put((EnumMap) Stage.RESUMED, (Stage) Lifecycle.State.RESUMED);
        enumMap.put((EnumMap) Stage.PAUSED, (Stage) Lifecycle.State.STARTED);
        enumMap.put((EnumMap) Stage.STOPPED, (Stage) Lifecycle.State.CREATED);
        enumMap.put((EnumMap) Stage.DESTROYED, (Stage) Lifecycle.State.DESTROYED);
    }

    static /* synthetic */ ActivityInvoker lambda$new$0() {
        return new InstrumentationActivityInvoker();
    }

    private ActivityScenario(Intent startActivityIntent) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.stateChangedCondition = reentrantLock.newCondition();
        this.activityInvoker = (ActivityInvoker) ServiceLoaderWrapper.loadSingleService(ActivityInvoker.class, ActivityScenario$$ExternalSyntheticLambda0.INSTANCE);
        this.controlledLooper = (ControlledLooper) ServiceLoaderWrapper.loadSingleService(ControlledLooper.class, ActivityScenario$$ExternalSyntheticLambda1.INSTANCE);
        this.currentActivityStage = Stage.PRE_ON_CREATE;
        this.activityLifecycleObserver = new ActivityLifecycleCallback() { // from class: androidx.test.core.app.ActivityScenario.1
            @Override // androidx.test.runner.lifecycle.ActivityLifecycleCallback
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (!ActivityScenario.activityMatchesIntent(ActivityScenario.this.startActivityIntent, activity)) {
                    Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the intent does not match. startActivityIntent=%s, activity.getIntent()=%s, activity=%s", ActivityScenario.this.startActivityIntent, activity.getIntent(), activity));
                    return;
                }
                ActivityScenario.this.lock.lock();
                try {
                    int i = AnonymousClass2.$SwitchMap$androidx$test$runner$lifecycle$Stage[ActivityScenario.this.currentActivityStage.ordinal()];
                    if (i == 1 || i == 2) {
                        if (stage != Stage.CREATED) {
                            Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the reported transition was not ON_CREATE while the last known transition was %s", ActivityScenario.this.currentActivityStage));
                            return;
                        }
                    } else if (ActivityScenario.this.currentActivity != activity) {
                        Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the activity instance does not match. currentActivity=%s, receivedActivity=%s", ActivityScenario.this.currentActivity, activity));
                        return;
                    }
                    ActivityScenario.this.currentActivityStage = stage;
                    ActivityScenario activityScenario = ActivityScenario.this;
                    if (stage == Stage.DESTROYED) {
                        activity = null;
                    }
                    activityScenario.currentActivity = activity;
                    Log.v(ActivityScenario.TAG, String.format("Update currentActivityStage to %s, currentActivity=%s", ActivityScenario.this.currentActivityStage, ActivityScenario.this.currentActivity));
                    ActivityScenario.this.stateChangedCondition.signal();
                } finally {
                    ActivityScenario.this.lock.unlock();
                }
            }
        };
        this.startActivityIntent = (Intent) Checks.checkNotNull(startActivityIntent);
    }

    private ActivityScenario(Class<A> activityClass) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.stateChangedCondition = reentrantLock.newCondition();
        ActivityInvoker activityInvoker = (ActivityInvoker) ServiceLoaderWrapper.loadSingleService(ActivityInvoker.class, ActivityScenario$$ExternalSyntheticLambda0.INSTANCE);
        this.activityInvoker = activityInvoker;
        this.controlledLooper = (ControlledLooper) ServiceLoaderWrapper.loadSingleService(ControlledLooper.class, ActivityScenario$$ExternalSyntheticLambda1.INSTANCE);
        this.currentActivityStage = Stage.PRE_ON_CREATE;
        this.activityLifecycleObserver = new ActivityLifecycleCallback() { // from class: androidx.test.core.app.ActivityScenario.1
            @Override // androidx.test.runner.lifecycle.ActivityLifecycleCallback
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (!ActivityScenario.activityMatchesIntent(ActivityScenario.this.startActivityIntent, activity)) {
                    Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the intent does not match. startActivityIntent=%s, activity.getIntent()=%s, activity=%s", ActivityScenario.this.startActivityIntent, activity.getIntent(), activity));
                    return;
                }
                ActivityScenario.this.lock.lock();
                try {
                    int i = AnonymousClass2.$SwitchMap$androidx$test$runner$lifecycle$Stage[ActivityScenario.this.currentActivityStage.ordinal()];
                    if (i == 1 || i == 2) {
                        if (stage != Stage.CREATED) {
                            Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the reported transition was not ON_CREATE while the last known transition was %s", ActivityScenario.this.currentActivityStage));
                            return;
                        }
                    } else if (ActivityScenario.this.currentActivity != activity) {
                        Log.v(ActivityScenario.TAG, String.format("Activity lifecycle changed event received but ignored because the activity instance does not match. currentActivity=%s, receivedActivity=%s", ActivityScenario.this.currentActivity, activity));
                        return;
                    }
                    ActivityScenario.this.currentActivityStage = stage;
                    ActivityScenario activityScenario = ActivityScenario.this;
                    if (stage == Stage.DESTROYED) {
                        activity = null;
                    }
                    activityScenario.currentActivity = activity;
                    Log.v(ActivityScenario.TAG, String.format("Update currentActivityStage to %s, currentActivity=%s", ActivityScenario.this.currentActivityStage, ActivityScenario.this.currentActivity));
                    ActivityScenario.this.stateChangedCondition.signal();
                } finally {
                    ActivityScenario.this.lock.unlock();
                }
            }
        };
        this.startActivityIntent = (Intent) Checks.checkNotNull(activityInvoker.getIntentForActivity((Class) Checks.checkNotNull(activityClass)));
    }

    public static <A extends Activity> ActivityScenario<A> launch(Class<A> activityClass) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        activityScenario.launchInternal(null, false);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Class<A> activityClass, Bundle activityOptions) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        activityScenario.launchInternal(activityOptions, false);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Intent startActivityIntent) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        activityScenario.launchInternal(null, false);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launch(Intent startActivityIntent, Bundle activityOptions) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        activityScenario.launchInternal(activityOptions, false);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launchActivityForResult(Class<A> activityClass) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        activityScenario.launchInternal(null, true);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launchActivityForResult(Class<A> activityClass, Bundle activityOptions) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Class) Checks.checkNotNull(activityClass));
        activityScenario.launchInternal(activityOptions, true);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launchActivityForResult(Intent startActivityIntent) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        activityScenario.launchInternal(null, true);
        return activityScenario;
    }

    public static <A extends Activity> ActivityScenario<A> launchActivityForResult(Intent startActivityIntent, Bundle activityOptions) {
        ActivityScenario<A> activityScenario = new ActivityScenario<>((Intent) Checks.checkNotNull(startActivityIntent));
        activityScenario.launchInternal(activityOptions, true);
        return activityScenario;
    }

    private void launchInternal(Bundle activityOptions, boolean launchActivityForResult) {
        Checks.checkState(Settings.System.getInt(InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver(), "always_finish_activities", 0) == 0, "\"Don't keep activities\" developer options must be disabled for ActivityScenario");
        Checks.checkNotMainThread();
        Trace.beginSection("ActivityScenario launch");
        try {
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
            ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(this.activityLifecycleObserver);
            if (activityOptions == null) {
                if (launchActivityForResult) {
                    this.activityInvoker.startActivityForResult(this.startActivityIntent);
                } else {
                    this.activityInvoker.startActivity(this.startActivityIntent);
                }
            } else if (launchActivityForResult) {
                this.activityInvoker.startActivityForResult(this.startActivityIntent, activityOptions);
            } else {
                this.activityInvoker.startActivity(this.startActivityIntent, activityOptions);
            }
            waitForActivityToBecomeAnyOf((Lifecycle.State[]) STEADY_STATES.values().toArray(new Lifecycle.State[0]));
        } finally {
            Trace.endSection();
        }
    }

    @Override // java.lang.AutoCloseable, java.io.Closeable
    public void close() {
        Trace.beginSection("ActivityScenario close");
        try {
            moveToState(Lifecycle.State.DESTROYED);
            ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(this.activityLifecycleObserver);
        } finally {
            Trace.endSection();
        }
    }

    private void waitForActivityToBecomeAnyOf(Lifecycle.State... expectedStates) {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        HashSet hashSet = new HashSet(Arrays.asList(expectedStates));
        this.lock.lock();
        try {
            try {
                if (hashSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                    return;
                }
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                long j = TIMEOUT_MILLISECONDS + jElapsedRealtime;
                while (jElapsedRealtime < j && !hashSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                    this.stateChangedCondition.await(j - jElapsedRealtime, TimeUnit.MILLISECONDS);
                    jElapsedRealtime = SystemClock.elapsedRealtime();
                }
                if (hashSet.contains(STEADY_STATES.get(this.currentActivityStage))) {
                } else {
                    throw new AssertionError(String.format("Activity never becomes requested state \"%s\" (last lifecycle transition = \"%s\")", hashSet, this.currentActivityStage));
                }
            } catch (InterruptedException e) {
                throw new AssertionError(String.format("Activity never becomes requested state \"%s\" (last lifecycle transition = \"%s\")", hashSet, this.currentActivityStage), e);
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean activityMatchesIntent(Intent startActivityIntent, Activity launchedActivity) {
        Intent intent = launchedActivity.getIntent();
        if (!equals(startActivityIntent.getAction(), intent.getAction()) || !equals(startActivityIntent.getData(), intent.getData()) || !equals(startActivityIntent.getType(), intent.getType())) {
            return false;
        }
        if (!(hasPackageEquivalentComponent(startActivityIntent) && hasPackageEquivalentComponent(intent)) && !equals(startActivityIntent.getPackage(), intent.getPackage())) {
            return false;
        }
        if ((startActivityIntent.getComponent() == null || equals(startActivityIntent.getComponent(), intent.getComponent())) && equals(startActivityIntent.getCategories(), intent.getCategories())) {
            return Build.VERSION.SDK_INT < 29 || equals(startActivityIntent.getIdentifier(), intent.getIdentifier());
        }
        return false;
    }

    private static boolean hasPackageEquivalentComponent(Intent intent) {
        ComponentName component = intent.getComponent();
        String str = intent.getPackage();
        return component != null && (str == null || str.equals(component.getPackageName()));
    }

    private static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    private static class ActivityState<A extends Activity> {
        final A activity;
        final Stage stage;
        final Lifecycle.State state;

        ActivityState(A activity, Lifecycle.State state, Stage stage) {
            this.activity = activity;
            this.state = state;
            this.stage = stage;
        }
    }

    private ActivityState<A> getCurrentActivityState() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        this.lock.lock();
        try {
            return new ActivityState<>(this.currentActivity, STEADY_STATES.get(this.currentActivityStage), this.currentActivityStage);
        } finally {
            this.lock.unlock();
        }
    }

    public ActivityScenario<A> moveToState(Lifecycle.State newState) {
        Checks.checkNotMainThread();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        ActivityState<A> currentActivityState = getCurrentActivityState();
        Checks.checkNotNull(currentActivityState.state, String.format("Current state was null unexpectedly. Last stage = %s", currentActivityState.stage));
        if (currentActivityState.state == newState) {
            return this;
        }
        Checks.checkState((currentActivityState.state == Lifecycle.State.DESTROYED || currentActivityState.activity == null) ? false : true, String.format("Cannot move to state \"%s\" since the Activity has been destroyed already", newState));
        int i = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$State[newState.ordinal()];
        if (i == 1) {
            this.activityInvoker.stopActivity(currentActivityState.activity);
        } else if (i == 2) {
            moveToState(Lifecycle.State.RESUMED);
            this.activityInvoker.pauseActivity(currentActivityState.activity);
        } else if (i == 3) {
            this.activityInvoker.resumeActivity(currentActivityState.activity);
        } else if (i == 4) {
            this.activityInvoker.finishActivity(currentActivityState.activity);
        } else {
            throw new IllegalArgumentException(String.format("A requested state \"%s\" is not supported", newState));
        }
        waitForActivityToBecomeAnyOf(newState);
        return this;
    }

    /* renamed from: androidx.test.core.app.ActivityScenario$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;
        static final /* synthetic */ int[] $SwitchMap$androidx$test$runner$lifecycle$Stage;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$State = iArr;
            try {
                iArr[Lifecycle.State.CREATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.RESUMED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Stage.values().length];
            $SwitchMap$androidx$test$runner$lifecycle$Stage = iArr2;
            try {
                iArr2[Stage.PRE_ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$test$runner$lifecycle$Stage[Stage.DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ActivityScenario<A> recreate() {
        ActivityState<A> currentActivityState;
        Checks.checkNotMainThread();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        ActivityState<A> currentActivityState2 = getCurrentActivityState();
        Checks.checkNotNull(currentActivityState2.activity);
        Checks.checkNotNull(currentActivityState2.state);
        moveToState(Lifecycle.State.RESUMED);
        this.activityInvoker.recreateActivity(currentActivityState2.activity);
        long jElapsedRealtime = SystemClock.elapsedRealtime() + TIMEOUT_MILLISECONDS;
        do {
            waitForActivityToBecomeAnyOf(Lifecycle.State.RESUMED);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            currentActivityState = getCurrentActivityState();
            if (jElapsedRealtime2 >= jElapsedRealtime) {
                break;
            }
        } while (currentActivityState.activity == currentActivityState2.activity);
        if (currentActivityState.activity == currentActivityState2.activity) {
            throw new IllegalStateException("Requested a re-creation of Activity but didn't happen");
        }
        moveToState(currentActivityState2.state);
        return this;
    }

    public ActivityScenario<A> onActivity(final ActivityAction<A> action) {
        Runnable runnable = new Runnable() { // from class: androidx.test.core.app.ActivityScenario$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6061lambda$onActivity$2$androidxtestcoreappActivityScenario(action);
            }
        };
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.controlledLooper.drainMainThreadUntilIdle();
            runnable.run();
        } else {
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
            InstrumentationRegistry.getInstrumentation().runOnMainSync(runnable);
        }
        return this;
    }

    /* renamed from: lambda$onActivity$2$androidx-test-core-app-ActivityScenario, reason: not valid java name */
    /* synthetic */ void m6061lambda$onActivity$2$androidxtestcoreappActivityScenario(ActivityAction activityAction) {
        Checks.checkMainThread();
        this.lock.lock();
        try {
            Checks.checkNotNull(this.currentActivity, "Cannot run onActivity since Activity has been destroyed already");
            activityAction.perform(this.currentActivity);
        } finally {
            this.lock.unlock();
        }
    }

    public Instrumentation.ActivityResult getResult() {
        return this.activityInvoker.getActivityResult();
    }

    public Lifecycle.State getState() {
        ActivityState<A> currentActivityState = getCurrentActivityState();
        return (Lifecycle.State) Checks.checkNotNull(currentActivityState.state, "Could not get current state of activity %s due to the transition is incomplete. Current stage = %s", currentActivityState.activity, currentActivityState.stage);
    }
}
