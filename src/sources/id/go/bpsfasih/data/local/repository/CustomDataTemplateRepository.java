package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.CustomDataTemplateDAO;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

/* compiled from: CustomDataTemplateRepository.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0014\u0010\u000e\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010J\u001b\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010J\"\u0010\u0016\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0005\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/data/local/repository/CustomDataTemplateRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/CustomDataTemplateDAO;", "(Lid/go/bpsfasih/data/local/dao/CustomDataTemplateDAO;)V", "template", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "getTemplate", "()Landroidx/lifecycle/LiveData;", "delete", "", "item", "deleteAll", "callback", "Lkotlin/Function0;", "getItemById", DownloadModel.ID, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "insertAll", "listItem", "update", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CustomDataTemplateRepository {
    public static final int $stable = 8;
    private final CustomDataTemplateDAO dao;
    private final LiveData<List<CustomDataTemplateEntity>> template;

    public CustomDataTemplateRepository(CustomDataTemplateDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    public final LiveData<List<CustomDataTemplateEntity>> getTemplate() {
        return this.template;
    }

    public final void insert(final CustomDataTemplateEntity item, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (item != null) {
            AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CustomDataTemplateRepository.insert$lambda$0(this.f$0, item, callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(CustomDataTemplateRepository this$0, CustomDataTemplateEntity customDataTemplateEntity, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.insert(customDataTemplateEntity);
        callback.invoke();
    }

    /* compiled from: CustomDataTemplateRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$getItemById$2", f = "CustomDataTemplateRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$getItemById$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CustomDataTemplateEntity>, Object> {
        final /* synthetic */ String $id;
        int label;
        final /* synthetic */ CustomDataTemplateRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, CustomDataTemplateRepository customDataTemplateRepository, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$id = str;
            this.this$0 = customDataTemplateRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$id, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CustomDataTemplateEntity> continuation) {
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
            return this.this$0.dao.getItemById(this.$id + File.separator + userId);
        }
    }

    public final Object getItemById(String str, Continuation<? super CustomDataTemplateEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, this, null), continuation);
    }

    public final void delete(final CustomDataTemplateEntity item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomDataTemplateRepository.delete$lambda$1(this.f$0, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$1(CustomDataTemplateRepository this$0, CustomDataTemplateEntity item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.dao.deleteItem(item);
    }

    public final void deleteAll(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CustomDataTemplateRepository.deleteAll$lambda$2(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$2(CustomDataTemplateRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.dao.deleteAll(FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    public final void update(final CustomDataTemplateEntity item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                CustomDataTemplateRepository.update$lambda$3(this.f$0, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void update$lambda$3(CustomDataTemplateRepository this$0, CustomDataTemplateEntity item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.dao.updateItem(item);
    }

    public final void insertAll(final List<CustomDataTemplateEntity> listItem, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(listItem, "listItem");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CustomDataTemplateRepository.insertAll$lambda$5(this.f$0, listItem, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAll$lambda$5(CustomDataTemplateRepository this$0, List listItem, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listItem, "$listItem");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        CustomDataTemplateDAO customDataTemplateDAO = this$0.dao;
        ArrayList arrayList = new ArrayList();
        for (Object obj : listItem) {
            if (((CustomDataTemplateEntity) obj).getIdPrimary().length() > 0) {
                arrayList.add(obj);
            }
        }
        customDataTemplateDAO.insertAll(arrayList);
        callback.invoke();
    }
}
