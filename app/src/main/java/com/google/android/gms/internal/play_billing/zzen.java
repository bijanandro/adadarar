package com.google.android.gms.internal.play_billing;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzen<T> implements zzev<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzfw.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzek zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzdy zzl;
    private final zzfm zzm;
    private final zzcq zzn;
    private final zzep zzo;
    private final zzef zzp;

    private zzen(int[] iArr, Object[] objArr, int i, int i2, zzek zzekVar, int i3, boolean z, int[] iArr2, int i4, int i5, zzep zzepVar, zzdy zzdyVar, zzfm zzfmVar, zzcq zzcqVar, zzef zzefVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        boolean z2 = false;
        if (zzcqVar != null && zzcqVar.zzc(zzekVar)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzi = iArr2;
        this.zzj = i4;
        this.zzk = i5;
        this.zzo = zzepVar;
        this.zzl = zzdyVar;
        this.zzm = zzfmVar;
        this.zzn = zzcqVar;
        this.zzg = zzekVar;
        this.zzp = zzefVar;
    }

    private static void zzA(Object obj) {
        if (!zzL(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzB(Object obj, Object obj2, int i) {
        if (zzI(obj2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzev zzevVarZzv = zzv(i);
            if (!zzI(obj, i)) {
                if (zzL(object)) {
                    Object objZze = zzevVarZzv.zze();
                    zzevVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzD(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzevVarZzv.zze();
                zzevVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzevVarZzv.zzg(object2, object);
        }
    }

    private final void zzC(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzM(obj2, i2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzev zzevVarZzv = zzv(i);
            if (!zzM(obj, i2, i)) {
                if (zzL(object)) {
                    Object objZze = zzevVarZzv.zze();
                    zzevVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzE(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzevVarZzv.zze();
                zzevVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzevVarZzv.zzg(object2, object);
        }
    }

    private final void zzD(Object obj, int i) {
        int iZzp = zzp(i);
        long j = 1048575 & iZzp;
        if (j == 1048575) {
            return;
        }
        zzfw.zzq(obj, j, (1 << (iZzp >>> 20)) | zzfw.zzc(obj, j));
    }

    private final void zzE(Object obj, int i, int i2) {
        zzfw.zzq(obj, zzp(i2) & 1048575, i);
    }

    private final void zzF(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzs(i) & 1048575, obj2);
        zzD(obj, i);
    }

    private final void zzG(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzs(i2) & 1048575, obj2);
        zzE(obj, i, i2);
    }

    private final boolean zzH(Object obj, Object obj2, int i) {
        return zzI(obj, i) == zzI(obj2, i);
    }

    private final boolean zzI(Object obj, int i) {
        int iZzp = zzp(i);
        long j = iZzp & 1048575;
        if (j != 1048575) {
            return (zzfw.zzc(obj, j) & (1 << (iZzp >>> 20))) != 0;
        }
        int iZzs = zzs(i);
        long j2 = iZzs & 1048575;
        switch (zzr(iZzs)) {
            case 0:
                return Double.doubleToRawLongBits(zzfw.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzfw.zzb(obj, j2)) != 0;
            case 2:
                return zzfw.zzd(obj, j2) != 0;
            case 3:
                return zzfw.zzd(obj, j2) != 0;
            case 4:
                return zzfw.zzc(obj, j2) != 0;
            case 5:
                return zzfw.zzd(obj, j2) != 0;
            case 6:
                return zzfw.zzc(obj, j2) != 0;
            case 7:
                return zzfw.zzw(obj, j2);
            case 8:
                Object objZzf = zzfw.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzcc) {
                    return !zzcc.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzfw.zzf(obj, j2) != null;
            case 10:
                return !zzcc.zzb.equals(zzfw.zzf(obj, j2));
            case 11:
                return zzfw.zzc(obj, j2) != 0;
            case 12:
                return zzfw.zzc(obj, j2) != 0;
            case 13:
                return zzfw.zzc(obj, j2) != 0;
            case 14:
                return zzfw.zzd(obj, j2) != 0;
            case 15:
                return zzfw.zzc(obj, j2) != 0;
            case 16:
                return zzfw.zzd(obj, j2) != 0;
            case 17:
                return zzfw.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzJ(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzI(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzK(Object obj, int i, zzev zzevVar) {
        return zzevVar.zzk(zzfw.zzf(obj, i & 1048575));
    }

    private static boolean zzL(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzdd) {
            return ((zzdd) obj).zzx();
        }
        return true;
    }

    private final boolean zzM(Object obj, int i, int i2) {
        return zzfw.zzc(obj, (long) (zzp(i2) & 1048575)) == i;
    }

    private static boolean zzN(Object obj, long j) {
        return ((Boolean) zzfw.zzf(obj, j)).booleanValue();
    }

    private static final void zzO(int i, Object obj, zzge zzgeVar) throws IOException {
        if (obj instanceof String) {
            zzgeVar.zzF(i, (String) obj);
        } else {
            zzgeVar.zzd(i, (zzcc) obj);
        }
    }

    static zzfn zzd(Object obj) {
        zzdd zzddVar = (zzdd) obj;
        zzfn zzfnVar = zzddVar.zzc;
        if (zzfnVar != zzfn.zzc()) {
            return zzfnVar;
        }
        zzfn zzfnVarZzf = zzfn.zzf();
        zzddVar.zzc = zzfnVarZzf;
        return zzfnVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0282  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.internal.play_billing.zzen zzl(java.lang.Class r33, com.google.android.gms.internal.play_billing.zzeh r34, com.google.android.gms.internal.play_billing.zzep r35, com.google.android.gms.internal.play_billing.zzdy r36, com.google.android.gms.internal.play_billing.zzfm r37, com.google.android.gms.internal.play_billing.zzcq r38, com.google.android.gms.internal.play_billing.zzef r39) {
        /*
            Method dump skipped, instruction units count: 1030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzen.zzl(java.lang.Class, com.google.android.gms.internal.play_billing.zzeh, com.google.android.gms.internal.play_billing.zzep, com.google.android.gms.internal.play_billing.zzdy, com.google.android.gms.internal.play_billing.zzfm, com.google.android.gms.internal.play_billing.zzcq, com.google.android.gms.internal.play_billing.zzef):com.google.android.gms.internal.play_billing.zzen");
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zzfw.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zzfw.zzf(obj, j)).floatValue();
    }

    private static int zzo(Object obj, long j) {
        return ((Integer) zzfw.zzf(obj, j)).intValue();
    }

    private final int zzp(int i) {
        return this.zzc[i + 2];
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzr(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzs(int i) {
        return this.zzc[i + 1];
    }

    private static long zzt(Object obj, long j) {
        return ((Long) zzfw.zzf(obj, j)).longValue();
    }

    private final zzdh zzu(int i) {
        int i2 = i / 3;
        return (zzdh) this.zzd[i2 + i2 + 1];
    }

    private final zzev zzv(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzev zzevVar = (zzev) objArr[i3];
        if (zzevVar != null) {
            return zzevVar;
        }
        zzev zzevVarZzb = zzes.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzevVarZzb;
        return zzevVarZzb;
    }

    private final Object zzw(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzx(Object obj, int i) {
        zzev zzevVarZzv = zzv(i);
        int iZzs = zzs(i) & 1048575;
        if (!zzI(obj, i)) {
            return zzevVarZzv.zze();
        }
        Object object = zzb.getObject(obj, iZzs);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzevVarZzv.zze();
        if (object != null) {
            zzevVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzy(Object obj, int i, int i2) {
        zzev zzevVarZzv = zzv(i2);
        if (!zzM(obj, i, i2)) {
            return zzevVarZzv.zze();
        }
        Object object = zzb.getObject(obj, zzs(i2) & 1048575);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzevVarZzv.zze();
        if (object != null) {
            zzevVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzz(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:207:0x054d  */
    /* JADX WARN: Type inference failed for: r0v108, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v109, types: [com.google.android.gms.internal.play_billing.zzds] */
    /* JADX WARN: Type inference failed for: r0v111, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v113, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v130 */
    /* JADX WARN: Type inference failed for: r0v178, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v248, types: [int] */
    /* JADX WARN: Type inference failed for: r0v256 */
    /* JADX WARN: Type inference failed for: r0v258 */
    /* JADX WARN: Type inference failed for: r0v259 */
    /* JADX WARN: Type inference failed for: r0v260 */
    /* JADX WARN: Type inference failed for: r0v261 */
    /* JADX WARN: Type inference failed for: r0v262 */
    /* JADX WARN: Type inference failed for: r0v263 */
    /* JADX WARN: Type inference failed for: r0v264 */
    /* JADX WARN: Type inference failed for: r0v265 */
    /* JADX WARN: Type inference failed for: r0v266 */
    /* JADX WARN: Type inference failed for: r0v267 */
    /* JADX WARN: Type inference failed for: r0v268 */
    /* JADX WARN: Type inference failed for: r0v269 */
    /* JADX WARN: Type inference failed for: r0v270 */
    /* JADX WARN: Type inference failed for: r0v271 */
    /* JADX WARN: Type inference failed for: r0v272 */
    /* JADX WARN: Type inference failed for: r0v273 */
    /* JADX WARN: Type inference failed for: r0v274 */
    /* JADX WARN: Type inference failed for: r0v275 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v112, types: [int] */
    /* JADX WARN: Type inference failed for: r1v115, types: [int] */
    /* JADX WARN: Type inference failed for: r1v154 */
    /* JADX WARN: Type inference failed for: r1v157 */
    /* JADX WARN: Type inference failed for: r1v158 */
    /* JADX WARN: Type inference failed for: r1v160 */
    /* JADX WARN: Type inference failed for: r1v161 */
    /* JADX WARN: Type inference failed for: r1v162 */
    /* JADX WARN: Type inference failed for: r1v72, types: [int] */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r2v26, types: [int] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32, types: [int] */
    /* JADX WARN: Type inference failed for: r2v36, types: [int] */
    /* JADX WARN: Type inference failed for: r2v40, types: [int] */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49, types: [int] */
    /* JADX WARN: Type inference failed for: r2v83 */
    /* JADX WARN: Type inference failed for: r2v84 */
    /* JADX WARN: Type inference failed for: r2v85 */
    /* JADX WARN: Type inference failed for: r2v86 */
    /* JADX WARN: Type inference failed for: r2v87 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23, types: [int] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26, types: [int] */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v35, types: [int] */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v42, types: [int] */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v30, types: [int] */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v37, types: [int] */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v58 */
    /* JADX WARN: Type inference failed for: r4v59 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int] */
    @Override // com.google.android.gms.internal.play_billing.zzev
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 2098
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzen.zza(java.lang.Object):int");
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final int zzb(Object obj) {
        int i;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzc.length; i4 += 3) {
            int iZzs = zzs(i4);
            int[] iArr = this.zzc;
            int i5 = 1048575 & iZzs;
            int iZzr = zzr(iZzs);
            int i6 = iArr[i4];
            long j = i5;
            int iHashCode = 37;
            switch (iZzr) {
                case 0:
                    i = i3 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzfw.zza(obj, j));
                    byte[] bArr = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 1:
                    i = i3 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzfw.zzb(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 2:
                    i = i3 * 53;
                    jDoubleToLongBits = zzfw.zzd(obj, j);
                    byte[] bArr2 = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 3:
                    i = i3 * 53;
                    jDoubleToLongBits = zzfw.zzd(obj, j);
                    byte[] bArr3 = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 4:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 5:
                    i = i3 * 53;
                    jDoubleToLongBits = zzfw.zzd(obj, j);
                    byte[] bArr4 = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 6:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 7:
                    i = i3 * 53;
                    iFloatToIntBits = zzdl.zza(zzfw.zzw(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 8:
                    i = i3 * 53;
                    iFloatToIntBits = ((String) zzfw.zzf(obj, j)).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 9:
                    i2 = i3 * 53;
                    Object objZzf = zzfw.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 10:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 11:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 12:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 13:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 14:
                    i = i3 * 53;
                    jDoubleToLongBits = zzfw.zzd(obj, j);
                    byte[] bArr5 = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 15:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 16:
                    i = i3 * 53;
                    jDoubleToLongBits = zzfw.zzd(obj, j);
                    byte[] bArr6 = zzdl.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 17:
                    i2 = i3 * 53;
                    Object objZzf2 = zzfw.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 50:
                    i = i3 * 53;
                    iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j));
                        byte[] bArr7 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 52:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 53:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr8 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 54:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr9 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 55:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 56:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr10 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 57:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 58:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzdl.zza(zzN(obj, j));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 59:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = ((String) zzfw.zzf(obj, j)).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 62:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 64:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 65:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr11 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 66:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 67:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr12 = zzdl.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 68:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzfw.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
            }
        }
        int iHashCode2 = (i3 * 53) + this.zzm.zzd(obj).hashCode();
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzn.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:565:0x0cff, code lost:
    
        if (r6 == 1048575) goto L567;
     */
    /* JADX WARN: Code restructure failed: missing block: B:566:0x0d01, code lost:
    
        r13.putInt(r7, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:567:0x0d05, code lost:
    
        r3 = r0.zzj;
     */
    /* JADX WARN: Code restructure failed: missing block: B:569:0x0d09, code lost:
    
        if (r3 >= r0.zzk) goto L684;
     */
    /* JADX WARN: Code restructure failed: missing block: B:570:0x0d0b, code lost:
    
        r5 = r0.zzi;
        r6 = r0.zzc;
        r5 = r5[r3];
        r6 = r6[r5];
        r6 = com.google.android.gms.internal.play_billing.zzfw.zzf(r7, r0.zzs(r5) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:571:0x0d1d, code lost:
    
        if (r6 != null) goto L573;
     */
    /* JADX WARN: Code restructure failed: missing block: B:574:0x0d24, code lost:
    
        if (r0.zzu(r5) != null) goto L685;
     */
    /* JADX WARN: Code restructure failed: missing block: B:575:0x0d26, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:576:0x0d29, code lost:
    
        r6 = (com.google.android.gms.internal.play_billing.zzee) r6;
        r1 = (com.google.android.gms.internal.play_billing.zzed) r0.zzw(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:577:0x0d31, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:578:0x0d32, code lost:
    
        if (r8 != 0) goto L584;
     */
    /* JADX WARN: Code restructure failed: missing block: B:580:0x0d36, code lost:
    
        if (r1 != r37) goto L582;
     */
    /* JADX WARN: Code restructure failed: missing block: B:583:0x0d3d, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzdn.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:585:0x0d40, code lost:
    
        if (r1 > r37) goto L588;
     */
    /* JADX WARN: Code restructure failed: missing block: B:586:0x0d42, code lost:
    
        if (r4 != r8) goto L588;
     */
    /* JADX WARN: Code restructure failed: missing block: B:587:0x0d44, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:589:0x0d49, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzdn.zze();
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x09c7 A[PHI: r0 r7 r8 r9 r10 r11 r13
  0x09c7: PHI (r0v34 com.google.android.gms.internal.play_billing.zzen<T>) = 
  (r0v1 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v1 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v1 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v1 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v9 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v33 com.google.android.gms.internal.play_billing.zzen<T>)
  (r0v1 com.google.android.gms.internal.play_billing.zzen<T>)
 binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r7v30 int) = (r7v7 int), (r7v8 int), (r7v9 int), (r7v14 int), (r7v19 int), (r7v25 int), (r7v34 int) binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r8v76 int) = (r8v52 int), (r8v53 int), (r8v54 int), (r8v56 int), (r8v62 int), (r8v74 int), (r8v79 int) binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r9v61 int) = (r9v32 int), (r9v33 int), (r9v34 int), (r9v39 int), (r9v46 int), (r9v56 int), (r9v63 int) binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r10v74 int) = (r10v36 int), (r10v37 int), (r10v38 int), (r10v52 int), (r10v65 int), (r10v72 int), (r10v77 int) binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r11v36 sun.misc.Unsafe) = 
  (r11v10 sun.misc.Unsafe)
  (r11v11 sun.misc.Unsafe)
  (r11v12 sun.misc.Unsafe)
  (r11v14 sun.misc.Unsafe)
  (r11v22 sun.misc.Unsafe)
  (r11v31 sun.misc.Unsafe)
  (r11v38 sun.misc.Unsafe)
 binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]
  0x09c7: PHI (r13v54 com.google.android.gms.internal.play_billing.zzbp) = 
  (r13v36 com.google.android.gms.internal.play_billing.zzbp)
  (r13v37 com.google.android.gms.internal.play_billing.zzbp)
  (r13v38 com.google.android.gms.internal.play_billing.zzbp)
  (r13v43 com.google.android.gms.internal.play_billing.zzbp)
  (r13v49 com.google.android.gms.internal.play_billing.zzbp)
  (r13v52 com.google.android.gms.internal.play_billing.zzbp)
  (r13v56 com.google.android.gms.internal.play_billing.zzbp)
 binds: [B:443:0x0980, B:427:0x0928, B:411:0x08d7, B:336:0x077f, B:287:0x06c0, B:252:0x060d, B:184:0x0484] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0c79 A[PHI: r1 r4 r5 r6 r9 r20
  0x0c79: PHI (r1v191 int) = 
  (r1v167 int)
  (r1v168 int)
  (r1v169 int)
  (r1v170 int)
  (r1v171 int)
  (r1v172 int)
  (r1v175 int)
  (r1v184 int)
  (r1v192 int)
 binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]
  0x0c79: PHI (r4v88 int) = (r4v59 int), (r4v60 int), (r4v61 int), (r4v62 int), (r4v63 int), (r4v64 int), (r4v67 int), (r4v80 int), (r4v89 int) binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]
  0x0c79: PHI (r5v125 com.google.android.gms.internal.play_billing.zzbp) = 
  (r5v109 com.google.android.gms.internal.play_billing.zzbp)
  (r5v110 com.google.android.gms.internal.play_billing.zzbp)
  (r5v111 com.google.android.gms.internal.play_billing.zzbp)
  (r5v112 com.google.android.gms.internal.play_billing.zzbp)
  (r5v113 com.google.android.gms.internal.play_billing.zzbp)
  (r5v114 com.google.android.gms.internal.play_billing.zzbp)
  (r5v117 com.google.android.gms.internal.play_billing.zzbp)
  (r5v121 com.google.android.gms.internal.play_billing.zzbp)
  (r5v126 com.google.android.gms.internal.play_billing.zzbp)
 binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]
  0x0c79: PHI (r6v98 byte[]) = 
  (r6v78 byte[])
  (r6v79 byte[])
  (r6v80 byte[])
  (r6v81 byte[])
  (r6v82 byte[])
  (r6v83 byte[])
  (r6v86 byte[])
  (r6v91 byte[])
  (r6v99 byte[])
 binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]
  0x0c79: PHI (r9v92 int) = (r9v66 int), (r9v67 int), (r9v68 int), (r9v69 int), (r9v70 int), (r9v71 int), (r9v74 int), (r9v84 int), (r9v93 int) binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]
  0x0c79: PHI (r20v35 int) = 
  (r20v15 int)
  (r20v16 int)
  (r20v17 int)
  (r20v18 int)
  (r20v19 int)
  (r20v20 int)
  (r20v23 int)
  (r20v29 int)
  (r20v36 int)
 binds: [B:543:0x0c62, B:540:0x0c41, B:536:0x0c20, B:533:0x0c03, B:530:0x0be6, B:527:0x0bc8, B:525:0x0bbb, B:503:0x0b4d, B:470:0x0a38] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:550:0x0c9a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:554:0x0ca9  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0cd0  */
    /* JADX WARN: Removed duplicated region for block: B:627:0x09ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:630:0x0c7c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:633:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:671:0x09d8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:673:0x0c93 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0206  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.gms.internal.play_billing.zzbp r39) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 3546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzen.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.play_billing.zzbp):int");
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final Object zze() {
        return ((zzdd) this.zzg).zzj();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006d  */
    @Override // com.google.android.gms.internal.play_billing.zzev
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzf(java.lang.Object r8) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzen.zzf(java.lang.Object):void");
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final void zzg(Object obj, Object obj2) {
        zzA(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            int i2 = 1048575 & iZzs;
            int[] iArr = this.zzc;
            int iZzr = zzr(iZzs);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzr) {
                case 0:
                    if (zzI(obj2, i)) {
                        zzfw.zzo(obj, j, zzfw.zza(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 1:
                    if (zzI(obj2, i)) {
                        zzfw.zzp(obj, j, zzfw.zzb(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 2:
                    if (zzI(obj2, i)) {
                        zzfw.zzr(obj, j, zzfw.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 3:
                    if (zzI(obj2, i)) {
                        zzfw.zzr(obj, j, zzfw.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 4:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 5:
                    if (zzI(obj2, i)) {
                        zzfw.zzr(obj, j, zzfw.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 6:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 7:
                    if (zzI(obj2, i)) {
                        zzfw.zzm(obj, j, zzfw.zzw(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 8:
                    if (zzI(obj2, i)) {
                        zzfw.zzs(obj, j, zzfw.zzf(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 9:
                    zzB(obj, obj2, i);
                    break;
                case 10:
                    if (zzI(obj2, i)) {
                        zzfw.zzs(obj, j, zzfw.zzf(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 11:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 12:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 13:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 14:
                    if (zzI(obj2, i)) {
                        zzfw.zzr(obj, j, zzfw.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 15:
                    if (zzI(obj2, i)) {
                        zzfw.zzq(obj, j, zzfw.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 16:
                    if (zzI(obj2, i)) {
                        zzfw.zzr(obj, j, zzfw.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 17:
                    zzB(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    this.zzl.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i4 = zzex.zza;
                    zzfw.zzs(obj, j, zzef.zza(zzfw.zzf(obj, j), zzfw.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzM(obj2, i3, i)) {
                        zzfw.zzs(obj, j, zzfw.zzf(obj2, j));
                        zzE(obj, i3, i);
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    zzC(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzM(obj2, i3, i)) {
                        zzfw.zzs(obj, j, zzfw.zzf(obj2, j));
                        zzE(obj, i3, i);
                    }
                    break;
                case 68:
                    zzC(obj, obj2, i);
                    break;
            }
        }
        zzex.zzp(this.zzm, obj, obj2);
        if (this.zzh) {
            this.zzn.zza(obj2);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzbp zzbpVar) throws IOException {
        zzc(obj, bArr, i, i2, 0, zzbpVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final void zzi(Object obj, zzge zzgeVar) throws IOException {
        int i;
        int i2;
        int i3;
        if (this.zzh) {
            this.zzn.zza(obj);
            throw null;
        }
        int[] iArr = this.zzc;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        while (i7 < iArr.length) {
            int iZzs = zzs(i7);
            int[] iArr2 = this.zzc;
            int iZzr = zzr(iZzs);
            int i8 = iArr2[i7];
            if (iZzr <= 17) {
                int i9 = iArr2[i7 + 2];
                int i10 = i9 & i4;
                if (i10 != i5) {
                    i6 = i10 == i4 ? 0 : unsafe.getInt(obj, i10);
                    i5 = i10;
                }
                i = i5;
                i2 = i6;
                i3 = 1 << (i9 >>> 20);
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
            }
            long j = iZzs & i4;
            switch (iZzr) {
                case 0:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzf(i8, zzfw.zza(obj, j));
                    }
                    break;
                case 1:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzo(i8, zzfw.zzb(obj, j));
                    }
                    break;
                case 2:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzt(i8, unsafe.getLong(obj, j));
                    }
                    break;
                case 3:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzJ(i8, unsafe.getLong(obj, j));
                    }
                    break;
                case 4:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzr(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 5:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzm(i8, unsafe.getLong(obj, j));
                    }
                    break;
                case 6:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzk(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 7:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzb(i8, zzfw.zzw(obj, j));
                    }
                    break;
                case 8:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzO(i8, unsafe.getObject(obj, j), zzgeVar);
                    }
                    break;
                case 9:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzv(i8, unsafe.getObject(obj, j), zzv(i7));
                    }
                    break;
                case 10:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzd(i8, (zzcc) unsafe.getObject(obj, j));
                    }
                    break;
                case 11:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzH(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 12:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzi(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 13:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzw(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 14:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzy(i8, unsafe.getLong(obj, j));
                    }
                    break;
                case 15:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzA(i8, unsafe.getInt(obj, j));
                    }
                    break;
                case 16:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzC(i8, unsafe.getLong(obj, j));
                    }
                    break;
                case 17:
                    if (zzJ(obj, i7, i, i2, i3)) {
                        zzgeVar.zzq(i8, unsafe.getObject(obj, j), zzv(i7));
                    }
                    break;
                case 18:
                    zzex.zzs(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 19:
                    zzex.zzw(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 20:
                    zzex.zzy(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 21:
                    zzex.zzE(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 22:
                    zzex.zzx(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 23:
                    zzex.zzv(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 24:
                    zzex.zzu(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 25:
                    zzex.zzr(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 26:
                    int i11 = this.zzc[i7];
                    List list = (List) unsafe.getObject(obj, j);
                    int i12 = zzex.zza;
                    if (list != null && !list.isEmpty()) {
                        zzgeVar.zzG(i11, list);
                    }
                    break;
                case 27:
                    int i13 = this.zzc[i7];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zzev zzevVarZzv = zzv(i7);
                    int i14 = zzex.zza;
                    if (list2 != null && !list2.isEmpty()) {
                        for (int i15 = 0; i15 < list2.size(); i15++) {
                            ((zzcl) zzgeVar).zzv(i13, list2.get(i15), zzevVarZzv);
                        }
                    }
                    break;
                case 28:
                    int i16 = this.zzc[i7];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i17 = zzex.zza;
                    if (list3 != null && !list3.isEmpty()) {
                        zzgeVar.zze(i16, list3);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    zzex.zzD(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 30:
                    zzex.zzt(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    zzex.zzz(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 32:
                    zzex.zzA(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 33:
                    zzex.zzB(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 34:
                    zzex.zzC(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, false);
                    break;
                case 35:
                    zzex.zzs(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 36:
                    zzex.zzw(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 37:
                    zzex.zzy(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 38:
                    zzex.zzE(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 39:
                    zzex.zzx(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 40:
                    zzex.zzv(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 41:
                    zzex.zzu(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 42:
                    zzex.zzr(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 43:
                    zzex.zzD(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 44:
                    zzex.zzt(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 45:
                    zzex.zzz(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 46:
                    zzex.zzA(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case 47:
                    zzex.zzB(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzex.zzC(this.zzc[i7], (List) unsafe.getObject(obj, j), zzgeVar, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    int i18 = this.zzc[i7];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zzev zzevVarZzv2 = zzv(i7);
                    int i19 = zzex.zza;
                    if (list4 != null && !list4.isEmpty()) {
                        for (int i20 = 0; i20 < list4.size(); i20++) {
                            ((zzcl) zzgeVar).zzq(i18, list4.get(i20), zzevVarZzv2);
                        }
                    }
                    break;
                case 50:
                    if (unsafe.getObject(obj, j) != null) {
                        throw null;
                    }
                    break;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzf(i8, zzm(obj, j));
                    }
                    break;
                case 52:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzo(i8, zzn(obj, j));
                    }
                    break;
                case 53:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzt(i8, zzt(obj, j));
                    }
                    break;
                case 54:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzJ(i8, zzt(obj, j));
                    }
                    break;
                case 55:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzr(i8, zzo(obj, j));
                    }
                    break;
                case 56:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzm(i8, zzt(obj, j));
                    }
                    break;
                case 57:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzk(i8, zzo(obj, j));
                    }
                    break;
                case 58:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzb(i8, zzN(obj, j));
                    }
                    break;
                case 59:
                    if (zzM(obj, i8, i7)) {
                        zzO(i8, unsafe.getObject(obj, j), zzgeVar);
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzv(i8, unsafe.getObject(obj, j), zzv(i7));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzd(i8, (zzcc) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzH(i8, zzo(obj, j));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzi(i8, zzo(obj, j));
                    }
                    break;
                case 64:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzw(i8, zzo(obj, j));
                    }
                    break;
                case 65:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzy(i8, zzt(obj, j));
                    }
                    break;
                case 66:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzA(i8, zzo(obj, j));
                    }
                    break;
                case 67:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzC(i8, zzt(obj, j));
                    }
                    break;
                case 68:
                    if (zzM(obj, i8, i7)) {
                        zzgeVar.zzq(i8, unsafe.getObject(obj, j), zzv(i7));
                    }
                    break;
            }
            i7 += 3;
            i5 = i;
            i6 = i2;
            i4 = 1048575;
        }
        zzfm zzfmVar = this.zzm;
        zzfmVar.zzi(zzfmVar.zzd(obj), zzgeVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzF;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            long j = iZzs & 1048575;
            switch (zzr(iZzs)) {
                case 0:
                    if (!zzH(obj, obj2, i) || Double.doubleToLongBits(zzfw.zza(obj, j)) != Double.doubleToLongBits(zzfw.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzH(obj, obj2, i) || Float.floatToIntBits(zzfw.zzb(obj, j)) != Float.floatToIntBits(zzfw.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzH(obj, obj2, i) || zzfw.zzd(obj, j) != zzfw.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzH(obj, obj2, i) || zzfw.zzd(obj, j) != zzfw.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzH(obj, obj2, i) || zzfw.zzd(obj, j) != zzfw.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzH(obj, obj2, i) || zzfw.zzw(obj, j) != zzfw.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzH(obj, obj2, i) || !zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzH(obj, obj2, i) || !zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzH(obj, obj2, i) || !zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzH(obj, obj2, i) || zzfw.zzd(obj, j) != zzfw.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzH(obj, obj2, i) || zzfw.zzc(obj, j) != zzfw.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzH(obj, obj2, i) || zzfw.zzd(obj, j) != zzfw.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzH(obj, obj2, i) || !zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zZzF = zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j));
                    break;
                case 50:
                    zZzF = zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzp = zzp(i) & 1048575;
                    if (zzfw.zzc(obj, jZzp) != zzfw.zzc(obj2, jZzp) || !zzex.zzF(zzfw.zzf(obj, j), zzfw.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zZzF) {
                return false;
            }
        }
        if (!this.zzm.zzd(obj).equals(this.zzm.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzn.zza(obj);
        this.zzn.zza(obj2);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    @Override // com.google.android.gms.internal.play_billing.zzev
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzk(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzen.zzk(java.lang.Object):boolean");
    }
}
