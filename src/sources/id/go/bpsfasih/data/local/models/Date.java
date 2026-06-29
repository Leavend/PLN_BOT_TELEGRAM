package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeriodeUpdateModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ2\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/data/local/models/Date;", "", "month", "", "year", "day", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getDay", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMonth", "getYear", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lid/go/bpsfasih/data/local/models/Date;", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Date {
    public static final int $stable = 0;

    @SerializedName("day")
    private final Integer day;

    @SerializedName("month")
    private final Integer month;

    @SerializedName("year")
    private final Integer year;

    public Date() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ Date copy$default(Date date, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = date.month;
        }
        if ((i & 2) != 0) {
            num2 = date.year;
        }
        if ((i & 4) != 0) {
            num3 = date.day;
        }
        return date.copy(num, num2, num3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getMonth() {
        return this.month;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getDay() {
        return this.day;
    }

    public final Date copy(Integer month, Integer year, Integer day) {
        return new Date(month, year, day);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Date)) {
            return false;
        }
        Date date = (Date) other;
        return Intrinsics.areEqual(this.month, date.month) && Intrinsics.areEqual(this.year, date.year) && Intrinsics.areEqual(this.day, date.day);
    }

    public int hashCode() {
        Integer num = this.month;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.year;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.day;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "Date(month=" + this.month + ", year=" + this.year + ", day=" + this.day + ")";
    }

    public Date(Integer num, Integer num2, Integer num3) {
        this.month = num;
        this.year = num2;
        this.day = num3;
    }

    public /* synthetic */ Date(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3);
    }

    public final Integer getMonth() {
        return this.month;
    }

    public final Integer getYear() {
        return this.year;
    }

    public final Integer getDay() {
        return this.day;
    }
}
