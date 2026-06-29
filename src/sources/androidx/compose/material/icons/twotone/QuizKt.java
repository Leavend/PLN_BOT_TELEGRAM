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

/* compiled from: Quiz.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_quiz", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Quiz", "Landroidx/compose/material/icons/Icons$TwoTone;", "getQuiz", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class QuizKt {
    private static ImageVector _quiz;

    public static final ImageVector getQuiz(Icons.TwoTone twoTone) {
        ImageVector imageVector = _quiz;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Quiz", Dp.m5735constructorimpl(24.0f), Dp.m5735constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.0f, 4.0f);
        pathBuilder.verticalLineToRelative(12.0f);
        pathBuilder.horizontalLineToRelative(12.0f);
        pathBuilder.verticalLineTo(4.0f);
        pathBuilder.horizontalLineTo(8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.74f, 14.69f);
        pathBuilder.curveTo(14.54f, 14.9f, 14.3f, 15.0f, 14.01f, 15.0f);
        pathBuilder.curveToRelative(-0.29f, 0.0f, -0.54f, -0.1f, -0.74f, -0.31f);
        pathBuilder.curveToRelative(-0.21f, -0.21f, -0.31f, -0.45f, -0.31f, -0.74f);
        pathBuilder.curveToRelative(0.0f, -0.29f, 0.1f, -0.54f, 0.31f, -0.74f);
        pathBuilder.curveToRelative(0.21f, -0.2f, 0.45f, -0.3f, 0.74f, -0.3f);
        pathBuilder.curveToRelative(0.29f, 0.0f, 0.54f, 0.1f, 0.74f, 0.3f);
        pathBuilder.curveToRelative(0.2f, 0.2f, 0.3f, 0.45f, 0.3f, 0.74f);
        pathBuilder.curveTo(15.05f, 14.24f, 14.94f, 14.49f, 14.74f, 14.69f);
        pathBuilder.close();
        pathBuilder.moveTo(16.51f, 8.83f);
        pathBuilder.curveToRelative(-0.23f, 0.34f, -0.54f, 0.69f, -0.92f, 1.06f);
        pathBuilder.curveToRelative(-0.3f, 0.27f, -0.51f, 0.52f, -0.64f, 0.75f);
        pathBuilder.curveToRelative(-0.12f, 0.23f, -0.18f, 0.49f, -0.18f, 0.78f);
        pathBuilder.verticalLineToRelative(0.4f);
        pathBuilder.horizontalLineToRelative(-1.52f);
        pathBuilder.verticalLineToRelative(-0.56f);
        pathBuilder.curveToRelative(0.0f, -0.42f, 0.09f, -0.78f, 0.26f, -1.09f);
        pathBuilder.curveTo(13.69f, 9.85f, 14.0f, 9.5f, 14.46f, 9.1f);
        pathBuilder.curveToRelative(0.32f, -0.29f, 0.55f, -0.54f, 0.69f, -0.74f);
        pathBuilder.curveToRelative(0.14f, -0.2f, 0.21f, -0.44f, 0.21f, -0.72f);
        pathBuilder.curveToRelative(0.0f, -0.36f, -0.12f, -0.65f, -0.36f, -0.87f);
        pathBuilder.curveToRelative(-0.24f, -0.23f, -0.57f, -0.34f, -0.99f, -0.34f);
        pathBuilder.curveToRelative(-0.4f, 0.0f, -0.72f, 0.12f, -0.97f, 0.36f);
        pathBuilder.curveToRelative(-0.25f, 0.24f, -0.42f, 0.53f, -0.53f, 0.87f);
        pathBuilder.lineToRelative(-1.37f, -0.57f);
        pathBuilder.curveToRelative(0.18f, -0.55f, 0.52f, -1.03f, 1.0f, -1.45f);
        pathBuilder.curveTo(12.63f, 5.21f, 13.25f, 5.0f, 13.99f, 5.0f);
        pathBuilder.curveToRelative(0.56f, 0.0f, 1.05f, 0.11f, 1.49f, 0.33f);
        pathBuilder.curveToRelative(0.44f, 0.22f, 0.78f, 0.53f, 1.02f, 0.93f);
        pathBuilder.curveToRelative(0.24f, 0.4f, 0.36f, 0.84f, 0.36f, 1.33f);
        pathBuilder.curveTo(16.86f, 8.08f, 16.75f, 8.49f, 16.51f, 8.83f);
        pathBuilder.close();
        ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3766getButtKaPHkGw, iM3776getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3440getBlack0d7_KjU(), null);
        int iM3766getButtKaPHkGw2 = StrokeCap.INSTANCE.m3766getButtKaPHkGw();
        int iM3776getBevelLxFBmk82 = StrokeJoin.INSTANCE.m3776getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(4.0f, 6.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(14.0f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 2.0f);
        pathBuilder2.horizontalLineTo(8.0f);
        pathBuilder2.curveTo(6.9f, 2.0f, 6.0f, 2.9f, 6.0f, 4.0f);
        pathBuilder2.verticalLineToRelative(12.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.verticalLineTo(4.0f);
        pathBuilder2.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 16.0f);
        pathBuilder2.horizontalLineTo(8.0f);
        pathBuilder2.verticalLineTo(4.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineTo(16.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.51f, 10.16f);
        pathBuilder2.curveToRelative(0.41f, -0.73f, 1.18f, -1.16f, 1.63f, -1.8f);
        pathBuilder2.curveToRelative(0.48f, -0.68f, 0.21f, -1.94f, -1.14f, -1.94f);
        pathBuilder2.curveToRelative(-0.88f, 0.0f, -1.32f, 0.67f, -1.5f, 1.23f);
        pathBuilder2.lineToRelative(-1.37f, -0.57f);
        pathBuilder2.curveTo(11.51f, 5.96f, 12.52f, 5.0f, 13.99f, 5.0f);
        pathBuilder2.curveToRelative(1.23f, 0.0f, 2.08f, 0.56f, 2.51f, 1.26f);
        pathBuilder2.curveToRelative(0.37f, 0.6f, 0.58f, 1.73f, 0.01f, 2.57f);
        pathBuilder2.curveToRelative(-0.63f, 0.93f, -1.23f, 1.21f, -1.56f, 1.81f);
        pathBuilder2.curveToRelative(-0.13f, 0.24f, -0.18f, 0.4f, -0.18f, 1.18f);
        pathBuilder2.horizontalLineToRelative(-1.52f);
        pathBuilder2.curveTo(13.26f, 11.41f, 13.19f, 10.74f, 13.51f, 10.16f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.95f, 13.95f);
        pathBuilder2.curveToRelative(0.0f, -0.59f, 0.47f, -1.04f, 1.05f, -1.04f);
        pathBuilder2.curveToRelative(0.59f, 0.0f, 1.04f, 0.45f, 1.04f, 1.04f);
        pathBuilder2.curveToRelative(0.0f, 0.58f, -0.44f, 1.05f, -1.04f, 1.05f);
        pathBuilder2.curveTo(13.42f, 15.0f, 12.95f, 14.53f, 12.95f, 13.95f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4047addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3766getButtKaPHkGw2, iM3776getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _quiz = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
