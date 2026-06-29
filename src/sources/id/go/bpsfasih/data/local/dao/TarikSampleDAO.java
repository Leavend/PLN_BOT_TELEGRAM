package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.TarikSampleEntity;
import kotlin.Metadata;

/* compiled from: TarikSampleDAO.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H'J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H'¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/data/local/dao/TarikSampleDAO;", "", "getSamplingBySurveyPeriodId", "Lid/go/bpsfasih/data/local/entities/TarikSampleEntity;", "surveyPeriodeId", "", "getSamplingBySurveyPeriodeTargetId", "surveyPeriodeTargetId", "insert", "", "tarikSampleEntity", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TarikSampleDAO {
    TarikSampleEntity getSamplingBySurveyPeriodId(String surveyPeriodeId);

    TarikSampleEntity getSamplingBySurveyPeriodeTargetId(String surveyPeriodeTargetId);

    void insert(TarikSampleEntity tarikSampleEntity);
}
