package com.nambimobile.widgets.efab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FabOption.kt */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001+\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ#\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u000b2\b\u0010?\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b@\u0010AJK\u0010B\u001a\u00020=2\u0006\u0010C\u001a\u00020\u00192\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u0002042\b\u0010>\u001a\u0004\u0018\u00010\u000b2\b\u0010?\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\bJ\u0010KJ\b\u0010L\u001a\u00020MH\u0002J\u0012\u0010N\u001a\u00020M2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016JP\u0010Q\u001a\u00020M2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00192\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010\u001f\u001a\u00020\u00132\b\b\u0002\u00101\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u000204H\u0002J\u0010\u0010R\u001a\u00020M2\u0006\u0010D\u001a\u00020\u0019H\u0016R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R6\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\n\u001a\u0004\u0018\u00010$@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,R\u0011\u0010-\u001a\u00020.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R$\u00101\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u0010\u0010R$\u00105\u001a\u0002042\u0006\u0010\n\u001a\u000204@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;¨\u0006S"}, d2 = {"Lcom/nambimobile/widgets/efab/FabOption;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "context", "Landroid/content/Context;", "orientation", "Lcom/nambimobile/widgets/efab/Orientation;", "(Landroid/content/Context;Lcom/nambimobile/widgets/efab/Orientation;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "closingAnimationDurationMs", "getClosingAnimationDurationMs", "()J", "setClosingAnimationDurationMs", "(J)V", "<set-?>", "Lkotlin/Function0;", "", "defaultOnClickBehavior", "getDefaultOnClickBehavior$expandable_fab_release", "()Lkotlin/jvm/functions/Function0;", "setDefaultOnClickBehavior$expandable_fab_release", "(Lkotlin/jvm/functions/Function0;)V", "", "fabOptionColor", "getFabOptionColor", "()I", "setFabOptionColor", "(I)V", "fabOptionEnabled", "getFabOptionEnabled", "()Z", "setFabOptionEnabled", "(Z)V", "Landroid/graphics/drawable/Drawable;", "fabOptionIcon", "getFabOptionIcon", "()Landroid/graphics/drawable/Drawable;", "setFabOptionIcon", "(Landroid/graphics/drawable/Drawable;)V", "hideOnAnimationEnd", "com/nambimobile/widgets/efab/FabOption$hideOnAnimationEnd$1", "Lcom/nambimobile/widgets/efab/FabOption$hideOnAnimationEnd$1;", Constants.ScionAnalytics.PARAM_LABEL, "Lcom/nambimobile/widgets/efab/Label;", "getLabel", "()Lcom/nambimobile/widgets/efab/Label;", "openingAnimationDurationMs", "getOpeningAnimationDurationMs", "setOpeningAnimationDurationMs", "", "openingOvershootTension", "getOpeningOvershootTension", "()F", "setOpeningOvershootTension", "(F)V", "getOrientation", "()Lcom/nambimobile/widgets/efab/Orientation;", "closingAnimations", "Landroid/animation/Animator;", "globalDurationMs", "globalLabelDurationMs", "closingAnimations$expandable_fab_release", "(Ljava/lang/Long;Ljava/lang/Long;)Landroid/animation/Animator;", "openingAnimations", FirebaseAnalytics.Param.INDEX, "size", "Lcom/nambimobile/widgets/efab/FabSize;", "position", "Lcom/nambimobile/widgets/efab/FabOptionPosition;", "firstFabOptionMarginPx", "successiveFabOptionMarginPx", "openingAnimations$expandable_fab_release", "(ILcom/nambimobile/widgets/efab/FabSize;Lcom/nambimobile/widgets/efab/FabOptionPosition;FFLjava/lang/Long;Ljava/lang/Long;)Landroid/animation/Animator;", "setLabelOnClickListener", "", "setOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setOptionalProperties", "setSize", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FabOption extends FloatingActionButton {
    private long closingAnimationDurationMs;
    private Function0<Boolean> defaultOnClickBehavior;
    private int fabOptionColor;
    private boolean fabOptionEnabled;
    private Drawable fabOptionIcon;
    private final FabOption$hideOnAnimationEnd$1 hideOnAnimationEnd;
    private final Label label;
    private long openingAnimationDurationMs;
    private float openingOvershootTension;
    private Orientation orientation;

    /* compiled from: FabOption.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FabOptionPosition.values().length];
            iArr[FabOptionPosition.ABOVE.ordinal()] = 1;
            iArr[FabOptionPosition.BELOW.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final int getFabOptionColor() {
        return this.fabOptionColor;
    }

    public final void setFabOptionColor(int i) {
        setBackgroundTintList(ColorStateList.valueOf(i));
        this.fabOptionColor = i;
    }

    public final Drawable getFabOptionIcon() {
        return this.fabOptionIcon;
    }

    public final void setFabOptionIcon(Drawable drawable) {
        setImageDrawable(drawable);
        this.fabOptionIcon = drawable;
    }

    public final boolean getFabOptionEnabled() {
        return this.fabOptionEnabled;
    }

    public final void setFabOptionEnabled(boolean z) {
        if (z) {
            setFabOptionColor(this.fabOptionColor);
        } else {
            setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.efab_disabled)));
        }
        setEnabled(z);
        this.label.setLabelEnabled$expandable_fab_release(z);
        this.fabOptionEnabled = z;
    }

    public final long getOpeningAnimationDurationMs() {
        return this.openingAnimationDurationMs;
    }

    public final void setOpeningAnimationDurationMs(long j) throws Resources.NotFoundException {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_faboption_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_faboption_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.openingAnimationDurationMs = j;
    }

    public final long getClosingAnimationDurationMs() {
        return this.closingAnimationDurationMs;
    }

    public final void setClosingAnimationDurationMs(long j) throws Resources.NotFoundException {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_faboption_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_faboption_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.closingAnimationDurationMs = j;
    }

    public final float getOpeningOvershootTension() {
        return this.openingOvershootTension;
    }

    public final void setOpeningOvershootTension(float f) throws Resources.NotFoundException {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_faboption_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_faboption_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.openingOvershootTension = f;
    }

    public final Label getLabel() {
        return this.label;
    }

    public final /* synthetic */ Function0 getDefaultOnClickBehavior$expandable_fab_release() throws Resources.NotFoundException {
        Function0<Boolean> function0 = this.defaultOnClickBehavior;
        if (function0 != null) {
            return function0;
        }
        String string = getResources().getString(R.string.efab_layout_must_be_child_of_expandablefab_layout);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_layout_must_be_child_of_expandablefab_layout)");
        ErrorsKt.illegalState$default(string, null, 2, null);
        throw new KotlinNothingValueException();
    }

    public final /* synthetic */ void setDefaultOnClickBehavior$expandable_fab_release(Function0 function0) {
        this.defaultOnClickBehavior = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r13v7, types: [com.nambimobile.widgets.efab.FabOption$hideOnAnimationEnd$1] */
    public FabOption(Context context, Orientation orientation) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.orientation = Orientation.PORTRAIT;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        this.fabOptionColor = ResourcesKt.getThemeColorAccent(context2);
        this.fabOptionEnabled = true;
        this.openingAnimationDurationMs = 125L;
        this.closingAnimationDurationMs = 75L;
        this.openingOvershootTension = 3.5f;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "context");
        Label label = new Label(context3);
        label.setLabelText(null);
        label.setLabelTextColor(ContextCompat.getColor(label.getContext(), android.R.color.white));
        label.setLabelTextSize(label.getResources().getDimension(R.dimen.efab_label_text_size));
        label.setLabelFont(Typeface.DEFAULT);
        label.setLabelBackgroundColor(ContextCompat.getColor(label.getContext(), R.color.efab_label_background));
        label.setLabelElevation(label.getResources().getDimensionPixelSize(R.dimen.efab_label_elevation));
        label.setPosition(LabelPosition.LEFT);
        label.setMarginPx(50.0f);
        label.setTranslationXPx(100.0f);
        label.setVisibleToHiddenAnimationDurationMs(75L);
        label.setHiddenToVisibleAnimationDurationMs(250L);
        label.setOvershootTension(3.5f);
        Unit unit = Unit.INSTANCE;
        this.label = label;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.FabOption$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        ImageViewCompat.setImageTintList(this, null);
        setVisibility(8);
        setOptionalProperties$default(this, orientation == null ? this.orientation : orientation, 0, null, false, 0L, 0L, 0.0f, 126, null);
    }

    public /* synthetic */ FabOption(Context context, Orientation orientation, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? Orientation.PORTRAIT : orientation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.nambimobile.widgets.efab.FabOption$hideOnAnimationEnd$1] */
    public FabOption(Context context, AttributeSet attributeSet) {
        long jLongValue;
        long jLongValue2;
        Typeface font;
        long jLongValue3;
        long jLongValue4;
        Drawable drawable;
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.orientation = Orientation.PORTRAIT;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        this.fabOptionColor = ResourcesKt.getThemeColorAccent(context2);
        this.fabOptionEnabled = true;
        this.openingAnimationDurationMs = 125L;
        this.closingAnimationDurationMs = 75L;
        this.openingOvershootTension = 3.5f;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "context");
        Label label = new Label(context3);
        label.setLabelText(null);
        label.setLabelTextColor(ContextCompat.getColor(label.getContext(), android.R.color.white));
        label.setLabelTextSize(label.getResources().getDimension(R.dimen.efab_label_text_size));
        label.setLabelFont(Typeface.DEFAULT);
        label.setLabelBackgroundColor(ContextCompat.getColor(label.getContext(), R.color.efab_label_background));
        label.setLabelElevation(label.getResources().getDimensionPixelSize(R.dimen.efab_label_elevation));
        label.setPosition(LabelPosition.LEFT);
        label.setMarginPx(50.0f);
        label.setTranslationXPx(100.0f);
        label.setVisibleToHiddenAnimationDurationMs(75L);
        label.setHiddenToVisibleAnimationDurationMs(250L);
        label.setOvershootTension(3.5f);
        Unit unit = Unit.INSTANCE;
        this.label = label;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.FabOption$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        ImageViewCompat.setImageTintList(this, null);
        setVisibility(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FabOption, 0, 0);
        try {
            try {
                int i = typedArrayObtainStyledAttributes.getInt(R.styleable.FabOption_label_position, LabelPosition.LEFT.ordinal());
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.FabOption_label_visibleToHiddenAnimationDurationMs);
                Long lValueOf = string == null ? null : Long.valueOf(Long.parseLong(string));
                if (lValueOf != null) {
                    jLongValue = lValueOf.longValue();
                } else {
                    jLongValue = getLabel().getVisibleToHiddenAnimationDurationMs();
                }
                String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.FabOption_label_hiddenToVisibleAnimationDurationMs);
                Long lValueOf2 = string2 == null ? null : Long.valueOf(Long.parseLong(string2));
                if (lValueOf2 != null) {
                    jLongValue2 = lValueOf2.longValue();
                } else {
                    jLongValue2 = getLabel().getHiddenToVisibleAnimationDurationMs();
                }
                Label label2 = getLabel();
                label2.setLabelText(typedArrayObtainStyledAttributes.getString(R.styleable.FabOption_label_text));
                label2.setLabelTextColor(typedArrayObtainStyledAttributes.getColor(R.styleable.FabOption_label_textColor, getLabel().getLabelTextColor()));
                label2.setLabelTextSize(typedArrayObtainStyledAttributes.getDimension(R.styleable.FabOption_label_textSize, getLabel().getLabelTextSize()));
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FabOption_label_font, 0);
                if (resourceId == 0) {
                    font = getLabel().getLabelFont();
                } else {
                    font = ResourcesCompat.getFont(context, resourceId);
                }
                label2.setLabelFont(font);
                label2.setLabelBackgroundColor(typedArrayObtainStyledAttributes.getColor(R.styleable.FabOption_label_backgroundColor, getLabel().getLabelBackgroundColor()));
                label2.setLabelElevation(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FabOption_label_elevation, getLabel().getLabelElevation()));
                label2.setPosition(LabelPosition.values()[i]);
                label2.setMarginPx(typedArrayObtainStyledAttributes.getFloat(R.styleable.FabOption_label_marginPx, getLabel().getMarginPx()));
                label2.setVisibleToHiddenAnimationDurationMs(jLongValue);
                label2.setHiddenToVisibleAnimationDurationMs(jLongValue2);
                label2.setOvershootTension(typedArrayObtainStyledAttributes.getFloat(R.styleable.FabOption_label_overshootTension, getLabel().getOvershootTension()));
                label2.setTranslationXPx(typedArrayObtainStyledAttributes.getFloat(R.styleable.FabOption_label_translationXPx, getLabel().getTranslationXPx()));
                typedArrayObtainStyledAttributes.recycle();
                typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FabOption, 0, 0);
                try {
                    try {
                        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.FabOption_fab_orientation, Orientation.PORTRAIT.ordinal());
                        String string3 = typedArrayObtainStyledAttributes.getString(R.styleable.FabOption_fab_openingAnimationDurationMs);
                        Long lValueOf3 = string3 == null ? null : Long.valueOf(Long.parseLong(string3));
                        if (lValueOf3 != null) {
                            jLongValue3 = lValueOf3.longValue();
                        } else {
                            jLongValue3 = getOpeningAnimationDurationMs();
                        }
                        long j = jLongValue3;
                        String string4 = typedArrayObtainStyledAttributes.getString(R.styleable.FabOption_fab_closingAnimationDurationMs);
                        Long lValueOf4 = string4 == null ? null : Long.valueOf(Long.parseLong(string4));
                        if (lValueOf4 != null) {
                            jLongValue4 = lValueOf4.longValue();
                        } else {
                            jLongValue4 = getClosingAnimationDurationMs();
                        }
                        long j2 = jLongValue4;
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.FabOption_fab_icon, 0);
                        if (resourceId2 == 0) {
                            drawable = null;
                        } else {
                            drawable = AppCompatResources.getDrawable(context, resourceId2);
                        }
                        setOptionalProperties(Orientation.values()[i2], typedArrayObtainStyledAttributes.getColor(R.styleable.FabOption_fab_color, getFabOptionColor()), drawable, typedArrayObtainStyledAttributes.getBoolean(R.styleable.FabOption_fab_enabled, true), j, j2, typedArrayObtainStyledAttributes.getFloat(R.styleable.FabOption_fab_openingOvershootTension, getOpeningOvershootTension()));
                    } finally {
                    }
                } catch (Exception e) {
                    String string5 = typedArrayObtainStyledAttributes.getResources().getString(R.string.efab_faboption_illegal_optional_properties);
                    Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.efab_faboption_illegal_optional_properties)");
                    ErrorsKt.illegalArg(string5, e);
                    throw new KotlinNothingValueException();
                }
            } catch (Exception e2) {
                String string6 = typedArrayObtainStyledAttributes.getResources().getString(R.string.efab_label_illegal_optional_properties);
                Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.efab_label_illegal_optional_properties)");
                ErrorsKt.illegalArg(string6, e2);
                throw new KotlinNothingValueException();
            }
        } finally {
        }
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() { // from class: com.nambimobile.widgets.efab.FabOption$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                FabOption.m6567setOnClickListener$lambda4(this.f$0, onClickListener, view);
            }
        });
        setLabelOnClickListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnClickListener$lambda-4, reason: not valid java name */
    public static final void m6567setOnClickListener$lambda4(FabOption this$0, View.OnClickListener onClickListener, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 defaultOnClickBehavior$expandable_fab_release = this$0.getDefaultOnClickBehavior$expandable_fab_release();
        Boolean bool = defaultOnClickBehavior$expandable_fab_release == null ? null : (Boolean) defaultOnClickBehavior$expandable_fab_release.invoke();
        if (!(bool == null ? false : bool.booleanValue()) || onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton
    public void setSize(int size) {
        if (size != FabSize.CUSTOM.getValue()) {
            super.setSize(size);
        }
    }

    static /* synthetic */ void setOptionalProperties$default(FabOption fabOption, Orientation orientation, int i, Drawable drawable, boolean z, long j, long j2, float f, int i2, Object obj) throws Resources.NotFoundException {
        fabOption.setOptionalProperties((i2 & 1) != 0 ? fabOption.orientation : orientation, (i2 & 2) != 0 ? fabOption.fabOptionColor : i, (i2 & 4) != 0 ? null : drawable, (i2 & 8) != 0 ? fabOption.fabOptionEnabled : z, (i2 & 16) != 0 ? fabOption.openingAnimationDurationMs : j, (i2 & 32) != 0 ? fabOption.closingAnimationDurationMs : j2, (i2 & 64) != 0 ? fabOption.openingOvershootTension : f);
    }

    private final void setOptionalProperties(Orientation orientation, int fabOptionColor, Drawable fabOptionIcon, boolean fabOptionEnabled, long openingAnimationDurationMs, long closingAnimationDurationMs, float openingOvershootTension) throws Resources.NotFoundException {
        this.orientation = orientation;
        setFabOptionColor(fabOptionColor);
        if (fabOptionIcon != null) {
            setFabOptionIcon(fabOptionIcon);
        }
        setFabOptionEnabled(fabOptionEnabled);
        setOpeningAnimationDurationMs(openingAnimationDurationMs);
        setClosingAnimationDurationMs(closingAnimationDurationMs);
        setOpeningOvershootTension(openingOvershootTension);
        if (hasOnClickListeners()) {
            setLabelOnClickListener();
        } else {
            setOnClickListener(null);
        }
    }

    private final void setLabelOnClickListener() {
        Label label = this.label;
        if (label == null) {
            return;
        }
        label.setOnClickListener(new View.OnClickListener() { // from class: com.nambimobile.widgets.efab.FabOption$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FabOption.m6566setLabelOnClickListener$lambda6(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setLabelOnClickListener$lambda-6, reason: not valid java name */
    public static final void m6566setLabelOnClickListener$lambda6(FabOption this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.callOnClick();
    }

    public final /* synthetic */ Animator openingAnimations$expandable_fab_release(int index, FabSize size, FabOptionPosition position, float firstFabOptionMarginPx, float successiveFabOptionMarginPx, Long globalDurationMs, Long globalLabelDurationMs) {
        Pair pair;
        ObjectAnimator objectAnimatorOfFloat;
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(position, "position");
        setAlpha(0.0f);
        setVisibility(0);
        setSize(size.getValue());
        int i = WhenMappings.$EnumSwitchMapping$0[position.ordinal()];
        if (i == 1) {
            pair = new Pair(Float.valueOf(-firstFabOptionMarginPx), Float.valueOf(-successiveFabOptionMarginPx));
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            pair = new Pair(Float.valueOf(firstFabOptionMarginPx), Float.valueOf(successiveFabOptionMarginPx));
        }
        float fFloatValue = ((Number) pair.component1()).floatValue();
        float fFloatValue2 = ((Number) pair.component2()).floatValue();
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[4];
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1.0f);
        objectAnimatorOfFloat2.setDuration(globalDurationMs == null ? getOpeningAnimationDurationMs() : globalDurationMs.longValue());
        objectAnimatorOfFloat2.setInterpolator(new OvershootInterpolator(getOpeningOvershootTension()));
        Unit unit = Unit.INSTANCE;
        animatorArr[0] = objectAnimatorOfFloat2;
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1.0f);
        objectAnimatorOfFloat3.setDuration(globalDurationMs == null ? getOpeningAnimationDurationMs() : globalDurationMs.longValue());
        objectAnimatorOfFloat3.setInterpolator(new OvershootInterpolator(getOpeningOvershootTension()));
        Unit unit2 = Unit.INSTANCE;
        animatorArr[1] = objectAnimatorOfFloat3;
        ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat4.setDuration(globalDurationMs == null ? getOpeningAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit3 = Unit.INSTANCE;
        animatorArr[2] = objectAnimatorOfFloat4;
        if (index == 0) {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationY", fFloatValue);
            objectAnimatorOfFloat.setDuration(1L);
            Unit unit4 = Unit.INSTANCE;
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationY", fFloatValue2);
            objectAnimatorOfFloat.setDuration(1L);
            Unit unit5 = Unit.INSTANCE;
        }
        animatorArr[3] = objectAnimatorOfFloat;
        animatorSet.playTogether(animatorArr);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(animatorSet, getLabel().hiddenToVisibleAnimations$expandable_fab_release(globalLabelDurationMs));
        return animatorSet2;
    }

    public final /* synthetic */ Animator closingAnimations$expandable_fab_release(Long globalDurationMs, Long globalLabelDurationMs) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[3];
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "scaleX", 0.0f);
        objectAnimatorOfFloat.setDuration(globalDurationMs == null ? getClosingAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit = Unit.INSTANCE;
        animatorArr[0] = objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "scaleY", 0.0f);
        objectAnimatorOfFloat2.setDuration(globalDurationMs == null ? getClosingAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit2 = Unit.INSTANCE;
        animatorArr[1] = objectAnimatorOfFloat2;
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        objectAnimatorOfFloat3.setDuration(globalDurationMs == null ? getClosingAnimationDurationMs() : globalDurationMs.longValue());
        Unit unit3 = Unit.INSTANCE;
        animatorArr[2] = objectAnimatorOfFloat3;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(this.hideOnAnimationEnd);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(animatorSet, getLabel().visibleToHiddenAnimations$expandable_fab_release(globalLabelDurationMs));
        return animatorSet2;
    }
}
