package com.nambimobile.widgets.efab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpandableFabLayout.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b*\u000269\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J$\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J$\u0010C\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J$\u0010D\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J$\u0010E\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J#\u0010F\u001a\u00020<2\u0016\u0010G\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010>0H\"\u0004\u0018\u00010>¢\u0006\u0002\u0010IJ\b\u0010J\u001a\u00020\tH\u0002J\u0006\u0010K\u001a\u00020<J\b\u0010L\u001a\u00020<H\u0002J\b\u0010M\u001a\u00020\tH\u0002J\b\u0010N\u001a\u00020<H\u0002J\u0010\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020(H\u0002J\u0006\u0010R\u001a\u00020(J\u0010\u0010S\u001a\u00020P2\u0006\u0010Q\u001a\u00020(H\u0002J\u0006\u0010T\u001a\u00020\tJ\b\u0010,\u001a\u00020<H\u0002J\b\u0010U\u001a\u00020<H\u0016J\u0010\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t8@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R\u000e\u0010!\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001e\u0010%\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001e\u0010)\u001a\u00020(2\u0006\u0010\n\u001a\u00020(@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u0010\u0015R\u001e\u00100\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b1\u0010\u0013\"\u0004\b2\u0010\u0015R\u001e\u00103\u001a\u00020(2\u0006\u0010\n\u001a\u00020(@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u0010+R\u0010\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0004\n\u0002\u00107R\u0010\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0004\n\u0002\u0010:¨\u0006X"}, d2 = {"Lcom/nambimobile/widgets/efab/ExpandableFabLayout;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "closeWhenAble", "", "<set-?>", "efabAnimationsFinished", "getEfabAnimationsFinished$expandable_fab_release", "()Z", "setEfabAnimationsFinished$expandable_fab_release", "(Z)V", "expandableFabClosingAnimationDurationMs", "", "getExpandableFabClosingAnimationDurationMs", "()Ljava/lang/Long;", "setExpandableFabClosingAnimationDurationMs", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "expandableFabOpeningAnimationDurationMs", "getExpandableFabOpeningAnimationDurationMs", "setExpandableFabOpeningAnimationDurationMs", "fabOptionAlreadyClicked", "fabOptionClosingAnimationDurationMs", "getFabOptionClosingAnimationDurationMs", "setFabOptionClosingAnimationDurationMs", "fabOptionOpeningAnimationDurationMs", "getFabOptionOpeningAnimationDurationMs", "setFabOptionOpeningAnimationDurationMs", "groupAnimationsFinished", "labelHiddenToVisibleAnimationDurationMs", "getLabelHiddenToVisibleAnimationDurationMs", "setLabelHiddenToVisibleAnimationDurationMs", "labelVisibleToHiddenAnimationDurationMs", "getLabelVisibleToHiddenAnimationDurationMs", "setLabelVisibleToHiddenAnimationDurationMs", "Lcom/nambimobile/widgets/efab/OrientationConfiguration;", "landscapeConfiguration", "getLandscapeConfiguration", "()Lcom/nambimobile/widgets/efab/OrientationConfiguration;", "open", "overlayClosingAnimationDurationMs", "getOverlayClosingAnimationDurationMs", "setOverlayClosingAnimationDurationMs", "overlayOpeningAnimationDurationMs", "getOverlayOpeningAnimationDurationMs", "setOverlayOpeningAnimationDurationMs", "portraitConfiguration", "getPortraitConfiguration", "setStateAsClosed", "com/nambimobile/widgets/efab/ExpandableFabLayout$setStateAsClosed$1", "Lcom/nambimobile/widgets/efab/ExpandableFabLayout$setStateAsClosed$1;", "setStateAsOpened", "com/nambimobile/widgets/efab/ExpandableFabLayout$setStateAsOpened$1", "Lcom/nambimobile/widgets/efab/ExpandableFabLayout$setStateAsOpened$1;", "addExpandableFab", "", "child", "Landroid/view/View;", FirebaseAnalytics.Param.INDEX, "", "params", "Landroid/view/ViewGroup$LayoutParams;", "addFabOption", "addOverlay", "addView", "addViews", "children", "", "([Landroid/view/View;)V", "animationsFinished", "close", "defaultExpandableFabOnClickBehavior", "defaultFabOptionOnClickBehavior", "defaultOverlayOnClickBehavior", "getClosingAnimations", "Landroid/animation/Animator;", "configuration", "getCurrentConfiguration", "getOpeningAnimations", "isOpen", "removeAllViews", "setState", "opened", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ExpandableFabLayout extends CoordinatorLayout {
    private boolean closeWhenAble;
    private boolean efabAnimationsFinished;
    private Long expandableFabClosingAnimationDurationMs;
    private Long expandableFabOpeningAnimationDurationMs;
    private boolean fabOptionAlreadyClicked;
    private Long fabOptionClosingAnimationDurationMs;
    private Long fabOptionOpeningAnimationDurationMs;
    private boolean groupAnimationsFinished;
    private Long labelHiddenToVisibleAnimationDurationMs;
    private Long labelVisibleToHiddenAnimationDurationMs;
    private OrientationConfiguration landscapeConfiguration;
    private boolean open;
    private Long overlayClosingAnimationDurationMs;
    private Long overlayOpeningAnimationDurationMs;
    private OrientationConfiguration portraitConfiguration;
    private ExpandableFabLayout$setStateAsClosed$1 setStateAsClosed;
    private ExpandableFabLayout$setStateAsOpened$1 setStateAsOpened;

    /* compiled from: ExpandableFabLayout.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            iArr[Orientation.PORTRAIT.ordinal()] = 1;
            iArr[Orientation.LANDSCAPE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final OrientationConfiguration getPortraitConfiguration() {
        return this.portraitConfiguration;
    }

    public final OrientationConfiguration getLandscapeConfiguration() {
        return this.landscapeConfiguration;
    }

    public final Long getOverlayOpeningAnimationDurationMs() {
        return this.overlayOpeningAnimationDurationMs;
    }

    public final void setOverlayOpeningAnimationDurationMs(Long l) {
        this.overlayOpeningAnimationDurationMs = l;
    }

    public final Long getOverlayClosingAnimationDurationMs() {
        return this.overlayClosingAnimationDurationMs;
    }

    public final void setOverlayClosingAnimationDurationMs(Long l) {
        this.overlayClosingAnimationDurationMs = l;
    }

    public final Long getExpandableFabOpeningAnimationDurationMs() {
        return this.expandableFabOpeningAnimationDurationMs;
    }

    public final void setExpandableFabOpeningAnimationDurationMs(Long l) {
        this.expandableFabOpeningAnimationDurationMs = l;
    }

    public final Long getExpandableFabClosingAnimationDurationMs() {
        return this.expandableFabClosingAnimationDurationMs;
    }

    public final void setExpandableFabClosingAnimationDurationMs(Long l) {
        this.expandableFabClosingAnimationDurationMs = l;
    }

    public final Long getFabOptionOpeningAnimationDurationMs() {
        return this.fabOptionOpeningAnimationDurationMs;
    }

    public final void setFabOptionOpeningAnimationDurationMs(Long l) {
        this.fabOptionOpeningAnimationDurationMs = l;
    }

    public final Long getFabOptionClosingAnimationDurationMs() {
        return this.fabOptionClosingAnimationDurationMs;
    }

    public final void setFabOptionClosingAnimationDurationMs(Long l) {
        this.fabOptionClosingAnimationDurationMs = l;
    }

    public final Long getLabelVisibleToHiddenAnimationDurationMs() {
        return this.labelVisibleToHiddenAnimationDurationMs;
    }

    public final void setLabelVisibleToHiddenAnimationDurationMs(Long l) {
        this.labelVisibleToHiddenAnimationDurationMs = l;
    }

    public final Long getLabelHiddenToVisibleAnimationDurationMs() {
        return this.labelHiddenToVisibleAnimationDurationMs;
    }

    public final void setLabelHiddenToVisibleAnimationDurationMs(Long l) {
        this.labelHiddenToVisibleAnimationDurationMs = l;
    }

    /* renamed from: getEfabAnimationsFinished$expandable_fab_release, reason: from getter */
    public final /* synthetic */ boolean getEfabAnimationsFinished() {
        return this.efabAnimationsFinished;
    }

    public final /* synthetic */ void setEfabAnimationsFinished$expandable_fab_release(boolean z) {
        this.efabAnimationsFinished = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsOpened$1] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsClosed$1] */
    public ExpandableFabLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.portraitConfiguration = new OrientationConfiguration();
        this.landscapeConfiguration = new OrientationConfiguration();
        this.efabAnimationsFinished = true;
        this.groupAnimationsFinished = true;
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        this.setStateAsOpened = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsOpened$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.groupAnimationsFinished = true;
                this.this$0.setState(true);
            }
        };
        this.setStateAsClosed = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsClosed$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.groupAnimationsFinished = true;
                this.this$0.setState(false);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsOpened$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsClosed$1] */
    public ExpandableFabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.portraitConfiguration = new OrientationConfiguration();
        this.landscapeConfiguration = new OrientationConfiguration();
        this.efabAnimationsFinished = true;
        this.groupAnimationsFinished = true;
        if (getId() == -1) {
            setId(ViewCompat.generateViewId());
        }
        this.setStateAsOpened = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsOpened$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.groupAnimationsFinished = true;
                this.this$0.setState(true);
            }
        };
        this.setStateAsClosed = new AnimatorListenerAdapter() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$setStateAsClosed$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                this.this$0.groupAnimationsFinished = true;
                this.this$0.setState(false);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ExpandableFabLayout, 0, 0);
        try {
            try {
                String string = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_overlayOpeningAnimationDurationMs);
                Long lValueOf = null;
                setOverlayOpeningAnimationDurationMs(string == null ? null : Long.valueOf(Long.parseLong(string)));
                String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_overlayClosingAnimationDurationMs);
                setOverlayClosingAnimationDurationMs(string2 == null ? null : Long.valueOf(Long.parseLong(string2)));
                String string3 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_expandableFabOpeningAnimationDurationMs);
                setExpandableFabOpeningAnimationDurationMs(string3 == null ? null : Long.valueOf(Long.parseLong(string3)));
                String string4 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_expandableFabClosingAnimationDurationMs);
                setExpandableFabClosingAnimationDurationMs(string4 == null ? null : Long.valueOf(Long.parseLong(string4)));
                String string5 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_fabOptionOpeningAnimationDurationMs);
                setFabOptionOpeningAnimationDurationMs(string5 == null ? null : Long.valueOf(Long.parseLong(string5)));
                String string6 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_fabOptionClosingAnimationDurationMs);
                setFabOptionClosingAnimationDurationMs(string6 == null ? null : Long.valueOf(Long.parseLong(string6)));
                String string7 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_labelVisibleToHiddenAnimationDurationMs);
                setLabelVisibleToHiddenAnimationDurationMs(string7 == null ? null : Long.valueOf(Long.parseLong(string7)));
                String string8 = typedArrayObtainStyledAttributes.getString(R.styleable.ExpandableFabLayout_efab_layout_labelHiddenToVisibleAnimationDurationMs);
                if (string8 != null) {
                    lValueOf = Long.valueOf(Long.parseLong(string8));
                }
                setLabelHiddenToVisibleAnimationDurationMs(lValueOf);
            } catch (Exception e) {
                String string9 = typedArrayObtainStyledAttributes.getResources().getString(R.string.efab_layout_illegal_optional_properties);
                Intrinsics.checkNotNullExpressionValue(string9, "resources.getString(R.string.efab_layout_illegal_optional_properties)");
                ErrorsKt.illegalArg(string9, e);
                throw new KotlinNothingValueException();
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) throws Resources.NotFoundException {
        if (child instanceof Overlay) {
            addOverlay(child, index, params);
            return;
        }
        if (child instanceof ExpandableFab) {
            addExpandableFab(child, index, params);
        } else if (child instanceof FabOption) {
            addFabOption(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }

    public final void addViews(View... children) {
        Intrinsics.checkNotNullParameter(children, "children");
        int length = children.length;
        int i = 0;
        while (i < length) {
            View view = children[i];
            i++;
            addView(view);
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        this.portraitConfiguration = new OrientationConfiguration();
        this.landscapeConfiguration = new OrientationConfiguration();
        this.efabAnimationsFinished = true;
        this.groupAnimationsFinished = true;
        this.open = false;
        this.closeWhenAble = false;
        this.fabOptionAlreadyClicked = false;
    }

    public final void close() {
        if (!animationsFinished()) {
            this.closeWhenAble = true;
        } else if (this.open) {
            this.groupAnimationsFinished = false;
            getClosingAnimations(getCurrentConfiguration()).start();
        }
    }

    /* renamed from: isOpen, reason: from getter */
    public final boolean getOpen() {
        return this.open;
    }

    public final OrientationConfiguration getCurrentConfiguration() {
        return getResources().getConfiguration().orientation == 1 ? this.portraitConfiguration.getEfab() != null ? this.portraitConfiguration : this.landscapeConfiguration : this.landscapeConfiguration.getEfab() != null ? this.landscapeConfiguration : this.portraitConfiguration;
    }

    private final void addOverlay(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child != null) {
            Overlay overlay = (Overlay) child;
            overlay.setDefaultOnClickBehavior$expandable_fab_release(new ExpandableFabLayout$addOverlay$overlay$1$1(this));
            int i = WhenMappings.$EnumSwitchMapping$0[overlay.getOrientation().ordinal()];
            if (i == 1) {
                this.portraitConfiguration.setOverlay$expandable_fab_release(overlay);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                this.landscapeConfiguration.setOverlay$expandable_fab_release(overlay);
                return;
            }
        }
        throw new NullPointerException("null cannot be cast to non-null type com.nambimobile.widgets.efab.Overlay");
    }

    private final void addExpandableFab(View child, int index, ViewGroup.LayoutParams params) throws Resources.NotFoundException {
        super.addView(child, index, params);
        if (child != null) {
            ExpandableFab expandableFab = (ExpandableFab) child;
            expandableFab.setDefaultOnClickBehavior$expandable_fab_release(new ExpandableFabLayout$addExpandableFab$efab$1$1(this));
            expandableFab.setOnAnimationStart$expandable_fab_release(new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$addExpandableFab$efab$1$2
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
                    this.this$0.setEfabAnimationsFinished$expandable_fab_release(false);
                }
            });
            Label label = expandableFab.getLabel();
            addView(label);
            ViewGroup.LayoutParams layoutParams = label.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            }
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            layoutParams2.setAnchorId(expandableFab.getId());
            label.setLayoutParams(layoutParams2);
            label.showLabel$expandable_fab_release();
            int i = WhenMappings.$EnumSwitchMapping$0[expandableFab.getOrientation().ordinal()];
            if (i == 1) {
                if (this.portraitConfiguration.getEfab() != null) {
                    String string = getResources().getString(R.string.efab_layout_multiple_efabs, expandableFab.getOrientation());
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.efab_layout_multiple_efabs, efab.orientation)");
                    ErrorsKt.illegalState$default(string, null, 2, null);
                    throw new KotlinNothingValueException();
                }
                this.portraitConfiguration.setEfab$expandable_fab_release(expandableFab);
                expandableFab.show();
                if (getResources().getConfiguration().orientation == 1) {
                    ExpandableFab efab = this.landscapeConfiguration.getEfab();
                    if (efab == null) {
                        return;
                    }
                    efab.hide();
                    return;
                }
                if (this.landscapeConfiguration.getEfab() != null) {
                    expandableFab.hide();
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            if (this.landscapeConfiguration.getEfab() != null) {
                String string2 = getResources().getString(R.string.efab_layout_multiple_efabs, expandableFab.getOrientation());
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.efab_layout_multiple_efabs, efab.orientation)");
                ErrorsKt.illegalState$default(string2, null, 2, null);
                throw new KotlinNothingValueException();
            }
            this.landscapeConfiguration.setEfab$expandable_fab_release(expandableFab);
            expandableFab.show();
            if (getResources().getConfiguration().orientation == 2) {
                ExpandableFab efab2 = this.portraitConfiguration.getEfab();
                if (efab2 == null) {
                    return;
                }
                efab2.hide();
                return;
            }
            if (this.portraitConfiguration.getEfab() != null) {
                expandableFab.hide();
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.nambimobile.widgets.efab.ExpandableFab");
    }

    private final void addFabOption(View child, int index, ViewGroup.LayoutParams params) {
        OrientationConfiguration orientationConfiguration;
        super.addView(child, index, params);
        if (child != null) {
            FabOption fabOption = (FabOption) child;
            fabOption.setDefaultOnClickBehavior$expandable_fab_release(new ExpandableFabLayout$addFabOption$fabOption$1$1(this));
            int i = WhenMappings.$EnumSwitchMapping$0[fabOption.getOrientation().ordinal()];
            if (i == 1) {
                orientationConfiguration = this.portraitConfiguration;
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                orientationConfiguration = this.landscapeConfiguration;
            }
            Label label = fabOption.getLabel();
            addView(label);
            ViewGroup.LayoutParams layoutParams = label.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            }
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            layoutParams2.setAnchorId(fabOption.getId());
            label.setLayoutParams(layoutParams2);
            orientationConfiguration.getFabOptions().add(fabOption);
            orientationConfiguration.setFabOptionAnchor$expandable_fab_release(fabOption, CollectionsKt.getLastIndex(orientationConfiguration.getFabOptions()));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.nambimobile.widgets.efab.FabOption");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void defaultOverlayOnClickBehavior() {
        close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void defaultExpandableFabOnClickBehavior() {
        if (this.open || !animationsFinished()) {
            close();
        } else {
            open();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean defaultFabOptionOnClickBehavior() {
        if (this.fabOptionAlreadyClicked) {
            return false;
        }
        this.fabOptionAlreadyClicked = true;
        close();
        return true;
    }

    private final void open() {
        if (this.open) {
            return;
        }
        this.groupAnimationsFinished = false;
        getOpeningAnimations(getCurrentConfiguration()).start();
    }

    private final Animator getOpeningAnimations(OrientationConfiguration configuration) {
        ExpandableFab efab = configuration.getEfab();
        if (efab == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.nambimobile.widgets.efab.ExpandableFab");
        }
        List<FabOption> fabOptions = configuration.getFabOptions();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(fabOptions, 10));
        int i = 0;
        for (Object obj : fabOptions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(((FabOption) obj).openingAnimations$expandable_fab_release(i, efab.getFabOptionSize(), efab.getFabOptionPosition(), efab.getFirstFabOptionMarginPx(), efab.getSuccessiveFabOptionMarginPx(), getFabOptionOpeningAnimationDurationMs(), getLabelHiddenToVisibleAnimationDurationMs()));
            i = i2;
        }
        ArrayList arrayList2 = arrayList;
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[3];
        Overlay overlay = configuration.getOverlay();
        AnimatorSet animatorSetOpeningAnimations$expandable_fab_release = overlay == null ? null : overlay.openingAnimations$expandable_fab_release(getOverlayOpeningAnimationDurationMs());
        if (animatorSetOpeningAnimations$expandable_fab_release == null) {
            animatorSetOpeningAnimations$expandable_fab_release = new AnimatorSet();
        }
        animatorArr[0] = animatorSetOpeningAnimations$expandable_fab_release;
        animatorArr[1] = efab.openingAnimations$expandable_fab_release(getExpandableFabOpeningAnimationDurationMs(), getLabelVisibleToHiddenAnimationDurationMs(), new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$getOpeningAnimations$1$1
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
                this.this$0.setEfabAnimationsFinished$expandable_fab_release(true);
                this.this$0.setState(true);
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(arrayList2);
        Unit unit = Unit.INSTANCE;
        animatorArr[2] = animatorSet2;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(this.setStateAsOpened);
        return animatorSet;
    }

    private final Animator getClosingAnimations(OrientationConfiguration configuration) {
        ExpandableFab efab = configuration.getEfab();
        if (efab == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.nambimobile.widgets.efab.ExpandableFab");
        }
        List<FabOption> fabOptions = configuration.getFabOptions();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(fabOptions, 10));
        Iterator<T> it = fabOptions.iterator();
        while (it.hasNext()) {
            arrayList.add(((FabOption) it.next()).closingAnimations$expandable_fab_release(getFabOptionClosingAnimationDurationMs(), getLabelVisibleToHiddenAnimationDurationMs()));
        }
        ArrayList arrayList2 = arrayList;
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[3];
        Overlay overlay = configuration.getOverlay();
        AnimatorSet animatorSetClosingAnimations$expandable_fab_release = overlay == null ? null : overlay.closingAnimations$expandable_fab_release(getOverlayClosingAnimationDurationMs());
        if (animatorSetClosingAnimations$expandable_fab_release == null) {
            animatorSetClosingAnimations$expandable_fab_release = new AnimatorSet();
        }
        animatorArr[0] = animatorSetClosingAnimations$expandable_fab_release;
        animatorArr[1] = efab.closingAnimations$expandable_fab_release(getExpandableFabClosingAnimationDurationMs(), getLabelHiddenToVisibleAnimationDurationMs(), new Function0<Unit>() { // from class: com.nambimobile.widgets.efab.ExpandableFabLayout$getClosingAnimations$1$1
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
                this.this$0.setEfabAnimationsFinished$expandable_fab_release(true);
                this.this$0.setState(false);
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(CollectionsKt.reversed(arrayList2));
        Unit unit = Unit.INSTANCE;
        animatorArr[2] = animatorSet2;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(this.setStateAsClosed);
        return animatorSet;
    }

    private final boolean animationsFinished() {
        return this.efabAnimationsFinished && this.groupAnimationsFinished;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(boolean opened) {
        if (animationsFinished()) {
            if (opened) {
                this.open = true;
                if (this.closeWhenAble) {
                    close();
                    return;
                }
                return;
            }
            this.open = false;
            this.closeWhenAble = false;
            this.fabOptionAlreadyClicked = false;
        }
    }
}
