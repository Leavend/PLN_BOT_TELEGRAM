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

/* compiled from: HistoryToggleOff.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_historyToggleOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HistoryToggleOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getHistoryToggleOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class HistoryToggleOffKt {
    private static ImageVector _historyToggleOff;

    public static final ImageVector getHistoryToggleOff(Icons.Rounded rounded) {
        ImageVector imageVector = _historyToggleOff;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HistoryToggleOff", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.1f, 19.37f);
        pathBuilder.lineToRelative(1.0f, 1.74f);
        pathBuilder.curveToRelative(-0.96f, 0.44f, -2.01f, 0.73f, -3.1f, 0.84f);
        pathBuilder.verticalLineToRelative(-2.02f);
        pathBuilder.curveTo(13.74f, 19.84f, 14.44f, 19.65f, 15.1f, 19.37f);
        pathBuilder.close();
        pathBuilder.moveTo(4.07f, 13.0f);
        pathBuilder.horizontalLineTo(2.05f);
        pathBuilder.curveToRelative(0.11f, 1.1f, 0.4f, 2.14f, 0.84f, 3.1f);
        pathBuilder.lineToRelative(1.74f, -1.0f);
        pathBuilder.curveTo(4.35f, 14.44f, 4.16f, 13.74f, 4.07f, 13.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.1f, 4.63f);
        pathBuilder.lineToRelative(1.0f, -1.74f);
        pathBuilder.curveTo(15.14f, 2.45f, 14.1f, 2.16f, 13.0f, 2.05f);
        pathBuilder.verticalLineToRelative(2.02f);
        pathBuilder.curveTo(13.74f, 4.16f, 14.44f, 4.35f, 15.1f, 4.63f);
        pathBuilder.close();
        pathBuilder.moveTo(19.93f, 11.0f);
        pathBuilder.horizontalLineToRelative(2.02f);
        pathBuilder.curveToRelative(-0.11f, -1.1f, -0.4f, -2.14f, -0.84f, -3.1f);
        pathBuilder.lineToRelative(-1.74f, 1.0f);
        pathBuilder.curveTo(19.65f, 9.56f, 19.84f, 10.26f, 19.93f, 11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.9f, 19.37f);
        pathBuilder.lineToRelative(-1.0f, 1.74f);
        pathBuilder.curveToRelative(0.96f, 0.44f, 2.01f, 0.73f, 3.1f, 0.84f);
        pathBuilder.verticalLineToRelative(-2.02f);
        pathBuilder.curveTo(10.26f, 19.84f, 9.56f, 19.65f, 8.9f, 19.37f);
        pathBuilder.close();
        pathBuilder.moveTo(11.0f, 4.07f);
        pathBuilder.verticalLineTo(2.05f);
        pathBuilder.curveToRelative(-1.1f, 0.11f, -2.14f, 0.4f, -3.1f, 0.84f);
        pathBuilder.lineToRelative(1.0f, 1.74f);
        pathBuilder.curveTo(9.56f, 4.35f, 10.26f, 4.16f, 11.0f, 4.07f);
        pathBuilder.close();
        pathBuilder.moveTo(18.36f, 7.17f);
        pathBuilder.lineToRelative(1.74f, -1.01f);
        pathBuilder.curveToRelative(-0.63f, -0.87f, -1.4f, -1.64f, -2.27f, -2.27f);
        pathBuilder.lineToRelative(-1.01f, 1.74f);
        pathBuilder.curveTo(17.41f, 6.08f, 17.92f, 6.59f, 18.36f, 7.17f);
        pathBuilder.close();
        pathBuilder.moveTo(4.63f, 8.9f);
        pathBuilder.lineToRelative(-1.74f, -1.0f);
        pathBuilder.curveTo(2.45f, 8.86f, 2.16f, 9.9f, 2.05f, 11.0f);
        pathBuilder.horizontalLineToRelative(2.02f);
        pathBuilder.curveTo(4.16f, 10.26f, 4.35f, 9.56f, 4.63f, 8.9f);
        pathBuilder.close();
        pathBuilder.moveTo(19.93f, 13.0f);
        pathBuilder.curveToRelative(-0.09f, 0.74f, -0.28f, 1.44f, -0.56f, 2.1f);
        pathBuilder.lineToRelative(1.74f, 1.0f);
        pathBuilder.curveToRelative(0.44f, -0.96f, 0.73f, -2.01f, 0.84f, -3.1f);
        pathBuilder.horizontalLineTo(19.93f);
        pathBuilder.close();
        pathBuilder.moveTo(16.83f, 18.36f);
        pathBuilder.lineToRelative(1.01f, 1.74f);
        pathBuilder.curveToRelative(0.87f, -0.63f, 1.64f, -1.4f, 2.27f, -2.27f);
        pathBuilder.lineToRelative(-1.74f, -1.01f);
        pathBuilder.curveTo(17.92f, 17.41f, 17.41f, 17.92f, 16.83f, 18.36f);
        pathBuilder.close();
        pathBuilder.moveTo(7.17f, 5.64f);
        pathBuilder.lineTo(6.17f, 3.89f);
        pathBuilder.curveTo(5.29f, 4.53f, 4.53f, 5.29f, 3.9f, 6.17f);
        pathBuilder.lineToRelative(1.74f, 1.01f);
        pathBuilder.curveTo(6.08f, 6.59f, 6.59f, 6.08f, 7.17f, 5.64f);
        pathBuilder.close();
        pathBuilder.moveTo(5.64f, 16.83f);
        pathBuilder.lineTo(3.9f, 17.83f);
        pathBuilder.curveToRelative(0.63f, 0.87f, 1.4f, 1.64f, 2.27f, 2.27f);
        pathBuilder.lineToRelative(1.01f, -1.74f);
        pathBuilder.curveTo(6.59f, 17.92f, 6.08f, 17.41f, 5.64f, 16.83f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 7.0f);
        pathBuilder.lineTo(12.0f, 7.0f);
        pathBuilder.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilder.verticalLineToRelative(3.59f);
        pathBuilder.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.59f, 1.41f);
        pathBuilder.lineToRelative(3.0f, 3.0f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilder.lineToRelative(-3.0f, -3.0f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.curveTo(13.0f, 7.45f, 12.55f, 7.0f, 12.0f, 7.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _historyToggleOff = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
