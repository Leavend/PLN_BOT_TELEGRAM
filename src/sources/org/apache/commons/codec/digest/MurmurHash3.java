package org.apache.commons.codec.digest;

import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes3.dex */
public final class MurmurHash3 {
    private static final long C1 = -8663945395140668459L;
    private static final int C1_32 = -862048943;
    private static final long C2 = 5545529020109919103L;
    private static final int C2_32 = 461845907;
    public static final int DEFAULT_SEED = 104729;
    static final int INTEGER_BYTES = 4;
    static final int LONG_BYTES = 8;
    private static final int M = 5;
    private static final int M_32 = 5;
    private static final int N1 = 1390208809;
    private static final int N2 = 944331445;
    public static final long NULL_HASHCODE = 2862933555777941757L;
    private static final int N_32 = -430675100;
    private static final int R1 = 31;
    private static final int R1_32 = 15;
    private static final int R2 = 27;
    private static final int R2_32 = 13;
    private static final int R3 = 33;
    static final int SHORT_BYTES = 2;

    private static int fmix32(int i, int i2) {
        int i3 = i ^ i2;
        int i4 = (i3 ^ (i3 >>> 16)) * (-2048144789);
        int i5 = (i4 ^ (i4 >>> 13)) * (-1028477387);
        return i5 ^ (i5 >>> 16);
    }

    private static long fmix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int orBytes(byte b, byte b2, byte b3, byte b4) {
        return (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16) | ((b4 & 255) << 24);
    }

    private MurmurHash3() {
    }

    public static int hash32(long j, long j2) {
        return hash32(j, j2, DEFAULT_SEED);
    }

    public static int hash32(long j) {
        return hash32(j, DEFAULT_SEED);
    }

    public static int hash32(long j, int i) {
        long jReverseBytes = Long.reverseBytes(j);
        return fmix32(8, mix32((int) (jReverseBytes >>> 32), mix32((int) jReverseBytes, i)));
    }

    public static int hash32(long j, long j2, int i) {
        long jReverseBytes = Long.reverseBytes(j);
        long jReverseBytes2 = Long.reverseBytes(j2);
        int i2 = (int) jReverseBytes2;
        return fmix32(16, mix32((int) (jReverseBytes2 >>> 32), mix32(i2, mix32((int) (jReverseBytes >>> 32), mix32((int) jReverseBytes, i)))));
    }

    public static int hash32(byte[] bArr) {
        return hash32(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    public static int hash32(String str) {
        byte[] bytes = str.getBytes();
        return hash32(bytes, 0, bytes.length, DEFAULT_SEED);
    }

    public static int hash32(byte[] bArr, int i) {
        return hash32(bArr, i, DEFAULT_SEED);
    }

    public static int hash32(byte[] bArr, int i, int i2) {
        return hash32(bArr, 0, i, i2);
    }

    public static int hash32(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 >> 2;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = (i5 << 2) + i;
            i3 = mix32(((bArr[i6 + 3] & 255) << 24) | (bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8) | ((bArr[i6 + 2] & 255) << 16), i3);
        }
        int i7 = i4 << 2;
        int i8 = i2 - i7;
        if (i8 != 1) {
            if (i8 != 2) {
                i = i8 == 3 ? 0 ^ (bArr[(i + i7) + 2] << 16) : 0;
            }
            i ^= bArr[(i + i7) + 1] << 8;
            i3 ^= Integer.rotateLeft((bArr[i + i7] ^ i) * (-862048943), 15) * C2_32;
        } else {
            i3 ^= Integer.rotateLeft((bArr[i + i7] ^ i) * (-862048943), 15) * C2_32;
        }
        return fmix32(i2, i3);
    }

    public static long hash64(byte[] bArr) {
        return hash64(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    public static long hash64(long j) {
        return fmix64(((Long.rotateLeft((Long.rotateLeft(Long.reverseBytes(j) * C1, 31) * C2) ^ 104729, 27) * 5) + 1390208809) ^ 8);
    }

    public static long hash64(int i) {
        return fmix64(((Long.rotateLeft((Integer.reverseBytes(i) & InternalZipConstants.ZIP_64_SIZE_LIMIT) * C1, 31) * C2) ^ 104729) ^ 4);
    }

    public static long hash64(short s) {
        return fmix64(((Long.rotateLeft(((((s & 255) << 8) ^ 0) ^ (255 & ((s & 65280) >> 8))) * C1, 31) * C2) ^ 104729) ^ 2);
    }

    public static long hash64(byte[] bArr, int i, int i2) {
        return hash64(bArr, i, i2, DEFAULT_SEED);
    }

    public static long hash64(byte[] bArr, int i, int i2, int i3) {
        long jRotateLeft = i3;
        int i4 = i2 >> 3;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + (i5 << 3);
            jRotateLeft = (Long.rotateLeft(jRotateLeft ^ (Long.rotateLeft(((((((((bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8)) | ((bArr[i6 + 2] & 255) << 16)) | ((bArr[i6 + 3] & 255) << 24)) | ((bArr[i6 + 4] & 255) << 32)) | ((bArr[i6 + 5] & 255) << 40)) | ((bArr[i6 + 6] & 255) << 48)) | ((bArr[i6 + 7] & 255) << 56)) * C1, 31) * C2), 27) * 5) + 1390208809;
        }
        long j = 0;
        switch (i2 - (i4 << 3)) {
            case 7:
                j = 0 ^ ((bArr[(i + r3) + 6] & 255) << 48);
            case 6:
                j ^= (bArr[(i + r3) + 5] & 255) << 40;
            case 5:
                j ^= (bArr[(i + r3) + 4] & 255) << 32;
            case 4:
                j ^= (bArr[(i + r3) + 3] & 255) << 24;
            case 3:
                j ^= (bArr[(i + r3) + 2] & 255) << 16;
            case 2:
                j ^= (bArr[(i + r3) + 1] & 255) << 8;
            case 1:
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j) * C1, 31) * C2;
                break;
        }
        return fmix64(jRotateLeft ^ i2);
    }

    public static long[] hash128(byte[] bArr) {
        return hash128(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    public static long[] hash128(String str) {
        byte[] bytes = str.getBytes();
        return hash128(bytes, 0, bytes.length, DEFAULT_SEED);
    }

    public static long[] hash128(byte[] bArr, int i, int i2, int i3) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long jRotateLeft = i3;
        int i4 = i2 >> 4;
        long jRotateLeft2 = jRotateLeft;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + (i5 << 4);
            jRotateLeft = ((Long.rotateLeft(jRotateLeft ^ (Long.rotateLeft(((((((((bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8)) | ((bArr[i6 + 2] & 255) << 16)) | ((bArr[i6 + 3] & 255) << 24)) | ((bArr[i6 + 4] & 255) << 32)) | ((bArr[i6 + 5] & 255) << 40)) | ((bArr[i6 + 6] & 255) << 48)) | ((bArr[i6 + 7] & 255) << 56)) * C1, 31) * C2), 27) + jRotateLeft2) * 5) + 1390208809;
            jRotateLeft2 = ((Long.rotateLeft(jRotateLeft2 ^ (Long.rotateLeft((((((((((bArr[i6 + 9] & 255) << 8) | (bArr[i6 + 8] & 255)) | ((bArr[i6 + 10] & 255) << 16)) | ((bArr[i6 + 11] & 255) << 24)) | ((bArr[i6 + 12] & 255) << 32)) | ((bArr[i6 + 13] & 255) << 40)) | ((bArr[i6 + 14] & 255) << 48)) | ((bArr[i6 + 15] & 255) << 56)) * C2, 33) * C1), 31) + jRotateLeft) * 5) + 944331445;
        }
        long j7 = 0;
        switch (i2 - (i4 << 4)) {
            case 8:
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
            case 7:
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
            case 6:
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
            case 5:
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
            case 4:
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
            case 3:
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
            case 2:
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
            case 1:
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 9:
                j = 0;
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 10:
                j2 = 0;
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 11:
                j3 = 0;
                j2 = j3 ^ ((bArr[(i + r3) + 10] & 255) << 16);
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 12:
                j4 = 0;
                j3 = j4 ^ ((bArr[(i + r3) + 11] & 255) << 24);
                j2 = j3 ^ ((bArr[(i + r3) + 10] & 255) << 16);
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 13:
                j5 = 0;
                j4 = j5 ^ ((bArr[(i + r3) + 12] & 255) << 32);
                j3 = j4 ^ ((bArr[(i + r3) + 11] & 255) << 24);
                j2 = j3 ^ ((bArr[(i + r3) + 10] & 255) << 16);
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 14:
                j6 = 0;
                j5 = ((bArr[(i + r3) + 13] & 255) << 40) ^ j6;
                j4 = j5 ^ ((bArr[(i + r3) + 12] & 255) << 32);
                j3 = j4 ^ ((bArr[(i + r3) + 11] & 255) << 24);
                j2 = j3 ^ ((bArr[(i + r3) + 10] & 255) << 16);
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
            case 15:
                j6 = ((bArr[(i + r3) + 14] & 255) << 48) ^ 0;
                j5 = ((bArr[(i + r3) + 13] & 255) << 40) ^ j6;
                j4 = j5 ^ ((bArr[(i + r3) + 12] & 255) << 32);
                j3 = j4 ^ ((bArr[(i + r3) + 11] & 255) << 24);
                j2 = j3 ^ ((bArr[(i + r3) + 10] & 255) << 16);
                j = j2 ^ ((bArr[(i + r3) + 9] & 255) << 8);
                jRotateLeft2 ^= Long.rotateLeft((j ^ (bArr[(i + r3) + 8] & 255)) * C2, 33) * C1;
                j7 = 0 ^ ((bArr[(i + r3) + 7] & 255) << 56);
                j7 ^= (bArr[(i + r3) + 6] & 255) << 48;
                j7 ^= (bArr[(i + r3) + 5] & 255) << 40;
                j7 ^= (bArr[(i + r3) + 4] & 255) << 32;
                j7 ^= (bArr[(i + r3) + 3] & 255) << 24;
                j7 ^= (bArr[(i + r3) + 2] & 255) << 16;
                j7 ^= (bArr[(i + r3) + 1] & 255) << 8;
                jRotateLeft ^= Long.rotateLeft(((bArr[i + r3] & 255) ^ j7) * C1, 31) * C2;
                break;
        }
        long j8 = i2;
        long j9 = jRotateLeft2 ^ j8;
        long j10 = (jRotateLeft ^ j8) + j9;
        long j11 = j9 + j10;
        long jFmix64 = fmix64(j10);
        long jFmix642 = fmix64(j11);
        long j12 = jFmix64 + jFmix642;
        return new long[]{j12, jFmix642 + j12};
    }

    private static int mix32(int i, int i2) {
        return (Integer.rotateLeft((Integer.rotateLeft(i * (-862048943), 15) * C2_32) ^ i2, 13) * 5) + N_32;
    }

    public static class IncrementalHash32 {
        int hash;
        byte[] tail = new byte[3];
        int tailLen;
        int totalLen;

        public final void start(int i) {
            this.totalLen = 0;
            this.tailLen = 0;
            this.hash = i;
        }

        public final void add(byte[] bArr, int i, int i2) {
            int i3;
            int iOrBytes;
            if (i2 == 0) {
                return;
            }
            this.totalLen += i2;
            int i4 = this.tailLen;
            if (i4 + i2 < 4) {
                System.arraycopy(bArr, i, this.tail, i4, i2);
                this.tailLen += i2;
                return;
            }
            if (i4 > 0) {
                i3 = 4 - i4;
                if (i4 == 1) {
                    iOrBytes = MurmurHash3.orBytes(this.tail[0], bArr[i], bArr[i + 1], bArr[i + 2]);
                } else if (i4 == 2) {
                    byte[] bArr2 = this.tail;
                    iOrBytes = MurmurHash3.orBytes(bArr2[0], bArr2[1], bArr[i], bArr[i + 1]);
                } else if (i4 == 3) {
                    byte[] bArr3 = this.tail;
                    iOrBytes = MurmurHash3.orBytes(bArr3[0], bArr3[1], bArr3[2], bArr[i]);
                } else {
                    throw new AssertionError(this.tailLen);
                }
                int iRotateLeft = (Integer.rotateLeft(iOrBytes * (-862048943), 15) * MurmurHash3.C2_32) ^ this.hash;
                this.hash = iRotateLeft;
                this.hash = (Integer.rotateLeft(iRotateLeft, 13) * 5) + MurmurHash3.N_32;
            } else {
                i3 = 0;
            }
            int i5 = i2 - i3;
            int i6 = i + i3;
            int i7 = i5 >> 2;
            for (int i8 = 0; i8 < i7; i8++) {
                int i9 = (i8 << 2) + i6;
                int iRotateLeft2 = (Integer.rotateLeft(MurmurHash3.orBytes(bArr[i9], bArr[i9 + 1], bArr[i9 + 2], bArr[i9 + 3]) * (-862048943), 15) * MurmurHash3.C2_32) ^ this.hash;
                this.hash = iRotateLeft2;
                this.hash = (Integer.rotateLeft(iRotateLeft2, 13) * 5) + MurmurHash3.N_32;
            }
            int i10 = i7 << 2;
            int i11 = i5 - i10;
            this.tailLen = i11;
            if (i10 == i5) {
                return;
            }
            System.arraycopy(bArr, i6 + i10, this.tail, 0, i11);
        }

        public final int end() {
            int i;
            int i2;
            int i3 = this.tailLen;
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 3) {
                        i2 = (this.tail[2] << 16) ^ 0;
                    }
                    int i4 = this.hash ^ this.totalLen;
                    int i5 = (i4 ^ (i4 >>> 16)) * (-2048144789);
                    int i6 = (i5 ^ (i5 >>> 13)) * (-1028477387);
                    int i7 = i6 ^ (i6 >>> 16);
                    this.hash = i7;
                    return i7;
                }
                i2 = 0;
                i = i2 ^ (this.tail[1] << 8);
            } else {
                i = 0;
            }
            this.hash = (Integer.rotateLeft((i ^ this.tail[0]) * (-862048943), 15) * MurmurHash3.C2_32) ^ this.hash;
            int i42 = this.hash ^ this.totalLen;
            int i52 = (i42 ^ (i42 >>> 16)) * (-2048144789);
            int i62 = (i52 ^ (i52 >>> 13)) * (-1028477387);
            int i72 = i62 ^ (i62 >>> 16);
            this.hash = i72;
            return i72;
        }
    }
}
