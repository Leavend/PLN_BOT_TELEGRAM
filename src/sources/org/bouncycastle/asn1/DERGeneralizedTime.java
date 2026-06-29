package org.bouncycastle.asn1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class DERGeneralizedTime extends ASN1Object {
    String time;

    public DERGeneralizedTime(String str) {
        this.time = str;
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public DERGeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = simpleDateFormat.format(date);
    }

    DERGeneralizedTime(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.time = new String(cArr);
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = "+";
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + convert(i) + ":" + convert(i2);
    }

    private String convert(int i) {
        return i < 10 ? "0" + i : Integer.toString(i);
    }

    public static DERGeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGeneralizedTime)) {
            return (DERGeneralizedTime) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new DERGeneralizedTime(((ASN1OctetString) obj).getOctets());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERGeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    private byte[] getOctets() {
        char[] charArray = this.time.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }

    private boolean hasFractionalSeconds() {
        return this.time.indexOf(46) == 14;
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERGeneralizedTime) {
            return this.time.equals(((DERGeneralizedTime) dERObject).time);
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject
    void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(24, getOctets());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Date getDate() throws java.text.ParseException {
        /*
            r8 = this;
            java.lang.String r0 = r8.time
            java.lang.String r1 = "Z"
            boolean r2 = r0.endsWith(r1)
            r3 = 0
            if (r2 == 0) goto L29
            boolean r2 = r8.hasFractionalSeconds()
            if (r2 == 0) goto L19
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss.SSS'Z'"
            r2.<init>(r4)
            goto L20
        L19:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss'Z'"
            r2.<init>(r4)
        L20:
            java.util.SimpleTimeZone r4 = new java.util.SimpleTimeZone
            r4.<init>(r3, r1)
        L25:
            r2.setTimeZone(r4)
            goto L84
        L29:
            java.lang.String r2 = r8.time
            r4 = 45
            int r2 = r2.indexOf(r4)
            if (r2 > 0) goto L65
            java.lang.String r2 = r8.time
            r4 = 43
            int r2 = r2.indexOf(r4)
            if (r2 <= 0) goto L3e
            goto L65
        L3e:
            boolean r1 = r8.hasFractionalSeconds()
            if (r1 == 0) goto L4c
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyyMMddHHmmss.SSS"
            r1.<init>(r2)
            goto L53
        L4c:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyyMMddHHmmss"
            r1.<init>(r2)
        L53:
            r2 = r1
            java.util.SimpleTimeZone r1 = new java.util.SimpleTimeZone
            java.util.TimeZone r4 = java.util.TimeZone.getDefault()
            java.lang.String r4 = r4.getID()
            r1.<init>(r3, r4)
            r2.setTimeZone(r1)
            goto L84
        L65:
            java.lang.String r0 = r8.getTime()
            boolean r2 = r8.hasFractionalSeconds()
            if (r2 == 0) goto L77
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmss.SSSz"
            r2.<init>(r4)
            goto L7e
        L77:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat
            java.lang.String r4 = "yyyyMMddHHmmssz"
            r2.<init>(r4)
        L7e:
            java.util.SimpleTimeZone r4 = new java.util.SimpleTimeZone
            r4.<init>(r3, r1)
            goto L25
        L84:
            boolean r1 = r8.hasFractionalSeconds()
            if (r1 == 0) goto Ldb
            r1 = 14
            java.lang.String r4 = r0.substring(r1)
            r5 = 1
        L91:
            int r6 = r4.length()
            if (r5 >= r6) goto La7
            char r6 = r4.charAt(r5)
            r7 = 48
            if (r7 > r6) goto La7
            r7 = 57
            if (r6 <= r7) goto La4
            goto La7
        La4:
            int r5 = r5 + 1
            goto L91
        La7:
            int r6 = r5 + (-1)
            r7 = 3
            if (r6 <= r7) goto Ldb
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = 4
            java.lang.String r7 = r4.substring(r3, r7)
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r4 = r4.substring(r5)
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = r0.substring(r3, r1)
            java.lang.StringBuilder r0 = r5.append(r0)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
        Ldb:
            java.util.Date r0 = r2.parse(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.DERGeneralizedTime.getDate():java.util.Date");
    }

    public String getTime() {
        StringBuilder sbAppend;
        String strSubstring;
        if (this.time.charAt(r0.length() - 1) == 'Z') {
            sbAppend = new StringBuilder().append(this.time.substring(0, r1.length() - 1));
            strSubstring = "GMT+00:00";
        } else {
            int length = this.time.length() - 5;
            char cCharAt = this.time.charAt(length);
            if (cCharAt == '-' || cCharAt == '+') {
                int i = length + 3;
                sbAppend = new StringBuilder().append(this.time.substring(0, length)).append("GMT").append(this.time.substring(length, i)).append(":");
                strSubstring = this.time.substring(i);
            } else {
                int length2 = this.time.length() - 3;
                char cCharAt2 = this.time.charAt(length2);
                if (cCharAt2 == '-' || cCharAt2 == '+') {
                    sbAppend = new StringBuilder().append(this.time.substring(0, length2)).append("GMT").append(this.time.substring(length2));
                    strSubstring = ":00";
                } else {
                    sbAppend = new StringBuilder().append(this.time);
                    strSubstring = calculateGMTOffset();
                }
            }
        }
        return sbAppend.append(strSubstring).toString();
    }

    public String getTimeString() {
        return this.time;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject, org.bouncycastle.asn1.ASN1Encodable
    public int hashCode() {
        return this.time.hashCode();
    }
}
