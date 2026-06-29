package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.periode.PeriodeViewModel;

/* loaded from: classes2.dex */
public class ActivityPeriodeBindingImpl extends ActivityPeriodeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ItemSurveyShimmerBinding mboundView1;
    private final ItemSurveyShimmerBinding mboundView11;
    private final ItemSurveyShimmerBinding mboundView12;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(15);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{5}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.progressBar, 6);
        sparseIntArray.put(R.id.notEmptyData_cl, 7);
        sparseIntArray.put(R.id.deskripsi_tv, 8);
        sparseIntArray.put(R.id.syncNotEmptyData_b, 9);
        sparseIntArray.put(R.id.periodeRecycler, 10);
        sparseIntArray.put(R.id.emptyData_cl, 11);
        sparseIntArray.put(R.id.emptyData_tv, 12);
        sparseIntArray.put(R.id.syncEmptyData_b, 13);
        sparseIntArray.put(R.id.shimmer_view_container_periode, 14);
    }

    public ActivityPeriodeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private ActivityPeriodeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[8], (ConstraintLayout) bindings[11], (TextView) bindings[12], (ViewToolbarBinding) bindings[5], (LinearLayout) bindings[1], (ConstraintLayout) bindings[7], (RecyclerView) bindings[10], (ProgressBar) bindings[6], (ShimmerFrameLayout) bindings[14], (Button) bindings[13], (Button) bindings[9]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.include3);
        this.llShimmerContainer.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        Object obj = bindings[2];
        this.mboundView1 = obj != null ? ItemSurveyShimmerBinding.bind((View) obj) : null;
        Object obj2 = bindings[3];
        this.mboundView11 = obj2 != null ? ItemSurveyShimmerBinding.bind((View) obj2) : null;
        Object obj3 = bindings[4];
        this.mboundView12 = obj3 != null ? ItemSurveyShimmerBinding.bind((View) obj3) : null;
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.include3.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.include3.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((PeriodeViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityPeriodeBinding
    public void setViewModel(PeriodeViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.include3.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeInclude3((ViewToolbarBinding) object, fieldId);
    }

    private boolean onChangeInclude3(ViewToolbarBinding Include3, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        executeBindingsOn(this.include3);
    }
}
