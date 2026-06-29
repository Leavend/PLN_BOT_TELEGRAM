package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityAssignmentlistBinding extends ViewDataBinding {
    public final ExpandableFab expendableFab;
    public final FabOption fabAddAssignment;
    public final FabOption fabColorInfo;
    public final FabOption fabFilterByStatus;
    public final FabOption fabRegionDone;
    public final FabOption fabSorting;
    public final ViewToolbarBinding include3;

    @Bindable
    protected AssignmentUpdateListingViewModel mViewModel;
    public final WebView wvMain;

    public abstract void setViewModel(AssignmentUpdateListingViewModel viewModel);

    protected ActivityAssignmentlistBinding(Object _bindingComponent, View _root, int _localFieldCount, ExpandableFab expendableFab, FabOption fabAddAssignment, FabOption fabColorInfo, FabOption fabFilterByStatus, FabOption fabRegionDone, FabOption fabSorting, ViewToolbarBinding include3, WebView wvMain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.expendableFab = expendableFab;
        this.fabAddAssignment = fabAddAssignment;
        this.fabColorInfo = fabColorInfo;
        this.fabFilterByStatus = fabFilterByStatus;
        this.fabRegionDone = fabRegionDone;
        this.fabSorting = fabSorting;
        this.include3 = include3;
        this.wvMain = wvMain;
    }

    public AssignmentUpdateListingViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityAssignmentlistBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAssignmentlistBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAssignmentlistBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_assignmentlist, root, attachToRoot, component);
    }

    public static ActivityAssignmentlistBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAssignmentlistBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAssignmentlistBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_assignmentlist, null, false, component);
    }

    public static ActivityAssignmentlistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAssignmentlistBinding bind(View view, Object component) {
        return (ActivityAssignmentlistBinding) bind(component, view, R.layout.activity_assignmentlist);
    }
}
