package org.bouncycastle.crypto.util;

import com.google.common.base.Ascii;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes3.dex */
public abstract class Pack {
    public static int bigEndianToInt(byte[] bArr, int i) {
        int i2 = bArr[i] << Ascii.CAN;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
    }

    public static long bigEndianToLong(byte[] bArr, int i) {
        int iBigEndianToInt = bigEndianToInt(bArr, i);
        return (bigEndianToInt(bArr, i + 4) & InternalZipConstants.ZIP_64_SIZE_LIMIT) | ((iBigEndianToInt & InternalZipConstants.ZIP_64_SIZE_LIMIT) << 32);
    }

    public static void intToBigEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public static void longToBigEndian(long j, byte[] bArr, int i) {
        intToBigEndian((int) (j >>> 32), bArr, i);
        intToBigEndian((int) (j & InternalZipConstants.ZIP_64_SIZE_LIMIT), bArr, i + 4);
    }
}
