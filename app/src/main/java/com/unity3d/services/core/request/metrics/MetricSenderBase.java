package com.unity3d.services.core.request.metrics;

import com.unity3d.services.core.properties.InitializationStatusReader;
import com.unity3d.services.core.properties.SdkProperties;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class MetricSenderBase implements ISDKMetrics {
    private final InitializationStatusReader _initStatusReader;

    public MetricSenderBase(InitializationStatusReader initializationStatusReader) {
        this._initStatusReader = initializationStatusReader;
    }

    @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
    public void sendMetricWithInitState(Metric metric) {
        if (metric == null) {
            return;
        }
        if (metric.getTags() == null) {
            metric = new Metric(metric.getName(), metric.getValue(), new HashMap());
        }
        metric.getTags().put(AdOperationMetric.INIT_STATE, this._initStatusReader.getInitializationStateString(SdkProperties.getCurrentInitializationState()));
        sendMetric(metric);
    }
}
