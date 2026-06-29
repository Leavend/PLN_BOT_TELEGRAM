package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.syncAnswer.SyncAnswerPartialViewModel;

/* loaded from: classes2.dex */
public abstract class ActivitySyncAnswerPartialBinding extends ViewDataBinding {
    public final ImageView icon;
    public final ViewToolbarBinding include;
    public final TextView judul;
    public final TextView keterangan;
    public final RecyclerView kontenRv;

    @Bindable
    protected SyncAnswerPartialViewModel mViewModel;
    public final ConstraintLayout mulaiCl;
    public final Button tutup;
    public final Button unduh;

    public abstract void setViewModel(SyncAnswerPartialViewModel viewModel);

    protected ActivitySyncAnswerPartialBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView icon, ViewToolbarBinding include, TextView judul, TextView keterangan, RecyclerView kontenRv, ConstraintLayout mulaiCl, Button tutup, Button unduh) {
        super(_bindingComponent, _root, _localFieldCount);
        this.icon = icon;
        this.include = include;
        this.judul = judul;
        this.keterangan = keterangan;
        this.kontenRv = kontenRv;
        this.mulaiCl = mulaiCl;
        this.tutup = tutup;
        this.unduh = unduh;
    }

    public SyncAnswerPartialViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivitySyncAnswerPartialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySyncAnswerPartialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySyncAnswerPartialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sync_answer_partial, root, attachToRoot, component);
    }

    public static ActivitySyncAnswerPartialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySyncAnswerPartialBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySyncAnswerPartialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_sync_answer_partial, null, false, component);
    }

    public static ActivitySyncAnswerPartialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySyncAnswerPartialBinding bind(View view, Object component) {
        return (ActivitySyncAnswerPartialBinding) bind(component, view, R.layout.activity_sync_answer_partial);
    }
}
