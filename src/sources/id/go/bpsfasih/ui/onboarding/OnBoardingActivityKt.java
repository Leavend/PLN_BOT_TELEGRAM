package id.go.bpsfasih.ui.onboarding;

import android.content.res.Resources;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingFunctionsKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.pager.PagerKt;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.foundation.pager.PagerStateKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.ClickableTextKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.logging.type.LogSeverity;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.theme.ColorsKt;
import id.go.bpsfasih.ui.theme.FasihTextStyles;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: OnBoardingActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001aX\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a;\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0016\u001a>\u0010\u0017\u001a\u00020\u00012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0007¢\u0006\u0002\u0010\u0018\u001a-\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001e\u001a\u0015\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\bH\u0007¢\u0006\u0002\u0010!\u001a\u001b\u0010\"\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0007¢\u0006\u0002\u0010#¨\u0006$"}, d2 = {"BottomSection", "", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "totalPages", "", "onLoginClick", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isDev", "onPrivacyPolicyClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/foundation/pager/PagerState;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "CleanButton", TextBundle.TEXT_ENTRY, "", "onClick", "onLongClick", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "CleanOnBoardingScreen", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "OnBoardingPageContent", "page", "Lid/go/bpsfasih/ui/onboarding/OnBoardingPage;", "isVisible", "pageIndex", "(Lid/go/bpsfasih/ui/onboarding/OnBoardingPage;ZILandroidx/compose/foundation/pager/PagerState;Landroidx/compose/runtime/Composer;I)V", "PageIndicator", "isSelected", "(ZLandroidx/compose/runtime/Composer;I)V", "PrivacyPolicyText", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class OnBoardingActivityKt {
    public static final void CleanOnBoardingScreen(final Function1<? super Boolean, Unit> onLoginClick, final Function0<Unit> onPrivacyPolicyClick, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onLoginClick, "onLoginClick");
        Intrinsics.checkNotNullParameter(onPrivacyPolicyClick, "onPrivacyPolicyClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(1690153597);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CleanOnBoardingScreen)");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onLoginClick) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onPrivacyPolicyClick) ? 32 : 16;
        }
        int i3 = i2;
        if ((i3 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1690153597, i3, -1, "id.go.bpsfasih.ui.onboarding.CleanOnBoardingScreen (OnBoardingActivity.kt:190)");
            }
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = CollectionsKt.listOf((Object[]) new OnBoardingPage[]{new OnBoardingPage(R.string.onboarding_title_welcome, R.string.onboarding_description_welcome, R.drawable.icon_wellcome_page_1), new OnBoardingPage(R.string.onboarding_title_realtime, R.string.onboarding_description_realtime, R.drawable.icon_wellcome_page_2), new OnBoardingPage(R.string.onboarding_title_offline, R.string.onboarding_description_offline, R.drawable.icon_wellcome_page_3)});
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            final List list = (List) objRememberedValue;
            final PagerState pagerStateRememberPagerState = PagerStateKt.rememberPagerState(0, 0.0f, new Function0<Integer>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$CleanOnBoardingScreen$pagerState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(list.size());
                }
            }, composerStartRestartGroup, 0, 3);
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue2;
            Unit unit = Unit.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mutableState);
            OnBoardingActivityKt$CleanOnBoardingScreen$1$1 onBoardingActivityKt$CleanOnBoardingScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || onBoardingActivityKt$CleanOnBoardingScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                onBoardingActivityKt$CleanOnBoardingScreen$1$1RememberedValue = new OnBoardingActivityKt$CleanOnBoardingScreen$1$1(mutableState, null);
                composerStartRestartGroup.updateRememberedValue(onBoardingActivityKt$CleanOnBoardingScreen$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) onBoardingActivityKt$CleanOnBoardingScreen$1$1RememberedValue, composerStartRestartGroup, 64);
            Modifier modifierM212backgroundbw27NRU$default = BackgroundKt.m212backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3451getWhite0d7_KjU(), null, 2, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM212backgroundbw27NRU$default);
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
            Modifier modifierNavigationBarsPadding = WindowInsetsPadding_androidKt.navigationBarsPadding(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null)));
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierNavigationBarsPadding);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2944constructorimpl2 = Updater.m2944constructorimpl(composerStartRestartGroup);
            Updater.m2951setimpl(composerM2944constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2944constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2944constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            PagerKt.m793HorizontalPagerxYaah8o(pagerStateRememberPagerState, ColumnScope.weight$default(ColumnScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), null, null, 0, 0.0f, null, null, false, false, null, null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 701813214, true, new Function4<PagerScope, Integer, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$CleanOnBoardingScreen$2$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(PagerScope pagerScope, Integer num, Composer composer3, Integer num2) {
                    invoke(pagerScope, num.intValue(), composer3, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PagerScope HorizontalPager, int i4, Composer composer3, int i5) {
                    Intrinsics.checkNotNullParameter(HorizontalPager, "$this$HorizontalPager");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(701813214, i5, -1, "id.go.bpsfasih.ui.onboarding.CleanOnBoardingScreen.<anonymous>.<anonymous>.<anonymous> (OnBoardingActivity.kt:239)");
                    }
                    OnBoardingActivityKt.OnBoardingPageContent(list.get(i4), OnBoardingActivityKt.CleanOnBoardingScreen$lambda$2(mutableState), i4, pagerStateRememberPagerState, composer3, (i5 << 3) & 896);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), composerStartRestartGroup, 0, 384, 4092);
            int i4 = i3 << 6;
            composer2 = composerStartRestartGroup;
            BottomSection(pagerStateRememberPagerState, list.size(), onLoginClick, onPrivacyPolicyClick, PaddingKt.m565padding3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(24)), composerStartRestartGroup, (i4 & 896) | 24576 | (i4 & 7168), 0);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.CleanOnBoardingScreen.3
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

            public final void invoke(Composer composer3, int i5) {
                OnBoardingActivityKt.CleanOnBoardingScreen(onLoginClick, onPrivacyPolicyClick, composer3, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CleanOnBoardingScreen$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CleanOnBoardingScreen$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static final void OnBoardingPageContent(final OnBoardingPage page, final boolean z, final int i, final PagerState pagerState, Composer composer, final int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(pagerState, "pagerState");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1842966478);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OnBoardingPageContent)P(1)");
        if ((i2 & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(page) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 112) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(pagerState) ? 2048 : 1024;
        }
        int i4 = i3;
        if ((i4 & 5851) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1842966478, i4, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingPageContent (OnBoardingActivity.kt:265)");
            }
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1), 0.0f, 8.0f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(4000, 0, EasingFunctionsKt.getEaseInOutSine(), 2, null), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            float currentPage = (pagerState.getCurrentPage() - i) + pagerState.getCurrentPageOffsetFraction();
            final float fCoerceAtMost = 1.0f - RangesKt.coerceAtMost(Math.abs(currentPage) * 0.2f, 0.2f);
            float f = 48;
            Modifier modifierAlpha = AlphaKt.alpha(PaddingKt.m566paddingVpY3zN4(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5735constructorimpl(32), Dp.m5735constructorimpl(f)), 1.0f - RangesKt.coerceAtMost(Math.abs(currentPage) * 0.5f, 1.0f));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierAlpha);
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
            Updater.m2951setimpl(composerM2944constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2944constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2944constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Alignment center2 = Alignment.INSTANCE.getCenter();
            Modifier modifierM614size3ABfNKs = SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(320));
            Float fValueOf = Float.valueOf(fCoerceAtMost);
            composerStartRestartGroup.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(fValueOf) | composerStartRestartGroup.changed(stateAnimateFloat);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                        invoke2(graphicsLayerScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GraphicsLayerScope graphicsLayer) {
                        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
                        graphicsLayer.setScaleX(fCoerceAtMost);
                        graphicsLayer.setScaleY(fCoerceAtMost);
                        graphicsLayer.setTranslationY(OnBoardingActivityKt.OnBoardingPageContent$lambda$7(stateAnimateFloat));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM614size3ABfNKs, (Function1) objRememberedValue);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center2, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2944constructorimpl2 = Updater.m2944constructorimpl(composerStartRestartGroup);
            Updater.m2951setimpl(composerM2944constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2944constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2944constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            CardKt.Card(SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(280)), RoundedCornerShapeKt.m836RoundedCornerShape0680j_4(Dp.m5735constructorimpl(20)), CardDefaults.INSTANCE.m1292cardColorsro_MJ88(Color.INSTANCE.m3451getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, 32774, 14), CardDefaults.INSTANCE.m1293cardElevationaqJV_2Y(Dp.m5735constructorimpl(8), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 2097158, 62), null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1729414652, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$2$1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) throws Resources.NotFoundException {
                    invoke(columnScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ColumnScope Card, Composer composer2, int i5) throws Resources.NotFoundException {
                    Intrinsics.checkNotNullParameter(Card, "$this$Card");
                    if ((i5 & 81) != 16 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1729414652, i5, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingPageContent.<anonymous>.<anonymous>.<anonymous> (OnBoardingActivity.kt:313)");
                        }
                        ImageKt.Image(PainterResources_androidKt.painterResource(page.getImageRes(), composer2, 0), StringResources_androidKt.stringResource(page.getTitleRes(), composer2, 0), PaddingKt.m565padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5735constructorimpl(24)), (Alignment) null, ContentScale.INSTANCE.getFit(), 0.0f, (ColorFilter) null, composer2, 24968, 104);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }), composerStartRestartGroup, 196614, 16);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m600height3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(f)), composerStartRestartGroup, 6);
            int i5 = i * 200;
            int i6 = 1572870 | (i4 & 112);
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, z, (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(LogSeverity.EMERGENCY_VALUE, i5, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), new Function1<Integer, Integer>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Integer invoke(int i7) {
                    return Integer.valueOf(i7 / 3);
                }
            })), (ExitTransition) null, (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -249001436, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$4
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) throws Resources.NotFoundException {
                    invoke(animatedVisibilityScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer2, int i7) throws Resources.NotFoundException {
                    Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-249001436, i7, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingPageContent.<anonymous>.<anonymous> (OnBoardingActivity.kt:336)");
                    }
                    String strStringResource = StringResources_androidKt.stringResource(page.getTitleRes(), composer2, 0);
                    TextStyle headingLarge = FasihTextStyles.INSTANCE.getHeadingLarge();
                    TextKt.m2130Text4IGK_g(strStringResource, PaddingKt.m567paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5735constructorimpl(16), 0.0f, 2, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5620boximpl(TextAlign.INSTANCE.m5627getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, headingLarge.m5261copyp1EtxEg((15695871 & 1) != 0 ? headingLarge.spanStyle.m5194getColor0d7_KjU() : ColorKt.Color(4280166715L), (15695871 & 2) != 0 ? headingLarge.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? headingLarge.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? headingLarge.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? headingLarge.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? headingLarge.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? headingLarge.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? headingLarge.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? headingLarge.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? headingLarge.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? headingLarge.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? headingLarge.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? headingLarge.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? headingLarge.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? headingLarge.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? headingLarge.paragraphStyle.getTextAlign() : 0, (15695871 & 65536) != 0 ? headingLarge.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? headingLarge.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? headingLarge.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? headingLarge.platformStyle : null, (15695871 & 1048576) != 0 ? headingLarge.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? headingLarge.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? headingLarge.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? headingLarge.paragraphStyle.getTextMotion() : null), composer2, 48, 0, 65020);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), composerStartRestartGroup, i6, 26);
            SpacerKt.Spacer(SizeKt.m600height3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(16)), composerStartRestartGroup, 6);
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, z, (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(LogSeverity.EMERGENCY_VALUE, i5 + 400, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), new Function1<Integer, Integer>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Integer invoke(int i7) {
                    return Integer.valueOf(i7 / 3);
                }
            })), (ExitTransition) null, (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 228855053, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$OnBoardingPageContent$1$6
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer2, Integer num) throws Resources.NotFoundException {
                    invoke(animatedVisibilityScope, composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer2, int i7) throws Resources.NotFoundException {
                    Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(228855053, i7, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingPageContent.<anonymous>.<anonymous> (OnBoardingActivity.kt:357)");
                    }
                    String strStringResource = StringResources_androidKt.stringResource(page.getDescriptionRes(), composer2, 0);
                    TextStyle bodyMedium = FasihTextStyles.INSTANCE.getBodyMedium();
                    TextKt.m2130Text4IGK_g(strStringResource, PaddingKt.m567paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m5735constructorimpl(16), 0.0f, 2, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5620boximpl(TextAlign.INSTANCE.m5627getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodyMedium.m5261copyp1EtxEg((15695871 & 1) != 0 ? bodyMedium.spanStyle.m5194getColor0d7_KjU() : ColorKt.Color(4282865001L), (15695871 & 2) != 0 ? bodyMedium.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? bodyMedium.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? bodyMedium.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? bodyMedium.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? bodyMedium.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? bodyMedium.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? bodyMedium.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? bodyMedium.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? bodyMedium.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? bodyMedium.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? bodyMedium.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? bodyMedium.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? bodyMedium.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? bodyMedium.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? bodyMedium.paragraphStyle.getTextAlign() : 0, (15695871 & 65536) != 0 ? bodyMedium.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? bodyMedium.paragraphStyle.getLineHeight() : TextUnitKt.getSp(22), (15695871 & 262144) != 0 ? bodyMedium.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? bodyMedium.platformStyle : null, (15695871 & 1048576) != 0 ? bodyMedium.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? bodyMedium.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? bodyMedium.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? bodyMedium.paragraphStyle.getTextMotion() : null), composer2, 48, 0, 65020);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), composerStartRestartGroup, i6, 26);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.OnBoardingPageContent.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i7) {
                OnBoardingActivityKt.OnBoardingPageContent(page, z, i, pagerState, composer2, i2 | 1);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02b3 A[EDGE_INSN: B:127:0x02b3->B:107:0x02b3 BREAK  A[LOOP:0: B:100:0x02a1->B:106:0x02ac], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x026d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BottomSection(final androidx.compose.foundation.pager.PagerState r19, final int r20, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r21, final kotlin.jvm.functions.Function0<kotlin.Unit> r22, androidx.compose.ui.Modifier r23, androidx.compose.runtime.Composer r24, final int r25, final int r26) {
        /*
            Method dump skipped, instructions count: 931
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.BottomSection(androidx.compose.foundation.pager.PagerState, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BottomSection$lambda$12(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BottomSection$lambda$13(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    public static final void PageIndicator(final boolean z, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(359022346);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PageIndicator)");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(359022346, i, -1, "id.go.bpsfasih.ui.onboarding.PageIndicator (OnBoardingActivity.kt:431)");
            }
            BoxKt.Box(BackgroundKt.m211backgroundbw27NRU(AlphaKt.alpha(SizeKt.m600height3ABfNKs(SizeKt.m619width3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(PageIndicator$lambda$19(AnimateAsStateKt.animateIntAsState(z ? 32 : 8, AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), "width", null, composerStartRestartGroup, 432, 8)))), Dp.m5735constructorimpl(8)), PageIndicator$lambda$20(AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.3f, AnimationSpecKt.tween$default(300, 0, null, 6, null), 0.0f, "alpha", null, composerStartRestartGroup, 3120, 20))), ColorsKt.getColorPrimary(), RoundedCornerShapeKt.m836RoundedCornerShape0680j_4(Dp.m5735constructorimpl(4))), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.PageIndicator.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                OnBoardingActivityKt.PageIndicator(z, composer2, i | 1);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CleanButton(final java.lang.String r58, final kotlin.jvm.functions.Function0<kotlin.Unit> r59, final kotlin.jvm.functions.Function0<kotlin.Unit> r60, androidx.compose.ui.Modifier r61, androidx.compose.runtime.Composer r62, final int r63, final int r64) {
        /*
            Method dump skipped, instructions count: 831
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.CleanButton(java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CleanButton$lambda$22(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CleanButton$lambda$23(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static final void PrivacyPolicyText(final Function0<Unit> onClick, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(-571228141);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PrivacyPolicyText)");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onClick) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-571228141, i2, -1, "id.go.bpsfasih.ui.onboarding.PrivacyPolicyText (OnBoardingActivity.kt:519)");
            }
            composerStartRestartGroup.startReplaceableGroup(-1621170528);
            AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
            builder.append(StringResources_androidKt.stringResource(R.string.onboarding_privacy_policy_text_1, composerStartRestartGroup, 0));
            builder.pushStringAnnotation("privacy_policy", "https://fasih-kebijakan-privasi.firebaseapp.com/");
            composerStartRestartGroup.startReplaceableGroup(-1621170201);
            int iPushStyle = builder.pushStyle(new SpanStyle(ColorKt.Color(4282090230L), 0L, FasihTextStyles.INSTANCE.getLabelLargeBold().getFontWeight(), (FontStyle) null, (FontSynthesis) null, FasihTextStyles.INSTANCE.getLabelLargeBold().getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65498, (DefaultConstructorMarker) null));
            try {
                builder.append(StringResources_androidKt.stringResource(R.string.onboarding_privacy_policy_text_2, composerStartRestartGroup, 0));
                Unit unit = Unit.INSTANCE;
                builder.pop(iPushStyle);
                composerStartRestartGroup.endReplaceableGroup();
                builder.pop();
                builder.append(StringResources_androidKt.stringResource(R.string.onboarding_privacy_policy_text_3, composerStartRestartGroup, 0));
                final AnnotatedString annotatedString = builder.toAnnotatedString();
                composerStartRestartGroup.endReplaceableGroup();
                TextStyle labelLarge = FasihTextStyles.INSTANCE.getLabelLarge();
                TextStyle textStyleM5261copyp1EtxEg = labelLarge.m5261copyp1EtxEg((15695871 & 1) != 0 ? labelLarge.spanStyle.m5194getColor0d7_KjU() : ColorsKt.getColorPrimary(), (15695871 & 2) != 0 ? labelLarge.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? labelLarge.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? labelLarge.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? labelLarge.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? labelLarge.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? labelLarge.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? labelLarge.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? labelLarge.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? labelLarge.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? labelLarge.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? labelLarge.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? labelLarge.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? labelLarge.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? labelLarge.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? labelLarge.paragraphStyle.getTextAlign() : TextAlign.INSTANCE.m5627getCentere0LSkKk(), (15695871 & 65536) != 0 ? labelLarge.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? labelLarge.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? labelLarge.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? labelLarge.platformStyle : null, (15695871 & 1048576) != 0 ? labelLarge.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? labelLarge.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? labelLarge.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? labelLarge.paragraphStyle.getTextMotion() : null);
                Modifier modifierM565padding3ABfNKs = PaddingKt.m565padding3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(8));
                composerStartRestartGroup.startReplaceableGroup(511388516);
                ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(annotatedString) | composerStartRestartGroup.changed(onClick);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = (Function1) new Function1<Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt$PrivacyPolicyText$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i3) {
                            if (((AnnotatedString.Range) CollectionsKt.firstOrNull((List) annotatedString.getStringAnnotations("privacy_policy", i3, i3))) != null) {
                                onClick.invoke();
                            }
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                ClickableTextKt.m849ClickableText4YKlhWE(annotatedString, modifierM565padding3ABfNKs, textStyleM5261copyp1EtxEg, false, 0, 0, null, (Function1) objRememberedValue, composerStartRestartGroup, 48, MenuKt.InTransitionDuration);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } catch (Throwable th) {
                builder.pop(iPushStyle);
                throw th;
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivityKt.PrivacyPolicyText.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                OnBoardingActivityKt.PrivacyPolicyText(onClick, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float OnBoardingPageContent$lambda$7(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final int PageIndicator$lambda$19(State<Integer> state) {
        return state.getValue().intValue();
    }

    private static final float PageIndicator$lambda$20(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float CleanButton$lambda$24(State<Float> state) {
        return state.getValue().floatValue();
    }
}
