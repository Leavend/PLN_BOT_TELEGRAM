package org.mockito.internal.junit.util;

import java.lang.reflect.Field;
import org.junit.runner.notification.Failure;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;

@Deprecated
/* loaded from: classes3.dex */
public class JUnitFailureHacker {
    public void appendWarnings(Failure failure, String str) throws IllegalAccessException, IllegalArgumentException {
        if (isEmpty(str)) {
            return;
        }
        Throwable th = (Throwable) getInternalState(failure, "fThrownException");
        ExceptionIncludingMockitoWarnings exceptionIncludingMockitoWarnings = new ExceptionIncludingMockitoWarnings("contains both: actual test failure *and* Mockito warnings.\n" + str + "\n *** The actual failure is because of: ***\n", th);
        exceptionIncludingMockitoWarnings.setStackTrace(th.getStackTrace());
        setInternalState(failure, "fThrownException", exceptionIncludingMockitoWarnings);
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    private static Object getInternalState(Object obj, String str) {
        try {
            Field fieldFromHierarchy = getFieldFromHierarchy(obj.getClass(), str);
            fieldFromHierarchy.setAccessible(true);
            return fieldFromHierarchy.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get internal state on a private field. Please report to mockito mailing list.", e);
        }
    }

    private static void setInternalState(Object obj, String str, Object obj2) throws IllegalAccessException, IllegalArgumentException {
        try {
            Field fieldFromHierarchy = getFieldFromHierarchy(obj.getClass(), str);
            fieldFromHierarchy.setAccessible(true);
            fieldFromHierarchy.set(obj, obj2);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set internal state on a private field. Please report to mockito mailing list.", e);
        }
    }

    private static Field getFieldFromHierarchy(Class<?> cls, String str) {
        Field field = getField(cls, str);
        while (field == null && cls != Object.class) {
            cls = cls.getSuperclass();
            field = getField(cls, str);
        }
        if (field != null) {
            return field;
        }
        throw new RuntimeException("You want me to get this field: '" + str + "' on this class: '" + cls.getSimpleName() + "' but this field is not declared within the hierarchy of this class!");
    }

    private static Field getField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
