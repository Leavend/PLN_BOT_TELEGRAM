package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetFilterAssignmentBinding implements ViewBinding {
    public final CheckBox approveCbBottomSheetFilterAssignment;
    public final LinearLayout bottomSheetBaseActivity;
    public final Button lButtonBottomDialogFilterAssignment;
    public final CheckBox openCbBottomSheetFilterAssignment;
    public final CheckBox pendingCbBottomSheetFilterAssignment;
    public final CheckBox pernahDibukaCbBottomSheetFilterAssignment;
    public final Button rButtonBottomDialogFilterAssignment;
    public final CheckBox rejectCbBottomSheetFilterAssignment;
    private final LinearLayout rootView;
    public final CheckBox submitCbBottomSheetFilterAssignment;
    public final ImageView tutupButtomDialogFilterAssignment;

    private BottomSheetFilterAssignmentBinding(LinearLayout rootView, CheckBox approveCbBottomSheetFilterAssignment, LinearLayout bottomSheetBaseActivity, Button lButtonBottomDialogFilterAssignment, CheckBox openCbBottomSheetFilterAssignment, CheckBox pendingCbBottomSheetFilterAssignment, CheckBox pernahDibukaCbBottomSheetFilterAssignment, Button rButtonBottomDialogFilterAssignment, CheckBox rejectCbBottomSheetFilterAssignment, CheckBox submitCbBottomSheetFilterAssignment, ImageView tutupButtomDialogFilterAssignment) {
        this.rootView = rootView;
        this.approveCbBottomSheetFilterAssignment = approveCbBottomSheetFilterAssignment;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.lButtonBottomDialogFilterAssignment = lButtonBottomDialogFilterAssignment;
        this.openCbBottomSheetFilterAssignment = openCbBottomSheetFilterAssignment;
        this.pendingCbBottomSheetFilterAssignment = pendingCbBottomSheetFilterAssignment;
        this.pernahDibukaCbBottomSheetFilterAssignment = pernahDibukaCbBottomSheetFilterAssignment;
        this.rButtonBottomDialogFilterAssignment = rButtonBottomDialogFilterAssignment;
        this.rejectCbBottomSheetFilterAssignment = rejectCbBottomSheetFilterAssignment;
        this.submitCbBottomSheetFilterAssignment = submitCbBottomSheetFilterAssignment;
        this.tutupButtomDialogFilterAssignment = tutupButtomDialogFilterAssignment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetFilterAssignmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetFilterAssignmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_filter_assignment, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetFilterAssignmentBinding bind(View rootView) {
        int i = R.id.approve_cb_bottomSheetFilterAssignment;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i);
        if (checkBox != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.lButton_bottomDialogFilterAssignment;
            Button button = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button != null) {
                i = R.id.open_cb_bottomSheetFilterAssignment;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(rootView, i);
                if (checkBox2 != null) {
                    i = R.id.pending_cb_bottomSheetFilterAssignment;
                    CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(rootView, i);
                    if (checkBox3 != null) {
                        i = R.id.pernahDibuka_cb_bottomSheetFilterAssignment;
                        CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(rootView, i);
                        if (checkBox4 != null) {
                            i = R.id.rButton_bottomDialogFilterAssignment;
                            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                            if (button2 != null) {
                                i = R.id.reject_cb_bottomSheetFilterAssignment;
                                CheckBox checkBox5 = (CheckBox) ViewBindings.findChildViewById(rootView, i);
                                if (checkBox5 != null) {
                                    i = R.id.submit_cb_bottomSheetFilterAssignment;
                                    CheckBox checkBox6 = (CheckBox) ViewBindings.findChildViewById(rootView, i);
                                    if (checkBox6 != null) {
                                        i = R.id.tutup_buttomDialogFilterAssignment;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView != null) {
                                            return new BottomSheetFilterAssignmentBinding(linearLayout, checkBox, linearLayout, button, checkBox2, checkBox3, checkBox4, button2, checkBox5, checkBox6, imageView);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
