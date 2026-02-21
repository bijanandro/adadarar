package com.unity3d.services.core.configuration;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.metrics.Metric;
import com.unity3d.services.core.request.metrics.SDKMetrics;
import com.unity3d.services.core.request.metrics.TSIMetric;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class InitializeEventsMetricSender implements IInitializeEventsMetricSender, IInitializationListener {
    private static InitializeEventsMetricSender _instance;
    private long _startTime = 0;
    private long _privacyConfigStartTime = 0;
    private long _privacyConfigEndTime = 0;
    private long _configStartTime = 0;
    private int _configRetryCount = 0;
    private int _webviewRetryCount = 0;
    private boolean _initMetricSent = false;
    private boolean _tokenMetricSent = false;

    public static IInitializeEventsMetricSender getInstance() {
        if (_instance == null) {
            _instance = new InitializeEventsMetricSender();
        }
        return _instance;
    }

    private InitializeEventsMetricSender() {
        InitializationNotificationCenter.getInstance().addListener(this);
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void didInitStart() {
        this._startTime = System.nanoTime();
        this._configRetryCount = 0;
        this._webviewRetryCount = 0;
        sendMetric(TSIMetric.newInitStarted());
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void didConfigRequestStart() {
        this._configStartTime = System.nanoTime();
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void didPrivacyConfigRequestStart() {
        this._privacyConfigStartTime = System.nanoTime();
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void didPrivacyConfigRequestEnd(boolean z) {
        this._privacyConfigEndTime = System.nanoTime();
        sendPrivacyResolutionRequestIfNeeded(z);
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public synchronized void sdkDidInitialize() {
        if (initializationStartTimeStamp().longValue() == 0) {
            DeviceLog.debug("sdkDidInitialize called before didInitStart, skipping metric");
            return;
        }
        if (!this._initMetricSent) {
            sendMetric(TSIMetric.newInitTimeSuccess(duration(), getRetryTags()));
            this._initMetricSent = true;
        }
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public Long initializationStartTimeStamp() {
        return Long.valueOf(this._startTime);
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public synchronized void sdkInitializeFailed(String str, ErrorState errorState) {
        if (this._startTime == 0) {
            DeviceLog.debug("sdkInitializeFailed called before didInitStart, skipping metric");
            return;
        }
        if (!this._initMetricSent) {
            sendMetric(TSIMetric.newInitTimeFailure(duration(), getErrorStateTags(errorState)));
            this._initMetricSent = true;
        }
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public synchronized void sdkTokenDidBecomeAvailableWithConfig(boolean z) {
        if (!this._tokenMetricSent) {
            sendTokenAvailabilityMetricWithConfig(z);
            if (z) {
                sendTokenResolutionRequestMetricIfNeeded();
            }
            this._tokenMetricSent = true;
        }
    }

    private void sendTokenAvailabilityMetricWithConfig(boolean z) {
        Metric metricNewTokenAvailabilityLatencyWebview;
        if (this._startTime == 0) {
            DeviceLog.debug("sendTokenAvailabilityMetricWithConfig called before didInitStart, skipping metric");
            return;
        }
        Long lValueOf = Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this._startTime));
        Map<String, String> retryTags = getRetryTags();
        if (z) {
            metricNewTokenAvailabilityLatencyWebview = TSIMetric.newTokenAvailabilityLatencyConfig(lValueOf, retryTags);
        } else {
            metricNewTokenAvailabilityLatencyWebview = TSIMetric.newTokenAvailabilityLatencyWebview(lValueOf, retryTags);
        }
        sendMetric(metricNewTokenAvailabilityLatencyWebview);
    }

    private void sendTokenResolutionRequestMetricIfNeeded() {
        if (this._configStartTime == 0) {
            DeviceLog.debug("sendTokenResolutionRequestMetricIfNeeded called before didInitStart, skipping metric");
        } else {
            sendMetric(TSIMetric.newTokenResolutionRequestLatency(tokenDuration(), getRetryTags()));
        }
    }

    private void sendPrivacyResolutionRequestIfNeeded(boolean z) {
        if (this._privacyConfigStartTime == 0 || this._privacyConfigEndTime == 0) {
            DeviceLog.debug("sendTokenResolutionRequestMetricIfNeeded called before didInitStart, skipping metric");
        } else if (z) {
            sendMetric(TSIMetric.newPrivacyResolutionRequestLatencySuccess(privacyConfigDuration()));
        } else {
            sendMetric(TSIMetric.newPrivacyResolutionRequestLatencyFailure(privacyConfigDuration()));
        }
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void onRetryConfig() {
        this._configRetryCount++;
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void onRetryWebview() {
        this._webviewRetryCount++;
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public Long duration() {
        return Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this._startTime));
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public Long tokenDuration() {
        return Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this._configStartTime));
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public Long privacyConfigDuration() {
        return Long.valueOf(TimeUnit.NANOSECONDS.toMillis(this._privacyConfigEndTime - this._privacyConfigStartTime));
    }

    public Map<String, String> getErrorStateTags(ErrorState errorState) {
        Map<String, String> retryTags = getRetryTags();
        retryTags.put("stt", errorState.getMetricName());
        return retryTags;
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public Map<String, String> getRetryTags() {
        return new HashMap<String, String>() { // from class: com.unity3d.services.core.configuration.InitializeEventsMetricSender.1
            {
                put("c_retry", String.valueOf(InitializeEventsMetricSender.this._configRetryCount));
                put("wv_retry", String.valueOf(InitializeEventsMetricSender.this._webviewRetryCount));
            }
        };
    }

    @Override // com.unity3d.services.core.configuration.IInitializeEventsMetricSender
    public void sendMetric(Metric metric) {
        SDKMetrics.getInstance().sendMetric(metric);
    }

    @Override // com.unity3d.services.core.configuration.IInitializationListener
    public void onSdkInitialized() {
        sdkDidInitialize();
    }

    @Override // com.unity3d.services.core.configuration.IInitializationListener
    public void onSdkInitializationFailed(String str, ErrorState errorState, int i) {
        sdkInitializeFailed(str, errorState);
    }
}
