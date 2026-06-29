package id.go.bpsfasih.data.repository;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.AllSurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyRolesResponse;
import id.go.bpsfasih.data.remote.dto.UserRolesResponse;
import id.go.bpsfasih.domain.repository.SurveyRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: SurveyRepository.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JW\u0010\u0003\u001a\u00020\u00042M\u0010\u0005\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00040\u0006H\u0016JM\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2#\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0015H\u0016J-\u0010\u0017\u001a\u00020\u00042#\u0010\u0018\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0015H\u0016J5\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\r2#\u0010\u0014\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0015H\u0016¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/data/repository/SurveyRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/SurveyRepository;", "()V", "checkSurveys", "", "surveysCallback", "Lkotlin/Function3;", "Lid/go/bpsfasih/data/remote/dto/AllSurveyResponse;", "Lkotlin/ParameterName;", "name", "result", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "errorMessage", "getListUserByPeriodRoleRegion", "periodeId", "surveyRoleId", "parentUserId", "smallRegionFullCode", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/UserRolesResponse;", "getNewSurvey", "surveyCallback", "Lid/go/bpsfasih/data/remote/dto/SurveyResponse;", "getSurveyRole", "surveyId", "Lid/go/bpsfasih/data/remote/dto/SurveyRolesResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyRepositoryImpl implements SurveyRepository {
    public static final int $stable = 0;

    /* compiled from: SurveyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1", f = "SurveyRepository.kt", i = {}, l = {24, 25, 30}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1, reason: invalid class name and case insensitive filesystem */
    static final class C08041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<SurveyResponse, Unit> $surveyCallback;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08041(String str, Function1<? super SurveyResponse, Unit> function1, Continuation<? super C08041> continuation) {
            super(2, continuation);
            this.$userId = str;
            this.$surveyCallback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08041(this.$userId, this.$surveyCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e) {
                String message = e.getMessage();
                if (message == null) {
                    message = "Unknown error";
                }
                Log.d("Survey Error", message);
                this.label = 3;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$surveyCallback, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = RetrofitClient.INSTANCE.getSurveyApiService().getAllListSurveyNew(this.$userId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2 && i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            Function1<SurveyResponse, Unit> function1 = this.$surveyCallback;
            this.label = 2;
            if (BuildersKt.withContext(main, new C01681(function1, (SurveyResponse) obj, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* compiled from: SurveyRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1$1", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ SurveyResponse $result;
            final /* synthetic */ Function1<SurveyResponse, Unit> $surveyCallback;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01681(Function1<? super SurveyResponse, Unit> function1, SurveyResponse surveyResponse, Continuation<? super C01681> continuation) {
                super(2, continuation);
                this.$surveyCallback = function1;
                this.$result = surveyResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01681(this.$surveyCallback, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$surveyCallback.invoke(this.$result);
                return Unit.INSTANCE;
            }
        }

        /* compiled from: SurveyRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1$2", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$getNewSurvey$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<SurveyResponse, Unit> $surveyCallback;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super SurveyResponse, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$surveyCallback = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$surveyCallback, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$surveyCallback.invoke(null);
                return Unit.INSTANCE;
            }
        }
    }

    @Override // id.go.bpsfasih.domain.repository.SurveyRepository
    public void getNewSurvey(Function1<? super SurveyResponse, Unit> surveyCallback) {
        Intrinsics.checkNotNullParameter(surveyCallback, "surveyCallback");
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08041(FasihApp.INSTANCE.getSession().getUserId(), surveyCallback, null), 2, null);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Unknown error";
            }
            Log.d("Survey Error", message);
            surveyCallback.invoke(null);
        }
    }

    @Override // id.go.bpsfasih.domain.repository.SurveyRepository
    public void checkSurveys(final Function3<? super AllSurveyResponse, ? super Boolean, ? super String, Unit> surveysCallback) {
        Intrinsics.checkNotNullParameter(surveysCallback, "surveysCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AllSurveyResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getSurveyApiService().getAllSurvey().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AllSurveyResponse, Unit> function1 = new Function1<AllSurveyResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.checkSurveys.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AllSurveyResponse allSurveyResponse) {
                invoke2(allSurveyResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AllSurveyResponse allSurveyResponse) {
                surveysCallback.invoke(allSurveyResponse, false, "");
            }
        };
        Consumer<? super AllSurveyResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.checkSurveys$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.checkSurveys.2
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
                Function3<AllSurveyResponse, Boolean, String, Unit> function3 = surveysCallback;
                String message2 = th.getMessage();
                Intrinsics.checkNotNull(message2);
                function3.invoke(null, true, message2);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.checkSurveys$lambda$1(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkSurveys$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkSurveys$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.SurveyRepository
    public void getListUserByPeriodRoleRegion(String periodeId, String surveyRoleId, String parentUserId, String smallRegionFullCode, final Function1<? super UserRolesResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(surveyRoleId, "surveyRoleId");
        Intrinsics.checkNotNullParameter(parentUserId, "parentUserId");
        Intrinsics.checkNotNullParameter(smallRegionFullCode, "smallRegionFullCode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<UserRolesResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getSurveyApiService().getUserRoleByPeriodRoleRegion(periodeId, surveyRoleId, parentUserId, smallRegionFullCode).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<UserRolesResponse, Unit> function1 = new Function1<UserRolesResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.getListUserByPeriodRoleRegion.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserRolesResponse userRolesResponse) {
                invoke2(userRolesResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserRolesResponse userRolesResponse) {
                callback.invoke(userRolesResponse);
            }
        };
        Consumer<? super UserRolesResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.getListUserByPeriodRoleRegion$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.getListUserByPeriodRoleRegion.2
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
                Log.d("USER ROLE ERROR ", message);
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.getListUserByPeriodRoleRegion$lambda$3(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getListUserByPeriodRoleRegion$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getListUserByPeriodRoleRegion$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.SurveyRepository
    public void getSurveyRole(String surveyId, final Function1<? super SurveyRolesResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<SurveyRolesResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getSurveyApiService().getAllSurveyRoles(surveyId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<SurveyRolesResponse, Unit> function1 = new Function1<SurveyRolesResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.getSurveyRole.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SurveyRolesResponse surveyRolesResponse) {
                invoke2(surveyRolesResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SurveyRolesResponse surveyRolesResponse) {
                callback.invoke(surveyRolesResponse);
            }
        };
        Consumer<? super SurveyRolesResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.getSurveyRole$lambda$4(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl.getSurveyRole.2
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
                Log.d("SURVEY ROLE ERROR", message);
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.SurveyRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SurveyRepositoryImpl.getSurveyRole$lambda$5(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getSurveyRole$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getSurveyRole$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
