package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
final class zzko implements zzla {
    final /* synthetic */ zzkt zza;

    zzko(zzkt zzktVar) {
        this.zza = zzktVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzla
    public final void zza(String str, String str2, Bundle bundle) throws IllegalStateException {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzaz().zzp(new zzkn(this, str, "_err", bundle));
            return;
        }
        zzkt zzktVar = this.zza;
        if (zzktVar.zzn != null) {
            zzktVar.zzn.zzay().zzd().zzb("AppId not known when logging event", "_err");
        }
    }
}
