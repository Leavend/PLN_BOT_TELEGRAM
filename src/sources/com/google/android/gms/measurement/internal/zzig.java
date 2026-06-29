package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
final class zzig implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzie zzb;
    final /* synthetic */ zzie zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzim zze;

    zzig(zzim zzimVar, Bundle bundle, zzie zzieVar, zzie zzieVar2, long j) {
        this.zze = zzimVar;
        this.zza = bundle;
        this.zzb = zzieVar;
        this.zzc = zzieVar2;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        zzim.zzp(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
