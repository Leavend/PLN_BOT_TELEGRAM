package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.reactivex.Single;
import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* compiled from: ConnectorApiService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/data/remote/api/ConnectorApiService;", "", "send", "Lio/reactivex/Single;", "Lokhttp3/ResponseBody;", "name", "", "body", "Lokhttp3/RequestBody;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface ConnectorApiService {
    @POST("/mobile/connector/api/hit/{name}")
    Single<ResponseBody> send(@Path("name") String name, @Body RequestBody body);
}
