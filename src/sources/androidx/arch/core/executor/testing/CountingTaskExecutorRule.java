package androidx.arch.core.executor.testing;

import android.os.SystemClock;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.executor.DefaultTaskExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/* loaded from: classes.dex */
public class CountingTaskExecutorRule extends TestWatcher {
    private final Object mCountLock = new Object();
    private int mTaskCount = 0;

    protected void onIdle() {
    }

    @Override // org.junit.rules.TestWatcher
    protected void starting(Description description) {
        super.starting(description);
        ArchTaskExecutor.getInstance().setDelegate(new DefaultTaskExecutor() { // from class: androidx.arch.core.executor.testing.CountingTaskExecutorRule.1
            @Override // androidx.arch.core.executor.DefaultTaskExecutor, androidx.arch.core.executor.TaskExecutor
            public void executeOnDiskIO(Runnable runnable) {
                super.executeOnDiskIO(CountingTaskExecutorRule.this.new CountingRunnable(runnable));
            }

            @Override // androidx.arch.core.executor.DefaultTaskExecutor, androidx.arch.core.executor.TaskExecutor
            public void postToMainThread(Runnable runnable) {
                super.postToMainThread(CountingTaskExecutorRule.this.new CountingRunnable(runnable));
            }
        });
    }

    @Override // org.junit.rules.TestWatcher
    protected void finished(Description description) {
        super.finished(description);
        ArchTaskExecutor.getInstance().setDelegate(null);
    }

    void increment() {
        synchronized (this.mCountLock) {
            this.mTaskCount++;
        }
    }

    void decrement() {
        synchronized (this.mCountLock) {
            int i = this.mTaskCount - 1;
            this.mTaskCount = i;
            if (i == 0) {
                onIdle();
                this.mCountLock.notifyAll();
            }
        }
    }

    public boolean isIdle() {
        boolean z;
        synchronized (this.mCountLock) {
            z = this.mTaskCount == 0;
        }
        return z;
    }

    public void drainTasks(int i, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long jUptimeMillis = SystemClock.uptimeMillis() + timeUnit.toMillis(i);
        synchronized (this.mCountLock) {
            while (this.mTaskCount != 0) {
                long jUptimeMillis2 = jUptimeMillis - SystemClock.uptimeMillis();
                if (jUptimeMillis2 > 0) {
                    this.mCountLock.wait(jUptimeMillis2);
                } else {
                    throw new TimeoutException("could not drain tasks");
                }
            }
        }
    }

    class CountingRunnable implements Runnable {
        final Runnable mWrapped;

        CountingRunnable(Runnable runnable) {
            this.mWrapped = runnable;
            CountingTaskExecutorRule.this.increment();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mWrapped.run();
            } finally {
                CountingTaskExecutorRule.this.decrement();
            }
        }
    }
}
