package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgr extends zzdd implements zzel {
    private static final zzgr zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private String zzf = "";
    private String zzh = "";

    static {
        zzgr zzgrVar = new zzgr();
        zzb = zzgrVar;
        zzdd.zzt(zzgr.class, zzgrVar);
    }

    private zzgr() {
    }

    static /* synthetic */ void zzB(zzgr zzgrVar, int i) {
        zzgrVar.zzd |= 1;
        zzgrVar.zze = i;
    }

    static /* synthetic */ void zzC(zzgr zzgrVar, String str) {
        str.getClass();
        zzgrVar.zzd |= 2;
        zzgrVar.zzf = str;
    }

    static /* synthetic */ void zzD(zzgr zzgrVar, String str) {
        zzgrVar.zzd |= 8;
        zzgrVar.zzh = str;
    }

    static /* synthetic */ void zzE(zzgr zzgrVar, int i) {
        zzgrVar.zzg = i - 1;
        zzgrVar.zzd |= 4;
    }

    public static zzgn zzz() {
        return (zzgn) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004᠌\u0002\u0005ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzgp.zza, "zzh"});
        }
        if (i2 == 3) {
            return new zzgr();
        }
        zzgm zzgmVar = null;
        if (i2 == 4) {
            return new zzgn(zzgmVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
