package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemDaftarWilayahBinding extends ViewDataBinding {
    public final LinearLayout btnRekapWilayah;
    public final ImageView imgWrappedDataKey;
    public final LinearLayout llLevel1;
    public final LinearLayout llLevel10;
    public final LinearLayout llLevel2;
    public final LinearLayout llLevel3;
    public final LinearLayout llLevel4;
    public final LinearLayout llLevel5;
    public final LinearLayout llLevel6;
    public final LinearLayout llLevel7;
    public final LinearLayout llLevel8;
    public final LinearLayout llLevel9;
    public final LinearLayout llRegionCode;
    public final TextView tvApprove;
    public final TextView tvLabelLevel1;
    public final TextView tvLabelLevel10;
    public final TextView tvLabelLevel2;
    public final TextView tvLabelLevel3;
    public final TextView tvLabelLevel4;
    public final TextView tvLabelLevel5;
    public final TextView tvLabelLevel6;
    public final TextView tvLabelLevel7;
    public final TextView tvLabelLevel8;
    public final TextView tvLabelLevel9;
    public final TextView tvLabelRegionCode;
    public final TextView tvOfflineSampling;
    public final TextView tvOpen;
    public final TextView tvPending;
    public final TextView tvReject;
    public final TextView tvSubmit;
    public final TextView tvValueLevel1;
    public final TextView tvValueLevel10;
    public final TextView tvValueLevel2;
    public final TextView tvValueLevel3;
    public final TextView tvValueLevel4;
    public final TextView tvValueLevel5;
    public final TextView tvValueLevel6;
    public final TextView tvValueLevel7;
    public final TextView tvValueLevel8;
    public final TextView tvValueLevel9;
    public final TextView tvValueRegionCode;
    public final LinearLayout updateListingLayout;

    protected ItemDaftarWilayahBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnRekapWilayah, ImageView imgWrappedDataKey, LinearLayout llLevel1, LinearLayout llLevel10, LinearLayout llLevel2, LinearLayout llLevel3, LinearLayout llLevel4, LinearLayout llLevel5, LinearLayout llLevel6, LinearLayout llLevel7, LinearLayout llLevel8, LinearLayout llLevel9, LinearLayout llRegionCode, TextView tvApprove, TextView tvLabelLevel1, TextView tvLabelLevel10, TextView tvLabelLevel2, TextView tvLabelLevel3, TextView tvLabelLevel4, TextView tvLabelLevel5, TextView tvLabelLevel6, TextView tvLabelLevel7, TextView tvLabelLevel8, TextView tvLabelLevel9, TextView tvLabelRegionCode, TextView tvOfflineSampling, TextView tvOpen, TextView tvPending, TextView tvReject, TextView tvSubmit, TextView tvValueLevel1, TextView tvValueLevel10, TextView tvValueLevel2, TextView tvValueLevel3, TextView tvValueLevel4, TextView tvValueLevel5, TextView tvValueLevel6, TextView tvValueLevel7, TextView tvValueLevel8, TextView tvValueLevel9, TextView tvValueRegionCode, LinearLayout updateListingLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnRekapWilayah = btnRekapWilayah;
        this.imgWrappedDataKey = imgWrappedDataKey;
        this.llLevel1 = llLevel1;
        this.llLevel10 = llLevel10;
        this.llLevel2 = llLevel2;
        this.llLevel3 = llLevel3;
        this.llLevel4 = llLevel4;
        this.llLevel5 = llLevel5;
        this.llLevel6 = llLevel6;
        this.llLevel7 = llLevel7;
        this.llLevel8 = llLevel8;
        this.llLevel9 = llLevel9;
        this.llRegionCode = llRegionCode;
        this.tvApprove = tvApprove;
        this.tvLabelLevel1 = tvLabelLevel1;
        this.tvLabelLevel10 = tvLabelLevel10;
        this.tvLabelLevel2 = tvLabelLevel2;
        this.tvLabelLevel3 = tvLabelLevel3;
        this.tvLabelLevel4 = tvLabelLevel4;
        this.tvLabelLevel5 = tvLabelLevel5;
        this.tvLabelLevel6 = tvLabelLevel6;
        this.tvLabelLevel7 = tvLabelLevel7;
        this.tvLabelLevel8 = tvLabelLevel8;
        this.tvLabelLevel9 = tvLabelLevel9;
        this.tvLabelRegionCode = tvLabelRegionCode;
        this.tvOfflineSampling = tvOfflineSampling;
        this.tvOpen = tvOpen;
        this.tvPending = tvPending;
        this.tvReject = tvReject;
        this.tvSubmit = tvSubmit;
        this.tvValueLevel1 = tvValueLevel1;
        this.tvValueLevel10 = tvValueLevel10;
        this.tvValueLevel2 = tvValueLevel2;
        this.tvValueLevel3 = tvValueLevel3;
        this.tvValueLevel4 = tvValueLevel4;
        this.tvValueLevel5 = tvValueLevel5;
        this.tvValueLevel6 = tvValueLevel6;
        this.tvValueLevel7 = tvValueLevel7;
        this.tvValueLevel8 = tvValueLevel8;
        this.tvValueLevel9 = tvValueLevel9;
        this.tvValueRegionCode = tvValueRegionCode;
        this.updateListingLayout = updateListingLayout;
    }

    public static ItemDaftarWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDaftarWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daftar_wilayah, root, attachToRoot, component);
    }

    public static ItemDaftarWilayahBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarWilayahBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDaftarWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_daftar_wilayah, null, false, component);
    }

    public static ItemDaftarWilayahBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDaftarWilayahBinding bind(View view, Object component) {
        return (ItemDaftarWilayahBinding) bind(component, view, R.layout.item_daftar_wilayah);
    }
}
