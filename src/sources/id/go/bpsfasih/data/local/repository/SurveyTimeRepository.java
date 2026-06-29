package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.dao.SurveyTimeDAO;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.SurveyTimeEntity;
import id.go.bpsfasih.data.local.pojo.Sync;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyTimeRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u0017\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0018\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0019\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/data/local/repository/SurveyTimeRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/SurveyTimeDAO;", "(Lid/go/bpsfasih/data/local/dao/SurveyTimeDAO;)V", "assignmentDao", "Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "getAssignmentDao", "()Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "userId", "", "getUserId", "()Ljava/lang/String;", "setUserId", "(Ljava/lang/String;)V", "insert", "", "item", "Lid/go/bpsfasih/data/local/entities/SurveyTimeEntity;", "assignmentId", "updateStatusSurveyTime", NotificationCompat.CATEGORY_STATUS, "", "updateStatusSurveyTimeByAssignmentToApproved", "updateStatusSurveyTimeByAssignmentToPending", "updateStatusSurveyTimeByAssignmentToRejected", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyTimeRepository {
    public static final int $stable = 8;
    private final AssigmentDAO assignmentDao;
    private final SurveyTimeDAO dao;
    private String userId;

    public SurveyTimeRepository(SurveyTimeDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
        this.userId = FasihApp.INSTANCE.getSession().getUserId();
        this.assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final AssigmentDAO getAssignmentDao() {
        return this.assignmentDao;
    }

    public final void insert(final SurveyTimeEntity item, final String assignmentId) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyTimeRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SurveyTimeRepository.insert$lambda$6(assignmentId, this, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$6(String str, SurveyTimeRepository this$0, SurveyTimeEntity item) {
        Integer assignmentStatusId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        if (str != null) {
            String userId = FasihApp.INSTANCE.getSession().getUserId();
            this$0.userId = userId;
            SurveyTimeEntity surveyTimeByAssignment = this$0.dao.getSurveyTimeByAssignment(str, userId);
            Sync assignmentById = this$0.assignmentDao.getAssignmentById(str);
            if (surveyTimeByAssignment != null) {
                if (surveyTimeByAssignment.getStatus() == CommonCons.INSTANCE.getASSIGNMENT_STATUS_APPROVED()) {
                    surveyTimeByAssignment.getStatus();
                    CommonCons.INSTANCE.getASSIGNMENT_STATUS_APPROVED();
                }
                this$0.dao.updateItem(item);
                return;
            }
            AssignmentEntity assignment = assignmentById.getAssignment();
            if (assignment == null || (assignmentStatusId = assignment.getAssignmentStatusId()) == null || assignmentStatusId.intValue() != CommonCons.INSTANCE.getASSIGNMENT_STATUS_OPEN()) {
                return;
            }
            this$0.dao.insert(item);
        }
    }

    public final void updateStatusSurveyTimeByAssignmentToPending(String assignmentId) {
        updateStatusSurveyTime(CommonCons.INSTANCE.getASSIGNMENT_STATUS_PENDING(), assignmentId);
    }

    public final void updateStatusSurveyTimeByAssignmentToApproved(String assignmentId) {
        this.dao.updateStatusSurveyTimeByAssignment(CommonCons.INSTANCE.getASSIGNMENT_STATUS_APPROVED(), assignmentId, this.userId);
    }

    public final void updateStatusSurveyTimeByAssignmentToRejected(String assignmentId) {
        this.dao.updateStatusSurveyTimeByAssignment(CommonCons.INSTANCE.getASSIGNMENT_STATUS_REJECTED(), assignmentId, this.userId);
    }

    private final void updateStatusSurveyTime(int status, String assignmentId) {
        String userId = FasihApp.INSTANCE.getSession().getUserId();
        this.userId = userId;
        this.dao.updateStatusSurveyTimeByAssignment(status, assignmentId, userId);
    }
}
