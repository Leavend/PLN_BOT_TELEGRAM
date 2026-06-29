package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.TemplateValidationDAO;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TemplateValidationRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eJ,\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;", "(Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;)V", "getDataBySurveyId", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "surveyId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertData", "", "data", "callback", "Lkotlin/Function0;", "updateData", "formEngineId", "", "formEngineBrandName", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TemplateValidationRepository {
    public static final int $stable = 8;
    private final TemplateValidationDAO dao;

    public TemplateValidationRepository(TemplateValidationDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    /* compiled from: TemplateValidationRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.TemplateValidationRepository$getDataBySurveyId$2", f = "TemplateValidationRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.TemplateValidationRepository$getDataBySurveyId$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TemplateValidationEntity>, Object> {
        final /* synthetic */ String $surveyId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$surveyId = str;
            this.$userId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TemplateValidationRepository.this.new AnonymousClass2(this.$surveyId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TemplateValidationEntity> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return TemplateValidationRepository.this.dao.getBySurveyId(this.$surveyId, this.$userId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getDataBySurveyId(String str, Continuation<? super TemplateValidationEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    public final void insertData(final TemplateValidationEntity data, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.TemplateValidationRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TemplateValidationRepository.insertData$lambda$0(this.f$0, data, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertData$lambda$0(TemplateValidationRepository this$0, TemplateValidationEntity data, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.insert(data);
        callback.invoke();
    }

    public final void updateData(final int formEngineId, final String formEngineBrandName, final String surveyId, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(formEngineBrandName, "formEngineBrandName");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final String userId = FasihApp.INSTANCE.getSession().getUserId();
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.TemplateValidationRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TemplateValidationRepository.updateData$lambda$1(this.f$0, formEngineId, formEngineBrandName, surveyId, userId, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateData$lambda$1(TemplateValidationRepository this$0, int i, String formEngineBrandName, String surveyId, String userId, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(formEngineBrandName, "$formEngineBrandName");
        Intrinsics.checkNotNullParameter(surveyId, "$surveyId");
        Intrinsics.checkNotNullParameter(userId, "$userId");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.updateFormEngineInfo(i, formEngineBrandName, surveyId, userId);
        callback.invoke();
    }
}
