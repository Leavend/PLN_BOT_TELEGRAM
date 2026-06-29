package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class FragmentHomeShimmerBinding implements ViewBinding {
    public final View bannerSlider1;
    public final LinearLayout llArea;
    public final LinearLayout llMemoryInfo;
    public final LinearLayout llPencacahan;
    public final LinearLayout llUpdateListing;
    private final ScrollView rootView;
    public final ScrollView scrollView2;

    private FragmentHomeShimmerBinding(ScrollView rootView, View bannerSlider1, LinearLayout llArea, LinearLayout llMemoryInfo, LinearLayout llPencacahan, LinearLayout llUpdateListing, ScrollView scrollView2) {
        this.rootView = rootView;
        this.bannerSlider1 = bannerSlider1;
        this.llArea = llArea;
        this.llMemoryInfo = llMemoryInfo;
        this.llPencacahan = llPencacahan;
        this.llUpdateListing = llUpdateListing;
        this.scrollView2 = scrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentHomeShimmerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentHomeShimmerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_home_shimmer, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHomeShimmerBinding bind(View rootView) {
        int i = R.id.banner_slider1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
        if (viewFindChildViewById != null) {
            i = R.id.ll_area;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
            if (linearLayout != null) {
                i = R.id.ll_memory_info;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                if (linearLayout2 != null) {
                    i = R.id.ll_pencacahan;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                    if (linearLayout3 != null) {
                        i = R.id.ll_update_listing;
                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                        if (linearLayout4 != null) {
                            ScrollView scrollView = (ScrollView) rootView;
                            return new FragmentHomeShimmerBinding(scrollView, viewFindChildViewById, linearLayout, linearLayout2, linearLayout3, linearLayout4, scrollView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
