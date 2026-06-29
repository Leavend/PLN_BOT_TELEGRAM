package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel;

/* loaded from: classes2.dex */
public class ActivityDaftarWilayahBindingImpl extends ActivityDaftarWilayahBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ViewToolbarBinding mboundView0;
    private final LinearLayout mboundView01;
    private final ItemDaftarWilayahShimmerBinding mboundView1;
    private final ItemDaftarWilayahShimmerBinding mboundView11;
    private final ItemDaftarWilayahShimmerBinding mboundView12;
    private final ItemDaftarWilayahShimmerBinding mboundView13;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(19);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{6}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.progressBar, 7);
        sparseIntArray.put(R.id.notEmptyData_cl, 8);
        sparseIntArray.put(R.id.swipeUpdateListing, 9);
        sparseIntArray.put(R.id.search_cl, 10);
        sparseIntArray.put(R.id.search_et, 11);
        sparseIntArray.put(R.id.sort_l, 12);
        sparseIntArray.put(R.id.syncNotEmptyData_b, 13);
        sparseIntArray.put(R.id.update_listing_recycler, 14);
        sparseIntArray.put(R.id.emptyData_cl, 15);
        sparseIntArray.put(R.id.emptyData_tv, 16);
        sparseIntArray.put(R.id.syncEmptyData_b, 17);
        sparseIntArray.put(R.id.shimmer_view_container_blok_sensus, 18);
    }

    public ActivityDaftarWilayahBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ActivityDaftarWilayahBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[15], (TextView) bindings[16], (LinearLayout) bindings[1], (ConstraintLayout) bindings[8], (ProgressBar) bindings[7], (LinearLayout) bindings[10], (SearchView) bindings[11], (ShimmerFrameLayout) bindings[18], (LinearLayout) bindings[12], (NestedScrollView) bindings[9], (Button) bindings[17], (Button) bindings[13], (RecyclerView) bindings[14]);
        this.mDirtyFlags = -1L;
        this.llShimmerContainer.setTag(null);
        ViewToolbarBinding viewToolbarBinding = (ViewToolbarBinding) bindings[6];
        this.mboundView0 = viewToolbarBinding;
        setContainedBinding(viewToolbarBinding);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView01 = linearLayout;
        linearLayout.setTag(null);
        Object obj = bindings[2];
        this.mboundView1 = obj != null ? ItemDaftarWilayahShimmerBinding.bind((View) obj) : null;
        Object obj2 = bindings[3];
        this.mboundView11 = obj2 != null ? ItemDaftarWilayahShimmerBinding.bind((View) obj2) : null;
        Object obj3 = bindings[4];
        this.mboundView12 = obj3 != null ? ItemDaftarWilayahShimmerBinding.bind((View) obj3) : null;
        Object obj4 = bindings[5];
        this.mboundView13 = obj4 != null ? ItemDaftarWilayahShimmerBinding.bind((View) obj4) : null;
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView0.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((UpdateListingViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityDaftarWilayahBinding
    public void setViewModel(UpdateListingViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        executeBindingsOn(this.mboundView0);
    }
}
