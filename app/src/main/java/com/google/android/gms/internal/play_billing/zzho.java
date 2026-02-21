package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzho extends zzdd implements zzel {
    private static final zzho zzb;
    private int zzd;
    private int zzf;
    private zzdk zze = zzdd.zzm();
    private String zzg = "";

    static {
        zzho zzhoVar = new zzho();
        zzb = zzhoVar;
        zzdd.zzt(zzho.class, zzhoVar);
    }

    private zzho() {
    }

    static /* synthetic */ void zzB(zzho zzhoVar, Iterable iterable) {
        zzdk zzdkVar = zzhoVar.zze;
        if (!zzdkVar.zzc()) {
            zzhoVar.zze = zzdd.zzn(zzdkVar);
        }
        zzbm.zzc(iterable, zzhoVar.zze);
    }

    static /* synthetic */ void zzC(zzho zzhoVar, int i) {
        zzhoVar.zzd |= 1;
        zzhoVar.zzf = i;
    }

    static /* synthetic */ void zzD(zzho zzhoVar, String str) {
        str.getClass();
        zzhoVar.zzd |= 2;
        zzhoVar.zzg = str;
    }

    public static zzhn zzz() {
        return (zzhn) zzb.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001a\u0002င\u0000\u0003ဈ\u0001", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzho();
        }
        zzhm zzhmVar = null;
        if (i2 == 4) {
            return new zzhn(zzhmVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
