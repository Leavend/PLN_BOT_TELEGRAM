package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetBaseDialogChangeModeBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final CheckBox checkboxBottomDialogChangeMode;
    public final TextView deskripsiBottomDialogChangeMode;
    public final EditText emailEtBottomDialogChangeMode;
    public final LinearLayout emailLBottomDialogChangeMode;
    public final ImageView iconBottomDialogChangeMode;
    public final TextView judulBottomDialogChangeMode;
    public final Button lButtonBottomDialogChangeMode;
    public final Button rButtonBottomDialogChangeMode;
    private final LinearLayout rootView;
    public final ImageView tutupBottomDialogChangeMode;

    private BottomSheetBaseDialogChangeModeBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, CheckBox checkboxBottomDialogChangeMode, TextView deskripsiBottomDialogChangeMode, EditText emailEtBottomDialogChangeMode, LinearLayout emailLBottomDialogChangeMode, ImageView iconBottomDialogChangeMode, TextView judulBottomDialogChangeMode, Button lButtonBottomDialogChangeMode, Button rButtonBottomDialogChangeMode, ImageView tutupBottomDialogChangeMode) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.checkboxBottomDialogChangeMode = checkboxBottomDialogChangeMode;
        this.deskripsiBottomDialogChangeMode = deskripsiBottomDialogChangeMode;
        this.emailEtBottomDialogChangeMode = emailEtBottomDialogChangeMode;
        this.emailLBottomDialogChangeMode = emailLBottomDialogChangeMode;
        this.iconBottomDialogChangeMode = iconBottomDialogChangeMode;
        this.judulBottomDialogChangeMode = judulBottomDialogChangeMode;
        this.lButtonBottomDialogChangeMode = lButtonBottomDialogChangeMode;
        this.rButtonBottomDialogChangeMode = rButtonBottomDialogChangeMode;
        this.tutupBottomDialogChangeMode = tutupBottomDialogChangeMode;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetBaseDialogChangeModeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetBaseDialogChangeModeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_base_dialog_change_mode, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetBaseDialogChangeModeBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.checkbox_bottomDialogChangeMode;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, i);
        if (checkBox != null) {
            i = R.id.deskripsi_bottomDialogChangeMode;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = R.id.email_et_bottomDialogChangeMode;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
                if (editText != null) {
                    i = R.id.email_l_bottomDialogChangeMode;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i);
                    if (linearLayout2 != null) {
                        i = R.id.icon_bottomDialogChangeMode;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            i = R.id.judul_bottomDialogChangeMode;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                            if (textView2 != null) {
                                i = R.id.lButton_bottomDialogChangeMode;
                                Button button = (Button) ViewBindings.findChildViewById(rootView, i);
                                if (button != null) {
                                    i = R.id.rButton_bottomDialogChangeMode;
                                    Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                                    if (button2 != null) {
                                        i = R.id.tutup_bottomDialogChangeMode;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                        if (imageView2 != null) {
                                            return new BottomSheetBaseDialogChangeModeBinding(linearLayout, linearLayout, checkBox, textView, editText, linearLayout2, imageView, textView2, button, button2, imageView2);
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
