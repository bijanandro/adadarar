package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.google.android.gms.internal.play_billing.zzhs;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzay implements ServiceConnection {
    final /* synthetic */ BillingClientImpl zza;
    private final Object zzb = new Object();
    private boolean zzc = false;
    private BillingClientStateListener zzd;

    /* synthetic */ zzay(BillingClientImpl billingClientImpl, BillingClientStateListener billingClientStateListener, zzax zzaxVar) {
        this.zza = billingClientImpl;
        this.zzd = billingClientStateListener;
    }

    private final void zzd(BillingResult billingResult) {
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingSetupFinished(billingResult);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Billing service connected.");
        this.zza.zzg = com.google.android.gms.internal.play_billing.zzr.zzu(iBinder);
        Callable callable = new Callable() { // from class: com.android.billingclient.api.zzav
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zza();
                return null;
            }
        };
        Runnable runnable = new Runnable() { // from class: com.android.billingclient.api.zzaw
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb();
            }
        };
        BillingClientImpl billingClientImpl = this.zza;
        if (billingClientImpl.zzak(callable, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, runnable, billingClientImpl.zzag()) == null) {
            BillingClientImpl billingClientImpl2 = this.zza;
            BillingResult billingResultZzai = billingClientImpl2.zzai();
            billingClientImpl2.zzf.zza(zzbx.zzb(25, 6, billingResultZzai));
            zzd(billingResultZzai);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Billing service disconnected.");
        this.zza.zzf.zzd(zzhs.zzA());
        this.zza.zzg = null;
        this.zza.zza = 0;
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingServiceDisconnected();
            }
        }
    }

    final /* synthetic */ Object zza() throws Exception {
        Bundle bundle;
        int i;
        int iZzy;
        synchronized (this.zzb) {
            if (!this.zzc) {
                if (TextUtils.isEmpty(null)) {
                    bundle = null;
                } else {
                    bundle = new Bundle();
                    bundle.putString("accountName", null);
                }
                int i2 = 3;
                try {
                    String packageName = this.zza.zze.getPackageName();
                    iZzy = 3;
                    int i3 = 22;
                    while (true) {
                        if (i3 < 3) {
                            i3 = 0;
                            break;
                        }
                        if (bundle == null) {
                            try {
                                iZzy = this.zza.zzg.zzy(i3, packageName, "subs");
                            } catch (Exception e) {
                                e = e;
                                i2 = iZzy;
                                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                                i = e instanceof DeadObjectException ? 101 : e instanceof RemoteException ? 100 : e instanceof SecurityException ? 102 : 42;
                                this.zza.zza = 0;
                                this.zza.zzg = null;
                                iZzy = i2;
                            }
                        } else {
                            iZzy = this.zza.zzg.zzc(i3, packageName, "subs", bundle);
                        }
                        if (iZzy == 0) {
                            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "highestLevelSupportedForSubs: " + i3);
                            break;
                        }
                        i3--;
                    }
                    boolean z = true;
                    this.zza.zzj = i3 >= 5;
                    this.zza.zzi = i3 >= 3;
                    if (i3 < 3) {
                        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "In-app billing API does not support subscription on this device.");
                        i = 9;
                    } else {
                        i = 1;
                    }
                    int i4 = 22;
                    while (true) {
                        if (i4 < 3) {
                            break;
                        }
                        iZzy = bundle == null ? this.zza.zzg.zzy(i4, packageName, "inapp") : this.zza.zzg.zzc(i4, packageName, "inapp", bundle);
                        if (iZzy == 0) {
                            this.zza.zzk = i4;
                            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "mHighestLevelSupportedForInApp: " + this.zza.zzk);
                            break;
                        }
                        i4--;
                    }
                    BillingClientImpl billingClientImpl = this.zza;
                    billingClientImpl.zzy = billingClientImpl.zzk >= 22;
                    BillingClientImpl billingClientImpl2 = this.zza;
                    billingClientImpl2.zzx = billingClientImpl2.zzk >= 21;
                    BillingClientImpl billingClientImpl3 = this.zza;
                    billingClientImpl3.zzw = billingClientImpl3.zzk >= 20;
                    BillingClientImpl billingClientImpl4 = this.zza;
                    billingClientImpl4.zzv = billingClientImpl4.zzk >= 19;
                    BillingClientImpl billingClientImpl5 = this.zza;
                    billingClientImpl5.zzu = billingClientImpl5.zzk >= 18;
                    BillingClientImpl billingClientImpl6 = this.zza;
                    billingClientImpl6.zzt = billingClientImpl6.zzk >= 17;
                    BillingClientImpl billingClientImpl7 = this.zza;
                    billingClientImpl7.zzs = billingClientImpl7.zzk >= 16;
                    BillingClientImpl billingClientImpl8 = this.zza;
                    billingClientImpl8.zzr = billingClientImpl8.zzk >= 15;
                    BillingClientImpl billingClientImpl9 = this.zza;
                    billingClientImpl9.zzq = billingClientImpl9.zzk >= 14;
                    BillingClientImpl billingClientImpl10 = this.zza;
                    billingClientImpl10.zzp = billingClientImpl10.zzk >= 12;
                    BillingClientImpl billingClientImpl11 = this.zza;
                    billingClientImpl11.zzo = billingClientImpl11.zzk >= 10;
                    BillingClientImpl billingClientImpl12 = this.zza;
                    billingClientImpl12.zzn = billingClientImpl12.zzk >= 9;
                    BillingClientImpl billingClientImpl13 = this.zza;
                    billingClientImpl13.zzm = billingClientImpl13.zzk >= 8;
                    BillingClientImpl billingClientImpl14 = this.zza;
                    if (billingClientImpl14.zzk < 6) {
                        z = false;
                    }
                    billingClientImpl14.zzl = z;
                    if (this.zza.zzk < 3) {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "In-app billing API version 3 is not supported on this device.");
                        i = 36;
                    }
                    if (iZzy == 0) {
                        this.zza.zza = 2;
                        if (this.zza.zzd != null) {
                            this.zza.zzd.zzg(this.zza.zzx);
                        }
                    } else {
                        this.zza.zza = 0;
                        this.zza.zzg = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                if (iZzy == 0) {
                    this.zza.zzf.zzb(zzbx.zzd(6));
                    zzd(zzca.zzl);
                } else {
                    this.zza.zzf.zza(zzbx.zzb(i, 6, zzca.zza));
                    zzd(zzca.zza);
                }
            }
        }
        return null;
    }

    final /* synthetic */ void zzb() {
        this.zza.zza = 0;
        this.zza.zzg = null;
        this.zza.zzf.zza(zzbx.zzb(24, 6, zzca.zzn));
        zzd(zzca.zzn);
    }

    final void zzc() {
        synchronized (this.zzb) {
            this.zzd = null;
            this.zzc = true;
        }
    }
}
