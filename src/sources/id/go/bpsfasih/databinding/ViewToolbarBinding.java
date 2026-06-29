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
import id.go.bpsfasih.ui.survey.SurveyViewModel;

/* loaded from: classes2.dex */
public abstract class ViewToolbarBinding extends ViewDataBinding {
    public final ImageView backButton;

    @Bindable
    protected SurveyViewModel mSecondToViewModel;
    public final ConstraintLayout menu;
    public final ImageView menuToolbarIv;
    public final Button modeDevTv;
    public final ImageView modeProdIv;
    public final TextView titleToolbar;
    public final ConstraintLayout toolbarContainer;

    public abstract void setSecondToViewModel(SurveyViewModel secondToViewModel);

    protected ViewToolbarBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView backButton, ConstraintLayout menu, ImageView menuToolbarIv, Button modeDevTv, ImageView modeProdIv, TextView titleToolbar, ConstraintLayout toolbarContainer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.backButton = backButton;
        this.menu = menu;
        this.menuToolbarIv = menuToolbarIv;
        this.modeDevTv = modeDevTv;
        this.modeProdIv = modeProdIv;
        this.titleToolbar = titleToolbar;
        this.toolbarContainer = toolbarContainer;
    }

    public SurveyViewModel getSecondToViewModel() {
        return this.mSecondToViewModel;
    }

    public static ViewToolbarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewToolbarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewToolbarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_toolbar, root, attachToRoot, component);
    }

    public static ViewToolbarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewToolbarBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewToolbarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_toolbar, null, false, component);
    }

    public static ViewToolbarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewToolbarBinding bind(View view, Object component) {
        return (ViewToolbarBinding) bind(component, view, R.layout.view_toolbar);
    }
}
