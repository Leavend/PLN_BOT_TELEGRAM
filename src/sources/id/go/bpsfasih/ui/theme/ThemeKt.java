package id.go.bpsfasih.ui.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"DarkColorScheme", "Landroidx/compose/material3/ColorScheme;", "LightColorScheme", "FasihTheme", "", "darkTheme", "", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ThemeKt {
    private static final ColorScheme DarkColorScheme = ColorSchemeKt.m1399darkColorSchemeCXl9yA$default(ColorsKt.getColorPrimary(), ColorsKt.getColorWhiteBackground(), 0, 0, 0, ColorsKt.getColorCyan(), ColorsKt.getColorWhiteBackground(), 0, 0, ColorsKt.getBlue300(), ColorsKt.getColorWhiteBackground(), 0, 0, ColorsKt.getGrey900(), ColorsKt.getColorWhiteBackground(), ColorsKt.getGrey800(), ColorsKt.getColorWhiteBackground(), 0, 0, 0, 0, 0, ColorsKt.getRedReject(), ColorsKt.getColorWhiteBackground(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -12707428, 15, null);
    private static final ColorScheme LightColorScheme = ColorSchemeKt.m1403lightColorSchemeCXl9yA$default(ColorsKt.getColorPrimary(), ColorsKt.getColorWhiteBackground(), 0, 0, 0, ColorsKt.getColorCyan(), ColorsKt.getColorWhiteBackground(), 0, 0, ColorsKt.getBlue300(), ColorsKt.getColorWhiteBackground(), 0, 0, ColorsKt.getColorWhiteBackground(), ColorsKt.getGrey800(), ColorsKt.getColorWhiteBackground(), ColorsKt.getGrey800(), ColorsKt.getGrey100(), 0, 0, 0, 0, ColorsKt.getRedReject(), ColorsKt.getColorWhiteBackground(), 0, 0, ColorsKt.getGrey300(), 0, 0, 0, 0, 0, 0, 0, 0, 0, -79947364, 15, null);

    /* JADX WARN: Removed duplicated region for block: B:42:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FasihTheme(final boolean r8, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r9, androidx.compose.runtime.Composer r10, final int r11, final int r12) {
        /*
            java.lang.String r0 = "content"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = -52970983(0xfffffffffcd7ba19, float:-8.9609467E36)
            androidx.compose.runtime.Composer r10 = r10.startRestartGroup(r0)
            java.lang.String r1 = "C(FasihTheme)P(1)"
            androidx.compose.runtime.ComposerKt.sourceInformation(r10, r1)
            r1 = r11 & 14
            if (r1 != 0) goto L24
            r1 = r12 & 1
            if (r1 != 0) goto L21
            boolean r1 = r10.changed(r8)
            if (r1 == 0) goto L21
            r1 = 4
            goto L22
        L21:
            r1 = 2
        L22:
            r1 = r1 | r11
            goto L25
        L24:
            r1 = r11
        L25:
            r2 = r12 & 2
            if (r2 == 0) goto L2c
            r1 = r1 | 48
            goto L3c
        L2c:
            r2 = r11 & 112(0x70, float:1.57E-43)
            if (r2 != 0) goto L3c
            boolean r2 = r10.changedInstance(r9)
            if (r2 == 0) goto L39
            r2 = 32
            goto L3b
        L39:
            r2 = 16
        L3b:
            r1 = r1 | r2
        L3c:
            r2 = r1 & 91
            r3 = 18
            if (r2 != r3) goto L4d
            boolean r2 = r10.getSkipping()
            if (r2 != 0) goto L49
            goto L4d
        L49:
            r10.skipToGroupEnd()
            goto L9a
        L4d:
            r10.startDefaults()
            r2 = r11 & 1
            if (r2 == 0) goto L63
            boolean r2 = r10.getDefaultsInvalid()
            if (r2 == 0) goto L5b
            goto L63
        L5b:
            r10.skipToGroupEnd()
            r2 = r12 & 1
            if (r2 == 0) goto L6e
            goto L6c
        L63:
            r2 = r12 & 1
            if (r2 == 0) goto L6e
            r8 = 0
            boolean r8 = androidx.compose.foundation.DarkThemeKt.isSystemInDarkTheme(r10, r8)
        L6c:
            r1 = r1 & (-15)
        L6e:
            r10.endDefaults()
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L7d
            r2 = -1
            java.lang.String r3 = "id.go.bpsfasih.ui.theme.FasihTheme (Theme.kt:43)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r1, r2, r3)
        L7d:
            if (r8 == 0) goto L82
            androidx.compose.material3.ColorScheme r0 = id.go.bpsfasih.ui.theme.ThemeKt.DarkColorScheme
            goto L84
        L82:
            androidx.compose.material3.ColorScheme r0 = id.go.bpsfasih.ui.theme.ThemeKt.LightColorScheme
        L84:
            r2 = 0
            r3 = 0
            int r1 = r1 << 6
            r6 = r1 & 7168(0x1c00, float:1.0045E-41)
            r7 = 6
            r1 = r0
            r4 = r9
            r5 = r10
            androidx.compose.material3.MaterialThemeKt.MaterialTheme(r1, r2, r3, r4, r5, r6, r7)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L9a
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L9a:
            androidx.compose.runtime.ScopeUpdateScope r10 = r10.endRestartGroup()
            if (r10 != 0) goto La1
            goto Lab
        La1:
            id.go.bpsfasih.ui.theme.ThemeKt$FasihTheme$1 r0 = new id.go.bpsfasih.ui.theme.ThemeKt$FasihTheme$1
            r0.<init>()
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r10.updateScope(r0)
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.theme.ThemeKt.FasihTheme(boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
