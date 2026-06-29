package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzkw extends zzla {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzkw() {
        super(null);
    }

    /* synthetic */ zzkw(zzkv zzkvVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzla
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzmy.zzf(obj, j);
        if (list instanceof zzku) {
            objUnmodifiableList = ((zzku) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzlt) && (list instanceof zzkm)) {
                zzkm zzkmVar = (zzkm) list;
                if (zzkmVar.zzc()) {
                    zzkmVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzmy.zzs(obj, j, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzla
    final void zzb(Object obj, Object obj2, long j) {
        zzkt zzktVar;
        List list = (List) zzmy.zzf(obj2, j);
        int size = list.size();
        List listZzd = (List) zzmy.zzf(obj, j);
        if (listZzd.isEmpty()) {
            listZzd = listZzd instanceof zzku ? new zzkt(size) : ((listZzd instanceof zzlt) && (listZzd instanceof zzkm)) ? ((zzkm) listZzd).zzd(size) : new ArrayList(size);
            zzmy.zzs(obj, j, listZzd);
        } else {
            if (zza.isAssignableFrom(listZzd.getClass())) {
                ArrayList arrayList = new ArrayList(listZzd.size() + size);
                arrayList.addAll(listZzd);
                zzmy.zzs(obj, j, arrayList);
                zzktVar = arrayList;
            } else if (listZzd instanceof zzmt) {
                zzkt zzktVar2 = new zzkt(listZzd.size() + size);
                zzktVar2.addAll(zzktVar2.size(), (zzmt) listZzd);
                zzmy.zzs(obj, j, zzktVar2);
                zzktVar = zzktVar2;
            } else if ((listZzd instanceof zzlt) && (listZzd instanceof zzkm)) {
                zzkm zzkmVar = (zzkm) listZzd;
                if (!zzkmVar.zzc()) {
                    listZzd = zzkmVar.zzd(listZzd.size() + size);
                    zzmy.zzs(obj, j, listZzd);
                }
            }
            listZzd = zzktVar;
        }
        int size2 = listZzd.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            listZzd.addAll(list);
        }
        if (size2 > 0) {
            list = listZzd;
        }
        zzmy.zzs(obj, j, list);
    }
}
