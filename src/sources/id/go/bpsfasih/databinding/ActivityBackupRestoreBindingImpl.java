package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.backupRestore.BackupRestoreViewModel;

/* loaded from: classes2.dex */
public class ActivityBackupRestoreBindingImpl extends ActivityBackupRestoreBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback4;
    private final View.OnClickListener mCallback5;
    private final View.OnClickListener mCallback6;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(8);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{4}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.backupData, 5);
        sparseIntArray.put(R.id.restoreData, 6);
        sparseIntArray.put(R.id.shareError, 7);
    }

    public ActivityBackupRestoreBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityBackupRestoreBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[5], (ConstraintLayout) bindings[1], (ViewToolbarBinding) bindings[4], (TextView) bindings[6], (ConstraintLayout) bindings[2], (TextView) bindings[7], (ConstraintLayout) bindings[3]);
        this.mDirtyFlags = -1L;
        this.backupDataL.setTag(null);
        setContainedBinding(this.include);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.restoreDataL.setTag(null);
        this.shareErrorL.setTag(null);
        setRootTag(root);
        this.mCallback5 = new OnClickListener(this, 2);
        this.mCallback6 = new OnClickListener(this, 3);
        this.mCallback4 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        setViewModel((BackupRestoreViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityBackupRestoreBinding
    public void setViewModel(BackupRestoreViewModel ViewModel) {
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
        this.include.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeInclude((ViewToolbarBinding) object, fieldId);
    }

    private boolean onChangeInclude(ViewToolbarBinding Include, int fieldId) {
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
        BackupRestoreViewModel backupRestoreViewModel = this.mViewModel;
        if ((j & 4) != 0) {
            this.backupDataL.setOnClickListener(this.mCallback4);
            this.restoreDataL.setOnClickListener(this.mCallback5);
            this.shareErrorL.setOnClickListener(this.mCallback6);
        }
        executeBindingsOn(this.include);
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        if (sourceId == 1) {
            BackupRestoreViewModel backupRestoreViewModel = this.mViewModel;
            if (backupRestoreViewModel != null) {
                backupRestoreViewModel.backupDataClicked();
                return;
            }
            return;
        }
        if (sourceId == 2) {
            BackupRestoreViewModel backupRestoreViewModel2 = this.mViewModel;
            if (backupRestoreViewModel2 != null) {
                backupRestoreViewModel2.restoreDataClicked();
                return;
            }
            return;
        }
        if (sourceId != 3) {
            return;
        }
        BackupRestoreViewModel backupRestoreViewModel3 = this.mViewModel;
        if (backupRestoreViewModel3 != null) {
            backupRestoreViewModel3.shareErrorClicked();
        }
    }
}
