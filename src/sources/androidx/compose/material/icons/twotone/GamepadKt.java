package androidx.compose.material.icons.twotone;

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

/* compiled from: Gamepad.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gamepad", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Gamepad", "Landroidx/compose/material/icons/Icons$TwoTone;", "getGamepad", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class GamepadKt {
    private static ImageVector _gamepad;

    public static final ImageVector getGamepad(Icons.TwoTone twoTone) {
        ImageVector imageVector = _gamepad;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Gamepad", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.67f, 11.0f);
        pathBuilder.lineTo(4.0f, 11.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(2.67f);
        pathBuilder.lineToRelative(1.0f, -1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(13.0f, 6.67f);
        pathBuilder.lineTo(13.0f, 4.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(2.67f);
        pathBuilder.lineToRelative(1.0f, 1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.0f, 17.33f);
        pathBuilder.lineTo(11.0f, 20.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(-2.67f);
        pathBuilder.lineToRelative(-1.0f, -1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.33f, 12.0f);
        pathBuilder.lineToRelative(1.0f, 1.0f);
        pathBuilder.lineTo(20.0f, 13.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineToRelative(-2.67f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 16.5f);
        pathBuilder2.lineTo(9.0f, 22.0f);
        pathBuilder2.horizontalLineToRelative(6.0f);
        pathBuilder2.verticalLineToRelative(-5.5f);
        pathBuilder2.lineToRelative(-3.0f, -3.0f);
        pathBuilder2.lineToRelative(-3.0f, 3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.0f, 20.0f);
        pathBuilder2.horizontalLineToRelative(-2.0f);
        pathBuilder2.verticalLineToRelative(-2.67f);
        pathBuilder2.lineToRelative(1.0f, -1.0f);
        pathBuilder2.lineToRelative(1.0f, 1.0f);
        pathBuilder2.lineTo(13.0f, 20.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.0f, 7.5f);
        pathBuilder2.lineTo(15.0f, 2.0f);
        pathBuilder2.lineTo(9.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(5.5f);
        pathBuilder2.lineToRelative(3.0f, 3.0f);
        pathBuilder2.lineToRelative(3.0f, -3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(11.0f, 4.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineToRelative(2.67f);
        pathBuilder2.lineToRelative(-1.0f, 1.0f);
        pathBuilder2.lineToRelative(-1.0f, -1.0f);
        pathBuilder2.lineTo(11.0f, 4.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(7.5f, 9.0f);
        pathBuilder2.lineTo(2.0f, 9.0f);
        pathBuilder2.verticalLineToRelative(6.0f);
        pathBuilder2.horizontalLineToRelative(5.5f);
        pathBuilder2.lineToRelative(3.0f, -3.0f);
        pathBuilder2.lineToRelative(-3.0f, -3.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.67f, 13.0f);
        pathBuilder2.lineTo(4.0f, 13.0f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.horizontalLineToRelative(2.67f);
        pathBuilder2.lineToRelative(1.0f, 1.0f);
        pathBuilder2.lineToRelative(-1.0f, 1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(16.5f, 9.0f);
        pathBuilder2.lineToRelative(-3.0f, 3.0f);
        pathBuilder2.lineToRelative(3.0f, 3.0f);
        pathBuilder2.lineTo(22.0f, 15.0f);
        pathBuilder2.lineTo(22.0f, 9.0f);
        pathBuilder2.horizontalLineToRelative(-5.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 13.0f);
        pathBuilder2.horizontalLineToRelative(-2.67f);
        pathBuilder2.lineToRelative(-1.0f, -1.0f);
        pathBuilder2.lineToRelative(1.0f, -1.0f);
        pathBuilder2.lineTo(20.0f, 11.0f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gamepad = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
