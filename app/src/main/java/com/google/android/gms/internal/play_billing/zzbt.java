package com.google.android.gms.internal.play_billing;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbt extends zzbv {
    final /* synthetic */ zzcc zza;
    private int zzb = 0;
    private final int zzc;

    zzbt(zzcc zzccVar) {
        this.zza = zzccVar;
        this.zzc = zzccVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbx
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
