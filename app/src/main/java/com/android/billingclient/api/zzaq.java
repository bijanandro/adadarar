package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzaq implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ PurchasesResponseListener zzb;
    final /* synthetic */ BillingClientImpl zzc;

    zzaq(BillingClientImpl billingClientImpl, String str, PurchasesResponseListener purchasesResponseListener) {
        this.zza = str;
        this.zzb = purchasesResponseListener;
        this.zzc = billingClientImpl;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzcx zzcxVarZzaf = BillingClientImpl.zzaf(this.zzc, this.zza, 9);
        if (zzcxVarZzaf.zzb() != null) {
            this.zzb.onQueryPurchasesResponse(zzcxVarZzaf.zza(), zzcxVarZzaf.zzb());
            return null;
        }
        this.zzb.onQueryPurchasesResponse(zzcxVarZzaf.zza(), com.google.android.gms.internal.play_billing.zzai.zzk());
        return null;
    }
}
