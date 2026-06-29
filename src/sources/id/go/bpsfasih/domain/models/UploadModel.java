package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: UploadModel.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b7\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B¥\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00107\u001a\u00020\u000eHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00109\u001a\u00020\u000eHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0007HÆ\u0003J\t\u0010?\u001a\u00020\u0007HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J©\u0001\u0010D\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u000eHÖ\u0001J\t\u0010I\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0010\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010\u0017R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0015\"\u0004\b+\u0010\u0017R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0015\"\u0004\b-\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010#\"\u0004\b1\u0010%R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0015\"\u0004\b3\u0010\u0017R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0015\"\u0004\b5\u0010\u0017¨\u0006J"}, d2 = {"Lid/go/bpsfasih/domain/models/UploadModel;", "", "url", "", ClientCookie.PATH_ATTR, "fileName", "startLength", "", "endLength", "assignmentId", "periodId", "dummyAssignmentId", "authorization", "surveyType", "", "userId", "errorCode", "data", "result", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAssignmentId", "()Ljava/lang/String;", "setAssignmentId", "(Ljava/lang/String;)V", "getAuthorization", "setAuthorization", "getData", "setData", "getDummyAssignmentId", "setDummyAssignmentId", "getEndLength", "()J", "setEndLength", "(J)V", "getErrorCode", "()I", "setErrorCode", "(I)V", "getFileName", "setFileName", "getPath", "setPath", "getPeriodId", "setPeriodId", "getResult", "setResult", "getStartLength", "setStartLength", "getSurveyType", "setSurveyType", "getUrl", "setUrl", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UploadModel {
    public static final int $stable = 8;
    private String assignmentId;
    private String authorization;
    private String data;
    private String dummyAssignmentId;
    private long endLength;
    private int errorCode;
    private String fileName;
    private String path;
    private String periodId;
    private String result;
    private long startLength;
    private int surveyType;
    private String url;
    private String userId;

    public UploadModel() {
        this(null, null, null, 0L, 0L, null, null, null, null, 0, null, 0, null, null, 16383, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component10, reason: from getter */
    public final int getSurveyType() {
        return this.surveyType;
    }

    /* renamed from: component11, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component12, reason: from getter */
    public final int getErrorCode() {
        return this.errorCode;
    }

    /* renamed from: component13, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component14, reason: from getter */
    public final String getResult() {
        return this.result;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component4, reason: from getter */
    public final long getStartLength() {
        return this.startLength;
    }

    /* renamed from: component5, reason: from getter */
    public final long getEndLength() {
        return this.endLength;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getPeriodId() {
        return this.periodId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getDummyAssignmentId() {
        return this.dummyAssignmentId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getAuthorization() {
        return this.authorization;
    }

    public final UploadModel copy(String url, String path, String fileName, long startLength, long endLength, String assignmentId, String periodId, String dummyAssignmentId, String authorization, int surveyType, String userId, int errorCode, String data, String result) {
        return new UploadModel(url, path, fileName, startLength, endLength, assignmentId, periodId, dummyAssignmentId, authorization, surveyType, userId, errorCode, data, result);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UploadModel)) {
            return false;
        }
        UploadModel uploadModel = (UploadModel) other;
        return Intrinsics.areEqual(this.url, uploadModel.url) && Intrinsics.areEqual(this.path, uploadModel.path) && Intrinsics.areEqual(this.fileName, uploadModel.fileName) && this.startLength == uploadModel.startLength && this.endLength == uploadModel.endLength && Intrinsics.areEqual(this.assignmentId, uploadModel.assignmentId) && Intrinsics.areEqual(this.periodId, uploadModel.periodId) && Intrinsics.areEqual(this.dummyAssignmentId, uploadModel.dummyAssignmentId) && Intrinsics.areEqual(this.authorization, uploadModel.authorization) && this.surveyType == uploadModel.surveyType && Intrinsics.areEqual(this.userId, uploadModel.userId) && this.errorCode == uploadModel.errorCode && Intrinsics.areEqual(this.data, uploadModel.data) && Intrinsics.areEqual(this.result, uploadModel.result);
    }

    public int hashCode() {
        String str = this.url;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.path;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fileName;
        int iHashCode3 = (((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Long.hashCode(this.startLength)) * 31) + Long.hashCode(this.endLength)) * 31;
        String str4 = this.assignmentId;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.periodId;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.dummyAssignmentId;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.authorization;
        int iHashCode7 = (((iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31) + Integer.hashCode(this.surveyType)) * 31;
        String str8 = this.userId;
        int iHashCode8 = (((iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31) + Integer.hashCode(this.errorCode)) * 31;
        String str9 = this.data;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.result;
        return iHashCode9 + (str10 != null ? str10.hashCode() : 0);
    }

    public String toString() {
        return "UploadModel(url=" + this.url + ", path=" + this.path + ", fileName=" + this.fileName + ", startLength=" + this.startLength + ", endLength=" + this.endLength + ", assignmentId=" + this.assignmentId + ", periodId=" + this.periodId + ", dummyAssignmentId=" + this.dummyAssignmentId + ", authorization=" + this.authorization + ", surveyType=" + this.surveyType + ", userId=" + this.userId + ", errorCode=" + this.errorCode + ", data=" + this.data + ", result=" + this.result + ")";
    }

    public UploadModel(String str, String str2, String str3, long j, long j2, String str4, String str5, String str6, String str7, int i, String str8, int i2, String str9, String str10) {
        this.url = str;
        this.path = str2;
        this.fileName = str3;
        this.startLength = j;
        this.endLength = j2;
        this.assignmentId = str4;
        this.periodId = str5;
        this.dummyAssignmentId = str6;
        this.authorization = str7;
        this.surveyType = i;
        this.userId = str8;
        this.errorCode = i2;
        this.data = str9;
        this.result = str10;
    }

    public /* synthetic */ UploadModel(String str, String str2, String str3, long j, long j2, String str4, String str5, String str6, String str7, int i, String str8, int i2, String str9, String str10, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? 0L : j, (i3 & 16) == 0 ? j2 : 0L, (i3 & 32) != 0 ? null : str4, (i3 & 64) != 0 ? null : str5, (i3 & 128) != 0 ? "0" : str6, (i3 & 256) != 0 ? null : str7, (i3 & 512) != 0 ? 0 : i, (i3 & 1024) != 0 ? null : str8, (i3 & 2048) == 0 ? i2 : 0, (i3 & 4096) != 0 ? null : str9, (i3 & 8192) != 0 ? null : str10);
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        this.fileName = str;
    }

    public final long getStartLength() {
        return this.startLength;
    }

    public final void setStartLength(long j) {
        this.startLength = j;
    }

    public final long getEndLength() {
        return this.endLength;
    }

    public final void setEndLength(long j) {
        this.endLength = j;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final void setAssignmentId(String str) {
        this.assignmentId = str;
    }

    public final String getPeriodId() {
        return this.periodId;
    }

    public final void setPeriodId(String str) {
        this.periodId = str;
    }

    public final String getDummyAssignmentId() {
        return this.dummyAssignmentId;
    }

    public final void setDummyAssignmentId(String str) {
        this.dummyAssignmentId = str;
    }

    public final String getAuthorization() {
        return this.authorization;
    }

    public final void setAuthorization(String str) {
        this.authorization = str;
    }

    public final int getSurveyType() {
        return this.surveyType;
    }

    public final void setSurveyType(int i) {
        this.surveyType = i;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }

    public final String getResult() {
        return this.result;
    }

    public final void setResult(String str) {
        this.result = str;
    }
}
