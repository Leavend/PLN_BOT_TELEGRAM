package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.daftarwilayah.UpdateListingViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityDaftarWilayahBinding extends ViewDataBinding {
    public final ConstraintLayout emptyDataCl;
    public final TextView emptyDataTv;
    public final LinearLayout llShimmerContainer;

    @Bindable
    protected UpdateListingViewModel mViewModel;
    public final ConstraintLayout notEmptyDataCl;
    public final ProgressBar progressBar;
    public final LinearLayout searchCl;
    public final SearchView searchEt;
    public final ShimmerFrameLayout shimmerViewContainerBlokSensus;
    public final LinearLayout sortL;
    public final NestedScrollView swipeUpdateListing;
    public final Button syncEmptyDataB;
    public final Button syncNotEmptyDataB;
    public final RecyclerView updateListingRecycler;

    public abstract void setViewModel(UpdateListingViewModel viewModel);

    protected ActivityDaftarWilayahBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout emptyDataCl, TextView emptyDataTv, LinearLayout llShimmerContainer, ConstraintLayout notEmptyDataCl, ProgressBar progressBar, LinearLayout searchCl, SearchView searchEt, ShimmerFrameLayout shimmerViewContainerBlokSensus, LinearLayout sortL, NestedScrollView swipeUpdateListing, Button syncEmptyDataB, Button syncNotEmptyDataB, RecyclerView updateListingRecycler) {
        super(_bindingComponent, _root, _localFieldCount);
        this.emptyDataCl = emptyDataCl;
        this.emptyDataTv = emptyDataTv;
        this.llShimmerContainer = llShimmerContainer;
        this.notEmptyDataCl = notEmptyDataCl;
        this.progressBar = progressBar;
        this.searchCl = searchCl;
        this.searchEt = searchEt;
        this.shimmerViewContainerBlokSensus = shimmerViewContainerBlokSensus;
        this.sortL = sortL;
        this.swipeUpdateListing = swipeUpdateListing;
        this.syncEmptyDataB = syncEmptyDataB;
        this.syncNotEmptyDataB = syncNotEmptyDataB;
        this.updateListingRecycler = updateListingRecycler;
    }

    public UpdateListingViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityDaftarWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDaftarWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDaftarWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daftar_wilayah, root, attachToRoot, component);
    }

    public static ActivityDaftarWilayahBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDaftarWilayahBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDaftarWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daftar_wilayah, null, false, component);
    }

    public static ActivityDaftarWilayahBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDaftarWilayahBinding bind(View view, Object component) {
        return (ActivityDaftarWilayahBinding) bind(component, view, R.layout.activity_daftar_wilayah);
    }
}
