package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
final class zzhu implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Uri zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzhw zze;

    zzhu(zzhw zzhwVar, boolean z, Uri uri, String str, String str2) {
        this.zze = zzhwVar;
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundleZzs;
        Bundle bundleZzs2;
        zzhw zzhwVar = this.zze;
        boolean z = this.zza;
        Uri uri = this.zzb;
        String str = this.zzc;
        String str2 = this.zzd;
        zzhwVar.zza.zzg();
        try {
            zzlb zzlbVarZzv = zzhwVar.zza.zzt.zzv();
            if (TextUtils.isEmpty(str2)) {
                bundleZzs = null;
            } else if (str2.contains("gclid") || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium") || str2.contains("utm_id") || str2.contains("dclid") || str2.contains("srsltid")) {
                bundleZzs = zzlbVarZzv.zzs(Uri.parse("https://google.com/search?".concat(String.valueOf(str2))));
                if (bundleZzs != null) {
                    bundleZzs.putString("_cis", "referrer");
                }
            } else {
                zzlbVarZzv.zzt.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                bundleZzs = null;
            }
            if (z && (bundleZzs2 = zzhwVar.zza.zzt.zzv().zzs(uri)) != null) {
                bundleZzs2.putString("_cis", "intent");
                if (!bundleZzs2.containsKey("gclid") && bundleZzs != null && bundleZzs.containsKey("gclid")) {
                    bundleZzs2.putString("_cer", String.format("gclid=%s", bundleZzs.getString("gclid")));
                }
                zzhwVar.zza.zzG(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZzs2);
                zzhwVar.zza.zzb.zza(str, bundleZzs2);
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            zzhwVar.zza.zzt.zzay().zzc().zzb("Activity created with referrer", str2);
            if (zzhwVar.zza.zzt.zzf().zzs(null, zzdu.zzY)) {
                if (bundleZzs != null) {
                    zzhwVar.zza.zzG(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZzs);
                    zzhwVar.zza.zzb.zza(str, bundleZzs);
                } else {
                    zzhwVar.zza.zzt.zzay().zzc().zzb("Referrer does not contain valid parameters", str2);
                }
                zzhwVar.zza.zzW(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", null, true);
                return;
            }
            if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                zzhwVar.zza.zzt.zzay().zzc().zza("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                zzhwVar.zza.zzW(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", str2, true);
            }
        } catch (RuntimeException e) {
            zzhwVar.zza.zzt.zzay().zzd().zzb("Throwable caught in handleReferrerForOnActivityCreated", e);
        }
    }
}
