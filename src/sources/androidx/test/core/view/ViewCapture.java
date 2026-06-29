package androidx.test.core.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
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

/* compiled from: ViewCapture.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\u0007\u001a\u001a\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0002\u001a\"\u0010\n\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0000\u001a*\u0010\f\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0002\u001a\"\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0002\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0002¨\u0006\u0013"}, d2 = {"captureToBitmap", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroid/graphics/Bitmap;", "Landroid/view/View;", "forceRedraw", "Ljava/lang/Void;", "generateBitmap", "", "bitmapFuture", "Landroidx/concurrent/futures/ResolvableFuture;", "generateBitmapFromDraw", "destBitmap", "generateBitmapFromPixelCopy", "window", "Landroid/view/Window;", "generateBitmapFromSurfaceViewPixelCopy", "Landroid/view/SurfaceView;", "getActivity", "Landroid/app/Activity;", "androidx.test.core"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class ViewCapture {
    public static final ListenableFuture<Bitmap> captureToBitmap(final View $this$captureToBitmap) {
        Intrinsics.checkNotNullParameter($this$captureToBitmap, "<this>");
        final ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        Intrinsics.checkNotNullExpressionValue(resolvableFutureCreate, "create()");
        final HandlerExecutor handlerExecutor = new HandlerExecutor(new Handler(Looper.getMainLooper()));
        if (!HardwareRendererCompat.isDrawingEnabled()) {
            HardwareRendererCompat.setDrawingEnabled(true);
            resolvableFutureCreate.addListener(new Runnable() { // from class: androidx.test.core.view.ViewCapture.captureToBitmap.1
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareRendererCompat.setDrawingEnabled(false);
                }
            }, handlerExecutor);
        }
        handlerExecutor.execute(new Runnable() { // from class: androidx.test.core.view.ViewCapture.captureToBitmap.2
            @Override // java.lang.Runnable
            public final void run() {
                ListenableFuture<Void> listenableFutureForceRedraw = ViewCapture.forceRedraw($this$captureToBitmap);
                final View view = $this$captureToBitmap;
                final ResolvableFuture<Bitmap> resolvableFuture = resolvableFutureCreate;
                listenableFutureForceRedraw.addListener(new Runnable() { // from class: androidx.test.core.view.ViewCapture.captureToBitmap.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ViewCapture.generateBitmap(view, resolvableFuture);
                    }
                }, handlerExecutor);
            }
        });
        return resolvableFutureCreate;
    }

    public static final ListenableFuture<Void> forceRedraw(final View $this$forceRedraw) {
        Intrinsics.checkNotNullParameter($this$forceRedraw, "<this>");
        final ResolvableFuture resolvableFutureCreate = ResolvableFuture.create();
        Intrinsics.checkNotNullExpressionValue(resolvableFutureCreate, "create()");
        if (Build.VERSION.SDK_INT >= 29 && $this$forceRedraw.isHardwareAccelerated()) {
            $this$forceRedraw.getViewTreeObserver().registerFrameCommitCallback(new Runnable() { // from class: androidx.test.core.view.ViewCapture.forceRedraw.1
                @Override // java.lang.Runnable
                public final void run() {
                    resolvableFutureCreate.set(null);
                }
            });
        } else {
            $this$forceRedraw.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() { // from class: androidx.test.core.view.ViewCapture.forceRedraw.2
                private boolean handled;

                public final boolean getHandled() {
                    return this.handled;
                }

                public final void setHandled(boolean z) {
                    this.handled = z;
                }

                @Override // android.view.ViewTreeObserver.OnDrawListener
                public void onDraw() {
                    if (this.handled) {
                        return;
                    }
                    this.handled = true;
                    resolvableFutureCreate.set(null);
                    Handler handler = new Handler(Looper.getMainLooper());
                    final View view = $this$forceRedraw;
                    handler.post(new Runnable() { // from class: androidx.test.core.view.ViewCapture$forceRedraw$2$onDraw$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            view.getViewTreeObserver().removeOnDrawListener(this);
                        }
                    });
                }
            });
        }
        $this$forceRedraw.invalidate();
        return resolvableFutureCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void generateBitmap(View $this$generateBitmap, ResolvableFuture<Bitmap> bitmapFuture) {
        if (bitmapFuture.isCancelled()) {
            return;
        }
        Bitmap destBitmap = Bitmap.createBitmap($this$generateBitmap.getWidth(), $this$generateBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (Build.VERSION.SDK_INT < 26) {
            Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
            generateBitmapFromDraw($this$generateBitmap, destBitmap, bitmapFuture);
            return;
        }
        if ($this$generateBitmap instanceof SurfaceView) {
            Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
            generateBitmapFromSurfaceViewPixelCopy((SurfaceView) $this$generateBitmap, destBitmap, bitmapFuture);
            return;
        }
        Activity activity = getActivity($this$generateBitmap);
        Window window = activity != null ? activity.getWindow() : null;
        if (window != null) {
            Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
            generateBitmapFromPixelCopy($this$generateBitmap, window, destBitmap, bitmapFuture);
        } else {
            Log.i("View.captureToImage", "Could not find window for view. Falling back to View#draw instead of PixelCopy");
            Intrinsics.checkNotNullExpressionValue(destBitmap, "destBitmap");
            generateBitmapFromDraw($this$generateBitmap, destBitmap, bitmapFuture);
        }
    }

    private static final void generateBitmapFromSurfaceViewPixelCopy(SurfaceView $this$generateBitmapFromSurfaceViewPixelCopy, final Bitmap destBitmap, final ResolvableFuture<Bitmap> bitmapFuture) {
        PixelCopy.request($this$generateBitmapFromSurfaceViewPixelCopy, (Rect) null, destBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: androidx.test.core.view.ViewCapture$generateBitmapFromSurfaceViewPixelCopy$onCopyFinished$1
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
        }, $this$generateBitmapFromSurfaceViewPixelCopy.getHandler());
    }

    public static final void generateBitmapFromDraw(View $this$generateBitmapFromDraw, Bitmap destBitmap, ResolvableFuture<Bitmap> bitmapFuture) {
        Intrinsics.checkNotNullParameter($this$generateBitmapFromDraw, "<this>");
        Intrinsics.checkNotNullParameter(destBitmap, "destBitmap");
        Intrinsics.checkNotNullParameter(bitmapFuture, "bitmapFuture");
        destBitmap.setDensity($this$generateBitmapFromDraw.getResources().getDisplayMetrics().densityDpi);
        $this$generateBitmapFromDraw.computeScroll();
        Canvas canvas = new Canvas(destBitmap);
        canvas.translate(-$this$generateBitmapFromDraw.getScrollX(), -$this$generateBitmapFromDraw.getScrollY());
        $this$generateBitmapFromDraw.draw(canvas);
        bitmapFuture.set(destBitmap);
    }

    private static final Activity getActivity$getActivity(Context $this$getActivity_u24getActivity) {
        if ($this$getActivity_u24getActivity instanceof Activity) {
            return (Activity) $this$getActivity_u24getActivity;
        }
        if (!($this$getActivity_u24getActivity instanceof ContextWrapper)) {
            return null;
        }
        Context baseContext = ((ContextWrapper) $this$getActivity_u24getActivity).getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "this.baseContext");
        return getActivity$getActivity(baseContext);
    }

    private static final Activity getActivity(View $this$getActivity) {
        Context context = $this$getActivity.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return getActivity$getActivity(context);
    }

    private static final void generateBitmapFromPixelCopy(View $this$generateBitmapFromPixelCopy, Window window, Bitmap destBitmap, ResolvableFuture<Bitmap> bitmapFuture) {
        int[] iArr = {0, 0};
        $this$generateBitmapFromPixelCopy.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        WindowCapture.generateBitmapFromPixelCopy(window, new Rect(i, i2, $this$generateBitmapFromPixelCopy.getWidth() + i, $this$generateBitmapFromPixelCopy.getHeight() + i2), destBitmap, bitmapFuture);
    }
}
