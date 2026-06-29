package com.dewakoding.androiddatatable.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrderBy.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/dewakoding/androiddatatable/data/OrderBy;", "", "columnId", "", "type", "", "(ILjava/lang/String;)V", "getColumnId", "()I", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "androiddatatable_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final /* data */ class OrderBy {
    private final int columnId;
    private final String type;

    public static /* synthetic */ OrderBy copy$default(OrderBy orderBy, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = orderBy.columnId;
        }
        if ((i2 & 2) != 0) {
            str = orderBy.type;
        }
        return orderBy.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getColumnId() {
        return this.columnId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final OrderBy copy(int columnId, String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new OrderBy(columnId, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderBy)) {
            return false;
        }
        OrderBy orderBy = (OrderBy) other;
        return this.columnId == orderBy.columnId && Intrinsics.areEqual(this.type, orderBy.type);
    }

    public int hashCode() {
        return (Integer.hashCode(this.columnId) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "OrderBy(columnId=" + this.columnId + ", type=" + this.type + ')';
    }

    public OrderBy(int i, String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.columnId = i;
        this.type = type;
    }

    public final int getColumnId() {
        return this.columnId;
    }

    public final String getType() {
        return this.type;
    }
}
