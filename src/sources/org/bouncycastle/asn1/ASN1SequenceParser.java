package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface ASN1SequenceParser extends DEREncodable {
    DEREncodable readObject() throws IOException;
}
