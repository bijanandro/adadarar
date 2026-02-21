package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzcz;
import com.google.android.gms.internal.play_billing.zzdd;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdd<MessageType extends zzdd<MessageType, BuilderType>, BuilderType extends zzcz<MessageType, BuilderType>> extends zzbm<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzfn zzc = zzfn.zzc();

    private static zzdd zzA(zzdd zzddVar, byte[] bArr, int i, int i2, zzcp zzcpVar) throws zzdn {
        zzdd zzddVarZzj = zzddVar.zzj();
        try {
            zzev zzevVarZzb = zzes.zza().zzb(zzddVarZzj.getClass());
            zzevVarZzb.zzh(zzddVarZzj, bArr, 0, i2, new zzbp(zzcpVar));
            zzevVarZzb.zzf(zzddVarZzj);
            return zzddVarZzj;
        } catch (zzdn e) {
            e.zzf(zzddVarZzj);
            throw e;
        } catch (zzfl e2) {
            zzdn zzdnVarZza = e2.zza();
            zzdnVarZza.zzf(zzddVarZzj);
            throw zzdnVarZza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzdn) {
                throw ((zzdn) e3.getCause());
            }
            zzdn zzdnVar = new zzdn(e3);
            zzdnVar.zzf(zzddVarZzj);
            throw zzdnVar;
        } catch (IndexOutOfBoundsException unused) {
            zzdn zzdnVarZzg = zzdn.zzg();
            zzdnVarZzg.zzf(zzddVarZzj);
            throw zzdnVarZzg;
        }
    }

    static zzdd zzi(Class cls) {
        Map map = zzb;
        zzdd zzddVar = (zzdd) map.get(cls);
        if (zzddVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzddVar = (zzdd) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzddVar == null) {
            zzddVar = (zzdd) ((zzdd) zzfw.zze(cls)).zzy(6, null, null);
            if (zzddVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzddVar);
        }
        return zzddVar;
    }

    protected static zzdd zzk(zzdd zzddVar, byte[] bArr, zzcp zzcpVar) throws zzdn {
        zzdd zzddVarZzA = zzA(zzddVar, bArr, 0, bArr.length, zzcpVar);
        if (zzddVarZzA == null || zzddVarZzA.zzw()) {
            return zzddVarZzA;
        }
        zzdn zzdnVarZza = new zzfl(zzddVarZzA).zza();
        zzdnVarZza.zzf(zzddVarZzA);
        throw zzdnVarZza;
    }

    protected static zzdi zzl() {
        return zzde.zzf();
    }

    protected static zzdk zzm() {
        return zzet.zze();
    }

    protected static zzdk zzn(zzdk zzdkVar) {
        int size = zzdkVar.size();
        return zzdkVar.zzd(size == 0 ? 10 : size + size);
    }

    static Object zzp(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzq(zzek zzekVar, String str, Object[] objArr) {
        return new zzeu(zzekVar, str, objArr);
    }

    protected static void zzt(Class cls, zzdd zzddVar) {
        zzddVar.zzs();
        zzb.put(cls, zzddVar);
    }

    private final int zzz(zzev zzevVar) {
        return zzes.zza().zzb(getClass()).zza(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzes.zza().zzb(getClass()).zzj(this, (zzdd) obj);
    }

    public final int hashCode() {
        if (zzx()) {
            return zze();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZze = zze();
        this.zza = iZze;
        return iZze;
    }

    public final String toString() {
        return zzem.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.play_billing.zzbm
    final int zza(zzev zzevVar) {
        if (zzx()) {
            int iZza = zzevVar.zza(this);
            if (iZza >= 0) {
                return iZza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + iZza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZza2 = zzevVar.zza(this);
        if (iZza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | iZza2;
            return iZza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + iZza2);
    }

    final int zze() {
        return zzes.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzel
    public final /* synthetic */ zzek zzf() {
        return (zzdd) zzy(6, null, null);
    }

    protected final zzcz zzh() {
        return (zzcz) zzy(5, null, null);
    }

    final zzdd zzj() {
        return (zzdd) zzy(4, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzek
    public final /* synthetic */ zzej zzo() {
        return (zzcz) zzy(5, null, null);
    }

    protected final void zzr() {
        zzes.zza().zzb(getClass()).zzf(this);
        zzs();
    }

    final void zzs() {
        this.zzd &= Integer.MAX_VALUE;
    }

    final void zzu(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.play_billing.zzek
    public final void zzv(zzck zzckVar) throws IOException {
        zzes.zza().zzb(getClass()).zzi(this, zzcl.zza(zzckVar));
    }

    public final boolean zzw() {
        boolean zBooleanValue = Boolean.TRUE.booleanValue();
        byte bByteValue = ((Byte) zzy(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzk = zzes.zza().zzb(getClass()).zzk(this);
        if (!zBooleanValue) {
            return zZzk;
        }
        zzy(2, true != zZzk ? null : this, null);
        return zZzk;
    }

    final boolean zzx() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    protected abstract Object zzy(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.play_billing.zzek
    public final int zzg() {
        int iZzz;
        if (zzx()) {
            iZzz = zzz(null);
            if (iZzz < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + iZzz);
            }
        } else {
            iZzz = this.zzd & Integer.MAX_VALUE;
            if (iZzz == Integer.MAX_VALUE) {
                iZzz = zzz(null);
                if (iZzz < 0) {
                    throw new IllegalStateException("serialized size must be non-negative, was " + iZzz);
                }
                this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzz;
            }
        }
        return iZzz;
    }
}
