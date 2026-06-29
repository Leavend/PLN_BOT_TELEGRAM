package org.mockito.internal.invocation;

import java.io.Serializable;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.StubInfo;

/* loaded from: classes3.dex */
public class StubInfoImpl implements StubInfo, Serializable {
    private static final long serialVersionUID = 2125827349332068867L;
    private final DescribedInvocation stubbedAt;

    public StubInfoImpl(DescribedInvocation describedInvocation) {
        this.stubbedAt = describedInvocation;
    }

    @Override // org.mockito.invocation.StubInfo
    public Location stubbedAt() {
        return this.stubbedAt.getLocation();
    }
}
