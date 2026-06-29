package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzin;
import com.google.android.gms.internal.measurement.zzio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes4.dex */
public abstract class zzio<MessageType extends zzio<MessageType, BuilderType>, BuilderType extends zzin<MessageType, BuilderType>> implements zzlm {
    protected int zzb = 0;

    protected static void zzbt(Iterable iterable, List list) {
        zzkn.zze(iterable);
        if (iterable instanceof zzku) {
            List listZzh = ((zzku) iterable).zzh();
            zzku zzkuVar = (zzku) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzkuVar.size() - size) + " is null.";
                    int size2 = zzkuVar.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            throw new NullPointerException(str);
                        }
                        zzkuVar.remove(size2);
                    }
                } else if (obj instanceof zzje) {
                    zzkuVar.zzi((zzje) obj);
                } else {
                    zzkuVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzlt) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size3 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                int size4 = list.size();
                while (true) {
                    size4--;
                    if (size4 < size3) {
                        throw new NullPointerException(str2);
                    }
                    list.remove(size4);
                }
            } else {
                list.add(obj2);
            }
        }
    }

    int zzbr(zzlx zzlxVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlm
    public final zzje zzbs() {
        try {
            int iZzbw = zzbw();
            zzje zzjeVar = zzje.zzb;
            byte[] bArr = new byte[iZzbw];
            zzjm zzjmVarZzC = zzjm.zzC(bArr);
            zzbN(zzjmVarZzC);
            zzjmVarZzC.zzD();
            return new zzjb(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzbu() {
        try {
            byte[] bArr = new byte[zzbw()];
            zzjm zzjmVarZzC = zzjm.zzC(bArr);
            zzbN(zzjmVarZzC);
            zzjmVarZzC.zzD();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
