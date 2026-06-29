package id.go.bpsfasih.ui.hompage.uploadfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.databinding.ItemFragmentUploadingBinding;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: UploadingAdapter.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001eB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\r2\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001b\u0010\u001b\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001cH\u0000¢\u0006\u0002\b\u001dR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingAdapter$UploadingViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "datas", "", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "cancelUpload", "", "data", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "reUpload", "view", "Landroid/view/View;", "setUploading", "", "setUploading$app_release", "UploadingViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UploadingAdapter extends RecyclerView.Adapter<UploadingViewHolder> {
    public static final int $stable = 8;
    private Context context;
    private List<AssignmentUploadEntity> datas = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());

    /* JADX INFO: Access modifiers changed from: private */
    public final void reUpload(AssignmentUploadEntity data, View view) {
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public UploadingAdapter(Context context) {
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UploadingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemFragmentUploadingBinding binding = (ItemFragmentUploadingBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_fragment_uploading, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new UploadingViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(UploadingViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.setData(this.datas.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    /* compiled from: UploadingAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingAdapter$UploadingViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemFragmentUploadingBinding;", "(Lid/go/bpsfasih/ui/hompage/uploadfragment/UploadingAdapter;Lid/go/bpsfasih/databinding/ItemFragmentUploadingBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemFragmentUploadingBinding;", "setData", "", "data", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class UploadingViewHolder extends RecyclerView.ViewHolder {
        private final ItemFragmentUploadingBinding binding;
        final /* synthetic */ UploadingAdapter this$0;

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setData$lambda$2$lambda$1(View view) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UploadingViewHolder(UploadingAdapter uploadingAdapter, ItemFragmentUploadingBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = uploadingAdapter;
            this.binding = binding;
        }

        public final ItemFragmentUploadingBinding getBinding() {
            return this.binding;
        }

        public final void setData(final AssignmentUploadEntity data) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UploadingAdapter$UploadingViewHolder$setData$1(data, this, null), 2, null);
            Button button = this.binding.btnReupload;
            final UploadingAdapter uploadingAdapter = this.this$0;
            button.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$UploadingViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UploadingAdapter.UploadingViewHolder.setData$lambda$2(uploadingAdapter, this, data, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setData$lambda$2(final UploadingAdapter this$0, final UploadingViewHolder this$1, final AssignmentUploadEntity assignmentUploadEntity, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type id.go.bpsfasih.ui.hompage.HomePageActivity");
            ((HomePageActivity) context).showAlertDialog("Konfirmasi", "Anda akan upload ulang ?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$UploadingViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    UploadingAdapter.UploadingViewHolder.setData$lambda$2$lambda$0(this.f$0, this$0, assignmentUploadEntity, view2);
                }
            }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$UploadingViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    UploadingAdapter.UploadingViewHolder.setData$lambda$2$lambda$1(view2);
                }
            }, true, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setData$lambda$2$lambda$0(UploadingViewHolder this$0, UploadingAdapter this$1, AssignmentUploadEntity assignmentUploadEntity, View it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.binding.btnReupload.setVisibility(8);
            Intrinsics.checkNotNull(assignmentUploadEntity);
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$1.reUpload(assignmentUploadEntity, it);
        }
    }

    public final void setUploading$app_release(List<AssignmentUploadEntity> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.datas = CollectionsKt.toMutableList((Collection) data);
        notifyDataSetChanged();
    }

    private final void cancelUpload(AssignmentUploadEntity data) {
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID(), "");
        if (sessionString == null || sessionString.length() == 0) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(data, null), 3, null);
    }

    /* compiled from: UploadingAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$cancelUpload$1", f = "UploadingAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$cancelUpload$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AssignmentUploadEntity $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AssignmentUploadEntity assignmentUploadEntity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$data = assignmentUploadEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DataSurvey.Upload.INSTANCE.getUploadRepository().delete(this.$data);
            return Unit.INSTANCE;
        }
    }
}
