package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzgr extends zzkf implements zzln {
    private static final zzgr zza;
    private int zzd;
    private String zze = "";
    private zzkm zzf = zzbE();

    static {
        zzgr zzgrVar = new zzgr();
        zza = zzgrVar;
        zzkf.zzbL(zzgr.class, zzgrVar);
    }

    private zzgr() {
    }

    public final String zzb() {
        return this.zze;
    }

    public final List zzc() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzkf
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzbI(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzd", "zze", "zzf", zzgy.class});
        }
        if (i2 == 3) {
            return new zzgr();
        }
        zzgn zzgnVar = null;
        if (i2 == 4) {
            return new zzgq(zzgnVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
