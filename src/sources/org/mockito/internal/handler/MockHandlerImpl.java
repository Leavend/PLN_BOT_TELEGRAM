package org.mockito.internal.handler;

import java.util.Iterator;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.MatchersBinder;
import org.mockito.internal.listeners.StubbingLookupListener;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.OngoingStubbingImpl;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.internal.stubbing.answers.DefaultAnswerValidator;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.mockito.internal.verification.VerificationDataImpl;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class MockHandlerImpl<T> implements MockHandler<T> {
    private static final long serialVersionUID = -2917871070982574165L;
    InvocationContainerImpl invocationContainer;
    MatchersBinder matchersBinder;
    private final MockCreationSettings<T> mockSettings;

    public MockHandlerImpl(MockCreationSettings<T> mockCreationSettings) {
        this.matchersBinder = new MatchersBinder();
        this.mockSettings = mockCreationSettings;
        this.matchersBinder = new MatchersBinder();
        this.invocationContainer = new InvocationContainerImpl(mockCreationSettings);
    }

    @Override // org.mockito.invocation.MockHandler
    public Object handle(Invocation invocation) throws Throwable {
        if (this.invocationContainer.hasAnswersForStubbing()) {
            this.invocationContainer.setMethodForStubbing(this.matchersBinder.bindMatchers(ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage(), invocation));
            return null;
        }
        VerificationMode verificationModePullVerificationMode = ThreadSafeMockingProgress.mockingProgress().pullVerificationMode();
        InvocationMatcher invocationMatcherBindMatchers = this.matchersBinder.bindMatchers(ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage(), invocation);
        ThreadSafeMockingProgress.mockingProgress().validateState();
        if (verificationModePullVerificationMode != null) {
            if (((MockAwareVerificationMode) verificationModePullVerificationMode).getMock() == invocation.getMock()) {
                verificationModePullVerificationMode.verify(createVerificationData(this.invocationContainer, invocationMatcherBindMatchers));
                return null;
            }
            ThreadSafeMockingProgress.mockingProgress().verificationStarted(verificationModePullVerificationMode);
        }
        this.invocationContainer.setInvocationForPotentialStubbing(invocationMatcherBindMatchers);
        OngoingStubbingImpl ongoingStubbingImpl = new OngoingStubbingImpl(this.invocationContainer);
        ThreadSafeMockingProgress.mockingProgress().reportOngoingStubbing(ongoingStubbingImpl);
        StubbedInvocationMatcher stubbedInvocationMatcherFindAnswerFor = this.invocationContainer.findAnswerFor(invocation);
        notifyStubbedAnswerLookup(invocation, stubbedInvocationMatcherFindAnswerFor);
        if (stubbedInvocationMatcherFindAnswerFor != null) {
            stubbedInvocationMatcherFindAnswerFor.captureArgumentsFrom(invocation);
            try {
                return stubbedInvocationMatcherFindAnswerFor.answer(invocation);
            } finally {
                ThreadSafeMockingProgress.mockingProgress().reportOngoingStubbing(ongoingStubbingImpl);
            }
        }
        Object objAnswer = this.mockSettings.getDefaultAnswer().answer(invocation);
        DefaultAnswerValidator.validateReturnValueFor(invocation, objAnswer);
        this.invocationContainer.resetInvocationForPotentialStubbing(invocationMatcherBindMatchers);
        return objAnswer;
    }

    @Override // org.mockito.invocation.MockHandler
    public MockCreationSettings<T> getMockSettings() {
        return this.mockSettings;
    }

    @Override // org.mockito.invocation.MockHandler
    public InvocationContainer getInvocationContainer() {
        return this.invocationContainer;
    }

    private VerificationDataImpl createVerificationData(InvocationContainerImpl invocationContainerImpl, InvocationMatcher invocationMatcher) {
        if (this.mockSettings.isStubOnly()) {
            throw Reporter.stubPassedToVerify();
        }
        return new VerificationDataImpl(invocationContainerImpl, invocationMatcher);
    }

    private void notifyStubbedAnswerLookup(Invocation invocation, StubbedInvocationMatcher stubbedInvocationMatcher) {
        Iterator<StubbingLookupListener> it = ((CreationSettings) this.mockSettings).getStubbingLookupListeners().iterator();
        while (it.hasNext()) {
            it.next().onStubbingLookup(invocation, stubbedInvocationMatcher);
        }
    }
}
