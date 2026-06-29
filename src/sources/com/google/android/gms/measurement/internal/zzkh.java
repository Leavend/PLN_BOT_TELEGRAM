package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
abstract class zzkh extends zzkg {
    private boolean zza;

    zzkh(zzkt zzktVar) {
        super(zzktVar);
        this.zzf.zzM();
    }

    protected final void zzW() {
        if (!zzY()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzX() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzb();
        this.zzf.zzH();
        this.zza = true;
    }

    final boolean zzY() {
        return this.zza;
    }

    protected abstract boolean zzb();
}
