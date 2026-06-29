package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PathParser.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001aX\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002\u001aX\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u001a\u001a\u0010\u001a\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0007\u001a\r\u0010\u001e\u001a\u00020\t*\u00020\tH\u0082\b\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u001f"}, d2 = {"EmptyArray", "", "getEmptyArray", "()[F", "arcToBezier", "", "p", "Landroidx/compose/ui/graphics/Path;", "cx", "", "cy", "a", "b", "e1x", "e1y", "theta", "start", "sweep", "drawArc", "x0", "y0", "x1", "y1", "isMoreThanHalf", "", "isPositiveArc", "toPath", "", "Landroidx/compose/ui/graphics/vector/PathNode;", TypedValues.AttributesType.S_TARGET, "toRadians", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class PathParserKt {
    private static final float[] EmptyArray = new float[0];

    private static final double toRadians(double d) {
        return (d / 180) * 3.141592653589793d;
    }

    public static final float[] getEmptyArray() {
        return EmptyArray;
    }

    public static /* synthetic */ Path toPath$default(List list, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return toPath(list, path);
    }

    public static final Path toPath(List<? extends PathNode> list, Path path) {
        PathNode pathNode;
        float f;
        int i;
        int i2;
        PathNode pathNode2;
        float f2;
        float f3;
        float x1;
        float dy1;
        float dy;
        float y1;
        float x2;
        float y2;
        float f4;
        float f5;
        float f6;
        float f7;
        float dy2;
        List<? extends PathNode> list2 = list;
        Path path2 = path;
        int iMo3305getFillTypeRgk1Os = path.mo3305getFillTypeRgk1Os();
        path.rewind();
        path2.mo3307setFillTypeoQ8Xj4U(iMo3305getFillTypeRgk1Os);
        PathNode pathNode3 = list.isEmpty() ? PathNode.Close.INSTANCE : list2.get(0);
        int size = list.size();
        float f8 = 0.0f;
        int i3 = 0;
        float f9 = 0.0f;
        float arcStartY = 0.0f;
        float arcStartX = 0.0f;
        float y = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (i3 < size) {
            PathNode pathNode4 = list2.get(i3);
            if (pathNode4 instanceof PathNode.Close) {
                path.close();
                path2.moveTo(f10, f11);
                pathNode2 = pathNode4;
                f9 = f10;
                arcStartX = f9;
                arcStartY = f11;
                y = arcStartY;
            } else if (pathNode4 instanceof PathNode.RelativeMoveTo) {
                PathNode.RelativeMoveTo relativeMoveTo = (PathNode.RelativeMoveTo) pathNode4;
                arcStartX += relativeMoveTo.getDx();
                y += relativeMoveTo.getDy();
                path2.relativeMoveTo(relativeMoveTo.getDx(), relativeMoveTo.getDy());
                pathNode2 = pathNode4;
                f10 = arcStartX;
                f11 = y;
            } else {
                if (pathNode4 instanceof PathNode.MoveTo) {
                    PathNode.MoveTo moveTo = (PathNode.MoveTo) pathNode4;
                    float x = moveTo.getX();
                    float y3 = moveTo.getY();
                    path2.moveTo(moveTo.getX(), moveTo.getY());
                    arcStartX = x;
                    f10 = arcStartX;
                    y = y3;
                    f11 = y;
                } else {
                    if (pathNode4 instanceof PathNode.RelativeLineTo) {
                        PathNode.RelativeLineTo relativeLineTo = (PathNode.RelativeLineTo) pathNode4;
                        path2.relativeLineTo(relativeLineTo.getDx(), relativeLineTo.getDy());
                        arcStartX += relativeLineTo.getDx();
                        dy2 = relativeLineTo.getDy();
                    } else if (pathNode4 instanceof PathNode.LineTo) {
                        PathNode.LineTo lineTo = (PathNode.LineTo) pathNode4;
                        path2.lineTo(lineTo.getX(), lineTo.getY());
                        float x3 = lineTo.getX();
                        y = lineTo.getY();
                        arcStartX = x3;
                    } else if (pathNode4 instanceof PathNode.RelativeHorizontalTo) {
                        PathNode.RelativeHorizontalTo relativeHorizontalTo = (PathNode.RelativeHorizontalTo) pathNode4;
                        path2.relativeLineTo(relativeHorizontalTo.getDx(), f8);
                        arcStartX += relativeHorizontalTo.getDx();
                    } else if (pathNode4 instanceof PathNode.HorizontalTo) {
                        PathNode.HorizontalTo horizontalTo = (PathNode.HorizontalTo) pathNode4;
                        path2.lineTo(horizontalTo.getX(), y);
                        arcStartX = horizontalTo.getX();
                    } else if (pathNode4 instanceof PathNode.RelativeVerticalTo) {
                        PathNode.RelativeVerticalTo relativeVerticalTo = (PathNode.RelativeVerticalTo) pathNode4;
                        path2.relativeLineTo(f8, relativeVerticalTo.getDy());
                        dy2 = relativeVerticalTo.getDy();
                    } else if (pathNode4 instanceof PathNode.VerticalTo) {
                        PathNode.VerticalTo verticalTo = (PathNode.VerticalTo) pathNode4;
                        path2.lineTo(arcStartX, verticalTo.getY());
                        y = verticalTo.getY();
                    } else {
                        if (pathNode4 instanceof PathNode.RelativeCurveTo) {
                            PathNode.RelativeCurveTo relativeCurveTo = (PathNode.RelativeCurveTo) pathNode4;
                            pathNode = pathNode4;
                            path.relativeCubicTo(relativeCurveTo.getDx1(), relativeCurveTo.getDy1(), relativeCurveTo.getDx2(), relativeCurveTo.getDy2(), relativeCurveTo.getDx3(), relativeCurveTo.getDy3());
                            x1 = relativeCurveTo.getDx2() + arcStartX;
                            dy1 = relativeCurveTo.getDy2() + y;
                            arcStartX += relativeCurveTo.getDx3();
                            dy = relativeCurveTo.getDy3();
                        } else {
                            pathNode = pathNode4;
                            if (pathNode instanceof PathNode.CurveTo) {
                                PathNode.CurveTo curveTo = (PathNode.CurveTo) pathNode;
                                path.cubicTo(curveTo.getX1(), curveTo.getY1(), curveTo.getX2(), curveTo.getY2(), curveTo.getX3(), curveTo.getY3());
                                x1 = curveTo.getX2();
                                y1 = curveTo.getY2();
                                x2 = curveTo.getX3();
                                y2 = curveTo.getY3();
                            } else if (pathNode instanceof PathNode.RelativeReflectiveCurveTo) {
                                if (pathNode3.getIsCurve()) {
                                    f7 = y - arcStartY;
                                    f6 = arcStartX - f9;
                                } else {
                                    f6 = f8;
                                    f7 = f6;
                                }
                                PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo = (PathNode.RelativeReflectiveCurveTo) pathNode;
                                path.relativeCubicTo(f6, f7, relativeReflectiveCurveTo.getDx1(), relativeReflectiveCurveTo.getDy1(), relativeReflectiveCurveTo.getDx2(), relativeReflectiveCurveTo.getDy2());
                                x1 = relativeReflectiveCurveTo.getDx1() + arcStartX;
                                dy1 = relativeReflectiveCurveTo.getDy1() + y;
                                arcStartX += relativeReflectiveCurveTo.getDx2();
                                dy = relativeReflectiveCurveTo.getDy2();
                            } else if (pathNode instanceof PathNode.ReflectiveCurveTo) {
                                if (pathNode3.getIsCurve()) {
                                    float f12 = 2;
                                    f5 = (f12 * y) - arcStartY;
                                    f4 = (arcStartX * f12) - f9;
                                } else {
                                    f4 = arcStartX;
                                    f5 = y;
                                }
                                PathNode.ReflectiveCurveTo reflectiveCurveTo = (PathNode.ReflectiveCurveTo) pathNode;
                                path.cubicTo(f4, f5, reflectiveCurveTo.getX1(), reflectiveCurveTo.getY1(), reflectiveCurveTo.getX2(), reflectiveCurveTo.getY2());
                                x1 = reflectiveCurveTo.getX1();
                                y1 = reflectiveCurveTo.getY1();
                                x2 = reflectiveCurveTo.getX2();
                                y2 = reflectiveCurveTo.getY2();
                            } else if (pathNode instanceof PathNode.RelativeQuadTo) {
                                PathNode.RelativeQuadTo relativeQuadTo = (PathNode.RelativeQuadTo) pathNode;
                                path2.relativeQuadraticBezierTo(relativeQuadTo.getDx1(), relativeQuadTo.getDy1(), relativeQuadTo.getDx2(), relativeQuadTo.getDy2());
                                x1 = relativeQuadTo.getDx1() + arcStartX;
                                dy1 = relativeQuadTo.getDy1() + y;
                                arcStartX += relativeQuadTo.getDx2();
                                dy = relativeQuadTo.getDy2();
                            } else if (pathNode instanceof PathNode.QuadTo) {
                                PathNode.QuadTo quadTo = (PathNode.QuadTo) pathNode;
                                path2.quadraticBezierTo(quadTo.getX1(), quadTo.getY1(), quadTo.getX2(), quadTo.getY2());
                                x1 = quadTo.getX1();
                                y1 = quadTo.getY1();
                                x2 = quadTo.getX2();
                                y2 = quadTo.getY2();
                            } else if (pathNode instanceof PathNode.RelativeReflectiveQuadTo) {
                                if (pathNode3.getIsQuad()) {
                                    f2 = arcStartX - f9;
                                    f3 = y - arcStartY;
                                } else {
                                    f2 = f8;
                                    f3 = f2;
                                }
                                PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo = (PathNode.RelativeReflectiveQuadTo) pathNode;
                                path2.relativeQuadraticBezierTo(f2, f3, relativeReflectiveQuadTo.getDx(), relativeReflectiveQuadTo.getDy());
                                x1 = f2 + arcStartX;
                                dy1 = f3 + y;
                                arcStartX += relativeReflectiveQuadTo.getDx();
                                dy = relativeReflectiveQuadTo.getDy();
                            } else {
                                if (pathNode instanceof PathNode.ReflectiveQuadTo) {
                                    if (pathNode3.getIsQuad()) {
                                        float f13 = 2;
                                        arcStartX = (arcStartX * f13) - f9;
                                        y = (f13 * y) - arcStartY;
                                    }
                                    PathNode.ReflectiveQuadTo reflectiveQuadTo = (PathNode.ReflectiveQuadTo) pathNode;
                                    path2.quadraticBezierTo(arcStartX, y, reflectiveQuadTo.getX(), reflectiveQuadTo.getY());
                                    float x4 = reflectiveQuadTo.getX();
                                    pathNode2 = pathNode;
                                    arcStartY = y;
                                    f = f8;
                                    i = i3;
                                    i2 = size;
                                    y = reflectiveQuadTo.getY();
                                    f9 = arcStartX;
                                    arcStartX = x4;
                                } else if (pathNode instanceof PathNode.RelativeArcTo) {
                                    PathNode.RelativeArcTo relativeArcTo = (PathNode.RelativeArcTo) pathNode;
                                    float arcStartDx = relativeArcTo.getArcStartDx() + arcStartX;
                                    float arcStartDy = relativeArcTo.getArcStartDy() + y;
                                    pathNode2 = pathNode;
                                    i = i3;
                                    f = 0.0f;
                                    i2 = size;
                                    drawArc(path, arcStartX, y, arcStartDx, arcStartDy, relativeArcTo.getHorizontalEllipseRadius(), relativeArcTo.getVerticalEllipseRadius(), relativeArcTo.getTheta(), relativeArcTo.isMoreThanHalf(), relativeArcTo.isPositiveArc());
                                    f9 = arcStartDx;
                                    arcStartX = f9;
                                    f10 = f10;
                                    f11 = f11;
                                    arcStartY = arcStartDy;
                                    y = arcStartY;
                                } else {
                                    float f14 = f10;
                                    float f15 = f11;
                                    f = f8;
                                    i = i3;
                                    i2 = size;
                                    if (pathNode instanceof PathNode.ArcTo) {
                                        PathNode.ArcTo arcTo = (PathNode.ArcTo) pathNode;
                                        pathNode2 = pathNode;
                                        drawArc(path, arcStartX, y, arcTo.getArcStartX(), arcTo.getArcStartY(), arcTo.getHorizontalEllipseRadius(), arcTo.getVerticalEllipseRadius(), arcTo.getTheta(), arcTo.isMoreThanHalf(), arcTo.isPositiveArc());
                                        arcStartX = arcTo.getArcStartX();
                                        arcStartY = arcTo.getArcStartY();
                                        y = arcStartY;
                                        f10 = f14;
                                        f11 = f15;
                                        f9 = arcStartX;
                                    } else {
                                        pathNode2 = pathNode;
                                        f10 = f14;
                                        f11 = f15;
                                    }
                                }
                                i3 = i + 1;
                                path2 = path;
                                size = i2;
                                pathNode3 = pathNode2;
                                f8 = f;
                                list2 = list;
                            }
                            pathNode2 = pathNode;
                            arcStartX = x2;
                            y = y2;
                            f = f8;
                            i = i3;
                            i2 = size;
                            arcStartY = y1;
                            f9 = x1;
                            i3 = i + 1;
                            path2 = path;
                            size = i2;
                            pathNode3 = pathNode2;
                            f8 = f;
                            list2 = list;
                        }
                        y += dy;
                        pathNode2 = pathNode;
                        arcStartY = dy1;
                        f = f8;
                        i = i3;
                        i2 = size;
                        f9 = x1;
                        i3 = i + 1;
                        path2 = path;
                        size = i2;
                        pathNode3 = pathNode2;
                        f8 = f;
                        list2 = list;
                    }
                    y += dy2;
                }
                pathNode2 = pathNode4;
            }
            f = f8;
            i = i3;
            i2 = size;
            i3 = i + 1;
            path2 = path;
            size = i2;
            pathNode3 = pathNode2;
            f8 = f;
            list2 = list;
        }
        return path;
    }

    private static final void drawArc(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2) {
        double d8;
        double d9;
        double d10 = (d7 / 180) * 3.141592653589793d;
        double dCos = Math.cos(d10);
        double dSin = Math.sin(d10);
        double d11 = ((d * dCos) + (d2 * dSin)) / d5;
        double d12 = (((-d) * dSin) + (d2 * dCos)) / d6;
        double d13 = ((d3 * dCos) + (d4 * dSin)) / d5;
        double d14 = (((-d3) * dSin) + (d4 * dCos)) / d6;
        double d15 = d11 - d13;
        double d16 = d12 - d14;
        double d17 = 2;
        double d18 = (d11 + d13) / d17;
        double d19 = (d12 + d14) / d17;
        double d20 = (d15 * d15) + (d16 * d16);
        if (d20 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        double d21 = (1.0d / d20) - 0.25d;
        if (d21 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            double dSqrt = (float) (Math.sqrt(d20) / 1.99999d);
            drawArc(path, d, d2, d3, d4, d5 * dSqrt, d6 * dSqrt, d7, z, z2);
            return;
        }
        double dSqrt2 = Math.sqrt(d21);
        double d22 = d15 * dSqrt2;
        double d23 = dSqrt2 * d16;
        if (z == z2) {
            d8 = d18 - d23;
            d9 = d19 + d22;
        } else {
            d8 = d18 + d23;
            d9 = d19 - d22;
        }
        double dAtan2 = Math.atan2(d12 - d9, d11 - d8);
        double dAtan22 = Math.atan2(d14 - d9, d13 - d8) - dAtan2;
        if (z2 != (dAtan22 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            dAtan22 = dAtan22 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
        }
        double d24 = d8 * d5;
        double d25 = d9 * d6;
        arcToBezier(path, (d24 * dCos) - (d25 * dSin), (d24 * dSin) + (d25 * dCos), d5, d6, d, d2, d10, dAtan2, dAtan22);
    }

    private static final void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        double d10 = d3;
        double d11 = 4;
        int iCeil = (int) Math.ceil(Math.abs((d9 * d11) / 3.141592653589793d));
        double dCos = Math.cos(d7);
        double dSin = Math.sin(d7);
        double dCos2 = Math.cos(d8);
        double dSin2 = Math.sin(d8);
        double d12 = -d10;
        double d13 = d12 * dCos;
        double d14 = d4 * dSin;
        double d15 = (d13 * dSin2) - (d14 * dCos2);
        double d16 = d12 * dSin;
        double d17 = d4 * dCos;
        double d18 = (dSin2 * d16) + (dCos2 * d17);
        double d19 = d9 / iCeil;
        double d20 = d5;
        double d21 = d18;
        double d22 = d15;
        int i = 0;
        double d23 = d6;
        double d24 = d8;
        while (i < iCeil) {
            double d25 = d24 + d19;
            double dSin3 = Math.sin(d25);
            double dCos3 = Math.cos(d25);
            int i2 = iCeil;
            double d26 = (d + ((d10 * dCos) * dCos3)) - (d14 * dSin3);
            double d27 = d2 + (d10 * dSin * dCos3) + (d17 * dSin3);
            double d28 = (d13 * dSin3) - (d14 * dCos3);
            double d29 = (dSin3 * d16) + (dCos3 * d17);
            double d30 = d25 - d24;
            double dTan = Math.tan(d30 / 2);
            double dSin4 = (Math.sin(d30) * (Math.sqrt(d11 + ((3.0d * dTan) * dTan)) - 1)) / 3;
            path.cubicTo((float) (d20 + (d22 * dSin4)), (float) (d23 + (d21 * dSin4)), (float) (d26 - (dSin4 * d28)), (float) (d27 - (dSin4 * d29)), (float) d26, (float) d27);
            i++;
            d19 = d19;
            dSin = dSin;
            d20 = d26;
            d16 = d16;
            d24 = d25;
            d21 = d29;
            d11 = d11;
            d22 = d28;
            dCos = dCos;
            iCeil = i2;
            d23 = d27;
            d10 = d3;
        }
    }
}
