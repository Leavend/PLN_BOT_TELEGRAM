package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemPeriodeBinding extends ViewDataBinding {
    public final Button btnUpdate;
    public final LinearLayout periodeConstrain;
    public final TextView periodeTime;
    public final TextView periodeTitle;
    public final TextView tvApprove;
    public final LinearLayout tvLihat;
    public final TextView tvOpen;
    public final TextView tvPending;
    public final TextView tvReject;
    public final TextView tvSubmit;

    protected ItemPeriodeBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnUpdate, LinearLayout periodeConstrain, TextView periodeTime, TextView periodeTitle, TextView tvApprove, LinearLayout tvLihat, TextView tvOpen, TextView tvPending, TextView tvReject, TextView tvSubmit) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnUpdate = btnUpdate;
        this.periodeConstrain = periodeConstrain;
        this.periodeTime = periodeTime;
        this.periodeTitle = periodeTitle;
        this.tvApprove = tvApprove;
        this.tvLihat = tvLihat;
        this.tvOpen = tvOpen;
        this.tvPending = tvPending;
        this.tvReject = tvReject;
        this.tvSubmit = tvSubmit;
    }

    public static ItemPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_periode, root, attachToRoot, component);
    }

    public static ItemPeriodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPeriodeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_periode, null, false, component);
    }

    public static ItemPeriodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPeriodeBinding bind(View view, Object component) {
        return (ItemPeriodeBinding) bind(component, view, R.layout.item_periode);
    }
}
