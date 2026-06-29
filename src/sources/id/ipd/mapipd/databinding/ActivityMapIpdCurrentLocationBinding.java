package id.ipd.mapipd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.ipd.mapipd.R;

/* loaded from: classes2.dex */
public final class ActivityMapIpdCurrentLocationBinding implements ViewBinding {
    public final ConstraintLayout accCl;
    public final TextView accLabelTv;
    public final TextView accTv;
    public final Button actionB;
    public final ConstraintLayout latCl;
    public final TextView latLabelTv;
    public final TextView latTv;
    public final ConstraintLayout longCl;
    public final TextView longLabelTv;
    public final TextView longTv;
    private final ConstraintLayout rootView;

    private ActivityMapIpdCurrentLocationBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, Button button, ConstraintLayout constraintLayout3, TextView textView3, TextView textView4, ConstraintLayout constraintLayout4, TextView textView5, TextView textView6) {
        this.rootView = constraintLayout;
        this.accCl = constraintLayout2;
        this.accLabelTv = textView;
        this.accTv = textView2;
        this.actionB = button;
        this.latCl = constraintLayout3;
        this.latLabelTv = textView3;
        this.latTv = textView4;
        this.longCl = constraintLayout4;
        this.longLabelTv = textView5;
        this.longTv = textView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMapIpdCurrentLocationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMapIpdCurrentLocationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_map_ipd_current_location, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMapIpdCurrentLocationBinding bind(View view) {
        int i = R.id.acc_cl;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
        if (constraintLayout != null) {
            i = R.id.accLabel_tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.acc_tv;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.action_b;
                    Button button = (Button) ViewBindings.findChildViewById(view, i);
                    if (button != null) {
                        i = R.id.lat_cl;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                        if (constraintLayout2 != null) {
                            i = R.id.latLabel_tv;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.lat_tv;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.long_cl;
                                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout3 != null) {
                                        i = R.id.longLabel_tv;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView5 != null) {
                                            i = R.id.long_tv;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView6 != null) {
                                                return new ActivityMapIpdCurrentLocationBinding((ConstraintLayout) view, constraintLayout, textView, textView2, button, constraintLayout2, textView3, textView4, constraintLayout3, textView5, textView6);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
