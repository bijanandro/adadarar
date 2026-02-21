package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzfu extends zzfv {
    zzfu(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    /* JADX WARN: Failed to inline method: com.google.android.gms.internal.play_billing.zzfw.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Failed to inline method: com.google.android.gms.internal.play_billing.zzfw.zzj(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r5v0 'z' boolean)' in method call: com.google.android.gms.internal.play_billing.zzfw.zzi(java.lang.Object, long, boolean):void */
    /* JADX WARN: Unknown register number '(r5v0 'z' boolean)' in method call: com.google.android.gms.internal.play_billing.zzfw.zzj(java.lang.Object, long, boolean):void */
    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final void zzc(Object obj, long j, boolean z) {
        if (zzfw.zzb) {
            zzfw.zzi(obj, j, z);
        } else {
            zzfw.zzj(obj, j, z);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final void zzd(Object obj, long j, byte b) {
        if (zzfw.zzb) {
            zzfw.zzD(obj, j, b);
        } else {
            zzfw.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.play_billing.zzfv
    public final boolean zzg(Object obj, long j) {
        return zzfw.zzb ? zzfw.zzt(obj, j) : zzfw.zzu(obj, j);
    }
}
