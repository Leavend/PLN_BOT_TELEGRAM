package org.mockito.internal;

import java.util.Arrays;
import java.util.List;
import org.mockito.InOrder;
import org.mockito.MockSettings;
import org.mockito.MockingDetails;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.finder.VerifiableInvocationsFinder;
import org.mockito.internal.listeners.VerificationStartedNotifier;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.OngoingStubbingImpl;
import org.mockito.internal.stubbing.StubberImpl;
import org.mockito.internal.util.DefaultMockingDetails;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.mockito.internal.verification.VerificationDataImpl;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.internal.verification.api.VerificationDataInOrderImpl;
import org.mockito.invocation.Invocation;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class MockitoCore {
    public boolean isTypeMockable(Class<?> cls) {
        return MockUtil.typeMockabilityOf(cls).mockable();
    }

    public <T> T mock(Class<T> cls, MockSettings mockSettings) {
        if (!MockSettingsImpl.class.isInstance(mockSettings)) {
            throw new IllegalArgumentException("Unexpected implementation of '" + mockSettings.getClass().getCanonicalName() + "'\nAt the moment, you cannot provide your own implementations of that class.");
        }
        MockCreationSettings<T> mockCreationSettingsBuild = ((MockSettingsImpl) MockSettingsImpl.class.cast(mockSettings)).build(cls);
        T t = (T) MockUtil.createMock(mockCreationSettingsBuild);
        ThreadSafeMockingProgress.mockingProgress().mockingStarted(t, mockCreationSettingsBuild);
        return t;
    }

    public <T> OngoingStubbing<T> when(T t) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.stubbingStarted();
        OngoingStubbing<T> ongoingStubbing = (OngoingStubbing<T>) mockingProgress.pullOngoingStubbing();
        if (ongoingStubbing != null) {
            return ongoingStubbing;
        }
        mockingProgress.reset();
        throw Reporter.missingMethodInvocation();
    }

    public <T> T verify(T t, VerificationMode verificationMode) {
        if (t == null) {
            throw Reporter.nullPassedToVerify();
        }
        MockingDetails mockingDetails = mockingDetails(t);
        if (!mockingDetails.isMock()) {
            throw Reporter.notAMockPassedToVerify(t.getClass());
        }
        T t2 = (T) VerificationStartedNotifier.notifyVerificationStarted(mockingDetails.getMockHandler().getMockSettings().getVerificationStartedListeners(), mockingDetails);
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.verificationStarted(new MockAwareVerificationMode(t2, mockingProgress.maybeVerifyLazily(verificationMode), mockingProgress.verificationListeners()));
        return t2;
    }

    public <T> void reset(T... tArr) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.validateState();
        mockingProgress.reset();
        mockingProgress.resetOngoingStubbing();
        for (T t : tArr) {
            MockUtil.resetMock(t);
        }
    }

    public <T> void clearInvocations(T... tArr) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.validateState();
        mockingProgress.reset();
        mockingProgress.resetOngoingStubbing();
        for (T t : tArr) {
            MockUtil.getInvocationContainer(t).clearInvocations();
        }
    }

    public void verifyNoMoreInteractions(Object... objArr) {
        assertMocksNotEmpty(objArr);
        ThreadSafeMockingProgress.mockingProgress().validateState();
        for (Object obj : objArr) {
            if (obj == null) {
                throw Reporter.nullPassedToVerifyNoMoreInteractions();
            }
            try {
                VerificationModeFactory.noMoreInteractions().verify(new VerificationDataImpl(MockUtil.getInvocationContainer(obj), null));
            } catch (NotAMockException unused) {
                throw Reporter.notAMockPassedToVerifyNoMoreInteractions();
            }
            throw Reporter.notAMockPassedToVerifyNoMoreInteractions();
        }
    }

    public void verifyNoMoreInteractionsInOrder(List<Object> list, InOrderContext inOrderContext) {
        ThreadSafeMockingProgress.mockingProgress().validateState();
        VerificationModeFactory.noMoreInteractions().verifyInOrder(new VerificationDataInOrderImpl(inOrderContext, VerifiableInvocationsFinder.find(list), null));
    }

    private void assertMocksNotEmpty(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw Reporter.mocksHaveToBePassedToVerifyNoMoreInteractions();
        }
    }

    public InOrder inOrder(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            throw Reporter.mocksHaveToBePassedWhenCreatingInOrder();
        }
        for (Object obj : objArr) {
            if (obj == null) {
                throw Reporter.nullPassedWhenCreatingInOrder();
            }
            if (!MockUtil.isMock(obj)) {
                throw Reporter.notAMockPassedWhenCreatingInOrder();
            }
        }
        return new InOrderImpl(Arrays.asList(objArr));
    }

    public Stubber stubber() {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.stubbingStarted();
        mockingProgress.resetOngoingStubbing();
        return new StubberImpl();
    }

    public void validateMockitoUsage() {
        ThreadSafeMockingProgress.mockingProgress().validateState();
    }

    public Invocation getLastInvocation() {
        return ((OngoingStubbingImpl) ThreadSafeMockingProgress.mockingProgress().pullOngoingStubbing()).getRegisteredInvocations().get(r0.size() - 1);
    }

    public Object[] ignoreStubs(Object... objArr) {
        for (Object obj : objArr) {
            for (Invocation invocation : MockUtil.getInvocationContainer(obj).getInvocations()) {
                if (invocation.stubInfo() != null) {
                    invocation.ignoreForVerification();
                }
            }
        }
        return objArr;
    }

    public MockingDetails mockingDetails(Object obj) {
        return new DefaultMockingDetails(obj);
    }
}
