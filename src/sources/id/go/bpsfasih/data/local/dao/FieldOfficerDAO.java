package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.FieldOfficerEntity;
import kotlin.Metadata;

/* compiled from: FieldOfficerDAO.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H'¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/data/local/dao/FieldOfficerDAO;", "", "getSyncedUser", "", "userId", "", "insert", "", "fieldOfficer", "Lid/go/bpsfasih/data/local/entities/FieldOfficerEntity;", "setIsSynced", "isSynced", DownloadModel.ID, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FieldOfficerDAO {
    boolean getSyncedUser(String userId);

    void insert(FieldOfficerEntity fieldOfficer);

    void setIsSynced(boolean isSynced, String id2);
}
