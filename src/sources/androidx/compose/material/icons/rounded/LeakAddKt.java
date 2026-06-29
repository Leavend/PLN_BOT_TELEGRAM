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

/* compiled from: LeakAdd.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_leakAdd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LeakAdd", "Landroidx/compose/material/icons/Icons$Rounded;", "getLeakAdd", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LeakAddKt {
    private static ImageVector _leakAdd;

    public static final ImageVector getLeakAdd(Icons.Rounded rounded) {
        ImageVector imageVector = _leakAdd;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LeakAdd", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.05f, 21.0f);
        pathBuilder.curveToRelative(0.5f, 0.0f, 0.94f, -0.37f, 0.99f, -0.87f);
        pathBuilder.curveToRelative(0.41f, -4.27f, 3.81f, -7.67f, 8.08f, -8.08f);
        pathBuilder.curveToRelative(0.5f, -0.05f, 0.88f, -0.48f, 0.88f, -0.99f);
        pathBuilder.curveToRelative(0.0f, -0.59f, -0.51f, -1.06f, -1.1f, -1.0f);
        pathBuilder.curveToRelative(-5.19f, 0.52f, -9.32f, 4.65f, -9.84f, 9.83f);
        pathBuilder.curveToRelative(-0.06f, 0.59f, 0.4f, 1.11f, 0.99f, 1.11f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 21.0f);
        pathBuilder.horizontalLineToRelative(3.0f);
        pathBuilder.verticalLineToRelative(-3.0f);
        pathBuilder.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.09f, 21.0f);
        pathBuilder.curveToRelative(0.49f, 0.0f, 0.9f, -0.36f, 0.98f, -0.85f);
        pathBuilder.curveToRelative(0.36f, -2.08f, 2.0f, -3.72f, 4.08f, -4.08f);
        pathBuilder.curveToRelative(0.49f, -0.08f, 0.85f, -0.49f, 0.85f, -0.98f);
        pathBuilder.curveToRelative(0.0f, -0.61f, -0.54f, -1.09f, -1.14f, -1.0f);
        pathBuilder.curveToRelative(-2.96f, 0.48f, -5.29f, 2.81f, -5.77f, 5.77f);
        pathBuilder.curveToRelative(-0.1f, 0.6f, 0.39f, 1.14f, 1.0f, 1.14f);
        pathBuilder.close();
        pathBuilder.moveTo(12.97f, 3.02f);
        pathBuilder.curveToRelative(-0.5f, 0.0f, -0.94f, 0.37f, -0.99f, 0.87f);
        pathBuilder.curveToRelative(-0.41f, 4.27f, -3.81f, 7.67f, -8.08f, 8.08f);
        pathBuilder.curveToRelative(-0.5f, 0.05f, -0.88f, 0.48f, -0.88f, 0.99f);
        pathBuilder.curveToRelative(0.0f, 0.59f, 0.51f, 1.06f, 1.1f, 1.0f);
        pathBuilder.curveToRelative(5.19f, -0.52f, 9.32f, -4.65f, 9.84f, -9.83f);
        pathBuilder.curveToRelative(0.07f, -0.58f, -0.39f, -1.11f, -0.99f, -1.11f);
        pathBuilder.close();
        pathBuilder.moveTo(6.03f, 3.02f);
        pathBuilder.horizontalLineToRelative(-3.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.94f, 3.02f);
        pathBuilder.curveToRelative(-0.49f, 0.0f, -0.9f, 0.36f, -0.98f, 0.85f);
        pathBuilder.curveToRelative(-0.36f, 2.08f, -2.0f, 3.72f, -4.08f, 4.08f);
        pathBuilder.curveToRelative(-0.49f, 0.09f, -0.85f, 0.49f, -0.85f, 0.99f);
        pathBuilder.curveToRelative(0.0f, 0.61f, 0.54f, 1.09f, 1.14f, 1.0f);
        pathBuilder.curveToRelative(2.96f, -0.48f, 5.29f, -2.81f, 5.77f, -5.77f);
        pathBuilder.curveToRelative(0.09f, -0.61f, -0.4f, -1.15f, -1.0f, -1.15f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _leakAdd = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
