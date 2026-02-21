package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzck extends zzbs {
    private static final Logger zzb = Logger.getLogger(zzck.class.getName());
    private static final boolean zzc = zzfw.zzx();
    zzcl zza;

    private zzck() {
    }

    /* synthetic */ zzck(zzcj zzcjVar) {
    }

    @Deprecated
    static int zzt(int i, zzek zzekVar, zzev zzevVar) {
        int iZzw = zzw(i << 3);
        return iZzw + iZzw + ((zzbm) zzekVar).zza(zzevVar);
    }

    static int zzu(zzek zzekVar, zzev zzevVar) {
        int iZza = ((zzbm) zzekVar).zza(zzevVar);
        return zzw(iZza) + iZza;
    }

    public static int zzv(String str) {
        int length;
        try {
            length = zzgb.zzc(str);
        } catch (zzga unused) {
            length = str.getBytes(zzdl.zzb).length;
        }
        return zzw(length) + length;
    }

    public static int zzw(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzx(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static zzck zzy(byte[] bArr, int i, int i2) {
        return new zzch(bArr, 0, i2);
    }

    final void zzA(String str, zzga zzgaVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzgaVar);
        byte[] bytes = str.getBytes(zzdl.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzci(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzcc zzccVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public final void zzz() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
}
