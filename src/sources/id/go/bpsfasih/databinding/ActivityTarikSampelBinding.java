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
import id.go.bpsfasih.ui.tariksampel.TarikSampelViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityTarikSampelBinding extends ViewDataBinding {
    public final ImageView icon;
    public final ViewToolbarBinding include3;
    public final TextView keterangan;

    @Bindable
    protected TarikSampelViewModel mViewModel;
    public final Button tarikSampel;

    public abstract void setViewModel(TarikSampelViewModel viewModel);

    protected ActivityTarikSampelBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView icon, ViewToolbarBinding include3, TextView keterangan, Button tarikSampel) {
        super(_bindingComponent, _root, _localFieldCount);
        this.icon = icon;
        this.include3 = include3;
        this.keterangan = keterangan;
        this.tarikSampel = tarikSampel;
    }

    public TarikSampelViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityTarikSampelBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTarikSampelBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tarik_sampel, root, attachToRoot, component);
    }

    public static ActivityTarikSampelBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTarikSampelBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_tarik_sampel, null, false, component);
    }

    public static ActivityTarikSampelBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTarikSampelBinding bind(View view, Object component) {
        return (ActivityTarikSampelBinding) bind(component, view, R.layout.activity_tarik_sampel);
    }
}
