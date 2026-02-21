package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbc extends com.google.android.gms.internal.play_billing.zze {
    final ExternalOfferReportingDetailsListener zza;
    final zzby zzb;

    /* synthetic */ zzbc(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, zzby zzbyVar, zzbb zzbbVar) {
        this.zza = externalOfferReportingDetailsListener;
        this.zzb = zzbyVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzf
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zza(zzbx.zzb(95, 24, zzca.zzj));
            this.zza.onExternalOfferReportingDetailsResponse(zzca.zzj, null);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing.zzb.zzb(bundle, "BillingClient");
        BillingResult billingResultZza = zzca.zza(iZzb, com.google.android.gms.internal.play_billing.zzb.zzg(bundle, "BillingClient"));
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "createExternalOfferReportingDetailsAsync() failed. Response code: " + iZzb);
            this.zzb.zza(zzbx.zzb(23, 24, billingResultZza));
            this.zza.onExternalOfferReportingDetailsResponse(billingResultZza, null);
            return;
        }
        try {
            this.zza.onExternalOfferReportingDetailsResponse(billingResultZza, new ExternalOfferReportingDetails(bundle.getString("CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS")));
        } catch (JSONException e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Error when parsing invalid external offer reporting details. \n Exception: ", e);
            this.zzb.zza(zzbx.zzb(104, 24, zzca.zzj));
            this.zza.onExternalOfferReportingDetailsResponse(zzca.zzj, null);
        }
    }
}
