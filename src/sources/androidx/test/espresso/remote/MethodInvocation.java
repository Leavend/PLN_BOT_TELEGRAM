package androidx.test.espresso.remote;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.cache.Cache;
import androidx.test.espresso.core.internal.deps.guava.cache.CacheBuilder;
import androidx.test.internal.util.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes5.dex */
final class MethodInvocation {
    private static final String TAG = "MethodInvocation";
    private static final Cache<MethodKey, Method> methodCache = CacheBuilder.newBuilder().maximumSize(256).build();
    private final Class<?> clazz;
    private final Object instance;
    private final String methodName;
    private final Class<?>[] parameterTypes;

    public MethodInvocation(Class<?> cls, Object obj, String str, Class<?>... clsArr) {
        this.clazz = (Class) Preconditions.checkNotNull(cls, "clazz cannot be null!");
        this.instance = obj;
        Preconditions.checkArgument((str == null || str.isEmpty()) ? false : true, "methodName cannot be null or empty");
        this.methodName = str;
        this.parameterTypes = clsArr;
    }

    private static Method getDeclaredMethod(MethodKey methodKey) throws NoSuchMethodException {
        return getMethodInternal(methodKey, true);
    }

    private static Method getMethod(MethodKey methodKey) throws NoSuchMethodException {
        return getMethodInternal(methodKey, false);
    }

    private static Method getMethodInternal(MethodKey methodKey, boolean z) throws NoSuchMethodException {
        Cache<MethodKey, Method> cache = methodCache;
        Method method = (Method) cache.getIfPresent(methodKey);
        if (method != null) {
            LogUtil.logDebug(TAG, "Cache hit for method: %s#%s(%s).", methodKey.type.getSimpleName(), methodKey.methodName, Arrays.toString(methodKey.parameterTypes));
            return method;
        }
        LogUtil.logDebug(TAG, "Cache miss for method: %s#%s(%s). Loading into cache.", methodKey.type.getSimpleName(), methodKey.methodName, Arrays.toString(methodKey.parameterTypes));
        Method declaredMethod = z ? methodKey.type.getDeclaredMethod(methodKey.methodName, methodKey.parameterTypes) : methodKey.type.getMethod(methodKey.methodName, methodKey.parameterTypes);
        cache.put(methodKey, declaredMethod);
        return declaredMethod;
    }

    public static void invalidateCache() {
        methodCache.invalidateAll();
    }

    public Object invokeDeclaredMethod(Object... objArr) {
        try {
            return invokeMethodExplosively(getDeclaredMethod(new MethodKey(this.clazz, this.methodName, this.parameterTypes)), objArr);
        } catch (NoSuchMethodException e) {
            throw new RemoteProtocolException(String.format(Locale.ROOT, "No method: %s(%s) found for clazz: %s Available methods: %s", this.methodName, Arrays.asList(this.parameterTypes), this.clazz.getName(), Arrays.asList(this.clazz.getDeclaredMethods())), e);
        }
    }

    public Object invokeMethod(Object... objArr) {
        try {
            return invokeMethodExplosively(getMethod(new MethodKey(this.clazz, this.methodName, this.parameterTypes)), objArr);
        } catch (NoSuchMethodException e) {
            throw new RemoteProtocolException(String.format(Locale.ROOT, "No method: %s found for clazz: %s. Available methods: %s", this.methodName, this.clazz.getName(), Arrays.asList(this.clazz.getMethods())), e);
        }
    }

    private static final class MethodKey {
        private final String methodName;
        private final Class<?>[] parameterTypes;
        private final Class<?> type;

        public MethodKey(Class<?> cls, String str, Class<?>[] clsArr) {
            this.type = cls;
            this.methodName = str;
            this.parameterTypes = clsArr;
        }

        public int hashCode() {
            return (((this.type.hashCode() * 31) + this.methodName.hashCode()) * 31) + Arrays.hashCode(this.parameterTypes);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MethodKey methodKey = (MethodKey) obj;
            if (this.type.equals(methodKey.type) && this.methodName.equals(methodKey.methodName)) {
                return Arrays.equals(this.parameterTypes, methodKey.parameterTypes);
            }
            return false;
        }
    }

    private Object invokeMethodExplosively(Method method, Object... objArr) {
        try {
            try {
                try {
                    method.setAccessible(true);
                    return method.invoke(this.instance, objArr);
                } catch (IllegalAccessException e) {
                    throw new RemoteProtocolException(String.format(Locale.ROOT, "Cannot create instance of %s", this.clazz.getName()), e);
                }
            } catch (SecurityException e2) {
                throw new RemoteProtocolException(String.format(Locale.ROOT, "Method not accessible: %s", method.getName()), e2);
            } catch (InvocationTargetException e3) {
                throw new RemoteProtocolException(String.format(Locale.ROOT, "Cannot invoke method %s with args [%s] on builder %s", method, Arrays.toString(objArr), this.clazz.getName()), e3);
            }
        } finally {
            LogUtil.logDebug(TAG, "%s.invokeMethodExplosively(%s,%s)", this.clazz.getSimpleName(), this.methodName, Arrays.toString(objArr));
        }
    }
}
