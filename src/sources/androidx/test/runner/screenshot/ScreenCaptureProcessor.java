package androidx.test.runner.screenshot;

import java.io.IOException;

@Deprecated
/* loaded from: classes5.dex */
public interface ScreenCaptureProcessor {
    String process(ScreenCapture capture) throws IOException;
}
