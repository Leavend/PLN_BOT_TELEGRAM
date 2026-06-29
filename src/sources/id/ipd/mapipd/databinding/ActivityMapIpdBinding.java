package id.ipd.mapipd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.ipd.mapipd.R;

/* loaded from: classes2.dex */
public final class ActivityMapIpdBinding implements ViewBinding {
    public final Button actionB;
    public final NestedScrollView descriptionL;
    public final TextView descriptionTv;
    public final CardView dialogCv;
    public final Button directionB;
    private final ConstraintLayout rootView;
    public final TextView titleTv;

    private ActivityMapIpdBinding(ConstraintLayout constraintLayout, Button button, NestedScrollView nestedScrollView, TextView textView, CardView cardView, Button button2, TextView textView2) {
        this.rootView = constraintLayout;
        this.actionB = button;
        this.descriptionL = nestedScrollView;
        this.descriptionTv = textView;
        this.dialogCv = cardView;
        this.directionB = button2;
        this.titleTv = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMapIpdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMapIpdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_map_ipd, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMapIpdBinding bind(View view) {
        int i = R.id.action_b;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.description_l;
            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, i);
            if (nestedScrollView != null) {
                i = R.id.description_tv;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.dialog_cv;
                    CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                    if (cardView != null) {
                        i = R.id.direction_b;
                        Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                        if (button2 != null) {
                            i = R.id.title_tv;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                return new ActivityMapIpdBinding((ConstraintLayout) view, button, nestedScrollView, textView, cardView, button2, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
