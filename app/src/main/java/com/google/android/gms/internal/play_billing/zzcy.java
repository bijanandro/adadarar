package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzcy implements zzei {
    private static final zzcy zza = new zzcy();

    private zzcy() {
    }

    public static zzcy zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final zzeh zzb(Class cls) {
        if (!zzdd.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzeh) zzdd.zzi(cls.asSubclass(zzdd.class)).zzy(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzei
    public final boolean zzc(Class cls) {
        return zzdd.class.isAssignableFrom(cls);
    }
}
