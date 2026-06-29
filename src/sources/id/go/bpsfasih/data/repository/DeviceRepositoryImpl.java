package id.go.bpsfasih.data.repository;

import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.domain.repository.DeviceRepository;
import id.go.bpsfasih.utils.helper.FcmHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016JE\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/data/repository/DeviceRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/DeviceRepository;", "()V", "registerDevice", "", "userId", "", "fcmToken", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "Lkotlin/ParameterName;", "name", "result", "unregisterDevice", "deviceId", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DeviceRepositoryImpl implements DeviceRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.DeviceRepository
    public void registerDevice(String userId, String fcmToken, final Function1<? super BaseResponse, Unit> callback) throws JSONException {
        final String sessionString;
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(fcmToken, "fcmToken");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String sessionString2 = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        if (sessionString2 == null || sessionString2.length() == 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            sessionString = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(sessionString, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        } else {
            sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("userId", userId);
        jSONObject.put("deviceId", sessionString);
        jSONObject.put("fcmId", fcmToken);
        jSONObject.put("manufacturer", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("imei", "");
        jSONObject.put("androidVersion", Build.VERSION.RELEASE);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "jo.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string2);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getDeviceApiService().registerDevice(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl.registerDevice.1
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
                Log.d("register-device-success", baseResponse.toString());
                FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID(), sessionString);
                callback.invoke(baseResponse);
            }
        };
        Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryImpl.registerDevice$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl.registerDevice.2
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
                Log.d("register-device-error", String.valueOf(th.getMessage()));
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                DeviceRepositoryImpl.registerDevice$lambda$1(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerDevice$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerDevice$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.DeviceRepository
    public void unregisterDevice(String userId, String deviceId, String fcmToken, final Function1<? super BaseResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(fcmToken, "fcmToken");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!(fcmToken.length() == 0)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", userId);
            jSONObject.put("deviceId", deviceId);
            jSONObject.put("fcmId", fcmToken);
            RequestBody.Companion companion = RequestBody.INSTANCE;
            MediaType mediaType = MediaType.INSTANCE.parse("application/json");
            String string = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "jo.toString()");
            RequestBody requestBodyCreate = companion.create(mediaType, string);
            CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
            Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getDeviceApiService().unregisterDevice(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl.unregisterDevice.1
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
                public final void invoke2(final BaseResponse baseResponse) {
                    FcmHelper.Companion companion2 = FcmHelper.INSTANCE;
                    final Function1<BaseResponse, Unit> function12 = callback;
                    companion2.deleteToken(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl.unregisterDevice.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z) {
                            if (z) {
                                function12.invoke(baseResponse);
                            } else {
                                function12.invoke(null);
                            }
                        }
                    });
                }
            };
            Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    DeviceRepositoryImpl.unregisterDevice$lambda$2(function1, obj);
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl.unregisterDevice.2
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
                    Log.d("unregister-device-error", String.valueOf(th.getMessage()));
                    callback.invoke(null);
                }
            };
            myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.DeviceRepositoryImpl$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    DeviceRepositoryImpl.unregisterDevice$lambda$3(function12, obj);
                }
            }));
            Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
            return;
        }
        Log.d("FOUR", "Error mendapatkan fcm token");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void unregisterDevice$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void unregisterDevice$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
