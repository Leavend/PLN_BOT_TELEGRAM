package androidx.test.platform.io;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.util.Checks;
import androidx.test.platform.io.PlatformTestStorageRegistry;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class PlatformTestStorageRegistry {
    private static PlatformTestStorage testStorageInstance = (PlatformTestStorage) ServiceLoaderWrapper.loadSingleService(PlatformTestStorage.class, new ServiceLoaderWrapper.Factory() { // from class: androidx.test.platform.io.PlatformTestStorageRegistry$$ExternalSyntheticLambda0
        @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
        public final Object create() {
            return new PlatformTestStorageRegistry.NoOpPlatformTestStorage();
        }
    });

    private PlatformTestStorageRegistry() {
    }

    public static synchronized void registerInstance(PlatformTestStorage instance) {
        testStorageInstance = (PlatformTestStorage) Checks.checkNotNull(instance);
    }

    public static synchronized PlatformTestStorage getInstance() {
        return testStorageInstance;
    }

    static class NoOpPlatformTestStorage implements PlatformTestStorage {
        @Override // androidx.test.platform.io.PlatformTestStorage
        public void addOutputProperties(Map<String, Serializable> properties) {
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public String getInputArg(String argName) {
            return null;
        }

        NoOpPlatformTestStorage() {
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public InputStream openInputFile(String pathname) {
            return new NullInputStream();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public Map<String, String> getInputArgs() {
            return new HashMap();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public OutputStream openOutputFile(String pathname) {
            return new NullOutputStream();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public OutputStream openOutputFile(String pathname, boolean append) {
            return new NullOutputStream();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public Map<String, Serializable> getOutputProperties() {
            return new HashMap();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public InputStream openInternalInputFile(String pathname) throws IOException {
            return new NullInputStream();
        }

        @Override // androidx.test.platform.io.PlatformTestStorage
        public OutputStream openInternalOutputFile(String pathname) throws IOException {
            return new NullOutputStream();
        }

        static class NullInputStream extends InputStream {
            @Override // java.io.InputStream
            public int read() {
                return -1;
            }

            NullInputStream() {
            }
        }

        static class NullOutputStream extends OutputStream {
            @Override // java.io.OutputStream
            public void write(int b) {
            }

            NullOutputStream() {
            }
        }
    }
}
