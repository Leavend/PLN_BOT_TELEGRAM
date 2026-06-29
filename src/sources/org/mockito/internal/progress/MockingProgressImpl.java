package org.mockito.internal.progress;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.mockito.internal.debugging.Localized;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Location;
import org.mockito.listeners.MockCreationListener;
import org.mockito.listeners.MockitoListener;
import org.mockito.listeners.VerificationListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

/* loaded from: classes3.dex */
public class MockingProgressImpl implements MockingProgress {
    private OngoingStubbing<?> ongoingStubbing;
    private Localized<VerificationMode> verificationMode;
    private final ArgumentMatcherStorage argumentMatcherStorage = new ArgumentMatcherStorageImpl();
    private Location stubbingInProgress = null;
    private final Set<MockitoListener> listeners = new LinkedHashSet();
    private VerificationStrategy verificationStrategy = getDefaultVerificationStrategy();

    public static VerificationStrategy getDefaultVerificationStrategy() {
        return new VerificationStrategy() { // from class: org.mockito.internal.progress.MockingProgressImpl.1
            @Override // org.mockito.verification.VerificationStrategy
            public VerificationMode maybeVerifyLazily(VerificationMode verificationMode) {
                return verificationMode;
            }
        };
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void reportOngoingStubbing(OngoingStubbing ongoingStubbing) {
        this.ongoingStubbing = ongoingStubbing;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public OngoingStubbing<?> pullOngoingStubbing() {
        OngoingStubbing<?> ongoingStubbing = this.ongoingStubbing;
        this.ongoingStubbing = null;
        return ongoingStubbing;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public Set<VerificationListener> verificationListeners() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (MockitoListener mockitoListener : this.listeners) {
            if (mockitoListener instanceof VerificationListener) {
                linkedHashSet.add((VerificationListener) mockitoListener);
            }
        }
        return linkedHashSet;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void verificationStarted(VerificationMode verificationMode) {
        validateState();
        resetOngoingStubbing();
        this.verificationMode = new Localized<>(verificationMode);
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void resetOngoingStubbing() {
        this.ongoingStubbing = null;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public VerificationMode pullVerificationMode() {
        Localized<VerificationMode> localized = this.verificationMode;
        if (localized == null) {
            return null;
        }
        VerificationMode object = localized.getObject();
        this.verificationMode = null;
        return object;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void stubbingStarted() {
        validateState();
        this.stubbingInProgress = new LocationImpl();
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void validateState() {
        validateMostStuff();
        Location location = this.stubbingInProgress;
        if (location == null) {
            return;
        }
        this.stubbingInProgress = null;
        throw Reporter.unfinishedStubbing(location);
    }

    private void validateMostStuff() {
        GlobalConfiguration.validate();
        Localized<VerificationMode> localized = this.verificationMode;
        if (localized != null) {
            Location location = localized.getLocation();
            this.verificationMode = null;
            throw Reporter.unfinishedVerificationException(location);
        }
        getArgumentMatcherStorage().validateState();
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void stubbingCompleted() {
        this.stubbingInProgress = null;
    }

    public String toString() {
        return "ongoingStubbing: " + this.ongoingStubbing + ", verificationMode: " + this.verificationMode + ", stubbingInProgress: " + this.stubbingInProgress;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void reset() {
        this.stubbingInProgress = null;
        this.verificationMode = null;
        getArgumentMatcherStorage().reset();
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public ArgumentMatcherStorage getArgumentMatcherStorage() {
        return this.argumentMatcherStorage;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void mockingStarted(Object obj, MockCreationSettings mockCreationSettings) {
        for (MockitoListener mockitoListener : this.listeners) {
            if (mockitoListener instanceof MockCreationListener) {
                ((MockCreationListener) mockitoListener).onMockCreated(obj, mockCreationSettings);
            }
        }
        validateMostStuff();
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void addListener(MockitoListener mockitoListener) {
        Iterator<MockitoListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(mockitoListener.getClass())) {
                Reporter.redundantMockitoListener(mockitoListener.getClass().getSimpleName());
            }
        }
        this.listeners.add(mockitoListener);
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void removeListener(MockitoListener mockitoListener) {
        this.listeners.remove(mockitoListener);
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void setVerificationStrategy(VerificationStrategy verificationStrategy) {
        this.verificationStrategy = verificationStrategy;
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public VerificationMode maybeVerifyLazily(VerificationMode verificationMode) {
        return this.verificationStrategy.maybeVerifyLazily(verificationMode);
    }

    @Override // org.mockito.internal.progress.MockingProgress
    public void clearListeners() {
        this.listeners.clear();
    }
}
