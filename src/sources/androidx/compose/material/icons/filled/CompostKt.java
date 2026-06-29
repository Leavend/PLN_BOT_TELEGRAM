package androidx.compose.material.icons.filled;

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

/* compiled from: Compost.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_compost", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Compost", "Landroidx/compose/material/icons/Icons$Filled;", "getCompost", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CompostKt {
    private static ImageVector _compost;

    public static final ImageVector getCompost(Icons.Filled filled) {
        ImageVector imageVector = _compost;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Compost", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.87f, 11.81f);
        pathBuilder.curveToRelative(-0.23f, -0.38f, -0.37f, -0.83f, -0.37f, -1.31f);
        pathBuilder.curveTo(12.5f, 9.12f, 13.62f, 8.0f, 15.0f, 8.0f);
        pathBuilder.lineToRelative(1.0f, 0.0f);
        pathBuilder.curveToRelative(1.51f, 0.0f, 2.0f, -1.0f, 2.0f, -1.0f);
        pathBuilder.reflectiveCurveToRelative(0.55f, 6.0f, -3.0f, 6.0f);
        pathBuilder.curveToRelative(-0.49f, 0.0f, -0.94f, -0.14f, -1.32f, -0.38f);
        pathBuilder.curveToRelative(-0.24f, 0.64f, -0.59f, 1.76f, -0.76f, 2.96f);
        pathBuilder.curveToRelative(1.26f, 0.22f, 2.28f, 0.89f, 2.77f, 1.77f);
        pathBuilder.curveToRelative(1.69f, -1.17f, 2.81f, -3.13f, 2.81f, -5.35f);
        pathBuilder.horizontalLineToRelative(3.0f);
        pathBuilder.curveToRelative(0.0f, 5.24f, -4.26f, 9.5f, -9.5f, 9.5f);
        pathBuilder.reflectiveCurveTo(2.5f, 17.24f, 2.5f, 12.0f);
        pathBuilder.reflectiveCurveTo(6.76f, 2.5f, 12.0f, 2.5f);
        pathBuilder.verticalLineTo(0.0f);
        pathBuilder.lineToRelative(4.0f, 4.0f);
        pathBuilder.lineToRelative(-4.0f, 4.0f);
        pathBuilder.verticalLineTo(5.5f);
        pathBuilder.curveToRelative(-3.58f, 0.0f, -6.5f, 2.92f, -6.5f, 6.5f);
        pathBuilder.curveToRelative(0.0f, 2.21f, 1.11f, 4.17f, 2.81f, 5.35f);
        pathBuilder.curveToRelative(0.51f, -0.92f, 1.63f, -1.62f, 2.98f, -1.8f);
        pathBuilder.curveToRelative(-0.09f, -0.69f, -0.26f, -1.42f, -0.49f, -2.03f);
        pathBuilder.curveTo(10.45f, 13.82f, 10.0f, 14.0f, 9.5f, 14.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.verticalLineToRelative(-0.99f);
        pathBuilder.curveToRelative(0.0f, -0.56f, -0.19f, -1.09f, -0.5f, -1.51f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 4.45f, -0.23f, 4.5f, 2.5f);
        pathBuilder.curveToRelative(0.0f, 0.29f, -0.06f, 0.56f, -0.17f, 0.8f);
        pathBuilder.curveTo(10.91f, 12.48f, 10.47f, 12.2f, 10.0f, 12.0f);
        pathBuilder.curveToRelative(0.58f, 0.43f, 1.37f, 1.37f, 2.0f, 2.6f);
        pathBuilder.curveToRelative(0.67f, -1.62f, 1.68f, -3.27f, 3.0f, -4.6f);
        pathBuilder.curveTo(14.24f, 10.52f, 13.53f, 11.12f, 12.87f, 11.81f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _compost = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
