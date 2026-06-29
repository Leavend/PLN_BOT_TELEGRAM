package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class BottomSheetBaseActivityBinding extends ViewDataBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final TextView halamanTv;
    public final Button laporkanB;
    public final TextView modeBottomSheerTv;
    public final ImageView tutup;
    public final TextView versiAplikasiTv;
    public final TextView versiFormgearTv;

    protected BottomSheetBaseActivityBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout bottomSheetBaseActivity, TextView halamanTv, Button laporkanB, TextView modeBottomSheerTv, ImageView tutup, TextView versiAplikasiTv, TextView versiFormgearTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.halamanTv = halamanTv;
        this.laporkanB = laporkanB;
        this.modeBottomSheerTv = modeBottomSheerTv;
        this.tutup = tutup;
        this.versiAplikasiTv = versiAplikasiTv;
        this.versiFormgearTv = versiFormgearTv;
    }

    public static BottomSheetBaseActivityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetBaseActivityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BottomSheetBaseActivityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_base_activity, root, attachToRoot, component);
    }

    public static BottomSheetBaseActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetBaseActivityBinding inflate(LayoutInflater inflater, Object component) {
        return (BottomSheetBaseActivityBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bottom_sheet_base_activity, null, false, component);
    }

    public static BottomSheetBaseActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BottomSheetBaseActivityBinding bind(View view, Object component) {
        return (BottomSheetBaseActivityBinding) bind(component, view, R.layout.bottom_sheet_base_activity);
    }
}
