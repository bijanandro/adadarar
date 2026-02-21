package com.google.android.gms.internal.play_billing;

import java.util.Objects;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzap extends zzai {
    static final zzai zza = new zzap(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzap(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzaa.zza(i, this.zzc, "index");
        return Objects.requireNonNull(this.zzb[i]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzai, com.google.android.gms.internal.play_billing.zzaf
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    final Object[] zzg() {
        return this.zzb;
    }
}
