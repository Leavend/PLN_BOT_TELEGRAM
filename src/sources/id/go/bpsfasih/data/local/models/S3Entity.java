package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: S3Model.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/S3Entity;", "", "presignedUrls", "", "Lid/go/bpsfasih/data/local/models/PresignedUrlsItem;", "assignmentId", "", "(Ljava/util/List;Ljava/lang/String;)V", "getAssignmentId", "()Ljava/lang/String;", "getPresignedUrls", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class S3Entity {
    public static final int $stable = 8;

    @SerializedName("assignmentId")
    private final String assignmentId;

    @SerializedName("presignedUrls")
    private final List<PresignedUrlsItem> presignedUrls;

    /* JADX WARN: Multi-variable type inference failed */
    public S3Entity() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ S3Entity copy$default(S3Entity s3Entity, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = s3Entity.presignedUrls;
        }
        if ((i & 2) != 0) {
            str = s3Entity.assignmentId;
        }
        return s3Entity.copy(list, str);
    }

    public final List<PresignedUrlsItem> component1() {
        return this.presignedUrls;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final S3Entity copy(List<PresignedUrlsItem> presignedUrls, String assignmentId) {
        return new S3Entity(presignedUrls, assignmentId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof S3Entity)) {
            return false;
        }
        S3Entity s3Entity = (S3Entity) other;
        return Intrinsics.areEqual(this.presignedUrls, s3Entity.presignedUrls) && Intrinsics.areEqual(this.assignmentId, s3Entity.assignmentId);
    }

    public int hashCode() {
        List<PresignedUrlsItem> list = this.presignedUrls;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.assignmentId;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "S3Entity(presignedUrls=" + this.presignedUrls + ", assignmentId=" + this.assignmentId + ")";
    }

    public S3Entity(List<PresignedUrlsItem> list, String str) {
        this.presignedUrls = list;
        this.assignmentId = str;
    }

    public /* synthetic */ S3Entity(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : str);
    }

    public final List<PresignedUrlsItem> getPresignedUrls() {
        return this.presignedUrls;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }
}
