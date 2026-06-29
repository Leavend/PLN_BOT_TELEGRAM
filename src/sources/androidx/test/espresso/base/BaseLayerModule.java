package androidx.test.espresso.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.base.IdlingResourceRegistry;
import androidx.test.espresso.core.internal.deps.guava.base.Optional;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListeningExecutorService;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.MoreExecutors;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ThreadFactoryBuilder;
import androidx.test.espresso.internal.inject.TargetContext;
import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.tracing.Tracing;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class BaseLayerModule {

    public static class FailureHandlerHolder {
        private final AtomicReference<FailureHandler> holder;

        public FailureHandlerHolder(@Default FailureHandler failureHandler) {
            this.holder = new AtomicReference<>(failureHandler);
        }

        public FailureHandler get() {
            return this.holder.get();
        }

        public void update(FailureHandler failureHandler) {
            this.holder.set(failureHandler);
        }
    }

    public ActiveRootLister provideActiveRootLister(RootsOracle rootsOracle) {
        return rootsOracle;
    }

    @CompatAsyncTask
    public IdleNotifier<Runnable> provideCompatAsyncTaskMonitor(ThreadPoolExecutorExtractor threadPoolExecutorExtractor) {
        Optional<ThreadPoolExecutor> compatAsyncTaskThreadPool = threadPoolExecutorExtractor.getCompatAsyncTaskThreadPool();
        return compatAsyncTaskThreadPool.isPresent() ? new AsyncTaskPoolMonitor((ThreadPoolExecutor) compatAsyncTaskThreadPool.get()).asIdleNotifier() : new NoopRunnableIdleNotifier();
    }

    public ControlledLooper provideControlledLooper() {
        return (ControlledLooper) ServiceLoaderWrapper.loadSingleService(ControlledLooper.class, new ServiceLoaderWrapper.Factory() { // from class: androidx.test.espresso.base.BaseLayerModule$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
            public final Object create() {
                return ControlledLooper.NO_OP_CONTROLLED_LOOPER;
            }
        });
    }

    DefaultFailureHandler provideDefaultFailureHander(@TargetContext Context context, PlatformTestStorage platformTestStorage) {
        return new DefaultFailureHandler(context, platformTestStorage);
    }

    @Default
    FailureHandler provideFailureHander(DefaultFailureHandler defaultFailureHandler) {
        return defaultFailureHandler;
    }

    FailureHandler provideFailureHandler(FailureHandlerHolder failureHandlerHolder) {
        return failureHandlerHolder.get();
    }

    public ActivityLifecycleMonitor provideLifecycleMonitor() {
        return ActivityLifecycleMonitorRegistry.getInstance();
    }

    public Looper provideMainLooper() {
        return Looper.getMainLooper();
    }

    @MainThread
    public Executor provideMainThreadExecutor(Looper looper) {
        final Handler handler = new Handler(looper);
        return new Executor(this) { // from class: androidx.test.espresso.base.BaseLayerModule.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    public ListeningExecutorService provideRemoteExecutor() {
        return MoreExecutors.listeningDecorator(new ThreadPoolExecutor(0, 5, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryBuilder().setNameFormat("Espresso Remote #%d").build()));
    }

    @SdkAsyncTask
    public IdleNotifier<Runnable> provideSdkAsyncTaskMonitor(ThreadPoolExecutorExtractor threadPoolExecutorExtractor) {
        return new AsyncTaskPoolMonitor(threadPoolExecutorExtractor.getAsyncTaskThreadPool()).asIdleNotifier();
    }

    @TargetContext
    public Context provideTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    Tracing providesTracing() {
        return Tracing.getInstance();
    }

    public IdleNotifier<IdlingResourceRegistry.IdleNotificationCallback> provideDynamicNotifer(IdlingResourceRegistry idlingResourceRegistry) {
        idlingResourceRegistry.sync(IdlingRegistry.getInstance().getResources(), IdlingRegistry.getInstance().getLoopers());
        return idlingResourceRegistry.asIdleNotifier();
    }

    public EventInjector provideEventInjector() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        InputManagerEventInjectionStrategy inputManagerEventInjectionStrategy = new InputManagerEventInjectionStrategy();
        inputManagerEventInjectionStrategy.initialize();
        return new EventInjector(inputManagerEventInjectionStrategy);
    }
}
