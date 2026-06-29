package androidx.test.internal.runner;

/* loaded from: classes5.dex */
public class EmptyTestRunner extends ErrorReportingRunner {
    public EmptyTestRunner(Class<?> clazz) {
        super(clazz.getName(), new RuntimeException(String.format("Invalid test class '%s': No test methods found", clazz.getName())));
    }
}
