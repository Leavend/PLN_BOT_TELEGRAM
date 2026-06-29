package com.wangjie.rapidfloatingactionbutton.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public class CircleButtonDrawable extends Drawable {
    private RectF bounds = new RectF();
    private CircleButtonProperties circleButtonProperties;
    private Context context;
    private float halfLen;
    private Paint paint;
    private int realSizePx;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public CircleButtonDrawable(Context context, CircleButtonProperties circleButtonProperties, int i) {
        this.context = context;
        this.circleButtonProperties = circleButtonProperties;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setFilterBitmap(true);
        this.paint.setDither(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(i);
        this.paint.setShadowLayer(circleButtonProperties.getShadowRadius(), circleButtonProperties.getShadowDx(), circleButtonProperties.getShadowDy(), circleButtonProperties.getShadowColor());
        int realSizePx = this.circleButtonProperties.getRealSizePx(context);
        this.realSizePx = realSizePx;
        setBounds(0, 0, realSizePx, realSizePx);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect.right - rect.left <= 0 || rect.bottom - rect.top <= 0) {
            return;
        }
        this.bounds.left = rect.left;
        this.bounds.right = rect.right;
        this.bounds.top = rect.top;
        this.bounds.bottom = rect.bottom;
        this.halfLen = Math.min((this.bounds.right - this.bounds.left) / 2.0f, (this.bounds.bottom - this.bounds.top) / 2.0f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f = this.halfLen;
        canvas.drawCircle(f, f, this.circleButtonProperties.getStandardSizePx(this.context) / 2, this.paint);
    }

    public Paint getPaint() {
        return this.paint;
    }

    public CircleButtonDrawable setColor(int i) {
        this.paint.setColor(i);
        return this;
    }
}
