package org.mockito.internal.creation.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.util.Primitives;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.reflection.AccessibilityChanger;

/* loaded from: classes3.dex */
public class ConstructorInstantiator implements org.mockito.creation.instance.Instantiator {
    private final Object[] constructorArgs;
    private final boolean hasOuterClassInstance;

    public ConstructorInstantiator(boolean z, Object... objArr) {
        this.hasOuterClassInstance = z;
        this.constructorArgs = objArr;
    }

    @Override // org.mockito.creation.instance.Instantiator
    public <T> T newInstance(Class<T> cls) {
        return (T) withParams(cls, this.constructorArgs);
    }

    private <T> T withParams(Class<T> cls, Object... objArr) throws SecurityException {
        LinkedList linkedList = new LinkedList();
        try {
            for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
                if (paramsMatch(constructor.getParameterTypes(), objArr)) {
                    evaluateConstructor(linkedList, constructor);
                }
            }
            if (linkedList.size() == 1) {
                return (T) invokeConstructor(linkedList.get(0), objArr);
            }
            if (linkedList.size() == 0) {
                throw noMatchingConstructor(cls);
            }
            throw multipleMatchingConstructors(cls, linkedList);
        } catch (Exception e) {
            throw paramsException(cls, e);
        }
    }

    private static <T> T invokeConstructor(Constructor<?> constructor, Object... objArr) throws IllegalAccessException, java.lang.InstantiationException, SecurityException, InvocationTargetException {
        new AccessibilityChanger().enableAccess(constructor);
        return (T) constructor.newInstance(objArr);
    }

    private org.mockito.creation.instance.InstantiationException paramsException(Class<?> cls, Exception exc) {
        return new org.mockito.creation.instance.InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Please ensure the target class has " + constructorArgsString() + " and executes cleanly."), exc);
    }

    private String constructorArgTypes() {
        boolean z = this.hasOuterClassInstance;
        String[] strArr = new String[this.constructorArgs.length - (z ? 1 : 0)];
        int i = z ? 1 : 0;
        while (true) {
            Object[] objArr = this.constructorArgs;
            if (i < objArr.length) {
                int i2 = i - (z ? 1 : 0);
                Object obj = objArr[i];
                strArr[i2] = obj == null ? null : obj.getClass().getName();
                i++;
            } else {
                return Arrays.toString(strArr);
            }
        }
    }

    private org.mockito.creation.instance.InstantiationException noMatchingConstructor(Class<?> cls) {
        return new org.mockito.creation.instance.InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Please ensure that the target class has " + constructorArgsString() + (this.hasOuterClassInstance ? " and provided outer instance is correct" : "") + "."), null);
    }

    private String constructorArgsString() {
        Object[] objArr = this.constructorArgs;
        return (objArr.length == 0 || (this.hasOuterClassInstance && objArr.length == 1)) ? "a 0-arg constructor" : "a constructor that matches these argument types: " + constructorArgTypes();
    }

    private org.mockito.creation.instance.InstantiationException multipleMatchingConstructors(Class<?> cls, List<Constructor<?>> list) {
        return new org.mockito.creation.instance.InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Multiple constructors could be matched to arguments of types " + constructorArgTypes() + ":", StringUtil.join("", " - ", list), "If you believe that Mockito could do a better job deciding on which constructor to use, please let us know.", "Ticket 685 contains the discussion and a workaround for ambiguous constructors using inner class.", "See https://github.com/mockito/mockito/issues/685"), null);
    }

    private static boolean paramsMatch(Class<?>[] clsArr, Object[] objArr) {
        if (objArr.length != clsArr.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] == null) {
                if (clsArr[i].isPrimitive()) {
                    return false;
                }
            } else if ((!clsArr[i].isPrimitive() && !clsArr[i].isInstance(objArr[i])) || (clsArr[i].isPrimitive() && !clsArr[i].equals(Primitives.primitiveTypeOf(objArr[i].getClass())))) {
                return false;
            }
        }
        return true;
    }

    private void evaluateConstructor(List<Constructor<?>> list, Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> cls = parameterTypes[i];
            if (!cls.isPrimitive()) {
                Iterator<Constructor<?>> it = list.iterator();
                while (it.hasNext()) {
                    Class<?> cls2 = it.next().getParameterTypes()[i];
                    if (cls != cls2) {
                        if (cls.isAssignableFrom(cls2)) {
                            z = true;
                        } else {
                            z2 = true;
                        }
                    }
                }
            }
        }
        if (!z) {
            list.clear();
        }
        if (z2 || !z) {
            list.add(constructor);
        }
    }
}
