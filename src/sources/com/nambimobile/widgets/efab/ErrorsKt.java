package com.nambimobile.widgets.efab;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Errors.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0007"}, d2 = {"illegalArg", "", "message", "", "cause", "", "illegalState", "expandable-fab_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ErrorsKt {
    public static /* synthetic */ Void illegalState$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return illegalState(str, th);
    }

    public static final /* synthetic */ Void illegalState(String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new IllegalStateException(message, th);
    }

    public static /* synthetic */ Void illegalArg$default(String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return illegalArg(str, th);
    }

    public static final /* synthetic */ Void illegalArg(String message, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new IllegalArgumentException(message, th);
    }
}
