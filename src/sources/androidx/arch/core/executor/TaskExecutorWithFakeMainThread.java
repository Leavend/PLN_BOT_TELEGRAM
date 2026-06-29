package androidx.arch.core.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class TaskExecutorWithFakeMainThread extends TaskExecutor {
    private ExecutorService mIOService;
    private final int mIOThreadCount;
    List<Throwable> mCaughtExceptions = Collections.synchronizedList(new ArrayList());
    private AtomicReference<Thread> mMainThread = new AtomicReference<>();
    private ExecutorService mMainThreadService = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: androidx.arch.core.executor.TaskExecutorWithFakeMainThread.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            TaskExecutorWithFakeMainThread$1$$ExternalSyntheticBackportWithForwarding0.m(TaskExecutorWithFakeMainThread.this.mMainThread, null, TaskExecutorWithFakeMainThread.this.new LoggingThread(runnable));
            return (Thread) TaskExecutorWithFakeMainThread.this.mMainThread.get();
        }
    });

    public TaskExecutorWithFakeMainThread(int i) {
        this.mIOThreadCount = i;
        this.mIOService = Executors.newFixedThreadPool(i, new ThreadFactory() { // from class: androidx.arch.core.executor.TaskExecutorWithFakeMainThread.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return TaskExecutorWithFakeMainThread.this.new LoggingThread(runnable);
            }
        });
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable runnable) {
        this.mIOService.execute(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.mMainThreadService.execute(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return Thread.currentThread() == this.mMainThread.get();
    }

    List<Throwable> getErrors() {
        return this.mCaughtExceptions;
    }

    void shutdown(int i) throws InterruptedException {
        this.mMainThreadService.shutdown();
        this.mIOService.shutdown();
        long j = i;
        this.mMainThreadService.awaitTermination(j, TimeUnit.SECONDS);
        this.mIOService.awaitTermination(j, TimeUnit.SECONDS);
    }

    public void drainTasks(int i) throws InterruptedException {
        if (isMainThread()) {
            throw new IllegalStateException();
        }
        final CountDownLatch countDownLatch = new CountDownLatch(this.mIOThreadCount);
        final CountDownLatch countDownLatch2 = new CountDownLatch(1);
        for (int i2 = 0; i2 < this.mIOThreadCount; i2++) {
            executeOnDiskIO(new Runnable() { // from class: androidx.arch.core.executor.TaskExecutorWithFakeMainThread.3
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    countDownLatch.countDown();
                    try {
                        countDownLatch2.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        final CountDownLatch countDownLatch3 = new CountDownLatch(1);
        postToMainThread(new Runnable() { // from class: androidx.arch.core.executor.TaskExecutorWithFakeMainThread.4
            @Override // java.lang.Runnable
            public void run() {
                countDownLatch3.countDown();
            }
        });
        long j = i;
        if (!countDownLatch.await(j, TimeUnit.SECONDS)) {
            throw new AssertionError("Could not drain IO tasks in " + i + " seconds");
        }
        countDownLatch2.countDown();
        if (!countDownLatch3.await(j, TimeUnit.SECONDS)) {
            throw new AssertionError("Could not drain UI tasks in " + i + " seconds");
        }
    }

    class LoggingThread extends Thread {
        LoggingThread(final Runnable runnable) {
            super(new Runnable() { // from class: androidx.arch.core.executor.TaskExecutorWithFakeMainThread.LoggingThread.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable th) {
                        taskExecutorWithFakeMainThread.mCaughtExceptions.add(th);
                    }
                }
            });
        }
    }
}
