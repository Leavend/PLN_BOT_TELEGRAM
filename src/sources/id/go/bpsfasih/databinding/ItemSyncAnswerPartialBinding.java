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
public abstract class ItemSyncAnswerPartialBinding extends ViewDataBinding {
    public final RecyclerView kontenRv;
    public final LinearLayout periodeConstrain;
    public final TextView periodeTitle;

    protected ItemSyncAnswerPartialBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView kontenRv, LinearLayout periodeConstrain, TextView periodeTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.kontenRv = kontenRv;
        this.periodeConstrain = periodeConstrain;
        this.periodeTitle = periodeTitle;
    }

    public static ItemSyncAnswerPartialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSyncAnswerPartialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync_answer_partial, root, attachToRoot, component);
    }

    public static ItemSyncAnswerPartialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSyncAnswerPartialBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync_answer_partial, null, false, component);
    }

    public static ItemSyncAnswerPartialBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialBinding bind(View view, Object component) {
        return (ItemSyncAnswerPartialBinding) bind(component, view, R.layout.item_sync_answer_partial);
    }
}
