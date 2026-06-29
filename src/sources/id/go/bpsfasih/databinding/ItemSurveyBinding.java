package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public abstract class ItemSurveyBinding extends ViewDataBinding {
    public final ImageView pinSurveyIv;
    public final ConstraintLayout root;
    public final LinearLayout tvLihat;
    public final TextView tvPeriodeCount;
    public final TextView tvSurveyName;

    protected ItemSurveyBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView pinSurveyIv, ConstraintLayout root, LinearLayout tvLihat, TextView tvPeriodeCount, TextView tvSurveyName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.pinSurveyIv = pinSurveyIv;
        this.root = root;
        this.tvLihat = tvLihat;
        this.tvPeriodeCount = tvPeriodeCount;
        this.tvSurveyName = tvSurveyName;
    }

    public static ItemSurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSurveyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_survey, root, attachToRoot, component);
    }

    public static ItemSurveyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSurveyBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSurveyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_survey, null, false, component);
    }

    public static ItemSurveyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSurveyBinding bind(View view, Object component) {
        return (ItemSurveyBinding) bind(component, view, R.layout.item_survey);
    }
}
