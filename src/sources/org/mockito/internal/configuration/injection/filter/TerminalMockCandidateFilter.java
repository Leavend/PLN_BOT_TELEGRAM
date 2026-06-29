package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.BeanPropertySetter;
import org.mockito.internal.util.reflection.FieldSetter;

/* loaded from: classes3.dex */
public class TerminalMockCandidateFilter implements MockCandidateFilter {
    @Override // org.mockito.internal.configuration.injection.filter.MockCandidateFilter
    public OngoingInjector filterCandidate(Collection<Object> collection, final Field field, List<Field> list, final Object obj) {
        if (collection.size() == 1) {
            final Object next = collection.iterator().next();
            return new OngoingInjector() { // from class: org.mockito.internal.configuration.injection.filter.TerminalMockCandidateFilter.1
                @Override // org.mockito.internal.configuration.injection.filter.OngoingInjector
                public Object thenInject() throws IllegalAccessException {
                    try {
                        if (!new BeanPropertySetter(obj, field).set(next)) {
                            FieldSetter.setField(obj, field, next);
                        }
                        return next;
                    } catch (RuntimeException e) {
                        throw Reporter.cannotInjectDependency(field, next, e);
                    }
                }
            };
        }
        return OngoingInjector.nop;
    }
}
