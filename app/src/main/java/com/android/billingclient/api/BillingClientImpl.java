package com.android.billingclient.api;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.BundleCompat;
import androidx.work.WorkRequest;
import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.gms.internal.play_billing.zzgg;
import com.google.android.gms.internal.play_billing.zzgh;
import com.google.android.gms.internal.play_billing.zzgk;
import com.google.android.gms.internal.play_billing.zzgl;
import com.google.android.gms.internal.play_billing.zzgn;
import com.google.android.gms.internal.play_billing.zzgr;
import com.google.android.gms.internal.play_billing.zzha;
import com.google.android.gms.internal.play_billing.zzhb;
import com.google.android.gms.internal.play_billing.zzhg;
import com.google.android.gms.internal.play_billing.zzhi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
class BillingClientImpl extends BillingClient {
    private boolean zzA;
    private ExecutorService zzB;
    private volatile int zza;
    private final String zzb;
    private final Handler zzc;
    private volatile zzk zzd;
    private Context zze;
    private zzby zzf;
    private volatile com.google.android.gms.internal.play_billing.zzs zzg;
    private volatile zzay zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private boolean zzx;
    private boolean zzy;
    private zzcn zzz;

    private BillingClientImpl(Activity activity, zzcn zzcnVar, String str) {
        this(activity.getApplicationContext(), zzcnVar, new zzbq(), str, null, null, null, null);
    }

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzcn zzcnVar, AlternativeBillingListener alternativeBillingListener, String str, zzby zzbyVar) {
        this.zze = context.getApplicationContext();
        zzha zzhaVarZzz = zzhb.zzz();
        zzhaVarZzz.zzj(str);
        zzhaVarZzz.zzi(this.zze.getPackageName());
        if (zzbyVar != null) {
            this.zzf = zzbyVar;
        } else {
            this.zzf = new zzcd(this.zze, (zzhb) zzhaVarZzz.zzc());
        }
        if (purchasesUpdatedListener == null) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzd = new zzk(this.zze, purchasesUpdatedListener, null, alternativeBillingListener, null, this.zzf);
        this.zzz = zzcnVar;
        this.zzA = alternativeBillingListener != null;
        this.zze.getPackageName();
    }

    private int launchBillingFlowCpp(Activity activity, BillingFlowParams billingFlowParams) {
        return launchBillingFlow(activity, billingFlowParams).getResponseCode();
    }

    private void startConnection(long j) {
        zzbq zzbqVar = new zzbq(j);
        if (isReady()) {
            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            this.zzf.zzb(zzbx.zzd(6));
            zzbqVar.onBillingSetupFinished(zzca.zzl);
            return;
        }
        int i = 1;
        if (this.zza == 1) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            this.zzf.zza(zzbx.zzb(37, 6, zzca.zzd));
            zzbqVar.onBillingSetupFinished(zzca.zzd);
            return;
        }
        if (this.zza == 3) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            this.zzf.zza(zzbx.zzb(38, 6, zzca.zzm));
            zzbqVar.onBillingSetupFinished(zzca.zzm);
            return;
        }
        this.zza = 1;
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Starting in-app billing setup.");
        this.zzh = new zzay(this, zzbqVar, null);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            i = 41;
        } else {
            ResolveInfo resolveInfo = listQueryIntentServices.get(0);
            if (resolveInfo.serviceInfo != null) {
                String str = resolveInfo.serviceInfo.packageName;
                String str2 = resolveInfo.serviceInfo.name;
                if (!"com.android.vending".equals(str) || str2 == null) {
                    com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "The device doesn't have valid Play Store.");
                    i = 40;
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    intent2.putExtra("playBillingLibraryVersion", this.zzb);
                    if (this.zze.bindService(intent2, this.zzh, 1)) {
                        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Service was bonded successfully.");
                        return;
                    } else {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Connection to Billing service is blocked.");
                        i = 39;
                    }
                }
            }
        }
        this.zza = 0;
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Billing service unavailable on device.");
        this.zzf.zza(zzbx.zzb(i, 6, zzca.zzc));
        zzbqVar.onBillingSetupFinished(zzca.zzc);
    }

    static /* synthetic */ zzcx zzaf(BillingClientImpl billingClientImpl, String str, int i) {
        Bundle bundleZzi;
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        int i2 = 0;
        Bundle bundleZzc = com.google.android.gms.internal.play_billing.zzb.zzc(billingClientImpl.zzn, billingClientImpl.zzv, true, false, billingClientImpl.zzb);
        List list = null;
        String string = null;
        while (true) {
            try {
                if (billingClientImpl.zzn) {
                    bundleZzi = billingClientImpl.zzg.zzj(z != billingClientImpl.zzv ? 9 : 19, billingClientImpl.zze.getPackageName(), str, string, bundleZzc);
                } else {
                    bundleZzi = billingClientImpl.zzg.zzi(3, billingClientImpl.zze.getPackageName(), str, string);
                }
                zzcy zzcyVarZza = zzcz.zza(bundleZzi, "BillingClient", "getPurchase()");
                BillingResult billingResultZza = zzcyVarZza.zza();
                if (billingResultZza != zzca.zzl) {
                    billingClientImpl.zzf.zza(zzbx.zzb(zzcyVarZza.zzb(), 9, billingResultZza));
                    return new zzcx(billingResultZza, list);
                }
                ArrayList<String> stringArrayList = bundleZzi.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzi.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzi.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i3 = i2;
                int i4 = i3;
                while (i3 < stringArrayList2.size()) {
                    String str2 = stringArrayList2.get(i3);
                    String str3 = stringArrayList3.get(i3);
                    com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i3))));
                    try {
                        Purchase purchase = new Purchase(str2, str3);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "BUG: empty/null token!");
                            i4 = 1;
                        }
                        arrayList.add(purchase);
                        i3++;
                    } catch (JSONException e) {
                        com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        billingClientImpl.zzf.zza(zzbx.zzb(51, 9, zzca.zzj));
                        return new zzcx(zzca.zzj, null);
                    }
                }
                if (i4 != 0) {
                    billingClientImpl.zzf.zza(zzbx.zzb(26, 9, zzca.zzj));
                }
                string = bundleZzi.getString("INAPP_CONTINUATION_TOKEN");
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                if (TextUtils.isEmpty(string)) {
                    return new zzcx(zzca.zzl, arrayList);
                }
                list = null;
                z = true;
                i2 = 0;
            } catch (Exception e2) {
                billingClientImpl.zzf.zza(zzbx.zzb(52, 9, zzca.zzm));
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got exception trying to get purchasesm try to reconnect", e2);
                return new zzcx(zzca.zzm, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler zzag() {
        return Looper.myLooper() == null ? this.zzc : new Handler(Looper.myLooper());
    }

    private final BillingResult zzah(final BillingResult billingResult) {
        if (Thread.interrupted()) {
            return billingResult;
        }
        this.zzc.post(new Runnable() { // from class: com.android.billingclient.api.zzm
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzR(billingResult);
            }
        });
        return billingResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BillingResult zzai() {
        return (this.zza == 0 || this.zza == 3) ? zzca.zzm : zzca.zzj;
    }

    private static String zzaj() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return BuildConfig.VERSION_NAME;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Future zzak(Callable callable, long j, final Runnable runnable, Handler handler) {
        if (this.zzB == null) {
            this.zzB = Executors.newFixedThreadPool(com.google.android.gms.internal.play_billing.zzb.zza, new zzap(this));
        }
        try {
            final Future futureSubmit = this.zzB.submit(callable);
            handler.postDelayed(new Runnable() { // from class: com.android.billingclient.api.zzu
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    Runnable runnable2 = runnable;
                    future.cancel(true);
                    com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, (long) (j * 0.95d));
            return futureSubmit;
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    private final void zzal(String str, final PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 11, zzca.zzm));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzca.zzm, null);
        } else if (zzak(new zzar(this, str, purchaseHistoryResponseListener), WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzai
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzZ(purchaseHistoryResponseListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 11, billingResultZzai));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(billingResultZzai, null);
        }
    }

    private final void zzam(String str, final PurchasesResponseListener purchasesResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 9, zzca.zzm));
            purchasesResponseListener.onQueryPurchasesResponse(zzca.zzm, com.google.android.gms.internal.play_billing.zzai.zzk());
        } else if (TextUtils.isEmpty(str)) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Please provide a valid product type.");
            this.zzf.zza(zzbx.zzb(50, 9, zzca.zzg));
            purchasesResponseListener.onQueryPurchasesResponse(zzca.zzg, com.google.android.gms.internal.play_billing.zzai.zzk());
        } else if (zzak(new zzaq(this, str, purchasesResponseListener), WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzaa
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzaa(purchasesResponseListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 9, billingResultZzai));
            purchasesResponseListener.onQueryPurchasesResponse(billingResultZzai, com.google.android.gms.internal.play_billing.zzai.zzk());
        }
    }

    private final void zzan(BillingResult billingResult, int i, int i2) {
        zzgl zzglVar = null;
        zzgh zzghVar = null;
        if (billingResult.getResponseCode() == 0) {
            zzby zzbyVar = this.zzf;
            int i3 = zzbx.zza;
            try {
                zzgk zzgkVarZzz = zzgl.zzz();
                zzgkVarZzz.zzj(5);
                zzhg zzhgVarZzz = zzhi.zzz();
                zzhgVarZzz.zzi(i2);
                zzgkVarZzz.zzi((zzhi) zzhgVarZzz.zzc());
                zzglVar = (zzgl) zzgkVarZzz.zzc();
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e);
            }
            zzbyVar.zzb(zzglVar);
            return;
        }
        zzby zzbyVar2 = this.zzf;
        int i4 = zzbx.zza;
        try {
            zzgg zzggVarZzz = zzgh.zzz();
            zzgn zzgnVarZzz = zzgr.zzz();
            zzgnVarZzz.zzk(billingResult.getResponseCode());
            zzgnVarZzz.zzj(billingResult.getDebugMessage());
            zzgnVarZzz.zzl(i);
            zzggVarZzz.zzi(zzgnVarZzz);
            zzggVarZzz.zzk(5);
            zzhg zzhgVarZzz2 = zzhi.zzz();
            zzhgVarZzz2.zzi(i2);
            zzggVarZzz.zzj((zzhi) zzhgVarZzz2.zzc());
            zzghVar = (zzgh) zzggVarZzz.zzc();
        } catch (Exception e2) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingLogger", "Unable to create logging payload", e2);
        }
        zzbyVar2.zza(zzghVar);
    }

    static /* synthetic */ zzbp zzg(BillingClientImpl billingClientImpl, String str) {
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Querying purchase history, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        int i = 0;
        Bundle bundleZzc = com.google.android.gms.internal.play_billing.zzb.zzc(billingClientImpl.zzn, billingClientImpl.zzv, true, false, billingClientImpl.zzb);
        String string = null;
        while (billingClientImpl.zzl) {
            try {
                Bundle bundleZzh = billingClientImpl.zzg.zzh(6, billingClientImpl.zze.getPackageName(), str, string, bundleZzc);
                zzcy zzcyVarZza = zzcz.zza(bundleZzh, "BillingClient", "getPurchaseHistory()");
                BillingResult billingResultZza = zzcyVarZza.zza();
                if (billingResultZza != zzca.zzl) {
                    billingClientImpl.zzf.zza(zzbx.zzb(zzcyVarZza.zzb(), 11, billingResultZza));
                    return new zzbp(billingResultZza, null);
                }
                ArrayList<String> stringArrayList = bundleZzh.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzh.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzh.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i2 = i;
                int i3 = i2;
                while (i2 < stringArrayList2.size()) {
                    String str2 = stringArrayList2.get(i2);
                    String str3 = stringArrayList3.get(i2);
                    com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Purchase record found for sku : ".concat(String.valueOf(stringArrayList.get(i2))));
                    try {
                        PurchaseHistoryRecord purchaseHistoryRecord = new PurchaseHistoryRecord(str2, str3);
                        if (TextUtils.isEmpty(purchaseHistoryRecord.getPurchaseToken())) {
                            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "BUG: empty/null token!");
                            i3 = 1;
                        }
                        arrayList.add(purchaseHistoryRecord);
                        i2++;
                    } catch (JSONException e) {
                        com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        billingClientImpl.zzf.zza(zzbx.zzb(51, 11, zzca.zzj));
                        return new zzbp(zzca.zzj, null);
                    }
                }
                if (i3 != 0) {
                    billingClientImpl.zzf.zza(zzbx.zzb(26, 11, zzca.zzj));
                }
                string = bundleZzh.getString("INAPP_CONTINUATION_TOKEN");
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                if (TextUtils.isEmpty(string)) {
                    return new zzbp(zzca.zzl, arrayList);
                }
                i = 0;
            } catch (RemoteException e2) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got exception trying to get purchase history, try to reconnect", e2);
                billingClientImpl.zzf.zza(zzbx.zzb(59, 11, zzca.zzm));
                return new zzbp(zzca.zzm, null);
            }
        }
        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getPurchaseHistory is not supported on current device");
        return new zzbp(zzca.zzq, null);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void acknowledgePurchase(final AcknowledgePurchaseParams acknowledgePurchaseParams, final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 3, zzca.zzm));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zzm);
            return;
        }
        if (TextUtils.isEmpty(acknowledgePurchaseParams.getPurchaseToken())) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Please provide a valid purchase token.");
            this.zzf.zza(zzbx.zzb(26, 3, zzca.zzi));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zzi);
        } else if (!this.zzn) {
            this.zzf.zza(zzbx.zzb(27, 3, zzca.zzb));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zzb);
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzq
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzk(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzr
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzQ(acknowledgePurchaseResponseListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 3, billingResultZzai));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResultZzai);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void consumeAsync(final ConsumeParams consumeParams, final ConsumeResponseListener consumeResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 4, zzca.zzm));
            consumeResponseListener.onConsumeResponse(zzca.zzm, consumeParams.getPurchaseToken());
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzad
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzl(consumeParams, consumeResponseListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzae
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzS(consumeResponseListener, consumeParams);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 4, billingResultZzai));
            consumeResponseListener.onConsumeResponse(billingResultZzai, consumeParams.getPurchaseToken());
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void createAlternativeBillingOnlyReportingDetailsAsync(final AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 15, zzca.zzm));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzca.zzm, null);
        } else if (!this.zzx) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support alternative billing only.");
            this.zzf.zza(zzbx.zzb(66, 15, zzca.zzE));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzca.zzE, null);
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzv
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzq(alternativeBillingOnlyReportingDetailsListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzw
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzT(alternativeBillingOnlyReportingDetailsListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 15, billingResultZzai));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(billingResultZzai, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void createExternalOfferReportingDetailsAsync(final ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 24, zzca.zzm));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzca.zzm, null);
        } else if (!this.zzy) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support external offer.");
            this.zzf.zza(zzbx.zzb(103, 24, zzca.zzy));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzca.zzy, null);
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzx
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzr(externalOfferReportingDetailsListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzag
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzU(externalOfferReportingDetailsListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 24, billingResultZzai));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(billingResultZzai, null);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void endConnection() {
        this.zzf.zzb(zzbx.zzd(12));
        try {
            try {
                if (this.zzd != null) {
                    this.zzd.zzf();
                }
                if (this.zzh != null) {
                    this.zzh.zzc();
                }
                if (this.zzh != null && this.zzg != null) {
                    com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Unbinding from service.");
                    this.zze.unbindService(this.zzh);
                    this.zzh = null;
                }
                this.zzg = null;
                ExecutorService executorService = this.zzB;
                if (executorService != null) {
                    executorService.shutdownNow();
                    this.zzB = null;
                }
            } catch (Exception e) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "There was an exception while ending connection!", e);
            }
        } finally {
            this.zza = 3;
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void getBillingConfigAsync(GetBillingConfigParams getBillingConfigParams, final BillingConfigResponseListener billingConfigResponseListener) {
        if (!isReady()) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Service disconnected.");
            this.zzf.zza(zzbx.zzb(2, 13, zzca.zzm));
            billingConfigResponseListener.onBillingConfigResponse(zzca.zzm, null);
        } else {
            if (!this.zzu) {
                com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support get billing config.");
                this.zzf.zza(zzbx.zzb(32, 13, zzca.zzA));
                billingConfigResponseListener.onBillingConfigResponse(zzca.zzA, null);
                return;
            }
            String str = this.zzb;
            final Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            if (zzak(new Callable() { // from class: com.android.billingclient.api.zzs
                @Override // java.util.concurrent.Callable
                public final Object call() throws Exception {
                    this.zza.zzm(bundle, billingConfigResponseListener);
                    return null;
                }
            }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzt
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzV(billingConfigResponseListener);
                }
            }, zzag()) == null) {
                BillingResult billingResultZzai = zzai();
                this.zzf.zza(zzbx.zzb(25, 13, billingResultZzai));
                billingConfigResponseListener.onBillingConfigResponse(billingResultZzai, null);
            }
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final int getConnectionState() {
        return this.zza;
    }

    @Override // com.android.billingclient.api.BillingClient
    public void isAlternativeBillingOnlyAvailableAsync(final AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 14, zzca.zzm));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzca.zzm);
        } else if (!this.zzx) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support alternative billing only.");
            this.zzf.zza(zzbx.zzb(66, 14, zzca.zzE));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzca.zzE);
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzab
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzs(alternativeBillingOnlyAvailabilityListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzac
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzW(alternativeBillingOnlyAvailabilityListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 14, billingResultZzai));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(billingResultZzai);
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public void isExternalOfferAvailableAsync(final ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 23, zzca.zzm));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzca.zzm);
        } else if (!this.zzy) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support external offer.");
            this.zzf.zza(zzbx.zzb(103, 23, zzca.zzy));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzca.zzy);
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzam
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzt(externalOfferAvailabilityListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzan
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzX(externalOfferAvailabilityListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 23, billingResultZzai));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(billingResultZzai);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d3  */
    @Override // com.android.billingclient.api.BillingClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.android.billingclient.api.BillingResult isFeatureSupported(java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.isFeatureSupported(java.lang.String):com.android.billingclient.api.BillingResult");
    }

    @Override // com.android.billingclient.api.BillingClient
    public final boolean isReady() {
        return (this.zza != 2 || this.zzg == null || this.zzh == null) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0444  */
    @Override // com.android.billingclient.api.BillingClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.android.billingclient.api.BillingResult launchBillingFlow(android.app.Activity r32, final com.android.billingclient.api.BillingFlowParams r33) {
        /*
            Method dump skipped, instruction units count: 1328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.launchBillingFlow(android.app.Activity, com.android.billingclient.api.BillingFlowParams):com.android.billingclient.api.BillingResult");
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryProductDetailsAsync(final QueryProductDetailsParams queryProductDetailsParams, final ProductDetailsResponseListener productDetailsResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 7, zzca.zzm));
            productDetailsResponseListener.onProductDetailsResponse(zzca.zzm, new ArrayList());
        } else if (!this.zzt) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Querying product details is not supported.");
            this.zzf.zza(zzbx.zzb(20, 7, zzca.zzv));
            productDetailsResponseListener.onProductDetailsResponse(zzca.zzv, new ArrayList());
        } else if (zzak(new Callable() { // from class: com.android.billingclient.api.zzaj
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzn(queryProductDetailsParams, productDetailsResponseListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzak
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzY(productDetailsResponseListener);
            }
        }, zzag()) == null) {
            BillingResult billingResultZzai = zzai();
            this.zzf.zza(zzbx.zzb(25, 7, billingResultZzai));
            productDetailsResponseListener.onProductDetailsResponse(billingResultZzai, new ArrayList());
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchaseHistoryAsync(QueryPurchaseHistoryParams queryPurchaseHistoryParams, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzal(queryPurchaseHistoryParams.zza(), purchaseHistoryResponseListener);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, PurchasesResponseListener purchasesResponseListener) {
        zzam(queryPurchasesParams.zza(), purchasesResponseListener);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams, final SkuDetailsResponseListener skuDetailsResponseListener) {
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 8, zzca.zzm));
            skuDetailsResponseListener.onSkuDetailsResponse(zzca.zzm, null);
            return;
        }
        final String skuType = skuDetailsParams.getSkuType();
        final List<String> skusList = skuDetailsParams.getSkusList();
        if (TextUtils.isEmpty(skuType)) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Please fix the input params. SKU type can't be empty.");
            this.zzf.zza(zzbx.zzb(49, 8, zzca.zzf));
            skuDetailsResponseListener.onSkuDetailsResponse(zzca.zzf, null);
        } else if (skusList == null) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Please fix the input params. The list of SKUs can't be empty.");
            this.zzf.zza(zzbx.zzb(48, 8, zzca.zze));
            skuDetailsResponseListener.onSkuDetailsResponse(zzca.zze, null);
        } else {
            final String str = null;
            if (zzak(new Callable(skuType, skusList, str, skuDetailsResponseListener) { // from class: com.android.billingclient.api.zzy
                public final /* synthetic */ String zzb;
                public final /* synthetic */ List zzc;
                public final /* synthetic */ SkuDetailsResponseListener zzd;

                {
                    this.zzd = skuDetailsResponseListener;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() throws Exception {
                    this.zza.zzo(this.zzb, this.zzc, null, this.zzd);
                    return null;
                }
            }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzz
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzab(skuDetailsResponseListener);
                }
            }, zzag()) == null) {
                BillingResult billingResultZzai = zzai();
                this.zzf.zza(zzbx.zzb(25, 8, billingResultZzai));
                skuDetailsResponseListener.onSkuDetailsResponse(billingResultZzai, null);
            }
        }
    }

    @Override // com.android.billingclient.api.BillingClient
    public final BillingResult showInAppMessages(final Activity activity, InAppMessageParams inAppMessageParams, InAppMessageResponseListener inAppMessageResponseListener) {
        if (!isReady()) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Service disconnected.");
            return zzca.zzm;
        }
        if (!this.zzp) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current client doesn't support showing in-app messages.");
            return zzca.zzw;
        }
        View viewFindViewById = activity.findViewById(R.id.content);
        IBinder windowToken = viewFindViewById.getWindowToken();
        Rect rect = new Rect();
        viewFindViewById.getGlobalVisibleRect(rect);
        final Bundle bundle = new Bundle();
        BundleCompat.putBinder(bundle, "KEY_WINDOW_TOKEN", windowToken);
        bundle.putInt("KEY_DIMEN_LEFT", rect.left);
        bundle.putInt("KEY_DIMEN_TOP", rect.top);
        bundle.putInt("KEY_DIMEN_RIGHT", rect.right);
        bundle.putInt("KEY_DIMEN_BOTTOM", rect.bottom);
        bundle.putString("playBillingLibraryVersion", this.zzb);
        bundle.putIntegerArrayList("KEY_CATEGORY_IDS", inAppMessageParams.zza());
        final zzas zzasVar = new zzas(this, this.zzc, inAppMessageResponseListener);
        zzak(new Callable() { // from class: com.android.billingclient.api.zzal
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzp(bundle, activity, zzasVar);
                return null;
            }
        }, 5000L, null, this.zzc);
        return zzca.zzl;
    }

    final /* synthetic */ void zzQ(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 3, zzca.zzn));
        acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zzn);
    }

    final /* synthetic */ void zzR(BillingResult billingResult) {
        if (this.zzd.zzd() != null) {
            this.zzd.zzd().onPurchasesUpdated(billingResult, null);
        } else {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    final /* synthetic */ void zzS(ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) {
        this.zzf.zza(zzbx.zzb(24, 4, zzca.zzn));
        consumeResponseListener.onConsumeResponse(zzca.zzn, consumeParams.getPurchaseToken());
    }

    final /* synthetic */ void zzT(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        this.zzf.zza(zzbx.zzb(24, 15, zzca.zzn));
        alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzca.zzn, null);
    }

    final /* synthetic */ void zzU(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        this.zzf.zza(zzbx.zzb(24, 24, zzca.zzn));
        externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzca.zzn, null);
    }

    final /* synthetic */ void zzV(BillingConfigResponseListener billingConfigResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 13, zzca.zzn));
        billingConfigResponseListener.onBillingConfigResponse(zzca.zzn, null);
    }

    final /* synthetic */ void zzW(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        this.zzf.zza(zzbx.zzb(24, 14, zzca.zzn));
        alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzca.zzn);
    }

    final /* synthetic */ void zzX(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        this.zzf.zza(zzbx.zzb(24, 23, zzca.zzn));
        externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzca.zzn);
    }

    final /* synthetic */ void zzY(ProductDetailsResponseListener productDetailsResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 7, zzca.zzn));
        productDetailsResponseListener.onProductDetailsResponse(zzca.zzn, new ArrayList());
    }

    final /* synthetic */ void zzZ(PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 11, zzca.zzn));
        purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzca.zzn, null);
    }

    final /* synthetic */ void zzaa(PurchasesResponseListener purchasesResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 9, zzca.zzn));
        purchasesResponseListener.onQueryPurchasesResponse(zzca.zzn, com.google.android.gms.internal.play_billing.zzai.zzk());
    }

    final /* synthetic */ void zzab(SkuDetailsResponseListener skuDetailsResponseListener) {
        this.zzf.zza(zzbx.zzb(24, 8, zzca.zzn));
        skuDetailsResponseListener.onSkuDetailsResponse(zzca.zzn, null);
    }

    final /* synthetic */ void zzac(AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        this.zzf.zza(zzbx.zzb(24, 16, zzca.zzn));
        alternativeBillingOnlyInformationDialogListener.onAlternativeBillingOnlyInformationDialogResponse(zzca.zzn);
    }

    final /* synthetic */ void zzad(ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        this.zzf.zza(zzbx.zzb(24, 25, zzca.zzn));
        externalOfferInformationDialogListener.onExternalOfferInformationDialogResponse(zzca.zzn);
    }

    final /* synthetic */ Bundle zzc(int i, String str, String str2, BillingFlowParams billingFlowParams, Bundle bundle) throws Exception {
        return this.zzg.zzg(i, this.zze.getPackageName(), str, str2, null, bundle);
    }

    final /* synthetic */ Bundle zzd(String str, String str2) throws Exception {
        return this.zzg.zzf(3, this.zze.getPackageName(), str, str2, null);
    }

    final /* synthetic */ Object zzk(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) throws Exception {
        try {
            com.google.android.gms.internal.play_billing.zzs zzsVar = this.zzg;
            String packageName = this.zze.getPackageName();
            String purchaseToken = acknowledgePurchaseParams.getPurchaseToken();
            String str = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            Bundle bundleZzd = zzsVar.zzd(9, packageName, purchaseToken, bundle);
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zza(com.google.android.gms.internal.play_billing.zzb.zzb(bundleZzd, "BillingClient"), com.google.android.gms.internal.play_billing.zzb.zzg(bundleZzd, "BillingClient")));
            return null;
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Error acknowledge purchase!", e);
            this.zzf.zza(zzbx.zzb(28, 3, zzca.zzm));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzca.zzm);
            return null;
        }
    }

    final /* synthetic */ Object zzl(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) throws Exception {
        int iZza;
        String strZzg;
        String purchaseToken = consumeParams.getPurchaseToken();
        try {
            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Consuming purchase with token: " + purchaseToken);
            if (this.zzn) {
                com.google.android.gms.internal.play_billing.zzs zzsVar = this.zzg;
                String packageName = this.zze.getPackageName();
                boolean z = this.zzn;
                String str = this.zzb;
                Bundle bundle = new Bundle();
                if (z) {
                    bundle.putString("playBillingLibraryVersion", str);
                }
                Bundle bundleZze = zzsVar.zze(9, packageName, purchaseToken, bundle);
                iZza = bundleZze.getInt("RESPONSE_CODE");
                strZzg = com.google.android.gms.internal.play_billing.zzb.zzg(bundleZze, "BillingClient");
            } else {
                iZza = this.zzg.zza(3, this.zze.getPackageName(), purchaseToken);
                strZzg = "";
            }
            BillingResult billingResultZza = zzca.zza(iZza, strZzg);
            if (iZza == 0) {
                com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Successfully consumed purchase.");
                consumeResponseListener.onConsumeResponse(billingResultZza, purchaseToken);
                return null;
            }
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Error consuming purchase with token. Response code: " + iZza);
            this.zzf.zza(zzbx.zzb(23, 4, billingResultZza));
            consumeResponseListener.onConsumeResponse(billingResultZza, purchaseToken);
            return null;
        } catch (Exception e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Error consuming purchase!", e);
            this.zzf.zza(zzbx.zzb(29, 4, zzca.zzm));
            consumeResponseListener.onConsumeResponse(zzca.zzm, purchaseToken);
            return null;
        }
    }

    final /* synthetic */ Object zzm(Bundle bundle, BillingConfigResponseListener billingConfigResponseListener) throws Exception {
        try {
            this.zzg.zzp(18, this.zze.getPackageName(), bundle, new zzbg(billingConfigResponseListener, this.zzf, null));
        } catch (DeadObjectException e) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "getBillingConfig got a dead object exception (try to reconnect).", e);
            this.zzf.zza(zzbx.zzb(62, 13, zzca.zzm));
            billingConfigResponseListener.onBillingConfigResponse(zzca.zzm, null);
        } catch (Exception e2) {
            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "getBillingConfig got an exception.", e2);
            this.zzf.zza(zzbx.zzb(62, 13, zzca.zzj));
            billingConfigResponseListener.onBillingConfigResponse(zzca.zzj, null);
        }
        return null;
    }

    final /* synthetic */ Object zzn(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) throws Exception {
        String strZzg;
        int iZzb;
        int i;
        int i2;
        com.google.android.gms.internal.play_billing.zzs zzsVar;
        int i3;
        String packageName;
        Bundle bundle;
        com.google.android.gms.internal.play_billing.zzai zzaiVar;
        int i4;
        ArrayList arrayList = new ArrayList();
        String strZzb = queryProductDetailsParams.zzb();
        com.google.android.gms.internal.play_billing.zzai zzaiVarZza = queryProductDetailsParams.zza();
        int size = zzaiVarZza.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                strZzg = "";
                iZzb = 0;
                break;
            }
            int i6 = i5 + 20;
            ArrayList arrayList2 = new ArrayList(zzaiVarZza.subList(i5, i6 > size ? size : i6));
            ArrayList<String> arrayList3 = new ArrayList<>();
            int size2 = arrayList2.size();
            for (int i7 = 0; i7 < size2; i7++) {
                arrayList3.add(((QueryProductDetailsParams.Product) arrayList2.get(i7)).zza());
            }
            Bundle bundle2 = new Bundle();
            bundle2.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle2.putString("playBillingLibraryVersion", this.zzb);
            try {
                zzsVar = this.zzg;
                i3 = true != this.zzw ? 17 : 20;
                packageName = this.zze.getPackageName();
                String str = this.zzb;
                if (TextUtils.isEmpty(null)) {
                    this.zze.getPackageName();
                }
                if (TextUtils.isEmpty(null)) {
                    this.zze.getPackageName();
                }
                bundle = new Bundle();
                bundle.putString("playBillingLibraryVersion", str);
                bundle.putBoolean("enablePendingPurchases", true);
                bundle.putString("SKU_DETAILS_RESPONSE_FORMAT", "PRODUCT_DETAILS");
                ArrayList<String> arrayList4 = new ArrayList<>();
                ArrayList<String> arrayList5 = new ArrayList<>();
                int size3 = arrayList2.size();
                zzaiVar = zzaiVarZza;
                int i8 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i8 < size3) {
                    QueryProductDetailsParams.Product product = (QueryProductDetailsParams.Product) arrayList2.get(i8);
                    ArrayList arrayList6 = arrayList2;
                    arrayList4.add(null);
                    z2 |= !TextUtils.isEmpty(null);
                    String strZzb2 = product.zzb();
                    int i9 = size;
                    if (strZzb2.equals("first_party")) {
                        com.google.android.gms.internal.play_billing.zzaa.zzc(null, "Serialized DocId is required for constructing ExtraParams to query ProductDetails for all first party products.");
                        arrayList5.add(null);
                        z = true;
                    }
                    i8++;
                    size = i9;
                    arrayList2 = arrayList6;
                }
                i4 = size;
                if (z2) {
                    bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                }
                if (!arrayList5.isEmpty()) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (z && !TextUtils.isEmpty(null)) {
                    bundle.putString("accountName", null);
                }
                i2 = 7;
            } catch (Exception e) {
                e = e;
                i = 6;
                i2 = 7;
            }
            try {
                Bundle bundleZzl = zzsVar.zzl(i3, packageName, strZzb, bundle2, bundle);
                strZzg = "Item is unavailable for purchase.";
                if (bundleZzl == null) {
                    com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "queryProductDetailsAsync got empty product details response.");
                    this.zzf.zza(zzbx.zzb(44, 7, zzca.zzC));
                    break;
                }
                if (bundleZzl.containsKey("DETAILS_LIST")) {
                    ArrayList<String> stringArrayList = bundleZzl.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList == null) {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "queryProductDetailsAsync got null response list");
                        this.zzf.zza(zzbx.zzb(46, 7, zzca.zzC));
                        break;
                    }
                    for (int i10 = 0; i10 < stringArrayList.size(); i10++) {
                        try {
                            ProductDetails productDetails = new ProductDetails(stringArrayList.get(i10));
                            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Got product details: ".concat(productDetails.toString()));
                            arrayList.add(productDetails);
                        } catch (JSONException e2) {
                            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e2);
                            strZzg = "Error trying to decode SkuDetails.";
                            i = 6;
                            this.zzf.zza(zzbx.zzb(47, 7, zzca.zza(6, "Error trying to decode SkuDetails.")));
                            iZzb = i;
                            productDetailsResponseListener.onProductDetailsResponse(zzca.zza(iZzb, strZzg), arrayList);
                            return null;
                        }
                    }
                    i5 = i6;
                    zzaiVarZza = zzaiVar;
                    size = i4;
                } else {
                    iZzb = com.google.android.gms.internal.play_billing.zzb.zzb(bundleZzl, "BillingClient");
                    strZzg = com.google.android.gms.internal.play_billing.zzb.zzg(bundleZzl, "BillingClient");
                    if (iZzb != 0) {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + iZzb);
                        this.zzf.zza(zzbx.zzb(23, 7, zzca.zza(iZzb, strZzg)));
                    } else {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.");
                        this.zzf.zza(zzbx.zzb(45, 7, zzca.zza(6, strZzg)));
                        iZzb = 6;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                i = 6;
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
                this.zzf.zza(zzbx.zzb(43, i2, zzca.zzj));
                strZzg = "An internal error occurred.";
                iZzb = i;
                productDetailsResponseListener.onProductDetailsResponse(zzca.zza(iZzb, strZzg), arrayList);
                return null;
            }
        }
        iZzb = 4;
        productDetailsResponseListener.onProductDetailsResponse(zzca.zza(iZzb, strZzg), arrayList);
        return null;
    }

    final /* synthetic */ Object zzo(String str, List list, String str2, SkuDetailsResponseListener skuDetailsResponseListener) throws Exception {
        String strZzg;
        int i;
        Bundle bundleZzk;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                strZzg = "";
                i = 0;
                break;
            }
            int i3 = i2 + 20;
            ArrayList<String> arrayList2 = new ArrayList<>(list.subList(i2, i3 > size ? size : i3));
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList2);
            bundle.putString("playBillingLibraryVersion", this.zzb);
            try {
                if (this.zzo) {
                    com.google.android.gms.internal.play_billing.zzs zzsVar = this.zzg;
                    String packageName = this.zze.getPackageName();
                    int i4 = this.zzk;
                    String str3 = this.zzb;
                    Bundle bundle2 = new Bundle();
                    if (i4 >= 9) {
                        bundle2.putString("playBillingLibraryVersion", str3);
                    }
                    if (i4 >= 9) {
                        bundle2.putBoolean("enablePendingPurchases", true);
                    }
                    bundleZzk = zzsVar.zzl(10, packageName, str, bundle, bundle2);
                } else {
                    bundleZzk = this.zzg.zzk(3, this.zze.getPackageName(), str, bundle);
                }
                strZzg = "Item is unavailable for purchase.";
                if (bundleZzk == null) {
                    com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "querySkuDetailsAsync got null sku details list");
                    this.zzf.zza(zzbx.zzb(44, 8, zzca.zzC));
                    break;
                }
                if (bundleZzk.containsKey("DETAILS_LIST")) {
                    ArrayList<String> stringArrayList = bundleZzk.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList == null) {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "querySkuDetailsAsync got null response list");
                        this.zzf.zza(zzbx.zzb(46, 8, zzca.zzC));
                        break;
                    }
                    for (int i5 = 0; i5 < stringArrayList.size(); i5++) {
                        try {
                            SkuDetails skuDetails = new SkuDetails(stringArrayList.get(i5));
                            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Got sku details: ".concat(skuDetails.toString()));
                            arrayList.add(skuDetails);
                        } catch (JSONException e) {
                            com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "Got a JSON exception trying to decode SkuDetails.", e);
                            strZzg = "Error trying to decode SkuDetails.";
                            this.zzf.zza(zzbx.zzb(47, 8, zzca.zza(6, "Error trying to decode SkuDetails.")));
                            arrayList = null;
                            i = 6;
                            skuDetailsResponseListener.onSkuDetailsResponse(zzca.zza(i, strZzg), arrayList);
                            return null;
                        }
                    }
                    i2 = i3;
                } else {
                    int iZzb = com.google.android.gms.internal.play_billing.zzb.zzb(bundleZzk, "BillingClient");
                    strZzg = com.google.android.gms.internal.play_billing.zzb.zzg(bundleZzk, "BillingClient");
                    if (iZzb != 0) {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getSkuDetails() failed. Response code: " + iZzb);
                        this.zzf.zza(zzbx.zzb(23, 8, zzca.zza(iZzb, strZzg)));
                        i = iZzb;
                    } else {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                        this.zzf.zza(zzbx.zzb(45, 8, zzca.zza(6, strZzg)));
                    }
                }
            } catch (Exception e2) {
                com.google.android.gms.internal.play_billing.zzb.zzl("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect).", e2);
                this.zzf.zza(zzbx.zzb(43, 8, zzca.zzm));
                strZzg = "Service connection is disconnected.";
                i = -1;
                arrayList = null;
            }
        }
        arrayList = null;
        i = 4;
        skuDetailsResponseListener.onSkuDetailsResponse(zzca.zza(i, strZzg), arrayList);
        return null;
    }

    final /* synthetic */ Object zzp(Bundle bundle, Activity activity, ResultReceiver resultReceiver) throws Exception {
        this.zzg.zzt(12, this.zze.getPackageName(), bundle, new zzbo(new WeakReference(activity), resultReceiver, null));
        return null;
    }

    final /* synthetic */ Void zzq(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) throws Exception {
        try {
            this.zzg.zzm(21, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzba(alternativeBillingOnlyReportingDetailsListener, this.zzf, null));
        } catch (Exception unused) {
            this.zzf.zza(zzbx.zzb(70, 15, zzca.zzj));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzca.zzj, null);
        }
        return null;
    }

    final /* synthetic */ Void zzr(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) throws Exception {
        try {
            this.zzg.zzn(22, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzbc(externalOfferReportingDetailsListener, this.zzf, null));
        } catch (Exception e) {
            this.zzf.zza(zzbx.zzc(94, 24, zzca.zzj, String.format("%s: %s", e.getClass().getName(), com.google.android.gms.internal.play_billing.zzab.zzb(e.getMessage()))));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzca.zzj, null);
        }
        return null;
    }

    final /* synthetic */ Void zzs(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) throws Exception {
        try {
            this.zzg.zzr(21, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzbk(alternativeBillingOnlyAvailabilityListener, this.zzf, null));
        } catch (Exception unused) {
            this.zzf.zza(zzbx.zzb(69, 14, zzca.zzj));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzca.zzj);
        }
        return null;
    }

    final /* synthetic */ Void zzt(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) throws Exception {
        try {
            this.zzg.zzs(22, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzbm(externalOfferAvailabilityListener, this.zzf, null));
        } catch (Exception e) {
            this.zzf.zza(zzbx.zzc(91, 23, zzca.zzj, String.format("%s: %s", e.getClass().getName(), com.google.android.gms.internal.play_billing.zzab.zzb(e.getMessage()))));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzca.zzj);
        }
        return null;
    }

    final /* synthetic */ Void zzu(Activity activity, ResultReceiver resultReceiver, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) throws Exception {
        try {
            this.zzg.zzo(21, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzbe(new WeakReference(activity), resultReceiver, null));
        } catch (Exception unused) {
            this.zzf.zza(zzbx.zzb(74, 16, zzca.zzj));
            alternativeBillingOnlyInformationDialogListener.onAlternativeBillingOnlyInformationDialogResponse(zzca.zzj);
        }
        return null;
    }

    final /* synthetic */ Void zzv(Activity activity, ResultReceiver resultReceiver, ExternalOfferInformationDialogListener externalOfferInformationDialogListener) throws Exception {
        try {
            this.zzg.zzq(22, this.zze.getPackageName(), com.google.android.gms.internal.play_billing.zzb.zzd(this.zzb), new zzbi(new WeakReference(activity), resultReceiver, null));
        } catch (Exception e) {
            this.zzf.zza(zzbx.zzc(98, 25, zzca.zzj, String.format("%s: %s", e.getClass().getName(), com.google.android.gms.internal.play_billing.zzab.zzb(e.getMessage()))));
            externalOfferInformationDialogListener.onExternalOfferInformationDialogResponse(zzca.zzj);
        }
        return null;
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchaseHistoryAsync(String str, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzal(str, purchaseHistoryResponseListener);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void queryPurchasesAsync(String str, PurchasesResponseListener purchasesResponseListener) {
        zzam(str, purchasesResponseListener);
    }

    @Override // com.android.billingclient.api.BillingClient
    public BillingResult showAlternativeBillingOnlyInformationDialog(final Activity activity, final AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        }
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 16, zzca.zzm));
            return zzca.zzm;
        }
        if (!this.zzx) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current Play Store version doesn't support alternative billing only.");
            this.zzf.zza(zzbx.zzb(66, 16, zzca.zzE));
            return zzca.zzE;
        }
        final zzat zzatVar = new zzat(this, this.zzc, alternativeBillingOnlyInformationDialogListener);
        if (zzak(new Callable() { // from class: com.android.billingclient.api.zzo
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzu(activity, zzatVar, alternativeBillingOnlyInformationDialogListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzp
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzac(alternativeBillingOnlyInformationDialogListener);
            }
        }, this.zzc) != null) {
            return zzca.zzl;
        }
        BillingResult billingResultZzai = zzai();
        this.zzf.zza(zzbx.zzb(25, 16, billingResultZzai));
        return billingResultZzai;
    }

    @Override // com.android.billingclient.api.BillingClient
    public BillingResult showExternalOfferInformationDialog(final Activity activity, final ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        }
        if (!isReady()) {
            this.zzf.zza(zzbx.zzb(2, 25, zzca.zzm));
            return zzca.zzm;
        }
        if (!this.zzy) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Current Play Store version doesn't support external offer.");
            this.zzf.zza(zzbx.zzb(103, 25, zzca.zzy));
            return zzca.zzy;
        }
        final zzau zzauVar = new zzau(this, this.zzc, externalOfferInformationDialogListener);
        if (zzak(new Callable() { // from class: com.android.billingclient.api.zzaf
            @Override // java.util.concurrent.Callable
            public final Object call() throws Exception {
                this.zza.zzv(activity, zzauVar, externalOfferInformationDialogListener);
                return null;
            }
        }, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, new Runnable() { // from class: com.android.billingclient.api.zzah
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzad(externalOfferInformationDialogListener);
            }
        }, this.zzc) != null) {
            return zzca.zzl;
        }
        BillingResult billingResultZzai = zzai();
        this.zzf.zza(zzbx.zzb(25, 25, billingResultZzai));
        return billingResultZzai;
    }

    private BillingClientImpl(Context context, zzcn zzcnVar, PurchasesUpdatedListener purchasesUpdatedListener, String str, String str2, UserChoiceBillingListener userChoiceBillingListener, zzby zzbyVar, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = str;
        initialize(context, purchasesUpdatedListener, zzcnVar, userChoiceBillingListener, str, (zzby) null);
    }

    private BillingClientImpl(String str) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = str;
    }

    BillingClientImpl(String str, Context context, zzby zzbyVar, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        String strZzaj = zzaj();
        this.zzb = strZzaj;
        this.zze = context.getApplicationContext();
        zzha zzhaVarZzz = zzhb.zzz();
        zzhaVarZzz.zzj(strZzaj);
        zzhaVarZzz.zzi(this.zze.getPackageName());
        this.zzf = new zzcd(this.zze, (zzhb) zzhaVarZzz.zzc());
        this.zze.getPackageName();
    }

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzcn zzcnVar, UserChoiceBillingListener userChoiceBillingListener, String str, zzby zzbyVar) {
        this.zze = context.getApplicationContext();
        zzha zzhaVarZzz = zzhb.zzz();
        zzhaVarZzz.zzj(str);
        zzhaVarZzz.zzi(this.zze.getPackageName());
        if (zzbyVar != null) {
            this.zzf = zzbyVar;
        } else {
            this.zzf = new zzcd(this.zze, (zzhb) zzhaVarZzz.zzc());
        }
        if (purchasesUpdatedListener == null) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzd = new zzk(this.zze, purchasesUpdatedListener, null, null, userChoiceBillingListener, this.zzf);
        this.zzz = zzcnVar;
        this.zzA = userChoiceBillingListener != null;
    }

    BillingClientImpl(String str, zzcn zzcnVar, Context context, zzcg zzcgVar, zzby zzbyVar, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = zzaj();
        this.zze = context.getApplicationContext();
        zzha zzhaVarZzz = zzhb.zzz();
        zzhaVarZzz.zzj(zzaj());
        zzhaVarZzz.zzi(this.zze.getPackageName());
        this.zzf = new zzcd(this.zze, (zzhb) zzhaVarZzz.zzc());
        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzd = new zzk(this.zze, null, null, null, null, this.zzf);
        this.zzz = zzcnVar;
        this.zze.getPackageName();
    }

    BillingClientImpl(String str, zzcn zzcnVar, Context context, PurchasesUpdatedListener purchasesUpdatedListener, AlternativeBillingListener alternativeBillingListener, zzby zzbyVar, ExecutorService executorService) {
        String strZzaj = zzaj();
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = strZzaj;
        initialize(context, purchasesUpdatedListener, zzcnVar, alternativeBillingListener, strZzaj, (zzby) null);
    }

    BillingClientImpl(String str, zzcn zzcnVar, Context context, PurchasesUpdatedListener purchasesUpdatedListener, UserChoiceBillingListener userChoiceBillingListener, zzby zzbyVar, ExecutorService executorService) {
        this(context, zzcnVar, purchasesUpdatedListener, zzaj(), null, userChoiceBillingListener, null, null);
    }

    @Override // com.android.billingclient.api.BillingClient
    public final void startConnection(BillingClientStateListener billingClientStateListener) {
        if (isReady()) {
            com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            this.zzf.zzb(zzbx.zzd(6));
            billingClientStateListener.onBillingSetupFinished(zzca.zzl);
            return;
        }
        int i = 1;
        if (this.zza == 1) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            this.zzf.zza(zzbx.zzb(37, 6, zzca.zzd));
            billingClientStateListener.onBillingSetupFinished(zzca.zzd);
            return;
        }
        if (this.zza == 3) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            this.zzf.zza(zzbx.zzb(38, 6, zzca.zzm));
            billingClientStateListener.onBillingSetupFinished(zzca.zzm);
            return;
        }
        this.zza = 1;
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Starting in-app billing setup.");
        this.zzh = new zzay(this, billingClientStateListener, null);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            i = 41;
        } else {
            ResolveInfo resolveInfo = listQueryIntentServices.get(0);
            if (resolveInfo.serviceInfo != null) {
                String str = resolveInfo.serviceInfo.packageName;
                String str2 = resolveInfo.serviceInfo.name;
                if (!"com.android.vending".equals(str) || str2 == null) {
                    com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "The device doesn't have valid Play Store.");
                    i = 40;
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    intent2.putExtra("playBillingLibraryVersion", this.zzb);
                    if (this.zze.bindService(intent2, this.zzh, 1)) {
                        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Service was bonded successfully.");
                        return;
                    } else {
                        com.google.android.gms.internal.play_billing.zzb.zzk("BillingClient", "Connection to Billing service is blocked.");
                        i = 39;
                    }
                }
            }
        }
        this.zza = 0;
        com.google.android.gms.internal.play_billing.zzb.zzj("BillingClient", "Billing service unavailable on device.");
        this.zzf.zza(zzbx.zzb(i, 6, zzca.zzc));
        billingClientStateListener.onBillingSetupFinished(zzca.zzc);
    }
}
