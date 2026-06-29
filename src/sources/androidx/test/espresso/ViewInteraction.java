package androidx.test.espresso;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.action.ScrollToAction;
import androidx.test.espresso.base.InterruptableUiController;
import androidx.test.espresso.base.MainThread;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.ImmutableMap;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListenableFutureTask;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListeningExecutorService;
import androidx.test.espresso.internal.data.TestFlowVisualizer;
import androidx.test.espresso.internal.data.model.ActionData;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.remote.Bindable;
import androidx.test.espresso.remote.IInteractionExecutionStatus;
import androidx.test.espresso.remote.RemoteInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TracingUtil;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.internal.util.Checks;
import androidx.test.internal.util.LogUtil;
import androidx.test.platform.tracing.Tracer;
import androidx.test.platform.tracing.Tracing;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;

/* loaded from: classes5.dex */
public final class ViewInteraction {
    private static final String TAG = "ViewInteraction";
    private final ControlledLooper controlledLooper;
    private volatile FailureHandler failureHandler;
    private boolean hasRootMatcher = false;
    private final Executor mainThreadExecutor;
    private final AtomicReference<Boolean> needsActivity;
    private final ListeningExecutorService remoteExecutor;
    private final RemoteInteraction remoteInteraction;
    private final AtomicReference<Matcher<Root>> rootMatcherRef;
    private final TestFlowVisualizer testFlowVisualizer;
    private final Tracing tracer;
    private final InterruptableUiController uiController;
    private final ViewFinder viewFinder;
    private final Matcher<View> viewMatcher;

    private static final class SingleExecutionViewAction implements ViewAction, Bindable {
        private IInteractionExecutionStatus actionExecutionStatus;
        final ViewAction viewAction;
        final Matcher<View> viewMatcher;

        private SingleExecutionViewAction(ViewAction viewAction, Matcher<View> matcher) {
            this.actionExecutionStatus = new IInteractionExecutionStatus.Stub(this) { // from class: androidx.test.espresso.ViewInteraction.SingleExecutionViewAction.1
                AtomicBoolean run = new AtomicBoolean(true);

                @Override // androidx.test.espresso.remote.IInteractionExecutionStatus
                public boolean canExecute() throws RemoteException {
                    return this.run.getAndSet(false);
                }
            };
            this.viewAction = viewAction;
            this.viewMatcher = matcher;
        }

        @Override // androidx.test.espresso.ViewAction
        public Matcher<View> getConstraints() {
            return this.viewAction.getConstraints();
        }

        @Override // androidx.test.espresso.ViewAction
        public String getDescription() {
            return this.viewAction.getDescription();
        }

        @Override // androidx.test.espresso.remote.Bindable
        public IBinder getIBinder() {
            return this.actionExecutionStatus.asBinder();
        }

        @Override // androidx.test.espresso.remote.Bindable
        public String getId() {
            return RemoteInteraction.BUNDLE_EXECUTION_STATUS;
        }

        ViewAction getInnerViewAction() {
            return this.viewAction;
        }

        @Override // androidx.test.espresso.ViewAction
        public void perform(UiController uiController, View view) {
            try {
                if (this.actionExecutionStatus.canExecute()) {
                    this.viewAction.perform(uiController, view);
                    return;
                }
                LogUtil.logDebugWithProcess(ViewInteraction.TAG, "Attempted to execute a Single Execution Action more then once: " + String.valueOf(this.viewAction), new Object[0]);
            } catch (RemoteException e) {
                throw new PerformException.Builder().withActionDescription(this.viewAction.getDescription()).withViewDescription(this.viewMatcher.toString()).withCause(new RuntimeException("Unable to query interaction execution status", e.getCause())).build();
            }
        }

        @Override // androidx.test.espresso.remote.Bindable
        public void setIBinder(IBinder iBinder) {
            this.actionExecutionStatus = IInteractionExecutionStatus.Stub.asInterface(iBinder);
        }
    }

    private static final class SingleExecutionViewAssertion implements ViewAssertion, Bindable {
        private IInteractionExecutionStatus assertionExecutionStatus;
        final ViewAssertion viewAssertion;

        private SingleExecutionViewAssertion(ViewAssertion viewAssertion) {
            this.assertionExecutionStatus = new IInteractionExecutionStatus.Stub(this) { // from class: androidx.test.espresso.ViewInteraction.SingleExecutionViewAssertion.1
                AtomicBoolean run = new AtomicBoolean(true);

                @Override // androidx.test.espresso.remote.IInteractionExecutionStatus
                public boolean canExecute() throws RemoteException {
                    return this.run.getAndSet(false);
                }
            };
            this.viewAssertion = viewAssertion;
        }

        @Override // androidx.test.espresso.ViewAssertion
        public void check(View view, NoMatchingViewException noMatchingViewException) {
            try {
                if (this.assertionExecutionStatus.canExecute()) {
                    this.viewAssertion.check(view, noMatchingViewException);
                    return;
                }
                LogUtil.logDebugWithProcess(ViewInteraction.TAG, "Attempted to execute a Single Execution Assertion more then once: " + String.valueOf(this.viewAssertion), new Object[0]);
            } catch (RemoteException e) {
                throw new RuntimeException("Unable to query interaction execution status", e.getCause());
            }
        }

        @Override // androidx.test.espresso.remote.Bindable
        public IBinder getIBinder() {
            return this.assertionExecutionStatus.asBinder();
        }

        @Override // androidx.test.espresso.remote.Bindable
        public String getId() {
            return RemoteInteraction.BUNDLE_EXECUTION_STATUS;
        }

        @Override // androidx.test.espresso.remote.Bindable
        public void setIBinder(IBinder iBinder) {
            this.assertionExecutionStatus = IInteractionExecutionStatus.Stub.asInterface(iBinder);
        }
    }

    ViewInteraction(UiController uiController, ViewFinder viewFinder, @MainThread Executor executor, FailureHandler failureHandler, Matcher<View> matcher, AtomicReference<Matcher<Root>> atomicReference, AtomicReference<Boolean> atomicReference2, RemoteInteraction remoteInteraction, ListeningExecutorService listeningExecutorService, ControlledLooper controlledLooper, TestFlowVisualizer testFlowVisualizer, Tracing tracing) {
        this.viewFinder = (ViewFinder) Preconditions.checkNotNull(viewFinder);
        this.uiController = (InterruptableUiController) Preconditions.checkNotNull(uiController);
        this.failureHandler = (FailureHandler) Preconditions.checkNotNull(failureHandler);
        this.mainThreadExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.viewMatcher = (Matcher) Preconditions.checkNotNull(matcher);
        this.rootMatcherRef = (AtomicReference) Preconditions.checkNotNull(atomicReference);
        this.needsActivity = (AtomicReference) Preconditions.checkNotNull(atomicReference2);
        this.remoteInteraction = (RemoteInteraction) Preconditions.checkNotNull(remoteInteraction);
        this.remoteExecutor = (ListeningExecutorService) Preconditions.checkNotNull(listeningExecutorService);
        this.controlledLooper = (ControlledLooper) Preconditions.checkNotNull(controlledLooper);
        this.testFlowVisualizer = (TestFlowVisualizer) Preconditions.checkNotNull(testFlowVisualizer);
        this.tracer = tracing;
    }

    private void desugaredPerform(final SingleExecutionViewAction singleExecutionViewAction, final int i, final boolean z) {
        final ViewAction innerViewAction = singleExecutionViewAction.getInnerViewAction();
        Callable<Void> callable = new Callable<Void>() { // from class: androidx.test.espresso.ViewInteraction.1
            @Override // java.util.concurrent.Callable
            public Void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                ViewAction viewAction = innerViewAction;
                Tracer.Span spanBeginSpan = ViewInteraction.this.tracer.beginSpan(TracingUtil.getSpanName("Espresso", "perform", TracingUtil.getClassName(viewAction, viewAction.getDescription()), ViewInteraction.this.viewMatcher));
                try {
                    ViewInteraction.this.doPerform(singleExecutionViewAction, i, z);
                    if (spanBeginSpan == null) {
                        return null;
                    }
                    spanBeginSpan.close();
                    return null;
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
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(postAsynchronouslyOnUiThread(callable));
        if (!this.remoteInteraction.isRemoteProcess()) {
            arrayList.add(this.remoteExecutor.submit(this.remoteInteraction.createRemotePerformCallable(this.rootMatcherRef.get(), this.viewMatcher, getIBindersFromViewActions(singleExecutionViewAction, innerViewAction), innerViewAction)));
        }
        waitForAndHandleInteractionResults(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPerform(SingleExecutionViewAction singleExecutionViewAction, int i, boolean z) throws NoMatchingViewException, AmbiguousViewMatcherException {
        Preconditions.checkNotNull(singleExecutionViewAction);
        Matcher matcher = (Matcher) Preconditions.checkNotNull(singleExecutionViewAction.getConstraints());
        this.uiController.loopMainThreadUntilIdle();
        View view = this.viewFinder.getView();
        Log.i(TAG, String.format(Locale.ROOT, "Performing '%s' action on view %s", singleExecutionViewAction.getDescription(), this.viewMatcher));
        if (!matcher.matches(view)) {
            StringDescription stringDescription = new StringDescription(new StringBuilder("Action will not be performed because the target view does not match one or more of the following constraints:\n"));
            matcher.describeTo(stringDescription);
            stringDescription.appendText("\nTarget view: ").appendValue(HumanReadables.describe(view));
            if ((singleExecutionViewAction.getInnerViewAction() instanceof ScrollToAction) && ViewMatchers.isDescendantOfA(ViewMatchers.isAssignableFrom(AdapterView.class)).matches(view)) {
                stringDescription.appendText("\nFurther Info: ScrollToAction on a view inside an AdapterView will not work. Use Espresso.onData to load the view.");
            }
            throw new PerformException.Builder().withActionDescription(singleExecutionViewAction.getDescription()).withViewDescription(this.viewMatcher.toString()).withCause(new RuntimeException(stringDescription.toString())).build();
        }
        ActionData actionData = new ActionData(i, singleExecutionViewAction.viewAction);
        if (z) {
            this.testFlowVisualizer.beforeActionRecordData(actionData, view);
        }
        singleExecutionViewAction.perform(this.uiController, view);
        if (z) {
            this.testFlowVisualizer.afterActionRecordData(actionData);
        }
    }

    private static List<Bindable> getBindables(Object... objArr) {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(objArr.length);
        for (Object obj : objArr) {
            if (obj instanceof Bindable) {
                arrayListNewArrayListWithCapacity.add((Bindable) obj);
            }
        }
        return arrayListNewArrayListWithCapacity;
    }

    private static Map<String, IBinder> getIBindersFromBindables(List<Bindable> list) {
        HashMap map = new HashMap();
        for (Bindable bindable : list) {
            map.put((String) Preconditions.checkNotNull(bindable.getId(), "Bindable id cannot be null!"), (IBinder) Preconditions.checkNotNull(bindable.getIBinder(), "Bindable binder cannot be null!"));
        }
        return ImmutableMap.copyOf((Map) map);
    }

    private static Map<String, IBinder> getIBindersFromViewActions(ViewAction... viewActionArr) {
        return getIBindersFromBindables(getBindables(viewActionArr));
    }

    private static Map<String, IBinder> getIBindersFromViewAssertions(ViewAssertion... viewAssertionArr) {
        return getIBindersFromBindables(getBindables(viewAssertionArr));
    }

    private ListenableFuture<Void> postAsynchronouslyOnUiThread(Callable<Void> callable) {
        Checks.checkNotMainThread();
        ListenableFutureTask listenableFutureTaskCreate = ListenableFutureTask.create(callable);
        this.mainThreadExecutor.execute(listenableFutureTaskCreate);
        return listenableFutureTaskCreate;
    }

    private void waitForAndHandleInteractionResults(List<ListenableFuture<Void>> list) {
        try {
            try {
                this.controlledLooper.drainMainThreadUntilIdle();
                InteractionResultsHandler.gatherAnyResult(list);
            } catch (Error e) {
                this.failureHandler.handle(e, this.viewMatcher);
            } catch (RuntimeException e2) {
                this.failureHandler.handle(e2, this.viewMatcher);
            }
        } finally {
            this.uiController.interruptEspressoTasks();
        }
    }

    public ViewInteraction check(final ViewAssertion viewAssertion) {
        Preconditions.checkNotNull(viewAssertion);
        final SingleExecutionViewAssertion singleExecutionViewAssertion = new SingleExecutionViewAssertion(viewAssertion);
        Callable<Void> callable = new Callable<Void>() { // from class: androidx.test.espresso.ViewInteraction.2
            @Override // java.util.concurrent.Callable
            public Void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                NoMatchingViewException noMatchingViewException;
                View view;
                Tracer.Span spanBeginSpan = ViewInteraction.this.tracer.beginSpan(TracingUtil.getSpanName("Espresso", "check", TracingUtil.getClassName(viewAssertion, "ViewAssertion"), ViewInteraction.this.viewMatcher));
                try {
                    ViewInteraction.this.uiController.loopMainThreadUntilIdle();
                    try {
                        view = ViewInteraction.this.viewFinder.getView();
                        noMatchingViewException = null;
                    } catch (NoMatchingViewException e) {
                        noMatchingViewException = e;
                        view = null;
                    }
                    Log.i(ViewInteraction.TAG, String.format(Locale.ROOT, "Checking '%s' assertion on view %s", viewAssertion, ViewInteraction.this.viewMatcher));
                    singleExecutionViewAssertion.check(view, noMatchingViewException);
                    if (spanBeginSpan != null) {
                        spanBeginSpan.close();
                    }
                    return null;
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
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(postAsynchronouslyOnUiThread(callable));
        if (!this.remoteInteraction.isRemoteProcess()) {
            arrayList.add(this.remoteExecutor.submit(this.remoteInteraction.createRemoteCheckCallable(this.rootMatcherRef.get(), this.viewMatcher, getIBindersFromViewAssertions(singleExecutionViewAssertion, viewAssertion), viewAssertion)));
        }
        waitForAndHandleInteractionResults(arrayList);
        return this;
    }

    public ViewInteraction inRoot(Matcher<Root> matcher) {
        this.hasRootMatcher = true;
        this.rootMatcherRef.set((Matcher) Preconditions.checkNotNull(matcher));
        return this;
    }

    public ViewInteraction noActivity() {
        if (!this.hasRootMatcher) {
            this.rootMatcherRef.set(Matchers.anyOf(RootMatchers.DEFAULT, Matchers.allOf(RootMatchers.hasWindowLayoutParams(), RootMatchers.isSystemAlertWindow())));
        }
        this.needsActivity.set(false);
        return this;
    }

    public ViewInteraction perform(ViewAction... viewActionArr) {
        Preconditions.checkNotNull(viewActionArr);
        for (ViewAction viewAction : viewActionArr) {
            int lastActionIndexAndIncrement = this.testFlowVisualizer.getLastActionIndexAndIncrement();
            boolean zIsEnabled = this.testFlowVisualizer.isEnabled();
            if (zIsEnabled) {
                this.testFlowVisualizer.beforeActionGenerateTestArtifact(lastActionIndexAndIncrement);
            }
            desugaredPerform(new SingleExecutionViewAction(viewAction, this.viewMatcher), lastActionIndexAndIncrement, zIsEnabled);
            if (zIsEnabled) {
                this.testFlowVisualizer.afterActionGenerateTestArtifact(lastActionIndexAndIncrement);
            }
        }
        return this;
    }

    public ViewInteraction withFailureHandler(FailureHandler failureHandler) {
        this.failureHandler = (FailureHandler) Preconditions.checkNotNull(failureHandler);
        return this;
    }
}
