package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u009d\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0012\b\u0002\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0015J\u000b\u0010+\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0013\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\rHÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001eJ¦\u0001\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0012\b\u0002\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u00108J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\u000fHÖ\u0001J\t\u0010=\u001a\u00020\u0004HÖ\u0001R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017¨\u0006>"}, d2 = {"Lid/go/bpsfasih/data/local/models/ContentItem;", "", "targetSchema", "createdAt", "", "regionGroupId", "name", "externalData", "", DownloadModel.ID, "source", "Lid/go/bpsfasih/data/local/models/Source;", "sourceSchema", "Lid/go/bpsfasih/data/local/models/SourceSchema;", "regionLevel", "", "script", "Lid/go/bpsfasih/data/local/models/Script;", TypedValues.AttributesType.S_TARGET, "Lid/go/bpsfasih/data/local/models/Target;", "updatedAt", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/Source;Lid/go/bpsfasih/data/local/models/SourceSchema;Ljava/lang/Integer;Lid/go/bpsfasih/data/local/models/Script;Lid/go/bpsfasih/data/local/models/Target;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/lang/String;", "getExternalData", "()Ljava/util/List;", "getId", "getName", "getRegionGroupId", "getRegionLevel", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getScript", "()Lid/go/bpsfasih/data/local/models/Script;", "getSource", "()Lid/go/bpsfasih/data/local/models/Source;", "getSourceSchema", "()Lid/go/bpsfasih/data/local/models/SourceSchema;", "getTarget", "()Lid/go/bpsfasih/data/local/models/Target;", "getTargetSchema", "()Ljava/lang/Object;", "getUpdatedAt", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/Source;Lid/go/bpsfasih/data/local/models/SourceSchema;Ljava/lang/Integer;Lid/go/bpsfasih/data/local/models/Script;Lid/go/bpsfasih/data/local/models/Target;Ljava/lang/String;)Lid/go/bpsfasih/data/local/models/ContentItem;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ContentItem {
    public static final int $stable = 8;

    @SerializedName("createdAt")
    private final String createdAt;

    @SerializedName("externalData")
    private final List<Object> externalData;

    @SerializedName("_id")
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("regionGroupId")
    private final String regionGroupId;

    @SerializedName("regionLevel")
    private final Integer regionLevel;

    @SerializedName("script")
    private final Script script;

    @SerializedName("source")
    private final Source source;

    @SerializedName("sourceSchema")
    private final SourceSchema sourceSchema;

    @SerializedName(TypedValues.AttributesType.S_TARGET)
    private final Target target;

    @SerializedName("targetSchema")
    private final Object targetSchema;

    @SerializedName("updatedAt")
    private final String updatedAt;

    public ContentItem() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Object getTargetSchema() {
        return this.targetSchema;
    }

    /* renamed from: component10, reason: from getter */
    public final Script getScript() {
        return this.script;
    }

    /* renamed from: component11, reason: from getter */
    public final Target getTarget() {
        return this.target;
    }

    /* renamed from: component12, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<Object> component5() {
        return this.externalData;
    }

    /* renamed from: component6, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component7, reason: from getter */
    public final Source getSource() {
        return this.source;
    }

    /* renamed from: component8, reason: from getter */
    public final SourceSchema getSourceSchema() {
        return this.sourceSchema;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getRegionLevel() {
        return this.regionLevel;
    }

    public final ContentItem copy(Object targetSchema, String createdAt, String regionGroupId, String name, List<? extends Object> externalData, String id2, Source source, SourceSchema sourceSchema, Integer regionLevel, Script script, Target target, String updatedAt) {
        return new ContentItem(targetSchema, createdAt, regionGroupId, name, externalData, id2, source, sourceSchema, regionLevel, script, target, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentItem)) {
            return false;
        }
        ContentItem contentItem = (ContentItem) other;
        return Intrinsics.areEqual(this.targetSchema, contentItem.targetSchema) && Intrinsics.areEqual(this.createdAt, contentItem.createdAt) && Intrinsics.areEqual(this.regionGroupId, contentItem.regionGroupId) && Intrinsics.areEqual(this.name, contentItem.name) && Intrinsics.areEqual(this.externalData, contentItem.externalData) && Intrinsics.areEqual(this.id, contentItem.id) && Intrinsics.areEqual(this.source, contentItem.source) && Intrinsics.areEqual(this.sourceSchema, contentItem.sourceSchema) && Intrinsics.areEqual(this.regionLevel, contentItem.regionLevel) && Intrinsics.areEqual(this.script, contentItem.script) && Intrinsics.areEqual(this.target, contentItem.target) && Intrinsics.areEqual(this.updatedAt, contentItem.updatedAt);
    }

    public int hashCode() {
        Object obj = this.targetSchema;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        String str = this.createdAt;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.regionGroupId;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<Object> list = this.externalData;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        String str4 = this.id;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Source source = this.source;
        int iHashCode7 = (iHashCode6 + (source == null ? 0 : source.hashCode())) * 31;
        SourceSchema sourceSchema = this.sourceSchema;
        int iHashCode8 = (iHashCode7 + (sourceSchema == null ? 0 : sourceSchema.hashCode())) * 31;
        Integer num = this.regionLevel;
        int iHashCode9 = (iHashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        Script script = this.script;
        int iHashCode10 = (iHashCode9 + (script == null ? 0 : script.hashCode())) * 31;
        Target target = this.target;
        int iHashCode11 = (iHashCode10 + (target == null ? 0 : target.hashCode())) * 31;
        String str5 = this.updatedAt;
        return iHashCode11 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "ContentItem(targetSchema=" + this.targetSchema + ", createdAt=" + this.createdAt + ", regionGroupId=" + this.regionGroupId + ", name=" + this.name + ", externalData=" + this.externalData + ", id=" + this.id + ", source=" + this.source + ", sourceSchema=" + this.sourceSchema + ", regionLevel=" + this.regionLevel + ", script=" + this.script + ", target=" + this.target + ", updatedAt=" + this.updatedAt + ")";
    }

    public ContentItem(Object obj, String str, String str2, String str3, List<? extends Object> list, String str4, Source source, SourceSchema sourceSchema, Integer num, Script script, Target target, String str5) {
        this.targetSchema = obj;
        this.createdAt = str;
        this.regionGroupId = str2;
        this.name = str3;
        this.externalData = list;
        this.id = str4;
        this.source = source;
        this.sourceSchema = sourceSchema;
        this.regionLevel = num;
        this.script = script;
        this.target = target;
        this.updatedAt = str5;
    }

    public /* synthetic */ ContentItem(Object obj, String str, String str2, String str3, List list, String str4, Source source, SourceSchema sourceSchema, Integer num, Script script, Target target, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : list, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : source, (i & 128) != 0 ? null : sourceSchema, (i & 256) != 0 ? null : num, (i & 512) != 0 ? null : script, (i & 1024) != 0 ? null : target, (i & 2048) == 0 ? str5 : null);
    }

    public final Object getTargetSchema() {
        return this.targetSchema;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getRegionGroupId() {
        return this.regionGroupId;
    }

    public final String getName() {
        return this.name;
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

    public final SourceSchema getSourceSchema() {
        return this.sourceSchema;
    }

    public final Integer getRegionLevel() {
        return this.regionLevel;
    }

    public final Script getScript() {
        return this.script;
    }

    public final Target getTarget() {
        return this.target;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }
}
