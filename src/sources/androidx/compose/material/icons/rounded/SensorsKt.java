package androidx.compose.material.icons.rounded;

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

/* compiled from: Sensors.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensors", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sensors", "Landroidx/compose/material/icons/Icons$Rounded;", "getSensors", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SensorsKt {
    private static ImageVector _sensors;

    public static final ImageVector getSensors(Icons.Rounded rounded) {
        ImageVector imageVector = _sensors;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Sensors", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.54f, 8.54f);
        pathBuilder.curveToRelative(0.35f, 0.35f, 0.37f, 0.88f, 0.1f, 1.29f);
        pathBuilder.curveTo(8.24f, 10.45f, 8.0f, 11.2f, 8.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.8f, 0.24f, 1.55f, 0.64f, 2.17f);
        pathBuilder.curveToRelative(0.27f, 0.41f, 0.24f, 0.95f, -0.11f, 1.29f);
        pathBuilder.curveToRelative(-0.43f, 0.43f, -1.17f, 0.4f, -1.51f, -0.11f);
        pathBuilder.curveTo(6.38f, 14.4f, 6.0f, 13.24f, 6.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -1.21f, 0.36f, -2.33f, 0.97f, -3.28f);
        pathBuilder.curveTo(7.33f, 8.18f, 8.08f, 8.08f, 8.54f, 8.54f);
        pathBuilder.close();
        pathBuilder.moveTo(15.46f, 15.46f);
        pathBuilder.curveToRelative(0.43f, 0.43f, 1.17f, 0.4f, 1.51f, -0.11f);
        pathBuilder.curveTo(17.62f, 14.4f, 18.0f, 13.24f, 18.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -1.24f, -0.38f, -2.4f, -1.03f, -3.36f);
        pathBuilder.curveToRelative(-0.34f, -0.5f, -1.08f, -0.54f, -1.51f, -0.11f);
        pathBuilder.curveToRelative(-0.35f, 0.35f, -0.37f, 0.88f, -0.11f, 1.29f);
        pathBuilder.curveTo(15.76f, 10.45f, 16.0f, 11.2f, 16.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.8f, -0.24f, 1.55f, -0.64f, 2.17f);
        pathBuilder.curveTo(15.09f, 14.58f, 15.12f, 15.12f, 15.46f, 15.46f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 10.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(13.1f, 10.0f, 12.0f, 10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.32f, 18.32f);
        pathBuilder.curveToRelative(0.42f, 0.42f, 1.12f, 0.39f, 1.5f, -0.08f);
        pathBuilder.curveTo(21.18f, 16.53f, 22.0f, 14.36f, 22.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(-0.82f, -4.53f, -2.18f, -6.24f);
        pathBuilder.curveToRelative(-0.37f, -0.47f, -1.07f, -0.5f, -1.5f, -0.08f);
        pathBuilder.curveToRelative(-0.36f, 0.36f, -0.4f, 0.92f, -0.08f, 1.32f);
        pathBuilder.curveToRelative(1.1f, 1.37f, 1.76f, 3.11f, 1.76f, 5.0f);
        pathBuilder.reflectiveCurveToRelative(-0.66f, 3.63f, -1.76f, 5.0f);
        pathBuilder.curveTo(17.92f, 17.39f, 17.96f, 17.96f, 18.32f, 18.32f);
        pathBuilder.close();
        pathBuilder.moveTo(5.68f, 5.68f);
        pathBuilder.curveToRelative(-0.42f, -0.42f, -1.12f, -0.39f, -1.5f, 0.08f);
        pathBuilder.curveTo(2.82f, 7.47f, 2.0f, 9.64f, 2.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.82f, 4.53f, 2.18f, 6.24f);
        pathBuilder.curveToRelative(0.37f, 0.47f, 1.07f, 0.5f, 1.5f, 0.08f);
        pathBuilder.curveToRelative(0.36f, -0.36f, 0.4f, -0.92f, 0.08f, -1.32f);
        pathBuilder.curveTo(4.66f, 15.63f, 4.0f, 13.89f, 4.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.66f, -3.63f, 1.76f, -5.0f);
        pathBuilder.curveTo(6.08f, 6.61f, 6.04f, 6.04f, 5.68f, 5.68f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensors = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
