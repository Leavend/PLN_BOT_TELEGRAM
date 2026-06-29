package org.mockito;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.GlobalConfiguration;

/* loaded from: classes3.dex */
public class MockitoAnnotations {
    public static void initMocks(Object obj) {
        if (obj == null) {
            throw new MockitoException("testClass cannot be null. For info how to use @Mock annotations see examples in javadoc for MockitoAnnotations class");
        }
        new GlobalConfiguration().tryGetPluginAnnotationEngine().process(obj.getClass(), obj);
    }
}
