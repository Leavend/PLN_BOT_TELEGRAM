package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dewakoding.androiddatatable.DataTableView;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ActivityRekapWilayahBinding extends ViewDataBinding {
    public final Button btnChangeVariable;
    public final DataTableView dtvTable;
    public final ViewToolbarBinding include3;
    public final TextView tvEmpty;
    public final TextView tvWilayah;

    protected ActivityRekapWilayahBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnChangeVariable, DataTableView dtvTable, ViewToolbarBinding include3, TextView tvEmpty, TextView tvWilayah) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnChangeVariable = btnChangeVariable;
        this.dtvTable = dtvTable;
        this.include3 = include3;
        this.tvEmpty = tvEmpty;
        this.tvWilayah = tvWilayah;
    }

    public static ActivityRekapWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRekapWilayahBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityRekapWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rekap_wilayah, root, attachToRoot, component);
    }

    public static ActivityRekapWilayahBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRekapWilayahBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityRekapWilayahBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_rekap_wilayah, null, false, component);
    }

    public static ActivityRekapWilayahBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRekapWilayahBinding bind(View view, Object component) {
        return (ActivityRekapWilayahBinding) bind(component, view, R.layout.activity_rekap_wilayah);
    }
}
