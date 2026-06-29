package id.go.bpsfasih.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import id.go.bpsfasih.R;
import id.go.bpsfasih.generated.callback.OnClickListener;
import id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragmentViewModel;

/* loaded from: classes2.dex */
public class FragmentPengaturanBindingImpl extends FragmentPengaturanBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener expTvandroidTextAttrChanged;
    private InverseBindingListener icPersonalDataandroidTextAttrChanged;
    private final View.OnClickListener mCallback10;
    private final View.OnClickListener mCallback11;
    private final View.OnClickListener mCallback12;
    private final View.OnClickListener mCallback7;
    private final View.OnClickListener mCallback8;
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final Button mboundView10;
    private InverseBindingListener tvEmailandroidTextAttrChanged;
    private InverseBindingListener usernameTvandroidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.header_l, 11);
        sparseIntArray.put(R.id.tv_pengaturan, 12);
        sparseIntArray.put(R.id.modeProd_iv, 13);
        sparseIntArray.put(R.id.modeDev_tv, 14);
        sparseIntArray.put(R.id.cv_1, 15);
        sparseIntArray.put(R.id.v, 16);
        sparseIntArray.put(R.id.ic_2, 17);
        sparseIntArray.put(R.id.ic_3, 18);
        sparseIntArray.put(R.id.ic_6, 19);
        sparseIntArray.put(R.id.ic_7, 20);
        sparseIntArray.put(R.id.ic_8, 21);
    }

    public FragmentPengaturanBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private FragmentPengaturanBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ConstraintLayout) bindings[15], (ConstraintLayout) bindings[5], (ConstraintLayout) bindings[6], (ConstraintLayout) bindings[7], (ConstraintLayout) bindings[8], (ConstraintLayout) bindings[9], (TextView) bindings[4], (ConstraintLayout) bindings[11], (ImageView) bindings[17], (ImageView) bindings[18], (ImageView) bindings[19], (ImageView) bindings[20], (ImageView) bindings[21], (TextView) bindings[1], (Button) bindings[14], (ImageView) bindings[13], (TextView) bindings[3], (TextView) bindings[12], (TextView) bindings[2], (View) bindings[16]);
        this.expTvandroidTextAttrChanged = new InverseBindingListener() { // from class: id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentPengaturanBindingImpl.this.expTv);
                PengaturanFragmentViewModel pengaturanFragmentViewModel = FragmentPengaturanBindingImpl.this.mViewModel;
                if (pengaturanFragmentViewModel != null) {
                    ObservableField<String> expiredSession = pengaturanFragmentViewModel.getExpiredSession();
                    if (expiredSession != null) {
                        expiredSession.set(textString);
                    }
                }
            }
        };
        this.icPersonalDataandroidTextAttrChanged = new InverseBindingListener() { // from class: id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentPengaturanBindingImpl.this.icPersonalData);
                PengaturanFragmentViewModel pengaturanFragmentViewModel = FragmentPengaturanBindingImpl.this.mViewModel;
                if (pengaturanFragmentViewModel != null) {
                    ObservableField<String> foto = pengaturanFragmentViewModel.getFoto();
                    if (foto != null) {
                        foto.set(textString);
                    }
                }
            }
        };
        this.tvEmailandroidTextAttrChanged = new InverseBindingListener() { // from class: id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentPengaturanBindingImpl.this.tvEmail);
                PengaturanFragmentViewModel pengaturanFragmentViewModel = FragmentPengaturanBindingImpl.this.mViewModel;
                if (pengaturanFragmentViewModel != null) {
                    ObservableField<String> email = pengaturanFragmentViewModel.getEmail();
                    if (email != null) {
                        email.set(textString);
                    }
                }
            }
        };
        this.usernameTvandroidTextAttrChanged = new InverseBindingListener() { // from class: id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentPengaturanBindingImpl.this.usernameTv);
                PengaturanFragmentViewModel pengaturanFragmentViewModel = FragmentPengaturanBindingImpl.this.mViewModel;
                if (pengaturanFragmentViewModel != null) {
                    ObservableField<String> username = pengaturanFragmentViewModel.getUsername();
                    if (username != null) {
                        username.set(textString);
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.cv2.setTag(null);
        this.cv3.setTag(null);
        this.cv6.setTag(null);
        this.cv7.setTag(null);
        this.cv8.setTag(null);
        this.expTv.setTag(null);
        this.icPersonalData.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        Button button = (Button) bindings[10];
        this.mboundView10 = button;
        button.setTag(null);
        this.tvEmail.setTag(null);
        this.usernameTv.setTag(null);
        setRootTag(root);
        this.mCallback11 = new OnClickListener(this, 5);
        this.mCallback8 = new OnClickListener(this, 2);
        this.mCallback12 = new OnClickListener(this, 6);
        this.mCallback10 = new OnClickListener(this, 4);
        this.mCallback9 = new OnClickListener(this, 3);
        this.mCallback7 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (3 != variableId) {
            return false;
        }
        setViewModel((PengaturanFragmentViewModel) variable);
        return true;
    }

    @Override // id.go.bpsfasih.databinding.FragmentPengaturanBinding
    public void setViewModel(PengaturanFragmentViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeViewModelFoto((ObservableField) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeViewModelUsername((ObservableField) object, fieldId);
        }
        if (localFieldId == 2) {
            return onChangeViewModelEmail((ObservableField) object, fieldId);
        }
        if (localFieldId != 3) {
            return false;
        }
        return onChangeViewModelExpiredSession((ObservableField) object, fieldId);
    }

    private boolean onChangeViewModelFoto(ObservableField<String> ViewModelFoto, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelUsername(ObservableField<String> ViewModelUsername, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelEmail(ObservableField<String> ViewModelEmail, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelExpiredSession(ObservableField<String> ViewModelExpiredSession, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl.executeBindings():void");
    }

    @Override // id.go.bpsfasih.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        switch (sourceId) {
            case 1:
                PengaturanFragmentViewModel pengaturanFragmentViewModel = this.mViewModel;
                if (pengaturanFragmentViewModel != null) {
                    pengaturanFragmentViewModel.sistemClicked();
                    break;
                }
                break;
            case 2:
                PengaturanFragmentViewModel pengaturanFragmentViewModel2 = this.mViewModel;
                if (pengaturanFragmentViewModel2 != null) {
                    pengaturanFragmentViewModel2.backupResoreClicked();
                    break;
                }
                break;
            case 3:
                PengaturanFragmentViewModel pengaturanFragmentViewModel3 = this.mViewModel;
                if (pengaturanFragmentViewModel3 != null) {
                    pengaturanFragmentViewModel3.kebijakanPrivasiClicked();
                    break;
                }
                break;
            case 4:
                PengaturanFragmentViewModel pengaturanFragmentViewModel4 = this.mViewModel;
                if (pengaturanFragmentViewModel4 != null) {
                    pengaturanFragmentViewModel4.faqClicked();
                    break;
                }
                break;
            case 5:
                PengaturanFragmentViewModel pengaturanFragmentViewModel5 = this.mViewModel;
                if (pengaturanFragmentViewModel5 != null) {
                    pengaturanFragmentViewModel5.liveTrackingClicked();
                    break;
                }
                break;
            case 6:
                PengaturanFragmentViewModel pengaturanFragmentViewModel6 = this.mViewModel;
                if (pengaturanFragmentViewModel6 != null) {
                    pengaturanFragmentViewModel6.logoutClicked();
                    break;
                }
                break;
        }
    }
}
