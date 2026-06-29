package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityFormgearBinding extends ViewDataBinding {
    public final TextView badgeEncrypted;
    public final ExpandableFab expendableFabBukanPencacah;
    public final ExpandableFab expendableFabPencacah;
    public final FabOption fabApproval;
    public final FabOption fabBantuanBukanPencacah;
    public final FabOption fabBantuanPencacah;
    public final FabOption fabCalculatorBukanPencacah;
    public final FabOption fabCalculatorPencacah;
    public final FabOption fabLocationPencacah;
    public final FabOption fabNotesBukanPencacah;
    public final FabOption fabNotesPencacah;
    public final FabOption fabRebuildBukanPencacah;
    public final FabOption fabRebuildPencacah;
    public final FabOption fabSimpanSementaraPencacah;

    @Bindable
    protected AssignmentUpdateListingViewModel mViewModel;
    public final RelativeLayout rootLayout;
    public final RelativeLayout rvBukanPencacah;
    public final RelativeLayout rvPencacah;
    public final WebView wvMain;

    public abstract void setViewModel(AssignmentUpdateListingViewModel viewModel);

    protected ActivityFormgearBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView badgeEncrypted, ExpandableFab expendableFabBukanPencacah, ExpandableFab expendableFabPencacah, FabOption fabApproval, FabOption fabBantuanBukanPencacah, FabOption fabBantuanPencacah, FabOption fabCalculatorBukanPencacah, FabOption fabCalculatorPencacah, FabOption fabLocationPencacah, FabOption fabNotesBukanPencacah, FabOption fabNotesPencacah, FabOption fabRebuildBukanPencacah, FabOption fabRebuildPencacah, FabOption fabSimpanSementaraPencacah, RelativeLayout rootLayout, RelativeLayout rvBukanPencacah, RelativeLayout rvPencacah, WebView wvMain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.badgeEncrypted = badgeEncrypted;
        this.expendableFabBukanPencacah = expendableFabBukanPencacah;
        this.expendableFabPencacah = expendableFabPencacah;
        this.fabApproval = fabApproval;
        this.fabBantuanBukanPencacah = fabBantuanBukanPencacah;
        this.fabBantuanPencacah = fabBantuanPencacah;
        this.fabCalculatorBukanPencacah = fabCalculatorBukanPencacah;
        this.fabCalculatorPencacah = fabCalculatorPencacah;
        this.fabLocationPencacah = fabLocationPencacah;
        this.fabNotesBukanPencacah = fabNotesBukanPencacah;
        this.fabNotesPencacah = fabNotesPencacah;
        this.fabRebuildBukanPencacah = fabRebuildBukanPencacah;
        this.fabRebuildPencacah = fabRebuildPencacah;
        this.fabSimpanSementaraPencacah = fabSimpanSementaraPencacah;
        this.rootLayout = rootLayout;
        this.rvBukanPencacah = rvBukanPencacah;
        this.rvPencacah = rvPencacah;
        this.wvMain = wvMain;
    }

    public AssignmentUpdateListingViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityFormgearBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityFormgearBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_formgear, root, attachToRoot, component);
    }

    public static ActivityFormgearBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityFormgearBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_formgear, null, false, component);
    }

    public static ActivityFormgearBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFormgearBinding bind(View view, Object component) {
        return (ActivityFormgearBinding) bind(component, view, R.layout.activity_formgear);
    }
}
