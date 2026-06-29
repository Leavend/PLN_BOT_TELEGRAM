package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyTimeEntity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J<\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\f¨\u0006#"}, d2 = {"Lid/go/bpsfasih/data/local/entities/SurveyTimeEntity;", "", DownloadModel.ID, "", "user_id", "", "assignment_id", NotificationCompat.CATEGORY_STATUS, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;I)V", "getAssignment_id", "()Ljava/lang/String;", "setAssignment_id", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getStatus", "()I", "setStatus", "(I)V", "getUser_id", "setUser_id", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;I)Lid/go/bpsfasih/data/local/entities/SurveyTimeEntity;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyTimeEntity {
    public static final int $stable = 8;
    private String assignment_id;
    private Integer id;
    private int status;
    private String user_id;

    public static /* synthetic */ SurveyTimeEntity copy$default(SurveyTimeEntity surveyTimeEntity, Integer num, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = surveyTimeEntity.id;
        }
        if ((i2 & 2) != 0) {
            str = surveyTimeEntity.user_id;
        }
        if ((i2 & 4) != 0) {
            str2 = surveyTimeEntity.assignment_id;
        }
        if ((i2 & 8) != 0) {
            i = surveyTimeEntity.status;
        }
        return surveyTimeEntity.copy(num, str, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUser_id() {
        return this.user_id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAssignment_id() {
        return this.assignment_id;
    }

    /* renamed from: component4, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final SurveyTimeEntity copy(Integer id2, String user_id, String assignment_id, int status) {
        return new SurveyTimeEntity(id2, user_id, assignment_id, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyTimeEntity)) {
            return false;
        }
        SurveyTimeEntity surveyTimeEntity = (SurveyTimeEntity) other;
        return Intrinsics.areEqual(this.id, surveyTimeEntity.id) && Intrinsics.areEqual(this.user_id, surveyTimeEntity.user_id) && Intrinsics.areEqual(this.assignment_id, surveyTimeEntity.assignment_id) && this.status == surveyTimeEntity.status;
    }

    public int hashCode() {
        Integer num = this.id;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.user_id;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.assignment_id;
        return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.status);
    }

    public String toString() {
        return "SurveyTimeEntity(id=" + this.id + ", user_id=" + this.user_id + ", assignment_id=" + this.assignment_id + ", status=" + this.status + ")";
    }

    public SurveyTimeEntity(Integer num, String str, String str2, int i) {
        this.id = num;
        this.user_id = str;
        this.assignment_id = str2;
        this.status = i;
    }

    public /* synthetic */ SurveyTimeEntity(Integer num, String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, str2, (i2 & 8) != 0 ? 0 : i);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getUser_id() {
        return this.user_id;
    }

    public final void setUser_id(String str) {
        this.user_id = str;
    }

    public final String getAssignment_id() {
        return this.assignment_id;
    }

    public final void setAssignment_id(String str) {
        this.assignment_id = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
