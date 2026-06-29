package id.go.bpsfasih.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import id.go.bpsfasih.R;

/* loaded from: classes2.dex */
public final class DialogProgressBinding implements ViewBinding {
    public final CardView cardProgress;
    public final ImageView ivLogo;
    public final TextView progressText;
    private final LinearLayout rootView;

    private DialogProgressBinding(LinearLayout rootView, CardView cardProgress, ImageView ivLogo, TextView progressText) {
        this.rootView = rootView;
        this.cardProgress = cardProgress;
        this.ivLogo = ivLogo;
        this.progressText = progressText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogProgressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogProgressBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_progress, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogProgressBinding bind(View rootView) {
        int i = R.id.card_progress;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, i);
        if (cardView != null) {
            i = R.id.iv_logo;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i);
            if (imageView != null) {
                i = R.id.progress_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i);
                if (textView != null) {
                    return new DialogProgressBinding((LinearLayout) rootView, cardView, imageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
