package org.mockito.internal.session;

import org.mockito.internal.util.MockitoLogger;
import org.mockito.session.MockitoSessionLogger;

/* loaded from: classes3.dex */
public class MockitoSessionLoggerAdapter implements MockitoSessionLogger {
    private final MockitoLogger logger;

    public MockitoSessionLoggerAdapter(MockitoLogger mockitoLogger) {
        this.logger = mockitoLogger;
    }

    @Override // org.mockito.session.MockitoSessionLogger
    public void log(String str) {
        this.logger.log(str);
    }
}
