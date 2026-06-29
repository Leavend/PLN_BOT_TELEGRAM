package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.core.view.ViewCompat;
import java.util.List;
import org.osmdroid.library.R;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

@Deprecated
/* loaded from: classes3.dex */
public class ItemizedOverlayWithFocus<Item extends OverlayItem> extends ItemizedIconOverlay<Item> {
    private final int DEFAULTMARKER_BACKGROUNDCOLOR;
    private int DESCRIPTION_BOX_CORNERWIDTH;
    private int DESCRIPTION_BOX_PADDING;
    private int DESCRIPTION_LINE_HEIGHT;
    private int DESCRIPTION_MAXWIDTH;
    private int DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT;
    private int FONT_SIZE_DP;
    private String UNKNOWN;
    private int fontSizePixels;
    private Context mContext;
    protected Paint mDescriptionPaint;
    protected boolean mFocusItemsOnTap;
    protected int mFocusedItemIndex;
    private final Point mFocusedScreenCoords;
    protected Paint mMarkerBackgroundPaint;
    protected int mMarkerFocusedBackgroundColor;
    protected Drawable mMarkerFocusedBase;
    private final Rect mRect;
    protected Paint mTitlePaint;

    public ItemizedOverlayWithFocus(Context context, List<Item> list, ItemizedIconOverlay.OnItemGestureListener<Item> onItemGestureListener) {
        this(list, onItemGestureListener, context);
    }

    public ItemizedOverlayWithFocus(List<Item> list, ItemizedIconOverlay.OnItemGestureListener<Item> onItemGestureListener, Context context) {
        this(list, context.getResources().getDrawable(R.drawable.marker_default), null, Integer.MIN_VALUE, onItemGestureListener, context);
    }

    public ItemizedOverlayWithFocus(List<Item> list, Drawable drawable, Drawable drawable2, int i, ItemizedIconOverlay.OnItemGestureListener<Item> onItemGestureListener, Context context) {
        super(list, drawable, onItemGestureListener, context);
        int iRgb = Color.rgb(101, 185, 74);
        this.DEFAULTMARKER_BACKGROUNDCOLOR = iRgb;
        this.DESCRIPTION_BOX_PADDING = 3;
        this.DESCRIPTION_BOX_CORNERWIDTH = 3;
        this.DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT = 2;
        this.FONT_SIZE_DP = 14;
        this.DESCRIPTION_MAXWIDTH = 600;
        this.DESCRIPTION_LINE_HEIGHT = 30;
        this.mFocusedScreenCoords = new Point();
        this.mRect = new Rect();
        this.mContext = context;
        if (drawable2 == null) {
            this.mMarkerFocusedBase = boundToHotspot(context.getResources().getDrawable(R.drawable.marker_default_focused_base), OverlayItem.HotspotPlace.BOTTOM_CENTER);
        } else {
            this.mMarkerFocusedBase = drawable2;
        }
        this.mMarkerFocusedBackgroundColor = i == Integer.MIN_VALUE ? iRgb : i;
        calculateDrawSettings();
        unSetFocusedItem();
    }

    private void calculateDrawSettings() {
        int iApplyDimension = (int) TypedValue.applyDimension(1, this.FONT_SIZE_DP, this.mContext.getResources().getDisplayMetrics());
        this.fontSizePixels = iApplyDimension;
        this.DESCRIPTION_LINE_HEIGHT = iApplyDimension + 5;
        this.DESCRIPTION_MAXWIDTH = (int) (this.mContext.getResources().getDisplayMetrics().widthPixels * 0.8d);
        this.UNKNOWN = this.mContext.getResources().getString(R.string.unknown);
        this.mMarkerBackgroundPaint = new Paint();
        Paint paint = new Paint();
        this.mDescriptionPaint = paint;
        paint.setAntiAlias(true);
        this.mDescriptionPaint.setTextSize(this.fontSizePixels);
        Paint paint2 = new Paint();
        this.mTitlePaint = paint2;
        paint2.setTextSize(this.fontSizePixels);
        this.mTitlePaint.setFakeBoldText(true);
        this.mTitlePaint.setAntiAlias(true);
    }

    public void setDescriptionBoxPadding(int i) {
        this.DESCRIPTION_BOX_PADDING = i;
    }

    public void setDescriptionBoxCornerWidth(int i) {
        this.DESCRIPTION_BOX_CORNERWIDTH = i;
    }

    public void setDescriptionTitleExtraLineHeight(int i) {
        this.DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT = i;
    }

    public void setMarkerBackgroundColor(int i) {
        this.mMarkerFocusedBackgroundColor = i;
    }

    public void setMarkerTitleForegroundColor(int i) {
        this.mTitlePaint.setColor(i);
    }

    public void setMarkerDescriptionForegroundColor(int i) {
        this.mDescriptionPaint.setColor(i);
    }

    public void setFontSize(int i) {
        this.FONT_SIZE_DP = i;
        calculateDrawSettings();
    }

    public void setDescriptionMaxWidth(int i) {
        this.DESCRIPTION_MAXWIDTH = i;
        calculateDrawSettings();
    }

    public void setDescriptionLineHeight(int i) {
        this.DESCRIPTION_LINE_HEIGHT = i;
        calculateDrawSettings();
    }

    public Item getFocusedItem() {
        if (this.mFocusedItemIndex == Integer.MIN_VALUE) {
            return null;
        }
        return this.mItemList.get(this.mFocusedItemIndex);
    }

    public void setFocusedItem(int i) {
        this.mFocusedItemIndex = i;
    }

    public void unSetFocusedItem() {
        this.mFocusedItemIndex = Integer.MIN_VALUE;
    }

    public void setFocusedItem(Item item) {
        int iIndexOf = this.mItemList.indexOf(item);
        if (iIndexOf < 0) {
            throw new IllegalArgumentException();
        }
        setFocusedItem(iIndexOf);
    }

    public void setFocusItemsOnTap(boolean z) {
        this.mFocusItemsOnTap = z;
    }

    @Override // org.osmdroid.views.overlay.ItemizedIconOverlay
    protected boolean onSingleTapUpHelper(int i, Item item, MapView mapView) {
        if (this.mFocusItemsOnTap) {
            this.mFocusedItemIndex = i;
            mapView.postInvalidate();
        }
        return this.mOnItemGestureListener.onItemSingleTapUp(i, item);
    }

    @Override // org.osmdroid.views.overlay.ItemizedOverlay, org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        super.draw(canvas, projection);
        if (this.mFocusedItemIndex == Integer.MIN_VALUE || this.mItemList == null) {
            return;
        }
        Item item = this.mItemList.get(this.mFocusedItemIndex);
        Drawable marker = item.getMarker(4);
        if (marker == null) {
            marker = this.mMarkerFocusedBase;
        }
        Drawable drawable = marker;
        projection.toPixels(item.getPoint(), this.mFocusedScreenCoords);
        drawable.copyBounds(this.mRect);
        this.mRect.offset(this.mFocusedScreenCoords.x, this.mFocusedScreenCoords.y);
        String title = item.getTitle() == null ? this.UNKNOWN : item.getTitle();
        String snippet = item.getSnippet() == null ? this.UNKNOWN : item.getSnippet();
        int length = snippet.length();
        float[] fArr = new float[length];
        this.mDescriptionPaint.getTextWidths(snippet, fArr);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        int iMax = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            if (!Character.isLetter(snippet.charAt(i))) {
                i4 = i;
            }
            float f = fArr[i];
            if (snippet.charAt(i) == '\n') {
                int i5 = i + 1;
                sb.append(snippet.subSequence(i2, i5));
                i4 = i5;
                iMax = Math.max(iMax, i3);
                i3 = 0;
                i2 = i4;
            } else if (i3 + f <= this.DESCRIPTION_MAXWIDTH) {
                i3 = (int) (i3 + f);
            } else {
                boolean z = i2 == i4;
                if (!z) {
                    i = i4;
                }
                sb.append(snippet.subSequence(i2, i));
                sb.append('\n');
                iMax = Math.max(iMax, i3);
                if (z) {
                    i2 = i;
                    i4 = i2;
                    i3 = 0;
                    i--;
                } else {
                    i2 = i;
                    i4 = i2;
                    i3 = 0;
                    i3 = (int) (i3 + f);
                }
            }
            i++;
        }
        if (i != i2) {
            String strSubstring = snippet.substring(i2, i);
            iMax = Math.max(iMax, (int) this.mDescriptionPaint.measureText(strSubstring));
            sb.append(strSubstring);
        }
        String[] strArrSplit = sb.toString().split("\n");
        int iMin = Math.min(Math.max(iMax, (int) this.mDescriptionPaint.measureText(title)), this.DESCRIPTION_MAXWIDTH);
        int iWidth = ((this.mRect.left - (iMin / 2)) - this.DESCRIPTION_BOX_PADDING) + (this.mRect.width() / 2);
        int i6 = iMin + iWidth + (this.DESCRIPTION_BOX_PADDING * 2);
        int i7 = this.mRect.top;
        int length2 = ((i7 - this.DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT) - ((strArrSplit.length + 1) * this.DESCRIPTION_LINE_HEIGHT)) - (this.DESCRIPTION_BOX_PADDING * 2);
        if (projection.getOrientation() != 0.0f) {
            canvas.save();
            canvas.rotate(-projection.getOrientation(), this.mFocusedScreenCoords.x, this.mFocusedScreenCoords.y);
        }
        this.mMarkerBackgroundPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        RectF rectF = new RectF(iWidth - 1, length2 - 1, i6 + 1, i7 + 1);
        int i8 = this.DESCRIPTION_BOX_CORNERWIDTH;
        canvas.drawRoundRect(rectF, i8, i8, this.mDescriptionPaint);
        this.mMarkerBackgroundPaint.setColor(this.mMarkerFocusedBackgroundColor);
        float f2 = iWidth;
        float f3 = i6;
        RectF rectF2 = new RectF(f2, length2, f3, i7);
        int i9 = this.DESCRIPTION_BOX_CORNERWIDTH;
        canvas.drawRoundRect(rectF2, i9, i9, this.mMarkerBackgroundPaint);
        int i10 = this.DESCRIPTION_BOX_PADDING;
        int i11 = iWidth + i10;
        int i12 = i7 - i10;
        for (int length3 = strArrSplit.length - 1; length3 >= 0; length3--) {
            canvas.drawText(strArrSplit[length3].trim(), i11, i12, this.mDescriptionPaint);
            i12 -= this.DESCRIPTION_LINE_HEIGHT;
        }
        canvas.drawText(title, i11, i12 - this.DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT, this.mTitlePaint);
        float f4 = i12;
        canvas.drawLine(f2, f4, f3, f4, this.mDescriptionPaint);
        drawable.setBounds(this.mRect);
        drawable.draw(canvas);
        this.mRect.offset(-this.mFocusedScreenCoords.x, -this.mFocusedScreenCoords.y);
        drawable.setBounds(this.mRect);
        if (projection.getOrientation() != 0.0f) {
            canvas.restore();
        }
    }

    @Override // org.osmdroid.views.overlay.ItemizedIconOverlay, org.osmdroid.views.overlay.ItemizedOverlay, org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        super.onDetach(mapView);
        this.mContext = null;
    }
}
