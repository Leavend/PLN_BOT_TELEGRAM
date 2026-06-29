package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.MultipartBody;

/* compiled from: TicketRepository.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J=\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/domain/repository/TicketRepository;", "", "sendFileAssignmentBantuan", "", "ticketId", "", StorageChooser.FILE_PICKER, "Lokhttp3/MultipartBody$Part;", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "Lkotlin/ParameterName;", "name", "result", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface TicketRepository {
    void sendFileAssignmentBantuan(String ticketId, MultipartBody.Part file, Function1<? super BaseResponse, Unit> callback);
}
