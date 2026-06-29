package androidx.test.espresso;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.base.IdlingResourceRegistry;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListenableFutureTask;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.TracingUtil;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.platform.tracing.Tracer;
import androidx.test.platform.tracing.Tracing;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/* loaded from: classes5.dex */
public final class Espresso {
    private static final BaseLayerComponent BASE;
    private static final Matcher<View> OVERFLOW_BUTTON_MATCHER;
    private static final String TAG = "Espresso";
    private static final int TIMEOUT_SECONDS = 5;
    private static final IdlingResourceRegistry baseRegistry;
    private static final Tracing tracer;

    private static class TransitionBridgingViewAction implements ViewAction {
        private TransitionBridgingViewAction() {
        }

        @Override // androidx.test.espresso.ViewAction
        public Matcher<View> getConstraints() {
            return ViewMatchers.isRoot();
        }

        @Override // androidx.test.espresso.ViewAction
        public String getDescription() {
            return "Handle transition between action bar and action bar context.";
        }

        private boolean isTransitioningBetweenActionBars(View view) {
            Iterator<View> it = TreeIterables.breadthFirstViewTraversal(view).iterator();
            int i = 0;
            while (it.hasNext()) {
                if (Espresso.OVERFLOW_BUTTON_MATCHER.matches(it.next())) {
                    i++;
                }
            }
            return i > 1;
        }

        @Override // androidx.test.espresso.ViewAction
        public void perform(UiController uiController, View view) {
            int i = 0;
            while (isTransitioningBetweenActionBars(view) && i < 100) {
                i++;
                uiController.loopMainThreadForAtLeast(50L);
            }
        }
    }

    private Espresso() {
    }

    public static void closeSoftKeyboard() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.closeSoftKeyboard");
        try {
            onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public static List<IdlingResource> getIdlingResources() {
        return baseRegistry.getResources();
    }

    public static DataInteraction onData(Matcher<? extends Object> matcher) {
        return new DataInteraction(matcher);
    }

    public static void onIdle() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        onIdle(new Callable<Void>() { // from class: androidx.test.espresso.Espresso.2
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                return null;
            }
        });
    }

    public static ViewInteraction onView(Matcher<View> matcher) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan(TracingUtil.getSpanName("Espresso", "onView", matcher));
        try {
            ViewInteraction viewInteraction = BASE.plus(new ViewInteractionModule(matcher)).viewInteraction();
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
            return viewInteraction;
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public static void openActionBarOverflowOrOptionsMenu(Context context) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.openActionBarOverflowOrOptionsMenu");
        try {
            waitUntilNextFrame(2);
            if (context.getApplicationInfo().targetSdkVersion < 11) {
                onView(ViewMatchers.isRoot()).perform(ViewActions.pressMenuKey());
            } else if (hasVirtualOverflowButton(context)) {
                onView(ViewMatchers.isRoot()).perform(new TransitionBridgingViewAction());
                onView(OVERFLOW_BUTTON_MATCHER).perform(ViewActions.click());
            } else {
                onView(ViewMatchers.isRoot()).perform(ViewActions.pressMenuKey());
            }
            waitUntilNextFrame(2);
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public static void openContextualActionModeOverflowMenu() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.openContextualActionModeOverflowMenu");
        try {
            onView(ViewMatchers.isRoot()).perform(new TransitionBridgingViewAction());
            onView(OVERFLOW_BUTTON_MATCHER).perform(ViewActions.click(ViewActions.pressBack()));
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public static void pressBack() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.pressBack");
        try {
            onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    public static void pressBackUnconditionally() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.pressBackUnconditionally");
        try {
            onView(ViewMatchers.isRoot()).perform(ViewActions.pressBackUnconditionally());
            if (spanBeginSpan != null) {
                spanBeginSpan.close();
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public static boolean registerIdlingResources(IdlingResource... idlingResourceArr) {
        if (!IdlingRegistry.getInstance().register(idlingResourceArr)) {
            return idlingResourceArr.length == 0;
        }
        baseRegistry.sync(IdlingRegistry.getInstance().getResources(), IdlingRegistry.getInstance().getLoopers());
        return true;
    }

    @Deprecated
    public static void registerLooperAsIdlingResource(Looper looper) {
        registerLooperAsIdlingResource(looper, false);
    }

    public static void setFailureHandler(FailureHandler failureHandler) {
        BASE.failureHolder().update((FailureHandler) Preconditions.checkNotNull(failureHandler));
    }

    @Deprecated
    public static boolean unregisterIdlingResources(IdlingResource... idlingResourceArr) {
        if (!IdlingRegistry.getInstance().unregister(idlingResourceArr)) {
            return idlingResourceArr.length == 0;
        }
        baseRegistry.sync(IdlingRegistry.getInstance().getResources(), IdlingRegistry.getInstance().getLoopers());
        return true;
    }

    static {
        BaseLayerComponent baseLayerComponentBaseLayer = GraphHolder.baseLayer();
        BASE = baseLayerComponentBaseLayer;
        baseRegistry = baseLayerComponentBaseLayer.idlingResourceRegistry();
        tracer = baseLayerComponentBaseLayer.tracer();
        OVERFLOW_BUTTON_MATCHER = Matchers.anyOf(Matchers.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withContentDescription("More options")), Matchers.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withClassName(Matchers.endsWith("OverflowMenuButton"))));
    }

    private static boolean hasVirtualOverflowButton(Context context) {
        return !ViewConfiguration.get(context).hasPermanentMenuKey();
    }

    public static <T> T onIdle(Callable<T> callable) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Tracer.Span spanBeginSpan = tracer.beginSpan("Espresso.onIdle");
        try {
            Checks.checkNotMainThread();
            BaseLayerComponent baseLayerComponent = BASE;
            Executor executorMainThreadExecutor = baseLayerComponent.mainThreadExecutor();
            ListenableFutureTask listenableFutureTaskCreate = ListenableFutureTask.create(new Runnable() { // from class: androidx.test.espresso.Espresso.1
                @Override // java.lang.Runnable
                public void run() {
                    Espresso.BASE.uiController().loopMainThreadUntilIdle();
                }
            }, null);
            FutureTask futureTask = new FutureTask(callable);
            listenableFutureTaskCreate.addListener(futureTask, executorMainThreadExecutor);
            executorMainThreadExecutor.execute(listenableFutureTaskCreate);
            baseLayerComponent.controlledLooper().drainMainThreadUntilIdle();
            try {
                listenableFutureTaskCreate.get();
                T t = (T) futureTask.get();
                if (spanBeginSpan != null) {
                    spanBeginSpan.close();
                }
                return t;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                if (e2.getCause() instanceof AppNotIdleException) {
                    throw ((AppNotIdleException) e2.getCause());
                }
                throw new RuntimeException(e2);
            }
        } catch (Throwable th) {
            if (spanBeginSpan != null) {
                try {
                    spanBeginSpan.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public static void registerLooperAsIdlingResource(Looper looper, boolean z) {
        IdlingRegistry.getInstance().registerLooperAsIdlingResource(looper);
        baseRegistry.sync(IdlingRegistry.getInstance().getResources(), IdlingRegistry.getInstance().getLoopers());
    }

    private static void waitUntilNextFrame(int i) throws InterruptedException {
        for (int i2 = 0; i2 < i; i2++) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() { // from class: androidx.test.espresso.Espresso$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.test.espresso.Espresso$$ExternalSyntheticLambda2
                        @Override // android.view.Choreographer.FrameCallback
                        public final void doFrame(long j) {
                            countDownLatch.countDown();
                        }
                    });
                }
            });
            BASE.controlledLooper().drainMainThreadUntilIdle();
            try {
                countDownLatch.await(5L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                Log.w(TAG, "Waited for the next frame to start but never happened.");
                return;
            }
        }
    }
}
