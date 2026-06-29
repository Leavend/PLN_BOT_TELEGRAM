package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes4.dex */
final class zzaa extends zzkh {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzkt zzktVar) {
        super(zzktVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzuVar.zze.get(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:412:0x0a45, code lost:
    
        r7 = r63.zzt.zzay().zzk();
        r9 = com.google.android.gms.measurement.internal.zzeh.zzn(r63.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x0a59, code lost:
    
        if (r8.zzj() == false) goto L415;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x0a5b, code lost:
    
        r8 = java.lang.Integer.valueOf(r8.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x0a64, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x0a65, code lost:
    
        r7.zzc("Invalid property filter ID. appId, id", r9, java.lang.String.valueOf(r8));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02bd A[PHI: r0 r5
      0x02bd: PHI (r0v69 java.util.Map) = (r0v45 java.util.Map), (r0v71 java.util.Map), (r0v39 java.util.Map) binds: [B:121:0x02ea, B:110:0x02c5, B:107:0x02bb] A[DONT_GENERATE, DONT_INLINE]
      0x02bd: PHI (r5v16 android.database.Cursor) = (r5v9 android.database.Cursor), (r5v17 android.database.Cursor), (r5v17 android.database.Cursor) binds: [B:121:0x02ea, B:110:0x02c5, B:107:0x02bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x07bf  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x07d9  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x086c  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0929 A[PHI: r0 r9
      0x0929: PHI (r0v147 java.util.Map) = (r0v149 java.util.Map), (r0v156 java.util.Map) binds: [B:378:0x0950, B:364:0x0927] A[DONT_GENERATE, DONT_INLINE]
      0x0929: PHI (r9v30 android.database.Cursor) = (r9v31 android.database.Cursor), (r9v34 android.database.Cursor) binds: [B:378:0x0950, B:364:0x0927] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x096e  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0a97  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0156 A[PHI: r0 r5
      0x0156: PHI (r0v186 java.util.Map) = (r0v185 java.util.Map), (r0v190 java.util.Map) binds: [B:53:0x0178, B:42:0x0154] A[DONT_GENERATE, DONT_INLINE]
      0x0156: PHI (r5v61 android.database.Cursor) = (r5v60 android.database.Cursor), (r5v62 android.database.Cursor) binds: [B:53:0x0178, B:42:0x0154] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b9 A[Catch: SQLiteException -> 0x0224, all -> 0x0b2b, TRY_LEAVE, TryCatch #4 {all -> 0x0b2b, blocks: (B:61:0x01b3, B:63:0x01b9, B:67:0x01c7, B:68:0x01cc, B:69:0x01d6, B:70:0x01e6, B:75:0x020e, B:72:0x01f3, B:74:0x0207, B:89:0x0230), top: B:448:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01c7 A[Catch: SQLiteException -> 0x0224, all -> 0x0b2b, TRY_ENTER, TryCatch #4 {all -> 0x0b2b, blocks: (B:61:0x01b3, B:63:0x01b9, B:67:0x01c7, B:68:0x01cc, B:69:0x01d6, B:70:0x01e6, B:75:0x020e, B:72:0x01f3, B:74:0x0207, B:89:0x0230), top: B:448:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0259  */
    /* JADX WARN: Type inference failed for: r0v199, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r4v29, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v63, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v65, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.List zza(java.lang.String r64, java.util.List r65, java.util.List r66, java.lang.Long r67, java.lang.Long r68) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2867
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }
}
