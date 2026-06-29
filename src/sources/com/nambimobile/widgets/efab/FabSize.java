package com.nambimobile.widgets.efab;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: FabSize.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/nambimobile/widgets/efab/FabSize;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "MINI", "NORMAL", "AUTO", "CUSTOM", "expandable-fab_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public enum FabSize {
    MINI(1),
    NORMAL(0),
    AUTO(-1),
    CUSTOM(-1234);

    private final int value;

    FabSize(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
