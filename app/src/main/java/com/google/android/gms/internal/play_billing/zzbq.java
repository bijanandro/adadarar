package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbq {
    static int zza(byte[] bArr, int i, zzbp zzbpVar) throws zzdn {
        int iZzh = zzh(bArr, i, zzbpVar);
        int i2 = zzbpVar.zza;
        if (i2 < 0) {
            throw zzdn.zzd();
        }
        if (i2 > bArr.length - iZzh) {
            throw zzdn.zzg();
        }
        if (i2 == 0) {
            zzbpVar.zzc = zzcc.zzb;
            return iZzh;
        }
        zzbpVar.zzc = zzcc.zzl(bArr, iZzh, i2);
        return iZzh + i2;
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = bArr[i] & UByte.MAX_VALUE;
        int i3 = bArr[i + 1] & UByte.MAX_VALUE;
        int i4 = bArr[i + 2] & UByte.MAX_VALUE;
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    static int zzc(zzev zzevVar, byte[] bArr, int i, int i2, int i3, zzbp zzbpVar) throws IOException {
        Object objZze = zzevVar.zze();
        int iZzl = zzl(objZze, zzevVar, bArr, i, i2, i3, zzbpVar);
        zzevVar.zzf(objZze);
        zzbpVar.zzc = objZze;
        return iZzl;
    }

    static int zzd(zzev zzevVar, byte[] bArr, int i, int i2, zzbp zzbpVar) throws IOException {
        Object objZze = zzevVar.zze();
        int iZzm = zzm(objZze, zzevVar, bArr, i, i2, zzbpVar);
        zzevVar.zzf(objZze);
        zzbpVar.zzc = objZze;
        return iZzm;
    }

    static int zze(zzev zzevVar, int i, byte[] bArr, int i2, int i3, zzdk zzdkVar, zzbp zzbpVar) throws IOException {
        int iZzd = zzd(zzevVar, bArr, i2, i3, zzbpVar);
        zzdkVar.add(zzbpVar.zzc);
        while (iZzd < i3) {
            int iZzh = zzh(bArr, iZzd, zzbpVar);
            if (i != zzbpVar.zza) {
                break;
            }
            iZzd = zzd(zzevVar, bArr, iZzh, i3, zzbpVar);
            zzdkVar.add(zzbpVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzdk zzdkVar, zzbp zzbpVar) throws IOException {
        zzde zzdeVar = (zzde) zzdkVar;
        int iZzh = zzh(bArr, i, zzbpVar);
        int i2 = zzbpVar.zza + iZzh;
        while (iZzh < i2) {
            iZzh = zzh(bArr, iZzh, zzbpVar);
            zzdeVar.zzh(zzbpVar.zza);
        }
        if (iZzh == i2) {
            return iZzh;
        }
        throw zzdn.zzg();
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzfn zzfnVar, zzbp zzbpVar) throws zzdn {
        if ((i >>> 3) == 0) {
            throw zzdn.zzb();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzk = zzk(bArr, i2, zzbpVar);
            zzfnVar.zzj(i, Long.valueOf(zzbpVar.zzb));
            return iZzk;
        }
        if (i4 == 1) {
            zzfnVar.zzj(i, Long.valueOf(zzn(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzh = zzh(bArr, i2, zzbpVar);
            int i5 = zzbpVar.zza;
            if (i5 < 0) {
                throw zzdn.zzd();
            }
            if (i5 > bArr.length - iZzh) {
                throw zzdn.zzg();
            }
            if (i5 == 0) {
                zzfnVar.zzj(i, zzcc.zzb);
            } else {
                zzfnVar.zzj(i, zzcc.zzl(bArr, iZzh, i5));
            }
            return iZzh + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw zzdn.zzb();
            }
            zzfnVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzfn zzfnVarZzf = zzfn.zzf();
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzh2 = zzh(bArr, i2, zzbpVar);
            int i8 = zzbpVar.zza;
            i7 = i8;
            if (i8 == i6) {
                i2 = iZzh2;
                break;
            }
            int iZzg = zzg(i7, bArr, iZzh2, i3, zzfnVarZzf, zzbpVar);
            i7 = i8;
            i2 = iZzg;
        }
        if (i2 > i3 || i7 != i6) {
            throw zzdn.zze();
        }
        zzfnVar.zzj(i, zzfnVarZzf);
        return i2;
    }

    static int zzh(byte[] bArr, int i, zzbp zzbpVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzi(b, bArr, i2, zzbpVar);
        }
        zzbpVar.zza = b;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzbp zzbpVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzbpVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzbpVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzbpVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzbpVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzbpVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzdk zzdkVar, zzbp zzbpVar) {
        zzde zzdeVar = (zzde) zzdkVar;
        int iZzh = zzh(bArr, i2, zzbpVar);
        zzdeVar.zzh(zzbpVar.zza);
        while (iZzh < i3) {
            int iZzh2 = zzh(bArr, iZzh, zzbpVar);
            if (i != zzbpVar.zza) {
                break;
            }
            iZzh = zzh(bArr, iZzh2, zzbpVar);
            zzdeVar.zzh(zzbpVar.zza);
        }
        return iZzh;
    }

    static int zzk(byte[] bArr, int i, zzbp zzbpVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzbpVar.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & ByteCompanionObject.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & ByteCompanionObject.MAX_VALUE)) << i4;
            b = b2;
            i3 = i5;
        }
        zzbpVar.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzev zzevVar, byte[] bArr, int i, int i2, int i3, zzbp zzbpVar) throws IOException {
        int iZzc = ((zzen) zzevVar).zzc(obj, bArr, i, i2, i3, zzbpVar);
        zzbpVar.zzc = obj;
        return iZzc;
    }

    static int zzm(Object obj, zzev zzevVar, byte[] bArr, int i, int i2, zzbp zzbpVar) throws IOException {
        int iZzi = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzi = zzi(i3, bArr, iZzi, zzbpVar);
            i3 = zzbpVar.zza;
        }
        int i4 = iZzi;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzdn.zzg();
        }
        int i5 = i3 + i4;
        zzevVar.zzh(obj, bArr, i4, i5, zzbpVar);
        zzbpVar.zzc = obj;
        return i5;
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }
}
