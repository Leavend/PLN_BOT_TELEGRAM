package com.facebook.shimmer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

/* loaded from: classes5.dex */
public class ShimmerFrameLayout extends FrameLayout {
    private static final PorterDuffXfermode DST_IN_PORTER_DUFF_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private static final String TAG = "ShimmerFrameLayout";
    private Paint mAlphaPaint;
    private boolean mAnimationStarted;
    protected ValueAnimator mAnimator;
    private boolean mAutoStart;
    private int mDuration;
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private Mask mMask;
    protected Bitmap mMaskBitmap;
    private int mMaskOffsetX;
    private int mMaskOffsetY;
    private Paint mMaskPaint;
    private MaskTranslation mMaskTranslation;
    private Bitmap mRenderMaskBitmap;
    private Bitmap mRenderUnmaskBitmap;
    private int mRepeatCount;
    private int mRepeatDelay;
    private int mRepeatMode;

    public enum MaskAngle {
        CW_0,
        CW_90,
        CW_180,
        CW_270
    }

    public enum MaskShape {
        LINEAR,
        RADIAL
    }

    private static class Mask {
        public MaskAngle angle;
        public float dropoff;
        public int fixedHeight;
        public int fixedWidth;
        public float intensity;
        public float relativeHeight;
        public float relativeWidth;
        public MaskShape shape;
        public float tilt;

        private Mask() {
        }

        public int maskWidth(int i) {
            int i2 = this.fixedWidth;
            return i2 > 0 ? i2 : (int) (i * this.relativeWidth);
        }

        public int maskHeight(int i) {
            int i2 = this.fixedHeight;
            return i2 > 0 ? i2 : (int) (i * this.relativeHeight);
        }

        public int[] getGradientColors() {
            if (AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape[this.shape.ordinal()] != 2) {
                return new int[]{0, ViewCompat.MEASURED_STATE_MASK, ViewCompat.MEASURED_STATE_MASK, 0};
            }
            return new int[]{ViewCompat.MEASURED_STATE_MASK, ViewCompat.MEASURED_STATE_MASK, 0};
        }

        public float[] getGradientPositions() {
            return AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape[this.shape.ordinal()] != 2 ? new float[]{Math.max(((1.0f - this.intensity) - this.dropoff) / 2.0f, 0.0f), Math.max((1.0f - this.intensity) / 2.0f, 0.0f), Math.min((this.intensity + 1.0f) / 2.0f, 1.0f), Math.min(((this.intensity + 1.0f) + this.dropoff) / 2.0f, 1.0f)} : new float[]{0.0f, Math.min(this.intensity, 1.0f), Math.min(this.intensity + this.dropoff, 1.0f)};
        }
    }

    private static class MaskTranslation {
        public int fromX;
        public int fromY;
        public int toX;
        public int toY;

        private MaskTranslation() {
        }

        public void set(int i, int i2, int i3, int i4) {
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }
    }

    public ShimmerFrameLayout(Context context) {
        this(context, null, 0);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        this.mMask = new Mask();
        this.mAlphaPaint = new Paint();
        Paint paint = new Paint();
        this.mMaskPaint = paint;
        paint.setAntiAlias(true);
        this.mMaskPaint.setDither(true);
        this.mMaskPaint.setFilterBitmap(true);
        this.mMaskPaint.setXfermode(DST_IN_PORTER_DUFF_XFERMODE);
        useDefaults();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0);
            try {
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_auto_start)) {
                    setAutoStart(typedArrayObtainStyledAttributes.getBoolean(R.styleable.ShimmerFrameLayout_auto_start, false));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_base_alpha)) {
                    setBaseAlpha(typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_base_alpha, 0.0f));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_duration)) {
                    setDuration(typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_duration, 0));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_repeat_count)) {
                    setRepeatCount(typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_repeat_count, 0));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_repeat_delay)) {
                    setRepeatDelay(typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_repeat_delay, 0));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_repeat_mode)) {
                    setRepeatMode(typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_repeat_mode, 0));
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_angle)) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_angle, 0);
                    if (i2 == 90) {
                        this.mMask.angle = MaskAngle.CW_90;
                    } else if (i2 == 180) {
                        this.mMask.angle = MaskAngle.CW_180;
                    } else if (i2 != 270) {
                        this.mMask.angle = MaskAngle.CW_0;
                    } else {
                        this.mMask.angle = MaskAngle.CW_270;
                    }
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_shape)) {
                    if (typedArrayObtainStyledAttributes.getInt(R.styleable.ShimmerFrameLayout_shape, 0) != 1) {
                        this.mMask.shape = MaskShape.LINEAR;
                    } else {
                        this.mMask.shape = MaskShape.RADIAL;
                    }
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_dropoff)) {
                    this.mMask.dropoff = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_dropoff, 0.0f);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_fixed_width)) {
                    this.mMask.fixedWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShimmerFrameLayout_fixed_width, 0);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_fixed_height)) {
                    this.mMask.fixedHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ShimmerFrameLayout_fixed_height, 0);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_intensity)) {
                    this.mMask.intensity = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_intensity, 0.0f);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_relative_width)) {
                    this.mMask.relativeWidth = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_relative_width, 0.0f);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_relative_height)) {
                    this.mMask.relativeHeight = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_relative_height, 0.0f);
                }
                if (typedArrayObtainStyledAttributes.hasValue(R.styleable.ShimmerFrameLayout_tilt)) {
                    this.mMask.tilt = typedArrayObtainStyledAttributes.getFloat(R.styleable.ShimmerFrameLayout_tilt, 0.0f);
                }
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    public void useDefaults() {
        setAutoStart(false);
        setDuration(1000);
        setRepeatCount(-1);
        setRepeatDelay(0);
        setRepeatMode(1);
        this.mMask.angle = MaskAngle.CW_0;
        this.mMask.shape = MaskShape.LINEAR;
        this.mMask.dropoff = 0.5f;
        this.mMask.fixedWidth = 0;
        this.mMask.fixedHeight = 0;
        this.mMask.intensity = 0.0f;
        this.mMask.relativeWidth = 1.0f;
        this.mMask.relativeHeight = 1.0f;
        this.mMask.tilt = 20.0f;
        this.mMaskTranslation = new MaskTranslation();
        setBaseAlpha(0.3f);
        resetAll();
    }

    public boolean isAutoStart() {
        return this.mAutoStart;
    }

    public void setAutoStart(boolean z) {
        this.mAutoStart = z;
        resetAll();
    }

    public float getBaseAlpha() {
        return this.mAlphaPaint.getAlpha() / 255.0f;
    }

    public void setBaseAlpha(float f) {
        this.mAlphaPaint.setAlpha((int) (clamp(0.0f, 1.0f, f) * 255.0f));
        resetAll();
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
        resetAll();
    }

    public int getRepeatCount() {
        return this.mRepeatCount;
    }

    public void setRepeatCount(int i) {
        this.mRepeatCount = i;
        resetAll();
    }

    public int getRepeatDelay() {
        return this.mRepeatDelay;
    }

    public void setRepeatDelay(int i) {
        this.mRepeatDelay = i;
        resetAll();
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
        resetAll();
    }

    public MaskShape getMaskShape() {
        return this.mMask.shape;
    }

    public void setMaskShape(MaskShape maskShape) {
        this.mMask.shape = maskShape;
        resetAll();
    }

    public MaskAngle getAngle() {
        return this.mMask.angle;
    }

    public void setAngle(MaskAngle maskAngle) {
        this.mMask.angle = maskAngle;
        resetAll();
    }

    public float getDropoff() {
        return this.mMask.dropoff;
    }

    public void setDropoff(float f) {
        this.mMask.dropoff = f;
        resetAll();
    }

    public int getFixedWidth() {
        return this.mMask.fixedWidth;
    }

    public void setFixedWidth(int i) {
        this.mMask.fixedWidth = i;
        resetAll();
    }

    public int getFixedHeight() {
        return this.mMask.fixedHeight;
    }

    public void setFixedHeight(int i) {
        this.mMask.fixedHeight = i;
        resetAll();
    }

    public float getIntensity() {
        return this.mMask.intensity;
    }

    public void setIntensity(float f) {
        this.mMask.intensity = f;
        resetAll();
    }

    public float getRelativeWidth() {
        return this.mMask.relativeWidth;
    }

    public void setRelativeWidth(int i) {
        this.mMask.relativeWidth = i;
        resetAll();
    }

    public float getRelativeHeight() {
        return this.mMask.relativeHeight;
    }

    public void setRelativeHeight(int i) {
        this.mMask.relativeHeight = i;
        resetAll();
    }

    public float getTilt() {
        return this.mMask.tilt;
    }

    public void setTilt(float f) {
        this.mMask.tilt = f;
        resetAll();
    }

    public void startShimmerAnimation() {
        if (this.mAnimationStarted) {
            return;
        }
        getShimmerAnimation().start();
        this.mAnimationStarted = true;
    }

    public void stopShimmerAnimation() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.mAnimator.removeAllUpdateListeners();
            this.mAnimator.cancel();
        }
        this.mAnimator = null;
        this.mAnimationStarted = false;
    }

    public boolean isAnimationStarted() {
        return this.mAnimationStarted;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMaskOffsetX(int i) {
        if (this.mMaskOffsetX == i) {
            return;
        }
        this.mMaskOffsetX = i;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMaskOffsetY(int i) {
        if (this.mMaskOffsetY == i) {
            return;
        }
        this.mMaskOffsetY = i;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mGlobalLayoutListener == null) {
            this.mGlobalLayoutListener = getLayoutListener();
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.mGlobalLayoutListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener getLayoutListener() {
        return new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.facebook.shimmer.ShimmerFrameLayout.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                boolean z = ShimmerFrameLayout.this.mAnimationStarted;
                ShimmerFrameLayout.this.resetAll();
                if (ShimmerFrameLayout.this.mAutoStart || z) {
                    ShimmerFrameLayout.this.startShimmerAnimation();
                }
            }
        };
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        stopShimmerAnimation();
        if (this.mGlobalLayoutListener != null) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mGlobalLayoutListener = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (!this.mAnimationStarted || getWidth() <= 0 || getHeight() <= 0) {
            super.dispatchDraw(canvas);
        } else {
            dispatchDrawUsingBitmap(canvas);
        }
    }

    private static float clamp(float f, float f2, float f3) {
        return Math.min(f2, Math.max(f, f3));
    }

    private boolean dispatchDrawUsingBitmap(Canvas canvas) {
        Bitmap bitmapTryObtainRenderUnmaskBitmap = tryObtainRenderUnmaskBitmap();
        Bitmap bitmapTryObtainRenderMaskBitmap = tryObtainRenderMaskBitmap();
        if (bitmapTryObtainRenderUnmaskBitmap == null || bitmapTryObtainRenderMaskBitmap == null) {
            return false;
        }
        drawUnmasked(new Canvas(bitmapTryObtainRenderUnmaskBitmap));
        canvas.drawBitmap(bitmapTryObtainRenderUnmaskBitmap, 0.0f, 0.0f, this.mAlphaPaint);
        drawMasked(new Canvas(bitmapTryObtainRenderMaskBitmap));
        canvas.drawBitmap(bitmapTryObtainRenderMaskBitmap, 0.0f, 0.0f, (Paint) null);
        return true;
    }

    private Bitmap tryObtainRenderUnmaskBitmap() {
        if (this.mRenderUnmaskBitmap == null) {
            this.mRenderUnmaskBitmap = tryCreateRenderBitmap();
        }
        return this.mRenderUnmaskBitmap;
    }

    private Bitmap tryObtainRenderMaskBitmap() {
        if (this.mRenderMaskBitmap == null) {
            this.mRenderMaskBitmap = tryCreateRenderBitmap();
        }
        return this.mRenderMaskBitmap;
    }

    private Bitmap tryCreateRenderBitmap() {
        int width = getWidth();
        int height = getHeight();
        try {
            return createBitmapAndGcIfNecessary(width, height);
        } catch (OutOfMemoryError unused) {
            StringBuilder sb = new StringBuilder("ShimmerFrameLayout failed to create working bitmap (width = ");
            sb.append(width);
            sb.append(", height = ");
            sb.append(height);
            sb.append(")\n\n");
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                sb.append(stackTraceElement.toString());
                sb.append("\n");
            }
            Log.d(TAG, sb.toString());
            return null;
        }
    }

    private void drawUnmasked(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        super.dispatchDraw(canvas);
    }

    private void drawMasked(Canvas canvas) {
        Bitmap maskBitmap = getMaskBitmap();
        if (maskBitmap == null) {
            return;
        }
        int i = this.mMaskOffsetX;
        canvas.clipRect(i, this.mMaskOffsetY, maskBitmap.getWidth() + i, this.mMaskOffsetY + maskBitmap.getHeight());
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        super.dispatchDraw(canvas);
        canvas.drawBitmap(maskBitmap, this.mMaskOffsetX, this.mMaskOffsetY, this.mMaskPaint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAll() {
        stopShimmerAnimation();
        resetMaskBitmap();
        resetRenderedView();
    }

    private void resetMaskBitmap() {
        Bitmap bitmap = this.mMaskBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mMaskBitmap = null;
        }
    }

    private void resetRenderedView() {
        Bitmap bitmap = this.mRenderUnmaskBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mRenderUnmaskBitmap = null;
        }
        Bitmap bitmap2 = this.mRenderMaskBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mRenderMaskBitmap = null;
        }
    }

    private Bitmap getMaskBitmap() {
        Shader radialGradient;
        int i;
        int i2;
        int i3;
        Bitmap bitmap = this.mMaskBitmap;
        if (bitmap != null) {
            return bitmap;
        }
        int iMaskWidth = this.mMask.maskWidth(getWidth());
        int iMaskHeight = this.mMask.maskHeight(getHeight());
        this.mMaskBitmap = createBitmapAndGcIfNecessary(iMaskWidth, iMaskHeight);
        Canvas canvas = new Canvas(this.mMaskBitmap);
        if (AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape[this.mMask.shape.ordinal()] != 2) {
            int i4 = AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle[this.mMask.angle.ordinal()];
            int i5 = 0;
            if (i4 == 2) {
                i = iMaskHeight;
                i2 = 0;
                i3 = 0;
            } else if (i4 == 3) {
                i2 = 0;
                i3 = 0;
                i = 0;
                i5 = iMaskWidth;
            } else if (i4 != 4) {
                i3 = iMaskWidth;
                i2 = 0;
                i = 0;
            } else {
                i2 = iMaskHeight;
                i3 = 0;
                i = 0;
            }
            radialGradient = new LinearGradient(i5, i2, i3, i, this.mMask.getGradientColors(), this.mMask.getGradientPositions(), Shader.TileMode.REPEAT);
        } else {
            radialGradient = new RadialGradient(iMaskWidth / 2, iMaskHeight / 2, (float) (Math.max(iMaskWidth, iMaskHeight) / Math.sqrt(2.0d)), this.mMask.getGradientColors(), this.mMask.getGradientPositions(), Shader.TileMode.REPEAT);
        }
        canvas.rotate(this.mMask.tilt, iMaskWidth / 2, iMaskHeight / 2);
        Paint paint = new Paint();
        paint.setShader(radialGradient);
        float f = -(((int) (Math.sqrt(2.0d) * Math.max(iMaskWidth, iMaskHeight))) / 2);
        canvas.drawRect(f, f, iMaskWidth + r3, iMaskHeight + r3, paint);
        return this.mMaskBitmap;
    }

    /* renamed from: com.facebook.shimmer.ShimmerFrameLayout$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape;

        static {
            int[] iArr = new int[MaskAngle.values().length];
            $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle = iArr;
            try {
                iArr[MaskAngle.CW_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle[MaskAngle.CW_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle[MaskAngle.CW_180.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle[MaskAngle.CW_270.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[MaskShape.values().length];
            $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape = iArr2;
            try {
                iArr2[MaskShape.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape[MaskShape.RADIAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private Animator getShimmerAnimation() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            return valueAnimator;
        }
        int width = getWidth();
        int height = getHeight();
        int i = AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskShape[this.mMask.shape.ordinal()];
        int i2 = AnonymousClass3.$SwitchMap$com$facebook$shimmer$ShimmerFrameLayout$MaskAngle[this.mMask.angle.ordinal()];
        if (i2 == 2) {
            this.mMaskTranslation.set(0, -height, 0, height);
        } else if (i2 == 3) {
            this.mMaskTranslation.set(width, 0, -width, 0);
        } else if (i2 != 4) {
            this.mMaskTranslation.set(-width, 0, width, 0);
        } else {
            this.mMaskTranslation.set(0, height, 0, -height);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, (this.mRepeatDelay / this.mDuration) + 1.0f);
        this.mAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.mDuration + this.mRepeatDelay);
        this.mAnimator.setRepeatCount(this.mRepeatCount);
        this.mAnimator.setRepeatMode(this.mRepeatMode);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.facebook.shimmer.ShimmerFrameLayout.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float fMax = Math.max(0.0f, Math.min(1.0f, ((Float) valueAnimator2.getAnimatedValue()).floatValue()));
                float f = 1.0f - fMax;
                ShimmerFrameLayout.this.setMaskOffsetX((int) ((r1.mMaskTranslation.fromX * f) + (ShimmerFrameLayout.this.mMaskTranslation.toX * fMax)));
                ShimmerFrameLayout.this.setMaskOffsetY((int) ((r1.mMaskTranslation.fromY * f) + (ShimmerFrameLayout.this.mMaskTranslation.toY * fMax)));
            }
        });
        return this.mAnimator;
    }

    protected static Bitmap createBitmapAndGcIfNecessary(int i, int i2) {
        try {
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            System.gc();
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
    }
}
