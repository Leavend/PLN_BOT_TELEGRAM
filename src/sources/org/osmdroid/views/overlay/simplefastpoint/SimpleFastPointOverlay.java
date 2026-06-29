package org.osmdroid.views.overlay.simplefastpoint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions;

/* loaded from: classes3.dex */
public class SimpleFastPointOverlay extends Overlay {
    private OnClickListener clickListener;
    private boolean[][] gridBool;
    private int gridHei;
    private List<StyledLabelledPoint> gridIndex;
    private int gridWid;
    private boolean hasMoved;
    private final BoundingBox mBoundingBox;
    private final PointAdapter mPointList;
    private Integer mSelectedPoint;
    private final SimpleFastPointOverlayOptions mStyle;
    private int numLabels;
    private BoundingBox prevBoundingBox;
    private BoundingBox startBoundingBox;
    private Projection startProjection;
    private int viewHei;
    private int viewWid;

    public interface OnClickListener {
        void onClick(PointAdapter pointAdapter, Integer num);
    }

    public interface PointAdapter extends Iterable<IGeoPoint> {
        IGeoPoint get(int i);

        boolean isLabelled();

        boolean isStyled();

        int size();
    }

    public class StyledLabelledPoint extends Point {
        private Paint mPointStyle;
        private Paint mTextStyle;
        private String mlabel;

        public StyledLabelledPoint(Point point, String str, Paint paint, Paint paint2) {
            super(point);
            this.mlabel = str;
            this.mPointStyle = paint;
            this.mTextStyle = paint2;
        }
    }

    public SimpleFastPointOverlayOptions getStyle() {
        return this.mStyle;
    }

    public SimpleFastPointOverlay(PointAdapter pointAdapter, SimpleFastPointOverlayOptions simpleFastPointOverlayOptions) {
        this.hasMoved = false;
        this.prevBoundingBox = new BoundingBox();
        this.mStyle = simpleFastPointOverlayOptions;
        this.mPointList = pointAdapter;
        Double dValueOf = null;
        Double dValueOf2 = null;
        Double dValueOf3 = null;
        Double dValueOf4 = null;
        for (IGeoPoint iGeoPoint : pointAdapter) {
            if (iGeoPoint != null) {
                dValueOf = (dValueOf == null || iGeoPoint.getLongitude() > dValueOf.doubleValue()) ? Double.valueOf(iGeoPoint.getLongitude()) : dValueOf;
                dValueOf4 = (dValueOf4 == null || iGeoPoint.getLongitude() < dValueOf4.doubleValue()) ? Double.valueOf(iGeoPoint.getLongitude()) : dValueOf4;
                dValueOf2 = (dValueOf2 == null || iGeoPoint.getLatitude() > dValueOf2.doubleValue()) ? Double.valueOf(iGeoPoint.getLatitude()) : dValueOf2;
                if (dValueOf3 == null || iGeoPoint.getLatitude() < dValueOf3.doubleValue()) {
                    dValueOf3 = Double.valueOf(iGeoPoint.getLatitude());
                }
            }
        }
        if (dValueOf != null) {
            this.mBoundingBox = new BoundingBox(dValueOf2.doubleValue(), dValueOf.doubleValue(), dValueOf3.doubleValue(), dValueOf4.doubleValue());
        } else {
            this.mBoundingBox = null;
        }
    }

    public SimpleFastPointOverlay(PointAdapter pointAdapter) {
        this(pointAdapter, SimpleFastPointOverlayOptions.getDefaultStyle());
    }

    private void updateGrid(MapView mapView) {
        this.viewWid = mapView.getWidth();
        this.viewHei = mapView.getHeight();
        this.gridWid = ((int) Math.floor(this.viewWid / this.mStyle.mCellSize)) + 1;
        int iFloor = ((int) Math.floor(this.viewHei / this.mStyle.mCellSize)) + 1;
        this.gridHei = iFloor;
        this.gridBool = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, this.gridWid, iFloor);
    }

    private void computeGrid(MapView mapView) {
        BoundingBox boundingBox = mapView.getBoundingBox();
        this.startBoundingBox = boundingBox;
        this.startProjection = mapView.getProjection();
        if (boundingBox.getLatNorth() == this.prevBoundingBox.getLatNorth() && boundingBox.getLatSouth() == this.prevBoundingBox.getLatSouth() && boundingBox.getLonWest() == this.prevBoundingBox.getLonWest() && boundingBox.getLonEast() == this.prevBoundingBox.getLonEast()) {
            return;
        }
        this.prevBoundingBox = new BoundingBox(boundingBox.getLatNorth(), boundingBox.getLonEast(), boundingBox.getLatSouth(), boundingBox.getLonWest());
        if (this.gridBool == null || this.viewHei != mapView.getHeight() || this.viewWid != mapView.getWidth()) {
            updateGrid(mapView);
        } else {
            for (boolean[] zArr : this.gridBool) {
                Arrays.fill(zArr, false);
            }
        }
        Point point = new Point();
        Projection projection = mapView.getProjection();
        this.gridIndex = new ArrayList();
        this.numLabels = 0;
        for (IGeoPoint iGeoPoint : this.mPointList) {
            if (iGeoPoint != null && iGeoPoint.getLatitude() > boundingBox.getLatSouth() && iGeoPoint.getLatitude() < boundingBox.getLatNorth() && iGeoPoint.getLongitude() > boundingBox.getLonWest() && iGeoPoint.getLongitude() < boundingBox.getLonEast()) {
                projection.toPixels(iGeoPoint, point);
                int iFloor = (int) Math.floor(point.x / this.mStyle.mCellSize);
                int iFloor2 = (int) Math.floor(point.y / this.mStyle.mCellSize);
                if (iFloor < this.gridWid && iFloor2 < this.gridHei && iFloor >= 0 && iFloor2 >= 0) {
                    boolean[] zArr2 = this.gridBool[iFloor];
                    if (!zArr2[iFloor2]) {
                        zArr2[iFloor2] = true;
                        this.gridIndex.add(new StyledLabelledPoint(point, this.mPointList.isLabelled() ? ((LabelledGeoPoint) iGeoPoint).getLabel() : null, this.mPointList.isStyled() ? ((StyledLabelledGeoPoint) iGeoPoint).getPointStyle() : null, this.mPointList.isStyled() ? ((StyledLabelledGeoPoint) iGeoPoint).getTextStyle() : null));
                        this.numLabels++;
                    }
                }
            }
        }
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
        if (this.mStyle.mAlgorithm != SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startBoundingBox = mapView.getBoundingBox();
            this.startProjection = mapView.getProjection();
        } else if (action == 1) {
            this.hasMoved = false;
            this.startBoundingBox = mapView.getBoundingBox();
            this.startProjection = mapView.getProjection();
            mapView.invalidate();
        } else if (action == 2) {
            this.hasMoved = true;
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView) {
        if (!this.mStyle.mClickable) {
            return false;
        }
        Point point = new Point();
        Projection projection = mapView.getProjection();
        Float fValueOf = null;
        int i = -1;
        for (int i2 = 0; i2 < this.mPointList.size(); i2++) {
            if (this.mPointList.get(i2) != null) {
                projection.toPixels(this.mPointList.get(i2), point);
                if (Math.abs(motionEvent.getX() - point.x) <= 50.0f && Math.abs(motionEvent.getY() - point.y) <= 50.0f) {
                    float x = ((motionEvent.getX() - point.x) * (motionEvent.getX() - point.x)) + ((motionEvent.getY() - point.y) * (motionEvent.getY() - point.y));
                    if (fValueOf == null || x < fValueOf.floatValue()) {
                        fValueOf = Float.valueOf(x);
                        i = i2;
                    }
                }
            }
        }
        if (fValueOf == null) {
            return false;
        }
        setSelectedPoint(Integer.valueOf(i));
        mapView.invalidate();
        OnClickListener onClickListener = this.clickListener;
        if (onClickListener == null) {
            return true;
        }
        onClickListener.onClick(this.mPointList, Integer.valueOf(i));
        return true;
    }

    public void setSelectedPoint(Integer num) {
        if (num == null || num.intValue() < 0 || num.intValue() >= this.mPointList.size()) {
            this.mSelectedPoint = null;
        } else {
            this.mSelectedPoint = num;
        }
    }

    public Integer getSelectedPoint() {
        return this.mSelectedPoint;
    }

    public BoundingBox getBoundingBox() {
        return this.mBoundingBox;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d8  */
    @Override // org.osmdroid.views.overlay.Overlay
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r21, org.osmdroid.views.MapView r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 949
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay.draw(android.graphics.Canvas, org.osmdroid.views.MapView, boolean):void");
    }

    /* renamed from: org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$overlay$simplefastpoint$SimpleFastPointOverlayOptions$RenderingAlgorithm;

        static {
            int[] iArr = new int[SimpleFastPointOverlayOptions.RenderingAlgorithm.values().length];
            $SwitchMap$org$osmdroid$views$overlay$simplefastpoint$SimpleFastPointOverlayOptions$RenderingAlgorithm = iArr;
            try {
                iArr[SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$osmdroid$views$overlay$simplefastpoint$SimpleFastPointOverlayOptions$RenderingAlgorithm[SimpleFastPointOverlayOptions.RenderingAlgorithm.MEDIUM_OPTIMIZATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$osmdroid$views$overlay$simplefastpoint$SimpleFastPointOverlayOptions$RenderingAlgorithm[SimpleFastPointOverlayOptions.RenderingAlgorithm.NO_OPTIMIZATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected void drawPointAt(Canvas canvas, float f, float f2, boolean z, String str, Paint paint, Paint paint2, MapView mapView) {
        canvas.save();
        canvas.rotate(-mapView.getMapOrientation(), f, f2);
        if (this.mStyle.mSymbol == SimpleFastPointOverlayOptions.Shape.CIRCLE) {
            canvas.drawCircle(f, f2, this.mStyle.mCircleRadius, paint);
        } else {
            canvas.drawRect(f - this.mStyle.mCircleRadius, f2 - this.mStyle.mCircleRadius, f + this.mStyle.mCircleRadius, f2 + this.mStyle.mCircleRadius, paint);
        }
        if (z && str != null) {
            canvas.drawText(str, f, (f2 - this.mStyle.mCircleRadius) - 5.0f, paint2);
        }
        canvas.restore();
    }
}
