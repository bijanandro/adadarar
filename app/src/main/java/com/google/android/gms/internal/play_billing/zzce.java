package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzce extends zzcg {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze;

    /* synthetic */ zzce(byte[] bArr, int i, int i2, boolean z, zzcd zzcdVar) {
        super(null);
        this.zze = Integer.MAX_VALUE;
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i) throws zzdn {
        int i2 = this.zze;
        this.zze = 0;
        int i3 = this.zzc + this.zzd;
        this.zzc = i3;
        if (i3 > 0) {
            this.zzd = i3;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return i2;
    }
}
