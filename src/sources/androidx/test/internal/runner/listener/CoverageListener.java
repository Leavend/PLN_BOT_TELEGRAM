package androidx.test.internal.runner.listener;

import android.app.Instrumentation;
import android.os.Bundle;
import androidx.test.internal.runner.coverage.InstrumentationCoverageReporter;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.io.PlatformTestStorageRegistry;
import java.io.PrintStream;
import org.junit.runner.Result;

/* loaded from: classes5.dex */
public class CoverageListener extends InstrumentationRunListener {
    private static final String REPORT_KEY_COVERAGE_PATH = "coverageFilePath";
    private final String coverageFilePath;
    private InstrumentationCoverageReporter coverageReporter;
    private PlatformTestStorage runnerIO;

    public CoverageListener(String customCoverageFilePath) {
        this(customCoverageFilePath, PlatformTestStorageRegistry.getInstance());
    }

    public CoverageListener(String customCoverageFilePath, PlatformTestStorage runnerIO) {
        this.coverageFilePath = customCoverageFilePath;
        this.runnerIO = runnerIO;
    }

    CoverageListener(String customCoverageFilePath, InstrumentationCoverageReporter coverageReporter) {
        this.coverageFilePath = customCoverageFilePath;
        this.coverageReporter = coverageReporter;
    }

    @Override // androidx.test.internal.runner.listener.InstrumentationRunListener
    public void setInstrumentation(Instrumentation instr) {
        super.setInstrumentation(instr);
        this.coverageReporter = new InstrumentationCoverageReporter(getInstrumentation(), this.runnerIO);
    }

    @Override // androidx.test.internal.runner.listener.InstrumentationRunListener
    public void instrumentationRunFinished(PrintStream writer, Bundle results, Result junitResults) {
        results.putString(REPORT_KEY_COVERAGE_PATH, this.coverageReporter.generateCoverageReport(this.coverageFilePath, writer));
    }
}
