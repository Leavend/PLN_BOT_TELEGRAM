package androidx.arch.core.executor.testing;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.executor.TaskExecutor;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/* loaded from: classes.dex */
public class InstantTaskExecutorRule extends TestWatcher {
    @Override // org.junit.rules.TestWatcher
    protected void starting(Description description) {
        super.starting(description);
        ArchTaskExecutor.getInstance().setDelegate(new TaskExecutor() { // from class: androidx.arch.core.executor.testing.InstantTaskExecutorRule.1
            @Override // androidx.arch.core.executor.TaskExecutor
            public boolean isMainThread() {
                return true;
            }

            @Override // androidx.arch.core.executor.TaskExecutor
            public void executeOnDiskIO(Runnable runnable) {
                runnable.run();
            }

            @Override // androidx.arch.core.executor.TaskExecutor
            public void postToMainThread(Runnable runnable) {
                runnable.run();
            }
        });
    }

    @Override // org.junit.rules.TestWatcher
    protected void finished(Description description) {
        super.finished(description);
        ArchTaskExecutor.getInstance().setDelegate(null);
    }
}
