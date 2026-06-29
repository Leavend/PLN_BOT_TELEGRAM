package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
final class ToolHandlerRegistry<T> {
    private final T mDefault;
    private final List<T> mHandlers = Arrays.asList(null, null, null, null, null);

    ToolHandlerRegistry(T t) {
        Preconditions.checkArgument(t != null);
        this.mDefault = t;
    }

    void set(int i, T t) {
        Preconditions.checkArgument(i >= 0 && i <= 4);
        Preconditions.checkState(this.mHandlers.get(i) == null);
        this.mHandlers.set(i, t);
    }

    T get(MotionEvent motionEvent) {
        T t = this.mHandlers.get(motionEvent.getToolType(0));
        return t != null ? t : this.mDefault;
    }
}
