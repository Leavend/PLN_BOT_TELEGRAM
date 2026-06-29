package androidx.compose.material.icons.filled;

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

/* compiled from: Surfing.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_surfing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Surfing", "Landroidx/compose/material/icons/Icons$Filled;", "getSurfing", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SurfingKt {
    private static ImageVector _surfing;

    public static final ImageVector getSurfing(Icons.Filled filled) {
        ImageVector imageVector = _surfing;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Surfing", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.0f, 23.0f);
        pathBuilder.curveToRelative(-1.03f, 0.0f, -2.06f, -0.25f, -3.0f, -0.75f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveToRelative(-1.89f, 1.0f, -4.11f, 1.0f, -6.0f, 0.0f);
        pathBuilder.curveToRelative(-1.89f, 1.0f, -4.11f, 1.0f, -6.0f, 0.0f);
        pathBuilder.curveTo(5.05f, 22.75f, 4.03f, 23.0f, 3.0f, 23.0f);
        pathBuilder.horizontalLineTo(2.0f);
        pathBuilder.lineToRelative(0.0f, -2.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.curveToRelative(1.04f, 0.0f, 2.08f, -0.35f, 3.0f, -1.0f);
        pathBuilder.curveToRelative(1.83f, 1.3f, 4.17f, 1.3f, 6.0f, 0.0f);
        pathBuilder.curveToRelative(1.83f, 1.3f, 4.17f, 1.3f, 6.0f, 0.0f);
        pathBuilder.curveToRelative(0.91f, 0.65f, 1.96f, 1.0f, 3.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(1.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineTo(21.0f);
        pathBuilder.close();
        pathBuilder.moveTo(17.0f, 1.5f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(18.1f, 1.5f, 17.0f, 1.5f);
        pathBuilder.close();
        pathBuilder.moveTo(14.43f, 8.48f);
        pathBuilder.lineTo(12.18f, 10.0f);
        pathBuilder.lineTo(16.0f, 13.0f);
        pathBuilder.verticalLineToRelative(3.84f);
        pathBuilder.curveToRelative(0.53f, 0.38f, 1.03f, 0.78f, 1.49f, 1.17f);
        pathBuilder.curveTo(16.81f, 18.59f, 15.94f, 19.0f, 15.0f, 19.0f);
        pathBuilder.curveToRelative(-1.2f, 0.0f, -2.27f, -0.66f, -3.0f, -1.5f);
        pathBuilder.curveToRelative(-0.73f, 0.84f, -1.8f, 1.5f, -3.0f, 1.5f);
        pathBuilder.curveToRelative(-0.33f, 0.0f, -0.65f, -0.05f, -0.96f, -0.14f);
        pathBuilder.curveTo(5.19f, 16.9f, 3.0f, 14.72f, 3.0f, 13.28f);
        pathBuilder.curveTo(3.0f, 12.25f, 4.01f, 12.0f, 4.85f, 12.0f);
        pathBuilder.curveToRelative(0.98f, 0.0f, 2.28f, 0.31f, 3.7f, 0.83f);
        pathBuilder.lineToRelative(-0.53f, -3.1f);
        pathBuilder.curveTo(7.91f, 9.06f, 8.2f, 8.35f, 8.8f, 7.94f);
        pathBuilder.lineToRelative(2.15f, -1.45f);
        pathBuilder.lineToRelative(-2.0f, -0.37f);
        pathBuilder.lineTo(6.13f, 8.05f);
        pathBuilder.lineTo(5.0f, 6.4f);
        pathBuilder.lineTo(8.5f, 4.0f);
        pathBuilder.lineToRelative(5.55f, 1.03f);
        pathBuilder.curveToRelative(0.45f, 0.09f, 0.93f, 0.37f, 1.22f, 0.89f);
        pathBuilder.lineToRelative(0.88f, 1.55f);
        pathBuilder.curveTo(17.01f, 8.98f, 18.64f, 10.0f, 20.5f, 10.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.curveTo(17.91f, 12.0f, 15.64f, 10.58f, 14.43f, 8.48f);
        pathBuilder.close();
        pathBuilder.moveTo(10.3f, 11.1f);
        pathBuilder.lineToRelative(0.44f, 2.65f);
        pathBuilder.curveToRelative(0.92f, 0.42f, 2.48f, 1.27f, 3.26f, 1.75f);
        pathBuilder.verticalLineTo(14.0f);
        pathBuilder.lineTo(10.3f, 11.1f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _surfing = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
