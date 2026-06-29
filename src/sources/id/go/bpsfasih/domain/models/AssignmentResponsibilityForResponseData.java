package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentResponsibilityModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bY\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u008d\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u001bJ\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010Q\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010R\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010S\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010X\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010Y\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010*J\u0010\u0010^\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0096\u0002\u0010f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010gJ\u0013\u0010h\u001a\u00020\r2\b\u0010i\u001a\u0004\u0018\u00010jHÖ\u0003J\t\u0010k\u001a\u00020\u0010HÖ\u0001J\t\u0010l\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b.\u0010#\"\u0004\b/\u0010%R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010\u001fR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001d\"\u0004\b3\u0010\u001fR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b4\u0010*\"\u0004\b5\u0010,R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001d\"\u0004\b7\u0010\u001fR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b8\u0010#\"\u0004\b9\u0010%R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001d\"\u0004\b;\u0010\u001fR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b<\u0010*\"\u0004\b=\u0010,R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b>\u0010#\"\u0004\b?\u0010%R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001d\"\u0004\bA\u0010\u001fR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001d\"\u0004\bC\u0010\u001fR\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\bD\u0010*\"\u0004\bE\u0010,R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001d\"\u0004\bG\u0010\u001fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u001d\"\u0004\bI\u0010\u001fR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001d\"\u0004\bK\u0010\u001fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u001d\"\u0004\bM\u0010\u001fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\u001d\"\u0004\bO\u0010\u001f¨\u0006m"}, d2 = {"Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "Ljava/io/Serializable;", DownloadModel.ID, "", "assignmentId", "surveyUserBeforeId", "surveyUserCurrentId", "assignmentResponsibilityStatusId", "beforeUserId", "beforeSurveyRoleId", "beforeSurveyRoleName", "beforeSurveyRoleRoleId", "beforeSurveyRoleCanPullSample", "", "beforeSurveyRoleIsPencacah", "beforeSurveyRoleIndex", "", "currentUserId", "currentSurveyRoleId", "currentSurveyRoleName", "currentSurveyRoleRoleId", "currentSurveyRoleCanPullSample", "currentSurveyRoleIsPencacah", "currentSurveyRoleIndex", "surveyPeriodeId", "beforeSurveyRolesequence", "currentSurveyRolesequence", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAssignmentId", "()Ljava/lang/String;", "setAssignmentId", "(Ljava/lang/String;)V", "getAssignmentResponsibilityStatusId", "setAssignmentResponsibilityStatusId", "getBeforeSurveyRoleCanPullSample", "()Ljava/lang/Boolean;", "setBeforeSurveyRoleCanPullSample", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getBeforeSurveyRoleId", "setBeforeSurveyRoleId", "getBeforeSurveyRoleIndex", "()Ljava/lang/Integer;", "setBeforeSurveyRoleIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBeforeSurveyRoleIsPencacah", "setBeforeSurveyRoleIsPencacah", "getBeforeSurveyRoleName", "setBeforeSurveyRoleName", "getBeforeSurveyRoleRoleId", "setBeforeSurveyRoleRoleId", "getBeforeSurveyRolesequence", "setBeforeSurveyRolesequence", "getBeforeUserId", "setBeforeUserId", "getCurrentSurveyRoleCanPullSample", "setCurrentSurveyRoleCanPullSample", "getCurrentSurveyRoleId", "setCurrentSurveyRoleId", "getCurrentSurveyRoleIndex", "setCurrentSurveyRoleIndex", "getCurrentSurveyRoleIsPencacah", "setCurrentSurveyRoleIsPencacah", "getCurrentSurveyRoleName", "setCurrentSurveyRoleName", "getCurrentSurveyRoleRoleId", "setCurrentSurveyRoleRoleId", "getCurrentSurveyRolesequence", "setCurrentSurveyRolesequence", "getCurrentUserId", "setCurrentUserId", "getId", "setId", "getSurveyPeriodeId", "setSurveyPeriodeId", "getSurveyUserBeforeId", "setSurveyUserBeforeId", "getSurveyUserCurrentId", "setSurveyUserCurrentId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "equals", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentResponsibilityForResponseData implements Serializable {
    public static final int $stable = 8;
    private String assignmentId;
    private String assignmentResponsibilityStatusId;
    private Boolean beforeSurveyRoleCanPullSample;
    private String beforeSurveyRoleId;
    private Integer beforeSurveyRoleIndex;
    private Boolean beforeSurveyRoleIsPencacah;
    private String beforeSurveyRoleName;
    private String beforeSurveyRoleRoleId;
    private Integer beforeSurveyRolesequence;
    private String beforeUserId;
    private Boolean currentSurveyRoleCanPullSample;
    private String currentSurveyRoleId;
    private Integer currentSurveyRoleIndex;
    private Boolean currentSurveyRoleIsPencacah;
    private String currentSurveyRoleName;
    private String currentSurveyRoleRoleId;
    private Integer currentSurveyRolesequence;
    private String currentUserId;
    private String id;
    private String surveyPeriodeId;
    private String surveyUserBeforeId;
    private String surveyUserCurrentId;

    public AssignmentResponsibilityForResponseData() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 4194303, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Boolean getBeforeSurveyRoleCanPullSample() {
        return this.beforeSurveyRoleCanPullSample;
    }

    /* renamed from: component11, reason: from getter */
    public final Boolean getBeforeSurveyRoleIsPencacah() {
        return this.beforeSurveyRoleIsPencacah;
    }

    /* renamed from: component12, reason: from getter */
    public final Integer getBeforeSurveyRoleIndex() {
        return this.beforeSurveyRoleIndex;
    }

    /* renamed from: component13, reason: from getter */
    public final String getCurrentUserId() {
        return this.currentUserId;
    }

    /* renamed from: component14, reason: from getter */
    public final String getCurrentSurveyRoleId() {
        return this.currentSurveyRoleId;
    }

    /* renamed from: component15, reason: from getter */
    public final String getCurrentSurveyRoleName() {
        return this.currentSurveyRoleName;
    }

    /* renamed from: component16, reason: from getter */
    public final String getCurrentSurveyRoleRoleId() {
        return this.currentSurveyRoleRoleId;
    }

    /* renamed from: component17, reason: from getter */
    public final Boolean getCurrentSurveyRoleCanPullSample() {
        return this.currentSurveyRoleCanPullSample;
    }

    /* renamed from: component18, reason: from getter */
    public final Boolean getCurrentSurveyRoleIsPencacah() {
        return this.currentSurveyRoleIsPencacah;
    }

    /* renamed from: component19, reason: from getter */
    public final Integer getCurrentSurveyRoleIndex() {
        return this.currentSurveyRoleIndex;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    /* renamed from: component21, reason: from getter */
    public final Integer getBeforeSurveyRolesequence() {
        return this.beforeSurveyRolesequence;
    }

    /* renamed from: component22, reason: from getter */
    public final Integer getCurrentSurveyRolesequence() {
        return this.currentSurveyRolesequence;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSurveyUserBeforeId() {
        return this.surveyUserBeforeId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSurveyUserCurrentId() {
        return this.surveyUserCurrentId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAssignmentResponsibilityStatusId() {
        return this.assignmentResponsibilityStatusId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getBeforeUserId() {
        return this.beforeUserId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBeforeSurveyRoleId() {
        return this.beforeSurveyRoleId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBeforeSurveyRoleName() {
        return this.beforeSurveyRoleName;
    }

    /* renamed from: component9, reason: from getter */
    public final String getBeforeSurveyRoleRoleId() {
        return this.beforeSurveyRoleRoleId;
    }

    public final AssignmentResponsibilityForResponseData copy(String id2, String assignmentId, String surveyUserBeforeId, String surveyUserCurrentId, String assignmentResponsibilityStatusId, String beforeUserId, String beforeSurveyRoleId, String beforeSurveyRoleName, String beforeSurveyRoleRoleId, Boolean beforeSurveyRoleCanPullSample, Boolean beforeSurveyRoleIsPencacah, Integer beforeSurveyRoleIndex, String currentUserId, String currentSurveyRoleId, String currentSurveyRoleName, String currentSurveyRoleRoleId, Boolean currentSurveyRoleCanPullSample, Boolean currentSurveyRoleIsPencacah, Integer currentSurveyRoleIndex, String surveyPeriodeId, Integer beforeSurveyRolesequence, Integer currentSurveyRolesequence) {
        return new AssignmentResponsibilityForResponseData(id2, assignmentId, surveyUserBeforeId, surveyUserCurrentId, assignmentResponsibilityStatusId, beforeUserId, beforeSurveyRoleId, beforeSurveyRoleName, beforeSurveyRoleRoleId, beforeSurveyRoleCanPullSample, beforeSurveyRoleIsPencacah, beforeSurveyRoleIndex, currentUserId, currentSurveyRoleId, currentSurveyRoleName, currentSurveyRoleRoleId, currentSurveyRoleCanPullSample, currentSurveyRoleIsPencacah, currentSurveyRoleIndex, surveyPeriodeId, beforeSurveyRolesequence, currentSurveyRolesequence);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentResponsibilityForResponseData)) {
            return false;
        }
        AssignmentResponsibilityForResponseData assignmentResponsibilityForResponseData = (AssignmentResponsibilityForResponseData) other;
        return Intrinsics.areEqual(this.id, assignmentResponsibilityForResponseData.id) && Intrinsics.areEqual(this.assignmentId, assignmentResponsibilityForResponseData.assignmentId) && Intrinsics.areEqual(this.surveyUserBeforeId, assignmentResponsibilityForResponseData.surveyUserBeforeId) && Intrinsics.areEqual(this.surveyUserCurrentId, assignmentResponsibilityForResponseData.surveyUserCurrentId) && Intrinsics.areEqual(this.assignmentResponsibilityStatusId, assignmentResponsibilityForResponseData.assignmentResponsibilityStatusId) && Intrinsics.areEqual(this.beforeUserId, assignmentResponsibilityForResponseData.beforeUserId) && Intrinsics.areEqual(this.beforeSurveyRoleId, assignmentResponsibilityForResponseData.beforeSurveyRoleId) && Intrinsics.areEqual(this.beforeSurveyRoleName, assignmentResponsibilityForResponseData.beforeSurveyRoleName) && Intrinsics.areEqual(this.beforeSurveyRoleRoleId, assignmentResponsibilityForResponseData.beforeSurveyRoleRoleId) && Intrinsics.areEqual(this.beforeSurveyRoleCanPullSample, assignmentResponsibilityForResponseData.beforeSurveyRoleCanPullSample) && Intrinsics.areEqual(this.beforeSurveyRoleIsPencacah, assignmentResponsibilityForResponseData.beforeSurveyRoleIsPencacah) && Intrinsics.areEqual(this.beforeSurveyRoleIndex, assignmentResponsibilityForResponseData.beforeSurveyRoleIndex) && Intrinsics.areEqual(this.currentUserId, assignmentResponsibilityForResponseData.currentUserId) && Intrinsics.areEqual(this.currentSurveyRoleId, assignmentResponsibilityForResponseData.currentSurveyRoleId) && Intrinsics.areEqual(this.currentSurveyRoleName, assignmentResponsibilityForResponseData.currentSurveyRoleName) && Intrinsics.areEqual(this.currentSurveyRoleRoleId, assignmentResponsibilityForResponseData.currentSurveyRoleRoleId) && Intrinsics.areEqual(this.currentSurveyRoleCanPullSample, assignmentResponsibilityForResponseData.currentSurveyRoleCanPullSample) && Intrinsics.areEqual(this.currentSurveyRoleIsPencacah, assignmentResponsibilityForResponseData.currentSurveyRoleIsPencacah) && Intrinsics.areEqual(this.currentSurveyRoleIndex, assignmentResponsibilityForResponseData.currentSurveyRoleIndex) && Intrinsics.areEqual(this.surveyPeriodeId, assignmentResponsibilityForResponseData.surveyPeriodeId) && Intrinsics.areEqual(this.beforeSurveyRolesequence, assignmentResponsibilityForResponseData.beforeSurveyRolesequence) && Intrinsics.areEqual(this.currentSurveyRolesequence, assignmentResponsibilityForResponseData.currentSurveyRolesequence);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.assignmentId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveyUserBeforeId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.surveyUserCurrentId;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.assignmentResponsibilityStatusId;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.beforeUserId;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.beforeSurveyRoleId;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.beforeSurveyRoleName;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.beforeSurveyRoleRoleId;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Boolean bool = this.beforeSurveyRoleCanPullSample;
        int iHashCode10 = (iHashCode9 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.beforeSurveyRoleIsPencacah;
        int iHashCode11 = (iHashCode10 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num = this.beforeSurveyRoleIndex;
        int iHashCode12 = (iHashCode11 + (num == null ? 0 : num.hashCode())) * 31;
        String str10 = this.currentUserId;
        int iHashCode13 = (iHashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.currentSurveyRoleId;
        int iHashCode14 = (iHashCode13 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.currentSurveyRoleName;
        int iHashCode15 = (iHashCode14 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.currentSurveyRoleRoleId;
        int iHashCode16 = (iHashCode15 + (str13 == null ? 0 : str13.hashCode())) * 31;
        Boolean bool3 = this.currentSurveyRoleCanPullSample;
        int iHashCode17 = (iHashCode16 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.currentSurveyRoleIsPencacah;
        int iHashCode18 = (iHashCode17 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Integer num2 = this.currentSurveyRoleIndex;
        int iHashCode19 = (iHashCode18 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str14 = this.surveyPeriodeId;
        int iHashCode20 = (iHashCode19 + (str14 == null ? 0 : str14.hashCode())) * 31;
        Integer num3 = this.beforeSurveyRolesequence;
        int iHashCode21 = (iHashCode20 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.currentSurveyRolesequence;
        return iHashCode21 + (num4 != null ? num4.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentResponsibilityForResponseData(id=" + this.id + ", assignmentId=" + this.assignmentId + ", surveyUserBeforeId=" + this.surveyUserBeforeId + ", surveyUserCurrentId=" + this.surveyUserCurrentId + ", assignmentResponsibilityStatusId=" + this.assignmentResponsibilityStatusId + ", beforeUserId=" + this.beforeUserId + ", beforeSurveyRoleId=" + this.beforeSurveyRoleId + ", beforeSurveyRoleName=" + this.beforeSurveyRoleName + ", beforeSurveyRoleRoleId=" + this.beforeSurveyRoleRoleId + ", beforeSurveyRoleCanPullSample=" + this.beforeSurveyRoleCanPullSample + ", beforeSurveyRoleIsPencacah=" + this.beforeSurveyRoleIsPencacah + ", beforeSurveyRoleIndex=" + this.beforeSurveyRoleIndex + ", currentUserId=" + this.currentUserId + ", currentSurveyRoleId=" + this.currentSurveyRoleId + ", currentSurveyRoleName=" + this.currentSurveyRoleName + ", currentSurveyRoleRoleId=" + this.currentSurveyRoleRoleId + ", currentSurveyRoleCanPullSample=" + this.currentSurveyRoleCanPullSample + ", currentSurveyRoleIsPencacah=" + this.currentSurveyRoleIsPencacah + ", currentSurveyRoleIndex=" + this.currentSurveyRoleIndex + ", surveyPeriodeId=" + this.surveyPeriodeId + ", beforeSurveyRolesequence=" + this.beforeSurveyRolesequence + ", currentSurveyRolesequence=" + this.currentSurveyRolesequence + ")";
    }

    public AssignmentResponsibilityForResponseData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool, Boolean bool2, Integer num, String str10, String str11, String str12, String str13, Boolean bool3, Boolean bool4, Integer num2, String str14, Integer num3, Integer num4) {
        this.id = str;
        this.assignmentId = str2;
        this.surveyUserBeforeId = str3;
        this.surveyUserCurrentId = str4;
        this.assignmentResponsibilityStatusId = str5;
        this.beforeUserId = str6;
        this.beforeSurveyRoleId = str7;
        this.beforeSurveyRoleName = str8;
        this.beforeSurveyRoleRoleId = str9;
        this.beforeSurveyRoleCanPullSample = bool;
        this.beforeSurveyRoleIsPencacah = bool2;
        this.beforeSurveyRoleIndex = num;
        this.currentUserId = str10;
        this.currentSurveyRoleId = str11;
        this.currentSurveyRoleName = str12;
        this.currentSurveyRoleRoleId = str13;
        this.currentSurveyRoleCanPullSample = bool3;
        this.currentSurveyRoleIsPencacah = bool4;
        this.currentSurveyRoleIndex = num2;
        this.surveyPeriodeId = str14;
        this.beforeSurveyRolesequence = num3;
        this.currentSurveyRolesequence = num4;
    }

    public /* synthetic */ AssignmentResponsibilityForResponseData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool, Boolean bool2, Integer num, String str10, String str11, String str12, String str13, Boolean bool3, Boolean bool4, Integer num2, String str14, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : bool, (i & 1024) != 0 ? null : bool2, (i & 2048) != 0 ? null : num, (i & 4096) != 0 ? null : str10, (i & 8192) != 0 ? null : str11, (i & 16384) != 0 ? null : str12, (i & 32768) != 0 ? null : str13, (i & 65536) != 0 ? null : bool3, (i & 131072) != 0 ? null : bool4, (i & 262144) != 0 ? null : num2, (i & 524288) != 0 ? null : str14, (i & 1048576) != 0 ? null : num3, (i & 2097152) != 0 ? null : num4);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final void setAssignmentId(String str) {
        this.assignmentId = str;
    }

    public final String getSurveyUserBeforeId() {
        return this.surveyUserBeforeId;
    }

    public final void setSurveyUserBeforeId(String str) {
        this.surveyUserBeforeId = str;
    }

    public final String getSurveyUserCurrentId() {
        return this.surveyUserCurrentId;
    }

    public final void setSurveyUserCurrentId(String str) {
        this.surveyUserCurrentId = str;
    }

    public final String getAssignmentResponsibilityStatusId() {
        return this.assignmentResponsibilityStatusId;
    }

    public final void setAssignmentResponsibilityStatusId(String str) {
        this.assignmentResponsibilityStatusId = str;
    }

    public final String getBeforeUserId() {
        return this.beforeUserId;
    }

    public final void setBeforeUserId(String str) {
        this.beforeUserId = str;
    }

    public final String getBeforeSurveyRoleId() {
        return this.beforeSurveyRoleId;
    }

    public final void setBeforeSurveyRoleId(String str) {
        this.beforeSurveyRoleId = str;
    }

    public final String getBeforeSurveyRoleName() {
        return this.beforeSurveyRoleName;
    }

    public final void setBeforeSurveyRoleName(String str) {
        this.beforeSurveyRoleName = str;
    }

    public final String getBeforeSurveyRoleRoleId() {
        return this.beforeSurveyRoleRoleId;
    }

    public final void setBeforeSurveyRoleRoleId(String str) {
        this.beforeSurveyRoleRoleId = str;
    }

    public final Boolean getBeforeSurveyRoleCanPullSample() {
        return this.beforeSurveyRoleCanPullSample;
    }

    public final void setBeforeSurveyRoleCanPullSample(Boolean bool) {
        this.beforeSurveyRoleCanPullSample = bool;
    }

    public final Boolean getBeforeSurveyRoleIsPencacah() {
        return this.beforeSurveyRoleIsPencacah;
    }

    public final void setBeforeSurveyRoleIsPencacah(Boolean bool) {
        this.beforeSurveyRoleIsPencacah = bool;
    }

    public final Integer getBeforeSurveyRoleIndex() {
        return this.beforeSurveyRoleIndex;
    }

    public final void setBeforeSurveyRoleIndex(Integer num) {
        this.beforeSurveyRoleIndex = num;
    }

    public final String getCurrentUserId() {
        return this.currentUserId;
    }

    public final void setCurrentUserId(String str) {
        this.currentUserId = str;
    }

    public final String getCurrentSurveyRoleId() {
        return this.currentSurveyRoleId;
    }

    public final void setCurrentSurveyRoleId(String str) {
        this.currentSurveyRoleId = str;
    }

    public final String getCurrentSurveyRoleName() {
        return this.currentSurveyRoleName;
    }

    public final void setCurrentSurveyRoleName(String str) {
        this.currentSurveyRoleName = str;
    }

    public final String getCurrentSurveyRoleRoleId() {
        return this.currentSurveyRoleRoleId;
    }

    public final void setCurrentSurveyRoleRoleId(String str) {
        this.currentSurveyRoleRoleId = str;
    }

    public final Boolean getCurrentSurveyRoleCanPullSample() {
        return this.currentSurveyRoleCanPullSample;
    }

    public final void setCurrentSurveyRoleCanPullSample(Boolean bool) {
        this.currentSurveyRoleCanPullSample = bool;
    }

    public final Boolean getCurrentSurveyRoleIsPencacah() {
        return this.currentSurveyRoleIsPencacah;
    }

    public final void setCurrentSurveyRoleIsPencacah(Boolean bool) {
        this.currentSurveyRoleIsPencacah = bool;
    }

    public final Integer getCurrentSurveyRoleIndex() {
        return this.currentSurveyRoleIndex;
    }

    public final void setCurrentSurveyRoleIndex(Integer num) {
        this.currentSurveyRoleIndex = num;
    }

    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    public final void setSurveyPeriodeId(String str) {
        this.surveyPeriodeId = str;
    }

    public final Integer getBeforeSurveyRolesequence() {
        return this.beforeSurveyRolesequence;
    }

    public final void setBeforeSurveyRolesequence(Integer num) {
        this.beforeSurveyRolesequence = num;
    }

    public final Integer getCurrentSurveyRolesequence() {
        return this.currentSurveyRolesequence;
    }

    public final void setCurrentSurveyRolesequence(Integer num) {
        this.currentSurveyRolesequence = num;
    }
}
