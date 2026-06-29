package com.wangjie.rapidfloatingactionbutton.contentimpl.viewbase;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent;
import com.wangjie.rapidfloatingactionbutton.widget.AnimationView;

/* loaded from: classes2.dex */
public abstract class RapidFloatingActionContentViewBase extends RapidFloatingActionContent implements AnimationView.OnViewAnimationDrawableListener {
    private static final String TAG = "RapidFloatingActionContentViewBase";
    private AnimationView mAnimationView;
    private View realContentView;

    protected abstract View getContentView();

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    protected void initialContentViews(View view) {
    }

    public RapidFloatingActionContentViewBase(Context context) {
        super(context);
    }

    public RapidFloatingActionContentViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RapidFloatingActionContentViewBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RapidFloatingActionContentViewBase(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    protected void initAfterRFABHelperBuild() {
        this.realContentView = getContentView();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.realContentView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(this.realContentView);
        AnimationView animationView = new AnimationView(getContext());
        this.mAnimationView = animationView;
        ViewCompat.setLayerType(animationView, 1, null);
        this.mAnimationView.setLayoutParams(this.realContentView.getLayoutParams());
        this.mAnimationView.setDrawView(this.realContentView);
        this.mAnimationView.setOnViewAnimationDrawableListener(this);
        frameLayout.addView(this.mAnimationView);
        setRootView(frameLayout);
        setMeasureAllChildren(true);
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.mAnimationView.setLayoutParams(new FrameLayout.LayoutParams(getMeasuredWidth(), getMeasuredHeight()));
        this.mAnimationView.setMinRadius(this.onRapidFloatingActionListener.obtainRFAButton().getRfabProperties().getRealSizePx(getContext()) / 2);
        this.mAnimationView.initialDraw();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.widget.AnimationView.OnViewAnimationDrawableListener
    public void onAnimationDrawableOpenStart() {
        this.realContentView.setVisibility(8);
        this.mAnimationView.setVisibility(0);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.widget.AnimationView.OnViewAnimationDrawableListener
    public void onAnimationDrawableOpenEnd() {
        this.realContentView.setVisibility(0);
        this.mAnimationView.setVisibility(8);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.widget.AnimationView.OnViewAnimationDrawableListener
    public void onAnimationDrawableCloseStart() {
        this.realContentView.setVisibility(8);
        this.mAnimationView.setVisibility(0);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.widget.AnimationView.OnViewAnimationDrawableListener
    public void onAnimationDrawableCloseEnd() {
        this.realContentView.setVisibility(0);
        this.mAnimationView.setVisibility(8);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    public void onExpandAnimator(AnimatorSet animatorSet) {
        AnimationView animationView = this.mAnimationView;
        if (animationView != null) {
            animatorSet.playTogether(animationView.getOpenAnimator());
        }
    }

    @Override // com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent
    public void onCollapseAnimator(AnimatorSet animatorSet) {
        AnimationView animationView = this.mAnimationView;
        if (animationView != null) {
            animatorSet.playTogether(animationView.getCloseAnimator());
        }
    }
}
