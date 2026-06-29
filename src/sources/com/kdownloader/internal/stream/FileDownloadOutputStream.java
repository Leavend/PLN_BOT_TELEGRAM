package com.kdownloader.internal.stream;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* compiled from: FileDownloadOutputStream.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\"\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&¨\u0006\u0010"}, d2 = {"Lcom/kdownloader/internal/stream/FileDownloadOutputStream;", "", "close", "", "flushAndSync", "seek", TypedValues.CycleType.S_WAVE_OFFSET, "", "setLength", "newLength", "write", "b", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FileDownloadOutputStream {
    void close() throws IOException;

    void flushAndSync() throws IOException;

    void seek(long offset) throws IllegalAccessException, IOException;

    void setLength(long newLength) throws IllegalAccessException, IOException;

    void write(byte[] b, int off, int len) throws IOException;
}
