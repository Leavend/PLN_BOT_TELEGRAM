package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mockito.internal.util.MockUtil;

/* loaded from: classes3.dex */
public class NameBasedCandidateFilter implements MockCandidateFilter {
    private final MockCandidateFilter next;

    public NameBasedCandidateFilter(MockCandidateFilter mockCandidateFilter) {
        this.next = mockCandidateFilter;
    }

    @Override // org.mockito.internal.configuration.injection.filter.MockCandidateFilter
    public OngoingInjector filterCandidate(Collection<Object> collection, Field field, List<Field> list, Object obj) {
        if (collection.size() == 1 && anotherCandidateMatchesMockName(collection, field, list)) {
            return OngoingInjector.nop;
        }
        MockCandidateFilter mockCandidateFilter = this.next;
        if (tooMany(collection)) {
            collection = selectMatchingName(collection, field);
        }
        return mockCandidateFilter.filterCandidate(collection, field, list, obj);
    }

    private boolean tooMany(Collection<Object> collection) {
        return collection.size() > 1;
    }

    private List<Object> selectMatchingName(Collection<Object> collection, Field field) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (field.getName().equals(MockUtil.getMockName(obj).toString())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private boolean anotherCandidateMatchesMockName(Collection<Object> collection, Field field, List<Field> list) {
        String string = MockUtil.getMockName(collection.iterator().next()).toString();
        for (Field field2 : list) {
            if (!field2.equals(field) && field2.getType().equals(field.getType()) && field2.getName().equals(string)) {
                return true;
            }
        }
        return false;
    }
}
