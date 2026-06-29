package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomDialogSortAssignmentBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final Button rButtonBottomDialog;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialog;

    private BottomDialogSortAssignmentBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, Button rButtonBottomDialog, ImageView tutupButtomDialog) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.rButtonBottomDialog = rButtonBottomDialog;
        this.tutupButtomDialog = tutupButtomDialog;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomDialogSortAssignmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomDialogSortAssignmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_dialog_sort_assignment, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomDialogSortAssignmentBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.rButton_bottomDialog;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = R.id.tutup_buttomDialog;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                return new BottomDialogSortAssignmentBinding(linearLayout, linearLayout, button, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
