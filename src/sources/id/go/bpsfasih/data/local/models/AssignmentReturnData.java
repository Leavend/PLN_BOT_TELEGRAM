package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.domain.models.AssignmentResponsibilityForResponseData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseResponse.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b.\b\u0007\u0018\u00002\u00020\u0001B³\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0010\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\b\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005¢\u0006\u0002\u0010\u0015R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001b\"\u0004\b$\u0010\u001dR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001b\"\u0004\b(\u0010\u001dR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001dR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b0\u0010*\"\u0004\b1\u0010,R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001b\"\u0004\b5\u0010\u001dR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u0010\u001dR$\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0017\"\u0004\b9\u0010\u0019R\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006>"}, d2 = {"Lid/go/bpsfasih/data/local/models/AssignmentReturnData;", "", DownloadModel.ID, "", "assignmentStatusId", "", "assignmentStatusAlias", "assignmentResponsibility", "", "Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "currentUserId", "currentUserUsername", "currentUserFullname", "currentUserSurveyRoleId", "currentUserSurveyRoleName", "currentUserSurveyRoleIsPencacah", "", "currentUserSurveyRoleCanPullSample", "mode", "basePath", "submitVersionCode", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;I)V", "getAssignmentResponsibility", "()Ljava/util/List;", "setAssignmentResponsibility", "(Ljava/util/List;)V", "getAssignmentStatusAlias", "()Ljava/lang/String;", "setAssignmentStatusAlias", "(Ljava/lang/String;)V", "getAssignmentStatusId", "()Ljava/lang/Integer;", "setAssignmentStatusId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBasePath", "setBasePath", "getCurrentUserFullname", "setCurrentUserFullname", "getCurrentUserId", "setCurrentUserId", "getCurrentUserSurveyRoleCanPullSample", "()Ljava/lang/Boolean;", "setCurrentUserSurveyRoleCanPullSample", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCurrentUserSurveyRoleId", "setCurrentUserSurveyRoleId", "getCurrentUserSurveyRoleIsPencacah", "setCurrentUserSurveyRoleIsPencacah", "getCurrentUserSurveyRoleName", "setCurrentUserSurveyRoleName", "getCurrentUserUsername", "setCurrentUserUsername", "getId", "setId", "getMode", "setMode", "getSubmitVersionCode", "()I", "setSubmitVersionCode", "(I)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentReturnData {
    public static final int $stable = 8;
    private List<AssignmentResponsibilityForResponseData> assignmentResponsibility;
    private String assignmentStatusAlias;
    private Integer assignmentStatusId;
    private String basePath;
    private String currentUserFullname;
    private String currentUserId;
    private Boolean currentUserSurveyRoleCanPullSample;
    private String currentUserSurveyRoleId;
    private Boolean currentUserSurveyRoleIsPencacah;
    private String currentUserSurveyRoleName;
    private String currentUserUsername;
    private String id;
    private List<String> mode;
    private int submitVersionCode;

    public AssignmentReturnData(String str, Integer num, String str2, List<AssignmentResponsibilityForResponseData> list, String str3, String str4, String str5, String str6, String str7, Boolean bool, Boolean bool2, List<String> list2, String str8, int i) {
        this.id = str;
        this.assignmentStatusId = num;
        this.assignmentStatusAlias = str2;
        this.assignmentResponsibility = list;
        this.currentUserId = str3;
        this.currentUserUsername = str4;
        this.currentUserFullname = str5;
        this.currentUserSurveyRoleId = str6;
        this.currentUserSurveyRoleName = str7;
        this.currentUserSurveyRoleIsPencacah = bool;
        this.currentUserSurveyRoleCanPullSample = bool2;
        this.mode = list2;
        this.basePath = str8;
        this.submitVersionCode = i;
    }

    public /* synthetic */ AssignmentReturnData(String str, Integer num, String str2, List list, String str3, String str4, String str5, String str6, String str7, Boolean bool, Boolean bool2, List list2, String str8, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : str2, list, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? null : str6, (i2 & 256) != 0 ? null : str7, (i2 & 512) != 0 ? null : bool, (i2 & 1024) != 0 ? null : bool2, list2, str8, (i2 & 8192) != 0 ? 0 : i);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final Integer getAssignmentStatusId() {
        return this.assignmentStatusId;
    }

    public final void setAssignmentStatusId(Integer num) {
        this.assignmentStatusId = num;
    }

    public final String getAssignmentStatusAlias() {
        return this.assignmentStatusAlias;
    }

    public final void setAssignmentStatusAlias(String str) {
        this.assignmentStatusAlias = str;
    }

    public final List<AssignmentResponsibilityForResponseData> getAssignmentResponsibility() {
        return this.assignmentResponsibility;
    }

    public final void setAssignmentResponsibility(List<AssignmentResponsibilityForResponseData> list) {
        this.assignmentResponsibility = list;
    }

    public final String getCurrentUserId() {
        return this.currentUserId;
    }

    public final void setCurrentUserId(String str) {
        this.currentUserId = str;
    }

    public final String getCurrentUserUsername() {
        return this.currentUserUsername;
    }

    public final void setCurrentUserUsername(String str) {
        this.currentUserUsername = str;
    }

    public final String getCurrentUserFullname() {
        return this.currentUserFullname;
    }

    public final void setCurrentUserFullname(String str) {
        this.currentUserFullname = str;
    }

    public final String getCurrentUserSurveyRoleId() {
        return this.currentUserSurveyRoleId;
    }

    public final void setCurrentUserSurveyRoleId(String str) {
        this.currentUserSurveyRoleId = str;
    }

    public final String getCurrentUserSurveyRoleName() {
        return this.currentUserSurveyRoleName;
    }

    public final void setCurrentUserSurveyRoleName(String str) {
        this.currentUserSurveyRoleName = str;
    }

    public final Boolean getCurrentUserSurveyRoleIsPencacah() {
        return this.currentUserSurveyRoleIsPencacah;
    }

    public final void setCurrentUserSurveyRoleIsPencacah(Boolean bool) {
        this.currentUserSurveyRoleIsPencacah = bool;
    }

    public final Boolean getCurrentUserSurveyRoleCanPullSample() {
        return this.currentUserSurveyRoleCanPullSample;
    }

    public final void setCurrentUserSurveyRoleCanPullSample(Boolean bool) {
        this.currentUserSurveyRoleCanPullSample = bool;
    }

    public final List<String> getMode() {
        return this.mode;
    }

    public final void setMode(List<String> list) {
        this.mode = list;
    }

    public final String getBasePath() {
        return this.basePath;
    }

    public final void setBasePath(String str) {
        this.basePath = str;
    }

    public final int getSubmitVersionCode() {
        return this.submitVersionCode;
    }

    public final void setSubmitVersionCode(int i) {
        this.submitVersionCode = i;
    }
}
