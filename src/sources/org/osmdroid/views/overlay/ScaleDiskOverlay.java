package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public class ScaleDiskOverlay extends Overlay {
    private Paint mCirclePaint1;
    private Paint mCirclePaint2;
    private int mDisplaySizeMax;
    private int mDisplaySizeMin;
    private final GeoPoint mGeoCenter;
    private final String mLabel;
    private Integer mLabelOffsetBottom;
    private Integer mLabelOffsetLeft;
    private Integer mLabelOffsetRight;
    private Integer mLabelOffsetTop;
    private final double mMeters;
    private Paint mTextPaint;
    private final Point mPixelCenter = new Point();
    private final Rect mLabelRect = new Rect();

    private int getOffsetY() {
        return 0;
    }

    public ScaleDiskOverlay(Context context, GeoPoint geoPoint, int i, GeoConstants.UnitOfMeasure unitOfMeasure) {
        this.mGeoCenter = geoPoint;
        this.mMeters = i * unitOfMeasure.getConversionFactorToMeters();
        this.mLabel = ScaleBarOverlay.getScaleString(context, String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(i)), unitOfMeasure);
    }

    public void setCirclePaint1(Paint paint) {
        this.mCirclePaint1 = paint;
    }

    public void setCirclePaint2(Paint paint) {
        this.mCirclePaint2 = paint;
    }

    public void setTextPaint(Paint paint) {
        this.mTextPaint = paint;
    }

    public void setLabelOffsetTop(Integer num) {
        this.mLabelOffsetTop = num;
    }

    public void setLabelOffsetBottom(Integer num) {
        this.mLabelOffsetBottom = num;
    }

    public void setLabelOffsetLeft(Integer num) {
        this.mLabelOffsetLeft = num;
    }

    public void setLabelOffsetRight(Integer num) {
        this.mLabelOffsetRight = num;
    }

    public void setDisplaySizeMin(int i) {
        this.mDisplaySizeMin = i;
    }

    public void setDisplaySizeMax(int i) {
        this.mDisplaySizeMax = i;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        projection.toPixels(this.mGeoCenter, this.mPixelCenter);
        int i = this.mPixelCenter.x;
        int i2 = this.mPixelCenter.y;
        int iMetersToPixels = (int) projection.metersToPixels((float) this.mMeters, this.mGeoCenter.getLatitude(), projection.getZoomLevel());
        int i3 = this.mDisplaySizeMin;
        if (i3 <= 0 || iMetersToPixels * 2 >= i3) {
            int i4 = this.mDisplaySizeMax;
            if (i4 <= 0 || iMetersToPixels * 2 <= i4) {
                Paint paint = this.mCirclePaint1;
                if (paint != null) {
                    canvas.drawCircle(i, i2, iMetersToPixels, paint);
                }
                Paint paint2 = this.mCirclePaint2;
                if (paint2 != null) {
                    canvas.drawCircle(i, i2, iMetersToPixels, paint2);
                }
                Paint paint3 = this.mTextPaint;
                if (paint3 != null) {
                    String str = this.mLabel;
                    paint3.getTextBounds(str, 0, str.length(), this.mLabelRect);
                    if (this.mLabelOffsetTop != null) {
                        canvas.drawText(this.mLabel, getOffsetX() + i, (-iMetersToPixels) + getOffsetY(this.mLabelOffsetTop.intValue()) + i2, this.mTextPaint);
                    }
                    if (this.mLabelOffsetLeft != null) {
                        canvas.drawText(this.mLabel, (-iMetersToPixels) + getOffsetX(r2.intValue()) + i, getOffsetY() + i2, this.mTextPaint);
                    }
                    if (this.mLabelOffsetBottom != null) {
                        canvas.drawText(this.mLabel, getOffsetX() + i, getOffsetY(this.mLabelOffsetBottom.intValue()) + iMetersToPixels + i2, this.mTextPaint);
                    }
                    if (this.mLabelOffsetRight != null) {
                        canvas.drawText(this.mLabel, i + iMetersToPixels + getOffsetX(r2.intValue()), i2 + getOffsetY(), this.mTextPaint);
                    }
                }
            }
        }
    }

    private int getOffsetX() {
        return (-this.mLabelRect.width()) / 2;
    }

    private int getOffsetX(int i) {
        return i + (i >= 0 ? 0 : -this.mLabelRect.width());
    }

    private int getOffsetY(int i) {
        Rect rect = this.mLabelRect;
        return i + (-(i >= 0 ? rect.top : rect.bottom));
    }
}
