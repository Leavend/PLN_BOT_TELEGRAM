package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.models.CustomDataTemplate;
import id.go.bpsfasih.data.local.models.ListLookupNotifResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: TemplateApiService.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u0006H'¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/data/remote/api/TemplateApiService;", "", "getCustomDataNotif", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/local/models/CustomDataTemplate;", "templateId", "", "templateVersion", "getListLookupNotif", "Lid/go/bpsfasih/data/local/models/ListLookupNotifResponse;", "surveyId", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TemplateApiService {
    @GET("/mobile/assignment-sync/api/mobile/template/custom-data/{templateId}?")
    Observable<CustomDataTemplate> getCustomDataNotif(@Path("templateId") String templateId, @Query("templateVersion") String templateVersion);

    @GET("/mobile/assignment-sync/api/mobile/survey/get-list-lookup-by-survey-id?")
    Observable<ListLookupNotifResponse> getListLookupNotif(@Query("surveyId") String surveyId);
}
