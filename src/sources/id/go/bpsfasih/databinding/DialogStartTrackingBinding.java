package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class DialogStartTrackingBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnStart;
    private final LinearLayout rootView;

    private DialogStartTrackingBinding(LinearLayout rootView, Button btnCancel, Button btnStart) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.btnStart = btnStart;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogStartTrackingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogStartTrackingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_start_tracking, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogStartTrackingBinding bind(View rootView) {
        int i = R.id.btnCancel;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = R.id.btnStart;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button2 != null) {
                return new DialogStartTrackingBinding((LinearLayout) rootView, button, button2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
