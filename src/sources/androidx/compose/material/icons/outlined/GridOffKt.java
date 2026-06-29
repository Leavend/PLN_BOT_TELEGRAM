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

/* compiled from: GridOff.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gridOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GridOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getGridOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class GridOffKt {
    private static ImageVector _gridOff;

    public static final ImageVector getGridOff(Icons.Outlined outlined) {
        ImageVector imageVector = _gridOff;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.GridOff", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.0f, 4.0f);
        pathBuilder.verticalLineToRelative(0.89f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(10.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineToRelative(-2.89f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(14.0f, 10.0f);
        pathBuilder.verticalLineToRelative(0.89f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(16.0f, 10.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineToRelative(-2.89f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(20.0f, 16.0f);
        pathBuilder.verticalLineToRelative(0.89f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(22.0f, 4.0f);
        pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.lineTo(5.11f, 2.0f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(8.0f, 4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.lineTo(16.0f, 4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(1.41f, 1.14f);
        pathBuilder.lineTo(0.0f, 2.55f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineTo(2.0f, 20.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(15.45f);
        pathBuilder.lineToRelative(2.01f, 2.01f);
        pathBuilder.lineToRelative(1.41f, -1.41f);
        pathBuilder.lineTo(1.41f, 1.14f);
        pathBuilder.close();
        pathBuilder.moveTo(10.0f, 12.55f);
        pathBuilder.lineTo(11.45f, 14.0f);
        pathBuilder.lineTo(10.0f, 14.0f);
        pathBuilder.verticalLineToRelative(-1.45f);
        pathBuilder.close();
        pathBuilder.moveTo(4.0f, 6.55f);
        pathBuilder.lineTo(5.45f, 8.0f);
        pathBuilder.lineTo(4.0f, 8.0f);
        pathBuilder.lineTo(4.0f, 6.55f);
        pathBuilder.close();
        pathBuilder.moveTo(8.0f, 20.0f);
        pathBuilder.lineTo(4.0f, 20.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.0f, 14.0f);
        pathBuilder.lineTo(4.0f, 14.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.horizontalLineToRelative(3.45f);
        pathBuilder.lineToRelative(0.55f, 0.55f);
        pathBuilder.lineTo(8.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 20.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.horizontalLineToRelative(3.45f);
        pathBuilder.lineToRelative(0.55f, 0.55f);
        pathBuilder.lineTo(14.0f, 20.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.0f, 20.0f);
        pathBuilder.verticalLineToRelative(-1.45f);
        pathBuilder.lineTo(17.45f, 20.0f);
        pathBuilder.lineTo(16.0f, 20.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gridOff = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
