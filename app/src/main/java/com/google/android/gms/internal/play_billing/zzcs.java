package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzcs {
    private static final zzcq zza = new zzcr();
    private static final zzcq zzb;

    static {
        zzcq zzcqVar = null;
        try {
            zzcqVar = (zzcq) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zzb = zzcqVar;
    }

    static zzcq zza() {
        zzcq zzcqVar = zzb;
        if (zzcqVar != null) {
            return zzcqVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzcq zzb() {
        return zza;
    }
}
