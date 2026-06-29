package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomColumnEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b9\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007¢\u0006\u0002\u0010\u0011J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0007HÆ\u0003J\t\u0010<\u001a\u00020\u0007HÆ\u0003J\u008b\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u0007HÆ\u0001J\u0013\u0010>\u001a\u00020\u00072\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020AHÖ\u0001J\t\u0010B\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0010\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0015R\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+¨\u0006C"}, d2 = {"Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "", "idPrimary", "", DownloadModel.ID, "userId", "data1", "", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZZZZZZZ)V", "getData1", "()Z", "setData1", "(Z)V", "getData10", "setData10", "getData2", "setData2", "getData3", "setData3", "getData4", "setData4", "getData5", "setData5", "getData6", "setData6", "getData7", "setData7", "getData8", "setData8", "getData9", "setData9", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getIdPrimary", "setIdPrimary", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class CustomColumnEntity {
    public static final int $stable = 8;
    private boolean data1;
    private boolean data10;
    private boolean data2;
    private boolean data3;
    private boolean data4;
    private boolean data5;
    private boolean data6;
    private boolean data7;
    private boolean data8;
    private boolean data9;
    private String id;
    private String idPrimary;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getIdPrimary() {
        return this.idPrimary;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getData7() {
        return this.data7;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getData8() {
        return this.data8;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getData9() {
        return this.data9;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getData10() {
        return this.data10;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getData1() {
        return this.data1;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getData2() {
        return this.data2;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getData3() {
        return this.data3;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getData4() {
        return this.data4;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getData5() {
        return this.data5;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getData6() {
        return this.data6;
    }

    public final CustomColumnEntity copy(String idPrimary, String id2, String userId, boolean data1, boolean data2, boolean data3, boolean data4, boolean data5, boolean data6, boolean data7, boolean data8, boolean data9, boolean data10) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new CustomColumnEntity(idPrimary, id2, userId, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomColumnEntity)) {
            return false;
        }
        CustomColumnEntity customColumnEntity = (CustomColumnEntity) other;
        return Intrinsics.areEqual(this.idPrimary, customColumnEntity.idPrimary) && Intrinsics.areEqual(this.id, customColumnEntity.id) && Intrinsics.areEqual(this.userId, customColumnEntity.userId) && this.data1 == customColumnEntity.data1 && this.data2 == customColumnEntity.data2 && this.data3 == customColumnEntity.data3 && this.data4 == customColumnEntity.data4 && this.data5 == customColumnEntity.data5 && this.data6 == customColumnEntity.data6 && this.data7 == customColumnEntity.data7 && this.data8 == customColumnEntity.data8 && this.data9 == customColumnEntity.data9 && this.data10 == customColumnEntity.data10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((this.idPrimary.hashCode() * 31) + this.id.hashCode()) * 31) + this.userId.hashCode()) * 31;
        boolean z = this.data1;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.data2;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.data3;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        boolean z4 = this.data4;
        int i7 = z4;
        if (z4 != 0) {
            i7 = 1;
        }
        int i8 = (i6 + i7) * 31;
        boolean z5 = this.data5;
        int i9 = z5;
        if (z5 != 0) {
            i9 = 1;
        }
        int i10 = (i8 + i9) * 31;
        boolean z6 = this.data6;
        int i11 = z6;
        if (z6 != 0) {
            i11 = 1;
        }
        int i12 = (i10 + i11) * 31;
        boolean z7 = this.data7;
        int i13 = z7;
        if (z7 != 0) {
            i13 = 1;
        }
        int i14 = (i12 + i13) * 31;
        boolean z8 = this.data8;
        int i15 = z8;
        if (z8 != 0) {
            i15 = 1;
        }
        int i16 = (i14 + i15) * 31;
        boolean z9 = this.data9;
        int i17 = z9;
        if (z9 != 0) {
            i17 = 1;
        }
        int i18 = (i16 + i17) * 31;
        boolean z10 = this.data10;
        return i18 + (z10 ? 1 : z10 ? 1 : 0);
    }

    public String toString() {
        return "CustomColumnEntity(idPrimary=" + this.idPrimary + ", id=" + this.id + ", userId=" + this.userId + ", data1=" + this.data1 + ", data2=" + this.data2 + ", data3=" + this.data3 + ", data4=" + this.data4 + ", data5=" + this.data5 + ", data6=" + this.data6 + ", data7=" + this.data7 + ", data8=" + this.data8 + ", data9=" + this.data9 + ", data10=" + this.data10 + ")";
    }

    public CustomColumnEntity(String idPrimary, String id2, String userId, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.idPrimary = idPrimary;
        this.id = id2;
        this.userId = userId;
        this.data1 = z;
        this.data2 = z2;
        this.data3 = z3;
        this.data4 = z4;
        this.data5 = z5;
        this.data6 = z6;
        this.data7 = z7;
        this.data8 = z8;
        this.data9 = z9;
        this.data10 = z10;
    }

    public /* synthetic */ CustomColumnEntity(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? true : z, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? true : z3, (i & 64) != 0 ? true : z4, (i & 128) != 0 ? true : z5, (i & 256) != 0 ? true : z6, (i & 512) != 0 ? true : z7, (i & 1024) != 0 ? true : z8, (i & 2048) != 0 ? true : z9, (i & 4096) != 0 ? true : z10);
    }

    public final String getIdPrimary() {
        return this.idPrimary;
    }

    public final void setIdPrimary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.idPrimary = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final boolean getData1() {
        return this.data1;
    }

    public final void setData1(boolean z) {
        this.data1 = z;
    }

    public final boolean getData2() {
        return this.data2;
    }

    public final void setData2(boolean z) {
        this.data2 = z;
    }

    public final boolean getData3() {
        return this.data3;
    }

    public final void setData3(boolean z) {
        this.data3 = z;
    }

    public final boolean getData4() {
        return this.data4;
    }

    public final void setData4(boolean z) {
        this.data4 = z;
    }

    public final boolean getData5() {
        return this.data5;
    }

    public final void setData5(boolean z) {
        this.data5 = z;
    }

    public final boolean getData6() {
        return this.data6;
    }

    public final void setData6(boolean z) {
        this.data6 = z;
    }

    public final boolean getData7() {
        return this.data7;
    }

    public final void setData7(boolean z) {
        this.data7 = z;
    }

    public final boolean getData8() {
        return this.data8;
    }

    public final void setData8(boolean z) {
        this.data8 = z;
    }

    public final boolean getData9() {
        return this.data9;
    }

    public final void setData9(boolean z) {
        this.data9 = z;
    }

    public final boolean getData10() {
        return this.data10;
    }

    public final void setData10(boolean z) {
        this.data10 = z;
    }
}
