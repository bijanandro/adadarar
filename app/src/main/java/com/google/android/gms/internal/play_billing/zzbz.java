package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
class zzbz extends zzby {
    protected final byte[] zza;

    zzbz(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcc) || zzd() != ((zzcc) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzbz)) {
            return obj.equals(this);
        }
        zzbz zzbzVar = (zzbz) obj;
        int iZzk = zzk();
        int iZzk2 = zzbzVar.zzk();
        if (iZzk != 0 && iZzk2 != 0 && iZzk != iZzk2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzbzVar.zzd()) {
            throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
        }
        if (iZzd > zzbzVar.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zzbzVar.zzd());
        }
        if (!(zzbzVar instanceof zzbz)) {
            return zzbzVar.zzf(0, iZzd).equals(zzf(0, iZzd));
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzbzVar.zza;
        zzbzVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < iZzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    protected final int zze(int i, int i2, int i3) {
        return zzdl.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    public final zzcc zzf(int i, int i2) {
        int iZzj = zzj(0, i2, zzd());
        return iZzj == 0 ? zzcc.zzb : new zzbw(this.zza, 0, iZzj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    final void zzh(zzbs zzbsVar) throws IOException {
        ((zzch) zzbsVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.play_billing.zzcc
    public final boolean zzi() {
        return zzgb.zze(this.zza, 0, zzd());
    }
}
