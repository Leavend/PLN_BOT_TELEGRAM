package org.apache.commons.codec.digest;

import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes3.dex */
public final class MurmurHash2 {
    private MurmurHash2() {
    }

    public static int hash32(byte[] bArr, int i, int i2) {
        int i3 = i2 ^ i;
        int i4 = i / 4;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 4;
            int i7 = ((bArr[i6 + 0] & 255) + ((bArr[i6 + 1] & 255) << 8) + ((bArr[i6 + 2] & 255) << 16) + ((bArr[i6 + 3] & 255) << 24)) * 1540483477;
            i3 = (i3 * 1540483477) ^ (((i7 >>> 24) ^ i7) * 1540483477);
        }
        int i8 = i % 4;
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 == 3) {
                    i3 ^= (bArr[(i & (-4)) + 2] & 255) << 16;
                }
            }
            i3 ^= (bArr[(i & (-4)) + 1] & 255) << 8;
            i3 = ((bArr[i & (-4)] & 255) ^ i3) * 1540483477;
        } else {
            i3 = ((bArr[i & (-4)] & 255) ^ i3) * 1540483477;
        }
        int i9 = ((i3 >>> 13) ^ i3) * 1540483477;
        return i9 ^ (i9 >>> 15);
    }

    public static int hash32(byte[] bArr, int i) {
        return hash32(bArr, i, -1756908916);
    }

    public static int hash32(String str) {
        byte[] bytes = str.getBytes();
        return hash32(bytes, bytes.length);
    }

    public static int hash32(String str, int i, int i2) {
        return hash32(str.substring(i, i2 + i));
    }

    public static long hash64(byte[] bArr, int i, int i2) {
        long j;
        long j2 = (i2 & InternalZipConstants.ZIP_64_SIZE_LIMIT) ^ (i * (-4132994306676758123L));
        for (int i3 = 0; i3 < i / 8; i3++) {
            int i4 = i3 * 8;
            long j3 = ((bArr[i4 + 0] & 255) + ((bArr[i4 + 1] & 255) << 8) + ((bArr[i4 + 2] & 255) << 16) + ((bArr[i4 + 3] & 255) << 24) + ((bArr[i4 + 4] & 255) << 32) + ((bArr[i4 + 5] & 255) << 40) + ((bArr[i4 + 6] & 255) << 48) + ((bArr[i4 + 7] & 255) << 56)) * (-4132994306676758123L);
            j2 = (j2 ^ ((j3 ^ (j3 >>> 47)) * (-4132994306676758123L))) * (-4132994306676758123L);
        }
        switch (i % 8) {
            case 7:
                j2 ^= (bArr[(i & (-8)) + 6] & 255) << 48;
            case 6:
                j2 ^= (bArr[(i & (-8)) + 5] & 255) << 40;
            case 5:
                j2 ^= (bArr[(i & (-8)) + 4] & 255) << 32;
            case 4:
                j2 ^= (bArr[(i & (-8)) + 3] & 255) << 24;
            case 3:
                j2 ^= (bArr[(i & (-8)) + 2] & 255) << 16;
            case 2:
                j2 ^= (bArr[(i & (-8)) + 1] & 255) << 8;
            case 1:
                j = -4132994306676758123L;
                j2 = (j2 ^ (bArr[i & (-8)] & 255)) * (-4132994306676758123L);
                break;
            default:
                j = -4132994306676758123L;
                break;
        }
        long j4 = (j2 ^ (j2 >>> 47)) * j;
        return j4 ^ (j4 >>> 47);
    }

    public static long hash64(byte[] bArr, int i) {
        return hash64(bArr, i, -512093083);
    }

    public static long hash64(String str) {
        byte[] bytes = str.getBytes();
        return hash64(bytes, bytes.length);
    }

    public static long hash64(String str, int i, int i2) {
        return hash64(str.substring(i, i2 + i));
    }
}
