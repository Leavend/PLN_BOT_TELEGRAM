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
public final class BottomSheetAssignmentAksiBinding implements ViewBinding {
    public final Button assignAssignmentB;
    public final LinearLayout bottomSheetBaseActivity;
    public final Button changeModeAssignmentB;
    public final Button deleteAssignmentB;
    public final Button infoAssignmentB;
    public final Button openAssignmentB;
    public final Button redownloadData;
    public final Button restoreAssignmentB;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialogFilterAssignment;

    private BottomSheetAssignmentAksiBinding(LinearLayout rootView, Button assignAssignmentB, LinearLayout bottomSheetBaseActivity, Button changeModeAssignmentB, Button deleteAssignmentB, Button infoAssignmentB, Button openAssignmentB, Button redownloadData, Button restoreAssignmentB, ImageView tutupButtomDialogFilterAssignment) {
        this.rootView = rootView;
        this.assignAssignmentB = assignAssignmentB;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.changeModeAssignmentB = changeModeAssignmentB;
        this.deleteAssignmentB = deleteAssignmentB;
        this.infoAssignmentB = infoAssignmentB;
        this.openAssignmentB = openAssignmentB;
        this.redownloadData = redownloadData;
        this.restoreAssignmentB = restoreAssignmentB;
        this.tutupButtomDialogFilterAssignment = tutupButtomDialogFilterAssignment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetAssignmentAksiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetAssignmentAksiBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_assignment_aksi, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetAssignmentAksiBinding bind(View rootView) {
        int i = R.id.assignAssignment_b;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.changeModeAssignment_b;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button2 != null) {
                i = R.id.deleteAssignment_b;
                Button button3 = (Button) ViewBindings.findChildViewById(rootView, i);
                if (button3 != null) {
                    i = R.id.infoAssignment_b;
                    Button button4 = (Button) ViewBindings.findChildViewById(rootView, i);
                    if (button4 != null) {
                        i = R.id.openAssignment_b;
                        Button button5 = (Button) ViewBindings.findChildViewById(rootView, i);
                        if (button5 != null) {
                            i = R.id.redownload_data;
                            Button button6 = (Button) ViewBindings.findChildViewById(rootView, i);
                            if (button6 != null) {
                                i = R.id.restoreAssignment_b;
                                Button button7 = (Button) ViewBindings.findChildViewById(rootView, i);
                                if (button7 != null) {
                                    i = R.id.tutup_buttomDialogFilterAssignment;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView != null) {
                                        return new BottomSheetAssignmentAksiBinding(linearLayout, button, linearLayout, button2, button3, button4, button5, button6, button7, imageView);
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
