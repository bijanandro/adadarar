package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzgh;
import com.google.android.gms.internal.play_billing.zzgl;
import com.google.android.gms.internal.play_billing.zzgv;
import com.google.android.gms.internal.play_billing.zzhs;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
interface zzby {
    public static final com.google.android.gms.internal.play_billing.zzal zza = com.google.android.gms.internal.play_billing.zzal.zzc("com.android.vending.billing.PURCHASES_UPDATED", zzgv.PURCHASES_UPDATED_ACTION, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", zzgv.LOCAL_PURCHASES_UPDATED_ACTION, "com.android.vending.billing.ALTERNATIVE_BILLING", zzgv.ALTERNATIVE_BILLING_ACTION);

    void zza(zzgh zzghVar);

    void zzb(zzgl zzglVar);

    void zzc(byte[] bArr);

    void zzd(zzhs zzhsVar);

    void zze(int i, List list, boolean z, boolean z2);

    void zzf(int i, List list, List list2, BillingResult billingResult, boolean z, boolean z2);
}
