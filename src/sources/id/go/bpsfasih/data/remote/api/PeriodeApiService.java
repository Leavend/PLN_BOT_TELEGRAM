package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.PeriodeResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* compiled from: PeriodeApiService.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/remote/api/PeriodeApiService;", "", "getAllPeriode", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/PeriodeResponse;", "surveyId", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface PeriodeApiService {
    @GET("/mobile/assignment-sync/api/mobile/survey-periode/get-by-survey-id?")
    Observable<PeriodeResponse> getAllPeriode(@Query("surveyId") String surveyId);
}
