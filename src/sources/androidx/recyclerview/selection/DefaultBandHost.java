package androidx.recyclerview.selection;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.GridModel;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
final class DefaultBandHost<K> extends GridModel.GridHost<K> {
    private static final Rect NILL_RECT = new Rect(0, 0, 0, 0);
    private final Drawable mBand;
    private final ItemKeyProvider<K> mKeyProvider;
    private final RecyclerView mRecyclerView;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;

    DefaultBandHost(RecyclerView recyclerView, int i, ItemKeyProvider<K> itemKeyProvider, SelectionTracker.SelectionPredicate<K> selectionPredicate) {
        Preconditions.checkArgument(recyclerView != null);
        this.mRecyclerView = recyclerView;
        Drawable drawable = ContextCompat.getDrawable(recyclerView.getContext(), i);
        this.mBand = drawable;
        Preconditions.checkArgument(drawable != null);
        Preconditions.checkArgument(itemKeyProvider != null);
        Preconditions.checkArgument(selectionPredicate != null);
        this.mKeyProvider = itemKeyProvider;
        this.mSelectionPredicate = selectionPredicate;
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: androidx.recyclerview.selection.DefaultBandHost.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDrawOver(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                DefaultBandHost.this.onDrawBand(canvas);
            }
        });
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    GridModel<K> createGridModel() {
        return new GridModel<>(this, this.mKeyProvider, this.mSelectionPredicate);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getAdapterPositionAt(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView.getChildAdapterPosition(recyclerView.getChildAt(i));
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mRecyclerView.addOnScrollListener(onScrollListener);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    void removeOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mRecyclerView.removeOnScrollListener(onScrollListener);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    Point createAbsolutePoint(Point point) {
        return new Point(point.x + this.mRecyclerView.computeHorizontalScrollOffset(), point.y + this.mRecyclerView.computeVerticalScrollOffset());
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    Rect getAbsoluteRectForChildViewAt(int i) {
        View childAt = this.mRecyclerView.getChildAt(i);
        Rect rect = new Rect();
        childAt.getHitRect(rect);
        rect.left += this.mRecyclerView.computeHorizontalScrollOffset();
        rect.right += this.mRecyclerView.computeHorizontalScrollOffset();
        rect.top += this.mRecyclerView.computeVerticalScrollOffset();
        rect.bottom += this.mRecyclerView.computeVerticalScrollOffset();
        return rect;
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getVisibleChildCount() {
        return this.mRecyclerView.getChildCount();
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getColumnCount() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    void showBand(Rect rect) {
        this.mBand.setBounds(rect);
        this.mRecyclerView.invalidate();
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    void hideBand() {
        this.mBand.setBounds(NILL_RECT);
        this.mRecyclerView.invalidate();
    }

    void onDrawBand(Canvas canvas) {
        this.mBand.draw(canvas);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    boolean hasView(int i) {
        return this.mRecyclerView.findViewHolderForAdapterPosition(i) != null;
    }
}
