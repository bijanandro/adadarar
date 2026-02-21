package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzk {
    private final Context zza;
    private final PurchasesUpdatedListener zzb;
    private final AlternativeBillingListener zzc;
    private final UserChoiceBillingListener zzd;
    private final zzby zze;
    private final zzj zzf = new zzj(this, true);
    private final zzj zzg = new zzj(this, false);
    private boolean zzh;

    zzk(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzcg zzcgVar, AlternativeBillingListener alternativeBillingListener, UserChoiceBillingListener userChoiceBillingListener, zzby zzbyVar) {
        this.zza = context;
        this.zzb = purchasesUpdatedListener;
        this.zzc = alternativeBillingListener;
        this.zzd = userChoiceBillingListener;
        this.zze = zzbyVar;
    }

    final PurchasesUpdatedListener zzd() {
        return this.zzb;
    }

    final void zzf() {
        this.zzf.zzb(this.zza);
        this.zzg.zzb(this.zza);
    }

    final void zzg(boolean z) {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter intentFilter2 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intentFilter2.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzh = z;
        this.zzg.zza(this.zza, intentFilter2);
        if (this.zzh) {
            zzdi.zza(this.zza);
        }
        this.zzf.zza(this.zza, intentFilter);
    }
}
