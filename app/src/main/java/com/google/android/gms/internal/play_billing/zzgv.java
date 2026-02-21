package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public enum zzgv implements zzdf {
    BROADCAST_ACTION_UNSPECIFIED(0),
    PURCHASES_UPDATED_ACTION(1),
    LOCAL_PURCHASES_UPDATED_ACTION(2),
    ALTERNATIVE_BILLING_ACTION(3);

    private static final zzdg zze = new zzdg() { // from class: com.google.android.gms.internal.play_billing.zzgt
    };
    private final int zzg;

    zzgv(int i) {
        this.zzg = i;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzg);
    }

    public final int zza() {
        return this.zzg;
    }
}
