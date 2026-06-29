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

/* compiled from: Sledding.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sledding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sledding", "Landroidx/compose/material/icons/Icons$Rounded;", "getSledding", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SleddingKt {
    private static ImageVector _sledding;

    public static final ImageVector getSledding(Icons.Rounded rounded) {
        ImageVector imageVector = _sledding;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Sledding", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.0f, 4.5f);
        pathBuilder.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveTo(14.0f, 3.4f, 14.0f, 4.5f);
        pathBuilder.close();
        pathBuilder.moveTo(18.92f, 19.24f);
        pathBuilder.curveToRelative(-0.13f, 0.39f, -0.55f, 0.61f, -0.95f, 0.48f);
        pathBuilder.lineToRelative(-2.61f, -0.85f);
        pathBuilder.lineTo(14.9f, 20.3f);
        pathBuilder.lineToRelative(3.32f, 1.08f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(1.31f, 0.43f, 2.72f, -0.29f, 3.15f, -1.61f);
        pathBuilder.curveToRelative(0.36f, -1.12f, -0.11f, -2.32f, -1.07f, -2.91f);
        pathBuilder.curveToRelative(-0.32f, -0.2f, -0.45f, -0.6f, -0.3f, -0.95f);
        pathBuilder.curveToRelative(0.2f, -0.44f, 0.71f, -0.57f, 1.12f, -0.31f);
        pathBuilder.curveToRelative(1.52f, 0.95f, 2.25f, 2.85f, 1.68f, 4.62f);
        pathBuilder.curveToRelative(-0.68f, 2.1f, -2.94f, 3.25f, -5.04f, 2.57f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.lineTo(1.74f, 17.6f);
        pathBuilder.curveToRelative(-0.39f, -0.13f, -0.63f, -0.54f, -0.52f, -0.93f);
        pathBuilder.curveToRelative(0.12f, -0.41f, 0.55f, -0.63f, 0.95f, -0.5f);
        pathBuilder.lineToRelative(3.22f, 1.05f);
        pathBuilder.lineToRelative(0.46f, -1.43f);
        pathBuilder.lineToRelative(-3.19f, -1.04f);
        pathBuilder.curveToRelative(-0.39f, -0.13f, -0.63f, -0.54f, -0.52f, -0.93f);
        pathBuilder.curveToRelative(0.12f, -0.41f, 0.55f, -0.63f, 0.95f, -0.5f);
        pathBuilder.lineTo(4.0f, 13.6f);
        pathBuilder.verticalLineToRelative(-2.78f);
        pathBuilder.curveToRelative(0.0f, -0.8f, 0.48f, -1.52f, 1.21f, -1.84f);
        pathBuilder.curveToRelative(0.75f, -0.32f, 4.11f, -1.76f, 4.26f, -1.83f);
        pathBuilder.curveToRelative(0.41f, -0.18f, 0.89f, -0.21f, 1.35f, -0.04f);
        pathBuilder.curveToRelative(0.91f, 0.34f, 1.37f, 1.36f, 1.07f, 2.28f);
        pathBuilder.lineToRelative(-1.04f, 3.2f);
        pathBuilder.lineTo(13.0f, 12.25f);
        pathBuilder.curveToRelative(0.89f, -0.15f, 1.76f, 0.32f, 2.14f, 1.14f);
        pathBuilder.lineToRelative(2.08f, 4.51f);
        pathBuilder.lineToRelative(1.22f, 0.4f);
        pathBuilder.curveTo(18.84f, 18.42f, 19.05f, 18.84f, 18.92f, 19.24f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 14.25f);
        pathBuilder.lineToRelative(1.01f, 0.33f);
        pathBuilder.curveToRelative(-0.22f, -0.42f, -0.28f, -0.92f, -0.12f, -1.4f);
        pathBuilder.lineTo(7.92f, 10.0f);
        pathBuilder.lineTo(6.0f, 10.82f);
        pathBuilder.verticalLineTo(14.25f);
        pathBuilder.close();
        pathBuilder.moveTo(13.94f, 18.41f);
        pathBuilder.lineToRelative(-6.66f, -2.16f);
        pathBuilder.lineToRelative(-0.46f, 1.43f);
        pathBuilder.lineToRelative(6.66f, 2.16f);
        pathBuilder.lineTo(13.94f, 18.41f);
        pathBuilder.close();
        pathBuilder.moveTo(14.63f, 17.05f);
        pathBuilder.lineToRelative(-1.18f, -2.56f);
        pathBuilder.lineToRelative(-3.97f, 0.89f);
        pathBuilder.lineTo(14.63f, 17.05f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sledding = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
