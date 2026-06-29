package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentSubmitVersionEntity;
import kotlin.Metadata;

/* compiled from: AssignmentSubmitVersionDAO.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H'¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;", "", "getByAssignmentId", "Lid/go/bpsfasih/data/local/entities/AssignmentSubmitVersionEntity;", "assignmentId", "", "insert", "", "item", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssignmentSubmitVersionDAO {
    AssignmentSubmitVersionEntity getByAssignmentId(String assignmentId);

    void insert(AssignmentSubmitVersionEntity item);
}
