package id.go.bpsfasih.data.repository;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import id.go.bpsfasih.domain.repository.RegionRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RegionRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016J5\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u000e\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016J5\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122#\u0010\u000e\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\bH\u0016J5\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122#\u0010\u000e\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\bH\u0016¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/data/repository/RegionRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/RegionRepository;", "()V", "getAssignmentRegion", "", "surveyPeriodeId", "", "assignmentRegionCallBack", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/AssignmentRegionResponse;", "Lkotlin/ParameterName;", "name", "result", "getRegionMetadata", "callback", "Lid/go/bpsfasih/data/remote/dto/RegionMetadataResponse;", "regionDone", "assignmentRegionEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "regionUndone", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RegionRepositoryImpl implements RegionRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.RegionRepository
    public void getAssignmentRegion(String surveyPeriodeId, final Function1<? super AssignmentRegionResponse, Unit> assignmentRegionCallBack) {
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(assignmentRegionCallBack, "assignmentRegionCallBack");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentRegionResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getRegionApiService().getAssignmentRegion(surveyPeriodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentRegionResponse, Unit> function1 = new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.getAssignmentRegion.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                assignmentRegionCallBack.invoke(assignmentRegionResponse);
            }
        };
        Consumer<? super AssignmentRegionResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.getAssignmentRegion$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.getAssignmentRegion.2
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
                String message = th.getMessage();
                Intrinsics.checkNotNull(message);
                Log.d("Survey Error", message);
                assignmentRegionCallBack.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.getAssignmentRegion$lambda$1(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentRegion$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentRegion$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.RegionRepository
    public void getRegionMetadata(String surveyPeriodeId, final Function1<? super RegionMetadataResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<RegionMetadataResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getRegionApiService().getRegionMetadata(surveyPeriodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<RegionMetadataResponse, Unit> function1 = new Function1<RegionMetadataResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.getRegionMetadata.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RegionMetadataResponse regionMetadataResponse) {
                invoke2(regionMetadataResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RegionMetadataResponse regionMetadataResponse) {
                callback.invoke(regionMetadataResponse);
            }
        };
        Consumer<? super RegionMetadataResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.getRegionMetadata$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.getRegionMetadata.2
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
                String message = th.getMessage();
                if (message == null) {
                    message = "Unknown error";
                }
                Log.d("Survey Error", message);
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.getRegionMetadata$lambda$3(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRegionMetadata$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRegionMetadata$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.RegionRepository
    public void regionDone(AssignmentRegionEntity assignmentRegionEntity, final Function1<? super AssignmentRegionResponse, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentRegionEntity, "assignmentRegionEntity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DownloadModel.ID, assignmentRegionEntity.get_id());
        jSONObject.put("regionId", assignmentRegionEntity.getRegion_id());
        jSONObject.put("regionGroupId", assignmentRegionEntity.getRegion_group_id());
        jSONObject.put("smallestRegionFullCode", assignmentRegionEntity.getSmallest_region_full_code());
        jSONObject.put("surveyPeriodId", assignmentRegionEntity.getSurvey_period_id());
        jSONObject.put("doneListing", true);
        jSONObject.put("doneTarikSample", false);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jo.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentRegionResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getRegionApiService().regionDone(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentRegionResponse, Unit> function1 = new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.regionDone.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                Log.d("FOUR", "regionDone: " + assignmentRegionResponse);
                callback.invoke(assignmentRegionResponse);
            }
        };
        Consumer<? super AssignmentRegionResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.regionDone$lambda$4(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.regionDone.2
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
                Log.d("FOUR", "regionDone Error: " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.regionDone$lambda$5(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void regionDone$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void regionDone$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.RegionRepository
    public void regionUndone(AssignmentRegionEntity assignmentRegionEntity, final Function1<? super AssignmentRegionResponse, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentRegionEntity, "assignmentRegionEntity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DownloadModel.ID, assignmentRegionEntity.get_id());
        jSONObject.put("regionId", assignmentRegionEntity.getRegion_id());
        jSONObject.put("regionGroupId", assignmentRegionEntity.getRegion_group_id());
        jSONObject.put("smallestRegionFullCode", assignmentRegionEntity.getSmallest_region_full_code());
        jSONObject.put("surveyPeriodId", assignmentRegionEntity.getSurvey_period_id());
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jo.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentRegionResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getRegionApiService().regionUndone(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentRegionResponse, Unit> function1 = new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.regionUndone.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                Log.d("FOUR", "regionUndone: " + assignmentRegionResponse);
                callback.invoke(assignmentRegionResponse);
            }
        };
        Consumer<? super AssignmentRegionResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.regionUndone$lambda$6(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl.regionUndone.2
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
                Log.d("FOUR", "regionUndone Error: " + th.getMessage());
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.RegionRepositoryImpl$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RegionRepositoryImpl.regionUndone$lambda$7(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void regionUndone$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void regionUndone$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
