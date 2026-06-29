package com.nambimobile.widgets.efab;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: ExpandableFabLayout.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
/* synthetic */ class ExpandableFabLayout$addFabOption$fabOption$1$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    ExpandableFabLayout$addFabOption$fabOption$1$1(ExpandableFabLayout expandableFabLayout) {
        super(0, expandableFabLayout, ExpandableFabLayout.class, "defaultFabOptionOnClickBehavior", "defaultFabOptionOnClickBehavior()Z", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Boolean invoke() {
        return Boolean.valueOf(invoke2());
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final boolean invoke2() {
        return ((ExpandableFabLayout) this.receiver).defaultFabOptionOnClickBehavior();
    }
}
