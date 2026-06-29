package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.util.Set;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.reflection.FieldReader;
import org.mockito.internal.util.reflection.FieldSetter;

/* loaded from: classes3.dex */
public class SpyOnInjectedFieldsHandler extends MockInjectionStrategy {
    @Override // org.mockito.internal.configuration.injection.MockInjectionStrategy
    protected boolean processInjection(Field field, Object obj, Set<Object> set) {
        FieldReader fieldReader = new FieldReader(obj, field);
        if (fieldReader.isNull() || !field.isAnnotationPresent(Spy.class)) {
            return false;
        }
        try {
            Object obj2 = fieldReader.read();
            if (MockUtil.isMock(obj2)) {
                Mockito.reset(obj2);
            } else {
                FieldSetter.setField(obj, field, Mockito.mock(obj2.getClass(), Mockito.withSettings().spiedInstance(obj2).defaultAnswer(Mockito.CALLS_REAL_METHODS).name(field.getName())));
            }
            return false;
        } catch (Exception e) {
            throw new MockitoException("Problems initiating spied field " + field.getName(), e);
        }
    }
}
