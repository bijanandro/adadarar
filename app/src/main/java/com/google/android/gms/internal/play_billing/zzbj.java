package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbj extends zzaz implements Serializable {
    private final MessageDigest zza;
    private final int zzb;
    private final boolean zzc;
    private final String zzd;

    zzbj(String str, String str2) {
        MessageDigest messageDigestZzc = zzc("SHA-256");
        this.zza = messageDigestZzc;
        this.zzb = messageDigestZzc.getDigestLength();
        this.zzd = "Hashing.sha256()";
        this.zzc = zzd(messageDigestZzc);
    }

    private static MessageDigest zzc(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static boolean zzd(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    public final String toString() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbd
    public final zzbe zzb() {
        zzbh zzbhVar = null;
        if (this.zzc) {
            try {
                return new zzbi((MessageDigest) this.zza.clone(), this.zzb, zzbhVar);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new zzbi(zzc(this.zza.getAlgorithm()), this.zzb, zzbhVar);
    }
}
