package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.periode.PeriodeViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityPeriodeBinding extends ViewDataBinding {
    public final TextView deskripsiTv;
    public final ConstraintLayout emptyDataCl;
    public final TextView emptyDataTv;
    public final ViewToolbarBinding include3;
    public final LinearLayout llShimmerContainer;

    @Bindable
    protected PeriodeViewModel mViewModel;
    public final ConstraintLayout notEmptyDataCl;
    public final RecyclerView periodeRecycler;
    public final ProgressBar progressBar;
    public final ShimmerFrameLayout shimmerViewContainerPeriode;
    public final Button syncEmptyDataB;
    public final Button syncNotEmptyDataB;

    public abstract void setViewModel(PeriodeViewModel viewModel);

    protected ActivityPeriodeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView deskripsiTv, ConstraintLayout emptyDataCl, TextView emptyDataTv, ViewToolbarBinding include3, LinearLayout llShimmerContainer, ConstraintLayout notEmptyDataCl, RecyclerView periodeRecycler, ProgressBar progressBar, ShimmerFrameLayout shimmerViewContainerPeriode, Button syncEmptyDataB, Button syncNotEmptyDataB) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deskripsiTv = deskripsiTv;
        this.emptyDataCl = emptyDataCl;
        this.emptyDataTv = emptyDataTv;
        this.include3 = include3;
        this.llShimmerContainer = llShimmerContainer;
        this.notEmptyDataCl = notEmptyDataCl;
        this.periodeRecycler = periodeRecycler;
        this.progressBar = progressBar;
        this.shimmerViewContainerPeriode = shimmerViewContainerPeriode;
        this.syncEmptyDataB = syncEmptyDataB;
        this.syncNotEmptyDataB = syncNotEmptyDataB;
    }

    public PeriodeViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_periode, root, attachToRoot, component);
    }

    public static ActivityPeriodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPeriodeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_periode, null, false, component);
    }

    public static ActivityPeriodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPeriodeBinding bind(View view, Object component) {
        return (ActivityPeriodeBinding) bind(component, view, R.layout.activity_periode);
    }
}
