package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Scaffold.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0087\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u00192\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001f\u001a\u00020 2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a±\u0001\u0010$\u001a\u00020\u00142\b\b\u0002\u0010%\u001a\u00020&2\u0013\b\u0002\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0013\b\u0002\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0013\b\u0002\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0013\b\u0002\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\b\b\u0002\u0010)\u001a\u00020\u00162\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020+2\b\b\u0002\u0010\u001f\u001a\u00020 2\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u0087\u0001\u0010/\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u00192\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001f\u001a\u00020 2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003ø\u0001\u0000¢\u0006\u0004\b0\u0010#\u001a\u0087\u0001\u00101\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u00192\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001f\u001a\u00020 2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u0019H\u0003ø\u0001\u0000¢\u0006\u0004\b2\u0010#\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"1\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8G@GX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "<set-?>", "", "ScaffoldSubcomposeInMeasureFix", "getScaffoldSubcomposeInMeasureFix$annotations", "()V", "getScaffoldSubcomposeInMeasureFix", "()Z", "setScaffoldSubcomposeInMeasureFix", "(Z)V", "ScaffoldSubcomposeInMeasureFix$delegate", "Landroidx/compose/runtime/MutableState;", "LegacyScaffoldLayout", "", "fabPosition", "Landroidx/compose/material3/FabPosition;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "snackbar", "fab", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "bottomBar", "LegacyScaffoldLayout-FMILGgc", "(ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Scaffold", "modifier", "Landroidx/compose/ui/Modifier;", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;IJJLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScaffoldLayout", "ScaffoldLayout-FMILGgc", "ScaffoldLayoutWithMeasureFix", "ScaffoldLayoutWithMeasureFix-FMILGgc", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ScaffoldKt {
    private static final MutableState ScaffoldSubcomposeInMeasureFix$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material3.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m5735constructorimpl(16);

    public static /* synthetic */ void getScaffoldSubcomposeInMeasureFix$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:198:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /* renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1785ScaffoldTvnljyQ(androidx.compose.ui.Modifier r28, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, int r33, long r34, long r36, androidx.compose.foundation.layout.WindowInsets r38, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42) {
        /*
            Method dump skipped, instructions count: 785
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt.m1785ScaffoldTvnljyQ(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, long, long, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m1786ScaffoldLayoutFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-975511942);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScaffoldLayout)P(4:c#material3.FabPosition,6,1,5,3,2):Scaffold.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((1572864 & i2) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if ((599187 & i3) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-975511942, i3, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:141)");
            }
            if (getScaffoldSubcomposeInMeasureFix()) {
                composerStartRestartGroup.startReplaceableGroup(-915303637);
                ComposerKt.sourceInformation(composerStartRestartGroup, "143@6712L283");
                m1787ScaffoldLayoutWithMeasureFixFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composerStartRestartGroup, (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (i3 & 3670016));
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(-915303332);
                ComposerKt.sourceInformation(composerStartRestartGroup, "153@7017L275");
                m1784LegacyScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composerStartRestartGroup, (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (i3 & 3670016));
                composerStartRestartGroup.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1
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

                public final void invoke(Composer composer2, int i4) {
                    ScaffoldKt.m1786ScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScaffoldLayoutWithMeasureFix-FMILGgc, reason: not valid java name */
    public static final void m1787ScaffoldLayoutWithMeasureFixFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        int i4;
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2037614249);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScaffoldLayoutWithMeasureFix)P(4:c#material3.FabPosition,6,1,5,3,2)178@7738L6567,178@7721L6584:Scaffold.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if ((i3 & 599187) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2037614249, i3, -1, "androidx.compose.material3.ScaffoldLayoutWithMeasureFix (Scaffold.kt:177)");
            }
            composerStartRestartGroup.startReplaceableGroup(-273325894);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Scaffold.kt#9igjgp");
            boolean z = ((i3 & 112) == 32) | ((i3 & 7168) == 2048) | ((458752 & i3) == 131072) | ((57344 & i3) == 16384) | ((i3 & 14) == 4) | ((3670016 & i3) == 1048576) | ((i3 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i4 = 0;
                obj = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1792invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1792invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, long j) {
                        Object obj2;
                        Object obj3;
                        Object obj4;
                        final FabPlacement fabPlacement;
                        Object obj5;
                        Integer numValueOf;
                        int i5;
                        int iIntValue;
                        int height;
                        int bottom;
                        Object obj6;
                        Object obj7;
                        int i6;
                        int i7;
                        final int iM5691getMaxWidthimpl = Constraints.m5691getMaxWidthimpl(j);
                        final int iM5690getMaxHeightimpl = Constraints.m5690getMaxHeightimpl(j);
                        long jM5682copyZbe2FdA$default = Constraints.m5682copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, function2);
                        ArrayList arrayList = new ArrayList(listSubcompose.size());
                        int size = listSubcompose.size();
                        for (int i8 = 0; i8 < size; i8++) {
                            arrayList.add(listSubcompose.get(i8).mo4684measureBRTryo0(jM5682copyZbe2FdA$default));
                        }
                        final ArrayList arrayList2 = arrayList;
                        if (arrayList2.isEmpty()) {
                            obj2 = null;
                        } else {
                            obj2 = arrayList2.get(0);
                            int height2 = ((Placeable) obj2).getHeight();
                            int lastIndex = CollectionsKt.getLastIndex(arrayList2);
                            if (1 <= lastIndex) {
                                int i9 = 1;
                                while (true) {
                                    Object obj8 = arrayList2.get(i9);
                                    int height3 = ((Placeable) obj8).getHeight();
                                    if (height2 < height3) {
                                        obj2 = obj8;
                                        height2 = height3;
                                    }
                                    if (i9 == lastIndex) {
                                        break;
                                    }
                                    i9++;
                                }
                            }
                        }
                        Placeable placeable = (Placeable) obj2;
                        final int height4 = placeable != null ? placeable.getHeight() : 0;
                        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, function22);
                        WindowInsets windowInsets2 = windowInsets;
                        ArrayList arrayList3 = new ArrayList(listSubcompose2.size());
                        int size2 = listSubcompose2.size();
                        int i10 = 0;
                        while (i10 < size2) {
                            SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                            arrayList3.add(listSubcompose2.get(i10).mo4684measureBRTryo0(ConstraintsKt.m5707offsetNN6EwU(jM5682copyZbe2FdA$default, (-windowInsets2.getLeft(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection())) - windowInsets2.getRight(subcomposeMeasureScope2, subcomposeMeasureScope.getLayoutDirection()), -windowInsets2.getBottom(subcomposeMeasureScope2))));
                            i10++;
                            listSubcompose2 = listSubcompose2;
                        }
                        final ArrayList arrayList4 = arrayList3;
                        if (arrayList4.isEmpty()) {
                            obj3 = null;
                        } else {
                            obj3 = arrayList4.get(0);
                            int height5 = ((Placeable) obj3).getHeight();
                            int lastIndex2 = CollectionsKt.getLastIndex(arrayList4);
                            if (1 <= lastIndex2) {
                                Object obj9 = obj3;
                                int i11 = height5;
                                int i12 = 1;
                                while (true) {
                                    Object obj10 = arrayList4.get(i12);
                                    int height6 = ((Placeable) obj10).getHeight();
                                    if (i11 < height6) {
                                        obj9 = obj10;
                                        i11 = height6;
                                    }
                                    if (i12 == lastIndex2) {
                                        break;
                                    }
                                    i12++;
                                }
                                obj3 = obj9;
                            }
                        }
                        Placeable placeable2 = (Placeable) obj3;
                        int height7 = placeable2 != null ? placeable2.getHeight() : 0;
                        if (arrayList4.isEmpty()) {
                            obj4 = null;
                        } else {
                            obj4 = arrayList4.get(0);
                            int width = ((Placeable) obj4).getWidth();
                            int lastIndex3 = CollectionsKt.getLastIndex(arrayList4);
                            if (1 <= lastIndex3) {
                                Object obj11 = obj4;
                                int i13 = width;
                                int i14 = 1;
                                while (true) {
                                    Object obj12 = arrayList4.get(i14);
                                    int width2 = ((Placeable) obj12).getWidth();
                                    if (i13 < width2) {
                                        obj11 = obj12;
                                        i13 = width2;
                                    }
                                    if (i14 == lastIndex3) {
                                        break;
                                    }
                                    i14++;
                                }
                                obj4 = obj11;
                            }
                        }
                        Placeable placeable3 = (Placeable) obj4;
                        int width3 = placeable3 != null ? placeable3.getWidth() : 0;
                        List<Measurable> listSubcompose3 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, function23);
                        WindowInsets windowInsets3 = windowInsets;
                        ArrayList arrayList5 = new ArrayList(listSubcompose3.size());
                        int size3 = listSubcompose3.size();
                        int i15 = 0;
                        while (i15 < size3) {
                            Measurable measurable = listSubcompose3.get(i15);
                            SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope;
                            List<Measurable> list = listSubcompose3;
                            int i16 = size3;
                            int right = (-windowInsets3.getLeft(subcomposeMeasureScope3, subcomposeMeasureScope.getLayoutDirection())) - windowInsets3.getRight(subcomposeMeasureScope3, subcomposeMeasureScope.getLayoutDirection());
                            int i17 = -windowInsets3.getBottom(subcomposeMeasureScope3);
                            WindowInsets windowInsets4 = windowInsets3;
                            Placeable placeableMo4684measureBRTryo0 = measurable.mo4684measureBRTryo0(ConstraintsKt.m5707offsetNN6EwU(jM5682copyZbe2FdA$default, right, i17));
                            if (!((placeableMo4684measureBRTryo0.getHeight() == 0 || placeableMo4684measureBRTryo0.getWidth() == 0) ? false : true)) {
                                placeableMo4684measureBRTryo0 = null;
                            }
                            if (placeableMo4684measureBRTryo0 != null) {
                                arrayList5.add(placeableMo4684measureBRTryo0);
                            }
                            i15++;
                            windowInsets3 = windowInsets4;
                            listSubcompose3 = list;
                            size3 = i16;
                        }
                        final ArrayList arrayList6 = arrayList5;
                        if (!arrayList6.isEmpty()) {
                            if (arrayList6.isEmpty()) {
                                obj6 = null;
                            } else {
                                obj6 = arrayList6.get(0);
                                int width4 = ((Placeable) obj6).getWidth();
                                int lastIndex4 = CollectionsKt.getLastIndex(arrayList6);
                                if (1 <= lastIndex4) {
                                    int i18 = width4;
                                    int i19 = 1;
                                    while (true) {
                                        Object obj13 = arrayList6.get(i19);
                                        int width5 = ((Placeable) obj13).getWidth();
                                        if (i18 < width5) {
                                            obj6 = obj13;
                                            i18 = width5;
                                        }
                                        if (i19 == lastIndex4) {
                                            break;
                                        }
                                        i19++;
                                    }
                                }
                            }
                            Intrinsics.checkNotNull(obj6);
                            int width6 = ((Placeable) obj6).getWidth();
                            if (arrayList6.isEmpty()) {
                                obj7 = null;
                            } else {
                                obj7 = arrayList6.get(0);
                                int height8 = ((Placeable) obj7).getHeight();
                                int lastIndex5 = CollectionsKt.getLastIndex(arrayList6);
                                if (1 <= lastIndex5) {
                                    Object obj14 = obj7;
                                    int i20 = height8;
                                    int i21 = 1;
                                    while (true) {
                                        Object obj15 = arrayList6.get(i21);
                                        Object obj16 = obj14;
                                        int height9 = ((Placeable) obj15).getHeight();
                                        if (i20 < height9) {
                                            i20 = height9;
                                            obj14 = obj15;
                                        } else {
                                            obj14 = obj16;
                                        }
                                        if (i21 == lastIndex5) {
                                            break;
                                        }
                                        i21++;
                                    }
                                    obj7 = obj14;
                                }
                            }
                            Intrinsics.checkNotNull(obj7);
                            int height10 = ((Placeable) obj7).getHeight();
                            int i22 = i;
                            if (FabPosition.m1556equalsimpl0(i22, FabPosition.INSTANCE.m1563getStartERTFSPs())) {
                                if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                    i6 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    fabPlacement = new FabPlacement(i6, width6, height10);
                                } else {
                                    i7 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    i6 = (iM5691getMaxWidthimpl - i7) - width6;
                                    fabPlacement = new FabPlacement(i6, width6, height10);
                                }
                            } else {
                                if (FabPosition.m1556equalsimpl0(i22, FabPosition.INSTANCE.m1561getEndERTFSPs()) ? true : FabPosition.m1556equalsimpl0(i22, FabPosition.INSTANCE.m1562getEndOverlayERTFSPs())) {
                                    if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                        i7 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                        i6 = (iM5691getMaxWidthimpl - i7) - width6;
                                    } else {
                                        i6 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    }
                                } else {
                                    i6 = (iM5691getMaxWidthimpl - width6) / 2;
                                }
                                fabPlacement = new FabPlacement(i6, width6, height10);
                            }
                        } else {
                            fabPlacement = null;
                        }
                        ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                        final Function2<Composer, Integer, Unit> function25 = function24;
                        List<Measurable> listSubcompose4 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(1843374446, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1$bottomBarPlaceables$1
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

                            public final void invoke(Composer composer2, int i23) {
                                ComposerKt.sourceInformation(composer2, "C258@11166L132:Scaffold.kt#uh7d8r");
                                if ((i23 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1843374446, i23, -1, "androidx.compose.material3.ScaffoldLayoutWithMeasureFix.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:258)");
                                    }
                                    CompositionLocalKt.CompositionLocalProvider(ScaffoldKt.getLocalFabPlacement().provides(fabPlacement), function25, composer2, ProvidedValue.$stable | 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }));
                        ArrayList arrayList7 = new ArrayList(listSubcompose4.size());
                        int size4 = listSubcompose4.size();
                        int i23 = 0;
                        while (i23 < size4) {
                            arrayList7.add(listSubcompose4.get(i23).mo4684measureBRTryo0(jM5682copyZbe2FdA$default));
                            i23++;
                            listSubcompose4 = listSubcompose4;
                        }
                        final ArrayList arrayList8 = arrayList7;
                        if (arrayList8.isEmpty()) {
                            obj5 = null;
                        } else {
                            obj5 = arrayList8.get(0);
                            int height11 = ((Placeable) obj5).getHeight();
                            int lastIndex6 = CollectionsKt.getLastIndex(arrayList8);
                            if (1 <= lastIndex6) {
                                int i24 = 1;
                                while (true) {
                                    Object obj17 = arrayList8.get(i24);
                                    Object obj18 = obj5;
                                    int height12 = ((Placeable) obj17).getHeight();
                                    if (height11 < height12) {
                                        height11 = height12;
                                        obj5 = obj17;
                                    } else {
                                        obj5 = obj18;
                                    }
                                    if (i24 == lastIndex6) {
                                        break;
                                    }
                                    i24++;
                                }
                            }
                        }
                        Placeable placeable4 = (Placeable) obj5;
                        Integer numValueOf2 = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
                        if (fabPlacement != null) {
                            int i25 = i;
                            WindowInsets windowInsets5 = windowInsets;
                            if (numValueOf2 == null || FabPosition.m1556equalsimpl0(i25, FabPosition.INSTANCE.m1562getEndOverlayERTFSPs())) {
                                height = fabPlacement.getHeight() + subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                bottom = windowInsets5.getBottom(subcomposeMeasureScope);
                            } else {
                                height = numValueOf2.intValue() + fabPlacement.getHeight();
                                bottom = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                            }
                            numValueOf = Integer.valueOf(height + bottom);
                        } else {
                            numValueOf = null;
                        }
                        if (height7 != 0) {
                            if (numValueOf != null) {
                                iIntValue = numValueOf.intValue();
                            } else {
                                iIntValue = numValueOf2 != null ? numValueOf2.intValue() : windowInsets.getBottom(subcomposeMeasureScope);
                            }
                            i5 = height7 + iIntValue;
                        } else {
                            i5 = 0;
                        }
                        ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                        final WindowInsets windowInsets6 = windowInsets;
                        final Function3<PaddingValues, Composer, Integer, Unit> function32 = function3;
                        final int i26 = width3;
                        final Integer num = numValueOf2;
                        final FabPlacement fabPlacement2 = fabPlacement;
                        List<Measurable> listSubcompose5 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(1655277373, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1$bodyContentPlaceables$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                invoke(composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i27) {
                                float top;
                                float bottom2;
                                Integer num2;
                                ComposerKt.sourceInformation(composer2, "C302@13090L21:Scaffold.kt#uh7d8r");
                                if ((i27 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1655277373, i27, -1, "androidx.compose.material3.ScaffoldLayoutWithMeasureFix.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:285)");
                                    }
                                    PaddingValues paddingValuesAsPaddingValues = WindowInsetsKt.asPaddingValues(windowInsets6, subcomposeMeasureScope);
                                    if (arrayList2.isEmpty()) {
                                        top = paddingValuesAsPaddingValues.getTop();
                                    } else {
                                        top = subcomposeMeasureScope.mo313toDpu2uoSUM(height4);
                                    }
                                    if (arrayList8.isEmpty() || (num2 = num) == null) {
                                        bottom2 = paddingValuesAsPaddingValues.getBottom();
                                    } else {
                                        bottom2 = subcomposeMeasureScope.mo313toDpu2uoSUM(num2.intValue());
                                    }
                                    function32.invoke(PaddingKt.m561PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), top, PaddingKt.calculateEndPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), bottom2), composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }));
                        ArrayList arrayList9 = new ArrayList(listSubcompose5.size());
                        int size5 = listSubcompose5.size();
                        for (int i27 = 0; i27 < size5; i27++) {
                            arrayList9.add(listSubcompose5.get(i27).mo4684measureBRTryo0(jM5682copyZbe2FdA$default));
                        }
                        final ArrayList arrayList10 = arrayList9;
                        final WindowInsets windowInsets7 = windowInsets;
                        final int i28 = i5;
                        final Integer num2 = numValueOf2;
                        final Integer num3 = numValueOf;
                        return MeasureScope.layout$default(subcomposeMeasureScope, iM5691getMaxWidthimpl, iM5690getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                List<Placeable> list2 = arrayList10;
                                int size6 = list2.size();
                                for (int i29 = 0; i29 < size6; i29++) {
                                    Placeable.PlacementScope.place$default(placementScope, list2.get(i29), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list3 = arrayList2;
                                int size7 = list3.size();
                                for (int i30 = 0; i30 < size7; i30++) {
                                    Placeable.PlacementScope.place$default(placementScope, list3.get(i30), 0, 0, 0.0f, 4, null);
                                }
                                List<Placeable> list4 = arrayList4;
                                int i31 = iM5691getMaxWidthimpl;
                                int i32 = i26;
                                WindowInsets windowInsets8 = windowInsets7;
                                SubcomposeMeasureScope subcomposeMeasureScope4 = subcomposeMeasureScope;
                                int i33 = iM5690getMaxHeightimpl;
                                int i34 = i28;
                                int size8 = list4.size();
                                for (int i35 = 0; i35 < size8; i35++) {
                                    Placeable.PlacementScope.place$default(placementScope, list4.get(i35), ((i31 - i32) / 2) + windowInsets8.getLeft(subcomposeMeasureScope4, subcomposeMeasureScope4.getLayoutDirection()), i33 - i34, 0.0f, 4, null);
                                }
                                List<Placeable> list5 = arrayList8;
                                int i36 = iM5690getMaxHeightimpl;
                                Integer num4 = num2;
                                int size9 = list5.size();
                                for (int i37 = 0; i37 < size9; i37++) {
                                    Placeable.PlacementScope.place$default(placementScope, list5.get(i37), 0, i36 - (num4 != null ? num4.intValue() : 0), 0.0f, 4, null);
                                }
                                FabPlacement fabPlacement3 = fabPlacement2;
                                if (fabPlacement3 != null) {
                                    List<Placeable> list6 = arrayList6;
                                    int i38 = iM5690getMaxHeightimpl;
                                    Integer num5 = num3;
                                    int size10 = list6.size();
                                    for (int i39 = 0; i39 < size10; i39++) {
                                        Placeable placeable5 = list6.get(i39);
                                        int left = fabPlacement3.getLeft();
                                        Intrinsics.checkNotNull(num5);
                                        Placeable.PlacementScope.place$default(placementScope, placeable5, left, i38 - num5.intValue(), 0.0f, 4, null);
                                    }
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
                i4 = 0;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) obj, composerStartRestartGroup, i4, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$2
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

                public final void invoke(Composer composer2, int i5) {
                    ScaffoldKt.m1787ScaffoldLayoutWithMeasureFixFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: LegacyScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m1784LegacyScaffoldLayoutFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        int i4;
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(1307205667);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LegacyScaffoldLayout)P(4:c#material3.FabPosition,6,1,5,3,2)348@14737L6941,348@14720L6958:Scaffold.kt#uh7d8r");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if ((i3 & 599187) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1307205667, i3, -1, "androidx.compose.material3.LegacyScaffoldLayout (Scaffold.kt:347)");
            }
            composerStartRestartGroup.startReplaceableGroup(1646578117);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Scaffold.kt#9igjgp");
            boolean z = ((i3 & 112) == 32) | ((i3 & 7168) == 2048) | ((458752 & i3) == 131072) | ((57344 & i3) == 16384) | ((i3 & 14) == 4) | ((3670016 & i3) == 1048576) | ((i3 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i4 = 0;
                obj = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m1791invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m1791invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, long j) {
                        final int iM5691getMaxWidthimpl = Constraints.m5691getMaxWidthimpl(j);
                        final int iM5690getMaxHeightimpl = Constraints.m5690getMaxHeightimpl(j);
                        final long jM5682copyZbe2FdA$default = Constraints.m5682copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        final Function2<Composer, Integer, Unit> function25 = function2;
                        final Function2<Composer, Integer, Unit> function26 = function22;
                        final Function2<Composer, Integer, Unit> function27 = function23;
                        final int i5 = i;
                        final WindowInsets windowInsets2 = windowInsets;
                        final Function2<Composer, Integer, Unit> function28 = function24;
                        final Function3<PaddingValues, Composer, Integer, Unit> function32 = function3;
                        return MeasureScope.layout$default(subcomposeMeasureScope, iM5691getMaxWidthimpl, iM5690getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                Object obj2;
                                Object obj3;
                                Object obj4;
                                final FabPlacement fabPlacement;
                                int i6;
                                Object obj5;
                                Integer numValueOf;
                                int i7;
                                int iIntValue;
                                int iIntValue2;
                                Object obj6;
                                Object obj7;
                                int i8;
                                int i9;
                                int i10;
                                List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, function25);
                                long j2 = jM5682copyZbe2FdA$default;
                                ArrayList arrayList = new ArrayList(listSubcompose.size());
                                int size = listSubcompose.size();
                                for (int i11 = 0; i11 < size; i11++) {
                                    arrayList.add(listSubcompose.get(i11).mo4684measureBRTryo0(j2));
                                }
                                final ArrayList arrayList2 = arrayList;
                                if (arrayList2.isEmpty()) {
                                    obj2 = null;
                                } else {
                                    obj2 = arrayList2.get(0);
                                    int height = ((Placeable) obj2).getHeight();
                                    int lastIndex = CollectionsKt.getLastIndex(arrayList2);
                                    if (1 <= lastIndex) {
                                        int i12 = 1;
                                        while (true) {
                                            Object obj8 = arrayList2.get(i12);
                                            int height2 = ((Placeable) obj8).getHeight();
                                            if (height < height2) {
                                                obj2 = obj8;
                                                height = height2;
                                            }
                                            if (i12 == lastIndex) {
                                                break;
                                            } else {
                                                i12++;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable = (Placeable) obj2;
                                final int height3 = placeable != null ? placeable.getHeight() : 0;
                                List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, function26);
                                WindowInsets windowInsets3 = windowInsets2;
                                SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                long j3 = jM5682copyZbe2FdA$default;
                                ArrayList arrayList3 = new ArrayList(listSubcompose2.size());
                                int size2 = listSubcompose2.size();
                                for (int i13 = 0; i13 < size2; i13++) {
                                    SubcomposeMeasureScope subcomposeMeasureScope3 = subcomposeMeasureScope2;
                                    arrayList3.add(listSubcompose2.get(i13).mo4684measureBRTryo0(ConstraintsKt.m5707offsetNN6EwU(j3, (-windowInsets3.getLeft(subcomposeMeasureScope3, subcomposeMeasureScope2.getLayoutDirection())) - windowInsets3.getRight(subcomposeMeasureScope3, subcomposeMeasureScope2.getLayoutDirection()), -windowInsets3.getBottom(subcomposeMeasureScope3))));
                                }
                                ArrayList arrayList4 = arrayList3;
                                if (arrayList4.isEmpty()) {
                                    obj3 = null;
                                } else {
                                    obj3 = arrayList4.get(0);
                                    int height4 = ((Placeable) obj3).getHeight();
                                    int lastIndex2 = CollectionsKt.getLastIndex(arrayList4);
                                    if (1 <= lastIndex2) {
                                        int i14 = 1;
                                        while (true) {
                                            Object obj9 = arrayList4.get(i14);
                                            int height5 = ((Placeable) obj9).getHeight();
                                            if (height4 < height5) {
                                                obj3 = obj9;
                                                height4 = height5;
                                            }
                                            if (i14 == lastIndex2) {
                                                break;
                                            } else {
                                                i14++;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable2 = (Placeable) obj3;
                                int height6 = placeable2 != null ? placeable2.getHeight() : 0;
                                if (arrayList4.isEmpty()) {
                                    obj4 = null;
                                } else {
                                    obj4 = arrayList4.get(0);
                                    int width = ((Placeable) obj4).getWidth();
                                    int lastIndex3 = CollectionsKt.getLastIndex(arrayList4);
                                    if (1 <= lastIndex3) {
                                        int i15 = 1;
                                        while (true) {
                                            Object obj10 = arrayList4.get(i15);
                                            int width2 = ((Placeable) obj10).getWidth();
                                            if (width < width2) {
                                                obj4 = obj10;
                                                width = width2;
                                            }
                                            if (i15 == lastIndex3) {
                                                break;
                                            } else {
                                                i15++;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable3 = (Placeable) obj4;
                                int width3 = placeable3 != null ? placeable3.getWidth() : 0;
                                List<Measurable> listSubcompose3 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, function27);
                                WindowInsets windowInsets4 = windowInsets2;
                                SubcomposeMeasureScope subcomposeMeasureScope4 = subcomposeMeasureScope;
                                long j4 = jM5682copyZbe2FdA$default;
                                ArrayList arrayList5 = new ArrayList(listSubcompose3.size());
                                int size3 = listSubcompose3.size();
                                int i16 = 0;
                                while (i16 < size3) {
                                    Measurable measurable = listSubcompose3.get(i16);
                                    SubcomposeMeasureScope subcomposeMeasureScope5 = subcomposeMeasureScope4;
                                    List<Measurable> list = listSubcompose3;
                                    int i17 = size3;
                                    int right = (-windowInsets4.getLeft(subcomposeMeasureScope5, subcomposeMeasureScope4.getLayoutDirection())) - windowInsets4.getRight(subcomposeMeasureScope5, subcomposeMeasureScope4.getLayoutDirection());
                                    int i18 = -windowInsets4.getBottom(subcomposeMeasureScope5);
                                    WindowInsets windowInsets5 = windowInsets4;
                                    Placeable placeableMo4684measureBRTryo0 = measurable.mo4684measureBRTryo0(ConstraintsKt.m5707offsetNN6EwU(j4, right, i18));
                                    if (!((placeableMo4684measureBRTryo0.getHeight() == 0 || placeableMo4684measureBRTryo0.getWidth() == 0) ? false : true)) {
                                        placeableMo4684measureBRTryo0 = null;
                                    }
                                    if (placeableMo4684measureBRTryo0 != null) {
                                        arrayList5.add(placeableMo4684measureBRTryo0);
                                    }
                                    i16++;
                                    windowInsets4 = windowInsets5;
                                    listSubcompose3 = list;
                                    size3 = i17;
                                }
                                ArrayList arrayList6 = arrayList5;
                                if (!arrayList6.isEmpty()) {
                                    if (arrayList6.isEmpty()) {
                                        obj6 = null;
                                    } else {
                                        obj6 = arrayList6.get(0);
                                        int width4 = ((Placeable) obj6).getWidth();
                                        int lastIndex4 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex4) {
                                            int i19 = 1;
                                            while (true) {
                                                Object obj11 = arrayList6.get(i19);
                                                int width5 = ((Placeable) obj11).getWidth();
                                                if (width4 < width5) {
                                                    obj6 = obj11;
                                                    width4 = width5;
                                                }
                                                if (i19 == lastIndex4) {
                                                    break;
                                                } else {
                                                    i19++;
                                                }
                                            }
                                        }
                                    }
                                    Intrinsics.checkNotNull(obj6);
                                    int width6 = ((Placeable) obj6).getWidth();
                                    if (arrayList6.isEmpty()) {
                                        obj7 = null;
                                    } else {
                                        obj7 = arrayList6.get(0);
                                        int height7 = ((Placeable) obj7).getHeight();
                                        int lastIndex5 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex5) {
                                            int i20 = 1;
                                            while (true) {
                                                Object obj12 = arrayList6.get(i20);
                                                int height8 = ((Placeable) obj12).getHeight();
                                                if (height7 < height8) {
                                                    obj7 = obj12;
                                                    height7 = height8;
                                                }
                                                if (i20 == lastIndex5) {
                                                    break;
                                                } else {
                                                    i20++;
                                                }
                                            }
                                        }
                                    }
                                    Intrinsics.checkNotNull(obj7);
                                    int height9 = ((Placeable) obj7).getHeight();
                                    int i21 = i5;
                                    if (FabPosition.m1556equalsimpl0(i21, FabPosition.INSTANCE.m1563getStartERTFSPs())) {
                                        if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                            i8 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                            fabPlacement = new FabPlacement(i8, width6, height9);
                                        } else {
                                            i9 = iM5691getMaxWidthimpl;
                                            i10 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                            i8 = (i9 - i10) - width6;
                                            fabPlacement = new FabPlacement(i8, width6, height9);
                                        }
                                    } else {
                                        if (FabPosition.m1556equalsimpl0(i21, FabPosition.INSTANCE.m1561getEndERTFSPs())) {
                                            if (subcomposeMeasureScope.getLayoutDirection() != LayoutDirection.Ltr) {
                                                i8 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                            } else {
                                                i9 = iM5691getMaxWidthimpl;
                                                i10 = subcomposeMeasureScope.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                                i8 = (i9 - i10) - width6;
                                            }
                                        } else {
                                            i8 = (iM5691getMaxWidthimpl - width6) / 2;
                                        }
                                        fabPlacement = new FabPlacement(i8, width6, height9);
                                    }
                                } else {
                                    fabPlacement = null;
                                }
                                SubcomposeMeasureScope subcomposeMeasureScope6 = subcomposeMeasureScope;
                                ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                                final Function2<Composer, Integer, Unit> function29 = function28;
                                List<Measurable> listSubcompose4 = subcomposeMeasureScope6.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-791102355, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$1$1$1$bottomBarPlaceables$1
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

                                    public final void invoke(Composer composer2, int i22) {
                                        ComposerKt.sourceInformation(composer2, "C429@18457L144:Scaffold.kt#uh7d8r");
                                        if ((i22 & 3) != 2 || !composer2.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-791102355, i22, -1, "androidx.compose.material3.LegacyScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:429)");
                                            }
                                            CompositionLocalKt.CompositionLocalProvider(ScaffoldKt.getLocalFabPlacement().provides(fabPlacement), function29, composer2, ProvidedValue.$stable | 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer2.skipToGroupEnd();
                                    }
                                }));
                                long j5 = jM5682copyZbe2FdA$default;
                                ArrayList arrayList7 = new ArrayList(listSubcompose4.size());
                                int size4 = listSubcompose4.size();
                                for (int i22 = 0; i22 < size4; i22++) {
                                    arrayList7.add(listSubcompose4.get(i22).mo4684measureBRTryo0(j5));
                                }
                                final ArrayList arrayList8 = arrayList7;
                                if (arrayList8.isEmpty()) {
                                    obj5 = null;
                                    i6 = 0;
                                } else {
                                    i6 = 0;
                                    obj5 = arrayList8.get(0);
                                    int height10 = ((Placeable) obj5).getHeight();
                                    int lastIndex6 = CollectionsKt.getLastIndex(arrayList8);
                                    if (1 <= lastIndex6) {
                                        int i23 = height10;
                                        Object obj13 = obj5;
                                        int i24 = 1;
                                        while (true) {
                                            Object obj14 = arrayList8.get(i24);
                                            int height11 = ((Placeable) obj14).getHeight();
                                            if (i23 < height11) {
                                                obj13 = obj14;
                                                i23 = height11;
                                            }
                                            if (i24 == lastIndex6) {
                                                break;
                                            } else {
                                                i24++;
                                            }
                                        }
                                        obj5 = obj13;
                                    }
                                }
                                Placeable placeable4 = (Placeable) obj5;
                                Integer numValueOf2 = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
                                if (fabPlacement != null) {
                                    SubcomposeMeasureScope subcomposeMeasureScope7 = subcomposeMeasureScope;
                                    WindowInsets windowInsets6 = windowInsets2;
                                    if (numValueOf2 != null) {
                                        iIntValue2 = numValueOf2.intValue() + fabPlacement.getHeight() + subcomposeMeasureScope7.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    } else {
                                        iIntValue2 = fabPlacement.getHeight() + subcomposeMeasureScope7.mo310roundToPx0680j_4(ScaffoldKt.FabSpacing) + windowInsets6.getBottom(subcomposeMeasureScope7);
                                    }
                                    numValueOf = Integer.valueOf(iIntValue2);
                                } else {
                                    numValueOf = null;
                                }
                                if (height6 != 0) {
                                    if (numValueOf != null) {
                                        iIntValue = numValueOf.intValue();
                                    } else {
                                        iIntValue = numValueOf2 != null ? numValueOf2.intValue() : windowInsets2.getBottom(subcomposeMeasureScope);
                                    }
                                    i7 = height6 + iIntValue;
                                } else {
                                    i7 = i6;
                                }
                                SubcomposeMeasureScope subcomposeMeasureScope8 = subcomposeMeasureScope;
                                ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                                final WindowInsets windowInsets7 = windowInsets2;
                                final SubcomposeMeasureScope subcomposeMeasureScope9 = subcomposeMeasureScope;
                                final Function3<PaddingValues, Composer, Integer, Unit> function33 = function32;
                                FabPlacement fabPlacement2 = fabPlacement;
                                ArrayList arrayList9 = arrayList8;
                                final Integer num = numValueOf2;
                                int i25 = i6;
                                List<Measurable> listSubcompose5 = subcomposeMeasureScope8.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(495329982, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$1$1$1$bodyContentPlaceables$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                        invoke(composer2, num2.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i26) {
                                        float top;
                                        float bottom;
                                        Integer num2;
                                        ComposerKt.sourceInformation(composer2, "C473@20504L21:Scaffold.kt#uh7d8r");
                                        if ((i26 & 3) != 2 || !composer2.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(495329982, i26, -1, "androidx.compose.material3.LegacyScaffoldLayout.<anonymous>.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:456)");
                                            }
                                            PaddingValues paddingValuesAsPaddingValues = WindowInsetsKt.asPaddingValues(windowInsets7, subcomposeMeasureScope9);
                                            if (arrayList2.isEmpty()) {
                                                top = paddingValuesAsPaddingValues.getTop();
                                            } else {
                                                top = subcomposeMeasureScope9.mo313toDpu2uoSUM(height3);
                                            }
                                            if (arrayList8.isEmpty() || (num2 = num) == null) {
                                                bottom = paddingValuesAsPaddingValues.getBottom();
                                            } else {
                                                bottom = subcomposeMeasureScope9.mo313toDpu2uoSUM(num2.intValue());
                                            }
                                            function33.invoke(PaddingKt.m561PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope9.getLayoutDirection()), top, PaddingKt.calculateEndPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope9.getLayoutDirection()), bottom), composer2, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer2.skipToGroupEnd();
                                    }
                                }));
                                long j6 = jM5682copyZbe2FdA$default;
                                ArrayList arrayList10 = new ArrayList(listSubcompose5.size());
                                int size5 = listSubcompose5.size();
                                for (int i26 = i25; i26 < size5; i26++) {
                                    arrayList10.add(listSubcompose5.get(i26).mo4684measureBRTryo0(j6));
                                }
                                ArrayList arrayList11 = arrayList10;
                                int size6 = arrayList11.size();
                                for (int i27 = i25; i27 < size6; i27++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList11.get(i27), 0, 0, 0.0f, 4, null);
                                }
                                int size7 = arrayList2.size();
                                for (int i28 = i25; i28 < size7; i28++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList2.get(i28), 0, 0, 0.0f, 4, null);
                                }
                                int i29 = iM5691getMaxWidthimpl;
                                WindowInsets windowInsets8 = windowInsets2;
                                SubcomposeMeasureScope subcomposeMeasureScope10 = subcomposeMeasureScope;
                                int i30 = iM5690getMaxHeightimpl;
                                int size8 = arrayList4.size();
                                for (int i31 = i25; i31 < size8; i31++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList4.get(i31), ((i29 - width3) / 2) + windowInsets8.getLeft(subcomposeMeasureScope10, subcomposeMeasureScope10.getLayoutDirection()), i30 - i7, 0.0f, 4, null);
                                }
                                int i32 = iM5690getMaxHeightimpl;
                                int size9 = arrayList9.size();
                                int i33 = i25;
                                while (i33 < size9) {
                                    ArrayList arrayList12 = arrayList9;
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList12.get(i33), 0, i32 - (numValueOf2 != null ? numValueOf2.intValue() : i25), 0.0f, 4, null);
                                    i33++;
                                    arrayList9 = arrayList12;
                                }
                                if (fabPlacement2 != null) {
                                    int i34 = iM5690getMaxHeightimpl;
                                    int size10 = arrayList6.size();
                                    for (int i35 = i25; i35 < size10; i35++) {
                                        Placeable placeable5 = (Placeable) arrayList6.get(i35);
                                        int left = fabPlacement2.getLeft();
                                        Intrinsics.checkNotNull(numValueOf);
                                        Placeable.PlacementScope.place$default(placementScope, placeable5, left, i34 - numValueOf.intValue(), 0.0f, 4, null);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    Unit unit2 = Unit.INSTANCE;
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
                i4 = 0;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) obj, composerStartRestartGroup, i4, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$2
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

                public final void invoke(Composer composer2, int i5) {
                    ScaffoldKt.m1784LegacyScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getScaffoldSubcomposeInMeasureFix() {
        return ((Boolean) ScaffoldSubcomposeInMeasureFix$delegate.getValue()).booleanValue();
    }

    public static final void setScaffoldSubcomposeInMeasureFix(boolean z) {
        ScaffoldSubcomposeInMeasureFix$delegate.setValue(Boolean.valueOf(z));
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
