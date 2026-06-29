package androidx.test.internal.util;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
/* loaded from: classes5.dex */
public class ReflectionUtil {
    private static final String TAG = "ReflectionUtil";

    public static class ReflectionParams {
        final Class<?> type;
        final Object value;

        public ReflectionParams(Class<?> type, Object value) {
            this.type = type;
            this.value = value;
        }

        public static Class<?>[] getTypes(ReflectionParams[] params) {
            Class<?>[] clsArr = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                clsArr[i] = params[i].type;
            }
            return clsArr;
        }

        public static Object[] getValues(ReflectionParams[] params) {
            Object[] objArr = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                objArr[i] = params[i].value;
            }
            return objArr;
        }
    }

    public static class ReflectionException extends Exception {
        ReflectionException(Exception cause) {
            super("Reflective call failed", cause);
        }
    }

    public static Object callStaticMethod(String className, String methodName, ReflectionParams... params) throws ReflectionException {
        try {
            return callStaticMethod(Class.forName(className), methodName, params);
        } catch (ClassNotFoundException e) {
            throw new ReflectionException(e);
        }
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, ReflectionParams... params) throws ReflectionException, NoSuchMethodException, SecurityException {
        Log.d(TAG, "Attempting to reflectively call: " + methodName);
        try {
            Class<?>[] types = ReflectionParams.getTypes(params);
            Object[] values = ReflectionParams.getValues(params);
            Method declaredMethod = clazz.getDeclaredMethod(methodName, types);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, values);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ReflectionException(e);
        }
    }
}
