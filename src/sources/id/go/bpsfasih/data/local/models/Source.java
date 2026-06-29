package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TarikSampleModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lid/go/bpsfasih/data/local/models/Source;", "", "surveyId", "", "surveyName", "name", "templateVersion", DownloadModel.ID, "templateId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getSurveyId", "getSurveyName", "getTemplateId", "getTemplateVersion", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Source {
    public static final int $stable = 0;

    @SerializedName(DownloadModel.ID)
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("surveyId")
    private final String surveyId;

    @SerializedName("surveyName")
    private final String surveyName;

    @SerializedName("templateId")
    private final String templateId;

    @SerializedName("templateVersion")
    private final String templateVersion;

    public Source() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ Source copy$default(Source source, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = source.surveyId;
        }
        if ((i & 2) != 0) {
            str2 = source.surveyName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = source.name;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = source.templateVersion;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = source.id;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = source.templateId;
        }
        return source.copy(str, str7, str8, str9, str10, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSurveyName() {
        return this.surveyName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTemplateId() {
        return this.templateId;
    }

    public final Source copy(String surveyId, String surveyName, String name, String templateVersion, String id2, String templateId) {
        return new Source(surveyId, surveyName, name, templateVersion, id2, templateId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Source)) {
            return false;
        }
        Source source = (Source) other;
        return Intrinsics.areEqual(this.surveyId, source.surveyId) && Intrinsics.areEqual(this.surveyName, source.surveyName) && Intrinsics.areEqual(this.name, source.name) && Intrinsics.areEqual(this.templateVersion, source.templateVersion) && Intrinsics.areEqual(this.id, source.id) && Intrinsics.areEqual(this.templateId, source.templateId);
    }

    public int hashCode() {
        String str = this.surveyId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.surveyName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.templateVersion;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.id;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.templateId;
        return iHashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "Source(surveyId=" + this.surveyId + ", surveyName=" + this.surveyName + ", name=" + this.name + ", templateVersion=" + this.templateVersion + ", id=" + this.id + ", templateId=" + this.templateId + ")";
    }

    public Source(String str, String str2, String str3, String str4, String str5, String str6) {
        this.surveyId = str;
        this.surveyName = str2;
        this.name = str3;
        this.templateVersion = str4;
        this.id = str5;
        this.templateId = str6;
    }

    public /* synthetic */ Source(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6);
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getSurveyName() {
        return this.surveyName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getTemplateVersion() {
        return this.templateVersion;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTemplateId() {
        return this.templateId;
    }
}
