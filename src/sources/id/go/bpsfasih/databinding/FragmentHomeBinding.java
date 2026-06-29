package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentHomeBinding extends ViewDataBinding {
    public final TextView assignmentTv;
    public final LinearLayout dashboardL;
    public final TextView desksripsiTv;
    public final TextView haloUserTv;
    public final ImageView icAssignment;
    public final ImageView icPeriode;
    public final ImageView icSurvei;
    public final TextView jmlAssignmentTv;
    public final TextView jmlPeriodeTv;
    public final TextView jmlSurveiTv;
    public final ConstraintLayout linearLayout3;
    public final LinearLayout linearLayout4;

    @Bindable
    protected HomeFragmentViewModel mViewModel;
    public final Button modeDevTv;
    public final ImageView modeProdIv;
    public final TextView periodeTv;
    public final FragmentHomeDaftarSurveyBinding secondary;
    public final TextView surveiTv;

    public abstract void setViewModel(HomeFragmentViewModel viewModel);

    protected FragmentHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView assignmentTv, LinearLayout dashboardL, TextView desksripsiTv, TextView haloUserTv, ImageView icAssignment, ImageView icPeriode, ImageView icSurvei, TextView jmlAssignmentTv, TextView jmlPeriodeTv, TextView jmlSurveiTv, ConstraintLayout linearLayout3, LinearLayout linearLayout4, Button modeDevTv, ImageView modeProdIv, TextView periodeTv, FragmentHomeDaftarSurveyBinding secondary, TextView surveiTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.assignmentTv = assignmentTv;
        this.dashboardL = dashboardL;
        this.desksripsiTv = desksripsiTv;
        this.haloUserTv = haloUserTv;
        this.icAssignment = icAssignment;
        this.icPeriode = icPeriode;
        this.icSurvei = icSurvei;
        this.jmlAssignmentTv = jmlAssignmentTv;
        this.jmlPeriodeTv = jmlPeriodeTv;
        this.jmlSurveiTv = jmlSurveiTv;
        this.linearLayout3 = linearLayout3;
        this.linearLayout4 = linearLayout4;
        this.modeDevTv = modeDevTv;
        this.modeProdIv = modeProdIv;
        this.periodeTv = periodeTv;
        this.secondary = secondary;
        this.surveiTv = surveiTv;
    }

    public HomeFragmentViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, root, attachToRoot, component);
    }

    public static FragmentHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home, null, false, component);
    }

    public static FragmentHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeBinding bind(View view, Object component) {
        return (FragmentHomeBinding) bind(component, view, R.layout.fragment_home);
    }
}
