package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity;

/* loaded from: classes2.dex */
public abstract class AssignmentStructureBinding extends ViewDataBinding {
    public final ViewToolbarBinding include3;

    @Bindable
    protected AssignmentStructureActivity mViewModel;
    public final WebView wvMain;

    public abstract void setViewModel(AssignmentStructureActivity viewModel);

    protected AssignmentStructureBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewToolbarBinding include3, WebView wvMain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include3 = include3;
        this.wvMain = wvMain;
    }

    public AssignmentStructureActivity getViewModel() {
        return this.mViewModel;
    }

    public static AssignmentStructureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AssignmentStructureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AssignmentStructureBinding) ViewDataBinding.inflateInternal(inflater, R.layout.assignment_structure, root, attachToRoot, component);
    }

    public static AssignmentStructureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AssignmentStructureBinding inflate(LayoutInflater inflater, Object component) {
        return (AssignmentStructureBinding) ViewDataBinding.inflateInternal(inflater, R.layout.assignment_structure, null, false, component);
    }

    public static AssignmentStructureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AssignmentStructureBinding bind(View view, Object component) {
        return (AssignmentStructureBinding) bind(component, view, R.layout.assignment_structure);
    }
}
