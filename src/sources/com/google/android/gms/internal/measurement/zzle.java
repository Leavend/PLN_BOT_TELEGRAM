package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
final class zzle implements zzly {
    private static final zzlk zza = new zzlc();
    private final zzlk zzb;

    public zzle() {
        zzlk zzlkVar;
        zzlk[] zzlkVarArr = new zzlk[2];
        zzlkVarArr[0] = zzka.zza();
        try {
            zzlkVar = (zzlk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzlkVar = zza;
        }
        zzlkVarArr[1] = zzlkVar;
        zzld zzldVar = new zzld(zzlkVarArr);
        zzkn.zzf(zzldVar, "messageInfoFactory");
        this.zzb = zzldVar;
    }

    private static boolean zzb(zzlj zzljVar) {
        return zzljVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzly
    public final zzlx zza(Class cls) {
        zzlz.zzG(cls);
        zzlj zzljVarZzb = this.zzb.zzb(cls);
        return zzljVarZzb.zzb() ? zzkf.class.isAssignableFrom(cls) ? zzlq.zzc(zzlz.zzB(), zzju.zzb(), zzljVarZzb.zza()) : zzlq.zzc(zzlz.zzz(), zzju.zza(), zzljVarZzb.zza()) : zzkf.class.isAssignableFrom(cls) ? zzb(zzljVarZzb) ? zzlp.zzl(cls, zzljVarZzb, zzls.zzb(), zzla.zzd(), zzlz.zzB(), zzju.zzb(), zzli.zzb()) : zzlp.zzl(cls, zzljVarZzb, zzls.zzb(), zzla.zzd(), zzlz.zzB(), null, zzli.zzb()) : zzb(zzljVarZzb) ? zzlp.zzl(cls, zzljVarZzb, zzls.zza(), zzla.zzc(), zzlz.zzz(), zzju.zza(), zzli.zza()) : zzlp.zzl(cls, zzljVarZzb, zzls.zza(), zzla.zzc(), zzlz.zzA(), null, zzli.zza());
    }
}
