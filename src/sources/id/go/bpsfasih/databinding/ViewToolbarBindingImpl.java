package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.survey.SurveyViewModel;

/* loaded from: classes2.dex */
public class ViewToolbarBindingImpl extends ViewToolbarBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.back_button, 1);
        sparseIntArray.put(R.id.title_toolbar, 2);
        sparseIntArray.put(R.id.menu, 3);
        sparseIntArray.put(R.id.modeProd_iv, 4);
        sparseIntArray.put(R.id.modeDev_tv, 5);
        sparseIntArray.put(R.id.menu_toolbar_iv, 6);
    }

    public ViewToolbarBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ViewToolbarBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (ConstraintLayout) bindings[3], (ImageView) bindings[6], (Button) bindings[5], (ImageView) bindings[4], (TextView) bindings[2], (ConstraintLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.toolbarContainer.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (1 != variableId) {
            return false;
        }
        setSecondToViewModel((SurveyViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ViewToolbarBinding
    public void setSecondToViewModel(SurveyViewModel SecondToViewModel) {
        this.mSecondToViewModel = SecondToViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
