package com.android.billingclient.api;

import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public interface ProductDetailsResponseListener {
    void onProductDetailsResponse(BillingResult billingResult, List<ProductDetails> list);
}
