package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ActivityMiniDashboardBinding extends ViewDataBinding {
    public final WebView wvMain;

    protected ActivityMiniDashboardBinding(Object _bindingComponent, View _root, int _localFieldCount, WebView wvMain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.wvMain = wvMain;
    }

    public static ActivityMiniDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMiniDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMiniDashboardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_mini_dashboard, root, attachToRoot, component);
    }

    public static ActivityMiniDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMiniDashboardBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMiniDashboardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_mini_dashboard, null, false, component);
    }

    public static ActivityMiniDashboardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMiniDashboardBinding bind(View view, Object component) {
        return (ActivityMiniDashboardBinding) bind(component, view, R.layout.activity_mini_dashboard);
    }
}
