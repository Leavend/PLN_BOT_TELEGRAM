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

/* compiled from: WavingHand.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wavingHand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WavingHand", "Landroidx/compose/material/icons/Icons$Rounded;", "getWavingHand", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WavingHandKt {
    private static ImageVector _wavingHand;

    public static final ImageVector getWavingHand(Icons.Rounded rounded) {
        ImageVector imageVector = _wavingHand;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WavingHand", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.07f, 9.8f);
        pathBuilder.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(1.07f, 1.07f, 1.16f, 2.75f, 0.28f, 3.92f);
        pathBuilder.curveToRelative(-0.15f, 0.2f, -0.12f, 0.47f, 0.05f, 0.65f);
        pathBuilder.curveToRelative(0.22f, 0.22f, 0.57f, 0.19f, 0.76f, -0.05f);
        pathBuilder.curveToRelative(1.07f, -1.43f, 1.06f, -3.43f, -0.04f, -4.85f);
        pathBuilder.lineToRelative(7.62f, -7.62f);
        pathBuilder.curveToRelative(0.49f, -0.49f, 1.28f, -0.49f, 1.77f, 0.0f);
        pathBuilder.reflectiveCurveToRelative(0.49f, 1.28f, 0.0f, 1.77f);
        pathBuilder.lineToRelative(-5.13f, 5.13f);
        pathBuilder.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilder.reflectiveCurveToRelative(0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilder.lineToRelative(6.54f, -6.54f);
        pathBuilder.curveToRelative(0.49f, -0.49f, 1.28f, -0.49f, 1.77f, 0.0f);
        pathBuilder.reflectiveCurveToRelative(0.49f, 1.28f, 0.0f, 1.77f);
        pathBuilder.lineToRelative(-6.54f, 6.54f);
        pathBuilder.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilder.reflectiveCurveToRelative(0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilder.lineToRelative(5.48f, -5.48f);
        pathBuilder.curveToRelative(0.49f, -0.49f, 1.28f, -0.49f, 1.77f, 0.0f);
        pathBuilder.reflectiveCurveToRelative(0.49f, 1.28f, 0.0f, 1.77f);
        pathBuilder.lineToRelative(-6.19f, 6.19f);
        pathBuilder.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilder.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilder.lineToRelative(4.07f, -4.07f);
        pathBuilder.curveToRelative(0.49f, -0.49f, 1.28f, -0.49f, 1.77f, 0.0f);
        pathBuilder.curveToRelative(0.49f, 0.49f, 0.49f, 1.28f, 0.0f, 1.77f);
        pathBuilder.lineToRelative(-7.07f, 7.07f);
        pathBuilder.curveToRelative(-3.22f, 3.22f, -8.45f, 3.22f, -11.67f, 0.0f);
        pathBuilder.reflectiveCurveToRelative(-3.22f, -8.45f, 0.0f, -11.67f);
        pathBuilder.lineToRelative(4.6f, -4.6f);
        pathBuilder.curveToRelative(0.49f, -0.49f, 1.28f, -0.49f, 1.77f, 0.0f);
        pathBuilder.curveToRelative(0.49f, 0.49f, 0.49f, 1.28f, 0.0f, 1.77f);
        pathBuilder.lineTo(6.07f, 9.8f);
        pathBuilder.close();
        pathBuilder.moveTo(7.0f, 1.8f);
        pathBuilder.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilder.curveToRelative(-0.04f, 0.0f, -0.07f, 0.0f, -0.11f, 0.01f);
        pathBuilder.curveToRelative(-2.63f, 0.38f, -4.7f, 2.46f, -5.08f, 5.08f);
        pathBuilder.curveToRelative(0.0f, 0.03f, -0.01f, 0.07f, -0.01f, 0.11f);
        pathBuilder.curveTo(1.05f, 6.66f, 1.39f, 7.0f, 1.8f, 7.0f);
        pathBuilder.curveToRelative(0.38f, 0.0f, 0.69f, -0.28f, 0.74f, -0.64f);
        pathBuilder.curveToRelative(0.28f, -1.97f, 1.84f, -3.53f, 3.81f, -3.81f);
        pathBuilder.curveTo(6.72f, 2.49f, 7.0f, 2.18f, 7.0f, 1.8f);
        pathBuilder.close();
        pathBuilder.moveTo(17.0f, 22.2f);
        pathBuilder.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilder.curveToRelative(0.04f, 0.0f, 0.07f, 0.0f, 0.11f, -0.01f);
        pathBuilder.curveToRelative(2.63f, -0.38f, 4.7f, -2.46f, 5.08f, -5.08f);
        pathBuilder.curveToRelative(0.0f, -0.03f, 0.01f, -0.07f, 0.01f, -0.11f);
        pathBuilder.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilder.curveToRelative(-0.38f, 0.0f, -0.69f, 0.28f, -0.74f, 0.64f);
        pathBuilder.curveToRelative(-0.28f, 1.97f, -1.84f, 3.53f, -3.81f, 3.81f);
        pathBuilder.curveTo(17.28f, 21.51f, 17.0f, 21.82f, 17.0f, 22.2f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wavingHand = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
