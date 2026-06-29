package androidx.test.internal.runner.coverage;

import android.app.Instrumentation;
import android.util.Log;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.services.storage.TestStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes5.dex */
public class InstrumentationCoverageReporter {
    private static final String DEFAULT_COVERAGE_FILE_NAME = "coverage.ec";
    private static final String EMMA_RUNTIME_CLASS = "com.vladium.emma.rt.RT";
    private static final String TAG = "InstrumentationCoverageReporter";
    private final Instrumentation instrumentation;
    private final PlatformTestStorage testStorage;

    public InstrumentationCoverageReporter(Instrumentation instrumentation, PlatformTestStorage testStorage) {
        this.instrumentation = instrumentation;
        this.testStorage = testStorage;
    }

    public String generateCoverageReport(String coverageFilePath, PrintStream instrumentationResultWriter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String strDumpCoverageToFile;
        if (this.testStorage instanceof TestStorage) {
            strDumpCoverageToFile = dumpCoverageToTestStorage(coverageFilePath, instrumentationResultWriter);
        } else {
            strDumpCoverageToFile = dumpCoverageToFile(coverageFilePath, instrumentationResultWriter);
        }
        Log.d(TAG, "Coverage file was generated to " + strDumpCoverageToFile);
        instrumentationResultWriter.format("\nGenerated code coverage data to %s", strDumpCoverageToFile);
        return strDumpCoverageToFile;
    }

    private String dumpCoverageToFile(String coverageFilePath, PrintStream instrumentationResultWriter) {
        if (coverageFilePath == null) {
            Log.d(TAG, "No coverage file path was specified. Dumps to the default file path.");
            coverageFilePath = this.instrumentation.getTargetContext().getFilesDir().getAbsolutePath() + File.separator + DEFAULT_COVERAGE_FILE_NAME;
        }
        if (!generateCoverageInternal(coverageFilePath, instrumentationResultWriter)) {
            Log.w(TAG, "Failed to generate the coverage data file. Please refer to the instrumentation result for more info.");
        }
        return coverageFilePath;
    }

    private String dumpCoverageToTestStorage(String coverageFilePath, PrintStream instrumentationResultWriter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (coverageFilePath == null) {
            Log.d(TAG, "No coverage file path was specified. Dumps to the default coverage file using test storage.");
            coverageFilePath = DEFAULT_COVERAGE_FILE_NAME;
        }
        String str = this.instrumentation.getTargetContext().getFilesDir().getAbsolutePath() + File.separator + DEFAULT_COVERAGE_FILE_NAME;
        if (!generateCoverageInternal(str, instrumentationResultWriter)) {
            Log.w(TAG, "Failed to generate the coverage data file. Please refer to the instrumentation result for more info.");
        }
        try {
            Log.d(TAG, "Test service is available. Moving the coverage data file to be managed by the storage service.");
            moveFileToTestStorage(str, coverageFilePath);
            return coverageFilePath;
        } catch (IOException e) {
            reportEmmaError(instrumentationResultWriter, e);
            return null;
        }
    }

    private void moveFileToTestStorage(String srcFilePath, String destFilePath) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        File file = new File(srcFilePath);
        if (file.exists()) {
            String str = TAG;
            Log.d(str, String.format("Moving coverage file [%s] to the internal test storage [%s].", srcFilePath, destFilePath));
            OutputStream outputStreamOpenInternalOutputFile = this.testStorage.openInternalOutputFile(destFilePath);
            try {
                FileChannel channel = new FileInputStream(srcFilePath).getChannel();
                try {
                    WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(outputStreamOpenInternalOutputFile);
                    try {
                        channel.transferTo(0L, channel.size(), writableByteChannelNewChannel);
                        if (writableByteChannelNewChannel != null) {
                            writableByteChannelNewChannel.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        if (outputStreamOpenInternalOutputFile != null) {
                            outputStreamOpenInternalOutputFile.close();
                        }
                        if (file.delete()) {
                            return;
                        }
                        Log.e(str, String.format("Failed to delete original coverage file [%s]", file.getAbsolutePath()));
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (outputStreamOpenInternalOutputFile != null) {
                    try {
                        outputStreamOpenInternalOutputFile.close();
                    } catch (Throwable th2) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    }
                }
                throw th;
            }
        }
    }

    public boolean generateCoverageInternal(String coverageFilePath, PrintStream instrumentationResultWriter) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls;
        File file = new File(coverageFilePath);
        try {
            try {
                try {
                    cls = Class.forName(EMMA_RUNTIME_CLASS, true, this.instrumentation.getTargetContext().getClassLoader());
                } catch (ClassNotFoundException unused) {
                    cls = Class.forName(EMMA_RUNTIME_CLASS, true, this.instrumentation.getContext().getClassLoader());
                    Log.w(TAG, "Generating coverage for alternate test context.");
                    instrumentationResultWriter.format("\nWarning: %s", "Generating coverage for alternate test context.");
                }
                cls.getMethod("dumpCoverageData", file.getClass(), Boolean.TYPE, Boolean.TYPE).invoke(null, file, false, false);
                return true;
            } catch (ClassNotFoundException e) {
                reportEmmaError(instrumentationResultWriter, "Is Emma/JaCoCo jar on classpath?", e);
                return false;
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            reportEmmaError(instrumentationResultWriter, e2);
            return false;
        }
    }

    private void reportEmmaError(PrintStream writer, Exception e) {
        reportEmmaError(writer, "", e);
    }

    private void reportEmmaError(PrintStream writer, String hint, Exception e) {
        String str = "Failed to generate Emma/JaCoCo coverage. " + hint;
        Log.e(TAG, str, e);
        writer.format("\nError: %s", str);
    }
}
