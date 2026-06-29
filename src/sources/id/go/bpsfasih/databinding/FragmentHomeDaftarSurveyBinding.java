package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentHomeDaftarSurveyBinding extends ViewDataBinding {
    public final LinearLayout emptyDataLl;
    public final TextView emptyDataTv;
    public final TextView emptyPinDataTv;
    public final TextView lihatSemuaTv;
    public final RecyclerView listSurvei;

    @Bindable
    protected HomeFragmentViewModel mSecondViewModel;
    public final LinearLayout notEmptyDataLl;
    public final CardView rekapTahunanCard;
    public final Button syncEmptyDataB;
    public final Button syncSurveyB;
    public final LinearLayout yearlyRecap;

    public abstract void setSecondViewModel(HomeFragmentViewModel secondViewModel);

    protected FragmentHomeDaftarSurveyBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout emptyDataLl, TextView emptyDataTv, TextView emptyPinDataTv, TextView lihatSemuaTv, RecyclerView listSurvei, LinearLayout notEmptyDataLl, CardView rekapTahunanCard, Button syncEmptyDataB, Button syncSurveyB, LinearLayout yearlyRecap) {
        super(_bindingComponent, _root, _localFieldCount);
        this.emptyDataLl = emptyDataLl;
        this.emptyDataTv = emptyDataTv;
        this.emptyPinDataTv = emptyPinDataTv;
        this.lihatSemuaTv = lihatSemuaTv;
        this.listSurvei = listSurvei;
        this.notEmptyDataLl = notEmptyDataLl;
        this.rekapTahunanCard = rekapTahunanCard;
        this.syncEmptyDataB = syncEmptyDataB;
        this.syncSurveyB = syncSurveyB;
        this.yearlyRecap = yearlyRecap;
    }

    public HomeFragmentViewModel getSecondViewModel() {
        return this.mSecondViewModel;
    }

    public static FragmentHomeDaftarSurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeDaftarSurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHomeDaftarSurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_daftar_survey, root, attachToRoot, component);
    }

    public static FragmentHomeDaftarSurveyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeDaftarSurveyBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHomeDaftarSurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_home_daftar_survey, null, false, component);
    }

    public static FragmentHomeDaftarSurveyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHomeDaftarSurveyBinding bind(View view, Object component) {
        return (FragmentHomeDaftarSurveyBinding) bind(component, view, R.layout.fragment_home_daftar_survey);
    }
}
