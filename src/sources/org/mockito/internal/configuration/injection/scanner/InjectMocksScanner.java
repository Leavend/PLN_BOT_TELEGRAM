package org.mockito.internal.configuration.injection.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.exceptions.Reporter;

/* loaded from: classes3.dex */
public class InjectMocksScanner {
    private final Class<?> clazz;

    public InjectMocksScanner(Class<?> cls) {
        this.clazz = cls;
    }

    public void addTo(Set<Field> set) {
        set.addAll(scan());
    }

    private Set<Field> scan() {
        HashSet hashSet = new HashSet();
        for (Field field : this.clazz.getDeclaredFields()) {
            if (field.getAnnotation(InjectMocks.class) != null) {
                assertNoAnnotations(field, Mock.class, Captor.class);
                hashSet.add(field);
            }
        }
        return hashSet;
    }

    private static void assertNoAnnotations(Field field, Class<? extends Annotation>... clsArr) {
        for (Class<? extends Annotation> cls : clsArr) {
            if (field.isAnnotationPresent(cls)) {
                throw Reporter.unsupportedCombinationOfAnnotations(cls.getSimpleName(), "InjectMocks");
            }
        }
    }
}
