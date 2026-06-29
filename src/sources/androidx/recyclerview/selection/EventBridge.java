package androidx.recyclerview.selection;

import android.util.Log;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class EventBridge {
    private static final String TAG = "EventsRelays";

    public static <K> void install(RecyclerView.Adapter<?> adapter, SelectionTracker<K> selectionTracker, ItemKeyProvider<K> itemKeyProvider, Consumer<Runnable> consumer) {
        new TrackerToAdapterBridge(selectionTracker, itemKeyProvider, adapter, consumer);
        adapter.registerAdapterDataObserver(selectionTracker.getAdapterDataObserver());
    }

    private static final class TrackerToAdapterBridge<K> extends SelectionTracker.SelectionObserver<K> {
        final RecyclerView.Adapter<?> mAdapter;
        private final ItemKeyProvider<K> mKeyProvider;
        private final Consumer<Runnable> mRunner;

        TrackerToAdapterBridge(SelectionTracker<K> selectionTracker, ItemKeyProvider<K> itemKeyProvider, RecyclerView.Adapter<?> adapter, Consumer<Runnable> consumer) {
            selectionTracker.addObserver(this);
            Preconditions.checkArgument(itemKeyProvider != null);
            Preconditions.checkArgument(adapter != null);
            Preconditions.checkArgument(consumer != null);
            this.mKeyProvider = itemKeyProvider;
            this.mAdapter = adapter;
            this.mRunner = consumer;
        }

        @Override // androidx.recyclerview.selection.SelectionTracker.SelectionObserver
        public void onItemStateChanged(K k, boolean z) {
            final int position = this.mKeyProvider.getPosition(k);
            if (position < 0) {
                Log.w(EventBridge.TAG, "Item change notification received for unknown item: " + k);
            } else {
                this.mRunner.accept(new Runnable() { // from class: androidx.recyclerview.selection.EventBridge.TrackerToAdapterBridge.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TrackerToAdapterBridge.this.mAdapter.notifyItemChanged(position, SelectionTracker.SELECTION_CHANGED_MARKER);
                    }
                });
            }
        }
    }

    private EventBridge() {
    }
}
