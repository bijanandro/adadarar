package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcl {
    private boolean zza;

    private zzcl() {
    }

    /* synthetic */ zzcl(zzck zzckVar) {
    }

    public final zzcl zza() {
        this.zza = true;
        return this;
    }

    public final zzcn zzb() {
        if (this.zza) {
            return new zzcn(true, false, null);
        }
        throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
    }
}
