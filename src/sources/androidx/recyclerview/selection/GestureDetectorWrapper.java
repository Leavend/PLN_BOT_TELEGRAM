package androidx.recyclerview.selection;

import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class GestureDetectorWrapper implements RecyclerView.OnItemTouchListener, Resettable {
    private final GestureDetector mDetector;
    private boolean mDisallowIntercept;

    @Override // androidx.recyclerview.selection.Resettable
    public boolean isResetRequired() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    GestureDetectorWrapper(GestureDetector gestureDetector) {
        Preconditions.checkArgument(gestureDetector != null);
        this.mDetector = gestureDetector;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mDisallowIntercept && MotionEvents.isActionDown(motionEvent)) {
            this.mDisallowIntercept = false;
        }
        return !this.mDisallowIntercept && this.mDetector.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            this.mDisallowIntercept = z;
            sendCancelEvent();
        }
    }

    @Override // androidx.recyclerview.selection.Resettable
    public void reset() {
        this.mDisallowIntercept = false;
        sendCancelEvent();
    }

    private void sendCancelEvent() {
        this.mDetector.onTouchEvent(MotionEvents.createCancelEvent());
    }
}
