package id.go.bpsfasih.ui.splash;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingFunctionsKt;
import androidx.compose.animation.core.EasingKt;
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
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.draw.BlurKt;
import androidx.compose.ui.draw.ScaleKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.logging.type.LogSeverity;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.theme.ColorsKt;
import id.go.bpsfasih.ui.theme.FasihTextStyles;
import id.go.bpsfasih.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\r\u0010\u0005\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\r\u0010\u0007\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001f\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"ElegantAnimatedSplashScreen", "", "onNavigationReady", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ElegantAnimatedSplashScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "ElegantLoadingAnimation", "FloatingParticles", "visible", "", "modifier", "Landroidx/compose/ui/Modifier;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SplashActivityKt {
    public static final void ElegantAnimatedSplashScreen(final Function0<Unit> onNavigationReady, Composer composer, final int i) {
        MutableState mutableState;
        MutableState mutableState2;
        MutableState mutableState3;
        State<Float> state;
        Object obj;
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onNavigationReady, "onNavigationReady");
        Composer composerStartRestartGroup = composer.startRestartGroup(1922836430);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ElegantAnimatedSplashScreen)");
        if ((((i & 14) == 0 ? (composerStartRestartGroup.changedInstance(onNavigationReady) ? 4 : 2) | i : i) & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1922836430, i, -1, "id.go.bpsfasih.ui.splash.ElegantAnimatedSplashScreen (SplashActivity.kt:119)");
            }
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableState4 = (MutableState) objRememberedValue;
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableState5 = (MutableState) objRememberedValue2;
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableState6 = (MutableState) objRememberedValue3;
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableState7 = (MutableState) objRememberedValue4;
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(8000, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(ElegantAnimatedSplashScreen$lambda$4(mutableState5) ? 1.0f : 0.0f, AnimationSpecKt.spring$default(0.5f, 200.0f, null, 4, null), 0.0f, null, null, composerStartRestartGroup, 48, 28);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(ElegantAnimatedSplashScreen$lambda$4(mutableState5) ? 0.0f : -180.0f, AnimationSpecKt.spring$default(0.5f, 200.0f, null, 4, null), 0.0f, null, null, composerStartRestartGroup, 48, 28);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.0f, 10.0f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(3000, 0, EasingFunctionsKt.getEaseInOutSine(), 2, null), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.3f, 1.0f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2000, 0, EasingFunctionsKt.getEaseInOutSine(), 2, null), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            State<Float> stateAnimateFloatAsState3 = AnimateAsStateKt.animateFloatAsState(ElegantAnimatedSplashScreen$lambda$7(mutableState6) ? 0.0f : 200.0f, AnimationSpecKt.spring$default(0.5f, 1500.0f, null, 4, null), 0.0f, null, null, composerStartRestartGroup, 48, 28);
            State<Float> stateAnimateFloatAsState4 = AnimateAsStateKt.animateFloatAsState(ElegantAnimatedSplashScreen$lambda$7(mutableState6) ? 1.0f : 0.0f, AnimationSpecKt.tween$default(LogSeverity.EMERGENCY_VALUE, 0, null, 6, null), 0.0f, null, null, composerStartRestartGroup, 48, 28);
            Unit unit = Unit.INSTANCE;
            Object[] objArr = {mutableState4, mutableState5, mutableState6, mutableState7, onNavigationReady};
            composerStartRestartGroup.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = false;
            for (int i3 = 0; i3 < 5; i3++) {
                zChanged |= composerStartRestartGroup.changed(objArr[i3]);
            }
            SplashActivityKt$ElegantAnimatedSplashScreen$1$1 splashActivityKt$ElegantAnimatedSplashScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || splashActivityKt$ElegantAnimatedSplashScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState7;
                mutableState2 = mutableState5;
                mutableState3 = mutableState4;
                state = stateAnimateFloatAsState3;
                obj = null;
                i2 = 1;
                composer2 = composerStartRestartGroup;
                splashActivityKt$ElegantAnimatedSplashScreen$1$1RememberedValue = new SplashActivityKt$ElegantAnimatedSplashScreen$1$1(onNavigationReady, mutableState4, mutableState2, mutableState6, mutableState, null);
                composer2.updateRememberedValue(splashActivityKt$ElegantAnimatedSplashScreen$1$1RememberedValue);
            } else {
                state = stateAnimateFloatAsState3;
                mutableState = mutableState7;
                mutableState2 = mutableState5;
                mutableState3 = mutableState4;
                composer2 = composerStartRestartGroup;
                obj = null;
                i2 = 1;
            }
            composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) splashActivityKt$ElegantAnimatedSplashScreen$1$1RememberedValue, composer2, 64);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, i2, obj);
            Brush.Companion companion = Brush.INSTANCE;
            Color[] colorArr = new Color[3];
            colorArr[0] = Color.m3404boximpl(ColorKt.Color(InternalZipConstants.ZIP_64_SIZE_LIMIT));
            colorArr[i2] = Color.m3404boximpl(ColorKt.Color(4294507260L));
            colorArr[2] = Color.m3404boximpl(Color.m3413copywmQWz5c$default(ColorKt.Color(4293060848L), (ElegantAnimatedSplashScreen$lambda$12(stateAnimateFloat) * 0.3f) + 0.5f, 0.0f, 0.0f, 0.0f, 14, null));
            Modifier modifierBackground$default = BackgroundKt.background$default(modifierFillMaxSize$default, Brush.Companion.m3371verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) colorArr), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composer2, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer2, 0);
            composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierBackground$default);
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
            Updater.m2951setimpl(composerM2944constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2944constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2944constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer2)), composer2, 0);
            composer2.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composer2, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            FloatingParticles(ElegantAnimatedSplashScreen$lambda$1(mutableState3), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, i2, obj), composer2, 48, 0);
            Modifier modifierM565padding3ABfNKs = PaddingKt.m565padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, i2, obj), Dp.m5735constructorimpl(40));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            composer2.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(composer2, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
            composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM565padding3ABfNKs);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer2.useNode();
            }
            Composer composerM2944constructorimpl2 = Updater.m2944constructorimpl(composer2);
            Updater.m2951setimpl(composerM2944constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2944constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2944constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer2)), composer2, 0);
            composer2.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composer2, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Alignment center2 = Alignment.INSTANCE.getCenter();
            Modifier modifierM614size3ABfNKs = SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(240));
            final MutableState mutableState8 = mutableState2;
            Object[] objArr2 = {stateAnimateFloatAsState, stateAnimateFloatAsState2, mutableState8, stateAnimateFloat2};
            composer2.startReplaceableGroup(-568225417);
            ComposerKt.sourceInformation(composer2, "CC(remember)P(1):Composables.kt#9igjgp");
            int i4 = 0;
            boolean zChanged2 = false;
            for (int i5 = 4; i4 < i5; i5 = 4) {
                zChanged2 |= composer2.changed(objArr2[i4]);
                i4++;
            }
            Object objRememberedValue5 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt$ElegantAnimatedSplashScreen$2$1$1$1
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
                        graphicsLayer.setScaleX(SplashActivityKt.ElegantAnimatedSplashScreen$lambda$13(stateAnimateFloatAsState));
                        graphicsLayer.setScaleY(SplashActivityKt.ElegantAnimatedSplashScreen$lambda$13(stateAnimateFloatAsState));
                        graphicsLayer.setRotationZ(SplashActivityKt.ElegantAnimatedSplashScreen$lambda$14(stateAnimateFloatAsState2));
                        graphicsLayer.setTranslationY(SplashActivityKt.ElegantAnimatedSplashScreen$lambda$4(mutableState8) ? SplashActivityKt.ElegantAnimatedSplashScreen$lambda$15(stateAnimateFloat2) : 0.0f);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue5);
            }
            composer2.endReplaceableGroup();
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierM614size3ABfNKs, (Function1) objRememberedValue5);
            composer2.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composer2, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer2, 6);
            composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor3);
            } else {
                composer2.useNode();
            }
            Composer composerM2944constructorimpl3 = Updater.m2944constructorimpl(composer2);
            Updater.m2951setimpl(composerM2944constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM2944constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM2944constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composer2)), composer2, 0);
            composer2.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composer2, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            float f = 20;
            BoxKt.Box(BlurKt.m3069blurF8QBwvs$default(BackgroundKt.background$default(AlphaKt.alpha(SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(220)), ElegantAnimatedSplashScreen$lambda$16(stateAnimateFloat3) * 0.4f), Brush.Companion.m3367radialGradientP_VxKs$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m3404boximpl(Color.m3413copywmQWz5c$default(ColorKt.Color(4282090230L), 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3404boximpl(Color.INSTANCE.m3449getTransparent0d7_KjU())}), 0L, 200.0f, 0, 10, (Object) null), RoundedCornerShapeKt.getCircleShape(), 0.0f, 4, null), Dp.m5735constructorimpl(f), null, 2, null), composer2, 0);
            composerStartRestartGroup = composer2;
            final State<Float> state2 = state;
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.img_logo_fasih, composer2, 0), "Logo FASIH", PaddingKt.m565padding3ABfNKs(BackgroundKt.m211backgroundbw27NRU(SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(200)), Color.INSTANCE.m3451getWhite0d7_KjU(), RoundedCornerShapeKt.m836RoundedCornerShape0680j_4(Dp.m5735constructorimpl(f))), Dp.m5735constructorimpl(f)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composerStartRestartGroup, 56, MenuKt.InTransitionDuration);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m600height3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(50)), composerStartRestartGroup, 6);
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, ElegantAnimatedSplashScreen$lambda$10(mutableState), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(600, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.spring$default(0.5f, 0.0f, null, 6, null), new Function1<Integer, Integer>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt$ElegantAnimatedSplashScreen$2$1$3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Integer invoke(int i6) {
                    return Integer.valueOf(i6 / 2);
                }
            })), (ExitTransition) null, (String) null, ComposableSingletons$SplashActivityKt.INSTANCE.m6821getLambda1$app_release(), composerStartRestartGroup, 1572870, 26);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
            Modifier modifierAlpha = AlphaKt.alpha(boxScopeInstance.align(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getBottomCenter()), ElegantAnimatedSplashScreen$lambda$18(stateAnimateFloatAsState4));
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(state2);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt$ElegantAnimatedSplashScreen$2$2$1
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
                        graphicsLayer.setTranslationY(SplashActivityKt.ElegantAnimatedSplashScreen$lambda$17(state2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM565padding3ABfNKs2 = PaddingKt.m565padding3ABfNKs(GraphicsLayerModifierKt.graphicsLayer(modifierAlpha, (Function1) objRememberedValue6), Dp.m5735constructorimpl(32));
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally2, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM565padding3ABfNKs2);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2944constructorimpl4 = Updater.m2944constructorimpl(composerStartRestartGroup);
            Updater.m2951setimpl(composerM2944constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM2944constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM2944constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.sis, composerStartRestartGroup, 0);
            TextStyle labelLarge = FasihTextStyles.INSTANCE.getLabelLarge();
            TextKt.m2130Text4IGK_g(strStringResource, PaddingKt.m569paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m5735constructorimpl(8), 7, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5620boximpl(TextAlign.INSTANCE.m5627getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelLarge.m5261copyp1EtxEg((15695871 & 1) != 0 ? labelLarge.spanStyle.m5194getColor0d7_KjU() : ColorKt.Color(4280166715L), (15695871 & 2) != 0 ? labelLarge.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? labelLarge.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? labelLarge.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? labelLarge.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? labelLarge.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? labelLarge.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? labelLarge.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? labelLarge.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? labelLarge.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? labelLarge.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? labelLarge.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? labelLarge.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? labelLarge.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? labelLarge.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? labelLarge.paragraphStyle.getTextAlign() : 0, (15695871 & 65536) != 0 ? labelLarge.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? labelLarge.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? labelLarge.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? labelLarge.platformStyle : null, (15695871 & 1048576) != 0 ? labelLarge.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? labelLarge.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? labelLarge.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? labelLarge.paragraphStyle.getTextMotion() : null), composerStartRestartGroup, 48, 0, 65020);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.copyright, composerStartRestartGroup, 0);
            TextStyle labelSmall = FasihTextStyles.INSTANCE.getLabelSmall();
            TextKt.m2130Text4IGK_g(strStringResource2, PaddingKt.m569paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m5735constructorimpl(16), 7, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5620boximpl(TextAlign.INSTANCE.m5627getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelSmall.m5261copyp1EtxEg((15695871 & 1) != 0 ? labelSmall.spanStyle.m5194getColor0d7_KjU() : ColorKt.Color(4282865001L), (15695871 & 2) != 0 ? labelSmall.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? labelSmall.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? labelSmall.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? labelSmall.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? labelSmall.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? labelSmall.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? labelSmall.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? labelSmall.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? labelSmall.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? labelSmall.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? labelSmall.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? labelSmall.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? labelSmall.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? labelSmall.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? labelSmall.paragraphStyle.getTextAlign() : 0, (15695871 & 65536) != 0 ? labelSmall.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? labelSmall.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? labelSmall.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? labelSmall.platformStyle : null, (15695871 & 1048576) != 0 ? labelSmall.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? labelSmall.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? labelSmall.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? labelSmall.paragraphStyle.getTextMotion() : null), composerStartRestartGroup, 48, 0, 65020);
            ImageKt.Image(PainterResources_androidKt.painterResource(R.drawable.logo, composerStartRestartGroup, 0), "Logo", PaddingKt.m569paddingqDBjuR0$default(ScaleKt.scale(SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(28)), ElegantAnimatedSplashScreen$lambda$26$lambda$25$lambda$24(InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 1.0f, 1.05f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2000, 0, EasingFunctionsKt.getEaseInOutSine(), 2, null), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8))), 0.0f, 0.0f, 0.0f, Dp.m5735constructorimpl(12), 7, null), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composerStartRestartGroup, 56, MenuKt.InTransitionDuration);
            TextStyle caption = FasihTextStyles.INSTANCE.getCaption();
            TextKt.m2130Text4IGK_g("Version 2.16.3 - Code 126", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5620boximpl(TextAlign.INSTANCE.m5627getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, caption.m5261copyp1EtxEg((15695871 & 1) != 0 ? caption.spanStyle.m5194getColor0d7_KjU() : ColorKt.Color(4284773515L), (15695871 & 2) != 0 ? caption.spanStyle.getFontSize() : 0L, (15695871 & 4) != 0 ? caption.spanStyle.getFontWeight() : null, (15695871 & 8) != 0 ? caption.spanStyle.getFontStyle() : null, (15695871 & 16) != 0 ? caption.spanStyle.getFontSynthesis() : null, (15695871 & 32) != 0 ? caption.spanStyle.getFontFamily() : null, (15695871 & 64) != 0 ? caption.spanStyle.getFontFeatureSettings() : null, (15695871 & 128) != 0 ? caption.spanStyle.getLetterSpacing() : 0L, (15695871 & 256) != 0 ? caption.spanStyle.getBaselineShift() : null, (15695871 & 512) != 0 ? caption.spanStyle.getTextGeometricTransform() : null, (15695871 & 1024) != 0 ? caption.spanStyle.getLocaleList() : null, (15695871 & 2048) != 0 ? caption.spanStyle.getBackground() : 0L, (15695871 & 4096) != 0 ? caption.spanStyle.getTextDecoration() : null, (15695871 & 8192) != 0 ? caption.spanStyle.getShadow() : null, (15695871 & 16384) != 0 ? caption.spanStyle.getDrawStyle() : null, (15695871 & 32768) != 0 ? caption.paragraphStyle.getTextAlign() : 0, (15695871 & 65536) != 0 ? caption.paragraphStyle.getTextDirection() : 0, (15695871 & 131072) != 0 ? caption.paragraphStyle.getLineHeight() : 0L, (15695871 & 262144) != 0 ? caption.paragraphStyle.getTextIndent() : null, (15695871 & 524288) != 0 ? caption.platformStyle : null, (15695871 & 1048576) != 0 ? caption.paragraphStyle.getLineHeightStyle() : null, (15695871 & 2097152) != 0 ? caption.paragraphStyle.getLineBreak() : 0, (15695871 & 4194304) != 0 ? caption.paragraphStyle.getHyphens() : 0, (15695871 & 8388608) != 0 ? caption.paragraphStyle.getTextMotion() : null), composerStartRestartGroup, 0, 0, 65022);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt.ElegantAnimatedSplashScreen.3
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
                SplashActivityKt.ElegantAnimatedSplashScreen(onNavigationReady, composer3, i | 1);
            }
        });
    }

    private static final boolean ElegantAnimatedSplashScreen$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ElegantAnimatedSplashScreen$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ElegantAnimatedSplashScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ElegantAnimatedSplashScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ElegantAnimatedSplashScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ElegantAnimatedSplashScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ElegantAnimatedSplashScreen$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ElegantAnimatedSplashScreen$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static final void ElegantLoadingAnimation(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1650165236);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ElegantLoadingAnimation)");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1650165236, i, -1, "id.go.bpsfasih.ui.splash.ElegantLoadingAnimation (SplashActivity.kt:357)");
            }
            InfiniteTransition infiniteTransitionRememberInfiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, composerStartRestartGroup, 0, 1);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM474spacedBy0680j_4 = Arrangement.INSTANCE.m474spacedBy0680j_4(Dp.m5735constructorimpl(12));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM474spacedBy0680j_4, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            Updater.m2951setimpl(composerM2944constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2951setimpl(composerM2944constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2944constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2944constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2944constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2944constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2935boximpl(SkippableUpdater.m2936constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = i2 * 200;
                BoxKt.Box(BackgroundKt.background$default(AlphaKt.alpha(ScaleKt.scale(SizeKt.m614size3ABfNKs(Modifier.INSTANCE, Dp.m5735constructorimpl(14)), ElegantLoadingAnimation$lambda$30$lambda$29$lambda$27(InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.7f, 1.2f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween(600, i3, EasingFunctionsKt.getEaseInOutSine()), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8))), ElegantLoadingAnimation$lambda$30$lambda$29$lambda$28(InfiniteTransitionKt.animateFloat(infiniteTransitionRememberInfiniteTransition, 0.4f, 1.0f, AnimationSpecKt.m127infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween(600, i3, EasingFunctionsKt.getEaseInOutSine()), RepeatMode.Reverse, 0L, 4, null), null, composerStartRestartGroup, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8))), Brush.Companion.m3367radialGradientP_VxKs$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m3404boximpl(ColorsKt.getColorPrimary()), Color.m3404boximpl(ColorsKt.getColorPrimary())}), 0L, 0.0f, 0, 14, (Object) null), RoundedCornerShapeKt.getCircleShape(), 0.0f, 4, null), composerStartRestartGroup, 0);
            }
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt.ElegantLoadingAnimation.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                SplashActivityKt.ElegantLoadingAnimation(composer2, i | 1);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FloatingParticles(final boolean r25, androidx.compose.ui.Modifier r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 648
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.splash.SplashActivityKt.FloatingParticles(boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void ElegantAnimatedSplashScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1563815646);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ElegantAnimatedSplashScreenPreview)");
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1563815646, i, -1, "id.go.bpsfasih.ui.splash.ElegantAnimatedSplashScreenPreview (SplashActivity.kt:476)");
            }
            ThemeKt.FasihTheme(false, ComposableSingletons$SplashActivityKt.INSTANCE.m6822getLambda2$app_release(), composerStartRestartGroup, 48, 1);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivityKt.ElegantAnimatedSplashScreenPreview.1
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
                SplashActivityKt.ElegantAnimatedSplashScreenPreview(composer2, i | 1);
            }
        });
    }

    private static final float ElegantAnimatedSplashScreen$lambda$12(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ElegantAnimatedSplashScreen$lambda$13(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ElegantAnimatedSplashScreen$lambda$14(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ElegantAnimatedSplashScreen$lambda$15(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ElegantAnimatedSplashScreen$lambda$16(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ElegantAnimatedSplashScreen$lambda$17(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ElegantAnimatedSplashScreen$lambda$18(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ElegantAnimatedSplashScreen$lambda$26$lambda$25$lambda$24(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ElegantLoadingAnimation$lambda$30$lambda$29$lambda$27(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ElegantLoadingAnimation$lambda$30$lambda$29$lambda$28(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float FloatingParticles$lambda$35$lambda$34$lambda$31(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float FloatingParticles$lambda$35$lambda$34$lambda$32(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float FloatingParticles$lambda$35$lambda$34$lambda$33(State<Float> state) {
        return state.getValue().floatValue();
    }
}
