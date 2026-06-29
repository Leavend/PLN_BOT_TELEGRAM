package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetLihatRekapanBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final Button btnBatal;
    public final Button btnLihatRekapan;
    public final RadioGroup listColumnRg;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialogFilterAssignment;

    private BottomSheetLihatRekapanBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, Button btnBatal, Button btnLihatRekapan, RadioGroup listColumnRg, ImageView tutupButtomDialogFilterAssignment) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.btnBatal = btnBatal;
        this.btnLihatRekapan = btnLihatRekapan;
        this.listColumnRg = listColumnRg;
        this.tutupButtomDialogFilterAssignment = tutupButtomDialogFilterAssignment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetLihatRekapanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetLihatRekapanBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_lihat_rekapan, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetLihatRekapanBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.btn_batal;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = R.id.btn_lihat_rekapan;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button2 != null) {
                i = R.id.listColumn_rg;
                RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i);
                if (radioGroup != null) {
                    i = R.id.tutup_buttomDialogFilterAssignment;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        return new BottomSheetLihatRekapanBinding(linearLayout, linearLayout, button, button2, radioGroup, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
