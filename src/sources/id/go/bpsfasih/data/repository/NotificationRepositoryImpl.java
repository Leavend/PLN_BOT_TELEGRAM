package id.go.bpsfasih.data.repository;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.models.BaseResponseAssignmentChangeMode;
import id.go.bpsfasih.data.local.models.FormEngineResponse;
import id.go.bpsfasih.data.local.models.PeriodeUpdateResponse;
import id.go.bpsfasih.data.local.models.TemplateValidationResponse;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.CheckNotificationAssignmentResponse;
import id.go.bpsfasih.domain.repository.NotificationRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: NotificationRepository.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016J5\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016J5\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016JU\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00062\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00172\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00172#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016J5\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00062#\u0010\u001c\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016J=\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tH\u0016¨\u0006 "}, d2 = {"Lid/go/bpsfasih/data/repository/NotificationRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/NotificationRepository;", "()V", "checkNotificationAssignment", "", "deviceId", "", "surveyPeriodId", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentResponse;", "Lkotlin/ParameterName;", "name", "result", "checkSurveyPeriode", "periodeId", "Lid/go/bpsfasih/data/local/models/PeriodeUpdateResponse;", "checkVersionTemplateValidation", "surveyId", "Lid/go/bpsfasih/data/local/models/TemplateValidationResponse;", "flagDoneNotification", "periodeIdGlobal", "listDownloaded", "", "listDeleted", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "getFormEngine", "formEngineId", "formEngineCallback", "Lid/go/bpsfasih/data/local/models/FormEngineResponse;", "reqAssignmentChangeMode", "Lid/go/bpsfasih/data/local/models/BaseResponseAssignmentChangeMode;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NotificationRepositoryImpl implements NotificationRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void checkNotificationAssignment(String deviceId, String surveyPeriodId, final Function1<? super CheckNotificationAssignmentResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<CheckNotificationAssignmentResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().checkNotificationAssignment(deviceId, surveyPeriodId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<CheckNotificationAssignmentResponse, Unit> function1 = new Function1<CheckNotificationAssignmentResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkNotificationAssignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CheckNotificationAssignmentResponse checkNotificationAssignmentResponse) {
                invoke2(checkNotificationAssignmentResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CheckNotificationAssignmentResponse checkNotificationAssignmentResponse) {
                Log.d("FOUR", "notification-check-assignment-success " + checkNotificationAssignmentResponse.getData());
                callback.invoke(checkNotificationAssignmentResponse);
            }
        };
        Consumer<? super CheckNotificationAssignmentResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkNotificationAssignment$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkNotificationAssignment.2
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
                Log.d("FOUR", "notification-check-assignment-failed " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkNotificationAssignment$lambda$1(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkNotificationAssignment$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkNotificationAssignment$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void reqAssignmentChangeMode(String deviceId, String surveyPeriodId, final Function1<? super BaseResponseAssignmentChangeMode, Unit> callback) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponseAssignmentChangeMode> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().reqAssignmenetChangeMode(deviceId, surveyPeriodId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseAssignmentChangeMode, Unit> function1 = new Function1<BaseResponseAssignmentChangeMode, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.reqAssignmentChangeMode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseAssignmentChangeMode baseResponseAssignmentChangeMode) {
                invoke2(baseResponseAssignmentChangeMode);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseAssignmentChangeMode baseResponseAssignmentChangeMode) {
                Log.d("FOUR", "notification-check-assignment-success " + baseResponseAssignmentChangeMode.getData());
                callback.invoke(baseResponseAssignmentChangeMode);
            }
        };
        Consumer<? super BaseResponseAssignmentChangeMode> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.reqAssignmentChangeMode$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.reqAssignmentChangeMode.2
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
                Log.d("FOUR", "notification-check-assignment-failed " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.reqAssignmentChangeMode$lambda$3(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reqAssignmentChangeMode$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reqAssignmentChangeMode$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void checkVersionTemplateValidation(String surveyId, final Function1<? super TemplateValidationResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<TemplateValidationResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().checkTemplateValidationVersion(surveyId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<TemplateValidationResponse, Unit> function1 = new Function1<TemplateValidationResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkVersionTemplateValidation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TemplateValidationResponse templateValidationResponse) {
                invoke2(templateValidationResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TemplateValidationResponse templateValidationResponse) {
                Log.d("FOUR", "notification-check-template-validation " + templateValidationResponse.getData());
                callback.invoke(templateValidationResponse);
            }
        };
        Consumer<? super TemplateValidationResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkVersionTemplateValidation$lambda$4(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkVersionTemplateValidation.2
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
                Log.d("FOUR", "notification-check-template-validation-failed " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkVersionTemplateValidation$lambda$5(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkVersionTemplateValidation$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkVersionTemplateValidation$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void checkSurveyPeriode(String periodeId, final Function1<? super PeriodeUpdateResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<PeriodeUpdateResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().checkSurveyPeriode(periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<PeriodeUpdateResponse, Unit> function1 = new Function1<PeriodeUpdateResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkSurveyPeriode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PeriodeUpdateResponse periodeUpdateResponse) {
                invoke2(periodeUpdateResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PeriodeUpdateResponse periodeUpdateResponse) {
                Log.d("FOUR", "notification-check-template-validation " + periodeUpdateResponse.getData());
                callback.invoke(periodeUpdateResponse);
            }
        };
        Consumer<? super PeriodeUpdateResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkSurveyPeriode$lambda$6(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.checkSurveyPeriode.2
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
                Log.d("FOUR", "notification-check-template-validation-failed " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.checkSurveyPeriode$lambda$7(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkSurveyPeriode$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkSurveyPeriode$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void flagDoneNotification(String periodeIdGlobal, List<String> listDownloaded, List<String> listDeleted, final Function1<? super BaseResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeIdGlobal, "periodeIdGlobal");
        Intrinsics.checkNotNullParameter(listDownloaded, "listDownloaded");
        Intrinsics.checkNotNullParameter(listDeleted, "listDeleted");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        Intrinsics.checkNotNull(sessionString);
        ParamRequestAssignmentNotif paramRequestAssignmentNotif = new ParamRequestAssignmentNotif(sessionString, periodeIdGlobal, listDownloaded, listDeleted);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String json = new Gson().toJson(paramRequestAssignmentNotif);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(paramRequestAssignmentNotif)");
        RequestBody requestBodyCreate = companion.create(mediaType, json);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().flagDoneNotification(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.flagDoneNotification.1
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
        Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.flagDoneNotification$lambda$8(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.flagDoneNotification.2
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
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.flagDoneNotification$lambda$9(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flagDoneNotification$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flagDoneNotification$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.NotificationRepository
    public void getFormEngine(String formEngineId, final Function1<? super FormEngineResponse, Unit> formEngineCallback) {
        Intrinsics.checkNotNullParameter(formEngineId, "formEngineId");
        Intrinsics.checkNotNullParameter(formEngineCallback, "formEngineCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<FormEngineResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getNotificationApiService().getFormEngineRelease(formEngineId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<FormEngineResponse, Unit> function1 = new Function1<FormEngineResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.getFormEngine.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FormEngineResponse formEngineResponse) {
                invoke2(formEngineResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FormEngineResponse formEngineResponse) {
                formEngineCallback.invoke(formEngineResponse);
            }
        };
        Consumer<? super FormEngineResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.getFormEngine$lambda$10(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl.getFormEngine.2
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
                formEngineCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.NotificationRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NotificationRepositoryImpl.getFormEngine$lambda$11(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getFormEngine$lambda$10(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getFormEngine$lambda$11(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
