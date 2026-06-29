package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/* compiled from: TicketApiService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/data/remote/api/TicketApiService;", "", "uploadFileAssignmentPusatBantuan", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "ticketId", "", "backup", "Lokhttp3/MultipartBody$Part;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TicketApiService {
    @POST("ticket/api/tickets/{ticketId}/assignment-backup")
    @Multipart
    Observable<BaseResponse> uploadFileAssignmentPusatBantuan(@Path("ticketId") String ticketId, @Part MultipartBody.Part backup);
}
