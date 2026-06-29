package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.survey.SurveyViewModel;

/* loaded from: classes2.dex */
public class ActivitySurveyBindingImpl extends ActivitySurveyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ViewToolbarBinding mboundView0;
    private final ItemSurveyShimmerBinding mboundView1;
    private final ItemSurveyShimmerBinding mboundView11;
    private final ItemSurveyShimmerBinding mboundView12;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(14);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{5}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.search_card, 6);
        sparseIntArray.put(R.id.et_search, 7);
        sparseIntArray.put(R.id.iv_clear_search, 8);
        sparseIntArray.put(R.id.shimmer_view_container_survey, 9);
        sparseIntArray.put(R.id.surveyRecycler, 10);
        sparseIntArray.put(R.id.pBar, 11);
        sparseIntArray.put(R.id.empty_state_layout, 12);
        sparseIntArray.put(R.id.tv_empty_message, 13);
    }

    public ActivitySurveyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivitySurveyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[0], (LinearLayout) bindings[12], (EditText) bindings[7], (ImageView) bindings[8], (LinearLayout) bindings[1], (ProgressBar) bindings[11], (CardView) bindings[6], (ShimmerFrameLayout) bindings[9], (RecyclerView) bindings[10], (TextView) bindings[13]);
        this.mDirtyFlags = -1L;
        this.drawerLayout.setTag(null);
        this.llShimmerContainer.setTag(null);
        ViewToolbarBinding viewToolbarBinding = (ViewToolbarBinding) bindings[5];
        this.mboundView0 = viewToolbarBinding;
        setContainedBinding(viewToolbarBinding);
        Object obj = bindings[2];
        this.mboundView1 = obj != null ? ItemSurveyShimmerBinding.bind((View) obj) : null;
        Object obj2 = bindings[3];
        this.mboundView11 = obj2 != null ? ItemSurveyShimmerBinding.bind((View) obj2) : null;
        Object obj3 = bindings[4];
        this.mboundView12 = obj3 != null ? ItemSurveyShimmerBinding.bind((View) obj3) : null;
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView0.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((SurveyViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivitySurveyBinding
    public void setViewModel(SurveyViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        executeBindingsOn(this.mboundView0);
    }
}
