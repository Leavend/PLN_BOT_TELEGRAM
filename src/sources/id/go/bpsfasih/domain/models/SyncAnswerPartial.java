package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncAnswerPartial.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J\u0011\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJ^\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006-"}, d2 = {"Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "", "idSurvey", "", "idPeriode", "filenameNfs", "listAssignmentIdNfs", "", "listAssignmentS3", "progress", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Double;)V", "getFilenameNfs", "()Ljava/lang/String;", "setFilenameNfs", "(Ljava/lang/String;)V", "getIdPeriode", "setIdPeriode", "getIdSurvey", "setIdSurvey", "getListAssignmentIdNfs", "()Ljava/util/List;", "setListAssignmentIdNfs", "(Ljava/util/List;)V", "getListAssignmentS3", "setListAssignmentS3", "getProgress", "()Ljava/lang/Double;", "setProgress", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Double;)Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SyncAnswerPartial {
    public static final int $stable = 8;
    private String filenameNfs;
    private String idPeriode;
    private String idSurvey;
    private List<String> listAssignmentIdNfs;
    private List<String> listAssignmentS3;
    private Double progress;

    public static /* synthetic */ SyncAnswerPartial copy$default(SyncAnswerPartial syncAnswerPartial, String str, String str2, String str3, List list, List list2, Double d, int i, Object obj) {
        if ((i & 1) != 0) {
            str = syncAnswerPartial.idSurvey;
        }
        if ((i & 2) != 0) {
            str2 = syncAnswerPartial.idPeriode;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = syncAnswerPartial.filenameNfs;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = syncAnswerPartial.listAssignmentIdNfs;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = syncAnswerPartial.listAssignmentS3;
        }
        List list4 = list2;
        if ((i & 32) != 0) {
            d = syncAnswerPartial.progress;
        }
        return syncAnswerPartial.copy(str, str4, str5, list3, list4, d);
    }

    /* renamed from: component1, reason: from getter */
    public final String getIdSurvey() {
        return this.idSurvey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getIdPeriode() {
        return this.idPeriode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFilenameNfs() {
        return this.filenameNfs;
    }

    public final List<String> component4() {
        return this.listAssignmentIdNfs;
    }

    public final List<String> component5() {
        return this.listAssignmentS3;
    }

    /* renamed from: component6, reason: from getter */
    public final Double getProgress() {
        return this.progress;
    }

    public final SyncAnswerPartial copy(String idSurvey, String idPeriode, String filenameNfs, List<String> listAssignmentIdNfs, List<String> listAssignmentS3, Double progress) {
        Intrinsics.checkNotNullParameter(idSurvey, "idSurvey");
        Intrinsics.checkNotNullParameter(idPeriode, "idPeriode");
        return new SyncAnswerPartial(idSurvey, idPeriode, filenameNfs, listAssignmentIdNfs, listAssignmentS3, progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncAnswerPartial)) {
            return false;
        }
        SyncAnswerPartial syncAnswerPartial = (SyncAnswerPartial) other;
        return Intrinsics.areEqual(this.idSurvey, syncAnswerPartial.idSurvey) && Intrinsics.areEqual(this.idPeriode, syncAnswerPartial.idPeriode) && Intrinsics.areEqual(this.filenameNfs, syncAnswerPartial.filenameNfs) && Intrinsics.areEqual(this.listAssignmentIdNfs, syncAnswerPartial.listAssignmentIdNfs) && Intrinsics.areEqual(this.listAssignmentS3, syncAnswerPartial.listAssignmentS3) && Intrinsics.areEqual((Object) this.progress, (Object) syncAnswerPartial.progress);
    }

    public int hashCode() {
        int iHashCode = ((this.idSurvey.hashCode() * 31) + this.idPeriode.hashCode()) * 31;
        String str = this.filenameNfs;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.listAssignmentIdNfs;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.listAssignmentS3;
        int iHashCode4 = (iHashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Double d = this.progress;
        return iHashCode4 + (d != null ? d.hashCode() : 0);
    }

    public String toString() {
        return "SyncAnswerPartial(idSurvey=" + this.idSurvey + ", idPeriode=" + this.idPeriode + ", filenameNfs=" + this.filenameNfs + ", listAssignmentIdNfs=" + this.listAssignmentIdNfs + ", listAssignmentS3=" + this.listAssignmentS3 + ", progress=" + this.progress + ")";
    }

    public SyncAnswerPartial(String idSurvey, String idPeriode, String str, List<String> list, List<String> list2, Double d) {
        Intrinsics.checkNotNullParameter(idSurvey, "idSurvey");
        Intrinsics.checkNotNullParameter(idPeriode, "idPeriode");
        this.idSurvey = idSurvey;
        this.idPeriode = idPeriode;
        this.filenameNfs = str;
        this.listAssignmentIdNfs = list;
        this.listAssignmentS3 = list2;
        this.progress = d;
    }

    public final String getIdSurvey() {
        return this.idSurvey;
    }

    public final void setIdSurvey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.idSurvey = str;
    }

    public final String getIdPeriode() {
        return this.idPeriode;
    }

    public final void setIdPeriode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.idPeriode = str;
    }

    public final String getFilenameNfs() {
        return this.filenameNfs;
    }

    public final void setFilenameNfs(String str) {
        this.filenameNfs = str;
    }

    public final List<String> getListAssignmentIdNfs() {
        return this.listAssignmentIdNfs;
    }

    public final void setListAssignmentIdNfs(List<String> list) {
        this.listAssignmentIdNfs = list;
    }

    public final List<String> getListAssignmentS3() {
        return this.listAssignmentS3;
    }

    public final void setListAssignmentS3(List<String> list) {
        this.listAssignmentS3 = list;
    }

    public /* synthetic */ SyncAnswerPartial(String str, String str2, String str3, List list, List list2, Double d, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, list, list2, (i & 32) != 0 ? Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : d);
    }

    public final Double getProgress() {
        return this.progress;
    }

    public final void setProgress(Double d) {
        this.progress = d;
    }
}
