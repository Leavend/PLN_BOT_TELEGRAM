package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class FieldCopier {
    public <T> void copyValue(T t, T t2, Field field) throws IllegalAccessException, IllegalArgumentException {
        field.set(t2, field.get(t));
    }
}
