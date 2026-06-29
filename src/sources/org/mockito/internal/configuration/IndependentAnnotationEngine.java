package org.mockito.internal.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.plugins.AnnotationEngine;

/* loaded from: classes3.dex */
public class IndependentAnnotationEngine implements AnnotationEngine, org.mockito.configuration.AnnotationEngine {
    private final Map<Class<? extends Annotation>, FieldAnnotationProcessor<?>> annotationProcessorMap = new HashMap();

    public IndependentAnnotationEngine() {
        registerAnnotationProcessor(Mock.class, new MockAnnotationProcessor());
        registerAnnotationProcessor(Captor.class, new CaptorAnnotationProcessor());
    }

    private Object createMockFor(Annotation annotation, Field field) {
        return forAnnotation(annotation).process(annotation, field);
    }

    private <A extends Annotation> FieldAnnotationProcessor<A> forAnnotation(A a) {
        if (this.annotationProcessorMap.containsKey(a.annotationType())) {
            return (FieldAnnotationProcessor) this.annotationProcessorMap.get(a.annotationType());
        }
        return (FieldAnnotationProcessor<A>) new FieldAnnotationProcessor<A>() { // from class: org.mockito.internal.configuration.IndependentAnnotationEngine.1
            /* JADX WARN: Incorrect types in method signature: (TA;Ljava/lang/reflect/Field;)Ljava/lang/Object; */
            @Override // org.mockito.internal.configuration.FieldAnnotationProcessor
            public Object process(Annotation annotation, Field field) {
                return null;
            }
        };
    }

    private <A extends Annotation> void registerAnnotationProcessor(Class<A> cls, FieldAnnotationProcessor<A> fieldAnnotationProcessor) {
        this.annotationProcessorMap.put(cls, fieldAnnotationProcessor);
    }

    @Override // org.mockito.plugins.AnnotationEngine
    public void process(Class<?> cls, Object obj) {
        for (Field field : cls.getDeclaredFields()) {
            boolean z = false;
            for (Annotation annotation : field.getAnnotations()) {
                Object objCreateMockFor = createMockFor(annotation, field);
                if (objCreateMockFor != null) {
                    throwIfAlreadyAssigned(field, z);
                    try {
                        FieldSetter.setField(obj, field, objCreateMockFor);
                        z = true;
                    } catch (Exception e) {
                        throw new MockitoException("Problems setting field " + field.getName() + " annotated with " + annotation, e);
                    }
                }
            }
        }
    }

    void throwIfAlreadyAssigned(Field field, boolean z) {
        if (z) {
            throw Reporter.moreThanOneAnnotationNotAllowed(field.getName());
        }
    }
}
