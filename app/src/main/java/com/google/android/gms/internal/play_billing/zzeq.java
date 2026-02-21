package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzeq {
    private static final zzep zza;
    private static final zzep zzb;

    static {
        zzep zzepVar = null;
        try {
            zzepVar = (zzep) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzepVar;
        zzb = new zzep();
    }

    static zzep zza() {
        return zza;
    }

    static zzep zzb() {
        return zzb;
    }
}
