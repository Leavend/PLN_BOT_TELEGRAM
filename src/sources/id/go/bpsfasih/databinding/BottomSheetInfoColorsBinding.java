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
public final class BottomSheetInfoColorsBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final Button rButtonBottomDialogInfoColors;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialogInfoColors;

    private BottomSheetInfoColorsBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, Button rButtonBottomDialogInfoColors, ImageView tutupButtomDialogInfoColors) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.rButtonBottomDialogInfoColors = rButtonBottomDialogInfoColors;
        this.tutupButtomDialogInfoColors = tutupButtomDialogInfoColors;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetInfoColorsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetInfoColorsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_info_colors, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetInfoColorsBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.rButton_bottomDialogInfoColors;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i);
        if (button != null) {
            i = R.id.tutup_buttomDialogInfoColors;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                return new BottomSheetInfoColorsBinding(linearLayout, linearLayout, button, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
