package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzeb implements zzei {
    private final zzei[] zza;

    zzeb(zzei... zzeiVarArr) {
        this.zza = zzeiVarArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final zzeh zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzei zzeiVar = this.zza[i];
            if (zzeiVar.zzc(cls)) {
                return zzeiVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
