package io.github.hyuwah.draggableviewlib;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: Draggable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/github/hyuwah/draggableviewlib/Draggable;", "", "()V", "DRAG_TOLERANCE", "", "DURATION_MILLIS", "", "STICKY", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
/* loaded from: classes2.dex */
public final class Draggable {
    public static final int DRAG_TOLERANCE = 16;
    public static final long DURATION_MILLIS = 250;
    public static final Draggable INSTANCE = new Draggable();

    /* compiled from: Draggable.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lio/github/hyuwah/draggableviewlib/Draggable$STICKY;", "", "(Ljava/lang/String;I)V", "NONE", "AXIS_X", "AXIS_Y", "AXIS_XY", "draggableviewlib_release"}, k = 1, mv = {1, 4, 1})
    @Deprecated(message = "Use DraggableView.Mode", replaceWith = @ReplaceWith(expression = "DraggableView.Mode", imports = {"io.github.hyuwah.draggableviewlib.DraggableView.Mode"}))
    public enum STICKY {
        NONE,
        AXIS_X,
        AXIS_Y,
        AXIS_XY
    }

    private Draggable() {
    }
}
