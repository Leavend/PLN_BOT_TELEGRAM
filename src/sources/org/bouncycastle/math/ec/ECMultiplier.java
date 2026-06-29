package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes3.dex */
interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger, PreCompInfo preCompInfo);
}
