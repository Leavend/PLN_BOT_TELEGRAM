package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO;
import id.go.bpsfasih.data.local.entities.CustomColumnEntity;
import java.io.File;
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

/* compiled from: CustomColumnRepository.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bJ\u001b\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lid/go/bpsfasih/data/local/repository/CustomColumnRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "(Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;)V", "delete", "", "item", "Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "deleteAll", "callback", "Lkotlin/Function0;", "getItemById", DownloadModel.ID, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "update", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CustomColumnRepository {
    public static final int $stable = 8;
    private final CustomColumnDAO dao;

    public CustomColumnRepository(CustomColumnDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    public final void insert(final CustomColumnEntity item, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (item != null) {
            AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomColumnRepository$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    CustomColumnRepository.insert$lambda$0(this.f$0, item, callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(CustomColumnRepository this$0, CustomColumnEntity customColumnEntity, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.insert(customColumnEntity);
        callback.invoke();
    }

    /* compiled from: CustomColumnRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.CustomColumnRepository$getItemById$2", f = "CustomColumnRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.CustomColumnRepository$getItemById$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CustomColumnEntity>, Object> {
        final /* synthetic */ String $id;
        int label;
        final /* synthetic */ CustomColumnRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, CustomColumnRepository customColumnRepository, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$id = str;
            this.this$0 = customColumnRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$id, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CustomColumnEntity> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String userId = FasihApp.INSTANCE.getSession().getUserId();
            return this.this$0.dao.getByIdPrimary(this.$id + File.separator + userId);
        }
    }

    public final Object getItemById(String str, Continuation<? super CustomColumnEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, this, null), continuation);
    }

    public final void delete(final CustomColumnEntity item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomColumnRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomColumnRepository.delete$lambda$1(this.f$0, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$1(CustomColumnRepository this$0, CustomColumnEntity item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.dao.deleteItem(item);
    }

    public final void deleteAll(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomColumnRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CustomColumnRepository.deleteAll$lambda$2(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$2(CustomColumnRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.deleteAll(FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    public final void update(final CustomColumnEntity item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomColumnRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CustomColumnRepository.update$lambda$3(this.f$0, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void update$lambda$3(CustomColumnRepository this$0, CustomColumnEntity item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.dao.update(item);
    }
}
