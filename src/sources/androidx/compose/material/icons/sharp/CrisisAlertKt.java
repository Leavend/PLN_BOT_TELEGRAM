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

/* compiled from: CrisisAlert.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_crisisAlert", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CrisisAlert", "Landroidx/compose/material/icons/Icons$Sharp;", "getCrisisAlert", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CrisisAlertKt {
    private static ImageVector _crisisAlert;

    public static final ImageVector getCrisisAlert(Icons.Sharp sharp) {
        ImageVector imageVector = _crisisAlert;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CrisisAlert", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.5f, 2.5f);
        pathBuilder.curveToRelative(0.0f, 1.06f, -0.75f, 3.64f, -1.19f, 5.04f);
        pathBuilder.curveTo(13.13f, 8.11f, 12.6f, 8.5f, 12.0f, 8.5f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveToRelative(-0.6f, 0.0f, -1.13f, -0.39f, -1.31f, -0.96f);
        pathBuilder.curveTo(10.25f, 6.14f, 9.5f, 3.56f, 9.5f, 2.5f);
        pathBuilder.curveTo(9.5f, 1.12f, 10.62f, 0.0f, 12.0f, 0.0f);
        pathBuilder.reflectiveCurveTo(14.5f, 1.12f, 14.5f, 2.5f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 10.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(13.1f, 10.0f, 12.0f, 10.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.08f, 5.11f);
        pathBuilder.curveToRelative(0.18f, -0.75f, 0.33f, -1.47f, 0.39f, -2.06f);
        pathBuilder.curveTo(19.75f, 4.69f, 22.0f, 8.08f, 22.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 5.52f, -4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilder.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -3.92f, 2.25f, -7.31f, 5.53f, -8.95f);
        pathBuilder.curveTo(7.6f, 3.64f, 7.74f, 4.37f, 7.92f, 5.11f);
        pathBuilder.curveTo(5.58f, 6.51f, 4.0f, 9.07f, 4.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilder.curveTo(20.0f, 9.07f, 18.42f, 6.51f, 16.08f, 5.11f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilder.curveToRelative(0.0f, -2.0f, 0.98f, -3.77f, 2.48f, -4.86f);
        pathBuilder.curveToRelative(0.23f, 0.81f, 0.65f, 2.07f, 0.65f, 2.07f);
        pathBuilder.curveTo(8.43f, 9.93f, 8.0f, 10.92f, 8.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilder.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder.curveToRelative(0.0f, -1.08f, -0.43f, -2.07f, -1.13f, -2.79f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 0.41f, -1.22f, 0.65f, -2.07f);
        pathBuilder.curveTo(17.02f, 8.23f, 18.0f, 10.0f, 18.0f, 12.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _crisisAlert = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
