package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhl extends zzdd implements zzel {
    private static final zzhl zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private zzhb zzg;
    private zzhe zzh;

    static {
        zzhl zzhlVar = new zzhl();
        zzb = zzhlVar;
        zzdd.zzt(zzhl.class, zzhlVar);
    }

    private zzhl() {
    }

    static /* synthetic */ void zzB(zzhl zzhlVar, zzhs zzhsVar) {
        zzhlVar.zzf = zzhsVar;
        zzhlVar.zze = 4;
    }

    static /* synthetic */ void zzC(zzhl zzhlVar, zzgy zzgyVar) {
        zzhlVar.zzf = zzgyVar;
        zzhlVar.zze = 5;
    }

    static /* synthetic */ void zzD(zzhl zzhlVar, zzhe zzheVar) {
        zzheVar.getClass();
        zzhlVar.zzh = zzheVar;
        zzhlVar.zzd |= 2;
    }

    static /* synthetic */ void zzE(zzhl zzhlVar, zzhb zzhbVar) {
        zzhlVar.zzg = zzhbVar;
        zzhlVar.zzd |= 1;
    }

    static /* synthetic */ void zzF(zzhl zzhlVar, zzgh zzghVar) {
        zzhlVar.zzf = zzghVar;
        zzhlVar.zze = 2;
    }

    static /* synthetic */ void zzG(zzhl zzhlVar, zzgl zzglVar) {
        zzhlVar.zzf = zzglVar;
        zzhlVar.zze = 3;
    }

    public static zzhk zzz() {
        return (zzhk) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006ဉ\u0001", new Object[]{"zzf", "zze", "zzd", "zzg", zzgh.class, zzgl.class, zzhs.class, zzgy.class, "zzh"});
        }
        if (i2 == 3) {
            return new zzhl();
        }
        zzhj zzhjVar = null;
        if (i2 == 4) {
            return new zzhk(zzhjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
