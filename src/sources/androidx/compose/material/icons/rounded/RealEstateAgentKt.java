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

/* compiled from: RealEstateAgent.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_realEstateAgent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RealEstateAgent", "Landroidx/compose/material/icons/Icons$Rounded;", "getRealEstateAgent", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class RealEstateAgentKt {
    private static ImageVector _realEstateAgent;

    public static final ImageVector getRealEstateAgent(Icons.Rounded rounded) {
        ImageVector imageVector = _realEstateAgent;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RealEstateAgent", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 22.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.verticalLineToRelative(-7.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.verticalLineToRelative(7.0f);
        pathBuilder.curveTo(1.0f, 21.1f, 1.9f, 22.0f, 3.0f, 22.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.37f, 16.43f);
        pathBuilder.lineToRelative(1.47f, 0.51f);
        pathBuilder.curveToRelative(0.11f, 0.04f, 0.22f, 0.06f, 0.33f, 0.06f);
        pathBuilder.horizontalLineToRelative(6.83f);
        pathBuilder.curveToRelative(1.11f, 0.0f, 2.0f, 0.89f, 2.01f, 2.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.lineToRelative(-7.39f, 2.77f);
        pathBuilder.curveToRelative(-0.4f, 0.15f, -0.84f, 0.17f, -1.25f, 0.05f);
        pathBuilder.lineTo(7.0f, 20.02f);
        pathBuilder.verticalLineTo(11.0f);
        pathBuilder.horizontalLineTo(8.6f);
        pathBuilder.curveToRelative(0.24f, 0.0f, 0.48f, 0.04f, 0.7f, 0.13f);
        pathBuilder.lineToRelative(6.93f, 2.59f);
        pathBuilder.curveToRelative(0.46f, 0.17f, 0.77f, 0.61f, 0.77f, 1.11f);
        pathBuilder.verticalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.0f, 0.65f, -0.53f, 1.18f, -1.18f, 1.18f);
        pathBuilder.horizontalLineToRelative(-2.63f);
        pathBuilder.curveToRelative(-0.12f, 0.0f, -0.24f, -0.02f, -0.36f, -0.07f);
        pathBuilder.lineToRelative(-1.12f, -0.43f);
        pathBuilder.curveToRelative(-0.26f, -0.1f, -0.55f, 0.04f, -0.64f, 0.3f);
        pathBuilder.curveTo(10.98f, 16.06f, 11.11f, 16.34f, 11.37f, 16.43f);
        pathBuilder.close();
        pathBuilder.moveTo(20.16f, 5.9f);
        pathBuilder.lineToRelative(-5.0f, -3.57f);
        pathBuilder.curveToRelative(-0.7f, -0.5f, -1.63f, -0.5f, -2.32f, 0.0f);
        pathBuilder.lineToRelative(-5.0f, 3.57f);
        pathBuilder.curveTo(7.31f, 6.28f, 7.0f, 6.88f, 7.0f, 7.53f);
        pathBuilder.verticalLineTo(9.0f);
        pathBuilder.horizontalLineToRelative(1.61f);
        pathBuilder.curveToRelative(0.25f, 0.0f, 0.51f, 0.05f, 0.74f, 0.14f);
        pathBuilder.lineToRelative(7.79f, 3.11f);
        pathBuilder.curveTo(18.26f, 12.71f, 19.0f, 13.79f, 19.0f, 15.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineTo(7.53f);
        pathBuilder.curveTo(21.0f, 6.88f, 20.69f, 6.28f, 20.16f, 5.9f);
        pathBuilder.close();
        pathBuilder.moveTo(13.0f, 10.0f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.curveTo(12.5f, 9.22f, 12.72f, 9.0f, 13.0f, 9.0f);
        pathBuilder.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder.curveTo(13.5f, 9.78f, 13.28f, 10.0f, 13.0f, 10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(13.0f, 8.0f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.curveTo(12.5f, 7.22f, 12.72f, 7.0f, 13.0f, 7.0f);
        pathBuilder.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder.curveTo(13.5f, 7.78f, 13.28f, 8.0f, 13.0f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 10.0f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.curveTo(14.5f, 9.22f, 14.72f, 9.0f, 15.0f, 9.0f);
        pathBuilder.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder.curveTo(15.5f, 9.78f, 15.28f, 10.0f, 15.0f, 10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 8.0f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.curveTo(14.5f, 7.22f, 14.72f, 7.0f, 15.0f, 7.0f);
        pathBuilder.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder.curveTo(15.5f, 7.78f, 15.28f, 8.0f, 15.0f, 8.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _realEstateAgent = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
