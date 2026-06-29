package org.mockito.internal.progress;

/* loaded from: classes3.dex */
public class ThreadSafeMockingProgress {
    private static final ThreadLocal<MockingProgress> MOCKING_PROGRESS_PROVIDER = new ThreadLocal<MockingProgress>() { // from class: org.mockito.internal.progress.ThreadSafeMockingProgress.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public MockingProgress initialValue() {
            return new MockingProgressImpl();
        }
    };

    private ThreadSafeMockingProgress() {
    }

    public static final MockingProgress mockingProgress() {
        return MOCKING_PROGRESS_PROVIDER.get();
    }
}
