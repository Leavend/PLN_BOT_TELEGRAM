package org.junit.internal.management;

import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
class FakeRuntimeMXBean implements RuntimeMXBean {
    FakeRuntimeMXBean() {
    }

    @Override // org.junit.internal.management.RuntimeMXBean
    public List<String> getInputArguments() {
        return Collections.emptyList();
    }
}
