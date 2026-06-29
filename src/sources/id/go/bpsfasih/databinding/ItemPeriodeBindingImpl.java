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
public class ItemPeriodeBindingImpl extends ItemPeriodeBinding {
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
        sparseIntArray.put(R.id.periode_title, 1);
        sparseIntArray.put(R.id.tv_open, 2);
        sparseIntArray.put(R.id.tv_submit, 3);
        sparseIntArray.put(R.id.tv_reject, 4);
        sparseIntArray.put(R.id.tv_pending, 5);
        sparseIntArray.put(R.id.tv_approve, 6);
        sparseIntArray.put(R.id.tv_lihat, 7);
        sparseIntArray.put(R.id.periode_time, 8);
        sparseIntArray.put(R.id.btn_update, 9);
    }

    public ItemPeriodeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemPeriodeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[9], (LinearLayout) bindings[0], (TextView) bindings[8], (TextView) bindings[1], (TextView) bindings[6], (LinearLayout) bindings[7], (TextView) bindings[2], (TextView) bindings[5], (TextView) bindings[4], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.periodeConstrain.setTag(null);
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
