package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.data.local.pojo.Sync;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SurveyDAONew.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u001e\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nH'J&\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H'J$\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H'J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H'J\u001e\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001e\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\nH'J\u001a\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\n2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J&\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\u0006\u0010!\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0005H'J\u001a\u0010#\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0018\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010*\u001a\u00020\u00032\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0005H'J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'¨\u0006/"}, d2 = {"Lid/go/bpsfasih/data/local/dao/SurveyDAONew;", "", "deleteAll", "", "userId", "", "deleteSurvey", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "getAllSurvey", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getAllSurveyAreaTrue", "getAllSurveyByParentId", "parentId", "getAllSurveyBySync", "Lid/go/bpsfasih/data/local/pojo/Sync;", "getAllSurveyByType", "updateListingType", "", "areaType", "getAllSurveyByTypeForHome", "getAllSurveyHome", "getAllSurveyPojo", "getAllSurveySync", "getAllSurveySyncDummy", "getIsTarikSample", DownloadModel.ID, "getJumlahSurveys", "", "getJumlahSurveysLiveData", "getSurveyById", "wordId", "getSurveyByIdPrimarySingle", "getSurveyByIdSingle", "getSurveyId", "periodeId", "getSurveyIdFromAssignment", "assignmentId", "getSurveys", "insert", "insertAll", "listItems", "updateStatusPin", "surveyIdPrimary", "updateSurvey", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface SurveyDAONew {
    void deleteAll(String userId);

    void deleteSurvey(SurveyEntity survey);

    LiveData<List<SurveyPojo>> getAllSurvey(String userId);

    LiveData<List<SurveyEntity>> getAllSurveyAreaTrue();

    LiveData<List<SurveyPojo>> getAllSurveyByParentId(String parentId, String userId);

    LiveData<List<Sync>> getAllSurveyBySync(String userId);

    LiveData<List<SurveyEntity>> getAllSurveyByType(boolean updateListingType, boolean areaType);

    LiveData<List<SurveyEntity>> getAllSurveyByTypeForHome(boolean updateListingType, boolean areaType);

    LiveData<List<SurveyEntity>> getAllSurveyHome(String userId);

    LiveData<List<SurveyPojo>> getAllSurveyPojo(String userId);

    LiveData<List<Sync>> getAllSurveySync(String userId);

    LiveData<List<Sync>> getAllSurveySyncDummy();

    boolean getIsTarikSample(String id2, String userId);

    int getJumlahSurveys(String userId);

    LiveData<Integer> getJumlahSurveysLiveData(String userId);

    LiveData<List<SurveyEntity>> getSurveyById(String wordId, String userId);

    SurveyEntity getSurveyByIdPrimarySingle(String id2);

    SurveyEntity getSurveyByIdSingle(String id2, String userId);

    String getSurveyId(String periodeId, String userId);

    String getSurveyIdFromAssignment(String assignmentId, String userId);

    List<SurveyEntity> getSurveys(String userId);

    void insert(SurveyEntity survey);

    void insertAll(List<SurveyEntity> listItems);

    void updateStatusPin(String surveyIdPrimary);

    void updateSurvey(SurveyEntity survey);
}
