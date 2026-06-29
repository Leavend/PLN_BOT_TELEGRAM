package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzoq implements zzop {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhyVarZza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zza = zzhyVarZza.zzd("measurement.id.lifecycle.app_in_background_parameter", 0L);
        zzb = zzhyVarZza.zzf("measurement.lifecycle.app_backgrounded_tracking", true);
        zzc = zzhyVarZza.zzf("measurement.lifecycle.app_in_background_parameter", false);
        zzd = zzhyVarZza.zzd("measurement.id.lifecycle.app_backgrounded_tracking", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzop
    public final boolean zza() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }
}
