package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentOfflineSamplingResult.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J±\u0001\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013¨\u00066"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentOfflineSamplingResult;", "", "preDefinedData", "", "surveyPeriodId", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", DownloadModel.ID, "codeIdentity", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCodeIdentity", "()Ljava/lang/String;", "getData1", "getData10", "getData2", "getData3", "getData4", "getData5", "getData6", "getData7", "getData8", "getData9", "getId", "getPreDefinedData", "getSurveyPeriodId", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentOfflineSamplingResult {
    public static final int $stable = 0;

    @SerializedName("code_identity")
    private final String codeIdentity;

    @SerializedName("data1")
    private final String data1;

    @SerializedName("data10")
    private final String data10;

    @SerializedName("data2")
    private final String data2;

    @SerializedName("data3")
    private final String data3;

    @SerializedName("data4")
    private final String data4;

    @SerializedName("data5")
    private final String data5;

    @SerializedName("data6")
    private final String data6;

    @SerializedName("data7")
    private final String data7;

    @SerializedName("data8")
    private final String data8;

    @SerializedName("data9")
    private final String data9;

    @SerializedName("_id")
    private final String id;

    @SerializedName("pre_defined_data")
    private final String preDefinedData;

    @SerializedName("survey_period_id")
    private final String surveyPeriodId;

    public AssignmentOfflineSamplingResult() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPreDefinedData() {
        return this.preDefinedData;
    }

    /* renamed from: component10, reason: from getter */
    public final String getData8() {
        return this.data8;
    }

    /* renamed from: component11, reason: from getter */
    public final String getData9() {
        return this.data9;
    }

    /* renamed from: component12, reason: from getter */
    public final String getData10() {
        return this.data10;
    }

    /* renamed from: component13, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component14, reason: from getter */
    public final String getCodeIdentity() {
        return this.codeIdentity;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getData1() {
        return this.data1;
    }

    /* renamed from: component4, reason: from getter */
    public final String getData2() {
        return this.data2;
    }

    /* renamed from: component5, reason: from getter */
    public final String getData3() {
        return this.data3;
    }

    /* renamed from: component6, reason: from getter */
    public final String getData4() {
        return this.data4;
    }

    /* renamed from: component7, reason: from getter */
    public final String getData5() {
        return this.data5;
    }

    /* renamed from: component8, reason: from getter */
    public final String getData6() {
        return this.data6;
    }

    /* renamed from: component9, reason: from getter */
    public final String getData7() {
        return this.data7;
    }

    public final AssignmentOfflineSamplingResult copy(String preDefinedData, String surveyPeriodId, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10, String id2, String codeIdentity) {
        return new AssignmentOfflineSamplingResult(preDefinedData, surveyPeriodId, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, id2, codeIdentity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentOfflineSamplingResult)) {
            return false;
        }
        AssignmentOfflineSamplingResult assignmentOfflineSamplingResult = (AssignmentOfflineSamplingResult) other;
        return Intrinsics.areEqual(this.preDefinedData, assignmentOfflineSamplingResult.preDefinedData) && Intrinsics.areEqual(this.surveyPeriodId, assignmentOfflineSamplingResult.surveyPeriodId) && Intrinsics.areEqual(this.data1, assignmentOfflineSamplingResult.data1) && Intrinsics.areEqual(this.data2, assignmentOfflineSamplingResult.data2) && Intrinsics.areEqual(this.data3, assignmentOfflineSamplingResult.data3) && Intrinsics.areEqual(this.data4, assignmentOfflineSamplingResult.data4) && Intrinsics.areEqual(this.data5, assignmentOfflineSamplingResult.data5) && Intrinsics.areEqual(this.data6, assignmentOfflineSamplingResult.data6) && Intrinsics.areEqual(this.data7, assignmentOfflineSamplingResult.data7) && Intrinsics.areEqual(this.data8, assignmentOfflineSamplingResult.data8) && Intrinsics.areEqual(this.data9, assignmentOfflineSamplingResult.data9) && Intrinsics.areEqual(this.data10, assignmentOfflineSamplingResult.data10) && Intrinsics.areEqual(this.id, assignmentOfflineSamplingResult.id) && Intrinsics.areEqual(this.codeIdentity, assignmentOfflineSamplingResult.codeIdentity);
    }

    public int hashCode() {
        String str = this.preDefinedData;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.surveyPeriodId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.data1;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.data2;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.data3;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.data4;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.data5;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.data6;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.data7;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.data8;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.data9;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.data10;
        int iHashCode12 = (iHashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.id;
        int iHashCode13 = (iHashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.codeIdentity;
        return iHashCode13 + (str14 != null ? str14.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentOfflineSamplingResult(preDefinedData=" + this.preDefinedData + ", surveyPeriodId=" + this.surveyPeriodId + ", data1=" + this.data1 + ", data2=" + this.data2 + ", data3=" + this.data3 + ", data4=" + this.data4 + ", data5=" + this.data5 + ", data6=" + this.data6 + ", data7=" + this.data7 + ", data8=" + this.data8 + ", data9=" + this.data9 + ", data10=" + this.data10 + ", id=" + this.id + ", codeIdentity=" + this.codeIdentity + ")";
    }

    public AssignmentOfflineSamplingResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.preDefinedData = str;
        this.surveyPeriodId = str2;
        this.data1 = str3;
        this.data2 = str4;
        this.data3 = str5;
        this.data4 = str6;
        this.data5 = str7;
        this.data6 = str8;
        this.data7 = str9;
        this.data8 = str10;
        this.data9 = str11;
        this.data10 = str12;
        this.id = str13;
        this.codeIdentity = str14;
    }

    public /* synthetic */ AssignmentOfflineSamplingResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10, (i & 1024) != 0 ? null : str11, (i & 2048) != 0 ? null : str12, (i & 4096) != 0 ? null : str13, (i & 8192) == 0 ? str14 : null);
    }

    public final String getPreDefinedData() {
        return this.preDefinedData;
    }

    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final String getData1() {
        return this.data1;
    }

    public final String getData2() {
        return this.data2;
    }

    public final String getData3() {
        return this.data3;
    }

    public final String getData4() {
        return this.data4;
    }

    public final String getData5() {
        return this.data5;
    }

    public final String getData6() {
        return this.data6;
    }

    public final String getData7() {
        return this.data7;
    }

    public final String getData8() {
        return this.data8;
    }

    public final String getData9() {
        return this.data9;
    }

    public final String getData10() {
        return this.data10;
    }

    public final String getId() {
        return this.id;
    }

    public final String getCodeIdentity() {
        return this.codeIdentity;
    }
}
