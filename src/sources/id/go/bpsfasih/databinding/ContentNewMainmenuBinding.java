package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class ContentNewMainmenuBinding implements ViewBinding {
    public final ConstraintLayout container;
    public final FrameLayout fragmentContainer;
    public final BottomNavigationView navigation;
    private final ConstraintLayout rootView;

    private ContentNewMainmenuBinding(ConstraintLayout rootView, ConstraintLayout container, FrameLayout fragmentContainer, BottomNavigationView navigation) {
        this.rootView = rootView;
        this.container = container;
        this.fragmentContainer = fragmentContainer;
        this.navigation = navigation;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ContentNewMainmenuBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ContentNewMainmenuBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.content_new_mainmenu, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ContentNewMainmenuBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.fragment_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i);
        if (frameLayout != null) {
            i = R.id.navigation;
            BottomNavigationView bottomNavigationView = (BottomNavigationView) ViewBindings.findChildViewById(rootView, i);
            if (bottomNavigationView != null) {
                return new ContentNewMainmenuBinding(constraintLayout, constraintLayout, frameLayout, bottomNavigationView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
