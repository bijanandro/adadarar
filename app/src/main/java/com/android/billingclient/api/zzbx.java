package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzgg;
import com.google.android.gms.internal.play_billing.zzgh;
import com.google.android.gms.internal.play_billing.zzgk;
import com.google.android.gms.internal.play_billing.zzgl;
import com.google.android.gms.internal.play_billing.zzgn;
import com.google.android.gms.internal.play_billing.zzgr;
import com.google.android.gms.internal.play_billing.zzgv;
import com.google.android.gms.internal.play_billing.zzgw;
import com.google.android.gms.internal.play_billing.zzgy;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zzbx {
    public static final /* synthetic */ int zza = 0;

    static {
        com.google.android.gms.internal.play_billing.zzal zzalVar = zzby.zza;
    }

    public static zzgv zza(String str) {
        return (zzgv) zzby.zza.getOrDefault(str, zzgv.BROADCAST_ACTION_UNSPECIFIED);
    }

    public static zzgh zzb(int i, int i2, BillingResult billingResult) {
        try {
            zzgg zzggVarZzz = zzgh.zzz();
            zzgn zzgnVarZzz = zzgr.zzz();
            zzgnVarZzz.zzk(billingResult.getResponseCode());
            zzgnVarZzz.zzj(billingResult.getDebugMessage());
            zzgnVarZzz.zzl(i);
            zzggVarZzz.zzi(zzgnVarZzz);
            zzggVarZzz.zzk(i2);
            return (zzgh) zzggVarZzz.zzc();
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }

    public static zzgh zzc(int i, int i2, BillingResult billingResult, String str) {
        try {
            zzgn zzgnVarZzz = zzgr.zzz();
            zzgnVarZzz.zzk(billingResult.getResponseCode());
            zzgnVarZzz.zzj(billingResult.getDebugMessage());
            zzgnVarZzz.zzl(i);
            if (str != null) {
                zzgnVarZzz.zzi(str);
            }
            zzgg zzggVarZzz = zzgh.zzz();
            zzggVarZzz.zzi(zzgnVarZzz);
            zzggVarZzz.zzk(i2);
            return (zzgh) zzggVarZzz.zzc();
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }

    public static zzgl zzd(int i) {
        try {
            zzgk zzgkVarZzz = zzgl.zzz();
            zzgkVarZzz.zzj(i);
            return (zzgl) zzgkVarZzz.zzc();
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }

    public static zzgy zze(int i, List list) {
        try {
            zzgw zzgwVarZzz = zzgy.zzz();
            zzgwVarZzz.zzn(3);
            zzgwVarZzz.zzi(list);
            return (zzgy) zzgwVarZzz.zzc();
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }
}
