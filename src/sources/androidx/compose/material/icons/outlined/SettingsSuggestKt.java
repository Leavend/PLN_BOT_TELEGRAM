package androidx.compose.material.icons.outlined;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsSuggest.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsSuggest", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsSuggest", "Landroidx/compose/material/icons/Icons$Outlined;", "getSettingsSuggest", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SettingsSuggestKt {
    private static ImageVector _settingsSuggest;

    public static final ImageVector getSettingsSuggest(Icons.Outlined outlined) {
        ImageVector imageVector = _settingsSuggest;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SettingsSuggest", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(10.0f, 13.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilder.reflectiveCurveTo(9.45f, 13.0f, 10.0f, 13.0f);
        pathBuilder.moveTo(10.0f, 11.0f);
        pathBuilder.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilder.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilder.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilder.reflectiveCurveTo(11.66f, 11.0f, 10.0f, 11.0f);
        pathBuilder.lineTo(10.0f, 11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.5f, 9.0f);
        pathBuilder.lineToRelative(1.09f, -2.41f);
        pathBuilder.lineTo(22.0f, 5.5f);
        pathBuilder.lineToRelative(-2.41f, -1.09f);
        pathBuilder.lineTo(18.5f, 2.0f);
        pathBuilder.lineToRelative(-1.09f, 2.41f);
        pathBuilder.lineTo(15.0f, 5.5f);
        pathBuilder.lineToRelative(2.41f, 1.09f);
        pathBuilder.lineTo(18.5f, 9.0f);
        pathBuilder.close();
        pathBuilder.moveTo(21.28f, 12.72f);
        pathBuilder.lineTo(20.5f, 11.0f);
        pathBuilder.lineToRelative(-0.78f, 1.72f);
        pathBuilder.lineTo(18.0f, 13.5f);
        pathBuilder.lineToRelative(1.72f, 0.78f);
        pathBuilder.lineTo(20.5f, 16.0f);
        pathBuilder.lineToRelative(0.78f, -1.72f);
        pathBuilder.lineTo(23.0f, 13.5f);
        pathBuilder.lineTo(21.28f, 12.72f);
        pathBuilder.close();
        pathBuilder.moveTo(16.25f, 14.0f);
        pathBuilder.curveToRelative(0.0f, -0.12f, 0.0f, -0.25f, -0.01f, -0.37f);
        pathBuilder.lineToRelative(1.94f, -1.47f);
        pathBuilder.lineToRelative(-2.5f, -4.33f);
        pathBuilder.lineToRelative(-2.24f, 0.94f);
        pathBuilder.curveToRelative(-0.2f, -0.13f, -0.42f, -0.26f, -0.64f, -0.37f);
        pathBuilder.lineTo(12.5f, 6.0f);
        pathBuilder.horizontalLineToRelative(-5.0f);
        pathBuilder.lineTo(7.2f, 8.41f);
        pathBuilder.curveTo(6.98f, 8.52f, 6.77f, 8.65f, 6.56f, 8.78f);
        pathBuilder.lineTo(4.32f, 7.83f);
        pathBuilder.lineToRelative(-2.5f, 4.33f);
        pathBuilder.lineToRelative(1.94f, 1.47f);
        pathBuilder.curveTo(3.75f, 13.75f, 3.75f, 13.88f, 3.75f, 14.0f);
        pathBuilder.reflectiveCurveToRelative(0.0f, 0.25f, 0.01f, 0.37f);
        pathBuilder.lineToRelative(-1.94f, 1.47f);
        pathBuilder.lineToRelative(2.5f, 4.33f);
        pathBuilder.lineToRelative(2.24f, -0.94f);
        pathBuilder.curveToRelative(0.2f, 0.13f, 0.42f, 0.26f, 0.64f, 0.37f);
        pathBuilder.lineTo(7.5f, 22.0f);
        pathBuilder.horizontalLineToRelative(5.0f);
        pathBuilder.lineToRelative(0.3f, -2.41f);
        pathBuilder.curveToRelative(0.22f, -0.11f, 0.43f, -0.23f, 0.64f, -0.37f);
        pathBuilder.lineToRelative(2.24f, 0.94f);
        pathBuilder.lineToRelative(2.5f, -4.33f);
        pathBuilder.lineToRelative(-1.94f, -1.47f);
        pathBuilder.curveTo(16.25f, 14.25f, 16.25f, 14.12f, 16.25f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.83f, 17.64f);
        pathBuilder.lineToRelative(-1.73f, -0.73f);
        pathBuilder.curveToRelative(-0.56f, 0.6f, -1.3f, 1.04f, -2.13f, 1.23f);
        pathBuilder.lineTo(10.73f, 20.0f);
        pathBuilder.horizontalLineTo(9.27f);
        pathBuilder.lineToRelative(-0.23f, -1.86f);
        pathBuilder.curveToRelative(-0.83f, -0.19f, -1.57f, -0.63f, -2.13f, -1.23f);
        pathBuilder.lineToRelative(-1.73f, 0.73f);
        pathBuilder.lineToRelative(-0.73f, -1.27f);
        pathBuilder.lineToRelative(1.49f, -1.13f);
        pathBuilder.curveToRelative(-0.12f, -0.39f, -0.18f, -0.8f, -0.18f, -1.23f);
        pathBuilder.curveToRelative(0.0f, -0.43f, 0.06f, -0.84f, 0.18f, -1.23f);
        pathBuilder.lineToRelative(-1.49f, -1.13f);
        pathBuilder.lineToRelative(0.73f, -1.27f);
        pathBuilder.lineToRelative(1.73f, 0.73f);
        pathBuilder.curveToRelative(0.56f, -0.6f, 1.3f, -1.04f, 2.13f, -1.23f);
        pathBuilder.lineTo(9.27f, 8.0f);
        pathBuilder.horizontalLineToRelative(1.47f);
        pathBuilder.lineToRelative(0.23f, 1.86f);
        pathBuilder.curveToRelative(0.83f, 0.19f, 1.57f, 0.63f, 2.13f, 1.23f);
        pathBuilder.lineToRelative(1.73f, -0.73f);
        pathBuilder.lineToRelative(0.73f, 1.27f);
        pathBuilder.lineToRelative(-1.49f, 1.13f);
        pathBuilder.curveToRelative(0.12f, 0.39f, 0.18f, 0.8f, 0.18f, 1.23f);
        pathBuilder.curveToRelative(0.0f, 0.43f, -0.06f, 0.84f, -0.18f, 1.23f);
        pathBuilder.lineToRelative(1.49f, 1.13f);
        pathBuilder.lineTo(14.83f, 17.64f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsSuggest = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
