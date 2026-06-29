package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zznw;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
public final class zzfr implements zzgm {
    private static volatile zzfr zzd;
    private zzdy zzA;
    private Boolean zzC;
    private long zzD;
    private volatile Boolean zzE;
    private volatile boolean zzF;
    private int zzG;
    protected Boolean zza;
    protected Boolean zzb;
    final long zzc;
    private final Context zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final zzab zzj;
    private final zzag zzk;
    private final zzew zzl;
    private final zzeh zzm;
    private final zzfo zzn;
    private final zzkc zzo;
    private final zzlb zzp;
    private final zzec zzq;
    private final Clock zzr;
    private final zzim zzs;
    private final zzhx zzt;
    private final zzd zzu;
    private final zzib zzv;
    private final String zzw;
    private zzea zzx;
    private zzjm zzy;
    private zzaq zzz;
    private boolean zzB = false;
    private final AtomicInteger zzH = new AtomicInteger(0);

    zzfr(zzgu zzguVar) throws IllegalStateException {
        Bundle bundle;
        Preconditions.checkNotNull(zzguVar);
        Context context = zzguVar.zza;
        zzab zzabVar = new zzab(context);
        this.zzj = zzabVar;
        zzdr.zza = zzabVar;
        this.zze = context;
        this.zzf = zzguVar.zzb;
        this.zzg = zzguVar.zzc;
        this.zzh = zzguVar.zzd;
        this.zzi = zzguVar.zzh;
        this.zzE = zzguVar.zze;
        this.zzw = zzguVar.zzj;
        this.zzF = true;
        com.google.android.gms.internal.measurement.zzcl zzclVar = zzguVar.zzg;
        if (zzclVar != null && (bundle = zzclVar.zzg) != null) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zza = (Boolean) obj;
            }
            Object obj2 = zzclVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzb = (Boolean) obj2;
            }
        }
        com.google.android.gms.internal.measurement.zzib.zze(context);
        Clock defaultClock = DefaultClock.getInstance();
        this.zzr = defaultClock;
        Long l = zzguVar.zzi;
        this.zzc = l != null ? l.longValue() : defaultClock.currentTimeMillis();
        this.zzk = new zzag(this);
        zzew zzewVar = new zzew(this);
        zzewVar.zzv();
        this.zzl = zzewVar;
        zzeh zzehVar = new zzeh(this);
        zzehVar.zzv();
        this.zzm = zzehVar;
        zzlb zzlbVar = new zzlb(this);
        zzlbVar.zzv();
        this.zzp = zzlbVar;
        this.zzq = new zzec(new zzgt(zzguVar, this));
        this.zzu = new zzd(this);
        zzim zzimVar = new zzim(this);
        zzimVar.zzb();
        this.zzs = zzimVar;
        zzhx zzhxVar = new zzhx(this);
        zzhxVar.zzb();
        this.zzt = zzhxVar;
        zzkc zzkcVar = new zzkc(this);
        zzkcVar.zzb();
        this.zzo = zzkcVar;
        zzib zzibVar = new zzib(this);
        zzibVar.zzv();
        this.zzv = zzibVar;
        zzfo zzfoVar = new zzfo(this);
        zzfoVar.zzv();
        this.zzn = zzfoVar;
        com.google.android.gms.internal.measurement.zzcl zzclVar2 = zzguVar.zzg;
        boolean z = zzclVar2 == null || zzclVar2.zzb == 0;
        if (context.getApplicationContext() instanceof Application) {
            zzhx zzhxVarZzq = zzq();
            if (zzhxVarZzq.zzt.zze.getApplicationContext() instanceof Application) {
                Application application = (Application) zzhxVarZzq.zzt.zze.getApplicationContext();
                if (zzhxVarZzq.zza == null) {
                    zzhxVarZzq.zza = new zzhw(zzhxVarZzq, null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzhxVarZzq.zza);
                    application.registerActivityLifecycleCallbacks(zzhxVarZzq.zza);
                    zzhxVarZzq.zzt.zzay().zzj().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzay().zzk().zza("Application context is not an Application");
        }
        zzfoVar.zzp(new zzfq(this, zzguVar));
    }

    static /* bridge */ /* synthetic */ void zzA(zzfr zzfrVar, zzgu zzguVar) {
        zzfrVar.zzaz().zzg();
        zzfrVar.zzk.zzn();
        zzaq zzaqVar = new zzaq(zzfrVar);
        zzaqVar.zzv();
        zzfrVar.zzz = zzaqVar;
        zzdy zzdyVar = new zzdy(zzfrVar, zzguVar.zzf);
        zzdyVar.zzb();
        zzfrVar.zzA = zzdyVar;
        zzea zzeaVar = new zzea(zzfrVar);
        zzeaVar.zzb();
        zzfrVar.zzx = zzeaVar;
        zzjm zzjmVar = new zzjm(zzfrVar);
        zzjmVar.zzb();
        zzfrVar.zzy = zzjmVar;
        zzfrVar.zzp.zzw();
        zzfrVar.zzl.zzw();
        zzfrVar.zzA.zzc();
        zzef zzefVarZzi = zzfrVar.zzay().zzi();
        zzfrVar.zzk.zzh();
        zzefVarZzi.zzb("App measurement initialized, version", 74029L);
        zzfrVar.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String strZzl = zzdyVar.zzl();
        if (TextUtils.isEmpty(zzfrVar.zzf)) {
            if (zzfrVar.zzv().zzae(strZzl)) {
                zzfrVar.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzfrVar.zzay().zzi().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(String.valueOf(strZzl)));
            }
        }
        zzfrVar.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzfrVar.zzG != zzfrVar.zzH.get()) {
            zzfrVar.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzfrVar.zzG), Integer.valueOf(zzfrVar.zzH.get()));
        }
        zzfrVar.zzB = true;
    }

    static final void zzO() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzP(zzgk zzgkVar) {
        if (zzgkVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzQ(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzfVar.zze()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzfVar.getClass()))));
        }
    }

    private static final void zzR(zzgl zzglVar) {
        if (zzglVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (!zzglVar.zzx()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzglVar.getClass()))));
        }
    }

    public static zzfr zzp(Context context, com.google.android.gms.internal.measurement.zzcl zzclVar, Long l) {
        Bundle bundle;
        if (zzclVar != null && (zzclVar.zze == null || zzclVar.zzf == null)) {
            zzclVar = new com.google.android.gms.internal.measurement.zzcl(zzclVar.zza, zzclVar.zzb, zzclVar.zzc, zzclVar.zzd, null, null, zzclVar.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzd == null) {
            synchronized (zzfr.class) {
                if (zzd == null) {
                    zzd = new zzfr(new zzgu(context, zzclVar, l));
                }
            }
        } else if (zzclVar != null && (bundle = zzclVar.zzg) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(zzd);
            zzd.zzE = Boolean.valueOf(zzclVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzd);
        return zzd;
    }

    final void zzB() {
        this.zzH.incrementAndGet();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ void zzC(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map r11) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfr.zzC(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    final void zzD() {
        this.zzG++;
    }

    public final void zzE() throws IllegalStateException {
        zzaz().zzg();
        zzR(zzr());
        String strZzl = zzh().zzl();
        Pair pairZzb = zzm().zzb(strZzl);
        if (!this.zzk.zzr() || ((Boolean) pairZzb.second).booleanValue() || TextUtils.isEmpty((CharSequence) pairZzb.first)) {
            zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        zzib zzibVarZzr = zzr();
        zzibVarZzr.zzu();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzibVarZzr.zzt.zze.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            try {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzay().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzlb zzlbVarZzv = zzv();
        zzh().zzt.zzk.zzh();
        URL urlZzE = zzlbVarZzv.zzE(74029L, strZzl, (String) pairZzb.first, zzm().zzo.zza() - 1);
        if (urlZzE != null) {
            zzib zzibVarZzr2 = zzr();
            zzfp zzfpVar = new zzfp(this);
            zzibVarZzr2.zzg();
            zzibVarZzr2.zzu();
            Preconditions.checkNotNull(urlZzE);
            Preconditions.checkNotNull(zzfpVar);
            zzibVarZzr2.zzt.zzaz().zzo(new zzia(zzibVarZzr2, strZzl, urlZzE, null, null, zzfpVar, null));
        }
    }

    final void zzF(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    public final void zzG(boolean z) {
        zzaz().zzg();
        this.zzF = z;
    }

    protected final void zzH(com.google.android.gms.internal.measurement.zzcl zzclVar) throws ClassNotFoundException {
        zzai zzaiVar;
        zzaz().zzg();
        zzai zzaiVarZzc = zzm().zzc();
        zzew zzewVarZzm = zzm();
        zzfr zzfrVar = zzewVarZzm.zzt;
        zzewVarZzm.zzg();
        int i = 100;
        int i2 = zzewVarZzm.zza().getInt("consent_source", 100);
        zzag zzagVar = this.zzk;
        zzfr zzfrVar2 = zzagVar.zzt;
        Boolean boolZzk = zzagVar.zzk("google_analytics_default_allow_ad_storage");
        zzag zzagVar2 = this.zzk;
        zzfr zzfrVar3 = zzagVar2.zzt;
        Boolean boolZzk2 = zzagVar2.zzk("google_analytics_default_allow_analytics_storage");
        if (!(boolZzk == null && boolZzk2 == null) && zzm().zzl(-10)) {
            zzaiVar = new zzai(boolZzk, boolZzk2);
            i = -10;
        } else {
            if (!TextUtils.isEmpty(zzh().zzm()) && (i2 == 0 || i2 == 30 || i2 == 10 || i2 == 30 || i2 == 30 || i2 == 40)) {
                zzq().zzS(zzai.zza, -10, this.zzc);
            } else if (TextUtils.isEmpty(zzh().zzm()) && zzclVar != null && zzclVar.zzg != null && zzm().zzl(30)) {
                zzaiVar = zzai.zza(zzclVar.zzg);
                if (!zzaiVar.equals(zzai.zza)) {
                    i = 30;
                }
            }
            zzaiVar = null;
        }
        if (zzaiVar != null) {
            zzq().zzS(zzaiVar, i, this.zzc);
            zzaiVarZzc = zzaiVar;
        }
        zzq().zzV(zzaiVarZzc);
        if (zzm().zzc.zza() == 0) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.zzc));
            zzm().zzc.zzb(this.zzc);
        }
        zzq().zzb.zzc();
        if (zzM()) {
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().zzk())) {
                zzlb zzlbVarZzv = zzv();
                String strZzm = zzh().zzm();
                zzew zzewVarZzm2 = zzm();
                zzewVarZzm2.zzg();
                String string = zzewVarZzm2.zza().getString("gmp_app_id", null);
                String strZzk = zzh().zzk();
                zzew zzewVarZzm3 = zzm();
                zzewVarZzm3.zzg();
                if (zzlbVarZzv.zzam(strZzm, string, strZzk, zzewVarZzm3.zza().getString("admob_app_id", null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzew zzewVarZzm4 = zzm();
                    zzewVarZzm4.zzg();
                    Boolean boolZzd = zzewVarZzm4.zzd();
                    SharedPreferences.Editor editorEdit = zzewVarZzm4.zza().edit();
                    editorEdit.clear();
                    editorEdit.apply();
                    if (boolZzd != null) {
                        zzewVarZzm4.zzh(boolZzd);
                    }
                    zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.zzr();
                    zzm().zzc.zzb(this.zzc);
                    zzm().zze.zzb(null);
                }
                zzew zzewVarZzm5 = zzm();
                String strZzm2 = zzh().zzm();
                zzewVarZzm5.zzg();
                SharedPreferences.Editor editorEdit2 = zzewVarZzm5.zza().edit();
                editorEdit2.putString("gmp_app_id", strZzm2);
                editorEdit2.apply();
                zzew zzewVarZzm6 = zzm();
                String strZzk2 = zzh().zzk();
                zzewVarZzm6.zzg();
                SharedPreferences.Editor editorEdit3 = zzewVarZzm6.zza().edit();
                editorEdit3.putString("admob_app_id", strZzk2);
                editorEdit3.apply();
            }
            if (!zzm().zzc().zzi(zzah.ANALYTICS_STORAGE)) {
                zzm().zze.zzb(null);
            }
            zzq().zzO(zzm().zze.zza());
            zznw.zzc();
            if (this.zzk.zzs(null, zzdu.zzac)) {
                try {
                    zzv().zzt.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException unused) {
                    if (!TextUtils.isEmpty(zzm().zzp.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().zzp.zzb(null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().zzk())) {
                boolean zZzJ = zzJ();
                if (!zzm().zzj() && !this.zzk.zzv()) {
                    zzm().zzi(!zZzJ);
                }
                if (zZzJ) {
                    zzq().zzz();
                }
                zzu().zza.zza();
                zzt().zzu(new AtomicReference());
                zzt().zzH(zzm().zzs.zza());
            }
        } else if (zzJ()) {
            if (!zzv().zzad("android.permission.INTERNET")) {
                zzay().zzd().zza("App is missing INTERNET permission");
            }
            if (!zzv().zzad("android.permission.ACCESS_NETWORK_STATE")) {
                zzay().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.zzx()) {
                if (!zzlb.zzaj(this.zze)) {
                    zzay().zzd().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzlb.zzak(this.zze, false)) {
                    zzay().zzd().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzay().zzd().zza("Uploading is not possible. App measurement disabled");
        }
        zzm().zzi.zza(true);
    }

    public final boolean zzI() {
        return this.zzE != null && this.zzE.booleanValue();
    }

    public final boolean zzJ() {
        return zza() == 0;
    }

    public final boolean zzK() {
        zzaz().zzg();
        return this.zzF;
    }

    @Pure
    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    protected final boolean zzM() {
        if (!this.zzB) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        zzaz().zzg();
        Boolean bool = this.zzC;
        if (bool == null || this.zzD == 0 || (!bool.booleanValue() && Math.abs(this.zzr.elapsedRealtime() - this.zzD) > 1000)) {
            this.zzD = this.zzr.elapsedRealtime();
            boolean z = true;
            Boolean boolValueOf = Boolean.valueOf(zzv().zzad("android.permission.INTERNET") && zzv().zzad("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.zzx() || (zzlb.zzaj(this.zze) && zzlb.zzak(this.zze, false))));
            this.zzC = boolValueOf;
            if (boolValueOf.booleanValue()) {
                if (!zzv().zzX(zzh().zzm(), zzh().zzk()) && TextUtils.isEmpty(zzh().zzk())) {
                    z = false;
                }
                this.zzC = Boolean.valueOf(z);
            }
        }
        return this.zzC.booleanValue();
    }

    @Pure
    public final boolean zzN() {
        return this.zzi;
    }

    public final int zza() {
        zzaz().zzg();
        if (this.zzk.zzv()) {
            return 1;
        }
        Boolean bool = this.zzb;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        zzaz().zzg();
        if (!this.zzF) {
            return 8;
        }
        Boolean boolZzd = zzm().zzd();
        if (boolZzd != null) {
            return boolZzd.booleanValue() ? 0 : 3;
        }
        zzag zzagVar = this.zzk;
        zzab zzabVar = zzagVar.zzt.zzj;
        Boolean boolZzk = zzagVar.zzk("firebase_analytics_collection_enabled");
        if (boolZzk != null) {
            return boolZzk.booleanValue() ? 0 : 4;
        }
        Boolean bool2 = this.zza;
        return bool2 != null ? bool2.booleanValue() ? 0 : 5 : (this.zzE == null || this.zzE.booleanValue()) ? 0 : 7;
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    @Pure
    public final Context zzau() {
        return this.zze;
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    @Pure
    public final Clock zzav() {
        return this.zzr;
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    @Pure
    public final zzab zzaw() {
        return this.zzj;
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    @Pure
    public final zzeh zzay() {
        zzR(this.zzm);
        return this.zzm;
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    @Pure
    public final zzfo zzaz() {
        zzR(this.zzn);
        return this.zzn;
    }

    @Pure
    public final zzd zzd() {
        zzd zzdVar = this.zzu;
        if (zzdVar != null) {
            return zzdVar;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzag zzf() {
        return this.zzk;
    }

    @Pure
    public final zzaq zzg() {
        zzR(this.zzz);
        return this.zzz;
    }

    @Pure
    public final zzdy zzh() {
        zzQ(this.zzA);
        return this.zzA;
    }

    @Pure
    public final zzea zzi() {
        zzQ(this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzec zzj() {
        return this.zzq;
    }

    public final zzeh zzl() {
        zzeh zzehVar = this.zzm;
        if (zzehVar == null || !zzehVar.zzx()) {
            return null;
        }
        return zzehVar;
    }

    @Pure
    public final zzew zzm() {
        zzP(this.zzl);
        return this.zzl;
    }

    @SideEffectFree
    final zzfo zzo() {
        return this.zzn;
    }

    @Pure
    public final zzhx zzq() {
        zzQ(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzib zzr() {
        zzR(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzim zzs() {
        zzQ(this.zzs);
        return this.zzs;
    }

    @Pure
    public final zzjm zzt() {
        zzQ(this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzkc zzu() {
        zzQ(this.zzo);
        return this.zzo;
    }

    @Pure
    public final zzlb zzv() {
        zzP(this.zzp);
        return this.zzp;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzg;
    }

    @Pure
    public final String zzy() {
        return this.zzh;
    }

    @Pure
    public final String zzz() {
        return this.zzw;
    }
}
