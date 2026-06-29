package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.google.android.gms.actions.SearchIntents;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentSampling;
import id.go.bpsfasih.data.local.pojo.AssignmentWilayahPojo;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.domain.models.AssignmentResponsibilityForResponseData;
import id.go.bpsfasih.domain.models.RekapWilayah;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import java.util.List;
import kotlin.Metadata;
import org.apache.http.cookie.ClientCookie;

/* compiled from: AssigmentDAO.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0006\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH'J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J\"\u0010\u000b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH'J\u001e\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\u00112\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0005H'J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00122\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012H'J \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00122\u0006\u0010\f\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J&\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J*\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00122\u0006\u0010\f\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010 \u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J&\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00120\u00112\u0006\u0010\f\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00122\u0006\u0010$\u001a\u00020%H'J\u001c\u0010&\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H'J&\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00122\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H'J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J$\u0010*\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H'J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00122\u0006\u0010$\u001a\u00020%H'J\u0010\u0010-\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH'J\u0016\u0010.\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012H'J\u0010\u00100\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH'J0\u00101\u001a\f\u0012\u0006\u0012\u0004\u0018\u000102\u0018\u00010\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'J\u001a\u00103\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H'J¥\u0001\u00104\u001a\u00020\u00032\b\u00105\u001a\u0004\u0018\u00010\t2\b\u00106\u001a\u0004\u0018\u00010\u00052\u000e\u00107\u001a\n\u0012\u0004\u0012\u000208\u0018\u00010\u00122\b\u00109\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u00052\b\u0010;\u001a\u0004\u0018\u00010\u00052\b\u0010<\u001a\u0004\u0018\u00010\u00052\b\u0010=\u001a\u0004\u0018\u00010\u00052\b\u0010>\u001a\u0004\u0018\u0001022\b\u0010?\u001a\u0004\u0018\u0001022\u0010\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00122\b\u0010A\u001a\u0004\u0018\u00010\u00052\u0006\u0010B\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005H'¢\u0006\u0002\u0010CJ\u0018\u0010D\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0018\u0010E\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H'J\u0018\u0010G\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u001e\u0010H\u001a\u00020\u00032\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0010\u0010J\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010K\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH'J)\u0010L\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00052\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010O\u001a\u0004\u0018\u00010NH'¢\u0006\u0002\u0010PJ\u0018\u0010Q\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010S\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u0005H'J\"\u0010U\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H'¨\u0006W"}, d2 = {"Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "", "delete", "", "assignmentId", "", "userId", "deleteAllNew", "statusAssignment", "", "deleteAllOld", "deleteByPeriode", "periodeId", "deleteItem", "Assigment", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "getAllAssigment", "Landroidx/lifecycle/LiveData;", "", "getAssignmentById", "Lid/go/bpsfasih/data/local/pojo/Sync;", "asmId", "getAssignmentByIdOne", "getAssignmentByIdPrimary", "assignmentIdPrimary", "getAssignmentByIdPrimarys", "getAssignmentByPeriodeId", "getAssignmentCopyFromByPeriodeId", "blokSensusId", "getAssignmentIdByPeriodeIdAndRegionId", "Lid/go/bpsfasih/data/local/entities/AssignmentSampling;", "regionId", "getAssignmentOnlyById", "getAssignmentWilayahPojoByPeriode", "Lid/go/bpsfasih/data/local/pojo/AssignmentWilayahPojo;", "getAssignments", SearchIntents.EXTRA_QUERY, "Landroidx/sqlite/db/SupportSQLiteQuery;", "getCountAllAssignmentByPeriodeId", "getDetailAssignmentById", "listId", "getJumlahAssignmentLiveData", "getRandomAssignmentPrelist", "getRekapWilayahAssignment", "Lid/go/bpsfasih/domain/models/RekapWilayah;", "insert", "insertAll", "listItems", "insertWithOutReplace", "isRegionDone", "", "rollbackPending", "updateAssignmentAfterUpload", "assignmentStatusId", "assignmentStatusAlias", "assignmentResponsibility", "Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "currentUserId", "currentUserUsername", "currentUserFullname", "currentUserSurveyRoleId", "currentUserSurveyRoleName", "currentUserSurveyRoleIsPencacah", "currentUserSurveyRoleCanPullSample", "mode", "basePath", "submitVersionCode", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;)V", "updateAssignmentToPending", "updateComment", ClientCookie.COMMENT_ATTR, "updateDataDownloadedAt", "updateDataDownloadedAtByIds", "assignmentIds", "updateIsEncrypt", "updateItem", "updateLatLong", "latitude", "", "longitude", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "updateNote", FormGearJavascriptInterface.NOTE_FILE, "updateParadata", "paradata", "updateStatusAssignment", NotificationCompat.CATEGORY_STATUS, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssigmentDAO {
    void delete(String assignmentId, String userId);

    void deleteAllNew(String userId, int statusAssignment);

    void deleteAllOld(String userId);

    void deleteByPeriode(String userId, String periodeId, int statusAssignment);

    void deleteItem(AssignmentEntity Assigment);

    LiveData<List<AssignmentEntity>> getAllAssigment(String userId);

    Sync getAssignmentById(String asmId);

    AssignmentEntity getAssignmentByIdOne(String asmId);

    Sync getAssignmentByIdPrimary(String assignmentIdPrimary);

    List<AssignmentEntity> getAssignmentByIdPrimarys(List<String> asmId);

    List<AssignmentEntity> getAssignmentByPeriodeId(String periodeId, String userId);

    AssignmentEntity getAssignmentCopyFromByPeriodeId(String blokSensusId, String periodeId, String userId);

    List<AssignmentSampling> getAssignmentIdByPeriodeIdAndRegionId(String periodeId, String userId, String regionId);

    AssignmentEntity getAssignmentOnlyById(String assignmentId, String userId);

    LiveData<List<AssignmentWilayahPojo>> getAssignmentWilayahPojoByPeriode(String periodeId, String userId);

    List<AssignmentEntity> getAssignments(SupportSQLiteQuery query);

    int getCountAllAssignmentByPeriodeId(String userId, String periodeId);

    List<AssignmentEntity> getDetailAssignmentById(String userId, List<String> listId);

    LiveData<Integer> getJumlahAssignmentLiveData(String userId);

    AssignmentEntity getRandomAssignmentPrelist(String userId, String regionId, String periodeId);

    List<RekapWilayah> getRekapWilayahAssignment(SupportSQLiteQuery query);

    void insert(AssignmentEntity Assigment);

    void insertAll(List<AssignmentEntity> listItems);

    void insertWithOutReplace(AssignmentEntity Assigment);

    List<Boolean> isRegionDone(String periodeId, String regionId, String userId);

    void rollbackPending(String userId, String assignmentId);

    void updateAssignmentAfterUpload(Integer assignmentStatusId, String assignmentStatusAlias, List<AssignmentResponsibilityForResponseData> assignmentResponsibility, String currentUserId, String currentUserUsername, String currentUserFullname, String currentUserSurveyRoleId, String currentUserSurveyRoleName, Boolean currentUserSurveyRoleIsPencacah, Boolean currentUserSurveyRoleCanPullSample, List<String> mode, String basePath, int submitVersionCode, String assignmentIdPrimary);

    void updateAssignmentToPending(String asmId, String userId);

    void updateComment(String assignmentIdPrimary, String comment);

    void updateDataDownloadedAt(String assignmentId, String userId);

    void updateDataDownloadedAtByIds(List<String> assignmentIds, String userId);

    void updateIsEncrypt(String assignmentId);

    void updateItem(AssignmentEntity Assigment);

    void updateLatLong(String assignmentIdPrimary, Double latitude, Double longitude);

    void updateNote(String note, String assignmentId);

    void updateParadata(String assignmentIdPrimary, String paradata);

    void updateStatusAssignment(String asmId, int status, String userId);
}
