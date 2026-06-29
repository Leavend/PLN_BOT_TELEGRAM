package id.go.bpsfasih.ui.syncAnswer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.databinding.ItemSyncAnswerPartialBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncAnswerPartialAdapter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialAdapter$SyncAnswerPartialViewHolder;", "context", "Landroid/content/Context;", "viewModel", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "(Landroid/content/Context;Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getViewModel", "()Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SyncAnswerPartialViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncAnswerPartialAdapter extends RecyclerView.Adapter<SyncAnswerPartialViewHolder> {
    public static final int $stable = 8;
    private Context context;
    private final SyncAnswerPartialViewModel viewModel;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final SyncAnswerPartialViewModel getViewModel() {
        return this.viewModel;
    }

    public SyncAnswerPartialAdapter(Context context, SyncAnswerPartialViewModel viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SyncAnswerPartialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSyncAnswerPartialBinding binding = (ItemSyncAnswerPartialBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_sync_answer_partial, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new SyncAnswerPartialViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SyncAnswerPartialViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<PeriodeEntityNew> periodeList = this.viewModel.getPeriodeList();
        holder.setData(periodeList != null ? periodeList.get(position) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PeriodeEntityNew> periodeList = this.viewModel.getPeriodeList();
        Integer numValueOf = periodeList != null ? Integer.valueOf(periodeList.size()) : null;
        Intrinsics.checkNotNull(numValueOf);
        return numValueOf.intValue();
    }

    /* compiled from: SyncAnswerPartialAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialAdapter$SyncAnswerPartialViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialBinding;", "(Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialAdapter;Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialBinding;", "setData", "", "periode", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class SyncAnswerPartialViewHolder extends RecyclerView.ViewHolder {
        private final ItemSyncAnswerPartialBinding binding;
        final /* synthetic */ SyncAnswerPartialAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SyncAnswerPartialViewHolder(SyncAnswerPartialAdapter syncAnswerPartialAdapter, ItemSyncAnswerPartialBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = syncAnswerPartialAdapter;
            this.binding = binding;
        }

        public final ItemSyncAnswerPartialBinding getBinding() {
            return this.binding;
        }

        public final void setData(PeriodeEntityNew periode) {
            this.binding.periodeTitle.setText(periode != null ? periode.getName() : null);
            Context context = this.this$0.getContext();
            SyncAnswerPartialViewModel viewModel = this.this$0.getViewModel();
            String id2 = periode != null ? periode.getId() : null;
            Intrinsics.checkNotNull(id2);
            SyncAnswerPartialPeriodeAdapter syncAnswerPartialPeriodeAdapter = new SyncAnswerPartialPeriodeAdapter(context, viewModel, id2);
            this.binding.kontenRv.setLayoutManager(new LinearLayoutManager(this.this$0.getContext(), 1, false));
            this.binding.kontenRv.setAdapter(syncAnswerPartialPeriodeAdapter);
        }
    }
}
