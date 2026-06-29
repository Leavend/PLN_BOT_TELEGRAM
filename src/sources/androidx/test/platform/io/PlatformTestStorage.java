package androidx.test.platform.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes5.dex */
public interface PlatformTestStorage {
    void addOutputProperties(Map<String, Serializable> properties);

    String getInputArg(String argName);

    Map<String, String> getInputArgs();

    Map<String, Serializable> getOutputProperties();

    InputStream openInputFile(String pathname) throws IOException;

    InputStream openInternalInputFile(String pathname) throws IOException;

    OutputStream openInternalOutputFile(String pathname) throws IOException;

    OutputStream openOutputFile(String pathname) throws IOException;

    OutputStream openOutputFile(String pathname, boolean append) throws IOException;
}
