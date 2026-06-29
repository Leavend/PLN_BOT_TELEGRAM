package androidx.test.espresso.screenshot;

import android.graphics.Bitmap;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.core.view.ViewCapture;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/* compiled from: ViewInteractionCaptureExt.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0017R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/test/espresso/screenshot/ImageCaptureViewAction;", "Landroidx/test/espresso/ViewAction;", "bitmapFuture", "Lcom/google/common/util/concurrent/SettableFuture;", "Landroid/graphics/Bitmap;", "(Lcom/google/common/util/concurrent/SettableFuture;)V", "getConstraints", "Lorg/hamcrest/Matcher;", "Landroid/view/View;", "getDescription", "", "perform", "", "uiController", "Landroidx/test/espresso/UiController;", "view", "androidx.test.espresso.screenshot"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
final class ImageCaptureViewAction implements ViewAction {
    private final SettableFuture<Bitmap> bitmapFuture;

    public ImageCaptureViewAction(SettableFuture<Bitmap> bitmapFuture) {
        Intrinsics.checkNotNullParameter(bitmapFuture, "bitmapFuture");
        this.bitmapFuture = bitmapFuture;
    }

    @Override // androidx.test.espresso.ViewAction
    public Matcher<View> getConstraints() {
        Matcher<View> matcherAny = Matchers.any(View.class);
        Intrinsics.checkNotNullExpressionValue(matcherAny, "any(View::class.java)");
        return matcherAny;
    }

    @Override // androidx.test.espresso.ViewAction
    public String getDescription() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.ROOT, "capture view to image", Arrays.copyOf(new Object[0], 0));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // androidx.test.espresso.ViewAction
    public void perform(UiController uiController, View view) {
        Intrinsics.checkNotNullParameter(uiController, "uiController");
        Intrinsics.checkNotNullParameter(view, "view");
        uiController.loopMainThreadUntilIdle();
        this.bitmapFuture.setFuture(ViewCapture.captureToBitmap(view));
    }
}
