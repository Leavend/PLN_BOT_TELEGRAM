package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.dao.SamplingRegionDAO;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: SamplingRegionRepository.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ#\u0010\u0005\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/local/repository/SamplingRegionRepository;", "", "samplingRegionDAO", "Lid/go/bpsfasih/data/local/dao/SamplingRegionDAO;", "(Lid/go/bpsfasih/data/local/dao/SamplingRegionDAO;)V", "getSamplingRegion", "", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "surveyPeriodId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fullcode", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSamplingRegionById", DownloadModel.ID, "insertData", "", "data", "updateIsDone", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SamplingRegionRepository {
    public static final int $stable = 8;
    private final SamplingRegionDAO samplingRegionDAO;

    public SamplingRegionRepository(SamplingRegionDAO samplingRegionDAO) {
        Intrinsics.checkNotNullParameter(samplingRegionDAO, "samplingRegionDAO");
        this.samplingRegionDAO = samplingRegionDAO;
    }

    public final void insertData(final SamplingRegionEntity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SamplingRegionRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SamplingRegionRepository.insertData$lambda$0(this.f$0, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertData$lambda$0(SamplingRegionRepository this$0, SamplingRegionEntity data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        this$0.samplingRegionDAO.insert(data);
    }

    /* compiled from: SamplingRegionRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegion$2", f = "SamplingRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegion$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SamplingRegionEntity>>, Object> {
        final /* synthetic */ String $surveyPeriodId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$surveyPeriodId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SamplingRegionRepository.this.new AnonymousClass2(this.$surveyPeriodId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SamplingRegionEntity>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<SamplingRegionEntity>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SamplingRegionEntity>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return SamplingRegionRepository.this.samplingRegionDAO.getSamplingRegion(this.$surveyPeriodId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSamplingRegion(String str, Continuation<? super List<SamplingRegionEntity>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    /* compiled from: SamplingRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegion$4", f = "SamplingRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegion$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SamplingRegionEntity>, Object> {
        final /* synthetic */ String $fullcode;
        final /* synthetic */ String $surveyPeriodId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(String str, String str2, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$surveyPeriodId = str;
            this.$fullcode = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SamplingRegionRepository.this.new AnonymousClass4(this.$surveyPeriodId, this.$fullcode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SamplingRegionEntity> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return SamplingRegionRepository.this.samplingRegionDAO.getSamplingRegion(this.$surveyPeriodId, this.$fullcode);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSamplingRegion(String str, String str2, Continuation<? super SamplingRegionEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass4(str, str2, null), continuation);
    }

    /* compiled from: SamplingRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegionById$2", f = "SamplingRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SamplingRegionRepository$getSamplingRegionById$2, reason: invalid class name and case insensitive filesystem */
    static final class C07332 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SamplingRegionEntity>, Object> {
        final /* synthetic */ String $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07332(String str, Continuation<? super C07332> continuation) {
            super(2, continuation);
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SamplingRegionRepository.this.new C07332(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SamplingRegionEntity> continuation) {
            return ((C07332) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return SamplingRegionRepository.this.samplingRegionDAO.getSamplingRegionById(this.$id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSamplingRegionById(String str, Continuation<? super SamplingRegionEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07332(str, null), continuation);
    }

    public final void updateIsDone(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SamplingRegionRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SamplingRegionRepository.updateIsDone$lambda$1(this.f$0, id2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateIsDone$lambda$1(SamplingRegionRepository this$0, String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.samplingRegionDAO.updateIsDone(id2);
    }
}
