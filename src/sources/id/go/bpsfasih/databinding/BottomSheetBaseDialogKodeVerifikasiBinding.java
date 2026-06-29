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
public final class BottomSheetBaseDialogKodeVerifikasiBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final EditText codeEt;
    public final TextView codeTv;
    public final TextView deskripsiBottomDialogKodeVerifikasi1;
    public final TextView deskripsiBottomDialogKodeVerifikasi2;
    public final TextView judulBottomDialogKodeVerifikasi;
    public final Button lButtonBottomDialogKodeVerifikasi;
    public final Button rButtonBottomDialogKodeVerifikasi;
    private final LinearLayout rootView;
    public final ImageView tutupBottomDialogKodeVerifikasi;

    private BottomSheetBaseDialogKodeVerifikasiBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, EditText codeEt, TextView codeTv, TextView deskripsiBottomDialogKodeVerifikasi1, TextView deskripsiBottomDialogKodeVerifikasi2, TextView judulBottomDialogKodeVerifikasi, Button lButtonBottomDialogKodeVerifikasi, Button rButtonBottomDialogKodeVerifikasi, ImageView tutupBottomDialogKodeVerifikasi) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.codeEt = codeEt;
        this.codeTv = codeTv;
        this.deskripsiBottomDialogKodeVerifikasi1 = deskripsiBottomDialogKodeVerifikasi1;
        this.deskripsiBottomDialogKodeVerifikasi2 = deskripsiBottomDialogKodeVerifikasi2;
        this.judulBottomDialogKodeVerifikasi = judulBottomDialogKodeVerifikasi;
        this.lButtonBottomDialogKodeVerifikasi = lButtonBottomDialogKodeVerifikasi;
        this.rButtonBottomDialogKodeVerifikasi = rButtonBottomDialogKodeVerifikasi;
        this.tutupBottomDialogKodeVerifikasi = tutupBottomDialogKodeVerifikasi;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetBaseDialogKodeVerifikasiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetBaseDialogKodeVerifikasiBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_base_dialog_kode_verifikasi, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetBaseDialogKodeVerifikasiBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.code_et;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i);
        if (editText != null) {
            i = R.id.code_tv;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
            if (textView != null) {
                i = R.id.deskripsi_bottomDialogKodeVerifikasi_1;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = R.id.deskripsi_bottomDialogKodeVerifikasi_2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i);
                    if (textView3 != null) {
                        i = R.id.judul_bottomDialogKodeVerifikasi;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i);
                        if (textView4 != null) {
                            i = R.id.lButton_bottomDialogKodeVerifikasi;
                            Button button = (Button) ViewBindings.findChildViewById(rootView, i);
                            if (button != null) {
                                i = R.id.rButton_bottomDialogKodeVerifikasi;
                                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                                if (button2 != null) {
                                    i = R.id.tutup_bottomDialogKodeVerifikasi;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                                    if (imageView != null) {
                                        return new BottomSheetBaseDialogKodeVerifikasiBinding(linearLayout, linearLayout, editText, textView, textView2, textView3, textView4, button, button2, imageView);
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
