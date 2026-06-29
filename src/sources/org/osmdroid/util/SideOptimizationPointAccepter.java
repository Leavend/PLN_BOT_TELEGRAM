package org.osmdroid.util;

/* loaded from: classes3.dex */
public class SideOptimizationPointAccepter implements PointAccepter {
    private static final int STATUS_DIFFERENT = 0;
    private static final int STATUS_SAME_X = 1;
    private static final int STATUS_SAME_Y = 2;
    private boolean mFirst;
    private long mMax;
    private long mMin;
    private final PointAccepter mPointAccepter;
    private int mStatus;
    private final PointL mLatestPoint = new PointL();
    private final PointL mStartPoint = new PointL();

    public SideOptimizationPointAccepter(PointAccepter pointAccepter) {
        this.mPointAccepter = pointAccepter;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mFirst = true;
        this.mStatus = 0;
        this.mPointAccepter.init();
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        if (this.mFirst) {
            this.mFirst = false;
            addToAccepter(j, j2);
            this.mLatestPoint.set(j, j2);
            return;
        }
        if (this.mLatestPoint.x == j && this.mLatestPoint.y == j2) {
            return;
        }
        if (this.mLatestPoint.x == j) {
            if (this.mStatus == 1) {
                if (this.mMin > j2) {
                    this.mMin = j2;
                }
                if (this.mMax < j2) {
                    this.mMax = j2;
                }
            } else {
                flushSides();
                this.mStatus = 1;
                this.mStartPoint.set(this.mLatestPoint);
                this.mMin = Math.min(j2, this.mLatestPoint.y);
                this.mMax = Math.max(j2, this.mLatestPoint.y);
            }
        } else if (this.mLatestPoint.y == j2) {
            if (this.mStatus == 2) {
                if (this.mMin > j) {
                    this.mMin = j;
                }
                if (this.mMax < j) {
                    this.mMax = j;
                }
            } else {
                flushSides();
                this.mStatus = 2;
                this.mStartPoint.set(this.mLatestPoint);
                this.mMin = Math.min(j, this.mLatestPoint.x);
                this.mMax = Math.max(j, this.mLatestPoint.x);
            }
        } else {
            flushSides();
            addToAccepter(j, j2);
        }
        this.mLatestPoint.set(j, j2);
    }

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
        flushSides();
        this.mPointAccepter.end();
    }

    private void flushSides() {
        long j;
        long j2;
        long j3;
        long j4;
        int i = this.mStatus;
        if (i == 1) {
            long j5 = this.mStartPoint.x;
            if (this.mStartPoint.y <= this.mLatestPoint.y) {
                j = this.mStartPoint.y;
                j2 = this.mLatestPoint.y;
            } else {
                j = this.mLatestPoint.y;
                j2 = this.mStartPoint.y;
            }
            long j6 = this.mMin;
            if (j6 < j) {
                addToAccepter(j5, j6);
            }
            long j7 = this.mMax;
            if (j7 > j2) {
                addToAccepter(j5, j7);
            }
            addToAccepter(j5, this.mLatestPoint.y);
        } else if (i == 2) {
            long j8 = this.mStartPoint.y;
            if (this.mStartPoint.x <= this.mLatestPoint.x) {
                j3 = this.mStartPoint.x;
                j4 = this.mLatestPoint.x;
            } else {
                j3 = this.mLatestPoint.x;
                j4 = this.mStartPoint.x;
            }
            long j9 = this.mMin;
            if (j9 < j3) {
                addToAccepter(j9, j8);
            }
            long j10 = this.mMax;
            if (j10 > j4) {
                addToAccepter(j10, j8);
            }
            addToAccepter(this.mLatestPoint.x, j8);
        }
        this.mStatus = 0;
    }

    private void addToAccepter(long j, long j2) {
        this.mPointAccepter.add(j, j2);
    }
}
