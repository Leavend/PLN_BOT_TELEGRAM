package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzmq extends zzmo {
    zzmq() {
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* synthetic */ int zza(Object obj) {
        return ((zzmp) obj).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* synthetic */ int zzb(Object obj) {
        return ((zzmp) obj).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzkf zzkfVar = (zzkf) obj;
        zzmp zzmpVar = zzkfVar.zzc;
        if (zzmpVar != zzmp.zzc()) {
            return zzmpVar;
        }
        zzmp zzmpVarZzf = zzmp.zzf();
        zzkfVar.zzc = zzmpVarZzf;
        return zzmpVarZzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* synthetic */ Object zzd(Object obj) {
        return ((zzkf) obj).zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzmp.zzc().equals(obj2)) {
            return obj;
        }
        if (zzmp.zzc().equals(obj)) {
            return zzmp.zze((zzmp) obj, (zzmp) obj2);
        }
        ((zzmp) obj).zzd((zzmp) obj2);
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* bridge */ /* synthetic */ void zzf(Object obj, int i, long j) {
        ((zzmp) obj).zzj(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final void zzg(Object obj) {
        ((zzkf) obj).zzc.zzh();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzkf) obj).zzc = (zzmp) obj2;
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    final /* synthetic */ void zzi(Object obj, zzng zzngVar) throws IOException {
        ((zzmp) obj).zzk(zzngVar);
    }
}
