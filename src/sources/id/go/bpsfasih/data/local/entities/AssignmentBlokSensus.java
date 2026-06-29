package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentBlokSensus.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0010J^\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentBlokSensus;", "", "()V", DownloadModel.ID, "", "userId", "blokSensusId", "surveyPeriodeId", "doneListing", "", "doneTarikSample", "doneTarikSampleOffline", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getBlokSensusId", "()Ljava/lang/String;", "getDoneListing", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDoneTarikSample", "getDoneTarikSampleOffline", "getId", "getSurveyPeriodeId", "getUserId", "setUserId", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lid/go/bpsfasih/data/local/entities/AssignmentBlokSensus;", "equals", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentBlokSensus {
    private final String blokSensusId;
    private final Boolean doneListing;
    private final Boolean doneTarikSample;
    private final Boolean doneTarikSampleOffline;
    private final String id;
    private final String surveyPeriodeId;
    private String userId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ AssignmentBlokSensus copy$default(AssignmentBlokSensus assignmentBlokSensus, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assignmentBlokSensus.id;
        }
        if ((i & 2) != 0) {
            str2 = assignmentBlokSensus.userId;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = assignmentBlokSensus.blokSensusId;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = assignmentBlokSensus.surveyPeriodeId;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            bool = assignmentBlokSensus.doneListing;
        }
        Boolean bool4 = bool;
        if ((i & 32) != 0) {
            bool2 = assignmentBlokSensus.doneTarikSample;
        }
        Boolean bool5 = bool2;
        if ((i & 64) != 0) {
            bool3 = assignmentBlokSensus.doneTarikSampleOffline;
        }
        return assignmentBlokSensus.copy(str, str5, str6, str7, bool4, bool5, bool3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getBlokSensusId() {
        return this.blokSensusId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getDoneListing() {
        return this.doneListing;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getDoneTarikSample() {
        return this.doneTarikSample;
    }

    /* renamed from: component7, reason: from getter */
    public final Boolean getDoneTarikSampleOffline() {
        return this.doneTarikSampleOffline;
    }

    public final AssignmentBlokSensus copy(String id2, String userId, String blokSensusId, String surveyPeriodeId, Boolean doneListing, Boolean doneTarikSample, Boolean doneTarikSampleOffline) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new AssignmentBlokSensus(id2, userId, blokSensusId, surveyPeriodeId, doneListing, doneTarikSample, doneTarikSampleOffline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentBlokSensus)) {
            return false;
        }
        AssignmentBlokSensus assignmentBlokSensus = (AssignmentBlokSensus) other;
        return Intrinsics.areEqual(this.id, assignmentBlokSensus.id) && Intrinsics.areEqual(this.userId, assignmentBlokSensus.userId) && Intrinsics.areEqual(this.blokSensusId, assignmentBlokSensus.blokSensusId) && Intrinsics.areEqual(this.surveyPeriodeId, assignmentBlokSensus.surveyPeriodeId) && Intrinsics.areEqual(this.doneListing, assignmentBlokSensus.doneListing) && Intrinsics.areEqual(this.doneTarikSample, assignmentBlokSensus.doneTarikSample) && Intrinsics.areEqual(this.doneTarikSampleOffline, assignmentBlokSensus.doneTarikSampleOffline);
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.userId.hashCode()) * 31;
        String str = this.blokSensusId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.surveyPeriodeId;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.doneListing;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.doneTarikSample;
        int iHashCode5 = (iHashCode4 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.doneTarikSampleOffline;
        return iHashCode5 + (bool3 != null ? bool3.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentBlokSensus(id=" + this.id + ", userId=" + this.userId + ", blokSensusId=" + this.blokSensusId + ", surveyPeriodeId=" + this.surveyPeriodeId + ", doneListing=" + this.doneListing + ", doneTarikSample=" + this.doneTarikSample + ", doneTarikSampleOffline=" + this.doneTarikSampleOffline + ")";
    }

    public AssignmentBlokSensus(String id2, String userId, String str, String str2, Boolean bool, Boolean bool2, Boolean bool3) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.id = id2;
        this.userId = userId;
        this.blokSensusId = str;
        this.surveyPeriodeId = str2;
        this.doneListing = bool;
        this.doneTarikSample = bool2;
        this.doneTarikSampleOffline = bool3;
    }

    public final String getId() {
        return this.id;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final String getBlokSensusId() {
        return this.blokSensusId;
    }

    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    public final Boolean getDoneListing() {
        return this.doneListing;
    }

    public final Boolean getDoneTarikSample() {
        return this.doneTarikSample;
    }

    public /* synthetic */ AssignmentBlokSensus(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Boolean bool3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, bool, bool2, (i & 64) != 0 ? false : bool3);
    }

    public final Boolean getDoneTarikSampleOffline() {
        return this.doneTarikSampleOffline;
    }

    public AssignmentBlokSensus() {
        this("", "", "", "", false, false, false);
    }

    /* compiled from: AssignmentBlokSensus.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentBlokSensus$Companion;", "", "()V", "mapIdToAssignmetnBlokSensus", "", "Lid/go/bpsfasih/data/local/entities/AssignmentBlokSensus;", "assignmentBlokSensus", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<AssignmentBlokSensus> mapIdToAssignmetnBlokSensus(List<AssignmentBlokSensus> assignmentBlokSensus) {
            Intrinsics.checkNotNullParameter(assignmentBlokSensus, "assignmentBlokSensus");
            Iterator<T> it = assignmentBlokSensus.iterator();
            while (it.hasNext()) {
                ((AssignmentBlokSensus) it.next()).setUserId(FasihApp.INSTANCE.getSession().getUserId().toString());
            }
            return assignmentBlokSensus;
        }
    }
}
