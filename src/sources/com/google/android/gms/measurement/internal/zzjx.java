package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.lang.reflect.InvocationTargetException;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
final class zzjx implements Runnable {
    final long zza;
    final long zzb;
    final /* synthetic */ zzjy zzc;

    zzjx(zzjy zzjyVar, long j, long j2) {
        this.zzc = zzjyVar;
        this.zza = j;
        this.zzb = j2;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zzc.zza.zzt.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjw
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                zzjx zzjxVar = this.zza;
                zzjy zzjyVar = zzjxVar.zzc;
                long j = zzjxVar.zza;
                long j2 = zzjxVar.zzb;
                zzjyVar.zza.zzg();
                zzjyVar.zza.zzt.zzay().zzc().zza("Application going to the background");
                zzjyVar.zza.zzt.zzm().zzm.zza(true);
                Bundle bundle = new Bundle();
                if (!zzjyVar.zza.zzt.zzf().zzu()) {
                    zzjyVar.zza.zzb.zzb(j2);
                    zzjyVar.zza.zzb.zzd(false, false, j2);
                }
                zzjyVar.zza.zzt.zzq().zzH(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ab", j, bundle);
            }
        });
    }
}
