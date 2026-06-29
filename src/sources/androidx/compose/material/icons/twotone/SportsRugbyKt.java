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

/* compiled from: SportsRugby.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsRugby", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsRugby", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSportsRugby", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SportsRugbyKt {
    private static ImageVector _sportsRugby;

    public static final ImageVector getSportsRugby(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sportsRugby;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SportsRugby", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.29f, 5.71f);
        pathBuilder.curveToRelative(-1.93f, 0.64f, -5.02f, 2.19f, -7.7f, 4.88f);
        pathBuilder.curveToRelative(-2.71f, 2.71f, -4.24f, 5.81f, -4.87f, 7.7f);
        pathBuilder.curveToRelative(1.93f, -0.64f, 5.03f, -2.2f, 7.7f, -4.87f);
        pathBuilder.curveTo(16.13f, 10.7f, 17.66f, 7.6f, 18.29f, 5.71f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.17f, 9.17f);
        pathBuilder2.curveToRelative(2.15f, -2.15f, 4.56f, -3.67f, 6.61f, -4.61f);
        pathBuilder2.curveTo(14.1f, 4.64f, 10.4f, 5.12f, 7.76f, 7.76f);
        pathBuilder2.curveToRelative(-2.32f, 2.32f, -3.1f, 5.58f, -3.2f, 8.04f);
        pathBuilder2.curveTo(5.5f, 13.75f, 7.01f, 11.33f, 9.17f, 9.17f);
        pathBuilder2.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw3 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk83 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(14.83f, 14.83f);
        pathBuilder3.curveToRelative(-2.15f, 2.15f, -4.56f, 3.67f, -6.61f, 4.61f);
        pathBuilder3.curveToRelative(1.68f, -0.08f, 5.39f, -0.55f, 8.03f, -3.19f);
        pathBuilder3.curveToRelative(2.32f, -2.32f, 3.1f, -5.58f, 3.2f, -8.04f);
        pathBuilder3.curveTo(18.5f, 10.25f, 16.99f, 12.67f, 14.83f, 14.83f);
        pathBuilder3.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw3, iM3776getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw4 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk84 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(20.49f, 3.51f);
        pathBuilder4.curveToRelative(-0.56f, -0.56f, -2.15f, -0.97f, -4.16f, -0.97f);
        pathBuilder4.curveToRelative(-3.08f, 0.0f, -7.15f, 0.96f, -9.98f, 3.79f);
        pathBuilder4.curveTo(1.66f, 11.03f, 2.1f, 19.07f, 3.51f, 20.49f);
        pathBuilder4.curveToRelative(0.56f, 0.56f, 2.15f, 0.97f, 4.16f, 0.97f);
        pathBuilder4.curveToRelative(3.08f, 0.0f, 7.15f, -0.96f, 9.98f, -3.79f);
        pathBuilder4.curveTo(22.34f, 12.97f, 21.9f, 4.93f, 20.49f, 3.51f);
        pathBuilder4.close();
        pathBuilder4.moveTo(5.71f, 18.29f);
        pathBuilder4.curveToRelative(0.63f, -1.89f, 2.16f, -4.99f, 4.87f, -7.7f);
        pathBuilder4.curveToRelative(2.68f, -2.68f, 5.78f, -4.23f, 7.7f, -4.88f);
        pathBuilder4.curveToRelative(-0.63f, 1.89f, -2.16f, 4.99f, -4.88f, 7.7f);
        pathBuilder4.curveTo(10.74f, 16.09f, 7.64f, 17.64f, 5.71f, 18.29f);
        pathBuilder4.close();
        pathBuilder4.moveTo(7.76f, 7.76f);
        pathBuilder4.curveToRelative(2.64f, -2.64f, 6.34f, -3.12f, 8.03f, -3.19f);
        pathBuilder4.curveToRelative(-2.05f, 0.94f, -4.46f, 2.46f, -6.61f, 4.61f);
        pathBuilder4.curveToRelative(-2.16f, 2.16f, -3.67f, 4.58f, -4.61f, 6.63f);
        pathBuilder4.curveTo(4.66f, 13.33f, 5.44f, 10.07f, 7.76f, 7.76f);
        pathBuilder4.close();
        pathBuilder4.moveTo(16.24f, 16.24f);
        pathBuilder4.curveToRelative(-2.64f, 2.64f, -6.34f, 3.12f, -8.03f, 3.19f);
        pathBuilder4.curveToRelative(2.05f, -0.94f, 4.46f, -2.46f, 6.61f, -4.61f);
        pathBuilder4.curveToRelative(2.16f, -2.16f, 3.67f, -4.58f, 4.62f, -6.63f);
        pathBuilder4.curveTo(19.34f, 10.67f, 18.56f, 13.93f, 16.24f, 16.24f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw4, iM3776getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsRugby = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
