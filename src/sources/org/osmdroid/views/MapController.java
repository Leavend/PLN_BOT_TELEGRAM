package org.osmdroid.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
import java.util.LinkedList;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MyMath;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;

/* loaded from: classes3.dex */
public class MapController implements IMapController, MapView.OnFirstLayoutListener {
    private Animator mCurrentAnimator;
    protected final MapView mMapView;
    private ScaleAnimation mZoomInAnimationOld;
    private ScaleAnimation mZoomOutAnimationOld;
    private double mTargetZoomLevel = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private ReplayController mReplayController = new ReplayController(this, null);

    private enum ReplayType {
        ZoomToSpanPoint,
        AnimateToPoint,
        AnimateToGeoPoint,
        SetCenterPoint
    }

    public MapController(MapView mapView) {
        this.mMapView = mapView;
        if (mapView.isLayoutOccurred()) {
            return;
        }
        mapView.addOnFirstLayoutListener(this);
    }

    @Override // org.osmdroid.views.MapView.OnFirstLayoutListener
    public void onFirstLayout(View view, int i, int i2, int i3, int i4) {
        this.mReplayController.replayCalls();
    }

    @Override // org.osmdroid.api.IMapController
    public void zoomToSpan(double d, double d2) {
        if (d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        if (!this.mMapView.isLayoutOccurred()) {
            this.mReplayController.zoomToSpan(d, d2);
            return;
        }
        BoundingBox boundingBox = this.mMapView.getProjection().getBoundingBox();
        double zoomLevel = this.mMapView.getProjection().getZoomLevel();
        double dMax = Math.max(d / boundingBox.getLatitudeSpan(), d2 / boundingBox.getLongitudeSpan());
        if (dMax > 1.0d) {
            this.mMapView.setZoomLevel(zoomLevel - MyMath.getNextSquareNumberAbove((float) dMax));
        } else if (dMax < 0.5d) {
            this.mMapView.setZoomLevel((zoomLevel + MyMath.getNextSquareNumberAbove(1.0f / ((float) dMax))) - 1.0d);
        }
    }

    @Override // org.osmdroid.api.IMapController
    public void zoomToSpan(int i, int i2) {
        zoomToSpan(i * 1.0E-6d, i2 * 1.0E-6d);
    }

    @Override // org.osmdroid.api.IMapController
    public void animateTo(IGeoPoint iGeoPoint) {
        animateTo(iGeoPoint, null, null);
    }

    @Override // org.osmdroid.api.IMapController
    public void animateTo(IGeoPoint iGeoPoint, Double d, Long l, Float f) {
        animateTo(iGeoPoint, d, l, f, null);
    }

    @Override // org.osmdroid.api.IMapController
    public void animateTo(IGeoPoint iGeoPoint, Double d, Long l, Float f, Boolean bool) {
        if (!this.mMapView.isLayoutOccurred()) {
            this.mReplayController.animateTo(iGeoPoint, d, l, f, bool);
            return;
        }
        MapAnimatorListener mapAnimatorListener = new MapAnimatorListener(this, Double.valueOf(this.mMapView.getZoomLevelDouble()), d, new GeoPoint(this.mMapView.getProjection().getCurrentCenter()), iGeoPoint, Float.valueOf(this.mMapView.getMapOrientation()), f, bool);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addListener(mapAnimatorListener);
        valueAnimatorOfFloat.addUpdateListener(mapAnimatorListener);
        if (l == null) {
            valueAnimatorOfFloat.setDuration(Configuration.getInstance().getAnimationSpeedDefault());
        } else {
            valueAnimatorOfFloat.setDuration(l.longValue());
        }
        Animator animator = this.mCurrentAnimator;
        if (animator != null) {
            mapAnimatorListener.onAnimationCancel(animator);
        }
        this.mCurrentAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.start();
    }

    @Override // org.osmdroid.api.IMapController
    public void animateTo(IGeoPoint iGeoPoint, Double d, Long l) {
        animateTo(iGeoPoint, d, l, null);
    }

    @Override // org.osmdroid.api.IMapController
    public void animateTo(int i, int i2) {
        if (!this.mMapView.isLayoutOccurred()) {
            this.mReplayController.animateTo(i, i2);
            return;
        }
        if (this.mMapView.isAnimating()) {
            return;
        }
        this.mMapView.mIsFlinging = false;
        int mapScrollX = (int) this.mMapView.getMapScrollX();
        int mapScrollY = (int) this.mMapView.getMapScrollY();
        int width = i - (this.mMapView.getWidth() / 2);
        int height = i2 - (this.mMapView.getHeight() / 2);
        if (width == mapScrollX && height == mapScrollY) {
            return;
        }
        this.mMapView.getScroller().startScroll(mapScrollX, mapScrollY, width, height, Configuration.getInstance().getAnimationSpeedDefault());
        this.mMapView.postInvalidate();
    }

    @Override // org.osmdroid.api.IMapController
    public void scrollBy(int i, int i2) {
        this.mMapView.scrollBy(i, i2);
    }

    @Override // org.osmdroid.api.IMapController
    public void setCenter(IGeoPoint iGeoPoint) {
        if (!this.mMapView.isLayoutOccurred()) {
            this.mReplayController.setCenter(iGeoPoint);
        } else {
            this.mMapView.setExpectedCenter(iGeoPoint);
        }
    }

    @Override // org.osmdroid.api.IMapController
    public void stopPanning() {
        this.mMapView.mIsFlinging = false;
        this.mMapView.getScroller().forceFinished(true);
    }

    @Override // org.osmdroid.api.IMapController
    public void stopAnimation(boolean z) {
        if (!this.mMapView.getScroller().isFinished()) {
            if (z) {
                this.mMapView.mIsFlinging = false;
                this.mMapView.getScroller().abortAnimation();
            } else {
                stopPanning();
            }
        }
        Animator animator = this.mCurrentAnimator;
        if (this.mMapView.mIsAnimating.get()) {
            if (z) {
                animator.end();
            } else {
                animator.cancel();
            }
        }
    }

    @Override // org.osmdroid.api.IMapController
    public int setZoom(int i) {
        return (int) setZoom(i);
    }

    @Override // org.osmdroid.api.IMapController
    public double setZoom(double d) {
        return this.mMapView.setZoomLevel(d);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomIn() {
        return zoomIn(null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomIn(Long l) {
        return zoomTo(this.mMapView.getZoomLevelDouble() + 1.0d, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomInFixing(int i, int i2, Long l) {
        return zoomToFixing(this.mMapView.getZoomLevelDouble() + 1.0d, i, i2, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomInFixing(int i, int i2) {
        return zoomInFixing(i, i2, null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomOut(Long l) {
        return zoomTo(this.mMapView.getZoomLevelDouble() - 1.0d, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomOut() {
        return zoomOut(null);
    }

    @Override // org.osmdroid.api.IMapController
    @Deprecated
    public boolean zoomOutFixing(int i, int i2) {
        return zoomToFixing(this.mMapView.getZoomLevelDouble() - 1.0d, i, i2, (Long) null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomTo(int i) {
        return zoomTo(i, (Long) null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomTo(int i, Long l) {
        return zoomTo(i, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomToFixing(int i, int i2, int i3, Long l) {
        return zoomToFixing(i, i2, i3, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomTo(double d, Long l) {
        return zoomToFixing(d, this.mMapView.getWidth() / 2, this.mMapView.getHeight() / 2, l);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomTo(double d) {
        return zoomTo(d, (Long) null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomToFixing(double d, int i, int i2, Long l) {
        double maxZoomLevel = d > this.mMapView.getMaxZoomLevel() ? this.mMapView.getMaxZoomLevel() : d;
        if (maxZoomLevel < this.mMapView.getMinZoomLevel()) {
            maxZoomLevel = this.mMapView.getMinZoomLevel();
        }
        double zoomLevelDouble = this.mMapView.getZoomLevelDouble();
        if (!((maxZoomLevel < zoomLevelDouble && this.mMapView.canZoomOut()) || (maxZoomLevel > zoomLevelDouble && this.mMapView.canZoomIn())) || this.mMapView.mIsAnimating.getAndSet(true)) {
            return false;
        }
        ZoomEvent zoomEvent = null;
        for (MapListener mapListener : this.mMapView.mListners) {
            if (zoomEvent == null) {
                zoomEvent = new ZoomEvent(this.mMapView, maxZoomLevel);
            }
            mapListener.onZoom(zoomEvent);
        }
        this.mMapView.setMultiTouchScaleInitPoint(i, i2);
        this.mMapView.startAnimation();
        Math.pow(2.0d, maxZoomLevel - zoomLevelDouble);
        MapAnimatorListener mapAnimatorListener = new MapAnimatorListener(this, Double.valueOf(zoomLevelDouble), Double.valueOf(maxZoomLevel), null, null, null, null, null);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addListener(mapAnimatorListener);
        valueAnimatorOfFloat.addUpdateListener(mapAnimatorListener);
        if (l == null) {
            valueAnimatorOfFloat.setDuration(Configuration.getInstance().getAnimationSpeedShort());
        } else {
            valueAnimatorOfFloat.setDuration(l.longValue());
        }
        this.mCurrentAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.start();
        return true;
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomToFixing(double d, int i, int i2) {
        return zoomToFixing(d, i, i2, (Long) null);
    }

    @Override // org.osmdroid.api.IMapController
    public boolean zoomToFixing(int i, int i2, int i3) {
        return zoomToFixing(i, i2, i3, (Long) null);
    }

    protected void onAnimationStart() {
        this.mMapView.mIsAnimating.set(true);
    }

    protected void onAnimationEnd() {
        this.mMapView.mIsAnimating.set(false);
        this.mMapView.resetMultiTouchScale();
        this.mCurrentAnimator = null;
        this.mMapView.invalidate();
    }

    private static class MapAnimatorListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private final GeoPoint mCenter = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        private final IGeoPoint mCenterEnd;
        private final IGeoPoint mCenterStart;
        private final MapController mMapController;
        private final Float mOrientationSpan;
        private final Float mOrientationStart;
        private final Double mZoomEnd;
        private final Double mZoomStart;

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        public MapAnimatorListener(MapController mapController, Double d, Double d2, IGeoPoint iGeoPoint, IGeoPoint iGeoPoint2, Float f, Float f2, Boolean bool) {
            this.mMapController = mapController;
            this.mZoomStart = d;
            this.mZoomEnd = d2;
            this.mCenterStart = iGeoPoint;
            this.mCenterEnd = iGeoPoint2;
            if (f2 == null) {
                this.mOrientationStart = null;
                this.mOrientationSpan = null;
            } else {
                this.mOrientationStart = f;
                this.mOrientationSpan = Float.valueOf((float) MyMath.getAngleDifference(f.floatValue(), f2.floatValue(), bool));
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.mMapController.onAnimationStart();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.mMapController.onAnimationEnd();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.mMapController.onAnimationEnd();
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (this.mZoomEnd != null) {
                this.mMapController.mMapView.setZoomLevel(this.mZoomStart.doubleValue() + ((this.mZoomEnd.doubleValue() - this.mZoomStart.doubleValue()) * fFloatValue));
            }
            if (this.mOrientationSpan != null) {
                this.mMapController.mMapView.setMapOrientation(this.mOrientationStart.floatValue() + (this.mOrientationSpan.floatValue() * fFloatValue));
            }
            if (this.mCenterEnd != null) {
                MapView mapView = this.mMapController.mMapView;
                TileSystem tileSystem = MapView.getTileSystem();
                double dCleanLongitude = tileSystem.cleanLongitude(this.mCenterStart.getLongitude());
                double d = fFloatValue;
                double dCleanLongitude2 = tileSystem.cleanLongitude(dCleanLongitude + ((tileSystem.cleanLongitude(this.mCenterEnd.getLongitude()) - dCleanLongitude) * d));
                double dCleanLatitude = tileSystem.cleanLatitude(this.mCenterStart.getLatitude());
                this.mCenter.setCoords(tileSystem.cleanLatitude(dCleanLatitude + ((tileSystem.cleanLatitude(this.mCenterEnd.getLatitude()) - dCleanLatitude) * d)), dCleanLongitude2);
                this.mMapController.mMapView.setExpectedCenter(this.mCenter);
            }
            this.mMapController.mMapView.invalidate();
        }
    }

    protected static class ZoomAnimationListener implements Animation.AnimationListener {
        private MapController mMapController;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        public ZoomAnimationListener(MapController mapController) {
            this.mMapController = mapController;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            this.mMapController.onAnimationStart();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.mMapController.onAnimationEnd();
        }
    }

    private class ReplayController {
        private LinkedList<ReplayClass> mReplayList;

        private ReplayController() {
            this.mReplayList = new LinkedList<>();
        }

        /* synthetic */ ReplayController(MapController mapController, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void animateTo(IGeoPoint iGeoPoint, Double d, Long l, Float f, Boolean bool) {
            this.mReplayList.add(new ReplayClass(ReplayType.AnimateToGeoPoint, null, iGeoPoint, d, l, f, bool));
        }

        public void animateTo(int i, int i2) {
            this.mReplayList.add(new ReplayClass(this, ReplayType.AnimateToPoint, new Point(i, i2), null));
        }

        public void setCenter(IGeoPoint iGeoPoint) {
            this.mReplayList.add(new ReplayClass(this, ReplayType.SetCenterPoint, null, iGeoPoint));
        }

        public void zoomToSpan(int i, int i2) {
            this.mReplayList.add(new ReplayClass(this, ReplayType.ZoomToSpanPoint, new Point(i, i2), null));
        }

        public void zoomToSpan(double d, double d2) {
            this.mReplayList.add(new ReplayClass(this, ReplayType.ZoomToSpanPoint, new Point((int) (d * 1000000.0d), (int) (d2 * 1000000.0d)), null));
        }

        public void replayCalls() {
            Iterator<ReplayClass> it = this.mReplayList.iterator();
            while (it.hasNext()) {
                ReplayClass next = it.next();
                int i = AnonymousClass1.$SwitchMap$org$osmdroid$views$MapController$ReplayType[next.mReplayType.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i == 4 && next.mPoint != null) {
                                MapController.this.zoomToSpan(next.mPoint.x, next.mPoint.y);
                            }
                        } else if (next.mGeoPoint != null) {
                            MapController.this.setCenter(next.mGeoPoint);
                        }
                    } else if (next.mPoint != null) {
                        MapController.this.animateTo(next.mPoint.x, next.mPoint.y);
                    }
                } else if (next.mGeoPoint != null) {
                    MapController.this.animateTo(next.mGeoPoint, next.mZoom, next.mSpeed, next.mOrientation, next.mClockwise);
                }
            }
            this.mReplayList.clear();
        }

        private class ReplayClass {
            private final Boolean mClockwise;
            private IGeoPoint mGeoPoint;
            private final Float mOrientation;
            private Point mPoint;
            private ReplayType mReplayType;
            private final Long mSpeed;
            private final Double mZoom;

            public ReplayClass(ReplayController replayController, ReplayType replayType, Point point, IGeoPoint iGeoPoint) {
                this(replayType, point, iGeoPoint, null, null, null, null);
            }

            public ReplayClass(ReplayType replayType, Point point, IGeoPoint iGeoPoint, Double d, Long l, Float f, Boolean bool) {
                this.mReplayType = replayType;
                this.mPoint = point;
                this.mGeoPoint = iGeoPoint;
                this.mSpeed = l;
                this.mZoom = d;
                this.mOrientation = f;
                this.mClockwise = bool;
            }
        }
    }

    /* renamed from: org.osmdroid.views.MapController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$MapController$ReplayType;

        static {
            int[] iArr = new int[ReplayType.values().length];
            $SwitchMap$org$osmdroid$views$MapController$ReplayType = iArr;
            try {
                iArr[ReplayType.AnimateToGeoPoint.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$osmdroid$views$MapController$ReplayType[ReplayType.AnimateToPoint.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$osmdroid$views$MapController$ReplayType[ReplayType.SetCenterPoint.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$osmdroid$views$MapController$ReplayType[ReplayType.ZoomToSpanPoint.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
