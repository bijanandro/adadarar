package com.google.android.gms.internal.play_billing;

import java.util.Objects;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzat extends zzai {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    zzat(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
        this.zzc = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzaa.zza(i, this.zzc, "index");
        return Objects.requireNonNull(this.zza[i + i + this.zzb]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    final boolean zzf() {
        return true;
    }
}
