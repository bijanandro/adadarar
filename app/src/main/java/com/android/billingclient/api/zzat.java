package com.android.billingclient.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzgq;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzat extends ResultReceiver {
    final /* synthetic */ AlternativeBillingOnlyInformationDialogListener zza;
    final /* synthetic */ BillingClientImpl zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzat(BillingClientImpl billingClientImpl, Handler handler, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        super(handler);
        this.zza = alternativeBillingOnlyInformationDialogListener;
        this.zzb = billingClientImpl;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        BillingResult.Builder builderNewBuilder = BillingResult.newBuilder();
        builderNewBuilder.setResponseCode(i);
        if (i != 0) {
            if (bundle == null) {
                this.zzb.zzf.zza(zzbx.zzb(73, 16, zzca.zzj));
                this.zza.onAlternativeBillingOnlyInformationDialogResponse(zzca.zzj);
                return;
            } else {
                builderNewBuilder.setDebugMessage(com.google.android.gms.internal.play_billing.zzb.zzg(bundle, "BillingClient"));
                int i2 = bundle.getInt("INTERNAL_LOG_ERROR_REASON");
                this.zzb.zzf.zza(zzbx.zzc(i2 != 0 ? zzgq.zza(i2) : 23, 16, builderNewBuilder.build(), bundle.getString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS")));
            }
        }
        this.zza.onAlternativeBillingOnlyInformationDialogResponse(builderNewBuilder.build());
    }
}
