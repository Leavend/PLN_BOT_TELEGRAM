package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.backupRestore.BackupRestoreViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityBackupRestoreBinding extends ViewDataBinding {
    public final TextView backupData;
    public final ConstraintLayout backupDataL;
    public final ViewToolbarBinding include;

    @Bindable
    protected BackupRestoreViewModel mViewModel;
    public final TextView restoreData;
    public final ConstraintLayout restoreDataL;
    public final TextView shareError;
    public final ConstraintLayout shareErrorL;

    public abstract void setViewModel(BackupRestoreViewModel viewModel);

    protected ActivityBackupRestoreBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView backupData, ConstraintLayout backupDataL, ViewToolbarBinding include, TextView restoreData, ConstraintLayout restoreDataL, TextView shareError, ConstraintLayout shareErrorL) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backupData = backupData;
        this.backupDataL = backupDataL;
        this.include = include;
        this.restoreData = restoreData;
        this.restoreDataL = restoreDataL;
        this.shareError = shareError;
        this.shareErrorL = shareErrorL;
    }

    public BackupRestoreViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityBackupRestoreBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBackupRestoreBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBackupRestoreBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_backup_restore, root, attachToRoot, component);
    }

    public static ActivityBackupRestoreBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBackupRestoreBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBackupRestoreBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_backup_restore, null, false, component);
    }

    public static ActivityBackupRestoreBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBackupRestoreBinding bind(View view, Object component) {
        return (ActivityBackupRestoreBinding) bind(component, view, R.layout.activity_backup_restore);
    }
}
