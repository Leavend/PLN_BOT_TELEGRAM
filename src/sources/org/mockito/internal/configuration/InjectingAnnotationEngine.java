package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.mockito.internal.configuration.injection.scanner.InjectMocksScanner;
import org.mockito.internal.configuration.injection.scanner.MockScanner;
import org.mockito.internal.util.collections.Sets;
import org.mockito.plugins.AnnotationEngine;

/* loaded from: classes3.dex */
public class InjectingAnnotationEngine implements AnnotationEngine, org.mockito.configuration.AnnotationEngine {
    private final AnnotationEngine delegate = new IndependentAnnotationEngine();
    private final AnnotationEngine spyAnnotationEngine = new SpyAnnotationEngine();

    protected void onInjection(Object obj, Class<?> cls, Set<Field> set, Set<Object> set2) {
    }

    @Override // org.mockito.plugins.AnnotationEngine
    public void process(Class<?> cls, Object obj) {
        processIndependentAnnotations(obj.getClass(), obj);
        processInjectMocks(obj.getClass(), obj);
    }

    private void processInjectMocks(Class<?> cls, Object obj) {
        while (cls != Object.class) {
            injectMocks(obj);
            cls = cls.getSuperclass();
        }
    }

    private void processIndependentAnnotations(Class<?> cls, Object obj) {
        while (cls != Object.class) {
            this.delegate.process(cls, obj);
            this.spyAnnotationEngine.process(cls, obj);
            cls = cls.getSuperclass();
        }
    }

    public void injectMocks(Object obj) {
        HashSet hashSet = new HashSet();
        Set<Object> setNewMockSafeHashSet = Sets.newMockSafeHashSet(new Object[0]);
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            new InjectMocksScanner(superclass).addTo(hashSet);
            new MockScanner(obj, superclass).addPreparedMocks(setNewMockSafeHashSet);
            onInjection(obj, superclass, hashSet, setNewMockSafeHashSet);
        }
        new DefaultInjectionEngine().injectMocksOnFields(hashSet, setNewMockSafeHashSet, obj);
    }
}
