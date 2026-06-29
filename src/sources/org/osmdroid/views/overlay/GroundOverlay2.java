package org.osmdroid.views.overlay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.Projection;

@Deprecated
/* loaded from: classes3.dex */
public class GroundOverlay2 extends Overlay {
    private Bitmap mImage;
    private float mLatD;
    private float mLatU;
    private float mLonL;
    private float mLonR;
    protected float mTransparency;
    private final Paint mPaint = new Paint();
    private Matrix mMatrix = new Matrix();
    protected float mBearing = 0.0f;

    public GroundOverlay2() {
        setTransparency(0.0f);
    }

    protected Paint getPaint() {
        return this.mPaint;
    }

    protected Matrix getMatrix() {
        return this.mMatrix;
    }

    public Bitmap getImage() {
        return this.mImage;
    }

    public float getBearing() {
        return this.mBearing;
    }

    public void setBearing(float f) {
        this.mBearing = f;
    }

    public void setTransparency(float f) {
        this.mTransparency = f;
        this.mPaint.setAlpha(255 - ((int) (f * 255.0f)));
    }

    public float getTransparency() {
        return this.mTransparency;
    }

    public void setImage(Bitmap bitmap) {
        this.mImage = bitmap;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        if (this.mImage == null) {
            return;
        }
        computeMatrix(projection);
        canvas.drawBitmap(getImage(), getMatrix(), getPaint());
    }

    public void setPosition(GeoPoint geoPoint, GeoPoint geoPoint2) {
        this.mLatU = (float) geoPoint.getLatitude();
        this.mLonL = (float) geoPoint.getLongitude();
        this.mLatD = (float) geoPoint2.getLatitude();
        this.mLonR = (float) geoPoint2.getLongitude();
    }

    protected void computeMatrix(Projection projection) {
        long longPixelXFromLongitude = projection.getLongPixelXFromLongitude(this.mLonL);
        long longPixelYFromLatitude = projection.getLongPixelYFromLatitude(this.mLatU);
        long longPixelXFromLongitude2 = projection.getLongPixelXFromLongitude(this.mLonR);
        long longPixelYFromLatitude2 = projection.getLongPixelYFromLatitude(this.mLatD);
        getMatrix().setScale((longPixelXFromLongitude2 - longPixelXFromLongitude) / getImage().getWidth(), (longPixelYFromLatitude2 - longPixelYFromLatitude) / getImage().getHeight());
        getMatrix().postTranslate(longPixelXFromLongitude, longPixelYFromLatitude);
    }
}
