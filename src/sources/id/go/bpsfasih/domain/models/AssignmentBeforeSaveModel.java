package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentBeforeSaveModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/domain/models/AssignmentBeforeSaveModel;", "", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "periodeId", "", "pathName", "isUpdateListing", "", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPathName", "()Ljava/lang/String;", "getPeriodeId", "getSurvey", "()Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "component1", "component2", "component3", "component4", "copy", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lid/go/bpsfasih/domain/models/AssignmentBeforeSaveModel;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentBeforeSaveModel {
    public static final int $stable = 8;
    private final Boolean isUpdateListing;
    private final String pathName;
    private final String periodeId;
    private final SurveyEntity survey;

    public static /* synthetic */ AssignmentBeforeSaveModel copy$default(AssignmentBeforeSaveModel assignmentBeforeSaveModel, SurveyEntity surveyEntity, String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            surveyEntity = assignmentBeforeSaveModel.survey;
        }
        if ((i & 2) != 0) {
            str = assignmentBeforeSaveModel.periodeId;
        }
        if ((i & 4) != 0) {
            str2 = assignmentBeforeSaveModel.pathName;
        }
        if ((i & 8) != 0) {
            bool = assignmentBeforeSaveModel.isUpdateListing;
        }
        return assignmentBeforeSaveModel.copy(surveyEntity, str, str2, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPeriodeId() {
        return this.periodeId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPathName() {
        return this.pathName;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getIsUpdateListing() {
        return this.isUpdateListing;
    }

    public final AssignmentBeforeSaveModel copy(SurveyEntity survey, String periodeId, String pathName, Boolean isUpdateListing) {
        return new AssignmentBeforeSaveModel(survey, periodeId, pathName, isUpdateListing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentBeforeSaveModel)) {
            return false;
        }
        AssignmentBeforeSaveModel assignmentBeforeSaveModel = (AssignmentBeforeSaveModel) other;
        return Intrinsics.areEqual(this.survey, assignmentBeforeSaveModel.survey) && Intrinsics.areEqual(this.periodeId, assignmentBeforeSaveModel.periodeId) && Intrinsics.areEqual(this.pathName, assignmentBeforeSaveModel.pathName) && Intrinsics.areEqual(this.isUpdateListing, assignmentBeforeSaveModel.isUpdateListing);
    }

    public int hashCode() {
        SurveyEntity surveyEntity = this.survey;
        int iHashCode = (surveyEntity == null ? 0 : surveyEntity.hashCode()) * 31;
        String str = this.periodeId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.pathName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isUpdateListing;
        return iHashCode3 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentBeforeSaveModel(survey=" + this.survey + ", periodeId=" + this.periodeId + ", pathName=" + this.pathName + ", isUpdateListing=" + this.isUpdateListing + ")";
    }

    public AssignmentBeforeSaveModel(SurveyEntity surveyEntity, String str, String str2, Boolean bool) {
        this.survey = surveyEntity;
        this.periodeId = str;
        this.pathName = str2;
        this.isUpdateListing = bool;
    }

    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final String getPathName() {
        return this.pathName;
    }

    public final Boolean isUpdateListing() {
        return this.isUpdateListing;
    }
}
