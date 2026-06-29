package id.go.bpsfasih;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomProgressBar.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0006\u0010\u0011\u001a\u00020\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lid/go/bpsfasih/CustomProgressBar;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", Constants.ScionAnalytics.PARAM_LABEL, "", "(Landroid/content/Context;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "progressText", "Landroid/widget/TextView;", "rotate", "Landroid/view/animation/RotateAnimation;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showLoading", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CustomProgressBar extends Dialog {
    public static final int $stable = 8;
    private final String label;
    private TextView progressText;
    private RotateAnimation rotate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomProgressBar(Context context, String label) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(label, "label");
        this.label = label;
    }

    public /* synthetic */ CustomProgressBar(Context context, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "" : str);
    }

    public final String getLabel() {
        return this.label;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        setContentView(R.layout.dialog_progress);
        CardView cardView = (CardView) findViewById(R.id.card_progress);
        TextView textView = (TextView) findViewById(R.id.progress_text);
        this.progressText = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        String str = this.label;
        if (!Intrinsics.areEqual(str, "")) {
            TextView textView2 = this.progressText;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.progressText;
            if (textView3 != null) {
                textView3.setText(str);
            }
        }
        cardView.setPreventCornerOverlap(true);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.rotate = rotateAnimation;
        rotateAnimation.setDuration(1000L);
        RotateAnimation rotateAnimation2 = this.rotate;
        if (rotateAnimation2 != null) {
            rotateAnimation2.setRepeatCount(-1);
        }
        ((ImageView) findViewById(R.id.iv_logo)).startAnimation(this.rotate);
    }

    public final void showLoading() {
        Object objM6852constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            CustomProgressBar customProgressBar = this;
            show();
            ((ImageView) findViewById(R.id.iv_logo)).startAnimation(this.rotate);
            objM6852constructorimpl = Result.m6852constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6852constructorimpl = Result.m6852constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM6855exceptionOrNullimpl = Result.m6855exceptionOrNullimpl(objM6852constructorimpl);
        if (thM6855exceptionOrNullimpl != null) {
            Log.e("CustomProgressBar", "Failed to show loading dialog", thM6855exceptionOrNullimpl);
        }
    }
}
