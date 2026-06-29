package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityFormgearDownloadBinding extends ViewDataBinding {
    public final ImageView icon;
    public final TextView keterangan;

    @Bindable
    protected DownloadFormEngineViewModel mViewModel;
    public final Button unduhEngine;

    public abstract void setViewModel(DownloadFormEngineViewModel viewModel);

    protected ActivityFormgearDownloadBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView icon, TextView keterangan, Button unduhEngine) {
        super(_bindingComponent, _root, _localFieldCount);
        this.icon = icon;
        this.keterangan = keterangan;
        this.unduhEngine = unduhEngine;
    }

    public DownloadFormEngineViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityFormgearDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearDownloadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityFormgearDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_formgear_download, root, attachToRoot, component);
    }

    public static ActivityFormgearDownloadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearDownloadBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityFormgearDownloadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_formgear_download, null, false, component);
    }

    public static ActivityFormgearDownloadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearDownloadBinding bind(View view, Object component) {
        return (ActivityFormgearDownloadBinding) bind(component, view, R.layout.activity_formgear_download);
    }
}
