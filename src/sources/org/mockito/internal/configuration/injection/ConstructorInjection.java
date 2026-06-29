package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.FieldInitializer;

/* loaded from: classes3.dex */
public class ConstructorInjection extends MockInjectionStrategy {
    @Override // org.mockito.internal.configuration.injection.MockInjectionStrategy
    public boolean processInjection(Field field, Object obj, Set<Object> set) {
        try {
            return new FieldInitializer(obj, field, new SimpleArgumentResolver(set)).initialize().fieldWasInitializedUsingContructorArgs();
        } catch (MockitoException e) {
            if (e.getCause() instanceof InvocationTargetException) {
                throw Reporter.fieldInitialisationThrewException(field, e.getCause().getCause());
            }
            return false;
        }
    }

    static class SimpleArgumentResolver implements FieldInitializer.ConstructorArgumentResolver {
        final Set<Object> objects;

        public SimpleArgumentResolver(Set<Object> set) {
            this.objects = set;
        }

        @Override // org.mockito.internal.util.reflection.FieldInitializer.ConstructorArgumentResolver
        public Object[] resolveTypeInstances(Class<?>... clsArr) {
            ArrayList arrayList = new ArrayList(clsArr.length);
            for (Class<?> cls : clsArr) {
                arrayList.add(objectThatIsAssignableFrom(cls));
            }
            return arrayList.toArray();
        }

        private Object objectThatIsAssignableFrom(Class<?> cls) {
            for (Object obj : this.objects) {
                if (cls.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
            }
            return null;
        }
    }
}
