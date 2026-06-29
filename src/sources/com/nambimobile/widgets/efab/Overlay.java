package com.nambimobile.widgets.efab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Overlay.kt */
@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u001a\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0019\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b0\u00101J\u0019\u00102\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b3\u00101J\u0012\u00104\u001a\u00020\u00132\b\u00105\u001a\u0004\u0018\u000106H\u0016J8\u00107\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0002R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R6\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR$\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020!2\u0006\u0010\n\u001a\u00020!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010(\u001a\u00020'2\u0006\u0010\n\u001a\u00020'@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00068"}, d2 = {"Lcom/nambimobile/widgets/efab/Overlay;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "orientation", "Lcom/nambimobile/widgets/efab/Orientation;", "(Landroid/content/Context;Lcom/nambimobile/widgets/efab/Orientation;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "closingAnimationDurationMs", "getClosingAnimationDurationMs", "()J", "setClosingAnimationDurationMs", "(J)V", "<set-?>", "Lkotlin/Function0;", "", "defaultOnClickBehavior", "getDefaultOnClickBehavior$expandable_fab_release", "()Lkotlin/jvm/functions/Function0;", "setDefaultOnClickBehavior$expandable_fab_release", "(Lkotlin/jvm/functions/Function0;)V", "hideOnAnimationEnd", "com/nambimobile/widgets/efab/Overlay$hideOnAnimationEnd$1", "Lcom/nambimobile/widgets/efab/Overlay$hideOnAnimationEnd$1;", "openingAnimationDurationMs", "getOpeningAnimationDurationMs", "setOpeningAnimationDurationMs", "getOrientation", "()Lcom/nambimobile/widgets/efab/Orientation;", "", "overlayAlpha", "getOverlayAlpha", "()F", "setOverlayAlpha", "(F)V", "", "overlayColor", "getOverlayColor", "()I", "setOverlayColor", "(I)V", "closingAnimations", "Landroid/animation/Animator;", "globalDurationMs", "closingAnimations$expandable_fab_release", "(Ljava/lang/Long;)Landroid/animation/Animator;", "openingAnimations", "openingAnimations$expandable_fab_release", "setOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setOptionalProperties", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Overlay extends FrameLayout {
    private long closingAnimationDurationMs;
    private Function0<Unit> defaultOnClickBehavior;
    private final Overlay$hideOnAnimationEnd$1 hideOnAnimationEnd;
    private long openingAnimationDurationMs;
    private Orientation orientation;
    private float overlayAlpha;
    private int overlayColor;

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final int getOverlayColor() {
        return this.overlayColor;
    }

    public final void setOverlayColor(int i) {
        setBackgroundColor(i);
        this.overlayColor = i;
    }

    public final float getOverlayAlpha() {
        return this.overlayAlpha;
    }

    public final void setOverlayAlpha(float f) {
        setAlpha(f);
        this.overlayAlpha = f;
    }

    public final long getOpeningAnimationDurationMs() {
        return this.openingAnimationDurationMs;
    }

    public final void setOpeningAnimationDurationMs(long j) throws Resources.NotFoundException {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_overlay_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_overlay_illegal_optional_properties)");
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
            String string = getResources().getString(R.string.efab_overlay_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_overlay_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.closingAnimationDurationMs = j;
    }

    public final /* synthetic */ Function0 getDefaultOnClickBehavior$expandable_fab_release() throws Resources.NotFoundException {
        Function0<Unit> function0 = this.defaultOnClickBehavior;
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
    /* JADX WARN: Type inference failed for: r11v5, types: [com.nambimobile.widgets.efab.Overlay$hideOnAnimationEnd$1] */
    public Overlay(Context context, Orientation orientation) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.orientation = Orientation.PORTRAIT;
        this.overlayColor = ContextCompat.getColor(getContext(), android.R.color.white);
        this.overlayAlpha = 0.78431f;
        this.openingAnimationDurationMs = 300L;
        this.closingAnimationDurationMs = 300L;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.Overlay$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        setVisibility(8);
        setOptionalProperties$default(this, orientation == null ? this.orientation : orientation, 0, 0.0f, 0L, 0L, 30, null);
    }

    public /* synthetic */ Overlay(Context context, Orientation orientation, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? Orientation.PORTRAIT : orientation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.nambimobile.widgets.efab.Overlay$hideOnAnimationEnd$1] */
    public Overlay(Context context, AttributeSet attributeSet) {
        long jLongValue;
        long jLongValue2;
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.orientation = Orientation.PORTRAIT;
        this.overlayColor = ContextCompat.getColor(getContext(), android.R.color.white);
        this.overlayAlpha = 0.78431f;
        this.openingAnimationDurationMs = 300L;
        this.closingAnimationDurationMs = 300L;
        this.hideOnAnimationEnd = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.Overlay$hideOnAnimationEnd$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.setVisibility(8);
            }
        };
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        setVisibility(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Overlay, 0, 0);
        try {
            try {
                int i = typedArrayObtainStyledAttributes.getInt(R.styleable.Overlay_overlay_orientation, Orientation.PORTRAIT.ordinal());
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.Overlay_overlay_openingAnimationDurationMs);
                Long lValueOf = null;
                Long lValueOf2 = string == null ? null : Long.valueOf(Long.parseLong(string));
                if (lValueOf2 != null) {
                    jLongValue = lValueOf2.longValue();
                } else {
                    jLongValue = getOpeningAnimationDurationMs();
                }
                long j = jLongValue;
                String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.Overlay_overlay_closingAnimationDurationMs);
                if (string2 != null) {
                    lValueOf = Long.valueOf(Long.parseLong(string2));
                }
                if (lValueOf != null) {
                    jLongValue2 = lValueOf.longValue();
                } else {
                    jLongValue2 = getClosingAnimationDurationMs();
                }
                setOptionalProperties(Orientation.values()[i], typedArrayObtainStyledAttributes.getColor(R.styleable.Overlay_overlay_color, getOverlayColor()), typedArrayObtainStyledAttributes.getFloat(R.styleable.Overlay_overlay_alpha, getOverlayAlpha()), j, jLongValue2);
            } catch (Exception e) {
                String string3 = typedArrayObtainStyledAttributes.getResources().getString(R.string.efab_overlay_illegal_optional_properties);
                Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.efab_overlay_illegal_optional_properties)");
                ErrorsKt.illegalArg(string3, e);
                throw new KotlinNothingValueException();
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() { // from class: com.nambimobile.widgets.efab.Overlay$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                Overlay.m6569setOnClickListener$lambda1(this.f$0, onClickListener, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnClickListener$lambda-1, reason: not valid java name */
    public static final void m6569setOnClickListener$lambda1(Overlay this$0, View.OnClickListener onClickListener, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 defaultOnClickBehavior$expandable_fab_release = this$0.getDefaultOnClickBehavior$expandable_fab_release();
        if (defaultOnClickBehavior$expandable_fab_release != null) {
            defaultOnClickBehavior$expandable_fab_release.invoke();
        }
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    static /* synthetic */ void setOptionalProperties$default(Overlay overlay, Orientation orientation, int i, float f, long j, long j2, int i2, Object obj) throws Resources.NotFoundException {
        overlay.setOptionalProperties(orientation, (i2 & 2) != 0 ? overlay.overlayColor : i, (i2 & 4) != 0 ? overlay.overlayAlpha : f, (i2 & 8) != 0 ? overlay.openingAnimationDurationMs : j, (i2 & 16) != 0 ? overlay.closingAnimationDurationMs : j2);
    }

    private final void setOptionalProperties(Orientation orientation, int overlayColor, float overlayAlpha, long openingAnimationDurationMs, long closingAnimationDurationMs) throws Resources.NotFoundException {
        this.orientation = orientation;
        setOverlayAlpha(overlayAlpha);
        setOverlayColor(overlayColor);
        setOpeningAnimationDurationMs(openingAnimationDurationMs);
        setClosingAnimationDurationMs(closingAnimationDurationMs);
        if (hasOnClickListeners()) {
            return;
        }
        setOnClickListener(null);
    }

    public final /* synthetic */ Animator openingAnimations$expandable_fab_release(Long globalDurationMs) {
        setAlpha(0.0f);
        setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "alpha", 0.0f, this.overlayAlpha);
        objectAnimatorOfFloat.setDuration(globalDurationMs == null ? getOpeningAnimationDurationMs() : globalDurationMs.longValue());
        Intrinsics.checkNotNullExpressionValue(objectAnimatorOfFloat, "ofFloat(this, \"alpha\", 0f,  overlayAlpha).apply {\n            this.duration = globalDurationMs ?: openingAnimationDurationMs\n        }");
        return objectAnimatorOfFloat;
    }

    public final /* synthetic */ Animator closingAnimations$expandable_fab_release(Long globalDurationMs) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        objectAnimatorOfFloat.setDuration(globalDurationMs == null ? getClosingAnimationDurationMs() : globalDurationMs.longValue());
        objectAnimatorOfFloat.addListener(this.hideOnAnimationEnd);
        Intrinsics.checkNotNullExpressionValue(objectAnimatorOfFloat, "ofFloat(this, \"alpha\", 0f).apply {\n            this.duration = globalDurationMs ?: closingAnimationDurationMs\n            addListener(hideOnAnimationEnd)\n        }");
        return objectAnimatorOfFloat;
    }
}
