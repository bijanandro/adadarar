package com.android.billingclient.api;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.play_billing.zzgh;
import com.google.android.gms.internal.play_billing.zzgl;
import com.google.android.gms.internal.play_billing.zzgn;
import com.google.android.gms.internal.play_billing.zzgr;
import com.google.android.gms.internal.play_billing.zzgw;
import com.google.android.gms.internal.play_billing.zzgy;
import com.google.android.gms.internal.play_billing.zzhb;
import com.google.android.gms.internal.play_billing.zzhd;
import com.google.android.gms.internal.play_billing.zzhe;
import com.google.android.gms.internal.play_billing.zzhk;
import com.google.android.gms.internal.play_billing.zzhl;
import com.google.android.gms.internal.play_billing.zzhn;
import com.google.android.gms.internal.play_billing.zzho;
import com.google.android.gms.internal.play_billing.zzhs;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzcd implements zzby {
    private final zzhb zzb;
    private final Context zzc;
    private final zzcf zzd;

    zzcd(Context context, zzhb zzhbVar) {
        this.zzd = new zzcf(context);
        this.zzb = zzhbVar;
        this.zzc = context;
    }

    @Override // com.android.billingclient.api.zzby
    public final void zza(zzgh zzghVar) {
        if (zzghVar == null) {
            return;
        }
        try {
            zzhk zzhkVarZzz = zzhl.zzz();
            zzhb zzhbVar = this.zzb;
            if (zzhbVar != null) {
                zzhkVarZzz.zzl(zzhbVar);
            }
            zzhkVarZzz.zzi(zzghVar);
            this.zzd.zza((zzhl) zzhkVarZzz.zzc());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzby
    public final void zzb(zzgl zzglVar) {
        if (zzglVar == null) {
            return;
        }
        try {
            zzhk zzhkVarZzz = zzhl.zzz();
            zzhb zzhbVar = this.zzb;
            if (zzhbVar != null) {
                zzhkVarZzz.zzl(zzhbVar);
            }
            zzhkVarZzz.zzj(zzglVar);
            this.zzd.zza((zzhl) zzhkVarZzz.zzc());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzby
    public final void zzc(byte[] bArr) {
        try {
            zzg(zzgy.zzB(bArr, com.google.android.gms.internal.play_billing.zzcp.zza()));
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzby
    public final void zzd(zzhs zzhsVar) {
        if (zzhsVar == null) {
            return;
        }
        try {
            zzhk zzhkVarZzz = zzhl.zzz();
            zzhb zzhbVar = this.zzb;
            if (zzhbVar != null) {
                zzhkVarZzz.zzl(zzhbVar);
            }
            zzhkVarZzz.zzn(zzhsVar);
            this.zzd.zza((zzhl) zzhkVarZzz.zzc());
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzby
    public final void zze(int i, List list, boolean z, boolean z2) {
        zzgy zzgyVar;
        try {
            int i2 = zzbx.zza;
            try {
                zzgw zzgwVarZzz = zzgy.zzz();
                zzgwVarZzz.zzn(i);
                zzgwVarZzz.zzm(false);
                zzgwVarZzz.zzl(z2);
                zzgwVarZzz.zzi(list);
                zzgyVar = (zzgy) zzgwVarZzz.zzc();
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
                zzgyVar = null;
            }
            zzg(zzgyVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.zzby
    public final void zzf(int i, List list, List list2, BillingResult billingResult, boolean z, boolean z2) {
        zzgy zzgyVar;
        try {
            int i2 = zzbx.zza;
            try {
                zzgw zzgwVarZzz = zzgy.zzz();
                zzgwVarZzz.zzn(4);
                zzgwVarZzz.zzi(list);
                zzgwVarZzz.zzm(false);
                zzgwVarZzz.zzl(z2);
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    Purchase purchase = (Purchase) it.next();
                    zzhn zzhnVarZzz = zzho.zzz();
                    zzhnVarZzz.zzi(purchase.getProducts());
                    zzhnVarZzz.zzk(purchase.getPurchaseState());
                    zzhnVarZzz.zzj(purchase.getPackageName());
                    zzgwVarZzz.zzj(zzhnVarZzz);
                }
                zzgn zzgnVarZzz = zzgr.zzz();
                zzgnVarZzz.zzk(billingResult.getResponseCode());
                zzgnVarZzz.zzj(billingResult.getDebugMessage());
                zzgwVarZzz.zzk(zzgnVarZzz);
                zzgyVar = (zzgy) zzgwVarZzz.zzc();
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
                zzgyVar = null;
            }
            zzg(zzgyVar);
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }

    final void zzg(zzgy zzgyVar) {
        if (zzgyVar == null) {
            return;
        }
        try {
            if (this.zzb != null) {
                try {
                    Context context = this.zzc;
                    String string = null;
                    ContentResolver contentResolver = context == null ? null : context.getContentResolver();
                    if (contentResolver != null) {
                        string = Settings.Secure.getString(contentResolver, "android_id");
                    }
                    int iZza = string == null ? 0 : com.google.android.gms.internal.play_billing.zzbg.zza().zza(string).zza();
                    int i = com.google.android.gms.internal.play_billing.zzbk.zza;
                    long j = ((long) (iZza % 100)) % 100;
                    if (j < 0) {
                        j += 100;
                    }
                    if (((int) j) < 0) {
                        zzhk zzhkVarZzz = zzhl.zzz();
                        zzhb zzhbVar = this.zzb;
                        if (zzhbVar != null) {
                            zzhkVarZzz.zzl(zzhbVar);
                        }
                        zzhkVarZzz.zzk(zzgyVar);
                        zzhd zzhdVarZzz = zzhe.zzz();
                        zzdi.zza(this.zzc);
                        zzhdVarZzz.zzi(false);
                        zzhkVarZzz.zzm(zzhdVarZzz);
                        this.zzd.zza((zzhl) zzhkVarZzz.zzc());
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Throwable th) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to log.", th);
        }
    }
}
