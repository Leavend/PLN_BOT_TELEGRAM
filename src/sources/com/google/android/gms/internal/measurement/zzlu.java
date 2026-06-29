package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzlu {
    private static final zzlu zza = new zzlu();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzly zzb = new zzle();

    private zzlu() {
    }

    public static zzlu zza() {
        return zza;
    }

    public final zzlx zzb(Class cls) {
        zzkn.zzf(cls, "messageType");
        zzlx zzlxVarZza = (zzlx) this.zzc.get(cls);
        if (zzlxVarZza == null) {
            zzlxVarZza = this.zzb.zza(cls);
            zzkn.zzf(cls, "messageType");
            zzkn.zzf(zzlxVarZza, "schema");
            zzlx zzlxVar = (zzlx) this.zzc.putIfAbsent(cls, zzlxVarZza);
            if (zzlxVar != null) {
                return zzlxVar;
            }
        }
        return zzlxVarZza;
    }
}
