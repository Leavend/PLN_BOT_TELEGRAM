package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
final class zzga implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ zzgj zzb;

    zzga(zzgj zzgjVar, zzq zzqVar) {
        this.zzb = zzgjVar;
        this.zza = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzA();
        zzkt zzktVar = this.zzb.zza;
        zzq zzqVar = this.zza;
        zzktVar.zzaz().zzg();
        zzktVar.zzB();
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzktVar.zzd(zzqVar);
    }
}
