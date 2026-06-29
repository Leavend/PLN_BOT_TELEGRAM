package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzfe extends zzkb implements zzln {
    private zzfe() {
        super(zzff.zza);
    }

    public final int zza() {
        return ((zzff) this.zza).zzb();
    }

    public final zzfd zzb(int i) {
        return ((zzff) this.zza).zzd(i);
    }

    public final zzfe zzc() {
        zzaG();
        ((zzff) this.zza).zzj = zzff.zzbE();
        return this;
    }

    public final zzfe zzd(int i, zzfc zzfcVar) {
        zzaG();
        zzff.zzo((zzff) this.zza, i, (zzfd) zzfcVar.zzaC());
        return this;
    }

    public final String zze() {
        return ((zzff) this.zza).zzi();
    }

    public final List zzf() {
        return Collections.unmodifiableList(((zzff) this.zza).zzj());
    }

    public final List zzg() {
        return Collections.unmodifiableList(((zzff) this.zza).zzk());
    }

    /* synthetic */ zzfe(zzez zzezVar) {
        super(zzff.zza);
    }
}
