package id.go.bpsfasih.ui.bantuan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BantuanActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lid/go/bpsfasih/ui/bantuan/SurveyWithPeriods;", "", DownloadModel.ID, "", "name", "periods", "", "Lid/go/bpsfasih/ui/bantuan/Periode;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getPeriods", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyWithPeriods {
    public static final int $stable = 8;
    private final String id;
    private final String name;
    private final List<Periode> periods;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SurveyWithPeriods copy$default(SurveyWithPeriods surveyWithPeriods, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = surveyWithPeriods.id;
        }
        if ((i & 2) != 0) {
            str2 = surveyWithPeriods.name;
        }
        if ((i & 4) != 0) {
            list = surveyWithPeriods.periods;
        }
        return surveyWithPeriods.copy(str, str2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<Periode> component3() {
        return this.periods;
    }

    public final SurveyWithPeriods copy(String id2, String name, List<Periode> periods) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(periods, "periods");
        return new SurveyWithPeriods(id2, name, periods);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyWithPeriods)) {
            return false;
        }
        SurveyWithPeriods surveyWithPeriods = (SurveyWithPeriods) other;
        return Intrinsics.areEqual(this.id, surveyWithPeriods.id) && Intrinsics.areEqual(this.name, surveyWithPeriods.name) && Intrinsics.areEqual(this.periods, surveyWithPeriods.periods);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.periods.hashCode();
    }

    public String toString() {
        return "SurveyWithPeriods(id=" + this.id + ", name=" + this.name + ", periods=" + this.periods + ")";
    }

    public SurveyWithPeriods(String id2, String name, List<Periode> periods) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(periods, "periods");
        this.id = id2;
        this.name = name;
        this.periods = periods;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final List<Periode> getPeriods() {
        return this.periods;
    }
}
