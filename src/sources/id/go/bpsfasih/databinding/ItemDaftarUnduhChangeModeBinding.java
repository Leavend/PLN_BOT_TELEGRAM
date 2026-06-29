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
public abstract class ItemDaftarUnduhChangeModeBinding extends ViewDataBinding {
    public final LinearLayout llData1;
    public final LinearLayout llData10;
    public final LinearLayout llData2;
    public final LinearLayout llData3;
    public final LinearLayout llData4;
    public final LinearLayout llData5;
    public final LinearLayout llData6;
    public final LinearLayout llData7;
    public final LinearLayout llData8;
    public final LinearLayout llData9;
    public final TextView tvLabelData1;
    public final TextView tvLabelData10;
    public final TextView tvLabelData2;
    public final TextView tvLabelData3;
    public final TextView tvLabelData4;
    public final TextView tvLabelData5;
    public final TextView tvLabelData6;
    public final TextView tvLabelData7;
    public final TextView tvLabelData8;
    public final TextView tvLabelData9;
    public final TextView tvValueData1;
    public final TextView tvValueData10;
    public final TextView tvValueData2;
    public final TextView tvValueData3;
    public final TextView tvValueData4;
    public final TextView tvValueData5;
    public final TextView tvValueData6;
    public final TextView tvValueData7;
    public final TextView tvValueData8;
    public final TextView tvValueData9;
    public final Button unduhAnswerB;
    public final LinearLayout updateListingLayout;

    protected ItemDaftarUnduhChangeModeBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llData1, LinearLayout llData10, LinearLayout llData2, LinearLayout llData3, LinearLayout llData4, LinearLayout llData5, LinearLayout llData6, LinearLayout llData7, LinearLayout llData8, LinearLayout llData9, TextView tvLabelData1, TextView tvLabelData10, TextView tvLabelData2, TextView tvLabelData3, TextView tvLabelData4, TextView tvLabelData5, TextView tvLabelData6, TextView tvLabelData7, TextView tvLabelData8, TextView tvLabelData9, TextView tvValueData1, TextView tvValueData10, TextView tvValueData2, TextView tvValueData3, TextView tvValueData4, TextView tvValueData5, TextView tvValueData6, TextView tvValueData7, TextView tvValueData8, TextView tvValueData9, Button unduhAnswerB, LinearLayout updateListingLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llData1 = llData1;
        this.llData10 = llData10;
        this.llData2 = llData2;
        this.llData3 = llData3;
        this.llData4 = llData4;
        this.llData5 = llData5;
        this.llData6 = llData6;
        this.llData7 = llData7;
        this.llData8 = llData8;
        this.llData9 = llData9;
        this.tvLabelData1 = tvLabelData1;
        this.tvLabelData10 = tvLabelData10;
        this.tvLabelData2 = tvLabelData2;
        this.tvLabelData3 = tvLabelData3;
        this.tvLabelData4 = tvLabelData4;
        this.tvLabelData5 = tvLabelData5;
        this.tvLabelData6 = tvLabelData6;
        this.tvLabelData7 = tvLabelData7;
        this.tvLabelData8 = tvLabelData8;
        this.tvLabelData9 = tvLabelData9;
        this.tvValueData1 = tvValueData1;
        this.tvValueData10 = tvValueData10;
        this.tvValueData2 = tvValueData2;
        this.tvValueData3 = tvValueData3;
        this.tvValueData4 = tvValueData4;
        this.tvValueData5 = tvValueData5;
        this.tvValueData6 = tvValueData6;
        this.tvValueData7 = tvValueData7;
        this.tvValueData8 = tvValueData8;
        this.tvValueData9 = tvValueData9;
        this.unduhAnswerB = unduhAnswerB;
        this.updateListingLayout = updateListingLayout;
    }

    public static ItemDaftarUnduhChangeModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarUnduhChangeModeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDaftarUnduhChangeModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daftar_unduh_change_mode, root, attachToRoot, component);
    }

    public static ItemDaftarUnduhChangeModeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarUnduhChangeModeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDaftarUnduhChangeModeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daftar_unduh_change_mode, null, false, component);
    }

    public static ItemDaftarUnduhChangeModeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarUnduhChangeModeBinding bind(View view, Object component) {
        return (ItemDaftarUnduhChangeModeBinding) bind(component, view, R.layout.item_daftar_unduh_change_mode);
    }
}
