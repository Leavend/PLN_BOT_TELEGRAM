package com.wangjie.rapidfloatingactionbutton.rfabgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingButtonGroupListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class RapidFloatingActionButtonGroup extends FrameLayout implements View.OnClickListener {
    public static final int NO_SELECTED = -1;
    private static final long SWITCH_ANIMATION_DEFAULT_DURATION = 280;
    private static final String TAG = "RapidFloatingActionButtonGroup";
    private List<RapidFloatingActionButton> allRfabs;
    private HashMap<String, RapidFloatingActionButton> allRfabsByIdentificationCode;
    private int currentSelectedIndex;
    private AccelerateInterpolator mAccelerateInterpolator;
    private DecelerateInterpolator mDecelerateInterpolator;
    private ScaleAnimation notSelectAnimation;
    private OnRapidFloatingButtonGroupListener onRapidFloatingButtonGroupListener;
    private ScaleAnimation selectAnimation;
    private ScaleAnimation selectCenterAnimation;

    private void initAfterConstructor() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public RapidFloatingActionButtonGroup(Context context) {
        super(context);
        this.selectAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.selectCenterAnimation = new ScaleAnimation(1.0f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.notSelectAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        this.allRfabs = new ArrayList();
        this.allRfabsByIdentificationCode = new HashMap<>();
        this.currentSelectedIndex = -1;
        initAfterConstructor();
    }

    public RapidFloatingActionButtonGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.selectAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.selectCenterAnimation = new ScaleAnimation(1.0f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.notSelectAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        this.allRfabs = new ArrayList();
        this.allRfabsByIdentificationCode = new HashMap<>();
        this.currentSelectedIndex = -1;
        initAfterConstructor();
    }

    public RapidFloatingActionButtonGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.selectCenterAnimation = new ScaleAnimation(1.0f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.notSelectAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        this.allRfabs = new ArrayList();
        this.allRfabsByIdentificationCode = new HashMap<>();
        this.currentSelectedIndex = -1;
        initAfterConstructor();
    }

    public RapidFloatingActionButtonGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.selectAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.selectCenterAnimation = new ScaleAnimation(1.0f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.notSelectAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mAccelerateInterpolator = new AccelerateInterpolator();
        this.allRfabs = new ArrayList();
        this.allRfabsByIdentificationCode = new HashMap<>();
        this.currentSelectedIndex = -1;
        initAfterConstructor();
    }

    public void setOnRapidFloatingButtonGroupListener(OnRapidFloatingButtonGroupListener onRapidFloatingButtonGroupListener) {
        this.onRapidFloatingButtonGroupListener = onRapidFloatingButtonGroupListener;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        reset();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt instanceof RapidFloatingActionButton) {
                addRFABToMemory((RapidFloatingActionButton) childAt);
            }
        }
        if (childCount > 0) {
            setSection(0, 0L);
        }
        OnRapidFloatingButtonGroupListener onRapidFloatingButtonGroupListener = this.onRapidFloatingButtonGroupListener;
        if (onRapidFloatingButtonGroupListener != null) {
            onRapidFloatingButtonGroupListener.onRFABGPrepared(this);
        }
    }

    private void reset() {
        this.allRfabs.clear();
        this.allRfabsByIdentificationCode.clear();
    }

    public void addRFABs(RapidFloatingActionButton... rapidFloatingActionButtonArr) {
        for (RapidFloatingActionButton rapidFloatingActionButton : rapidFloatingActionButtonArr) {
            addRFABToMemory(rapidFloatingActionButton);
            addView(rapidFloatingActionButton);
        }
    }

    private void addRFABToMemory(RapidFloatingActionButton rapidFloatingActionButton) {
        this.allRfabs.add(rapidFloatingActionButton);
        String identificationCode = rapidFloatingActionButton.getIdentificationCode();
        if ("".equals(identificationCode)) {
            throw new RuntimeException("RFAB[" + rapidFloatingActionButton + "]'s IDENTIFICATION CODE can not be IDENTIFICATION_CODE_NONE if you used RapidFloatingActionButtonGroup");
        }
        if (this.allRfabsByIdentificationCode.containsKey(identificationCode)) {
            throw new RuntimeException("RFAB[" + rapidFloatingActionButton + "] Duplicate IDENTIFICATION CODE");
        }
        this.allRfabsByIdentificationCode.put(identificationCode, rapidFloatingActionButton);
    }

    public RapidFloatingActionButton getRFABByIdentificationCode(String str) {
        return this.allRfabsByIdentificationCode.get(str);
    }

    public void setSection(int i) {
        setSection(i, SWITCH_ANIMATION_DEFAULT_DURATION);
    }

    public void setSection(int i, long j) {
        if (i < 0 || i >= this.allRfabs.size() || this.currentSelectedIndex == i) {
            return;
        }
        executeSwitchWithAnimation(i, j);
    }

    private void executeSwitchWithAnimation(final int i, long j) {
        long j2 = j / 2;
        int size = this.allRfabs.size();
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (i2 < size) {
            final RapidFloatingActionButton rapidFloatingActionButton = this.allRfabs.get(i2);
            rapidFloatingActionButton.setVisibility(4);
            if (i2 == i) {
                this.selectAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.wangjie.rapidfloatingactionbutton.rfabgroup.RapidFloatingActionButtonGroup.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                        rapidFloatingActionButton.setVisibility(0);
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        rapidFloatingActionButton.setVisibility(0);
                        rapidFloatingActionButton.clearAnimation();
                        RapidFloatingActionButtonGroup.this.currentSelectedIndex = i;
                    }
                });
                this.selectAnimation.setInterpolator(this.mDecelerateInterpolator);
                this.selectAnimation.setDuration(j2);
                rapidFloatingActionButton.setAnimation(this.selectAnimation);
                this.selectCenterAnimation.setInterpolator(this.mDecelerateInterpolator);
                this.selectCenterAnimation.setDuration(j2);
                final ImageView centerDrawableIv = rapidFloatingActionButton.getCenterDrawableIv();
                this.selectCenterAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.wangjie.rapidfloatingactionbutton.rfabgroup.RapidFloatingActionButtonGroup.2
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        centerDrawableIv.clearAnimation();
                    }
                });
                centerDrawableIv.setAnimation(this.selectCenterAnimation);
            } else if (i2 == this.currentSelectedIndex) {
                rapidFloatingActionButton.setVisibility(0);
                this.notSelectAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.wangjie.rapidfloatingactionbutton.rfabgroup.RapidFloatingActionButtonGroup.3
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                        rapidFloatingActionButton.setVisibility(0);
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        rapidFloatingActionButton.setVisibility(4);
                        rapidFloatingActionButton.clearAnimation();
                    }
                });
                this.notSelectAnimation.setInterpolator(this.mAccelerateInterpolator);
                this.notSelectAnimation.setDuration(j2);
                rapidFloatingActionButton.setAnimation(this.notSelectAnimation);
                z2 = true;
            }
            i2++;
            z = true;
        }
        if (z) {
            if (!z2) {
                this.selectAnimation.start();
                this.selectCenterAnimation.setStartOffset(j2);
                this.selectCenterAnimation.start();
            } else {
                this.selectAnimation.setStartOffset(j2);
                this.notSelectAnimation.start();
                this.selectAnimation.start();
                this.selectCenterAnimation.setStartOffset(j2 * 2);
                this.selectCenterAnimation.start();
            }
        }
    }
}
