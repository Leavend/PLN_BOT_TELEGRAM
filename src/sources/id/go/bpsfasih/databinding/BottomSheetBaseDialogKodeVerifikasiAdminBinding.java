package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetBaseDialogKodeVerifikasiAdminBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final EditText codeEtBottomDialogKodeVerifikasiAdmin;
    public final TextView judulBottomDialogKodeVerifikasiAdmin;
    public final Button lButtonBottomDialogKodeVerifikasiAdmin;
    public final Button rButtonBottomDialogKodeVerifikasiAdmin;
    private final LinearLayout rootView;
    public final ImageView tutupBottomDialogKodeVerifikasiAdmin;

    private BottomSheetBaseDialogKodeVerifikasiAdminBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, EditText codeEtBottomDialogKodeVerifikasiAdmin, TextView judulBottomDialogKodeVerifikasiAdmin, Button lButtonBottomDialogKodeVerifikasiAdmin, Button rButtonBottomDialogKodeVerifikasiAdmin, ImageView tutupBottomDialogKodeVerifikasiAdmin) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.codeEtBottomDialogKodeVerifikasiAdmin = codeEtBottomDialogKodeVerifikasiAdmin;
        this.judulBottomDialogKodeVerifikasiAdmin = judulBottomDialogKodeVerifikasiAdmin;
        this.lButtonBottomDialogKodeVerifikasiAdmin = lButtonBottomDialogKodeVerifikasiAdmin;
        this.rButtonBottomDialogKodeVerifikasiAdmin = rButtonBottomDialogKodeVerifikasiAdmin;
        this.tutupBottomDialogKodeVerifikasiAdmin = tutupBottomDialogKodeVerifikasiAdmin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetBaseDialogKodeVerifikasiAdminBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetBaseDialogKodeVerifikasiAdminBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_base_dialog_kode_verifikasi_admin, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetBaseDialogKodeVerifikasiAdminBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.code_et_bottomDialogKodeVerifikasiAdmin;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = R.id.judul_bottomDialogKodeVerifikasiAdmin;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = R.id.lButton_bottomDialogKodeVerifikasiAdmin;
                Button button = (Button) ViewBindings.findChildViewById(rootView, i);
                if (button != null) {
                    i = R.id.rButton_bottomDialogKodeVerifikasiAdmin;
                    Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                    if (button2 != null) {
                        i = R.id.tutup_bottomDialogKodeVerifikasiAdmin;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                        if (imageView != null) {
                            return new BottomSheetBaseDialogKodeVerifikasiAdminBinding(linearLayout, linearLayout, editText, textView, button, button2, imageView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
