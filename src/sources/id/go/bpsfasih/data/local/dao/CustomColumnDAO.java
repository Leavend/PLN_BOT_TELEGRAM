package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.CustomColumnEntity;
import kotlin.Metadata;

/* compiled from: CustomColumnDAO.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0014\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH'Jb\u0010\u000e\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H'¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "", "deleteAll", "", "userId", "", "deleteItem", "item", "Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "getByIdPrimary", "idPrimary", "insert", "customColumnEntity", "update", "updateByIdPrimary", "showData1", "", "showData2", "showData3", "showData4", "showData5", "showData6", "showData7", "showData8", "showData9", "showData10", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface CustomColumnDAO {
    void deleteAll(String userId);

    void deleteItem(CustomColumnEntity item);

    CustomColumnEntity getByIdPrimary(String idPrimary);

    void insert(CustomColumnEntity customColumnEntity);

    void update(CustomColumnEntity customColumnEntity);

    void updateByIdPrimary(String idPrimary, boolean showData1, boolean showData2, boolean showData3, boolean showData4, boolean showData5, boolean showData6, boolean showData7, boolean showData8, boolean showData9, boolean showData10);
}
