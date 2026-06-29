package com.nambimobile.widgets.efab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.Constants;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.TimersKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: ExpandableFab.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ1\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010\r2\b\u0010Z\u001a\u0004\u0018\u00010\r2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0000¢\u0006\u0004\b\\\u0010]J\b\u0010^\u001a\u00020\u001bH\u0016J.\u0010_\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020\r2\u0006\u0010a\u001a\u00020\u00132\u0006\u0010b\u001a\u00020\u00132\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\b\u0010c\u001a\u00020\u001bH\u0014J1\u0010d\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010\r2\b\u0010Z\u001a\u0004\u0018\u00010\r2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0000¢\u0006\u0004\be\u0010]J\b\u0010f\u001a\u00020\u001bH\u0002J\u0012\u0010g\u001a\u00020\u001b2\b\u0010h\u001a\u0004\u0018\u00010iH\u0016J\u008c\u0001\u0010j\u001a\u00020\u001b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020!2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-2\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010E\u001a\u00020\u00132\b\b\u0002\u0010?\u001a\u0002032\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010B\u001a\u00020\u00132\b\b\u0002\u0010T\u001a\u00020\u00132\b\b\u0002\u0010O\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010k\u001a\u00020\u001b2\u0006\u0010l\u001a\u00020!H\u0016J\b\u0010m\u001a\u00020\u001bH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R6\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010(\u001a\u00020'2\u0006\u0010\f\u001a\u00020'@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R(\u0010.\u001a\u0004\u0018\u00010-2\b\u0010\f\u001a\u0004\u0018\u00010-@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R$\u00104\u001a\u0002032\u0006\u0010\f\u001a\u000203@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u000203X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00106\"\u0004\bA\u00108R$\u0010B\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0016\"\u0004\bD\u0010\u0018R\u001a\u0010E\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0016\"\u0004\bG\u0010\u0018R\u0011\u0010H\u001a\u00020I¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR6\u0010L\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u001e\"\u0004\bN\u0010 R$\u0010O\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0010\"\u0004\bQ\u0010\u0012R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR$\u0010T\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0016\"\u0004\bV\u0010\u0018¨\u0006n"}, d2 = {"Lcom/nambimobile/widgets/efab/ExpandableFab;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "context", "Landroid/content/Context;", "orientation", "Lcom/nambimobile/widgets/efab/Orientation;", "(Landroid/content/Context;Lcom/nambimobile/widgets/efab/Orientation;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "animationTimer", "Ljava/util/Timer;", "value", "", "closingAnimationDurationMs", "getClosingAnimationDurationMs", "()J", "setClosingAnimationDurationMs", "(J)V", "", "closingAnticipateTension", "getClosingAnticipateTension", "()F", "setClosingAnticipateTension", "(F)V", "<set-?>", "Lkotlin/Function0;", "", "defaultOnClickBehavior", "getDefaultOnClickBehavior$expandable_fab_release", "()Lkotlin/jvm/functions/Function0;", "setDefaultOnClickBehavior$expandable_fab_release", "(Lkotlin/jvm/functions/Function0;)V", "", "efabColor", "getEfabColor", "()I", "setEfabColor", "(I)V", "", "efabEnabled", "getEfabEnabled", "()Z", "setEfabEnabled", "(Z)V", "Landroid/graphics/drawable/Drawable;", "efabIcon", "getEfabIcon", "()Landroid/graphics/drawable/Drawable;", "setEfabIcon", "(Landroid/graphics/drawable/Drawable;)V", "Lcom/nambimobile/widgets/efab/FabSize;", "efabSize", "getEfabSize", "()Lcom/nambimobile/widgets/efab/FabSize;", "setEfabSize", "(Lcom/nambimobile/widgets/efab/FabSize;)V", "fabOptionPosition", "Lcom/nambimobile/widgets/efab/FabOptionPosition;", "getFabOptionPosition", "()Lcom/nambimobile/widgets/efab/FabOptionPosition;", "setFabOptionPosition", "(Lcom/nambimobile/widgets/efab/FabOptionPosition;)V", "fabOptionSize", "getFabOptionSize", "setFabOptionSize", "firstFabOptionMarginPx", "getFirstFabOptionMarginPx", "setFirstFabOptionMarginPx", "iconAnimationRotationDeg", "getIconAnimationRotationDeg", "setIconAnimationRotationDeg", Constants.ScionAnalytics.PARAM_LABEL, "Lcom/nambimobile/widgets/efab/Label;", "getLabel", "()Lcom/nambimobile/widgets/efab/Label;", "onAnimationStart", "getOnAnimationStart$expandable_fab_release", "setOnAnimationStart$expandable_fab_release", "openingAnimationDurationMs", "getOpeningAnimationDurationMs", "setOpeningAnimationDurationMs", "getOrientation", "()Lcom/nambimobile/widgets/efab/Orientation;", "successiveFabOptionMarginPx", "getSuccessiveFabOptionMarginPx", "setSuccessiveFabOptionMarginPx", "closingAnimations", "Landroid/animation/Animator;", "globalDurationMs", "globalLabelDurationMs", "onAnimationFinished", "closingAnimations$expandable_fab_release", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;)Landroid/animation/Animator;", "hide", "manualIconAnimation", "durationMs", "startRotationDegrees", "endRotationDegrees", "onDetachedFromWindow", "openingAnimations", "openingAnimations$expandable_fab_release", "setLabelOnClickListener", "setOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setOptionalProperties", "setSize", "size", "show", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ExpandableFab extends FloatingActionButton {
    private Timer animationTimer;
    private long closingAnimationDurationMs;
    private float closingAnticipateTension;
    private Function0<Unit> defaultOnClickBehavior;
    private int efabColor;
    private boolean efabEnabled;
    private Drawable efabIcon;
    private FabSize efabSize;
    private FabOptionPosition fabOptionPosition;
    private FabSize fabOptionSize;
    private float firstFabOptionMarginPx;
    private float iconAnimationRotationDeg;
    private final Label label;
    private Function0<Unit> onAnimationStart;
    private long openingAnimationDurationMs;
    private Orientation orientation;
    private float successiveFabOptionMarginPx;

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final int getEfabColor() {
        return this.efabColor;
    }

    public final void setEfabColor(int i) {
        setBackgroundTintList(ColorStateList.valueOf(i));
        this.efabColor = i;
    }

    public final Drawable getEfabIcon() {
        return this.efabIcon;
    }

    public final void setEfabIcon(Drawable drawable) {
        setImageDrawable(drawable);
        this.efabIcon = drawable;
    }

    public final FabSize getEfabSize() {
        return this.efabSize;
    }

    public final void setEfabSize(FabSize value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value != FabSize.CUSTOM) {
            setSize(value.getValue());
        }
        this.efabSize = value;
    }

    public final boolean getEfabEnabled() {
        return this.efabEnabled;
    }

    public final void setEfabEnabled(boolean z) {
        if (z) {
            setEfabColor(this.efabColor);
        } else {
            setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.efab_disabled)));
        }
        setEnabled(z);
        this.label.setLabelEnabled$expandable_fab_release(z);
        this.efabEnabled = z;
    }

    public final float getIconAnimationRotationDeg() {
        return this.iconAnimationRotationDeg;
    }

    public final void setIconAnimationRotationDeg(float f) {
        this.iconAnimationRotationDeg = f;
    }

    public final FabSize getFabOptionSize() {
        return this.fabOptionSize;
    }

    public final void setFabOptionSize(FabSize fabSize) {
        Intrinsics.checkNotNullParameter(fabSize, "<set-?>");
        this.fabOptionSize = fabSize;
    }

    public final FabOptionPosition getFabOptionPosition() {
        return this.fabOptionPosition;
    }

    public final void setFabOptionPosition(FabOptionPosition fabOptionPosition) {
        Intrinsics.checkNotNullParameter(fabOptionPosition, "<set-?>");
        this.fabOptionPosition = fabOptionPosition;
    }

    public final float getFirstFabOptionMarginPx() {
        return this.firstFabOptionMarginPx;
    }

    public final void setFirstFabOptionMarginPx(float f) throws Resources.NotFoundException {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_efab_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.firstFabOptionMarginPx = f;
    }

    public final float getSuccessiveFabOptionMarginPx() {
        return this.successiveFabOptionMarginPx;
    }

    public final void setSuccessiveFabOptionMarginPx(float f) throws Resources.NotFoundException {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_efab_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.successiveFabOptionMarginPx = f;
    }

    public final long getOpeningAnimationDurationMs() {
        return this.openingAnimationDurationMs;
    }

    public final void setOpeningAnimationDurationMs(long j) throws Resources.NotFoundException {
        if (j < 0) {
            String string = getResources().getString(R.string.efab_efab_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
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
            String string = getResources().getString(R.string.efab_efab_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.closingAnimationDurationMs = j;
    }

    public final float getClosingAnticipateTension() {
        return this.closingAnticipateTension;
    }

    public final void setClosingAnticipateTension(float f) throws Resources.NotFoundException {
        if (f < 0.0f) {
            String string = getResources().getString(R.string.efab_efab_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
            ErrorsKt.illegalArg$default(string, null, 2, null);
            throw new KotlinNothingValueException();
        }
        this.closingAnticipateTension = f;
    }

    public final Label getLabel() {
        return this.label;
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

    public final /* synthetic */ Function0 getOnAnimationStart$expandable_fab_release() throws Resources.NotFoundException {
        Function0<Unit> function0 = this.onAnimationStart;
        if (function0 != null) {
            return function0;
        }
        String string = getResources().getString(R.string.efab_layout_must_be_child_of_expandablefab_layout);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_layout_must_be_child_of_expandablefab_layout)");
        ErrorsKt.illegalState$default(string, null, 2, null);
        throw new KotlinNothingValueException();
    }

    public final /* synthetic */ void setOnAnimationStart$expandable_fab_release(Function0 function0) {
        this.onAnimationStart = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpandableFab(Context context, Orientation orientation) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.orientation = Orientation.PORTRAIT;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        this.efabColor = ResourcesKt.getThemeColorAccent(context2);
        this.efabIcon = AppCompatResources.getDrawable(getContext(), R.drawable.ic_plus_white_24dp);
        this.efabSize = FabSize.NORMAL;
        this.efabEnabled = true;
        this.iconAnimationRotationDeg = -135.0f;
        this.fabOptionSize = FabSize.MINI;
        this.fabOptionPosition = FabOptionPosition.ABOVE;
        this.firstFabOptionMarginPx = 80.0f;
        this.successiveFabOptionMarginPx = 75.0f;
        this.openingAnimationDurationMs = 250L;
        this.closingAnimationDurationMs = 500L;
        this.closingAnticipateTension = 2.0f;
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
        label.setVisibleToHiddenAnimationDurationMs(125L);
        label.setHiddenToVisibleAnimationDurationMs(250L);
        label.setOvershootTension(3.5f);
        Unit unit = Unit.INSTANCE;
        this.label = label;
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        ImageViewCompat.setImageTintList(this, null);
        setOptionalProperties$default(this, orientation == null ? this.orientation : orientation, 0, null, null, false, 0.0f, null, null, 0.0f, 0.0f, 0L, 0L, 0.0f, 8190, null);
    }

    public /* synthetic */ ExpandableFab(Context context, Orientation orientation, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? Orientation.PORTRAIT : orientation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpandableFab(Context context, AttributeSet attributeSet) throws Throwable {
        long jLongValue;
        long jLongValue2;
        Typeface font;
        TypedArray typedArray;
        long jLongValue3;
        long jLongValue4;
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.orientation = Orientation.PORTRAIT;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        this.efabColor = ResourcesKt.getThemeColorAccent(context2);
        this.efabIcon = AppCompatResources.getDrawable(getContext(), R.drawable.ic_plus_white_24dp);
        this.efabSize = FabSize.NORMAL;
        this.efabEnabled = true;
        this.iconAnimationRotationDeg = -135.0f;
        this.fabOptionSize = FabSize.MINI;
        this.fabOptionPosition = FabOptionPosition.ABOVE;
        this.firstFabOptionMarginPx = 80.0f;
        this.successiveFabOptionMarginPx = 75.0f;
        this.openingAnimationDurationMs = 250L;
        this.closingAnimationDurationMs = 500L;
        this.closingAnticipateTension = 2.0f;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "context");
        Label label = new Label(context3);
        Drawable drawable = null;
        label.setLabelText(null);
        label.setLabelTextColor(ContextCompat.getColor(label.getContext(), android.R.color.white));
        label.setLabelTextSize(label.getResources().getDimension(R.dimen.efab_label_text_size));
        label.setLabelFont(Typeface.DEFAULT);
        label.setLabelBackgroundColor(ContextCompat.getColor(label.getContext(), R.color.efab_label_background));
        label.setLabelElevation(label.getResources().getDimensionPixelSize(R.dimen.efab_label_elevation));
        label.setPosition(LabelPosition.LEFT);
        label.setMarginPx(50.0f);
        label.setTranslationXPx(100.0f);
        label.setVisibleToHiddenAnimationDurationMs(125L);
        label.setHiddenToVisibleAnimationDurationMs(250L);
        label.setOvershootTension(3.5f);
        Unit unit = Unit.INSTANCE;
        this.label = label;
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        ImageViewCompat.setImageTintList(this, null);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ExpandableFab, 0, 0);
        try {
            try {
                int i = typedArrayObtainStyledAttributes.getInt(R.styleable.ExpandableFab_label_position, LabelPosition.LEFT.ordinal());
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFab_label_visibleToHiddenAnimationDurationMs);
                Long lValueOf = string == null ? null : Long.valueOf(Long.parseLong(string));
                if (lValueOf != null) {
                    jLongValue = lValueOf.longValue();
                } else {
                    jLongValue = getLabel().getVisibleToHiddenAnimationDurationMs();
                }
                String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFab_label_hiddenToVisibleAnimationDurationMs);
                Long lValueOf2 = string2 == null ? null : Long.valueOf(Long.parseLong(string2));
                if (lValueOf2 != null) {
                    jLongValue2 = lValueOf2.longValue();
                } else {
                    jLongValue2 = getLabel().getHiddenToVisibleAnimationDurationMs();
                }
                Label label2 = getLabel();
                label2.setLabelText(typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFab_label_text));
                label2.setLabelTextColor(typedArrayObtainStyledAttributes.getColor(R.styleable.ExpandableFab_label_textColor, getLabel().getLabelTextColor()));
                label2.setLabelTextSize(typedArrayObtainStyledAttributes.getDimension(R.styleable.ExpandableFab_label_textSize, getLabel().getLabelTextSize()));
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ExpandableFab_label_font, 0);
                if (resourceId == 0) {
                    font = getLabel().getLabelFont();
                } else {
                    font = ResourcesCompat.getFont(context, resourceId);
                }
                label2.setLabelFont(font);
                label2.setLabelBackgroundColor(typedArrayObtainStyledAttributes.getColor(R.styleable.ExpandableFab_label_backgroundColor, getLabel().getLabelBackgroundColor()));
                label2.setLabelElevation(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ExpandableFab_label_elevation, getLabel().getLabelElevation()));
                label2.setPosition(LabelPosition.values()[i]);
                label2.setMarginPx(typedArrayObtainStyledAttributes.getFloat(R.styleable.ExpandableFab_label_marginPx, getLabel().getMarginPx()));
                label2.setVisibleToHiddenAnimationDurationMs(jLongValue);
                label2.setHiddenToVisibleAnimationDurationMs(jLongValue2);
                label2.setOvershootTension(typedArrayObtainStyledAttributes.getFloat(R.styleable.ExpandableFab_label_overshootTension, getLabel().getOvershootTension()));
                label2.setTranslationXPx(typedArrayObtainStyledAttributes.getFloat(R.styleable.ExpandableFab_label_translationXPx, getLabel().getTranslationXPx()));
                typedArrayObtainStyledAttributes.recycle();
                TypedArray typedArrayObtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ExpandableFab, 0, 0);
                try {
                    int i2 = typedArrayObtainStyledAttributes2.getInt(R.styleable.ExpandableFab_efab_orientation, Orientation.PORTRAIT.ordinal());
                    int i3 = typedArrayObtainStyledAttributes2.getInt(R.styleable.ExpandableFab_efab_fabOptionPosition, FabOptionPosition.ABOVE.ordinal());
                    int i4 = typedArrayObtainStyledAttributes2.getInt(R.styleable.ExpandableFab_efab_size, FabSize.NORMAL.ordinal());
                    int i5 = typedArrayObtainStyledAttributes2.getInt(R.styleable.ExpandableFab_efab_fabOptionSize, FabSize.MINI.ordinal());
                    String string3 = typedArrayObtainStyledAttributes2.getString(R.styleable.ExpandableFab_efab_openingAnimationDurationMs);
                    Long lValueOf3 = string3 == null ? null : Long.valueOf(Long.parseLong(string3));
                    if (lValueOf3 != null) {
                        jLongValue3 = lValueOf3.longValue();
                    } else {
                        jLongValue3 = getOpeningAnimationDurationMs();
                    }
                    long j = jLongValue3;
                    String string4 = typedArrayObtainStyledAttributes2.getString(R.styleable.ExpandableFab_efab_closingAnimationDurationMs);
                    Long lValueOf4 = string4 == null ? null : Long.valueOf(Long.parseLong(string4));
                    if (lValueOf4 != null) {
                        jLongValue4 = lValueOf4.longValue();
                    } else {
                        jLongValue4 = getClosingAnimationDurationMs();
                    }
                    long j2 = jLongValue4;
                    int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.ExpandableFab_efab_icon, 0);
                    if (resourceId2 == 0) {
                    } else {
                        drawable = AppCompatResources.getDrawable(context, resourceId2);
                    }
                    typedArray = typedArrayObtainStyledAttributes2;
                    try {
                        try {
                            setOptionalProperties(Orientation.values()[i2], typedArrayObtainStyledAttributes2.getColor(R.styleable.ExpandableFab_efab_color, getEfabColor()), drawable == null ? getEfabIcon() : drawable, FabSize.values()[i4], typedArrayObtainStyledAttributes2.getBoolean(R.styleable.ExpandableFab_efab_enabled, true), typedArrayObtainStyledAttributes2.getFloat(R.styleable.ExpandableFab_efab_iconAnimationRotationDeg, getIconAnimationRotationDeg()), FabSize.values()[i5], FabOptionPosition.values()[i3], typedArrayObtainStyledAttributes2.getFloat(R.styleable.ExpandableFab_efab_firstFabOptionMarginPx, getFirstFabOptionMarginPx()), typedArrayObtainStyledAttributes2.getFloat(R.styleable.ExpandableFab_efab_successiveFabOptionMarginPx, getSuccessiveFabOptionMarginPx()), j, j2, typedArrayObtainStyledAttributes2.getFloat(R.styleable.ExpandableFab_efab_closingAnticipateTension, getClosingAnticipateTension()));
                            typedArray.recycle();
                        } catch (Exception e) {
                            e = e;
                            String string5 = typedArray.getResources().getString(R.string.efab_efab_illegal_optional_properties);
                            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.efab_efab_illegal_optional_properties)");
                            ErrorsKt.illegalArg(string5, e);
                            throw new KotlinNothingValueException();
                        }
                    } catch (Throwable th) {
                        th = th;
                        typedArray.recycle();
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    typedArray = typedArrayObtainStyledAttributes2;
                } catch (Throwable th2) {
                    th = th2;
                    typedArray = typedArrayObtainStyledAttributes2;
                    typedArray.recycle();
                    throw th;
                }
            } catch (Throwable th3) {
                typedArrayObtainStyledAttributes.recycle();
                throw th3;
            }
        } catch (Exception e3) {
            String string6 = typedArrayObtainStyledAttributes.getResources().getString(R.string.efab_label_illegal_optional_properties);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.efab_label_illegal_optional_properties)");
            ErrorsKt.illegalArg(string6, e3);
            throw new KotlinNothingValueException();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() { // from class: com.nambimobile.widgets.efab.ExpandableFab$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                ExpandableFab.m6563setOnClickListener$lambda4(this.f$0, onClickListener, view);
            }
        });
        setLabelOnClickListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnClickListener$lambda-4, reason: not valid java name */
    public static final void m6563setOnClickListener$lambda4(ExpandableFab this$0, View.OnClickListener onClickListener, View view) throws Resources.NotFoundException {
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

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Timer timer = this.animationTimer;
        if (timer == null) {
            return;
        }
        timer.cancel();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton
    public void show() {
        super.show();
        this.label.showLabel$expandable_fab_release();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton
    public void hide() {
        super.hide();
        this.label.hideLabel$expandable_fab_release();
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton
    public void setSize(int size) {
        if (size != FabSize.CUSTOM.getValue()) {
            super.setSize(size);
        }
    }

    static /* synthetic */ void setOptionalProperties$default(ExpandableFab expandableFab, Orientation orientation, int i, Drawable drawable, FabSize fabSize, boolean z, float f, FabSize fabSize2, FabOptionPosition fabOptionPosition, float f2, float f3, long j, long j2, float f4, int i2, Object obj) throws Resources.NotFoundException {
        expandableFab.setOptionalProperties((i2 & 1) != 0 ? expandableFab.orientation : orientation, (i2 & 2) != 0 ? expandableFab.efabColor : i, (i2 & 4) != 0 ? expandableFab.efabIcon : drawable, (i2 & 8) != 0 ? expandableFab.efabSize : fabSize, (i2 & 16) != 0 ? expandableFab.efabEnabled : z, (i2 & 32) != 0 ? expandableFab.iconAnimationRotationDeg : f, (i2 & 64) != 0 ? expandableFab.fabOptionSize : fabSize2, (i2 & 128) != 0 ? expandableFab.fabOptionPosition : fabOptionPosition, (i2 & 256) != 0 ? expandableFab.firstFabOptionMarginPx : f2, (i2 & 512) != 0 ? expandableFab.successiveFabOptionMarginPx : f3, (i2 & 1024) != 0 ? expandableFab.openingAnimationDurationMs : j, (i2 & 2048) != 0 ? expandableFab.closingAnimationDurationMs : j2, (i2 & 4096) != 0 ? expandableFab.closingAnticipateTension : f4);
    }

    private final void setOptionalProperties(Orientation orientation, int efabColor, Drawable efabIcon, FabSize efabSize, boolean efabEnabled, float iconAnimationRotationDeg, FabSize fabOptionSize, FabOptionPosition fabOptionPosition, float firstFabOptionMarginPx, float successiveFabOptionMarginPx, long openingAnimationDurationMs, long closingAnimationDurationMs, float closingAnticipateTension) throws Resources.NotFoundException {
        this.orientation = orientation;
        setEfabColor(efabColor);
        setEfabIcon(efabIcon);
        this.iconAnimationRotationDeg = iconAnimationRotationDeg;
        setEfabSize(efabSize);
        setEfabEnabled(efabEnabled);
        this.fabOptionSize = fabOptionSize;
        this.fabOptionPosition = fabOptionPosition;
        setFirstFabOptionMarginPx(firstFabOptionMarginPx);
        setSuccessiveFabOptionMarginPx(successiveFabOptionMarginPx);
        setOpeningAnimationDurationMs(openingAnimationDurationMs);
        setClosingAnimationDurationMs(closingAnimationDurationMs);
        setClosingAnticipateTension(closingAnticipateTension);
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
        label.setOnClickListener(new View.OnClickListener() { // from class: com.nambimobile.widgets.efab.ExpandableFab$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExpandableFab.m6562setLabelOnClickListener$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setLabelOnClickListener$lambda-5, reason: not valid java name */
    public static final void m6562setLabelOnClickListener$lambda5(ExpandableFab this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.callOnClick();
    }

    public final /* synthetic */ Animator openingAnimations$expandable_fab_release(Long globalDurationMs, Long globalLabelDurationMs, final Function0 onAnimationFinished) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(onAnimationFinished, "onAnimationFinished");
        manualIconAnimation(globalDurationMs == null ? this.openingAnimationDurationMs : globalDurationMs.longValue(), 0.0f, this.iconAnimationRotationDeg, new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFab$openingAnimations$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                onAnimationFinished.invoke();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(getLabel().visibleToHiddenAnimations$expandable_fab_release(globalLabelDurationMs));
        return animatorSet;
    }

    public final /* synthetic */ Animator closingAnimations$expandable_fab_release(final Long globalDurationMs, Long globalLabelDurationMs, final Function0 onAnimationFinished) throws Resources.NotFoundException {
        long jLongValue;
        Intrinsics.checkNotNullParameter(onAnimationFinished, "onAnimationFinished");
        float fAbs = Math.abs(this.iconAnimationRotationDeg / 10.0f) * this.closingAnticipateTension;
        float f = this.iconAnimationRotationDeg;
        final float f2 = f < 0.0f ? f - fAbs : f + fAbs;
        if (globalDurationMs != null) {
            jLongValue = globalDurationMs.longValue() / 5;
        } else {
            jLongValue = this.closingAnimationDurationMs / 5;
        }
        final long j = jLongValue;
        final boolean z = ((double) Math.abs(fAbs - 0.0f)) > 0.01d;
        if (z) {
            manualIconAnimation(j, this.iconAnimationRotationDeg, f2, new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFab$closingAnimations$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    boolean z2 = z;
                    ExpandableFab expandableFab = this;
                    Function0<Unit> function0 = onAnimationFinished;
                    Long l = globalDurationMs;
                    ExpandableFab.closingAnimations$regularClosingAnimation(z2, expandableFab, function0, (l == null ? expandableFab.getClosingAnimationDurationMs() : l.longValue()) - j, f2);
                }
            });
        } else {
            closingAnimations$regularClosingAnimation(z, this, onAnimationFinished, globalDurationMs == null ? this.closingAnimationDurationMs : globalDurationMs.longValue(), this.iconAnimationRotationDeg);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(getLabel().hiddenToVisibleAnimations$expandable_fab_release(globalLabelDurationMs));
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void closingAnimations$regularClosingAnimation(boolean z, final ExpandableFab expandableFab, final Function0<Unit> function0, final long j, final float f) {
        new Timer().schedule(new TimerTask() { // from class: com.nambimobile.widgets.efab.ExpandableFab$closingAnimations$regularClosingAnimation$$inlined$schedule$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                ExpandableFab expandableFab2 = this.this$0;
                long j2 = j;
                float f2 = f;
                final Function0 function02 = function0;
                expandableFab2.manualIconAnimation(j2, f2, 0.0f, new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFab$closingAnimations$regularClosingAnimation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function02.invoke();
                    }
                });
            }
        }, z ? 100L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void manualIconAnimation(long durationMs, float startRotationDegrees, final float endRotationDegrees, final Function0<Unit> onAnimationFinished) throws Resources.NotFoundException {
        float fAbs = Math.abs(endRotationDegrees - startRotationDegrees);
        if (durationMs != 0) {
            fAbs = Math.abs(fAbs / durationMs);
        }
        final float f = fAbs * 10;
        final double d = 0.01d;
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = startRotationDegrees;
        final Matrix matrix = new Matrix();
        Function0 onAnimationStart$expandable_fab_release = getOnAnimationStart$expandable_fab_release();
        if (onAnimationStart$expandable_fab_release != null) {
            onAnimationStart$expandable_fab_release.invoke();
        }
        Timer timer = TimersKt.timer(null, false);
        timer.schedule(new TimerTask() { // from class: com.nambimobile.widgets.efab.ExpandableFab$manualIconAnimation$$inlined$timer$default$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                float fMax;
                ExpandableFab$manualIconAnimation$$inlined$timer$default$1 expandableFab$manualIconAnimation$$inlined$timer$default$1 = this;
                Ref.FloatRef floatRef2 = floatRef;
                if (endRotationDegrees > floatRef2.element) {
                    floatRef.element += f;
                    fMax = Math.min(floatRef.element, endRotationDegrees);
                } else {
                    floatRef.element -= f;
                    fMax = Math.max(floatRef.element, endRotationDegrees);
                }
                floatRef2.element = fMax;
                ExpandableFab expandableFab = this;
                final ExpandableFab expandableFab2 = this;
                final Matrix matrix2 = matrix;
                final Ref.FloatRef floatRef3 = floatRef;
                expandableFab.post(new Runnable() { // from class: com.nambimobile.widgets.efab.ExpandableFab$manualIconAnimation$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (expandableFab2.getDrawable() == null) {
                            return;
                        }
                        Matrix matrix3 = matrix2;
                        ExpandableFab expandableFab3 = expandableFab2;
                        Ref.FloatRef floatRef4 = floatRef3;
                        matrix3.reset();
                        expandableFab3.setScaleType(ImageView.ScaleType.MATRIX);
                        matrix3.postRotate(floatRef4.element, r0.getBounds().width() / 2, r0.getBounds().height() / 2);
                        expandableFab3.setImageMatrix(matrix3);
                    }
                });
                if (Math.abs(endRotationDegrees - floatRef.element) < d) {
                    expandableFab$manualIconAnimation$$inlined$timer$default$1.cancel();
                    ExpandableFab expandableFab3 = this;
                    final Function0 function0 = onAnimationFinished;
                    expandableFab3.post(new Runnable() { // from class: com.nambimobile.widgets.efab.ExpandableFab$manualIconAnimation$1$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            function0.invoke();
                        }
                    });
                }
            }
        }, 0L, 10L);
        this.animationTimer = timer;
    }
}
