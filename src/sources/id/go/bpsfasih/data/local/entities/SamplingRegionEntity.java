package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SamplingRegionEntity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\b\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "", DownloadModel.ID, "", "survey_periode_id", "fullcode", "mode", NotificationCompat.CATEGORY_STATUS, "is_done", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getFullcode", "()Ljava/lang/String;", "setFullcode", "(Ljava/lang/String;)V", "getId", "setId", "()Ljava/lang/Boolean;", "set_done", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getMode", "setMode", "getStatus", "setStatus", "getSurvey_periode_id", "setSurvey_periode_id", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SamplingRegionEntity {
    public static final int $stable = 8;
    private String fullcode;
    private String id;
    private Boolean is_done;
    private String mode;
    private String status;
    private String survey_periode_id;

    public SamplingRegionEntity(String id2, String survey_periode_id, String fullcode, String mode, String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(survey_periode_id, "survey_periode_id");
        Intrinsics.checkNotNullParameter(fullcode, "fullcode");
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.id = id2;
        this.survey_periode_id = survey_periode_id;
        this.fullcode = fullcode;
        this.mode = mode;
        this.status = str;
        this.is_done = bool;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getSurvey_periode_id() {
        return this.survey_periode_id;
    }

    public final void setSurvey_periode_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.survey_periode_id = str;
    }

    public final String getFullcode() {
        return this.fullcode;
    }

    public final void setFullcode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fullcode = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mode = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public /* synthetic */ SamplingRegionEntity(String str, String str2, String str3, String str4, String str5, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? false : bool);
    }

    /* renamed from: is_done, reason: from getter */
    public final Boolean getIs_done() {
        return this.is_done;
    }

    public final void set_done(Boolean bool) {
        this.is_done = bool;
    }
}
