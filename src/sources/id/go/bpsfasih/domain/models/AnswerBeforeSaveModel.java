package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: AnswerBeforeSaveModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/domain/models/AnswerBeforeSaveModel;", "", ClientCookie.PATH_ATTR, "", "filename", "periodeId", "surveyId", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFilename", "()Ljava/lang/String;", "getPath", "getPeriodeId", "getSurveyId", "getUserId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AnswerBeforeSaveModel {
    public static final int $stable = 0;
    private final String filename;
    private final String path;
    private final String periodeId;
    private final String surveyId;
    private final String userId;

    public static /* synthetic */ AnswerBeforeSaveModel copy$default(AnswerBeforeSaveModel answerBeforeSaveModel, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = answerBeforeSaveModel.path;
        }
        if ((i & 2) != 0) {
            str2 = answerBeforeSaveModel.filename;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = answerBeforeSaveModel.periodeId;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = answerBeforeSaveModel.surveyId;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = answerBeforeSaveModel.userId;
        }
        return answerBeforeSaveModel.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPeriodeId() {
        return this.periodeId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    public final AnswerBeforeSaveModel copy(String path, String filename, String periodeId, String surveyId, String userId) {
        return new AnswerBeforeSaveModel(path, filename, periodeId, surveyId, userId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnswerBeforeSaveModel)) {
            return false;
        }
        AnswerBeforeSaveModel answerBeforeSaveModel = (AnswerBeforeSaveModel) other;
        return Intrinsics.areEqual(this.path, answerBeforeSaveModel.path) && Intrinsics.areEqual(this.filename, answerBeforeSaveModel.filename) && Intrinsics.areEqual(this.periodeId, answerBeforeSaveModel.periodeId) && Intrinsics.areEqual(this.surveyId, answerBeforeSaveModel.surveyId) && Intrinsics.areEqual(this.userId, answerBeforeSaveModel.userId);
    }

    public int hashCode() {
        String str = this.path;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.filename;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.periodeId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.surveyId;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.userId;
        return iHashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "AnswerBeforeSaveModel(path=" + this.path + ", filename=" + this.filename + ", periodeId=" + this.periodeId + ", surveyId=" + this.surveyId + ", userId=" + this.userId + ")";
    }

    public AnswerBeforeSaveModel(String str, String str2, String str3, String str4, String str5) {
        this.path = str;
        this.filename = str2;
        this.periodeId = str3;
        this.surveyId = str4;
        this.userId = str5;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getUserId() {
        return this.userId;
    }
}
