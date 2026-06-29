package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public class TypeBasedCandidateFilter implements MockCandidateFilter {
    private final MockCandidateFilter next;

    public TypeBasedCandidateFilter(MockCandidateFilter mockCandidateFilter) {
        this.next = mockCandidateFilter;
    }

    @Override // org.mockito.internal.configuration.injection.filter.MockCandidateFilter
    public OngoingInjector filterCandidate(Collection<Object> collection, Field field, List<Field> list, Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : collection) {
            if (field.getType().isAssignableFrom(obj2.getClass())) {
                arrayList.add(obj2);
            }
        }
        return this.next.filterCandidate(arrayList, field, list, obj);
    }
}
