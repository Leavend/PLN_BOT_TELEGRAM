package org.mockito.internal.exceptions.util;

import java.util.List;
import org.mockito.internal.exceptions.VerificationAwareInvocation;

/* loaded from: classes3.dex */
public class ScenarioPrinter {
    public String print(List<VerificationAwareInvocation> list) {
        if (list.size() == 1) {
            return "Actually, above is the only interaction with this mock.";
        }
        StringBuilder sb = new StringBuilder("***\nFor your reference, here is the list of all invocations ([?] - means unverified).\n");
        int i = 0;
        for (VerificationAwareInvocation verificationAwareInvocation : list) {
            i++;
            sb.append(i).append(". ");
            if (!verificationAwareInvocation.isVerified()) {
                sb.append("[?]");
            }
            sb.append(verificationAwareInvocation.getLocation()).append("\n");
        }
        return sb.toString();
    }
}
