package com.android.billingclient.api;

import com.android.billingclient.api.ProductDetails;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.ads.metadata.MediationMetaData;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcu {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final ProductDetails.PricingPhase zzf;

    zzcu(JSONObject jSONObject) throws JSONException {
        this.zza = jSONObject.getString(InAppPurchaseMetaData.KEY_PRODUCT_ID);
        this.zzb = jSONObject.optString("title");
        this.zzc = jSONObject.optString(MediationMetaData.KEY_NAME);
        this.zzd = jSONObject.optString("description");
        this.zze = jSONObject.optString("basePlanId");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("pricingPhase");
        this.zzf = jSONObjectOptJSONObject == null ? null : new ProductDetails.PricingPhase(jSONObjectOptJSONObject);
    }
}
