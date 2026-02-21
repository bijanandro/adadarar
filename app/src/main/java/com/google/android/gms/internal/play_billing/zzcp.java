package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcp {
    static final zzcp zza = new zzcp(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private static volatile zzcp zzd;
    private final Map zze;

    zzcp() {
        this.zze = new HashMap();
    }

    public final zzdb zzb(zzek zzekVar, int i) {
        return (zzdb) this.zze.get(new zzco(zzekVar, i));
    }

    zzcp(boolean z) {
        this.zze = Collections.emptyMap();
    }

    public static zzcp zza() {
        zzcp zzcpVar = zzd;
        if (zzcpVar != null) {
            return zzcpVar;
        }
        synchronized (zzcp.class) {
            zzcp zzcpVar2 = zzd;
            if (zzcpVar2 != null) {
                return zzcpVar2;
            }
            zzcp zzcpVarZzb = zzcx.zzb(zzcp.class);
            zzd = zzcpVarZzb;
            return zzcpVarZzb;
        }
    }
}
