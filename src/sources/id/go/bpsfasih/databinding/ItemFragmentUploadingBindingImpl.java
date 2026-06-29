package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public class ItemFragmentUploadingBindingImpl extends ItemFragmentUploadingBinding {
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
        sparseIntArray.put(R.id.constrain_uploading, 1);
        sparseIntArray.put(R.id.custom_data, 2);
        sparseIntArray.put(R.id.ll_data1, 3);
        sparseIntArray.put(R.id.title_data1, 4);
        sparseIntArray.put(R.id.value_data1, 5);
        sparseIntArray.put(R.id.ll_data2, 6);
        sparseIntArray.put(R.id.title_data2, 7);
        sparseIntArray.put(R.id.value_data2, 8);
        sparseIntArray.put(R.id.ll_data3, 9);
        sparseIntArray.put(R.id.title_data3, 10);
        sparseIntArray.put(R.id.value_data3, 11);
        sparseIntArray.put(R.id.ll_data4, 12);
        sparseIntArray.put(R.id.title_data4, 13);
        sparseIntArray.put(R.id.value_data4, 14);
        sparseIntArray.put(R.id.ll_data5, 15);
        sparseIntArray.put(R.id.title_data5, 16);
        sparseIntArray.put(R.id.value_data5, 17);
        sparseIntArray.put(R.id.tv_data_not_found, 18);
        sparseIntArray.put(R.id.tv_error_upload, 19);
        sparseIntArray.put(R.id.tv_belum_upload, 20);
        sparseIntArray.put(R.id.btn_reupload, 21);
    }

    public ItemFragmentUploadingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private ItemFragmentUploadingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[21], (ConstraintLayout) bindings[1], (LinearLayout) bindings[2], (CardView) bindings[0], (LinearLayout) bindings[3], (LinearLayout) bindings[6], (LinearLayout) bindings[9], (LinearLayout) bindings[12], (LinearLayout) bindings[15], (TextView) bindings[4], (TextView) bindings[7], (TextView) bindings[10], (TextView) bindings[13], (TextView) bindings[16], (TextView) bindings[20], (TextView) bindings[18], (TextView) bindings[19], (TextView) bindings[5], (TextView) bindings[8], (TextView) bindings[11], (TextView) bindings[14], (TextView) bindings[17]);
        this.mDirtyFlags = -1L;
        this.cvUploading.setTag(null);
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
