package com.google.android.gms.internal.play_billing;

import javax.annotation.CheckForNull;
import kotlin.UByte;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzbc {
    private static final char[] zza = "0123456789abcdef".toCharArray();
    public static final /* synthetic */ int zzb = 0;

    zzbc() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzbc) {
            zzbc zzbcVar = (zzbc) obj;
            if (zzb() == zzbcVar.zzb() && zzc(zzbcVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (zzb() >= 32) {
            return zza();
        }
        byte[] bArrZzd = zzd();
        int i = bArrZzd[0] & UByte.MAX_VALUE;
        for (int i2 = 1; i2 < bArrZzd.length; i2++) {
            i |= (bArrZzd[i2] & UByte.MAX_VALUE) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] bArrZzd = zzd();
        int length = bArrZzd.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bArrZzd) {
            char[] cArr = zza;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public abstract int zza();

    public abstract int zzb();

    abstract boolean zzc(zzbc zzbcVar);

    byte[] zzd() {
        throw null;
    }
}
