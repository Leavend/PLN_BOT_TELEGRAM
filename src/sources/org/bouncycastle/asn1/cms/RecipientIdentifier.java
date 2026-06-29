package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes3.dex */
public class RecipientIdentifier extends ASN1Encodable implements ASN1Choice {

    /* renamed from: id, reason: collision with root package name */
    private DEREncodable f177id;

    public RecipientIdentifier(ASN1OctetString aSN1OctetString) {
        this.f177id = new DERTaggedObject(false, 0, aSN1OctetString);
    }

    public RecipientIdentifier(DERObject dERObject) {
        this.f177id = dERObject;
    }

    public RecipientIdentifier(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.f177id = issuerAndSerialNumber;
    }

    public static RecipientIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof RecipientIdentifier)) {
            return (RecipientIdentifier) obj;
        }
        if (obj instanceof IssuerAndSerialNumber) {
            return new RecipientIdentifier((IssuerAndSerialNumber) obj);
        }
        if (obj instanceof ASN1OctetString) {
            return new RecipientIdentifier((ASN1OctetString) obj);
        }
        if (obj instanceof DERObject) {
            return new RecipientIdentifier((DERObject) obj);
        }
        throw new IllegalArgumentException("Illegal object in RecipientIdentifier: " + obj.getClass().getName());
    }

    public DEREncodable getId() {
        DEREncodable dEREncodable = this.f177id;
        return dEREncodable instanceof ASN1TaggedObject ? ASN1OctetString.getInstance((ASN1TaggedObject) dEREncodable, false) : IssuerAndSerialNumber.getInstance(dEREncodable);
    }

    public boolean isTagged() {
        return this.f177id instanceof ASN1TaggedObject;
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public DERObject toASN1Object() {
        return this.f177id.getDERObject();
    }
}
