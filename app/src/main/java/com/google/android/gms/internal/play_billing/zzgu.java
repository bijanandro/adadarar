package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzgu implements zzdh {
    static final zzdh zza = new zzgu();

    private zzgu() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzdh
    public final boolean zza(int i) {
        zzgv zzgvVar = zzgv.BROADCAST_ACTION_UNSPECIFIED;
        return (i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : zzgv.ALTERNATIVE_BILLING_ACTION : zzgv.LOCAL_PURCHASES_UPDATED_ACTION : zzgv.PURCHASES_UPDATED_ACTION : zzgv.BROADCAST_ACTION_UNSPECIFIED) != null;
    }
}
