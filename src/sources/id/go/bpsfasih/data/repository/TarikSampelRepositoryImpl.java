package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.GetSamplingPeriodeResponse;
import id.go.bpsfasih.data.remote.dto.SamplingRegionResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleConfigResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleResponse;
import id.go.bpsfasih.domain.repository.TarikSampelRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TarikSampelRepository.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JN\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u00062#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nH\u0016¢\u0006\u0002\u0010\u000fJ5\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\nH\u0016J5\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nH\u0016J5\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062#\u0010\u0018\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\nH\u0016J¡\u0001\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010%\u001a\u0004\u0018\u00010\u00062\b\u0010&\u001a\u0004\u0018\u00010\u00062#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010'¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\nH\u0016¨\u0006("}, d2 = {"Lid/go/bpsfasih/data/repository/TarikSampelRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/TarikSampelRepository;", "()V", "getRegion", "", "surveyPeriodId", "", "fullCodes", "", "samplingRegionCallback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/SamplingRegionResponse;", "Lkotlin/ParameterName;", "name", "result", "(Ljava/lang/String;[Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getSamplingPeriode", "callback", "Lid/go/bpsfasih/data/remote/dto/GetSamplingPeriodeResponse;", "getTarikSample", "tarikSampleCallback", "Lid/go/bpsfasih/data/remote/dto/TarikSampleResponse;", "getTarikSampleConfig", DownloadModel.ID, "tarikSampleConfigCallback", "Lid/go/bpsfasih/data/remote/dto/TarikSampleConfigResponse;", "runTarikSampling", "samplingSurveyPeriodId", "samplingRegionFullCode", "region1Id", "region2Id", "region3Id", "region4Id", "region5Id", "region6Id", "region7Id", "region8Id", "region9Id", "region10Id", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampelRepositoryImpl implements TarikSampelRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.TarikSampelRepository
    public void getSamplingPeriode(String surveyPeriodId, final Function1<? super GetSamplingPeriodeResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<GetSamplingPeriodeResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTarikSampelApiService().getSamplingPeriode(surveyPeriodId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<GetSamplingPeriodeResponse, Unit> function1 = new Function1<GetSamplingPeriodeResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getSamplingPeriode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GetSamplingPeriodeResponse getSamplingPeriodeResponse) {
                invoke2(getSamplingPeriodeResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GetSamplingPeriodeResponse getSamplingPeriodeResponse) {
                callback.invoke(getSamplingPeriodeResponse);
            }
        };
        Consumer<? super GetSamplingPeriodeResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getSamplingPeriode$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getSamplingPeriode.2
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
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getSamplingPeriode$lambda$1(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getSamplingPeriode$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getSamplingPeriode$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.TarikSampelRepository
    public void runTarikSampling(String samplingSurveyPeriodId, String samplingRegionFullCode, String region1Id, String region2Id, String region3Id, String region4Id, String region5Id, String region6Id, String region7Id, String region8Id, String region9Id, String region10Id, final Function1<? super BaseResponse, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(samplingSurveyPeriodId, "samplingSurveyPeriodId");
        Intrinsics.checkNotNullParameter(samplingRegionFullCode, "samplingRegionFullCode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("samplingSurveyPeriodId", samplingSurveyPeriodId);
        jSONObject.put("samplingRegionFullCode", samplingRegionFullCode);
        jSONObject.put("region1Id", region1Id == null ? "" : region1Id);
        jSONObject.put("region2Id", region2Id == null ? "" : region2Id);
        jSONObject.put("region3Id", region3Id == null ? "" : region3Id);
        jSONObject.put("region4Id", region4Id == null ? "" : region4Id);
        jSONObject.put("region5Id", region5Id == null ? "" : region5Id);
        jSONObject.put("region6Id", region6Id == null ? "" : region6Id);
        jSONObject.put("region7Id", region7Id == null ? "" : region7Id);
        jSONObject.put("region8Id", region8Id == null ? "" : region8Id);
        jSONObject.put("region9Id", region9Id == null ? "" : region9Id);
        jSONObject.put("region10Id", region10Id != null ? region10Id : "");
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jo.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTarikSampelApiService().tarikSampel(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.runTarikSampling.1
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
        Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.runTarikSampling$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.runTarikSampling.2
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
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.runTarikSampling$lambda$3(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTarikSampling$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runTarikSampling$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.TarikSampelRepository
    public void getTarikSample(String surveyPeriodId, final Function1<? super TarikSampleResponse, Unit> tarikSampleCallback) {
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(tarikSampleCallback, "tarikSampleCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<TarikSampleResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTarikSampelApiService().getSamplingSurveyPeriode(surveyPeriodId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<TarikSampleResponse, Unit> function1 = new Function1<TarikSampleResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getTarikSample.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TarikSampleResponse tarikSampleResponse) {
                invoke2(tarikSampleResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TarikSampleResponse tarikSampleResponse) {
                tarikSampleCallback.invoke(tarikSampleResponse);
            }
        };
        Consumer<? super TarikSampleResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getTarikSample$lambda$4(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getTarikSample.2
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
                tarikSampleCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getTarikSample$lambda$5(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTarikSample$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTarikSample$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.TarikSampelRepository
    public void getTarikSampleConfig(String id2, final Function1<? super TarikSampleConfigResponse, Unit> tarikSampleConfigCallback) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(tarikSampleConfigCallback, "tarikSampleConfigCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<TarikSampleConfigResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTarikSampelApiService().getSamplingSurveyPeriodeConfig(id2).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<TarikSampleConfigResponse, Unit> function1 = new Function1<TarikSampleConfigResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getTarikSampleConfig.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TarikSampleConfigResponse tarikSampleConfigResponse) {
                invoke2(tarikSampleConfigResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TarikSampleConfigResponse tarikSampleConfigResponse) {
                tarikSampleConfigCallback.invoke(tarikSampleConfigResponse);
            }
        };
        Consumer<? super TarikSampleConfigResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getTarikSampleConfig$lambda$6(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getTarikSampleConfig.2
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
                tarikSampleConfigCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getTarikSampleConfig$lambda$7(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTarikSampleConfig$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTarikSampleConfig$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.TarikSampelRepository
    public void getRegion(String surveyPeriodId, String[] fullCodes, final Function1<? super SamplingRegionResponse, Unit> samplingRegionCallback) {
        Intrinsics.checkNotNullParameter(surveyPeriodId, "surveyPeriodId");
        Intrinsics.checkNotNullParameter(fullCodes, "fullCodes");
        Intrinsics.checkNotNullParameter(samplingRegionCallback, "samplingRegionCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<SamplingRegionResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTarikSampelApiService().getRegionSampling(surveyPeriodId, "OFFLINE", (String[]) Arrays.copyOf(fullCodes, fullCodes.length)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<SamplingRegionResponse, Unit> function1 = new Function1<SamplingRegionResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getRegion.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SamplingRegionResponse samplingRegionResponse) {
                invoke2(samplingRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SamplingRegionResponse samplingRegionResponse) {
                samplingRegionCallback.invoke(samplingRegionResponse);
            }
        };
        Consumer<? super SamplingRegionResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getRegion$lambda$8(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl.getRegion.2
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
                samplingRegionCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TarikSampelRepositoryImpl.getRegion$lambda$9(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRegion$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRegion$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
