package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleConfigModel.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0012\b\u0002\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0013\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\rHÆ\u0003Jq\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\b\u0002\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0004HÖ\u0001R \u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018¨\u0006+"}, d2 = {"Lid/go/bpsfasih/data/local/models/TarikSampleConfig;", "", "targetSchema", "scriptLang", "", "externalData", "", DownloadModel.ID, "source", "Lid/go/bpsfasih/data/local/models/Source;", "sourceSchema", "script", TypedValues.AttributesType.S_TARGET, "Lid/go/bpsfasih/data/local/models/Target;", "(Ljava/lang/Object;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/Source;Ljava/lang/Object;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/Target;)V", "getExternalData", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "getScript", "getScriptLang", "getSource", "()Lid/go/bpsfasih/data/local/models/Source;", "getSourceSchema", "()Ljava/lang/Object;", "getTarget", "()Lid/go/bpsfasih/data/local/models/Target;", "getTargetSchema", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TarikSampleConfig {
    public static final int $stable = 8;

    @SerializedName("externalData")
    private final List<Object> externalData;

    @SerializedName("_id")
    private final String id;

    @SerializedName("script")
    private final String script;

    @SerializedName("scriptLang")
    private final String scriptLang;

    @SerializedName("source")
    private final Source source;

    @SerializedName("sourceSchema")
    private final Object sourceSchema;

    @SerializedName(TypedValues.AttributesType.S_TARGET)
    private final Target target;

    @SerializedName("targetSchema")
    private final Object targetSchema;

    public TarikSampleConfig() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Object getTargetSchema() {
        return this.targetSchema;
    }

    /* renamed from: component2, reason: from getter */
    public final String getScriptLang() {
        return this.scriptLang;
    }

    public final List<Object> component3() {
        return this.externalData;
    }

    /* renamed from: component4, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final Source getSource() {
        return this.source;
    }

    /* renamed from: component6, reason: from getter */
    public final Object getSourceSchema() {
        return this.sourceSchema;
    }

    /* renamed from: component7, reason: from getter */
    public final String getScript() {
        return this.script;
    }

    /* renamed from: component8, reason: from getter */
    public final Target getTarget() {
        return this.target;
    }

    public final TarikSampleConfig copy(Object targetSchema, String scriptLang, List<? extends Object> externalData, String id2, Source source, Object sourceSchema, String script, Target target) {
        return new TarikSampleConfig(targetSchema, scriptLang, externalData, id2, source, sourceSchema, script, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TarikSampleConfig)) {
            return false;
        }
        TarikSampleConfig tarikSampleConfig = (TarikSampleConfig) other;
        return Intrinsics.areEqual(this.targetSchema, tarikSampleConfig.targetSchema) && Intrinsics.areEqual(this.scriptLang, tarikSampleConfig.scriptLang) && Intrinsics.areEqual(this.externalData, tarikSampleConfig.externalData) && Intrinsics.areEqual(this.id, tarikSampleConfig.id) && Intrinsics.areEqual(this.source, tarikSampleConfig.source) && Intrinsics.areEqual(this.sourceSchema, tarikSampleConfig.sourceSchema) && Intrinsics.areEqual(this.script, tarikSampleConfig.script) && Intrinsics.areEqual(this.target, tarikSampleConfig.target);
    }

    public int hashCode() {
        Object obj = this.targetSchema;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        String str = this.scriptLang;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<Object> list = this.externalData;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str2 = this.id;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Source source = this.source;
        int iHashCode5 = (iHashCode4 + (source == null ? 0 : source.hashCode())) * 31;
        Object obj2 = this.sourceSchema;
        int iHashCode6 = (iHashCode5 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        String str3 = this.script;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Target target = this.target;
        return iHashCode7 + (target != null ? target.hashCode() : 0);
    }

    public String toString() {
        return "TarikSampleConfig(targetSchema=" + this.targetSchema + ", scriptLang=" + this.scriptLang + ", externalData=" + this.externalData + ", id=" + this.id + ", source=" + this.source + ", sourceSchema=" + this.sourceSchema + ", script=" + this.script + ", target=" + this.target + ")";
    }

    public TarikSampleConfig(Object obj, String str, List<? extends Object> list, String str2, Source source, Object obj2, String str3, Target target) {
        this.targetSchema = obj;
        this.scriptLang = str;
        this.externalData = list;
        this.id = str2;
        this.source = source;
        this.sourceSchema = obj2;
        this.script = str3;
        this.target = target;
    }

    public /* synthetic */ TarikSampleConfig(Object obj, String str, List list, String str2, Source source, Object obj2, String str3, Target target, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : source, (i & 32) != 0 ? null : obj2, (i & 64) != 0 ? null : str3, (i & 128) == 0 ? target : null);
    }

    public final Object getTargetSchema() {
        return this.targetSchema;
    }

    public final String getScriptLang() {
        return this.scriptLang;
    }

    public final List<Object> getExternalData() {
        return this.externalData;
    }

    public final String getId() {
        return this.id;
    }

    public final Source getSource() {
        return this.source;
    }

    public final Object getSourceSchema() {
        return this.sourceSchema;
    }

    public final String getScript() {
        return this.script;
    }

    public final Target getTarget() {
        return this.target;
    }
}
