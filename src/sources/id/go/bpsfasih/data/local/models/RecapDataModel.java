package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecapModel.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/data/local/models/RecapDataModel;", "", "recap", "Lid/go/bpsfasih/data/local/models/RecapModel;", "metadatas", "", "Lid/go/bpsfasih/data/local/models/MetadataModel;", "(Lid/go/bpsfasih/data/local/models/RecapModel;Ljava/util/List;)V", "getMetadatas", "()Ljava/util/List;", "getRecap", "()Lid/go/bpsfasih/data/local/models/RecapModel;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class RecapDataModel {
    public static final int $stable = 8;

    @SerializedName("metadatas")
    private final List<MetadataModel> metadatas;

    @SerializedName("recap")
    private final RecapModel recap;

    /* JADX WARN: Multi-variable type inference failed */
    public RecapDataModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RecapDataModel copy$default(RecapDataModel recapDataModel, RecapModel recapModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            recapModel = recapDataModel.recap;
        }
        if ((i & 2) != 0) {
            list = recapDataModel.metadatas;
        }
        return recapDataModel.copy(recapModel, list);
    }

    /* renamed from: component1, reason: from getter */
    public final RecapModel getRecap() {
        return this.recap;
    }

    public final List<MetadataModel> component2() {
        return this.metadatas;
    }

    public final RecapDataModel copy(RecapModel recap, List<MetadataModel> metadatas) {
        return new RecapDataModel(recap, metadatas);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecapDataModel)) {
            return false;
        }
        RecapDataModel recapDataModel = (RecapDataModel) other;
        return Intrinsics.areEqual(this.recap, recapDataModel.recap) && Intrinsics.areEqual(this.metadatas, recapDataModel.metadatas);
    }

    public int hashCode() {
        RecapModel recapModel = this.recap;
        int iHashCode = (recapModel == null ? 0 : recapModel.hashCode()) * 31;
        List<MetadataModel> list = this.metadatas;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "RecapDataModel(recap=" + this.recap + ", metadatas=" + this.metadatas + ")";
    }

    public RecapDataModel(RecapModel recapModel, List<MetadataModel> list) {
        this.recap = recapModel;
        this.metadatas = list;
    }

    public /* synthetic */ RecapDataModel(RecapModel recapModel, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : recapModel, (i & 2) != 0 ? null : list);
    }

    public final RecapModel getRecap() {
        return this.recap;
    }

    public final List<MetadataModel> getMetadatas() {
        return this.metadatas;
    }
}
