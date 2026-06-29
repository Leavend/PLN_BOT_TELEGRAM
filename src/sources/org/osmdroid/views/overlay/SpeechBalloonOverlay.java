package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.PointL;
import org.osmdroid.util.RectL;
import org.osmdroid.util.SpeechBalloonHelper;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public class SpeechBalloonOverlay extends Overlay {
    private Paint mBackground;
    private Paint mDragBackground;
    private float mDragDeltaX;
    private float mDragDeltaY;
    private Paint mDragForeground;
    private float mDragStartX;
    private float mDragStartY;
    private Paint mForeground;
    private GeoPoint mGeoPoint;
    private boolean mIsDragged;
    private int mMargin;
    private int mOffsetX;
    private int mOffsetY;
    private double mRadius;
    private String mTitle;
    private final SpeechBalloonHelper mHelper = new SpeechBalloonHelper();
    private final RectL mRect = new RectL();
    private final PointL mPoint = new PointL();
    private final PointL mIntersection1 = new PointL();
    private final PointL mIntersection2 = new PointL();
    private final Path mPath = new Path();
    private final Rect mTextRect = new Rect();
    private final Point mPixel = new Point();
    private boolean mDraggable = true;

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.mGeoPoint = geoPoint;
    }

    public void setBackground(Paint paint) {
        this.mBackground = paint;
    }

    public void setForeground(Paint paint) {
        this.mForeground = paint;
    }

    public void setDragBackground(Paint paint) {
        this.mDragBackground = paint;
    }

    public void setDragForeground(Paint paint) {
        this.mDragForeground = paint;
    }

    public void setMargin(int i) {
        this.mMargin = i;
    }

    public void setRadius(long j) {
        this.mRadius = j;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        Paint paint;
        Paint paint2;
        String str;
        if (this.mIsDragged) {
            paint = this.mDragBackground;
            if (paint == null) {
                paint = this.mBackground;
            }
            paint2 = this.mDragForeground;
            if (paint2 == null) {
                paint2 = this.mForeground;
            }
        } else {
            paint = this.mBackground;
            paint2 = this.mForeground;
        }
        Paint paint3 = paint;
        Paint paint4 = paint2;
        if (this.mGeoPoint == null || (str = this.mTitle) == null || str.trim().length() == 0 || paint4 == null || paint3 == null) {
            return;
        }
        projection.toPixels(this.mGeoPoint, this.mPixel);
        String str2 = this.mTitle;
        paint4.getTextBounds(str2, 0, str2.length(), this.mTextRect);
        this.mPoint.set(this.mPixel.x, this.mPixel.y);
        this.mTextRect.offset((int) (this.mPoint.x + this.mOffsetX + this.mDragDeltaX), (int) (this.mPoint.y + this.mOffsetY + this.mDragDeltaY));
        this.mTextRect.top -= this.mMargin;
        this.mTextRect.left -= this.mMargin;
        this.mTextRect.right += this.mMargin;
        this.mTextRect.bottom += this.mMargin;
        this.mRect.set(this.mTextRect.left, this.mTextRect.top, this.mTextRect.right, this.mTextRect.bottom);
        int iCompute = this.mHelper.compute(this.mRect, this.mPoint, this.mRadius, this.mIntersection1, this.mIntersection2);
        canvas.drawRect(this.mTextRect.left, this.mTextRect.top, this.mTextRect.right, this.mTextRect.bottom, paint3);
        if (iCompute != -1) {
            this.mPath.reset();
            this.mPath.moveTo(this.mPoint.x, this.mPoint.y);
            this.mPath.lineTo(this.mIntersection1.x, this.mIntersection1.y);
            this.mPath.lineTo(this.mIntersection2.x, this.mIntersection2.y);
            this.mPath.close();
            canvas.drawPath(this.mPath, paint3);
        }
        canvas.drawText(str2, this.mTextRect.left + this.mMargin, this.mTextRect.bottom - this.mMargin, paint4);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onLongPress(MotionEvent motionEvent, MapView mapView) {
        boolean zHitTest = hitTest(motionEvent, mapView);
        if (zHitTest && this.mDraggable) {
            this.mIsDragged = true;
            this.mDragStartX = motionEvent.getX();
            this.mDragStartY = motionEvent.getY();
            this.mDragDeltaX = 0.0f;
            this.mDragDeltaY = 0.0f;
            mapView.invalidate();
        }
        return zHitTest;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
        if (this.mDraggable && this.mIsDragged) {
            if (motionEvent.getAction() == 1) {
                this.mDragDeltaX = motionEvent.getX() - this.mDragStartX;
                float y = motionEvent.getY() - this.mDragStartY;
                this.mOffsetX = (int) (this.mOffsetX + this.mDragDeltaX);
                this.mOffsetY = (int) (this.mOffsetY + y);
                this.mDragDeltaX = 0.0f;
                this.mDragDeltaY = 0.0f;
                this.mIsDragged = false;
                mapView.invalidate();
                return true;
            }
            if (motionEvent.getAction() == 2) {
                this.mDragDeltaX = motionEvent.getX() - this.mDragStartX;
                this.mDragDeltaY = motionEvent.getY() - this.mDragStartY;
                mapView.invalidate();
                return true;
            }
        }
        return false;
    }

    private boolean hitTest(MotionEvent motionEvent, MapView mapView) {
        return this.mRect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }
}
