package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.MotionEvent;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.Distance;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.PointL;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.milestones.MilestoneManager;

/* loaded from: classes3.dex */
public abstract class PolyOverlayWithIW extends OverlayWithIW {
    private final boolean mClosePath;
    protected float mDensity;
    private boolean mDowngradeDisplay;
    private int mDowngradeMaximumPixelSize;
    private int mDowngradeMaximumRectanglePixelSize;
    private float[] mDowngradeSegments;
    protected Paint mFillPaint;
    private GeoPoint mInfoWindowLocation;
    private LineDrawer mLineDrawer;
    protected LinearRing mOutline;
    protected Path mPath;
    protected List<LinearRing> mHoles = new ArrayList();
    protected Paint mOutlinePaint = new Paint();
    private final List<PaintList> mOutlinePaintLists = new ArrayList();
    private List<MilestoneManager> mMilestoneManagers = new ArrayList();
    private boolean mIsPaintOrPaintList = true;
    private final PointL mVisibilityProjectedCenter = new PointL();
    private final PointL mVisibilityProjectedCorner = new PointL();
    private final PointL mVisibilityRectangleCenter = new PointL();
    private final PointL mVisibilityRectangleCorner = new PointL();
    private final Point mDowngradeTopLeft = new Point();
    private final Point mDowngradeBottomRight = new Point();
    private final PointL mDowngradeCenter = new PointL();
    private final PointL mDowngradeOffset = new PointL();
    private float mDensityMultiplier = 1.0f;

    protected abstract boolean click(MapView mapView, GeoPoint geoPoint);

    protected PolyOverlayWithIW(MapView mapView, boolean z, boolean z2) {
        this.mDensity = 1.0f;
        this.mClosePath = z2;
        if (mapView != null) {
            setInfoWindow(mapView.getRepository().getDefaultPolylineInfoWindow());
            this.mDensity = mapView.getContext().getResources().getDisplayMetrics().density;
        }
        usePath(z);
    }

    public void usePath(boolean z) {
        LinearRing linearRing = this.mOutline;
        ArrayList<GeoPoint> points = linearRing == null ? null : linearRing.getPoints();
        if (z) {
            this.mPath = new Path();
            this.mLineDrawer = null;
            this.mOutline = new LinearRing(this.mPath, this.mClosePath);
        } else {
            this.mPath = null;
            this.mLineDrawer = new LineDrawer(256);
            this.mOutline = new LinearRing(this.mLineDrawer, this.mClosePath);
            this.mLineDrawer.setPaint(this.mOutlinePaint);
        }
        if (points != null) {
            setPoints(points);
        }
    }

    public void setVisible(boolean z) {
        setEnabled(z);
    }

    public boolean isVisible() {
        return isEnabled();
    }

    public Paint getOutlinePaint() {
        this.mIsPaintOrPaintList = true;
        return this.mOutlinePaint;
    }

    public List<PaintList> getOutlinePaintLists() {
        this.mIsPaintOrPaintList = false;
        return this.mOutlinePaintLists;
    }

    protected Paint getFillPaint() {
        return this.mFillPaint;
    }

    public void setGeodesic(boolean z) {
        this.mOutline.setGeodesic(z);
    }

    public boolean isGeodesic() {
        return this.mOutline.isGeodesic();
    }

    @Override // org.osmdroid.views.overlay.OverlayWithIW
    public void setInfoWindow(InfoWindow infoWindow) {
        if (this.mInfoWindow != null && this.mInfoWindow.getRelatedObject() == this) {
            this.mInfoWindow.setRelatedObject(null);
        }
        this.mInfoWindow = infoWindow;
    }

    public void showInfoWindow() {
        if (this.mInfoWindow == null || this.mInfoWindowLocation == null) {
            return;
        }
        this.mInfoWindow.open(this, this.mInfoWindowLocation, 0, 0);
    }

    public void setInfoWindowLocation(GeoPoint geoPoint) {
        this.mInfoWindowLocation = geoPoint;
    }

    public GeoPoint getInfoWindowLocation() {
        return this.mInfoWindowLocation;
    }

    public void setMilestoneManagers(List<MilestoneManager> list) {
        if (list == null) {
            if (this.mMilestoneManagers.size() > 0) {
                this.mMilestoneManagers.clear();
                return;
            }
            return;
        }
        this.mMilestoneManagers = list;
    }

    public double getDistance() {
        return this.mOutline.getDistance();
    }

    protected void setDefaultInfoWindowLocation() {
        if (this.mOutline.getPoints().size() == 0) {
            this.mInfoWindowLocation = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            return;
        }
        if (this.mInfoWindowLocation == null) {
            this.mInfoWindowLocation = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        this.mOutline.getCenter(this.mInfoWindowLocation);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) throws Exception {
        if (isVisible(projection)) {
            if (this.mDowngradeMaximumPixelSize > 0 && !isWorthDisplaying(projection)) {
                if (this.mDowngradeDisplay) {
                    displayDowngrade(canvas, projection);
                }
            } else if (this.mPath != null) {
                drawWithPath(canvas, projection);
            } else {
                drawWithLines(canvas, projection);
            }
        }
    }

    private boolean isVisible(Projection projection) {
        BoundingBox bounds = getBounds();
        projection.toProjectedPixels(bounds.getCenterLatitude(), bounds.getCenterLongitude(), this.mVisibilityProjectedCenter);
        projection.toProjectedPixels(bounds.getLatNorth(), bounds.getLonEast(), this.mVisibilityProjectedCorner);
        projection.getLongPixelsFromProjected(this.mVisibilityProjectedCenter, projection.getProjectedPowerDifference(), true, this.mVisibilityRectangleCenter);
        projection.getLongPixelsFromProjected(this.mVisibilityProjectedCorner, projection.getProjectedPowerDifference(), true, this.mVisibilityRectangleCorner);
        int width = projection.getWidth() / 2;
        double d = width;
        double height = projection.getHeight() / 2;
        return Math.sqrt(Distance.getSquaredDistanceToPoint((double) this.mVisibilityRectangleCenter.x, (double) this.mVisibilityRectangleCenter.y, d, height)) <= Math.sqrt(Distance.getSquaredDistanceToPoint((double) this.mVisibilityRectangleCenter.x, (double) this.mVisibilityRectangleCenter.y, (double) this.mVisibilityRectangleCorner.x, (double) this.mVisibilityRectangleCorner.y)) + Math.sqrt(Distance.getSquaredDistanceToPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d, height));
    }

    private void drawWithPath(Canvas canvas, Projection projection) throws Exception {
        this.mPath.rewind();
        this.mOutline.setClipArea(projection);
        PointL pointLBuildPathPortion = this.mOutline.buildPathPortion(projection, null, this.mMilestoneManagers.size() > 0);
        for (MilestoneManager milestoneManager : this.mMilestoneManagers) {
            milestoneManager.init();
            milestoneManager.setDistances(this.mOutline.getDistances());
            Iterator<PointL> it = this.mOutline.getPointsForMilestones().iterator();
            while (it.hasNext()) {
                PointL next = it.next();
                milestoneManager.add(next.x, next.y);
            }
            milestoneManager.end();
        }
        List<LinearRing> list = this.mHoles;
        if (list != null) {
            for (LinearRing linearRing : list) {
                linearRing.setClipArea(projection);
                linearRing.buildPathPortion(projection, pointLBuildPathPortion, this.mMilestoneManagers.size() > 0);
            }
            this.mPath.setFillType(Path.FillType.EVEN_ODD);
        }
        if (isVisible(this.mFillPaint)) {
            canvas.drawPath(this.mPath, this.mFillPaint);
        }
        if (isVisible(this.mOutlinePaint)) {
            canvas.drawPath(this.mPath, this.mOutlinePaint);
        }
        Iterator<MilestoneManager> it2 = this.mMilestoneManagers.iterator();
        while (it2.hasNext()) {
            it2.next().draw(canvas);
        }
        if (isInfoWindowOpen() && this.mInfoWindow != null && this.mInfoWindow.getRelatedObject() == this) {
            this.mInfoWindow.draw();
        }
    }

    private void drawWithLines(Canvas canvas, Projection projection) throws Exception {
        this.mLineDrawer.setCanvas(canvas);
        this.mOutline.setClipArea(projection);
        boolean z = this.mMilestoneManagers.size() > 0;
        if (this.mIsPaintOrPaintList) {
            this.mLineDrawer.setPaint(getOutlinePaint());
            this.mOutline.buildLinePortion(projection, z);
        } else {
            Iterator<PaintList> it = getOutlinePaintLists().iterator();
            while (it.hasNext()) {
                this.mLineDrawer.setPaint(it.next());
                this.mOutline.buildLinePortion(projection, z);
                z = false;
            }
        }
        for (MilestoneManager milestoneManager : this.mMilestoneManagers) {
            milestoneManager.init();
            milestoneManager.setDistances(this.mOutline.getDistances());
            Iterator<PointL> it2 = this.mOutline.getPointsForMilestones().iterator();
            while (it2.hasNext()) {
                PointL next = it2.next();
                milestoneManager.add(next.x, next.y);
            }
            milestoneManager.end();
        }
        Iterator<MilestoneManager> it3 = this.mMilestoneManagers.iterator();
        while (it3.hasNext()) {
            it3.next().draw(canvas);
        }
        if (isInfoWindowOpen() && this.mInfoWindow != null && this.mInfoWindow.getRelatedObject() == this) {
            this.mInfoWindow.draw();
        }
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        LinearRing linearRing = this.mOutline;
        if (linearRing != null) {
            linearRing.clear();
            this.mOutline = null;
        }
        this.mHoles.clear();
        this.mMilestoneManagers.clear();
        onDestroy();
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public BoundingBox getBounds() {
        return this.mOutline.getBoundingBox();
    }

    public void setPoints(List<GeoPoint> list) {
        this.mOutline.setPoints(list);
        setDefaultInfoWindowLocation();
    }

    public void addPoint(GeoPoint geoPoint) {
        this.mOutline.addPoint(geoPoint);
    }

    public List<GeoPoint> getActualPoints() {
        return this.mOutline.getPoints();
    }

    public void setDowngradeDisplay(boolean z) {
        this.mDowngradeDisplay = z;
    }

    public void setDowngradePixelSizes(int i, int i2) {
        this.mDowngradeMaximumRectanglePixelSize = i2;
        this.mDowngradeMaximumPixelSize = Math.max(i, i2);
    }

    private boolean isWorthDisplaying(Projection projection) {
        BoundingBox bounds = getBounds();
        projection.toPixels(new GeoPoint(bounds.getLatNorth(), bounds.getLonEast()), this.mDowngradeTopLeft);
        projection.toPixels(new GeoPoint(bounds.getLatSouth(), bounds.getLonWest()), this.mDowngradeBottomRight);
        double worldMapSize = projection.getWorldMapSize();
        return Math.abs(this.mDowngradeTopLeft.x - this.mDowngradeBottomRight.x) >= this.mDowngradeMaximumPixelSize && Math.abs(((long) this.mDowngradeTopLeft.x) - Math.round(LinearRing.getCloserValue((double) this.mDowngradeTopLeft.x, (double) this.mDowngradeBottomRight.x, worldMapSize))) >= ((long) this.mDowngradeMaximumPixelSize) && Math.abs(this.mDowngradeTopLeft.y - this.mDowngradeBottomRight.y) >= this.mDowngradeMaximumPixelSize && Math.abs(((long) this.mDowngradeTopLeft.y) - Math.round(LinearRing.getCloserValue((double) this.mDowngradeTopLeft.y, (double) this.mDowngradeBottomRight.y, worldMapSize))) >= ((long) this.mDowngradeMaximumPixelSize);
    }

    private boolean isVisible(Paint paint) {
        return (paint == null || paint.getColor() == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void displayDowngrade(android.graphics.Canvas r23, org.osmdroid.views.Projection r24) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.views.overlay.PolyOverlayWithIW.displayDowngrade(android.graphics.Canvas, org.osmdroid.views.Projection):void");
    }

    public void setDensityMultiplier(float f) {
        this.mDensityMultiplier = f;
    }

    public boolean contains(MotionEvent motionEvent) {
        if (this.mPath.isEmpty()) {
            return false;
        }
        RectF rectF = new RectF();
        this.mPath.computeBounds(rectF, true);
        Region region = new Region();
        region.setPath(this.mPath, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        return region.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public boolean isCloseTo(GeoPoint geoPoint, double d, MapView mapView) {
        return getCloseTo(geoPoint, d, mapView) != null;
    }

    public GeoPoint getCloseTo(GeoPoint geoPoint, double d, MapView mapView) {
        return this.mOutline.getCloseTo(geoPoint, d, mapView.getProjection(), this.mClosePath);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView) {
        GeoPoint closeTo = (GeoPoint) mapView.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY());
        if (this.mPath != null) {
            if (!contains(motionEvent)) {
                closeTo = null;
            }
        } else {
            closeTo = getCloseTo(closeTo, this.mOutlinePaint.getStrokeWidth() * this.mDensity * this.mDensityMultiplier, mapView);
        }
        if (closeTo != null) {
            return click(mapView, closeTo);
        }
        return false;
    }
}
