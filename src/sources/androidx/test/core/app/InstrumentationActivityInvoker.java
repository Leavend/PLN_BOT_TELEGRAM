package androidx.test.core.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class InstrumentationActivityInvoker implements ActivityInvoker {
    private static final String BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY";
    private static final String BOOTSTRAP_ACTIVITY_RESULT_RECEIVED = "androidx.test.core.app.InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED";
    private static final String CANCEL_ACTIVITY_RESULT_WAITER = "androidx.test.core.app.InstrumentationActivityInvoker.CANCEL_ACTIVITY_RESULT_WAITER";
    private static final String EMPTY_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED";
    private static final String EMPTY_FLOATING_ACTIVITY_RESUMED = "androidx.test.core.app.InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED";
    private static final String FINISH_BOOTSTRAP_ACTIVITY = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY";
    private static final String FINISH_EMPTY_ACTIVITIES = "androidx.test.core.app.InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES";
    private static final String TARGET_ACTIVITY_INTENT_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.START_TARGET_ACTIVITY_INTENT_KEY";
    private static final String TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY = "androidx.test.core.app.InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY";
    private ActivityResultWaiter activityResultWaiter;

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public /* synthetic */ Intent getIntentForActivity(Class cls) {
        return ActivityInvoker.CC.$default$getIntentForActivity(this, cls);
    }

    InstrumentationActivityInvoker() {
    }

    public static class BootstrapActivity extends Activity {
        private static final String IS_TARGET_ACTIVITY_STARTED_KEY = "IS_TARGET_ACTIVITY_STARTED_KEY";
        private static final String TAG = "androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity";
        private boolean isTargetActivityStarted;
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.BootstrapActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BootstrapActivity.this.finishActivity(0);
                BootstrapActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_BOOTSTRAP_ACTIVITY));
            this.isTargetActivityStarted = savedInstanceState != null && savedInstanceState.getBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, false);
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            if (this.isTargetActivityStarted) {
                return;
            }
            this.isTargetActivityStarted = true;
            PendingIntent pendingIntent = (PendingIntent) Checks.checkNotNull((PendingIntent) getIntent().getParcelableExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_INTENT_KEY));
            Bundle bundleExtra = getIntent().getBundleExtra(InstrumentationActivityInvoker.TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY);
            try {
                if (bundleExtra == null) {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 268435456, 0, 0);
                } else {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 0, null, 268435456, 0, 0, bundleExtra);
                }
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "Failed to start target activity.", e);
                throw new RuntimeException(e);
            }
        }

        @Override // android.app.Activity
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(IS_TARGET_ACTIVITY_STARTED_KEY, this.isTargetActivityStarted);
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }

        @Override // android.app.Activity
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 0) {
                Intent intent = new Intent(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED);
                intent.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, resultCode);
                if (data != null) {
                    intent.putExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY, data);
                }
                sendBroadcast(intent);
                finish();
            }
        }
    }

    private static class ActivityResultWaiter {
        private static final String TAG = "androidx.test.core.app.InstrumentationActivityInvoker$ActivityResultWaiter";
        private Instrumentation.ActivityResult activityResult;
        private final CountDownLatch latch = new CountDownLatch(1);

        public ActivityResultWaiter(Context context) {
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.ActivityResultWaiter.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    context2.unregisterReceiver(this);
                    if (InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED.equals(intent.getAction())) {
                        int intExtra = intent.getIntExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_CODE_KEY, 0);
                        Intent intent2 = (Intent) intent.getParcelableExtra(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_DATA_KEY);
                        if (intent2 != null) {
                            intent2 = new Intent(intent2);
                        }
                        ActivityResultWaiter.this.activityResult = new Instrumentation.ActivityResult(intExtra, intent2);
                        ActivityResultWaiter.this.latch.countDown();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter(InstrumentationActivityInvoker.BOOTSTRAP_ACTIVITY_RESULT_RECEIVED);
            intentFilter.addAction(InstrumentationActivityInvoker.CANCEL_ACTIVITY_RESULT_WAITER);
            InstrumentationActivityInvoker.registerBroadcastReceiver(context, broadcastReceiver, intentFilter);
        }

        public Instrumentation.ActivityResult getActivityResult() throws InterruptedException {
            try {
                this.latch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.i(TAG, "Waiting activity result was interrupted", e);
            }
            Checks.checkNotNull(this.activityResult, "onActivityResult never be called after %d milliseconds", Long.valueOf(ActivityLifecycleTimeout.getMillis()));
            return this.activityResult;
        }
    }

    public static class EmptyActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_ACTIVITY_RESUMED));
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }
    }

    public static class EmptyFloatingActivity extends Activity {
        private final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: androidx.test.core.app.InstrumentationActivityInvoker.EmptyFloatingActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmptyFloatingActivity.this.finish();
            }
        };

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            InstrumentationActivityInvoker.registerBroadcastReceiver(this, this.receiver, new IntentFilter(InstrumentationActivityInvoker.FINISH_EMPTY_ACTIVITIES));
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            overridePendingTransition(0, 0);
        }

        @Override // android.app.Activity
        protected void onResume() {
            super.onResume();
            sendBroadcast(new Intent(InstrumentationActivityInvoker.EMPTY_FLOATING_ACTIVITY_RESUMED));
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(this.receiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent, Bundle activityOptions) {
        if (intent.resolveActivityInfo(ApplicationProvider.getApplicationContext().getPackageManager(), 0) == null) {
            throw new RuntimeException("Unable to resolve activity for: " + String.valueOf(intent));
        }
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
        intent.addFlags(268468224);
        if (Build.VERSION.SDK_INT >= 28) {
            InstrumentationRegistry.getInstrumentation().startActivitySync(intent, activityOptions);
        } else {
            if (activityOptions != null) {
                throw new IllegalStateException("Starting an activity with activityOptions is not supported on APIs below 28.");
            }
            InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivityForResult(Intent intent, Bundle activityOptions) {
        if (intent.resolveActivityInfo(ApplicationProvider.getApplicationContext().getPackageManager(), 0) == null) {
            throw new IllegalStateException("Unable to resolve activity for: " + String.valueOf(intent));
        }
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
        this.activityResultWaiter = new ActivityResultWaiter(ApplicationProvider.getApplicationContext());
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(BootstrapActivity.class).setFlags(268468224).putExtra(TARGET_ACTIVITY_INTENT_KEY, PendingIntent.getActivity(ApplicationProvider.getApplicationContext(), 0, intent, 167772160)).putExtra(TARGET_ACTIVITY_OPTIONS_BUNDLE_KEY, activityOptions), activityOptions);
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void startActivityForResult(Intent intent) {
        startActivityForResult(intent, null);
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public Instrumentation.ActivityResult getActivityResult() {
        ActivityResultWaiter activityResultWaiter = this.activityResultWaiter;
        if (activityResultWaiter == null) {
            throw new IllegalStateException("You must start Activity first. Make sure you are using launchActivityForResult() to launch an Activity.");
        }
        return activityResultWaiter.getActivityResult();
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void resumeActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void pauseActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED);
        startFloatingEmptyActivitySync();
    }

    private void startFloatingEmptyActivitySync() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                countDownLatch.countDown();
            }
        };
        registerBroadcastReceiver(ApplicationProvider.getApplicationContext(), broadcastReceiver, new IntentFilter(EMPTY_FLOATING_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyFloatingActivity.class).setFlags(268435456));
        try {
            try {
                countDownLatch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to pause activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void stopActivity(Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        startEmptyActivitySync();
    }

    private void startEmptyActivitySync() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: androidx.test.core.app.InstrumentationActivityInvoker.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                countDownLatch.countDown();
            }
        };
        registerBroadcastReceiver(ApplicationProvider.getApplicationContext(), broadcastReceiver, new IntentFilter(EMPTY_ACTIVITY_RESUMED));
        ApplicationProvider.getApplicationContext().startActivity(getIntentForActivity(EmptyActivity.class).setFlags(268435456));
        try {
            try {
                countDownLatch.await(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to stop activity", e);
            }
        } finally {
            ApplicationProvider.getApplicationContext().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void recreateActivity(final Activity activity) {
        checkActivityStageIsIn(activity, Stage.RESUMED, Stage.PAUSED, Stage.STOPPED);
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        activity.getClass();
        instrumentation.runOnMainSync(new Runnable() { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                activity.recreate();
            }
        });
    }

    @Override // androidx.test.internal.platform.app.ActivityInvoker
    public void finishActivity(final Activity activity) {
        startEmptyActivitySync();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        activity.getClass();
        instrumentation.runOnMainSync(new Runnable() { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                activity.finish();
            }
        });
        if (this.activityResultWaiter != null) {
            ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_BOOTSTRAP_ACTIVITY));
            startEmptyActivitySync();
            Instrumentation instrumentation2 = InstrumentationRegistry.getInstrumentation();
            activity.getClass();
            instrumentation2.runOnMainSync(new Runnable() { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    activity.finish();
                }
            });
        }
        ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(FINISH_EMPTY_ACTIVITIES));
        if (this.activityResultWaiter != null) {
            ApplicationProvider.getApplicationContext().sendBroadcast(new Intent(CANCEL_ACTIVITY_RESULT_WAITER));
        }
    }

    private static void checkActivityStageIsIn(Activity activity, Stage... expected) {
        checkActivityStageIsIn(activity, new HashSet(Arrays.asList(expected)));
    }

    private static void checkActivityStageIsIn(final Activity activity, final Set<Stage> expected) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() { // from class: androidx.test.core.app.InstrumentationActivityInvoker$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                InstrumentationActivityInvoker.lambda$checkActivityStageIsIn$0(activity, expected);
            }
        });
    }

    static /* synthetic */ void lambda$checkActivityStageIsIn$0(Activity activity, Set set) {
        Stage lifecycleStageOf = ActivityLifecycleMonitorRegistry.getInstance().getLifecycleStageOf(activity);
        Checks.checkState(set.contains(lifecycleStageOf), "Activity's stage must be %s but was %s", set, lifecycleStageOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void registerBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (Build.VERSION.SDK_INT < 33) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, 2);
        }
    }
}
