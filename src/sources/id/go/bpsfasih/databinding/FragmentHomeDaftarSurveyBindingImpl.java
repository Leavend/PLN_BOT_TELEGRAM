package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel;

/* loaded from: classes2.dex */
public class FragmentHomeDaftarSurveyBindingImpl extends FragmentHomeDaftarSurveyBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback1;
    private final View.OnClickListener mCallback2;
    private final View.OnClickListener mCallback3;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rekapTahunan_card, 4);
        sparseIntArray.put(R.id.yearly_recap, 5);
        sparseIntArray.put(R.id.notEmptyData_ll, 6);
        sparseIntArray.put(R.id.emptyPinData_tv, 7);
        sparseIntArray.put(R.id.list_survei, 8);
        sparseIntArray.put(R.id.emptyData_ll, 9);
        sparseIntArray.put(R.id.emptyData_tv, 10);
    }

    public FragmentHomeDaftarSurveyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentHomeDaftarSurveyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[9], (TextView) bindings[10], (TextView) bindings[7], (TextView) bindings[2], (RecyclerView) bindings[8], (LinearLayout) bindings[6], (CardView) bindings[4], (Button) bindings[3], (Button) bindings[1], (LinearLayout) bindings[5]);
        this.mDirtyFlags = -1L;
        this.lihatSemuaTv.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.syncEmptyDataB.setTag(null);
        this.syncSurveyB.setTag(null);
        setRootTag(root);
        this.mCallback3 = new OnClickListener(this, 3);
        this.mCallback1 = new OnClickListener(this, 1);
        this.mCallback2 = new OnClickListener(this, 2);
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
        if (2 != variableId) {
            return false;
        }
        setSecondViewModel((HomeFragmentViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.FragmentHomeDaftarSurveyBinding
    public void setSecondViewModel(HomeFragmentViewModel SecondViewModel) {
        this.mSecondViewModel = SecondViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        HomeFragmentViewModel homeFragmentViewModel = this.mSecondViewModel;
        if ((j & 2) != 0) {
            this.lihatSemuaTv.setOnClickListener(this.mCallback2);
            this.syncEmptyDataB.setOnClickListener(this.mCallback3);
            this.syncSurveyB.setOnClickListener(this.mCallback1);
        }
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        if (sourceId == 1) {
            HomeFragmentViewModel homeFragmentViewModel = this.mSecondViewModel;
            if (homeFragmentViewModel != null) {
                homeFragmentViewModel.syncSurvei();
                return;
            }
            return;
        }
        if (sourceId == 2) {
            HomeFragmentViewModel homeFragmentViewModel2 = this.mSecondViewModel;
            if (homeFragmentViewModel2 != null) {
                homeFragmentViewModel2.viewAll();
                return;
            }
            return;
        }
        if (sourceId != 3) {
            return;
        }
        HomeFragmentViewModel homeFragmentViewModel3 = this.mSecondViewModel;
        if (homeFragmentViewModel3 != null) {
            homeFragmentViewModel3.syncSurvei();
        }
    }
}
