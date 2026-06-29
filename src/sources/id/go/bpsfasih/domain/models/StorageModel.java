package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: StorageModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006&"}, d2 = {"Lid/go/bpsfasih/domain/models/StorageModel;", "", "available", "", "total", "usage", "survey", "pencacahan", "area", "(JJJJJJ)V", "getArea", "()J", "setArea", "(J)V", "getAvailable", "setAvailable", "getPencacahan", "setPencacahan", "getSurvey", "setSurvey", "getTotal", "setTotal", "getUsage", "setUsage", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class StorageModel {
    public static final int $stable = 8;
    private long area;
    private long available;
    private long pencacahan;
    private long survey;
    private long total;
    private long usage;

    public StorageModel() {
        this(0L, 0L, 0L, 0L, 0L, 0L, 63, null);
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

    /* renamed from: component4, reason: from getter */
    public final long getSurvey() {
        return this.survey;
    }

    /* renamed from: component5, reason: from getter */
    public final long getPencacahan() {
        return this.pencacahan;
    }

    /* renamed from: component6, reason: from getter */
    public final long getArea() {
        return this.area;
    }

    public final StorageModel copy(long available, long total, long usage, long survey, long pencacahan, long area) {
        return new StorageModel(available, total, usage, survey, pencacahan, area);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StorageModel)) {
            return false;
        }
        StorageModel storageModel = (StorageModel) other;
        return this.available == storageModel.available && this.total == storageModel.total && this.usage == storageModel.usage && this.survey == storageModel.survey && this.pencacahan == storageModel.pencacahan && this.area == storageModel.area;
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.available) * 31) + Long.hashCode(this.total)) * 31) + Long.hashCode(this.usage)) * 31) + Long.hashCode(this.survey)) * 31) + Long.hashCode(this.pencacahan)) * 31) + Long.hashCode(this.area);
    }

    public String toString() {
        return "StorageModel(available=" + this.available + ", total=" + this.total + ", usage=" + this.usage + ", survey=" + this.survey + ", pencacahan=" + this.pencacahan + ", area=" + this.area + ")";
    }

    public StorageModel(long j, long j2, long j3, long j4, long j5, long j6) {
        this.available = j;
        this.total = j2;
        this.usage = j3;
        this.survey = j4;
        this.pencacahan = j5;
        this.area = j6;
    }

    public /* synthetic */ StorageModel(long j, long j2, long j3, long j4, long j5, long j6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? 0L : j2, (i & 4) != 0 ? 0L : j3, (i & 8) != 0 ? 0L : j4, (i & 16) != 0 ? 0L : j5, (i & 32) == 0 ? j6 : 0L);
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

    public final long getSurvey() {
        return this.survey;
    }

    public final void setSurvey(long j) {
        this.survey = j;
    }

    public final long getPencacahan() {
        return this.pencacahan;
    }

    public final void setPencacahan(long j) {
        this.pencacahan = j;
    }

    public final long getArea() {
        return this.area;
    }

    public final void setArea(long j) {
        this.area = j;
    }
}
