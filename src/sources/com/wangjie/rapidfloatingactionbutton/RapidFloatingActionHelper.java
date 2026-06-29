package com.wangjie.rapidfloatingactionbutton;

import android.animation.AnimatorSet;
import android.content.Context;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener;

/* loaded from: classes2.dex */
public final class RapidFloatingActionHelper implements OnRapidFloatingActionListener {
    private Context context;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionContent rfaContent;
    private RapidFloatingActionLayout rfaLayout;

    public RapidFloatingActionHelper(Context context, RapidFloatingActionLayout rapidFloatingActionLayout, RapidFloatingActionButton rapidFloatingActionButton, RapidFloatingActionContent rapidFloatingActionContent) {
        this.context = context;
        this.rfaLayout = rapidFloatingActionLayout;
        this.rfaBtn = rapidFloatingActionButton;
        this.rfaContent = rapidFloatingActionContent;
    }

    public RapidFloatingActionHelper setRfaLayout(RapidFloatingActionLayout rapidFloatingActionLayout) {
        this.rfaLayout = rapidFloatingActionLayout;
        return this;
    }

    public RapidFloatingActionHelper setRfaButton(RapidFloatingActionButton rapidFloatingActionButton) {
        this.rfaBtn = rapidFloatingActionButton;
        return this;
    }

    public RapidFloatingActionHelper setRfaContent(RapidFloatingActionContent rapidFloatingActionContent) {
        this.rfaContent = rapidFloatingActionContent;
        return this;
    }

    public final RapidFloatingActionHelper build() {
        this.rfaLayout.setOnRapidFloatingActionListener(this);
        this.rfaBtn.setOnRapidFloatingActionListener(this);
        this.rfaContent.setOnRapidFloatingActionListener(this);
        this.rfaLayout.setContentView(this.rfaContent);
        this.rfaContent.initAfterRFABHelperBuild();
        return this;
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void onRFABClick() {
        this.rfaLayout.toggleContent();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void toggleContent() {
        this.rfaLayout.toggleContent();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void expandContent() {
        this.rfaLayout.expandContent();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void collapseContent() {
        this.rfaLayout.collapseContent();
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public final RapidFloatingActionLayout obtainRFALayout() {
        return this.rfaLayout;
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public final RapidFloatingActionButton obtainRFAButton() {
        return this.rfaBtn;
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public final RapidFloatingActionContent obtainRFAContent() {
        return this.rfaContent;
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void onExpandAnimator(AnimatorSet animatorSet) {
        this.rfaContent.onExpandAnimator(animatorSet);
        this.rfaBtn.onExpandAnimator(animatorSet);
    }

    @Override // com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener
    public void onCollapseAnimator(AnimatorSet animatorSet) {
        this.rfaContent.onCollapseAnimator(animatorSet);
        this.rfaBtn.onCollapseAnimator(animatorSet);
    }
}
