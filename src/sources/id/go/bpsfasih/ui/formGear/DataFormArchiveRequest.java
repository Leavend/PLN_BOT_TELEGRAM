package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataFormArchiveHelper.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/ui/formGear/DataFormArchiveRequest;", "", "assignmentId", "", "sourceDirectory", "Ljava/io/File;", "tempDirectory", "overrideDataJson", "(Ljava/lang/String;Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "getAssignmentId", "()Ljava/lang/String;", "getOverrideDataJson", "getSourceDirectory", "()Ljava/io/File;", "getTempDirectory", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataFormArchiveRequest {
    private final String assignmentId;
    private final String overrideDataJson;
    private final File sourceDirectory;
    private final File tempDirectory;

    public static /* synthetic */ DataFormArchiveRequest copy$default(DataFormArchiveRequest dataFormArchiveRequest, String str, File file, File file2, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dataFormArchiveRequest.assignmentId;
        }
        if ((i & 2) != 0) {
            file = dataFormArchiveRequest.sourceDirectory;
        }
        if ((i & 4) != 0) {
            file2 = dataFormArchiveRequest.tempDirectory;
        }
        if ((i & 8) != 0) {
            str2 = dataFormArchiveRequest.overrideDataJson;
        }
        return dataFormArchiveRequest.copy(str, file, file2, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component2, reason: from getter */
    public final File getSourceDirectory() {
        return this.sourceDirectory;
    }

    /* renamed from: component3, reason: from getter */
    public final File getTempDirectory() {
        return this.tempDirectory;
    }

    /* renamed from: component4, reason: from getter */
    public final String getOverrideDataJson() {
        return this.overrideDataJson;
    }

    public final DataFormArchiveRequest copy(String assignmentId, File sourceDirectory, File tempDirectory, String overrideDataJson) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(sourceDirectory, "sourceDirectory");
        Intrinsics.checkNotNullParameter(tempDirectory, "tempDirectory");
        return new DataFormArchiveRequest(assignmentId, sourceDirectory, tempDirectory, overrideDataJson);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataFormArchiveRequest)) {
            return false;
        }
        DataFormArchiveRequest dataFormArchiveRequest = (DataFormArchiveRequest) other;
        return Intrinsics.areEqual(this.assignmentId, dataFormArchiveRequest.assignmentId) && Intrinsics.areEqual(this.sourceDirectory, dataFormArchiveRequest.sourceDirectory) && Intrinsics.areEqual(this.tempDirectory, dataFormArchiveRequest.tempDirectory) && Intrinsics.areEqual(this.overrideDataJson, dataFormArchiveRequest.overrideDataJson);
    }

    public int hashCode() {
        int iHashCode = ((((this.assignmentId.hashCode() * 31) + this.sourceDirectory.hashCode()) * 31) + this.tempDirectory.hashCode()) * 31;
        String str = this.overrideDataJson;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "DataFormArchiveRequest(assignmentId=" + this.assignmentId + ", sourceDirectory=" + this.sourceDirectory + ", tempDirectory=" + this.tempDirectory + ", overrideDataJson=" + this.overrideDataJson + ")";
    }

    public DataFormArchiveRequest(String assignmentId, File sourceDirectory, File tempDirectory, String str) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(sourceDirectory, "sourceDirectory");
        Intrinsics.checkNotNullParameter(tempDirectory, "tempDirectory");
        this.assignmentId = assignmentId;
        this.sourceDirectory = sourceDirectory;
        this.tempDirectory = tempDirectory;
        this.overrideDataJson = str;
    }

    public /* synthetic */ DataFormArchiveRequest(String str, File file, File file2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, file, file2, (i & 8) != 0 ? null : str2);
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final File getSourceDirectory() {
        return this.sourceDirectory;
    }

    public final File getTempDirectory() {
        return this.tempDirectory;
    }

    public final String getOverrideDataJson() {
        return this.overrideDataJson;
    }
}
