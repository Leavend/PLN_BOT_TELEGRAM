package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel;

/* loaded from: classes2.dex */
public class ActivitySyncAnswerPartialBindingImpl extends ActivitySyncAnswerPartialBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback13;
    private final View.OnClickListener mCallback14;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final NestedScrollView mboundView4;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{5}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon, 6);
        sparseIntArray.put(R.id.judul, 7);
        sparseIntArray.put(R.id.keterangan, 8);
        sparseIntArray.put(R.id.konten_rv, 9);
    }

    public ActivitySyncAnswerPartialBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivitySyncAnswerPartialBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (ImageView) bindings[6], (ViewToolbarBinding) bindings[5], (TextView) bindings[7], (TextView) bindings[8], (RecyclerView) bindings[9], (ConstraintLayout) bindings[1], (Button) bindings[3], (Button) bindings[2]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.include);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[4];
        this.mboundView4 = nestedScrollView;
        nestedScrollView.setTag(null);
        this.mulaiCl.setTag(null);
        this.tutup.setTag(null);
        this.unduh.setTag(null);
        setRootTag(root);
        this.mCallback13 = new OnClickListener(this, 1);
        this.mCallback14 = new OnClickListener(this, 2);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
        }
        this.include.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.include.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (3 != variableId) {
            return false;
        }
        setViewModel((SyncAnswerPartialViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivitySyncAnswerPartialBinding
    public void setViewModel(SyncAnswerPartialViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.include.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeInclude((ViewToolbarBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeViewModelVisibilityLayoutMulai((ObservableField) object, fieldId);
        }
        if (localFieldId == 2) {
            return onChangeViewModelVisibilityLayoutSelesai((ObservableField) object, fieldId);
        }
        if (localFieldId == 3) {
            return onChangeViewModelVisibilityButtonUnduh((ObservableField) object, fieldId);
        }
        if (localFieldId != 4) {
            return false;
        }
        return onChangeViewModelVisibilityButtonTutup((ObservableField) object, fieldId);
    }

    private boolean onChangeInclude(ViewToolbarBinding Include, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelVisibilityLayoutMulai(ObservableField<Integer> ViewModelVisibilityLayoutMulai, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelVisibilityLayoutSelesai(ObservableField<Integer> ViewModelVisibilityLayoutSelesai, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelVisibilityButtonUnduh(ObservableField<Integer> ViewModelVisibilityButtonUnduh, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelVisibilityButtonTutup(ObservableField<Integer> ViewModelVisibilityButtonTutup, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int iSafeUnbox;
        int iSafeUnbox2;
        int iSafeUnbox3;
        int i;
        int iSafeUnbox4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        SyncAnswerPartialViewModel syncAnswerPartialViewModel = this.mViewModel;
        if ((126 & j) != 0) {
            if ((j & 98) != 0) {
                ObservableField<Integer> visibilityLayoutMulai = syncAnswerPartialViewModel != null ? syncAnswerPartialViewModel.getVisibilityLayoutMulai() : null;
                updateRegistration(1, visibilityLayoutMulai);
                iSafeUnbox2 = ViewDataBinding.safeUnbox(visibilityLayoutMulai != null ? visibilityLayoutMulai.get() : null);
            } else {
                iSafeUnbox2 = 0;
            }
            if ((j & 100) != 0) {
                ObservableField<Integer> visibilityLayoutSelesai = syncAnswerPartialViewModel != null ? syncAnswerPartialViewModel.getVisibilityLayoutSelesai() : null;
                updateRegistration(2, visibilityLayoutSelesai);
                iSafeUnbox4 = ViewDataBinding.safeUnbox(visibilityLayoutSelesai != null ? visibilityLayoutSelesai.get() : null);
            } else {
                iSafeUnbox4 = 0;
            }
            if ((j & 104) != 0) {
                ObservableField<Integer> visibilityButtonUnduh = syncAnswerPartialViewModel != null ? syncAnswerPartialViewModel.getVisibilityButtonUnduh() : null;
                updateRegistration(3, visibilityButtonUnduh);
                iSafeUnbox3 = ViewDataBinding.safeUnbox(visibilityButtonUnduh != null ? visibilityButtonUnduh.get() : null);
            } else {
                iSafeUnbox3 = 0;
            }
            if ((j & 112) != 0) {
                ObservableField<Integer> visibilityButtonTutup = syncAnswerPartialViewModel != null ? syncAnswerPartialViewModel.getVisibilityButtonTutup() : null;
                updateRegistration(4, visibilityButtonTutup);
                iSafeUnbox = ViewDataBinding.safeUnbox(visibilityButtonTutup != null ? visibilityButtonTutup.get() : null);
                i = iSafeUnbox4;
            } else {
                i = iSafeUnbox4;
                iSafeUnbox = 0;
            }
        } else {
            iSafeUnbox = 0;
            iSafeUnbox2 = 0;
            iSafeUnbox3 = 0;
            i = 0;
        }
        if ((100 & j) != 0) {
            this.mboundView4.setVisibility(i);
        }
        if ((98 & j) != 0) {
            this.mulaiCl.setVisibility(iSafeUnbox2);
        }
        if ((64 & j) != 0) {
            this.tutup.setOnClickListener(this.mCallback14);
            this.unduh.setOnClickListener(this.mCallback13);
        }
        if ((j & 112) != 0) {
            this.tutup.setVisibility(iSafeUnbox);
        }
        if ((j & 104) != 0) {
            this.unduh.setVisibility(iSafeUnbox3);
        }
        executeBindingsOn(this.include);
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        if (sourceId == 1) {
            SyncAnswerPartialViewModel syncAnswerPartialViewModel = this.mViewModel;
            if (syncAnswerPartialViewModel != null) {
                syncAnswerPartialViewModel.unduhMulai();
                return;
            }
            return;
        }
        if (sourceId != 2) {
            return;
        }
        SyncAnswerPartialViewModel syncAnswerPartialViewModel2 = this.mViewModel;
        if (syncAnswerPartialViewModel2 != null) {
            syncAnswerPartialViewModel2.finish();
        }
    }
}
