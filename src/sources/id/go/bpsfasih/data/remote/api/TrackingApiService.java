package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.remote.dto.LocationTrackingPointsRequest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: TrackingApiService.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lid/go/bpsfasih/data/remote/api/TrackingApiService;", "", "liveTracking", "Lretrofit2/Response;", "", "param", "Lid/go/bpsfasih/data/remote/dto/LocationTrackingPointsRequest;", "(Lid/go/bpsfasih/data/remote/dto/LocationTrackingPointsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TrackingApiService {
    @Headers({"accept: application/json"})
    @POST(Config.LOCATION_TRACKING_PATH)
    Object liveTracking(@Body LocationTrackingPointsRequest locationTrackingPointsRequest, Continuation<? super Response<Unit>> continuation);
}
