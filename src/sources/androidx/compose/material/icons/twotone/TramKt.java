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

/* compiled from: Tram.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tram", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tram", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTram", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TramKt {
    private static ImageVector _tram;

    public static final ImageVector getTram(Icons.TwoTone twoTone) {
        ImageVector imageVector = _tram;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Tram", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.97f, 7.0f);
        pathBuilder.horizontalLineToRelative(-1.94f);
        pathBuilder.curveToRelative(-2.75f, 0.08f, -3.62f, 0.58f, -3.9f, 1.0f);
        pathBuilder.horizontalLineToRelative(9.74f);
        pathBuilder.curveToRelative(-0.28f, -0.42f, -1.15f, -0.92f, -3.9f, -1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(7.0f, 16.0f);
        pathBuilder.verticalLineToRelative(1.0f);
        pathBuilder.curveToRelative(0.0f, 0.45f, 0.3f, 0.84f, 0.74f, 0.95f);
        pathBuilder.horizontalLineToRelative(3.11f);
        pathBuilder.curveToRelative(-0.22f, -0.26f, -0.35f, -0.59f, -0.35f, -0.95f);
        pathBuilder.curveToRelative(0.0f, -0.39f, 0.15f, -0.73f, 0.39f, -1.0f);
        pathBuilder.lineTo(7.0f, 16.0f);
        pathBuilder.close();
        pathBuilder.moveTo(13.5f, 17.0f);
        pathBuilder.curveToRelative(0.0f, 0.36f, -0.13f, 0.69f, -0.35f, 0.95f);
        pathBuilder.horizontalLineToRelative(3.11f);
        pathBuilder.curveToRelative(0.44f, -0.11f, 0.74f, -0.5f, 0.74f, -0.95f);
        pathBuilder.verticalLineToRelative(-1.0f);
        pathBuilder.horizontalLineToRelative(-3.89f);
        pathBuilder.curveToRelative(0.24f, 0.27f, 0.39f, 0.61f, 0.39f, 1.0f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(13.0f, 5.0f);
        pathBuilder2.lineToRelative(0.75f, -1.5f);
        pathBuilder2.lineTo(17.0f, 3.5f);
        pathBuilder2.lineTo(17.0f, 2.0f);
        pathBuilder2.lineTo(7.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(1.5f);
        pathBuilder2.horizontalLineToRelative(4.75f);
        pathBuilder2.lineTo(11.0f, 5.0f);
        pathBuilder2.curveToRelative(-3.13f, 0.09f, -6.0f, 0.73f, -6.0f, 3.5f);
        pathBuilder2.lineTo(5.0f, 17.0f);
        pathBuilder2.curveToRelative(0.0f, 1.5f, 1.11f, 2.73f, 2.55f, 2.95f);
        pathBuilder2.lineTo(6.0f, 21.5f);
        pathBuilder2.verticalLineToRelative(0.5f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.lineToRelative(2.0f, -2.0f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.lineToRelative(2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineToRelative(-0.5f);
        pathBuilder2.lineToRelative(-1.55f, -1.55f);
        pathBuilder2.horizontalLineToRelative(-0.01f);
        pathBuilder2.horizontalLineToRelative(0.01f);
        pathBuilder2.curveTo(17.89f, 19.73f, 19.0f, 18.5f, 19.0f, 17.0f);
        pathBuilder2.lineTo(19.0f, 8.5f);
        pathBuilder2.curveToRelative(0.0f, -2.77f, -2.87f, -3.41f, -6.0f, -3.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(11.03f, 7.0f);
        pathBuilder2.horizontalLineToRelative(1.94f);
        pathBuilder2.curveToRelative(2.75f, 0.08f, 3.62f, 0.58f, 3.9f, 1.0f);
        pathBuilder2.lineTo(7.13f, 8.0f);
        pathBuilder2.curveToRelative(0.28f, -0.42f, 1.15f, -0.92f, 3.9f, -1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.85f, 17.95f);
        pathBuilder2.lineTo(7.74f, 17.95f);
        pathBuilder2.curveTo(7.3f, 17.84f, 7.0f, 17.45f, 7.0f, 17.0f);
        pathBuilder2.verticalLineToRelative(-1.0f);
        pathBuilder2.horizontalLineToRelative(3.89f);
        pathBuilder2.curveToRelative(-0.24f, 0.27f, -0.39f, 0.61f, -0.39f, 1.0f);
        pathBuilder2.curveToRelative(0.0f, 0.36f, 0.13f, 0.69f, 0.35f, 0.95f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.0f, 17.0f);
        pathBuilder2.curveToRelative(0.0f, 0.45f, -0.3f, 0.84f, -0.74f, 0.95f);
        pathBuilder2.horizontalLineToRelative(-3.11f);
        pathBuilder2.curveToRelative(0.22f, -0.26f, 0.35f, -0.59f, 0.35f, -0.95f);
        pathBuilder2.curveToRelative(0.0f, -0.39f, -0.15f, -0.73f, -0.39f, -1.0f);
        pathBuilder2.lineTo(17.0f, 16.0f);
        pathBuilder2.verticalLineToRelative(1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.0f, 14.0f);
        pathBuilder2.lineTo(7.0f, 14.0f);
        pathBuilder2.verticalLineToRelative(-4.0f);
        pathBuilder2.horizontalLineToRelative(10.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tram = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
