package org.mockito.internal.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.StringUtil;
import org.mockito.plugins.AnnotationEngine;

/* loaded from: classes3.dex */
public class SpyAnnotationEngine implements AnnotationEngine, org.mockito.configuration.AnnotationEngine {
    @Override // org.mockito.plugins.AnnotationEngine
    public void process(Class<?> cls, Object obj) throws IllegalAccessException, IllegalArgumentException {
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(Spy.class) && !field.isAnnotationPresent(InjectMocks.class)) {
                assertNoIncompatibleAnnotations(Spy.class, field, Mock.class, Captor.class);
                field.setAccessible(true);
                try {
                    Object obj2 = field.get(obj);
                    if (MockUtil.isMock(obj2)) {
                        Mockito.reset(obj2);
                    } else if (obj2 != null) {
                        field.set(obj, spyInstance(field, obj2));
                    } else {
                        field.set(obj, spyNewInstance(obj, field));
                    }
                } catch (Exception e) {
                    throw new MockitoException("Unable to initialize @Spy annotated field '" + field.getName() + "'.\n" + e.getMessage(), e);
                }
            }
        }
    }

    private static Object spyInstance(Field field, Object obj) {
        return Mockito.mock(obj.getClass(), Mockito.withSettings().spiedInstance(obj).defaultAnswer(Mockito.CALLS_REAL_METHODS).name(field.getName()));
    }

    private static Object spyNewInstance(Object obj, Field field) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        MockSettings mockSettingsName = Mockito.withSettings().defaultAnswer(Mockito.CALLS_REAL_METHODS).name(field.getName());
        Class<?> type = field.getType();
        if (type.isInterface()) {
            return Mockito.mock(type, mockSettingsName.useConstructor(new Object[0]));
        }
        int modifiers = type.getModifiers();
        if (typeIsPrivateAbstractInnerClass(type, modifiers)) {
            throw new MockitoException(StringUtil.join("@Spy annotation can't initialize private abstract inner classes.", "  inner class: '" + type.getSimpleName() + "'", "  outer class: '" + type.getEnclosingClass().getSimpleName() + "'", "", "You should augment the visibility of this inner class"));
        }
        if (typeIsNonStaticInnerClass(type, modifiers)) {
            Class<?> enclosingClass = type.getEnclosingClass();
            if (!enclosingClass.isInstance(obj)) {
                throw new MockitoException(StringUtil.join("@Spy annotation can only initialize inner classes declared in the test.", "  inner class: '" + type.getSimpleName() + "'", "  outer class: '" + enclosingClass.getSimpleName() + "'", ""));
            }
            return Mockito.mock(type, mockSettingsName.useConstructor(new Object[0]).outerInstance(obj));
        }
        Constructor<?> constructorNoArgConstructorOf = noArgConstructorOf(type);
        if (Modifier.isPrivate(constructorNoArgConstructorOf.getModifiers())) {
            constructorNoArgConstructorOf.setAccessible(true);
            return Mockito.mock(type, mockSettingsName.spiedInstance(constructorNoArgConstructorOf.newInstance(new Object[0])));
        }
        return Mockito.mock(type, mockSettingsName.useConstructor(new Object[0]));
    }

    private static Constructor<?> noArgConstructorOf(Class<?> cls) {
        try {
            return cls.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException unused) {
            throw new MockitoException("Please ensure that the type '" + cls.getSimpleName() + "' has a no-arg constructor.");
        }
    }

    private static boolean typeIsNonStaticInnerClass(Class<?> cls, int i) {
        return (Modifier.isStatic(i) || cls.getEnclosingClass() == null) ? false : true;
    }

    private static boolean typeIsPrivateAbstractInnerClass(Class<?> cls, int i) {
        return Modifier.isPrivate(i) && Modifier.isAbstract(i) && cls.getEnclosingClass() != null;
    }

    private static void assertNoIncompatibleAnnotations(Class<? extends Annotation> cls, Field field, Class<? extends Annotation>... clsArr) {
        for (Class<? extends Annotation> cls2 : clsArr) {
            if (field.isAnnotationPresent(cls2)) {
                throw Reporter.unsupportedCombinationOfAnnotations(cls.getSimpleName(), cls2.getSimpleName());
            }
        }
    }
}
