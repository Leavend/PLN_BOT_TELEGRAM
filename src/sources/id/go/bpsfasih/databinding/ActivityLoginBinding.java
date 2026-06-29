package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.login.LoginViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityLoginBinding extends ViewDataBinding {
    public final TextView deskripsiTv;
    public final ConstraintLayout formL;
    public final ConstraintLayout headerL;
    public final ImageView icon;
    public final ViewToolbarBinding include;
    public final Button loginEksternalButton;
    public final Button loginInternalButton;

    @Bindable
    protected LoginViewModel mViewModel;
    public final TextView modeLoginTv;

    public abstract void setViewModel(LoginViewModel viewModel);

    protected ActivityLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView deskripsiTv, ConstraintLayout formL, ConstraintLayout headerL, ImageView icon, ViewToolbarBinding include, Button loginEksternalButton, Button loginInternalButton, TextView modeLoginTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deskripsiTv = deskripsiTv;
        this.formL = formL;
        this.headerL = headerL;
        this.icon = icon;
        this.include = include;
        this.loginEksternalButton = loginEksternalButton;
        this.loginInternalButton = loginInternalButton;
        this.modeLoginTv = modeLoginTv;
    }

    public LoginViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, root, attachToRoot, component);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_login, null, false, component);
    }

    public static ActivityLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding bind(View view, Object component) {
        return (ActivityLoginBinding) bind(component, view, R.layout.activity_login);
    }
}
