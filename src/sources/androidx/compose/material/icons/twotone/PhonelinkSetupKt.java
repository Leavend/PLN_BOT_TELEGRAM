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

/* compiled from: PhonelinkSetup.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phonelinkSetup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhonelinkSetup", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPhonelinkSetup", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PhonelinkSetupKt {
    private static ImageVector _phonelinkSetup;

    public static final ImageVector getPhonelinkSetup(Icons.TwoTone twoTone) {
        ImageVector imageVector = _phonelinkSetup;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PhonelinkSetup", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.0f, 3.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.lineTo(9.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(10.0f);
        pathBuilder.verticalLineToRelative(16.0f);
        pathBuilder.lineTo(9.0f, 20.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.lineTo(7.0f, 18.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(10.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.lineTo(21.0f, 3.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.lineTo(9.0f, 1.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(9.5f, 15.5f);
        pathBuilder.curveToRelative(0.29f, -0.12f, 0.55f, -0.29f, 0.8f, -0.48f);
        pathBuilder.lineToRelative(-0.02f, 0.03f);
        pathBuilder.lineToRelative(1.01f, 0.39f);
        pathBuilder.curveToRelative(0.23f, 0.09f, 0.49f, 0.0f, 0.61f, -0.22f);
        pathBuilder.lineToRelative(0.84f, -1.46f);
        pathBuilder.curveToRelative(0.12f, -0.21f, 0.07f, -0.49f, -0.12f, -0.64f);
        pathBuilder.lineToRelative(-0.85f, -0.68f);
        pathBuilder.lineToRelative(-0.02f, 0.03f);
        pathBuilder.curveToRelative(0.02f, -0.16f, 0.05f, -0.32f, 0.05f, -0.48f);
        pathBuilder.reflectiveCurveToRelative(-0.03f, -0.32f, -0.05f, -0.48f);
        pathBuilder.lineToRelative(0.02f, 0.03f);
        pathBuilder.lineToRelative(0.85f, -0.68f);
        pathBuilder.curveToRelative(0.19f, -0.15f, 0.24f, -0.43f, 0.12f, -0.64f);
        pathBuilder.lineToRelative(-0.84f, -1.46f);
        pathBuilder.curveToRelative(-0.12f, -0.21f, -0.38f, -0.31f, -0.61f, -0.22f);
        pathBuilder.lineToRelative(-1.01f, 0.39f);
        pathBuilder.lineToRelative(0.02f, 0.03f);
        pathBuilder.curveToRelative(-0.25f, -0.17f, -0.51f, -0.34f, -0.8f, -0.46f);
        pathBuilder.lineToRelative(-0.17f, -1.08f);
        pathBuilder.curveTo(9.3f, 7.18f, 9.09f, 7.0f, 8.84f, 7.0f);
        pathBuilder.lineTo(7.16f, 7.0f);
        pathBuilder.curveToRelative(-0.25f, 0.0f, -0.46f, 0.18f, -0.49f, 0.42f);
        pathBuilder.lineTo(6.5f, 8.5f);
        pathBuilder.curveToRelative(-0.29f, 0.12f, -0.55f, 0.29f, -0.8f, 0.48f);
        pathBuilder.lineToRelative(0.02f, -0.03f);
        pathBuilder.lineToRelative(-1.02f, -0.39f);
        pathBuilder.curveToRelative(-0.23f, -0.09f, -0.49f, 0.0f, -0.61f, 0.22f);
        pathBuilder.lineToRelative(-0.84f, 1.46f);
        pathBuilder.curveToRelative(-0.12f, 0.21f, -0.07f, 0.49f, 0.12f, 0.64f);
        pathBuilder.lineToRelative(0.85f, 0.68f);
        pathBuilder.lineToRelative(0.02f, -0.03f);
        pathBuilder.curveToRelative(-0.02f, 0.15f, -0.05f, 0.31f, -0.05f, 0.47f);
        pathBuilder.reflectiveCurveToRelative(0.03f, 0.32f, 0.05f, 0.48f);
        pathBuilder.lineToRelative(-0.02f, -0.03f);
        pathBuilder.lineToRelative(-0.85f, 0.68f);
        pathBuilder.curveToRelative(-0.19f, 0.15f, -0.24f, 0.43f, -0.12f, 0.64f);
        pathBuilder.lineToRelative(0.84f, 1.46f);
        pathBuilder.curveToRelative(0.12f, 0.21f, 0.38f, 0.31f, 0.61f, 0.22f);
        pathBuilder.lineToRelative(1.01f, -0.39f);
        pathBuilder.lineToRelative(-0.01f, -0.04f);
        pathBuilder.curveToRelative(0.25f, 0.19f, 0.51f, 0.36f, 0.8f, 0.48f);
        pathBuilder.lineToRelative(0.17f, 1.07f);
        pathBuilder.curveToRelative(0.03f, 0.25f, 0.24f, 0.43f, 0.49f, 0.43f);
        pathBuilder.horizontalLineToRelative(1.68f);
        pathBuilder.curveToRelative(0.25f, 0.0f, 0.46f, -0.18f, 0.49f, -0.42f);
        pathBuilder.lineToRelative(0.17f, -1.08f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phonelinkSetup = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
