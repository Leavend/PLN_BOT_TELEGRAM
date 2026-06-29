package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzjt {
    private final Context zza;

    public zzjt(Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context;
    }

    private final zzeh zzk() {
        return zzfr.zzp(this.zza, null, null).zzay();
    }

    public final int zza(final Intent intent, int i, final int i2) throws IllegalStateException {
        zzfr zzfrVarZzp = zzfr.zzp(this.zza, null, null);
        final zzeh zzehVarZzay = zzfrVarZzp.zzay();
        if (intent == null) {
            zzehVarZzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzfrVarZzp.zzaw();
        zzehVarZzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjq
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzc(i2, zzehVarZzay, intent);
                }
            });
        }
        return 2;
    }

    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgj(zzkt.zzt(this.zza), null);
        }
        zzk().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    final /* synthetic */ void zzc(int i, zzeh zzehVar, Intent intent) {
        if (((zzjs) this.zza).zzc(i)) {
            zzehVar.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzk().zzj().zza("Completed wakeful intent.");
            ((zzjs) this.zza).zza(intent);
        }
    }

    final /* synthetic */ void zzd(zzeh zzehVar, JobParameters jobParameters) {
        zzehVar.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzjs) this.zza).zzb(jobParameters, false);
    }

    public final void zze() {
        zzfr zzfrVarZzp = zzfr.zzp(this.zza, null, null);
        zzeh zzehVarZzay = zzfrVarZzp.zzay();
        zzfrVarZzp.zzaw();
        zzehVarZzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    public final void zzf() {
        zzfr zzfrVarZzp = zzfr.zzp(this.zza, null, null);
        zzeh zzehVarZzay = zzfrVarZzp.zzay();
        zzfrVarZzp.zzaw();
        zzehVarZzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzg(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onRebind called with null intent");
        } else {
            zzk().zzj().zzb("onRebind called. action", intent.getAction());
        }
    }

    public final void zzh(Runnable runnable) throws IllegalStateException {
        zzkt zzktVarZzt = zzkt.zzt(this.zza);
        zzktVarZzt.zzaz().zzp(new zzjr(this, zzktVarZzt, runnable));
    }

    public final boolean zzi(final JobParameters jobParameters) throws IllegalStateException {
        zzfr zzfrVarZzp = zzfr.zzp(this.zza, null, null);
        final zzeh zzehVarZzay = zzfrVarZzp.zzay();
        String string = jobParameters.getExtras().getString("action");
        zzfrVarZzp.zzaw();
        zzehVarZzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zzh(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzjp
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzd(zzehVarZzay, jobParameters);
            }
        });
        return true;
    }

    public final boolean zzj(Intent intent) {
        if (intent == null) {
            zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        zzk().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
