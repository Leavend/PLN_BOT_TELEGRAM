package org.osmdroid.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import org.osmdroid.library.R;

/* loaded from: classes3.dex */
public class CustomZoomButtonsDisplay {
    private float mAdditionalPixelMarginBottom;
    private float mAdditionalPixelMarginLeft;
    private float mAdditionalPixelMarginRight;
    private float mAdditionalPixelMarginTop;
    private Paint mAlphaPaint;
    private int mBitmapSize;
    private boolean mHorizontalOrVertical;
    private HorizontalPosition mHorizontalPosition;
    private final MapView mMapView;
    private float mMargin;
    private float mPadding;
    private float mPixelMarginBottom;
    private float mPixelMarginLeft;
    private float mPixelMarginRight;
    private float mPixelMarginTop;
    private final Point mUnrotatedPoint = new Point();
    private VerticalPosition mVerticalPosition;
    private Bitmap mZoomInBitmapDisabled;
    private Bitmap mZoomInBitmapEnabled;
    private Bitmap mZoomOutBitmapDisabled;
    private Bitmap mZoomOutBitmapEnabled;

    public enum HorizontalPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum VerticalPosition {
        TOP,
        CENTER,
        BOTTOM
    }

    public CustomZoomButtonsDisplay(MapView mapView) {
        this.mMapView = mapView;
        setPositions(true, HorizontalPosition.CENTER, VerticalPosition.BOTTOM);
        setMarginPadding(0.5f, 0.5f);
    }

    public void setPositions(boolean z, HorizontalPosition horizontalPosition, VerticalPosition verticalPosition) {
        this.mHorizontalOrVertical = z;
        this.mHorizontalPosition = horizontalPosition;
        this.mVerticalPosition = verticalPosition;
    }

    public void setMarginPadding(float f, float f2) {
        this.mMargin = f;
        this.mPadding = f2;
        refreshPixelMargins();
    }

    public void setAdditionalPixelMargins(float f, float f2, float f3, float f4) {
        this.mAdditionalPixelMarginLeft = f;
        this.mAdditionalPixelMarginTop = f2;
        this.mAdditionalPixelMarginRight = f3;
        this.mAdditionalPixelMarginBottom = f4;
        refreshPixelMargins();
    }

    private void refreshPixelMargins() {
        float f = this.mMargin * this.mBitmapSize;
        this.mPixelMarginLeft = this.mAdditionalPixelMarginLeft + f;
        this.mPixelMarginTop = this.mAdditionalPixelMarginTop + f;
        this.mPixelMarginRight = this.mAdditionalPixelMarginRight + f;
        this.mPixelMarginBottom = f + this.mAdditionalPixelMarginBottom;
    }

    public void setBitmaps(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4) {
        this.mZoomInBitmapEnabled = bitmap;
        this.mZoomInBitmapDisabled = bitmap2;
        this.mZoomOutBitmapEnabled = bitmap3;
        this.mZoomOutBitmapDisabled = bitmap4;
        this.mBitmapSize = bitmap.getWidth();
        refreshPixelMargins();
    }

    protected Bitmap getZoomBitmap(boolean z, boolean z2) {
        Bitmap icon = getIcon(z);
        this.mBitmapSize = icon.getWidth();
        refreshPixelMargins();
        int i = this.mBitmapSize;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColor(z2 ? -1 : -3355444);
        paint.setStyle(Paint.Style.FILL);
        int i2 = this.mBitmapSize;
        canvas.drawRect(0.0f, 0.0f, i2 - 1, i2 - 1, paint);
        canvas.drawBitmap(icon, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    protected Bitmap getIcon(boolean z) {
        return ((BitmapDrawable) this.mMapView.getResources().getDrawable(z ? R.drawable.sharp_add_black_36 : R.drawable.sharp_remove_black_36)).getBitmap();
    }

    public void draw(Canvas canvas, float f, boolean z, boolean z2) {
        Paint paint;
        if (f == 0.0f) {
            return;
        }
        if (f == 1.0f) {
            paint = null;
        } else {
            if (this.mAlphaPaint == null) {
                this.mAlphaPaint = new Paint();
            }
            this.mAlphaPaint.setAlpha((int) (f * 255.0f));
            paint = this.mAlphaPaint;
        }
        canvas.drawBitmap(getBitmap(true, z), getTopLeft(true, true), getTopLeft(true, false), paint);
        canvas.drawBitmap(getBitmap(false, z2), getTopLeft(false, true), getTopLeft(false, false), paint);
    }

    private float getTopLeft(boolean z, boolean z2) {
        int i;
        float f;
        float f2;
        if (z2) {
            float firstLeft = getFirstLeft(this.mMapView.getWidth());
            if (!this.mHorizontalOrVertical || !z) {
                return firstLeft;
            }
            i = this.mBitmapSize;
            f = firstLeft + i;
            f2 = this.mPadding;
        } else {
            float firstTop = getFirstTop(this.mMapView.getHeight());
            if (this.mHorizontalOrVertical || z) {
                return firstTop;
            }
            i = this.mBitmapSize;
            f = firstTop + i;
            f2 = this.mPadding;
        }
        return f + (f2 * i);
    }

    private float getFirstLeft(int i) {
        float f;
        int i2 = AnonymousClass1.$SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$HorizontalPosition[this.mHorizontalPosition.ordinal()];
        if (i2 == 1) {
            return this.mPixelMarginLeft;
        }
        if (i2 == 2) {
            float f2 = i - this.mPixelMarginRight;
            int i3 = this.mBitmapSize;
            return (f2 - i3) - (this.mHorizontalOrVertical ? (this.mPadding * i3) + i3 : 0.0f);
        }
        if (i2 == 3) {
            float f3 = i / 2.0f;
            if (this.mHorizontalOrVertical) {
                float f4 = this.mPadding;
                int i4 = this.mBitmapSize;
                f = ((f4 * i4) / 2.0f) + i4;
            } else {
                f = this.mBitmapSize / 2.0f;
            }
            return f3 - f;
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: org.osmdroid.views.CustomZoomButtonsDisplay$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$HorizontalPosition;
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$VerticalPosition;

        static {
            int[] iArr = new int[VerticalPosition.values().length];
            $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$VerticalPosition = iArr;
            try {
                iArr[VerticalPosition.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$VerticalPosition[VerticalPosition.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$VerticalPosition[VerticalPosition.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[HorizontalPosition.values().length];
            $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$HorizontalPosition = iArr2;
            try {
                iArr2[HorizontalPosition.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$HorizontalPosition[HorizontalPosition.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$HorizontalPosition[HorizontalPosition.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private float getFirstTop(int i) {
        float f;
        float f2;
        int i2 = AnonymousClass1.$SwitchMap$org$osmdroid$views$CustomZoomButtonsDisplay$VerticalPosition[this.mVerticalPosition.ordinal()];
        if (i2 == 1) {
            return this.mPixelMarginTop;
        }
        if (i2 == 2) {
            float f3 = i - this.mPixelMarginBottom;
            int i3 = this.mBitmapSize;
            float f4 = f3 - i3;
            if (this.mHorizontalOrVertical) {
                f = 0.0f;
            } else {
                f = i3 + (this.mPadding * i3);
            }
            return f4 - f;
        }
        if (i2 == 3) {
            float f5 = i / 2.0f;
            if (this.mHorizontalOrVertical) {
                f2 = this.mBitmapSize / 2.0f;
            } else {
                float f6 = this.mPadding;
                int i4 = this.mBitmapSize;
                f2 = ((f6 * i4) / 2.0f) + i4;
            }
            return f5 - f2;
        }
        throw new IllegalArgumentException();
    }

    private Bitmap getBitmap(boolean z, boolean z2) {
        if (this.mZoomInBitmapEnabled == null) {
            setBitmaps(getZoomBitmap(true, true), getZoomBitmap(true, false), getZoomBitmap(false, true), getZoomBitmap(false, false));
        }
        return z ? z2 ? this.mZoomInBitmapEnabled : this.mZoomInBitmapDisabled : z2 ? this.mZoomOutBitmapEnabled : this.mZoomOutBitmapDisabled;
    }

    @Deprecated
    public boolean isTouchedRotated(MotionEvent motionEvent, boolean z) {
        if (this.mMapView.getMapOrientation() == 0.0f) {
            this.mUnrotatedPoint.set((int) motionEvent.getX(), (int) motionEvent.getY());
        } else {
            this.mMapView.getProjection().rotateAndScalePoint((int) motionEvent.getX(), (int) motionEvent.getY(), this.mUnrotatedPoint);
        }
        return isTouched(this.mUnrotatedPoint.x, this.mUnrotatedPoint.y, z);
    }

    public boolean isTouched(MotionEvent motionEvent, boolean z) {
        if (motionEvent.getAction() == 1) {
            return isTouched((int) motionEvent.getX(), (int) motionEvent.getY(), z);
        }
        return false;
    }

    private boolean isTouched(int i, int i2, boolean z) {
        return isTouched(z, true, (float) i) && isTouched(z, false, (float) i2);
    }

    private boolean isTouched(boolean z, boolean z2, float f) {
        float topLeft = getTopLeft(z, z2);
        return f >= topLeft && f <= topLeft + ((float) this.mBitmapSize);
    }
}
