package com.google.android.gms.internal.play_billing;

import java.util.Arrays;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzak {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzaj zzc;

    public final zzak zza(Object obj, Object obj2) {
        int i = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int iHighestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = iHighestOneBit + iHighestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
        }
        zzad.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        int i5 = i4 + i4;
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        this.zzb = i4 + 1;
        return this;
    }

    public final zzal zzb() {
        zzaj zzajVar = this.zzc;
        if (zzajVar != null) {
            throw zzajVar.zza();
        }
        zzau zzauVarZzg = zzau.zzg(this.zzb, this.zza, this);
        zzaj zzajVar2 = this.zzc;
        if (zzajVar2 == null) {
            return zzauVarZzg;
        }
        throw zzajVar2.zza();
    }
}
