package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckNotificationAssignmentResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentModel;", "", "idsDownload", "", "", "idsDelete", "(Ljava/util/List;Ljava/util/List;)V", "getIdsDelete", "()Ljava/util/List;", "setIdsDelete", "(Ljava/util/List;)V", "getIdsDownload", "setIdsDownload", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class CheckNotificationAssignmentModel {
    public static final int $stable = 8;
    private List<String> idsDelete;
    private List<String> idsDownload;

    /* JADX WARN: Multi-variable type inference failed */
    public CheckNotificationAssignmentModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CheckNotificationAssignmentModel copy$default(CheckNotificationAssignmentModel checkNotificationAssignmentModel, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = checkNotificationAssignmentModel.idsDownload;
        }
        if ((i & 2) != 0) {
            list2 = checkNotificationAssignmentModel.idsDelete;
        }
        return checkNotificationAssignmentModel.copy(list, list2);
    }

    public final List<String> component1() {
        return this.idsDownload;
    }

    public final List<String> component2() {
        return this.idsDelete;
    }

    public final CheckNotificationAssignmentModel copy(List<String> idsDownload, List<String> idsDelete) {
        return new CheckNotificationAssignmentModel(idsDownload, idsDelete);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckNotificationAssignmentModel)) {
            return false;
        }
        CheckNotificationAssignmentModel checkNotificationAssignmentModel = (CheckNotificationAssignmentModel) other;
        return Intrinsics.areEqual(this.idsDownload, checkNotificationAssignmentModel.idsDownload) && Intrinsics.areEqual(this.idsDelete, checkNotificationAssignmentModel.idsDelete);
    }

    public int hashCode() {
        List<String> list = this.idsDownload;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<String> list2 = this.idsDelete;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "CheckNotificationAssignmentModel(idsDownload=" + this.idsDownload + ", idsDelete=" + this.idsDelete + ")";
    }

    public CheckNotificationAssignmentModel(List<String> list, List<String> list2) {
        this.idsDownload = list;
        this.idsDelete = list2;
    }

    public /* synthetic */ CheckNotificationAssignmentModel(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
    }

    public final List<String> getIdsDownload() {
        return this.idsDownload;
    }

    public final void setIdsDownload(List<String> list) {
        this.idsDownload = list;
    }

    public final List<String> getIdsDelete() {
        return this.idsDelete;
    }

    public final void setIdsDelete(List<String> list) {
        this.idsDelete = list;
    }
}
