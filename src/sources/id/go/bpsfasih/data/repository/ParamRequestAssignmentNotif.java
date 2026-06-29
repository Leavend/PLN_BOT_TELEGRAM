package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006HÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\"\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012¨\u0006 "}, d2 = {"Lid/go/bpsfasih/data/repository/ParamRequestAssignmentNotif;", "", "deviceId", "", "surveyPeriodId", "assignmentIdsDownloaded", "", "assignmentIdsDeleted", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAssignmentIdsDeleted", "()Ljava/util/List;", "setAssignmentIdsDeleted", "(Ljava/util/List;)V", "getAssignmentIdsDownloaded", "setAssignmentIdsDownloaded", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "getSurveyPeriodId", "setSurveyPeriodId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ParamRequestAssignmentNotif {
    public static final int $stable = 8;
    private List<String> assignmentIdsDeleted;
    private List<String> assignmentIdsDownloaded;
    private String deviceId;
    private String surveyPeriodId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ParamRequestAssignmentNotif copy$default(ParamRequestAssignmentNotif paramRequestAssignmentNotif, String str, String str2, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = paramRequestAssignmentNotif.deviceId;
        }
        if ((i & 2) != 0) {
            str2 = paramRequestAssignmentNotif.surveyPeriodId;
        }
        if ((i & 4) != 0) {
            list = paramRequestAssignmentNotif.assignmentIdsDownloaded;
        }
        if ((i & 8) != 0) {
            list2 = paramRequestAssignmentNotif.assignmentIdsDeleted;
        }
        return paramRequestAssignmentNotif.copy(str, str2, list, list2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final List<String> component3() {
        return this.assignmentIdsDownloaded;
    }

    public final List<String> component4() {
        return this.assignmentIdsDeleted;
    }

    public final ParamRequestAssignmentNotif copy(String deviceId, String surveyPeriodId, List<String> assignmentIdsDownloaded, List<String> assignmentIdsDeleted) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(assignmentIdsDownloaded, "assignmentIdsDownloaded");
        Intrinsics.checkNotNullParameter(assignmentIdsDeleted, "assignmentIdsDeleted");
        return new ParamRequestAssignmentNotif(deviceId, surveyPeriodId, assignmentIdsDownloaded, assignmentIdsDeleted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParamRequestAssignmentNotif)) {
            return false;
        }
        ParamRequestAssignmentNotif paramRequestAssignmentNotif = (ParamRequestAssignmentNotif) other;
        return Intrinsics.areEqual(this.deviceId, paramRequestAssignmentNotif.deviceId) && Intrinsics.areEqual(this.surveyPeriodId, paramRequestAssignmentNotif.surveyPeriodId) && Intrinsics.areEqual(this.assignmentIdsDownloaded, paramRequestAssignmentNotif.assignmentIdsDownloaded) && Intrinsics.areEqual(this.assignmentIdsDeleted, paramRequestAssignmentNotif.assignmentIdsDeleted);
    }

    public int hashCode() {
        return (((((this.deviceId.hashCode() * 31) + this.surveyPeriodId.hashCode()) * 31) + this.assignmentIdsDownloaded.hashCode()) * 31) + this.assignmentIdsDeleted.hashCode();
    }

    public String toString() {
        return "ParamRequestAssignmentNotif(deviceId=" + this.deviceId + ", surveyPeriodId=" + this.surveyPeriodId + ", assignmentIdsDownloaded=" + this.assignmentIdsDownloaded + ", assignmentIdsDeleted=" + this.assignmentIdsDeleted + ")";
    }

    public ParamRequestAssignmentNotif(String deviceId, String surveyPeriodId, List<String> assignmentIdsDownloaded, List<String> assignmentIdsDeleted) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(assignmentIdsDownloaded, "assignmentIdsDownloaded");
        Intrinsics.checkNotNullParameter(assignmentIdsDeleted, "assignmentIdsDeleted");
        this.deviceId = deviceId;
        this.surveyPeriodId = surveyPeriodId;
        this.assignmentIdsDownloaded = assignmentIdsDownloaded;
        this.assignmentIdsDeleted = assignmentIdsDeleted;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceId = str;
    }

    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final void setSurveyPeriodId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surveyPeriodId = str;
    }

    public final List<String> getAssignmentIdsDownloaded() {
        return this.assignmentIdsDownloaded;
    }

    public final void setAssignmentIdsDownloaded(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.assignmentIdsDownloaded = list;
    }

    public final List<String> getAssignmentIdsDeleted() {
        return this.assignmentIdsDeleted;
    }

    public final void setAssignmentIdsDeleted(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.assignmentIdsDeleted = list;
    }
}
