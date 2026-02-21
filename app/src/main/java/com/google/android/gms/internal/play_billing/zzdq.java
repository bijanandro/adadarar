package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public class zzdq {
    private static final zzcp zzb = zzcp.zza;
    protected volatile zzek zza;
    private volatile zzcc zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdq)) {
            return false;
        }
        zzdq zzdqVar = (zzdq) obj;
        zzek zzekVar = this.zza;
        zzek zzekVar2 = zzdqVar.zza;
        if (zzekVar == null && zzekVar2 == null) {
            return zzb().equals(zzdqVar.zzb());
        }
        if (zzekVar != null && zzekVar2 != null) {
            return zzekVar.equals(zzekVar2);
        }
        if (zzekVar != null) {
            zzdqVar.zzc(zzekVar.zzf());
            return zzekVar.equals(zzdqVar.zza);
        }
        zzc(zzekVar2.zzf());
        return this.zza.equals(zzekVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzbz) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzg();
        }
        return 0;
    }

    public final zzcc zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzcc.zzb;
            } else {
                this.zzc = this.zza.zzb();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzek zzekVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza != null) {
                return;
            }
            try {
                this.zza = zzekVar;
                this.zzc = zzcc.zzb;
            } catch (zzdn unused) {
                this.zza = zzekVar;
                this.zzc = zzcc.zzb;
            }
        }
    }
}
