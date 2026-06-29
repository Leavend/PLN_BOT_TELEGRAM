package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class PointerDragEventInterceptor implements RecyclerView.OnItemTouchListener {
    private RecyclerView.OnItemTouchListener mDelegate;
    private final OnDragInitiatedListener mDragListener;
    private final ItemDetailsLookup<?> mEventDetailsLookup;

    PointerDragEventInterceptor(ItemDetailsLookup<?> itemDetailsLookup, OnDragInitiatedListener onDragInitiatedListener, RecyclerView.OnItemTouchListener onItemTouchListener) {
        Preconditions.checkArgument(itemDetailsLookup != null);
        Preconditions.checkArgument(onDragInitiatedListener != null);
        this.mEventDetailsLookup = itemDetailsLookup;
        this.mDragListener = onDragInitiatedListener;
        if (onItemTouchListener != null) {
            this.mDelegate = onItemTouchListener;
        } else {
            this.mDelegate = new DummyOnItemTouchListener();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (MotionEvents.isPointerDragEvent(motionEvent) && this.mEventDetailsLookup.inItemDragRegion(motionEvent)) {
            return this.mDragListener.onDragInitiated(motionEvent);
        }
        return this.mDelegate.onInterceptTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mDelegate.onTouchEvent(recyclerView, motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        this.mDelegate.onRequestDisallowInterceptTouchEvent(z);
    }
}
