package androidx.recyclerview.selection;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class GestureSelectionHelper implements RecyclerView.OnItemTouchListener, Resettable {
    private static final String TAG = "GestureSelectionHelper";
    private final OperationMonitor mLock;
    private final AutoScroller mScroller;
    private final SelectionTracker<?> mSelectionMgr;
    private final SelectionTracker.SelectionPredicate<?> mSelectionPredicate;
    private boolean mStarted = false;
    private final ViewDelegate mView;

    static float getInboundY(float f, float f2) {
        if (f2 < 0.0f) {
            return 0.0f;
        }
        return f2 > f ? f : f2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    GestureSelectionHelper(SelectionTracker<?> selectionTracker, SelectionTracker.SelectionPredicate<?> selectionPredicate, ViewDelegate viewDelegate, AutoScroller autoScroller, OperationMonitor operationMonitor) {
        Preconditions.checkArgument(selectionTracker != null);
        Preconditions.checkArgument(selectionPredicate != null);
        Preconditions.checkArgument(viewDelegate != null);
        Preconditions.checkArgument(autoScroller != null);
        Preconditions.checkArgument(operationMonitor != null);
        this.mSelectionMgr = selectionTracker;
        this.mSelectionPredicate = selectionPredicate;
        this.mView = viewDelegate;
        this.mScroller = autoScroller;
        this.mLock = operationMonitor;
    }

    void start() {
        if (this.mStarted) {
            return;
        }
        this.mStarted = true;
        this.mLock.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mStarted) {
            onTouchEvent(recyclerView, motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 2) {
            return this.mStarted;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mStarted) {
            if (!this.mSelectionMgr.isRangeActive()) {
                Log.e(TAG, "Internal state of GestureSelectionHelper out of sync w/ SelectionTracker (isRangeActive is false). Ignoring event and resetting state.");
                endSelection();
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1) {
                handleUpEvent();
            } else {
                if (actionMasked != 2) {
                    return;
                }
                handleMoveEvent(motionEvent);
            }
        }
    }

    private void handleUpEvent() {
        this.mSelectionMgr.mergeProvisionalSelection();
        endSelection();
    }

    @Override // androidx.recyclerview.selection.Resettable
    public void reset() {
        this.mStarted = false;
        this.mScroller.reset();
    }

    @Override // androidx.recyclerview.selection.Resettable
    public boolean isResetRequired() {
        return this.mStarted;
    }

    private void endSelection() {
        this.mStarted = false;
        this.mScroller.reset();
        this.mLock.stop();
    }

    private void handleMoveEvent(MotionEvent motionEvent) {
        if (!this.mStarted) {
            Log.e(TAG, "Received event while not started.");
        }
        int lastGlidedItemPosition = this.mView.getLastGlidedItemPosition(motionEvent);
        if (this.mSelectionPredicate.canSetStateAtPosition(lastGlidedItemPosition, true)) {
            extendSelection(lastGlidedItemPosition);
        }
        this.mScroller.scroll(MotionEvents.getOrigin(motionEvent));
    }

    private void extendSelection(int i) {
        this.mSelectionMgr.extendProvisionalRange(i);
    }

    static GestureSelectionHelper create(SelectionTracker<?> selectionTracker, SelectionTracker.SelectionPredicate<?> selectionPredicate, RecyclerView recyclerView, AutoScroller autoScroller, OperationMonitor operationMonitor) {
        return new GestureSelectionHelper(selectionTracker, selectionPredicate, new RecyclerViewDelegate(recyclerView), autoScroller, operationMonitor);
    }

    static abstract class ViewDelegate {
        abstract int getHeight();

        abstract int getItemUnder(MotionEvent motionEvent);

        abstract int getLastGlidedItemPosition(MotionEvent motionEvent);

        ViewDelegate() {
        }
    }

    static final class RecyclerViewDelegate extends ViewDelegate {
        private final RecyclerView mRecyclerView;

        RecyclerViewDelegate(RecyclerView recyclerView) {
            Preconditions.checkArgument(recyclerView != null);
            this.mRecyclerView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getHeight() {
            return this.mRecyclerView.getHeight();
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getItemUnder(MotionEvent motionEvent) {
            View viewFindChildViewUnder = this.mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (viewFindChildViewUnder != null) {
                return this.mRecyclerView.getChildAdapterPosition(viewFindChildViewUnder);
            }
            return -1;
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getLastGlidedItemPosition(MotionEvent motionEvent) {
            View childAt = this.mRecyclerView.getLayoutManager().getChildAt(this.mRecyclerView.getLayoutManager().getChildCount() - 1);
            boolean zIsPastLastItem = isPastLastItem(childAt.getTop(), childAt.getLeft(), childAt.getRight(), motionEvent, ViewCompat.getLayoutDirection(this.mRecyclerView));
            float inboundY = GestureSelectionHelper.getInboundY(this.mRecyclerView.getHeight(), motionEvent.getY());
            if (zIsPastLastItem) {
                return this.mRecyclerView.getAdapter().getItemCount() - 1;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            return recyclerView.getChildAdapterPosition(recyclerView.findChildViewUnder(motionEvent.getX(), inboundY));
        }

        static boolean isPastLastItem(int i, int i2, int i3, MotionEvent motionEvent, int i4) {
            return i4 == 0 ? motionEvent.getX() > ((float) i3) && motionEvent.getY() > ((float) i) : motionEvent.getX() < ((float) i2) && motionEvent.getY() > ((float) i);
        }
    }
}
