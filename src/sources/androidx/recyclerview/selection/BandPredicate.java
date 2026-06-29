package androidx.recyclerview.selection;

import android.view.MotionEvent;
import android.view.View;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public abstract class BandPredicate {
    public abstract boolean canInitiate(MotionEvent motionEvent);

    static boolean hasSupportedLayoutManager(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        return (layoutManager instanceof GridLayoutManager) || (layoutManager instanceof LinearLayoutManager);
    }

    public static final class EmptyArea extends BandPredicate {
        private final RecyclerView mRecyclerView;

        public EmptyArea(RecyclerView recyclerView) {
            Preconditions.checkArgument(recyclerView != null);
            this.mRecyclerView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.BandPredicate
        public boolean canInitiate(MotionEvent motionEvent) {
            if (!hasSupportedLayoutManager(this.mRecyclerView) || this.mRecyclerView.hasPendingAdapterUpdates()) {
                return false;
            }
            View viewFindChildViewUnder = this.mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return (viewFindChildViewUnder != null ? this.mRecyclerView.getChildAdapterPosition(viewFindChildViewUnder) : -1) == -1;
        }
    }

    public static final class NonDraggableArea extends BandPredicate {
        private final ItemDetailsLookup<?> mDetailsLookup;
        private final RecyclerView mRecyclerView;

        public NonDraggableArea(RecyclerView recyclerView, ItemDetailsLookup<?> itemDetailsLookup) {
            Preconditions.checkArgument(recyclerView != null);
            Preconditions.checkArgument(itemDetailsLookup != null);
            this.mRecyclerView = recyclerView;
            this.mDetailsLookup = itemDetailsLookup;
        }

        @Override // androidx.recyclerview.selection.BandPredicate
        public boolean canInitiate(MotionEvent motionEvent) {
            if (!hasSupportedLayoutManager(this.mRecyclerView) || this.mRecyclerView.hasPendingAdapterUpdates()) {
                return false;
            }
            ItemDetailsLookup.ItemDetails<?> itemDetails = this.mDetailsLookup.getItemDetails(motionEvent);
            return itemDetails == null || !itemDetails.inDragRegion(motionEvent);
        }
    }
}
