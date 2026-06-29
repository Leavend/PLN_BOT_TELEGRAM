package org.mockito.internal.stubbing.answers;

import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.internal.util.reflection.LenientCopyTool;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class ClonesArguments implements Answer<Object> {
    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] arguments = invocationOnMock.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            Object obj = arguments[i];
            Object objNewInstance = Plugins.getInstantiatorProvider().getInstantiator(null).newInstance(obj.getClass());
            new LenientCopyTool().copyToRealObject(obj, objNewInstance);
            arguments[i] = objNewInstance;
        }
        return new ReturnsEmptyValues().answer(invocationOnMock);
    }
}
