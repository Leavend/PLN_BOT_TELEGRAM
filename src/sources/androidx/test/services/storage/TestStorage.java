package androidx.test.services.storage;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.services.storage.file.HostedFile;
import androidx.test.services.storage.file.PropertyFile;
import androidx.test.services.storage.internal.TestStorageUtil;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes5.dex */
public final class TestStorage implements PlatformTestStorage {
    private static final String PROPERTIES_FILE_NAME = "properties.dat";
    private static final String TAG = "TestStorage";
    private final ContentResolver contentResolver;

    public TestStorage() {
        this(InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver());
    }

    public TestStorage(@Nonnull ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public static Uri getInputFileUri(@Nonnull String pathname) {
        Checks.checkNotNull(pathname);
        return HostedFile.buildUri(HostedFile.FileHost.TEST_FILE, pathname);
    }

    public static Uri getOutputFileUri(@Nonnull String pathname) {
        Checks.checkNotNull(pathname);
        return HostedFile.buildUri(HostedFile.FileHost.OUTPUT, pathname);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public InputStream openInputFile(@Nonnull String pathname) throws FileNotFoundException {
        return TestStorageUtil.getInputStream(getInputFileUri(pathname), this.contentResolver);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public String getInputArg(@Nonnull String argName) {
        Checks.checkNotNull(argName);
        Uri uriBuildUri = PropertyFile.buildUri(PropertyFile.Authority.TEST_ARGS, argName);
        Cursor cursor = null;
        try {
            Cursor cursorDoQuery = doQuery(this.contentResolver, uriBuildUri);
            if (cursorDoQuery.getCount() == 0) {
                throw new TestStorageException(String.format("Query for URI '%s' did not return any results. Make sure the argName is actually being passed in as a test argument.", uriBuildUri));
            }
            if (cursorDoQuery.getCount() > 1) {
                throw new TestStorageException(String.format("Query for URI '%s' returned more than one result. Weird.", uriBuildUri));
            }
            cursorDoQuery.moveToFirst();
            String string = cursorDoQuery.getString(PropertyFile.Column.VALUE.getPosition());
            if (cursorDoQuery != null) {
                cursorDoQuery.close();
            }
            return string;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public Map<String, String> getInputArgs() {
        Cursor cursorDoQuery = null;
        try {
            cursorDoQuery = doQuery(this.contentResolver, PropertyFile.buildUri(PropertyFile.Authority.TEST_ARGS));
            return getProperties(cursorDoQuery);
        } finally {
            if (cursorDoQuery != null) {
                cursorDoQuery.close();
            }
        }
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openOutputFile(@Nonnull String pathname) throws FileNotFoundException {
        return openOutputFile(pathname, false);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openOutputFile(@Nonnull String pathname, boolean append) throws FileNotFoundException {
        Checks.checkNotNull(pathname);
        return TestStorageUtil.getOutputStream(getOutputFileUri(pathname), this.contentResolver, append);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public void addOutputProperties(Map<String, Serializable> properties) throws Throwable {
        ObjectOutputStream objectOutputStream;
        if (properties == null || properties.isEmpty()) {
            return;
        }
        Map<String, Serializable> outputProperties = getOutputProperties();
        outputProperties.putAll(properties);
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(TestStorageUtil.getOutputStream(getPropertyFileUri(), this.contentResolver)));
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException e) {
            e = e;
        } catch (IOException e2) {
            e = e2;
        }
        try {
            objectOutputStream.writeObject(outputProperties);
            silentlyClose(objectOutputStream);
        } catch (FileNotFoundException e3) {
            e = e3;
            throw new TestStorageException("Unable to create file", e);
        } catch (IOException e4) {
            e = e4;
            throw new TestStorageException("I/O error occurred during reading test properties.", e);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            silentlyClose(objectOutputStream2);
            throw th;
        }
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public Map<String, Serializable> getOutputProperties() throws Throwable {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        Uri propertyFileUri = getPropertyFileUri();
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                inputStream = TestStorageUtil.getInputStream(propertyFileUri, this.contentResolver);
                try {
                    objectInputStream = new ObjectInputStream(inputStream);
                } catch (FileNotFoundException unused) {
                } catch (IOException e) {
                    e = e;
                } catch (ClassNotFoundException e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                Map<String, Serializable> map = (Map) objectInputStream.readObject();
                if (map == null) {
                    map = new HashMap<>();
                }
                silentlyClose(objectInputStream);
                silentlyClose(inputStream);
                return map;
            } catch (FileNotFoundException unused2) {
                objectInputStream2 = objectInputStream;
                Log.i(TAG, String.format("%s: does not exist, we must be the first call.", propertyFileUri));
                silentlyClose(objectInputStream2);
                silentlyClose(inputStream);
                return new HashMap();
            } catch (IOException e3) {
                e = e3;
                objectInputStream2 = objectInputStream;
                Log.w(TAG, "Failed to read recorded stats!", e);
                silentlyClose(objectInputStream2);
                silentlyClose(inputStream);
                return new HashMap();
            } catch (ClassNotFoundException e4) {
                e = e4;
                objectInputStream2 = objectInputStream;
                Log.w(TAG, "Failed to read recorded stats!", e);
                silentlyClose(objectInputStream2);
                silentlyClose(inputStream);
                return new HashMap();
            } catch (Throwable th2) {
                th = th2;
                objectInputStream2 = objectInputStream;
                silentlyClose(objectInputStream2);
                silentlyClose(inputStream);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            inputStream = null;
        } catch (IOException e5) {
            e = e5;
            inputStream = null;
            Log.w(TAG, "Failed to read recorded stats!", e);
            silentlyClose(objectInputStream2);
            silentlyClose(inputStream);
            return new HashMap();
        } catch (ClassNotFoundException e6) {
            e = e6;
            inputStream = null;
            Log.w(TAG, "Failed to read recorded stats!", e);
            silentlyClose(objectInputStream2);
            silentlyClose(inputStream);
            return new HashMap();
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public InputStream openInternalInputFile(String pathname) throws IOException {
        Checks.checkNotNull(pathname);
        return TestStorageUtil.getInputStream(HostedFile.buildUri(HostedFile.FileHost.INTERNAL_USE_ONLY, pathname), this.contentResolver);
    }

    @Override // androidx.test.platform.io.PlatformTestStorage
    public OutputStream openInternalOutputFile(String pathname) throws IOException {
        Checks.checkNotNull(pathname);
        return TestStorageUtil.getOutputStream(HostedFile.buildUri(HostedFile.FileHost.INTERNAL_USE_ONLY, pathname), this.contentResolver);
    }

    private static Uri getPropertyFileUri() {
        return HostedFile.buildUri(HostedFile.FileHost.EXPORT_PROPERTIES, PROPERTIES_FILE_NAME);
    }

    private static Cursor doQuery(ContentResolver resolver, Uri uri) {
        Checks.checkNotNull(resolver);
        Checks.checkNotNull(uri);
        Cursor cursorQuery = resolver.query(uri, null, null, null, null);
        if (cursorQuery != null) {
            return cursorQuery;
        }
        throw new TestStorageException(String.format("Failed to resolve query for URI: %s", uri));
    }

    private static Map<String, String> getProperties(Cursor cursor) {
        Checks.checkNotNull(cursor);
        HashMap map = new HashMap();
        while (cursor.moveToNext()) {
            map.put(cursor.getString(PropertyFile.Column.NAME.getPosition()), cursor.getString(PropertyFile.Column.VALUE.getPosition()));
        }
        return map;
    }

    private static void silentlyClose(InputStream in) throws IOException {
        if (in != null) {
            try {
                in.close();
            } catch (IOException unused) {
            }
        }
    }

    private static void silentlyClose(OutputStream out) throws IOException {
        if (out != null) {
            try {
                out.close();
            } catch (IOException unused) {
            }
        }
    }
}
