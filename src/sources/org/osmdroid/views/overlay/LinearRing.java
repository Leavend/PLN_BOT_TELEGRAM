package org.osmdroid.views.overlay;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.Distance;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.IntegerAccepter;
import org.osmdroid.util.LineBuilder;
import org.osmdroid.util.ListPointAccepter;
import org.osmdroid.util.ListPointL;
import org.osmdroid.util.PathBuilder;
import org.osmdroid.util.PointAccepter;
import org.osmdroid.util.PointL;
import org.osmdroid.util.SegmentClipper;
import org.osmdroid.util.SideOptimizationPointAccepter;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public class LinearRing {
    private boolean isHorizontalRepeating;
    private boolean isVerticalRepeating;
    private final BoundingBox mBoundingBox;
    private final boolean mClosed;
    private double[] mDistances;
    private boolean mDistancesPrecomputed;
    private int mDowngradePixelSize;
    private float[] mDowngradePointList;
    private boolean mGeodesic;
    private final IntegerAccepter mIntegerAccepter;
    private final ArrayList<GeoPoint> mOriginalPoints;
    private final Path mPath;
    private final PointAccepter mPointAccepter;
    private final ListPointL mPointsForMilestones;
    private final PointL mProjectedCenter;
    private long mProjectedHeight;
    private long[] mProjectedPoints;
    private boolean mProjectedPrecomputed;
    private long mProjectedWidth;
    private final SegmentClipper mSegmentClipper;

    public LinearRing(Path path) {
        this(path, true);
    }

    public LinearRing(LineBuilder lineBuilder, boolean z) {
        this.mOriginalPoints = new ArrayList<>();
        this.mProjectedCenter = new PointL();
        this.mSegmentClipper = new SegmentClipper();
        this.mBoundingBox = new BoundingBox();
        this.isHorizontalRepeating = true;
        this.isVerticalRepeating = true;
        this.mPointsForMilestones = new ListPointL();
        this.mGeodesic = false;
        this.mPath = null;
        this.mPointAccepter = lineBuilder;
        if (lineBuilder instanceof LineDrawer) {
            IntegerAccepter integerAccepter = new IntegerAccepter(lineBuilder.getLines().length / 2);
            this.mIntegerAccepter = integerAccepter;
            ((LineDrawer) lineBuilder).setIntegerAccepter(integerAccepter);
        } else {
            this.mIntegerAccepter = null;
        }
        this.mClosed = z;
    }

    public LinearRing(LineBuilder lineBuilder) {
        this(lineBuilder, false);
    }

    public LinearRing(Path path, boolean z) {
        this.mOriginalPoints = new ArrayList<>();
        this.mProjectedCenter = new PointL();
        this.mSegmentClipper = new SegmentClipper();
        this.mBoundingBox = new BoundingBox();
        this.isHorizontalRepeating = true;
        this.isVerticalRepeating = true;
        this.mPointsForMilestones = new ListPointL();
        this.mGeodesic = false;
        this.mPath = path;
        this.mPointAccepter = new SideOptimizationPointAccepter(new PathBuilder(path));
        this.mIntegerAccepter = null;
        this.mClosed = z;
    }

    void clearPath() {
        this.mOriginalPoints.clear();
        this.mProjectedPoints = null;
        this.mDistances = null;
        resetPrecomputations();
        this.mPointAccepter.init();
    }

    protected void addGreatCircle(GeoPoint geoPoint, GeoPoint geoPoint2, int i) {
        double latitude = geoPoint.getLatitude() * 0.017453292519943295d;
        double longitude = geoPoint.getLongitude() * 0.017453292519943295d;
        double latitude2 = geoPoint2.getLatitude() * 0.017453292519943295d;
        double longitude2 = geoPoint2.getLongitude() * 0.017453292519943295d;
        double dAsin = Math.asin(Math.sqrt(Math.pow(Math.sin((latitude - latitude2) / 2.0d), 2.0d) + (Math.cos(latitude) * Math.cos(latitude2) * Math.pow(Math.sin((longitude - longitude2) / 2.0d), 2.0d)))) * 2.0d;
        int i2 = 1;
        while (i2 <= i) {
            double d = (i2 * 1.0d) / (i + 1);
            double dSin = Math.sin((1.0d - d) * dAsin) / Math.sin(dAsin);
            double dSin2 = Math.sin(d * dAsin) / Math.sin(dAsin);
            double dCos = (Math.cos(latitude) * dSin * Math.cos(longitude)) + (Math.cos(latitude2) * dSin2 * Math.cos(longitude2));
            double d2 = dAsin;
            double dCos2 = (Math.cos(latitude) * dSin * Math.sin(longitude)) + (Math.cos(latitude2) * dSin2 * Math.sin(longitude2));
            this.mOriginalPoints.add(new GeoPoint(Math.atan2((dSin * Math.sin(latitude)) + (dSin2 * Math.sin(latitude2)), Math.sqrt(Math.pow(dCos, 2.0d) + Math.pow(dCos2, 2.0d))) * 57.29577951308232d, Math.atan2(dCos2, dCos) * 57.29577951308232d));
            i2++;
            dAsin = d2;
        }
    }

    public void addPoint(GeoPoint geoPoint) {
        if (this.mGeodesic && this.mOriginalPoints.size() > 0) {
            GeoPoint geoPoint2 = this.mOriginalPoints.get(r0.size() - 1);
            addGreatCircle(geoPoint2, geoPoint, ((int) geoPoint2.distanceToAsDouble(geoPoint)) / AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength);
        }
        this.mOriginalPoints.add(geoPoint);
        resetPrecomputations();
    }

    private void resetPrecomputations() {
        this.mProjectedPrecomputed = false;
        this.mDistancesPrecomputed = false;
        this.mDowngradePixelSize = 0;
        this.mDowngradePointList = null;
    }

    public void setPoints(List<GeoPoint> list) {
        clearPath();
        Iterator<GeoPoint> it = list.iterator();
        while (it.hasNext()) {
            addPoint(it.next());
        }
    }

    public ArrayList<GeoPoint> getPoints() {
        return this.mOriginalPoints;
    }

    double[] getDistances() {
        computeDistances();
        return this.mDistances;
    }

    public double getDistance() {
        double[] distances = getDistances();
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (double d2 : distances) {
            d += d2;
        }
        return d;
    }

    public void setGeodesic(boolean z) {
        this.mGeodesic = z;
    }

    public boolean isGeodesic() {
        return this.mGeodesic;
    }

    PointL buildPathPortion(Projection projection, PointL pointL, boolean z) {
        if (this.mOriginalPoints.size() < 2) {
            return pointL;
        }
        computeProjected();
        computeDistances();
        if (pointL == null) {
            pointL = new PointL();
            getBestOffset(projection, pointL);
        }
        this.mSegmentClipper.init();
        clipAndStore(projection, pointL, this.mClosed, z, this.mSegmentClipper);
        this.mSegmentClipper.end();
        if (this.mClosed) {
            this.mPath.close();
        }
        return pointL;
    }

    void buildLinePortion(Projection projection, boolean z) {
        if (this.mOriginalPoints.size() < 2) {
            return;
        }
        computeProjected();
        computeDistances();
        PointL pointL = new PointL();
        getBestOffset(projection, pointL);
        this.mSegmentClipper.init();
        clipAndStore(projection, pointL, this.mClosed, z, this.mSegmentClipper);
        this.mSegmentClipper.end();
    }

    public ListPointL getPointsForMilestones() {
        return this.mPointsForMilestones;
    }

    private void getBestOffset(Projection projection, PointL pointL) {
        getBestOffset(projection, pointL, projection.getLongPixelsFromProjected(this.mProjectedCenter, projection.getProjectedPowerDifference(), false, null));
    }

    public void getBestOffset(Projection projection, PointL pointL, PointL pointL2) {
        Rect intrinsicScreenRect = projection.getIntrinsicScreenRect();
        getBestOffset(pointL2.x, pointL2.y, (intrinsicScreenRect.left + intrinsicScreenRect.right) / 2.0d, (intrinsicScreenRect.top + intrinsicScreenRect.bottom) / 2.0d, projection.getWorldMapSize(), pointL);
    }

    private void getBestOffset(double d, double d2, double d3, double d4, double d5, PointL pointL) {
        long j;
        int bestOffset;
        int i;
        long j2;
        int bestOffset2;
        long jRound = Math.round(d5);
        int bestOffset3 = 0;
        if (this.isVerticalRepeating) {
            int bestOffset4 = getBestOffset(d, d2, d3, d4, 0L, jRound);
            j = jRound;
            bestOffset = getBestOffset(d, d2, d3, d4, 0L, -jRound);
            i = bestOffset4;
        } else {
            j = jRound;
            bestOffset = 0;
            i = 0;
        }
        if (i <= bestOffset) {
            i = -bestOffset;
        }
        long j3 = j;
        pointL.y = j * i;
        if (this.isHorizontalRepeating) {
            bestOffset3 = getBestOffset(d, d2, d3, d4, j3, 0L);
            j2 = j3;
            bestOffset2 = getBestOffset(d, d2, d3, d4, -j3, 0L);
        } else {
            j2 = j3;
            bestOffset2 = 0;
        }
        if (bestOffset3 <= bestOffset2) {
            bestOffset3 = -bestOffset2;
        }
        pointL.x = j2 * bestOffset3;
    }

    private int getBestOffset(double d, double d2, double d3, double d4, long j, long j2) {
        double d5 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        int i = 0;
        while (true) {
            long j3 = i;
            double squaredDistanceToPoint = Distance.getSquaredDistanceToPoint(d + (j3 * j), d2 + (j3 * j2), d3, d4);
            if (i != 0 && d5 <= squaredDistanceToPoint) {
                return i - 1;
            }
            i++;
            d5 = squaredDistanceToPoint;
        }
    }

    private void clipAndStore(Projection projection, PointL pointL, boolean z, boolean z2, SegmentClipper segmentClipper) {
        this.mPointsForMilestones.clear();
        double projectedPowerDifference = projection.getProjectedPowerDifference();
        PointL pointL2 = new PointL();
        PointL pointL3 = new PointL();
        PointL pointL4 = new PointL();
        int i = 0;
        while (true) {
            long[] jArr = this.mProjectedPoints;
            if (i >= jArr.length) {
                break;
            }
            pointL2.set(jArr[i], jArr[i + 1]);
            projection.getLongPixelsFromProjected(pointL2, projectedPowerDifference, false, pointL3);
            long j = pointL3.x + pointL.x;
            long j2 = pointL3.y + pointL.y;
            if (z2) {
                this.mPointsForMilestones.add(j, j2);
            }
            if (segmentClipper != null) {
                segmentClipper.add(j, j2);
            }
            if (i == 0) {
                pointL4.set(j, j2);
            }
            i += 2;
        }
        if (z) {
            if (segmentClipper != null) {
                segmentClipper.add(pointL4.x, pointL4.y);
            }
            if (z2) {
                this.mPointsForMilestones.add(pointL4.x, pointL4.y);
            }
        }
    }

    public static double getCloserValue(double d, double d2, double d3) {
        while (true) {
            double d4 = d2 - d3;
            if (Math.abs(d4 - d) >= Math.abs(d2 - d)) {
                break;
            }
            d2 = d4;
        }
        while (true) {
            double d5 = d2 + d3;
            if (Math.abs(d5 - d) >= Math.abs(d2 - d)) {
                return d2;
            }
            d2 = d5;
        }
    }

    private void setCloserPoint(PointL pointL, PointL pointL2, double d) {
        if (this.isHorizontalRepeating) {
            pointL2.x = Math.round(getCloserValue(pointL.x, pointL2.x, d));
        }
        if (this.isVerticalRepeating) {
            pointL2.y = Math.round(getCloserValue(pointL.y, pointL2.y, d));
        }
    }

    boolean isCloseTo(GeoPoint geoPoint, double d, Projection projection, boolean z) {
        return getCloseTo(geoPoint, d, projection, z) != null;
    }

    GeoPoint getCloseTo(GeoPoint geoPoint, double d, Projection projection, boolean z) {
        double d2;
        double d3;
        double d4;
        double d5;
        Iterator<PointL> it;
        LinearRing linearRing = this;
        computeProjected();
        GeoPoint geoPoint2 = null;
        Point pixels = projection.toPixels(geoPoint, null);
        PointL pointL = new PointL();
        linearRing.getBestOffset(projection, pointL);
        clipAndStore(projection, pointL, z, true, null);
        double worldMapSize = projection.getWorldMapSize();
        Rect intrinsicScreenRect = projection.getIntrinsicScreenRect();
        int iWidth = intrinsicScreenRect.width();
        int iHeight = intrinsicScreenRect.height();
        double d6 = pixels.x;
        while (true) {
            double d7 = d6 - worldMapSize;
            if (d7 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                break;
            }
            d6 = d7;
        }
        double d8 = pixels.y;
        while (true) {
            double d9 = d8 - worldMapSize;
            if (d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                break;
            }
            d8 = d9;
        }
        double d10 = d * d;
        PointL pointL2 = new PointL();
        PointL pointL3 = new PointL();
        Iterator<PointL> it2 = linearRing.mPointsForMilestones.iterator();
        boolean z2 = true;
        int i = 0;
        while (it2.hasNext()) {
            pointL3.set(it2.next());
            if (z2) {
                d3 = worldMapSize;
                d4 = d6;
                d2 = d8;
                d5 = d10;
                it = it2;
                z2 = false;
            } else {
                double d11 = d6;
                d2 = d8;
                while (d11 < iWidth) {
                    double d12 = d2;
                    int i2 = iWidth;
                    double d13 = d6;
                    while (d12 < iHeight) {
                        Iterator<PointL> it3 = it2;
                        double d14 = worldMapSize;
                        double d15 = d11;
                        double d16 = d12;
                        double projectionFactorToSegment = Distance.getProjectionFactorToSegment(d15, d16, pointL2.x, pointL2.y, pointL3.x, pointL3.y);
                        double d17 = d10;
                        int i3 = i2;
                        if (d17 > Distance.getSquaredDistanceToProjection(d15, d16, pointL2.x, pointL2.y, pointL3.x, pointL3.y, projectionFactorToSegment)) {
                            long[] jArr = this.mProjectedPoints;
                            int i4 = (i - 1) * 2;
                            int i5 = i * 2;
                            return MapView.getTileSystem().getGeoFromMercator((long) (jArr[i4] + ((jArr[i5] - r5) * projectionFactorToSegment)), (long) (jArr[i4 + 1] + ((jArr[i5 + 1] - r7) * projectionFactorToSegment)), 1.15292150460684698E18d, null, false, false);
                        }
                        d12 += d14;
                        it2 = it3;
                        linearRing = this;
                        i2 = i3;
                        worldMapSize = d14;
                        d10 = d17;
                    }
                    d11 += worldMapSize;
                    iWidth = i2;
                    d6 = d13;
                    d10 = d10;
                }
                d3 = worldMapSize;
                d4 = d6;
                d5 = d10;
                it = it2;
            }
            int i6 = iWidth;
            LinearRing linearRing2 = linearRing;
            pointL2.set(pointL3);
            i++;
            it2 = it;
            d8 = d2;
            linearRing = linearRing2;
            iWidth = i6;
            d6 = d4;
            worldMapSize = d3;
            d10 = d5;
            geoPoint2 = null;
        }
        return geoPoint2;
    }

    public void setClipArea(long j, long j2, long j3, long j4) {
        this.mSegmentClipper.set(j, j2, j3, j4, this.mPointAccepter, this.mIntegerAccepter, this.mPath != null);
    }

    public void setClipArea(Projection projection) {
        Rect intrinsicScreenRect = projection.getIntrinsicScreenRect();
        int iWidth = intrinsicScreenRect.width() / 2;
        int iHeight = intrinsicScreenRect.height() / 2;
        int iSqrt = (int) (Math.sqrt((iWidth * iWidth) + (iHeight * iHeight)) * 2.0d * 1.1d);
        setClipArea(iWidth - iSqrt, iHeight - iSqrt, iWidth + iSqrt, iHeight + iSqrt);
        this.isHorizontalRepeating = projection.isHorizontalWrapEnabled();
        this.isVerticalRepeating = projection.isVerticalWrapEnabled();
    }

    public GeoPoint getCenter(GeoPoint geoPoint) {
        if (geoPoint == null) {
            geoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        BoundingBox boundingBox = getBoundingBox();
        geoPoint.setLatitude(boundingBox.getCenterLatitude());
        geoPoint.setLongitude(boundingBox.getCenterLongitude());
        return geoPoint;
    }

    private void computeProjected() {
        if (this.mProjectedPrecomputed) {
            return;
        }
        this.mProjectedPrecomputed = true;
        long[] jArr = this.mProjectedPoints;
        if (jArr == null || jArr.length != this.mOriginalPoints.size() * 2) {
            this.mProjectedPoints = new long[this.mOriginalPoints.size() * 2];
        }
        PointL pointL = new PointL();
        PointL pointL2 = new PointL();
        TileSystem tileSystem = MapView.getTileSystem();
        Iterator<GeoPoint> it = this.mOriginalPoints.iterator();
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i = 0;
        while (it.hasNext()) {
            GeoPoint next = it.next();
            double latitude = next.getLatitude();
            double longitude = next.getLongitude();
            tileSystem.getMercatorFromGeo(latitude, longitude, 1.15292150460684698E18d, pointL2, false);
            if (i == 0) {
                j = pointL2.x;
                j2 = j;
                j3 = pointL2.y;
                j4 = j3;
                d = latitude;
                d3 = d;
                d2 = longitude;
                d4 = d2;
            } else {
                setCloserPoint(pointL, pointL2, 1.15292150460684698E18d);
                if (j2 > pointL2.x) {
                    j2 = pointL2.x;
                    d4 = longitude;
                }
                if (j < pointL2.x) {
                    j = pointL2.x;
                    d2 = longitude;
                }
                if (j4 > pointL2.y) {
                    j4 = pointL2.y;
                    d = latitude;
                }
                if (j3 < pointL2.y) {
                    j3 = pointL2.y;
                    d3 = latitude;
                }
            }
            int i2 = i * 2;
            this.mProjectedPoints[i2] = pointL2.x;
            this.mProjectedPoints[i2 + 1] = pointL2.y;
            pointL.set(pointL2.x, pointL2.y);
            i++;
        }
        this.mProjectedWidth = j - j2;
        this.mProjectedHeight = j3 - j4;
        this.mProjectedCenter.set((j2 + j) / 2, (j4 + j3) / 2);
        this.mBoundingBox.set(d, d2, d3, d4);
    }

    private void computeDistances() {
        if (this.mDistancesPrecomputed) {
            return;
        }
        this.mDistancesPrecomputed = true;
        double[] dArr = this.mDistances;
        if (dArr == null || dArr.length != this.mOriginalPoints.size()) {
            this.mDistances = new double[this.mOriginalPoints.size()];
        }
        GeoPoint geoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        Iterator<GeoPoint> it = this.mOriginalPoints.iterator();
        int i = 0;
        while (it.hasNext()) {
            GeoPoint next = it.next();
            if (i == 0) {
                this.mDistances[i] = 0.0d;
            } else {
                this.mDistances[i] = next.distanceToAsDouble(geoPoint);
            }
            geoPoint.setCoords(next.getLatitude(), next.getLongitude());
            i++;
        }
    }

    public BoundingBox getBoundingBox() {
        if (!this.mProjectedPrecomputed) {
            computeProjected();
        }
        return this.mBoundingBox;
    }

    public void clear() {
        this.mOriginalPoints.clear();
        Path path = this.mPath;
        if (path != null) {
            path.reset();
        }
        this.mPointsForMilestones.clear();
    }

    float[] computeDowngradePointList(int i) {
        if (i == 0) {
            return null;
        }
        if (this.mDowngradePixelSize == i) {
            return this.mDowngradePointList;
        }
        computeProjected();
        long j = this.mProjectedWidth;
        long j2 = this.mProjectedHeight;
        if (j <= j2) {
            j = j2;
        }
        if (j == 0) {
            return null;
        }
        ListPointAccepter listPointAccepter = new ListPointAccepter(true);
        SideOptimizationPointAccepter sideOptimizationPointAccepter = new SideOptimizationPointAccepter(listPointAccepter);
        double d = (j * 1.0d) / i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            long[] jArr = this.mProjectedPoints;
            if (i3 >= jArr.length) {
                break;
            }
            long j3 = jArr[i3];
            i3 = i3 + 1 + 1;
            sideOptimizationPointAccepter.add(Math.round((j3 - this.mProjectedCenter.x) / d), Math.round((jArr[r7] - this.mProjectedCenter.y) / d));
        }
        this.mDowngradePixelSize = i;
        this.mDowngradePointList = new float[listPointAccepter.getList().size()];
        while (true) {
            float[] fArr = this.mDowngradePointList;
            if (i2 >= fArr.length) {
                return fArr;
            }
            fArr[i2] = listPointAccepter.getList().get(i2).longValue();
            i2++;
        }
    }
}
