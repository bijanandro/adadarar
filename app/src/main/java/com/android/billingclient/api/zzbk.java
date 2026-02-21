package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbk extends com.google.android.gms.internal.play_billing.zzm {
    final AlternativeBillingOnlyAvailabilityListener zza;
    final zzby zzb;

    /* synthetic */ zzbk(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener, zzby zzbyVar, zzbj zzbjVar) {
        this.zza = alternativeBillingOnlyAvailabilityListener;
        this.zzb = zzbyVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzn
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zza(zzbx.zzb(67, 14, zzca.zzj));
            this.zza.onAlternativeBillingOnlyAvailabilityResponse(zzca.zzj);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing.zzb.zzb(bundle, "BillingClient");
        BillingResult billingResultZza = zzca.zza(iZzb, com.google.android.gms.internal.play_billing.zzb.zzg(bundle, "BillingClient"));
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "isAlternativeBillingOnlyAvailableAsync() failed. Response code: " + iZzb);
            this.zzb.zza(zzbx.zzb(23, 14, billingResultZza));
        }
        this.zza.onAlternativeBillingOnlyAvailabilityResponse(billingResultZza);
    }
}
