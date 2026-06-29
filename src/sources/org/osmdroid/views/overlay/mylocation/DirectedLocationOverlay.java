package org.osmdroid.views.overlay.mylocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import org.osmdroid.library.R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

/* loaded from: classes3.dex */
public class DirectedLocationOverlay extends Overlay {
    protected Bitmap DIRECTION_ARROW;
    private float DIRECTION_ARROW_CENTER_X;
    private float DIRECTION_ARROW_CENTER_Y;
    private int DIRECTION_ARROW_HEIGHT;
    private int DIRECTION_ARROW_WIDTH;
    protected float mBearing;
    protected GeoPoint mLocation;
    protected Paint mPaint = new Paint();
    protected Paint mAccuracyPaint = new Paint();
    private final Matrix directionRotater = new Matrix();
    private final Point screenCoords = new Point();
    private int mAccuracy = 0;
    private boolean mShowAccuracy = true;

    public DirectedLocationOverlay(Context context) {
        setDirectionArrow(((BitmapDrawable) context.getResources().getDrawable(R.drawable.twotone_navigation_black_48)).getBitmap());
        this.mAccuracyPaint.setStrokeWidth(2.0f);
        this.mAccuracyPaint.setColor(-16776961);
        this.mAccuracyPaint.setAntiAlias(true);
    }

    public void setDirectionArrow(Bitmap bitmap) {
        this.DIRECTION_ARROW = bitmap;
        this.DIRECTION_ARROW_CENTER_X = (bitmap.getWidth() / 2.0f) - 0.5f;
        this.DIRECTION_ARROW_CENTER_Y = (this.DIRECTION_ARROW.getHeight() / 2.0f) - 0.5f;
        this.DIRECTION_ARROW_HEIGHT = this.DIRECTION_ARROW.getHeight();
        this.DIRECTION_ARROW_WIDTH = this.DIRECTION_ARROW.getWidth();
    }

    public void setShowAccuracy(boolean z) {
        this.mShowAccuracy = z;
    }

    public void setLocation(GeoPoint geoPoint) {
        this.mLocation = geoPoint;
    }

    public GeoPoint getLocation() {
        return this.mLocation;
    }

    public void setAccuracy(int i) {
        this.mAccuracy = i;
    }

    public void setBearing(float f) {
        this.mBearing = f;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        this.mPaint = null;
        this.mAccuracyPaint = null;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        int i;
        GeoPoint geoPoint = this.mLocation;
        if (geoPoint != null) {
            projection.toPixels(geoPoint, this.screenCoords);
            if (this.mShowAccuracy && (i = this.mAccuracy) > 10) {
                float fMetersToPixels = projection.metersToPixels(i, this.mLocation.getLatitude(), projection.getZoomLevel());
                if (fMetersToPixels > 8.0f) {
                    this.mAccuracyPaint.setAntiAlias(false);
                    this.mAccuracyPaint.setAlpha(30);
                    this.mAccuracyPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(this.screenCoords.x, this.screenCoords.y, fMetersToPixels, this.mAccuracyPaint);
                    this.mAccuracyPaint.setAntiAlias(true);
                    this.mAccuracyPaint.setAlpha(150);
                    this.mAccuracyPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(this.screenCoords.x, this.screenCoords.y, fMetersToPixels, this.mAccuracyPaint);
                }
            }
            this.directionRotater.setRotate(this.mBearing, this.DIRECTION_ARROW_CENTER_X, this.DIRECTION_ARROW_CENTER_Y);
            canvas.drawBitmap(Bitmap.createBitmap(this.DIRECTION_ARROW, 0, 0, this.DIRECTION_ARROW_WIDTH, this.DIRECTION_ARROW_HEIGHT, this.directionRotater, false), this.screenCoords.x - (r12.getWidth() / 2), this.screenCoords.y - (r12.getHeight() / 2), this.mPaint);
        }
    }
}
