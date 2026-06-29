package androidx.recyclerview.selection;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class ViewAutoScroller extends AutoScroller {
    private static final float DEFAULT_SCROLL_THRESHOLD_RATIO = 0.125f;
    private static final int MAX_SCROLL_STEP = 70;
    private static final String TAG = "ViewAutoScroller";
    private final ScrollHost mHost;
    private Point mLastLocation;
    private Point mOrigin;
    private boolean mPassedInitialMotionThreshold;
    private final Runnable mRunner;
    private final float mScrollThresholdRatio;

    ViewAutoScroller(ScrollHost scrollHost) {
        this(scrollHost, DEFAULT_SCROLL_THRESHOLD_RATIO);
    }

    ViewAutoScroller(ScrollHost scrollHost, float f) {
        Preconditions.checkArgument(scrollHost != null);
        this.mHost = scrollHost;
        this.mScrollThresholdRatio = f;
        this.mRunner = new Runnable() { // from class: androidx.recyclerview.selection.ViewAutoScroller.1
            @Override // java.lang.Runnable
            public void run() {
                ViewAutoScroller.this.runScroll();
            }
        };
    }

    @Override // androidx.recyclerview.selection.AutoScroller
    public void reset() {
        this.mHost.removeCallback(this.mRunner);
        this.mOrigin = null;
        this.mLastLocation = null;
        this.mPassedInitialMotionThreshold = false;
    }

    @Override // androidx.recyclerview.selection.AutoScroller
    public void scroll(Point point) {
        this.mLastLocation = point;
        if (this.mOrigin == null) {
            this.mOrigin = point;
        }
        this.mHost.runAtNextFrame(this.mRunner);
    }

    void runScroll() {
        int viewHeight;
        int viewHeight2 = (int) (this.mHost.getViewHeight() * this.mScrollThresholdRatio);
        if (this.mLastLocation.y <= viewHeight2) {
            viewHeight = this.mLastLocation.y - viewHeight2;
        } else {
            viewHeight = this.mLastLocation.y >= this.mHost.getViewHeight() - viewHeight2 ? (this.mLastLocation.y - this.mHost.getViewHeight()) + viewHeight2 : 0;
        }
        if (viewHeight == 0) {
            return;
        }
        if (this.mPassedInitialMotionThreshold || aboveMotionThreshold(this.mLastLocation)) {
            this.mPassedInitialMotionThreshold = true;
            if (viewHeight <= viewHeight2) {
                viewHeight2 = viewHeight;
            }
            this.mHost.scrollBy(computeScrollDistance(viewHeight2));
            this.mHost.removeCallback(this.mRunner);
            this.mHost.runAtNextFrame(this.mRunner);
        }
    }

    private boolean aboveMotionThreshold(Point point) {
        float viewHeight = this.mHost.getViewHeight();
        float f = this.mScrollThresholdRatio;
        return Math.abs(this.mOrigin.y - point.y) >= ((int) ((viewHeight * f) * (f * 2.0f)));
    }

    int computeScrollDistance(int i) {
        int viewHeight = (int) (this.mHost.getViewHeight() * this.mScrollThresholdRatio);
        int iSignum = (int) Math.signum(i);
        int iSmoothOutOfBoundsRatio = (int) (iSignum * MAX_SCROLL_STEP * smoothOutOfBoundsRatio(Math.min(1.0f, Math.abs(i) / viewHeight)));
        return iSmoothOutOfBoundsRatio != 0 ? iSmoothOutOfBoundsRatio : iSignum;
    }

    private float smoothOutOfBoundsRatio(float f) {
        return (float) Math.pow(f, 10.0d);
    }

    static abstract class ScrollHost {
        abstract int getViewHeight();

        abstract void removeCallback(Runnable runnable);

        abstract void runAtNextFrame(Runnable runnable);

        abstract void scrollBy(int i);

        ScrollHost() {
        }
    }

    static ScrollHost createScrollHost(RecyclerView recyclerView) {
        return new RuntimeHost(recyclerView);
    }

    private static final class RuntimeHost extends ScrollHost {
        private final RecyclerView mView;

        RuntimeHost(RecyclerView recyclerView) {
            this.mView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void runAtNextFrame(Runnable runnable) {
            ViewCompat.postOnAnimation(this.mView, runnable);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void removeCallback(Runnable runnable) {
            this.mView.removeCallbacks(runnable);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void scrollBy(int i) {
            this.mView.scrollBy(0, i);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        int getViewHeight() {
            Rect rect = new Rect();
            this.mView.getGlobalVisibleRect(rect);
            return rect.height();
        }
    }
}
