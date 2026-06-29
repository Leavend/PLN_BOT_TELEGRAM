package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class BottomSheetEditTampilanKolomBinding implements ViewBinding {
    public final LinearLayout bottomSheetBaseActivity;
    public final RecyclerView kolomRv;
    public final Button lButtonBottomDialogEditTampilanKolom;
    public final Button rButtonBottomDialogEditTampilanKolom;
    private final LinearLayout rootView;
    public final ImageView tutupButtomDialogFilterAssignment;

    private BottomSheetEditTampilanKolomBinding(LinearLayout rootView, LinearLayout bottomSheetBaseActivity, RecyclerView kolomRv, Button lButtonBottomDialogEditTampilanKolom, Button rButtonBottomDialogEditTampilanKolom, ImageView tutupButtomDialogFilterAssignment) {
        this.rootView = rootView;
        this.bottomSheetBaseActivity = bottomSheetBaseActivity;
        this.kolomRv = kolomRv;
        this.lButtonBottomDialogEditTampilanKolom = lButtonBottomDialogEditTampilanKolom;
        this.rButtonBottomDialogEditTampilanKolom = rButtonBottomDialogEditTampilanKolom;
        this.tutupButtomDialogFilterAssignment = tutupButtomDialogFilterAssignment;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetEditTampilanKolomBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetEditTampilanKolomBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.bottom_sheet_edit_tampilan_kolom, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BottomSheetEditTampilanKolomBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.kolom_rv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, i);
        if (recyclerView != null) {
            i = R.id.lButton_bottomDialogEditTampilanKolom;
            Button button = (Button) ViewBindings.findChildViewById(rootView, i);
            if (button != null) {
                i = R.id.rButton_bottomDialogEditTampilanKolom;
                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i);
                if (button2 != null) {
                    i = R.id.tutup_buttomDialogFilterAssignment;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
                    if (imageView != null) {
                        return new BottomSheetEditTampilanKolomBinding(linearLayout, linearLayout, recyclerView, button, button2, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
