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

/* compiled from: Emergency.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Emergency", "Landroidx/compose/material/icons/Icons$Outlined;", "getEmergency", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EmergencyKt {
    private static ImageVector _emergency;

    public static final ImageVector getEmergency(Icons.Outlined outlined) {
        ImageVector imageVector = _emergency;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Emergency", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.29f, 13.9f);
        pathBuilder.lineTo(18.0f, 12.0f);
        pathBuilder.lineToRelative(3.29f, -1.9f);
        pathBuilder.curveToRelative(0.48f, -0.28f, 0.64f, -0.89f, 0.37f, -1.37f);
        pathBuilder.lineToRelative(-2.0f, -3.46f);
        pathBuilder.curveToRelative(-0.28f, -0.48f, -0.89f, -0.64f, -1.37f, -0.37f);
        pathBuilder.lineTo(15.0f, 6.8f);
        pathBuilder.verticalLineTo(3.0f);
        pathBuilder.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.curveTo(9.45f, 2.0f, 9.0f, 2.45f, 9.0f, 3.0f);
        pathBuilder.verticalLineToRelative(3.8f);
        pathBuilder.lineTo(5.71f, 4.9f);
        pathBuilder.curveTo(5.23f, 4.63f, 4.62f, 4.79f, 4.34f, 5.27f);
        pathBuilder.lineToRelative(-2.0f, 3.46f);
        pathBuilder.curveTo(2.06f, 9.21f, 2.23f, 9.82f, 2.71f, 10.1f);
        pathBuilder.lineTo(6.0f, 12.0f);
        pathBuilder.lineToRelative(-3.29f, 1.9f);
        pathBuilder.curveToRelative(-0.48f, 0.28f, -0.64f, 0.89f, -0.37f, 1.37f);
        pathBuilder.lineToRelative(2.0f, 3.46f);
        pathBuilder.curveToRelative(0.28f, 0.48f, 0.89f, 0.64f, 1.37f, 0.37f);
        pathBuilder.lineTo(9.0f, 17.2f);
        pathBuilder.verticalLineTo(21.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(-3.8f);
        pathBuilder.lineToRelative(3.29f, 1.9f);
        pathBuilder.curveToRelative(0.48f, 0.28f, 1.09f, 0.11f, 1.37f, -0.37f);
        pathBuilder.lineToRelative(2.0f, -3.46f);
        pathBuilder.curveTo(21.94f, 14.79f, 21.77f, 14.18f, 21.29f, 13.9f);
        pathBuilder.close();
        pathBuilder.moveTo(18.43f, 16.87f);
        pathBuilder.lineToRelative(-4.68f, -2.7f);
        pathBuilder.curveTo(13.42f, 13.97f, 13.0f, 14.21f, 13.0f, 14.6f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(-5.4f);
        pathBuilder.curveToRelative(0.0f, -0.38f, -0.42f, -0.63f, -0.75f, -0.43f);
        pathBuilder.lineToRelative(-4.68f, 2.7f);
        pathBuilder.lineToRelative(-1.0f, -1.73f);
        pathBuilder.lineToRelative(4.68f, -2.7f);
        pathBuilder.curveToRelative(0.33f, -0.19f, 0.33f, -0.67f, 0.0f, -0.87f);
        pathBuilder.lineToRelative(-4.68f, -2.7f);
        pathBuilder.lineToRelative(1.0f, -1.73f);
        pathBuilder.lineToRelative(4.68f, 2.7f);
        pathBuilder.curveTo(10.58f, 10.03f, 11.0f, 9.79f, 11.0f, 9.4f);
        pathBuilder.verticalLineTo(4.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(5.4f);
        pathBuilder.curveToRelative(0.0f, 0.38f, 0.42f, 0.63f, 0.75f, 0.43f);
        pathBuilder.lineToRelative(4.68f, -2.7f);
        pathBuilder.lineToRelative(1.0f, 1.73f);
        pathBuilder.lineToRelative(-4.68f, 2.7f);
        pathBuilder.curveToRelative(-0.33f, 0.19f, -0.33f, 0.67f, 0.0f, 0.87f);
        pathBuilder.lineToRelative(4.68f, 2.7f);
        pathBuilder.lineTo(18.43f, 16.87f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emergency = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
