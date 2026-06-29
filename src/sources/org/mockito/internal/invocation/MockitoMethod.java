package org.mockito.internal.invocation;

import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public interface MockitoMethod extends AbstractAwareMethod {
    Class<?>[] getExceptionTypes();

    Method getJavaMethod();

    String getName();

    Class<?>[] getParameterTypes();

    Class<?> getReturnType();

    boolean isVarArgs();
}
