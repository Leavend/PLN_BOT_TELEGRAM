package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.models.BaseResponseAssignmentChangeMode;
import id.go.bpsfasih.data.local.models.FormEngineResponse;
import id.go.bpsfasih.data.local.models.PeriodeUpdateResponse;
import id.go.bpsfasih.data.local.models.TemplateValidationResponse;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: NotificationRepository.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J=\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&J5\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00052#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&J5\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00052#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&JU\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00162\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00162#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&J5\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00052#\u0010\u001b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&J=\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001e¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00030\bH&¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/domain/repository/NotificationRepository;", "", "checkNotificationAssignment", "", "deviceId", "", "surveyPeriodId", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentResponse;", "Lkotlin/ParameterName;", "name", "result", "checkSurveyPeriode", "periodeId", "Lid/go/bpsfasih/data/local/models/PeriodeUpdateResponse;", "checkVersionTemplateValidation", "surveyId", "Lid/go/bpsfasih/data/local/models/TemplateValidationResponse;", "flagDoneNotification", "periodeIdGlobal", "listDownloaded", "", "listDeleted", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "getFormEngine", "formEngineId", "formEngineCallback", "Lid/go/bpsfasih/data/local/models/FormEngineResponse;", "reqAssignmentChangeMode", "Lid/go/bpsfasih/data/local/models/BaseResponseAssignmentChangeMode;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface NotificationRepository {
    void checkNotificationAssignment(String deviceId, String surveyPeriodId, Function1<? super CheckNotificationAssignmentResponse, Unit> callback);

    void checkSurveyPeriode(String periodeId, Function1<? super PeriodeUpdateResponse, Unit> callback);

    void checkVersionTemplateValidation(String surveyId, Function1<? super TemplateValidationResponse, Unit> callback);

    void flagDoneNotification(String periodeIdGlobal, List<String> listDownloaded, List<String> listDeleted, Function1<? super BaseResponse, Unit> callback);

    void getFormEngine(String formEngineId, Function1<? super FormEngineResponse, Unit> formEngineCallback);

    void reqAssignmentChangeMode(String deviceId, String surveyPeriodId, Function1<? super BaseResponseAssignmentChangeMode, Unit> callback);
}
