package id.go.bpsfasih.ui.assignmentList;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.data.local.entities.CustomColumnEntity;
import id.go.bpsfasih.data.local.entities.Data;
import id.go.bpsfasih.databinding.ItemCustomColumnBinding;
import id.go.bpsfasih.ui.assignmentList.EditTampilanKolomBottomSheetAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EditTampilanKolomBottomSheetAdapter.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u001c\u0010\u001b\u001a\u00020\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u001c2\u0006\u0010\u001d\u001a\u00020\fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/EditTampilanKolomBottomSheetAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/assignmentList/EditTampilanKolomBottomSheetAdapter$EditTampilanKolomBottomSheetViewHolder;", "()V", "list", "", "Lid/go/bpsfasih/data/local/entities/Data;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "listSelected", "Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "getListSelected", "()Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;", "setListSelected", "(Lid/go/bpsfasih/data/local/entities/CustomColumnEntity;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "", "customColumnEntity", "EditTampilanKolomBottomSheetViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class EditTampilanKolomBottomSheetAdapter extends RecyclerView.Adapter<EditTampilanKolomBottomSheetViewHolder> {
    public static final int $stable = 8;
    private List<Data> list = new ArrayList();
    public CustomColumnEntity listSelected;

    public final List<Data> getList() {
        return this.list;
    }

    public final void setList(List<Data> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public final CustomColumnEntity getListSelected() {
        CustomColumnEntity customColumnEntity = this.listSelected;
        if (customColumnEntity != null) {
            return customColumnEntity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("listSelected");
        return null;
    }

    public final void setListSelected(CustomColumnEntity customColumnEntity) {
        Intrinsics.checkNotNullParameter(customColumnEntity, "<set-?>");
        this.listSelected = customColumnEntity;
    }

    /* compiled from: EditTampilanKolomBottomSheetAdapter.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J9\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\b0\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/EditTampilanKolomBottomSheetAdapter$EditTampilanKolomBottomSheetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemCustomColumnBinding;", "(Lid/go/bpsfasih/databinding/ItemCustomColumnBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemCustomColumnBinding;", "onBinding", "", "item", "", "isChecked", "", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class EditTampilanKolomBottomSheetViewHolder extends RecyclerView.ViewHolder {
        public static final int $stable = 8;
        private final ItemCustomColumnBinding binding;

        public final ItemCustomColumnBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EditTampilanKolomBottomSheetViewHolder(ItemCustomColumnBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void onBinding(String item, boolean isChecked, final Function1<? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.binding.columnNameTv.setText(item);
            this.binding.itemCb.setChecked(isChecked);
            this.binding.itemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: id.go.bpsfasih.ui.assignmentList.EditTampilanKolomBottomSheetAdapter$EditTampilanKolomBottomSheetViewHolder$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    EditTampilanKolomBottomSheetAdapter.EditTampilanKolomBottomSheetViewHolder.onBinding$lambda$0(callback, compoundButton, z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onBinding$lambda$0(Function1 callback, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(callback, "$callback");
            Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
            callback.invoke(Boolean.valueOf(z));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public EditTampilanKolomBottomSheetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemCustomColumnBinding itemCustomColumnBindingInflate = ItemCustomColumnBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(itemCustomColumnBindingInflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new EditTampilanKolomBottomSheetViewHolder(itemCustomColumnBindingInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(EditTampilanKolomBottomSheetViewHolder holder, final int position) {
        boolean data1;
        Intrinsics.checkNotNullParameter(holder, "holder");
        switch (position) {
            case 0:
                data1 = getListSelected().getData1();
                break;
            case 1:
                data1 = getListSelected().getData2();
                break;
            case 2:
                data1 = getListSelected().getData3();
                break;
            case 3:
                data1 = getListSelected().getData4();
                break;
            case 4:
                data1 = getListSelected().getData5();
                break;
            case 5:
                data1 = getListSelected().getData6();
                break;
            case 6:
                data1 = getListSelected().getData7();
                break;
            case 7:
                data1 = getListSelected().getData8();
                break;
            case 8:
                data1 = getListSelected().getData9();
                break;
            case 9:
                data1 = getListSelected().getData10();
                break;
            default:
                data1 = true;
                break;
        }
        holder.onBinding(String.valueOf(this.list.get(position).getColumnName()), data1, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.EditTampilanKolomBottomSheetAdapter.onBindViewHolder.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                switch (position) {
                    case 0:
                        this.getListSelected().setData1(z);
                        break;
                    case 1:
                        this.getListSelected().setData2(z);
                        break;
                    case 2:
                        this.getListSelected().setData3(z);
                        break;
                    case 3:
                        this.getListSelected().setData4(z);
                        break;
                    case 4:
                        this.getListSelected().setData5(z);
                        break;
                    case 5:
                        this.getListSelected().setData6(z);
                        break;
                    case 6:
                        this.getListSelected().setData7(z);
                        break;
                    case 7:
                        this.getListSelected().setData8(z);
                        break;
                    case 8:
                        this.getListSelected().setData9(z);
                        break;
                    case 9:
                        this.getListSelected().setData10(z);
                        break;
                }
            }
        });
    }

    public final void setData(List<Data> list, CustomColumnEntity customColumnEntity) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(customColumnEntity, "customColumnEntity");
        this.list.clear();
        this.list.addAll(list);
        setListSelected(customColumnEntity);
        notifyDataSetChanged();
    }
}
