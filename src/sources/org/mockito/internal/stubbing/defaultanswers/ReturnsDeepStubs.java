package org.mockito.internal.stubbing.defaultanswers;

import java.io.IOException;
import java.io.Serializable;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class ReturnsDeepStubs implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -7105341425736035847L;

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        GenericMetadataSupport genericMetadataSupportResolveGenericReturnType = actualParameterizedType(invocationOnMock.getMock()).resolveGenericReturnType(invocationOnMock.getMethod());
        Class<?> clsRawType = genericMetadataSupportResolveGenericReturnType.rawType();
        if (!mockitoCore().isTypeMockable(clsRawType)) {
            return delegate().returnValueFor(clsRawType);
        }
        return deepStub(invocationOnMock, genericMetadataSupportResolveGenericReturnType);
    }

    private Object deepStub(InvocationOnMock invocationOnMock, GenericMetadataSupport genericMetadataSupport) throws Throwable {
        InvocationContainerImpl invocationContainer = MockUtil.getInvocationContainer(invocationOnMock.getMock());
        for (Stubbing stubbing : invocationContainer.getStubbedInvocations()) {
            if (invocationContainer.getInvocationForStubbing().matches(stubbing.getInvocation())) {
                return stubbing.answer(invocationOnMock);
            }
        }
        StubbedInvocationMatcher stubbedInvocationMatcherRecordDeepStubAnswer = recordDeepStubAnswer(newDeepStubMock(genericMetadataSupport, invocationOnMock.getMock()), invocationContainer);
        stubbedInvocationMatcherRecordDeepStubAnswer.markStubUsed(stubbedInvocationMatcherRecordDeepStubAnswer.getInvocation());
        return stubbedInvocationMatcherRecordDeepStubAnswer.answer(invocationOnMock);
    }

    private Object newDeepStubMock(GenericMetadataSupport genericMetadataSupport, Object obj) {
        return mockitoCore().mock(genericMetadataSupport.rawType(), withSettingsUsing(genericMetadataSupport, MockUtil.getMockSettings(obj)));
    }

    private MockSettings withSettingsUsing(GenericMetadataSupport genericMetadataSupport, MockCreationSettings mockCreationSettings) {
        MockSettings mockSettingsWithSettings;
        if (genericMetadataSupport.hasRawExtraInterfaces()) {
            mockSettingsWithSettings = Mockito.withSettings().extraInterfaces(genericMetadataSupport.rawExtraInterfaces());
        } else {
            mockSettingsWithSettings = Mockito.withSettings();
        }
        return propagateSerializationSettings(mockSettingsWithSettings, mockCreationSettings).defaultAnswer(returnsDeepStubsAnswerUsing(genericMetadataSupport));
    }

    private MockSettings propagateSerializationSettings(MockSettings mockSettings, MockCreationSettings mockCreationSettings) {
        return mockSettings.serializable(mockCreationSettings.getSerializableMode());
    }

    private ReturnsDeepStubs returnsDeepStubsAnswerUsing(GenericMetadataSupport genericMetadataSupport) {
        return new ReturnsDeepStubsSerializationFallback(genericMetadataSupport);
    }

    private StubbedInvocationMatcher recordDeepStubAnswer(Object obj, InvocationContainerImpl invocationContainerImpl) {
        return invocationContainerImpl.addAnswer(new DeeplyStubbedAnswer(obj), false);
    }

    protected GenericMetadataSupport actualParameterizedType(Object obj) {
        return GenericMetadataSupport.inferFrom(((CreationSettings) MockUtil.getMockHandler(obj).getMockSettings()).getTypeToMock());
    }

    private static class ReturnsDeepStubsSerializationFallback extends ReturnsDeepStubs implements Serializable {
        private final GenericMetadataSupport returnTypeGenericMetadata;

        public ReturnsDeepStubsSerializationFallback(GenericMetadataSupport genericMetadataSupport) {
            this.returnTypeGenericMetadata = genericMetadataSupport;
        }

        @Override // org.mockito.internal.stubbing.defaultanswers.ReturnsDeepStubs
        protected GenericMetadataSupport actualParameterizedType(Object obj) {
            return this.returnTypeGenericMetadata;
        }

        private Object writeReplace() throws IOException {
            return Mockito.RETURNS_DEEP_STUBS;
        }
    }

    private static class DeeplyStubbedAnswer implements Answer<Object>, Serializable {
        private final Object mock;

        DeeplyStubbedAnswer(Object obj) {
            this.mock = obj;
        }

        @Override // org.mockito.stubbing.Answer
        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            return this.mock;
        }
    }

    private static MockitoCore mockitoCore() {
        return LazyHolder.MOCKITO_CORE;
    }

    private static ReturnsEmptyValues delegate() {
        return LazyHolder.DELEGATE;
    }

    private static class LazyHolder {
        private static final MockitoCore MOCKITO_CORE = new MockitoCore();
        private static final ReturnsEmptyValues DELEGATE = new ReturnsEmptyValues();

        private LazyHolder() {
        }
    }
}
