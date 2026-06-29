package androidx.test.runner.screenshot;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Deprecated
/* loaded from: classes5.dex */
public class BasicScreenCaptureProcessor implements ScreenCaptureProcessor {
    protected String mDefaultFilenamePrefix;
    protected File mDefaultScreenshotPath;
    protected String mFileNameDelimiter;
    protected String mTag;
    private static int sAndroidRuntimeVersion = Build.VERSION.SDK_INT;
    private static String sAndroidDeviceName = Build.DEVICE;

    public BasicScreenCaptureProcessor() {
        this(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "screenshots"));
    }

    BasicScreenCaptureProcessor(File defaultScreenshotPath) {
        this.mTag = "BasicScreenCaptureProcessor";
        this.mFileNameDelimiter = "-";
        this.mDefaultFilenamePrefix = "screenshot";
        this.mDefaultScreenshotPath = defaultScreenshotPath;
    }

    @Override // androidx.test.runner.screenshot.ScreenCaptureProcessor
    public String process(ScreenCapture capture) throws Throwable {
        String str = (capture.getName() == null ? getDefaultFilename() : getFilename(capture.getName())) + "." + capture.getFormat().toString().toLowerCase();
        File file = this.mDefaultScreenshotPath;
        file.mkdirs();
        if (!file.isDirectory() && !file.canWrite()) {
            throw new IOException(String.format("The directory %s does not exist and could not be created or is not writable.", file));
        }
        File file2 = new File(file, str);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file2));
            try {
                capture.getBitmap().compress(capture.getFormat(), 100, bufferedOutputStream2);
                bufferedOutputStream2.flush();
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e) {
                    Log.e(this.mTag, "Could not close output steam.", e);
                }
                return str;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2) {
                        Log.e(this.mTag, "Could not close output steam.", e2);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected String getDefaultFilename() {
        String str = this.mDefaultFilenamePrefix;
        String str2 = this.mFileNameDelimiter;
        return getFilename(str + str2 + sAndroidDeviceName + str2 + sAndroidRuntimeVersion);
    }

    protected String getFilename(String prefix) {
        return prefix + this.mFileNameDelimiter + String.valueOf(UUID.randomUUID());
    }

    static void setAndroidDeviceName(String deviceName) {
        sAndroidDeviceName = deviceName;
    }

    static void setAndroidRuntimeVersion(int sdkInt) {
        sAndroidRuntimeVersion = sdkInt;
    }
}
