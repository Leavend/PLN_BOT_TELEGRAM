package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
final class zzfg implements com.google.android.gms.internal.measurement.zzr {
    final /* synthetic */ zzfi zza;

    zzfg(zzfi zzfiVar) {
        this.zza = zzfiVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i, String str, List list, boolean z, boolean z2) {
        int i2 = i - 1;
        zzef zzefVarZzi = i2 != 0 ? i2 != 1 ? i2 != 3 ? i2 != 4 ? this.zza.zzt.zzay().zzi() : z ? this.zza.zzt.zzay().zzm() : !z2 ? this.zza.zzt.zzay().zzl() : this.zza.zzt.zzay().zzk() : this.zza.zzt.zzay().zzj() : z ? this.zza.zzt.zzay().zzh() : !z2 ? this.zza.zzt.zzay().zze() : this.zza.zzt.zzay().zzd() : this.zza.zzt.zzay().zzc();
        int size = list.size();
        if (size == 1) {
            zzefVarZzi.zzb(str, list.get(0));
            return;
        }
        if (size == 2) {
            zzefVarZzi.zzc(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzefVarZzi.zza(str);
        } else {
            zzefVarZzi.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
