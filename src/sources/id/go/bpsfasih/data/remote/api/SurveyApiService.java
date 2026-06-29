package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.AllSurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyRolesResponse;
import id.go.bpsfasih.data.remote.dto.UserRolesResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* compiled from: SurveyApiService.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH'J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\b\b\u0001\u0010\f\u001a\u00020\u0005H'J6\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\b\b\u0001\u0010\u000f\u001a\u00020\u00052\b\b\u0001\u0010\u0010\u001a\u00020\u00052\b\b\u0001\u0010\u0011\u001a\u00020\u00052\b\b\u0001\u0010\u0012\u001a\u00020\u0005H'\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/remote/api/SurveyApiService;", "", "getAllListSurveyNew", "Lid/go/bpsfasih/data/remote/dto/SurveyResponse;", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSurvey", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/AllSurveyResponse;", "getAllSurveyRoles", "Lid/go/bpsfasih/data/remote/dto/SurveyRolesResponse;", "surveyId", "getUserRoleByPeriodRoleRegion", "Lid/go/bpsfasih/data/remote/dto/UserRolesResponse;", "surveyPeriodId", "surveyRoleId", "parentUserId", "regionCode", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface SurveyApiService {
    @GET("/mobile/assignment-sync/api/mobile/survey/get-survey-by-user-id?")
    Object getAllListSurveyNew(@Query("userId") String str, Continuation<? super SurveyResponse> continuation);

    @GET("/mobile/assignment-sync/api/mobile/survey/get-survey-for-capi")
    Observable<AllSurveyResponse> getAllSurvey();

    @GET("/mobile/survey/api/v1/mobile/survey-roles?")
    Observable<SurveyRolesResponse> getAllSurveyRoles(@Query("surveyId") String surveyId);

    @GET("/mobile/survey/api/v1/mobile/survey-period-role-users/region?")
    Observable<UserRolesResponse> getUserRoleByPeriodRoleRegion(@Query("surveyPeriodId") String surveyPeriodId, @Query("surveyRoleId") String surveyRoleId, @Query("parentUserId") String parentUserId, @Query("regionCode") String regionCode);
}
