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
public abstract class ItemSyncBinding extends ViewDataBinding {
    public final CheckBox itemCbASync;
    public final LinearLayout rootLASync;
    public final TextView surveyTitleTv;

    protected ItemSyncBinding(Object _bindingComponent, View _root, int _localFieldCount, CheckBox itemCbASync, LinearLayout rootLASync, TextView surveyTitleTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.itemCbASync = itemCbASync;
        this.rootLASync = rootLASync;
        this.surveyTitleTv = surveyTitleTv;
    }

    public static ItemSyncBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSyncBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync, root, attachToRoot, component);
    }

    public static ItemSyncBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSyncBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync, null, false, component);
    }

    public static ItemSyncBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncBinding bind(View view, Object component) {
        return (ItemSyncBinding) bind(component, view, R.layout.item_sync);
    }
}
