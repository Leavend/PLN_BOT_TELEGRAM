package com.wangjie.rapidfloatingactionbutton;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener;

/* loaded from: classes2.dex */
public abstract class RapidFloatingActionContent extends FrameLayout {
    protected OnRapidFloatingActionListener onRapidFloatingActionListener;
    private View rootView;

    protected void initAfterRFABHelperBuild() {
    }

    protected void initInConstructor() {
    }

    protected abstract void initialContentViews(View view);

    public void onCollapseAnimator(AnimatorSet animatorSet) {
    }

    public void onExpandAnimator(AnimatorSet animatorSet) {
    }

    public RapidFloatingActionContent(Context context) {
        super(context);
        initInConstructor();
    }

    public RapidFloatingActionContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initInConstructor();
    }

    public RapidFloatingActionContent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initInConstructor();
    }

    public RapidFloatingActionContent(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initInConstructor();
    }

    protected void setOnRapidFloatingActionListener(OnRapidFloatingActionListener onRapidFloatingActionListener) {
        this.onRapidFloatingActionListener = onRapidFloatingActionListener;
    }

    public RapidFloatingActionContent setRootView(View view) {
        if (view == null) {
            return this;
        }
        this.rootView = view;
        removeAllViews();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        setLayoutParams(layoutParams);
        addView(this.rootView);
        initialContentViews(this.rootView);
        return this;
    }

    public RapidFloatingActionContent setRootView(int i) {
        return setRootView(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null));
    }
}
