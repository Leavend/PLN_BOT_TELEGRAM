package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BatteryInfo.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001aHÖ\u0001R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/domain/models/BatteryInfo;", "", "batteryLevel", "", "batteryTemperature", "", "(Ljava/lang/Integer;Ljava/lang/Float;)V", "getBatteryLevel", "()Ljava/lang/Integer;", "setBatteryLevel", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBatteryTemperature", "()Ljava/lang/Float;", "setBatteryTemperature", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/Float;)Lid/go/bpsfasih/domain/models/BatteryInfo;", "equals", "", "other", "hashCode", "toJsonString", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class BatteryInfo {
    public static final int $stable = 8;
    private Integer batteryLevel;
    private Float batteryTemperature;

    /* JADX WARN: Multi-variable type inference failed */
    public BatteryInfo() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ BatteryInfo copy$default(BatteryInfo batteryInfo, Integer num, Float f, int i, Object obj) {
        if ((i & 1) != 0) {
            num = batteryInfo.batteryLevel;
        }
        if ((i & 2) != 0) {
            f = batteryInfo.batteryTemperature;
        }
        return batteryInfo.copy(num, f);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getBatteryLevel() {
        return this.batteryLevel;
    }

    /* renamed from: component2, reason: from getter */
    public final Float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public final BatteryInfo copy(Integer batteryLevel, Float batteryTemperature) {
        return new BatteryInfo(batteryLevel, batteryTemperature);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BatteryInfo)) {
            return false;
        }
        BatteryInfo batteryInfo = (BatteryInfo) other;
        return Intrinsics.areEqual(this.batteryLevel, batteryInfo.batteryLevel) && Intrinsics.areEqual((Object) this.batteryTemperature, (Object) batteryInfo.batteryTemperature);
    }

    public int hashCode() {
        Integer num = this.batteryLevel;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Float f = this.batteryTemperature;
        return iHashCode + (f != null ? f.hashCode() : 0);
    }

    public String toString() {
        return "BatteryInfo(batteryLevel=" + this.batteryLevel + ", batteryTemperature=" + this.batteryTemperature + ")";
    }

    public BatteryInfo(Integer num, Float f) {
        this.batteryLevel = num;
        this.batteryTemperature = f;
    }

    public /* synthetic */ BatteryInfo(Integer num, Float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : f);
    }

    public final Integer getBatteryLevel() {
        return this.batteryLevel;
    }

    public final void setBatteryLevel(Integer num) {
        this.batteryLevel = num;
    }

    public final Float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public final void setBatteryTemperature(Float f) {
        this.batteryTemperature = f;
    }

    public final String toJsonString() {
        return StringsKt.trimIndent("\n            {\n                \"batteryLevel\": " + this.batteryLevel + ",\n                \"batteryTemperature\": " + this.batteryTemperature + ",\n            }\n        ");
    }
}
