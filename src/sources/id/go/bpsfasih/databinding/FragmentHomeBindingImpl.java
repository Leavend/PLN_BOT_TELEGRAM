package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel;

/* loaded from: classes2.dex */
public class FragmentHomeBindingImpl extends FragmentHomeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final ConstraintLayout mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(19);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"fragment_home_daftar_survey"}, new int[]{2}, new int[]{R.layout.fragment_home_daftar_survey});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.linearLayout3, 3);
        sparseIntArray.put(R.id.linearLayout4, 4);
        sparseIntArray.put(R.id.haloUser_tv, 5);
        sparseIntArray.put(R.id.modeProd_iv, 6);
        sparseIntArray.put(R.id.modeDev_tv, 7);
        sparseIntArray.put(R.id.desksripsi_tv, 8);
        sparseIntArray.put(R.id.dashboard_l, 9);
        sparseIntArray.put(R.id.ic_survei, 10);
        sparseIntArray.put(R.id.jml_survei_tv, 11);
        sparseIntArray.put(R.id.survei_tv, 12);
        sparseIntArray.put(R.id.ic_periode, 13);
        sparseIntArray.put(R.id.jml_periode_tv, 14);
        sparseIntArray.put(R.id.periode_tv, 15);
        sparseIntArray.put(R.id.ic_assignment, 16);
        sparseIntArray.put(R.id.jml_assignment_tv, 17);
        sparseIntArray.put(R.id.assignment_tv, 18);
    }

    public FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private FragmentHomeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[18], (LinearLayout) bindings[9], (TextView) bindings[8], (TextView) bindings[5], (ImageView) bindings[16], (ImageView) bindings[13], (ImageView) bindings[10], (TextView) bindings[17], (TextView) bindings[14], (TextView) bindings[11], (ConstraintLayout) bindings[3], (LinearLayout) bindings[4], (Button) bindings[7], (ImageView) bindings[6], (TextView) bindings[15], (FragmentHomeDaftarSurveyBinding) bindings[2], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        setContainedBinding(this.secondary);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.secondary.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.secondary.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((HomeFragmentViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.FragmentHomeBinding
    public void setViewModel(HomeFragmentViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.secondary.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeSecondary((FragmentHomeDaftarSurveyBinding) object, fieldId);
    }

    private boolean onChangeSecondary(FragmentHomeDaftarSurveyBinding Secondary, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        HomeFragmentViewModel homeFragmentViewModel = this.mViewModel;
        if ((j & 6) != 0) {
            this.secondary.setSecondViewModel(homeFragmentViewModel);
        }
        executeBindingsOn(this.secondary);
    }
}
