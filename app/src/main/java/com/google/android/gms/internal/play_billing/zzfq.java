package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzfq implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzfr zzb;

    zzfq(zzfr zzfrVar) {
        this.zzb = zzfrVar;
        this.zza = zzfrVar.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
