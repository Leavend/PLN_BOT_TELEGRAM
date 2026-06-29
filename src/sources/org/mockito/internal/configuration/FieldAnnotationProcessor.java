package org.mockito.internal.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public interface FieldAnnotationProcessor<A extends Annotation> {
    Object process(A a, Field field);
}
