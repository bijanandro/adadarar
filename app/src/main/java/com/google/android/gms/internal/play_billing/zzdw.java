package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdw extends zzdy {
    private zzdw() {
        super(null);
    }

    /* synthetic */ zzdw(zzdv zzdvVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdy
    final void zza(Object obj, long j) {
        ((zzdk) zzfw.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdy
    final void zzb(Object obj, Object obj2, long j) {
        zzdk zzdkVarZzd = (zzdk) zzfw.zzf(obj, j);
        zzdk zzdkVar = (zzdk) zzfw.zzf(obj2, j);
        int size = zzdkVarZzd.size();
        int size2 = zzdkVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzdkVarZzd.zzc()) {
                zzdkVarZzd = zzdkVarZzd.zzd(size2 + size);
            }
            zzdkVarZzd.addAll(zzdkVar);
        }
        if (size > 0) {
            zzdkVar = zzdkVarZzd;
        }
        zzfw.zzs(obj, j, zzdkVar);
    }
}
