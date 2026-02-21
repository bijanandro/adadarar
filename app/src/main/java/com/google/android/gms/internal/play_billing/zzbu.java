package com.google.android.gms.internal.play_billing;

import java.util.Comparator;
import kotlin.UByte;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbu implements Comparator {
    zzbu() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzcc zzccVar = (zzcc) obj;
        zzcc zzccVar2 = (zzcc) obj2;
        zzbt zzbtVar = new zzbt(zzccVar);
        zzbt zzbtVar2 = new zzbt(zzccVar2);
        while (zzbtVar.hasNext() && zzbtVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzbtVar.zza() & UByte.MAX_VALUE).compareTo(Integer.valueOf(zzbtVar2.zza() & UByte.MAX_VALUE));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzccVar.zzd()).compareTo(Integer.valueOf(zzccVar2.zzd()));
    }
}
