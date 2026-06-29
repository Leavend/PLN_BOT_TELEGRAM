package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SamplingRegionDAO.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H'J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\u0006H'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H'J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0006H'¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/data/local/dao/SamplingRegionDAO;", "", "getSamplingRegion", "", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "surveyPeriodeId", "", "fullcode", "getSamplingRegionById", DownloadModel.ID, "insert", "", "samplingRegionEntity", "updateIsDone", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface SamplingRegionDAO {
    SamplingRegionEntity getSamplingRegion(String surveyPeriodeId, String fullcode);

    List<SamplingRegionEntity> getSamplingRegion(String surveyPeriodeId);

    SamplingRegionEntity getSamplingRegionById(String id2);

    void insert(SamplingRegionEntity samplingRegionEntity);

    void updateIsDone(String id2);
}
