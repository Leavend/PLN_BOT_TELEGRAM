package androidx.test.internal.platform.reflect;

import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class ReflectiveField<T> {
    private final String className;
    private Field field;
    private final String fieldName;
    private boolean initialized = false;

    public ReflectiveField(String className, String fieldName) {
        this.className = className;
        this.fieldName = fieldName;
    }

    public T get(Object obj) throws ReflectionException {
        try {
            initIfNecessary();
            return (T) this.field.get(obj);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            throw new ReflectionException(e);
        }
    }

    private synchronized void initIfNecessary() throws NoSuchFieldException, ClassNotFoundException {
        if (this.initialized) {
            return;
        }
        Field declaredField = Class.forName(this.className).getDeclaredField(this.fieldName);
        this.field = declaredField;
        declaredField.setAccessible(true);
        this.initialized = true;
    }
}
