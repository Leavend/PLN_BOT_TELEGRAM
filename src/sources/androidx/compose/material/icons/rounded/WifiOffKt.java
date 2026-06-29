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

/* compiled from: WifiOff.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifiOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WifiOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getWifiOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WifiOffKt {
    private static ImageVector _wifiOff;

    public static final ImageVector getWifiOff(Icons.Rounded rounded) {
        ImageVector imageVector = _wifiOff;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WifiOff", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.06f, 10.14f);
        pathBuilder.curveToRelative(0.56f, 0.46f, 1.38f, 0.42f, 1.89f, -0.09f);
        pathBuilder.curveToRelative(0.59f, -0.59f, 0.55f, -1.57f, -0.1f, -2.1f);
        pathBuilder.curveToRelative(-3.59f, -2.94f, -8.2f, -4.03f, -12.55f, -3.26f);
        pathBuilder.lineToRelative(2.59f, 2.59f);
        pathBuilder.curveToRelative(2.89f, -0.03f, 5.8f, 0.92f, 8.17f, 2.86f);
        pathBuilder.close();
        pathBuilder.moveTo(17.79f, 11.97f);
        pathBuilder.curveToRelative(-0.78f, -0.57f, -1.63f, -1.0f, -2.52f, -1.3f);
        pathBuilder.lineToRelative(2.95f, 2.95f);
        pathBuilder.curveToRelative(0.24f, -0.58f, 0.1f, -1.27f, -0.43f, -1.65f);
        pathBuilder.close();
        pathBuilder.moveTo(13.95f, 16.23f);
        pathBuilder.curveToRelative(-1.22f, -0.63f, -2.68f, -0.63f, -3.91f, 0.0f);
        pathBuilder.curveToRelative(-0.59f, 0.31f, -0.7f, 1.12f, -0.23f, 1.59f);
        pathBuilder.lineToRelative(1.47f, 1.47f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.lineToRelative(1.47f, -1.47f);
        pathBuilder.curveToRelative(0.49f, -0.47f, 0.39f, -1.28f, -0.21f, -1.59f);
        pathBuilder.close();
        pathBuilder.moveTo(19.68f, 17.9f);
        pathBuilder.lineTo(4.12f, 2.34f);
        pathBuilder.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilder.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilder.lineTo(5.05f, 6.1f);
        pathBuilder.curveToRelative(-1.01f, 0.5f, -1.99f, 1.11f, -2.89f, 1.85f);
        pathBuilder.curveToRelative(-0.65f, 0.53f, -0.69f, 1.51f, -0.1f, 2.1f);
        pathBuilder.curveToRelative(0.51f, 0.51f, 1.32f, 0.56f, 1.87f, 0.1f);
        pathBuilder.curveToRelative(1.0f, -0.82f, 2.1f, -1.46f, 3.25f, -1.93f);
        pathBuilder.lineToRelative(2.23f, 2.23f);
        pathBuilder.curveToRelative(-1.13f, 0.3f, -2.21f, 0.8f, -3.19f, 1.51f);
        pathBuilder.curveToRelative(-0.69f, 0.5f, -0.73f, 1.51f, -0.13f, 2.11f);
        pathBuilder.lineToRelative(0.01f, 0.01f);
        pathBuilder.curveToRelative(0.49f, 0.49f, 1.26f, 0.54f, 1.83f, 0.13f);
        pathBuilder.curveToRelative(1.19f, -0.84f, 2.58f, -1.26f, 3.97f, -1.29f);
        pathBuilder.lineToRelative(6.37f, 6.37f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.curveToRelative(0.39f, -0.37f, 0.39f, -1.0f, 0.0f, -1.39f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifiOff = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
