package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zziw implements Comparator {
    zziw() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzje zzjeVar = (zzje) obj;
        zzje zzjeVar2 = (zzje) obj2;
        zziv zzivVar = new zziv(zzjeVar);
        zziv zzivVar2 = new zziv(zzjeVar2);
        while (zzivVar.hasNext() && zzivVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzivVar.zza() & 255).compareTo(Integer.valueOf(zzivVar2.zza() & 255));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzjeVar.zzd()).compareTo(Integer.valueOf(zzjeVar2.zzd()));
    }
}
