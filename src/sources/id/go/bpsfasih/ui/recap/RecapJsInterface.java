package id.go.bpsfasih.ui.recap;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.data.local.models.RecapDataModel;
import id.go.bpsfasih.data.local.models.RecapModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RecapActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0007J\b\u0010\u000f\u001a\u00020\nH\u0007J\b\u0010\u0010\u001a\u00020\nH\u0007J\b\u0010\u0011\u001a\u00020\nH\u0007J\b\u0010\u0012\u001a\u00020\nH\u0007J\b\u0010\u0013\u001a\u00020\nH\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/ui/recap/RecapJsInterface;", "", "recapData", "Lid/go/bpsfasih/data/local/models/RecapDataModel;", "onClose", "Lkotlin/Function0;", "", "(Lid/go/bpsfasih/data/local/models/RecapDataModel;Lkotlin/jvm/functions/Function0;)V", "closeActivity", "getAssignmentPerMonth", "", "getMonthName", "month", "", "getMostActiveMonth", "getRecapData", "getSurveyAssignment", "getSurveyName", "getTotalAssignment", "getTotalSurvey", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RecapJsInterface {
    public static final int $stable = 8;
    private final Function0<Unit> onClose;
    private final RecapDataModel recapData;

    private final String getMonthName(int month) {
        switch (month) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juni";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Desember";
            default:
                return "";
        }
    }

    public RecapJsInterface(RecapDataModel recapDataModel, Function0<Unit> onClose) {
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.recapData = recapDataModel;
        this.onClose = onClose;
    }

    @JavascriptInterface
    public final String getRecapData() {
        String json = new Gson().toJson(this.recapData);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(recapData)");
        return json;
    }

    @JavascriptInterface
    public final void closeActivity() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: id.go.bpsfasih.ui.recap.RecapJsInterface$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RecapJsInterface.closeActivity$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void closeActivity$lambda$0(RecapJsInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onClose.invoke();
    }

    @JavascriptInterface
    public final String getSurveyName() {
        RecapModel recap;
        String survei;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (survei = recap.getSurvei()) == null) ? "" : survei;
    }

    @JavascriptInterface
    public final String getTotalSurvey() {
        RecapModel recap;
        String jmlSurvei;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (jmlSurvei = recap.getJmlSurvei()) == null) ? "0" : jmlSurvei;
    }

    @JavascriptInterface
    public final String getTotalAssignment() {
        RecapModel recap;
        String jmlAssignmentAll;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (jmlAssignmentAll = recap.getJmlAssignmentAll()) == null) ? "0" : jmlAssignmentAll;
    }

    @JavascriptInterface
    public final String getMostActiveMonth() {
        RecapModel recap;
        String mostActiveMonth;
        Integer intOrNull;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (mostActiveMonth = recap.getMostActiveMonth()) == null || (intOrNull = StringsKt.toIntOrNull(mostActiveMonth)) == null) ? "" : getMonthName(intOrNull.intValue());
    }

    @JavascriptInterface
    public final String getAssignmentPerMonth() {
        RecapModel recap;
        String jmlAssignmentBulan;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (jmlAssignmentBulan = recap.getJmlAssignmentBulan()) == null) ? "0" : jmlAssignmentBulan;
    }

    @JavascriptInterface
    public final String getSurveyAssignment() {
        RecapModel recap;
        String jmlAssignmentSurvei;
        RecapDataModel recapDataModel = this.recapData;
        return (recapDataModel == null || (recap = recapDataModel.getRecap()) == null || (jmlAssignmentSurvei = recap.getJmlAssignmentSurvei()) == null) ? "0" : jmlAssignmentSurvei;
    }
}
