package com.android.billingclient.api;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcs {
    private final String zza;
    private final String zzb;

    zzcs(JSONObject jSONObject) throws JSONException {
        this.zza = jSONObject.getString("rentalPeriod");
        String strOptString = jSONObject.optString("rentalExpirationPeriod");
        this.zzb = true == strOptString.isEmpty() ? null : strOptString;
    }
}
