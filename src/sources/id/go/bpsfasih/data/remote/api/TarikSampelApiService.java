package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.GetSamplingPeriodeResponse;
import id.go.bpsfasih.data.remote.dto.SamplingRegionResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleConfigResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: TarikSampelApiService.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J=\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\u0014\b\u0001\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\t\"\u00020\u0006H'¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0006H'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0016H'¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/data/remote/api/TarikSampelApiService;", "", "getRegionSampling", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/SamplingRegionResponse;", "surveyPeriodId", "", "mode", "fullCodes", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lio/reactivex/Observable;", "getSamplingPeriode", "Lid/go/bpsfasih/data/remote/dto/GetSamplingPeriodeResponse;", "getSamplingSurveyPeriode", "Lid/go/bpsfasih/data/remote/dto/TarikSampleResponse;", "surveyPeriodeId", "getSamplingSurveyPeriodeConfig", "Lid/go/bpsfasih/data/remote/dto/TarikSampleConfigResponse;", "tarikSampleId", "tarikSampel", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "param", "Lokhttp3/RequestBody;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TarikSampelApiService {
    @GET("mobile/sampling/api/region")
    Observable<SamplingRegionResponse> getRegionSampling(@Query("samplingSurveyPeriodId") String surveyPeriodId, @Query("mode") String mode, @Query("fullCodes") String... fullCodes);

    @GET("mobile/sampling/api/sampling-survey-period")
    Observable<GetSamplingPeriodeResponse> getSamplingPeriode(@Query("surveyPeriodId") String surveyPeriodId);

    @GET("mobile/sampling/api/sampling-survey-period")
    Observable<TarikSampleResponse> getSamplingSurveyPeriode(@Query("surveyPeriodId") String surveyPeriodeId);

    @GET("mobile/sampling/api/sampling-survey-period/{id}/config")
    Observable<TarikSampleConfigResponse> getSamplingSurveyPeriodeConfig(@Path(DownloadModel.ID) String tarikSampleId);

    @POST("mobile/sampling/api/runner")
    Observable<BaseResponse> tarikSampel(@Body RequestBody param);
}
