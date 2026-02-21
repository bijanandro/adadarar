package com.android.billingclient.api;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzco {
    zzco(JSONObject jSONObject) throws JSONException {
        jSONObject.getInt("commitmentPaymentsCount");
        jSONObject.optInt("subsequentCommitmentPaymentsCount");
    }
}
