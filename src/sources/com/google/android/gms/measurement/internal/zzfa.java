package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzfa {
    private final zza zza;

    /* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzfa(zza zzaVar) {
        Preconditions.checkNotNull(zzaVar);
        this.zza = zzaVar;
    }

    public final void zza(Context context, Intent intent) {
        zzfr zzfrVarZzp = zzfr.zzp(context, null, null);
        zzeh zzehVarZzay = zzfrVarZzp.zzay();
        if (intent == null) {
            zzehVarZzay.zzk().zza("Receiver called with null intent");
            return;
        }
        zzfrVarZzp.zzaw();
        String action = intent.getAction();
        zzehVarZzay.zzj().zzb("Local receiver got", action);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(action)) {
            if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
                zzehVarZzay.zzk().zza("Install Referrer Broadcasts are deprecated");
            }
        } else {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzehVarZzay.zzj().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
        }
    }
}
