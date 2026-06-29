package org.mockito.internal.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.MockUtil;

/* loaded from: classes3.dex */
public class FieldInitializer {
    private final Field field;
    private final Object fieldOwner;
    private final ConstructorInstantiator instantiator;

    public interface ConstructorArgumentResolver {
        Object[] resolveTypeInstances(Class<?>... clsArr);
    }

    private interface ConstructorInstantiator {
        FieldInitializationReport instantiate();
    }

    public FieldInitializer(Object obj, Field field) {
        this(obj, field, new NoArgConstructorInstantiator(obj, field));
    }

    public FieldInitializer(Object obj, Field field, ConstructorArgumentResolver constructorArgumentResolver) {
        this(obj, field, new ParameterizedConstructorInstantiator(obj, field, constructorArgumentResolver));
    }

    private FieldInitializer(Object obj, Field field, ConstructorInstantiator constructorInstantiator) {
        if (new FieldReader(obj, field).isNull()) {
            checkNotLocal(field);
            checkNotInner(field);
            checkNotInterface(field);
            checkNotEnum(field);
            checkNotAbstract(field);
        }
        this.fieldOwner = obj;
        this.field = field;
        this.instantiator = constructorInstantiator;
    }

    public FieldInitializationReport initialize() throws SecurityException {
        AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
        accessibilityChanger.enableAccess(this.field);
        try {
            try {
                return acquireFieldInstance();
            } catch (IllegalAccessException e) {
                throw new MockitoException("Problems initializing field '" + this.field.getName() + "' of type '" + this.field.getType().getSimpleName() + "'", e);
            }
        } finally {
            accessibilityChanger.safelyDisableAccess(this.field);
        }
    }

    private void checkNotLocal(Field field) {
        if (field.getType().isLocalClass()) {
            throw new MockitoException("the type '" + field.getType().getSimpleName() + "' is a local class.");
        }
    }

    private void checkNotInner(Field field) {
        Class<?> type = field.getType();
        if (type.isMemberClass() && !Modifier.isStatic(type.getModifiers())) {
            throw new MockitoException("the type '" + type.getSimpleName() + "' is an inner non static class.");
        }
    }

    private void checkNotInterface(Field field) {
        if (field.getType().isInterface()) {
            throw new MockitoException("the type '" + field.getType().getSimpleName() + "' is an interface.");
        }
    }

    private void checkNotAbstract(Field field) {
        if (Modifier.isAbstract(field.getType().getModifiers())) {
            throw new MockitoException("the type '" + field.getType().getSimpleName() + "' is an abstract class.");
        }
    }

    private void checkNotEnum(Field field) {
        if (field.getType().isEnum()) {
            throw new MockitoException("the type '" + field.getType().getSimpleName() + "' is an enum.");
        }
    }

    private FieldInitializationReport acquireFieldInstance() throws IllegalAccessException, IllegalArgumentException {
        Object obj = this.field.get(this.fieldOwner);
        if (obj != null) {
            return new FieldInitializationReport(obj, false, false);
        }
        return this.instantiator.instantiate();
    }

    static class NoArgConstructorInstantiator implements ConstructorInstantiator {
        private final Field field;
        private final Object testClass;

        NoArgConstructorInstantiator(Object obj, Field field) {
            this.testClass = obj;
            this.field = field;
        }

        @Override // org.mockito.internal.util.reflection.FieldInitializer.ConstructorInstantiator
        public FieldInitializationReport instantiate() {
            AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
            Constructor<?> declaredConstructor = null;
            try {
                try {
                    try {
                        declaredConstructor = this.field.getType().getDeclaredConstructor(new Class[0]);
                        accessibilityChanger.enableAccess(declaredConstructor);
                        FieldSetter.setField(this.testClass, this.field, declaredConstructor.newInstance(new Object[0]));
                        return new FieldInitializationReport(this.field.get(this.testClass), true, false);
                    } catch (IllegalAccessException e) {
                        throw new MockitoException("IllegalAccessException (see the stack trace for cause): " + e.toString(), e);
                    } catch (InvocationTargetException e2) {
                        throw new MockitoException("the default constructor of type '" + this.field.getType().getSimpleName() + "' has raised an exception (see the stack trace for cause): " + e2.getTargetException().toString(), e2);
                    }
                } catch (InstantiationException e3) {
                    throw new MockitoException("InstantiationException (see the stack trace for cause): " + e3.toString(), e3);
                } catch (NoSuchMethodException e4) {
                    throw new MockitoException("the type '" + this.field.getType().getSimpleName() + "' has no default constructor", e4);
                }
            } finally {
                if (declaredConstructor != null) {
                    accessibilityChanger.safelyDisableAccess(declaredConstructor);
                }
            }
        }
    }

    static class ParameterizedConstructorInstantiator implements ConstructorInstantiator {
        private final ConstructorArgumentResolver argResolver;
        private final Comparator<Constructor<?>> byParameterNumber = new Comparator<Constructor<?>>() { // from class: org.mockito.internal.util.reflection.FieldInitializer.ParameterizedConstructorInstantiator.1
            @Override // java.util.Comparator
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                int length = constructor2.getParameterTypes().length - constructor.getParameterTypes().length;
                if (length != 0) {
                    return length;
                }
                return countMockableParams(constructor2) - countMockableParams(constructor);
            }

            private int countMockableParams(Constructor<?> constructor) {
                int i = 0;
                for (Class<?> cls : constructor.getParameterTypes()) {
                    if (MockUtil.typeMockabilityOf(cls).mockable()) {
                        i++;
                    }
                }
                return i;
            }
        };
        private final Field field;
        private final Object testClass;

        ParameterizedConstructorInstantiator(Object obj, Field field, ConstructorArgumentResolver constructorArgumentResolver) {
            this.testClass = obj;
            this.field = field;
            this.argResolver = constructorArgumentResolver;
        }

        @Override // org.mockito.internal.util.reflection.FieldInitializer.ConstructorInstantiator
        public FieldInitializationReport instantiate() {
            AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
            Constructor<?> constructorBiggestConstructor = null;
            try {
                try {
                    try {
                        try {
                            constructorBiggestConstructor = biggestConstructor(this.field.getType());
                            accessibilityChanger.enableAccess(constructorBiggestConstructor);
                            FieldSetter.setField(this.testClass, this.field, constructorBiggestConstructor.newInstance(this.argResolver.resolveTypeInstances(constructorBiggestConstructor.getParameterTypes())));
                            return new FieldInitializationReport(this.field.get(this.testClass), false, true);
                        } catch (InvocationTargetException e) {
                            throw new MockitoException("the constructor of type '" + this.field.getType().getSimpleName() + "' has raised an exception (see the stack trace for cause): " + e.getTargetException().toString(), e);
                        }
                    } catch (IllegalArgumentException e2) {
                        throw new MockitoException("internal error : argResolver provided incorrect types for constructor " + constructorBiggestConstructor + " of type " + this.field.getType().getSimpleName(), e2);
                    }
                } catch (IllegalAccessException e3) {
                    throw new MockitoException("IllegalAccessException (see the stack trace for cause): " + e3.toString(), e3);
                } catch (InstantiationException e4) {
                    throw new MockitoException("InstantiationException (see the stack trace for cause): " + e4.toString(), e4);
                }
            } finally {
                if (constructorBiggestConstructor != null) {
                    accessibilityChanger.safelyDisableAccess(constructorBiggestConstructor);
                }
            }
        }

        private void checkParameterized(Constructor<?> constructor, Field field) {
            if (constructor.getParameterTypes().length == 0) {
                throw new MockitoException("the field " + field.getName() + " of type " + field.getType() + " has no parameterized constructor");
            }
        }

        private Constructor<?> biggestConstructor(Class<?> cls) {
            List listAsList = Arrays.asList(cls.getDeclaredConstructors());
            Collections.sort(listAsList, this.byParameterNumber);
            Constructor<?> constructor = (Constructor) listAsList.get(0);
            checkParameterized(constructor, this.field);
            return constructor;
        }
    }
}
