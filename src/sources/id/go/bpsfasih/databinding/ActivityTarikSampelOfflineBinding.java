package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityTarikSampelOfflineBinding extends ViewDataBinding {

    @Bindable
    protected TarikSampelViewModel mViewModel;
    public final WebView wvMain;

    public abstract void setViewModel(TarikSampelViewModel viewModel);

    protected ActivityTarikSampelOfflineBinding(Object _bindingComponent, View _root, int _localFieldCount, WebView wvMain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.wvMain = wvMain;
    }

    public TarikSampelViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityTarikSampelOfflineBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelOfflineBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTarikSampelOfflineBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tarik_sampel_offline, root, attachToRoot, component);
    }

    public static ActivityTarikSampelOfflineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelOfflineBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTarikSampelOfflineBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tarik_sampel_offline, null, false, component);
    }

    public static ActivityTarikSampelOfflineBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelOfflineBinding bind(View view, Object component) {
        return (ActivityTarikSampelOfflineBinding) bind(component, view, R.layout.activity_tarik_sampel_offline);
    }
}
