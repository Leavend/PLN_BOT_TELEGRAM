package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class ActivityUploadProgressBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TabLayout slidingTabs;
    public final ViewPager viewpager;

    private ActivityUploadProgressBinding(ConstraintLayout rootView, TabLayout slidingTabs, ViewPager viewpager) {
        this.rootView = rootView;
        this.slidingTabs = slidingTabs;
        this.viewpager = viewpager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUploadProgressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUploadProgressBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_upload_progress, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUploadProgressBinding bind(View rootView) {
        int i = R.id.sliding_tabs;
        TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(rootView, i);
        if (tabLayout != null) {
            i = R.id.viewpager;
            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(rootView, i);
            if (viewPager != null) {
                return new ActivityUploadProgressBinding((ConstraintLayout) rootView, tabLayout, viewPager);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
