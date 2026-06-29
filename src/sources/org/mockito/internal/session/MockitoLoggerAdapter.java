package org.mockito.internal.session;

import org.mockito.internal.util.MockitoLogger;
import org.mockito.session.MockitoSessionLogger;

/* loaded from: classes3.dex */
class MockitoLoggerAdapter implements MockitoLogger {
    private final MockitoSessionLogger logger;

    MockitoLoggerAdapter(MockitoSessionLogger mockitoSessionLogger) {
        this.logger = mockitoSessionLogger;
    }

    @Override // org.mockito.internal.util.MockitoLogger
    public void log(Object obj) {
        this.logger.log(String.valueOf(obj));
    }
}
