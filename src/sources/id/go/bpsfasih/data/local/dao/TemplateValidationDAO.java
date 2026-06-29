package id.go.bpsfasih.data.local.dao;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import kotlin.Metadata;

/* compiled from: TemplateValidationDAO.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H'J,\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;", "", "getBySurveyId", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "surveyId", "", "userId", "insert", "", "templateValidationEntity", "updateFormEngineInfo", "formEngineId", "", "formEngineBrandName", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TemplateValidationDAO {
    TemplateValidationEntity getBySurveyId(String surveyId, String userId);

    void insert(TemplateValidationEntity templateValidationEntity);

    void updateFormEngineInfo(int formEngineId, String formEngineBrandName, String surveyId, String userId);
}
