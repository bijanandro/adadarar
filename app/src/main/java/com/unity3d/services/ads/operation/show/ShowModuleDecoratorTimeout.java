package com.unity3d.services.ads.operation.show;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.request.metrics.AdOperationError;
import com.unity3d.services.core.request.metrics.AdOperationMetric;
import com.unity3d.services.core.timer.BaseTimer;
import com.unity3d.services.core.timer.ITimerListener;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class ShowModuleDecoratorTimeout extends ShowModuleDecorator {
    private static final String errorMsgTimeout = "[UnityAds] Timeout while trying to show ";
    private final ExecutorService _executorService;
    private final boolean _useNewTimer;

    public ShowModuleDecoratorTimeout(IShowModule iShowModule, ConfigurationReader configurationReader) {
        super(iShowModule);
        this._executorService = Executors.newSingleThreadExecutor();
        this._useNewTimer = configurationReader.getCurrentConfiguration().getExperiments().isNewLifecycleTimer();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, ShowOperationState showOperationState) {
        getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowStart());
        showOperationState.start();
        startShowTimeout(showOperationState);
        super.executeAdOperation(iWebViewBridgeInvoker, showOperationState);
    }

    private void startShowTimeout(final ShowOperationState showOperationState) {
        if (!this._useNewTimer) {
            this._executorService.submit(new Runnable() { // from class: com.unity3d.services.ads.operation.show.ShowModuleDecoratorTimeout.2
                @Override // java.lang.Runnable
                public void run() {
                    if (showOperationState.timeoutCV.block(showOperationState.configuration.getShowTimeout())) {
                        return;
                    }
                    ShowModuleDecoratorTimeout.this.onOperationTimeout(showOperationState, UnityAds.UnityAdsShowError.TIMEOUT, ShowModuleDecoratorTimeout.errorMsgTimeout + showOperationState.placementId);
                }
            });
        } else {
            if (showOperationState == null) {
                return;
            }
            showOperationState.timeoutTimer = new BaseTimer(Integer.valueOf(showOperationState.configuration.getShowTimeout()), new ITimerListener() { // from class: com.unity3d.services.ads.operation.show.ShowModuleDecoratorTimeout.1
                @Override // com.unity3d.services.core.timer.ITimerListener
                public void onTimerFinished() {
                    ShowModuleDecoratorTimeout.this.onOperationTimeout(showOperationState, UnityAds.UnityAdsShowError.TIMEOUT, ShowModuleDecoratorTimeout.errorMsgTimeout + showOperationState.placementId);
                }
            });
            showOperationState.timeoutTimer.start(Executors.newSingleThreadScheduledExecutor());
        }
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowConsent(String str) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowConsent(str);
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowFailure(String str, UnityAds.UnityAdsShowError unityAdsShowError, String str2) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowFailure(str, unityAdsShowError, str2);
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowStart(String str) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowStart(str);
    }

    private void releaseOperationTimeoutLock(String str) {
        ShowOperationState showOperationState;
        IShowOperation iShowOperation = get(str);
        if (iShowOperation == null || (showOperationState = iShowOperation.getShowOperationState()) == null) {
            return;
        }
        if (this._useNewTimer) {
            BaseTimer baseTimer = showOperationState.timeoutTimer;
            if (baseTimer == null) {
                return;
            }
            baseTimer.kill();
            return;
        }
        if (iShowOperation.getShowOperationState().timeoutCV == null) {
            return;
        }
        iShowOperation.getShowOperationState().timeoutCV.open();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOperationTimeout(ShowOperationState showOperationState, UnityAds.UnityAdsShowError unityAdsShowError, String str) {
        if (showOperationState != null) {
            getMetricSender().sendMetricWithInitState(AdOperationMetric.newAdShowFailure(AdOperationError.timeout, Long.valueOf(showOperationState.duration())));
            remove(showOperationState.id);
            showOperationState.onUnityAdsShowFailure(unityAdsShowError, str);
        }
    }
}
