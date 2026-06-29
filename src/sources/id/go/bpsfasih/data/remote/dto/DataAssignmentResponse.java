package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataAssignmentResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/DataAssignmentResponse;", "", "total", "", FirebaseAnalytics.Param.CONTENT, "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "(ILjava/util/List;)V", "getContent", "()Ljava/util/List;", "getTotal", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataAssignmentResponse {
    public static final int $stable = 8;
    private final List<AssignmentEntity> content;
    private final int total;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DataAssignmentResponse copy$default(DataAssignmentResponse dataAssignmentResponse, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = dataAssignmentResponse.total;
        }
        if ((i2 & 2) != 0) {
            list = dataAssignmentResponse.content;
        }
        return dataAssignmentResponse.copy(i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    public final List<AssignmentEntity> component2() {
        return this.content;
    }

    public final DataAssignmentResponse copy(int total, List<AssignmentEntity> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return new DataAssignmentResponse(total, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataAssignmentResponse)) {
            return false;
        }
        DataAssignmentResponse dataAssignmentResponse = (DataAssignmentResponse) other;
        return this.total == dataAssignmentResponse.total && Intrinsics.areEqual(this.content, dataAssignmentResponse.content);
    }

    public int hashCode() {
        return (Integer.hashCode(this.total) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "DataAssignmentResponse(total=" + this.total + ", content=" + this.content + ")";
    }

    public DataAssignmentResponse(int i, List<AssignmentEntity> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.total = i;
        this.content = content;
    }

    public final int getTotal() {
        return this.total;
    }

    public final List<AssignmentEntity> getContent() {
        return this.content;
    }
}
