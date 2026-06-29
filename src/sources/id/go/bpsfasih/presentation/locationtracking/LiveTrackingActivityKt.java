package id.go.bpsfasih.presentation.locationtracking;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.SwitchDefaults;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.ColorResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.profileinstaller.ProfileVerifier;
import id.go.bpsfasih.R;
import id.go.bpsfasih.presentation.locationtracking.LocationTrackingUiState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveTrackingActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\u000b\u001a\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0002\u001a\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"LiveTrackingStatusScreen", "", "viewModel", "Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingViewModel;", "onBackPressed", "Lkotlin/Function0;", "onToggleTracking", "Lkotlin/Function1;", "", "onOpenLocationSettings", "onOpenAppSettings", "(Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "buildLocationRequirementMessage", "", "hasLocationPermission", "isGpsEnabled", "formatDuration", "totalSeconds", "", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LiveTrackingActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildLocationRequirementMessage(boolean z, boolean z2) {
        return (z || z2) ? !z ? "Izin lokasi belum aktif. Tracking dimatikan untuk melindungi konsistensi data." : "GPS perangkat sedang nonaktif. Tracking dimatikan sampai GPS diaktifkan kembali." : "Izin lokasi dan GPS harus aktif. Tracking akan dimatikan sampai keduanya tersedia.";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTrackingStatusScreen(final LiveTrackingViewModel liveTrackingViewModel, final Function0<Unit> function0, final Function1<? super Boolean, Unit> function1, final Function0<Unit> function02, final Function0<Unit> function03, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1600327760);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1600327760, i, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen (LiveTrackingActivity.kt:159)");
        }
        State stateCollectAsState = SnapshotStateKt.collectAsState(liveTrackingViewModel.getUiState(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState2 = SnapshotStateKt.collectAsState(liveTrackingViewModel.isTracking(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState3 = SnapshotStateKt.collectAsState(liveTrackingViewModel.isGpsEnabled(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState4 = SnapshotStateKt.collectAsState(liveTrackingViewModel.getHasLocationPermission(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState5 = SnapshotStateKt.collectAsState(liveTrackingViewModel.getUnsentTrackingCount(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState6 = SnapshotStateKt.collectAsState(liveTrackingViewModel.isSendingAllData(), null, composerStartRestartGroup, 8, 1);
        final State stateCollectAsState7 = SnapshotStateKt.collectAsState(liveTrackingViewModel.getSendAllDataMessage(), null, composerStartRestartGroup, 8, 1);
        final long saveIntervalSeconds = liveTrackingViewModel.getSaveIntervalSeconds();
        final long sendIntervalSeconds = liveTrackingViewModel.getSendIntervalSeconds();
        final boolean z = LiveTrackingStatusScreen$lambda$3(stateCollectAsState4) && LiveTrackingStatusScreen$lambda$2(stateCollectAsState3);
        LocationTrackingUiState locationTrackingUiStateLiveTrackingStatusScreen$lambda$0 = LiveTrackingStatusScreen$lambda$0(stateCollectAsState);
        final String message = locationTrackingUiStateLiveTrackingStatusScreen$lambda$0 instanceof LocationTrackingUiState.Error ? ((LocationTrackingUiState.Error) locationTrackingUiStateLiveTrackingStatusScreen$lambda$0).getMessage() : null;
        ScaffoldKt.m1785ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 732835444, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt.LiveTrackingStatusScreen.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                if ((i2 & 11) != 2 || !composer2.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(732835444, i2, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous> (LiveTrackingActivity.kt:182)");
                    }
                    Function2<Composer, Integer, Unit> function2M6682getLambda1$app_release = ComposableSingletons$LiveTrackingActivityKt.INSTANCE.m6682getLambda1$app_release();
                    final Function0<Unit> function04 = function0;
                    final int i3 = i;
                    AppBarKt.TopAppBar(function2M6682getLambda1$app_release, null, ComposableLambdaKt.composableLambda(composer2, 422593518, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt.LiveTrackingStatusScreen.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i4) {
                            if ((i4 & 11) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(422593518, i4, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous> (LiveTrackingActivity.kt:185)");
                                }
                                IconButtonKt.IconButton(function04, null, false, null, null, ComposableSingletons$LiveTrackingActivityKt.INSTANCE.m6683getLambda2$app_release(), composer3, ((i3 >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }), null, null, TopAppBarDefaults.INSTANCE.m2289topAppBarColorszjMxDiM(ColorResources_androidKt.colorResource(R.color.colorPrimary, composer2, 0), 0L, Color.INSTANCE.m3451getWhite0d7_KjU(), Color.INSTANCE.m3451getWhite0d7_KjU(), 0L, composer2, 265600, 18), null, composer2, 390, 90);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2.skipToGroupEnd();
            }
        }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 257837247, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt.LiveTrackingStatusScreen.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer2, Integer num) {
                invoke(paddingValues, composer2, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(PaddingValues padding, Composer composer2, int i2) {
                int i3;
                int i4;
                Intrinsics.checkNotNullParameter(padding, "padding");
                if ((i2 & 14) == 0) {
                    i3 = (composer2.changed(padding) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i3 & 91) != 18 || !composer2.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(257837247, i2, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous> (LiveTrackingActivity.kt:200)");
                    }
                    float f = 16;
                    Modifier modifierM565padding3ABfNKs = PaddingKt.m565padding3ABfNKs(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding), Dp.m5735constructorimpl(f));
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM474spacedBy0680j_4 = Arrangement.INSTANCE.m474spacedBy0680j_4(Dp.m5735constructorimpl(f));
                    final String str = message;
                    final State<Boolean> state = stateCollectAsState2;
                    final State<Boolean> state2 = stateCollectAsState4;
                    final State<Boolean> state3 = stateCollectAsState3;
                    final Function0<Unit> function04 = function03;
                    final int i5 = i;
                    final Function0<Unit> function05 = function02;
                    final Function1<Boolean, Unit> function12 = function1;
                    final boolean z2 = z;
                    final long j = saveIntervalSeconds;
                    final long j2 = sendIntervalSeconds;
                    final State<Integer> state4 = stateCollectAsState5;
                    final State<Boolean> state5 = stateCollectAsState6;
                    final LiveTrackingViewModel liveTrackingViewModel2 = liveTrackingViewModel;
                    final State<String> state6 = stateCollectAsState7;
                    composer2.startReplaceableGroup(-483455358);
                    ComposerKt.sourceInformation(composer2, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM474spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer2, 6);
                    composer2.startReplaceableGroup(-1323940314);
                    ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM565padding3ABfNKs);
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2944constructorimpl = Updater.m2944constructorimpl(composer2);
                    Updater.m2951setimpl(composerM2944constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m2951setimpl(composerM2944constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM2944constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2944constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2944constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    function3ModifierMaterializerOf.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer2)), composer2, 0);
                    composer2.startReplaceableGroup(2058660585);
                    ComposerKt.sourceInformationMarkerStart(composer2, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    SurfaceKt.m1982SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getShapes(composer2, 8).getMedium(), 0L, 0L, Dp.m5735constructorimpl(2), 0.0f, null, ComposableLambdaKt.composableLambda(composer2, -51517170, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i6) {
                            Object obj;
                            float f2;
                            String str2;
                            String str3;
                            String str4;
                            int i7;
                            if ((i6 & 11) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-51517170, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:212)");
                                }
                                Modifier modifierM565padding3ABfNKs2 = PaddingKt.m565padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5735constructorimpl(16));
                                Arrangement.HorizontalOrVertical horizontalOrVerticalM474spacedBy0680j_42 = Arrangement.INSTANCE.m474spacedBy0680j_4(Dp.m5735constructorimpl(12));
                                State<Boolean> state7 = state;
                                State<Boolean> state8 = state2;
                                State<Boolean> state9 = state3;
                                Function0<Unit> function06 = function04;
                                int i8 = i5;
                                Function0<Unit> function07 = function05;
                                Function1<Boolean, Unit> function13 = function12;
                                boolean z3 = z2;
                                composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM474spacedBy0680j_42, Alignment.INSTANCE.getStart(), composer3, 6);
                                composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM565padding3ABfNKs2);
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor2);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2944constructorimpl2 = Updater.m2944constructorimpl(composer3);
                                Updater.m2951setimpl(composerM2944constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2951setimpl(composerM2944constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM2944constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    composerM2944constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM2944constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer3)), composer3, 0);
                                composer3.startReplaceableGroup(2058660585);
                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                TextKt.m2130Text4IGK_g(LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$1(state7) ? "Live Tracking Aktif" : "Live Tracking Non-Aktif", (Modifier) null, 0L, TextUnitKt.getSp(18), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 199680, 0, 131030);
                                composer3.startReplaceableGroup(800357806);
                                if (LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$3(state8) && LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$2(state9)) {
                                    str4 = "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo";
                                    str2 = "C92@4661L9:Row.kt#2w3rfo";
                                    str3 = "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh";
                                    i7 = 1;
                                    obj = null;
                                    f2 = 0.0f;
                                } else {
                                    TextKt.m2130Text4IGK_g(LiveTrackingActivityKt.buildLocationRequirementMessage(LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$3(state8), LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$2(state9)), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, 8).getError(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3072, 0, 131058);
                                    obj = null;
                                    f2 = 0.0f;
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    Arrangement.HorizontalOrVertical horizontalOrVerticalM474spacedBy0680j_43 = Arrangement.INSTANCE.m474spacedBy0680j_4(Dp.m5735constructorimpl(8));
                                    composer3.startReplaceableGroup(693286680);
                                    ComposerKt.sourceInformation(composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM474spacedBy0680j_43, Alignment.INSTANCE.getTop(), composer3, 6);
                                    composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor3);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2944constructorimpl3 = Updater.m2944constructorimpl(composer3);
                                    Updater.m2951setimpl(composerM2944constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2951setimpl(composerM2944constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM2944constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                                        composerM2944constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                                        composerM2944constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                                    }
                                    function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer3)), composer3, 0);
                                    composer3.startReplaceableGroup(2058660585);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    composer3.startReplaceableGroup(-238321541);
                                    if (LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$3(state8)) {
                                        str2 = "C92@4661L9:Row.kt#2w3rfo";
                                        str3 = "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh";
                                        str4 = "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo";
                                        i7 = 1;
                                    } else {
                                        str2 = "C92@4661L9:Row.kt#2w3rfo";
                                        str3 = "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh";
                                        str4 = "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo";
                                        i7 = 1;
                                        ButtonKt.Button(function06, null, false, null, ButtonDefaults.INSTANCE.m1271buttonColorsro_MJ88(ColorResources_androidKt.colorResource(R.color.colorPrimary, composer3, 0), Color.INSTANCE.m3451getWhite0d7_KjU(), 0L, 0L, composer3, 32816, 12), null, null, null, null, ComposableSingletons$LiveTrackingActivityKt.INSTANCE.m6684getLambda3$app_release(), composer3, ((i8 >> 12) & 14) | 805306368, 494);
                                    }
                                    composer3.endReplaceableGroup();
                                    if (!LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$2(state9)) {
                                        ButtonKt.OutlinedButton(function07, null, false, null, null, null, null, null, null, ComposableSingletons$LiveTrackingActivityKt.INSTANCE.m6685getLambda4$app_release(), composer3, ((i8 >> 9) & 14) | 805306368, 510);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endReplaceableGroup();
                                    composer3.endNode();
                                    composer3.endReplaceableGroup();
                                    composer3.endReplaceableGroup();
                                }
                                composer3.endReplaceableGroup();
                                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f2, i7, obj);
                                Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                composer3.startReplaceableGroup(693286680);
                                ComposerKt.sourceInformation(composer3, str4);
                                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composer3, 54);
                                composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation(composer3, str3);
                                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default2);
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor4);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2944constructorimpl4 = Updater.m2944constructorimpl(composer3);
                                Updater.m2951setimpl(composerM2944constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2951setimpl(composerM2944constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM2944constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                                    composerM2944constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                                    composerM2944constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                                }
                                function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer3)), composer3, 0);
                                composer3.startReplaceableGroup(2058660585);
                                ComposerKt.sourceInformationMarkerStart(composer3, -326681643, str2);
                                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                TextKt.m2130Text4IGK_g(LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$1(state7) ? "Matikan tracking" : "Nyalakan tracking", (Modifier) null, 0L, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3072, 0, 131062);
                                SwitchKt.Switch(LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$1(state7), function13, null, null, LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$1(state7) || z3, SwitchDefaults.INSTANCE.m2013colorsV1nXRL4(Color.INSTANCE.m3451getWhite0d7_KjU(), ColorResources_androidKt.colorResource(R.color.colorPrimary, composer3, 0), 0L, 0L, Color.INSTANCE.m3451getWhite0d7_KjU(), Color.INSTANCE.m3446getLightGray0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer3, 221190, 2097152, 65484), null, composer3, (i8 >> 3) & 112, 76);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endReplaceableGroup();
                                composer3.endNode();
                                composer3.endReplaceableGroup();
                                composer3.endReplaceableGroup();
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endReplaceableGroup();
                                composer3.endNode();
                                composer3.endReplaceableGroup();
                                composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }), composer2, 12607494, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
                    composer2.startReplaceableGroup(1476987776);
                    String str2 = str;
                    if (str2 == null || str2.length() == 0) {
                        i4 = 1;
                    } else {
                        i4 = 1;
                        SurfaceKt.m1982SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getShapes(composer2, 8).getMedium(), 0L, 0L, Dp.m5735constructorimpl(1), 0.0f, null, ComposableLambdaKt.composableLambda(composer2, -555278189, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i6) {
                                if ((i6 & 11) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-555278189, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:288)");
                                    }
                                    TextKt.m2130Text4IGK_g(str, PaddingKt.m565padding3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme(composer3, 8).getError(), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3120, 0, 131056);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }), composer2, 12607494, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
                    }
                    composer2.endReplaceableGroup();
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i4, null);
                    boolean z3 = i4;
                    CornerBasedShape medium = MaterialTheme.INSTANCE.getShapes(composer2, 8).getMedium();
                    float f2 = z3 ? 1.0f : 0.0f;
                    SurfaceKt.m1982SurfaceT9BRK9s(modifierFillMaxWidth$default, medium, 0L, 0L, Dp.m5735constructorimpl(f2), 0.0f, null, ComposableLambdaKt.composableLambda(composer2, 1853079607, z3, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i6) {
                            if ((i6 & 11) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1853079607, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:302)");
                                }
                                Modifier modifierM565padding3ABfNKs2 = PaddingKt.m565padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5735constructorimpl(16));
                                Arrangement.HorizontalOrVertical horizontalOrVerticalM474spacedBy0680j_42 = Arrangement.INSTANCE.m474spacedBy0680j_4(Dp.m5735constructorimpl(6));
                                long j3 = j;
                                long j4 = j2;
                                composer3.startReplaceableGroup(-483455358);
                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM474spacedBy0680j_42, Alignment.INSTANCE.getStart(), composer3, 6);
                                composer3.startReplaceableGroup(-1323940314);
                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM565padding3ABfNKs2);
                                if (!(composer3.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor2);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2944constructorimpl2 = Updater.m2944constructorimpl(composer3);
                                Updater.m2951setimpl(composerM2944constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m2951setimpl(composerM2944constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM2944constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    composerM2944constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM2944constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer3)), composer3, 0);
                                composer3.startReplaceableGroup(2058660585);
                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                TextKt.m2130Text4IGK_g("Lokasi disimpan otomatis setiap " + LiveTrackingActivityKt.formatDuration(j3) + ".", (Modifier) null, 0L, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3072, 0, 131062);
                                TextKt.m2130Text4IGK_g("Data dikirim ke server setiap " + LiveTrackingActivityKt.formatDuration(j4) + ".", (Modifier) null, 0L, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3072, 0, 131062);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                composer3.endReplaceableGroup();
                                composer3.endNode();
                                composer3.endReplaceableGroup();
                                composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }), composer2, 12607494, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
                    SurfaceKt.m1982SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z3 ? 1 : 0, null), MaterialTheme.INSTANCE.getShapes(composer2, 8).getMedium(), 0L, 0L, Dp.m5735constructorimpl(f2), 0.0f, null, ComposableLambdaKt.composableLambda(composer2, 1194013526, z3, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i6) {
                            if ((i6 & 11) != 2 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1194013526, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:324)");
                                }
                                TextKt.m2130Text4IGK_g("Data lokasi yang belum terkirim : " + LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$4(state4), PaddingKt.m565padding3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(16)), 0L, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 199728, 0, 131028);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }), composer2, 12607494, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
                    ButtonKt.Button(new Function0<Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$5
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
                            liveTrackingViewModel2.sendAllTrackingDataInBatches();
                        }
                    }, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z3 ? 1 : 0, null), !LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$5(state5), null, ButtonDefaults.INSTANCE.m1271buttonColorsro_MJ88(ColorResources_androidKt.colorResource(R.color.colorPrimary, composer2, 0), Color.INSTANCE.m3451getWhite0d7_KjU(), 0L, 0L, composer2, 32816, 12), null, null, null, null, ComposableLambdaKt.composableLambda(composer2, 182317977, z3, new Function3<RowScope, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer3, Integer num) {
                            invoke(rowScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RowScope Button, Composer composer3, int i6) {
                            Intrinsics.checkNotNullParameter(Button, "$this$Button");
                            if ((i6 & 81) != 16 || !composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(182317977, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:341)");
                                }
                                TextKt.m2130Text4IGK_g(LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$5(state5) ? "Mengirim..." : "Kirim Semua Data Tracking (Batch 20)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131070);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer3.skipToGroupEnd();
                        }
                    }), composer2, 805306416, 488);
                    String strLiveTrackingStatusScreen$lambda$6 = LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$6(state6);
                    if (((strLiveTrackingStatusScreen$lambda$6 == null || strLiveTrackingStatusScreen$lambda$6.length() == 0) ? z3 ? 1 : 0 : false) == false) {
                        SurfaceKt.m1982SurfaceT9BRK9s(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z3 ? 1 : 0, null), MaterialTheme.INSTANCE.getShapes(composer2, 8).getMedium(), 0L, 0L, Dp.m5735constructorimpl(f2), 0.0f, null, ComposableLambdaKt.composableLambda(composer2, -1224922500, z3, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt$LiveTrackingStatusScreen$2$1$7
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i6) {
                                if ((i6 & 11) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1224922500, i6, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingStatusScreen.<anonymous>.<anonymous>.<anonymous> (LiveTrackingActivity.kt:356)");
                                    }
                                    String strLiveTrackingStatusScreen$lambda$62 = LiveTrackingActivityKt.LiveTrackingStatusScreen$lambda$6(state6);
                                    Intrinsics.checkNotNull(strLiveTrackingStatusScreen$lambda$62);
                                    TextKt.m2130Text4IGK_g(strLiveTrackingStatusScreen$lambda$62, PaddingKt.m565padding3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(16)), 0L, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3120, 0, 131060);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }), composer2, 12607494, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endReplaceableGroup();
                    composer2.endNode();
                    composer2.endReplaceableGroup();
                    composer2.endReplaceableGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2.skipToGroupEnd();
            }
        }), composerStartRestartGroup, 805306416, 509);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivityKt.LiveTrackingStatusScreen.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                LiveTrackingActivityKt.LiveTrackingStatusScreen(liveTrackingViewModel, function0, function1, function02, function03, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatDuration(long j) {
        if (j <= 0) {
            return "0 detik";
        }
        if (j % 60 == 0) {
            long j2 = j / 60;
            return j2 == 1 ? "1 menit" : j2 + " menit";
        }
        return j + " detik";
    }

    private static final LocationTrackingUiState LiveTrackingStatusScreen$lambda$0(State<? extends LocationTrackingUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTrackingStatusScreen$lambda$1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTrackingStatusScreen$lambda$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTrackingStatusScreen$lambda$3(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTrackingStatusScreen$lambda$4(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTrackingStatusScreen$lambda$5(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTrackingStatusScreen$lambda$6(State<String> state) {
        return state.getValue();
    }
}
