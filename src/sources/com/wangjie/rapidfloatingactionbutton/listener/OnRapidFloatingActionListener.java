package com.wangjie.rapidfloatingactionbutton.listener;

import android.animation.AnimatorSet;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionContent;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;

/* loaded from: classes2.dex */
public interface OnRapidFloatingActionListener {
    void collapseContent();

    void expandContent();

    RapidFloatingActionButton obtainRFAButton();

    RapidFloatingActionContent obtainRFAContent();

    RapidFloatingActionLayout obtainRFALayout();

    void onCollapseAnimator(AnimatorSet animatorSet);

    void onExpandAnimator(AnimatorSet animatorSet);

    void onRFABClick();

    void toggleContent();
}
