package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* compiled from: DeviceApiService.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'¨\u0006\b"}, d2 = {"Lid/go/bpsfasih/data/remote/api/DeviceApiService;", "", "registerDevice", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "param", "Lokhttp3/RequestBody;", "unregisterDevice", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface DeviceApiService {
    @POST("/mobile/registration/api/mobile/register-device")
    Observable<BaseResponse> registerDevice(@Body RequestBody param);

    @POST("/mobile/registration/api/mobile/un-register-device")
    Observable<BaseResponse> unregisterDevice(@Body RequestBody param);
}
