package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzgh;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzj extends BroadcastReceiver {
    final /* synthetic */ zzk zza;
    private boolean zzb;
    private final boolean zzc;
    private boolean zzd;

    zzj(zzk zzkVar, boolean z) {
        this.zza = zzkVar;
        this.zzc = z;
    }

    private final void zzc(Bundle bundle, BillingResult billingResult, int i) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") == null) {
            this.zza.zze.zza(zzbx.zzb(23, i, billingResult));
            return;
        }
        try {
            this.zza.zze.zza(zzgh.zzB(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), com.google.android.gms.internal.play_billing.zzcp.zza()));
        } catch (Throwable unused) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "Bundle is null.");
            this.zza.zze.zza(zzbx.zzb(11, 1, zzca.zzj));
            zzk zzkVar = this.zza;
            if (zzkVar.zzb != null) {
                zzkVar.zzb.onPurchasesUpdated(zzca.zzj, null);
                return;
            }
            return;
        }
        BillingResult billingResultZze = com.google.android.gms.internal.play_billing.zzb.zze(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        int i = true != Objects.equals(extras.getString("INTENT_SOURCE"), "LAUNCH_BILLING_FLOW") ? 1 : 2;
        if (extras.getByteArray("BROADCAST_RECEIVER_LOGGING_PAYLOAD") != null) {
            try {
                this.zza.zze.zzc(extras.getByteArray("BROADCAST_RECEIVER_LOGGING_PAYLOAD"));
            } catch (Throwable unused) {
                com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "Failed parsing Api failure.");
            }
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED") || action.equals("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED")) {
            List<Purchase> listZzi = com.google.android.gms.internal.play_billing.zzb.zzi(extras);
            if (billingResultZze.getResponseCode() == 0) {
                this.zza.zze.zzb(zzbx.zzd(i));
            } else {
                zzc(extras, billingResultZze, i);
            }
            this.zza.zze.zzf(4, com.google.android.gms.internal.play_billing.zzai.zzl(zzbx.zza(action)), listZzi, billingResultZze, false, this.zzd);
            this.zza.zzb.onPurchasesUpdated(billingResultZze, listZzi);
            return;
        }
        if (action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
            this.zza.zze.zze(4, com.google.android.gms.internal.play_billing.zzai.zzl(zzbx.zza(action)), false, this.zzd);
            if (billingResultZze.getResponseCode() != 0) {
                zzc(extras, billingResultZze, i);
                this.zza.zzb.onPurchasesUpdated(billingResultZze, com.google.android.gms.internal.play_billing.zzai.zzk());
                return;
            }
            zzk zzkVar2 = this.zza;
            if (zzkVar2.zzc == null && zzkVar2.zzd == null) {
                com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "AlternativeBillingListener and UserChoiceBillingListener is null.");
                this.zza.zze.zza(zzbx.zzb(77, i, zzca.zzj));
                this.zza.zzb.onPurchasesUpdated(zzca.zzj, com.google.android.gms.internal.play_billing.zzai.zzk());
                return;
            }
            String string = extras.getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
            if (string == null) {
                com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "Couldn't find alternative billing user choice data in bundle.");
                this.zza.zze.zza(zzbx.zzb(16, i, zzca.zzj));
                this.zza.zzb.onPurchasesUpdated(zzca.zzj, com.google.android.gms.internal.play_billing.zzai.zzk());
                return;
            }
            try {
                if (this.zza.zzd != null) {
                    this.zza.zzd.userSelectedAlternativeBilling(new UserChoiceDetails(string));
                } else {
                    this.zza.zzc.userSelectedAlternativeBilling(new AlternativeChoiceDetails(string));
                }
                this.zza.zze.zzb(zzbx.zzd(i));
            } catch (JSONException unused2) {
                com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", String.format("Error when parsing invalid user choice data: [%s]", string));
                this.zza.zze.zza(zzbx.zzb(17, i, zzca.zzj));
                this.zza.zzb.onPurchasesUpdated(zzca.zzj, com.google.android.gms.internal.play_billing.zzai.zzk());
            }
        }
    }

    public final synchronized void zza(Context context, IntentFilter intentFilter) {
        if (this.zzb) {
            return;
        }
        zzk zzkVar = this.zza;
        this.zzd = zzkVar.zzh;
        zzby zzbyVar = zzkVar.zze;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < intentFilter.countActions(); i++) {
            arrayList.add(zzbx.zza(intentFilter.getAction(i)));
        }
        zzbyVar.zze(2, arrayList, false, this.zzd);
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this, intentFilter, true != this.zzc ? 4 : 2);
        } else {
            context.registerReceiver(this, intentFilter);
        }
        this.zzb = true;
    }

    public final synchronized void zzb(Context context) {
        if (!this.zzb) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingBroadcastManager", "Receiver is not registered.");
        } else {
            context.unregisterReceiver(this);
            this.zzb = false;
        }
    }
}
