package androidx.test.core.app;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.core.internal.os.HandlerExecutor;
import androidx.test.core.view.ViewCapture;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.platform.graphics.HardwareRendererCompat;
import androidx.test.platform.view.inspector.WindowInspectorCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceCapture.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\b\u0010\b\u001a\u00020\tH\u0007\u001a\b\u0010\n\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"canTakeScreenshot", "", "forceRedrawGlobalWindowViews", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "Ljava/lang/Void;", "mainExecutor", "Ljava/util/concurrent/Executor;", "takeScreenshot", "Landroid/graphics/Bitmap;", "takeScreenshotNoSync", "androidx.test.core"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class DeviceCapture {
    public static final boolean canTakeScreenshot() {
        return (InstrumentationRegistry.getInstrumentation().getUiAutomation() == null || Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) ? false : true;
    }

    public static final Bitmap takeScreenshot() throws RuntimeException {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        return takeScreenshotNoSync();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Bitmap takeScreenshotNoSync() throws RuntimeException {
        Checks.checkState(canTakeScreenshot());
        final ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        Intrinsics.checkNotNullExpressionValue(resolvableFutureCreate, "create()");
        HandlerExecutor handlerExecutor = new HandlerExecutor(new Handler(Looper.getMainLooper()));
        final UiAutomation uiAutomation = InstrumentationRegistry.getInstrumentation().getUiAutomation();
        if (uiAutomation == null) {
            throw new RuntimeException("uiautomation is null");
        }
        if (!HardwareRendererCompat.isDrawingEnabled()) {
            HardwareRendererCompat.setDrawingEnabled(true);
            resolvableFutureCreate.addListener(new Runnable() { // from class: androidx.test.core.app.DeviceCapture.takeScreenshotNoSync.1
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareRendererCompat.setDrawingEnabled(false);
                }
            }, handlerExecutor);
        }
        try {
            forceRedrawGlobalWindowViews(handlerExecutor).get(5L, TimeUnit.SECONDS);
        } catch (Exception e) {
            Log.w("takeScreenshot", "force redraw failed. Proceeding with screenshot", e);
        }
        handlerExecutor.execute(new Runnable() { // from class: androidx.test.core.app.DeviceCapture.takeScreenshotNoSync.2
            @Override // java.lang.Runnable
            public final void run() {
                Choreographer choreographer = Choreographer.getInstance();
                final UiAutomation uiAutomation2 = uiAutomation;
                final ResolvableFuture<Bitmap> resolvableFuture = resolvableFutureCreate;
                choreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.test.core.app.DeviceCapture.takeScreenshotNoSync.2.1
                    @Override // android.view.Choreographer.FrameCallback
                    public final void doFrame(long it) {
                        Bitmap bitmapTakeScreenshot = uiAutomation2.takeScreenshot();
                        if (bitmapTakeScreenshot == null) {
                            resolvableFuture.setException(new RuntimeException("uiAutomation.takeScreenshot returned null"));
                        } else {
                            resolvableFuture.set(bitmapTakeScreenshot);
                        }
                    }
                });
            }
        });
        try {
            V v = resolvableFutureCreate.get(5L, TimeUnit.SECONDS);
            Intrinsics.checkNotNullExpressionValue(v, "bitmapFuture.get(5, TimeUnit.SECONDS)");
            return (Bitmap) v;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Uiautomation.takeScreenshot was interrupted");
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                Throwable cause = e2.getCause();
                Intrinsics.checkNotNull(cause, "null cannot be cast to non-null type java.lang.RuntimeException");
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException("UiAutomation.takeScreenshot failed with unrecognized exception", e2.getCause());
        } catch (TimeoutException e3) {
            throw new RuntimeException("Uiautomation.takeScreenshot failed to complete in 5 seconds", e3);
        }
    }

    private static final ListenableFuture<List<Void>> forceRedrawGlobalWindowViews(final Executor mainExecutor) {
        final ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        Intrinsics.checkNotNullExpressionValue(resolvableFutureCreate, "create()");
        mainExecutor.execute(new Runnable() { // from class: androidx.test.core.app.DeviceCapture.forceRedrawGlobalWindowViews.1
            @Override // java.lang.Runnable
            public final void run() throws WindowInspectorCompat.ViewRetrievalException {
                List<View> globalWindowViews = WindowInspectorCompat.getGlobalWindowViews();
                ArrayList arrayList = new ArrayList();
                for (View view : globalWindowViews) {
                    Intrinsics.checkNotNullExpressionValue(view, "view");
                    arrayList.add(ViewCapture.forceRedraw(view));
                }
                Log.d("takeScreenshot", "Found " + globalWindowViews.size() + " global views to redraw");
                resolvableFutureCreate.setFuture(new ListFuture(arrayList, true, mainExecutor));
            }
        });
        return resolvableFutureCreate;
    }
}
