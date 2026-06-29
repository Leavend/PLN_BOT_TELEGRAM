package org.mockito.internal.framework;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import org.mockito.MockitoFramework;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.invocation.DefaultInvocationFactory;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.util.Checks;
import org.mockito.invocation.InvocationFactory;
import org.mockito.listeners.MockitoListener;
import org.mockito.plugins.MockitoPlugins;

/* loaded from: classes3.dex */
public class DefaultMockitoFramework implements MockitoFramework {
    @Override // org.mockito.MockitoFramework
    public MockitoFramework addListener(MockitoListener mockitoListener) {
        Checks.checkNotNull(mockitoListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ThreadSafeMockingProgress.mockingProgress().addListener(mockitoListener);
        return this;
    }

    @Override // org.mockito.MockitoFramework
    public MockitoFramework removeListener(MockitoListener mockitoListener) {
        Checks.checkNotNull(mockitoListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ThreadSafeMockingProgress.mockingProgress().removeListener(mockitoListener);
        return this;
    }

    @Override // org.mockito.MockitoFramework
    public MockitoPlugins getPlugins() {
        return Plugins.getPlugins();
    }

    @Override // org.mockito.MockitoFramework
    public InvocationFactory getInvocationFactory() {
        return new DefaultInvocationFactory();
    }
}
