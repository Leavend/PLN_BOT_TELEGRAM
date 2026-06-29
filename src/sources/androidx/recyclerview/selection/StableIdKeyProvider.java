package androidx.recyclerview.selection;

import android.util.SparseArray;
import android.view.View;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public final class StableIdKeyProvider extends ItemKeyProvider<Long> {
    private static final String TAG = "StableIdKeyProvider";
    private final LongSparseArray<Integer> mKeyToPosition;
    private final SparseArray<Long> mPositionToKey;
    private final RecyclerView mRecyclerView;

    public StableIdKeyProvider(RecyclerView recyclerView) {
        super(1);
        this.mPositionToKey = new SparseArray<>();
        this.mKeyToPosition = new LongSparseArray<>();
        this.mRecyclerView = recyclerView;
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() { // from class: androidx.recyclerview.selection.StableIdKeyProvider.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                StableIdKeyProvider.this.onAttached(view);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                StableIdKeyProvider.this.onDetached(view);
            }
        });
    }

    void onAttached(View view) {
        RecyclerView.ViewHolder viewHolderFindContainingViewHolder = this.mRecyclerView.findContainingViewHolder(view);
        if (viewHolderFindContainingViewHolder == null) {
            return;
        }
        int adapterPosition = viewHolderFindContainingViewHolder.getAdapterPosition();
        long itemId = viewHolderFindContainingViewHolder.getItemId();
        if (adapterPosition == -1 || itemId == -1) {
            return;
        }
        this.mPositionToKey.put(adapterPosition, Long.valueOf(itemId));
        this.mKeyToPosition.put(itemId, Integer.valueOf(adapterPosition));
    }

    void onDetached(View view) {
        RecyclerView.ViewHolder viewHolderFindContainingViewHolder = this.mRecyclerView.findContainingViewHolder(view);
        if (viewHolderFindContainingViewHolder == null) {
            return;
        }
        int adapterPosition = viewHolderFindContainingViewHolder.getAdapterPosition();
        long itemId = viewHolderFindContainingViewHolder.getItemId();
        if (adapterPosition == -1 || itemId == -1) {
            return;
        }
        this.mPositionToKey.delete(adapterPosition);
        this.mKeyToPosition.remove(itemId);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.selection.ItemKeyProvider
    public Long getKey(int i) {
        return this.mPositionToKey.get(i, null);
    }

    @Override // androidx.recyclerview.selection.ItemKeyProvider
    public int getPosition(Long l) {
        return this.mKeyToPosition.get(l.longValue(), -1).intValue();
    }
}
