package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleEntity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0007\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010\u0010¨\u0006!"}, d2 = {"Lid/go/bpsfasih/data/local/entities/TarikSampleEntity;", "", DownloadModel.ID, "", "survey_periode_source_id", "survey_periode_target_id", "script_id", "script_sampling", "source_schema", "target_schema", "source", TypedValues.AttributesType.S_TARGET, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getScript_id", "setScript_id", "getScript_sampling", "setScript_sampling", "getSource", "setSource", "getSource_schema", "setSource_schema", "getSurvey_periode_source_id", "setSurvey_periode_source_id", "getSurvey_periode_target_id", "setSurvey_periode_target_id", "getTarget", "setTarget", "getTarget_schema", "setTarget_schema", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampleEntity {
    public static final int $stable = 8;
    private String id;
    private String script_id;
    private String script_sampling;
    private String source;
    private String source_schema;
    private String survey_periode_source_id;
    private String survey_periode_target_id;
    private String target;
    private String target_schema;

    public TarikSampleEntity(String id2, String survey_periode_source_id, String survey_periode_target_id, String script_id, String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(survey_periode_source_id, "survey_periode_source_id");
        Intrinsics.checkNotNullParameter(survey_periode_target_id, "survey_periode_target_id");
        Intrinsics.checkNotNullParameter(script_id, "script_id");
        this.id = id2;
        this.survey_periode_source_id = survey_periode_source_id;
        this.survey_periode_target_id = survey_periode_target_id;
        this.script_id = script_id;
        this.script_sampling = str;
        this.source_schema = str2;
        this.target_schema = str3;
        this.source = str4;
        this.target = str5;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getSurvey_periode_source_id() {
        return this.survey_periode_source_id;
    }

    public final void setSurvey_periode_source_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.survey_periode_source_id = str;
    }

    public final String getSurvey_periode_target_id() {
        return this.survey_periode_target_id;
    }

    public final void setSurvey_periode_target_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.survey_periode_target_id = str;
    }

    public final String getScript_id() {
        return this.script_id;
    }

    public final void setScript_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.script_id = str;
    }

    public final String getScript_sampling() {
        return this.script_sampling;
    }

    public final void setScript_sampling(String str) {
        this.script_sampling = str;
    }

    public final String getSource_schema() {
        return this.source_schema;
    }

    public final void setSource_schema(String str) {
        this.source_schema = str;
    }

    public final String getTarget_schema() {
        return this.target_schema;
    }

    public final void setTarget_schema(String str) {
        this.target_schema = str;
    }

    public final String getSource() {
        return this.source;
    }

    public final void setSource(String str) {
        this.source = str;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void setTarget(String str) {
        this.target = str;
    }
}
