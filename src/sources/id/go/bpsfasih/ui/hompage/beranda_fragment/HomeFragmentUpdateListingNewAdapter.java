package id.go.bpsfasih.ui.hompage.beranda_fragment;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.databinding.ItemSurveyBinding;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentUpdateListingNewAdapter;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeFragmentUpdateListingNewAdapter.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001&B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u001c\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00060\u0002R\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001aH\u0016J\u001b\u0010\u0015\u001a\u00020\u001d2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120$H\u0000¢\u0006\u0002\b%R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentUpdateListingNewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentUpdateListingNewAdapter$HomeFragmentUpdateListingNewViewHolder;", "context", "Landroid/content/Context;", "viewModel", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentViewModel;", "fragment", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment;", "(Landroid/content/Context;Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentViewModel;Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getFragment", "()Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment;", "surveys", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getSurveys", "()Ljava/util/List;", "setSurveys", "(Ljava/util/List;)V", "getViewModel", "()Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentViewModel;", "width", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "setSurveys$app_release", "HomeFragmentUpdateListingNewViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class HomeFragmentUpdateListingNewAdapter extends RecyclerView.Adapter<HomeFragmentUpdateListingNewViewHolder> {
    public static final int $stable = 8;
    private Context context;
    private final HomeFragment fragment;
    private List<SurveyPojo> surveys;
    private final HomeFragmentViewModel viewModel;
    private final int width;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final HomeFragmentViewModel getViewModel() {
        return this.viewModel;
    }

    public final HomeFragment getFragment() {
        return this.fragment;
    }

    public HomeFragmentUpdateListingNewAdapter(Context context, HomeFragmentViewModel viewModel, HomeFragment fragment) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.context = context;
        this.viewModel = viewModel;
        this.fragment = fragment;
        this.surveys = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context2 = this.context;
        Object systemService = context2 != null ? context2.getSystemService("window") : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        this.width = (int) (displayMetrics.widthPixels * 0.6d);
    }

    public final List<SurveyPojo> getSurveys() {
        return this.surveys;
    }

    public final void setSurveys(List<SurveyPojo> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.surveys = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public HomeFragmentUpdateListingNewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSurveyBinding binding = (ItemSurveyBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_survey, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new HomeFragmentUpdateListingNewViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(HomeFragmentUpdateListingNewViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.setData(this.surveys.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.surveys.size() > 5) {
            return 5;
        }
        return this.surveys.size();
    }

    /* compiled from: HomeFragmentUpdateListingNewAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentUpdateListingNewAdapter$HomeFragmentUpdateListingNewViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemSurveyBinding;", "(Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentUpdateListingNewAdapter;Lid/go/bpsfasih/databinding/ItemSurveyBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemSurveyBinding;", "setData", "", "survey", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class HomeFragmentUpdateListingNewViewHolder extends RecyclerView.ViewHolder {
        private final ItemSurveyBinding binding;
        final /* synthetic */ HomeFragmentUpdateListingNewAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HomeFragmentUpdateListingNewViewHolder(HomeFragmentUpdateListingNewAdapter homeFragmentUpdateListingNewAdapter, ItemSurveyBinding binding) {
            super(binding.root);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = homeFragmentUpdateListingNewAdapter;
            this.binding = binding;
        }

        public final ItemSurveyBinding getBinding() {
            return this.binding;
        }

        public final void setData(final SurveyPojo survey) {
            SurveyEntity survey2;
            SurveyEntity survey3;
            ConstraintLayout constraintLayout = this.binding.root;
            final HomeFragmentUpdateListingNewAdapter homeFragmentUpdateListingNewAdapter = this.this$0;
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentUpdateListingNewAdapter$HomeFragmentUpdateListingNewViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragmentUpdateListingNewAdapter.HomeFragmentUpdateListingNewViewHolder.setData$lambda$0(homeFragmentUpdateListingNewAdapter, survey, view);
                }
            });
            Boolean boolValueOf = null;
            this.binding.tvSurveyName.setText((survey == null || (survey3 = survey.getSurvey()) == null) ? null : survey3.getName());
            TextView textView = this.binding.tvPeriodeCount;
            if (survey != null && (survey2 = survey.getSurvey()) != null) {
                boolValueOf = Boolean.valueOf(survey2.getUpdateListingType());
            }
            Intrinsics.checkNotNull(boolValueOf);
            textView.setText(boolValueOf.booleanValue() ? "Survey Updating/Listing" : "Survey Sample");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setData$lambda$0(HomeFragmentUpdateListingNewAdapter this$0, SurveyPojo surveyPojo, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getViewModel().checkSurvey(surveyPojo, this$0.getFragment());
        }
    }

    public final void setSurveys$app_release(List<SurveyPojo> surveys) {
        Intrinsics.checkNotNullParameter(surveys, "surveys");
        this.surveys = CollectionsKt.toMutableList((Collection) surveys);
        notifyDataSetChanged();
    }
}
