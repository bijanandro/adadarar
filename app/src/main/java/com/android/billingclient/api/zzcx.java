package com.android.billingclient.api;

import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcx {
    private final List zza;
    private final BillingResult zzb;

    public zzcx(BillingResult billingResult, List list) {
        this.zza = list;
        this.zzb = billingResult;
    }

    public final BillingResult zza() {
        return this.zzb;
    }

    public final List zzb() {
        return this.zza;
    }
}
