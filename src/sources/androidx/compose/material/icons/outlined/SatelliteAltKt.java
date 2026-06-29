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

/* compiled from: SatelliteAlt.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_satelliteAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SatelliteAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getSatelliteAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SatelliteAltKt {
    private static ImageVector _satelliteAlt;

    public static final ImageVector getSatelliteAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _satelliteAlt;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SatelliteAlt", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.0f, 14.0f);
        pathBuilder.lineToRelative(2.0f, 0.0f);
        pathBuilder.curveToRelative(0.0f, 4.97f, -4.03f, 9.0f, -9.0f, 9.0f);
        pathBuilder.lineToRelative(0.0f, -2.0f);
        pathBuilder.curveTo(17.87f, 21.0f, 21.0f, 17.87f, 21.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 17.0f);
        pathBuilder.lineToRelative(0.0f, 2.0f);
        pathBuilder.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilder.lineToRelative(-2.0f, 0.0f);
        pathBuilder.curveTo(17.0f, 15.66f, 15.66f, 17.0f, 14.0f, 17.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.26f, 0.59f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.curveToRelative(0.78f, 0.78f, 0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilder.lineToRelative(-3.18f, 3.18f);
        pathBuilder.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        pathBuilder.lineToRelative(-1.24f, -1.24f);
        pathBuilder.lineTo(13.84f, 9.6f);
        pathBuilder.lineToRelative(1.24f, 1.24f);
        pathBuilder.curveToRelative(0.78f, 0.78f, 0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilder.lineToRelative(-1.41f, 1.41f);
        pathBuilder.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        pathBuilder.lineTo(9.6f, 13.84f);
        pathBuilder.lineToRelative(-0.71f, 0.71f);
        pathBuilder.lineToRelative(1.24f, 1.24f);
        pathBuilder.curveToRelative(0.78f, 0.78f, 0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilder.lineTo(6.95f, 21.8f);
        pathBuilder.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        pathBuilder.lineToRelative(-3.54f, -3.54f);
        pathBuilder.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilder.lineToRelative(3.18f, -3.18f);
        pathBuilder.curveToRelative(0.78f, -0.78f, 2.05f, -0.78f, 2.83f, 0.0f);
        pathBuilder.lineToRelative(1.24f, 1.24f);
        pathBuilder.lineToRelative(0.71f, -0.71f);
        pathBuilder.lineTo(7.3f, 11.55f);
        pathBuilder.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilder.lineTo(8.72f, 7.3f);
        pathBuilder.curveToRelative(0.78f, -0.78f, 2.05f, -0.78f, 2.83f, 0.0f);
        pathBuilder.lineToRelative(1.24f, 1.24f);
        pathBuilder.lineToRelative(0.71f, -0.71f);
        pathBuilder.lineTo(12.25f, 6.6f);
        pathBuilder.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilder.lineToRelative(3.18f, -3.18f);
        pathBuilder.curveTo(16.22f, -0.2f, 17.48f, -0.2f, 18.26f, 0.59f);
        pathBuilder.close();
        pathBuilder.moveTo(3.06f, 15.79f);
        pathBuilder.lineTo(2.0f, 16.85f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.06f, -1.06f);
        pathBuilder.lineTo(3.06f, 15.79f);
        pathBuilder.close();
        pathBuilder.moveTo(5.18f, 13.67f);
        pathBuilder.lineToRelative(-1.06f, 1.06f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.06f, -1.06f);
        pathBuilder.lineTo(5.18f, 13.67f);
        pathBuilder.close();
        pathBuilder.moveTo(10.13f, 8.72f);
        pathBuilder.lineToRelative(-1.41f, 1.41f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.41f, -1.41f);
        pathBuilder.lineTo(10.13f, 8.72f);
        pathBuilder.close();
        pathBuilder.moveTo(14.73f, 4.12f);
        pathBuilder.lineToRelative(-1.06f, 1.06f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.06f, -1.06f);
        pathBuilder.lineTo(14.73f, 4.12f);
        pathBuilder.close();
        pathBuilder.moveTo(16.85f, 2.0f);
        pathBuilder.lineToRelative(-1.06f, 1.06f);
        pathBuilder.lineToRelative(3.54f, 3.54f);
        pathBuilder.lineToRelative(1.06f, -1.06f);
        pathBuilder.lineTo(16.85f, 2.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _satelliteAlt = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
