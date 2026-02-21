package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzbl;
import com.google.android.gms.internal.play_billing.zzbm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzbm<MessageType extends zzbm<MessageType, BuilderType>, BuilderType extends zzbl<MessageType, BuilderType>> implements zzek {
    protected int zza = 0;

    /* JADX WARN: Multi-variable type inference failed */
    protected static void zzc(Iterable iterable, List list) {
        byte[] bArr = zzdl.zzd;
        iterable.getClass();
        if (iterable instanceof zzds) {
            List listZzh = ((zzds) iterable).zzh();
            zzds zzdsVar = (zzds) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzdsVar.size() - size) + " is null.";
                    int size2 = zzdsVar.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            throw new NullPointerException(str);
                        }
                        zzdsVar.remove(size2);
                    }
                } else if (obj instanceof zzcc) {
                    zzdsVar.zzi((zzcc) obj);
                } else {
                    zzdsVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzer) {
            list.addAll(iterable);
            return;
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
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

    int zza(zzev zzevVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzek
    public final zzcc zzb() {
        try {
            int iZzg = zzg();
            zzcc zzccVar = zzcc.zzb;
            byte[] bArr = new byte[iZzg];
            zzck zzckVarZzy = zzck.zzy(bArr, 0, iZzg);
            zzv(zzckVarZzy);
            zzckVarZzy.zzz();
            return new zzbz(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzd() {
        try {
            int iZzg = zzg();
            byte[] bArr = new byte[iZzg];
            zzck zzckVarZzy = zzck.zzy(bArr, 0, iZzg);
            zzv(zzckVarZzy);
            zzckVarZzy.zzz();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
