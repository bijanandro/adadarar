package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzex {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzfm zzc;
    private static final zzfm zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zzfm zzfmVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzfmVar = (zzfm) cls2.getConstructor(null).newInstance(null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzfmVar;
        zzd = new zzfo();
    }

    public static void zzA(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzz(i, list, z);
    }

    public static void zzB(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzB(i, list, z);
    }

    public static void zzC(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzD(i, list, z);
    }

    public static void zzD(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzI(i, list, z);
    }

    public static void zzE(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzK(i, list, z);
    }

    static boolean zzF(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzde) {
            zzde zzdeVar = (zzde) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(zzdeVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzck.zzw(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzck.zzw(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzde) {
            zzde zzdeVar = (zzde) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(zzdeVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzg(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdzVar = (zzdz) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(zzdzVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzh(int i, Object obj, zzev zzevVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzdq)) {
            return zzck.zzw(i2) + zzck.zzu((zzek) obj, zzevVar);
        }
        int iZzw = zzck.zzw(i2);
        int iZza = ((zzdq) obj).zza();
        return iZzw + zzck.zzw(iZza) + iZza;
    }

    static int zzi(List list) {
        int iZzw;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzde) {
            zzde zzdeVar = (zzde) list;
            iZzw = 0;
            while (i < size) {
                int iZze = zzdeVar.zze(i);
                iZzw += zzck.zzw((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzw = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzw += zzck.zzw((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzw;
    }

    static int zzj(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdzVar = (zzdz) list;
            iZzx = 0;
            while (i < size) {
                long jZze = zzdzVar.zze(i);
                iZzx += zzck.zzx((jZze >> 63) ^ (jZze + jZze));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzx += zzck.zzx((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzx;
    }

    static int zzk(List list) {
        int iZzw;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzde) {
            zzde zzdeVar = (zzde) list;
            iZzw = 0;
            while (i < size) {
                iZzw += zzck.zzw(zzdeVar.zze(i));
                i++;
            }
        } else {
            iZzw = 0;
            while (i < size) {
                iZzw += zzck.zzw(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzw;
    }

    static int zzl(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdzVar = (zzdz) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(zzdzVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzck.zzx(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzx;
    }

    public static zzfm zzm() {
        return zzc;
    }

    public static zzfm zzn() {
        return zzd;
    }

    static Object zzo(Object obj, int i, int i2, Object obj2, zzfm zzfmVar) {
        if (obj2 == null) {
            obj2 = zzfmVar.zzc(obj);
        }
        zzfmVar.zzf(obj2, i, i2);
        return obj2;
    }

    static void zzp(zzfm zzfmVar, Object obj, Object obj2) {
        zzfmVar.zzh(obj, zzfmVar.zze(zzfmVar.zzd(obj), zzfmVar.zzd(obj2)));
    }

    public static void zzq(Class cls) {
        Class cls2;
        if (!zzdd.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzr(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzc(i, list, z);
    }

    public static void zzs(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzg(i, list, z);
    }

    public static void zzt(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzj(i, list, z);
    }

    public static void zzu(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzl(i, list, z);
    }

    public static void zzv(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzn(i, list, z);
    }

    public static void zzw(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzp(i, list, z);
    }

    public static void zzx(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzs(i, list, z);
    }

    public static void zzy(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzu(i, list, z);
    }

    public static void zzz(int i, List list, zzge zzgeVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgeVar.zzx(i, list, z);
    }
}
