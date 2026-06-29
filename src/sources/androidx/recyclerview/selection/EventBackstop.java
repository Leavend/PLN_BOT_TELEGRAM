package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
class EventBackstop implements RecyclerView.OnItemTouchListener, Resettable {
    private boolean mLongPressFired;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    EventBackstop() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (MotionEvents.isActionUp(motionEvent) && this.mLongPressFired) {
            this.mLongPressFired = false;
            return true;
        }
        if (MotionEvents.isActionDown(motionEvent) && isResetRequired()) {
            reset();
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        throw new UnsupportedOperationException("Wrap me in an InterceptFilter.");
    }

    @Override // androidx.recyclerview.selection.Resettable
    public boolean isResetRequired() {
        return this.mLongPressFired;
    }

    @Override // androidx.recyclerview.selection.Resettable
    public void reset() {
        this.mLongPressFired = false;
    }

    void onLongPress() {
        this.mLongPressFired = true;
    }
}
