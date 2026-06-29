package androidx.test.core.view;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.core.internal.os.HandlerExecutor;
import androidx.test.platform.graphics.HardwareRendererCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: WindowCapture.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0000\u001a.\u0010\n\u001a\u00020\u0007*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0000¨\u0006\f"}, d2 = {"captureRegionToBitmap", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroid/graphics/Bitmap;", "Landroid/view/Window;", "boundsInWindow", "Landroid/graphics/Rect;", "generateBitmap", "", "bitmapFuture", "Landroidx/concurrent/futures/ResolvableFuture;", "generateBitmapFromPixelCopy", "destBitmap", "androidx.test.core"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class WindowCapture {
    public static /* synthetic */ ListenableFuture captureRegionToBitmap$default(Window window, Rect rect, int i, Object obj) {
        if ((i & 1) != 0) {
            rect = null;
        }
        return captureRegionToBitmap(window, rect);
    }

    public static final ListenableFuture<Bitmap> captureRegionToBitmap(final Window $this$captureRegionToBitmap, final Rect boundsInWindow) {
        Intrinsics.checkNotNullParameter($this$captureRegionToBitmap, "<this>");
        final ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        Intrinsics.checkNotNullExpressionValue(resolvableFutureCreate, "create()");
        final HandlerExecutor handlerExecutor = new HandlerExecutor(new Handler(Looper.getMainLooper()));
        if (!HardwareRendererCompat.isDrawingEnabled()) {
            HardwareRendererCompat.setDrawingEnabled(true);
            resolvableFutureCreate.addListener(new Runnable() { // from class: androidx.test.core.view.WindowCapture.captureRegionToBitmap.1
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareRendererCompat.setDrawingEnabled(false);
                }
            }, handlerExecutor);
        }
        handlerExecutor.execute(new Runnable() { // from class: androidx.test.core.view.WindowCapture.captureRegionToBitmap.2
            @Override // java.lang.Runnable
            public final void run() {
                View decorView = $this$captureRegionToBitmap.getDecorView();
                Intrinsics.checkNotNullExpressionValue(decorView, "decorView");
                ListenableFuture<Void> listenableFutureForceRedraw = ViewCapture.forceRedraw(decorView);
                final Window window = $this$captureRegionToBitmap;
                final Rect rect = boundsInWindow;
                final ResolvableFuture<Bitmap> resolvableFuture = resolvableFutureCreate;
                listenableFutureForceRedraw.addListener(new Runnable() { // from class: androidx.test.core.view.WindowCapture.captureRegionToBitmap.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        WindowCapture.generateBitmap(window, rect, resolvableFuture);
                    }
                }, handlerExecutor);
            }
        });
        return resolvableFutureCreate;
    }

    public static /* synthetic */ void generateBitmap$default(Window window, Rect rect, ResolvableFuture resolvableFuture, int i, Object obj) {
        if ((i & 1) != 0) {
            rect = null;
        }
        generateBitmap(window, rect, resolvableFuture);
    }

    public static final void generateBitmap(Window $this$generateBitmap, Rect boundsInWindow, ResolvableFuture<Bitmap> bitmapFuture) {
        Intrinsics.checkNotNullParameter($this$generateBitmap, "<this>");
        Intrinsics.checkNotNullParameter(bitmapFuture, "bitmapFuture");
        Bitmap destBitmap = Bitmap.createBitmap(boundsInWindow != null ? boundsInWindow.width() : $this$generateBitmap.getDecorView().getWidth(), boundsInWindow != null ? boundsInWindow.height() : $this$generateBitmap.getDecorView().getHeight(), Bitmap.Config.ARGB_8888);
        if (Build.VERSION.SDK_INT < 26) {
            View decorView = $this$generateBitmap.getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "decorView");
            Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
            ViewCapture.generateBitmapFromDraw(decorView, destBitmap, bitmapFuture);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
        generateBitmapFromPixelCopy($this$generateBitmap, boundsInWindow, destBitmap, bitmapFuture);
    }

    public static /* synthetic */ void generateBitmapFromPixelCopy$default(Window window, Rect rect, Bitmap bitmap, ResolvableFuture resolvableFuture, int i, Object obj) {
        if ((i & 1) != 0) {
            rect = null;
        }
        generateBitmapFromPixelCopy(window, rect, bitmap, resolvableFuture);
    }

    public static final void generateBitmapFromPixelCopy(Window $this$generateBitmapFromPixelCopy, Rect boundsInWindow, final Bitmap destBitmap, final ResolvableFuture<Bitmap> bitmapFuture) {
        Intrinsics.checkNotNullParameter($this$generateBitmapFromPixelCopy, "<this>");
        Intrinsics.checkNotNullParameter(destBitmap, "destBitmap");
        Intrinsics.checkNotNullParameter(bitmapFuture, "bitmapFuture");
        PixelCopy.request($this$generateBitmapFromPixelCopy, boundsInWindow, destBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: androidx.test.core.view.WindowCapture$generateBitmapFromPixelCopy$onCopyFinished$1
            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
            public final void onPixelCopyFinished(int result) {
                if (result == 0) {
                    bitmapFuture.set(destBitmap);
                    return;
                }
                ResolvableFuture<Bitmap> resolvableFuture = bitmapFuture;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("PixelCopy failed: %d", Arrays.copyOf(new Object[]{Integer.valueOf(result)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                resolvableFuture.setException(new RuntimeException(str));
            }
        }, new Handler(Looper.getMainLooper()));
    }
}
