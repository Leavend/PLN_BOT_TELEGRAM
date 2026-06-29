package com.wangjie.rapidfloatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener;

/* loaded from: classes2.dex */
public class RapidFloatingActionLayout extends RelativeLayout implements View.OnClickListener {
    public static final long ANIMATION_DURATION = 150;
    private static final String TAG = "RapidFloatingActionLayout";
    private AnimatorSet animatorSet;
    private ObjectAnimator contentAnimator;
    private RapidFloatingActionContent contentView;
    private boolean disableContentDefaultAnimation;
    private ObjectAnimator fillFrameAnimator;
    private View fillFrameView;
    private float frameAlpha;
    private int frameColor;
    private boolean isContentAboveLayout;
    private boolean isExpanded;
    private AccelerateInterpolator mAccelerateInterpolator;
    private OnRapidFloatingActionListener onRapidFloatingActionListener;

    private void initAfterConstructor() {
    }

    public RapidFloatingActionLayout(Context context) {
        super(context);
        this.isContentAboveLayout = true;
        this.disableContentDefaultAnimation = false;
        this.isExpanded = false;
        this.contentAnimator = new ObjectAnimator();
        this.fillFrameAnimator = new ObjectAnimator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        initAfterConstructor();
    }

    public RapidFloatingActionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isContentAboveLayout = true;
        this.disableContentDefaultAnimation = false;
        this.isExpanded = false;
        this.contentAnimator = new ObjectAnimator();
        this.fillFrameAnimator = new ObjectAnimator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        parserAttrs(context, attributeSet, 0, 0);
        initAfterConstructor();
    }

    public RapidFloatingActionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isContentAboveLayout = true;
        this.disableContentDefaultAnimation = false;
        this.isExpanded = false;
        this.contentAnimator = new ObjectAnimator();
        this.fillFrameAnimator = new ObjectAnimator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        parserAttrs(context, attributeSet, i, 0);
        initAfterConstructor();
    }

    public RapidFloatingActionLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isContentAboveLayout = true;
        this.disableContentDefaultAnimation = false;
        this.isExpanded = false;
        this.contentAnimator = new ObjectAnimator();
        this.fillFrameAnimator = new ObjectAnimator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        parserAttrs(context, attributeSet, i, i2);
        initAfterConstructor();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x003c A[PHI: r4
      0x003c: PHI (r4v10 float) = (r4v8 float), (r4v9 float) binds: [B:3:0x003a, B:6:0x0041] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parserAttrs(android.content.Context r2, android.util.AttributeSet r3, int r4, int r5) {
        /*
            r1 = this;
            int[] r0 = com.wangjie.rapidfloatingactionbutton.R.styleable.RapidFloatingActionLayout
            android.content.res.TypedArray r2 = r2.obtainStyledAttributes(r3, r0, r4, r5)
            int r3 = com.wangjie.rapidfloatingactionbutton.R.styleable.RapidFloatingActionLayout_rfal_frame_color
            android.content.Context r4 = r1.getContext()
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.wangjie.rapidfloatingactionbutton.R.color.rfab__color_frame
            int r4 = r4.getColor(r5)
            int r3 = r2.getColor(r3, r4)
            r1.frameColor = r3
            int r3 = com.wangjie.rapidfloatingactionbutton.R.styleable.RapidFloatingActionLayout_rfal_frame_alpha
            android.content.res.Resources r4 = r1.getResources()
            int r5 = com.wangjie.rapidfloatingactionbutton.R.string.rfab_rfal__float_convert_color_alpha
            java.lang.String r4 = r4.getString(r5)
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            float r4 = r4.floatValue()
            float r3 = r2.getFloat(r3, r4)
            r1.frameAlpha = r3
            r4 = 1065353216(0x3f800000, float:1.0)
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 <= 0) goto L3e
        L3c:
            r3 = r4
            goto L44
        L3e:
            r4 = 0
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 >= 0) goto L44
            goto L3c
        L44:
            r1.frameAlpha = r3
            r2.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout.parserAttrs(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    public void setOnRapidFloatingActionListener(OnRapidFloatingActionListener onRapidFloatingActionListener) {
        this.onRapidFloatingActionListener = onRapidFloatingActionListener;
    }

    public void setIsContentAboveLayout(boolean z) {
        this.isContentAboveLayout = z;
    }

    public boolean isContentAboveLayout() {
        return this.isContentAboveLayout;
    }

    public void setDisableContentDefaultAnimation(boolean z) {
        this.disableContentDefaultAnimation = z;
    }

    public RapidFloatingActionLayout setContentView(RapidFloatingActionContent rapidFloatingActionContent) {
        OnRapidFloatingActionListener onRapidFloatingActionListener;
        if (rapidFloatingActionContent == null) {
            throw new RuntimeException("contentView can not be null");
        }
        RapidFloatingActionContent rapidFloatingActionContent2 = this.contentView;
        if (rapidFloatingActionContent2 != null) {
            removeView(rapidFloatingActionContent2);
            Log.w(TAG, "contentView: [" + this.contentView + "] is already initialed");
        }
        this.contentView = rapidFloatingActionContent;
        View view = new View(getContext());
        this.fillFrameView = view;
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.fillFrameView.setBackgroundColor(this.frameColor);
        this.fillFrameView.setVisibility(8);
        this.fillFrameView.setOnClickListener(this);
        addView(this.fillFrameView, Math.max(getChildCount() - 1, 0));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int id2 = this.onRapidFloatingActionListener.obtainRFAButton().getId();
        layoutParams.addRule(2, id2);
        layoutParams.addRule(7, id2);
        if (!this.isContentAboveLayout && (onRapidFloatingActionListener = this.onRapidFloatingActionListener) != null) {
            layoutParams.bottomMargin = -onRapidFloatingActionListener.obtainRFAButton().getRfabProperties().getRealSizePx(getContext());
        }
        this.contentView.setLayoutParams(layoutParams);
        this.contentView.setVisibility(8);
        addView(this.contentView);
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.fillFrameView == view) {
            collapseContent();
        }
    }

    public void setFrameColor(int i) {
        this.frameColor = i;
        View view = this.fillFrameView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void setFrameAlpha(float f) {
        this.frameAlpha = f;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public void toggleContent() {
        if (this.isExpanded) {
            collapseContent();
        } else {
            expandContent();
        }
    }

    public void expandContent() {
        if (this.isExpanded) {
            return;
        }
        endAnimatorSet();
        this.isExpanded = true;
        this.fillFrameAnimator.setTarget(this.fillFrameView);
        this.fillFrameAnimator.setFloatValues(0.0f, this.frameAlpha);
        this.fillFrameAnimator.setPropertyName("alpha");
        AnimatorSet animatorSet = new AnimatorSet();
        this.animatorSet = animatorSet;
        if (this.disableContentDefaultAnimation) {
            animatorSet.playTogether(this.fillFrameAnimator);
        } else {
            this.contentAnimator.setTarget(this.contentView);
            this.contentAnimator.setFloatValues(0.0f, 1.0f);
            this.contentAnimator.setPropertyName("alpha");
            this.animatorSet.playTogether(this.contentAnimator, this.fillFrameAnimator);
        }
        this.animatorSet.setDuration(150L);
        this.animatorSet.setInterpolator(this.mAccelerateInterpolator);
        this.onRapidFloatingActionListener.onExpandAnimator(this.animatorSet);
        this.animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                RapidFloatingActionLayout.this.contentView.setVisibility(0);
                RapidFloatingActionLayout.this.fillFrameView.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                RapidFloatingActionLayout.this.isExpanded = true;
            }
        });
        this.animatorSet.start();
    }

    public void collapseContent() {
        if (this.isExpanded) {
            endAnimatorSet();
            this.isExpanded = false;
            this.fillFrameAnimator.setTarget(this.fillFrameView);
            this.fillFrameAnimator.setFloatValues(this.frameAlpha, 0.0f);
            this.fillFrameAnimator.setPropertyName("alpha");
            AnimatorSet animatorSet = new AnimatorSet();
            this.animatorSet = animatorSet;
            if (this.disableContentDefaultAnimation) {
                animatorSet.playTogether(this.fillFrameAnimator);
            } else {
                this.contentAnimator.setTarget(this.contentView);
                this.contentAnimator.setFloatValues(1.0f, 0.0f);
                this.contentAnimator.setPropertyName("alpha");
                this.animatorSet.playTogether(this.contentAnimator, this.fillFrameAnimator);
            }
            this.animatorSet.setDuration(150L);
            this.animatorSet.setInterpolator(this.mAccelerateInterpolator);
            this.onRapidFloatingActionListener.onCollapseAnimator(this.animatorSet);
            this.animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    RapidFloatingActionLayout.this.fillFrameView.setVisibility(0);
                    RapidFloatingActionLayout.this.contentView.setVisibility(0);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    RapidFloatingActionLayout.this.fillFrameView.setVisibility(8);
                    RapidFloatingActionLayout.this.contentView.setVisibility(8);
                    RapidFloatingActionLayout.this.isExpanded = false;
                }
            });
            this.animatorSet.start();
        }
    }

    private void endAnimatorSet() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.end();
        }
    }

    public RapidFloatingActionContent getContentView() {
        return this.contentView;
    }
}
