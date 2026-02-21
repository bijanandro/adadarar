package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzbv implements zzbx {
    zzbv() {
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return Byte.valueOf(zza());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
