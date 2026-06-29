package androidx.compose.material.icons.twotone;

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

/* compiled from: Attractions.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attractions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Attractions", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAttractions", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class AttractionsKt {
    private static ImageVector _attractions;

    public static final ImageVector getAttractions(Icons.TwoTone twoTone) {
        ImageVector imageVector = _attractions;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Attractions", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.98f, 12.02f);
        pathBuilder.moveToRelative(-1.5f, 0.0f);
        pathBuilder.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilder.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(20.15f, 14.42f);
        pathBuilder2.curveToRelative(0.23f, -0.77f, 0.35f, -1.58f, 0.35f, -2.42f);
        pathBuilder2.reflectiveCurveToRelative(-0.12f, -1.65f, -0.35f, -2.42f);
        pathBuilder2.curveToRelative(0.78f, -0.6f, 1.02f, -1.7f, 0.51f, -2.58f);
        pathBuilder2.curveToRelative(-0.51f, -0.88f, -1.58f, -1.23f, -2.49f, -0.85f);
        pathBuilder2.curveToRelative(-1.11f, -1.17f, -2.56f, -2.03f, -4.18f, -2.42f);
        pathBuilder2.curveTo(13.85f, 2.75f, 13.01f, 2.0f, 12.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(-1.85f, 0.75f, -1.98f, 1.73f);
        pathBuilder2.curveTo(8.39f, 4.12f, 6.95f, 4.98f, 5.83f, 6.15f);
        pathBuilder2.curveTo(4.92f, 5.77f, 3.85f, 6.12f, 3.34f, 7.0f);
        pathBuilder2.curveTo(2.83f, 7.88f, 3.07f, 8.98f, 3.85f, 9.58f);
        pathBuilder2.curveTo(3.62f, 10.35f, 3.5f, 11.16f, 3.5f, 12.0f);
        pathBuilder2.reflectiveCurveToRelative(0.12f, 1.65f, 0.35f, 2.42f);
        pathBuilder2.curveToRelative(-0.78f, 0.6f, -1.02f, 1.7f, -0.51f, 2.58f);
        pathBuilder2.curveToRelative(0.51f, 0.88f, 1.58f, 1.23f, 2.49f, 0.85f);
        pathBuilder2.curveToRelative(0.4f, 0.42f, 0.83f, 0.79f, 1.3f, 1.12f);
        pathBuilder2.lineTo(5.78f, 22.0f);
        pathBuilder2.horizontalLineToRelative(1.88f);
        pathBuilder2.lineToRelative(0.98f, -2.19f);
        pathBuilder2.curveToRelative(0.44f, 0.19f, 0.9f, 0.34f, 1.38f, 0.46f);
        pathBuilder2.curveTo(10.15f, 21.25f, 10.99f, 22.0f, 12.0f, 22.0f);
        pathBuilder2.reflectiveCurveToRelative(1.85f, -0.75f, 1.98f, -1.73f);
        pathBuilder2.curveToRelative(0.46f, -0.11f, 0.91f, -0.26f, 1.34f, -0.44f);
        pathBuilder2.lineTo(16.3f, 22.0f);
        pathBuilder2.horizontalLineToRelative(1.88f);
        pathBuilder2.lineToRelative(-1.34f, -3.0f);
        pathBuilder2.curveToRelative(0.48f, -0.34f, 0.93f, -0.72f, 1.34f, -1.15f);
        pathBuilder2.curveToRelative(0.91f, 0.38f, 1.99f, 0.03f, 2.49f, -0.85f);
        pathBuilder2.reflectiveCurveTo(20.93f, 15.02f, 20.15f, 14.42f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.56f, 18.75f);
        pathBuilder2.curveTo(13.19f, 18.29f, 12.63f, 18.0f, 12.0f, 18.0f);
        pathBuilder2.reflectiveCurveToRelative(-1.2f, 0.29f, -1.57f, 0.75f);
        pathBuilder2.curveToRelative(-0.4f, -0.09f, -0.79f, -0.21f, -1.16f, -0.37f);
        pathBuilder2.lineToRelative(1.43f, -3.19f);
        pathBuilder2.curveToRelative(0.4f, 0.16f, 0.84f, 0.25f, 1.3f, 0.25f);
        pathBuilder2.curveToRelative(0.44f, 0.0f, 0.87f, -0.08f, 1.26f, -0.23f);
        pathBuilder2.lineToRelative(1.42f, 3.18f);
        pathBuilder2.curveTo(14.32f, 18.54f, 13.95f, 18.66f, 13.56f, 18.75f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.48f, 12.02f);
        pathBuilder2.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilder2.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder2.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilder2.curveTo(11.15f, 13.52f, 10.48f, 12.85f, 10.48f, 12.02f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.71f, 14.01f);
        pathBuilder2.curveToRelative(-0.61f, 0.07f, -1.18f, 0.41f, -1.52f, 0.99f);
        pathBuilder2.curveToRelative(-0.32f, 0.56f, -0.34f, 1.2f, -0.12f, 1.75f);
        pathBuilder2.curveToRelative(-0.28f, 0.29f, -0.58f, 0.55f, -0.9f, 0.79f);
        pathBuilder2.lineToRelative(-1.5f, -3.35f);
        pathBuilder2.curveToRelative(0.49f, -0.59f, 0.78f, -1.34f, 0.78f, -2.16f);
        pathBuilder2.curveToRelative(0.0f, -1.89f, -1.55f, -3.41f, -3.46f, -3.41f);
        pathBuilder2.curveToRelative(-1.91f, 0.0f, -3.46f, 1.53f, -3.46f, 3.41f);
        pathBuilder2.curveToRelative(0.0f, 0.8f, 0.28f, 1.54f, 0.75f, 2.13f);
        pathBuilder2.lineToRelative(-1.52f, 3.39f);
        pathBuilder2.curveToRelative(-0.31f, -0.23f, -0.6f, -0.48f, -0.87f, -0.76f);
        pathBuilder2.curveTo(7.15f, 16.23f, 7.13f, 15.57f, 6.8f, 15.0f);
        pathBuilder2.curveToRelative(-0.34f, -0.59f, -0.93f, -0.94f, -1.56f, -0.99f);
        pathBuilder2.curveToRelative(-0.22f, -0.68f, -0.33f, -1.4f, -0.33f, -2.15f);
        pathBuilder2.curveToRelative(0.0f, -0.64f, 0.09f, -1.26f, 0.25f, -1.85f);
        pathBuilder2.curveToRelative(0.66f, -0.03f, 1.3f, -0.38f, 1.65f, -1.0f);
        pathBuilder2.curveToRelative(0.37f, -0.63f, 0.35f, -1.38f, 0.01f, -1.98f);
        pathBuilder2.curveTo(7.74f, 6.05f, 8.93f, 5.34f, 10.27f, 5.0f);
        pathBuilder2.curveToRelative(0.34f, 0.59f, 0.99f, 1.0f, 1.73f, 1.0f);
        pathBuilder2.reflectiveCurveToRelative(1.39f, -0.4f, 1.73f, -1.0f);
        pathBuilder2.curveToRelative(1.34f, 0.34f, 2.53f, 1.07f, 3.44f, 2.05f);
        pathBuilder2.curveTo(16.85f, 7.64f, 16.84f, 8.38f, 17.2f, 9.0f);
        pathBuilder2.curveToRelative(0.35f, 0.6f, 0.96f, 0.95f, 1.6f, 1.0f);
        pathBuilder2.curveToRelative(0.16f, 0.59f, 0.25f, 1.21f, 0.25f, 1.86f);
        pathBuilder2.curveTo(19.05f, 12.61f, 18.93f, 13.33f, 18.71f, 14.01f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attractions = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
