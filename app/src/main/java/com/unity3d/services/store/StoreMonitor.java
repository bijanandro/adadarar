package com.unity3d.services.store;

import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.store.core.StoreExceptionHandler;
import com.unity3d.services.store.core.StoreLifecycleListener;
import com.unity3d.services.store.gpbl.BillingResultResponseCode;
import com.unity3d.services.store.gpbl.IBillingClientStateListener;
import com.unity3d.services.store.gpbl.StoreBilling;
import com.unity3d.services.store.gpbl.bridges.BillingResultBridge;
import com.unity3d.services.store.gpbl.bridges.PurchaseBridge;
import com.unity3d.services.store.gpbl.bridges.PurchaseHistoryRecordBridge;
import com.unity3d.services.store.gpbl.bridges.SkuDetailsBridge;
import com.unity3d.services.store.listeners.IPurchaseHistoryResponseListener;
import com.unity3d.services.store.listeners.IPurchaseUpdatedResponseListener;
import com.unity3d.services.store.listeners.ISkuDetailsResponseListener;
import com.unity3d.services.store.listeners.PurchasesResponseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class StoreMonitor {
    private static AtomicBoolean _isInitialized = new AtomicBoolean(false);
    private static StoreLifecycleListener _lifecycleListener;
    private static StoreBilling _storeBilling;
    private static StoreExceptionHandler _storeExceptionHandler;

    public static void initialize(StoreExceptionHandler storeExceptionHandler) throws IllegalAccessException, InterruptedException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        if (_isInitialized.get()) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.INITIALIZATION_REQUEST_RESULT, Integer.valueOf(BillingResultResponseCode.OK.getResponseCode()));
            return;
        }
        _storeExceptionHandler = storeExceptionHandler;
        StoreBilling storeBilling = new StoreBilling(ClientProperties.getApplicationContext(), new IPurchaseUpdatedResponseListener() { // from class: com.unity3d.services.store.StoreMonitor.1
            @Override // com.unity3d.services.store.listeners.IPurchaseUpdatedResponseListener, com.unity3d.services.store.listeners.IBillingDataResponseListener
            public void onBillingResponse(BillingResultBridge billingResultBridge, List<PurchaseBridge> list) {
                if (billingResultBridge.getResponseCode() == BillingResultResponseCode.OK) {
                    JSONArray jSONArray = new JSONArray();
                    if (list != null) {
                        Iterator<PurchaseBridge> it = list.iterator();
                        while (it.hasNext()) {
                            jSONArray.put(it.next().toJson());
                        }
                    }
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASES_UPDATED_RESULT, jSONArray);
                    return;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASES_UPDATED_ERROR, billingResultBridge.getResponseCode());
            }
        });
        _storeBilling = storeBilling;
        storeBilling.initialize(new IBillingClientStateListener() { // from class: com.unity3d.services.store.StoreMonitor.2
            @Override // com.unity3d.services.store.gpbl.IBillingClientStateListener
            public void onBillingSetupFinished(BillingResultBridge billingResultBridge) {
                if (billingResultBridge.getResponseCode() == BillingResultResponseCode.OK) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.INITIALIZATION_REQUEST_RESULT, billingResultBridge.getResponseCode());
                    StoreMonitor._isInitialized.set(true);
                } else {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.INITIALIZATION_REQUEST_FAILED, billingResultBridge.getResponseCode());
                }
            }

            @Override // com.unity3d.services.store.gpbl.IBillingClientStateListener
            public void onBillingServiceDisconnected() {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.DISCONNECTED_RESULT, new Object[0]);
            }
        });
    }

    public static boolean isInitialized() {
        return _isInitialized.get();
    }

    public static int isFeatureSupported(int i, String str) {
        int iIsFeatureSupported = -1;
        try {
            iIsFeatureSupported = _storeBilling.isFeatureSupported(str);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.IS_FEATURE_SUPPORTED_REQUEST_RESULT, Integer.valueOf(i), Integer.valueOf(iIsFeatureSupported));
            return iIsFeatureSupported;
        } catch (Exception e) {
            _storeExceptionHandler.handleStoreException(StoreEvent.IS_FEATURE_SUPPORTED_REQUEST_ERROR, i, e);
            return iIsFeatureSupported;
        }
    }

    public static void getPurchases(int i, String str) {
        try {
            _storeBilling.getPurchases(str, new PurchasesResponseListener(Integer.valueOf(i), StoreEvent.PURCHASES_REQUEST_RESULT, StoreEvent.PURCHASES_REQUEST_ERROR));
        } catch (Exception e) {
            _storeExceptionHandler.handleStoreException(StoreEvent.PURCHASES_REQUEST_ERROR, i, e);
        }
    }

    public static void getPurchaseHistory(final int i, String str, int i2) {
        try {
            _storeBilling.getPurchaseHistory(str, i2, new IPurchaseHistoryResponseListener() { // from class: com.unity3d.services.store.StoreMonitor.3
                @Override // com.unity3d.services.store.listeners.IPurchaseHistoryResponseListener, com.unity3d.services.store.listeners.IBillingDataResponseListener
                public void onBillingResponse(BillingResultBridge billingResultBridge, List<PurchaseHistoryRecordBridge> list) {
                    JSONArray jSONArray = new JSONArray();
                    if (list != null) {
                        Iterator<PurchaseHistoryRecordBridge> it = list.iterator();
                        while (it.hasNext()) {
                            jSONArray.put(it.next().getOriginalJson());
                        }
                    }
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.PURCHASE_HISTORY_LIST_REQUEST_RESULT, Integer.valueOf(i), jSONArray);
                }
            });
        } catch (Exception e) {
            _storeExceptionHandler.handleStoreException(StoreEvent.PURCHASE_HISTORY_LIST_REQUEST_ERROR, i, e);
        }
    }

    public static void getSkuDetails(final int i, String str, ArrayList<String> arrayList) {
        try {
            _storeBilling.getSkuDetails(str, arrayList, new ISkuDetailsResponseListener() { // from class: com.unity3d.services.store.StoreMonitor.4
                @Override // com.unity3d.services.store.listeners.ISkuDetailsResponseListener, com.unity3d.services.store.listeners.IBillingDataResponseListener
                public void onBillingResponse(BillingResultBridge billingResultBridge, List<SkuDetailsBridge> list) {
                    JSONArray jSONArray = new JSONArray();
                    if (list != null) {
                        Iterator<SkuDetailsBridge> it = list.iterator();
                        while (it.hasNext()) {
                            jSONArray.put(it.next().getOriginalJson());
                        }
                    }
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORE, StoreEvent.SKU_DETAILS_LIST_REQUEST_RESULT, Integer.valueOf(i), jSONArray);
                }
            });
        } catch (Exception e) {
            _storeExceptionHandler.handleStoreException(StoreEvent.SKU_DETAILS_LIST_REQUEST_ERROR, i, e);
        }
    }

    public static void startPurchaseTracking(ArrayList<String> arrayList) {
        if (_lifecycleListener != null) {
            stopPurchaseTracking();
        }
        _lifecycleListener = new StoreLifecycleListener(arrayList, _storeBilling);
        ClientProperties.getApplication().registerActivityLifecycleCallbacks(_lifecycleListener);
    }

    public static void stopPurchaseTracking() {
        if (_lifecycleListener != null) {
            ClientProperties.getApplication().unregisterActivityLifecycleCallbacks(_lifecycleListener);
            _lifecycleListener = null;
        }
    }
}
