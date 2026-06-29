package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.domain.repository.TicketRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MultipartBody;

/* compiled from: TicketRepository.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nH\u0016¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/data/repository/TicketRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/TicketRepository;", "()V", "sendFileAssignmentBantuan", "", "ticketId", "", StorageChooser.FILE_PICKER, "Lokhttp3/MultipartBody$Part;", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "Lkotlin/ParameterName;", "name", "result", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TicketRepositoryImpl implements TicketRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.TicketRepository
    public void sendFileAssignmentBantuan(String ticketId, MultipartBody.Part file, final Function1<? super BaseResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(ticketId, "ticketId");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTicketApiService().uploadFileAssignmentPusatBantuan(ticketId, file).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TicketRepositoryImpl.sendFileAssignmentBantuan.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                invoke2(baseResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponse baseResponse) {
                callback.invoke(baseResponse);
            }
        };
        Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TicketRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TicketRepositoryImpl.sendFileAssignmentBantuan$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TicketRepositoryImpl.sendFileAssignmentBantuan.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callback.invoke(new BaseResponse(false, null, th.getMessage(), 2, null));
            }
        };
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TicketRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TicketRepositoryImpl.sendFileAssignmentBantuan$lambda$1(function12, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendFileAssignmentBantuan$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendFileAssignmentBantuan$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
