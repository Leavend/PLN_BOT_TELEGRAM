package id.go.bpsfasih.data.repository;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.LookupsList;
import id.go.bpsfasih.data.local.entities.TemplateLookupList;
import id.go.bpsfasih.data.local.models.CustomDataTemplate;
import id.go.bpsfasih.data.local.models.ListLookupNotifResponse;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.domain.repository.TemplateRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateRepository.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JR\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000628\u0010\b\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\tH\u0016JR\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062@\u0010\u0012\u001a<\u0012\u001d\u0012\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u0013¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\tH\u0016¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/repository/TemplateRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/TemplateRepository;", "()V", "getCustomData", "", "templateId", "", "templateVersion", "customDataCallback", "Lkotlin/Function2;", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "Lkotlin/ParameterName;", "name", "result", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "getListLookup", "surveyId", "callback", "", "Lid/go/bpsfasih/data/local/entities/LookupsList;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TemplateRepositoryImpl implements TemplateRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.TemplateRepository
    public void getCustomData(String templateId, String templateVersion, final Function2<? super CustomDataTemplateEntity, ? super Boolean, Unit> customDataCallback) {
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(templateVersion, "templateVersion");
        Intrinsics.checkNotNullParameter(customDataCallback, "customDataCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<CustomDataTemplate> observableSubscribeOn = RetrofitClient.INSTANCE.getTemplateApiService().getCustomDataNotif(templateId, templateVersion).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<CustomDataTemplate, Unit> function1 = new Function1<CustomDataTemplate, Unit>() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl.getCustomData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CustomDataTemplate customDataTemplate) {
                invoke2(customDataTemplate);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CustomDataTemplate customDataTemplate) {
                customDataCallback.invoke(customDataTemplate.getData(), false);
            }
        };
        Consumer<? super CustomDataTemplate> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TemplateRepositoryImpl.getCustomData$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl.getCustomData.2
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
                Log.d("Custom Data Error", message);
                customDataCallback.invoke(null, true);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TemplateRepositoryImpl.getCustomData$lambda$1(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCustomData$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCustomData$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.TemplateRepository
    public void getListLookup(String surveyId, final Function2<? super List<LookupsList>, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<ListLookupNotifResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getTemplateApiService().getListLookupNotif(surveyId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<ListLookupNotifResponse, Unit> function1 = new Function1<ListLookupNotifResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl.getListLookup.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ListLookupNotifResponse listLookupNotifResponse) {
                invoke2(listLookupNotifResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ListLookupNotifResponse listLookupNotifResponse) {
                callback.invoke(((TemplateLookupList) CollectionsKt.first((List) listLookupNotifResponse.getData())).getLookups(), false);
            }
        };
        Consumer<? super ListLookupNotifResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TemplateRepositoryImpl.getListLookup$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl.getListLookup.2
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
                Log.d("Custom Data Error", message);
                callback.invoke(null, true);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.TemplateRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TemplateRepositoryImpl.getListLookup$lambda$3(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getListLookup$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getListLookup$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
