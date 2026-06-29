package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.domain.repository.ConnectorRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/* compiled from: ConnectorRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u000b\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\nH\u0016¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/data/repository/ConnectorRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/ConnectorRepository;", "()V", "send", "", "name", "", "body", "Lokhttp3/RequestBody;", "connectoreApiCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "result", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ConnectorRepositoryImpl implements ConnectorRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.ConnectorRepository
    public void send(String name, RequestBody body, final Function1<? super String, Unit> connectoreApiCallback) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(connectoreApiCallback, "connectoreApiCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Single<ResponseBody> singleSubscribeOn = RetrofitClient.INSTANCE.getConnectorApiService().send(name, body).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<ResponseBody, Unit> function1 = new Function1<ResponseBody, Unit>() { // from class: id.go.bpsfasih.data.repository.ConnectorRepositoryImpl.send.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ResponseBody responseBody) {
                invoke2(responseBody);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ResponseBody responseBody) {
                connectoreApiCallback.invoke(responseBody.string());
            }
        };
        Consumer<? super ResponseBody> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.ConnectorRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ConnectorRepositoryImpl.send$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.ConnectorRepositoryImpl.send.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws IOException {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) throws IOException {
                Response<?> response;
                ResponseBody responseBodyErrorBody;
                String message = null;
                if ((th instanceof HttpException) && (response = ((HttpException) th).response()) != null && (responseBodyErrorBody = response.errorBody()) != null) {
                    message = responseBodyErrorBody.string();
                }
                Function1<String, Unit> function13 = connectoreApiCallback;
                if (message == null) {
                    message = th.getMessage();
                }
                function13.invoke(message);
            }
        };
        myCompositeDisposable.add(singleSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.ConnectorRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ConnectorRepositoryImpl.send$lambda$1(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void send$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void send$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
