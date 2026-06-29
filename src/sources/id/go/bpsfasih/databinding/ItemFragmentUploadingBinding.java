package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemFragmentUploadingBinding extends ViewDataBinding {
    public final Button btnReupload;
    public final ConstraintLayout constrainUploading;
    public final LinearLayout customData;
    public final CardView cvUploading;
    public final LinearLayout llData1;
    public final LinearLayout llData2;
    public final LinearLayout llData3;
    public final LinearLayout llData4;
    public final LinearLayout llData5;
    public final TextView titleData1;
    public final TextView titleData2;
    public final TextView titleData3;
    public final TextView titleData4;
    public final TextView titleData5;
    public final TextView tvBelumUpload;
    public final TextView tvDataNotFound;
    public final TextView tvErrorUpload;
    public final TextView valueData1;
    public final TextView valueData2;
    public final TextView valueData3;
    public final TextView valueData4;
    public final TextView valueData5;

    protected ItemFragmentUploadingBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnReupload, ConstraintLayout constrainUploading, LinearLayout customData, CardView cvUploading, LinearLayout llData1, LinearLayout llData2, LinearLayout llData3, LinearLayout llData4, LinearLayout llData5, TextView titleData1, TextView titleData2, TextView titleData3, TextView titleData4, TextView titleData5, TextView tvBelumUpload, TextView tvDataNotFound, TextView tvErrorUpload, TextView valueData1, TextView valueData2, TextView valueData3, TextView valueData4, TextView valueData5) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnReupload = btnReupload;
        this.constrainUploading = constrainUploading;
        this.customData = customData;
        this.cvUploading = cvUploading;
        this.llData1 = llData1;
        this.llData2 = llData2;
        this.llData3 = llData3;
        this.llData4 = llData4;
        this.llData5 = llData5;
        this.titleData1 = titleData1;
        this.titleData2 = titleData2;
        this.titleData3 = titleData3;
        this.titleData4 = titleData4;
        this.titleData5 = titleData5;
        this.tvBelumUpload = tvBelumUpload;
        this.tvDataNotFound = tvDataNotFound;
        this.tvErrorUpload = tvErrorUpload;
        this.valueData1 = valueData1;
        this.valueData2 = valueData2;
        this.valueData3 = valueData3;
        this.valueData4 = valueData4;
        this.valueData5 = valueData5;
    }

    public static ItemFragmentUploadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFragmentUploadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemFragmentUploadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fragment_uploading, root, attachToRoot, component);
    }

    public static ItemFragmentUploadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFragmentUploadingBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemFragmentUploadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_fragment_uploading, null, false, component);
    }

    public static ItemFragmentUploadingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFragmentUploadingBinding bind(View view, Object component) {
        return (ItemFragmentUploadingBinding) bind(component, view, R.layout.item_fragment_uploading);
    }
}
