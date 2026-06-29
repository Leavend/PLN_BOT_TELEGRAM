package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzky extends zzla {
    private zzky() {
        super(null);
    }

    /* synthetic */ zzky(zzkx zzkxVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzla
    final void zza(Object obj, long j) {
        ((zzkm) zzmy.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzla
    final void zzb(Object obj, Object obj2, long j) {
        zzkm zzkmVarZzd = (zzkm) zzmy.zzf(obj, j);
        zzkm zzkmVar = (zzkm) zzmy.zzf(obj2, j);
        int size = zzkmVarZzd.size();
        int size2 = zzkmVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzkmVarZzd.zzc()) {
                zzkmVarZzd = zzkmVarZzd.zzd(size2 + size);
            }
            zzkmVarZzd.addAll(zzkmVar);
        }
        if (size > 0) {
            zzkmVar = zzkmVarZzd;
        }
        zzmy.zzs(obj, j, zzkmVar);
    }
}
