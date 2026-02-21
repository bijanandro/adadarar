package com.unity3d.services.store.listeners;

import com.unity3d.services.store.gpbl.bridges.BillingResultBridge;
import com.unity3d.services.store.gpbl.bridges.PurchaseBridge;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IPurchaseUpdatedResponseListener extends IBillingDataResponseListener<PurchaseBridge> {
    @Override // com.unity3d.services.store.listeners.IBillingDataResponseListener
    void onBillingResponse(BillingResultBridge billingResultBridge, List<PurchaseBridge> list);
}
