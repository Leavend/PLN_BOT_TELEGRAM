package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel;

/* loaded from: classes2.dex */
public class ActivityFormgearBindingImpl extends ActivityFormgearBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.wv_main, 1);
        sparseIntArray.put(R.id.rv_bukan_pencacah, 2);
        sparseIntArray.put(R.id.expendable_fab_bukan_pencacah, 3);
        sparseIntArray.put(R.id.fab_rebuild_bukan_pencacah, 4);
        sparseIntArray.put(R.id.fab_calculator_bukan_pencacah, 5);
        sparseIntArray.put(R.id.fab_notes_bukan_pencacah, 6);
        sparseIntArray.put(R.id.fab_bantuan_bukan_pencacah, 7);
        sparseIntArray.put(R.id.fab_approval, 8);
        sparseIntArray.put(R.id.rv_pencacah, 9);
        sparseIntArray.put(R.id.expendable_fab_pencacah, 10);
        sparseIntArray.put(R.id.fab_simpan_sementara_pencacah, 11);
        sparseIntArray.put(R.id.fab_rebuild_pencacah, 12);
        sparseIntArray.put(R.id.fab_calculator_pencacah, 13);
        sparseIntArray.put(R.id.fab_notes_pencacah, 14);
        sparseIntArray.put(R.id.fab_bantuan_pencacah, 15);
        sparseIntArray.put(R.id.fab_location_pencacah, 16);
        sparseIntArray.put(R.id.badge_encrypted, 17);
    }

    public ActivityFormgearBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityFormgearBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[17], (ExpandableFab) bindings[3], (ExpandableFab) bindings[10], (FabOption) bindings[8], (FabOption) bindings[7], (FabOption) bindings[15], (FabOption) bindings[5], (FabOption) bindings[13], (FabOption) bindings[16], (FabOption) bindings[6], (FabOption) bindings[14], (FabOption) bindings[4], (FabOption) bindings[12], (FabOption) bindings[11], (RelativeLayout) bindings[0], (RelativeLayout) bindings[2], (RelativeLayout) bindings[9], (WebView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.rootLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((AssignmentUpdateListingViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityFormgearBinding
    public void setViewModel(AssignmentUpdateListingViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
