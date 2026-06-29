package androidx.test.espresso.screenshot;

import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewInteractionCaptureExt.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"captureToBitmap", "Landroid/graphics/Bitmap;", "Landroidx/test/espresso/ViewInteraction;", "androidx.test.espresso.screenshot"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ViewInteractionCapture {
    public static final Bitmap captureToBitmap(ViewInteraction viewInteraction) {
        Intrinsics.checkNotNullParameter(viewInteraction, "<this>");
        SettableFuture settableFutureCreate = SettableFuture.create();
        Intrinsics.checkNotNullExpressionValue(settableFutureCreate, "create<Bitmap>()");
        viewInteraction.perform(new ImageCaptureViewAction(settableFutureCreate));
        try {
            Object obj = settableFutureCreate.get(IdlingPolicies.getMasterIdlingPolicy().getIdleTimeout(), IdlingPolicies.getMasterIdlingPolicy().getIdleTimeoutUnit());
            Intrinsics.checkNotNullExpressionValue(obj, "bitmapFuture[\n      Idli…Policy().idleTimeoutUnit]");
            return (Bitmap) obj;
        } catch (InterruptedException e) {
            throw new CaptureImageException("failed to capture image", e);
        } catch (ExecutionException e2) {
            throw new CaptureImageException("failed to capture image", e2);
        } catch (TimeoutException e3) {
            throw new CaptureImageException("failed to capture image", e3);
        }
    }
}
