package com.android.billingclient.api;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.internal.play_billing.zzhl;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzcf {
    private boolean zza;
    private Transport zzb;

    zzcf(Context context) {
        try {
            TransportRuntime.initialize(context);
            this.zzb = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("PLAY_BILLING_LIBRARY", zzhl.class, Encoding.of("proto"), new Transformer() { // from class: com.android.billingclient.api.zzce
                @Override // com.google.android.datatransport.Transformer
                public final Object apply(Object obj) {
                    return ((zzhl) obj).zzd();
                }
            });
        } catch (Throwable unused) {
            this.zza = true;
        }
    }

    public final void zza(zzhl zzhlVar) {
        if (this.zza) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingLogger", "Skipping logging since initialization failed.");
            return;
        }
        try {
            this.zzb.send(Event.ofData(zzhlVar));
        } catch (Throwable unused) {
            com.google.android.gms.internal.play_billing.zzb.zzk("BillingLogger", "logging failed.");
        }
    }
}
