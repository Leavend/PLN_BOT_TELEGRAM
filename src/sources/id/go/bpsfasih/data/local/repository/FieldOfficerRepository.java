package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.dao.FieldOfficerDAO;
import id.go.bpsfasih.data.local.entities.FieldOfficerEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: FieldOfficerRepository.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/data/local/repository/FieldOfficerRepository;", "", "fieldOfficerDAO", "Lid/go/bpsfasih/data/local/dao/FieldOfficerDAO;", "(Lid/go/bpsfasih/data/local/dao/FieldOfficerDAO;)V", "getSyncedUser", "", DownloadModel.ID, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "fieldOfficerEntity", "Lid/go/bpsfasih/data/local/entities/FieldOfficerEntity;", "setSynced", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FieldOfficerRepository {
    public static final int $stable = 8;
    private final FieldOfficerDAO fieldOfficerDAO;

    public FieldOfficerRepository(FieldOfficerDAO fieldOfficerDAO) {
        Intrinsics.checkNotNullParameter(fieldOfficerDAO, "fieldOfficerDAO");
        this.fieldOfficerDAO = fieldOfficerDAO;
    }

    public final void insert(final FieldOfficerEntity fieldOfficerEntity) {
        Intrinsics.checkNotNullParameter(fieldOfficerEntity, "fieldOfficerEntity");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.FieldOfficerRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FieldOfficerRepository.insert$lambda$0(this.f$0, fieldOfficerEntity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(FieldOfficerRepository this$0, FieldOfficerEntity fieldOfficerEntity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fieldOfficerEntity, "$fieldOfficerEntity");
        this$0.fieldOfficerDAO.insert(fieldOfficerEntity);
    }

    /* compiled from: FieldOfficerRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.FieldOfficerRepository$getSyncedUser$2", f = "FieldOfficerRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.FieldOfficerRepository$getSyncedUser$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ String $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FieldOfficerRepository.this.new AnonymousClass2(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(FieldOfficerRepository.this.fieldOfficerDAO.getSyncedUser(this.$id));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSyncedUser(String str, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    public final void setSynced(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.FieldOfficerRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FieldOfficerRepository.setSynced$lambda$1(this.f$0, id2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setSynced$lambda$1(FieldOfficerRepository this$0, String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.fieldOfficerDAO.setIsSynced(true, id2);
    }
}
