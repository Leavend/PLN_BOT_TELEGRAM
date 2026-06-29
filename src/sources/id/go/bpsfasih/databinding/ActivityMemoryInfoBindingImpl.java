package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.memoryInfo.MemoryInfoViewModel;

/* loaded from: classes2.dex */
public class ActivityMemoryInfoBindingImpl extends ActivityMemoryInfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(14);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{7}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.storage_usage, 8);
        sparseIntArray.put(R.id.storage_avail, 9);
        sparseIntArray.put(R.id.storage_total, 10);
        sparseIntArray.put(R.id.memory_usage, 11);
        sparseIntArray.put(R.id.memory_avail, 12);
        sparseIntArray.put(R.id.memory_total, 13);
    }

    public ActivityMemoryInfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityMemoryInfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7, (ViewToolbarBinding) bindings[7], (TextView) bindings[12], (TextView) bindings[5], (TextView) bindings[13], (TextView) bindings[6], (TextView) bindings[11], (TextView) bindings[4], (TextView) bindings[9], (TextView) bindings[2], (TextView) bindings[10], (TextView) bindings[3], (TextView) bindings[8], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.include);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.memoryAvailText.setTag(null);
        this.memoryTotalText.setTag(null);
        this.memoryUsageText.setTag(null);
        this.storageAvailText.setTag(null);
        this.storageTotalText.setTag(null);
        this.storageUsageText.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
        }
        this.include.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.include.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((MemoryInfoViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityMemoryInfoBinding
    public void setViewModel(MemoryInfoViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.include.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelMemoryUsage((ObservableField) object, fieldId);
            case 1:
                return onChangeInclude((ViewToolbarBinding) object, fieldId);
            case 2:
                return onChangeViewModelStorageUsage((ObservableField) object, fieldId);
            case 3:
                return onChangeViewModelStorageTotal((ObservableField) object, fieldId);
            case 4:
                return onChangeViewModelMemoryTotal((ObservableField) object, fieldId);
            case 5:
                return onChangeViewModelMemoryAvail((ObservableField) object, fieldId);
            case 6:
                return onChangeViewModelStorageAvail((ObservableField) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelMemoryUsage(ObservableField<String> ViewModelMemoryUsage, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeInclude(ViewToolbarBinding Include, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelStorageUsage(ObservableField<String> ViewModelStorageUsage, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelStorageTotal(ObservableField<String> ViewModelStorageTotal, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelMemoryTotal(ObservableField<String> ViewModelMemoryTotal, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelMemoryAvail(ObservableField<String> ViewModelMemoryAvail, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelStorageAvail(ObservableField<String> ViewModelStorageAvail, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00bd  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.databinding.ActivityMemoryInfoBindingImpl.executeBindings():void");
    }
}
