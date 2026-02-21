package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgy extends zzdd implements zzel {
    private static final zzdj zzb = new zzgs();
    private static final zzgy zzd;
    private int zze;
    private int zzg;
    private zzgr zzj;
    private boolean zzk;
    private boolean zzl;
    private String zzf = "";
    private zzdi zzh = zzl();
    private zzdk zzi = zzm();

    static {
        zzgy zzgyVar = new zzgy();
        zzd = zzgyVar;
        zzdd.zzt(zzgy.class, zzgyVar);
    }

    private zzgy() {
    }

    public static zzgy zzB(byte[] bArr, zzcp zzcpVar) throws zzdn {
        return (zzgy) zzdd.zzk(zzd, bArr, zzcpVar);
    }

    static /* synthetic */ void zzC(zzgy zzgyVar, zzho zzhoVar) {
        zzhoVar.getClass();
        zzdk zzdkVar = zzgyVar.zzi;
        if (!zzdkVar.zzc()) {
            zzgyVar.zzi = zzdd.zzn(zzdkVar);
        }
        zzgyVar.zzi.add(zzhoVar);
    }

    static /* synthetic */ void zzD(zzgy zzgyVar, zzgr zzgrVar) {
        zzgrVar.getClass();
        zzgyVar.zzj = zzgrVar;
        zzgyVar.zze |= 4;
    }

    static /* synthetic */ void zzE(zzgy zzgyVar, boolean z) {
        zzgyVar.zze |= 8;
        zzgyVar.zzk = z;
    }

    static /* synthetic */ void zzF(zzgy zzgyVar, boolean z) {
        zzgyVar.zze |= 16;
        zzgyVar.zzl = false;
    }

    static /* synthetic */ void zzG(zzgy zzgyVar, Iterable iterable) {
        zzdi zzdiVar = zzgyVar.zzh;
        if (!zzdiVar.zzc()) {
            int size = zzdiVar.size();
            zzgyVar.zzh = zzdiVar.zzd(size == 0 ? 10 : size + size);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzgyVar.zzh.zzh(((zzgv) it.next()).zza());
        }
    }

    static /* synthetic */ void zzH(zzgy zzgyVar, int i) {
        zzgyVar.zzg = i - 1;
        zzgyVar.zze |= 2;
    }

    public static zzgw zzz() {
        return (zzgw) zzd.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdd
    protected final Object zzy(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzq(zzd, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0002\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ࠬ\u0004\u001b\u0005ဉ\u0002\u0006ဇ\u0003\u0007ဇ\u0004", new Object[]{"zze", "zzf", "zzg", zzgx.zza, "zzh", zzgu.zza, "zzi", zzho.class, "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzgy();
        }
        zzgs zzgsVar = null;
        if (i2 == 4) {
            return new zzgw(zzgsVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
