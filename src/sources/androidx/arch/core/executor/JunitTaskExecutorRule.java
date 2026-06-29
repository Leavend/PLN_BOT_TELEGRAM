package androidx.arch.core.executor;

import java.util.List;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

/* loaded from: classes.dex */
public class JunitTaskExecutorRule implements TestRule {
    private final TaskExecutorWithFakeMainThread mTaskExecutor;

    public JunitTaskExecutorRule(int i, boolean z) {
        if (z) {
            this.mTaskExecutor = (TaskExecutorWithFakeMainThread) Mockito.spy(new TaskExecutorWithFakeMainThread(i));
        } else {
            this.mTaskExecutor = new TaskExecutorWithFakeMainThread(i);
        }
    }

    void beforeStart() {
        ArchTaskExecutor.getInstance().setDelegate(this.mTaskExecutor);
    }

    void afterFinished() {
        ArchTaskExecutor.getInstance().setDelegate(null);
    }

    public TaskExecutor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    public void drainTasks(int i) throws InterruptedException {
        this.mTaskExecutor.drainTasks(i);
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement statement, Description description) {
        return new Statement() { // from class: androidx.arch.core.executor.JunitTaskExecutorRule.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                JunitTaskExecutorRule.this.beforeStart();
                try {
                    statement.evaluate();
                    JunitTaskExecutorRule.this.finishExecutors();
                } finally {
                }
            }
        };
    }

    void finishExecutors() throws InterruptedException, MultipleFailureException {
        this.mTaskExecutor.shutdown(10);
        List<Throwable> errors = this.mTaskExecutor.getErrors();
        if (!errors.isEmpty()) {
            throw new MultipleFailureException(errors);
        }
    }
}
