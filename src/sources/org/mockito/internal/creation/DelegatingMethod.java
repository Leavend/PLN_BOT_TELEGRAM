package org.mockito.internal.creation;

import java.lang.reflect.Method;
import org.mockito.internal.invocation.MockitoMethod;

/* loaded from: classes3.dex */
public class DelegatingMethod implements MockitoMethod {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Method method;
    private final Class<?>[] parameterTypes;

    public DelegatingMethod(Method method) {
        this.method = method;
        this.parameterTypes = SuspendMethod.trimSuspendParameterTypes(method.getParameterTypes());
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public Class<?>[] getExceptionTypes() {
        return this.method.getExceptionTypes();
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public Method getJavaMethod() {
        return this.method;
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public String getName() {
        return this.method.getName();
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public Class<?> getReturnType() {
        return this.method.getReturnType();
    }

    @Override // org.mockito.internal.invocation.MockitoMethod
    public boolean isVarArgs() {
        return this.method.isVarArgs();
    }

    @Override // org.mockito.internal.invocation.AbstractAwareMethod
    public boolean isAbstract() {
        return (this.method.getModifiers() & 1024) != 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DelegatingMethod) {
            return this.method.equals(((DelegatingMethod) obj).method);
        }
        return this.method.equals(obj);
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}
