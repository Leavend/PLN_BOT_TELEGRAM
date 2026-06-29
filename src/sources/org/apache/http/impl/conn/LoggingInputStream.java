package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
class LoggingInputStream extends InputStream {
    private final InputStream in;
    private final Wire wire;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public LoggingInputStream(InputStream inputStream, Wire wire) {
        this.in = inputStream;
        this.wire = wire;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.in.read();
        if (i != -1) {
            this.wire.input(i);
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int i = this.in.read(bArr);
        if (i != -1) {
            this.wire.input(bArr, 0, i);
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.in.read(bArr, i, i2);
        if (i3 != -1) {
            this.wire.input(bArr, i, i3);
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return super.skip(j);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        super.mark(i);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }
}
