package org.mockito.internal.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mockito.MockitoSession;
import org.mockito.internal.framework.DefaultMockitoSession;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;
import org.mockito.session.MockitoSessionBuilder;
import org.mockito.session.MockitoSessionLogger;

/* loaded from: classes3.dex */
public class DefaultMockitoSessionBuilder implements MockitoSessionBuilder {
    private MockitoSessionLogger logger;
    private String name;
    private Strictness strictness;
    private List<Object> testClassInstances = new ArrayList();

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder initMocks(Object obj) {
        if (obj != null) {
            this.testClassInstances.add(obj);
        }
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder initMocks(Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                initMocks(obj);
            }
        }
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder name(String str) {
        this.name = str;
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder strictness(Strictness strictness) {
        this.strictness = strictness;
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSessionBuilder logger(MockitoSessionLogger mockitoSessionLogger) {
        this.logger = mockitoSessionLogger;
        return this;
    }

    @Override // org.mockito.session.MockitoSessionBuilder
    public MockitoSession startMocking() {
        List arrayList;
        String name;
        if (this.testClassInstances.isEmpty()) {
            arrayList = Collections.emptyList();
            name = this.name;
            if (name == null) {
                name = "<Unnamed Session>";
            }
        } else {
            arrayList = new ArrayList(this.testClassInstances);
            Object obj = this.testClassInstances.get(r1.size() - 1);
            String str = this.name;
            name = str == null ? obj.getClass().getName() : str;
        }
        Strictness strictness = this.strictness;
        if (strictness == null) {
            strictness = Strictness.STRICT_STUBS;
        }
        return new DefaultMockitoSession(arrayList, name, strictness, this.logger == null ? new ConsoleMockitoLogger() : new MockitoLoggerAdapter(this.logger));
    }
}
