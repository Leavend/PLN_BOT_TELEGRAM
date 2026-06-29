package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemSortAssignmentBinding extends ViewDataBinding {
    protected ItemSortAssignmentBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static ItemSortAssignmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSortAssignmentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSortAssignmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sort_assignment, root, attachToRoot, component);
    }

    public static ItemSortAssignmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSortAssignmentBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSortAssignmentBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sort_assignment, null, false, component);
    }

    public static ItemSortAssignmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSortAssignmentBinding bind(View view, Object component) {
        return (ItemSortAssignmentBinding) bind(component, view, R.layout.item_sort_assignment);
    }
}
