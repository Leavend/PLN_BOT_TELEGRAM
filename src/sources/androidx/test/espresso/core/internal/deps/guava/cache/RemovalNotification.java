package androidx.test.espresso.core.internal.deps.guava.cache;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import java.util.AbstractMap;

/* loaded from: classes5.dex */
public final class RemovalNotification extends AbstractMap.SimpleImmutableEntry {
    private static final long serialVersionUID = 0;
    private final RemovalCause cause;

    private RemovalNotification(Object obj, Object obj2, RemovalCause removalCause) {
        super(obj, obj2);
        this.cause = (RemovalCause) Preconditions.checkNotNull(removalCause);
    }

    public static RemovalNotification create(Object obj, Object obj2, RemovalCause removalCause) {
        return new RemovalNotification(obj, obj2, removalCause);
    }
}
