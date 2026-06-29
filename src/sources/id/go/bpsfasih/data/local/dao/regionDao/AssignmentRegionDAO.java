package id.go.bpsfasih.data.local.dao.regionDao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.RegionMetadata;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import java.util.List;
import kotlin.Metadata;

/* compiled from: AssignmentRegionDAO.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001c\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J&\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J(\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J(\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0014H'J\u0016\u0010\u0017\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\rH'J$\u0010\u0018\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH'J0\u0010\u001b\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H'J5\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;", "", "deleteAll", "", "userId", "", "deleteByPeriode", "periodeId", "getAssignmentRegionCountByPeriode", "", "surveyPeriodeId", "getAssignmentRegionWilayahByPeriode", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "getMissingRegionCount", "getMissingRegionMetadataCount", "getSmallRegionFullCodeBySurveyPeriodeId", "regionId", "getStatusRegionBySurveyPeriodeId", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "insert", "assignmentRegionEntity", "insertAll", "updateRegionMetadataByPeriode", "regionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "updateStateDataTable", "value", "updateStatusListingDone", "isDone", "", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssignmentRegionDAO {
    void deleteAll(String userId);

    void deleteByPeriode(String userId, String periodeId);

    int getAssignmentRegionCountByPeriode(String surveyPeriodeId, String userId);

    LiveData<List<AssignmentRegionWilayahPojo>> getAssignmentRegionWilayahByPeriode(String surveyPeriodeId, String userId);

    int getMissingRegionCount(String surveyPeriodeId, String userId);

    int getMissingRegionMetadataCount(String surveyPeriodeId, String userId);

    String getSmallRegionFullCodeBySurveyPeriodeId(String regionId, String surveyPeriodeId, String userId);

    AssignmentRegionEntity getStatusRegionBySurveyPeriodeId(String regionId, String surveyPeriodeId, String userId);

    void insert(AssignmentRegionEntity assignmentRegionEntity);

    void insertAll(List<AssignmentRegionEntity> assignmentRegionEntity);

    void updateRegionMetadataByPeriode(String surveyPeriodeId, String userId, RegionMetadata regionMetadata);

    void updateStateDataTable(String regionId, String surveyPeriodeId, String userId, String value);

    void updateStatusListingDone(Boolean isDone, String regionId, String surveyPeriodeId, String userId);
}
