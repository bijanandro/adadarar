package com.unity3d.services.ads.operation.show;

import android.app.Activity;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.ads.operation.AdModule;
import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
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
public class ShowModule extends AdModule<IShowOperation, ShowOperationState> implements IShowModule {
    public static String errorMsgPlacementIdNull = "[UnityAds] Placement ID cannot be null";
    private static IShowModule instance;

    public static IShowModule getInstance() {
        if (instance == null) {
            instance = new ShowModuleDecoratorTimeout(new ShowModule(SDKMetrics.getInstance()), new ConfigurationReader());
        }
        return instance;
    }

    public ShowModule(ISDKMetrics iSDKMetrics) {
        super(iSDKMetrics);
    }

    @Override // com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, final ShowOperationState showOperationState) {
        if (TextUtils.isEmpty(showOperationState.placementId)) {
            sendOnUnityAdsFailedToShow(showOperationState, errorMsgPlacementIdNull, UnityAds.UnityAdsShowError.INVALID_ARGUMENT, true);
            return;
        }
        ShowOperation showOperation = new ShowOperation(showOperationState, new WebViewBridgeInvocation(this._executorService, iWebViewBridgeInvoker, new IWebViewBridgeInvocationCallback() { // from class: com.unity3d.services.ads.operation.show.ShowModule.1
            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onSuccess() {
            }

            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onFailure(String str, CallbackStatus callbackStatus) {
                ShowModule.this.getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowFailure(AdOperationError.callback_error, Long.valueOf(showOperationState.duration())));
                ShowModule.this.sendOnUnityAdsFailedToShow(showOperationState, str, UnityAds.UnityAdsShowError.INTERNAL_ERROR, false);
                ShowModule.this.remove(showOperationState.id);
            }

            @Override // com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocationCallback
            public void onTimeout() {
                ShowModule.this.getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowFailure(AdOperationError.callback_timeout, Long.valueOf(showOperationState.duration())));
                ShowModule.this.sendOnUnityAdsFailedToShow(showOperationState, "[UnityAds] Show Invocation Timeout", UnityAds.UnityAdsShowError.INTERNAL_ERROR, false);
                ShowModule.this.remove(showOperationState.id);
            }
        }));
        ClientProperties.setActivity(showOperationState.activity);
        Activity activity = showOperationState.activity;
        Activity activity2 = showOperationState.activity;
        Display defaultDisplay = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("requestedOrientation", showOperationState.activity.getRequestedOrientation());
            jSONObject3.put("rotation", defaultDisplay.getRotation());
            Point point = new Point();
            defaultDisplay.getSize(point);
            jSONObject3.put("width", point.x);
            jSONObject3.put("height", point.y);
            jSONObject2.put("display", jSONObject3);
            jSONObject2.put("headerBiddingOptions", showOperationState.showOptions.getData());
            jSONObject.put("options", jSONObject2);
            jSONObject.put("listenerId", showOperation.getId());
            jSONObject.put("placementId", showOperationState.placementId);
            jSONObject.put("time", Device.getElapsedRealtime());
            set(showOperation);
            showOperation.invoke(showOperationState.configuration.getWebViewBridgeTimeout(), jSONObject);
        } catch (NullPointerException unused) {
            sendOnUnityAdsFailedToShow(showOperationState, "[UnityAds] Error creating show options", UnityAds.UnityAdsShowError.INTERNAL_ERROR, true);
        } catch (JSONException unused2) {
            sendOnUnityAdsFailedToShow(showOperationState, "[UnityAds] Error creating show options", UnityAds.UnityAdsShowError.INTERNAL_ERROR, true);
        }
    }

    @Override // com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowFailure(String str, UnityAds.UnityAdsShowError unityAdsShowError, String str2) {
        IShowOperation iShowOperation = (IShowOperation) get(str);
        if (iShowOperation == null || iShowOperation.getShowOperationState() == null) {
            return;
        }
        ShowOperationState showOperationState = iShowOperation.getShowOperationState();
        getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowFailure(unityAdsShowError, Long.valueOf(showOperationState.duration())));
        iShowOperation.onUnityAdsShowFailure(showOperationState.placementId, unityAdsShowError, str2);
        remove(str);
    }

    @Override // com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowConsent(String str) {
        IShowOperation iShowOperation = (IShowOperation) get(str);
        if (iShowOperation != null) {
            iShowOperation.getShowOperationState();
        }
    }

    @Override // com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowStart(String str) {
        IShowOperation iShowOperation = (IShowOperation) get(str);
        if (iShowOperation == null || iShowOperation.getShowOperationState() == null) {
            return;
        }
        iShowOperation.onUnityAdsShowStart(iShowOperation.getShowOperationState().placementId);
    }

    @Override // com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowClick(String str) {
        IShowOperation iShowOperation = (IShowOperation) get(str);
        if (iShowOperation == null || iShowOperation.getShowOperationState() == null) {
            return;
        }
        iShowOperation.onUnityAdsShowClick(iShowOperation.getShowOperationState().placementId);
    }

    @Override // com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowComplete(String str, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
        IShowOperation iShowOperation = (IShowOperation) get(str);
        if (iShowOperation == null || iShowOperation.getShowOperationState() == null) {
            return;
        }
        ShowOperationState showOperationState = iShowOperation.getShowOperationState();
        getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowSuccess(Long.valueOf(showOperationState.duration())));
        iShowOperation.onUnityAdsShowComplete(showOperationState.placementId, unityAdsShowCompletionState);
        remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOnUnityAdsFailedToShow(final ShowOperationState showOperationState, final String str, final UnityAds.UnityAdsShowError unityAdsShowError, boolean z) {
        if (showOperationState == null || showOperationState.listener == null) {
            return;
        }
        if (z) {
            getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowFailure(unityAdsShowError, Long.valueOf(showOperationState.duration())));
        }
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.operation.show.ShowModule.2
            @Override // java.lang.Runnable
            public void run() {
                showOperationState.onUnityAdsShowFailure(unityAdsShowError, str);
            }
        });
    }
}
