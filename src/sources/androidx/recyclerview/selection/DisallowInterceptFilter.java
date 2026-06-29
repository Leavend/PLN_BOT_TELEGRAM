package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
class DisallowInterceptFilter implements RecyclerView.OnItemTouchListener, Resettable {
    private final RecyclerView.OnItemTouchListener mDelegate;
    private boolean mDisallowIntercept;

    DisallowInterceptFilter(RecyclerView.OnItemTouchListener onItemTouchListener) {
        this.mDelegate = onItemTouchListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mDisallowIntercept && MotionEvents.isActionDown(motionEvent)) {
            this.mDisallowIntercept = false;
        }
        return !this.mDisallowIntercept && this.mDelegate.onInterceptTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mDelegate.onInterceptTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        this.mDisallowIntercept = true;
    }

    @Override // androidx.recyclerview.selection.Resettable
    public boolean isResetRequired() {
        return this.mDisallowIntercept;
    }

    @Override // androidx.recyclerview.selection.Resettable
    public void reset() {
        this.mDisallowIntercept = false;
    }
}
