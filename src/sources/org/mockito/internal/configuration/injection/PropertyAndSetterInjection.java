package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.injection.filter.MockCandidateFilter;
import org.mockito.internal.configuration.injection.filter.NameBasedCandidateFilter;
import org.mockito.internal.configuration.injection.filter.TerminalMockCandidateFilter;
import org.mockito.internal.configuration.injection.filter.TypeBasedCandidateFilter;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.internal.util.collections.Sets;
import org.mockito.internal.util.reflection.FieldInitializationReport;
import org.mockito.internal.util.reflection.FieldInitializer;
import org.mockito.internal.util.reflection.SuperTypesLastSorter;

/* loaded from: classes3.dex */
public class PropertyAndSetterInjection extends MockInjectionStrategy {
    private final MockCandidateFilter mockCandidateFilter = new TypeBasedCandidateFilter(new NameBasedCandidateFilter(new TerminalMockCandidateFilter()));
    private final ListUtil.Filter<Field> notFinalOrStatic = new ListUtil.Filter<Field>() { // from class: org.mockito.internal.configuration.injection.PropertyAndSetterInjection.1
        @Override // org.mockito.internal.util.collections.ListUtil.Filter
        public boolean isOut(Field field) {
            return Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers());
        }
    };

    @Override // org.mockito.internal.configuration.injection.MockInjectionStrategy
    public boolean processInjection(Field field, Object obj, Set<Object> set) {
        FieldInitializationReport fieldInitializationReportInitializeInjectMocksField = initializeInjectMocksField(field, obj);
        Object objFieldInstance = fieldInitializationReportInitializeInjectMocksField.fieldInstance();
        boolean zInjectMockCandidates = false;
        for (Class<?> clsFieldClass = fieldInitializationReportInitializeInjectMocksField.fieldClass(); clsFieldClass != Object.class; clsFieldClass = clsFieldClass.getSuperclass()) {
            zInjectMockCandidates |= injectMockCandidates(clsFieldClass, objFieldInstance, Sets.newMockSafeHashSet(set));
        }
        return zInjectMockCandidates;
    }

    private FieldInitializationReport initializeInjectMocksField(Field field, Object obj) {
        try {
            return new FieldInitializer(obj, field).initialize();
        } catch (MockitoException e) {
            if (e.getCause() instanceof InvocationTargetException) {
                throw Reporter.fieldInitialisationThrewException(field, e.getCause().getCause());
            }
            throw Reporter.cannotInitializeForInjectMocksAnnotation(field.getName(), e.getMessage());
        }
    }

    private boolean injectMockCandidates(Class<?> cls, Object obj, Set<Object> set) {
        List<Field> listOrderedInstanceFieldsFrom = orderedInstanceFieldsFrom(cls);
        boolean zInjectMockCandidatesOnFields = injectMockCandidatesOnFields(set, obj, false, listOrderedInstanceFieldsFrom);
        return injectMockCandidatesOnFields(set, obj, zInjectMockCandidatesOnFields, listOrderedInstanceFieldsFrom) | zInjectMockCandidatesOnFields;
    }

    private boolean injectMockCandidatesOnFields(Set<Object> set, Object obj, boolean z, List<Field> list) {
        Iterator<Field> it = list.iterator();
        while (it.hasNext()) {
            Object objThenInject = this.mockCandidateFilter.filterCandidate(set, it.next(), list, obj).thenInject();
            if (objThenInject != null) {
                z |= true;
                set.remove(objThenInject);
                it.remove();
            }
        }
        return z;
    }

    private List<Field> orderedInstanceFieldsFrom(Class<?> cls) {
        return SuperTypesLastSorter.sortSuperTypesLast(ListUtil.filter(Arrays.asList(cls.getDeclaredFields()), this.notFinalOrStatic));
    }
}
