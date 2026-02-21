package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import kotlin.UByte;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbb extends zzbc implements Serializable {
    final byte[] zza;

    zzbb(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbc
    public final int zzb() {
        return this.zza.length * 8;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbc
    final boolean zzc(zzbc zzbcVar) {
        if (this.zza.length != zzbcVar.zzd().length) {
            return false;
        }
        boolean z = true;
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return z;
            }
            z &= bArr[i] == zzbcVar.zzd()[i];
            i++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzbc
    final byte[] zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbc
    public final int zza() {
        byte[] bArr = this.zza;
        int length = bArr.length;
        if (length < 4) {
            throw new IllegalStateException(zzab.zza("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(length)));
        }
        return ((bArr[3] & UByte.MAX_VALUE) << 24) | (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8) | ((bArr[2] & UByte.MAX_VALUE) << 16);
    }
}
