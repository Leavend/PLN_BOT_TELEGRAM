package androidx.test.espresso.core.internal.deps.guava.cache;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes5.dex */
public abstract class CacheLoader {

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }

    public abstract Object load(Object obj) throws Exception;

    public ListenableFuture reload(Object obj, Object obj2) throws Exception {
        throw null;
    }
}
