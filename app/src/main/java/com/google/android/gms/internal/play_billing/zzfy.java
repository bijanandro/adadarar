package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzfy {
    zzfy() {
    }

    abstract int zza(int i, byte[] bArr, int i2, int i3);

    final boolean zzb(byte[] bArr, int i, int i2) {
        return zza(0, bArr, i, i2) == 0;
    }
}
