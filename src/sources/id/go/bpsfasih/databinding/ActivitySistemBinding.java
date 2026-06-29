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
import id.go.bpsfasih.ui.sistem.SistemViewModel;

/* loaded from: classes2.dex */
public abstract class ActivitySistemBinding extends ViewDataBinding {
    public final ConstraintLayout changeLogAplikasi;
    public final ConstraintLayout changeLogFormGear;
    public final ViewToolbarBinding include;

    @Bindable
    protected SistemViewModel mViewModel;
    public final TextView memoryInfo;
    public final TextView versiApp;
    public final TextView versiAppText;
    public final TextView versiCode;
    public final TextView versiCodeText;

    public abstract void setViewModel(SistemViewModel viewModel);

    protected ActivitySistemBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout changeLogAplikasi, ConstraintLayout changeLogFormGear, ViewToolbarBinding include, TextView memoryInfo, TextView versiApp, TextView versiAppText, TextView versiCode, TextView versiCodeText) {
        super(_bindingComponent, _root, _localFieldCount);
        this.changeLogAplikasi = changeLogAplikasi;
        this.changeLogFormGear = changeLogFormGear;
        this.include = include;
        this.memoryInfo = memoryInfo;
        this.versiApp = versiApp;
        this.versiAppText = versiAppText;
        this.versiCode = versiCode;
        this.versiCodeText = versiCodeText;
    }

    public SistemViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivitySistemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySistemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySistemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sistem, root, attachToRoot, component);
    }

    public static ActivitySistemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySistemBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySistemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sistem, null, false, component);
    }

    public static ActivitySistemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySistemBinding bind(View view, Object component) {
        return (ActivitySistemBinding) bind(component, view, R.layout.activity_sistem);
    }
}
