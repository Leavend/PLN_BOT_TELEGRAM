package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel;

/* loaded from: classes2.dex */
public class ActivityAssignmentlistBindingImpl extends ActivityAssignmentlistBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback15;
    private final View.OnClickListener mCallback16;
    private final View.OnClickListener mCallback17;
    private final View.OnClickListener mCallback18;
    private final View.OnClickListener mCallback19;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{6}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.wv_main, 7);
        sparseIntArray.put(R.id.expendable_fab, 8);
    }

    public ActivityAssignmentlistBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityAssignmentlistBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ExpandableFab) bindings[8], (FabOption) bindings[5], (FabOption) bindings[1], (FabOption) bindings[3], (FabOption) bindings[4], (FabOption) bindings[2], (ViewToolbarBinding) bindings[6], (WebView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.fabAddAssignment.setTag(null);
        this.fabColorInfo.setTag(null);
        this.fabFilterByStatus.setTag(null);
        this.fabRegionDone.setTag(null);
        this.fabSorting.setTag(null);
        setContainedBinding(this.include3);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        setRootTag(root);
        this.mCallback16 = new OnClickListener(this, 2);
        this.mCallback17 = new OnClickListener(this, 3);
        this.mCallback15 = new OnClickListener(this, 1);
        this.mCallback18 = new OnClickListener(this, 4);
        this.mCallback19 = new OnClickListener(this, 5);
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
        setViewModel((AssignmentUpdateListingViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityAssignmentlistBinding
    public void setViewModel(AssignmentUpdateListingViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
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
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.mViewModel;
        if ((j & 4) != 0) {
            this.fabAddAssignment.setOnClickListener(this.mCallback19);
            this.fabColorInfo.setOnClickListener(this.mCallback15);
            this.fabFilterByStatus.setOnClickListener(this.mCallback17);
            this.fabRegionDone.setOnClickListener(this.mCallback18);
            this.fabSorting.setOnClickListener(this.mCallback16);
        }
        executeBindingsOn(this.include3);
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        if (sourceId == 1) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.mViewModel;
            if (assignmentUpdateListingViewModel != null) {
                assignmentUpdateListingViewModel.showInfoWarna();
                return;
            }
            return;
        }
        if (sourceId == 2) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = this.mViewModel;
            if (assignmentUpdateListingViewModel2 != null) {
                assignmentUpdateListingViewModel2.sorting();
                return;
            }
            return;
        }
        if (sourceId == 3) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = this.mViewModel;
            if (assignmentUpdateListingViewModel3 != null) {
                assignmentUpdateListingViewModel3.filterByStatus();
                return;
            }
            return;
        }
        if (sourceId == 4) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = this.mViewModel;
            if (assignmentUpdateListingViewModel4 != null) {
                assignmentUpdateListingViewModel4.showDialogDoneOrUndone();
                return;
            }
            return;
        }
        if (sourceId != 5) {
            return;
        }
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel5 = this.mViewModel;
        if (assignmentUpdateListingViewModel5 != null) {
            assignmentUpdateListingViewModel5.addAssignment();
        }
    }
}
