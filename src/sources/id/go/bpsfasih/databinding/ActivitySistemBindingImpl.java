package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.sistem.SistemViewModel;

/* loaded from: classes2.dex */
public class ActivitySistemBindingImpl extends ActivitySistemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback20;
    private final View.OnClickListener mCallback21;
    private final View.OnClickListener mCallback22;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView5;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{6}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.versi_app, 7);
        sparseIntArray.put(R.id.versi_code, 8);
        sparseIntArray.put(R.id.memory_info, 9);
    }

    public ActivitySistemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivitySistemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ConstraintLayout) bindings[3], (ConstraintLayout) bindings[4], (ViewToolbarBinding) bindings[6], (TextView) bindings[9], (TextView) bindings[7], (TextView) bindings[1], (TextView) bindings[8], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.changeLogAplikasi.setTag(null);
        this.changeLogFormGear.setTag(null);
        setContainedBinding(this.include);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[5];
        this.mboundView5 = constraintLayout2;
        constraintLayout2.setTag(null);
        this.versiAppText.setTag(null);
        this.versiCodeText.setTag(null);
        setRootTag(root);
        this.mCallback21 = new OnClickListener(this, 2);
        this.mCallback22 = new OnClickListener(this, 3);
        this.mCallback20 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        setViewModel((SistemViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivitySistemBinding
    public void setViewModel(SistemViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
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
            return onChangeViewModelVersiApp((ObservableField) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeViewModelVersiCode((ObservableField) object, fieldId);
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

    private boolean onChangeViewModelVersiApp(ObservableField<String> ViewModelVersiApp, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelVersiCode(ObservableField<String> ViewModelVersiCode, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r13 = this;
            monitor-enter(r13)
            long r0 = r13.mDirtyFlags     // Catch: java.lang.Throwable -> L87
            r2 = 0
            r13.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L87
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L87
            id.go.bpsfasih.ui.sistem.SistemViewModel r4 = r13.mViewModel
            r5 = 30
            long r5 = r5 & r0
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            r6 = 28
            r8 = 26
            r10 = 0
            if (r5 == 0) goto L50
            long r11 = r0 & r8
            int r5 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r5 == 0) goto L31
            if (r4 == 0) goto L23
            androidx.databinding.ObservableField r5 = r4.getVersiApp()
            goto L24
        L23:
            r5 = r10
        L24:
            r11 = 1
            r13.updateRegistration(r11, r5)
            if (r5 == 0) goto L31
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L32
        L31:
            r5 = r10
        L32:
            long r11 = r0 & r6
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L4d
            if (r4 == 0) goto L3f
            androidx.databinding.ObservableField r4 = r4.getVersiCode()
            goto L40
        L3f:
            r4 = r10
        L40:
            r11 = 2
            r13.updateRegistration(r11, r4)
            if (r4 == 0) goto L4d
            java.lang.Object r4 = r4.get()
            r10 = r4
            java.lang.String r10 = (java.lang.String) r10
        L4d:
            r4 = r10
            r10 = r5
            goto L51
        L50:
            r4 = r10
        L51:
            r11 = 16
            long r11 = r11 & r0
            int r5 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r5 == 0) goto L6d
            androidx.constraintlayout.widget.ConstraintLayout r5 = r13.changeLogAplikasi
            android.view.View$OnClickListener r11 = r13.mCallback20
            r5.setOnClickListener(r11)
            androidx.constraintlayout.widget.ConstraintLayout r5 = r13.changeLogFormGear
            android.view.View$OnClickListener r11 = r13.mCallback21
            r5.setOnClickListener(r11)
            androidx.constraintlayout.widget.ConstraintLayout r5 = r13.mboundView5
            android.view.View$OnClickListener r11 = r13.mCallback22
            r5.setOnClickListener(r11)
        L6d:
            long r8 = r8 & r0
            int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r5 == 0) goto L77
            android.widget.TextView r5 = r13.versiAppText
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r5, r10)
        L77:
            long r0 = r0 & r6
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L81
            android.widget.TextView r0 = r13.versiCodeText
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L81:
            id.go.bpsfasih.databinding.ViewToolbarBinding r0 = r13.include
            executeBindingsOn(r0)
            return
        L87:
            r0 = move-exception
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L87
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.databinding.ActivitySistemBindingImpl.executeBindings():void");
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        if (sourceId == 1) {
            SistemViewModel sistemViewModel = this.mViewModel;
            if (sistemViewModel != null) {
                sistemViewModel.changeLogAplikasiClicked();
                return;
            }
            return;
        }
        if (sourceId == 2) {
            SistemViewModel sistemViewModel2 = this.mViewModel;
            if (sistemViewModel2 != null) {
                sistemViewModel2.changeLogFormGearClicked();
                return;
            }
            return;
        }
        if (sourceId != 3) {
            return;
        }
        SistemViewModel sistemViewModel3 = this.mViewModel;
        if (sistemViewModel3 != null) {
            sistemViewModel3.memoryInfoClicked();
        }
    }
}
