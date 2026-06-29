package androidx.test.espresso.screenshot;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.espresso.EspressoException;
import kotlin.Metadata;

/* compiled from: ViewInteractionCaptureExt.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B!\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Landroidx/test/espresso/screenshot/CaptureImageException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "Landroidx/test/espresso/EspressoException;", "message", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/Exception;)V", "androidx.test.espresso.screenshot"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
final class CaptureImageException extends RuntimeException implements EspressoException {
    public CaptureImageException(String str, Exception exc) {
        super(str, exc);
    }
}
