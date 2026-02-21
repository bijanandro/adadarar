package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbw extends zzbz {
    private final int zzc;

    zzbw(byte[] bArr, int i, int i2) {
        super(bArr);
        zzj(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbz, com.google.android.gms.internal.play_billing.zzcc
    final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.play_billing.zzbz
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbz, com.google.android.gms.internal.play_billing.zzcc
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbz, com.google.android.gms.internal.play_billing.zzcc
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.zza[i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }
}
