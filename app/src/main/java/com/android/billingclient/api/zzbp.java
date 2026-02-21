package com.android.billingclient.api;

import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbp {
    private final List zza;
    private final BillingResult zzb;

    zzbp(BillingResult billingResult, List list) {
        this.zza = list;
        this.zzb = billingResult;
    }

    final BillingResult zza() {
        return this.zzb;
    }

    final List zzb() {
        return this.zza;
    }
}
