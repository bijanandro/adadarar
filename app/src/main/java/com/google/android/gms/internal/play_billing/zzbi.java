package com.google.android.gms.internal.play_billing;

import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbi extends zzay {
    private final MessageDigest zza;
    private final int zzb;
    private boolean zzc;

    /* synthetic */ zzbi(MessageDigest messageDigest, int i, zzbh zzbhVar) {
        this.zza = messageDigest;
        this.zzb = i;
    }

    private final void zzd() {
        if (this.zzc) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzay
    protected final void zzb(byte[] bArr, int i, int i2) {
        zzd();
        this.zza.update(bArr, 0, 2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbe
    public final zzbc zzc() {
        zzd();
        this.zzc = true;
        int i = this.zzb;
        if (i == this.zza.getDigestLength()) {
            byte[] bArrDigest = this.zza.digest();
            int i2 = zzbc.zzb;
            return new zzbb(bArrDigest);
        }
        byte[] bArrCopyOf = Arrays.copyOf(this.zza.digest(), i);
        int i3 = zzbc.zzb;
        return new zzbb(bArrCopyOf);
    }
}
