package com.wangjie.rapidfloatingactionbutton.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.wangjie.rapidfloatingactionbutton.util.RFABIOUtil;

/* loaded from: classes2.dex */
public class AnimationView extends View {
    private static final int DURATION_DEFAULT = 300;
    private static final String TAG = "AnimationView";
    private ValueAnimator animator;
    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
    private AnimatorListenerAdapter closeAnimatorListenerAdapter;
    private DecelerateInterpolator closeInterpolator;
    private int currentRadius;
    private View drawView;
    private int height;
    private PorterDuffXfermode mProPorterDuffXfermode;
    private int minRadius;
    private OnViewAnimationDrawableListener onViewAnimationDrawableListener;
    private AnimatorListenerAdapter openAnimatorListenerAdapter;
    private DecelerateInterpolator openInterpolator;
    private Paint paint;
    private int radius;
    private Bitmap viewBitmap;
    private int width;

    public interface OnViewAnimationDrawableListener {
        void onAnimationDrawableCloseEnd();

        void onAnimationDrawableCloseStart();

        void onAnimationDrawableOpenEnd();

        void onAnimationDrawableOpenStart();
    }

    public void setOnViewAnimationDrawableListener(OnViewAnimationDrawableListener onViewAnimationDrawableListener) {
        this.onViewAnimationDrawableListener = onViewAnimationDrawableListener;
    }

    public AnimationView(Context context) {
        super(context);
        this.openInterpolator = new DecelerateInterpolator(0.6f);
        this.closeInterpolator = new DecelerateInterpolator(1.8f);
        this.animator = new ValueAnimator();
        this.mProPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.minRadius = 0;
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimationView.this.currentRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AnimationView.this.invalidate();
            }
        };
        this.openAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenEnd();
                }
            }
        };
        this.closeAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseEnd();
                }
            }
        };
        init();
    }

    public AnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.openInterpolator = new DecelerateInterpolator(0.6f);
        this.closeInterpolator = new DecelerateInterpolator(1.8f);
        this.animator = new ValueAnimator();
        this.mProPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.minRadius = 0;
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimationView.this.currentRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AnimationView.this.invalidate();
            }
        };
        this.openAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenEnd();
                }
            }
        };
        this.closeAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseEnd();
                }
            }
        };
        init();
    }

    public AnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.openInterpolator = new DecelerateInterpolator(0.6f);
        this.closeInterpolator = new DecelerateInterpolator(1.8f);
        this.animator = new ValueAnimator();
        this.mProPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.minRadius = 0;
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimationView.this.currentRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AnimationView.this.invalidate();
            }
        };
        this.openAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenEnd();
                }
            }
        };
        this.closeAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseEnd();
                }
            }
        };
        init();
    }

    public AnimationView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.openInterpolator = new DecelerateInterpolator(0.6f);
        this.closeInterpolator = new DecelerateInterpolator(1.8f);
        this.animator = new ValueAnimator();
        this.mProPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.minRadius = 0;
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimationView.this.currentRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AnimationView.this.invalidate();
            }
        };
        this.openAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableOpenEnd();
                }
            }
        };
        this.closeAnimatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.widget.AnimationView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseStart();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                AnimationView.this.clearAnimation();
                if (AnimationView.this.onViewAnimationDrawableListener != null) {
                    AnimationView.this.onViewAnimationDrawableListener.onAnimationDrawableCloseEnd();
                }
            }
        };
        init();
    }

    private void init() {
        setBackgroundColor(0);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setColor(-7829368);
        this.animator.addUpdateListener(this.animatorUpdateListener);
    }

    public void setDrawView(View view) {
        this.drawView = view;
    }

    public void setMinRadius(int i) {
        this.minRadius = i;
    }

    public void initialDraw() {
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
        int i = this.width;
        int iSqrt = (int) Math.sqrt((i * i) + (r0 * r0));
        this.radius = iSqrt;
        this.currentRadius = iSqrt;
        generateViewBitmap();
        invalidate();
    }

    private void generateViewBitmap() {
        Bitmap bitmapConvertViewToBitmap;
        if (this.viewBitmap != null || (bitmapConvertViewToBitmap = convertViewToBitmap(this.drawView)) == null) {
            return;
        }
        this.viewBitmap = Bitmap.createBitmap(bitmapConvertViewToBitmap, 0, 0, bitmapConvertViewToBitmap.getWidth(), bitmapConvertViewToBitmap.getHeight());
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        generateViewBitmap();
        canvas.drawColor(0);
        this.paint.setXfermode(null);
        int i = this.width;
        int i2 = this.minRadius;
        canvas.drawCircle(i - i2, this.height - i2, this.currentRadius, this.paint);
        this.paint.setXfermode(this.mProPorterDuffXfermode);
        Bitmap bitmap = this.viewBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.paint);
        }
    }

    public void startOpenAnimation() {
        startOpenAnimation(300L);
    }

    public void startOpenAnimation(long j) {
        getOpenAnimator(j).start();
    }

    public void startCloseAnimation() {
        startCloseAnimation(300L);
    }

    public void startCloseAnimation(long j) {
        getCloseAnimator(j).start();
    }

    public ValueAnimator getOpenAnimator() {
        return getOpenAnimator(300L);
    }

    public ValueAnimator getOpenAnimator(long j) {
        this.animator.removeAllListeners();
        this.animator.setIntValues(this.minRadius, this.radius);
        this.animator.setDuration(j);
        this.animator.addListener(this.openAnimatorListenerAdapter);
        this.animator.setInterpolator(this.openInterpolator);
        return this.animator;
    }

    public ValueAnimator getCloseAnimator() {
        return getCloseAnimator(300L);
    }

    public ValueAnimator getCloseAnimator(long j) {
        this.animator.removeAllListeners();
        this.animator.setIntValues(this.radius, this.minRadius);
        this.animator.setDuration(j);
        this.animator.addListener(this.closeAnimatorListenerAdapter);
        this.animator.setInterpolator(this.closeInterpolator);
        return this.animator;
    }

    public void recycle() {
        RFABIOUtil.recycleBitmap(this.viewBitmap);
    }

    public Bitmap convertViewToBitmap(View view) {
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }

    public static Bitmap convertViewToBitmapWithDraw(View view, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycle();
    }
}
