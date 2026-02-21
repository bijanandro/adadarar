package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhe extends zzdd implements zzel {
    private static final zzhe zzb;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzhe zzheVar = new zzhe();
        zzb = zzheVar;
        zzdd.zzt(zzhe.class, zzheVar);
    }

    private zzhe() {
    }

    static /* synthetic */ void zzB(zzhe zzheVar, boolean z) {
        zzheVar.zzd |= 2;
        zzheVar.zzf = false;
    }

    public static zzhd zzz() {
        return (zzhd) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzhe();
        }
        zzhc zzhcVar = null;
        if (i2 == 4) {
            return new zzhd(zzhcVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
