package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.periode.PeriodeViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityUnduhChangeModeBinding extends ViewDataBinding {
    public final RecyclerView changeModeRecycler;
    public final TextView deskripsiTv;
    public final ViewToolbarBinding include3;

    @Bindable
    protected PeriodeViewModel mViewModel;

    public abstract void setViewModel(PeriodeViewModel viewModel);

    protected ActivityUnduhChangeModeBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView changeModeRecycler, TextView deskripsiTv, ViewToolbarBinding include3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.changeModeRecycler = changeModeRecycler;
        this.deskripsiTv = deskripsiTv;
        this.include3 = include3;
    }

    public PeriodeViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityUnduhChangeModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUnduhChangeModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityUnduhChangeModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_unduh_change_mode, root, attachToRoot, component);
    }

    public static ActivityUnduhChangeModeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUnduhChangeModeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityUnduhChangeModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_unduh_change_mode, null, false, component);
    }

    public static ActivityUnduhChangeModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUnduhChangeModeBinding bind(View view, Object component) {
        return (ActivityUnduhChangeModeBinding) bind(component, view, R.layout.activity_unduh_change_mode);
    }
}
