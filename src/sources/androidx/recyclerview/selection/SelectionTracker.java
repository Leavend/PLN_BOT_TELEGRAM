package androidx.recyclerview.selection;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.BandPredicate;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Set;

/* loaded from: classes5.dex */
public abstract class SelectionTracker<K> {
    public static final String SELECTION_CHANGED_MARKER = "Selection-Changed";
    private static final String TAG = "SelectionTracker";

    public static abstract class SelectionObserver<K> {
        public void onItemStateChanged(K k, boolean z) {
        }

        public void onSelectionChanged() {
        }

        protected void onSelectionCleared() {
        }

        public void onSelectionRefresh() {
        }

        public void onSelectionRestored() {
        }
    }

    public static abstract class SelectionPredicate<K> {
        public abstract boolean canSelectMultiple();

        public abstract boolean canSetStateAtPosition(int i, boolean z);

        public abstract boolean canSetStateForKey(K k, boolean z);
    }

    public abstract void addObserver(SelectionObserver<K> selectionObserver);

    public abstract void anchorRange(int i);

    protected abstract void clearProvisionalSelection();

    public abstract boolean clearSelection();

    public abstract void copySelection(MutableSelection<K> mutableSelection);

    public abstract boolean deselect(K k);

    public abstract void endRange();

    protected abstract void extendProvisionalRange(int i);

    public abstract void extendRange(int i);

    protected abstract RecyclerView.AdapterDataObserver getAdapterDataObserver();

    public abstract Selection<K> getSelection();

    public abstract boolean hasSelection();

    public abstract boolean isRangeActive();

    public abstract boolean isSelected(K k);

    protected abstract void mergeProvisionalSelection();

    public abstract void onRestoreInstanceState(Bundle bundle);

    public abstract void onSaveInstanceState(Bundle bundle);

    protected abstract void restoreSelection(Selection<K> selection);

    public abstract boolean select(K k);

    public abstract boolean setItemsSelected(Iterable<K> iterable, boolean z);

    protected abstract void setProvisionalSelection(Set<K> set);

    public abstract void startRange(int i);

    public static final class Builder<K> {
        private final RecyclerView.Adapter<?> mAdapter;
        private BandPredicate mBandPredicate;
        private final Context mContext;
        private ItemDetailsLookup<K> mDetailsLookup;
        private ItemKeyProvider<K> mKeyProvider;
        private OnContextClickListener mOnContextClickListener;
        private OnDragInitiatedListener mOnDragInitiatedListener;
        private OnItemActivatedListener<K> mOnItemActivatedListener;
        final RecyclerView mRecyclerView;
        private final String mSelectionId;
        private final StorageStrategy<K> mStorage;
        SelectionPredicate<K> mSelectionPredicate = SelectionPredicates.createSelectAnything();
        private OperationMonitor mMonitor = new OperationMonitor();
        private FocusDelegate<K> mFocusDelegate = FocusDelegate.dummy();
        private int mBandOverlayId = R.drawable.selection_band_overlay;
        private int[] mGestureToolTypes = {1};
        private int[] mPointerToolTypes = {3};

        public Builder(String str, RecyclerView recyclerView, ItemKeyProvider<K> itemKeyProvider, ItemDetailsLookup<K> itemDetailsLookup, StorageStrategy<K> storageStrategy) {
            Preconditions.checkArgument(str != null);
            Preconditions.checkArgument(!str.trim().isEmpty());
            Preconditions.checkArgument(recyclerView != null);
            this.mSelectionId = str;
            this.mRecyclerView = recyclerView;
            this.mContext = recyclerView.getContext();
            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            this.mAdapter = adapter;
            Preconditions.checkArgument(adapter != null);
            Preconditions.checkArgument(itemKeyProvider != null);
            Preconditions.checkArgument(itemDetailsLookup != null);
            Preconditions.checkArgument(storageStrategy != null);
            this.mDetailsLookup = itemDetailsLookup;
            this.mKeyProvider = itemKeyProvider;
            this.mStorage = storageStrategy;
            this.mBandPredicate = new BandPredicate.NonDraggableArea(recyclerView, itemDetailsLookup);
        }

        public Builder<K> withSelectionPredicate(SelectionPredicate<K> selectionPredicate) {
            Preconditions.checkArgument(selectionPredicate != null);
            this.mSelectionPredicate = selectionPredicate;
            return this;
        }

        public Builder<K> withOperationMonitor(OperationMonitor operationMonitor) {
            Preconditions.checkArgument(operationMonitor != null);
            this.mMonitor = operationMonitor;
            return this;
        }

        public Builder<K> withFocusDelegate(FocusDelegate<K> focusDelegate) {
            Preconditions.checkArgument(focusDelegate != null);
            this.mFocusDelegate = focusDelegate;
            return this;
        }

        public Builder<K> withOnItemActivatedListener(OnItemActivatedListener<K> onItemActivatedListener) {
            Preconditions.checkArgument(onItemActivatedListener != null);
            this.mOnItemActivatedListener = onItemActivatedListener;
            return this;
        }

        public Builder<K> withOnContextClickListener(OnContextClickListener onContextClickListener) {
            Preconditions.checkArgument(onContextClickListener != null);
            this.mOnContextClickListener = onContextClickListener;
            return this;
        }

        public Builder<K> withOnDragInitiatedListener(OnDragInitiatedListener onDragInitiatedListener) {
            Preconditions.checkArgument(onDragInitiatedListener != null);
            this.mOnDragInitiatedListener = onDragInitiatedListener;
            return this;
        }

        @Deprecated
        public Builder<K> withGestureTooltypes(int... iArr) {
            Log.w(SelectionTracker.TAG, "Setting gestureTooltypes is likely to result in unexpected behavior.");
            this.mGestureToolTypes = iArr;
            return this;
        }

        public Builder<K> withBandOverlay(int i) {
            this.mBandOverlayId = i;
            return this;
        }

        public Builder<K> withBandPredicate(BandPredicate bandPredicate) {
            this.mBandPredicate = bandPredicate;
            return this;
        }

        @Deprecated
        public Builder<K> withPointerTooltypes(int... iArr) {
            Log.w(SelectionTracker.TAG, "Setting pointerTooltypes is likely to result in unexpected behavior.");
            this.mPointerToolTypes = iArr;
            return this;
        }

        public SelectionTracker<K> build() {
            BandSelectionHelper bandSelectionHelperCreate;
            DefaultSelectionTracker defaultSelectionTracker = new DefaultSelectionTracker(this.mSelectionId, this.mKeyProvider, this.mSelectionPredicate, this.mStorage);
            RecyclerView.Adapter<?> adapter = this.mAdapter;
            ItemKeyProvider<K> itemKeyProvider = this.mKeyProvider;
            final RecyclerView recyclerView = this.mRecyclerView;
            recyclerView.getClass();
            EventBridge.install(adapter, defaultSelectionTracker, itemKeyProvider, new Consumer() { // from class: androidx.recyclerview.selection.SelectionTracker$Builder$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    recyclerView.post((Runnable) obj);
                }
            });
            ViewAutoScroller viewAutoScroller = new ViewAutoScroller(ViewAutoScroller.createScrollHost(this.mRecyclerView));
            GestureRouter gestureRouter = new GestureRouter();
            GestureDetector gestureDetector = new GestureDetector(this.mContext, gestureRouter);
            final GestureSelectionHelper gestureSelectionHelperCreate = GestureSelectionHelper.create(defaultSelectionTracker, this.mSelectionPredicate, this.mRecyclerView, viewAutoScroller, this.mMonitor);
            EventRouter eventRouter = new EventRouter();
            GestureDetectorWrapper gestureDetectorWrapper = new GestureDetectorWrapper(gestureDetector);
            EventRouter eventRouter2 = new EventRouter();
            final EventBackstop eventBackstop = new EventBackstop();
            DisallowInterceptFilter disallowInterceptFilter = new DisallowInterceptFilter(eventBackstop);
            eventRouter2.set(1, disallowInterceptFilter);
            this.mRecyclerView.addOnItemTouchListener(eventRouter);
            this.mRecyclerView.addOnItemTouchListener(gestureDetectorWrapper);
            this.mRecyclerView.addOnItemTouchListener(eventRouter2);
            ResetManager resetManager = new ResetManager();
            defaultSelectionTracker.addObserver(resetManager.getSelectionObserver());
            eventRouter.set(0, resetManager.getInputListener());
            resetManager.addResetHandler(defaultSelectionTracker);
            resetManager.addResetHandler(this.mMonitor.asResettable());
            resetManager.addResetHandler(gestureSelectionHelperCreate);
            resetManager.addResetHandler(gestureDetectorWrapper);
            resetManager.addResetHandler(eventRouter);
            resetManager.addResetHandler(eventRouter2);
            resetManager.addResetHandler(eventBackstop);
            resetManager.addResetHandler(disallowInterceptFilter);
            OnDragInitiatedListener onDragInitiatedListener = this.mOnDragInitiatedListener;
            if (onDragInitiatedListener == null) {
                onDragInitiatedListener = new OnDragInitiatedListener() { // from class: androidx.recyclerview.selection.SelectionTracker.Builder.1
                    @Override // androidx.recyclerview.selection.OnDragInitiatedListener
                    public boolean onDragInitiated(MotionEvent motionEvent) {
                        return false;
                    }
                };
            }
            this.mOnDragInitiatedListener = onDragInitiatedListener;
            OnItemActivatedListener<K> onItemActivatedListener = this.mOnItemActivatedListener;
            if (onItemActivatedListener == null) {
                onItemActivatedListener = new OnItemActivatedListener<K>() { // from class: androidx.recyclerview.selection.SelectionTracker.Builder.2
                    @Override // androidx.recyclerview.selection.OnItemActivatedListener
                    public boolean onItemActivated(ItemDetailsLookup.ItemDetails<K> itemDetails, MotionEvent motionEvent) {
                        return false;
                    }
                };
            }
            this.mOnItemActivatedListener = onItemActivatedListener;
            OnContextClickListener onContextClickListener = this.mOnContextClickListener;
            if (onContextClickListener == null) {
                onContextClickListener = new OnContextClickListener() { // from class: androidx.recyclerview.selection.SelectionTracker.Builder.3
                    @Override // androidx.recyclerview.selection.OnContextClickListener
                    public boolean onContextClick(MotionEvent motionEvent) {
                        return false;
                    }
                };
            }
            this.mOnContextClickListener = onContextClickListener;
            ItemKeyProvider<K> itemKeyProvider2 = this.mKeyProvider;
            ItemDetailsLookup<K> itemDetailsLookup = this.mDetailsLookup;
            SelectionPredicate<K> selectionPredicate = this.mSelectionPredicate;
            gestureSelectionHelperCreate.getClass();
            TouchInputHandler touchInputHandler = new TouchInputHandler(defaultSelectionTracker, itemKeyProvider2, itemDetailsLookup, selectionPredicate, new Runnable() { // from class: androidx.recyclerview.selection.SelectionTracker$Builder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    gestureSelectionHelperCreate.start();
                }
            }, this.mOnDragInitiatedListener, this.mOnItemActivatedListener, this.mFocusDelegate, new Runnable() { // from class: androidx.recyclerview.selection.SelectionTracker.Builder.4
                @Override // java.lang.Runnable
                public void run() {
                    Builder.this.mRecyclerView.performHapticFeedback(0);
                }
            }, new Runnable() { // from class: androidx.recyclerview.selection.SelectionTracker$Builder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    eventBackstop.onLongPress();
                }
            });
            for (int i : this.mGestureToolTypes) {
                gestureRouter.register(i, touchInputHandler);
                eventRouter.set(i, gestureSelectionHelperCreate);
            }
            MouseInputHandler mouseInputHandler = new MouseInputHandler(defaultSelectionTracker, this.mKeyProvider, this.mDetailsLookup, this.mOnContextClickListener, this.mOnItemActivatedListener, this.mFocusDelegate);
            for (int i2 : this.mPointerToolTypes) {
                gestureRouter.register(i2, mouseInputHandler);
            }
            if (this.mKeyProvider.hasAccess(0) && this.mSelectionPredicate.canSelectMultiple()) {
                bandSelectionHelperCreate = BandSelectionHelper.create(this.mRecyclerView, viewAutoScroller, this.mBandOverlayId, this.mKeyProvider, defaultSelectionTracker, this.mSelectionPredicate, this.mBandPredicate, this.mFocusDelegate, this.mMonitor);
                resetManager.addResetHandler(bandSelectionHelperCreate);
            } else {
                bandSelectionHelperCreate = null;
            }
            eventRouter.set(3, new PointerDragEventInterceptor(this.mDetailsLookup, this.mOnDragInitiatedListener, bandSelectionHelperCreate));
            return defaultSelectionTracker;
        }
    }
}
