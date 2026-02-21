package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzec implements zzew {
    private static final zzei zza = new zzea();
    private final zzei zzb;

    public zzec() {
        zzei zzeiVar;
        zzei[] zzeiVarArr = new zzei[2];
        zzeiVarArr[0] = zzcy.zza();
        try {
            zzeiVar = (zzei) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            zzeiVar = zza;
        }
        zzeiVarArr[1] = zzeiVar;
        zzeb zzebVar = new zzeb(zzeiVarArr);
        byte[] bArr = zzdl.zzd;
        this.zzb = zzebVar;
    }

    private static boolean zzb(zzeh zzehVar) {
        return zzehVar.zzc() + (-1) != 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzew
    public final zzev zza(Class cls) {
        zzex.zzq(cls);
        zzeh zzehVarZzb = this.zzb.zzb(cls);
        return zzehVarZzb.zzb() ? zzdd.class.isAssignableFrom(cls) ? zzeo.zzc(zzex.zzn(), zzcs.zzb(), zzehVarZzb.zza()) : zzeo.zzc(zzex.zzm(), zzcs.zza(), zzehVarZzb.zza()) : zzdd.class.isAssignableFrom(cls) ? zzb(zzehVarZzb) ? zzen.zzl(cls, zzehVarZzb, zzeq.zzb(), zzdy.zzd(), zzex.zzn(), zzcs.zzb(), zzeg.zzb()) : zzen.zzl(cls, zzehVarZzb, zzeq.zzb(), zzdy.zzd(), zzex.zzn(), null, zzeg.zzb()) : zzb(zzehVarZzb) ? zzen.zzl(cls, zzehVarZzb, zzeq.zza(), zzdy.zzc(), zzex.zzm(), zzcs.zza(), zzeg.zza()) : zzen.zzl(cls, zzehVarZzb, zzeq.zza(), zzdy.zzc(), zzex.zzm(), null, zzeg.zza());
    }
}
