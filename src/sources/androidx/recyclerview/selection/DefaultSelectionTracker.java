package androidx.recyclerview.selection;

import android.os.Bundle;
import android.util.Log;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.Range;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class DefaultSelectionTracker<K> extends SelectionTracker<K> implements Resettable {
    private static final String EXTRA_SELECTION_PREFIX = "androidx.recyclerview.selection";
    private static final String TAG = "DefaultSelectionTracker";
    private final AdapterObserver mAdapterObserver;
    private final ItemKeyProvider<K> mKeyProvider;
    private Range mRange;
    private final DefaultSelectionTracker<K>.RangeCallbacks mRangeCallbacks;
    private final String mSelectionId;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;
    private final boolean mSingleSelect;
    private final StorageStrategy<K> mStorage;
    private final Selection<K> mSelection = new Selection<>();
    private final List<SelectionTracker.SelectionObserver<K>> mObservers = new ArrayList(1);

    public DefaultSelectionTracker(String str, ItemKeyProvider<K> itemKeyProvider, SelectionTracker.SelectionPredicate<K> selectionPredicate, StorageStrategy<K> storageStrategy) {
        Preconditions.checkArgument(str != null);
        Preconditions.checkArgument(!str.trim().isEmpty());
        Preconditions.checkArgument(itemKeyProvider != null);
        Preconditions.checkArgument(selectionPredicate != null);
        Preconditions.checkArgument(storageStrategy != null);
        this.mSelectionId = str;
        this.mKeyProvider = itemKeyProvider;
        this.mSelectionPredicate = selectionPredicate;
        this.mStorage = storageStrategy;
        this.mRangeCallbacks = new RangeCallbacks();
        this.mSingleSelect = !selectionPredicate.canSelectMultiple();
        this.mAdapterObserver = new AdapterObserver(this);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void addObserver(SelectionTracker.SelectionObserver<K> selectionObserver) {
        Preconditions.checkArgument(selectionObserver != null);
        this.mObservers.add(selectionObserver);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean hasSelection() {
        return !this.mSelection.isEmpty();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public Selection<K> getSelection() {
        return this.mSelection;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void copySelection(MutableSelection<K> mutableSelection) {
        mutableSelection.copyFrom(this.mSelection);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean isSelected(K k) {
        return this.mSelection.contains(k);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    protected void restoreSelection(Selection<K> selection) {
        Preconditions.checkArgument(selection != null);
        setItemsSelectedQuietly(selection.mSelection, true);
        notifySelectionRestored();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean setItemsSelected(Iterable<K> iterable, boolean z) {
        boolean itemsSelectedQuietly = setItemsSelectedQuietly(iterable, z);
        notifySelectionChanged();
        return itemsSelectedQuietly;
    }

    private boolean setItemsSelectedQuietly(Iterable<K> iterable, boolean z) {
        boolean z2 = false;
        for (K k : iterable) {
            boolean z3 = true;
            if (!z ? !canSetState(k, false) || !this.mSelection.remove(k) : !canSetState(k, true) || !this.mSelection.add(k)) {
                z3 = false;
            }
            if (z3) {
                notifyItemStateChanged(k, z);
            }
            z2 |= z3;
        }
        return z2;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean clearSelection() {
        if (!hasSelection()) {
            return false;
        }
        clearProvisionalSelection();
        clearPrimarySelection();
        notifySelectionCleared();
        return true;
    }

    private void clearPrimarySelection() {
        if (hasSelection()) {
            notifySelectionCleared(clearSelectionQuietly());
            notifySelectionChanged();
        }
    }

    private Selection<K> clearSelectionQuietly() {
        this.mRange = null;
        MutableSelection<K> mutableSelection = new MutableSelection<>();
        if (hasSelection()) {
            copySelection(mutableSelection);
            this.mSelection.clear();
        }
        return mutableSelection;
    }

    @Override // androidx.recyclerview.selection.Resettable
    public void reset() {
        clearSelection();
        this.mRange = null;
    }

    @Override // androidx.recyclerview.selection.Resettable
    public boolean isResetRequired() {
        return hasSelection() || isRangeActive();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean select(K k) {
        Preconditions.checkArgument(k != null);
        if (this.mSelection.contains(k) || !canSetState(k, true)) {
            return false;
        }
        if (this.mSingleSelect && hasSelection()) {
            notifySelectionCleared(clearSelectionQuietly());
        }
        this.mSelection.add(k);
        notifyItemStateChanged(k, true);
        notifySelectionChanged();
        return true;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean deselect(K k) {
        Preconditions.checkArgument(k != null);
        if (!this.mSelection.contains(k) || !canSetState(k, false)) {
            return false;
        }
        this.mSelection.remove(k);
        notifyItemStateChanged(k, false);
        notifySelectionChanged();
        if (this.mSelection.isEmpty() && isRangeActive()) {
            endRange();
        }
        return true;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void startRange(int i) {
        if (this.mSelection.contains(this.mKeyProvider.getKey(i)) || select(this.mKeyProvider.getKey(i))) {
            anchorRange(i);
        }
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void extendRange(int i) {
        extendRange(i, 0);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void endRange() {
        this.mRange = null;
        clearProvisionalSelection();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void anchorRange(int i) {
        Preconditions.checkArgument(i != -1);
        Preconditions.checkArgument(this.mSelection.contains(this.mKeyProvider.getKey(i)));
        this.mRange = new Range(i, this.mRangeCallbacks);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void extendProvisionalRange(int i) {
        if (this.mSingleSelect) {
            return;
        }
        extendRange(i, 1);
    }

    private void extendRange(int i, int i2) {
        if (!isRangeActive()) {
            Log.e(TAG, "Ignoring attempt to extend unestablished range. Ignoring.");
        } else if (i == -1) {
            Log.w(TAG, "Ignoring attempt to extend range to invalid position: " + i);
        } else {
            this.mRange.extendRange(i, i2);
            notifySelectionChanged();
        }
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void setProvisionalSelection(Set<K> set) {
        if (this.mSingleSelect) {
            return;
        }
        for (Map.Entry<K, Boolean> entry : this.mSelection.setProvisionalSelection(set).entrySet()) {
            notifyItemStateChanged(entry.getKey(), entry.getValue().booleanValue());
        }
        notifySelectionChanged();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void mergeProvisionalSelection() {
        this.mSelection.mergeProvisionalSelection();
        notifySelectionChanged();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void clearProvisionalSelection() {
        Iterator<K> it = this.mSelection.mProvisionalSelection.iterator();
        while (it.hasNext()) {
            notifyItemStateChanged(it.next(), false);
        }
        this.mSelection.clearProvisionalSelection();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean isRangeActive() {
        return this.mRange != null;
    }

    private boolean canSetState(K k, boolean z) {
        return this.mSelectionPredicate.canSetStateForKey(k, z);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    protected RecyclerView.AdapterDataObserver getAdapterDataObserver() {
        return this.mAdapterObserver;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void onDataSetChanged() {
        if (this.mSelection.isEmpty()) {
            Log.d(TAG, "Ignoring onDataSetChange. No active selection.");
            return;
        }
        this.mSelection.clearProvisionalSelection();
        notifySelectionRefresh();
        Iterator<K> it = this.mSelection.iterator();
        ArrayList arrayList = null;
        while (it.hasNext()) {
            K next = it.next();
            if (this.mKeyProvider.getPosition(next) == -1 || !canSetState(next, true)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(next);
            } else {
                for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                    this.mObservers.get(size).onItemStateChanged(next, true);
                }
            }
        }
        if (arrayList != null) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                deselect(it2.next());
            }
        }
        notifySelectionChanged();
    }

    private void notifyItemStateChanged(K k, boolean z) {
        Preconditions.checkArgument(k != null);
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onItemStateChanged(k, z);
        }
    }

    private void notifySelectionCleared() {
        Iterator<SelectionTracker.SelectionObserver<K>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onSelectionCleared();
        }
    }

    private void notifySelectionCleared(Selection<K> selection) {
        Iterator<K> it = selection.mSelection.iterator();
        while (it.hasNext()) {
            notifyItemStateChanged(it.next(), false);
        }
        Iterator<K> it2 = selection.mProvisionalSelection.iterator();
        while (it2.hasNext()) {
            notifyItemStateChanged(it2.next(), false);
        }
    }

    private void notifySelectionChanged() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionChanged();
        }
    }

    private void notifySelectionRestored() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionRestored();
        }
    }

    private void notifySelectionRefresh() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionRefresh();
        }
    }

    void updateForRegularRange(int i, int i2, boolean z) {
        Preconditions.checkArgument(i2 >= i);
        while (i <= i2) {
            K key = this.mKeyProvider.getKey(i);
            if (key != null) {
                if (z) {
                    select(key);
                } else {
                    deselect(key);
                }
            }
            i++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void updateForProvisionalRange(int r5, int r6, boolean r7) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            if (r6 < r5) goto L6
            r2 = r1
            goto L7
        L6:
            r2 = r0
        L7:
            androidx.core.util.Preconditions.checkArgument(r2)
        La:
            if (r5 > r6) goto L41
            androidx.recyclerview.selection.ItemKeyProvider<K> r2 = r4.mKeyProvider
            java.lang.Object r2 = r2.getKey(r5)
            if (r2 != 0) goto L15
            goto L3e
        L15:
            if (r7 == 0) goto L31
            boolean r3 = r4.canSetState(r2, r1)
            if (r3 == 0) goto L2f
            androidx.recyclerview.selection.Selection<K> r3 = r4.mSelection
            java.util.Set<K> r3 = r3.mSelection
            boolean r3 = r3.contains(r2)
            if (r3 != 0) goto L2f
            androidx.recyclerview.selection.Selection<K> r3 = r4.mSelection
            java.util.Set<K> r3 = r3.mProvisionalSelection
            r3.add(r2)
            goto L38
        L2f:
            r3 = r0
            goto L39
        L31:
            androidx.recyclerview.selection.Selection<K> r3 = r4.mSelection
            java.util.Set<K> r3 = r3.mProvisionalSelection
            r3.remove(r2)
        L38:
            r3 = r1
        L39:
            if (r3 == 0) goto L3e
            r4.notifyItemStateChanged(r2, r7)
        L3e:
            int r5 = r5 + 1
            goto La
        L41:
            r4.notifySelectionChanged()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.selection.DefaultSelectionTracker.updateForProvisionalRange(int, int, boolean):void");
    }

    String getInstanceStateKey() {
        return "androidx.recyclerview.selection:" + this.mSelectionId;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public final void onSaveInstanceState(Bundle bundle) {
        if (this.mSelection.isEmpty()) {
            return;
        }
        bundle.putBundle(getInstanceStateKey(), this.mStorage.asBundle(this.mSelection));
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public final void onRestoreInstanceState(Bundle bundle) {
        Bundle bundle2;
        Selection<K> selectionAsSelection;
        if (bundle == null || (bundle2 = bundle.getBundle(getInstanceStateKey())) == null || (selectionAsSelection = this.mStorage.asSelection(bundle2)) == null || selectionAsSelection.isEmpty()) {
            return;
        }
        restoreSelection(selectionAsSelection);
    }

    private final class RangeCallbacks extends Range.Callbacks {
        RangeCallbacks() {
        }

        @Override // androidx.recyclerview.selection.Range.Callbacks
        void updateForRange(int i, int i2, boolean z, int i3) {
            if (i3 == 0) {
                DefaultSelectionTracker.this.updateForRegularRange(i, i2, z);
            } else {
                if (i3 == 1) {
                    DefaultSelectionTracker.this.updateForProvisionalRange(i, i2, z);
                    return;
                }
                throw new IllegalArgumentException("Invalid range type: " + i3);
            }
        }
    }

    private static final class AdapterObserver extends RecyclerView.AdapterDataObserver {
        private final DefaultSelectionTracker<?> mSelectionTracker;

        AdapterObserver(DefaultSelectionTracker<?> defaultSelectionTracker) {
            Preconditions.checkArgument(defaultSelectionTracker != null);
            this.mSelectionTracker = defaultSelectionTracker;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            this.mSelectionTracker.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            if (SelectionTracker.SELECTION_CHANGED_MARKER.equals(obj)) {
                return;
            }
            this.mSelectionTracker.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            this.mSelectionTracker.endRange();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            this.mSelectionTracker.endRange();
            this.mSelectionTracker.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            this.mSelectionTracker.endRange();
        }
    }
}
