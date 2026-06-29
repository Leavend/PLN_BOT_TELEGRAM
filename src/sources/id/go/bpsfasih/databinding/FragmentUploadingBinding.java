package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class FragmentUploadingBinding extends ViewDataBinding {
    public final RecyclerView fragmentUploadingRecycler;
    public final TextView tvEmpty;

    protected FragmentUploadingBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView fragmentUploadingRecycler, TextView tvEmpty) {
        super(_bindingComponent, _root, _localFieldCount);
        this.fragmentUploadingRecycler = fragmentUploadingRecycler;
        this.tvEmpty = tvEmpty;
    }

    public static FragmentUploadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUploadingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentUploadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_uploading, root, attachToRoot, component);
    }

    public static FragmentUploadingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUploadingBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentUploadingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_uploading, null, false, component);
    }

    public static FragmentUploadingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUploadingBinding bind(View view, Object component) {
        return (FragmentUploadingBinding) bind(component, view, R.layout.fragment_uploading);
    }
}
