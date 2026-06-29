package androidx.test.platform.io;

import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class FileTestStorage implements PlatformTestStorage {
    private static final String TAG = "FileTestStorage";

    @Override // androidx.test.platform.io.PlatformTestStorage
    public InputStream openInputFile(String pathname) throws IOException {
        return new FileInputStream(pathname);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openOutputFile(String pathname) throws IOException {
        return new FileOutputStream(pathname);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openOutputFile(String pathname, boolean append) throws IOException {
        return new FileOutputStream(pathname, append);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public String getInputArg(String argName) {
        Log.w(TAG, "Test input args is not supported.");
        return null;
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public Map<String, String> getInputArgs() {
        Log.w(TAG, "Test input args is not supported.");
        return new HashMap();
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public void addOutputProperties(Map<String, Serializable> properties) {
        Log.w(TAG, "Output properties is not supported.");
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public Map<String, Serializable> getOutputProperties() {
        Log.w(TAG, "Output properties is not supported.");
        return new HashMap();
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public InputStream openInternalInputFile(String pathname) throws IOException {
        return new FileInputStream(pathname);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openInternalOutputFile(String pathname) throws IOException {
        return new FileOutputStream(pathname);
    }
}
