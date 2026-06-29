package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzgi extends zzkf implements zzln {
    private static final zzgi zza;
    private zzkl zzd = zzbC();
    private zzkl zze = zzbC();
    private zzkm zzf = zzbE();
    private zzkm zzg = zzbE();

    static {
        zzgi zzgiVar = new zzgi();
        zza = zzgiVar;
        zzkf.zzbL(zzgi.class, zzgiVar);
    }

    private zzgi() {
    }

    public static zzgh zzf() {
        return (zzgh) zza.zzbx();
    }

    public static zzgi zzh() {
        return zza;
    }

    static /* synthetic */ void zzo(zzgi zzgiVar, Iterable iterable) {
        zzkl zzklVar = zzgiVar.zzd;
        if (!zzklVar.zzc()) {
            zzgiVar.zzd = zzkf.zzbD(zzklVar);
        }
        zzio.zzbt(iterable, zzgiVar.zzd);
    }

    static /* synthetic */ void zzq(zzgi zzgiVar, Iterable iterable) {
        zzkl zzklVar = zzgiVar.zze;
        if (!zzklVar.zzc()) {
            zzgiVar.zze = zzkf.zzbD(zzklVar);
        }
        zzio.zzbt(iterable, zzgiVar.zze);
    }

    static /* synthetic */ void zzs(zzgi zzgiVar, Iterable iterable) {
        zzgiVar.zzy();
        zzio.zzbt(iterable, zzgiVar.zzf);
    }

    static /* synthetic */ void zzu(zzgi zzgiVar, int i) {
        zzgiVar.zzy();
        zzgiVar.zzf.remove(i);
    }

    static /* synthetic */ void zzv(zzgi zzgiVar, Iterable iterable) {
        zzgiVar.zzz();
        zzio.zzbt(iterable, zzgiVar.zzg);
    }

    static /* synthetic */ void zzx(zzgi zzgiVar, int i) {
        zzgiVar.zzz();
        zzgiVar.zzg.remove(i);
    }

    private final void zzy() {
        zzkm zzkmVar = this.zzf;
        if (zzkmVar.zzc()) {
            return;
        }
        this.zzf = zzkf.zzbF(zzkmVar);
    }

    private final void zzz() {
        zzkm zzkmVar = this.zzg;
        if (zzkmVar.zzc()) {
            return;
        }
        this.zzg = zzkf.zzbF(zzkmVar);
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final int zzd() {
        return this.zzd.size();
    }

    public final zzfr zze(int i) {
        return (zzfr) this.zzf.get(i);
    }

    public final zzgk zzi(int i) {
        return (zzgk) this.zzg.get(i);
    }

    public final List zzj() {
        return this.zzf;
    }

    public final List zzk() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzkf
    protected final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzbI(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzd", "zze", "zzf", zzfr.class, "zzg", zzgk.class});
        }
        if (i2 == 3) {
            return new zzgi();
        }
        zzfk zzfkVar = null;
        if (i2 == 4) {
            return new zzgh(zzfkVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final List zzm() {
        return this.zzg;
    }

    public final List zzn() {
        return this.zzd;
    }
}
