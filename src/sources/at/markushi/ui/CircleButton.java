package at.markushi.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import at.markushi.circlebutton.R;

/* loaded from: classes5.dex */
public class CircleButton extends ImageView {
    private static final int ANIMATION_TIME_ID = 17694720;
    private static final int DEFAULT_PRESSED_RING_WIDTH_DIP = 4;
    private static final int PRESSED_COLOR_LIGHTUP = 10;
    private static final int PRESSED_RING_ALPHA = 75;
    private float animationProgress;
    private int centerX;
    private int centerY;
    private Paint circlePaint;
    private int defaultColor;
    private Paint focusPaint;
    private int outerRadius;
    private ObjectAnimator pressedAnimator;
    private int pressedColor;
    private int pressedRingRadius;
    private int pressedRingWidth;

    public CircleButton(Context context) throws Resources.NotFoundException {
        super(context);
        this.defaultColor = ViewCompat.MEASURED_STATE_MASK;
        init(context, null);
    }

    public CircleButton(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        this.defaultColor = ViewCompat.MEASURED_STATE_MASK;
        init(context, attributeSet);
    }

    public CircleButton(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.defaultColor = ViewCompat.MEASURED_STATE_MASK;
        init(context, attributeSet);
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        Paint paint = this.circlePaint;
        if (paint != null) {
            paint.setColor(z ? this.pressedColor : this.defaultColor);
        }
        if (z) {
            showPressedRing();
        } else {
            hidePressedRing();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(this.centerX, this.centerY, this.pressedRingRadius + this.animationProgress, this.focusPaint);
        canvas.drawCircle(this.centerX, this.centerY, this.outerRadius - this.pressedRingWidth, this.circlePaint);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.centerX = i / 2;
        this.centerY = i2 / 2;
        int iMin = Math.min(i, i2) / 2;
        this.outerRadius = iMin;
        int i5 = this.pressedRingWidth;
        this.pressedRingRadius = (iMin - i5) - (i5 / 2);
    }

    public float getAnimationProgress() {
        return this.animationProgress;
    }

    public void setAnimationProgress(float f) {
        this.animationProgress = f;
        invalidate();
    }

    public void setColor(int i) {
        this.defaultColor = i;
        this.pressedColor = getHighlightColor(i, 10);
        this.circlePaint.setColor(this.defaultColor);
        this.focusPaint.setColor(this.defaultColor);
        this.focusPaint.setAlpha(75);
        invalidate();
    }

    private void hidePressedRing() {
        this.pressedAnimator.setFloatValues(this.pressedRingWidth, 0.0f);
        this.pressedAnimator.start();
    }

    private void showPressedRing() {
        this.pressedAnimator.setFloatValues(this.animationProgress, this.pressedRingWidth);
        this.pressedAnimator.start();
    }

    private void init(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        setFocusable(true);
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setClickable(true);
        Paint paint = new Paint(1);
        this.circlePaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.focusPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.pressedRingWidth = (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
        int color = ViewCompat.MEASURED_STATE_MASK;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleButton);
            color = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            this.pressedRingWidth = (int) typedArrayObtainStyledAttributes.getDimension(1, this.pressedRingWidth);
            typedArrayObtainStyledAttributes.recycle();
        }
        setColor(color);
        this.focusPaint.setStrokeWidth(this.pressedRingWidth);
        int integer = getResources().getInteger(17694720);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "animationProgress", 0.0f, 0.0f);
        this.pressedAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(integer);
    }

    private int getHighlightColor(int i, int i2) {
        return Color.argb(Math.min(255, Color.alpha(i)), Math.min(255, Color.red(i) + i2), Math.min(255, Color.green(i) + i2), Math.min(255, Color.blue(i) + i2));
    }
}
