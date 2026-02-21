package com.unity3d.services.core.request.metrics;

import android.text.TextUtils;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.InitializationStatusReader;
import com.unity3d.services.core.request.WebRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MetricSender extends MetricSenderBase {
    private final MetricCommonTags _commonTags;
    private final ExecutorService _executorService;
    private final String _metricEndpoint;
    private final String _metricSampleRate;

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public boolean areMetricsEnabledForCurrentSession() {
        return true;
    }

    @Override // com.unity3d.services.core.request.metrics.MetricSenderBase, com.unity3d.services.core.request.metrics.ISDKMetrics
    public /* bridge */ /* synthetic */ void sendMetricWithInitState(Metric metric) {
        super.sendMetricWithInitState(metric);
    }

    public MetricSender(Configuration configuration, InitializationStatusReader initializationStatusReader) {
        super(initializationStatusReader);
        this._metricEndpoint = configuration.getMetricsUrl();
        this._executorService = Executors.newSingleThreadExecutor();
        this._metricSampleRate = String.valueOf((int) Math.round(configuration.getMetricSampleRate()));
        MetricCommonTags metricCommonTags = new MetricCommonTags();
        this._commonTags = metricCommonTags;
        metricCommonTags.updateWithConfig(configuration);
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendEvent(String str) {
        sendEvent(str, null);
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendEvent(String str, Map<String, String> map) {
        if (str == null || str.isEmpty()) {
            DeviceLog.debug("Metric event not sent due to being null or empty: " + str);
            return;
        }
        sendEvent(str, null, map);
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendEvent(String str, String str2, Map<String, String> map) {
        sendMetrics(new ArrayList(Collections.singletonList(new Metric(str, str2, map))));
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendMetric(Metric metric) {
        sendMetrics(new ArrayList(Collections.singletonList(metric)));
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendMetrics(final List<Metric> list) {
        if (list == null || list.size() <= 0) {
            DeviceLog.debug("Metrics event not send due to being null or empty");
            return;
        }
        if (TextUtils.isEmpty(this._metricEndpoint)) {
            DeviceLog.debug("Metrics: " + list + " was not sent to null or empty endpoint: " + this._metricEndpoint);
            return;
        }
        if (this._executorService.isShutdown()) {
            DeviceLog.debug("Metrics " + list + " was not sent due to misconfiguration");
            return;
        }
        this._executorService.submit(new Runnable() { // from class: com.unity3d.services.core.request.metrics.MetricSender.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = new JSONObject(new MetricsContainer(MetricSender.this._metricSampleRate, MetricSender.this._commonTags, list).asMap()).toString();
                    WebRequest webRequest = new WebRequest(MetricSender.this._metricEndpoint, "POST", null);
                    webRequest.setBody(string);
                    webRequest.makeRequest();
                    if (webRequest.getResponseCode() / 100 == 2) {
                        DeviceLog.debug("Metric " + list + " sent to " + MetricSender.this._metricEndpoint);
                    } else {
                        DeviceLog.debug("Metric " + list + " failed to send with response code: " + webRequest.getResponseCode());
                    }
                } catch (Exception e) {
                    DeviceLog.debug("Metric " + list + " failed to send from exception: " + e.getMessage());
                }
            }
        });
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public String getMetricEndPoint() {
        return this._metricEndpoint;
    }

    void shutdown() {
        this._commonTags.shutdown();
        this._executorService.shutdown();
    }
}
