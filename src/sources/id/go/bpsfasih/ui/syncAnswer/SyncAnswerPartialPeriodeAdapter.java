package id.go.bpsfasih.ui.syncAnswer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.R;
import id.go.bpsfasih.databinding.ItemSyncAnswerPartialPeriodeBinding;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialPeriodeAdapter;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: SyncAnswerPartialPeriodeAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001!B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u001c\u0010\u001d\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialPeriodeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialPeriodeAdapter$SyncAnswerPartialPeriodeViewHolder;", "context", "Landroid/content/Context;", "viewModel", "Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "idPeriode", "", "(Landroid/content/Context;Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;Ljava/lang/String;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getIdPeriode", "()Ljava/lang/String;", "list", "", "Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "getList", "()Ljava/util/List;", "getViewModel", "()Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialViewModel;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SyncAnswerPartialPeriodeViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncAnswerPartialPeriodeAdapter extends RecyclerView.Adapter<SyncAnswerPartialPeriodeViewHolder> {
    public static final int $stable = 8;
    private Context context;
    private final String idPeriode;
    private final List<SyncAnswerPartial> list;
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

    public final String getIdPeriode() {
        return this.idPeriode;
    }

    public SyncAnswerPartialPeriodeAdapter(Context context, SyncAnswerPartialViewModel viewModel, String idPeriode) {
        HashMap<String, List<SyncAnswerPartial>> value;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(idPeriode, "idPeriode");
        this.context = context;
        this.viewModel = viewModel;
        this.idPeriode = idPeriode;
        MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap = viewModel.getAssignmentMap();
        this.list = (assignmentMap == null || (value = assignmentMap.getValue()) == null) ? null : value.get(idPeriode);
    }

    public final List<SyncAnswerPartial> getList() {
        return this.list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SyncAnswerPartialPeriodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSyncAnswerPartialPeriodeBinding binding = (ItemSyncAnswerPartialPeriodeBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_sync_answer_partial_periode, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new SyncAnswerPartialPeriodeViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SyncAnswerPartialPeriodeViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<SyncAnswerPartial> list = this.list;
        holder.setData(list != null ? list.get(position) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        HashMap<String, List<SyncAnswerPartial>> value;
        List<SyncAnswerPartial> list;
        MutableLiveData<HashMap<String, List<SyncAnswerPartial>>> assignmentMap = this.viewModel.getAssignmentMap();
        Integer numValueOf = (assignmentMap == null || (value = assignmentMap.getValue()) == null || (list = value.get(this.idPeriode)) == null) ? null : Integer.valueOf(list.size());
        Intrinsics.checkNotNull(numValueOf);
        return numValueOf.intValue();
    }

    /* compiled from: SyncAnswerPartialPeriodeAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialPeriodeAdapter$SyncAnswerPartialPeriodeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialPeriodeBinding;", "(Lid/go/bpsfasih/ui/syncAnswer/SyncAnswerPartialPeriodeAdapter;Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialPeriodeBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemSyncAnswerPartialPeriodeBinding;", "setData", "", "unit", "Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class SyncAnswerPartialPeriodeViewHolder extends RecyclerView.ViewHolder {
        private final ItemSyncAnswerPartialPeriodeBinding binding;
        final /* synthetic */ SyncAnswerPartialPeriodeAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SyncAnswerPartialPeriodeViewHolder(SyncAnswerPartialPeriodeAdapter syncAnswerPartialPeriodeAdapter, ItemSyncAnswerPartialPeriodeBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = syncAnswerPartialPeriodeAdapter;
            this.binding = binding;
        }

        public final ItemSyncAnswerPartialPeriodeBinding getBinding() {
            return this.binding;
        }

        public final void setData(final SyncAnswerPartial unit) {
            Double progress;
            if ((unit == null || (progress = unit.getProgress()) == null || ((int) progress.doubleValue()) != 100) ? false : true) {
                this.binding.deskripsiTvItemSyncAnswerPartial.setText("Selesai Mengunduh");
                TextView textView = this.binding.deskripsiTvItemSyncAnswerPartial;
                Context context = this.this$0.getContext();
                Resources resources = context != null ? context.getResources() : null;
                Intrinsics.checkNotNull(resources);
                int i = R.color.success30;
                Context context2 = this.this$0.getContext();
                textView.setTextColor(resources.getColor(i, context2 != null ? context2.getTheme() : null));
                this.binding.progressPgItemSyncAnswerPartial.setProgress(100);
                this.binding.ulangiB.setVisibility(8);
            } else {
                Double progress2 = unit != null ? unit.getProgress() : null;
                Intrinsics.checkNotNull(progress2);
                if (progress2.doubleValue() < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    this.binding.deskripsiTvItemSyncAnswerPartial.setText("Kesalahan");
                    TextView textView2 = this.binding.deskripsiTvItemSyncAnswerPartial;
                    Context context3 = this.this$0.getContext();
                    Resources resources2 = context3 != null ? context3.getResources() : null;
                    Intrinsics.checkNotNull(resources2);
                    int i2 = R.color.error30;
                    Context context4 = this.this$0.getContext();
                    textView2.setTextColor(resources2.getColor(i2, context4 != null ? context4.getTheme() : null));
                    this.binding.progressPgItemSyncAnswerPartial.setProgress(0);
                    this.binding.ulangiB.setVisibility(0);
                } else {
                    this.binding.deskripsiTvItemSyncAnswerPartial.setText("Sedang mengunduh...");
                    TextView textView3 = this.binding.deskripsiTvItemSyncAnswerPartial;
                    Context context5 = this.this$0.getContext();
                    Resources resources3 = context5 != null ? context5.getResources() : null;
                    Intrinsics.checkNotNull(resources3);
                    int i3 = R.color.primary30;
                    Context context6 = this.this$0.getContext();
                    textView3.setTextColor(resources3.getColor(i3, context6 != null ? context6.getTheme() : null));
                    ProgressBar progressBar = this.binding.progressPgItemSyncAnswerPartial;
                    Double progress3 = unit.getProgress();
                    progressBar.setProgress(progress3 != null ? MathKt.roundToInt(progress3.doubleValue()) : 0);
                    this.binding.ulangiB.setVisibility(8);
                }
            }
            Button button = this.binding.ulangiB;
            final SyncAnswerPartialPeriodeAdapter syncAnswerPartialPeriodeAdapter = this.this$0;
            button.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialPeriodeAdapter$SyncAnswerPartialPeriodeViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SyncAnswerPartialPeriodeAdapter.SyncAnswerPartialPeriodeViewHolder.setData$lambda$0(syncAnswerPartialPeriodeAdapter, unit, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setData$lambda$0(SyncAnswerPartialPeriodeAdapter this$0, SyncAnswerPartial syncAnswerPartial, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getViewModel().unduhUlang(syncAnswerPartial);
        }
    }
}
