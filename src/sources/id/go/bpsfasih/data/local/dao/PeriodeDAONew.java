package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.pojo.PeriodePojo;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PeriodeDAONew.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H'J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J \u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0005H'J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005H'J(\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\b\b\u0002\u0010\r\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J1\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u000bH'¢\u0006\u0002\u0010\u0014J&\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J&\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\b0\u00072\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\tH'J\u0016\u0010\u001d\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bH'J0\u0010\u001f\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u00052\b\u0010!\u001a\u0004\u0018\u00010\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0005H'¨\u0006#"}, d2 = {"Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;", "", "deleteAll", "", "userId", "", "getAllPeriode", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getJumlahPeriode", "", "getListPeriodeBySurvey", "surveyId", "getPeriodeById", "periodeId", "getPeriodeByPrimaryId", "periodeIdPrimary", "getPeriodeBySurvey", "getPeriodeBySurveyByUser", "(Ljava/lang/Integer;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "getPeriodeBySurveysId", "surveyIds", "getPeriodeByUserId", "getPeriodePojoBySurvey", "Lid/go/bpsfasih/data/local/pojo/PeriodePojo;", "getSurveyRoleUserId", "insert", "Survey", "insertAll", "listItems", "updatePeriode", "name", "startDate", "endDate", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface PeriodeDAONew {
    void deleteAll(String userId);

    LiveData<List<PeriodeEntityNew>> getAllPeriode();

    LiveData<Integer> getJumlahPeriode(String userId);

    List<PeriodeEntityNew> getListPeriodeBySurvey(String surveyId, String userId);

    PeriodeEntityNew getPeriodeById(String periodeId);

    PeriodeEntityNew getPeriodeByPrimaryId(String periodeIdPrimary);

    LiveData<List<PeriodeEntityNew>> getPeriodeBySurvey(String surveyId, String userId);

    LiveData<List<PeriodeEntityNew>> getPeriodeBySurveyByUser(Integer surveyId, Integer userId);

    List<PeriodeEntityNew> getPeriodeBySurveysId(List<String> surveyIds, String userId);

    List<PeriodeEntityNew> getPeriodeByUserId(String userId);

    LiveData<List<PeriodePojo>> getPeriodePojoBySurvey(String surveyId, String userId);

    String getSurveyRoleUserId(String periodeId, String userId);

    void insert(PeriodeEntityNew Survey);

    void insertAll(List<PeriodeEntityNew> listItems);

    void updatePeriode(String periodeId, String name, String startDate, String endDate);

    /* compiled from: PeriodeDAONew.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ LiveData getPeriodeBySurvey$default(PeriodeDAONew periodeDAONew, String str, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPeriodeBySurvey");
            }
            if ((i & 1) != 0) {
                str = "";
            }
            return periodeDAONew.getPeriodeBySurvey(str, str2);
        }

        public static /* synthetic */ LiveData getPeriodeBySurveyByUser$default(PeriodeDAONew periodeDAONew, Integer num, Integer num2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPeriodeBySurveyByUser");
            }
            if ((i & 1) != 0) {
                num = -1;
            }
            if ((i & 2) != 0) {
                num2 = -1;
            }
            return periodeDAONew.getPeriodeBySurveyByUser(num, num2);
        }
    }
}
