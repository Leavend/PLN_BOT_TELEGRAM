package androidx.test.core.graphics;

import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.services.storage.TestStorage;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapStorageExt.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"writeToTestStorage", "", "Landroid/graphics/Bitmap;", "testStorage", "Landroidx/test/platform/io/PlatformTestStorage;", "name", "", "androidx.test.core"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class BitmapStorage {
    public static final void writeToTestStorage(Bitmap $this$writeToTestStorage, String name) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeToTestStorage, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        writeToTestStorage($this$writeToTestStorage, new TestStorage(), name);
    }

    public static final void writeToTestStorage(Bitmap $this$writeToTestStorage, PlatformTestStorage testStorage, String name) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeToTestStorage, "<this>");
        Intrinsics.checkNotNullParameter(testStorage, "testStorage");
        Intrinsics.checkNotNullParameter(name, "name");
        OutputStream outputStreamOpenOutputFile = testStorage.openOutputFile(name + ".png");
        try {
            if (!$this$writeToTestStorage.compress(Bitmap.CompressFormat.PNG, 0, outputStreamOpenOutputFile)) {
                throw new IOException("Failed to compress bitmap");
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStreamOpenOutputFile, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(outputStreamOpenOutputFile, th);
                throw th2;
            }
        }
    }
}
