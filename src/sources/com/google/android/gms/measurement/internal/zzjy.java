package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
final class zzjy {
    final /* synthetic */ zzkc zza;
    private zzjx zzb;

    zzjy(zzkc zzkcVar) {
        this.zza = zzkcVar;
    }

    final void zza(long j) {
        this.zzb = new zzjx(this, this.zza.zzt.zzav().currentTimeMillis(), j);
        this.zza.zzd.postDelayed(this.zzb, 2000L);
    }

    final void zzb() {
        this.zza.zzg();
        zzjx zzjxVar = this.zzb;
        if (zzjxVar != null) {
            this.zza.zzd.removeCallbacks(zzjxVar);
        }
        this.zza.zzt.zzm().zzm.zza(false);
    }
}
