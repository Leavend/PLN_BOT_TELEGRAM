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

/* compiled from: Wash.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wash", "Landroidx/compose/material/icons/Icons$Rounded;", "getWash", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WashKt {
    private static ImageVector _wash;

    public static final ImageVector getWash(Icons.Rounded rounded) {
        ImageVector imageVector = _wash;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Wash", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(1.94f, 11.79f);
        pathBuilder.curveTo(1.34f, 12.35f, 1.0f, 13.14f, 1.0f, 13.97f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilder.lineToRelative(13.68f, 0.0f);
        pathBuilder.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilder.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilder.horizontalLineTo(12.5f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.verticalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilder.lineToRelative(7.18f, 0.0f);
        pathBuilder.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilder.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilder.horizontalLineTo(12.5f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilder.lineToRelative(8.18f, 0.0f);
        pathBuilder.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilder.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilder.horizontalLineTo(12.5f);
        pathBuilder.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder.verticalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilder.lineToRelative(6.18f, 0.0f);
        pathBuilder.curveToRelative(0.65f, 0.0f, 1.25f, -0.47f, 1.32f, -1.12f);
        pathBuilder.curveToRelative(0.08f, -0.75f, -0.51f, -1.38f, -1.24f, -1.38f);
        pathBuilder.horizontalLineTo(8.86f);
        pathBuilder.lineToRelative(1.49f, -2.61f);
        pathBuilder.curveToRelative(0.09f, -0.16f, 0.14f, -0.33f, 0.14f, -0.53f);
        pathBuilder.curveToRelative(0.0f, -0.26f, -0.09f, -0.5f, -0.26f, -0.7f);
        pathBuilder.lineTo(9.81f, 5.71f);
        pathBuilder.curveTo(9.43f, 5.32f, 8.8f, 5.3f, 8.4f, 5.68f);
        pathBuilder.lineTo(1.94f, 11.79f);
        pathBuilder.close();
        pathBuilder.moveTo(18.5f, 8.0f);
        pathBuilder.curveTo(19.88f, 8.0f, 21.0f, 6.88f, 21.0f, 5.5f);
        pathBuilder.curveToRelative(0.0f, -1.25f, -1.41f, -3.16f, -2.11f, -4.04f);
        pathBuilder.curveToRelative(-0.2f, -0.25f, -0.57f, -0.25f, -0.77f, 0.0f);
        pathBuilder.curveTo(17.41f, 2.34f, 16.0f, 4.25f, 16.0f, 5.5f);
        pathBuilder.curveTo(16.0f, 6.88f, 17.12f, 8.0f, 18.5f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(13.5f, 9.0f);
        pathBuilder.curveTo(14.33f, 9.0f, 15.0f, 8.33f, 15.0f, 7.5f);
        pathBuilder.curveToRelative(0.0f, -0.56f, -0.67f, -1.49f, -1.11f, -2.04f);
        pathBuilder.curveToRelative(-0.2f, -0.25f, -0.58f, -0.25f, -0.77f, 0.0f);
        pathBuilder.curveTo(12.67f, 6.01f, 12.0f, 6.94f, 12.0f, 7.5f);
        pathBuilder.curveTo(12.0f, 8.33f, 12.67f, 9.0f, 13.5f, 9.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wash = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
