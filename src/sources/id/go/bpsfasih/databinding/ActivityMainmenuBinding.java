package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.hompage.HomePageViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityMainmenuBinding extends ViewDataBinding {
    public final RelativeLayout container;
    public final LinearLayout llShimmerContainer;

    @Bindable
    protected HomePageViewModel mViewModel;
    public final ShimmerFrameLayout shimmerViewContainerHomepage;

    public abstract void setViewModel(HomePageViewModel viewModel);

    protected ActivityMainmenuBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout container, LinearLayout llShimmerContainer, ShimmerFrameLayout shimmerViewContainerHomepage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.container = container;
        this.llShimmerContainer = llShimmerContainer;
        this.shimmerViewContainerHomepage = shimmerViewContainerHomepage;
    }

    public HomePageViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainmenuBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainmenuBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainmenuBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_mainmenu, root, attachToRoot, component);
    }

    public static ActivityMainmenuBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainmenuBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainmenuBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_mainmenu, null, false, component);
    }

    public static ActivityMainmenuBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainmenuBinding bind(View view, Object component) {
        return (ActivityMainmenuBinding) bind(component, view, R.layout.activity_mainmenu);
    }
}
