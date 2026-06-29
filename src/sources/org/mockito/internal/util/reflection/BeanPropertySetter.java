package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* loaded from: classes3.dex */
public class BeanPropertySetter {
    private static final String SET_PREFIX = "set";
    private final Field field;
    private final boolean reportNoSetterFound;
    private final Object target;

    public BeanPropertySetter(Object obj, Field field, boolean z) {
        this.field = field;
        this.target = obj;
        this.reportNoSetterFound = z;
    }

    public BeanPropertySetter(Object obj, Field field) {
        this(obj, field, false);
    }

    public boolean set(Object obj) {
        AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
        Method method = null;
        try {
            try {
                method = this.target.getClass().getMethod(setterName(this.field.getName()), this.field.getType());
                accessibilityChanger.enableAccess(method);
                method.invoke(this.target, obj);
                if (method != null) {
                    accessibilityChanger.safelyDisableAccess(method);
                }
                return true;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Access not authorized on field '" + this.field + "' of object '" + this.target + "' with value: '" + obj + "'", e);
            } catch (NoSuchMethodException unused) {
                reportNoSetterFound();
                if (method != null) {
                    accessibilityChanger.safelyDisableAccess(method);
                }
                reportNoSetterFound();
                return false;
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Setter '" + method + "' of '" + this.target + "' with value '" + obj + "' threw exception : '" + e2.getTargetException() + "'", e2);
            }
        } catch (Throwable th) {
            if (method != null) {
                accessibilityChanger.safelyDisableAccess(method);
            }
            throw th;
        }
    }

    private String setterName(String str) {
        return SET_PREFIX + str.substring(0, 1).toUpperCase(Locale.ENGLISH) + str.substring(1);
    }

    private void reportNoSetterFound() {
        if (this.reportNoSetterFound) {
            throw new RuntimeException("Problems setting value on object: [" + this.target + "] for property : [" + this.field.getName() + "], setter not found");
        }
    }
}
