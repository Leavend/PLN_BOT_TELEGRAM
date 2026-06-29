package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzgb extends zzkf implements zzln {
    private static final zzgb zza;
    private zzkm zzd = zzbE();

    static {
        zzgb zzgbVar = new zzgb();
        zza = zzgbVar;
        zzkf.zzbL(zzgb.class, zzgbVar);
    }

    private zzgb() {
    }

    public static zzga zza() {
        return (zzga) zza.zzbx();
    }

    static /* synthetic */ void zze(zzgb zzgbVar, zzgd zzgdVar) {
        zzgdVar.getClass();
        zzkm zzkmVar = zzgbVar.zzd;
        if (!zzkmVar.zzc()) {
            zzgbVar.zzd = zzkf.zzbF(zzkmVar);
        }
        zzgbVar.zzd.add(zzgdVar);
    }

    public final zzgd zzc(int i) {
        return (zzgd) this.zzd.get(0);
    }

    public final List zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzkf
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzbI(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzgd.class});
        }
        if (i2 == 3) {
            return new zzgb();
        }
        zzfk zzfkVar = null;
        if (i2 == 4) {
            return new zzga(zzfkVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
