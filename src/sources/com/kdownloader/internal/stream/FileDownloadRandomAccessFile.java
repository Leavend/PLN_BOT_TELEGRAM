package com.kdownloader.internal.stream;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: FileDownloadRandomAccessFile.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J\"\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/kdownloader/internal/stream/FileDownloadRandomAccessFile;", "Lcom/kdownloader/internal/stream/FileDownloadOutputStream;", StorageChooser.FILE_PICKER, "Ljava/io/File;", "(Ljava/io/File;)V", "fd", "Ljava/io/FileDescriptor;", "out", "Ljava/io/BufferedOutputStream;", "randomAccess", "Ljava/io/RandomAccessFile;", "close", "", "flushAndSync", "seek", TypedValues.CycleType.S_WAVE_OFFSET, "", "setLength", "newLength", "write", "b", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FileDownloadRandomAccessFile implements FileDownloadOutputStream {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FileDescriptor fd;
    private final BufferedOutputStream out;
    private final RandomAccessFile randomAccess;

    public /* synthetic */ FileDownloadRandomAccessFile(File file, DefaultConstructorMarker defaultConstructorMarker) {
        this(file);
    }

    private FileDownloadRandomAccessFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        this.randomAccess = randomAccessFile;
        FileDescriptor fd = randomAccessFile.getFD();
        Intrinsics.checkNotNullExpressionValue(fd, "randomAccess.fd");
        this.fd = fd;
        this.out = new BufferedOutputStream(new FileOutputStream(randomAccessFile.getFD()));
    }

    @Override // com.kdownloader.internal.stream.FileDownloadOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override // com.kdownloader.internal.stream.FileDownloadOutputStream
    public void flushAndSync() throws IOException {
        this.out.flush();
        this.fd.sync();
    }

    @Override // com.kdownloader.internal.stream.FileDownloadOutputStream
    public void close() throws IOException {
        this.out.close();
        this.randomAccess.close();
    }

    @Override // com.kdownloader.internal.stream.FileDownloadOutputStream
    public void seek(long offset) throws IOException {
        this.randomAccess.seek(offset);
    }

    @Override // com.kdownloader.internal.stream.FileDownloadOutputStream
    public void setLength(long newLength) throws IOException {
        this.randomAccess.setLength(newLength);
    }

    /* compiled from: FileDownloadRandomAccessFile.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/kdownloader/internal/stream/FileDownloadRandomAccessFile$Companion;", "", "()V", "create", "Lcom/kdownloader/internal/stream/FileDownloadOutputStream;", StorageChooser.FILE_PICKER, "Ljava/io/File;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FileDownloadOutputStream create(File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            return new FileDownloadRandomAccessFile(file, null);
        }
    }
}
