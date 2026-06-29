package id.go.bpsfasih.ui.recap;

import android.content.Context;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.data.local.models.RecapDataModel;
import id.go.bpsfasih.data.remote.dto.RecapResponse;
import id.go.bpsfasih.data.repository.AnalyticsRepositoryImpl;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RecapActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"RecapWebViewScreen", "", "onClose", "Lkotlin/Function0;", "analyticsRepository", "Lid/go/bpsfasih/data/repository/AnalyticsRepositoryImpl;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "(Lkotlin/jvm/functions/Function0;Lid/go/bpsfasih/data/repository/AnalyticsRepositoryImpl;Lio/reactivex/disposables/CompositeDisposable;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RecapActivityKt {
    public static final void RecapWebViewScreen(final Function0<Unit> onClose, final AnalyticsRepositoryImpl analyticsRepository, final CompositeDisposable compositeDisposable, Composer composer, final int i) {
        Composer composer2;
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        Intrinsics.checkNotNullParameter(analyticsRepository, "analyticsRepository");
        Intrinsics.checkNotNullParameter(compositeDisposable, "compositeDisposable");
        Composer composerStartRestartGroup = composer.startRestartGroup(-562250797);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RecapWebViewScreen)P(2)");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-562250797, i, -1, "id.go.bpsfasih.ui.recap.RecapWebViewScreen (RecapActivity.kt:56)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Context context = (Context) objConsume;
        composerStartRestartGroup.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        final MutableState mutableState = (MutableState) objRememberedValue;
        composerStartRestartGroup.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
        }
        composerStartRestartGroup.endReplaceableGroup();
        final MutableState mutableState2 = (MutableState) objRememberedValue2;
        composerStartRestartGroup.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
        }
        composerStartRestartGroup.endReplaceableGroup();
        MutableState mutableState3 = (MutableState) objRememberedValue3;
        composerStartRestartGroup.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
        }
        composerStartRestartGroup.endReplaceableGroup();
        MutableState mutableState4 = (MutableState) objRememberedValue4;
        composerStartRestartGroup.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
        if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
        }
        composerStartRestartGroup.endReplaceableGroup();
        MutableState mutableState5 = (MutableState) objRememberedValue5;
        EffectsKt.LaunchedEffect(Unit.INSTANCE, new AnonymousClass1(analyticsRepository, compositeDisposable, mutableState2, mutableState5, mutableState4, mutableState3, null), composerStartRestartGroup, 64);
        composerStartRestartGroup.startReplaceableGroup(-60094431);
        if (RecapWebViewScreen$lambda$13(mutableState5)) {
            EffectsKt.LaunchedEffect(Unit.INSTANCE, new AnonymousClass2(context, onClose, null), composerStartRestartGroup, 64);
        }
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(onClose);
        Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = (Function0) new Function0<Unit>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    WebView webViewRecapWebViewScreen$lambda$1 = RecapActivityKt.RecapWebViewScreen$lambda$1(mutableState);
                    if (webViewRecapWebViewScreen$lambda$1 != null && webViewRecapWebViewScreen$lambda$1.canGoBack()) {
                        webViewRecapWebViewScreen$lambda$1.goBack();
                    } else {
                        onClose.invoke();
                    }
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
        }
        composerStartRestartGroup.endReplaceableGroup();
        BackHandlerKt.BackHandler(false, (Function0) objRememberedValue6, composerStartRestartGroup, 0, 1);
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
        composerStartRestartGroup.startReplaceableGroup(733328855);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(-1323940314);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(constructor);
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2944constructorimpl = Updater.m2944constructorimpl(composerStartRestartGroup);
        Updater.m2951setimpl(composerM2944constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2951setimpl(composerM2944constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (composerM2944constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2944constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2944constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        function3ModifierMaterializerOf.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
        composerStartRestartGroup.startReplaceableGroup(2058660585);
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        if (RecapWebViewScreen$lambda$7(mutableState3)) {
            composerStartRestartGroup.startReplaceableGroup(-712182495);
            composer2 = composerStartRestartGroup;
            ProgressIndicatorKt.m1749CircularProgressIndicatorLxG7B9w(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), 0L, 0.0f, 0L, 0, composer2, 0, 30);
            composer2.endReplaceableGroup();
        } else {
            composer2 = composerStartRestartGroup;
            composer2.startReplaceableGroup(-712182365);
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation(composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean zChanged2 = composer2.changed(mutableState2) | composer2.changed(onClose) | composer2.changed(mutableState);
            Object objRememberedValue7 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = (Function1) new Function1<Context, WebView>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$4$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final WebView invoke(Context ctx) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                        Intrinsics.checkNotNullParameter(ctx, "ctx");
                        WebView webView = new WebView(ctx);
                        Function0<Unit> function0 = onClose;
                        final MutableState<RecapDataModel> mutableState6 = mutableState2;
                        MutableState<WebView> mutableState7 = mutableState;
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.getSettings().setDomStorageEnabled(true);
                        webView.getSettings().setAllowFileAccess(true);
                        webView.getSettings().setAllowContentAccess(true);
                        try {
                            Class<?> cls = webView.getSettings().getClass();
                            cls.getMethod("setAllowUniversalAccessFromFileURLs", Boolean.TYPE).invoke(webView.getSettings(), true);
                            cls.getMethod("setAllowFileAccessFromFileURLs", Boolean.TYPE).invoke(webView.getSettings(), true);
                        } catch (Exception unused) {
                        }
                        webView.addJavascriptInterface(new RecapJsInterface(RecapActivityKt.RecapWebViewScreen$lambda$4(mutableState6), function0), "RecapData");
                        webView.setWebChromeClient(new WebChromeClient());
                        webView.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$4$1$1$1$1
                            @Override // android.webkit.WebViewClient
                            public void onPageFinished(WebView webView2, String str) {
                                super.onPageFinished(webView2, str);
                                RecapDataModel recapDataModelRecapWebViewScreen$lambda$4 = RecapActivityKt.RecapWebViewScreen$lambda$4(mutableState6);
                                if (recapDataModelRecapWebViewScreen$lambda$4 != null) {
                                    String json = new Gson().toJson(recapDataModelRecapWebViewScreen$lambda$4);
                                    if (webView2 != null) {
                                        webView2.evaluateJavascript("if(typeof setRecapData === 'function') setRecapData(" + json + ");", null);
                                    }
                                }
                            }
                        });
                        webView.loadUrl("file:///android_asset/recap/index.html");
                        mutableState7.setValue(webView);
                        return webView;
                    }
                };
                composer2.updateRememberedValue(objRememberedValue7);
            }
            composer2.endReplaceableGroup();
            AndroidView_androidKt.AndroidView((Function1) objRememberedValue7, modifierFillMaxSize$default2, new Function1<WebView, Unit>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$4$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(WebView it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(WebView webView) {
                    invoke2(webView);
                    return Unit.INSTANCE;
                }
            }, composer2, 432, 0);
            composer2.endReplaceableGroup();
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt.RecapWebViewScreen.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i2) {
                RecapActivityKt.RecapWebViewScreen(onClose, analyticsRepository, compositeDisposable, composer3, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WebView RecapWebViewScreen$lambda$1(MutableState<WebView> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RecapDataModel RecapWebViewScreen$lambda$4(MutableState<RecapDataModel> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean RecapWebViewScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RecapWebViewScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean RecapWebViewScreen$lambda$13(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RecapWebViewScreen$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* compiled from: RecapActivity.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1", f = "RecapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnalyticsRepositoryImpl $analyticsRepository;
        final /* synthetic */ CompositeDisposable $compositeDisposable;
        final /* synthetic */ MutableState<String> $errorMessage$delegate;
        final /* synthetic */ MutableState<Boolean> $isLoading$delegate;
        final /* synthetic */ MutableState<RecapDataModel> $recapData$delegate;
        final /* synthetic */ MutableState<Boolean> $shouldClose$delegate;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AnalyticsRepositoryImpl analyticsRepositoryImpl, CompositeDisposable compositeDisposable, MutableState<RecapDataModel> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$analyticsRepository = analyticsRepositoryImpl;
            this.$compositeDisposable = compositeDisposable;
            this.$recapData$delegate = mutableState;
            this.$shouldClose$delegate = mutableState2;
            this.$errorMessage$delegate = mutableState3;
            this.$isLoading$delegate = mutableState4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$analyticsRepository, this.$compositeDisposable, this.$recapData$delegate, this.$shouldClose$delegate, this.$errorMessage$delegate, this.$isLoading$delegate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Observable<RecapResponse> observableObserveOn = this.$analyticsRepository.getRecap().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                final MutableState<RecapDataModel> mutableState = this.$recapData$delegate;
                final MutableState<Boolean> mutableState2 = this.$shouldClose$delegate;
                final MutableState<String> mutableState3 = this.$errorMessage$delegate;
                final MutableState<Boolean> mutableState4 = this.$isLoading$delegate;
                final Function1<RecapResponse, Unit> function1 = new Function1<RecapResponse, Unit>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1$disposable$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(RecapResponse recapResponse) {
                        invoke2(recapResponse);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RecapResponse recapResponse) {
                        if (!Intrinsics.areEqual((Object) recapResponse.getSuccess(), (Object) true) || recapResponse.getData() == null) {
                            RecapActivityKt.RecapWebViewScreen$lambda$14(mutableState2, true);
                            mutableState.setValue(null);
                            MutableState<String> mutableState5 = mutableState3;
                            String message = recapResponse.getMessage();
                            if (message == null) {
                                message = "Failed to fetch recap data";
                            }
                            mutableState5.setValue(message);
                            Log.e("RecapActivity", "Failed to fetch recap: " + recapResponse.getMessage());
                        } else {
                            mutableState.setValue(recapResponse.getData());
                            Log.d("RecapActivity", "Recap data fetched successfully: " + new Gson().toJson(recapResponse.getData()));
                        }
                        RecapActivityKt.RecapWebViewScreen$lambda$8(mutableState4, false);
                    }
                };
                Consumer<? super RecapResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1$$ExternalSyntheticLambda0
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj2) {
                        function1.invoke(obj2);
                    }
                };
                final MutableState<Boolean> mutableState5 = this.$shouldClose$delegate;
                final MutableState<String> mutableState6 = this.$errorMessage$delegate;
                final MutableState<Boolean> mutableState7 = this.$isLoading$delegate;
                final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1$disposable$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        RecapActivityKt.RecapWebViewScreen$lambda$14(mutableState5, true);
                        MutableState<String> mutableState8 = mutableState6;
                        String message = th.getMessage();
                        if (message == null) {
                            message = "Unknown error occurred";
                        }
                        mutableState8.setValue(message);
                        Log.e("RecapActivity", "Error fetching recap", th);
                        RecapActivityKt.RecapWebViewScreen$lambda$8(mutableState7, false);
                    }
                };
                this.$compositeDisposable.add(observableObserveOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$1$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj2) {
                        function12.invoke(obj2);
                    }
                }));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: RecapActivity.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$2", f = "RecapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.recap.RecapActivityKt$RecapWebViewScreen$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Function0<Unit> $onClose;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Function0<Unit> function0, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$onClose = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, this.$onClose, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Toast.makeText(this.$context, "Data rekap tidak ditemukan", 0).show();
                this.$onClose.invoke();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
