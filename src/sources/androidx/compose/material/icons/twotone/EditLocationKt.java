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

/* compiled from: EditLocation.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editLocation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditLocation", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEditLocation", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EditLocationKt {
    private static ImageVector _editLocation;

    public static final ImageVector getEditLocation(Icons.TwoTone twoTone) {
        ImageVector imageVector = _editLocation;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EditLocation", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.11f, 14.0f);
        pathBuilder.horizontalLineToRelative(-0.83f);
        pathBuilder.horizontalLineTo(10.0f);
        pathBuilder.horizontalLineTo(8.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(8.74f);
        pathBuilder.verticalLineTo(7.91f);
        pathBuilder.lineToRelative(0.59f, -0.59f);
        pathBuilder.lineTo(11.91f, 4.0f);
        pathBuilder.curveTo(8.61f, 4.05f, 6.0f, 6.6f, 6.0f, 10.2f);
        pathBuilder.curveToRelative(0.0f, 2.34f, 1.95f, 5.44f, 6.0f, 9.14f);
        pathBuilder.curveToRelative(4.05f, -3.7f, 6.0f, -6.79f, 6.0f, -9.14f);
        pathBuilder.curveToRelative(0.0f, -0.03f, 0.0f, -0.06f, 0.0f, -0.08f);
        pathBuilder.lineToRelative(-3.3f, 3.3f);
        pathBuilder.lineTo(14.11f, 14.0f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(18.17f, 4.91f);
        pathBuilder2.lineTo(17.1f, 3.84f);
        pathBuilder2.lineToRelative(-5.55f, 5.55f);
        pathBuilder2.verticalLineToRelative(1.08f);
        pathBuilder2.horizontalLineToRelative(1.08f);
        pathBuilder2.lineTo(18.17f, 4.91f);
        pathBuilder2.close();
        pathBuilder2.moveTo(16.0f, 2.74f);
        pathBuilder2.lineToRelative(1.29f, -1.29f);
        pathBuilder2.curveToRelative(0.58f, -0.59f, 1.52f, -0.59f, 2.11f, -0.01f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 0.01f, 0.01f, 0.01f, 0.01f);
        pathBuilder2.lineToRelative(1.15f, 1.15f);
        pathBuilder2.curveToRelative(0.59f, 0.59f, 0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilder2.lineTo(19.88f, 5.4f);
        pathBuilder2.lineToRelative(-0.02f, 0.02f);
        pathBuilder2.lineTo(19.28f, 6.0f);
        pathBuilder2.lineToRelative(-6.0f, 6.0f);
        pathBuilder2.horizontalLineTo(10.0f);
        pathBuilder2.verticalLineTo(8.74f);
        pathBuilder2.lineTo(16.0f, 2.74f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.72f, 2.19f);
        pathBuilder2.lineToRelative(-0.55f, 0.55f);
        pathBuilder2.lineTo(11.9f, 4.01f);
        pathBuilder2.curveTo(8.6f, 4.06f, 6.0f, 6.61f, 6.0f, 10.21f);
        pathBuilder2.curveToRelative(0.0f, 2.34f, 1.95f, 5.44f, 6.0f, 9.14f);
        pathBuilder2.curveToRelative(4.05f, -3.7f, 6.0f, -6.79f, 6.0f, -9.14f);
        pathBuilder2.verticalLineToRelative(-0.1f);
        pathBuilder2.lineToRelative(1.8f, -1.8f);
        pathBuilder2.curveToRelative(0.13f, 0.6f, 0.2f, 1.24f, 0.2f, 1.9f);
        pathBuilder2.curveToRelative(0.0f, 3.32f, -2.67f, 7.25f, -8.0f, 11.8f);
        pathBuilder2.curveToRelative(-5.33f, -4.55f, -8.0f, -8.48f, -8.0f, -11.8f);
        pathBuilder2.curveToRelative(0.0f, -4.98f, 3.8f, -8.2f, 8.0f, -8.2f);
        pathBuilder2.curveTo(12.58f, 2.01f, 13.16f, 2.07f, 13.72f, 2.19f);
        pathBuilder2.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw3 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk83 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(18.17f, 4.91f);
        pathBuilder3.lineToRelative(-1.07f, -1.07f);
        pathBuilder3.lineToRelative(-5.55f, 5.55f);
        pathBuilder3.lineToRelative(0.0f, 1.08f);
        pathBuilder3.lineToRelative(1.08f, 0.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw3, iM3776getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editLocation = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
