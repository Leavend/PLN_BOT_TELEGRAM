package org.osmdroid.util;

/* loaded from: classes3.dex */
public class SpeechBalloonHelper {
    public static final int CORNER_BOTTOM = 8;
    public static final int CORNER_INSIDE = -1;
    public static final int CORNER_LEFT = 1;
    public static final int CORNER_NONE = 0;
    public static final int CORNER_RIGHT = 2;
    public static final int CORNER_TOP = 4;
    private PointL mPoint;
    private RectL mRect;
    private final PointL mTrianglePoint = new PointL();

    public int compute(RectL rectL, PointL pointL, double d, PointL pointL2, PointL pointL3) {
        this.mRect = rectL;
        this.mPoint = pointL;
        if (rectL.contains(pointL.x, this.mPoint.y)) {
            return -1;
        }
        double dComputeAngle = MyMath.computeAngle(this.mRect.centerX(), this.mRect.centerY(), this.mPoint.x, this.mPoint.y);
        computeCirclePoint(this.mTrianglePoint, d, dComputeAngle, false);
        int iCheckIntersection = checkIntersection(pointL2);
        computeCirclePoint(this.mTrianglePoint, d, dComputeAngle, true);
        int iCheckIntersection2 = checkIntersection(pointL3);
        if (iCheckIntersection == iCheckIntersection2) {
            return 0;
        }
        return iCheckIntersection2 | iCheckIntersection;
    }

    private int checkIntersection(PointL pointL) {
        if (this.mPoint.y <= this.mRect.top && checkIntersectionY(this.mRect.top, pointL)) {
            return 4;
        }
        if (this.mPoint.y >= this.mRect.bottom && checkIntersectionY(this.mRect.bottom, pointL)) {
            return 8;
        }
        if (this.mPoint.x <= this.mRect.left && checkIntersectionX(this.mRect.left, pointL)) {
            return 1;
        }
        if (this.mPoint.x < this.mRect.right || !checkIntersectionX(this.mRect.right, pointL)) {
            throw new IllegalArgumentException();
        }
        return 2;
    }

    private boolean checkIntersectionX(long j, PointL pointL) {
        double d = j;
        return SegmentIntersection.intersection(this.mPoint.x, this.mPoint.y, this.mTrianglePoint.x, this.mTrianglePoint.y, d, this.mRect.top, d, this.mRect.bottom, pointL);
    }

    private boolean checkIntersectionY(long j, PointL pointL) {
        double d = j;
        return SegmentIntersection.intersection(this.mPoint.x, this.mPoint.y, this.mTrianglePoint.x, this.mTrianglePoint.y, this.mRect.left, d, this.mRect.right, d, pointL);
    }

    private void computeCirclePoint(PointL pointL, double d, double d2, boolean z) {
        MyMath.computeCirclePoint(this.mRect.centerX(), this.mRect.centerY(), d, d2 + ((z ? 1 : -1) * 1.5707963267948966d), pointL);
    }
}
