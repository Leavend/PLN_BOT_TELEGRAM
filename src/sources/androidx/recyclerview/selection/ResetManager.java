package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
final class ResetManager<K> {
    private static final String TAG = "ResetManager";
    private final List<Resettable> mResetHandlers = new ArrayList();
    private final RecyclerView.OnItemTouchListener mInputListener = new RecyclerView.OnItemTouchListener() { // from class: androidx.recyclerview.selection.ResetManager.1
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (!MotionEvents.isActionCancel(motionEvent)) {
                return false;
            }
            ResetManager.this.callResetHandlers();
            return false;
        }
    };
    private final SelectionTracker.SelectionObserver<K> mSelectionObserver = new SelectionTracker.SelectionObserver<K>() { // from class: androidx.recyclerview.selection.ResetManager.2
        @Override // androidx.recyclerview.selection.SelectionTracker.SelectionObserver
        protected void onSelectionCleared() {
            ResetManager.this.callResetHandlers();
        }
    };

    ResetManager() {
    }

    SelectionTracker.SelectionObserver<K> getSelectionObserver() {
        return this.mSelectionObserver;
    }

    RecyclerView.OnItemTouchListener getInputListener() {
        return this.mInputListener;
    }

    void addResetHandler(Resettable resettable) {
        this.mResetHandlers.add(resettable);
    }

    void callResetHandlers() {
        for (Resettable resettable : this.mResetHandlers) {
            if (resettable.isResetRequired()) {
                resettable.reset();
            }
        }
    }
}
