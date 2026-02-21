package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhs extends zzdd implements zzel {
    private static final zzhs zzb;
    private int zzd;
    private int zze;

    static {
        zzhs zzhsVar = new zzhs();
        zzb = zzhsVar;
        zzdd.zzt(zzhs.class, zzhsVar);
    }

    private zzhs() {
    }

    public static zzhs zzA() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzhr.zza});
        }
        if (i2 == 3) {
            return new zzhs();
        }
        zzhp zzhpVar = null;
        if (i2 == 4) {
            return new zzhq(zzhpVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
