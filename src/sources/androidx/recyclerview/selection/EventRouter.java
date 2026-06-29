package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class EventRouter implements RecyclerView.OnItemTouchListener, Resettable {
    private final ToolHandlerRegistry<RecyclerView.OnItemTouchListener> mDelegates = new ToolHandlerRegistry<>(new DummyOnItemTouchListener());
    private boolean mDisallowIntercept;

    EventRouter() {
    }

    void set(int i, RecyclerView.OnItemTouchListener onItemTouchListener) {
        Preconditions.checkArgument(onItemTouchListener != null);
        this.mDelegates.set(i, onItemTouchListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mDisallowIntercept && MotionEvents.isActionDown(motionEvent)) {
            this.mDisallowIntercept = false;
        }
        return !this.mDisallowIntercept && this.mDelegates.get(motionEvent).onInterceptTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mDisallowIntercept) {
            return;
        }
        this.mDelegates.get(motionEvent).onTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            this.mDisallowIntercept = z;
        }
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
