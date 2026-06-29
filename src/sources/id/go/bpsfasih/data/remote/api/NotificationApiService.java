package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.models.BaseResponseAssignmentChangeMode;
import id.go.bpsfasih.data.local.models.FormEngineResponse;
import id.go.bpsfasih.data.local.models.PeriodeUpdateResponse;
import id.go.bpsfasih.data.local.models.TemplateValidationResponse;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* compiled from: NotificationApiService.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u0006H'J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u0006H'J\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H'J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0006H'J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/data/remote/api/NotificationApiService;", "", "checkNotificationAssignment", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentResponse;", "deviceId", "", "surveyPeriodId", "checkSurveyPeriode", "Lid/go/bpsfasih/data/local/models/PeriodeUpdateResponse;", "periodeId", "checkTemplateValidationVersion", "Lid/go/bpsfasih/data/local/models/TemplateValidationResponse;", "surveyId", "flagDoneNotification", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "param", "Lokhttp3/RequestBody;", "getFormEngineRelease", "Lid/go/bpsfasih/data/local/models/FormEngineResponse;", "formEngineId", "reqAssignmenetChangeMode", "Lid/go/bpsfasih/data/local/models/BaseResponseAssignmentChangeMode;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface NotificationApiService {
    @GET("/mobile/notification-service/api/mobile//check-notification-assignment")
    Observable<CheckNotificationAssignmentResponse> checkNotificationAssignment(@Query("deviceId") String deviceId, @Query("surveyPeriodId") String surveyPeriodId);

    @GET("/mobile/notification-service/api/mobile//check-notification-survey-period")
    Observable<PeriodeUpdateResponse> checkSurveyPeriode(@Query("surveyPeriodId") String periodeId);

    @GET("/mobile/notification-service/api/mobile//check-notification-survey-metadata")
    Observable<TemplateValidationResponse> checkTemplateValidationVersion(@Query("surveyId") String surveyId);

    @POST("/mobile/notification-service/api/mobile//flag-notif-assignment")
    Observable<BaseResponse> flagDoneNotification(@Body RequestBody param);

    @GET("/mobile/notification-service/api/mobile//check-form-engine-release")
    Observable<FormEngineResponse> getFormEngineRelease(@Query("formEngineId") String formEngineId);

    @GET("/mobile/notification-service/api/mobile//check-notification-assignment-mode-changed")
    Observable<BaseResponseAssignmentChangeMode> reqAssignmenetChangeMode(@Query("deviceId") String deviceId, @Query("surveyPeriodId") String surveyPeriodId);
}
