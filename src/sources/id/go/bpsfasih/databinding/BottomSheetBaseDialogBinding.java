package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetBaseDialogBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final TextView deskripsiBottomDialog;
    public final ImageView iconBottomDialog;
    public final TextView judulBottomDialog;
    public final Button lButtonBottomDialog;
    public final Button rButtonBottomDialog;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialog;

    private BottomSheetBaseDialogBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, TextView deskripsiBottomDialog, ImageView iconBottomDialog, TextView judulBottomDialog, Button lButtonBottomDialog, Button rButtonBottomDialog, ImageView tutupButtomDialog) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.deskripsiBottomDialog = deskripsiBottomDialog;
        this.iconBottomDialog = iconBottomDialog;
        this.judulBottomDialog = judulBottomDialog;
        this.lButtonBottomDialog = lButtonBottomDialog;
        this.rButtonBottomDialog = rButtonBottomDialog;
        this.tutupButtomDialog = tutupButtomDialog;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetBaseDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetBaseDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_base_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetBaseDialogBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.deskripsi_bottomDialog;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
        if (textView != null) {
            i = R.id.icon_bottomDialog;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = R.id.judul_bottomDialog;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView2 != null) {
                    i = R.id.lButton_bottomDialog;
                    Button button = (Button) ViewBindings.findChildViewById(rootView, i);
                    if (button != null) {
                        i = R.id.rButton_bottomDialog;
                        Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                        if (button2 != null) {
                            i = R.id.tutup_buttomDialog;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i);
                            if (imageView2 != null) {
                                return new BottomSheetBaseDialogBinding(linearLayout, linearLayout, textView, imageView, textView2, button, button2, imageView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
