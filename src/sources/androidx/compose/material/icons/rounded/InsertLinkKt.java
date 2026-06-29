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

/* compiled from: InsertLink.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insertLink", "Landroidx/compose/ui/graphics/vector/ImageVector;", "InsertLink", "Landroidx/compose/material/icons/Icons$Rounded;", "getInsertLink", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class InsertLinkKt {
    private static ImageVector _insertLink;

    public static final ImageVector getInsertLink(Icons.Rounded rounded) {
        ImageVector imageVector = _insertLink;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.InsertLink", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.96f, 11.38f);
        pathBuilder.curveTo(4.24f, 9.91f, 5.62f, 8.9f, 7.12f, 8.9f);
        pathBuilder.horizontalLineToRelative(2.93f);
        pathBuilder.curveToRelative(0.52f, 0.0f, 0.95f, -0.43f, 0.95f, -0.95f);
        pathBuilder.reflectiveCurveTo(10.57f, 7.0f, 10.05f, 7.0f);
        pathBuilder.lineTo(7.22f, 7.0f);
        pathBuilder.curveToRelative(-2.61f, 0.0f, -4.94f, 1.91f, -5.19f, 4.51f);
        pathBuilder.curveTo(1.74f, 14.49f, 4.08f, 17.0f, 7.0f, 17.0f);
        pathBuilder.horizontalLineToRelative(3.05f);
        pathBuilder.curveToRelative(0.52f, 0.0f, 0.95f, -0.43f, 0.95f, -0.95f);
        pathBuilder.reflectiveCurveToRelative(-0.43f, -0.95f, -0.95f, -0.95f);
        pathBuilder.lineTo(7.0f, 15.1f);
        pathBuilder.curveToRelative(-1.91f, 0.0f, -3.42f, -1.74f, -3.04f, -3.72f);
        pathBuilder.close();
        pathBuilder.moveTo(9.0f, 13.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.lineTo(9.0f, 11.0f);
        pathBuilder.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.78f, 7.0f);
        pathBuilder.horizontalLineToRelative(-2.83f);
        pathBuilder.curveToRelative(-0.52f, 0.0f, -0.95f, 0.43f, -0.95f, 0.95f);
        pathBuilder.reflectiveCurveToRelative(0.43f, 0.95f, 0.95f, 0.95f);
        pathBuilder.horizontalLineToRelative(2.93f);
        pathBuilder.curveToRelative(1.5f, 0.0f, 2.88f, 1.01f, 3.16f, 2.48f);
        pathBuilder.curveToRelative(0.38f, 1.98f, -1.13f, 3.72f, -3.04f, 3.72f);
        pathBuilder.horizontalLineToRelative(-3.05f);
        pathBuilder.curveToRelative(-0.52f, 0.0f, -0.95f, 0.43f, -0.95f, 0.95f);
        pathBuilder.reflectiveCurveToRelative(0.43f, 0.95f, 0.95f, 0.95f);
        pathBuilder.lineTo(17.0f, 17.0f);
        pathBuilder.curveToRelative(2.92f, 0.0f, 5.26f, -2.51f, 4.98f, -5.49f);
        pathBuilder.curveToRelative(-0.25f, -2.6f, -2.59f, -4.51f, -5.2f, -4.51f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _insertLink = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
