package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: FormEngineEntity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b&\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0013\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u008e\u0001\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\b\u0002\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010*J\u0013\u0010+\u001a\u00020\u00032\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0005HÖ\u0001J\t\u0010.\u001a\u00020\tHÖ\u0001R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\f\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u000e\u0010\u0017R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0002\u0010\u0017R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0018\u0010\r\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R \u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012¨\u0006/"}, d2 = {"Lid/go/bpsfasih/data/local/entities/FormEngineEntity;", "", "isForce", "", "formEngineId", "", "userIds", "", "linkDownload", "", "modifiedBy", "basePath", DownloadModel.ID, "message", "isDefault", ClientCookie.VERSION_ATTR, "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "getBasePath", "()Ljava/lang/String;", "getFormEngineId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLinkDownload", "getMessage", "getModifiedBy", "getUserIds", "()Ljava/util/List;", "getVersion", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lid/go/bpsfasih/data/local/entities/FormEngineEntity;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class FormEngineEntity {
    public static final int $stable = 8;

    @SerializedName("base_path")
    private final String basePath;

    @SerializedName("form_engine_id")
    private final Integer formEngineId;

    @SerializedName("_id")
    private final String id;

    @SerializedName("is_default")
    private final Boolean isDefault;

    @SerializedName("is_force")
    private final Boolean isForce;

    @SerializedName("linkDownload")
    private final String linkDownload;

    @SerializedName("message")
    private final String message;

    @SerializedName("modified_by")
    private final String modifiedBy;

    @SerializedName("user_ids")
    private final List<Object> userIds;

    @SerializedName(ClientCookie.VERSION_ATTR)
    private final String version;

    public FormEngineEntity() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getIsForce() {
        return this.isForce;
    }

    /* renamed from: component10, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getFormEngineId() {
        return this.formEngineId;
    }

    public final List<Object> component3() {
        return this.userIds;
    }

    /* renamed from: component4, reason: from getter */
    public final String getLinkDownload() {
        return this.linkDownload;
    }

    /* renamed from: component5, reason: from getter */
    public final String getModifiedBy() {
        return this.modifiedBy;
    }

    /* renamed from: component6, reason: from getter */
    public final String getBasePath() {
        return this.basePath;
    }

    /* renamed from: component7, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component8, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component9, reason: from getter */
    public final Boolean getIsDefault() {
        return this.isDefault;
    }

    public final FormEngineEntity copy(Boolean isForce, Integer formEngineId, List<? extends Object> userIds, String linkDownload, String modifiedBy, String basePath, String id2, String message, Boolean isDefault, String version) {
        return new FormEngineEntity(isForce, formEngineId, userIds, linkDownload, modifiedBy, basePath, id2, message, isDefault, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FormEngineEntity)) {
            return false;
        }
        FormEngineEntity formEngineEntity = (FormEngineEntity) other;
        return Intrinsics.areEqual(this.isForce, formEngineEntity.isForce) && Intrinsics.areEqual(this.formEngineId, formEngineEntity.formEngineId) && Intrinsics.areEqual(this.userIds, formEngineEntity.userIds) && Intrinsics.areEqual(this.linkDownload, formEngineEntity.linkDownload) && Intrinsics.areEqual(this.modifiedBy, formEngineEntity.modifiedBy) && Intrinsics.areEqual(this.basePath, formEngineEntity.basePath) && Intrinsics.areEqual(this.id, formEngineEntity.id) && Intrinsics.areEqual(this.message, formEngineEntity.message) && Intrinsics.areEqual(this.isDefault, formEngineEntity.isDefault) && Intrinsics.areEqual(this.version, formEngineEntity.version);
    }

    public int hashCode() {
        Boolean bool = this.isForce;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Integer num = this.formEngineId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        List<Object> list = this.userIds;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.linkDownload;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.modifiedBy;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.basePath;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.id;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.message;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool2 = this.isDefault;
        int iHashCode9 = (iHashCode8 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str6 = this.version;
        return iHashCode9 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "FormEngineEntity(isForce=" + this.isForce + ", formEngineId=" + this.formEngineId + ", userIds=" + this.userIds + ", linkDownload=" + this.linkDownload + ", modifiedBy=" + this.modifiedBy + ", basePath=" + this.basePath + ", id=" + this.id + ", message=" + this.message + ", isDefault=" + this.isDefault + ", version=" + this.version + ")";
    }

    public FormEngineEntity(Boolean bool, Integer num, List<? extends Object> list, String str, String str2, String str3, String str4, String str5, Boolean bool2, String str6) {
        this.isForce = bool;
        this.formEngineId = num;
        this.userIds = list;
        this.linkDownload = str;
        this.modifiedBy = str2;
        this.basePath = str3;
        this.id = str4;
        this.message = str5;
        this.isDefault = bool2;
        this.version = str6;
    }

    public /* synthetic */ FormEngineEntity(Boolean bool, Integer num, List list, String str, String str2, String str3, String str4, String str5, Boolean bool2, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : bool2, (i & 512) == 0 ? str6 : null);
    }

    public final Boolean isForce() {
        return this.isForce;
    }

    public final Integer getFormEngineId() {
        return this.formEngineId;
    }

    public final List<Object> getUserIds() {
        return this.userIds;
    }

    public final String getLinkDownload() {
        return this.linkDownload;
    }

    public final String getModifiedBy() {
        return this.modifiedBy;
    }

    public final String getBasePath() {
        return this.basePath;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Boolean isDefault() {
        return this.isDefault;
    }

    public final String getVersion() {
        return this.version;
    }
}
