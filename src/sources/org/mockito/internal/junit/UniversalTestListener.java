package org.mockito.internal.junit;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public class UniversalTestListener implements MockitoTestListener {
    private Strictness currentStrictness;
    private final MockitoLogger logger;
    private Map<Object, MockCreationSettings> mocks = new IdentityHashMap();
    private DefaultStubbingLookupListener stubbingLookupListener;

    public UniversalTestListener(Strictness strictness, MockitoLogger mockitoLogger) {
        this.currentStrictness = strictness;
        this.logger = mockitoLogger;
        this.stubbingLookupListener = new DefaultStubbingLookupListener(this.currentStrictness);
    }

    @Override // org.mockito.internal.junit.MockitoTestListener
    public void testFinished(TestFinishedEvent testFinishedEvent) {
        Set<Object> setKeySet = this.mocks.keySet();
        this.mocks = new IdentityHashMap();
        int i = AnonymousClass1.$SwitchMap$org$mockito$quality$Strictness[this.currentStrictness.ordinal()];
        if (i == 1) {
            emitWarnings(this.logger, testFinishedEvent, setKeySet);
        } else if (i == 2) {
            reportUnusedStubs(testFinishedEvent, setKeySet);
        } else if (i != 3) {
            throw new IllegalStateException("Unknown strictness: " + this.currentStrictness);
        }
    }

    /* renamed from: org.mockito.internal.junit.UniversalTestListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$mockito$quality$Strictness;

        static {
            int[] iArr = new int[Strictness.values().length];
            $SwitchMap$org$mockito$quality$Strictness = iArr;
            try {
                iArr[Strictness.WARN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$mockito$quality$Strictness[Strictness.STRICT_STUBS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$mockito$quality$Strictness[Strictness.LENIENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void reportUnusedStubs(TestFinishedEvent testFinishedEvent, Collection<Object> collection) {
        if (testFinishedEvent.getFailure() != null || this.stubbingLookupListener.isMismatchesReported()) {
            return;
        }
        new UnusedStubbingsFinder().getUnusedStubbings(collection).reportUnused();
    }

    private static void emitWarnings(MockitoLogger mockitoLogger, TestFinishedEvent testFinishedEvent, Collection<Object> collection) {
        if (testFinishedEvent.getFailure() != null) {
            new ArgMismatchFinder().getStubbingArgMismatches(collection).format(testFinishedEvent.getTestName(), mockitoLogger);
        } else {
            new UnusedStubbingsFinder().getUnusedStubbings(collection).format(testFinishedEvent.getTestName(), mockitoLogger);
        }
    }

    @Override // org.mockito.listeners.MockCreationListener
    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        this.mocks.put(obj, mockCreationSettings);
        ((CreationSettings) mockCreationSettings).getStubbingLookupListeners().add(this.stubbingLookupListener);
    }

    public void setStrictness(Strictness strictness) {
        this.currentStrictness = strictness;
        this.stubbingLookupListener.setCurrentStrictness(strictness);
    }
}
