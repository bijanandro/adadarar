package com.google.android.gms.internal.play_billing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class zzfr extends AbstractList implements RandomAccess, zzds {
    private final zzds zza;

    public zzfr(zzds zzdsVar) {
        this.zza = zzdsVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzdr) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzfq(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzfp(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final zzds zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final List zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final void zzi(zzcc zzccVar) {
        throw new UnsupportedOperationException();
    }
}
