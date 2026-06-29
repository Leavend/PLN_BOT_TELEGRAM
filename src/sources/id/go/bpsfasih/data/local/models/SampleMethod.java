package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllSurveyModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010=\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u009e\u0001\u0010>\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010?J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020\u0007HÖ\u0001J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010\u001aR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010\u0015R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0018\"\u0004\b-\u0010\u001aR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u0010\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0018\"\u0004\b1\u0010\u001a¨\u0006E"}, d2 = {"Lid/go/bpsfasih/data/local/models/SampleMethod;", "", DownloadModel.ID, "", "name", "userId", "primaryCount", "", "criteriaWhere", "criteriaOrderBy", FirebaseAnalytics.Param.METHOD, "methodType", "countType", "criteriaWhereMobile", "criteriaOrderByMobile", "dateCreated", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getCountType", "()Ljava/lang/Integer;", "setCountType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCriteriaOrderBy", "()Ljava/lang/String;", "setCriteriaOrderBy", "(Ljava/lang/String;)V", "getCriteriaOrderByMobile", "setCriteriaOrderByMobile", "getCriteriaWhere", "setCriteriaWhere", "getCriteriaWhereMobile", "setCriteriaWhereMobile", "getDateCreated", "()Ljava/lang/Long;", "setDateCreated", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getId", "setId", "getMethod", "setMethod", "getMethodType", "setMethodType", "getName", "setName", "getPrimaryCount", "setPrimaryCount", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lid/go/bpsfasih/data/local/models/SampleMethod;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SampleMethod {
    public static final int $stable = 8;
    private Integer countType;
    private String criteriaOrderBy;
    private String criteriaOrderByMobile;
    private String criteriaWhere;
    private String criteriaWhereMobile;
    private Long dateCreated;
    private String id;
    private String method;
    private Integer methodType;
    private String name;
    private Integer primaryCount;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getCriteriaWhereMobile() {
        return this.criteriaWhereMobile;
    }

    /* renamed from: component11, reason: from getter */
    public final String getCriteriaOrderByMobile() {
        return this.criteriaOrderByMobile;
    }

    /* renamed from: component12, reason: from getter */
    public final Long getDateCreated() {
        return this.dateCreated;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getPrimaryCount() {
        return this.primaryCount;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCriteriaWhere() {
        return this.criteriaWhere;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCriteriaOrderBy() {
        return this.criteriaOrderBy;
    }

    /* renamed from: component7, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getMethodType() {
        return this.methodType;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getCountType() {
        return this.countType;
    }

    public final SampleMethod copy(String id2, String name, String userId, Integer primaryCount, String criteriaWhere, String criteriaOrderBy, String method, Integer methodType, Integer countType, String criteriaWhereMobile, String criteriaOrderByMobile, Long dateCreated) {
        return new SampleMethod(id2, name, userId, primaryCount, criteriaWhere, criteriaOrderBy, method, methodType, countType, criteriaWhereMobile, criteriaOrderByMobile, dateCreated);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SampleMethod)) {
            return false;
        }
        SampleMethod sampleMethod = (SampleMethod) other;
        return Intrinsics.areEqual(this.id, sampleMethod.id) && Intrinsics.areEqual(this.name, sampleMethod.name) && Intrinsics.areEqual(this.userId, sampleMethod.userId) && Intrinsics.areEqual(this.primaryCount, sampleMethod.primaryCount) && Intrinsics.areEqual(this.criteriaWhere, sampleMethod.criteriaWhere) && Intrinsics.areEqual(this.criteriaOrderBy, sampleMethod.criteriaOrderBy) && Intrinsics.areEqual(this.method, sampleMethod.method) && Intrinsics.areEqual(this.methodType, sampleMethod.methodType) && Intrinsics.areEqual(this.countType, sampleMethod.countType) && Intrinsics.areEqual(this.criteriaWhereMobile, sampleMethod.criteriaWhereMobile) && Intrinsics.areEqual(this.criteriaOrderByMobile, sampleMethod.criteriaOrderByMobile) && Intrinsics.areEqual(this.dateCreated, sampleMethod.dateCreated);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.userId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.primaryCount;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.criteriaWhere;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.criteriaOrderBy;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.method;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num2 = this.methodType;
        int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.countType;
        int iHashCode9 = (iHashCode8 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str7 = this.criteriaWhereMobile;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.criteriaOrderByMobile;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Long l = this.dateCreated;
        return iHashCode11 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "SampleMethod(id=" + this.id + ", name=" + this.name + ", userId=" + this.userId + ", primaryCount=" + this.primaryCount + ", criteriaWhere=" + this.criteriaWhere + ", criteriaOrderBy=" + this.criteriaOrderBy + ", method=" + this.method + ", methodType=" + this.methodType + ", countType=" + this.countType + ", criteriaWhereMobile=" + this.criteriaWhereMobile + ", criteriaOrderByMobile=" + this.criteriaOrderByMobile + ", dateCreated=" + this.dateCreated + ")";
    }

    public SampleMethod(String str, String str2, String str3, Integer num, String str4, String str5, String str6, Integer num2, Integer num3, String str7, String str8, Long l) {
        this.id = str;
        this.name = str2;
        this.userId = str3;
        this.primaryCount = num;
        this.criteriaWhere = str4;
        this.criteriaOrderBy = str5;
        this.method = str6;
        this.methodType = num2;
        this.countType = num3;
        this.criteriaWhereMobile = str7;
        this.criteriaOrderByMobile = str8;
        this.dateCreated = l;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final Integer getPrimaryCount() {
        return this.primaryCount;
    }

    public final void setPrimaryCount(Integer num) {
        this.primaryCount = num;
    }

    public final String getCriteriaWhere() {
        return this.criteriaWhere;
    }

    public final void setCriteriaWhere(String str) {
        this.criteriaWhere = str;
    }

    public final String getCriteriaOrderBy() {
        return this.criteriaOrderBy;
    }

    public final void setCriteriaOrderBy(String str) {
        this.criteriaOrderBy = str;
    }

    public final String getMethod() {
        return this.method;
    }

    public final void setMethod(String str) {
        this.method = str;
    }

    public final Integer getMethodType() {
        return this.methodType;
    }

    public final void setMethodType(Integer num) {
        this.methodType = num;
    }

    public final Integer getCountType() {
        return this.countType;
    }

    public final void setCountType(Integer num) {
        this.countType = num;
    }

    public final String getCriteriaWhereMobile() {
        return this.criteriaWhereMobile;
    }

    public final void setCriteriaWhereMobile(String str) {
        this.criteriaWhereMobile = str;
    }

    public final String getCriteriaOrderByMobile() {
        return this.criteriaOrderByMobile;
    }

    public final void setCriteriaOrderByMobile(String str) {
        this.criteriaOrderByMobile = str;
    }

    public final Long getDateCreated() {
        return this.dateCreated;
    }

    public final void setDateCreated(Long l) {
        this.dateCreated = l;
    }
}
