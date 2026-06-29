package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzim {
    public static zzii zza(zzii zziiVar) {
        return ((zziiVar instanceof zzik) || (zziiVar instanceof zzij)) ? zziiVar : zziiVar instanceof Serializable ? new zzij(zziiVar) : new zzik(zziiVar);
    }

    public static zzii zzb(Object obj) {
        return new zzil(obj);
    }
}
