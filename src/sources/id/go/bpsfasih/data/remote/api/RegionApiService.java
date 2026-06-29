package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* compiled from: RegionApiService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH'J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH'¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/data/remote/api/RegionApiService;", "", "getAssignmentRegion", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/AssignmentRegionResponse;", "surveyPeriodeId", "", "getRegionMetadata", "Lid/go/bpsfasih/data/remote/dto/RegionMetadataResponse;", "regionDone", "param", "Lokhttp3/RequestBody;", "regionUndone", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface RegionApiService {
    @GET("/mobile/assignment-sync/api/mobile/assignment-region/get-by-survey-periode-id?")
    Observable<AssignmentRegionResponse> getAssignmentRegion(@Query("surveyPeriodeId") String surveyPeriodeId);

    @GET("/mobile/assignment-sync/api/region/region-metadata?")
    Observable<RegionMetadataResponse> getRegionMetadata(@Query("surveyPeriodId") String surveyPeriodeId);

    @POST("/mobile/assignment-submit-2/api/mobile/assignment-region/done")
    Observable<AssignmentRegionResponse> regionDone(@Body RequestBody param);

    @POST("/mobile/assignment-submit-2/api/mobile/assignment-region/undone")
    Observable<AssignmentRegionResponse> regionUndone(@Body RequestBody param);
}
