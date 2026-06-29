package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes4.dex */
public abstract class zzdw extends com.google.android.gms.internal.measurement.zzbn implements zzdx {
    public zzdw() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzbn
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzaw zzawVar = (zzaw) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzaw.CREATOR);
                zzq zzqVar = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzk(zzawVar, zzqVar);
                parcel2.writeNoException();
                return true;
            case 2:
                zzkw zzkwVar = (zzkw) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzkw.CREATOR);
                zzq zzqVar2 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzt(zzkwVar, zzqVar2);
                parcel2.writeNoException();
                return true;
            case 3:
            case 8:
            default:
                return false;
            case 4:
                zzq zzqVar3 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzj(zzqVar3);
                parcel2.writeNoException();
                return true;
            case 5:
                zzaw zzawVar2 = (zzaw) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzaw.CREATOR);
                String string = parcel.readString();
                String string2 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzl(zzawVar2, string, string2);
                parcel2.writeNoException();
                return true;
            case 6:
                zzq zzqVar4 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzs(zzqVar4);
                parcel2.writeNoException();
                return true;
            case 7:
                zzq zzqVar5 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                boolean zZzg = com.google.android.gms.internal.measurement.zzbo.zzg(parcel);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                List listZze = zze(zzqVar5, zZzg);
                parcel2.writeNoException();
                parcel2.writeTypedList(listZze);
                return true;
            case 9:
                zzaw zzawVar3 = (zzaw) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzaw.CREATOR);
                String string3 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                byte[] bArrZzu = zzu(zzawVar3, string3);
                parcel2.writeNoException();
                parcel2.writeByteArray(bArrZzu);
                return true;
            case 10:
                long j = parcel.readLong();
                String string4 = parcel.readString();
                String string5 = parcel.readString();
                String string6 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzq(j, string4, string5, string6);
                parcel2.writeNoException();
                return true;
            case 11:
                zzq zzqVar6 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                String strZzd = zzd(zzqVar6);
                parcel2.writeNoException();
                parcel2.writeString(strZzd);
                return true;
            case 12:
                zzac zzacVar = (zzac) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzac.CREATOR);
                zzq zzqVar7 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzn(zzacVar, zzqVar7);
                parcel2.writeNoException();
                return true;
            case 13:
                zzac zzacVar2 = (zzac) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzac.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzo(zzacVar2);
                parcel2.writeNoException();
                return true;
            case 14:
                String string7 = parcel.readString();
                String string8 = parcel.readString();
                boolean zZzg2 = com.google.android.gms.internal.measurement.zzbo.zzg(parcel);
                zzq zzqVar8 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                List listZzh = zzh(string7, string8, zZzg2, zzqVar8);
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzh);
                return true;
            case 15:
                String string9 = parcel.readString();
                String string10 = parcel.readString();
                String string11 = parcel.readString();
                boolean zZzg3 = com.google.android.gms.internal.measurement.zzbo.zzg(parcel);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                List listZzi = zzi(string9, string10, string11, zZzg3);
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzi);
                return true;
            case 16:
                String string12 = parcel.readString();
                String string13 = parcel.readString();
                zzq zzqVar9 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                List listZzf = zzf(string12, string13, zzqVar9);
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzf);
                return true;
            case 17:
                String string14 = parcel.readString();
                String string15 = parcel.readString();
                String string16 = parcel.readString();
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                List listZzg = zzg(string14, string15, string16);
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzg);
                return true;
            case 18:
                zzq zzqVar10 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzm(zzqVar10);
                parcel2.writeNoException();
                return true;
            case 19:
                Bundle bundle = (Bundle) com.google.android.gms.internal.measurement.zzbo.zza(parcel, Bundle.CREATOR);
                zzq zzqVar11 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzr(bundle, zzqVar11);
                parcel2.writeNoException();
                return true;
            case 20:
                zzq zzqVar12 = (zzq) com.google.android.gms.internal.measurement.zzbo.zza(parcel, zzq.CREATOR);
                com.google.android.gms.internal.measurement.zzbo.zzc(parcel);
                zzp(zzqVar12);
                parcel2.writeNoException();
                return true;
        }
    }
}
