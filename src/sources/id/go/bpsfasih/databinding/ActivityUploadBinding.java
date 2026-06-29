package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ActivityUploadBinding extends ViewDataBinding {
    public final ViewToolbarBinding include3;
    public final LinearLayout llEmpty;
    public final RecyclerView rvUpload;
    public final TextView tvEmpty;

    protected ActivityUploadBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewToolbarBinding include3, LinearLayout llEmpty, RecyclerView rvUpload, TextView tvEmpty) {
        super(_bindingComponent, _root, _localFieldCount);
        this.include3 = include3;
        this.llEmpty = llEmpty;
        this.rvUpload = rvUpload;
        this.tvEmpty = tvEmpty;
    }

    public static ActivityUploadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUploadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityUploadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_upload, root, attachToRoot, component);
    }

    public static ActivityUploadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUploadBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityUploadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_upload, null, false, component);
    }

    public static ActivityUploadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityUploadBinding bind(View view, Object component) {
        return (ActivityUploadBinding) bind(component, view, R.layout.activity_upload);
    }
}
