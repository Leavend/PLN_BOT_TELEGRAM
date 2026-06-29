package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetSortAssignmentBinding implements ViewBinding {
    public final RadioButton angkaRb;
    public final RadioButton ascRb;
    public final LinearLayout bottomSheetBaseActivity;
    public final RadioButton descRb;
    public final RadioButton hurufRb;
    public final Button lButton;
    public final RadioGroup listColumnRg;
    public final Button rButton;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialogFilterAssignment;

    private BottomSheetSortAssignmentBinding(LinearLayout rootView, RadioButton angkaRb, RadioButton ascRb, LinearLayout bottomSheetBaseActivity, RadioButton descRb, RadioButton hurufRb, Button lButton, RadioGroup listColumnRg, Button rButton, ImageView tutupButtomDialogFilterAssignment) {
        this.rootView = rootView;
        this.angkaRb = angkaRb;
        this.ascRb = ascRb;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.descRb = descRb;
        this.hurufRb = hurufRb;
        this.lButton = lButton;
        this.listColumnRg = listColumnRg;
        this.rButton = rButton;
        this.tutupButtomDialogFilterAssignment = tutupButtomDialogFilterAssignment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetSortAssignmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetSortAssignmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_sort_assignment, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetSortAssignmentBinding bind(View rootView) {
        int i = R.id.angka_rb;
        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i);
        if (radioButton != null) {
            i = R.id.asc_rb;
            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
            if (radioButton2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                i = R.id.desc_rb;
                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                if (radioButton3 != null) {
                    i = R.id.huruf_rb;
                    RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, i);
                    if (radioButton4 != null) {
                        i = R.id.lButton;
                        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
                        if (button != null) {
                            i = R.id.listColumn_rg;
                            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i);
                            if (radioGroup != null) {
                                i = R.id.rButton;
                                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                                if (button2 != null) {
                                    i = R.id.tutup_buttomDialogFilterAssignment;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView != null) {
                                        return new BottomSheetSortAssignmentBinding(linearLayout, radioButton, radioButton2, linearLayout, radioButton3, radioButton4, button, radioGroup, button2, imageView);
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
