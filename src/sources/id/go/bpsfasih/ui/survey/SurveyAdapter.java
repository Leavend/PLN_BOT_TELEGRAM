package id.go.bpsfasih.ui.survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.data.local.repository.SurveyRepositoryNew;
import id.go.bpsfasih.databinding.ItemSurveyBinding;
import id.go.bpsfasih.utils.SingleLiveEvent;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyAdapter.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00016B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020%H\u0016J\r\u0010)\u001a\u00020*H\u0000¢\u0006\u0002\b+J\u0018\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010(\u001a\u00020%H\u0016J\u0018\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020%H\u0016J\u001b\u00102\u001a\u00020*2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001b04H\u0000¢\u0006\u0002\b5R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00067"}, d2 = {"Lid/go/bpsfasih/ui/survey/SurveyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/survey/SurveyAdapter$SurveyViewHolder;", "context", "Landroid/content/Context;", "viewModel", "Lid/go/bpsfasih/ui/survey/SurveyViewModel;", "(Landroid/content/Context;Lid/go/bpsfasih/ui/survey/SurveyViewModel;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "isChanged", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setChanged", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "isLongClick", "()Z", "setLongClick", "(Z)V", RemoteConfigConstants.ResponseFieldKey.STATE, "getState", "setState", "survey", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getSurvey", "()Ljava/util/List;", "setSurvey", "(Ljava/util/List;)V", "getViewModel", "()Lid/go/bpsfasih/ui/survey/SurveyViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/survey/SurveyViewModel;)V", "getItemCount", "", "getItemId", "", "position", "hideCheckbox", "", "hideCheckbox$app_release", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSurveys", "surveys", "", "setSurveys$app_release", "SurveyViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyAdapter extends RecyclerView.Adapter<SurveyViewHolder> {
    public static final int $stable = 8;
    private Context context;
    private SingleLiveEvent<Boolean> isChanged;
    private boolean isLongClick;
    private boolean state;
    private List<SurveyPojo> survey;
    private SurveyViewModel viewModel;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final SurveyViewModel getViewModel() {
        return this.viewModel;
    }

    public final void setViewModel(SurveyViewModel surveyViewModel) {
        Intrinsics.checkNotNullParameter(surveyViewModel, "<set-?>");
        this.viewModel = surveyViewModel;
    }

    public SurveyAdapter(Context context, SurveyViewModel viewModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.context = context;
        this.viewModel = viewModel;
        this.survey = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());
        SingleLiveEvent<Boolean> singleLiveEvent = new SingleLiveEvent<>();
        this.isChanged = singleLiveEvent;
        singleLiveEvent.postValue(false);
        setHasStableIds(true);
    }

    public final List<SurveyPojo> getSurvey() {
        return this.survey;
    }

    public final void setSurvey(List<SurveyPojo> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.survey = list;
    }

    public final boolean getState() {
        return this.state;
    }

    public final void setState(boolean z) {
        this.state = z;
    }

    /* renamed from: isLongClick, reason: from getter */
    public final boolean getIsLongClick() {
        return this.isLongClick;
    }

    public final void setLongClick(boolean z) {
        this.isLongClick = z;
    }

    public final SingleLiveEvent<Boolean> isChanged() {
        return this.isChanged;
    }

    public final void setChanged(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.isChanged = singleLiveEvent;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSurveyBinding binding = (ItemSurveyBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_survey, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new SurveyViewHolder(binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.survey.size();
    }

    /* compiled from: SurveyAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/survey/SurveyAdapter$SurveyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemSurveyBinding;", "(Lid/go/bpsfasih/databinding/ItemSurveyBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemSurveyBinding;", "bind", "", "item", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SurveyViewHolder extends RecyclerView.ViewHolder {
        public static final int $stable = 8;
        private final ItemSurveyBinding binding;

        public final ItemSurveyBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SurveyViewHolder(ItemSurveyBinding binding) {
            super(binding.root);
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(SurveyPojo item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.tvSurveyName.setText(item.getSurvey().getName());
            TextView textView = this.binding.tvPeriodeCount;
            SurveyEntity survey = item.getSurvey();
            Boolean boolValueOf = survey != null ? Boolean.valueOf(survey.getUpdateListingType()) : null;
            Intrinsics.checkNotNull(boolValueOf);
            textView.setText(boolValueOf.booleanValue() ? "Survey Updating/Listing" : "Survey Sample");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SurveyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final SurveyPojo surveyPojo = this.survey.get(position);
        holder.bind(surveyPojo);
        holder.getBinding().root.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.survey.SurveyAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SurveyAdapter.onBindViewHolder$lambda$0(this.f$0, surveyPojo, view);
            }
        });
        holder.getBinding().pinSurveyIv.setVisibility(0);
        holder.getBinding().pinSurveyIv.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.survey.SurveyAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SurveyAdapter.onBindViewHolder$lambda$1(surveyPojo, view);
            }
        });
        holder.getBinding().executePendingBindings();
        if (surveyPojo.getSurvey().is_pin()) {
            holder.getBinding().pinSurveyIv.setColorFilter(ContextCompat.getColor(this.context, R.color.primary30));
        } else {
            holder.getBinding().pinSurveyIv.setColorFilter(ContextCompat.getColor(this.context, R.color.neural30));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(SurveyAdapter this$0, SurveyPojo current, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        this$0.viewModel.checkPeriode(current);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(SurveyPojo current, View view) {
        Intrinsics.checkNotNullParameter(current, "$current");
        new SurveyRepositoryNew(FasihApp.INSTANCE.getSurveyDao()).updateStatusPin(current.getSurvey().getIdPrimary());
    }

    public final void hideCheckbox$app_release() {
        if (!this.state || this.isLongClick) {
            return;
        }
        this.state = false;
        notifyDataSetChanged();
    }

    public final void setSurveys$app_release(List<SurveyPojo> surveys) {
        Intrinsics.checkNotNullParameter(surveys, "surveys");
        this.survey = CollectionsKt.toMutableList((Collection) surveys);
        notifyDataSetChanged();
    }
}
