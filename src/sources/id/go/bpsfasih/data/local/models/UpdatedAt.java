package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeriodeUpdateModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/UpdatedAt;", "", "date", "Lid/go/bpsfasih/data/local/models/Date;", "time", "Lid/go/bpsfasih/data/local/models/Time;", "(Lid/go/bpsfasih/data/local/models/Date;Lid/go/bpsfasih/data/local/models/Time;)V", "getDate", "()Lid/go/bpsfasih/data/local/models/Date;", "getTime", "()Lid/go/bpsfasih/data/local/models/Time;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UpdatedAt {
    public static final int $stable = 0;

    @SerializedName("date")
    private final Date date;

    @SerializedName("time")
    private final Time time;

    /* JADX WARN: Multi-variable type inference failed */
    public UpdatedAt() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ UpdatedAt copy$default(UpdatedAt updatedAt, Date date, Time time, int i, Object obj) {
        if ((i & 1) != 0) {
            date = updatedAt.date;
        }
        if ((i & 2) != 0) {
            time = updatedAt.time;
        }
        return updatedAt.copy(date, time);
    }

    /* renamed from: component1, reason: from getter */
    public final Date getDate() {
        return this.date;
    }

    /* renamed from: component2, reason: from getter */
    public final Time getTime() {
        return this.time;
    }

    public final UpdatedAt copy(Date date, Time time) {
        return new UpdatedAt(date, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdatedAt)) {
            return false;
        }
        UpdatedAt updatedAt = (UpdatedAt) other;
        return Intrinsics.areEqual(this.date, updatedAt.date) && Intrinsics.areEqual(this.time, updatedAt.time);
    }

    public int hashCode() {
        Date date = this.date;
        int iHashCode = (date == null ? 0 : date.hashCode()) * 31;
        Time time = this.time;
        return iHashCode + (time != null ? time.hashCode() : 0);
    }

    public String toString() {
        return "UpdatedAt(date=" + this.date + ", time=" + this.time + ")";
    }

    public UpdatedAt(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public /* synthetic */ UpdatedAt(Date date, Time time, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : date, (i & 2) != 0 ? null : time);
    }

    public final Date getDate() {
        return this.date;
    }

    public final Time getTime() {
        return this.time;
    }
}
