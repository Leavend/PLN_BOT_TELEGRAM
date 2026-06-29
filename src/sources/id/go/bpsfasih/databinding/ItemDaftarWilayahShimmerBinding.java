package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class ItemDaftarWilayahShimmerBinding implements ViewBinding {
    public final View bannerSlider1;
    private final CardView rootView;

    private ItemDaftarWilayahShimmerBinding(CardView rootView, View bannerSlider1) {
        this.rootView = rootView;
        this.bannerSlider1 = bannerSlider1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemDaftarWilayahShimmerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemDaftarWilayahShimmerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_daftar_wilayah_shimmer, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDaftarWilayahShimmerBinding bind(View rootView) {
        int i = R.id.banner_slider1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, i);
        if (viewFindChildViewById != null) {
            return new ItemDaftarWilayahShimmerBinding((CardView) rootView, viewFindChildViewById);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
