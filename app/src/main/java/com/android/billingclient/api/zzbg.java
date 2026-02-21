package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.billingclient.api.BillingResult;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzbg extends com.google.android.gms.internal.play_billing.zzi {
    final BillingConfigResponseListener zza;
    final zzby zzb;

    /* synthetic */ zzbg(BillingConfigResponseListener billingConfigResponseListener, zzby zzbyVar, zzbf zzbfVar) {
        this.zza = billingConfigResponseListener;
        this.zzb = zzbyVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzj
    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zza(zzbx.zzb(63, 13, zzca.zzj));
            this.zza.onBillingConfigResponse(zzca.zzj, null);
            return;
        }
        int iZzb = com.google.android.gms.internal.play_billing.zzb.zzb(bundle, "BillingClient");
        String strZzg = com.google.android.gms.internal.play_billing.zzb.zzg(bundle, "BillingClient");
        BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
        builderNewBuilder.setResponseCode(iZzb);
        builderNewBuilder.setDebugMessage(strZzg);
        if (iZzb != 0) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getBillingConfig() failed. Response code: " + iZzb);
            BillingResult billingResultBuild = builderNewBuilder.build();
            this.zzb.zza(zzbx.zzb(23, 13, billingResultBuild));
            this.zza.onBillingConfigResponse(billingResultBuild, null);
            return;
        }
        if (!bundle.containsKey("BILLING_CONFIG")) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getBillingConfig() returned a bundle with neither an error nor a billing config response");
            builderNewBuilder.setResponseCode(6);
            BillingResult billingResultBuild2 = builderNewBuilder.build();
            this.zzb.zza(zzbx.zzb(64, 13, billingResultBuild2));
            this.zza.onBillingConfigResponse(billingResultBuild2, null);
            return;
        }
        try {
            this.zza.onBillingConfigResponse(builderNewBuilder.build(), new BillingConfig(bundle.getString("BILLING_CONFIG")));
        } catch (JSONException e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got a JSON exception trying to decode BillingConfig. \n Exception: ", e);
            this.zzb.zza(zzbx.zzb(65, 13, zzca.zzj));
            this.zza.onBillingConfigResponse(zzca.zzj, null);
        }
    }
}
