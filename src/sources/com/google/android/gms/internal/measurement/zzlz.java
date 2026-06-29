package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzlz {
    private static final Class zza;
    private static final zzmo zzb;
    private static final zzmo zzc;
    private static final zzmo zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzmq();
    }

    public static zzmo zzA() {
        return zzc;
    }

    public static zzmo zzB() {
        return zzd;
    }

    static Object zzC(Object obj, int i, List list, zzkj zzkjVar, Object obj2, zzmo zzmoVar) {
        Object objZzD = null;
        if (zzkjVar == null) {
            return null;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (zzkjVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    objZzD = zzD(obj, i, iIntValue, objZzD, zzmoVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return objZzD;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!zzkjVar.zza(iIntValue2)) {
                    objZzD = zzD(obj, i, iIntValue2, objZzD, zzmoVar);
                    it.remove();
                }
            }
        }
        return objZzD;
    }

    static Object zzD(Object obj, int i, int i2, Object obj2, zzmo zzmoVar) {
        if (obj2 == null) {
            obj2 = zzmoVar.zzc(obj);
        }
        zzmoVar.zzf(obj2, i, i2);
        return obj2;
    }

    static void zzE(zzjs zzjsVar, Object obj, Object obj2) {
        zzjsVar.zza(obj2);
        throw null;
    }

    static void zzF(zzmo zzmoVar, Object obj, Object obj2) {
        zzmoVar.zzh(obj, zzmoVar.zze(zzmoVar.zzd(obj), zzmoVar.zzd(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzkf.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzc(i, list, z);
    }

    public static void zzI(int i, List list, zzng zzngVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zze(i, list);
    }

    public static void zzJ(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzg(i, list, z);
    }

    public static void zzK(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzj(i, list, z);
    }

    public static void zzL(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzl(i, list, z);
    }

    public static void zzM(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzn(i, list, z);
    }

    public static void zzN(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzp(i, list, z);
    }

    public static void zzO(int i, List list, zzng zzngVar, zzlx zzlxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjn) zzngVar).zzq(i, list.get(i2), zzlxVar);
        }
    }

    public static void zzP(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzs(i, list, z);
    }

    public static void zzQ(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzu(i, list, z);
    }

    public static void zzR(int i, List list, zzng zzngVar, zzlx zzlxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzjn) zzngVar).zzv(i, list.get(i2), zzlxVar);
        }
    }

    public static void zzS(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzx(i, list, z);
    }

    public static void zzT(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzz(i, list, z);
    }

    public static void zzU(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzB(i, list, z);
    }

    public static void zzV(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzD(i, list, z);
    }

    public static void zzW(int i, List list, zzng zzngVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzG(i, list);
    }

    public static void zzX(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzI(i, list, z);
    }

    public static void zzY(int i, List list, zzng zzngVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzngVar.zzK(i, list, z);
    }

    static boolean zzZ(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjm.zzA(i << 3) + 1);
    }

    static void zzaa(zzlh zzlhVar, Object obj, Object obj2, long j) {
        zzmy.zzs(obj, j, zzlh.zzb(zzmy.zzf(obj, j), zzmy.zzf(obj2, j)));
    }

    private static zzmo zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzmo) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = size * zzjm.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzz += zzjm.zzt((zzje) list.get(i2));
        }
        return iZzz;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjm.zzz(i));
    }

    static int zze(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkg) {
            zzkg zzkgVar = (zzkg) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzjm.zzv(zzkgVar.zze(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzjm.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjm.zzA(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjm.zzA(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzlx zzlxVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzu = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzu += zzjm.zzu(i, (zzlm) list.get(i2), zzlxVar);
        }
        return iZzu;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzjm.zzz(i));
    }

    static int zzl(List list) {
        int iZzv;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkg) {
            zzkg zzkgVar = (zzkg) list;
            iZzv = 0;
            while (i < size) {
                iZzv += zzjm.zzv(zzkgVar.zze(i));
                i++;
            }
        } else {
            iZzv = 0;
            while (i < size) {
                iZzv += zzjm.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzv;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzjm.zzz(i));
    }

    static int zzn(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlb) {
            zzlb zzlbVar = (zzlb) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzjm.zzB(zzlbVar.zza(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzjm.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    static int zzo(int i, Object obj, zzlx zzlxVar) {
        if (!(obj instanceof zzks)) {
            return zzjm.zzA(i << 3) + zzjm.zzx((zzlm) obj, zzlxVar);
        }
        int iZzA = zzjm.zzA(i << 3);
        int iZza = ((zzks) obj).zza();
        return iZzA + zzjm.zzA(iZza) + iZza;
    }

    static int zzp(int i, List list, zzlx zzlxVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjm.zzz(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            iZzz += obj instanceof zzks ? zzjm.zzw((zzks) obj) : zzjm.zzx((zzlm) obj, zzlxVar);
        }
        return iZzz;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzjm.zzz(i));
    }

    static int zzr(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkg) {
            zzkg zzkgVar = (zzkg) list;
            iZzA = 0;
            while (i < size) {
                int iZze = zzkgVar.zze(i);
                iZzA += zzjm.zzA((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzA += zzjm.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzA;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzjm.zzz(i));
    }

    static int zzt(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlb) {
            zzlb zzlbVar = (zzlb) list;
            iZzB = 0;
            while (i < size) {
                long jZza = zzlbVar.zza(i);
                iZzB += zzjm.zzB((jZza >> 63) ^ (jZza + jZza));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzB += zzjm.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzB;
    }

    static int zzu(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzz = zzjm.zzz(i) * size;
        if (list instanceof zzku) {
            zzku zzkuVar = (zzku) list;
            while (i2 < size) {
                Object objZzf = zzkuVar.zzf(i2);
                iZzz += objZzf instanceof zzje ? zzjm.zzt((zzje) objZzf) : zzjm.zzy((String) objZzf);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iZzz += obj instanceof zzje ? zzjm.zzt((zzje) obj) : zzjm.zzy((String) obj);
                i2++;
            }
        }
        return iZzz;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzjm.zzz(i));
    }

    static int zzw(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkg) {
            zzkg zzkgVar = (zzkg) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzjm.zzA(zzkgVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzjm.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzjm.zzz(i));
    }

    static int zzy(List list) {
        int iZzB;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlb) {
            zzlb zzlbVar = (zzlb) list;
            iZzB = 0;
            while (i < size) {
                iZzB += zzjm.zzB(zzlbVar.zza(i));
                i++;
            }
        } else {
            iZzB = 0;
            while (i < size) {
                iZzB += zzjm.zzB(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzB;
    }

    public static zzmo zzz() {
        return zzb;
    }
}
