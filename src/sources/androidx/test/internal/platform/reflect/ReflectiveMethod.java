package androidx.test.internal.platform.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class ReflectiveMethod<T> {
    private final String className;
    private boolean initialized = false;
    private Method method;
    private final String methodName;
    private final Class<?>[] paramTypes;

    public ReflectiveMethod(String className, String methodName, Class<?>... paramTypes) {
        this.className = className;
        this.paramTypes = paramTypes;
        this.methodName = methodName;
    }

    public T invoke(Object obj, Object... objArr) throws ReflectionException {
        try {
            initIfNecessary();
            return (T) this.method.invoke(obj, objArr);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ReflectionException(e);
        }
    }

    public T invokeStatic(Object... paramValues) throws ReflectionException {
        return invoke(null, paramValues);
    }

    private synchronized void initIfNecessary() throws NoSuchMethodException, ClassNotFoundException {
        if (this.initialized) {
            return;
        }
        Method declaredMethod = Class.forName(this.className).getDeclaredMethod(this.methodName, this.paramTypes);
        this.method = declaredMethod;
        declaredMethod.setAccessible(true);
        this.initialized = true;
    }
}
