package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class ForwardsInvocations implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -8343690268123254910L;
    private Object delegatedObject;

    public ForwardsInvocations(Object obj) {
        this.delegatedObject = obj;
    }

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Method method = invocationOnMock.getMethod();
        try {
            Method delegateMethod = getDelegateMethod(method);
            if (!compatibleReturnTypes(method.getReturnType(), delegateMethod.getReturnType())) {
                throw Reporter.delegatedMethodHasWrongReturnType(method, delegateMethod, invocationOnMock.getMock(), this.delegatedObject);
            }
            return delegateMethod.invoke(this.delegatedObject, ((Invocation) invocationOnMock).getRawArguments());
        } catch (NoSuchMethodException unused) {
            throw Reporter.delegatedMethodDoesNotExistOnDelegate(method, invocationOnMock.getMock(), this.delegatedObject);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Method getDelegateMethod(Method method) throws NoSuchMethodException {
        return method.getDeclaringClass().isAssignableFrom(this.delegatedObject.getClass()) ? method : this.delegatedObject.getClass().getMethod(method.getName(), method.getParameterTypes());
    }

    private static boolean compatibleReturnTypes(Class<?> cls, Class<?> cls2) {
        return cls.equals(cls2) || cls.isAssignableFrom(cls2);
    }
}
