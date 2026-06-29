package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemCustomColumnBinding extends ViewDataBinding {
    public final TextView columnNameTv;
    public final CheckBox itemCb;
    public final LinearLayout rootLASync;

    protected ItemCustomColumnBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView columnNameTv, CheckBox itemCb, LinearLayout rootLASync) {
        super(_bindingComponent, _root, _localFieldCount);
        this.columnNameTv = columnNameTv;
        this.itemCb = itemCb;
        this.rootLASync = rootLASync;
    }

    public static ItemCustomColumnBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomColumnBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemCustomColumnBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_custom_column, root, attachToRoot, component);
    }

    public static ItemCustomColumnBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomColumnBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemCustomColumnBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_custom_column, null, false, component);
    }

    public static ItemCustomColumnBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCustomColumnBinding bind(View view, Object component) {
        return (ItemCustomColumnBinding) bind(component, view, R.layout.item_custom_column);
    }
}
