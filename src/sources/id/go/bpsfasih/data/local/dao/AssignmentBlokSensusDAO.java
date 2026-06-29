package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentBlokSensus;
import kotlin.Metadata;

/* compiled from: AssignmentBlokSensusDAO.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0007H'J$\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H'J+\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H'¢\u0006\u0002\u0010\u0011J+\u0010\u0012\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H'¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/local/dao/AssignmentBlokSensusDAO;", "", "deleteAll", "", "userId", "", "getStatusBSBySurveyPeriodeId", "Lid/go/bpsfasih/data/local/entities/AssignmentBlokSensus;", "blokSensusId", "surveyPeriodeId", "insert", "assignmentBlokSensus", "setTarikSampleOffline", "isTarikSampelOfflineDona", "", "updateStatusListingDone", "isDone", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "updateStatusTarikSample", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssignmentBlokSensusDAO {
    void deleteAll(String userId);

    AssignmentBlokSensus getStatusBSBySurveyPeriodeId(String blokSensusId, String surveyPeriodeId);

    void insert(AssignmentBlokSensus assignmentBlokSensus);

    void setTarikSampleOffline(boolean isTarikSampelOfflineDona, String blokSensusId, String surveyPeriodeId);

    void updateStatusListingDone(Boolean isDone, String blokSensusId, String surveyPeriodeId);

    void updateStatusTarikSample(Boolean isDone, String blokSensusId, String surveyPeriodeId);
}
