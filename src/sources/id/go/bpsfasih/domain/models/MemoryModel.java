package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: MemoryModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/domain/models/MemoryModel;", "", "available", "", "total", "usage", "(JJJ)V", "getAvailable", "()J", "setAvailable", "(J)V", "getTotal", "setTotal", "getUsage", "setUsage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class MemoryModel {
    public static final int $stable = 8;
    private long available;
    private long total;
    private long usage;

    public MemoryModel() {
        this(0L, 0L, 0L, 7, null);
    }

    public static /* synthetic */ MemoryModel copy$default(MemoryModel memoryModel, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = memoryModel.available;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = memoryModel.total;
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = memoryModel.usage;
        }
        return memoryModel.copy(j4, j5, j3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getAvailable() {
        return this.available;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTotal() {
        return this.total;
    }

    /* renamed from: component3, reason: from getter */
    public final long getUsage() {
        return this.usage;
    }

    public final MemoryModel copy(long available, long total, long usage) {
        return new MemoryModel(available, total, usage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryModel)) {
            return false;
        }
        MemoryModel memoryModel = (MemoryModel) other;
        return this.available == memoryModel.available && this.total == memoryModel.total && this.usage == memoryModel.usage;
    }

    public int hashCode() {
        return (((Long.hashCode(this.available) * 31) + Long.hashCode(this.total)) * 31) + Long.hashCode(this.usage);
    }

    public String toString() {
        return "MemoryModel(available=" + this.available + ", total=" + this.total + ", usage=" + this.usage + ")";
    }

    public MemoryModel(long j, long j2, long j3) {
        this.available = j;
        this.total = j2;
        this.usage = j3;
    }

    public /* synthetic */ MemoryModel(long j, long j2, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? 0L : j2, (i & 4) != 0 ? 0L : j3);
    }

    public final long getAvailable() {
        return this.available;
    }

    public final void setAvailable(long j) {
        this.available = j;
    }

    public final long getTotal() {
        return this.total;
    }

    public final void setTotal(long j) {
        this.total = j;
    }

    public final long getUsage() {
        return this.usage;
    }

    public final void setUsage(long j) {
        this.usage = j;
    }
}
