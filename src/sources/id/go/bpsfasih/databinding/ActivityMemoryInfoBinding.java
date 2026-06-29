package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.memoryInfo.MemoryInfoViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityMemoryInfoBinding extends ViewDataBinding {
    public final ViewToolbarBinding include;

    @Bindable
    protected MemoryInfoViewModel mViewModel;
    public final TextView memoryAvail;
    public final TextView memoryAvailText;
    public final TextView memoryTotal;
    public final TextView memoryTotalText;
    public final TextView memoryUsage;
    public final TextView memoryUsageText;
    public final TextView storageAvail;
    public final TextView storageAvailText;
    public final TextView storageTotal;
    public final TextView storageTotalText;
    public final TextView storageUsage;
    public final TextView storageUsageText;

    public abstract void setViewModel(MemoryInfoViewModel viewModel);

    protected ActivityMemoryInfoBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewToolbarBinding include, TextView memoryAvail, TextView memoryAvailText, TextView memoryTotal, TextView memoryTotalText, TextView memoryUsage, TextView memoryUsageText, TextView storageAvail, TextView storageAvailText, TextView storageTotal, TextView storageTotalText, TextView storageUsage, TextView storageUsageText) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include = include;
        this.memoryAvail = memoryAvail;
        this.memoryAvailText = memoryAvailText;
        this.memoryTotal = memoryTotal;
        this.memoryTotalText = memoryTotalText;
        this.memoryUsage = memoryUsage;
        this.memoryUsageText = memoryUsageText;
        this.storageAvail = storageAvail;
        this.storageAvailText = storageAvailText;
        this.storageTotal = storageTotal;
        this.storageTotalText = storageTotalText;
        this.storageUsage = storageUsage;
        this.storageUsageText = storageUsageText;
    }

    public MemoryInfoViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMemoryInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMemoryInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMemoryInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_memory_info, root, attachToRoot, component);
    }

    public static ActivityMemoryInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMemoryInfoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMemoryInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_memory_info, null, false, component);
    }

    public static ActivityMemoryInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMemoryInfoBinding bind(View view, Object component) {
        return (ActivityMemoryInfoBinding) bind(component, view, R.layout.activity_memory_info);
    }
}
