package org.mockito.internal.util.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import org.mockito.exceptions.base.MockitoException;

/* loaded from: classes3.dex */
public class IOUtil {
    public static void writeText(String str, File file) throws Throwable {
        PrintWriter printWriter;
        PrintWriter printWriter2 = null;
        try {
            try {
                printWriter = new PrintWriter(new FileWriter(file));
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            printWriter.write(str);
            close(printWriter);
        } catch (Exception e2) {
            e = e2;
            printWriter2 = printWriter;
            throw new MockitoException("Problems writing text to file: " + file, e);
        } catch (Throwable th2) {
            th = th2;
            printWriter2 = printWriter;
            close(printWriter2);
            throw th;
        }
    }

    public static Collection<String> readLines(InputStream inputStream) throws IOException {
        LinkedList linkedList = new LinkedList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return linkedList;
                }
                linkedList.add(line);
            } catch (IOException e) {
                throw new MockitoException("Problems reading from: " + inputStream, e);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) throws IOException {
        try {
            close(closeable);
        } catch (MockitoException unused) {
        }
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new MockitoException("Problems closing stream: " + closeable, e);
            }
        }
    }
}
