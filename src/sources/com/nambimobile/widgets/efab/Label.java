package com.nambimobile.widgets.efab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Label.kt */
@Metadata(d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0010\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bL\u0010MJ\r\u0010N\u001a\u00020OH\u0000¢\u0006\u0002\bPJ\u0012\u0010Q\u001a\u00020\u001b2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010T\u001a\u00020OH\u0002J\r\u0010U\u001a\u00020OH\u0000¢\u0006\u0002\bVJ\u0019\u0010W\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bX\u0010MR$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R&\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u001b8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R4\u0010#\u001a\n \"*\u0004\u0018\u00010!0!2\u000e\u0010\b\u001a\n \"*\u0004\u0018\u00010!0!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R(\u0010)\u001a\u0004\u0018\u00010(2\b\u0010\b\u001a\u0004\u0018\u00010(@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0015\"\u0004\b0\u0010\u0017R$\u00102\u001a\u0002012\u0006\u0010\b\u001a\u000201@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u0002012\u0006\u0010\b\u001a\u000201@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00104\"\u0004\b9\u00106R$\u0010:\u001a\u0002012\u0006\u0010\b\u001a\u000201@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00104\"\u0004\b<\u00106R\u001a\u0010=\u001a\u00020>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u00104\"\u0004\bE\u00106R$\u0010F\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\f\"\u0004\bH\u0010\u000e¨\u0006Y"}, d2 = {"Lcom/nambimobile/widgets/efab/Label;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "hiddenToVisibleAnimationDurationMs", "getHiddenToVisibleAnimationDurationMs", "()J", "setHiddenToVisibleAnimationDurationMs", "(J)V", "hideOnAnimationEnd", "com/nambimobile/widgets/efab/Label$hideOnAnimationEnd$1", "Lcom/nambimobile/widgets/efab/Label$hideOnAnimationEnd$1;", "", "labelBackgroundColor", "getLabelBackgroundColor", "()I", "setLabelBackgroundColor", "(I)V", "labelElevation", "getLabelElevation", "setLabelElevation", "", "labelEnabled", "getLabelEnabled$expandable_fab_release", "()Z", "setLabelEnabled$expandable_fab_release", "(Z)V", "Landroid/graphics/Typeface;", "kotlin.jvm.PlatformType", "labelFont", "getLabelFont", "()Landroid/graphics/Typeface;", "setLabelFont", "(Landroid/graphics/Typeface;)V", "", "labelText", "getLabelText", "()Ljava/lang/CharSequence;", "setLabelText", "(Ljava/lang/CharSequence;)V", "labelTextColor", "getLabelTextColor", "setLabelTextColor", "", "labelTextSize", "getLabelTextSize", "()F", "setLabelTextSize", "(F)V", "marginPx", "getMarginPx", "setMarginPx", "overshootTension", "getOvershootTension", "setOvershootTension", "position", "Lcom/nambimobile/widgets/efab/LabelPosition;", "getPosition", "()Lcom/nambimobile/widgets/efab/LabelPosition;", "setPosition", "(Lcom/nambimobile/widgets/efab/LabelPosition;)V", "translationXPx", "getTranslationXPx", "setTranslationXPx", "visibleToHiddenAnimationDurationMs", "getVisibleToHiddenAnimationDurationMs", "setVisibleToHiddenAnimationDurationMs", "hiddenToVisibleAnimations", "Landroid/animation/Animator;", "globalDurationMs", "hiddenToVisibleAnimations$expandable_fab_release", "(Ljava/lang/Long;)Landroid/animation/Animator;", "hideLabel", "", "hideLabel$expandable_fab_release", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "positionSelf", "showLabel", "showLabel$expandable_fab_release", "visibleToHiddenAnimations", "visibleToHiddenAnimations$expandable_fab_release", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Label extends AppCompatTextView {
    private long hiddenToVisibleAnimationDurationMs;
    private final Label$hideOnAnimationEnd$1 hideOnAnimationEnd;
    private int labelBackgroundColor;
    private int labelElevation;
    private boolean labelEnabled;
    private Typeface labelFont;
    private CharSequence labelText;
    private int labelTextColor;
    private float labelTextSize;
    private float marginPx;
    private float overshootTension;
    private LabelPosition position;
    private float translationXPx;
    private long visibleToHiddenAnimationDurationMs;

    /* compiled from: Label.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LabelPosition.values().length];
            iArr[LabelPosition.LEFT.ordinal()] = 1;
            iArr[LabelPosition.RIGHT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final CharSequence getLabelText() {
        return this.labelText;
    }

    public final void setLabelText(CharSequence charSequence) {
        if (charSequence == null) {
            setVisibility(8);
        }
        if (charSequence != null) {
            setText(charSequence);
        }
        this.labelText = charSequence;
    }

    public final int getLabelTextColor() {
        return this.labelTextColor;
    }

    public final void setLabelTextColor(int i) {
        setTextColor(i);
        this.labelTextColor = i;
    }

    public final float getLabelTextSize() {
        return this.labelTextSize;
    }

    public final void setLabelTextSize(float f) {
        setTextSize(0, f);
        this.labelTextSize = f;
    }

    public final Typeface getLabelFont() {
        return this.labelFont;
    }

    public final void setLabelFont(Typeface typeface) {
        setTypeface(typeface);
        this.labelFont = typeface;
    }

    public final int getLabelBackgroundColor() {
        return this.labelBackgroundColor;
    }

    public final void setLabelBackgroundColor(int i) {
        Drawable background = getBackground();
        if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(i);
        } else {
            background.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
        this.labelBackgroundColor = i;
    }

    public final int getLabelElevation() {
        return this.labelElevation;
    }

    public final void setLabelElevation(int i) {
        if (i < 0) {
            String string = getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        ViewCompat.setElevation(this, i);
        this.labelElevation = i;
    }

    /* renamed from: getLabelEnabled$expandable_fab_release, reason: from getter */
    public final /* synthetic */ boolean getLabelEnabled() {
        return this.labelEnabled;
    }

    public final /* synthetic */ void setLabelEnabled$expandable_fab_release(boolean z) {
        if (z) {
            setLabelBackgroundColor(this.labelBackgroundColor);
            setLabelTextColor(this.labelTextColor);
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.efab_disabled);
            int color2 = ContextCompat.getColor(getContext(), R.color.efab_disabled_text);
            getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            setTextColor(color2);
        }
        setEnabled(z);
        this.labelEnabled = z;
    }

    public final LabelPosition getPosition() {
        return this.position;
    }

    public final void setPosition(LabelPosition labelPosition) {
        Intrinsics.checkNotNullParameter(labelPosition, "<set-?>");
        this.position = labelPosition;
    }

    public final float getMarginPx() {
        return this.marginPx;
    }

    public final void setMarginPx(float f) {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.marginPx = f;
    }

    public final float getTranslationXPx() {
        return this.translationXPx;
    }

    public final void setTranslationXPx(float f) {
        this.translationXPx = f;
    }

    public final long getVisibleToHiddenAnimationDurationMs() {
        return this.visibleToHiddenAnimationDurationMs;
    }

    public final void setVisibleToHiddenAnimationDurationMs(long j) {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.visibleToHiddenAnimationDurationMs = j;
    }

    public final long getHiddenToVisibleAnimationDurationMs() {
        return this.hiddenToVisibleAnimationDurationMs;
    }

    public final void setHiddenToVisibleAnimationDurationMs(long j) {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.hiddenToVisibleAnimationDurationMs = j;
    }

    public final float getOvershootTension() {
        return this.overshootTension;
    }

    public final void setOvershootTension(float f) {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.overshootTension = f;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v15, types: [com.nambimobile.widgets.efab.Label$hideOnAnimationEnd$1] */
    public Label(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.labelTextColor = ContextCompat.getColor(getContext(), android.R.color.white);
        this.labelTextSize = getResources().getDimension(R.dimen.efab_label_text_size);
        this.labelFont = Typeface.DEFAULT;
        this.labelBackgroundColor = ContextCompat.getColor(getContext(), R.color.efab_label_background);
        this.labelElevation = getResources().getDimensionPixelSize(R.dimen.efab_label_elevation);
        this.labelEnabled = true;
        this.position = LabelPosition.LEFT;
        this.marginPx = 50.0f;
        this.translationXPx = 100.0f;
        this.visibleToHiddenAnimationDurationMs = 250L;
        this.hiddenToVisibleAnimationDurationMs = 75L;
        this.overshootTension = 3.5f;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.Label$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        setId(ViewCompat.generateViewId());
        setVisibility(8);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(ContextCompat.getColor(getContext(), R.color.efab_label_background));
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.efab_ui_margin_xxs));
        setPadding((int) getResources().getDimension(R.dimen.efab_ui_margin_xs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xxs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xxs));
        ViewCompat.setBackground(this, gradientDrawable);
        setLabelText(this.labelText);
        setLabelTextColor(this.labelTextColor);
        setLabelTextSize(this.labelTextSize);
        setLabelFont(this.labelFont);
        setLabelBackgroundColor(this.labelBackgroundColor);
        setLabelElevation(this.labelElevation);
        this.position = this.position;
        setMarginPx(this.marginPx);
        this.translationXPx = this.translationXPx;
        setVisibleToHiddenAnimationDurationMs(this.visibleToHiddenAnimationDurationMs);
        setHiddenToVisibleAnimationDurationMs(this.hiddenToVisibleAnimationDurationMs);
        setOvershootTension(this.overshootTension);
    }

    /* JADX WARN: Type inference failed for: r5v17, types: [com.nambimobile.widgets.efab.Label$hideOnAnimationEnd$1] */
    private Label(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.labelTextColor = ContextCompat.getColor(getContext(), android.R.color.white);
        this.labelTextSize = getResources().getDimension(R.dimen.efab_label_text_size);
        this.labelFont = Typeface.DEFAULT;
        this.labelBackgroundColor = ContextCompat.getColor(getContext(), R.color.efab_label_background);
        this.labelElevation = getResources().getDimensionPixelSize(R.dimen.efab_label_elevation);
        this.labelEnabled = true;
        this.position = LabelPosition.LEFT;
        this.marginPx = 50.0f;
        this.translationXPx = 100.0f;
        this.visibleToHiddenAnimationDurationMs = 250L;
        this.hiddenToVisibleAnimationDurationMs = 75L;
        this.overshootTension = 3.5f;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.Label$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        setId(ViewCompat.generateViewId());
        setVisibility(8);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(ContextCompat.getColor(getContext(), R.color.efab_label_background));
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.efab_ui_margin_xxs));
        setPadding((int) getResources().getDimension(R.dimen.efab_ui_margin_xs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xxs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xs), (int) getResources().getDimension(R.dimen.efab_ui_margin_xxs));
        ViewCompat.setBackground(this, gradientDrawable);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Integer numValueOf = event == null ? null : Integer.valueOf(event.getAction());
        if (numValueOf != null && numValueOf.intValue() == 0) {
            setScaleX(0.925f);
            setScaleY(0.925f);
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            setScaleX(1.0f);
            setScaleY(1.0f);
        }
        return super.onTouchEvent(event);
    }

    public final /* synthetic */ Animator hiddenToVisibleAnimations$expandable_fab_release(Long globalDurationMs) {
        float f;
        float f2;
        float f3;
        if (this.labelText == null) {
            return new AnimatorSet();
        }
        positionSelf();
        setAlpha(0.0f);
        setVisibility(0);
        int i = WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()];
        if (i == 1) {
            f = -this.marginPx;
            f2 = this.translationXPx;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            f = this.marginPx;
            f2 = this.translationXPx;
        }
        float f4 = f + f2;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()];
        if (i2 == 1) {
            f3 = -this.marginPx;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            f3 = this.marginPx;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[2];
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationX", f4, f3);
        objectAnimatorOfFloat.setDuration(globalDurationMs == null ? getHiddenToVisibleAnimationDurationMs() : globalDurationMs.longValue());
        objectAnimatorOfFloat.setInterpolator(new OvershootInterpolator(getOvershootTension()));
        Unit unit = Unit.INSTANCE;
        animatorArr[0] = objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat2.setDuration(globalDurationMs == null ? getHiddenToVisibleAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit2 = Unit.INSTANCE;
        animatorArr[1] = objectAnimatorOfFloat2;
        animatorSet.playTogether(animatorArr);
        return animatorSet;
    }

    public final /* synthetic */ Animator visibleToHiddenAnimations$expandable_fab_release(Long globalDurationMs) {
        if (this.labelText == null) {
            return new AnimatorSet();
        }
        float translationX = getTranslationX() + this.translationXPx;
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[2];
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationX", getTranslationX(), translationX);
        objectAnimatorOfFloat.setDuration(globalDurationMs == null ? getVisibleToHiddenAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit = Unit.INSTANCE;
        animatorArr[0] = objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        objectAnimatorOfFloat2.setDuration(globalDurationMs == null ? getVisibleToHiddenAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit2 = Unit.INSTANCE;
        animatorArr[1] = objectAnimatorOfFloat2;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(this.hideOnAnimationEnd);
        return animatorSet;
    }

    private final void positionSelf() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        }
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
        if (layoutParams2.getAnchorId() != -1) {
            layoutParams2.anchorGravity = getPosition().getValue();
            layoutParams2.gravity = getPosition().getValue();
            setLayoutParams(layoutParams2);
        }
    }

    public final /* synthetic */ void showLabel$expandable_fab_release() {
        if (this.labelText != null) {
            positionSelf();
            setVisibility(0);
            int i = WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()];
            if (i == 1) {
                setTranslationX(-this.marginPx);
            } else {
                if (i != 2) {
                    return;
                }
                setTranslationX(this.marginPx);
            }
        }
    }

    public final /* synthetic */ void hideLabel$expandable_fab_release() {
        setVisibility(8);
    }
}
