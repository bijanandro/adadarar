package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzes {
    private static final zzes zza = new zzes();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzew zzb = new zzec();

    private zzes() {
    }

    public static zzes zza() {
        return zza;
    }

    public final zzev zzb(Class cls) {
        zzdl.zzc(cls, "messageType");
        zzev zzevVarZza = (zzev) this.zzc.get(cls);
        if (zzevVarZza == null) {
            zzevVarZza = this.zzb.zza(cls);
            zzdl.zzc(cls, "messageType");
            zzev zzevVar = (zzev) this.zzc.putIfAbsent(cls, zzevVarZza);
            if (zzevVar != null) {
                return zzevVar;
            }
        }
        return zzevVarZza;
    }
}
