package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: AssignmentUploadDAO.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bH'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\tH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH'¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;", "", "deleteAll", "", "deleteItem", "item", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "getAllItem", "Landroidx/lifecycle/LiveData;", "", "insert", "insertAll", "listItems", "isUploadSuccessful", DownloadModel.ID, "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssignmentUploadDAO {
    void deleteAll();

    void deleteItem(AssignmentUploadEntity item);

    LiveData<List<AssignmentUploadEntity>> getAllItem();

    void insert(AssignmentUploadEntity item);

    void insertAll(List<AssignmentUploadEntity> listItems);

    void isUploadSuccessful(String id2);
}
