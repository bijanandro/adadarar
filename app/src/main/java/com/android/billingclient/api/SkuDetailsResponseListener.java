package com.android.billingclient.api;

import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface SkuDetailsResponseListener {
    void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list);
}
