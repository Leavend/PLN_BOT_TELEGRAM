package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecapModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/MetadataModel;", "", DownloadModel.ID, "", "columnName", "desc", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getColumnName", "()Ljava/lang/String;", "getDesc", "getId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class MetadataModel {
    public static final int $stable = 0;

    @SerializedName("columnName")
    private final String columnName;

    @SerializedName("desc")
    private final String desc;

    @SerializedName(DownloadModel.ID)
    private final String id;

    public MetadataModel() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ MetadataModel copy$default(MetadataModel metadataModel, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = metadataModel.id;
        }
        if ((i & 2) != 0) {
            str2 = metadataModel.columnName;
        }
        if ((i & 4) != 0) {
            str3 = metadataModel.desc;
        }
        return metadataModel.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getColumnName() {
        return this.columnName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final MetadataModel copy(String id2, String columnName, String desc) {
        return new MetadataModel(id2, columnName, desc);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataModel)) {
            return false;
        }
        MetadataModel metadataModel = (MetadataModel) other;
        return Intrinsics.areEqual(this.id, metadataModel.id) && Intrinsics.areEqual(this.columnName, metadataModel.columnName) && Intrinsics.areEqual(this.desc, metadataModel.desc);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.columnName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.desc;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "MetadataModel(id=" + this.id + ", columnName=" + this.columnName + ", desc=" + this.desc + ")";
    }

    public MetadataModel(String str, String str2, String str3) {
        this.id = str;
        this.columnName = str2;
        this.desc = str3;
    }

    public /* synthetic */ MetadataModel(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getId() {
        return this.id;
    }

    public final String getColumnName() {
        return this.columnName;
    }

    public final String getDesc() {
        return this.desc;
    }
}
