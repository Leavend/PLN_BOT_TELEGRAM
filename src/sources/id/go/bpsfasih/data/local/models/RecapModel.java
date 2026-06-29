package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecapModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ji\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006$"}, d2 = {"Lid/go/bpsfasih/data/local/models/RecapModel;", "", "jmlAssignmentAll", "", "mostActiveMonth", "survei", "jmlAssignmentBulan", DownloadModel.ID, "jmlAssignmentSurvei", "jmlSurvei", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getJmlAssignmentAll", "getJmlAssignmentBulan", "getJmlAssignmentSurvei", "getJmlSurvei", "getMostActiveMonth", "getSurvei", "getUserId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class RecapModel {
    public static final int $stable = 0;

    @SerializedName(DownloadModel.ID)
    private final String id;

    @SerializedName("jmlAssignmentAll")
    private final String jmlAssignmentAll;

    @SerializedName("jmlAssignmentBulan")
    private final String jmlAssignmentBulan;

    @SerializedName("jmlAssignmentSurvei")
    private final String jmlAssignmentSurvei;

    @SerializedName("jmlSurvei")
    private final String jmlSurvei;

    @SerializedName("mostActiveMonth")
    private final String mostActiveMonth;

    @SerializedName("survei")
    private final String survei;

    @SerializedName("userId")
    private final String userId;

    public RecapModel() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getJmlAssignmentAll() {
        return this.jmlAssignmentAll;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMostActiveMonth() {
        return this.mostActiveMonth;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSurvei() {
        return this.survei;
    }

    /* renamed from: component4, reason: from getter */
    public final String getJmlAssignmentBulan() {
        return this.jmlAssignmentBulan;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component6, reason: from getter */
    public final String getJmlAssignmentSurvei() {
        return this.jmlAssignmentSurvei;
    }

    /* renamed from: component7, reason: from getter */
    public final String getJmlSurvei() {
        return this.jmlSurvei;
    }

    /* renamed from: component8, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    public final RecapModel copy(String jmlAssignmentAll, String mostActiveMonth, String survei, String jmlAssignmentBulan, String id2, String jmlAssignmentSurvei, String jmlSurvei, String userId) {
        return new RecapModel(jmlAssignmentAll, mostActiveMonth, survei, jmlAssignmentBulan, id2, jmlAssignmentSurvei, jmlSurvei, userId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecapModel)) {
            return false;
        }
        RecapModel recapModel = (RecapModel) other;
        return Intrinsics.areEqual(this.jmlAssignmentAll, recapModel.jmlAssignmentAll) && Intrinsics.areEqual(this.mostActiveMonth, recapModel.mostActiveMonth) && Intrinsics.areEqual(this.survei, recapModel.survei) && Intrinsics.areEqual(this.jmlAssignmentBulan, recapModel.jmlAssignmentBulan) && Intrinsics.areEqual(this.id, recapModel.id) && Intrinsics.areEqual(this.jmlAssignmentSurvei, recapModel.jmlAssignmentSurvei) && Intrinsics.areEqual(this.jmlSurvei, recapModel.jmlSurvei) && Intrinsics.areEqual(this.userId, recapModel.userId);
    }

    public int hashCode() {
        String str = this.jmlAssignmentAll;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.mostActiveMonth;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.survei;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.jmlAssignmentBulan;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.id;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.jmlAssignmentSurvei;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.jmlSurvei;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.userId;
        return iHashCode7 + (str8 != null ? str8.hashCode() : 0);
    }

    public String toString() {
        return "RecapModel(jmlAssignmentAll=" + this.jmlAssignmentAll + ", mostActiveMonth=" + this.mostActiveMonth + ", survei=" + this.survei + ", jmlAssignmentBulan=" + this.jmlAssignmentBulan + ", id=" + this.id + ", jmlAssignmentSurvei=" + this.jmlAssignmentSurvei + ", jmlSurvei=" + this.jmlSurvei + ", userId=" + this.userId + ")";
    }

    public RecapModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.jmlAssignmentAll = str;
        this.mostActiveMonth = str2;
        this.survei = str3;
        this.jmlAssignmentBulan = str4;
        this.id = str5;
        this.jmlAssignmentSurvei = str6;
        this.jmlSurvei = str7;
        this.userId = str8;
    }

    public /* synthetic */ RecapModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) == 0 ? str8 : null);
    }

    public final String getJmlAssignmentAll() {
        return this.jmlAssignmentAll;
    }

    public final String getMostActiveMonth() {
        return this.mostActiveMonth;
    }

    public final String getSurvei() {
        return this.survei;
    }

    public final String getJmlAssignmentBulan() {
        return this.jmlAssignmentBulan;
    }

    public final String getId() {
        return this.id;
    }

    public final String getJmlAssignmentSurvei() {
        return this.jmlAssignmentSurvei;
    }

    public final String getJmlSurvei() {
        return this.jmlSurvei;
    }

    public final String getUserId() {
        return this.userId;
    }
}
