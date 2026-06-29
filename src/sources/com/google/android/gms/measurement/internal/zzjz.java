package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
final class zzjz extends zzap {
    final /* synthetic */ zzka zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzjz(zzka zzkaVar, zzgm zzgmVar) {
        super(zzgmVar);
        this.zza = zzkaVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzap
    public final void zzc() {
        zzka zzkaVar = this.zza;
        zzkaVar.zzc.zzg();
        zzkaVar.zzd(false, false, zzkaVar.zzc.zzt.zzav().elapsedRealtime());
        zzkaVar.zzc.zzt.zzd().zzf(zzkaVar.zzc.zzt.zzav().elapsedRealtime());
    }
}
