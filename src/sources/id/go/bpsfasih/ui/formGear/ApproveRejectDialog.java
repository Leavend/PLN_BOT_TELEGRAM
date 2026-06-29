package id.go.bpsfasih.ui.formGear;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApproveRejectDialog.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nJ\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/ui/formGear/ApproveRejectDialog;", "Landroid/app/Dialog;", "data", "", "isError", "", "context", "Landroid/content/Context;", "(IZLandroid/content/Context;)V", "approveClick", "Landroid/view/View$OnClickListener;", "getData", "()I", "setData", "(I)V", "()Z", "rejectClick", "unapproveClick", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setApprove", "view", "setReject", "setUnApprove", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ApproveRejectDialog extends Dialog {
    public static final int $stable = 8;
    private View.OnClickListener approveClick;
    private int data;
    private final boolean isError;
    private View.OnClickListener rejectClick;
    private View.OnClickListener unapproveClick;

    /* renamed from: isError, reason: from getter */
    public final boolean getIsError() {
        return this.isError;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApproveRejectDialog(int i, boolean z, Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.isError = z;
        this.data = i;
    }

    public final int getData() {
        return this.data;
    }

    public final void setData(int i) {
        this.data = i;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.dialog_approve_reject);
        int i = this.data;
        if (i == 0) {
            ((TextView) findViewById(R.id.reject)).setVisibility(8);
            ((TextView) findViewById(R.id.approve)).setVisibility(8);
            ((TextView) findViewById(R.id.unapprove)).setVisibility(8);
        } else if (i == 1) {
            ((TextView) findViewById(R.id.unapprove)).setVisibility(8);
        } else if (i == 2) {
            ((TextView) findViewById(R.id.reject)).setVisibility(8);
            ((TextView) findViewById(R.id.approve)).setVisibility(8);
        }
        if (this.isError) {
            ((TextView) findViewById(R.id.approve)).setVisibility(8);
        }
        ((TextView) findViewById(R.id.reject)).setOnClickListener(this.rejectClick);
        ((TextView) findViewById(R.id.approve)).setOnClickListener(this.approveClick);
        ((TextView) findViewById(R.id.unapprove)).setOnClickListener(this.unapproveClick);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public final void setReject(View.OnClickListener view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.rejectClick = view;
    }

    public final void setApprove(View.OnClickListener view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.approveClick = view;
    }

    public final void setUnApprove(View.OnClickListener view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.unapproveClick = view;
    }
}
