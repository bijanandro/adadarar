package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.Objects;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzaq extends zzai {
    final /* synthetic */ zzar zza;

    zzaq(zzar zzarVar) {
        this.zza = zzarVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaa.zza(i, this.zza.zzc, "index");
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    public final boolean zzf() {
        return true;
    }
}
