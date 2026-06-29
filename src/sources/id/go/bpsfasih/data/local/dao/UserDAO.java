package id.go.bpsfasih.data.local.dao;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.UserEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: UserDAO.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b0\nH'J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH'J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000eH'J\u0016\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH'J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/dao/UserDAO;", "", "deleteAllUser", "", "deleteUser", "Users", "Lid/go/bpsfasih/data/local/entities/UserEntity;", DownloadModel.ID, "", "getAllUser", "Landroidx/lifecycle/LiveData;", "", "getUserById", "userId", "", "getUserByUsername", HintConstants.AUTOFILL_HINT_USERNAME, "insertAllUser", "listItems", "insertUser", "updateUser", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface UserDAO {
    void deleteAllUser();

    void deleteUser(int id2);

    void deleteUser(UserEntity Users);

    LiveData<List<UserEntity>> getAllUser();

    UserEntity getUserById(String userId);

    UserEntity getUserByUsername(String username);

    void insertAllUser(List<UserEntity> listItems);

    void insertUser(UserEntity Users);

    void updateUser(UserEntity Users);
}
