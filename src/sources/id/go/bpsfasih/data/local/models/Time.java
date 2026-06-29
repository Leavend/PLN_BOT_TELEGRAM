package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeriodeUpdateModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ>\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\r\u0010\t¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/data/local/models/Time;", "", "hour", "", "nano", "minute", "second", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getHour", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMinute", "getNano", "getSecond", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lid/go/bpsfasih/data/local/models/Time;", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Time {
    public static final int $stable = 0;

    @SerializedName("hour")
    private final Integer hour;

    @SerializedName("minute")
    private final Integer minute;

    @SerializedName("nano")
    private final Integer nano;

    @SerializedName("second")
    private final Integer second;

    public Time() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ Time copy$default(Time time, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = time.hour;
        }
        if ((i & 2) != 0) {
            num2 = time.nano;
        }
        if ((i & 4) != 0) {
            num3 = time.minute;
        }
        if ((i & 8) != 0) {
            num4 = time.second;
        }
        return time.copy(num, num2, num3, num4);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getHour() {
        return this.hour;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getNano() {
        return this.nano;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getMinute() {
        return this.minute;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getSecond() {
        return this.second;
    }

    public final Time copy(Integer hour, Integer nano, Integer minute, Integer second) {
        return new Time(hour, nano, minute, second);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Time)) {
            return false;
        }
        Time time = (Time) other;
        return Intrinsics.areEqual(this.hour, time.hour) && Intrinsics.areEqual(this.nano, time.nano) && Intrinsics.areEqual(this.minute, time.minute) && Intrinsics.areEqual(this.second, time.second);
    }

    public int hashCode() {
        Integer num = this.hour;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.nano;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.minute;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.second;
        return iHashCode3 + (num4 != null ? num4.hashCode() : 0);
    }

    public String toString() {
        return "Time(hour=" + this.hour + ", nano=" + this.nano + ", minute=" + this.minute + ", second=" + this.second + ")";
    }

    public Time(Integer num, Integer num2, Integer num3, Integer num4) {
        this.hour = num;
        this.nano = num2;
        this.minute = num3;
        this.second = num4;
    }

    public /* synthetic */ Time(Integer num, Integer num2, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4);
    }

    public final Integer getHour() {
        return this.hour;
    }

    public final Integer getNano() {
        return this.nano;
    }

    public final Integer getMinute() {
        return this.minute;
    }

    public final Integer getSecond() {
        return this.second;
    }
}
