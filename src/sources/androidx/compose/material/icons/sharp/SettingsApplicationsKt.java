package androidx.compose.material.icons.sharp;

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

/* compiled from: SettingsApplications.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsApplications", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsApplications", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettingsApplications", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SettingsApplicationsKt {
    private static ImageVector _settingsApplications;

    public static final ImageVector getSettingsApplications(Icons.Sharp sharp) {
        ImageVector imageVector = _settingsApplications;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SettingsApplications", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.0f, 3.0f);
        pathBuilder.lineTo(5.0f, 3.0f);
        pathBuilder.curveToRelative(-1.11f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.verticalLineToRelative(14.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.curveToRelative(1.11f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.lineTo(21.0f, 5.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(17.25f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.24f, -0.02f, 0.47f, -0.05f, 0.71f);
        pathBuilder.lineToRelative(0.01f, -0.02f);
        pathBuilder.lineToRelative(1.47f, 1.16f);
        pathBuilder.curveToRelative(0.14f, 0.1f, 0.23f, 0.18f, 0.23f, 0.18f);
        pathBuilder.lineToRelative(-1.7f, 2.94f);
        pathBuilder.lineToRelative(-2.02f, -0.8f);
        pathBuilder.lineToRelative(0.02f, -0.03f);
        pathBuilder.curveToRelative(-0.37f, 0.29f, -0.77f, 0.53f, -1.21f, 0.71f);
        pathBuilder.horizontalLineToRelative(0.01f);
        pathBuilder.lineToRelative(-0.27f, 1.85f);
        pathBuilder.curveToRelative(-0.02f, 0.17f, -0.04f, 0.3f, -0.04f, 0.3f);
        pathBuilder.horizontalLineToRelative(-3.4f);
        pathBuilder.lineToRelative(-0.31f, -2.15f);
        pathBuilder.lineTo(10.0f, 16.85f);
        pathBuilder.curveToRelative(-0.44f, -0.18f, -0.84f, -0.42f, -1.21f, -0.71f);
        pathBuilder.lineToRelative(0.02f, 0.03f);
        pathBuilder.lineToRelative(-2.02f, 0.8f);
        pathBuilder.lineToRelative(-1.7f, -2.94f);
        pathBuilder.reflectiveCurveToRelative(0.1f, -0.08f, 0.23f, -0.18f);
        pathBuilder.lineToRelative(1.47f, -1.16f);
        pathBuilder.lineToRelative(0.01f, 0.02f);
        pathBuilder.curveToRelative(-0.03f, -0.24f, -0.05f, -0.47f, -0.05f, -0.71f);
        pathBuilder.reflectiveCurveToRelative(0.02f, -0.47f, 0.05f, -0.69f);
        pathBuilder.lineToRelative(-0.01f, 0.01f);
        pathBuilder.lineToRelative(-1.7f, -1.34f);
        pathBuilder.lineToRelative(1.7f, -2.95f);
        pathBuilder.lineToRelative(2.01f, 0.81f);
        pathBuilder.verticalLineToRelative(0.01f);
        pathBuilder.curveToRelative(0.37f, -0.28f, 0.77f, -0.52f, 1.2f, -0.7f);
        pathBuilder.horizontalLineToRelative(-0.01f);
        pathBuilder.lineTo(10.3f, 5.0f);
        pathBuilder.horizontalLineToRelative(3.41f);
        pathBuilder.lineToRelative(0.3f, 2.15f);
        pathBuilder.lineTo(14.0f, 7.15f);
        pathBuilder.curveToRelative(0.43f, 0.18f, 0.83f, 0.42f, 1.2f, 0.7f);
        pathBuilder.verticalLineToRelative(-0.01f);
        pathBuilder.lineToRelative(2.01f, -0.81f);
        pathBuilder.lineToRelative(1.7f, 2.95f);
        pathBuilder.lineToRelative(-1.71f, 1.34f);
        pathBuilder.lineToRelative(-0.01f, -0.01f);
        pathBuilder.curveToRelative(0.04f, 0.22f, 0.06f, 0.45f, 0.06f, 0.69f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 12.0f);
        pathBuilder2.moveToRelative(-2.45f, 0.0f);
        pathBuilder2.arcToRelative(2.45f, 2.45f, 0.0f, true, true, 4.9f, 0.0f);
        pathBuilder2.arcToRelative(2.45f, 2.45f, 0.0f, true, true, -4.9f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsApplications = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
