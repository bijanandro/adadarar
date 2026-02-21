package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgl extends zzdd implements zzel {
    private static final zzgl zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;

    static {
        zzgl zzglVar = new zzgl();
        zzb = zzglVar;
        zzdd.zzt(zzgl.class, zzglVar);
    }

    private zzgl() {
    }

    static /* synthetic */ void zzB(zzgl zzglVar, zzhi zzhiVar) {
        zzhiVar.getClass();
        zzglVar.zzf = zzhiVar;
        zzglVar.zze = 2;
    }

    static /* synthetic */ void zzC(zzgl zzglVar, int i) {
        zzglVar.zzg = i - 1;
        zzglVar.zzd |= 1;
    }

    public static zzgk zzz() {
        return (zzgk) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0002\u0001\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzgi.zza, zzhi.class});
        }
        if (i2 == 3) {
            return new zzgl();
        }
        zzgj zzgjVar = null;
        if (i2 == 4) {
            return new zzgk(zzgjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
