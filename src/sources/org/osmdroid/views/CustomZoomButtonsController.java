package org.osmdroid.views;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import java.lang.Thread;

/* loaded from: classes3.dex */
public class CustomZoomButtonsController {
    private boolean detached;
    private float mAlpha01;
    private CustomZoomButtonsDisplay mDisplay;
    private final ValueAnimator mFadeOutAnimation;
    private boolean mJustActivated;
    private long mLatestActivation;
    private OnZoomListener mListener;
    private final MapView mMapView;
    private final Runnable mRunnable;
    private Thread mThread;
    private boolean mZoomInEnabled;
    private boolean mZoomOutEnabled;
    private final Object mThreadSync = new Object();
    private Visibility mVisibility = Visibility.NEVER;
    private int mFadeOutAnimationDurationInMillis = 500;
    private int mShowDelayInMillis = 3500;

    public interface OnZoomListener {
        void onVisibilityChanged(boolean z);

        void onZoom(boolean z);
    }

    public enum Visibility {
        ALWAYS,
        NEVER,
        SHOW_AND_FADEOUT
    }

    public CustomZoomButtonsController(MapView mapView) {
        this.mMapView = mapView;
        this.mDisplay = new CustomZoomButtonsDisplay(mapView);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mFadeOutAnimation = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat.setDuration(this.mFadeOutAnimationDurationInMillis);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.osmdroid.views.CustomZoomButtonsController.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (CustomZoomButtonsController.this.detached) {
                    CustomZoomButtonsController.this.mFadeOutAnimation.cancel();
                    return;
                }
                CustomZoomButtonsController.this.mAlpha01 = 1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CustomZoomButtonsController.this.invalidate();
            }
        });
        this.mRunnable = new Runnable() { // from class: org.osmdroid.views.CustomZoomButtonsController.2
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (true) {
                    long jNowInMillis = (CustomZoomButtonsController.this.mLatestActivation + CustomZoomButtonsController.this.mShowDelayInMillis) - CustomZoomButtonsController.this.nowInMillis();
                    if (jNowInMillis <= 0) {
                        CustomZoomButtonsController.this.startFadeOut();
                        return;
                    }
                    try {
                        Thread.sleep(jNowInMillis, 0);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        };
    }

    public void setZoomInEnabled(boolean z) {
        this.mZoomInEnabled = z;
    }

    public void setZoomOutEnabled(boolean z) {
        this.mZoomOutEnabled = z;
    }

    public CustomZoomButtonsDisplay getDisplay() {
        return this.mDisplay;
    }

    public void setOnZoomListener(OnZoomListener onZoomListener) {
        this.mListener = onZoomListener;
    }

    /* renamed from: org.osmdroid.views.CustomZoomButtonsController$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility;

        static {
            int[] iArr = new int[Visibility.values().length];
            $SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility = iArr;
            try {
                iArr[Visibility.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility[Visibility.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility[Visibility.SHOW_AND_FADEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void setVisibility(Visibility visibility) {
        this.mVisibility = visibility;
        int i = AnonymousClass4.$SwitchMap$org$osmdroid$views$CustomZoomButtonsController$Visibility[this.mVisibility.ordinal()];
        if (i == 1) {
            this.mAlpha01 = 1.0f;
        } else if (i == 2 || i == 3) {
            this.mAlpha01 = 0.0f;
        }
    }

    public void setShowFadeOutDelays(int i, int i2) {
        this.mShowDelayInMillis = i;
        this.mFadeOutAnimationDurationInMillis = i2;
    }

    public void onDetach() {
        this.detached = true;
        stopFadeOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long nowInMillis() {
        return System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFadeOut() {
        if (this.detached) {
            return;
        }
        this.mFadeOutAnimation.setStartDelay(0L);
        this.mMapView.post(new Runnable() { // from class: org.osmdroid.views.CustomZoomButtonsController.3
            @Override // java.lang.Runnable
            public void run() {
                CustomZoomButtonsController.this.mFadeOutAnimation.start();
            }
        });
    }

    private void stopFadeOut() {
        this.mFadeOutAnimation.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidate() {
        if (this.detached) {
            return;
        }
        this.mMapView.postInvalidate();
    }

    public void activate() {
        if (!this.detached && this.mVisibility == Visibility.SHOW_AND_FADEOUT) {
            float f = this.mAlpha01;
            if (!this.mJustActivated) {
                this.mJustActivated = f == 0.0f;
            } else {
                this.mJustActivated = false;
            }
            stopFadeOut();
            this.mAlpha01 = 1.0f;
            this.mLatestActivation = nowInMillis();
            invalidate();
            Thread thread = this.mThread;
            if (thread == null || thread.getState() == Thread.State.TERMINATED) {
                synchronized (this.mThreadSync) {
                    Thread thread2 = this.mThread;
                    if (thread2 == null || thread2.getState() == Thread.State.TERMINATED) {
                        Thread thread3 = new Thread(this.mRunnable);
                        this.mThread = thread3;
                        thread3.setName(getClass().getName() + "#active");
                        this.mThread.start();
                    }
                }
            }
        }
    }

    private boolean checkJustActivated() {
        if (!this.mJustActivated) {
            return false;
        }
        this.mJustActivated = false;
        return true;
    }

    public boolean isTouched(MotionEvent motionEvent) {
        OnZoomListener onZoomListener;
        OnZoomListener onZoomListener2;
        if (this.mAlpha01 == 0.0f || checkJustActivated()) {
            return false;
        }
        if (this.mDisplay.isTouched(motionEvent, true)) {
            if (this.mZoomInEnabled && (onZoomListener2 = this.mListener) != null) {
                onZoomListener2.onZoom(true);
            }
            return true;
        }
        if (!this.mDisplay.isTouched(motionEvent, false)) {
            return false;
        }
        if (this.mZoomOutEnabled && (onZoomListener = this.mListener) != null) {
            onZoomListener.onZoom(false);
        }
        return true;
    }

    @Deprecated
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return isTouched(motionEvent);
    }

    @Deprecated
    public boolean onLongPress(MotionEvent motionEvent) {
        return isTouched(motionEvent);
    }

    public void draw(Canvas canvas) {
        this.mDisplay.draw(canvas, this.mAlpha01, this.mZoomInEnabled, this.mZoomOutEnabled);
    }
}
