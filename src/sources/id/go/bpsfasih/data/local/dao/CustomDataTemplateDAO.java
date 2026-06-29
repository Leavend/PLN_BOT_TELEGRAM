package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: CustomDataTemplateDAO.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005H'J$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\fH'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'¨\u0006\u0012"}, d2 = {"Lid/go/bpsfasih/data/local/dao/CustomDataTemplateDAO;", "", "deleteAll", "", "userId", "", "deleteItem", "item", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "getItemById", DownloadModel.ID, "getItemByIds", "", "ids", "insert", "insertAll", "listItems", "updateItem", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface CustomDataTemplateDAO {
    void deleteAll(String userId);

    void deleteItem(CustomDataTemplateEntity item);

    CustomDataTemplateEntity getItemById(String id2);

    List<CustomDataTemplateEntity> getItemByIds(List<String> ids, String userId);

    void insert(CustomDataTemplateEntity item);

    void insertAll(List<CustomDataTemplateEntity> listItems);

    void updateItem(CustomDataTemplateEntity item);
}
