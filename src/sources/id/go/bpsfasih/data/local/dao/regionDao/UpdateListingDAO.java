package id.go.bpsfasih.data.local.dao.regionDao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: UpdateListingDAO.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/data/local/dao/regionDao/UpdateListingDAO;", "", "getAssignmentByBS", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface UpdateListingDAO {
    LiveData<List<AssignmentEntity>> getAssignmentByBS();
}
