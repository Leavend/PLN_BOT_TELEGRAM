package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

/* loaded from: classes5.dex */
final class TouchInputHandler<K> extends MotionInputHandler<K> {
    private static final String TAG = "TouchInputHandler";
    private final ItemDetailsLookup<K> mDetailsLookup;
    private final Runnable mGestureStarter;
    private final Runnable mHapticPerformer;
    private final Runnable mLongPressCallback;
    private final OnDragInitiatedListener mOnDragInitiatedListener;
    private final OnItemActivatedListener<K> mOnItemActivatedListener;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;

    TouchInputHandler(SelectionTracker<K> selectionTracker, ItemKeyProvider<K> itemKeyProvider, ItemDetailsLookup<K> itemDetailsLookup, SelectionTracker.SelectionPredicate<K> selectionPredicate, Runnable runnable, OnDragInitiatedListener onDragInitiatedListener, OnItemActivatedListener<K> onItemActivatedListener, FocusDelegate<K> focusDelegate, Runnable runnable2, Runnable runnable3) {
        super(selectionTracker, itemKeyProvider, focusDelegate);
        Preconditions.checkArgument(itemDetailsLookup != null);
        Preconditions.checkArgument(selectionPredicate != null);
        Preconditions.checkArgument(runnable != null);
        Preconditions.checkArgument(onItemActivatedListener != null);
        Preconditions.checkArgument(onDragInitiatedListener != null);
        Preconditions.checkArgument(runnable2 != null);
        this.mDetailsLookup = itemDetailsLookup;
        this.mSelectionPredicate = selectionPredicate;
        this.mGestureStarter = runnable;
        this.mOnItemActivatedListener = onItemActivatedListener;
        this.mOnDragInitiatedListener = onDragInitiatedListener;
        this.mHapticPerformer = runnable2;
        this.mLongPressCallback = runnable3;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails = this.mDetailsLookup.getItemDetails(motionEvent);
        if (itemDetails == null || !itemDetails.hasSelectionKey()) {
            return this.mSelectionTracker.clearSelection();
        }
        if (this.mSelectionTracker.hasSelection()) {
            if (shouldExtendRange(motionEvent)) {
                extendSelectionRange(itemDetails);
                return true;
            }
            if (this.mSelectionTracker.isSelected(itemDetails.getSelectionKey())) {
                this.mSelectionTracker.deselect(itemDetails.getSelectionKey());
                return true;
            }
            selectItem(itemDetails);
            return true;
        }
        if (itemDetails.inSelectionHotspot(motionEvent)) {
            return selectItem(itemDetails);
        }
        return this.mOnItemActivatedListener.onItemActivated(itemDetails, motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return MotionEvents.isActionUp(motionEvent) && onSingleTapUp(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails;
        if (this.mDetailsLookup.overItemWithSelectionKey(motionEvent) && (itemDetails = this.mDetailsLookup.getItemDetails(motionEvent)) != null) {
            this.mLongPressCallback.run();
            if (shouldExtendRange(motionEvent)) {
                extendSelectionRange(itemDetails);
                this.mHapticPerformer.run();
                return;
            }
            if (this.mSelectionTracker.isSelected(itemDetails.getSelectionKey())) {
                if (this.mOnDragInitiatedListener.onDragInitiated(motionEvent)) {
                    this.mHapticPerformer.run();
                }
            } else if (this.mSelectionPredicate.canSetStateForKey(itemDetails.getSelectionKey(), true) && selectItem(itemDetails)) {
                if (this.mSelectionPredicate.canSelectMultiple() && this.mSelectionTracker.isRangeActive()) {
                    this.mGestureStarter.run();
                }
                this.mHapticPerformer.run();
            }
        }
    }
}
