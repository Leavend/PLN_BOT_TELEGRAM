package org.osmdroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.ZoomButtonsController;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.metalev.multitouch.controller.MultiTouchController;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.IStyledTileSource;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleInvalidationHandler;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.GeometryMath;
import org.osmdroid.util.TileSystem;
import org.osmdroid.util.TileSystemWebMercator;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.DefaultOverlayManager;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.TilesOverlay;

/* loaded from: classes3.dex */
public class MapView extends ViewGroup implements IMapView, MultiTouchController.MultiTouchObjectCanvas<Object> {
    private static TileSystem mTileSystem = new TileSystemWebMercator();
    private boolean enableFling;
    private boolean horizontalMapRepetitionEnabled;
    private GeoPoint mCenter;
    private final MapController mController;
    private boolean mDestroyModeOnDetach;
    private final GestureDetector mGestureDetector;
    private boolean mImpossibleFlinging;
    private final Rect mInvalidateRect;
    protected final AtomicBoolean mIsAnimating;
    protected boolean mIsFlinging;
    private boolean mLayoutOccurred;
    private final Point mLayoutPoint;
    protected List<MapListener> mListners;
    private int mMapCenterOffsetX;
    private int mMapCenterOffsetY;
    private TilesOverlay mMapOverlay;
    private long mMapScrollX;
    private long mMapScrollY;
    protected Double mMaximumZoomLevel;
    protected Double mMinimumZoomLevel;
    private MultiTouchController<Object> mMultiTouchController;
    private PointF mMultiTouchScaleCurrentPoint;
    private final GeoPoint mMultiTouchScaleGeoPoint;
    private final PointF mMultiTouchScaleInitPoint;
    private final LinkedList<OnFirstLayoutListener> mOnFirstLayoutListeners;
    private OverlayManager mOverlayManager;
    protected Projection mProjection;
    private final MapViewRepository mRepository;
    private final Rect mRescaleScreenRect;
    final Point mRotateScalePoint;
    private double mScrollableAreaLimitEast;
    private int mScrollableAreaLimitExtraPixelHeight;
    private int mScrollableAreaLimitExtraPixelWidth;
    private boolean mScrollableAreaLimitLatitude;
    private boolean mScrollableAreaLimitLongitude;
    private double mScrollableAreaLimitNorth;
    private double mScrollableAreaLimitSouth;
    private double mScrollableAreaLimitWest;
    private final Scroller mScroller;
    private double mStartAnimationZoom;
    private MapTileProviderBase mTileProvider;
    private Handler mTileRequestCompleteHandler;
    private float mTilesScaleFactor;
    private boolean mTilesScaledToDpi;
    private final CustomZoomButtonsController mZoomController;
    private double mZoomLevel;
    private boolean mZoomRounding;
    private float mapOrientation;
    private boolean pauseFling;
    private boolean verticalMapRepetitionEnabled;

    public interface OnFirstLayoutListener {
        void onFirstLayout(View view, int i, int i2, int i3, int i4);
    }

    @Deprecated
    public float getMapScale() {
        return 1.0f;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public MapView(Context context, MapTileProviderBase mapTileProviderBase, Handler handler, AttributeSet attributeSet) {
        this(context, mapTileProviderBase, handler, attributeSet, Configuration.getInstance().isMapViewHardwareAccelerated());
    }

    public MapView(Context context, MapTileProviderBase mapTileProviderBase, Handler handler, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.mZoomLevel = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mIsAnimating = new AtomicBoolean(false);
        this.mMultiTouchScaleInitPoint = new PointF();
        this.mMultiTouchScaleGeoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this.mapOrientation = 0.0f;
        this.mInvalidateRect = new Rect();
        this.mTilesScaledToDpi = false;
        this.mTilesScaleFactor = 1.0f;
        this.mRotateScalePoint = new Point();
        this.mLayoutPoint = new Point();
        this.mOnFirstLayoutListeners = new LinkedList<>();
        this.mLayoutOccurred = false;
        this.horizontalMapRepetitionEnabled = true;
        this.verticalMapRepetitionEnabled = true;
        this.mListners = new ArrayList();
        this.mRepository = new MapViewRepository(this);
        this.mRescaleScreenRect = new Rect();
        this.mDestroyModeOnDetach = true;
        this.enableFling = true;
        this.pauseFling = false;
        Configuration.getInstance().getOsmdroidTileCache(context);
        if (isInEditMode()) {
            this.mTileRequestCompleteHandler = null;
            this.mController = null;
            this.mZoomController = null;
            this.mScroller = null;
            this.mGestureDetector = null;
            return;
        }
        if (!z) {
            setLayerType(1, null);
        }
        this.mController = new MapController(this);
        this.mScroller = new Scroller(context);
        mapTileProviderBase = mapTileProviderBase == null ? new MapTileProviderBasic(context.getApplicationContext(), getTileSourceFromAttributes(attributeSet)) : mapTileProviderBase;
        this.mTileRequestCompleteHandler = handler == null ? new SimpleInvalidationHandler(this) : handler;
        this.mTileProvider = mapTileProviderBase;
        mapTileProviderBase.getTileRequestCompleteHandlers().add(this.mTileRequestCompleteHandler);
        updateTileSizeForDensity(this.mTileProvider.getTileSource());
        this.mMapOverlay = new TilesOverlay(this.mTileProvider, context, this.horizontalMapRepetitionEnabled, this.verticalMapRepetitionEnabled);
        this.mOverlayManager = new DefaultOverlayManager(this.mMapOverlay);
        CustomZoomButtonsController customZoomButtonsController = new CustomZoomButtonsController(this);
        this.mZoomController = customZoomButtonsController;
        customZoomButtonsController.setOnZoomListener(new MapViewZoomListener());
        checkZoomButtons();
        GestureDetector gestureDetector = new GestureDetector(context, new MapViewGestureDetectorListener());
        this.mGestureDetector = gestureDetector;
        gestureDetector.setOnDoubleTapListener(new MapViewDoubleClickListener());
        if (Configuration.getInstance().isMapViewRecyclerFriendly()) {
            setHasTransientState(true);
        }
        customZoomButtonsController.setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        this(context, null, null, attributeSet);
    }

    public MapView(Context context) {
        this(context, null, null, null);
    }

    public MapView(Context context, MapTileProviderBase mapTileProviderBase) {
        this(context, mapTileProviderBase, null);
    }

    public MapView(Context context, MapTileProviderBase mapTileProviderBase, Handler handler) {
        this(context, mapTileProviderBase, handler, null);
    }

    @Override // org.osmdroid.api.IMapView
    public IMapController getController() {
        return this.mController;
    }

    public List<Overlay> getOverlays() {
        return getOverlayManager().overlays();
    }

    public OverlayManager getOverlayManager() {
        return this.mOverlayManager;
    }

    public void setOverlayManager(OverlayManager overlayManager) {
        this.mOverlayManager = overlayManager;
    }

    public MapTileProviderBase getTileProvider() {
        return this.mTileProvider;
    }

    public Scroller getScroller() {
        return this.mScroller;
    }

    public Handler getTileRequestCompleteHandler() {
        return this.mTileRequestCompleteHandler;
    }

    @Override // org.osmdroid.api.IMapView
    public double getLatitudeSpanDouble() {
        return getBoundingBox().getLatitudeSpan();
    }

    @Override // org.osmdroid.api.IMapView
    public double getLongitudeSpanDouble() {
        return getBoundingBox().getLongitudeSpan();
    }

    public BoundingBox getBoundingBox() {
        return getProjection().getBoundingBox();
    }

    public Rect getScreenRect(Rect rect) {
        Rect intrinsicScreenRect = getIntrinsicScreenRect(rect);
        if (getMapOrientation() != 0.0f && getMapOrientation() != 180.0f) {
            GeometryMath.getBoundingBoxForRotatatedRectangle(intrinsicScreenRect, intrinsicScreenRect.centerX(), intrinsicScreenRect.centerY(), getMapOrientation(), intrinsicScreenRect);
        }
        return intrinsicScreenRect;
    }

    public Rect getIntrinsicScreenRect(Rect rect) {
        if (rect == null) {
            rect = new Rect();
        }
        rect.set(0, 0, getWidth(), getHeight());
        return rect;
    }

    @Override // org.osmdroid.api.IMapView
    public Projection getProjection() {
        if (this.mProjection == null) {
            Projection projection = new Projection(this);
            this.mProjection = projection;
            projection.adjustOffsets(this.mMultiTouchScaleGeoPoint, this.mMultiTouchScaleCurrentPoint);
            if (this.mScrollableAreaLimitLatitude) {
                projection.adjustOffsets(this.mScrollableAreaLimitNorth, this.mScrollableAreaLimitSouth, true, this.mScrollableAreaLimitExtraPixelHeight);
            }
            if (this.mScrollableAreaLimitLongitude) {
                projection.adjustOffsets(this.mScrollableAreaLimitWest, this.mScrollableAreaLimitEast, false, this.mScrollableAreaLimitExtraPixelWidth);
            }
            this.mImpossibleFlinging = projection.setMapScroll(this);
        }
        return this.mProjection;
    }

    @Deprecated
    protected void setProjection(Projection projection) {
        this.mProjection = projection;
    }

    private void resetProjection() {
        this.mProjection = null;
    }

    @Deprecated
    void setMapCenter(IGeoPoint iGeoPoint) {
        getController().animateTo(iGeoPoint);
    }

    @Deprecated
    void setMapCenter(int i, int i2) {
        setMapCenter(new GeoPoint(i, i2));
    }

    @Deprecated
    void setMapCenter(double d, double d2) {
        setMapCenter(new GeoPoint(d, d2));
    }

    public boolean isTilesScaledToDpi() {
        return this.mTilesScaledToDpi;
    }

    public void setTilesScaledToDpi(boolean z) {
        this.mTilesScaledToDpi = z;
        updateTileSizeForDensity(getTileProvider().getTileSource());
    }

    public float getTilesScaleFactor() {
        return this.mTilesScaleFactor;
    }

    public void setTilesScaleFactor(float f) {
        this.mTilesScaleFactor = f;
        updateTileSizeForDensity(getTileProvider().getTileSource());
    }

    public void resetTilesScaleFactor() {
        this.mTilesScaleFactor = 1.0f;
        updateTileSizeForDensity(getTileProvider().getTileSource());
    }

    private void updateTileSizeForDensity(ITileSource iTileSource) {
        float tileSizePixels = iTileSource.getTileSizePixels();
        int i = (int) (tileSizePixels * (isTilesScaledToDpi() ? ((getResources().getDisplayMetrics().density * 256.0f) / tileSizePixels) * this.mTilesScaleFactor : this.mTilesScaleFactor));
        if (Configuration.getInstance().isDebugMapView()) {
            Log.d(IMapView.LOGTAG, "Scaling tiles to " + i);
        }
        TileSystem.setTileSize(i);
    }

    public void setTileSource(ITileSource iTileSource) {
        this.mTileProvider.setTileSource(iTileSource);
        updateTileSizeForDensity(iTileSource);
        checkZoomButtons();
        setZoomLevel(this.mZoomLevel);
        postInvalidate();
    }

    double setZoomLevel(double d) {
        double dMax = Math.max(getMinZoomLevel(), Math.min(getMaxZoomLevel(), d));
        double d2 = this.mZoomLevel;
        if (dMax != d2) {
            Scroller scroller = this.mScroller;
            if (scroller != null) {
                scroller.forceFinished(true);
            }
            this.mIsFlinging = false;
        }
        GeoPoint currentCenter = getProjection().getCurrentCenter();
        this.mZoomLevel = dMax;
        setExpectedCenter(currentCenter);
        checkZoomButtons();
        ZoomEvent zoomEvent = null;
        if (isLayoutOccurred()) {
            getController().setCenter(currentCenter);
            Point point = new Point();
            Projection projection = getProjection();
            if (getOverlayManager().onSnapToItem((int) this.mMultiTouchScaleInitPoint.x, (int) this.mMultiTouchScaleInitPoint.y, point, this)) {
                getController().animateTo(projection.fromPixels(point.x, point.y, null, false));
            }
            this.mTileProvider.rescaleCache(projection, dMax, d2, getScreenRect(this.mRescaleScreenRect));
            this.pauseFling = true;
        }
        if (dMax != d2) {
            for (MapListener mapListener : this.mListners) {
                if (zoomEvent == null) {
                    zoomEvent = new ZoomEvent(this, dMax);
                }
                mapListener.onZoom(zoomEvent);
            }
        }
        requestLayout();
        invalidate();
        return this.mZoomLevel;
    }

    public void zoomToBoundingBox(BoundingBox boundingBox, boolean z) {
        zoomToBoundingBox(boundingBox, z, 0);
    }

    public double zoomToBoundingBox(BoundingBox boundingBox, boolean z, int i, double d, Long l) {
        int i2 = i * 2;
        double boundingBoxZoom = mTileSystem.getBoundingBoxZoom(boundingBox, getWidth() - i2, getHeight() - i2);
        if (boundingBoxZoom == Double.MIN_VALUE || boundingBoxZoom > d) {
            boundingBoxZoom = d;
        }
        double dMin = Math.min(getMaxZoomLevel(), Math.max(boundingBoxZoom, getMinZoomLevel()));
        GeoPoint centerWithDateLine = boundingBox.getCenterWithDateLine();
        Projection projection = new Projection(dMin, getWidth(), getHeight(), centerWithDateLine, getMapOrientation(), isHorizontalMapRepetitionEnabled(), isVerticalMapRepetitionEnabled(), getMapCenterOffsetX(), getMapCenterOffsetY());
        Point point = new Point();
        double centerLongitude = boundingBox.getCenterLongitude();
        projection.toPixels(new GeoPoint(boundingBox.getActualNorth(), centerLongitude), point);
        int i3 = point.y;
        projection.toPixels(new GeoPoint(boundingBox.getActualSouth(), centerLongitude), point);
        int height = ((getHeight() - point.y) - i3) / 2;
        if (height != 0) {
            projection.adjustOffsets(0L, height);
            projection.fromPixels(getWidth() / 2, getHeight() / 2, centerWithDateLine);
        }
        if (z) {
            getController().animateTo(centerWithDateLine, Double.valueOf(dMin), l);
        } else {
            getController().setZoom(dMin);
            getController().setCenter(centerWithDateLine);
        }
        return dMin;
    }

    public void zoomToBoundingBox(BoundingBox boundingBox, boolean z, int i) {
        zoomToBoundingBox(boundingBox, z, i, getMaxZoomLevel(), null);
    }

    @Override // org.osmdroid.api.IMapView
    @Deprecated
    public int getZoomLevel() {
        return (int) getZoomLevelDouble();
    }

    @Override // org.osmdroid.api.IMapView
    public double getZoomLevelDouble() {
        return this.mZoomLevel;
    }

    @Deprecated
    public double getZoomLevel(boolean z) {
        return getZoomLevelDouble();
    }

    public double getMinZoomLevel() {
        Double d = this.mMinimumZoomLevel;
        return d == null ? this.mMapOverlay.getMinimumZoomLevel() : d.doubleValue();
    }

    @Override // org.osmdroid.api.IMapView
    public double getMaxZoomLevel() {
        Double d = this.mMaximumZoomLevel;
        return d == null ? this.mMapOverlay.getMaximumZoomLevel() : d.doubleValue();
    }

    public void setMinZoomLevel(Double d) {
        this.mMinimumZoomLevel = d;
    }

    public void setMaxZoomLevel(Double d) {
        this.mMaximumZoomLevel = d;
    }

    public boolean canZoomIn() {
        return this.mZoomLevel < getMaxZoomLevel();
    }

    public boolean canZoomOut() {
        return this.mZoomLevel > getMinZoomLevel();
    }

    @Deprecated
    boolean zoomIn() {
        return getController().zoomIn();
    }

    @Deprecated
    boolean zoomInFixing(IGeoPoint iGeoPoint) {
        Point pixels = getProjection().toPixels(iGeoPoint, null);
        return getController().zoomInFixing(pixels.x, pixels.y);
    }

    @Deprecated
    boolean zoomInFixing(int i, int i2) {
        return getController().zoomInFixing(i, i2);
    }

    @Deprecated
    boolean zoomOut() {
        return getController().zoomOut();
    }

    @Deprecated
    boolean zoomOutFixing(IGeoPoint iGeoPoint) {
        Point pixels = getProjection().toPixels(iGeoPoint, null);
        return zoomOutFixing(pixels.x, pixels.y);
    }

    @Deprecated
    boolean zoomOutFixing(int i, int i2) {
        return getController().zoomOutFixing(i, i2);
    }

    @Override // org.osmdroid.api.IMapView
    public IGeoPoint getMapCenter() {
        return getMapCenter(null);
    }

    public IGeoPoint getMapCenter(GeoPoint geoPoint) {
        return getProjection().fromPixels(getWidth() / 2, getHeight() / 2, geoPoint, false);
    }

    public void setMapOrientation(float f) {
        setMapOrientation(f, true);
    }

    public void setMapOrientation(float f, boolean z) {
        this.mapOrientation = f % 360.0f;
        if (z) {
            requestLayout();
            invalidate();
        }
    }

    public float getMapOrientation() {
        return this.mapOrientation;
    }

    public boolean useDataConnection() {
        return this.mMapOverlay.useDataConnection();
    }

    public void setUseDataConnection(boolean z) {
        this.mMapOverlay.setUseDataConnection(z);
    }

    public void setScrollableAreaLimitDouble(BoundingBox boundingBox) {
        if (boundingBox == null) {
            resetScrollableAreaLimitLatitude();
            resetScrollableAreaLimitLongitude();
        } else {
            setScrollableAreaLimitLatitude(boundingBox.getActualNorth(), boundingBox.getActualSouth(), 0);
            setScrollableAreaLimitLongitude(boundingBox.getLonWest(), boundingBox.getLonEast(), 0);
        }
    }

    public void resetScrollableAreaLimitLatitude() {
        this.mScrollableAreaLimitLatitude = false;
    }

    public void resetScrollableAreaLimitLongitude() {
        this.mScrollableAreaLimitLongitude = false;
    }

    public void setScrollableAreaLimitLatitude(double d, double d2, int i) {
        this.mScrollableAreaLimitLatitude = true;
        this.mScrollableAreaLimitNorth = d;
        this.mScrollableAreaLimitSouth = d2;
        this.mScrollableAreaLimitExtraPixelHeight = i;
    }

    public void setScrollableAreaLimitLongitude(double d, double d2, int i) {
        this.mScrollableAreaLimitLongitude = true;
        this.mScrollableAreaLimitWest = d;
        this.mScrollableAreaLimitEast = d2;
        this.mScrollableAreaLimitExtraPixelWidth = i;
    }

    public boolean isScrollableAreaLimitLatitude() {
        return this.mScrollableAreaLimitLatitude;
    }

    public boolean isScrollableAreaLimitLongitude() {
        return this.mScrollableAreaLimitLongitude;
    }

    public void invalidateMapCoordinates(Rect rect) {
        invalidateMapCoordinates(rect.left, rect.top, rect.right, rect.bottom, false);
    }

    public void invalidateMapCoordinates(int i, int i2, int i3, int i4) {
        invalidateMapCoordinates(i, i2, i3, i4, false);
    }

    public void postInvalidateMapCoordinates(int i, int i2, int i3, int i4) {
        invalidateMapCoordinates(i, i2, i3, i4, true);
    }

    private void invalidateMapCoordinates(int i, int i2, int i3, int i4, boolean z) {
        this.mInvalidateRect.set(i, i2, i3, i4);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        if (getMapOrientation() != 0.0f) {
            GeometryMath.getBoundingBoxForRotatatedRectangle(this.mInvalidateRect, width, height, getMapOrientation() + 180.0f, this.mInvalidateRect);
        }
        if (z) {
            super.postInvalidate(this.mInvalidateRect.left, this.mInvalidateRect.top, this.mInvalidateRect.right, this.mInvalidateRect.bottom);
        } else {
            super.invalidate(this.mInvalidateRect);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2, null, 8, 0, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        myOnLayout(z, i, i2, i3, i4);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0062. Please report as an issue. */
    protected void myOnLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop;
        long paddingTop2;
        int i5;
        long j;
        int paddingTop3;
        resetProjection();
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int measuredWidth = childAt.getMeasuredWidth();
                getProjection().toPixels(layoutParams.geoPoint, this.mLayoutPoint);
                if (getMapOrientation() != 0.0f) {
                    Point pointRotateAndScalePoint = getProjection().rotateAndScalePoint(this.mLayoutPoint.x, this.mLayoutPoint.y, null);
                    this.mLayoutPoint.x = pointRotateAndScalePoint.x;
                    this.mLayoutPoint.y = pointRotateAndScalePoint.y;
                }
                long paddingLeft = this.mLayoutPoint.x;
                long j2 = this.mLayoutPoint.y;
                switch (layoutParams.alignment) {
                    case 1:
                        paddingLeft += getPaddingLeft();
                        paddingTop = getPaddingTop();
                        j2 += paddingTop;
                        break;
                    case 2:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - (measuredWidth / 2);
                        paddingTop = getPaddingTop();
                        j2 += paddingTop;
                        break;
                    case 3:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - measuredWidth;
                        paddingTop = getPaddingTop();
                        j2 += paddingTop;
                        break;
                    case 4:
                        paddingLeft += getPaddingLeft();
                        paddingTop2 = getPaddingTop() + j2;
                        i5 = measuredHeight / 2;
                        j = i5;
                        j2 = paddingTop2 - j;
                        break;
                    case 5:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - (measuredWidth / 2);
                        paddingTop2 = getPaddingTop() + j2;
                        i5 = measuredHeight / 2;
                        j = i5;
                        j2 = paddingTop2 - j;
                        break;
                    case 6:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - measuredWidth;
                        paddingTop2 = getPaddingTop() + j2;
                        i5 = measuredHeight / 2;
                        j = i5;
                        j2 = paddingTop2 - j;
                        break;
                    case 7:
                        paddingLeft += getPaddingLeft();
                        paddingTop3 = getPaddingTop();
                        paddingTop2 = paddingTop3 + j2;
                        j = measuredHeight;
                        j2 = paddingTop2 - j;
                        break;
                    case 8:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - (measuredWidth / 2);
                        paddingTop3 = getPaddingTop();
                        paddingTop2 = paddingTop3 + j2;
                        j = measuredHeight;
                        j2 = paddingTop2 - j;
                        break;
                    case 9:
                        paddingLeft = (getPaddingLeft() + paddingLeft) - measuredWidth;
                        paddingTop3 = getPaddingTop();
                        paddingTop2 = paddingTop3 + j2;
                        j = measuredHeight;
                        j2 = paddingTop2 - j;
                        break;
                }
                long j3 = paddingLeft + layoutParams.offsetX;
                long j4 = j2 + layoutParams.offsetY;
                childAt.layout(TileSystem.truncateToInt(j3), TileSystem.truncateToInt(j4), TileSystem.truncateToInt(j3 + measuredWidth), TileSystem.truncateToInt(j4 + measuredHeight));
            }
        }
        if (!isLayoutOccurred()) {
            this.mLayoutOccurred = true;
            Iterator<OnFirstLayoutListener> it = this.mOnFirstLayoutListeners.iterator();
            while (it.hasNext()) {
                it.next().onFirstLayout(this, i, i2, i3, i4);
            }
            this.mOnFirstLayoutListeners.clear();
        }
        resetProjection();
    }

    public void addOnFirstLayoutListener(OnFirstLayoutListener onFirstLayoutListener) {
        if (isLayoutOccurred()) {
            return;
        }
        this.mOnFirstLayoutListeners.add(onFirstLayoutListener);
    }

    public void removeOnFirstLayoutListener(OnFirstLayoutListener onFirstLayoutListener) {
        this.mOnFirstLayoutListeners.remove(onFirstLayoutListener);
    }

    public boolean isLayoutOccurred() {
        return this.mLayoutOccurred;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void onPause() {
        getOverlayManager().onPause();
    }

    public void onResume() {
        getOverlayManager().onResume();
    }

    public void onDetach() {
        getOverlayManager().onDetach(this);
        this.mTileProvider.detach();
        CustomZoomButtonsController customZoomButtonsController = this.mZoomController;
        if (customZoomButtonsController != null) {
            customZoomButtonsController.onDetach();
        }
        Handler handler = this.mTileRequestCompleteHandler;
        if (handler instanceof SimpleInvalidationHandler) {
            ((SimpleInvalidationHandler) handler).destroy();
        }
        this.mTileRequestCompleteHandler = null;
        Projection projection = this.mProjection;
        if (projection != null) {
            projection.detach();
        }
        this.mProjection = null;
        this.mRepository.onDetach();
        this.mListners.clear();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return getOverlayManager().onKeyDown(i, keyEvent, this) || super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return getOverlayManager().onKeyUp(i, keyEvent, this) || super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (getOverlayManager().onTrackballEvent(motionEvent, this)) {
            return true;
        }
        scrollBy((int) (motionEvent.getX() * 25.0f), (int) (motionEvent.getY() * 25.0f));
        return super.onTrackballEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (Configuration.getInstance().isDebugMapView()) {
            Log.d(IMapView.LOGTAG, "dispatchTouchEvent(" + motionEvent + ")");
        }
        if (this.mZoomController.isTouched(motionEvent)) {
            this.mZoomController.activate();
            return true;
        }
        MotionEvent motionEventRotateTouchEvent = rotateTouchEvent(motionEvent);
        try {
            if (super.dispatchTouchEvent(motionEvent)) {
                if (Configuration.getInstance().isDebugMapView()) {
                    Log.d(IMapView.LOGTAG, "super handled onTouchEvent");
                }
                return true;
            }
            if (getOverlayManager().onTouchEvent(motionEventRotateTouchEvent, this)) {
                if (motionEventRotateTouchEvent != motionEvent) {
                    motionEventRotateTouchEvent.recycle();
                }
                return true;
            }
            MultiTouchController<Object> multiTouchController = this.mMultiTouchController;
            if (multiTouchController == null || !multiTouchController.onTouchEvent(motionEvent)) {
                z = false;
            } else {
                if (Configuration.getInstance().isDebugMapView()) {
                    Log.d(IMapView.LOGTAG, "mMultiTouchController handled onTouchEvent");
                }
                z = true;
            }
            if (this.mGestureDetector.onTouchEvent(motionEventRotateTouchEvent)) {
                if (Configuration.getInstance().isDebugMapView()) {
                    Log.d(IMapView.LOGTAG, "mGestureDetector handled onTouchEvent");
                }
                z = true;
            }
            if (z) {
                if (motionEventRotateTouchEvent != motionEvent) {
                    motionEventRotateTouchEvent.recycle();
                }
                return true;
            }
            if (motionEventRotateTouchEvent != motionEvent) {
                motionEventRotateTouchEvent.recycle();
            }
            if (Configuration.getInstance().isDebugMapView()) {
                Log.d(IMapView.LOGTAG, "no-one handled onTouchEvent");
            }
            return false;
        } finally {
            if (motionEventRotateTouchEvent != motionEvent) {
                motionEventRotateTouchEvent.recycle();
            }
        }
    }

    private MotionEvent rotateTouchEvent(MotionEvent motionEvent) {
        if (getMapOrientation() == 0.0f) {
            return motionEvent;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.transform(getProjection().getInvertedScaleRotateCanvasMatrix());
        return motionEventObtain;
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mScroller;
        if (scroller != null && this.mIsFlinging && scroller.computeScrollOffset()) {
            if (this.mScroller.isFinished()) {
                this.mIsFlinging = false;
            } else {
                scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
                postInvalidate();
            }
        }
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        setMapScroll(i, i2);
        resetProjection();
        invalidate();
        if (getMapOrientation() != 0.0f) {
            myOnLayout(true, getLeft(), getTop(), getRight(), getBottom());
        }
        ScrollEvent scrollEvent = null;
        for (MapListener mapListener : this.mListners) {
            if (scrollEvent == null) {
                scrollEvent = new ScrollEvent(this, i, i2);
            }
            mapListener.onScroll(scrollEvent);
        }
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        scrollTo((int) (getMapScrollX() + i), (int) (getMapScrollY() + i2));
    }

    @Override // android.view.View, org.osmdroid.api.IMapView
    public void setBackgroundColor(int i) {
        this.mMapOverlay.setLoadingBackgroundColor(i);
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        resetProjection();
        getProjection().save(canvas, true, false);
        try {
            getOverlayManager().onDraw(canvas, this);
            getProjection().restore(canvas, false);
            CustomZoomButtonsController customZoomButtonsController = this.mZoomController;
            if (customZoomButtonsController != null) {
                customZoomButtonsController.draw(canvas);
            }
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            Log.e(IMapView.LOGTAG, "error dispatchDraw, probably in edit mode", e);
        }
        if (Configuration.getInstance().isDebugMapView()) {
            Log.d(IMapView.LOGTAG, "Rendering overall: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.mDestroyModeOnDetach) {
            onDetach();
        }
        super.onDetachedFromWindow();
    }

    public boolean isAnimating() {
        return this.mIsAnimating.get();
    }

    @Override // org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas
    public Object getDraggableObjectAtPoint(MultiTouchController.PointInfo pointInfo) {
        if (isAnimating()) {
            return null;
        }
        setMultiTouchScaleInitPoint(pointInfo.getX(), pointInfo.getY());
        return this;
    }

    @Override // org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas
    public void getPositionAndScale(Object obj, MultiTouchController.PositionAndScale positionAndScale) {
        startAnimation();
        positionAndScale.set(this.mMultiTouchScaleInitPoint.x, this.mMultiTouchScaleInitPoint.y, true, 1.0f, false, 0.0f, 0.0f, false, 0.0f);
    }

    @Override // org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas
    public void selectObject(Object obj, MultiTouchController.PointInfo pointInfo) {
        if (this.mZoomRounding) {
            this.mZoomLevel = Math.round(this.mZoomLevel);
            invalidate();
        }
        resetMultiTouchScale();
    }

    @Override // org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas
    public boolean setPositionAndScale(Object obj, MultiTouchController.PositionAndScale positionAndScale, MultiTouchController.PointInfo pointInfo) {
        setMultiTouchScaleCurrentPoint(positionAndScale.getXOff(), positionAndScale.getYOff());
        setMultiTouchScale(positionAndScale.getScale());
        requestLayout();
        invalidate();
        return true;
    }

    public void resetMultiTouchScale() {
        this.mMultiTouchScaleCurrentPoint = null;
    }

    protected void setMultiTouchScaleInitPoint(float f, float f2) {
        this.mMultiTouchScaleInitPoint.set(f, f2);
        Point pointUnrotateAndScalePoint = getProjection().unrotateAndScalePoint((int) f, (int) f2, null);
        getProjection().fromPixels(pointUnrotateAndScalePoint.x, pointUnrotateAndScalePoint.y, this.mMultiTouchScaleGeoPoint);
        setMultiTouchScaleCurrentPoint(f, f2);
    }

    protected void setMultiTouchScaleCurrentPoint(float f, float f2) {
        this.mMultiTouchScaleCurrentPoint = new PointF(f, f2);
    }

    protected void setMultiTouchScale(float f) {
        setZoomLevel((Math.log(f) / Math.log(2.0d)) + this.mStartAnimationZoom);
    }

    protected void startAnimation() {
        this.mStartAnimationZoom = getZoomLevelDouble();
    }

    @Deprecated
    public void setMapListener(MapListener mapListener) {
        this.mListners.add(mapListener);
    }

    public void addMapListener(MapListener mapListener) {
        this.mListners.add(mapListener);
    }

    public void removeMapListener(MapListener mapListener) {
        this.mListners.remove(mapListener);
    }

    private void checkZoomButtons() {
        this.mZoomController.setZoomInEnabled(canZoomIn());
        this.mZoomController.setZoomOutEnabled(canZoomOut());
    }

    @Deprecated
    public void setBuiltInZoomControls(boolean z) {
        CustomZoomButtonsController.Visibility visibility;
        CustomZoomButtonsController customZoomButtonsController = this.mZoomController;
        if (z) {
            visibility = CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT;
        } else {
            visibility = CustomZoomButtonsController.Visibility.NEVER;
        }
        customZoomButtonsController.setVisibility(visibility);
    }

    public void setMultiTouchControls(boolean z) {
        this.mMultiTouchController = z ? new MultiTouchController<>(this, false) : null;
    }

    public boolean isHorizontalMapRepetitionEnabled() {
        return this.horizontalMapRepetitionEnabled;
    }

    public void setHorizontalMapRepetitionEnabled(boolean z) {
        this.horizontalMapRepetitionEnabled = z;
        this.mMapOverlay.setHorizontalWrapEnabled(z);
        resetProjection();
        invalidate();
    }

    public boolean isVerticalMapRepetitionEnabled() {
        return this.verticalMapRepetitionEnabled;
    }

    public void setVerticalMapRepetitionEnabled(boolean z) {
        this.verticalMapRepetitionEnabled = z;
        this.mMapOverlay.setVerticalWrapEnabled(z);
        resetProjection();
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, org.osmdroid.tileprovider.tilesource.ITileSource] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.StringBuilder] */
    private ITileSource getTileSourceFromAttributes(AttributeSet attributeSet) {
        String attributeValue;
        OnlineTileSourceBase onlineTileSourceBase = TileSourceFactory.DEFAULT_TILE_SOURCE;
        if (attributeSet != null && (attributeValue = attributeSet.getAttributeValue(null, "tilesource")) != null) {
            try {
                ?? tileSource = TileSourceFactory.getTileSource(attributeValue);
                Log.i(IMapView.LOGTAG, "Using tile source specified in layout attributes: " + tileSource);
                onlineTileSourceBase = tileSource;
            } catch (IllegalArgumentException unused) {
                Log.w(IMapView.LOGTAG, "Invalid tile source specified in layout attributes: " + onlineTileSourceBase);
            }
        }
        if (attributeSet != null && (onlineTileSourceBase instanceof IStyledTileSource)) {
            String attributeValue2 = attributeSet.getAttributeValue(null, "style");
            if (attributeValue2 == null) {
                Log.i(IMapView.LOGTAG, "Using default style: 1");
            } else {
                Log.i(IMapView.LOGTAG, "Using style specified in layout attributes: " + attributeValue2);
                ((IStyledTileSource) onlineTileSourceBase).setStyle(attributeValue2);
            }
        }
        Log.i(IMapView.LOGTAG, "Using tile source: " + onlineTileSourceBase.name());
        return onlineTileSourceBase;
    }

    public void setFlingEnabled(boolean z) {
        this.enableFling = z;
    }

    public boolean isFlingEnabled() {
        return this.enableFling;
    }

    private class MapViewGestureDetectorListener implements GestureDetector.OnGestureListener {
        private MapViewGestureDetectorListener() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (MapView.this.mIsFlinging) {
                if (MapView.this.mScroller != null) {
                    MapView.this.mScroller.abortAnimation();
                }
                MapView.this.mIsFlinging = false;
            }
            if (!MapView.this.getOverlayManager().onDown(motionEvent, MapView.this) && MapView.this.mZoomController != null) {
                MapView.this.mZoomController.activate();
            }
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!MapView.this.enableFling || MapView.this.pauseFling) {
                MapView.this.pauseFling = false;
                return false;
            }
            if (MapView.this.getOverlayManager().onFling(motionEvent, motionEvent2, f, f2, MapView.this)) {
                return true;
            }
            if (MapView.this.mImpossibleFlinging) {
                MapView.this.mImpossibleFlinging = false;
                return false;
            }
            MapView.this.mIsFlinging = true;
            if (MapView.this.mScroller != null) {
                MapView.this.mScroller.fling((int) MapView.this.getMapScrollX(), (int) MapView.this.getMapScrollY(), -((int) f), -((int) f2), Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (MapView.this.mMultiTouchController == null || !MapView.this.mMultiTouchController.isPinching()) {
                MapView.this.getOverlayManager().onLongPress(motionEvent, MapView.this);
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (MapView.this.getOverlayManager().onScroll(motionEvent, motionEvent2, f, f2, MapView.this)) {
                return true;
            }
            MapView.this.scrollBy((int) f, (int) f2);
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            MapView.this.getOverlayManager().onShowPress(motionEvent, MapView.this);
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return MapView.this.getOverlayManager().onSingleTapUp(motionEvent, MapView.this);
        }
    }

    private class MapViewDoubleClickListener implements GestureDetector.OnDoubleTapListener {
        private MapViewDoubleClickListener() {
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (MapView.this.getOverlayManager().onDoubleTap(motionEvent, MapView.this)) {
                return true;
            }
            MapView.this.getProjection().rotateAndScalePoint((int) motionEvent.getX(), (int) motionEvent.getY(), MapView.this.mRotateScalePoint);
            return MapView.this.getController().zoomInFixing(MapView.this.mRotateScalePoint.x, MapView.this.mRotateScalePoint.y);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return MapView.this.getOverlayManager().onDoubleTapEvent(motionEvent, MapView.this);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return MapView.this.getOverlayManager().onSingleTapConfirmed(motionEvent, MapView.this);
        }
    }

    private class MapViewZoomListener implements CustomZoomButtonsController.OnZoomListener, ZoomButtonsController.OnZoomListener {
        @Override // org.osmdroid.views.CustomZoomButtonsController.OnZoomListener, android.widget.ZoomButtonsController.OnZoomListener
        public void onVisibilityChanged(boolean z) {
        }

        private MapViewZoomListener() {
        }

        @Override // org.osmdroid.views.CustomZoomButtonsController.OnZoomListener, android.widget.ZoomButtonsController.OnZoomListener
        public void onZoom(boolean z) {
            if (z) {
                MapView.this.getController().zoomIn();
            } else {
                MapView.this.getController().zoomOut();
            }
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public static final int BOTTOM_CENTER = 8;
        public static final int BOTTOM_LEFT = 7;
        public static final int BOTTOM_RIGHT = 9;
        public static final int CENTER = 5;
        public static final int CENTER_LEFT = 4;
        public static final int CENTER_RIGHT = 6;
        public static final int TOP_CENTER = 2;
        public static final int TOP_LEFT = 1;
        public static final int TOP_RIGHT = 3;
        public int alignment;
        public IGeoPoint geoPoint;
        public int offsetX;
        public int offsetY;

        public LayoutParams(int i, int i2, IGeoPoint iGeoPoint, int i3, int i4, int i5) {
            super(i, i2);
            if (iGeoPoint != null) {
                this.geoPoint = iGeoPoint;
            } else {
                this.geoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            }
            this.alignment = i3;
            this.offsetX = i4;
            this.offsetY = i5;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.geoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            this.alignment = 8;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public void setTileProvider(MapTileProviderBase mapTileProviderBase) {
        this.mTileProvider.detach();
        this.mTileProvider.clearTileCache();
        this.mTileProvider = mapTileProviderBase;
        mapTileProviderBase.getTileRequestCompleteHandlers().add(this.mTileRequestCompleteHandler);
        updateTileSizeForDensity(this.mTileProvider.getTileSource());
        TilesOverlay tilesOverlay = new TilesOverlay(this.mTileProvider, getContext(), this.horizontalMapRepetitionEnabled, this.verticalMapRepetitionEnabled);
        this.mMapOverlay = tilesOverlay;
        this.mOverlayManager.setTilesOverlay(tilesOverlay);
        invalidate();
    }

    @Deprecated
    public void setInitCenter(IGeoPoint iGeoPoint) {
        setExpectedCenter(iGeoPoint);
    }

    public long getMapScrollX() {
        return this.mMapScrollX;
    }

    public long getMapScrollY() {
        return this.mMapScrollY;
    }

    void setMapScroll(long j, long j2) {
        this.mMapScrollX = j;
        this.mMapScrollY = j2;
        requestLayout();
    }

    GeoPoint getExpectedCenter() {
        return this.mCenter;
    }

    public void setExpectedCenter(IGeoPoint iGeoPoint, long j, long j2) {
        GeoPoint currentCenter = getProjection().getCurrentCenter();
        this.mCenter = (GeoPoint) iGeoPoint;
        setMapScroll(-j, -j2);
        resetProjection();
        if (!getProjection().getCurrentCenter().equals(currentCenter)) {
            ScrollEvent scrollEvent = null;
            for (MapListener mapListener : this.mListners) {
                if (scrollEvent == null) {
                    scrollEvent = new ScrollEvent(this, 0, 0);
                }
                mapListener.onScroll(scrollEvent);
            }
        }
        invalidate();
    }

    public void setExpectedCenter(IGeoPoint iGeoPoint) {
        setExpectedCenter(iGeoPoint, 0L, 0L);
    }

    public void setZoomRounding(boolean z) {
        this.mZoomRounding = z;
    }

    public static TileSystem getTileSystem() {
        return mTileSystem;
    }

    public static void setTileSystem(TileSystem tileSystem) {
        mTileSystem = tileSystem;
    }

    public MapViewRepository getRepository() {
        return this.mRepository;
    }

    public CustomZoomButtonsController getZoomController() {
        return this.mZoomController;
    }

    public TilesOverlay getMapOverlay() {
        return this.mMapOverlay;
    }

    public void setDestroyMode(boolean z) {
        this.mDestroyModeOnDetach = z;
    }

    public int getMapCenterOffsetX() {
        return this.mMapCenterOffsetX;
    }

    public int getMapCenterOffsetY() {
        return this.mMapCenterOffsetY;
    }

    public void setMapCenterOffset(int i, int i2) {
        this.mMapCenterOffsetX = i;
        this.mMapCenterOffsetY = i2;
    }
}
