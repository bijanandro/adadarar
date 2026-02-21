package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgh extends zzdd implements zzel {
    private static final zzgh zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private zzgr zzh;

    static {
        zzgh zzghVar = new zzgh();
        zzb = zzghVar;
        zzdd.zzt(zzgh.class, zzghVar);
    }

    private zzgh() {
    }

    public static zzgh zzB(byte[] bArr, zzcp zzcpVar) throws zzdn {
        return (zzgh) zzdd.zzk(zzb, bArr, zzcpVar);
    }

    static /* synthetic */ void zzC(zzgh zzghVar, zzgr zzgrVar) {
        zzgrVar.getClass();
        zzghVar.zzh = zzgrVar;
        zzghVar.zzd |= 2;
    }

    static /* synthetic */ void zzD(zzgh zzghVar, zzhi zzhiVar) {
        zzhiVar.getClass();
        zzghVar.zzf = zzhiVar;
        zzghVar.zze = 4;
    }

    static /* synthetic */ void zzE(zzgh zzghVar, int i) {
        zzghVar.zzg = i - 1;
        zzghVar.zzd |= 1;
    }

    public static zzgg zzz() {
        return (zzgg) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0003\u0001\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0004<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzgi.zza, "zzh", zzhi.class});
        }
        if (i2 == 3) {
            return new zzgh();
        }
        zzgf zzgfVar = null;
        if (i2 == 4) {
            return new zzgg(zzgfVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
