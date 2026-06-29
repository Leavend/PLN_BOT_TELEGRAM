package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomDataTemplateEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0011J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u009f\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020BHÖ\u0001J\t\u0010C\u001a\u00020\u0003HÖ\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R \u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R \u0010\n\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R \u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R \u0010\f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R \u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R \u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0015R \u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+¨\u0006D"}, d2 = {"Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "", "idPrimary", "", DownloadModel.ID, "userId", "data1", "Lid/go/bpsfasih/data/local/entities/Data;", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;Lid/go/bpsfasih/data/local/entities/Data;)V", "getData1", "()Lid/go/bpsfasih/data/local/entities/Data;", "setData1", "(Lid/go/bpsfasih/data/local/entities/Data;)V", "getData10", "setData10", "getData2", "setData2", "getData3", "setData3", "getData4", "setData4", "getData5", "setData5", "getData6", "setData6", "getData7", "setData7", "getData8", "setData8", "getData9", "setData9", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getIdPrimary", "setIdPrimary", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class CustomDataTemplateEntity {
    public static final int $stable = 8;
    private Data data1;
    private Data data10;
    private Data data2;
    private Data data3;
    private Data data4;
    private Data data5;
    private Data data6;
    private Data data7;
    private Data data8;
    private Data data9;
    private String id;
    private String idPrimary;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getIdPrimary() {
        return this.idPrimary;
    }

    /* renamed from: component10, reason: from getter */
    public final Data getData7() {
        return this.data7;
    }

    /* renamed from: component11, reason: from getter */
    public final Data getData8() {
        return this.data8;
    }

    /* renamed from: component12, reason: from getter */
    public final Data getData9() {
        return this.data9;
    }

    /* renamed from: component13, reason: from getter */
    public final Data getData10() {
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
    public final Data getData1() {
        return this.data1;
    }

    /* renamed from: component5, reason: from getter */
    public final Data getData2() {
        return this.data2;
    }

    /* renamed from: component6, reason: from getter */
    public final Data getData3() {
        return this.data3;
    }

    /* renamed from: component7, reason: from getter */
    public final Data getData4() {
        return this.data4;
    }

    /* renamed from: component8, reason: from getter */
    public final Data getData5() {
        return this.data5;
    }

    /* renamed from: component9, reason: from getter */
    public final Data getData6() {
        return this.data6;
    }

    public final CustomDataTemplateEntity copy(String idPrimary, String id2, String userId, Data data1, Data data2, Data data3, Data data4, Data data5, Data data6, Data data7, Data data8, Data data9, Data data10) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new CustomDataTemplateEntity(idPrimary, id2, userId, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomDataTemplateEntity)) {
            return false;
        }
        CustomDataTemplateEntity customDataTemplateEntity = (CustomDataTemplateEntity) other;
        return Intrinsics.areEqual(this.idPrimary, customDataTemplateEntity.idPrimary) && Intrinsics.areEqual(this.id, customDataTemplateEntity.id) && Intrinsics.areEqual(this.userId, customDataTemplateEntity.userId) && Intrinsics.areEqual(this.data1, customDataTemplateEntity.data1) && Intrinsics.areEqual(this.data2, customDataTemplateEntity.data2) && Intrinsics.areEqual(this.data3, customDataTemplateEntity.data3) && Intrinsics.areEqual(this.data4, customDataTemplateEntity.data4) && Intrinsics.areEqual(this.data5, customDataTemplateEntity.data5) && Intrinsics.areEqual(this.data6, customDataTemplateEntity.data6) && Intrinsics.areEqual(this.data7, customDataTemplateEntity.data7) && Intrinsics.areEqual(this.data8, customDataTemplateEntity.data8) && Intrinsics.areEqual(this.data9, customDataTemplateEntity.data9) && Intrinsics.areEqual(this.data10, customDataTemplateEntity.data10);
    }

    public int hashCode() {
        int iHashCode = ((((this.idPrimary.hashCode() * 31) + this.id.hashCode()) * 31) + this.userId.hashCode()) * 31;
        Data data = this.data1;
        int iHashCode2 = (iHashCode + (data == null ? 0 : data.hashCode())) * 31;
        Data data2 = this.data2;
        int iHashCode3 = (iHashCode2 + (data2 == null ? 0 : data2.hashCode())) * 31;
        Data data3 = this.data3;
        int iHashCode4 = (iHashCode3 + (data3 == null ? 0 : data3.hashCode())) * 31;
        Data data4 = this.data4;
        int iHashCode5 = (iHashCode4 + (data4 == null ? 0 : data4.hashCode())) * 31;
        Data data5 = this.data5;
        int iHashCode6 = (iHashCode5 + (data5 == null ? 0 : data5.hashCode())) * 31;
        Data data6 = this.data6;
        int iHashCode7 = (iHashCode6 + (data6 == null ? 0 : data6.hashCode())) * 31;
        Data data7 = this.data7;
        int iHashCode8 = (iHashCode7 + (data7 == null ? 0 : data7.hashCode())) * 31;
        Data data8 = this.data8;
        int iHashCode9 = (iHashCode8 + (data8 == null ? 0 : data8.hashCode())) * 31;
        Data data9 = this.data9;
        int iHashCode10 = (iHashCode9 + (data9 == null ? 0 : data9.hashCode())) * 31;
        Data data10 = this.data10;
        return iHashCode10 + (data10 != null ? data10.hashCode() : 0);
    }

    public String toString() {
        return "CustomDataTemplateEntity(idPrimary=" + this.idPrimary + ", id=" + this.id + ", userId=" + this.userId + ", data1=" + this.data1 + ", data2=" + this.data2 + ", data3=" + this.data3 + ", data4=" + this.data4 + ", data5=" + this.data5 + ", data6=" + this.data6 + ", data7=" + this.data7 + ", data8=" + this.data8 + ", data9=" + this.data9 + ", data10=" + this.data10 + ")";
    }

    public CustomDataTemplateEntity(String idPrimary, String id2, String userId, Data data, Data data2, Data data3, Data data4, Data data5, Data data6, Data data7, Data data8, Data data9, Data data10) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.idPrimary = idPrimary;
        this.id = id2;
        this.userId = userId;
        this.data1 = data;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;
        this.data9 = data9;
        this.data10 = data10;
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

    public final Data getData1() {
        return this.data1;
    }

    public final void setData1(Data data) {
        this.data1 = data;
    }

    public final Data getData2() {
        return this.data2;
    }

    public final void setData2(Data data) {
        this.data2 = data;
    }

    public final Data getData3() {
        return this.data3;
    }

    public final void setData3(Data data) {
        this.data3 = data;
    }

    public final Data getData4() {
        return this.data4;
    }

    public final void setData4(Data data) {
        this.data4 = data;
    }

    public final Data getData5() {
        return this.data5;
    }

    public final void setData5(Data data) {
        this.data5 = data;
    }

    public final Data getData6() {
        return this.data6;
    }

    public final void setData6(Data data) {
        this.data6 = data;
    }

    public final Data getData7() {
        return this.data7;
    }

    public final void setData7(Data data) {
        this.data7 = data;
    }

    public final Data getData8() {
        return this.data8;
    }

    public final void setData8(Data data) {
        this.data8 = data;
    }

    public final Data getData9() {
        return this.data9;
    }

    public final void setData9(Data data) {
        this.data9 = data;
    }

    public final Data getData10() {
        return this.data10;
    }

    public final void setData10(Data data) {
        this.data10 = data;
    }
}
