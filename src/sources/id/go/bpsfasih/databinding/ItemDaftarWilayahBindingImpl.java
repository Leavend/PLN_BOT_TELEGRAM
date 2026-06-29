package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public class ItemDaftarWilayahBindingImpl extends ItemDaftarWilayahBinding {
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
        sparseIntArray.put(R.id.tv_offline_sampling, 1);
        sparseIntArray.put(R.id.img_wrapped_data_key, 2);
        sparseIntArray.put(R.id.btn_rekap_wilayah, 3);
        sparseIntArray.put(R.id.ll_region_code, 4);
        sparseIntArray.put(R.id.tv_label_region_code, 5);
        sparseIntArray.put(R.id.tv_value_region_code, 6);
        sparseIntArray.put(R.id.ll_level_1, 7);
        sparseIntArray.put(R.id.tv_label_level_1, 8);
        sparseIntArray.put(R.id.tv_value_level_1, 9);
        sparseIntArray.put(R.id.ll_level_2, 10);
        sparseIntArray.put(R.id.tv_label_level_2, 11);
        sparseIntArray.put(R.id.tv_value_level_2, 12);
        sparseIntArray.put(R.id.ll_level_3, 13);
        sparseIntArray.put(R.id.tv_label_level_3, 14);
        sparseIntArray.put(R.id.tv_value_level_3, 15);
        sparseIntArray.put(R.id.ll_level_4, 16);
        sparseIntArray.put(R.id.tv_label_level_4, 17);
        sparseIntArray.put(R.id.tv_value_level_4, 18);
        sparseIntArray.put(R.id.ll_level_5, 19);
        sparseIntArray.put(R.id.tv_label_level_5, 20);
        sparseIntArray.put(R.id.tv_value_level_5, 21);
        sparseIntArray.put(R.id.ll_level_6, 22);
        sparseIntArray.put(R.id.tv_label_level_6, 23);
        sparseIntArray.put(R.id.tv_value_level_6, 24);
        sparseIntArray.put(R.id.ll_level_7, 25);
        sparseIntArray.put(R.id.tv_label_level_7, 26);
        sparseIntArray.put(R.id.tv_value_level_7, 27);
        sparseIntArray.put(R.id.ll_level_8, 28);
        sparseIntArray.put(R.id.tv_label_level_8, 29);
        sparseIntArray.put(R.id.tv_value_level_8, 30);
        sparseIntArray.put(R.id.ll_level_9, 31);
        sparseIntArray.put(R.id.tv_label_level_9, 32);
        sparseIntArray.put(R.id.tv_value_level_9, 33);
        sparseIntArray.put(R.id.ll_level_10, 34);
        sparseIntArray.put(R.id.tv_label_level_10, 35);
        sparseIntArray.put(R.id.tv_value_level_10, 36);
        sparseIntArray.put(R.id.tv_open, 37);
        sparseIntArray.put(R.id.tv_submit, 38);
        sparseIntArray.put(R.id.tv_reject, 39);
        sparseIntArray.put(R.id.tv_pending, 40);
        sparseIntArray.put(R.id.tv_approve, 41);
    }

    public ItemDaftarWilayahBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 42, sIncludes, sViewsWithIds));
    }

    private ItemDaftarWilayahBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[3], (ImageView) bindings[2], (LinearLayout) bindings[7], (LinearLayout) bindings[34], (LinearLayout) bindings[10], (LinearLayout) bindings[13], (LinearLayout) bindings[16], (LinearLayout) bindings[19], (LinearLayout) bindings[22], (LinearLayout) bindings[25], (LinearLayout) bindings[28], (LinearLayout) bindings[31], (LinearLayout) bindings[4], (TextView) bindings[41], (TextView) bindings[8], (TextView) bindings[35], (TextView) bindings[11], (TextView) bindings[14], (TextView) bindings[17], (TextView) bindings[20], (TextView) bindings[23], (TextView) bindings[26], (TextView) bindings[29], (TextView) bindings[32], (TextView) bindings[5], (TextView) bindings[1], (TextView) bindings[37], (TextView) bindings[40], (TextView) bindings[39], (TextView) bindings[38], (TextView) bindings[9], (TextView) bindings[36], (TextView) bindings[12], (TextView) bindings[15], (TextView) bindings[18], (TextView) bindings[21], (TextView) bindings[24], (TextView) bindings[27], (TextView) bindings[30], (TextView) bindings[33], (TextView) bindings[6], (LinearLayout) bindings[0]);
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
