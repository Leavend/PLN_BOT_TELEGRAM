package androidx.test.espresso.core.internal.deps.dagger.internal;

/* loaded from: classes5.dex */
public final class Preconditions {
    public static Object checkNotNull(Object obj) {
        obj.getClass();
        return obj;
    }

    public static Object checkNotNullFromProvides(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
}
