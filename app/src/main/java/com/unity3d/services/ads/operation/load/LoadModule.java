package com.unity3d.services.ads.operation.load;

import android.text.TextUtils;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.ads.operation.AdModule;
import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.configuration.InitializationNotificationCenter;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.request.metrics.AdOperationError;
import com.unity3d.services.core.request.metrics.AdOperationMetric;
import com.unity3d.services.core.request.metrics.ISDKMetrics;
import com.unity3d.services.core.request.metrics.SDKMetrics;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback;
import com.unity3d.services.core.webview.bridge.invocation.WebViewBridgeInvocation;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LoadModule extends AdModule<ILoadOperation, LoadOperationState> implements ILoadModule {
    private static ILoadModule _instance = null;
    private static final String errorMsgFailedToCreateLoadRequest = "[UnityAds] Failed to create load request";
    private static final String errorMsgInternalCommunicationFailure = "[UnityAds] Internal communication failure";
    private static final String errorMsgInternalCommunicationTimeout = "[UnityAds] Internal communication timeout";
    private static final String errorMsgPlacementIdNull = "[UnityAds] Placement ID cannot be null";

    public static ILoadModule getInstance() {
        if (_instance == null) {
            _instance = new LoadModuleDecoratorTimeout(new LoadModuleDecoratorInitializationBuffer(new LoadModule(SDKMetrics.getInstance()), InitializationNotificationCenter.getInstance()), new ConfigurationReader());
        }
        return _instance;
    }

    public LoadModule(ISDKMetrics iSDKMetrics) {
        super(iSDKMetrics);
    }

    @Override // com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, final LoadOperationState loadOperationState) {
        if (TextUtils.isEmpty(loadOperationState.placementId)) {
            sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INVALID_ARGUMENT, errorMsgPlacementIdNull, true);
            return;
        }
        LoadOperation loadOperation = new LoadOperation(loadOperationState, new WebViewBridgeInvocation(this._executorService, iWebViewBridgeInvoker, new IWebViewBridgeInvocationCallback() { // from class: com.unity3d.services.ads.operation.load.LoadModule.1
            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onSuccess() {
            }

            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onFailure(String str, CallbackStatus callbackStatus) {
                LoadModule.this.getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdLoadFailure(AdOperationError.callback_error, Long.valueOf(loadOperationState.duration())));
                LoadModule.this.sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INTERNAL_ERROR, LoadModule.errorMsgInternalCommunicationFailure, false);
                LoadModule.this.remove(loadOperationState.id);
            }

            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onTimeout() {
                LoadModule.this.getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdLoadFailure(AdOperationError.callback_timeout, Long.valueOf(loadOperationState.duration())));
                LoadModule.this.sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INTERNAL_ERROR, LoadModule.errorMsgInternalCommunicationTimeout, false);
                LoadModule.this.remove(loadOperationState.id);
            }
        }));
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("headerBiddingOptions", loadOperationState.loadOptions.getData());
            jSONObject.put("options", jSONObject2);
            jSONObject.put("listenerId", loadOperation.getId());
            jSONObject.put("placementId", loadOperationState.placementId);
            jSONObject.put("time", Device.getElapsedRealtime());
            set(loadOperation);
            loadOperation.invoke(loadOperationState.configuration.getWebViewBridgeTimeout(), jSONObject);
        } catch (NullPointerException unused) {
            sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INTERNAL_ERROR, errorMsgFailedToCreateLoadRequest, true);
        } catch (JSONException unused2) {
            sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INTERNAL_ERROR, errorMsgFailedToCreateLoadRequest, true);
        }
    }

    @Override // com.unity3d.services.ads.operation.load.ILoadModule
    public void onUnityAdsAdLoaded(String str) {
        ILoadOperation iLoadOperation = (ILoadOperation) get(str);
        if (iLoadOperation == null || iLoadOperation.getLoadOperationState() == null) {
            return;
        }
        LoadOperationState loadOperationState = iLoadOperation.getLoadOperationState();
        getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdLoadSuccess(Long.valueOf(loadOperationState.duration())));
        iLoadOperation.onUnityAdsAdLoaded(loadOperationState.placementId);
        remove(str);
    }

    @Override // com.unity3d.services.ads.operation.load.ILoadModule
    public void onUnityAdsFailedToLoad(String str, UnityAds.UnityAdsLoadError unityAdsLoadError, String str2) {
        ILoadOperation iLoadOperation = (ILoadOperation) get(str);
        if (iLoadOperation == null || iLoadOperation.getLoadOperationState() == null) {
            return;
        }
        LoadOperationState loadOperationState = iLoadOperation.getLoadOperationState();
        getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdLoadFailure(unityAdsLoadError, Long.valueOf(loadOperationState.duration())));
        iLoadOperation.onUnityAdsFailedToLoad(loadOperationState.placementId, unityAdsLoadError, str2);
        remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOnUnityAdsFailedToLoad(final LoadOperationState loadOperationState, final UnityAds.UnityAdsLoadError unityAdsLoadError, final String str, boolean z) {
        if (loadOperationState == null || loadOperationState.listener == null) {
            return;
        }
        if (z) {
            getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdLoadFailure(unityAdsLoadError, Long.valueOf(loadOperationState.duration())));
        }
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.operation.load.LoadModule.2
            @Override // java.lang.Runnable
            public void run() {
                loadOperationState.onUnityAdsFailedToLoad(unityAdsLoadError, str);
            }
        });
    }
}
