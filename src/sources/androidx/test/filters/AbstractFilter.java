package androidx.test.filters;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

/* loaded from: classes5.dex */
public abstract class AbstractFilter extends Filter {
    protected abstract boolean evaluateTest(Description description);

    @Override // org.junit.runner.manipulation.Filter
    public boolean shouldRun(Description description) {
        if (description.isTest()) {
            return evaluateTest(description);
        }
        Iterator<Description> it = description.getChildren().iterator();
        while (it.hasNext()) {
            if (shouldRun(it.next())) {
                return true;
            }
        }
        return false;
    }

    protected List<Annotation> getMethodAnnotations(Description description) {
        ArrayList arrayList = new ArrayList();
        for (Annotation annotation : description.getAnnotations()) {
            CustomFilter customFilter = (CustomFilter) annotation.annotationType().getAnnotation(CustomFilter.class);
            if (customFilter != null && customFilter.filterClass().isInstance(this)) {
                arrayList.add(annotation);
            }
        }
        return arrayList;
    }

    protected List<Annotation> getClassAnnotations(Description description) {
        ArrayList arrayList = new ArrayList();
        for (Annotation annotation : description.getTestClass().getAnnotations()) {
            CustomFilter customFilter = (CustomFilter) annotation.annotationType().getAnnotation(CustomFilter.class);
            if (customFilter != null && customFilter.filterClass().isInstance(this)) {
                arrayList.add(annotation);
            }
        }
        return arrayList;
    }
}
