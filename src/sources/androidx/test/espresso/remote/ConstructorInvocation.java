package androidx.test.espresso.remote;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.cache.Cache;
import androidx.test.espresso.core.internal.deps.guava.cache.CacheBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class ConstructorInvocation {
    private static final String TAG = "ConstructorInvocation";
    private static final Cache<ConstructorKey, Constructor<?>> constructorCache = CacheBuilder.newBuilder().maximumSize(256).build();
    private final Class<? extends Annotation> annotationClass;
    private final Class<?> clazz;
    private final Class<?>[] parameterTypes;

    public ConstructorInvocation(Class<?> cls, Class<? extends Annotation> cls2, Class<?>... clsArr) {
        this.clazz = (Class) Preconditions.checkNotNull(cls, "clazz cannot be null!");
        this.annotationClass = cls2;
        this.parameterTypes = clsArr;
    }

    static void invalidateCache() {
        constructorCache.invalidateAll();
    }

    public Object invokeConstructor(Object... objArr) {
        return invokeConstructorExplosively(objArr);
    }

    private static final class ConstructorKey {
        private final Class<?>[] parameterTypes;
        private final Class<?> type;

        public ConstructorKey(Class<?> cls, Class<?>[] clsArr) {
            this.type = cls;
            this.parameterTypes = clsArr;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + Arrays.hashCode(this.parameterTypes);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ConstructorKey constructorKey = (ConstructorKey) obj;
            if (this.type.equals(constructorKey.type)) {
                return Arrays.equals(this.parameterTypes, constructorKey.parameterTypes);
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0052 A[Catch: SecurityException -> 0x005b, InvocationTargetException -> 0x005d, all -> 0x00ad, NoSuchMethodException -> 0x00c6, InstantiationException -> 0x00eb, IllegalAccessException -> 0x0102, TRY_ENTER, TryCatch #7 {NoSuchMethodException -> 0x00c6, blocks: (B:3:0x0013, B:5:0x001d, B:7:0x0036, B:9:0x0040, B:15:0x0052, B:23:0x0065, B:27:0x008e, B:25:0x0078), top: B:51:0x0013, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Object invokeConstructorExplosively(java.lang.Object... r14) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.test.espresso.remote.ConstructorInvocation.invokeConstructorExplosively(java.lang.Object[]):java.lang.Object");
    }
}
