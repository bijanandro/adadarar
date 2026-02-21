package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhb extends zzdd implements zzel {
    private static final zzhb zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzhb zzhbVar = new zzhb();
        zzb = zzhbVar;
        zzdd.zzt(zzhb.class, zzhbVar);
    }

    private zzhb() {
    }

    static /* synthetic */ void zzB(zzhb zzhbVar, String str) {
        str.getClass();
        zzhbVar.zzd |= 1;
        zzhbVar.zze = str;
    }

    static /* synthetic */ void zzC(zzhb zzhbVar, String str) {
        str.getClass();
        zzhbVar.zzd |= 2;
        zzhbVar.zzf = str;
    }

    public static zzha zzz() {
        return (zzha) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzhb();
        }
        zzgz zzgzVar = null;
        if (i2 == 4) {
            return new zzha(zzgzVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
