package id.go.bpsfasih.ui.unduhChangeMode;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.databinding.ItemDaftarUnduhChangeModeBinding;
import id.go.bpsfasih.domain.models.UnduhChangeModeModel;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: UnduhChangeModeAdapter.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001'B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001aH\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001aH\u0016J\u001b\u0010#\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140%H\u0000¢\u0006\u0002\b&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeAdapter$UnduhChangeModeViewHolder;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "viewModel", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;", "(Landroid/app/Activity;Landroid/content/Context;Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;)V", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "list", "", "Lid/go/bpsfasih/domain/models/UnduhChangeModeModel;", "getViewModel", "()Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "", "setData$app_release", "UnduhChangeModeViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UnduhChangeModeAdapter extends RecyclerView.Adapter<UnduhChangeModeViewHolder> {
    public static final int $stable = 8;
    private Activity activity;
    private Context context;
    private List<UnduhChangeModeModel> list;
    private UnduhChangeModeViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1$lambda$0(View view) {
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final void setActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.activity = activity;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final UnduhChangeModeViewModel getViewModel() {
        return this.viewModel;
    }

    public final void setViewModel(UnduhChangeModeViewModel unduhChangeModeViewModel) {
        Intrinsics.checkNotNullParameter(unduhChangeModeViewModel, "<set-?>");
        this.viewModel = unduhChangeModeViewModel;
    }

    public UnduhChangeModeAdapter(Activity activity, Context context, UnduhChangeModeViewModel viewModel) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.activity = activity;
        this.context = context;
        this.viewModel = viewModel;
        this.list = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());
    }

    /* compiled from: UnduhChangeModeAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeAdapter$UnduhChangeModeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemDaftarUnduhChangeModeBinding;", "(Lid/go/bpsfasih/databinding/ItemDaftarUnduhChangeModeBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemDaftarUnduhChangeModeBinding;", "bind", "", "item", "Lid/go/bpsfasih/domain/models/UnduhChangeModeModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class UnduhChangeModeViewHolder extends RecyclerView.ViewHolder {
        public static final int $stable = 8;
        private final ItemDaftarUnduhChangeModeBinding binding;

        public final ItemDaftarUnduhChangeModeBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnduhChangeModeViewHolder(ItemDaftarUnduhChangeModeBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(UnduhChangeModeModel item) {
            Intrinsics.checkNotNullParameter(item, "item");
            if (item.getLabel1() != null) {
                this.binding.llData1.setVisibility(0);
                this.binding.tvLabelData1.setText(item.getLabel1());
                this.binding.tvValueData1.setText(item.getData1());
            }
            if (item.getLabel2() != null) {
                this.binding.llData2.setVisibility(0);
                this.binding.tvLabelData2.setText(item.getLabel2());
                this.binding.tvValueData2.setText(item.getData2());
            }
            if (item.getLabel3() != null) {
                this.binding.llData3.setVisibility(0);
                this.binding.tvLabelData3.setText(item.getLabel3());
                this.binding.tvValueData3.setText(item.getData3());
            }
            if (item.getLabel4() != null) {
                this.binding.llData4.setVisibility(0);
                this.binding.tvLabelData4.setText(item.getLabel4());
                this.binding.tvValueData4.setText(item.getData4());
            }
            if (item.getLabel5() != null) {
                this.binding.llData5.setVisibility(0);
                this.binding.tvLabelData5.setText(item.getLabel5());
                this.binding.tvValueData5.setText(item.getData5());
            }
            if (item.getLabel6() != null) {
                this.binding.llData6.setVisibility(0);
                this.binding.tvLabelData6.setText(item.getLabel6());
                this.binding.tvValueData6.setText(item.getData6());
            }
            if (item.getLabel7() != null) {
                this.binding.llData7.setVisibility(0);
                this.binding.tvLabelData7.setText(item.getLabel7());
                this.binding.tvValueData7.setText(item.getData7());
            }
            if (item.getLabel8() != null) {
                this.binding.llData8.setVisibility(0);
                this.binding.tvLabelData8.setText(item.getLabel8());
                this.binding.tvValueData8.setText(item.getData8());
            }
            if (item.getLabel9() != null) {
                this.binding.llData9.setVisibility(0);
                this.binding.tvLabelData9.setText(item.getLabel9());
                this.binding.tvValueData9.setText(item.getData9());
            }
            if (item.getLabel10() != null) {
                this.binding.llData10.setVisibility(0);
                this.binding.tvLabelData10.setText(item.getLabel10());
                this.binding.tvValueData10.setText(item.getData10());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UnduhChangeModeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemDaftarUnduhChangeModeBinding binding = (ItemDaftarUnduhChangeModeBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_daftar_unduh_change_mode, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new UnduhChangeModeViewHolder(binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(UnduhChangeModeViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final UnduhChangeModeModel unduhChangeModeModel = this.list.get(position);
        holder.bind(unduhChangeModeModel);
        if (unduhChangeModeModel.isDownloaded()) {
            holder.getBinding().unduhAnswerB.setVisibility(0);
        } else {
            holder.getBinding().unduhAnswerB.setVisibility(8);
        }
        holder.getBinding().unduhAnswerB.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UnduhChangeModeAdapter.onBindViewHolder$lambda$1(unduhChangeModeModel, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(UnduhChangeModeModel current, UnduhChangeModeAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(current, "$current");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (current.getBasePath() != null) {
            Activity activity = this$0.activity;
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
            ((UnduhChangeModeActivity) activity).showProgressBar();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UnduhChangeModeAdapter$onBindViewHolder$1$1(current, this$0, null), 2, null);
            return;
        }
        Activity activity2 = this$0.activity;
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
        UnduhChangeModeActivity unduhChangeModeActivity = (UnduhChangeModeActivity) activity2;
        int i = R.color.error30;
        int i2 = R.color.error30;
        unduhChangeModeActivity.showAlertDialogColor("Gagal", Integer.valueOf(i), "Link unduh tidak tersedia", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UnduhChangeModeAdapter.onBindViewHolder$lambda$1$lambda$0(view2);
            }
        }, null, null, null, Integer.valueOf(R.color.error30), true);
    }

    public final void setData$app_release(List<UnduhChangeModeModel> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.list = CollectionsKt.toMutableList((Collection) data);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
