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
import id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragmentViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentPengaturanBinding extends ViewDataBinding {
    public final ConstraintLayout cv1;
    public final ConstraintLayout cv2;
    public final ConstraintLayout cv3;
    public final ConstraintLayout cv6;
    public final ConstraintLayout cv7;
    public final ConstraintLayout cv8;
    public final TextView expTv;
    public final ConstraintLayout headerL;
    public final ImageView ic2;
    public final ImageView ic3;
    public final ImageView ic6;
    public final ImageView ic7;
    public final ImageView ic8;
    public final TextView icPersonalData;

    @Bindable
    protected PengaturanFragmentViewModel mViewModel;
    public final Button modeDevTv;
    public final ImageView modeProdIv;
    public final TextView tvEmail;
    public final TextView tvPengaturan;
    public final TextView usernameTv;
    public final View v;

    public abstract void setViewModel(PengaturanFragmentViewModel viewModel);

    protected FragmentPengaturanBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout cv1, ConstraintLayout cv2, ConstraintLayout cv3, ConstraintLayout cv6, ConstraintLayout cv7, ConstraintLayout cv8, TextView expTv, ConstraintLayout headerL, ImageView ic2, ImageView ic3, ImageView ic6, ImageView ic7, ImageView ic8, TextView icPersonalData, Button modeDevTv, ImageView modeProdIv, TextView tvEmail, TextView tvPengaturan, TextView usernameTv, View v) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cv1 = cv1;
        this.cv2 = cv2;
        this.cv3 = cv3;
        this.cv6 = cv6;
        this.cv7 = cv7;
        this.cv8 = cv8;
        this.expTv = expTv;
        this.headerL = headerL;
        this.ic2 = ic2;
        this.ic3 = ic3;
        this.ic6 = ic6;
        this.ic7 = ic7;
        this.ic8 = ic8;
        this.icPersonalData = icPersonalData;
        this.modeDevTv = modeDevTv;
        this.modeProdIv = modeProdIv;
        this.tvEmail = tvEmail;
        this.tvPengaturan = tvPengaturan;
        this.usernameTv = usernameTv;
        this.v = v;
    }

    public PengaturanFragmentViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentPengaturanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPengaturanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPengaturanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_pengaturan, root, attachToRoot, component);
    }

    public static FragmentPengaturanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPengaturanBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPengaturanBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_pengaturan, null, false, component);
    }

    public static FragmentPengaturanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPengaturanBinding bind(View view, Object component) {
        return (FragmentPengaturanBinding) bind(component, view, R.layout.fragment_pengaturan);
    }
}
