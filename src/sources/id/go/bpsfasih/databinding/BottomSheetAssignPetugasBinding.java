package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetAssignPetugasBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final Button lButtonBd;
    public final RadioGroup petugasRgBd;
    public final Button rButtonBd;
    private final LinearLayout rootView;
    public final TextView titleBd;
    public final ImageView tutupBd;

    private BottomSheetAssignPetugasBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, Button lButtonBd, RadioGroup petugasRgBd, Button rButtonBd, TextView titleBd, ImageView tutupBd) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.lButtonBd = lButtonBd;
        this.petugasRgBd = petugasRgBd;
        this.rButtonBd = rButtonBd;
        this.titleBd = titleBd;
        this.tutupBd = tutupBd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetAssignPetugasBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetAssignPetugasBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_assign_petugas, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetAssignPetugasBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.lButton_bd;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = R.id.petugas_rg_bd;
            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i);
            if (radioGroup != null) {
                i = R.id.rButton_bd;
                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                if (button2 != null) {
                    i = R.id.title_bd;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView != null) {
                        i = R.id.tutup_bd;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            return new BottomSheetAssignPetugasBinding(linearLayout, linearLayout, button, radioGroup, button2, textView, imageView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
