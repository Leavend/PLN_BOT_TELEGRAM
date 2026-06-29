package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemSyncAnswerPartialPeriodeBinding extends ViewDataBinding {
    public final TextView deskripsiTvItemSyncAnswerPartial;
    public final LinearLayout periodeConstrain;
    public final ProgressBar progressPgItemSyncAnswerPartial;
    public final Button ulangiB;

    protected ItemSyncAnswerPartialPeriodeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView deskripsiTvItemSyncAnswerPartial, LinearLayout periodeConstrain, ProgressBar progressPgItemSyncAnswerPartial, Button ulangiB) {
        super(_bindingComponent, _root, _localFieldCount);
        this.deskripsiTvItemSyncAnswerPartial = deskripsiTvItemSyncAnswerPartial;
        this.periodeConstrain = periodeConstrain;
        this.progressPgItemSyncAnswerPartial = progressPgItemSyncAnswerPartial;
        this.ulangiB = ulangiB;
    }

    public static ItemSyncAnswerPartialPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialPeriodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSyncAnswerPartialPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync_answer_partial_periode, root, attachToRoot, component);
    }

    public static ItemSyncAnswerPartialPeriodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialPeriodeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSyncAnswerPartialPeriodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sync_answer_partial_periode, null, false, component);
    }

    public static ItemSyncAnswerPartialPeriodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSyncAnswerPartialPeriodeBinding bind(View view, Object component) {
        return (ItemSyncAnswerPartialPeriodeBinding) bind(component, view, R.layout.item_sync_answer_partial_periode);
    }
}
