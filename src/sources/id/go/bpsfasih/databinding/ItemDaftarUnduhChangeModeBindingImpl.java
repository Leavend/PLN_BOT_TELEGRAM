package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public class ItemDaftarUnduhChangeModeBindingImpl extends ItemDaftarUnduhChangeModeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_data_1, 1);
        sparseIntArray.put(R.id.tv_label_data_1, 2);
        sparseIntArray.put(R.id.tv_value_data_1, 3);
        sparseIntArray.put(R.id.ll_data_2, 4);
        sparseIntArray.put(R.id.tv_label_data_2, 5);
        sparseIntArray.put(R.id.tv_value_data_2, 6);
        sparseIntArray.put(R.id.ll_data_3, 7);
        sparseIntArray.put(R.id.tv_label_data_3, 8);
        sparseIntArray.put(R.id.tv_value_data_3, 9);
        sparseIntArray.put(R.id.ll_data_4, 10);
        sparseIntArray.put(R.id.tv_label_data_4, 11);
        sparseIntArray.put(R.id.tv_value_data_4, 12);
        sparseIntArray.put(R.id.ll_data_5, 13);
        sparseIntArray.put(R.id.tv_label_data_5, 14);
        sparseIntArray.put(R.id.tv_value_data_5, 15);
        sparseIntArray.put(R.id.ll_data_6, 16);
        sparseIntArray.put(R.id.tv_label_data_6, 17);
        sparseIntArray.put(R.id.tv_value_data_6, 18);
        sparseIntArray.put(R.id.ll_data_7, 19);
        sparseIntArray.put(R.id.tv_label_data_7, 20);
        sparseIntArray.put(R.id.tv_value_data_7, 21);
        sparseIntArray.put(R.id.ll_data_8, 22);
        sparseIntArray.put(R.id.tv_label_data_8, 23);
        sparseIntArray.put(R.id.tv_value_data_8, 24);
        sparseIntArray.put(R.id.ll_data_9, 25);
        sparseIntArray.put(R.id.tv_label_data_9, 26);
        sparseIntArray.put(R.id.tv_value_data_9, 27);
        sparseIntArray.put(R.id.ll_data_10, 28);
        sparseIntArray.put(R.id.tv_label_data_10, 29);
        sparseIntArray.put(R.id.tv_value_data_10, 30);
        sparseIntArray.put(R.id.unduhAnswer_b, 31);
    }

    public ItemDaftarUnduhChangeModeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds));
    }

    private ItemDaftarUnduhChangeModeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[1], (LinearLayout) bindings[28], (LinearLayout) bindings[4], (LinearLayout) bindings[7], (LinearLayout) bindings[10], (LinearLayout) bindings[13], (LinearLayout) bindings[16], (LinearLayout) bindings[19], (LinearLayout) bindings[22], (LinearLayout) bindings[25], (TextView) bindings[2], (TextView) bindings[29], (TextView) bindings[5], (TextView) bindings[8], (TextView) bindings[11], (TextView) bindings[14], (TextView) bindings[17], (TextView) bindings[20], (TextView) bindings[23], (TextView) bindings[26], (TextView) bindings[3], (TextView) bindings[30], (TextView) bindings[6], (TextView) bindings[9], (TextView) bindings[12], (TextView) bindings[15], (TextView) bindings[18], (TextView) bindings[21], (TextView) bindings[24], (TextView) bindings[27], (Button) bindings[31], (LinearLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.updateListingLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
