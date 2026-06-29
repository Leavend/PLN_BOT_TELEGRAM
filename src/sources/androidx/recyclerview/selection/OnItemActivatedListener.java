package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.recyclerview.selection.ItemDetailsLookup;

/* loaded from: classes5.dex */
public interface OnItemActivatedListener<K> {
    boolean onItemActivated(ItemDetailsLookup.ItemDetails<K> itemDetails, MotionEvent motionEvent);
}
