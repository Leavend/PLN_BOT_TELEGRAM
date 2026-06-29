package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
abstract class zzgl extends zzgk {
    private boolean zza;

    zzgl(zzfr zzfrVar) {
        super(zzfrVar);
        this.zzt.zzD();
    }

    protected void zzaA() {
    }

    protected abstract boolean zzf();

    protected final void zzu() {
        if (!zzx()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzf()) {
            return;
        }
        this.zzt.zzB();
        this.zza = true;
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzaA();
        this.zzt.zzB();
        this.zza = true;
    }

    final boolean zzx() {
        return this.zza;
    }
}
