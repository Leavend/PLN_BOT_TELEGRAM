package com.wangjie.rapidfloatingactionbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.motion.widget.Key;
import com.wangjie.rapidfloatingactionbutton.constants.RFABSize;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingActionListener;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingButtonSeparateListener;
import com.wangjie.rapidfloatingactionbutton.util.RFABImageUtil;
import com.wangjie.rapidfloatingactionbutton.util.RFABShape;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;
import com.wangjie.rapidfloatingactionbutton.util.RFABViewUtil;
import com.wangjie.rapidfloatingactionbutton.widget.CircleButtonDrawable;
import com.wangjie.rapidfloatingactionbutton.widget.CircleButtonProperties;

/* loaded from: classes2.dex */
public class RapidFloatingActionButton extends FrameLayout implements View.OnClickListener {
    private static final int DEFAULT_BUTTON_DRAWABLE_RES_ID = R.drawable.rfab__drawable_rfab_default;
    public static final String IDENTIFICATION_CODE_NONE = "";
    private Drawable buttonDrawable;
    private int buttonDrawableSize;
    private ImageView centerDrawableIv;
    private String identificationCode;
    private ObjectAnimator mDrawableAnimator;
    private OvershootInterpolator mOvershootInterpolator;
    private int normalColor;
    private OnRapidFloatingActionListener onRapidFloatingActionListener;
    private OnRapidFloatingButtonSeparateListener onRapidFloatingButtonSeparateListener;
    private int pressedColor;
    private CircleButtonProperties rfabProperties;

    public String getIdentificationCode() {
        return this.identificationCode;
    }

    public void setIdentificationCode(String str) {
        this.identificationCode = str;
    }

    public ImageView getCenterDrawableIv() {
        return this.centerDrawableIv;
    }

    public void setOnRapidFloatingActionListener(OnRapidFloatingActionListener onRapidFloatingActionListener) {
        this.onRapidFloatingActionListener = onRapidFloatingActionListener;
    }

    public void setOnRapidFloatingButtonSeparateListener(OnRapidFloatingButtonSeparateListener onRapidFloatingButtonSeparateListener) {
        this.onRapidFloatingButtonSeparateListener = onRapidFloatingButtonSeparateListener;
    }

    public RapidFloatingActionButton(Context context) {
        super(context);
        this.identificationCode = "";
        this.rfabProperties = new CircleButtonProperties();
        initAfterConstructor();
    }

    public RapidFloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.identificationCode = "";
        this.rfabProperties = new CircleButtonProperties();
        parserAttrs(context, attributeSet, 0, 0);
        initAfterConstructor();
    }

    public RapidFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.identificationCode = "";
        this.rfabProperties = new CircleButtonProperties();
        parserAttrs(context, attributeSet, i, 0);
        initAfterConstructor();
    }

    public RapidFloatingActionButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.identificationCode = "";
        this.rfabProperties = new CircleButtonProperties();
        parserAttrs(context, attributeSet, i, i2);
        initAfterConstructor();
    }

    private void parserAttrs(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RapidFloatingActionButton, i, i2);
        try {
            String string = typedArrayObtainStyledAttributes.getString(R.styleable.RapidFloatingActionButton_rfab_identification_code);
            this.identificationCode = string;
            if (string == null) {
                this.identificationCode = "";
            }
            this.buttonDrawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.RapidFloatingActionButton_rfab_drawable);
            this.normalColor = typedArrayObtainStyledAttributes.getColor(R.styleable.RapidFloatingActionButton_rfab_color_normal, getContext().getResources().getColor(R.color.rfab__color_background_normal));
            this.pressedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.RapidFloatingActionButton_rfab_color_pressed, getContext().getResources().getColor(R.color.rfab__color_background_pressed));
            this.rfabProperties.setStandardSize(RFABSize.getRFABSizeByCode(typedArrayObtainStyledAttributes.getInt(R.styleable.RapidFloatingActionButton_rfab_size, RFABSize.NORMAL.getCode())));
            this.rfabProperties.setShadowColor(typedArrayObtainStyledAttributes.getInt(R.styleable.RapidFloatingActionButton_rfab_shadow_color, 0));
            this.rfabProperties.setShadowDx(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RapidFloatingActionButton_rfab_shadow_dx, 0));
            this.rfabProperties.setShadowDy(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RapidFloatingActionButton_rfab_shadow_dy, 0));
            this.rfabProperties.setShadowRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RapidFloatingActionButton_rfab_shadow_radius, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void initAfterConstructor() {
        setOnClickListener(this);
        this.buttonDrawableSize = RFABTextUtil.dip2px(getContext(), 24.0f);
        refreshRFABDisplay();
    }

    private void refreshRFABDisplay() {
        if (this.buttonDrawable == null) {
            this.buttonDrawable = RFABImageUtil.getResourceDrawableBounded(getContext(), DEFAULT_BUTTON_DRAWABLE_RES_ID, this.buttonDrawableSize);
        }
        CircleButtonDrawable circleButtonDrawable = new CircleButtonDrawable(getContext(), this.rfabProperties, this.normalColor);
        RFABViewUtil.setBackgroundDrawable(this, RFABShape.selectorClickSimple(circleButtonDrawable, new CircleButtonDrawable(getContext(), this.rfabProperties, this.pressedColor)));
        setLayerType(1, circleButtonDrawable.getPaint());
        if (this.centerDrawableIv == null) {
            removeAllViews();
            ImageView imageView = new ImageView(getContext());
            this.centerDrawableIv = imageView;
            addView(imageView);
            int i = this.buttonDrawableSize;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i);
            layoutParams.gravity = 17;
            this.centerDrawableIv.setLayoutParams(layoutParams);
        }
        resetCenterImageView();
    }

    private void resetCenterImageView() {
        Drawable drawable = this.buttonDrawable;
        int i = this.buttonDrawableSize;
        drawable.setBounds(0, 0, i, i);
        this.centerDrawableIv.setImageDrawable(this.buttonDrawable);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int realSizePx = this.rfabProperties.getRealSizePx(getContext());
        setMeasuredDimension(realSizePx, realSizePx);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnRapidFloatingActionListener onRapidFloatingActionListener = this.onRapidFloatingActionListener;
        if (onRapidFloatingActionListener != null) {
            onRapidFloatingActionListener.onRFABClick();
        }
        OnRapidFloatingButtonSeparateListener onRapidFloatingButtonSeparateListener = this.onRapidFloatingButtonSeparateListener;
        if (onRapidFloatingButtonSeparateListener != null) {
            onRapidFloatingButtonSeparateListener.onRFABClick();
        }
    }

    public CircleButtonProperties getRfabProperties() {
        return this.rfabProperties;
    }

    public void setButtonDrawable(Drawable drawable) {
        this.buttonDrawable = drawable;
    }

    public void setNormalColor(int i) {
        this.normalColor = i;
    }

    public void setPressedColor(int i) {
        this.pressedColor = i;
    }

    public void build() {
        refreshRFABDisplay();
    }

    public void onExpandAnimator(AnimatorSet animatorSet) {
        ensureDrawableAnimator();
        ensureDrawableInterpolator();
        this.mDrawableAnimator.cancel();
        this.mDrawableAnimator.setTarget(this.centerDrawableIv);
        this.mDrawableAnimator.setFloatValues(0.0f, -45.0f);
        this.mDrawableAnimator.setPropertyName(Key.ROTATION);
        this.mDrawableAnimator.setInterpolator(this.mOvershootInterpolator);
        animatorSet.playTogether(this.mDrawableAnimator);
    }

    public void onCollapseAnimator(AnimatorSet animatorSet) {
        ensureDrawableAnimator();
        ensureDrawableInterpolator();
        this.mDrawableAnimator.cancel();
        this.mDrawableAnimator.setTarget(this.centerDrawableIv);
        this.mDrawableAnimator.setFloatValues(-45.0f, 0.0f);
        this.mDrawableAnimator.setPropertyName(Key.ROTATION);
        this.mDrawableAnimator.setInterpolator(this.mOvershootInterpolator);
        animatorSet.playTogether(this.mDrawableAnimator);
    }

    private void ensureDrawableAnimator() {
        if (this.mDrawableAnimator == null) {
            this.mDrawableAnimator = new ObjectAnimator();
        }
    }

    private void ensureDrawableInterpolator() {
        if (this.mOvershootInterpolator == null) {
            this.mOvershootInterpolator = new OvershootInterpolator();
        }
    }
}
