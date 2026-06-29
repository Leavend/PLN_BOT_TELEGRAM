package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.remote.dto.RecapResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.GET;

/* compiled from: AnalyticApiService.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/data/remote/api/AnalyticApiService;", "", "getRecap", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/RecapResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AnalyticApiService {
    @GET(Config.ANALYTICS)
    Observable<RecapResponse> getRecap();
}
