package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.login.LoginViewModel;

/* loaded from: classes2.dex */
public class ActivityLoginBindingImpl extends ActivityLoginBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_toolbar"}, new int[]{3}, new int[]{R.layout.view_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.header_l, 4);
        sparseIntArray.put(R.id.icon, 5);
        sparseIntArray.put(R.id.modeLogin_tv, 6);
        sparseIntArray.put(R.id.deskripsi_tv, 7);
        sparseIntArray.put(R.id.form_l, 8);
    }

    public ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityLoginBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (TextView) bindings[7], (ConstraintLayout) bindings[8], (ConstraintLayout) bindings[4], (ImageView) bindings[5], (ViewToolbarBinding) bindings[3], (Button) bindings[1], (Button) bindings[2], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.include);
        this.loginEksternalButton.setTag(null);
        this.loginInternalButton.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        setViewModel((LoginViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.ActivityLoginBinding
    public void setViewModel(LoginViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
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
        if (localFieldId != 1) {
            return false;
        }
        return onChangeViewModelBtnSelected((ObservableBoolean) object, fieldId);
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

    private boolean onChangeViewModelBtnSelected(ObservableBoolean ViewModelBtnSelected, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.mDirtyFlags     // Catch: java.lang.Throwable -> L37
            r2 = 0
            r7.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L37
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L37
            id.go.bpsfasih.ui.login.LoginViewModel r4 = r7.mViewModel
            r5 = 14
            long r0 = r0 & r5
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L24
            if (r4 == 0) goto L18
            androidx.databinding.ObservableBoolean r1 = r4.getBtnSelected()
            goto L19
        L18:
            r1 = 0
        L19:
            r2 = 1
            r7.updateRegistration(r2, r1)
            if (r1 == 0) goto L24
            boolean r1 = r1.get()
            goto L25
        L24:
            r1 = 0
        L25:
            if (r0 == 0) goto L31
            android.widget.Button r0 = r7.loginEksternalButton
            r0.setEnabled(r1)
            android.widget.Button r0 = r7.loginInternalButton
            r0.setEnabled(r1)
        L31:
            id.go.bpsfasih.databinding.ViewToolbarBinding r0 = r7.include
            executeBindingsOn(r0)
            return
        L37:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L37
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.databinding.ActivityLoginBindingImpl.executeBindings():void");
    }
}
