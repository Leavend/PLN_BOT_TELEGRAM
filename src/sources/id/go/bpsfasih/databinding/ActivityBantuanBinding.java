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
import id.go.bpsfasih.ui.sistem.SistemViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityBantuanBinding extends ViewDataBinding {
    public final TextView deskripsiTv;
    public final ImageView iconIv;
    public final ViewToolbarBinding include;

    @Bindable
    protected SistemViewModel mViewModel;
    public final Button mulaiPercakapanB;
    public final TextView namaTv;

    public abstract void setViewModel(SistemViewModel viewModel);

    protected ActivityBantuanBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView deskripsiTv, ImageView iconIv, ViewToolbarBinding include, Button mulaiPercakapanB, TextView namaTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deskripsiTv = deskripsiTv;
        this.iconIv = iconIv;
        this.include = include;
        this.mulaiPercakapanB = mulaiPercakapanB;
        this.namaTv = namaTv;
    }

    public SistemViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityBantuanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBantuanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBantuanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bantuan, root, attachToRoot, component);
    }

    public static ActivityBantuanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBantuanBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBantuanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bantuan, null, false, component);
    }

    public static ActivityBantuanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBantuanBinding bind(View view, Object component) {
        return (ActivityBantuanBinding) bind(component, view, R.layout.activity_bantuan);
    }
}
