package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhi extends zzdd implements zzel {
    private static final zzhi zzb;
    private int zzd;
    private int zze;

    static {
        zzhi zzhiVar = new zzhi();
        zzb = zzhiVar;
        zzdd.zzt(zzhi.class, zzhiVar);
    }

    private zzhi() {
    }

    static /* synthetic */ void zzB(zzhi zzhiVar, int i) {
        zzhiVar.zze = i - 1;
        zzhiVar.zzd |= 1;
    }

    public static zzhg zzz() {
        return (zzhg) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzhh.zza});
        }
        if (i2 == 3) {
            return new zzhi();
        }
        zzhf zzhfVar = null;
        if (i2 == 4) {
            return new zzhg(zzhfVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
