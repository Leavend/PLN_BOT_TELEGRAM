package androidx.test.espresso.base;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingPolicy;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.base.IdlingResourceRegistry;
import androidx.test.espresso.base.Interrogator;
import androidx.test.espresso.core.internal.deps.guava.base.Joiner;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Throwables;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.inject.Provider;

/* loaded from: classes5.dex */
final class UiControllerImpl implements InterruptableUiController, Handler.Callback, IdlingUiController {
    private static final Callable<Void> NO_OP = new Callable<Void>() { // from class: androidx.test.espresso.base.UiControllerImpl.1
        @Override // java.util.concurrent.Callable
        public Void call() {
            return null;
        }
    };
    private static final String TAG = "UiControllerImpl";
    private IdleNotifier<Runnable> asyncIdle;
    private IdleNotifier<Runnable> compatIdle;
    private final Handler controllerHandler;
    private Provider<IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback>> dynamicIdleProvider;
    private final EventInjector eventInjector;
    private final IdlingResourceRegistry idlingResourceRegistry;
    private MainThreadInterrogation interrogation;
    private final Looper mainLooper;
    private final ExecutorService keyEventExecutor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("Espresso Key Event #%d").build());
    private int generation = 0;
    private final BitSet conditionSet = IdleCondition.createConditionSet();

    /* renamed from: androidx.test.espresso.base.UiControllerImpl$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$androidx$test$espresso$base$UiControllerImpl$IdleCondition;

        static {
            int[] iArr = new int[IdleCondition.values().length];
            $SwitchMap$androidx$test$espresso$base$UiControllerImpl$IdleCondition = iArr;
            try {
                iArr[IdleCondition.ASYNC_TASKS_HAVE_IDLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$test$espresso$base$UiControllerImpl$IdleCondition[IdleCondition.COMPAT_TASKS_HAVE_IDLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$test$espresso$base$UiControllerImpl$IdleCondition[IdleCondition.DYNAMIC_TASKS_HAVE_IDLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    enum IdleCondition {
        DELAY_HAS_PAST,
        ASYNC_TASKS_HAVE_IDLED,
        COMPAT_TASKS_HAVE_IDLED,
        KEY_INJECT_HAS_COMPLETED,
        MOTION_INJECTION_HAS_COMPLETED,
        DYNAMIC_TASKS_HAVE_IDLED;

        public static BitSet createConditionSet() {
            return new BitSet(values().length);
        }

        public static boolean handleMessage(Message message, BitSet bitSet, int i) {
            IdleCondition[] idleConditionArrValues = values();
            if (message.what < 0 || message.what >= idleConditionArrValues.length) {
                return false;
            }
            IdleCondition idleCondition = idleConditionArrValues[message.what];
            if (message.arg1 == i) {
                idleCondition.signal(bitSet);
                return true;
            }
            Log.w(UiControllerImpl.TAG, "ignoring signal of: " + String.valueOf(idleCondition) + " from previous generation: " + message.arg1 + " current generation: " + i);
            return true;
        }

        public Message createSignal(Handler handler, int i) {
            return Message.obtain(handler, ordinal(), i, 0, null);
        }

        public boolean isSignaled(BitSet bitSet) {
            return bitSet.get(ordinal());
        }

        public void reset(BitSet bitSet) {
            bitSet.set(ordinal(), false);
        }

        protected void signal(BitSet bitSet) {
            bitSet.set(ordinal());
        }
    }

    private enum InterrogationStatus {
        TIMED_OUT,
        COMPLETED,
        INTERRUPTED
    }

    private static final class MainThreadInterrogation implements Interrogator.InterrogationHandler<InterrogationStatus> {
        private final BitSet conditionSet;
        private final EnumSet<IdleCondition> conditions;
        private final long giveUpAtMs;
        private String lastMessage;
        private InterrogationStatus status = InterrogationStatus.COMPLETED;
        private int execCount = 0;

        MainThreadInterrogation(EnumSet<IdleCondition> enumSet, BitSet bitSet, long j) {
            this.conditions = enumSet;
            this.conditionSet = bitSet;
            this.giveUpAtMs = j;
        }

        private boolean conditionsMet() {
            boolean z = true;
            if (InterrogationStatus.INTERRUPTED == this.status) {
                return true;
            }
            int i = this.execCount;
            boolean z2 = i > 0 && i % 100 == 0;
            Iterator it = this.conditions.iterator();
            while (it.hasNext()) {
                IdleCondition idleCondition = (IdleCondition) it.next();
                if (!idleCondition.isSignaled(this.conditionSet)) {
                    if (!z2) {
                        return false;
                    }
                    Log.w(UiControllerImpl.TAG, "Waiting for: " + idleCondition.name() + " for " + this.execCount + " iterations.");
                    z = false;
                }
            }
            return z;
        }

        private boolean continueOrTimeout() {
            if (InterrogationStatus.INTERRUPTED == this.status) {
                return false;
            }
            if (SystemClock.uptimeMillis() < this.giveUpAtMs) {
                return true;
            }
            this.status = InterrogationStatus.TIMED_OUT;
            return false;
        }

        @Override // androidx.test.espresso.base.Interrogator.QueueInterrogationHandler
        public boolean barrierUp() {
            return continueOrTimeout();
        }

        @Override // androidx.test.espresso.base.Interrogator.InterrogationHandler
        public boolean beforeTaskDispatch() {
            this.execCount++;
            return continueOrTimeout();
        }

        @Override // androidx.test.espresso.base.Interrogator.QueueInterrogationHandler
        public InterrogationStatus get() {
            return this.status;
        }

        @Override // androidx.test.espresso.base.Interrogator.InterrogationHandler
        public String getMessage() {
            return this.lastMessage;
        }

        void interruptInterrogation() {
            this.status = InterrogationStatus.INTERRUPTED;
        }

        @Override // androidx.test.espresso.base.Interrogator.QueueInterrogationHandler
        public boolean queueEmpty() {
            return !conditionsMet();
        }

        @Override // androidx.test.espresso.base.Interrogator.InterrogationHandler
        public void quitting() {
        }

        @Override // androidx.test.espresso.base.Interrogator.InterrogationHandler
        public void setMessage(Message message) {
            try {
                this.lastMessage = message.toString();
            } catch (NullPointerException e) {
                this.lastMessage = "NPE calling message toString(): " + String.valueOf(e);
            }
        }

        @Override // androidx.test.espresso.base.Interrogator.QueueInterrogationHandler
        public boolean taskDueLong() {
            return !conditionsMet();
        }

        @Override // androidx.test.espresso.base.Interrogator.QueueInterrogationHandler
        public boolean taskDueSoon() {
            return continueOrTimeout();
        }
    }

    private class SignalingTask<T> extends FutureTask<T> {
        private final IdleCondition condition;
        private final int myGeneration;

        public SignalingTask(Callable<T> callable, IdleCondition idleCondition, int i) {
            super(callable);
            this.condition = (IdleCondition) Preconditions.checkNotNull(idleCondition);
            this.myGeneration = i;
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            UiControllerImpl.this.controllerHandler.sendMessage(this.condition.createSignal(UiControllerImpl.this.controllerHandler, this.myGeneration));
        }
    }

    UiControllerImpl(EventInjector eventInjector, @SdkAsyncTask IdleNotifier<Runnable> idleNotifier, @CompatAsyncTask IdleNotifier<Runnable> idleNotifier2, Provider<IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback>> provider, Looper looper, IdlingResourceRegistry idlingResourceRegistry) {
        this.eventInjector = (EventInjector) Preconditions.checkNotNull(eventInjector);
        this.asyncIdle = (IdleNotifier) Preconditions.checkNotNull(idleNotifier);
        this.compatIdle = (IdleNotifier) Preconditions.checkNotNull(idleNotifier2);
        this.dynamicIdleProvider = (Provider) Preconditions.checkNotNull(provider);
        this.mainLooper = (Looper) Preconditions.checkNotNull(looper);
        this.idlingResourceRegistry = (IdlingResourceRegistry) Preconditions.checkNotNull(idlingResourceRegistry);
        this.controllerHandler = new Handler(looper, this);
    }

    private void loopUntil(IdleCondition idleCondition, IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback> idleNotifier) {
        loopUntil(EnumSet.of(idleCondition), idleNotifier);
    }

    @Override // androidx.test.espresso.base.IdlingUiController
    public IdlingResourceRegistry getIdlingResourceRegistry() {
        return this.idlingResourceRegistry;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (IdleCondition.handleMessage(message, this.conditionSet, this.generation)) {
            return true;
        }
        Log.i(TAG, "Unknown message type: " + String.valueOf(message));
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.test.espresso.UiController
    public boolean injectKeyEvent(final KeyEvent keyEvent) throws InjectEventSecurityException {
        Preconditions.checkNotNull(keyEvent);
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        loopMainThreadUntilIdle();
        SignalingTask signalingTask = new SignalingTask(new Callable<Boolean>() { // from class: androidx.test.espresso.base.UiControllerImpl.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UiControllerImpl.this.eventInjector.injectKeyEvent(keyEvent));
            }
        }, IdleCondition.KEY_INJECT_HAS_COMPLETED, this.generation);
        this.keyEventExecutor.submit(signalingTask);
        loopUntil(IdleCondition.KEY_INJECT_HAS_COMPLETED, this.dynamicIdleProvider.get2());
        try {
            Preconditions.checkState(signalingTask.isDone(), "Key injection was signaled - but it wasnt done.");
            return ((Boolean) signalingTask.get()).booleanValue();
        } catch (InterruptedException e) {
            throw new RuntimeException("impossible.", e);
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof InjectEventSecurityException) {
                throw ((InjectEventSecurityException) e2.getCause());
            }
            throw new RuntimeException(e2.getCause());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.test.espresso.UiController
    public boolean injectMotionEvent(final MotionEvent motionEvent) throws InjectEventSecurityException {
        Preconditions.checkNotNull(motionEvent);
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        SignalingTask signalingTask = new SignalingTask(new Callable<Boolean>() { // from class: androidx.test.espresso.base.UiControllerImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UiControllerImpl.this.eventInjector.injectMotionEvent(motionEvent));
            }
        }, IdleCondition.MOTION_INJECTION_HAS_COMPLETED, this.generation);
        this.keyEventExecutor.submit(signalingTask);
        loopUntil(IdleCondition.MOTION_INJECTION_HAS_COMPLETED, this.dynamicIdleProvider.get2());
        try {
            try {
                Preconditions.checkState(signalingTask.isDone(), "Motion event injection was signaled - but it wasnt done.");
                return ((Boolean) signalingTask.get()).booleanValue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                if (e2.getCause() instanceof InjectEventSecurityException) {
                    throw ((InjectEventSecurityException) e2.getCause());
                }
                Throwables.throwIfUnchecked(e2.getCause() != null ? e2.getCause() : e2);
                Throwable cause = e2.getCause();
                Throwable cause2 = e2;
                if (cause != null) {
                    cause2 = e2.getCause();
                }
                throw new RuntimeException(cause2);
            }
        } finally {
            loopMainThreadUntilIdle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.test.espresso.UiController
    public boolean injectMotionEventSequence(Iterable<MotionEvent> iterable) throws InjectEventSecurityException {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkState(!Iterables.isEmpty(iterable), "Expecting non-empty events to inject");
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        final Iterator<MotionEvent> it = iterable.iterator();
        final long jUptimeMillis = SystemClock.uptimeMillis() - ((MotionEvent) Iterables.getFirst(iterable, null)).getEventTime();
        SignalingTask signalingTask = new SignalingTask(new Callable<Boolean>() { // from class: androidx.test.espresso.base.UiControllerImpl.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                boolean zInjectMotionEventAsync = true;
                while (it.hasNext()) {
                    MotionEvent motionEvent = (MotionEvent) it.next();
                    long eventTime = (motionEvent.getEventTime() + jUptimeMillis) - SystemClock.uptimeMillis();
                    if (eventTime > 10) {
                        SystemClock.sleep(eventTime);
                    }
                    zInjectMotionEventAsync &= it.hasNext() ? UiControllerImpl.this.eventInjector.injectMotionEventAsync(motionEvent) : UiControllerImpl.this.eventInjector.injectMotionEvent(motionEvent);
                }
                return Boolean.valueOf(zInjectMotionEventAsync);
            }
        }, IdleCondition.MOTION_INJECTION_HAS_COMPLETED, this.generation);
        this.keyEventExecutor.submit(signalingTask);
        loopUntil(IdleCondition.MOTION_INJECTION_HAS_COMPLETED, this.dynamicIdleProvider.get2());
        try {
            try {
                try {
                    Preconditions.checkState(signalingTask.isDone(), "MotionEvents injection was signaled - but it wasnt done.");
                    return ((Boolean) signalingTask.get()).booleanValue();
                } catch (ExecutionException e) {
                    if (e.getCause() instanceof InjectEventSecurityException) {
                        throw ((InjectEventSecurityException) e.getCause());
                    }
                    Throwables.throwIfUnchecked(e.getCause() != null ? e.getCause() : e);
                    Throwable cause = e.getCause();
                    Throwable cause2 = e;
                    if (cause != null) {
                        cause2 = e.getCause();
                    }
                    throw new RuntimeException(cause2);
                }
            } catch (InterruptedException e2) {
                throw new RuntimeException(e2);
            }
        } finally {
            loopMainThreadUntilIdle();
        }
    }

    @Override // androidx.test.espresso.UiController
    public boolean injectString(String str) throws InjectEventSecurityException {
        Preconditions.checkNotNull(str);
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        if (str.isEmpty()) {
            Log.w(TAG, "Supplied string is empty resulting in no-op (nothing is typed).");
            return true;
        }
        KeyEvent[] events = getKeyCharacterMap().getEvents(str.toCharArray());
        if (events == null) {
            throw new RuntimeException(String.format(Locale.ROOT, "Failed to get key events for string %s (i.e. current IME does not understand how to translate the string into key events). As a workaround, you can use replaceText action to set the text directly in the EditText field.", str));
        }
        Log.d(TAG, String.format(Locale.ROOT, "Injecting string: \"%s\"", str));
        int length = events.length;
        int i = 0;
        boolean zInjectKeyEvent = false;
        while (true) {
            if (i >= length) {
                break;
            }
            KeyEvent keyEvent = events[i];
            Preconditions.checkNotNull(keyEvent, String.format(Locale.ROOT, "Failed to get event for character (%c) with key code (%s)", Integer.valueOf(keyEvent.getKeyCode()), Integer.valueOf(keyEvent.getUnicodeChar())));
            KeyEvent keyEventChangeTimeRepeat = keyEvent;
            zInjectKeyEvent = false;
            for (int i2 = 0; !zInjectKeyEvent && i2 < 4; i2++) {
                keyEventChangeTimeRepeat = KeyEvent.changeTimeRepeat(keyEventChangeTimeRepeat, SystemClock.uptimeMillis(), 0);
                zInjectKeyEvent = injectKeyEvent(keyEventChangeTimeRepeat);
            }
            if (!zInjectKeyEvent) {
                Log.e(TAG, String.format(Locale.ROOT, "Failed to inject event for character (%c) with key code (%s)", Integer.valueOf(keyEventChangeTimeRepeat.getUnicodeChar()), Integer.valueOf(keyEventChangeTimeRepeat.getKeyCode())));
                break;
            }
            i++;
        }
        return zInjectKeyEvent;
    }

    @Override // androidx.test.espresso.base.InterruptableUiController
    public void interruptEspressoTasks() {
        this.controllerHandler.post(new Runnable() { // from class: androidx.test.espresso.base.UiControllerImpl.6
            @Override // java.lang.Runnable
            public void run() {
                if (UiControllerImpl.this.interrogation != null) {
                    UiControllerImpl.this.interrogation.interruptInterrogation();
                    UiControllerImpl.this.controllerHandler.removeCallbacksAndMessages(Integer.valueOf(UiControllerImpl.this.generation));
                }
            }
        });
    }

    @Override // androidx.test.espresso.UiController
    public void loopMainThreadForAtLeast(long j) {
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        Preconditions.checkState(!IdleCondition.DELAY_HAS_PAST.isSignaled(this.conditionSet), "recursion detected!");
        Preconditions.checkArgument(j > 0);
        this.controllerHandler.postAtTime(new SignalingTask(NO_OP, IdleCondition.DELAY_HAS_PAST, this.generation), Integer.valueOf(this.generation), SystemClock.uptimeMillis() + j);
        loopUntil(IdleCondition.DELAY_HAS_PAST, this.dynamicIdleProvider.get2());
        loopMainThreadUntilIdle();
    }

    @Override // androidx.test.espresso.UiController
    public void loopMainThreadUntilIdle() {
        Preconditions.checkState(Looper.myLooper() == this.mainLooper, "Expecting to be on main thread!");
        IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback> idleNotifierLoopUntil = this.dynamicIdleProvider.get2();
        while (true) {
            EnumSet<IdleCondition> enumSetNoneOf = EnumSet.noneOf(IdleCondition.class);
            if (!this.asyncIdle.isIdleNow()) {
                this.asyncIdle.registerNotificationCallback(new SignalingTask(NO_OP, IdleCondition.ASYNC_TASKS_HAVE_IDLED, this.generation));
                enumSetNoneOf.add(IdleCondition.ASYNC_TASKS_HAVE_IDLED);
            }
            if (!this.compatIdle.isIdleNow()) {
                this.compatIdle.registerNotificationCallback(new SignalingTask(NO_OP, IdleCondition.COMPAT_TASKS_HAVE_IDLED, this.generation));
                enumSetNoneOf.add(IdleCondition.COMPAT_TASKS_HAVE_IDLED);
            }
            if (!idleNotifierLoopUntil.isIdleNow()) {
                final IdlingPolicy dynamicIdlingResourceWarningPolicy = IdlingPolicies.getDynamicIdlingResourceWarningPolicy();
                final IdlingPolicy dynamicIdlingResourceErrorPolicy = IdlingPolicies.getDynamicIdlingResourceErrorPolicy();
                final SignalingTask signalingTask = new SignalingTask(NO_OP, IdleCondition.DYNAMIC_TASKS_HAVE_IDLED, this.generation);
                idleNotifierLoopUntil.registerNotificationCallback(new IdlingResourceRegistry.IdleNotificationCallback() { // from class: androidx.test.espresso.base.UiControllerImpl.5
                    @Override // androidx.test.espresso.base.IdlingResourceRegistry.IdleNotificationCallback
                    public void allResourcesIdle() {
                        UiControllerImpl.this.controllerHandler.post(signalingTask);
                    }

                    @Override // androidx.test.espresso.base.IdlingResourceRegistry.IdleNotificationCallback
                    public void resourcesHaveTimedOut(List<String> list) {
                        dynamicIdlingResourceErrorPolicy.handleTimeout(list, "IdlingResources have timed out!");
                        UiControllerImpl.this.controllerHandler.post(signalingTask);
                    }

                    @Override // androidx.test.espresso.base.IdlingResourceRegistry.IdleNotificationCallback
                    public void resourcesStillBusyWarning(List<String> list) {
                        dynamicIdlingResourceWarningPolicy.handleTimeout(list, "IdlingResources are still busy!");
                    }
                });
                enumSetNoneOf.add(IdleCondition.DYNAMIC_TASKS_HAVE_IDLED);
            }
            try {
                idleNotifierLoopUntil = loopUntil(enumSetNoneOf, idleNotifierLoopUntil);
                this.asyncIdle.cancelCallback();
                this.compatIdle.cancelCallback();
                idleNotifierLoopUntil.cancelCallback();
                if (this.asyncIdle.isIdleNow() && this.compatIdle.isIdleNow() && idleNotifierLoopUntil.isIdleNow()) {
                    return;
                }
            } catch (Throwable th) {
                this.asyncIdle.cancelCallback();
                this.compatIdle.cancelCallback();
                idleNotifierLoopUntil.cancelCallback();
                throw th;
            }
        }
    }

    public static KeyCharacterMap getKeyCharacterMap() {
        return KeyCharacterMap.load(-1);
    }

    private IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback> loopUntil(EnumSet<IdleCondition> enumSet, IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback> idleNotifier) {
        IdlingPolicy masterIdlingPolicy = IdlingPolicies.getMasterIdlingPolicy();
        IdlingPolicy dynamicIdlingResourceErrorPolicy = IdlingPolicies.getDynamicIdlingResourceErrorPolicy();
        try {
            MainThreadInterrogation mainThreadInterrogation = new MainThreadInterrogation(enumSet, this.conditionSet, SystemClock.uptimeMillis() + masterIdlingPolicy.getIdleTimeoutUnit().toMillis(masterIdlingPolicy.getIdleTimeout()));
            this.interrogation = mainThreadInterrogation;
            InterrogationStatus interrogationStatus = (InterrogationStatus) Interrogator.loopAndInterrogate(mainThreadInterrogation);
            if (InterrogationStatus.COMPLETED == interrogationStatus) {
                return idleNotifier;
            }
            if (InterrogationStatus.INTERRUPTED == interrogationStatus) {
                Log.w(TAG, "Espresso interrogation of the main thread is interrupted");
                throw new RuntimeException("Espresso interrogation of the main thread is interrupted");
            }
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            Iterator it = enumSet.iterator();
            while (it.hasNext()) {
                IdleCondition idleCondition = (IdleCondition) it.next();
                if (!idleCondition.isSignaled(this.conditionSet)) {
                    String strName = idleCondition.name();
                    int i = AnonymousClass7.$SwitchMap$androidx$test$espresso$base$UiControllerImpl$IdleCondition[idleCondition.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                if (dynamicIdlingResourceErrorPolicy.getDisableOnTimeout() || (!masterIdlingPolicy.getTimeoutIfDebuggerAttached() && Debug.isDebuggerConnected())) {
                                    idleNotifier.cancelCallback();
                                    NoopIdleNotificationCallbackIdleNotifierProvider noopIdleNotificationCallbackIdleNotifierProvider = new NoopIdleNotificationCallbackIdleNotifierProvider();
                                    this.dynamicIdleProvider = noopIdleNotificationCallbackIdleNotifierProvider;
                                    idleNotifier = noopIdleNotificationCallbackIdleNotifierProvider.get2();
                                }
                                strName = String.format(Locale.ROOT, "%s(busy resources=%s)", strName, Joiner.on(",").join(this.idlingResourceRegistry.getBusyResources()));
                            }
                        } else if (masterIdlingPolicy.getDisableOnTimeout() || (!masterIdlingPolicy.getTimeoutIfDebuggerAttached() && Debug.isDebuggerConnected())) {
                            this.compatIdle.cancelCallback();
                            this.compatIdle = new NoopRunnableIdleNotifier();
                        }
                    } else if (masterIdlingPolicy.getDisableOnTimeout() || (!masterIdlingPolicy.getTimeoutIfDebuggerAttached() && Debug.isDebuggerConnected())) {
                        this.asyncIdle.cancelCallback();
                        this.asyncIdle = new NoopRunnableIdleNotifier();
                    }
                    arrayListNewArrayList.add(strName);
                }
            }
            if (arrayListNewArrayList.isEmpty()) {
                arrayListNewArrayList.add("MAIN_LOOPER_HAS_IDLED(last message: " + this.interrogation.getMessage() + ")");
            }
            masterIdlingPolicy.handleTimeout(arrayListNewArrayList, String.format(Locale.ROOT, "Looped for %s iterations over %s %s.", Integer.valueOf(this.interrogation.execCount), Long.valueOf(masterIdlingPolicy.getIdleTimeout()), masterIdlingPolicy.getIdleTimeoutUnit().name()));
            this.generation++;
            Iterator it2 = enumSet.iterator();
            while (it2.hasNext()) {
                ((IdleCondition) it2.next()).reset(this.conditionSet);
            }
            this.interrogation = null;
            return idleNotifier;
        } finally {
            this.generation++;
            Iterator it3 = enumSet.iterator();
            while (it3.hasNext()) {
                ((IdleCondition) it3.next()).reset(this.conditionSet);
            }
            this.interrogation = null;
        }
    }
}
