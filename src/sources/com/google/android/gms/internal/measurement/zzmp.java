package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzmp {
    private static final zzmp zza = new zzmp(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmp() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmp(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmp zzc() {
        return zza;
    }

    static zzmp zze(zzmp zzmpVar, zzmp zzmpVar2) {
        int i = zzmpVar.zzb + zzmpVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzmpVar.zzc, i);
        System.arraycopy(zzmpVar2.zzc, 0, iArrCopyOf, zzmpVar.zzb, zzmpVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzmpVar.zzd, i);
        System.arraycopy(zzmpVar2.zzd, 0, objArrCopyOf, zzmpVar.zzb, zzmpVar2.zzb);
        return new zzmp(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzmp zzf() {
        return new zzmp(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmp)) {
            return false;
        }
        zzmp zzmpVar = (zzmp) obj;
        int i = this.zzb;
        if (i == zzmpVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmpVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmpVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int iZzA;
        int iZzB;
        int iZzA2;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA3 = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 != 0) {
                if (i5 == 1) {
                    ((Long) this.zzd[i2]).longValue();
                    iZzA2 = zzjm.zzA(i4 << 3) + 8;
                } else if (i5 == 2) {
                    zzje zzjeVar = (zzje) this.zzd[i2];
                    int iZzA4 = zzjm.zzA(i4 << 3);
                    int iZzd = zzjeVar.zzd();
                    iZzA3 += iZzA4 + zzjm.zzA(iZzd) + iZzd;
                } else if (i5 == 3) {
                    int iZzz = zzjm.zzz(i4);
                    iZzA = iZzz + iZzz;
                    iZzB = ((zzmp) this.zzd[i2]).zza();
                } else {
                    if (i5 != 5) {
                        throw new IllegalStateException(zzkp.zza());
                    }
                    ((Integer) this.zzd[i2]).intValue();
                    iZzA2 = zzjm.zzA(i4 << 3) + 4;
                }
                iZzA3 += iZzA2;
            } else {
                long jLongValue = ((Long) this.zzd[i2]).longValue();
                iZzA = zzjm.zzA(i4 << 3);
                iZzB = zzjm.zzB(jLongValue);
            }
            iZzA2 = iZzA + iZzB;
            iZzA3 += iZzA2;
        }
        this.zze = iZzA3;
        return iZzA3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzA = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            zzje zzjeVar = (zzje) this.zzd[i2];
            int iZzA2 = zzjm.zzA(8);
            int iZzd = zzjeVar.zzd();
            iZzA += iZzA2 + iZzA2 + zzjm.zzA(16) + zzjm.zzA(i3 >>> 3) + zzjm.zzA(24) + zzjm.zzA(iZzd) + iZzd;
        }
        this.zze = iZzA;
        return iZzA;
    }

    final zzmp zzd(zzmp zzmpVar) {
        if (zzmpVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzmpVar.zzb;
        zzl(i);
        System.arraycopy(zzmpVar.zzc, 0, this.zzc, this.zzb, zzmpVar.zzb);
        System.arraycopy(zzmpVar.zzd, 0, this.zzd, this.zzb, zzmpVar.zzb);
        this.zzb = i;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        this.zzf = false;
    }

    final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzlo.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzj(int i, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    public final void zzk(zzng zzngVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzngVar.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzngVar.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzngVar.zzd(i3, (zzje) obj);
                } else if (i4 == 3) {
                    zzngVar.zzE(i3);
                    ((zzmp) obj).zzk(zzngVar);
                    zzngVar.zzh(i3);
                } else {
                    if (i4 != 5) {
                        throw new RuntimeException(zzkp.zza());
                    }
                    zzngVar.zzk(i3, ((Integer) obj).intValue());
                }
            }
        }
    }
}
