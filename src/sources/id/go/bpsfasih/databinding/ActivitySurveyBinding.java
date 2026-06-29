package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.survey.SurveyViewModel;

/* loaded from: classes2.dex */
public abstract class ActivitySurveyBinding extends ViewDataBinding {
    public final LinearLayout drawerLayout;
    public final LinearLayout emptyStateLayout;
    public final EditText etSearch;
    public final ImageView ivClearSearch;
    public final LinearLayout llShimmerContainer;

    @Bindable
    protected SurveyViewModel mViewModel;
    public final ProgressBar pBar;
    public final CardView searchCard;
    public final ShimmerFrameLayout shimmerViewContainerSurvey;
    public final RecyclerView surveyRecycler;
    public final TextView tvEmptyMessage;

    public abstract void setViewModel(SurveyViewModel viewModel);

    protected ActivitySurveyBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout drawerLayout, LinearLayout emptyStateLayout, EditText etSearch, ImageView ivClearSearch, LinearLayout llShimmerContainer, ProgressBar pBar, CardView searchCard, ShimmerFrameLayout shimmerViewContainerSurvey, RecyclerView surveyRecycler, TextView tvEmptyMessage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.drawerLayout = drawerLayout;
        this.emptyStateLayout = emptyStateLayout;
        this.etSearch = etSearch;
        this.ivClearSearch = ivClearSearch;
        this.llShimmerContainer = llShimmerContainer;
        this.pBar = pBar;
        this.searchCard = searchCard;
        this.shimmerViewContainerSurvey = shimmerViewContainerSurvey;
        this.surveyRecycler = surveyRecycler;
        this.tvEmptyMessage = tvEmptyMessage;
    }

    public SurveyViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivitySurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_survey, root, attachToRoot, component);
    }

    public static ActivitySurveyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySurveyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_survey, null, false, component);
    }

    public static ActivitySurveyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySurveyBinding bind(View view, Object component) {
        return (ActivitySurveyBinding) bind(component, view, R.layout.activity_survey);
    }
}
