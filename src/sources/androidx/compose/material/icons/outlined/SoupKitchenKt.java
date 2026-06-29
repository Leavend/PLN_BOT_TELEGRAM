package androidx.compose.material.icons.outlined;

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

/* compiled from: SoupKitchen.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_soupKitchen", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SoupKitchen", "Landroidx/compose/material/icons/Icons$Outlined;", "getSoupKitchen", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SoupKitchenKt {
    private static ImageVector _soupKitchen;

    public static final ImageVector getSoupKitchen(Icons.Outlined outlined) {
        ImageVector imageVector = _soupKitchen;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SoupKitchen", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.4f, 7.0f);
        pathBuilder.curveTo(6.06f, 7.55f, 6.0f, 7.97f, 6.0f, 8.38f);
        pathBuilder.curveTo(6.0f, 9.15f, 7.0f, 11.0f, 7.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.95f, -0.4f, 1.5f, -0.4f, 1.5f);
        pathBuilder.horizontalLineTo(5.1f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 0.4f, -0.55f, 0.4f, -1.5f);
        pathBuilder.curveToRelative(0.0f, -1.0f, -1.0f, -2.85f, -1.0f, -3.62f);
        pathBuilder.curveTo(4.5f, 7.97f, 4.56f, 7.55f, 4.9f, 7.0f);
        pathBuilder.horizontalLineTo(6.4f);
        pathBuilder.close();
        pathBuilder.moveTo(11.4f, 7.0f);
        pathBuilder.curveTo(11.06f, 7.55f, 11.0f, 7.97f, 11.0f, 8.38f);
        pathBuilder.curveTo(11.0f, 9.15f, 12.0f, 11.0f, 12.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.95f, -0.4f, 1.5f, -0.4f, 1.5f);
        pathBuilder.horizontalLineToRelative(1.5f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 0.4f, -0.55f, 0.4f, -1.5f);
        pathBuilder.curveToRelative(0.0f, -1.0f, -1.0f, -2.85f, -1.0f, -3.62f);
        pathBuilder.curveToRelative(0.0f, -0.41f, 0.06f, -0.83f, 0.4f, -1.38f);
        pathBuilder.horizontalLineTo(11.4f);
        pathBuilder.close();
        pathBuilder.moveTo(8.15f, 7.0f);
        pathBuilder.curveToRelative(-0.34f, 0.55f, -0.4f, 0.97f, -0.4f, 1.38f);
        pathBuilder.curveToRelative(0.0f, 0.77f, 1.0f, 2.63f, 1.0f, 3.62f);
        pathBuilder.curveToRelative(0.0f, 0.95f, -0.4f, 1.5f, -0.4f, 1.5f);
        pathBuilder.horizontalLineToRelative(1.5f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 0.4f, -0.55f, 0.4f, -1.5f);
        pathBuilder.curveToRelative(0.0f, -1.0f, -1.0f, -2.85f, -1.0f, -3.62f);
        pathBuilder.curveToRelative(0.0f, -0.41f, 0.06f, -0.83f, 0.4f, -1.38f);
        pathBuilder.horizontalLineTo(8.15f);
        pathBuilder.close();
        pathBuilder.moveTo(18.6f, 2.0f);
        pathBuilder.curveToRelative(-1.54f, 0.0f, -2.81f, 1.16f, -2.98f, 2.65f);
        pathBuilder.lineTo(14.53f, 15.0f);
        pathBuilder.horizontalLineTo(4.01f);
        pathBuilder.curveToRelative(-0.6f, 0.0f, -1.09f, 0.53f, -1.0f, 1.13f);
        pathBuilder.curveTo(3.53f, 19.46f, 6.39f, 22.0f, 9.75f, 22.0f);
        pathBuilder.curveToRelative(3.48f, 0.0f, 6.34f, -2.73f, 6.71f, -6.23f);
        pathBuilder.lineToRelative(1.15f, -10.87f);
        pathBuilder.curveTo(17.66f, 4.39f, 18.08f, 4.0f, 18.6f, 4.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder.curveToRelative(0.0f, 0.3f, -0.1f, 1.25f, -0.1f, 1.25f);
        pathBuilder.lineToRelative(1.97f, 0.25f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 0.13f, -1.06f, 0.13f, -1.5f);
        pathBuilder.curveTo(21.6f, 3.35f, 20.25f, 2.0f, 18.6f, 2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(9.75f, 20.0f);
        pathBuilder.curveToRelative(-1.94f, 0.0f, -3.67f, -1.23f, -4.43f, -3.0f);
        pathBuilder.horizontalLineToRelative(8.78f);
        pathBuilder.horizontalLineToRelative(0.01f);
        pathBuilder.curveTo(13.39f, 18.78f, 11.69f, 20.0f, 9.75f, 20.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _soupKitchen = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
