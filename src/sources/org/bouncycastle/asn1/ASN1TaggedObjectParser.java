package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ASN1TaggedObjectParser extends DEREncodable {
    DEREncodable getObjectParser(int i, boolean z) throws IOException;

    int getTagNo();
}
