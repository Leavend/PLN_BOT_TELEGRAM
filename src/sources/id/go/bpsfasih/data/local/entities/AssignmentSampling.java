package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentSampling;", "", "_id", "", "survey_period_id", "pre_defined_data", "data", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "get_id", "()Ljava/lang/String;", "set_id", "(Ljava/lang/String;)V", "getData", "setData", "getPre_defined_data", "setPre_defined_data", "getSurvey_period_id", "setSurvey_period_id", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentSampling {
    public static final int $stable = 8;
    private String _id;
    private String data;
    private String pre_defined_data;
    private String survey_period_id;

    public static /* synthetic */ AssignmentSampling copy$default(AssignmentSampling assignmentSampling, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assignmentSampling._id;
        }
        if ((i & 2) != 0) {
            str2 = assignmentSampling.survey_period_id;
        }
        if ((i & 4) != 0) {
            str3 = assignmentSampling.pre_defined_data;
        }
        if ((i & 8) != 0) {
            str4 = assignmentSampling.data;
        }
        return assignmentSampling.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String get_id() {
        return this._id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSurvey_period_id() {
        return this.survey_period_id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPre_defined_data() {
        return this.pre_defined_data;
    }

    /* renamed from: component4, reason: from getter */
    public final String getData() {
        return this.data;
    }

    public final AssignmentSampling copy(String _id, String survey_period_id, String pre_defined_data, String data) {
        Intrinsics.checkNotNullParameter(_id, "_id");
        Intrinsics.checkNotNullParameter(survey_period_id, "survey_period_id");
        return new AssignmentSampling(_id, survey_period_id, pre_defined_data, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentSampling)) {
            return false;
        }
        AssignmentSampling assignmentSampling = (AssignmentSampling) other;
        return Intrinsics.areEqual(this._id, assignmentSampling._id) && Intrinsics.areEqual(this.survey_period_id, assignmentSampling.survey_period_id) && Intrinsics.areEqual(this.pre_defined_data, assignmentSampling.pre_defined_data) && Intrinsics.areEqual(this.data, assignmentSampling.data);
    }

    public int hashCode() {
        int iHashCode = ((this._id.hashCode() * 31) + this.survey_period_id.hashCode()) * 31;
        String str = this.pre_defined_data;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.data;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentSampling(_id=" + this._id + ", survey_period_id=" + this.survey_period_id + ", pre_defined_data=" + this.pre_defined_data + ", data=" + this.data + ")";
    }

    public AssignmentSampling(String _id, String survey_period_id, String str, String str2) {
        Intrinsics.checkNotNullParameter(_id, "_id");
        Intrinsics.checkNotNullParameter(survey_period_id, "survey_period_id");
        this._id = _id;
        this.survey_period_id = survey_period_id;
        this.pre_defined_data = str;
        this.data = str2;
    }

    public final String get_id() {
        return this._id;
    }

    public final void set_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this._id = str;
    }

    public final String getSurvey_period_id() {
        return this.survey_period_id;
    }

    public final void setSurvey_period_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.survey_period_id = str;
    }

    public final String getPre_defined_data() {
        return this.pre_defined_data;
    }

    public final void setPre_defined_data(String str) {
        this.pre_defined_data = str;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }
}
