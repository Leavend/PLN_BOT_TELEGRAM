package androidx.test.espresso;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import androidx.test.espresso.base.ActiveRootLister;
import androidx.test.espresso.base.BaseLayerModule;
import androidx.test.espresso.base.BaseLayerModule_FailureHandlerHolder_Factory;
import androidx.test.espresso.base.BaseLayerModule_ProvideActiveRootListerFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideCompatAsyncTaskMonitorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideControlledLooperFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideDefaultFailureHanderFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideDynamicNotiferFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideFailureHanderFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideFailureHandlerFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideLifecycleMonitorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideMainLooperFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideMainThreadExecutorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideRemoteExecutorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideSdkAsyncTaskMonitorFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvideTargetContextFactory;
import androidx.test.espresso.base.BaseLayerModule_ProvidesTracingFactory;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.base.IdlingResourceRegistry;
import androidx.test.espresso.base.IdlingResourceRegistry_Factory;
import androidx.test.espresso.base.PlatformTestStorageModule;
import androidx.test.espresso.base.PlatformTestStorageModule_ProvideTestStorageFactory;
import androidx.test.espresso.base.RootViewPicker;
import androidx.test.espresso.base.RootViewPicker_Factory;
import androidx.test.espresso.base.RootViewPicker_RootResultFetcher_Factory;
import androidx.test.espresso.base.RootsOracle_Factory;
import androidx.test.espresso.base.ThreadPoolExecutorExtractor_Factory;
import androidx.test.espresso.base.UiControllerImpl_Factory;
import androidx.test.espresso.base.UiControllerModule;
import androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory;
import androidx.test.espresso.base.ViewFinderImpl;
import androidx.test.espresso.base.ViewFinderImpl_Factory;
import androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck;
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.ListeningExecutorService;
import androidx.test.espresso.internal.data.TestFlowVisualizer;
import androidx.test.internal.platform.os.ControlledLooper;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.tracing.Tracing;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class DaggerBaseLayerComponent {

    private static final class BaseLayerComponentImpl implements BaseLayerComponent {
        private final BaseLayerComponentImpl baseLayerComponentImpl;
        private final BaseLayerModule baseLayerModule;
        private Provider<BaseLayerModule.FailureHandlerHolder> failureHandlerHolderProvider;
        private Provider<IdlingResourceRegistry> idlingResourceRegistryProvider;
        private final PlatformTestStorageModule platformTestStorageModule;
        private Provider<ActiveRootLister> provideActiveRootListerProvider;
        private Provider provideCompatAsyncTaskMonitorProvider;
        private Provider<ControlledLooper> provideControlledLooperProvider;
        private Provider<DefaultFailureHandler> provideDefaultFailureHanderProvider;
        private Provider provideDynamicNotiferProvider;
        private Provider provideEventInjectorProvider;
        private Provider<FailureHandler> provideFailureHanderProvider;
        private Provider<ActivityLifecycleMonitor> provideLifecycleMonitorProvider;
        private Provider<Looper> provideMainLooperProvider;
        private Provider<Executor> provideMainThreadExecutorProvider;
        private Provider<ListeningExecutorService> provideRemoteExecutorProvider;
        private Provider provideSdkAsyncTaskMonitorProvider;
        private Provider<Context> provideTargetContextProvider;
        private Provider<PlatformTestStorage> provideTestStorageProvider;
        private Provider<UiController> provideUiControllerProvider;
        private Provider<Tracing> providesTracingProvider;
        private Provider rootsOracleProvider;
        private Provider threadPoolExecutorExtractorProvider;
        private Provider uiControllerImplProvider;

        private BaseLayerComponentImpl(BaseLayerModule baseLayerModule, PlatformTestStorageModule platformTestStorageModule, UiControllerModule uiControllerModule) {
            this.baseLayerComponentImpl = this;
            this.baseLayerModule = baseLayerModule;
            this.platformTestStorageModule = platformTestStorageModule;
            initialize(baseLayerModule, platformTestStorageModule, uiControllerModule);
        }

        private void initialize(BaseLayerModule baseLayerModule, PlatformTestStorageModule platformTestStorageModule, UiControllerModule uiControllerModule) {
            this.provideTargetContextProvider = BaseLayerModule_ProvideTargetContextFactory.create(baseLayerModule);
            PlatformTestStorageModule_ProvideTestStorageFactory platformTestStorageModule_ProvideTestStorageFactoryCreate = PlatformTestStorageModule_ProvideTestStorageFactory.create(platformTestStorageModule);
            this.provideTestStorageProvider = platformTestStorageModule_ProvideTestStorageFactoryCreate;
            BaseLayerModule_ProvideDefaultFailureHanderFactory baseLayerModule_ProvideDefaultFailureHanderFactoryCreate = BaseLayerModule_ProvideDefaultFailureHanderFactory.create(baseLayerModule, this.provideTargetContextProvider, platformTestStorageModule_ProvideTestStorageFactoryCreate);
            this.provideDefaultFailureHanderProvider = baseLayerModule_ProvideDefaultFailureHanderFactoryCreate;
            BaseLayerModule_ProvideFailureHanderFactory baseLayerModule_ProvideFailureHanderFactoryCreate = BaseLayerModule_ProvideFailureHanderFactory.create(baseLayerModule, baseLayerModule_ProvideDefaultFailureHanderFactoryCreate);
            this.provideFailureHanderProvider = baseLayerModule_ProvideFailureHanderFactoryCreate;
            this.failureHandlerHolderProvider = DoubleCheck.provider(BaseLayerModule_FailureHandlerHolder_Factory.create(baseLayerModule_ProvideFailureHanderFactoryCreate));
            this.provideMainLooperProvider = DoubleCheck.provider(BaseLayerModule_ProvideMainLooperFactory.create(baseLayerModule));
            Provider<Tracing> provider = DoubleCheck.provider(BaseLayerModule_ProvidesTracingFactory.create(baseLayerModule));
            this.providesTracingProvider = provider;
            this.idlingResourceRegistryProvider = DoubleCheck.provider(IdlingResourceRegistry_Factory.create(this.provideMainLooperProvider, provider));
            this.provideEventInjectorProvider = DoubleCheck.provider(BaseLayerModule_ProvideEventInjectorFactory.create(baseLayerModule));
            Provider provider2 = DoubleCheck.provider(ThreadPoolExecutorExtractor_Factory.create(this.provideMainLooperProvider));
            this.threadPoolExecutorExtractorProvider = provider2;
            this.provideSdkAsyncTaskMonitorProvider = DoubleCheck.provider(BaseLayerModule_ProvideSdkAsyncTaskMonitorFactory.create(baseLayerModule, provider2));
            this.provideCompatAsyncTaskMonitorProvider = DoubleCheck.provider(BaseLayerModule_ProvideCompatAsyncTaskMonitorFactory.create(baseLayerModule, this.threadPoolExecutorExtractorProvider));
            BaseLayerModule_ProvideDynamicNotiferFactory baseLayerModule_ProvideDynamicNotiferFactoryCreate = BaseLayerModule_ProvideDynamicNotiferFactory.create(baseLayerModule, this.idlingResourceRegistryProvider);
            this.provideDynamicNotiferProvider = baseLayerModule_ProvideDynamicNotiferFactoryCreate;
            Provider provider3 = DoubleCheck.provider(UiControllerImpl_Factory.create(this.provideEventInjectorProvider, this.provideSdkAsyncTaskMonitorProvider, this.provideCompatAsyncTaskMonitorProvider, baseLayerModule_ProvideDynamicNotiferFactoryCreate, this.provideMainLooperProvider, this.idlingResourceRegistryProvider));
            this.uiControllerImplProvider = provider3;
            this.provideUiControllerProvider = DoubleCheck.provider(UiControllerModule_ProvideUiControllerFactory.create(uiControllerModule, provider3));
            this.provideMainThreadExecutorProvider = DoubleCheck.provider(BaseLayerModule_ProvideMainThreadExecutorFactory.create(baseLayerModule, this.provideMainLooperProvider));
            this.provideControlledLooperProvider = DoubleCheck.provider(BaseLayerModule_ProvideControlledLooperFactory.create(baseLayerModule));
            RootsOracle_Factory rootsOracle_FactoryCreate = RootsOracle_Factory.create(this.provideMainLooperProvider);
            this.rootsOracleProvider = rootsOracle_FactoryCreate;
            this.provideActiveRootListerProvider = BaseLayerModule_ProvideActiveRootListerFactory.create(baseLayerModule, rootsOracle_FactoryCreate);
            this.provideLifecycleMonitorProvider = BaseLayerModule_ProvideLifecycleMonitorFactory.create(baseLayerModule);
            this.provideRemoteExecutorProvider = DoubleCheck.provider(BaseLayerModule_ProvideRemoteExecutorFactory.create(baseLayerModule));
        }

        private Object rootsOracle() {
            return RootsOracle_Factory.newInstance(this.provideMainLooperProvider.get2());
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public ActiveRootLister activeRootLister() {
            return BaseLayerModule_ProvideActiveRootListerFactory.provideActiveRootLister(this.baseLayerModule, rootsOracle());
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public ControlledLooper controlledLooper() {
            return this.provideControlledLooperProvider.get2();
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public FailureHandler failureHandler() {
            return BaseLayerModule_ProvideFailureHandlerFactory.provideFailureHandler(this.baseLayerModule, this.failureHandlerHolderProvider.get2());
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public BaseLayerModule.FailureHandlerHolder failureHolder() {
            return this.failureHandlerHolderProvider.get2();
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public IdlingResourceRegistry idlingResourceRegistry() {
            return this.idlingResourceRegistryProvider.get2();
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public Executor mainThreadExecutor() {
            return this.provideMainThreadExecutorProvider.get2();
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public ViewInteractionComponent plus(ViewInteractionModule viewInteractionModule) {
            Preconditions.checkNotNull(viewInteractionModule);
            return new ViewInteractionComponentImpl(this.baseLayerComponentImpl, viewInteractionModule);
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public PlatformTestStorage testStorage() {
            return PlatformTestStorageModule_ProvideTestStorageFactory.provideTestStorage(this.platformTestStorageModule);
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public Tracing tracer() {
            return this.providesTracingProvider.get2();
        }

        @Override // androidx.test.espresso.BaseLayerComponent
        public UiController uiController() {
            return this.provideUiControllerProvider.get2();
        }
    }

    public static final class Builder {
        private BaseLayerModule baseLayerModule;
        private PlatformTestStorageModule platformTestStorageModule;
        private UiControllerModule uiControllerModule;

        private Builder() {
        }

        public Builder baseLayerModule(BaseLayerModule baseLayerModule) {
            this.baseLayerModule = (BaseLayerModule) Preconditions.checkNotNull(baseLayerModule);
            return this;
        }

        public BaseLayerComponent build() {
            if (this.baseLayerModule == null) {
                this.baseLayerModule = new BaseLayerModule();
            }
            if (this.platformTestStorageModule == null) {
                this.platformTestStorageModule = new PlatformTestStorageModule();
            }
            if (this.uiControllerModule == null) {
                this.uiControllerModule = new UiControllerModule();
            }
            return new BaseLayerComponentImpl(this.baseLayerModule, this.platformTestStorageModule, this.uiControllerModule);
        }

        public Builder platformTestStorageModule(PlatformTestStorageModule platformTestStorageModule) {
            this.platformTestStorageModule = (PlatformTestStorageModule) Preconditions.checkNotNull(platformTestStorageModule);
            return this;
        }

        public Builder uiControllerModule(UiControllerModule uiControllerModule) {
            this.uiControllerModule = (UiControllerModule) Preconditions.checkNotNull(uiControllerModule);
            return this;
        }
    }

    private static final class ViewInteractionComponentImpl implements ViewInteractionComponent {
        private final BaseLayerComponentImpl baseLayerComponentImpl;
        private Provider<AtomicReference<Boolean>> provideNeedsActivityProvider;
        private Provider<AtomicReference<Matcher<Root>>> provideRootMatcherProvider;
        private Provider<View> provideRootViewProvider;
        private Provider rootResultFetcherProvider;
        private Provider<RootViewPicker> rootViewPickerProvider;
        private final ViewInteractionComponentImpl viewInteractionComponentImpl;
        private final ViewInteractionModule viewInteractionModule;

        private ViewInteractionComponentImpl(BaseLayerComponentImpl baseLayerComponentImpl, ViewInteractionModule viewInteractionModule) {
            this.viewInteractionComponentImpl = this;
            this.baseLayerComponentImpl = baseLayerComponentImpl;
            this.viewInteractionModule = viewInteractionModule;
            initialize(viewInteractionModule);
        }

        private void initialize(ViewInteractionModule viewInteractionModule) {
            this.provideRootMatcherProvider = ViewInteractionModule_ProvideRootMatcherFactory.create(viewInteractionModule);
            this.rootResultFetcherProvider = RootViewPicker_RootResultFetcher_Factory.create(this.baseLayerComponentImpl.provideActiveRootListerProvider, this.provideRootMatcherProvider);
            this.provideNeedsActivityProvider = ViewInteractionModule_ProvideNeedsActivityFactory.create(viewInteractionModule);
            Provider<RootViewPicker> provider = DoubleCheck.provider(RootViewPicker_Factory.create(this.baseLayerComponentImpl.provideUiControllerProvider, this.rootResultFetcherProvider, this.baseLayerComponentImpl.provideLifecycleMonitorProvider, this.provideNeedsActivityProvider, this.baseLayerComponentImpl.provideControlledLooperProvider, this.baseLayerComponentImpl.provideTargetContextProvider));
            this.rootViewPickerProvider = provider;
            this.provideRootViewProvider = ViewInteractionModule_ProvideRootViewFactory.create(viewInteractionModule, provider);
        }

        private TestFlowVisualizer testFlowVisualizer() {
            return ViewInteractionModule_ProvideTestFlowVisualizerFactory.provideTestFlowVisualizer(this.viewInteractionModule, PlatformTestStorageModule_ProvideTestStorageFactory.provideTestStorage(this.baseLayerComponentImpl.platformTestStorageModule));
        }

        private ViewFinder viewFinder() {
            return ViewInteractionModule_ProvideViewFinderFactory.provideViewFinder(this.viewInteractionModule, viewFinderImpl());
        }

        private ViewFinderImpl viewFinderImpl() {
            return ViewFinderImpl_Factory.newInstance(ViewInteractionModule_ProvideViewMatcherFactory.provideViewMatcher(this.viewInteractionModule), this.provideRootViewProvider);
        }

        @Override // androidx.test.espresso.ViewInteractionComponent
        public ViewInteraction viewInteraction() {
            return new ViewInteraction((UiController) this.baseLayerComponentImpl.provideUiControllerProvider.get2(), viewFinder(), (Executor) this.baseLayerComponentImpl.provideMainThreadExecutorProvider.get2(), this.baseLayerComponentImpl.failureHandler(), ViewInteractionModule_ProvideViewMatcherFactory.provideViewMatcher(this.viewInteractionModule), ViewInteractionModule_ProvideRootMatcherFactory.provideRootMatcher(this.viewInteractionModule), ViewInteractionModule_ProvideNeedsActivityFactory.provideNeedsActivity(this.viewInteractionModule), ViewInteractionModule_ProvideRemoteInteractionFactory.provideRemoteInteraction(this.viewInteractionModule), (ListeningExecutorService) this.baseLayerComponentImpl.provideRemoteExecutorProvider.get2(), (ControlledLooper) this.baseLayerComponentImpl.provideControlledLooperProvider.get2(), testFlowVisualizer(), (Tracing) this.baseLayerComponentImpl.providesTracingProvider.get2());
        }
    }

    private DaggerBaseLayerComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BaseLayerComponent create() {
        return new Builder().build();
    }
}
